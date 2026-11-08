package net.mcreator.sweetapocalpysebiome.procedures;

import net.minecraft.world.entity.Entity;

public class CorruptionGUIStage10Procedure {
	public static boolean execute(Entity entity) {
		return GetCorruptionStageProcedure.isStage(entity, 10);
	}
}
