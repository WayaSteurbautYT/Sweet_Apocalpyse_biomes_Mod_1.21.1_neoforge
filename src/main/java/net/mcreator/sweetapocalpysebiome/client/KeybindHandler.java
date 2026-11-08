package net.mcreator.sweetapocalpysebiome.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

import net.minecraft.client.Minecraft;

import net.mcreator.sweetapocalpysebiome.network.OpenPurifyGUIPacket;

@EventBusSubscriber(Dist.CLIENT)
public class KeybindHandler {
	@SubscribeEvent
	public static void onClientTick(ClientTickEvent.Post event) {
		Minecraft mc = Minecraft.getInstance();
		if (mc.player == null || mc.screen != null) {
			return;
		}
		while (ModKeybinds.OPEN_PURIFY_GUI.get().consumeClick()) {
			if (mc.options.keyShift.isDown()) {
				OpenPurifyGUIPacket.send();
			}
		}
	}
}
