package net.mcreator.sweetapocalpysebiome.block;

import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.ButtonBlock;

public class ChocolateButtonBlock extends ButtonBlock {
	@SuppressWarnings("null")
	public ChocolateButtonBlock() {
		super(BlockSetType.OAK, 30, BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(1.5f).noCollission().pushReaction(PushReaction.DESTROY));
	}
}