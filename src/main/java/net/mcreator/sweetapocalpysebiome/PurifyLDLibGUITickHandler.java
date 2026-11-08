package net.mcreator.sweetapocalpysebiome;

import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.entity.player.Player;
import net.minecraft.sounds.SoundEvents;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import net.mcreator.sweetapocalpysebiome.client.gui.ldlib.LDLibGUIContext;

@EventBusSubscriber(Dist.CLIENT)
public class PurifyLDLibGUITickHandler {
	
	private static final Map<UUID, Integer> lastSoundStages = new ConcurrentHashMap<>();
	
	@SubscribeEvent
	public static void onClientTick(net.neoforged.neoforge.event.tick.PlayerTickEvent.Post event) {
		Player player = event.getEntity();
		if (!player.level().isClientSide()) {
			return;
		}
		
		// Only process if LDLib GUI is open
		if (!LDLibGUIContext.isOpen(player, net.mcreator.sweetapocalpysebiome.client.gui.PurifyLDLibGUIUI.UI_ID)) {
			lastSoundStages.remove(player.getUUID());
			return;
		}
		
		float progressValue = (float) LDLibGUIContext.getProgress(player, "PurifyLDLibGUI/progress_bar");
		if (progressValue > 0.0F && progressValue < 1.0F) {
			int currentStage = Math.min(3, (int) (progressValue * 4));
			Integer lastSoundStage = lastSoundStages.put(player.getUUID(), currentStage);
			if (lastSoundStage == null || currentStage > lastSoundStage) {
				playProgressSound(player, currentStage);
			}
		} else {
			lastSoundStages.remove(player.getUUID());
		}
	}
	private static void playProgressSound(Player player, int stage) {
		switch (stage) {
			case 0:
				player.playSound(SoundEvents.BREWING_STAND_BREW, 0.5F, 1.0F);
				break;
			case 1:
				player.playSound(SoundEvents.BUBBLE_COLUMN_UPWARDS_AMBIENT, 0.6F, 1.2F);
				break;
			case 2:
				player.playSound(SoundEvents.ENCHANTMENT_TABLE_USE, 0.7F, 1.0F);
				break;
			case 3:
				player.playSound(SoundEvents.AMETHYST_BLOCK_CHIME, 0.8F, 1.5F);
				break;
		}
	}
}