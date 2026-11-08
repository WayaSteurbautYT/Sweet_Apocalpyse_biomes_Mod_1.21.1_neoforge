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

import net.mcreator.sweetapocalpysebiome.procedures.PurifyGUIStage0Procedure;
import net.mcreator.sweetapocalpysebiome.procedures.PurifyGUIStage1Procedure;
import net.mcreator.sweetapocalpysebiome.procedures.PurifyGUIStage2Procedure;
import net.mcreator.sweetapocalpysebiome.procedures.PurifyGUIStage3Procedure;
import net.mcreator.sweetapocalpysebiome.procedures.PurifyGUIStage4Procedure;
import net.mcreator.sweetapocalpysebiome.procedures.PurifyGUIStage5Procedure;
import net.mcreator.sweetapocalpysebiome.procedures.PurifyGUIStage6Procedure;
import net.mcreator.sweetapocalpysebiome.procedures.PurifyGUIStage7Procedure;
import net.mcreator.sweetapocalpysebiome.procedures.PurifyGUIStage8Procedure;
import net.mcreator.sweetapocalpysebiome.procedures.PurifyGUIStage9Procedure;
import net.mcreator.sweetapocalpysebiome.procedures.PurifyGUIStage10Procedure;
import net.mcreator.sweetapocalpysebiome.procedures.PurifyGUIStage11Procedure;
import net.mcreator.sweetapocalpysebiome.procedures.PurifyGUIStage12Procedure;
import net.mcreator.sweetapocalpysebiome.procedures.PurifyGUIStage13Procedure;
import net.mcreator.sweetapocalpysebiome.procedures.PurifyGUIStage14Procedure;

@EventBusSubscriber(Dist.CLIENT)
public class PurifyBarOverlay {
	// Use same sprites as corruption for consistent HUD bar
	private static final ResourceLocation SPRITE_BASE = ResourceLocation.parse("sweetapocalpysebiome:textures/screens/emptcorruptedfoodbar.png");
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
		
		// Always show base bar
		event.getGuiGraphics().blit(SPRITE_BASE, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
		
		// Show stages based on purification level (going down from 100 to 0)
		// High purification = more stages shown, Low purification = fewer stages shown
		if (PurifyGUIStage0Procedure.execute(entity)) {
			// 100% purification - show all stages
			event.getGuiGraphics().blit(SPRITE_14, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_13, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_12, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_11, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_10, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_9, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_8, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_7, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_6, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_5, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_4, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_3, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_2, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_1, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
		}
		if (PurifyGUIStage1Procedure.execute(entity)) {
			// 90-100% purification - show stages 1-13
			event.getGuiGraphics().blit(SPRITE_13, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_12, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_11, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_10, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_9, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_8, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_7, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_6, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_5, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_4, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_3, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_2, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_1, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
		}
		if (PurifyGUIStage2Procedure.execute(entity)) {
			// 80-90% purification - show stages 1-12
			event.getGuiGraphics().blit(SPRITE_12, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_11, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_10, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_9, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_8, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_7, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_6, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_5, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_4, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_3, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_2, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_1, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
		}
		if (PurifyGUIStage3Procedure.execute(entity)) {
			// 70-80% purification - show stages 1-11
			event.getGuiGraphics().blit(SPRITE_11, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_10, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_9, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_8, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_7, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_6, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_5, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_4, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_3, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_2, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_1, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
		}
		if (PurifyGUIStage4Procedure.execute(entity)) {
			// 60-70% purification - show stages 1-10
			event.getGuiGraphics().blit(SPRITE_10, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_9, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_8, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_7, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_6, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_5, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_4, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_3, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_2, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_1, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
		}
		if (PurifyGUIStage5Procedure.execute(entity)) {
			// 50-60% purification - show stages 1-9
			event.getGuiGraphics().blit(SPRITE_9, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_8, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_7, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_6, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_5, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_4, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_3, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_2, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_1, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
		}
		if (PurifyGUIStage6Procedure.execute(entity)) {
			// 40-50% purification - show stages 1-8
			event.getGuiGraphics().blit(SPRITE_8, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_7, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_6, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_5, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_4, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_3, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_2, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_1, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
		}
		if (PurifyGUIStage7Procedure.execute(entity)) {
			// 30-40% purification - show stages 1-7
			event.getGuiGraphics().blit(SPRITE_7, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_6, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_5, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_4, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_3, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_2, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_1, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
		}
		if (PurifyGUIStage8Procedure.execute(entity)) {
			// 20-30% purification - show stages 1-6
			event.getGuiGraphics().blit(SPRITE_6, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_5, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_4, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_3, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_2, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_1, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
		}
		if (PurifyGUIStage9Procedure.execute(entity)) {
			// 10-20% purification - show stages 1-5
			event.getGuiGraphics().blit(SPRITE_5, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_4, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_3, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_2, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_1, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
		}
		if (PurifyGUIStage10Procedure.execute(entity)) {
			// 5-10% purification - show stages 1-4
			event.getGuiGraphics().blit(SPRITE_4, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_3, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_2, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_1, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
		}
		if (PurifyGUIStage11Procedure.execute(entity)) {
			// 2-5% purification - show stages 1-3
			event.getGuiGraphics().blit(SPRITE_3, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_2, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_1, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
		}
		if (PurifyGUIStage12Procedure.execute(entity)) {
			// 1-2% purification - show stages 1-2
			event.getGuiGraphics().blit(SPRITE_2, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
			event.getGuiGraphics().blit(SPRITE_1, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
		}
		if (PurifyGUIStage13Procedure.execute(entity)) {
			// 0-1% purification - show stage 1
			event.getGuiGraphics().blit(SPRITE_1, w / 2 + 121, h / 2 + 66, 0, 0, 81, 16, 81, 16);
		}
		// Stage 14 (0% purification) shows only the base bar (already shown above)
	}
}