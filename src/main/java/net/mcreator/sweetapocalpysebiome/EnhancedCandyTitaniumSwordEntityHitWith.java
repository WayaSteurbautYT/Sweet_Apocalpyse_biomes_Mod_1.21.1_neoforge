package net.mcreator.sweetapocalpysebiome;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.Level;

public class EnhancedCandyTitaniumSwordEntityHitWith {

	public static void execute(Level world, Entity entity, Entity target, ItemStack itemstack) {
		if (entity instanceof Player player && target instanceof LivingEntity livingTarget) {
			// Heal player when hitting enemies with candy magic sword
			player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 1));
			player.addEffect(new MobEffectInstance(MobEffects.SATURATION, 200, 0));
			
			// Apply candy corruption to target
			livingTarget.addEffect(new MobEffectInstance(MobEffects.POISON, 60, 0));
			livingTarget.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 40, 1));
		}
	}
}
