package net.mcreator.sweetapocalpysebiome.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.sweetapocalpysebiome.network.SweetapocalpysebiomeModVariables;

public class CorruptionGUIStage2Procedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if (entity.getData(SweetapocalpysebiomeModVariables.PLAYER_VARIABLES).Corruption >= 20 && entity.getData(SweetapocalpysebiomeModVariables.PLAYER_VARIABLES).Corruption < 30) {
			return true;
		}
		return false;
	}
}