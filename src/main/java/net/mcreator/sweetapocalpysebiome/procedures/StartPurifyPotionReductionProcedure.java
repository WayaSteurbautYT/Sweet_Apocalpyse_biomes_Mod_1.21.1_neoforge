package net.mcreator.sweetapocalpysebiome.procedures;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import net.mcreator.sweetapocalpysebiome.network.SweetapocalpysebiomeModVariables;

public class StartPurifyPotionReductionProcedure {
	public static final int DURATION_TICKS = 400;

	public static void execute(Player player) {
		if (player == null || player.level().isClientSide()) {
			return;
		}
		SweetapocalpysebiomeModVariables.PlayerVariables vars = player.getData(SweetapocalpysebiomeModVariables.PLAYER_VARIABLES);
		vars.markSyncDirty();
		player.displayClientMessage(Component.literal("The Purify Potion is working. Corruption is decreasing..."), true);
		if (player instanceof ServerPlayer serverPlayer) {
			CorruptionAdvancementsProcedure.award(serverPlayer, "drink_purify_potion");
		}
	}
}
