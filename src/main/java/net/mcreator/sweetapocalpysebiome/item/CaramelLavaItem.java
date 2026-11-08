package net.mcreator.sweetapocalpysebiome.item;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BucketItem;

import net.mcreator.sweetapocalpysebiome.init.SweetapocalpysebiomeModFluids;

public class CaramelLavaItem extends BucketItem {
	public CaramelLavaItem() {
		super(SweetapocalpysebiomeModFluids.CARAMEL_LAVA.get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)

		);
	}
}