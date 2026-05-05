package net.mcreator.sweetapocalpysebiome.block;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.SoundType;

import net.mcreator.sweetapocalpysebiome.init.SweetapocalpysebiomeModWoodTypes;
import net.mcreator.sweetapocalpysebiome.init.SweetapocalpysebiomeModBlocks;

public class ChocolateWallSignBlock extends WallSignBlock {
	@SuppressWarnings("null")
	public ChocolateWallSignBlock() {
		super(SweetapocalpysebiomeModWoodTypes.CHOCOLATE_SIGN_WOOD_TYPE,
				BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(3f).noCollission().ignitedByLava().instrument(NoteBlockInstrument.BASS).forceSolidOn().dropsLike(SweetapocalpysebiomeModBlocks.CHOCOLATE_SIGN.get()));
	}
}