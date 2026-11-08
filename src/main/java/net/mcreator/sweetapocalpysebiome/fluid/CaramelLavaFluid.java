package net.mcreator.sweetapocalpysebiome.fluid;

import net.neoforged.neoforge.fluids.BaseFlowingFluid;

import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.LiquidBlock;

import net.mcreator.sweetapocalpysebiome.init.SweetapocalpysebiomeModItems;
import net.mcreator.sweetapocalpysebiome.init.SweetapocalpysebiomeModFluids;
import net.mcreator.sweetapocalpysebiome.init.SweetapocalpysebiomeModFluidTypes;
import net.mcreator.sweetapocalpysebiome.init.SweetapocalpysebiomeModBlocks;

public abstract class CaramelLavaFluid extends BaseFlowingFluid {
	public static final BaseFlowingFluid.Properties PROPERTIES = new BaseFlowingFluid.Properties(() -> SweetapocalpysebiomeModFluidTypes.CARAMEL_LAVA_TYPE.get(), () -> SweetapocalpysebiomeModFluids.CARAMEL_LAVA.get(),
			() -> SweetapocalpysebiomeModFluids.FLOWING_CARAMEL_LAVA.get()).explosionResistance(100f).tickRate(40).levelDecreasePerBlock(3).slopeFindDistance(10).bucket(() -> SweetapocalpysebiomeModItems.CARAMEL_LAVA_BUCKET.get())
			.block(() -> (LiquidBlock) SweetapocalpysebiomeModBlocks.CARAMEL_LAVA.get());

	private CaramelLavaFluid() {
		super(PROPERTIES);
	}

	public static class Source extends CaramelLavaFluid {
		public int getAmount(FluidState state) {
			return 8;
		}

		public boolean isSource(FluidState state) {
			return true;
		}
	}

	public static class Flowing extends CaramelLavaFluid {
		protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
			super.createFluidStateDefinition(builder);
			builder.add(LEVEL);
		}

		public int getAmount(FluidState state) {
			return state.getValue(LEVEL);
		}

		public boolean isSource(FluidState state) {
			return false;
		}
	}
}