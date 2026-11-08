package net.mcreator.sweetapocalpysebiome.potion;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import net.mcreator.sweetapocalpysebiome.PurifyPotionEffectActiveTick;
import net.mcreator.sweetapocalpysebiome.PurifyPotionEffectStarted;

public class PurifyPotionEffectMobEffect extends MobEffect {
	public PurifyPotionEffectMobEffect() {
		super(MobEffectCategory.BENEFICIAL, 0xFFFFFF);
	}

	@Override
	public void onEffectStarted(LivingEntity entity, int amplifier) {
		if (entity instanceof Player player)
			PurifyPotionEffectStarted.execute(player);
	}

	@Override
	public boolean applyEffectTick(LivingEntity entity, int amplifier) {
		if (entity instanceof Player player)
			PurifyPotionEffectActiveTick.execute(player);
		return true;
	}
}
