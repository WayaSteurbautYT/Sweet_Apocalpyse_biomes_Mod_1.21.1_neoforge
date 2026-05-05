package net.mcreator.sweetapocalpysebiome.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class SweetAuraPotionItem extends Item {
	public SweetAuraPotionItem() {
		super(new Item.Properties().rarity(Rarity.RARE).stacksTo(1));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
		// Apply sweet aura effects
		entity.addEffect(new MobEffectInstance(MobEffects.SATURATION, 600, 0));
		entity.addEffect(new MobEffectInstance(MobEffects.LUCK, 1200, 1));
		
		// TODO: Add custom cookie god effect when available
		
		if (stack.hasCraftingRemainingItem()) {
			return stack.getCraftingRemainingItem();
		}
		return ItemStack.EMPTY;
	}
}
