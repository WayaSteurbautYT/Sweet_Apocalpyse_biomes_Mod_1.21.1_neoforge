package net.mcreator.sweetapocalpysebiome;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.LevelAccessor;

public class CandyCompressorBlockRightClicked {

	public static void execute(LevelAccessor world, BlockPos x, BlockPos y, BlockPos z, BlockState blockstate, Player entity) {
		if (entity == null)
			return;
		
		if (entity instanceof Player) {
			entity.sendSystemMessage(Component.literal("Candy Compressor - Right Click to Compress Materials"));
		}
	}
}
