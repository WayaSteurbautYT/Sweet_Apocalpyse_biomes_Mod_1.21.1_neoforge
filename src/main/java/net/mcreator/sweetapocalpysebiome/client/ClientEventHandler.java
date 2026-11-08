package net.mcreator.sweetapocalpysebiome.client;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiEvent;

@EventBusSubscriber(value = Dist.CLIENT)
public class ClientEventHandler {
	@SubscribeEvent
	public static void onRenderGui(RenderGuiEvent.Post event) {
		SugarHudOverlay.onRenderOverlay(event);
		MutationUnlockOverlay.onRenderOverlay(event);
	}
}
