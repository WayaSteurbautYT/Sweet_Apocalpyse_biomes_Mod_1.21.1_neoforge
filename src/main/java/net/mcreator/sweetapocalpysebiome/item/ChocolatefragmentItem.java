package net.mcreator.sweetapocalpysebiome.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;

public class ChocolatefragmentItem extends Item {
	public ChocolatefragmentItem() {
		super(new Item.Properties().food((new FoodProperties.Builder()).nutrition(4).saturationModifier(0.3f).build()));
	}
}