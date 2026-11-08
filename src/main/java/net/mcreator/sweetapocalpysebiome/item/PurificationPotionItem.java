package net.mcreator.sweetapocalpysebiome.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

import net.mcreator.sweetapocalpysebiome.init.SweetapocalpysebiomeModMobEffects;
import net.mcreator.sweetapocalpysebiome.network.SweetapocalpysebiomeModVariables;

public class PurificationPotionItem extends Item {
    public PurificationPotionItem() {
        super(new Item.Properties().stacksTo(16).rarity(Rarity.RARE));
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 32;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide() && entity instanceof Player player) {
            // Apply the Purify Potion Effect
            player.addEffect(new MobEffectInstance(SweetapocalpysebiomeModMobEffects.PURIFY, 400, 0, true, false));
        }
        
        stack.shrink(1);
        return stack;
    }
}
