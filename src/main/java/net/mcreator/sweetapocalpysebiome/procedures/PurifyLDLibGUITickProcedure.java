package net.mcreator.sweetapocalpysebiome.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.ItemStack;
import net.mcreator.sweetapocalpysebiome.FoodCorruptionHelper;
import net.mcreator.sweetapocalpysebiome.PurifyGUIElementCustom;
import net.mcreator.sweetapocalpysebiome.client.gui.ldlib.LDLibGUIContext;
import net.mcreator.sweetapocalpysebiome.network.PurifyGUIStatePacket;

public class PurifyLDLibGUITickProcedure {
	public static void execute(Player player) {
		if (player == null) {
			return;
		}
		// The chamber contents are owned by the server. Never let a client side call mutate them.
		if (player.level().isClientSide()) {
			return;
		}

		// Only process if LDLib GUI is open
		if (!LDLibGUIContext.isOpen(player, net.mcreator.sweetapocalpysebiome.client.gui.PurifyLDLibGUIUI.UI_ID)) {
			return;
		}
		
		// Check for existing purification progress
		PurifyGUIElementCustom.PurifyProgress progress = PurifyGUIElementCustom.getPurificationProgress(player.getUUID());
		if (progress == null) {
			// Idle: keep the batch going - start (or resume) as soon as both inputs are present
			// and the output slot can still accept the next result.
			var chamber = PurifyGUIElementCustom.getServerPurifyInventory(player);
			if (chamber != null && player instanceof ServerPlayer serverPlayer
					&& FoodCorruptionHelper.isPurifyPotion(chamber.getStackInSlot(0))
					&& FoodCorruptionHelper.isCorruptedFood(chamber.getStackInSlot(1))) {
				PurifyGUIElementCustom.PurifyProgress started = PurifyGUIElementCustom.tryAutoStart(serverPlayer, chamber, 60);
				if (started != null) {
					serverPlayer.playSound(net.minecraft.sounds.SoundEvents.BREWING_STAND_BREW, 0.7F, 1.0F);
					serverPlayer.sendSystemMessage(net.minecraft.network.chat.Component.translatable("message.sweetapocalpysebiome.purify.started", started.totalFoodCount));
					progress = started;
				}
			}
		}
		if (progress != null && !progress.isComplete()) {
			float progressValue = progress.getProgress();
			
			// Update progress bar
			LDLibGUIContext.setProgress(player, "PurifyLDLibGUI/progress_bar", progressValue);
			
			// Update progress percentage label
			int percent = (int) (progressValue * 100);
			String percentText = progress.completedFoodCount + "/" + progress.totalFoodCount + " items";
			LDLibGUIContext.setLabelText(player, "PurifyLDLibGUI/progress_percent", percentText);
			String[][] coreFrames = {
				{"✦  →  ◉", "✧  →  ◉"},
				{"✦ → → ◉", "✧ → ✦ → ◉"},
				{"✦  ◉  ✦", "✧  ◉  ✧"},
				{"✧  ✨  ✧", "✦  ✨  ✦"}
			};
			String[] statusFrames = {"Preparing chamber...", "Drawing out corruption...", "Purifying the essence...", "Sealing the clean result..."};
			int phase = Math.min(3, percent / 25);
			int frame = (player.tickCount / 5) % 2;
			String coreText = coreFrames[phase][frame];
			String statusText = statusFrames[phase];
			LDLibGUIContext.setLabelText(player, "PurifyLDLibGUI/core_label", coreText);
			
			// Update button state during purification
			LDLibGUIContext.setButtonText(player, "PurifyLDLibGUI/purify_button", "PURIFYING...");
			LDLibGUIContext.setElementActive(player, "PurifyLDLibGUI/purify_button", false);
			LDLibGUIContext.setLabelText(player, "PurifyLDLibGUI/chamber_title", statusText);
			if (player instanceof ServerPlayer serverPlayer) {
				PurifyGUIStatePacket.send(serverPlayer, progressValue, percentText, coreText, statusText, "PURIFYING...", false);
				if (player.tickCount % 5 == 0 && player.level() instanceof ServerLevel serverLevel) {
					serverLevel.sendParticles(ParticleTypes.ENCHANT, player.getX(), player.getY() + 1.0, player.getZ(), 3, 0.3, 0.35, 0.3, 0.08);
				}
			}
		} else {
			// Reset to ready state
			LDLibGUIContext.setProgress(player, "PurifyLDLibGUI/progress_bar", 0.0);
			LDLibGUIContext.setLabelText(player, "PurifyLDLibGUI/core_label", "◉ CORE ◉");
			var inventory = PurifyGUIElementCustom.getServerPurifyInventory(player);
			if (inventory == null) {
				return;
			}
			ItemStack potionStack = inventory.getStackInSlot(0);
			ItemStack foodStack = inventory.getStackInSlot(1);
			ItemStack outputStack = inventory.getStackInSlot(2);
			boolean hasPotion = FoodCorruptionHelper.isPurifyPotion(potionStack);
			boolean hasFood = FoodCorruptionHelper.isCorruptedFood(foodStack);
			boolean hasInputs = hasPotion && hasFood;
			boolean outputEmpty = outputStack.isEmpty();
			boolean canCollect = !outputEmpty;
			PurifyGUIElementCustom.PurifyBatchStatus batchStatus = PurifyGUIElementCustom.getPurifyBatchStatus(player.getUUID());
			int shownTotal = batchStatus.total() > 0 ? batchStatus.total() : foodStack.getCount();
			int shownCompleted = Math.min(batchStatus.completed(), shownTotal);
			String countText = shownCompleted + "/" + shownTotal + " items";
			LDLibGUIContext.setLabelText(player, "PurifyLDLibGUI/progress_percent", countText);
			String statusText;
			if (hasInputs && !outputEmpty) {
				statusText = "Collect result to continue";
			} else if (hasInputs) {
				statusText = "Cannot purify this food";
			} else if (!outputEmpty) {
				statusText = "Ready: " + outputStack.getHoverName().getString();
			} else if (!hasPotion) {
				statusText = "Add a Purify Potion";
			} else {
				statusText = "Add corrupted food";
			}
			String buttonText = canCollect ? "COLLECT RESULT" : hasInputs ? "PURIFY" : "ADD INGREDIENTS";
			boolean buttonActive = canCollect || hasInputs;
			LDLibGUIContext.setButtonText(player, "PurifyLDLibGUI/purify_button", buttonText);
			LDLibGUIContext.setElementActive(player, "PurifyLDLibGUI/purify_button", buttonActive);
			LDLibGUIContext.setLabelText(player, "PurifyLDLibGUI/chamber_title", statusText);
			if (player instanceof ServerPlayer serverPlayer) {
				PurifyGUIStatePacket.send(serverPlayer, 0.0F, countText, "◉ CORE ◉", statusText, buttonText, buttonActive);
			}
		}
	}
}