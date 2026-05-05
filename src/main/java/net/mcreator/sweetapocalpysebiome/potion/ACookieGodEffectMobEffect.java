package net.mcreator.sweetapocalpysebiome.potion;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;

import net.mcreator.sweetapocalpysebiome.procedures.ACookieGodEffectOnEntityDeathProcedure;
import net.mcreator.sweetapocalpysebiome.procedures.ACookieGodEffectEffectStartedappliedProcedure;
import net.mcreator.sweetapocalpysebiome.SweetapocalpysebiomeMod;

@SuppressWarnings("null")
public class ACookieGodEffectMobEffect extends MobEffect {
	public ACookieGodEffectMobEffect() {
		super(MobEffectCategory.NEUTRAL, -5932476);
		this.withSoundOnAdded(BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.generic.explode")));
		this.addAttributeModifier(Attributes.ARMOR, ResourceLocation.fromNamespaceAndPath(SweetapocalpysebiomeMod.MODID, "effect.a_cookie_god_effect_0"), 0, AttributeModifier.Operation.ADD_VALUE);
	}

	@Override
	public void onEffectStarted(LivingEntity entity, int amplifier) {
		ACookieGodEffectEffectStartedappliedProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity);
	}

	@Override
	public void onMobRemoved(LivingEntity entity, int amplifier, Entity.RemovalReason reason) {
		if (reason == Entity.RemovalReason.KILLED) {
			ACookieGodEffectOnEntityDeathProcedure.execute(entity.level(), entity);
		}
	}
}