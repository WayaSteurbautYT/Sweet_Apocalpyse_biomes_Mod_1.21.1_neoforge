package net.mcreator.sweetapocalpysebiome.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.sweetapocalpysebiome.world.inventory.PurifyGUIMenu;
import net.mcreator.sweetapocalpysebiome.init.SweetapocalpysebiomeModScreens;
import net.mcreator.sweetapocalpysebiome.network.PurifyGUICraftPacket;
import net.mcreator.sweetapocalpysebiome.network.PurifyGUIStatePacket;

import com.mojang.blaze3d.systems.RenderSystem;

public class PurifyGUIScreen extends AbstractContainerScreen<PurifyGUIMenu> implements SweetapocalpysebiomeModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private Button button_purify;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("sweetapocalpysebiome:textures/screens/purify_gui.png");

	public PurifyGUIScreen(PurifyGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		menuStateUpdateActive = false;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		Object state = menu.menuState.get("purify_state");
		if (state instanceof PurifyGUIStatePacket purifyState) {
			guiGraphics.drawString(this.font, purifyState.status(), 8, 6, 0x404040, false);
			guiGraphics.drawString(this.font, purifyState.percent(), 86, 58, 0x404040, false);
		}
		// Show what is currently inside the chamber, so the GUI message area always lists the items.
		net.neoforged.neoforge.items.IItemHandler chamber = menu.getInternalHandler();
		if (chamber != null && chamber.getSlots() >= 3) {
			String summary = "Potion x" + chamber.getStackInSlot(0).getCount() + "  Food x" + chamber.getStackInSlot(1).getCount();
			guiGraphics.drawString(this.font, summary, 45, 68, 0x404040, false);
		}
	}

	@Override
	public void init() {
		super.init();
		button_purify = Button.builder(Component.translatable("gui.sweetapocalpysebiome.purify_gui.button_purify"), e -> PurifyGUICraftPacket.send())
				.bounds(this.leftPos + 60, this.topPos + 34, 55, 20).build();
		this.addRenderableWidget(button_purify);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		if (button_purify == null) {
			return;
		}
		Object state = menu.menuState.get("purify_state");
		if (state instanceof PurifyGUIStatePacket purifyState) {
			button_purify.setMessage(Component.literal(purifyState.button()));
			button_purify.active = purifyState.buttonActive();
		}
	}
}