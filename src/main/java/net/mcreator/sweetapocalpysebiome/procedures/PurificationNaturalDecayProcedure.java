package net.mcreator.sweetapocalpysebiome.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.sweetapocalpysebiome.network.SweetapocalpysebiomeModVariables;

public class PurificationNaturalDecayProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		// Natural corruption decay is now handled in CustomPurificationSystem
		// This procedure is kept for compatibility but does nothing
	}
}
