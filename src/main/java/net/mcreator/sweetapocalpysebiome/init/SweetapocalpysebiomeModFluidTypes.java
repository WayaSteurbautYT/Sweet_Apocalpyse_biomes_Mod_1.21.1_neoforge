/*
 * MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.sweetapocalpysebiome.init;

import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.fluids.FluidType;

import net.mcreator.sweetapocalpysebiome.SweetapocalpysebiomeMod;
import net.mcreator.sweetapocalpysebiome.fluid.types.CaramelLavaFluidType;

public class SweetapocalpysebiomeModFluidTypes {
	public static final DeferredRegister<FluidType> REGISTRY = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, SweetapocalpysebiomeMod.MODID);
	public static final DeferredHolder<FluidType, FluidType> CARAMEL_LAVA_TYPE = REGISTRY.register("caramel_lava", CaramelLavaFluidType::new);
}