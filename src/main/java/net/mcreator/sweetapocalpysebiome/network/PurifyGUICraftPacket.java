package net.mcreator.sweetapocalpysebiome.network;

import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.IItemHandlerModifiable;

import com.lowdragmc.lowdraglib2.gui.holder.ModularUIContainerMenu;

import net.mcreator.sweetapocalpysebiome.SweetapocalpysebiomeMod;
import net.mcreator.sweetapocalpysebiome.world.inventory.PurifyGUIMenu;
import net.mcreator.sweetapocalpysebiome.client.gui.PurifyLDLibGUIUI;
import net.mcreator.sweetapocalpysebiome.client.gui.ldlib.LDLibGUIContext;
import net.mcreator.sweetapocalpysebiome.FoodCorruptionHelper;
import net.mcreator.sweetapocalpysebiome.PurifyGUIElementCustom;
import net.mcreator.sweetapocalpysebiome.PurifyGUIElementCustom.PurifyProgress;
import net.mcreator.sweetapocalpysebiome.procedures.CorruptionAdvancementsProcedure;
import net.mcreator.sweetapocalpysebiome.procedures.PurifyGUIConvertProcedure;

public class PurifyGUICraftPacket implements CustomPacketPayload {
	public static final Type<PurifyGUICraftPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(SweetapocalpysebiomeMod.MODID, "purify_gui_craft"));
	public static final StreamCodec<RegistryFriendlyByteBuf, PurifyGUICraftPacket> STREAM_CODEC = StreamCodec.of((buffer, message) -> {
	}, buffer -> new PurifyGUICraftPacket());
	private static final int PURIFY_DURATION_TICKS = 60;

	@Override
	public Type<PurifyGUICraftPacket> type() {
		return TYPE;
	}

	public static void handleData(final PurifyGUICraftPacket message, final IPayloadContext context) {
		context.enqueueWork(() -> {
			if (context.flow() != PacketFlow.SERVERBOUND || !(context.player() instanceof ServerPlayer player)) {
				return;
			}
			IItemHandler inventory;
			if (player.containerMenu instanceof PurifyGUIMenu menu) {
				inventory = menu.getInternalHandler();
			} else if (isPurifyGuiOpen(player)) {
				inventory = PurifyGUIElementCustom.getServerPurifyInventory(player);
			} else {
				// The GUI is not open (any more): never act on hidden machine state.
				return;
			}
			if (inventory == null || inventory.getSlots() < 3 || !(inventory instanceof IItemHandlerModifiable modifiableInventory)) {
				return;
			}
			ItemStack potion = inventory.getStackInSlot(0);
			ItemStack food = inventory.getStackInSlot(1);
			PurifyProgress existingProgress = PurifyGUIElementCustom.getPurificationProgress(player.getUUID());
			if (existingProgress != null) {
				player.sendSystemMessage(Component.literal(existingProgress.isComplete() ? "Purification is finishing..." : "Purification in progress..."));
				return;
			}
			if (!inventory.getStackInSlot(2).isEmpty()) {
				// COLLECT RESULT: the chamber is emptied before the items are handed over, so a
				// double click (or two packets in the same tick) can never duplicate the result.
				int collected = PurifyGUIElementCustom.collectOutput(player, modifiableInventory);
				if (collected > 0) {
					player.playSound(net.minecraft.sounds.SoundEvents.ITEM_PICKUP, 0.8F, 1.0F);
					player.sendSystemMessage(Component.literal("Collected " + collected + " purified item(s)."));
				}
				// Collecting the result also resumes an unfinished batch (keeping its counters).
				PurifyGUIElementCustom.tryAutoStart(player, modifiableInventory, PURIFY_DURATION_TICKS);
				return;
			}

			if (!FoodCorruptionHelper.isPurifyPotion(potion) || !FoodCorruptionHelper.isCorruptedFood(food)) {
				player.sendSystemMessage(Component.literal("Need a Purify Potion in slot 0 and corrupted food in slot 1."));
				return;
			}
			PurifyProgress progress = PurifyGUIElementCustom.tryAutoStart(player, modifiableInventory, PURIFY_DURATION_TICKS);
			if (progress != null) {
				player.sendSystemMessage(Component.literal("Purification started..."));
				player.playSound(net.minecraft.sounds.SoundEvents.BREWING_STAND_BREW, 0.7F, 1.0F);
			} else {
				player.sendSystemMessage(Component.literal("No purification recipe available for this item."));
			}
		}).exceptionally(e -> {
			context.connection().disconnect(Component.literal(e.getMessage()));
			return null;
		});
	}

	/** True when the LDLib purify GUI is the players currently open container. */
	private static boolean isPurifyGuiOpen(ServerPlayer player) {
		return LDLibGUIContext.isOpen(player, PurifyLDLibGUIUI.UI_ID) && player.containerMenu instanceof ModularUIContainerMenu;
	}

	public static void send() {
		PacketDistributor.sendToServer(new PurifyGUICraftPacket());
	}
}
