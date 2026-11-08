package net.mcreator.sweetapocalpysebiome.procedures;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

import net.mcreator.sweetapocalpysebiome.network.SweetapocalpysebiomeModVariables;
import net.mcreator.sweetapocalpysebiome.init.SweetapocalpysebiomeModMobEffects;

@EventBusSubscriber
public class CorruptionStageEffectsProcedure {
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent.Post event) {
		Player player = event.getEntity();
		if (player.level().isClientSide()) {
			return;
		}

		SweetapocalpysebiomeModVariables.PlayerVariables vars = player.getData(SweetapocalpysebiomeModVariables.PLAYER_VARIABLES);
		vars.Corruption = GetCorruptionStageProcedure.clampCorruption(vars.Corruption);
		int stage = GetCorruptionStageProcedure.getStage(vars.Corruption);
		// Temporarily disabled LastCorruptionStage tracking due to missing variable
		// int previousStage = vars.LastCorruptionStage;
		// if (previousStage != stage) {
		// 	vars.LastCorruptionStage = stage;
		// 	vars.markSyncDirty();
		// 	handleStageTransition(player, previousStage, stage);
		// }

		// Apply corruption effect based on corruption level (sync action bar with corruption bar)
		if (stage > 0 && !player.hasEffect(SweetapocalpysebiomeModMobEffects.CORRUPTION_POTION_EFFECT)) {
			player.addEffect(new MobEffectInstance(SweetapocalpysebiomeModMobEffects.CORRUPTION_POTION_EFFECT, 100, 0, true, false));
		} else if (stage == 0 && player.hasEffect(SweetapocalpysebiomeModMobEffects.CORRUPTION_POTION_EFFECT)) {
			player.removeEffect(SweetapocalpysebiomeModMobEffects.CORRUPTION_POTION_EFFECT);
		}

		applyStageEffects(player, stage);
	}

	private static void handleStageTransition(Player player, int from, int to) {
		if (to == 1 && player instanceof ServerPlayer serverPlayer) {
			CorruptionAdvancementsProcedure.award(serverPlayer, "early_corruption");
			CorruptionAdvancementsProcedure.award(serverPlayer, "corrupted_king");
			player.displayClientMessage(Component.literal("Your body feels a little corrupted..."), true);
		} else if (to == 14 && player instanceof ServerPlayer serverPlayer) {
			CorruptionAdvancementsProcedure.award(serverPlayer, "terminal_corruption");
			player.displayClientMessage(Component.literal("Corruption is at its worst. You are at great risk of dying!"), true);
		} else if (to > from && to > 0) {
			player.displayClientMessage(Component.literal("Corruption worsened to Stage " + to), true);
		} else if (to < from && from > 0) {
			if (to <= 0) {
				player.displayClientMessage(Component.literal("You feel purified. Corruption is gone."), true);
			} else {
				player.displayClientMessage(Component.literal("Corruption eased to Stage " + to), true);
			}
		}
	}

	private static void applyStageEffects(Player player, int stage) {
		if (stage <= 0) {
			return;
		}
		
		// Apply effects every 20 ticks (1 second) for consistent gameplay
		if (player.tickCount % 20 != 0) {
			return;
		}
		
		if (stage == 1) {
			player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 0, true, false));
		} else if (stage <= 4) {
			player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, 0, true, false));
			player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 0, true, false));
		} else if (stage <= 7) {
			player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 80, 0, true, false));
			player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 80, 0, true, false));
			player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 80, 0, true, false));
		} else if (stage <= 10) {
			player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 1, true, false));
			player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 60, 0, true, false));
			player.addEffect(new MobEffectInstance(MobEffects.POISON, 60, 0, true, false));
		} else if (stage <= 13) {
			player.addEffect(new MobEffectInstance(MobEffects.WITHER, 60, 0, true, false));
			player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 2, true, false));
			player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 30, 0, true, false));
			player.hurt(player.damageSources().magic(), 1.0F);
		} else {
			player.addEffect(new MobEffectInstance(MobEffects.WITHER, 80, 1, true, false));
			player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 120, 3, true, false));
			player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 80, 1, true, false));
			player.hurt(player.damageSources().magic(), 2.0F);
			if (player.getRandom().nextFloat() < 0.15F) {
				player.hurt(player.damageSources().wither(), 4.0F);
			}
		}
	}
}
