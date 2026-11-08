package net.mcreator.sweetapocalpysebiome;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import net.minecraft.world.entity.player.Player;

import net.mcreator.sweetapocalpysebiome.network.SweetapocalpysebiomeModVariables;

@EventBusSubscriber
public class CustomPurificationSystem {
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent.Post event) {
		Player player = event.getEntity();
		if (player.level().isClientSide()) {
			return;
		}
		var playerVariables = player.getData(SweetapocalpysebiomeModVariables.PLAYER_VARIABLES);
		// Natural corruption decay over time (slowly reduces corruption when not eating corrupted food)
		if (playerVariables.Corruption > 0 && player.tickCount % 20 == 0) {
			playerVariables.Corruption = Math.max(0, playerVariables.Corruption - 0.1);
			playerVariables.markSyncDirty();
		}
	}
}
