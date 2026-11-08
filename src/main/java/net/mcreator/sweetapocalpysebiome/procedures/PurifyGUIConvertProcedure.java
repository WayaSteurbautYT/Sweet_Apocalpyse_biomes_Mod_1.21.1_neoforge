package net.mcreator.sweetapocalpysebiome.procedures;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.sweetapocalpysebiome.FoodCorruptionHelper;
import net.mcreator.sweetapocalpysebiome.PurifyGUIElementCustom;
import net.mcreator.sweetapocalpysebiome.init.SweetapocalpysebiomeModItems;
import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.IItemHandler;

public class PurifyGUIConvertProcedure {
	public static boolean execute(Player player, IItemHandler handler, ItemStack expectedPotion, ItemStack expectedFood) {
		if (player == null || player.level().isClientSide() || handler == null || handler.getSlots() < 3) {
			return false;
		}
		ItemStack potion = handler.getStackInSlot(0);
		ItemStack food = handler.getStackInSlot(1);
		ItemStack output = handler.getStackInSlot(2);
		if (!ItemStack.matches(potion, expectedPotion) || !ItemStack.matches(food, expectedFood)
				|| !FoodCorruptionHelper.isPurifyPotion(potion) || !FoodCorruptionHelper.isCorruptedFood(food) || !canAcceptOutput(handler, food)) {
			return false;
		}
		if (!(handler instanceof IItemHandlerModifiable modifiable)) {
			return false;
		}
		ItemStack corruptedItem = modifiable.extractItem(1, 1, false);
		if (corruptedItem.isEmpty()) {
			return false;
		}
		ItemStack result = purifiedVariantOf(corruptedItem);
		if (result.isEmpty()) {
			return false;
		}
		FoodCorruptionHelper.markPurified(result);
		// Output first, potion last: exactly one food item turns into exactly one purified item.
		ItemStack currentOutput = modifiable.getStackInSlot(2);
		if (currentOutput.isEmpty()) {
			modifiable.setStackInSlot(2, result);
		} else {
			ItemStack mergedOutput = currentOutput.copy();
			mergedOutput.grow(result.getCount());
			modifiable.setStackInSlot(2, mergedOutput);
		}
		if (modifiable.getStackInSlot(1).isEmpty()) {
			consumeOnePotion(modifiable);
		}
		if (player instanceof ServerPlayer serverPlayer) {
			AdvancementHolder firstPurification = serverPlayer.server.getAdvancements().get(ResourceLocation.parse("sweetapocalpysebiome:purify_food_gui"));
			boolean firstRun = firstPurification != null && !serverPlayer.getAdvancements().getOrStartProgress(firstPurification).isDone();
			CorruptionAdvancementsProcedure.award(serverPlayer, "purify_food_gui");
			int purifiedCount = serverPlayer.getPersistentData().getInt("sweetapocalpysebiome_purified_food_count") + 1;
			serverPlayer.getPersistentData().putInt("sweetapocalpysebiome_purified_food_count", purifiedCount);
			if (purifiedCount >= 16) {
				CorruptionAdvancementsProcedure.award(serverPlayer, "purify_food_batch");
			}
			if (firstRun) {
				serverPlayer.displayClientMessage(Component.translatable("message.sweetapocalpysebiome.purify.first_complete"), true);
			}
		}
		return true;
	}

	/**
	 * The normal (purified) counterpart of a corrupted food item, without touching any inventory.
	 */
	public static ItemStack purifiedVariantOf(ItemStack corruptedItem) {
		if (corruptedItem == null || corruptedItem.isEmpty()) {
			return ItemStack.EMPTY;
		}
		ItemStack result;
		if (corruptedItem.is(SweetapocalpysebiomeModItems.CORRUPTED_COOKIE.get())) {
			result = new ItemStack(Items.COOKIE);
		} else if (corruptedItem.is(SweetapocalpysebiomeModItems.CHOCOLATECORRUPTED.get())) {
			result = new ItemStack(SweetapocalpysebiomeModItems.CHOCOLATEFRAGMENT.get());
		} else if (FoodCorruptionHelper.isCorruptedFood(corruptedItem)) {
			result = corruptedItem.copy();
		} else {
			return ItemStack.EMPTY;
		}
		return result;
	}

	/**
	 * Takes exactly one potion out of the input slot. Purify potions are drunk from a bottle, so
	 * once the last potion of the stack is consumed an empty glass bottle is left behind for the
	 * player to collect. Left over potions keep their slot.
	 */
	private static void consumeOnePotion(IItemHandlerModifiable inventory) {
		ItemStack potion = inventory.getStackInSlot(0);
		if (!FoodCorruptionHelper.isPurifyPotion(potion)) {
			return;
		}
		if (inventory.extractItem(0, 1, false).isEmpty()) {
			return;
		}
		if (inventory.getStackInSlot(0).isEmpty()) {
			inventory.setStackInSlot(0, new ItemStack(Items.GLASS_BOTTLE));
		}
	}

	public static boolean canAcceptOutput(IItemHandler handler, ItemStack corruptedFood) {
		if (handler == null || handler.getSlots() < 3) {
			return false;
		}
		ItemStack result = purifiedVariantOf(corruptedFood);
		if (result.isEmpty()) {
			return false;
		}
		ItemStack output = handler.getStackInSlot(2);
		if (output.isEmpty()) {
			return true;
		}
		int maxCount = Math.min(result.getMaxStackSize(), handler.getSlotLimit(2));
		return ItemStack.isSameItemSameComponents(output, result) && output.getCount() < maxCount;
	}
}
