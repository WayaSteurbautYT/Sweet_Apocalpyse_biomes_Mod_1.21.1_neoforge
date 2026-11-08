package net.mcreator.sweetapocalpysebiome.procedures;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import net.mcreator.sweetapocalpysebiome.FoodCorruptionHelper;
import net.mcreator.sweetapocalpysebiome.network.SweetapocalpysebiomeModVariables;

@EventBusSubscriber
public class EatNormalFoodReduceCorruptionProcedure {
	@SubscribeEvent
	public static void onEat(LivingEntityUseItemEvent.Finish event) {
		if (!(event.getEntity() instanceof Player player) || player.level().isClientSide()) {
			return;
		}
		ItemStack stack = event.getItem();
		if (FoodCorruptionHelper.isCorruptedFood(stack) || !FoodCorruptionHelper.isNormalFood(stack)) {
			return;
		}
		SweetapocalpysebiomeModVariables.PlayerVariables vars = player.getData(SweetapocalpysebiomeModVariables.PLAYER_VARIABLES);
		if (vars.Corruption <= 0) {
			return;
		}
		vars.Corruption = GetCorruptionStageProcedure.clampCorruption(vars.Corruption - GetCorruptionStageProcedure.STAGE_SIZE);
		vars.markSyncDirty();
		int stage = GetCorruptionStageProcedure.getStage(vars.Corruption);
		player.displayClientMessage(Component.literal("Clean food is helping. Corruption Stage " + stage), true);
		if (player instanceof ServerPlayer serverPlayer) {
			CorruptionAdvancementsProcedure.award(serverPlayer, "cleanse_with_food");
		}
	}
}
