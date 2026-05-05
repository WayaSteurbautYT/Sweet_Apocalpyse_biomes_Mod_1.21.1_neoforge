package net.mcreator.sweetapocalpysebiome;

import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.entity.player.Player;

@EventBusSubscriber
public class WhenOnPlayerDrinksPurifyPotion {

    @SubscribeEvent
    public static void onDrink(LivingEntityUseItemEvent.Finish event) {
        if (!(event.getEntity() instanceof Player player)) return;

        // 🔥 CHANGE THIS TO YOUR POTION ITEM
        if (event.getItem().getItem().toString().contains("Purifypotion")) {
            OnplayerPurifyUse.purifyInventory(player);
        }
    }
}