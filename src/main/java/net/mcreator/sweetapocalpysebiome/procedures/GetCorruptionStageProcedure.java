package net.mcreator.sweetapocalpysebiome.procedures;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

import net.mcreator.sweetapocalpysebiome.network.SweetapocalpysebiomeModVariables;

public class GetCorruptionStageProcedure {
	public static final int MAX_STAGE = 14;
	public static final double MAX_CORRUPTION = 100.0;
	public static final double STAGE_SIZE = MAX_CORRUPTION / MAX_STAGE;
	public static final double HALF_STAGE = STAGE_SIZE / 2.0;

	public static double execute(Entity entity) {
		return getStage(entity);
	}

	public static int getStage(Entity entity) {
		if (entity == null) {
			return 0;
		}
		return getStage(entity.getData(SweetapocalpysebiomeModVariables.PLAYER_VARIABLES).Corruption);
	}

	public static int getStage(double corruption) {
		double clamped = clampCorruption(corruption);
		if (clamped <= 0) {
			return 0;
		}
		int stage = (int) Math.ceil(clamped / STAGE_SIZE);
		return Mth.clamp(stage, 1, MAX_STAGE);
	}

	public static double clampCorruption(double corruption) {
		if (Double.isNaN(corruption) || Double.isInfinite(corruption) || corruption < 0) {
			return 0;
		}
		return Math.min(MAX_CORRUPTION, corruption);
	}

	public static boolean isStage(Entity entity, int stage) {
		return getStage(entity) == stage;
	}
}
