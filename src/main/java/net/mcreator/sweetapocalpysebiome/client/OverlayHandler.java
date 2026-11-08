package net.mcreator.sweetapocalpysebiome.client;

import net.neoforged.neoforge.client.event.RenderGuiEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.sweetapocalpysebiome.network.SweetapocalpysebiomeModVariables;

@EventBusSubscriber
public class OverlayHandler {
    
    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiEvent.Post event) {
        // Disabled for now since bar overlays handle the display
        // This can be used for additional UI elements if needed
    }
}
