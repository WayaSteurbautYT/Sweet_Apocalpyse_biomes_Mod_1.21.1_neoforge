package net.mcreator.sweetapocalpysebiome.procedures;

import net.minecraft.world.entity.Entity;

public class CorruptionEarlyStageProcedure {
	public static boolean execute(Entity entity) {
		int stage = GetCorruptionStageProcedure.getStage(entity);
		return stage == 1;
	}
}
