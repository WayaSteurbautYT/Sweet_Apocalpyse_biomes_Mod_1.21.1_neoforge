package net.mcreator.sweetapocalpysebiome.procedures;

import net.neoforged.neoforge.items.ItemHandlerHelper;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.client.Minecraft;
import net.minecraft.ChatFormatting;

import net.mcreator.sweetapocalpysebiome.init.SweetapocalpysebiomeModMobEffects;

public class ACookieGodEffectOnEntityDeathProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(SweetapocalpysebiomeModMobEffects.A_COOKIE_GOD_EFFECT)) {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("You Gained 1 Command Block"), false);
			if (world instanceof ServerLevel _level) {
				_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal("Has gained God POWERS").withColor(0x2aff95).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC), false);
			}
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(Blocks.COMMAND_BLOCK).copy();
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
			if (world.isClientSide())
				Minecraft.getInstance().gameRenderer.displayItemActivation(new ItemStack(Blocks.COMMAND_BLOCK));
		}
	}
}