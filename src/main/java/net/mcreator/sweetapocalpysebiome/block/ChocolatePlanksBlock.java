package net.mcreator.sweetapocalpysebiome.block;

import net.neoforged.neoforge.common.ItemAbility;
import net.neoforged.neoforge.common.ItemAbilities;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.sweetapocalpysebiome.init.SweetapocalpysebiomeModBlocks;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("null")
public class ChocolatePlanksBlock extends Block {
	public ChocolatePlanksBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(6f, 7.2246740558f).ignitedByLava().instrument(NoteBlockInstrument.BASS));
	}

	@Override
	public int getFlammability(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull Direction face) {
		return 20;
	}

	@Override
	public int getFireSpreadSpeed(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull Direction face) {
		return 5;
	}

	@Override
	public BlockState getToolModifiedState(@NotNull BlockState blockstate, @NotNull UseOnContext context, @NotNull ItemAbility itemAbility, boolean simulate) {
		if (ItemAbilities.AXE_STRIP == itemAbility && context.getItemInHand().canPerformAction(itemAbility)) {
			return SweetapocalpysebiomeModBlocks.STRIPPED_CHOCOLATE_PLANKS.get().withPropertiesOf(blockstate);
		}
		return super.getToolModifiedState(blockstate, context, itemAbility, simulate);
	}
}