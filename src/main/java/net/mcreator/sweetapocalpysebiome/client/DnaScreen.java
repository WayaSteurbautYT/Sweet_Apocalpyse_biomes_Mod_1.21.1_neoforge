package net.mcreator.sweetapocalpysebiome.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.sweetapocalpysebiome.SweetapocalpysebiomeMod;
import net.mcreator.sweetapocalpysebiome.capability.PlayerDnaCapability;
import net.mcreator.sweetapocalpysebiome.system.DnaType;
import net.mcreator.sweetapocalpysebiome.system.ApocalypseAbility;

import java.util.Set;

public class DnaScreen extends Screen {
	private static final ResourceLocation BACKGROUND = ResourceLocation.fromNamespaceAndPath(SweetapocalpysebiomeMod.MODID, "textures/gui/dna_screen.png");

	public DnaScreen() {
		super(Component.translatable("gui.sweetapocalpysebiome.dna_screen.title"));
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
		super.render(guiGraphics, mouseX, mouseY, partialTick);

		int x = (this.width - 256) / 2;
		int y = (this.height - 200) / 2;

		guiGraphics.blit(BACKGROUND, x, y, 0, 0, 256, 200, 256, 200);

		guiGraphics.drawString(this.font, Component.translatable("gui.sweetapocalpysebiome.dna_screen.title"), x + 10, y + 10, 0xFFFFFF);

		if (Minecraft.getInstance().player == null) {
			return;
		}

		PlayerDnaCapability cap = Minecraft.getInstance().player.getCapability(PlayerDnaCapability.CAPABILITY);
		if (cap == null) {
			return;
		}

		Set<DnaType> unlockedDna = cap.getUnlockedDna();
		int yOffset = 40;

		guiGraphics.drawString(this.font, Component.translatable("gui.sweetapocalpysebiome.dna_screen.unlocked"), x + 10, y + yOffset, 0xAAFFFF);
		yOffset += 20;

		for (DnaType dna : DnaType.values()) {
			boolean unlocked = unlockedDna.contains(dna);
			String status = unlocked ? "✔" : "✘";
			int color = unlocked ? 0x55FF55 : 0xFF5555;

			guiGraphics.drawString(this.font, 
				Component.literal(status + " " + dna.displayName().getString() + " - " + dna.abilityName().getString()),
				x + 15, y + yOffset, color);
			yOffset += 15;
		}

		guiGraphics.drawString(this.font, Component.translatable("gui.sweetapocalpysebiome.dna_screen.close"), x + 10, y + 180, 0xAAAAAA);
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}
}
