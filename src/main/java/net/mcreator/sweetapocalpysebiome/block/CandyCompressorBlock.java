package net.mcreator.sweetapocalpysebiome.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;

import net.mcreator.sweetapocalpysebiome.CandyCompressorBlockRightClicked;
import net.mcreator.sweetapocalpysebiome.CandyCompressorBlockAdded;
import net.mcreator.sweetapocalpysebiome.CandyCompressorBlockDestroyed;

public class CandyCompressorBlock extends Block {
	public CandyCompressorBlock() {
		super(BlockBehaviour.Properties.of().sound(net.minecraft.world.level.block.SoundType.METAL).strength(3.5f, 15f).requiresCorrectToolForDrops());
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}

	@Override
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		CandyCompressorBlockAdded.execute(world, pos, pos, pos);
	}

	@Override
	public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			CandyCompressorBlockDestroyed.execute(world, pos, pos, pos);
		}
		super.onRemove(state, world, pos, newState, isMoving);
	}

	@Override
	public void attack(BlockState state, Level world, BlockPos pos, Player entity) {
		CandyCompressorBlockRightClicked.execute(world, pos, pos, pos, state, entity);
	}

	public void use(BlockState state, Level world, BlockPos pos, Player entity, net.minecraft.world.InteractionHand hand, net.minecraft.world.phys.BlockHitResult hit) {
		CandyCompressorBlockRightClicked.execute(world, pos, pos, pos, state, entity);
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState state) {
		return PushReaction.BLOCK;
	}
}
