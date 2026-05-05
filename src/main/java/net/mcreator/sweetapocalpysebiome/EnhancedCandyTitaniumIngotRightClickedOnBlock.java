package net.mcreator.sweetapocalpysebiome;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.network.chat.Component;

public class EnhancedCandyTitaniumIngotRightClickedOnBlock {

	public static void execute(Level world, Player player, ItemStack stack) {
		// Give candy-themed effects when right-clicked
		player.addEffect(new MobEffectInstance(MobEffects.SATURATION, 200, 0));
		player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0));
		player.sendSystemMessage(Component.literal("§6You feel the candy power flowing through you!§r"));
	}
}
