package net.mcreator.sweetapocalpysebiome;

import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

@EventBusSubscriber
public class CorruptedFoodEffect {

    @SubscribeEvent
    public static void onItemUseFinish(LivingEntityUseItemEvent.Finish event) {
        if (!(event.getEntity() instanceof Player player)) return;

        ItemStack stack = event.getItem();

        if (stack.getItem().getFoodProperties(stack, player) != null) {
            CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
            if (customData != null && customData.copyTag().getBoolean("Corrupted")) {
                player.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 0));
                player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 200, 1));
            }
        }
    }
}