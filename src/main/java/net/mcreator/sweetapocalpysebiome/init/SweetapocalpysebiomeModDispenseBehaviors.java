/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.sweetapocalpysebiome.init;

import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.core.dispenser.BoatDispenseItemBehavior;

@EventBusSubscriber
public class SweetapocalpysebiomeModDispenseBehaviors {
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			DispenserBlock.registerBehavior(SweetapocalpysebiomeModItems.CHOCOLATE_BOAT.get(), new BoatDispenseItemBehavior(SweetapocalpysebiomeModBoatTypes.CHOCOLATE_BOAT_TYPE.getValue()));
			DispenserBlock.registerBehavior(SweetapocalpysebiomeModItems.CHOCOLATE_CHEST_BOAT.get(), new BoatDispenseItemBehavior(SweetapocalpysebiomeModBoatTypes.CHOCOLATE_CHEST_BOAT_TYPE.getValue(), true));
		});
	}
}