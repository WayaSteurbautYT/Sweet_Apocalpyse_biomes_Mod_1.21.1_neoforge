package net.mcreator.sweetapocalpysebiome;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.sweetapocalpysebiome.network.SweetapocalpysebiomeModVariables;
import net.mcreator.sweetapocalpysebiome.init.SweetapocalpysebiomeModMobEffects;
import net.mcreator.sweetapocalpysebiome.procedures.GetCorruptionStageProcedure;

public class PurifyPotionEffectActiveTick {

	public static void execute(Player player) {
		if (player == null)
			return;

		var playerVariables = player.getData(SweetapocalpysebiomeModVariables.PLAYER_VARIABLES);
		
		// Reduce corruption by 1 full stage per tick (7.14 corruption) - quick purification
		if (playerVariables.Corruption > 0) {
			playerVariables.Corruption = Math.max(0, playerVariables.Corruption - GetCorruptionStageProcedure.STAGE_SIZE);
			playerVariables.markSyncDirty();
		}
		
		// Clear corruption potion effect when corruption is 0
		if (playerVariables.Corruption <= 0 && player.hasEffect(SweetapocalpysebiomeModMobEffects.CORRUPTION_POTION_EFFECT)) {
			player.removeEffect(SweetapocalpysebiomeModMobEffects.CORRUPTION_POTION_EFFECT);
		}
	}
}
