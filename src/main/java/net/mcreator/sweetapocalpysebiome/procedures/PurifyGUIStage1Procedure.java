package net.mcreator.sweetapocalpysebiome.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.sweetapocalpysebiome.network.SweetapocalpysebiomeModVariables;

public class PurifyGUIStage1Procedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		// Purify stages based on corruption level (inverted - less corruption = more purified)
		double corruption = entity.getData(SweetapocalpysebiomeModVariables.PLAYER_VARIABLES).Corruption;
		// Stage 1: 90-100% corruption (least purified)
		return corruption >= 90 && corruption < 100;
	}
}