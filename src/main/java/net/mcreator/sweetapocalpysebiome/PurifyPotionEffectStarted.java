package net.mcreator.sweetapocalpysebiome;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.network.chat.Component;

public class PurifyPotionEffectStarted {

	public static void execute(Player entity) {
		if (entity == null)
			return;

		// Iterate through player's inventory and cleanse corrupted items
		for (int i = 0; i < entity.getInventory().getContainerSize(); i++) {
			ItemStack stack = entity.getInventory().getItem(i);
			if (!stack.isEmpty()) {
				// Check if item is corrupted
				CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
				if (customData != null && customData.copyTag().getBoolean("Corrupted")) {
					// Remove corruption tag
					CompoundTag newTag = customData.copyTag();
					newTag.remove("Corrupted");
					stack.set(DataComponents.CUSTOM_DATA, CustomData.of(newTag));
					
					// Send message to player
					entity.sendSystemMessage(Component.literal("§aPurified " + stack.getDisplayName().getString()));
				}
			}
		}
	}
}
