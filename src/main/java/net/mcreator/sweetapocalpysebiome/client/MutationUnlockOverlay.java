package net.mcreator.sweetapocalpysebiome.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.event.RenderGuiEvent;

import net.mcreator.sweetapocalpysebiome.SweetapocalpysebiomeMod;
import net.mcreator.sweetapocalpysebiome.system.DnaType;

public class MutationUnlockOverlay {
	private static DnaType pendingUnlock = null;
	private static int displayTimer = 0;
	private static final int DISPLAY_DURATION = 100;

	public static void showUnlock(DnaType dnaType) {
		pendingUnlock = dnaType;
		displayTimer = DISPLAY_DURATION;
	}

	public static void onRenderOverlay(RenderGuiEvent.Post event) {
		if (pendingUnlock == null || displayTimer <= 0) {
			return;
		}

		displayTimer--;
		if (displayTimer <= 0) {
			pendingUnlock = null;
			return;
		}

		Minecraft mc = Minecraft.getInstance();
		GuiGraphics guiGraphics = event.getGuiGraphics();

		int screenWidth = mc.getWindow().getGuiScaledWidth();
		int screenHeight = mc.getWindow().getGuiScaledHeight();

		int x = screenWidth / 2 - 100;
		int y = screenHeight / 2 - 50;

		float alpha = Math.min(1.0f, (float) displayTimer / 20.0f);
		if (displayTimer > DISPLAY_DURATION - 20) {
			alpha = Math.min(1.0f, (float) (DISPLAY_DURATION - displayTimer) / 20.0f);
		}

		guiGraphics.fill(x, y, x + 200, y + 100, (int) (0x80000000 * alpha));

		Component title = Component.translatable("gui.sweetapocalpysebiome.mutation_unlock.title");
		Component dnaName = pendingUnlock.displayName();
		Component abilityName = pendingUnlock.abilityName();

		guiGraphics.drawString(mc.font, title, x + 10, y + 10, 0xFFFFFF);
		guiGraphics.drawString(mc.font, dnaName, x + 10, y + 35, 0xAAFFFF);
		guiGraphics.drawString(mc.font, Component.translatable("gui.sweetapocalpysebiome.mutation_unlock.ability"), x + 10, y + 55, 0xAAAAAA);
		guiGraphics.drawString(mc.font, abilityName, x + 10, y + 70, 0x55FF55);
	}
}
