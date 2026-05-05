package net.mcreator.sweetapocalpysebiome;

import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.CustomData;

import net.mcreator.sweetapocalpysebiome.init.SweetapocalpysebiomeModItems;

@EventBusSubscriber
public class CustomCorruptionSystem {

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();

        if (player.level().isClientSide()) return;

        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack stack = player.getInventory().getItem(i);

            if (stack.isEmpty()) continue;

            CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
            CompoundTag tag = customData != null ? customData.copyTag() : new CompoundTag();
            boolean purified = tag.getBoolean("Purified");

            // 🍪 COOKIE → auto corrupt
            if (stack.getItem() == Items.COOKIE) {
                if (!purified) {
                    tag.putBoolean("Corrupted", true);
                    stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
                }
            }

            // 🍫 CORRUPTED CHOCOLATE (your item)
            if (stack.getItem() == SweetapocalpysebiomeModItems.CHOCOLATECORRUPTED.get()) {
                tag.putBoolean("Corrupted", true);

                // ⏳ TIMER SYSTEM (YOUR CODE ADDED HERE)
                int time = tag.getInt("PurifyTimer") + 1;
                tag.putInt("PurifyTimer", time);
                stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));

                if (time > 1200) { // 60 seconds
                    player.getInventory().setItem(i,
                        new ItemStack(SweetapocalpysebiomeModItems.CHOCOLATEFRAGMENT.get())
                    );
                }
            }

            // 🍫 NORMAL FRAGMENT (always purified)
            if (stack.getItem() == SweetapocalpysebiomeModItems.CHOCOLATEFRAGMENT.get()) {
                tag.putBoolean("Corrupted", false);
                tag.putBoolean("Purified", true);
                stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
            }
        }
    }
}