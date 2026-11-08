package net.mcreator.sweetapocalpysebiome;

import net.minecraft.world.entity.player.Player;

import net.mcreator.sweetapocalpysebiome.procedures.StartPurifyPotionReductionProcedure;

public class PurifyPotionEffectStarted {
	public static void execute(Player entity) {
		StartPurifyPotionReductionProcedure.execute(entity);
	}
}
