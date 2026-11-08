package net.mcreator.sweetapocalpysebiome.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.sweetapocalpysebiome.network.SweetapocalpysebiomeModVariables;

public class PurifyGUIStage0Procedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		// Stage 0: 0% corruption (fully purified)
		return entity.getData(SweetapocalpysebiomeModVariables.PLAYER_VARIABLES).Corruption <= 0;
	}
}