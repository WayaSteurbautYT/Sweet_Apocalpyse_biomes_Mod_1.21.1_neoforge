package net.mcreator.sweetapocalpysebiome.item;

import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import org.jetbrains.annotations.NotNull;

public class TitaniumCandySwordItem extends SwordItem {
	public TitaniumCandySwordItem() {
		super(Tiers.NETHERITE, new Item.Properties());
	}

	@Override
	public boolean hasCraftingRemainingItem(@NotNull ItemStack stack) {
		return true;
	}

	@Override
	public ItemStack getCraftingRemainingItem(@NotNull ItemStack itemstack) {
		ItemStack retval = new ItemStack(this);
		retval.setDamageValue(itemstack.getDamageValue() + 1);
		if (retval.getDamageValue() >= retval.getMaxDamage()) {
			return ItemStack.EMPTY;
		}
		return retval;
	}
}
