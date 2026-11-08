package net.mcreator.sweetapocalpysebiome.procedures;

import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import net.mcreator.sweetapocalpysebiome.FoodCorruptionHelper;
import net.mcreator.sweetapocalpysebiome.network.SweetapocalpysebiomeModVariables;

import javax.annotation.Nullable;

@EventBusSubscriber
public class PurifiedFoodAddPurificationProcedure {
	@SubscribeEvent
	public static void onItemUseFinish(LivingEntityUseItemEvent.Finish event) {
		if (event.getEntity() != null) {
			execute(event, event.getEntity(), event.getItem());
		}
	}

	public static void execute(net.minecraft.world.entity.Entity entity) {
		ItemStack stack = entity instanceof LivingEntity living ? living.getMainHandItem() : ItemStack.EMPTY;
		execute(null, entity, stack);
	}

	private static void execute(@Nullable Event event, net.minecraft.world.entity.Entity entity, ItemStack itemStack) {
		if (entity == null || FoodCorruptionHelper.isCorruptedFood(itemStack) || !FoodCorruptionHelper.isNormalFood(itemStack)) {
			return;
		}
		SweetapocalpysebiomeModVariables.PlayerVariables vars = entity.getData(SweetapocalpysebiomeModVariables.PLAYER_VARIABLES);
		// Eating normal food reduces corruption by 1 full stage (7.14 corruption)
		vars.Corruption = Math.max(0, vars.Corruption - GetCorruptionStageProcedure.STAGE_SIZE);
		vars.markSyncDirty();
	}
}
