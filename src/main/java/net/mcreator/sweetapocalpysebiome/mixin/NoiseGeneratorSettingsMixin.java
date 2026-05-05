package net.mcreator.sweetapocalpysebiome.mixin;

import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.core.Holder;

import net.mcreator.sweetapocalpysebiome.init.SweetapocalpysebiomeModBiomes;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;

@Mixin(NoiseGeneratorSettings.class)
public class NoiseGeneratorSettingsMixin implements SweetapocalpysebiomeModBiomes.SweetapocalpysebiomeModNoiseGeneratorSettings {
	@Unique
	private Holder<DimensionType> sweetapocalpysebiome_dimensionTypeReference;

	@WrapMethod(method = "surfaceRule")
	public SurfaceRules.RuleSource surfaceRule(Operation<SurfaceRules.RuleSource> original) {
		SurfaceRules.RuleSource retval = original.call();
		if (this.sweetapocalpysebiome_dimensionTypeReference != null) {
			retval = SweetapocalpysebiomeModBiomes.adaptSurfaceRule(retval, this.sweetapocalpysebiome_dimensionTypeReference);
		}
		return retval;
	}

	@Override
	public void setsweetapocalpysebiomeDimensionTypeReference(Holder<DimensionType> dimensionType) {
		this.sweetapocalpysebiome_dimensionTypeReference = dimensionType;
	}
}