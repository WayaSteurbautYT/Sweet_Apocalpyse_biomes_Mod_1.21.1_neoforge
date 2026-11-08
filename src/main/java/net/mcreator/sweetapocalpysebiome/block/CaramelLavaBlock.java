package net.mcreator.sweetapocalpysebiome.block;

import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.LiquidBlock;

import net.mcreator.sweetapocalpysebiome.init.SweetapocalpysebiomeModFluids;

public class CaramelLavaBlock extends LiquidBlock {
	public CaramelLavaBlock() {
		super(SweetapocalpysebiomeModFluids.CARAMEL_LAVA.get(),
				BlockBehaviour.Properties.of().mapColor(MapColor.PODZOL).strength(100f).lightLevel(state -> 10).noCollission().noLootTable().liquid().pushReaction(PushReaction.DESTROY).sound(SoundType.EMPTY).replaceable());
	}
}