package net.mcreator.sweetapocalpysebiome.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.sweetapocalpysebiome.network.SweetapocalpysebiomeModVariables;

public class CorruptionGUIStage1Procedure {
	public static boolean execute(Entity entity) {
		if (entity.getData(SweetapocalpysebiomeModVariables.PLAYER_VARIABLES).Corruption >= 10 && entity.getData(SweetapocalpysebiomeModVariables.PLAYER_VARIABLES).Corruption < 20) {
			return true;
		}
		return false;
	}
}