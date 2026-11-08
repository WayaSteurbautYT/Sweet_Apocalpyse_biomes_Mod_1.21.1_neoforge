/*
 * The code of this mod element is always locked.
 *
 * You can register new events in this class too.
 *
 * If you want to make a plain independent class, create it using
 * Project Browser -> New... and make sure to make the class
 * outside net.mcreator.sweetapocalpysebiome as this package is managed by MCreator.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
 *
 * This class will be added in the mod root package.
*/
package net.mcreator.sweetapocalpysebiome;

import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.event.entity.player.PlayerContainerEvent;
import net.minecraft.sounds.SoundEvents;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.IItemHandlerModifiable;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.core.BlockPos;

import net.mcreator.sweetapocalpysebiome.init.SweetapocalpysebiomeModItems;
import net.mcreator.sweetapocalpysebiome.init.SweetapocalpysebiomeModMobEffects;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@EventBusSubscriber
public class PurifyGUIElementCustom {
	public PurifyGUIElementCustom() {
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		new PurifyGUIElementCustom();
		registerRecipes(event);
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
	}

	@EventBusSubscriber
	private static class PurifyGUIElementCustomForgeBusEvents {
		@SubscribeEvent
		public static void serverLoad(ServerStartingEvent event) {
		}
	}

	@EventBusSubscriber
	private static class PurifyProgressTicker {
		@SubscribeEvent
		public static void onServerTick(ServerTickEvent.Post event) {
			// Advance every running purification by one server tick. Using ticks instead of wall clock
			// time keeps the progress bar in sync with the world (and pauses with it).
			for (PurifyProgress running : activePurifications.values()) {
				running.elapsedTicks++;
			}
			for (PurifyProgress progress : new java.util.ArrayList<>(activePurifications.values())) {
				if (!progress.isComplete()) {
					continue;
				}
				if (!activePurifications.remove(progress.playerId, progress)) {
					continue;
				}
				if (event.getServer().getPlayerList().getPlayer(progress.playerId) instanceof net.minecraft.server.level.ServerPlayer player) {
					int completedCount = progress.completedFoodCount;
					if (net.mcreator.sweetapocalpysebiome.procedures.PurifyGUIConvertProcedure.execute(player, progress.inventory, progress.purifyPotion, progress.corruptedFood)) {
						player.playSound(SoundEvents.AMETHYST_BLOCK_CHIME, 1.0F, 1.4F);
						ItemStack potion = progress.inventory.getStackInSlot(0);
						ItemStack food = progress.inventory.getStackInSlot(1);
						completedCount++;
						PurifyBatchStatus status = new PurifyBatchStatus(completedCount, progress.totalFoodCount);
						purifyBatchStatuses.put(player.getUUID(), status);
						player.displayClientMessage(net.minecraft.network.chat.Component.translatable("message.sweetapocalpysebiome.purify.count", completedCount, progress.totalFoodCount), true);
						if (FoodCorruptionHelper.isPurifyPotion(potion) && FoodCorruptionHelper.isCorruptedFood(food)) {
							// Keep the batch running: start the next item as soon as the output slot can
							// accept it, keeping the original batch counters (3/16 stays 3/16).
							int[] counters = batchCounters(player.getUUID(), food.getCount());
							if (startPurification(player.getUUID(), food, potion, progress.durationTicks, counters[0], counters[1], progress.inventory) == null) {
								// Output slot is full: the batch pauses until the player collects the result.
								player.sendSystemMessage(net.minecraft.network.chat.Component.translatable("message.sweetapocalpysebiome.purify.output_full"));
							}
						} else {
							player.sendSystemMessage(net.minecraft.network.chat.Component.translatable("message.sweetapocalpysebiome.purify.complete", completedCount, progress.totalFoodCount));
						}
					} else {
						player.sendSystemMessage(net.minecraft.network.chat.Component.literal("Purification could not finish. Check the chamber slots."));
					}
				}
			}
			for (net.minecraft.server.level.ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
				syncGuiState(player);
			}
		}

		/** Pushes the chamber state to whichever purify GUI the player currently has open. */
		private static void syncGuiState(net.minecraft.server.level.ServerPlayer player) {
			if (net.mcreator.sweetapocalpysebiome.client.gui.ldlib.LDLibGUIContext.isOpen(player, net.mcreator.sweetapocalpysebiome.client.gui.PurifyLDLibGUIUI.UI_ID)) {
				net.mcreator.sweetapocalpysebiome.procedures.PurifyLDLibGUITickProcedure.execute(player);
				return;
			}
			if (!(player.containerMenu instanceof net.mcreator.sweetapocalpysebiome.world.inventory.PurifyGUIMenu menu)) {
				return;
			}
			net.neoforged.neoforge.items.IItemHandler inventory = menu.getInternalHandler();
			PurifyProgress progress = getPurificationProgress(player.getUUID());
			ItemStack potionStack = inventory.getStackInSlot(0);
			ItemStack foodStack = inventory.getStackInSlot(1);
			ItemStack outputStack = inventory.getStackInSlot(2);
			boolean outputEmpty = outputStack.isEmpty();
			boolean hasPotion = FoodCorruptionHelper.isPurifyPotion(potionStack);
			boolean hasFood = FoodCorruptionHelper.isCorruptedFood(foodStack);
			if (progress == null && hasPotion && hasFood && inventory instanceof IItemHandlerModifiable modifiable) {
				// Start (or resume) the batch automatically while inputs are present. tryAutoStart
				// only succeeds when the output slot can accept another result, so a full output
				// slot simply pauses the batch until the player collects it.
				progress = tryAutoStart(player, modifiable, 60);
				if (progress != null) {
					player.sendSystemMessage(net.minecraft.network.chat.Component.translatable("message.sweetapocalpysebiome.purify.started", progress.totalFoodCount));
					player.playSound(SoundEvents.BREWING_STAND_BREW, 0.7F, 1.0F);
				}
			}
			PurifyBatchStatus status = getPurifyBatchStatus(player.getUUID());
			int shownTotal = status.total() > 0 ? status.total() : foodStack.getCount();
			String count = status.completed() + "/" + shownTotal + " items";
			String statusText;
			if (progress != null) {
				statusText = "Purifying: " + foodStack.getHoverName().getString();
			} else if (hasPotion && hasFood && !outputEmpty) {
				statusText = "Collect result to continue";
			} else if (!outputEmpty) {
				statusText = "Ready: " + outputStack.getHoverName().getString();
			} else if (!hasPotion) {
				statusText = "Add a Purify Potion";
			} else if (!hasFood) {
				statusText = "Add corrupted food";
			} else {
				statusText = "Cannot purify this food";
			}
			String buttonText = progress != null ? "PURIFYING..." : !outputEmpty ? "COLLECT RESULT" : "PURIFY";
			boolean active = progress == null && (!outputEmpty || (hasPotion && hasFood));
			net.mcreator.sweetapocalpysebiome.network.PurifyGUIStatePacket.send(player, progress == null ? 0.0F : progress.getProgress(), count, "◉ CORE ◉", statusText, buttonText, active);
		}

		@SubscribeEvent
		public static void onContainerClose(PlayerContainerEvent.Close event) {
			Player player = event.getEntity();
			// Only the client side mirror is dropped here. The server side chamber is the machine's
			// real storage and has to survive closing the GUI: deleting it on close is what used to
			// desynchronise - and eventually duplicate or void - the chamber contents.
			if (player.level().isClientSide) {
				clientInventories.remove(player.getUUID());
			}
		}
	}

	private static final Map<UUID, PurifyProgress> activePurifications = new ConcurrentHashMap<>();
	/**
	 * Server side, authoritative purification chambers. Only ever touched by the server thread.
	 * <p>
	 * These instances must never reach the client: a single mutable container shared by both sides
	 * (which does happen in every singleplayer world, because statics are shared) is exactly what
	 * lets a GUI duplicate items.
	 */
	private static final Map<UUID, ItemStackHandler> serverInventories = new ConcurrentHashMap<>();
	/**
	 * Client side mirrors of the chambers. Display buffers only - the server keeps them in sync
	 * through the container menu and the client never authors their contents.
	 */
	private static final Map<UUID, ItemStackHandler> clientInventories = new ConcurrentHashMap<>();
	private static final Map<UUID, PurifyBatchStatus> purifyBatchStatuses = new ConcurrentHashMap<>();
	private static final Map<UUID, BlockPos> purifierBlocks = new ConcurrentHashMap<>();

	/** Authoritative chamber of a player, or {@code null} when called from the client. */
	public static ItemStackHandler getServerPurifyInventory(Player player) {
		if (player == null || player.level().isClientSide()) {
			return null;
		}
		return orCreateServerInventory(player.getUUID());
	}

	public static ItemStackHandler orCreateServerInventory(UUID playerId) {
		return serverInventories.computeIfAbsent(playerId, id -> new ItemStackHandler(3) {
			private boolean isInputLocked(int slot) {
				return slot >= 0 && slot < 2 && activePurifications.containsKey(id);
			}

			@Override
			public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
				return isInputLocked(slot) ? stack : super.insertItem(slot, stack, simulate);
			}

			@Override
			public ItemStack extractItem(int slot, int amount, boolean simulate) {
				return isInputLocked(slot) ? ItemStack.EMPTY : super.extractItem(slot, amount, simulate);
			}

			@Override
			public void setStackInSlot(int slot, ItemStack stack) {
				if (!isInputLocked(slot)) {
					super.setStackInSlot(slot, stack);
				}
			}

			@Override
			public boolean isItemValid(int slot, ItemStack stack) {
				return !isInputLocked(slot) && switch (slot) {
					case 0 -> FoodCorruptionHelper.isPurifyPotion(stack);
					case 1 -> FoodCorruptionHelper.isCorruptedFood(stack);
					default -> false;
				};
			}
		});
	}

	/** Kept for compatibility: always resolves to the authoritative (server side) chamber. */
	public static ItemStackHandler getOrCreatePurifyInventory(UUID playerId) {
		return orCreateServerInventory(playerId);
	}

	/**
	 * Chamber used by GUIs. On the server this is the authoritative chamber, on the client it is the
	 * local mirror that the menu keeps in sync. The two are deliberately different instances so that
	 * no client side click can ever write into (and duplicate out of) the real machine contents.
	 */
	public static ItemStackHandler getOrCreatePurifyInventory(Player player) {
		if (player == null) {
			return new ItemStackHandler(3);
		}
		if (player.level().isClientSide()) {
			return clientInventories.computeIfAbsent(player.getUUID(), id -> new ItemStackHandler(3));
		}
		return orCreateServerInventory(player.getUUID());
	}

	/**
	 * Server side only. Moves the whole output slot into the player inventory.
	 *
	 * @return the amount of items that were really collected (0 when there was nothing to collect)
	 */
	public static int collectOutput(net.minecraft.server.level.ServerPlayer player, IItemHandlerModifiable inventory) {
		if (player == null || inventory == null || inventory.getSlots() < 3) {
			return 0;
		}
		// Extract first, hand out second. Giving the items out before the slot is emptied is the
		// classic way a GUI ends up duplicating its output.
		ItemStack collected = inventory.extractItem(2, Integer.MAX_VALUE, false);
		if (collected.isEmpty() || !inventory.getStackInSlot(2).isEmpty()) {
			return 0;
		}
		player.getInventory().placeItemBackInInventory(collected);
		return collected.getCount();
	}

	public static void bindInventoryToBlock(Player player, BlockPos pos) {
		if (player != null && pos != null) {
			purifierBlocks.put(player.getUUID(), pos.immutable());
		}
	}

	public static void onPurifierBlockBroken(net.minecraft.world.level.Level level, BlockPos pos) {
		if (level == null || level.isClientSide || pos == null) {
			return;
		}
		for (Map.Entry<UUID, BlockPos> entry : new java.util.ArrayList<>(purifierBlocks.entrySet())) {
			if (!pos.equals(entry.getValue()) || !purifierBlocks.remove(entry.getKey(), entry.getValue())) {
				continue;
			}
			PurifyProgress progress = activePurifications.remove(entry.getKey());
			ItemStackHandler inventory = serverInventories.remove(entry.getKey());
			purifyBatchStatuses.remove(entry.getKey());
			if (inventory == null && progress != null && progress.inventory instanceof ItemStackHandler handler) {
				inventory = handler;
			}
			if (inventory == null) {
				continue;
			}
			// Drop exactly what is inside the chamber. (The old code replaced the stored potions with
			// glass bottles before dropping them, which conjured up items that were never there.)
			for (int slot = 0; slot < inventory.getSlots(); slot++) {
				ItemStack stack = inventory.extractItem(slot, Integer.MAX_VALUE, false);
				if (!stack.isEmpty()) {
					level.addFreshEntity(new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, stack));
				}
			}
		}
	}

	public static class PurifyProgress {
		public final UUID playerId;
		public final ItemStack corruptedFood;
		public final ItemStack purifyPotion;
		public final ItemStack purifiedResult;
		public final IItemHandlerModifiable inventory;
		public final int durationTicks;
		public final int totalFoodCount;
		public final int completedFoodCount;
		private int elapsedTicks = 0;

		public PurifyProgress(UUID playerId, ItemStack corruptedFood, ItemStack purifyPotion, ItemStack purifiedResult, int durationTicks, int totalFoodCount, int completedFoodCount, IItemHandlerModifiable inventory) {
			this.playerId = playerId;
			this.corruptedFood = corruptedFood.copy();
			this.purifyPotion = purifyPotion.copy();
			this.purifiedResult = purifiedResult.copy();
			this.inventory = inventory;
			this.durationTicks = durationTicks;
			this.totalFoodCount = totalFoodCount;
			this.completedFoodCount = completedFoodCount;
		}

		public float getProgress() {
			if (durationTicks <= 0) {
				return 1.0F;
			}
			return Math.max(0.0F, Math.min(1.0F, (float) elapsedTicks / (float) durationTicks));
		}

		public int getRemainingTicks() {
			return Math.max(0, durationTicks - elapsedTicks);
		}

		public boolean isComplete() {
			return elapsedTicks >= durationTicks;
		}
	}

	public static class PurifyRecipe {
		public final ItemStack corruptedInput;
		public final ItemStack purifiedOutput;

		public PurifyRecipe(ItemStack corruptedInput, ItemStack purifiedOutput) {
			this.corruptedInput = corruptedInput;
			this.purifiedOutput = purifiedOutput;
		}
	}

	private static final Map<String, PurifyRecipe> purifyRecipes = new HashMap<>();

	@SubscribeEvent
	public static void registerRecipes(net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			registerRecipe(new PurifyRecipe(
				new ItemStack(SweetapocalpysebiomeModItems.CORRUPTED_COOKIE.get()),
				new ItemStack(Items.COOKIE)
			));
			registerRecipe(new PurifyRecipe(
				new ItemStack(SweetapocalpysebiomeModItems.CHOCOLATECORRUPTED.get()),
				new ItemStack(SweetapocalpysebiomeModItems.CHOCOLATEFRAGMENT.get())
			));
		});
	}

	public static void registerRecipe(PurifyRecipe recipe) {
		String key = getItemKey(recipe.corruptedInput);
		if (key != null) {
			purifyRecipes.put(key, recipe);
		}
	}

	private static String getItemKey(ItemStack stack) {
		if (stack == null || stack.isEmpty()) {
			return null;
		}
		return stack.getItem().toString();
	}

	public static PurifyRecipe getRecipeForCorruptedFood(ItemStack corruptedFood) {
		if (corruptedFood == null || corruptedFood.isEmpty()) {
			return null;
		}
		String key = getItemKey(corruptedFood);
		return purifyRecipes.get(key);
	}

	public static ItemStack getPurifiedResult(ItemStack corruptedFood) {
		PurifyRecipe recipe = getRecipeForCorruptedFood(corruptedFood);
		if (recipe != null) {
			ItemStack result = recipe.purifiedOutput.copy();
			result.setCount(corruptedFood.getCount());
			markAsPurified(result);
			return result;
		}
		return ItemStack.EMPTY;
	}

	public static void markAsPurified(ItemStack stack) {
		if (stack == null || stack.isEmpty()) {
			return;
		}
		CompoundTag tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
		tag.putBoolean("Corrupted", false);
		tag.putBoolean("Purified", true);
		tag.putInt("PurifyTimer", 0);
		stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
	}

	public static PurifyProgress startPurification(UUID playerId, ItemStack corruptedFood, ItemStack purifyPotion, int durationTicks, IItemHandlerModifiable inventory) {
		return startPurification(playerId, corruptedFood, purifyPotion, durationTicks, corruptedFood.getCount(), 0, inventory);
	}

	public record PurifyBatchStatus(int completed, int total) {
	}

	public static PurifyBatchStatus getPurifyBatchStatus(UUID playerId) {
		return purifyBatchStatuses.getOrDefault(playerId, new PurifyBatchStatus(0, 0));
	}

	public static PurifyProgress startPurification(UUID playerId, ItemStack corruptedFood, ItemStack purifyPotion, int durationTicks, int totalFoodCount, int completedFoodCount, IItemHandlerModifiable inventory) {
		if (playerId == null || activePurifications.containsKey(playerId) || inventory == null || inventory.getSlots() < 3
				|| !ItemStack.matches(inventory.getStackInSlot(0), purifyPotion) || !ItemStack.matches(inventory.getStackInSlot(1), corruptedFood)
				|| !FoodCorruptionHelper.isPurifyPotion(purifyPotion) || !FoodCorruptionHelper.isCorruptedFood(corruptedFood)
				|| !net.mcreator.sweetapocalpysebiome.procedures.PurifyGUIConvertProcedure.canAcceptOutput(inventory, corruptedFood)) {
			return null;
		}
		ItemStack purifiedResult = getPurifiedResult(corruptedFood);
		if (purifiedResult.isEmpty()) {
			return null;
		}
		PurifyProgress progress = new PurifyProgress(playerId, corruptedFood, purifyPotion, purifiedResult, durationTicks, totalFoodCount, completedFoodCount, inventory);
		if (activePurifications.putIfAbsent(playerId, progress) != null) {
			return null;
		}
		purifyBatchStatuses.put(playerId, new PurifyBatchStatus(completedFoodCount, totalFoodCount));
		return progress;
	}

	/**
	 * Batch counters for the item that starts next: an unfinished batch keeps its original total
	 * (growing it when more food was added), so the "x/y items" message stays correct even after
	 * a pause caused by a full output slot.
	 *
	 * @return {@code {totalFoodCount, completedFoodCount}} for the next item
	 */
	public static int[] batchCounters(UUID playerId, int remainingFood) {
		PurifyBatchStatus status = purifyBatchStatuses.getOrDefault(playerId, new PurifyBatchStatus(0, 0));
		if (status.total() > status.completed()) {
			return new int[] { Math.max(status.total(), status.completed() + remainingFood), status.completed() };
		}
		return new int[] { Math.max(remainingFood, 1), 0 };
	}

	/**
	 * Server side only. Starts (or resumes) purification while both inputs are present and the
	 * output slot can still accept the result. This is what keeps a batch running until every
	 * corrupted item is purified: it is called after every completed item and whenever the GUI
	 * ticks or the player collects the result.
	 *
	 * @return the started progress, or {@code null} when nothing could start (inputs missing,
	 *         no recipe, output slot full, or a purification is already running)
	 */
	public static PurifyProgress tryAutoStart(net.minecraft.server.level.ServerPlayer player, IItemHandlerModifiable inventory, int durationTicks) {
		if (player == null || inventory == null || inventory.getSlots() < 3 || activePurifications.containsKey(player.getUUID())) {
			return null;
		}
		ItemStack potion = inventory.getStackInSlot(0);
		ItemStack food = inventory.getStackInSlot(1);
		if (!FoodCorruptionHelper.isPurifyPotion(potion) || !FoodCorruptionHelper.isCorruptedFood(food)) {
			return null;
		}
		int[] counters = batchCounters(player.getUUID(), food.getCount());
		return startPurification(player.getUUID(), food, potion, durationTicks, counters[0], counters[1], inventory);
	}

	public static PurifyProgress getPurificationProgress(UUID playerId) {
		return activePurifications.get(playerId);
	}

	public static PurifyProgress completePurification(UUID playerId) {
		PurifyProgress progress = activePurifications.remove(playerId);
		return progress;
	}

	public static void cancelPurification(UUID playerId) {
		activePurifications.remove(playerId);
	}
}