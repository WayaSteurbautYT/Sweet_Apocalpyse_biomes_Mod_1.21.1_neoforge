package net.mcreator.sweetapocalpysebiome;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.Level;

public class CandyDimensionPortalItemRightClickedOnBlock {

	public static void execute(Level world, BlockPos pos, Player player, ItemStack stack) {
		// Create portal structure when right-clicked on ground
		if (world.getBlockState(pos.below()).isAir()) {
			world.setBlockAndUpdate(pos.below(), Blocks.OBSIDIAN.defaultBlockState());
		}
	}
}
