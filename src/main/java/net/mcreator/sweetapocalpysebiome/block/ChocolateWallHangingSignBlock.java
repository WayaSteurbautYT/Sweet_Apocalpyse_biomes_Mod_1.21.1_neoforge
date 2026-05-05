package net.mcreator.sweetapocalpysebiome.block;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.SoundType;

import net.mcreator.sweetapocalpysebiome.init.SweetapocalpysebiomeModWoodTypes;
import net.mcreator.sweetapocalpysebiome.init.SweetapocalpysebiomeModBlocks;

public class ChocolateWallHangingSignBlock extends WallHangingSignBlock {
	@SuppressWarnings("null")
	public ChocolateWallHangingSignBlock() {
		super(SweetapocalpysebiomeModWoodTypes.CHOCOLATE_HANGING_SIGN_WOOD_TYPE,
				BlockBehaviour.Properties.of().sound(SoundType.HANGING_SIGN).strength(3f).noCollission().ignitedByLava().instrument(NoteBlockInstrument.BASS).forceSolidOn().dropsLike(SweetapocalpysebiomeModBlocks.CHOCOLATE_HANGING_SIGN.get()));
	}
}