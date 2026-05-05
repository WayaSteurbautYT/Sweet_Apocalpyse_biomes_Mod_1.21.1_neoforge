package net.mcreator.sweetapocalpysebiome.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.BlockPos;

import net.mcreator.sweetapocalpysebiome.procedures.WhenCandyBlocksAreBrokenProcedure;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("null")
public class CookyCobbleStoneBlock extends Block {
	public CookyCobbleStoneBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(3.55f, 6f).requiresCorrectToolForDrops().hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true).instrument(NoteBlockInstrument.BASEDRUM));
	}

	@Override
	public int getLightBlock(@NotNull BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos pos) {
		return 15;
	}

	@Override
	public boolean onDestroyedByPlayer(@NotNull BlockState blockstate, @NotNull Level world, @NotNull BlockPos pos, @NotNull Player entity, boolean willHarvest, @NotNull FluidState fluid) {
		boolean retval = super.onDestroyedByPlayer(blockstate, world, pos, entity, willHarvest, fluid);
		WhenCandyBlocksAreBrokenProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
		return retval;
	}
}