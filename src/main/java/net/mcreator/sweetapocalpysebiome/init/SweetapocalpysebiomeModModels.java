/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.sweetapocalpysebiome.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.sweetapocalpysebiome.client.model.ModelStrawberry_Vine_Creeper;
import net.mcreator.sweetapocalpysebiome.client.model.ModelStrawberryVineCreeper;

@EventBusSubscriber(Dist.CLIENT)
public class SweetapocalpysebiomeModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ModelStrawberryVineCreeper.LAYER_LOCATION, ModelStrawberryVineCreeper::createBodyLayer);
		event.registerLayerDefinition(ModelStrawberry_Vine_Creeper.LAYER_LOCATION, ModelStrawberry_Vine_Creeper::createBodyLayer);
	}
}