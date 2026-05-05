package net.mcreator.sweetapocalpysebiome.block;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.CeilingHangingSignBlock;

import net.mcreator.sweetapocalpysebiome.init.SweetapocalpysebiomeModWoodTypes;

public class ChocolateHangingSignBlock extends CeilingHangingSignBlock {
	@SuppressWarnings("null")
	public ChocolateHangingSignBlock() {
		super(SweetapocalpysebiomeModWoodTypes.CHOCOLATE_HANGING_SIGN_WOOD_TYPE, BlockBehaviour.Properties.of().sound(SoundType.HANGING_SIGN).strength(3f).noCollission().ignitedByLava().instrument(NoteBlockInstrument.BASS).forceSolidOn());
	}
}