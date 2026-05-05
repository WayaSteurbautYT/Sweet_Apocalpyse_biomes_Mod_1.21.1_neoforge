package net.mcreator.sweetapocalpysebiome.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class CorruptedBrewPotionItem extends Item {
	public CorruptedBrewPotionItem() {
		super(new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
		// Apply corrupted brew effects
		entity.addEffect(new MobEffectInstance(MobEffects.POISON, 400, 1));
		entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 600, 0));
		entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 200, 0));
		
		// TODO: Add custom skin damage effect when available
		
		if (stack.hasCraftingRemainingItem()) {
			return stack.getCraftingRemainingItem();
		}
		return ItemStack.EMPTY;
	}
}
