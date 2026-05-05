package net.mcreator.sweetapocalpysebiome.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public class EnhancedCandyIngotItem extends Item {
	public EnhancedCandyIngotItem() {
		super(new Item.Properties().rarity(Rarity.EPIC).fireResistant());
	}

	@Override
	public boolean isFoil(ItemStack stack) {
		return true; // Makes the item have enchantment glint
	}

	@Override
	public boolean hasCraftingRemainingItem(ItemStack stack) {
		return false;
	}
}
