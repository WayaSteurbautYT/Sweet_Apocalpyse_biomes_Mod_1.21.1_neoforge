package net.mcreator.sweetapocalpysebiome.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;

public class CorruptedCookieItem extends Item {
	public CorruptedCookieItem() {
		super(new Item.Properties().food((new FoodProperties.Builder()).nutrition(2).saturationModifier(0.4f).build()));
	}
}