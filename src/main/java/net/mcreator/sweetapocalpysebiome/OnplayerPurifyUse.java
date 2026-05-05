package net.mcreator.sweetapocalpysebiome;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.CustomData;

public class OnplayerPurifyUse {

    public static void purifyInventory(Player player) {
        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack stack = player.getInventory().getItem(i);

            if (stack.isEmpty()) continue;

            CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
            if (customData != null && customData.copyTag().getBoolean("Corrupted")) {
                CompoundTag mutableTag = customData.copyTag();
                mutableTag.putBoolean("Corrupted", false);
                mutableTag.putBoolean("Purified", true);
                mutableTag.putInt("PurifyTimer", 0);
                stack.set(DataComponents.CUSTOM_DATA, CustomData.of(mutableTag));
            }
        }
    }
}