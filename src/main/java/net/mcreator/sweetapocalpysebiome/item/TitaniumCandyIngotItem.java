package net.mcreator.sweetapocalpysebiome.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

public class TitaniumCandyIngotItem extends Item {
	public TitaniumCandyIngotItem() {
		super(new Item.Properties().rarity(Rarity.EPIC));
	}

	@Override
	public boolean isPiglinCurrency(ItemStack stack) {
		return true;
	}
}