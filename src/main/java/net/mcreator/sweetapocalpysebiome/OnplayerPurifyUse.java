package net.mcreator.sweetapocalpysebiome;

import net.minecraft.world.entity.player.Player;

import net.mcreator.sweetapocalpysebiome.procedures.StartPurifyPotionReductionProcedure;

public class OnplayerPurifyUse {
	public static void purifyInventory(Player player) {
		StartPurifyPotionReductionProcedure.execute(player);
	}
}
