package net.mcreator.sweetapocalpysebiome.potion;

import net.neoforged.neoforge.common.EffectCures;
import net.neoforged.neoforge.common.EffectCure;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.ParticleOptions;

import net.mcreator.sweetapocalpysebiome.procedures.AStickySituationEffectOnEntityDeathProcedure;
import net.mcreator.sweetapocalpysebiome.procedures.AStickySituationEffectEffectStartedappliedProcedure;

import java.util.Set;

public class AStickySituationEffectMobEffect extends MobEffect {
	public AStickySituationEffectMobEffect() {
		super(MobEffectCategory.HARMFUL, -65419);
		this.withSoundOnAdded(BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.slime.death")));
	}

	@Override
	public ParticleOptions createParticleOptions(MobEffectInstance mobEffectInstance) {
		return ParticleTypes.DAMAGE_INDICATOR;
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
			AStickySituationEffectOnEntityDeathProcedure.execute(entity.level(), entity);
		}
	}
}