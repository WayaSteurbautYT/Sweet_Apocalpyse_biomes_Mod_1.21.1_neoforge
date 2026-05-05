package net.mcreator.sweetapocalpysebiome.block;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.SoundType;

import net.mcreator.sweetapocalpysebiome.init.SweetapocalpysebiomeModWoodTypes;

@SuppressWarnings("null")
public class ChocolateSignBlock extends StandingSignBlock {
	public ChocolateSignBlock() {
		super(SweetapocalpysebiomeModWoodTypes.CHOCOLATE_SIGN_WOOD_TYPE, BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(3f).noCollission().ignitedByLava().instrument(NoteBlockInstrument.BASS).forceSolidOn());
	}
}