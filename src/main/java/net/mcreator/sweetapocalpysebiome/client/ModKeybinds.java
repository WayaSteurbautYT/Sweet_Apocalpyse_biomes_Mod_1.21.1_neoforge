package net.mcreator.sweetapocalpysebiome.client;

import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.util.Lazy;

import org.lwjgl.glfw.GLFW;

import net.mcreator.sweetapocalpysebiome.SweetapocalpysebiomeMod;

@EventBusSubscriber(modid = SweetapocalpysebiomeMod.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModKeybinds {
	public static final Lazy<KeyMapping> OPEN_PURIFY_GUI = Lazy.of(() -> new KeyMapping("key.sweetapocalpysebiome.open_purify_gui", GLFW.GLFW_KEY_P, "key.categories.sweetapocalpysebiome"));

	@SubscribeEvent
	public static void register(RegisterKeyMappingsEvent event) {
		event.register(OPEN_PURIFY_GUI.get());
	}
}
