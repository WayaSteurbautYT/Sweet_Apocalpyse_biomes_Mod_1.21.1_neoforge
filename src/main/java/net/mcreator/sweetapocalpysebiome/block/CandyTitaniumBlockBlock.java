package net.mcreator.sweetapocalpysebiome.block;

import net.neoforged.neoforge.common.util.TriState;
import net.neoforged.neoforge.common.ItemAbility;
import net.neoforged.neoforge.common.ItemAbilities;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.FastColor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import org.jetbrains.annotations.NotNull;

import net.mcreator.sweetapocalpysebiome.procedures.ItsNotGoodForTheSkinEffectEffectStartedappliedProcedure;
import net.mcreator.sweetapocalpysebiome.procedures.AStickySituationEffectEffectStartedappliedProcedure;

@SuppressWarnings("null")
public class CandyTitaniumBlockBlock extends Block implements BonemealableBlock {
	public CandyTitaniumBlockBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).sound(SoundType.ANVIL).strength(50f, 10f).friction(0.8f).speedFactor(1.5f).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true)
				.instrument(NoteBlockInstrument.CUSTOM_HEAD));
	}

	@Override
	public Integer getBeaconColorMultiplier(@NotNull BlockState state, @NotNull LevelReader world, @NotNull BlockPos pos, @NotNull BlockPos beaconPos) {
		return FastColor.ARGB32.opaque(-16711816);
	}

	@Override
	public int getLightBlock(@NotNull BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos pos) {
		return 9;
	}

	@Override
	public BlockState getToolModifiedState(@NotNull BlockState blockstate, @NotNull UseOnContext context, @NotNull ItemAbility itemAbility, boolean simulate) {
		if (ItemAbilities.AXE_STRIP == itemAbility && context.getItemInHand().canPerformAction(itemAbility)) {
			return Blocks.SLIME_BLOCK.withPropertiesOf(blockstate);
		}
		return super.getToolModifiedState(blockstate, context, itemAbility, simulate);
	}

	@Override
	public TriState canSustainPlant(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull Direction direction, @NotNull BlockState plant) {
		return TriState.TRUE;
	}

	@Override
	public boolean onDestroyedByPlayer(@NotNull BlockState blockstate, @NotNull Level world, @NotNull BlockPos pos, @NotNull Player entity, boolean willHarvest, @NotNull FluidState fluid) {
		boolean retval = super.onDestroyedByPlayer(blockstate, world, pos, entity, willHarvest, fluid);
		ItsNotGoodForTheSkinEffectEffectStartedappliedProcedure.execute(world, entity);
		return retval;
	}

	@Override
	public void stepOn(@NotNull Level world, @NotNull BlockPos pos, @NotNull BlockState blockstate, @NotNull Entity entity) {
		super.stepOn(world, pos, blockstate, entity);
		AStickySituationEffectEffectStartedappliedProcedure.execute(world, entity);
	}

	@Override
	public boolean isValidBonemealTarget(@NotNull LevelReader worldIn, @NotNull BlockPos pos, @NotNull BlockState blockstate) {
		return true;
	}

	@Override
	public boolean isBonemealSuccess(@NotNull Level world, @NotNull RandomSource random, @NotNull BlockPos pos, @NotNull BlockState blockstate) {
		return true;
	}

	@Override
	public void performBonemeal(@NotNull ServerLevel world, @NotNull RandomSource random, @NotNull BlockPos pos, @NotNull BlockState blockstate) {
	}
}