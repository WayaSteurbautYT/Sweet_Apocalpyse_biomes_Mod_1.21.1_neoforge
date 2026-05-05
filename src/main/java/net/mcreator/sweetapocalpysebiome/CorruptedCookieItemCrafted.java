package net.mcreator.sweetapocalpysebiome;

import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.CustomData;

public class CorruptedCookieItemCrafted {

	public static void execute(ItemStack itemstack) {
		if (itemstack == null)
			return;

		// Add corruption tag to the crafted cookie
		CompoundTag tag = new CompoundTag();
		tag.putBoolean("Corrupted", true);
		itemstack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
	}
}
