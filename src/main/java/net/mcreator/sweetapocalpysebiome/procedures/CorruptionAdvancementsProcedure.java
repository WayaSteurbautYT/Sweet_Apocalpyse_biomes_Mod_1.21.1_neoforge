package net.mcreator.sweetapocalpysebiome.procedures;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class CorruptionAdvancementsProcedure {
	public static void award(ServerPlayer player, String advancementPath) {
		if (player == null || player.server == null) {
			return;
		}
		AdvancementHolder advancement = player.server.getAdvancements().get(ResourceLocation.parse("sweetapocalpysebiome:" + advancementPath));
		if (advancement == null) {
			return;
		}
		AdvancementProgress progress = player.getAdvancements().getOrStartProgress(advancement);
		if (!progress.isDone()) {
			for (String criteria : progress.getRemainingCriteria()) {
				player.getAdvancements().award(advancement, criteria);
			}
		}
	}
}
