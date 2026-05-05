/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.sweetapocalpysebiome.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;

import net.mcreator.sweetapocalpysebiome.SweetapocalpysebiomeMod;

public class SweetapocalpysebiomeModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(Registries.SOUND_EVENT, SweetapocalpysebiomeMod.MODID);
	public static final DeferredHolder<SoundEvent, SoundEvent> SWEET_APOCALPYSE_SONG1 = REGISTRY.register("sweet_apocalpyse_song1",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("sweetapocalpysebiome", "sweet_apocalpyse_song1")));
}