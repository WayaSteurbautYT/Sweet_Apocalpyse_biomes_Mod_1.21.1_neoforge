package net.mcreator.sweetapocalpysebiome.client.screens;

import net.neoforged.neoforge.client.event.RenderGuiEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.Minecraft;

import net.mcreator.sweetapocalpysebiome.procedures.CorruptionGUIStage2Procedure;

@EventBusSubscriber(Dist.CLIENT)
public class CorruptionBarOverlay {
	private static final ResourceLocation SPRITE_0 = ResourceLocation.parse("sweetapocalpysebiome:textures/screens/emptcorruptedfoodbar.png");
	private static final ResourceLocation SPRITE_1 = ResourceLocation.parse("sweetapocalpysebiome:textures/screens/emptcorruptedfoodbar_stage1.png");
	private static final ResourceLocation SPRITE_2 = ResourceLocation.parse("sweetapocalpysebiome:textures/screens/emptcorruptedfoodbar_stage2.png");
	private static final ResourceLocation SPRITE_3 = ResourceLocation.parse("sweetapocalpysebiome:textures/screens/emptcorruptedfoodbar_stage3.png");
	private static final ResourceLocation SPRITE_4 = ResourceLocation.parse("sweetapocalpysebiome:textures/screens/emptcorruptedfoodbar_stage4.png");
	private static final ResourceLocation SPRITE_5 = ResourceLocation.parse("sweetapocalpysebiome:textures/screens/emptcorruptedfoodbar_stage5.png");
	private static final ResourceLocation SPRITE_6 = ResourceLocation.parse("sweetapocalpysebiome:textures/screens/emptcorruptedfoodbar_stage6.png");
	private static final ResourceLocation SPRITE_7 = ResourceLocation.parse("sweetapocalpysebiome:textures/screens/emptcorruptedfoodbar_stage7.png");
	private static final ResourceLocation SPRITE_8 = ResourceLocation.parse("sweetapocalpysebiome:textures/screens/emptcorruptedfoodbar_stage8.png");
	private static final ResourceLocation SPRITE_9 = ResourceLocation.parse("sweetapocalpysebiome:textures/screens/emptcorruptedfoodbar_stage9.png");
	private static final ResourceLocation SPRITE_10 = ResourceLocation.parse("sweetapocalpysebiome:textures/screens/emptcorruptedfoodbar_stage10.png");
	private static final ResourceLocation SPRITE_11 = ResourceLocation.parse("sweetapocalpysebiome:textures/screens/emptcorruptedfoodbar_stage11.png");
	private static final ResourceLocation SPRITE_12 = ResourceLocation.parse("sweetapocalpysebiome:textures/screens/emptcorruptedfoodbar_stage12.png");
	private static final ResourceLocation SPRITE_13 = ResourceLocation.parse("sweetapocalpysebiome:textures/screens/emptcorruptedfoodbar_stage13.png");
	private static final ResourceLocation SPRITE_14 = ResourceLocation.parse("sweetapocalpysebiome:textures/screens/emptcorruptedfoodbar_stage14.png");

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void eventHandler(RenderGuiEvent.Pre event) {
		int w = event.getGuiGraphics().guiWidth();
		int h = event.getGuiGraphics().guiHeight();
		Level world = null;
		double x = 0;
		double y = 0;
		double z = 0;
		Player entity = Minecraft.getInstance().player;
		if (entity != null) {
			world = entity.level();
			x = entity.getX();
			y = entity.getY();
			z = entity.getZ();
		}
		if (CorruptionGUIStage2Procedure.execute(entity)) {

			event.getGuiGraphics().blit(SPRITE_0, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);

			event.getGuiGraphics().blit(SPRITE_1, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);

			event.getGuiGraphics().blit(SPRITE_2, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);

			event.getGuiGraphics().blit(SPRITE_3, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);

			event.getGuiGraphics().blit(SPRITE_4, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);

			event.getGuiGraphics().blit(SPRITE_5, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);

			event.getGuiGraphics().blit(SPRITE_6, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);

			event.getGuiGraphics().blit(SPRITE_7, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);

			event.getGuiGraphics().blit(SPRITE_8, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);

			event.getGuiGraphics().blit(SPRITE_9, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);

			event.getGuiGraphics().blit(SPRITE_10, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);

			event.getGuiGraphics().blit(SPRITE_11, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);

			event.getGuiGraphics().blit(SPRITE_12, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);

			event.getGuiGraphics().blit(SPRITE_13, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);

			event.getGuiGraphics().blit(SPRITE_14, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);

		}
	}
}