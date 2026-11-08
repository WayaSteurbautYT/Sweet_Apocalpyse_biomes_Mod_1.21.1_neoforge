package net.mcreator.sweetapocalpysebiome.procedures;

import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.component.DataComponents;

import net.mcreator.sweetapocalpysebiome.network.SweetapocalpysebiomeModVariables;
import net.mcreator.sweetapocalpysebiome.init.SweetapocalpysebiomeModMobEffects;

import javax.annotation.Nullable;

@EventBusSubscriber
public class CorruptedFoodAddCorruptionProcedure {
	@SubscribeEvent
	public static void onUseItemStart(LivingEntityUseItemEvent.Start event) {
		if (event.getEntity() != null) {
			execute(event, event.getEntity());
		}
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).has(DataComponents.FOOD)
				&& (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("sweetapocalpysebiome:corrupted_foods")))) {
			{
				SweetapocalpysebiomeModVariables.PlayerVariables _vars = entity.getData(SweetapocalpysebiomeModVariables.PLAYER_VARIABLES);
				_vars.Corruption = entity.getData(SweetapocalpysebiomeModVariables.PLAYER_VARIABLES).Corruption + 10;
				_vars.markSyncDirty();
			}
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(SweetapocalpysebiomeModMobEffects.CORRUPTION_POTION_EFFECT, 1200, 0, true, false));
		}
	}
}