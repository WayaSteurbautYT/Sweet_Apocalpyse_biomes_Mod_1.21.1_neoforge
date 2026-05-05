package net.mcreator.sweetapocalpysebiome;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

public class CandyDimensionEffects {

	public static void execute(Level world, Entity entity) {
		if (entity instanceof LivingEntity livingEntity && world instanceof ServerLevel) {
			// Apply candy dimension-specific effects
			livingEntity.addEffect(new MobEffectInstance(MobEffects.SATURATION, 200, 0));
			livingEntity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0));
		}
	}
}
