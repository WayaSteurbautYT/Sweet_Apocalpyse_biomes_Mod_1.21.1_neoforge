package net.mcreator.sweetapocalpysebiome.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.event.RenderGuiEvent;

import net.mcreator.sweetapocalpysebiome.SweetapocalpysebiomeMod;
import net.mcreator.sweetapocalpysebiome.capability.PlayerSugarCapability;

public class SugarHudOverlay {
	private static final ResourceLocation SUGAR_BAR_TEXTURE = ResourceLocation.fromNamespaceAndPath(SweetapocalpysebiomeMod.MODID, "textures/gui/sugar_bar.png");
	private static final int BAR_WIDTH = 81;
	private static final int BAR_HEIGHT = 5;
	private static final int BAR_X = 109;
	private static final int BAR_Y = 49;

	public static void onRenderOverlay(RenderGuiEvent.Post event) {
		if (Minecraft.getInstance().screen != null) {
			return;
		}

		Minecraft mc = Minecraft.getInstance();
		if (mc.player == null) {
			return;
		}

		PlayerSugarCapability cap = mc.player.getCapability(PlayerSugarCapability.CAPABILITY);
		if (cap == null) {
			return;
		}

		GuiGraphics guiGraphics = event.getGuiGraphics();
		int currentSugar = cap.getCurrentSugar();
		int maxSugar = cap.getMaxSugar();
		float ratio = cap.getSugarRatio();

		int filledWidth = (int) (BAR_WIDTH * ratio);
		int x = BAR_X;
		int y = BAR_Y;

		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		
		if (cap.isSugarRush()) {
			RenderSystem.setShaderColor(1.0F, 0.5F, 0.5F, 1.0F);
		} else if (cap.isHypoglycemia()) {
			RenderSystem.setShaderColor(0.5F, 0.5F, 1.0F, 1.0F);
		}

		guiGraphics.blit(SUGAR_BAR_TEXTURE, x, y, 0, 0, BAR_WIDTH, BAR_HEIGHT, BAR_WIDTH, BAR_HEIGHT);
		guiGraphics.blit(SUGAR_BAR_TEXTURE, x, y, 0, BAR_HEIGHT, filledWidth, BAR_HEIGHT, BAR_WIDTH, BAR_HEIGHT * 2);

		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
	}
}
