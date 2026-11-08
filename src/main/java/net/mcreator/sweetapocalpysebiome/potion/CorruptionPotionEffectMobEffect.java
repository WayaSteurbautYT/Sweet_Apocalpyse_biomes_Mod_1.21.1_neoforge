package net.mcreator.sweetapocalpysebiome.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.sweetapocalpysebiome.procedures.CorruptionPotionEffectEffectStartedappliedProcedure;

public class CorruptionPotionEffectMobEffect extends MobEffect {
	public CorruptionPotionEffectMobEffect() {
		super(MobEffectCategory.NEUTRAL, -15717345);
	}

	@Override
	public void onEffectStarted(LivingEntity entity, int amplifier) {
		CorruptionPotionEffectEffectStartedappliedProcedure.execute(entity);
	}
}