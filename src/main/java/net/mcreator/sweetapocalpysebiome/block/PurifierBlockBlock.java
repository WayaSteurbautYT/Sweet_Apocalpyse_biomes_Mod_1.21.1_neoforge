package net.mcreator.sweetapocalpysebiome.block;

import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.BlockPos;

import net.mcreator.sweetapocalpysebiome.PurifyGUIElementCustom;
import net.mcreator.sweetapocalpysebiome.network.OpenPurifyGUIPacket;

public class PurifierBlockBlock extends Block {
	public PurifierBlockBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).sound(SoundType.SCULK).strength(3.5f, 10f).instrument(NoteBlockInstrument.CUSTOM_HEAD));
	}

	@Override
	public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
		if (!state.is(newState.getBlock()) && !level.isClientSide) {
			PurifyGUIElementCustom.onPurifierBlockBroken(level, pos);
		}
		super.onRemove(state, level, pos, newState, isMoving);
	}

	@Override
	public InteractionResult useWithoutItem(BlockState blockstate, Level world, BlockPos pos, Player entity, BlockHitResult hit) {
		super.useWithoutItem(blockstate, world, pos, entity, hit);
		if (!world.isClientSide && entity instanceof net.minecraft.server.level.ServerPlayer serverPlayer) {
			PurifyGUIElementCustom.bindInventoryToBlock(entity, pos);
			OpenPurifyGUIPacket.open(serverPlayer, pos);
		}
		return InteractionResult.SUCCESS;
	}
}