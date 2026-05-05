/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.sweetapocalpysebiome.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.core.registries.Registries;

import net.mcreator.sweetapocalpysebiome.SweetapocalpysebiomeMod;

public class SweetapocalpysebiomeModPotions {
	public static final DeferredRegister<Potion> REGISTRY = DeferredRegister.create(Registries.POTION, SweetapocalpysebiomeMod.MODID);
	public static final DeferredHolder<Potion, Potion> A_COOKIE_GOD_EFFECT_POTION = REGISTRY.register("a_cookie_god_effect_potion", () -> new Potion(new MobEffectInstance(SweetapocalpysebiomeModMobEffects.A_COOKIE_GOD_EFFECT, 3600, 0, false, true)));
	public static final DeferredHolder<Potion, Potion> A_STICKY_SITUATION_EFFECT_POTION = REGISTRY.register("a_sticky_situation_effect_potion",
			() -> new Potion(new MobEffectInstance(SweetapocalpysebiomeModMobEffects.A_STICKY_SITUATION_EFFECT, 3600, 0, false, true)));
	public static final DeferredHolder<Potion, Potion> ITS_NOT_GOOD_FOR_THE_SKIN_POTION_EFFECT = REGISTRY.register("its_not_good_for_the_skin_potion_effect",
			() -> new Potion(new MobEffectInstance(SweetapocalpysebiomeModMobEffects.ITS_NOT_GOOD_FOR_THE_SKIN_EFFECT, 3600, 0, false, true)));
	public static final DeferredHolder<Potion, Potion> CORRUPTIONPOTION = REGISTRY.register("corruptionpotion", () -> new Potion());
	public static final DeferredHolder<Potion, Potion> PURIFYPOTION = REGISTRY.register("purifypotion", () -> new Potion());
}