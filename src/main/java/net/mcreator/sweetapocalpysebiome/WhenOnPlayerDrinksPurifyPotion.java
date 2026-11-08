package net.mcreator.sweetapocalpysebiome;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import net.mcreator.sweetapocalpysebiome.procedures.StartPurifyPotionReductionProcedure;

@EventBusSubscriber
public class WhenOnPlayerDrinksPurifyPotion {
	@SubscribeEvent
	public static void onDrink(LivingEntityUseItemEvent.Finish event) {
		if (!(event.getEntity() instanceof Player player) || player.level().isClientSide()) {
			return;
		}
		ItemStack stack = event.getItem();
		if (FoodCorruptionHelper.isPurifyPotion(stack)) {
			StartPurifyPotionReductionProcedure.execute(player);
		}
	}
}
