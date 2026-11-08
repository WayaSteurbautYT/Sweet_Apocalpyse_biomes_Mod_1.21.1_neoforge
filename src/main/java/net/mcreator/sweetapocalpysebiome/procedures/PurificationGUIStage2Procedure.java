package net.mcreator.sweetapocalpysebiome.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.sweetapocalpysebiome.network.SweetapocalpysebiomeModVariables;

public class PurificationGUIStage2Procedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		double corruption = entity.getData(SweetapocalpysebiomeModVariables.PLAYER_VARIABLES).Corruption;
		return corruption >= 20 && corruption < 30;
	}
}
