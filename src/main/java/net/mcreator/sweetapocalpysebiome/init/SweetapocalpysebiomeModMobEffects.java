/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.sweetapocalpysebiome.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.core.registries.Registries;

import net.mcreator.sweetapocalpysebiome.potion.ItsNotGoodForTheSkinEffectMobEffect;
import net.mcreator.sweetapocalpysebiome.potion.AStickySituationEffectMobEffect;
import net.mcreator.sweetapocalpysebiome.potion.ACookieGodEffectMobEffect;
import net.mcreator.sweetapocalpysebiome.SweetapocalpysebiomeMod;

public class SweetapocalpysebiomeModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(Registries.MOB_EFFECT, SweetapocalpysebiomeMod.MODID);
	public static final DeferredHolder<MobEffect, MobEffect> A_COOKIE_GOD_EFFECT = REGISTRY.register("a_cookie_god_effect", () -> new ACookieGodEffectMobEffect());
	public static final DeferredHolder<MobEffect, MobEffect> ITS_NOT_GOOD_FOR_THE_SKIN_EFFECT = REGISTRY.register("its_not_good_for_the_skin_effect", () -> new ItsNotGoodForTheSkinEffectMobEffect());
	public static final DeferredHolder<MobEffect, MobEffect> A_STICKY_SITUATION_EFFECT = REGISTRY.register("a_sticky_situation_effect", () -> new AStickySituationEffectMobEffect());
}