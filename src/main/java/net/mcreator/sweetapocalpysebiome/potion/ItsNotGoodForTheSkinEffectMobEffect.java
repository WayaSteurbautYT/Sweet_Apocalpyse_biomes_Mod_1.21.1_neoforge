package net.mcreator.sweetapocalpysebiome.potion;

import net.neoforged.neoforge.common.EffectCures;
import net.neoforged.neoforge.common.EffectCure;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.sweetapocalpysebiome.procedures.ItsNotGoodForTheSkinEffectOnEntityDeathProcedure;
import net.mcreator.sweetapocalpysebiome.procedures.AStickySituationEffectEffectStartedappliedProcedure;

import java.util.Set;

public class ItsNotGoodForTheSkinEffectMobEffect extends MobEffect {
	public ItsNotGoodForTheSkinEffectMobEffect() {
		super(MobEffectCategory.HARMFUL, -3270762);
	}

	@Override
	public void fillEffectCures(Set<EffectCure> cures, MobEffectInstance effectInstance) {
		cures.add(EffectCures.MILK);
		cures.add(EffectCures.PROTECTED_BY_TOTEM);
		cures.add(EffectCures.HONEY);
	}

	@Override
	public void onEffectStarted(LivingEntity entity, int amplifier) {
		AStickySituationEffectEffectStartedappliedProcedure.execute(entity.level(), entity);
	}

	@Override
	public void onMobRemoved(LivingEntity entity, int amplifier, Entity.RemovalReason reason) {
		if (reason == Entity.RemovalReason.KILLED) {
			ItsNotGoodForTheSkinEffectOnEntityDeathProcedure.execute(entity.level(), entity);
		}
	}
}