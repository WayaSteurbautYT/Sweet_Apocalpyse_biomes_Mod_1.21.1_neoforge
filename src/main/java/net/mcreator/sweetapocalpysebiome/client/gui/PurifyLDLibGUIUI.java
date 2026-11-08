package net.mcreator.sweetapocalpysebiome.client.gui;

import org.joml.Vector2f;

import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

import net.mcreator.sweetapocalpysebiome.client.gui.ldlib.LDLibGUIContext;
import net.mcreator.sweetapocalpysebiome.SweetapocalpysebiomeMod;
import net.mcreator.sweetapocalpysebiome.PurifyGUIElementCustom;

import javax.annotation.Nullable;

import dev.vfyjxf.taffy.style.TaffyPosition;
import dev.vfyjxf.taffy.style.FlexDirection;
import dev.vfyjxf.taffy.style.AlignItems;
import dev.vfyjxf.taffy.style.AlignContent;

import com.lowdragmc.lowdraglib2.gui.util.WindowDragHelper;
import com.lowdragmc.lowdraglib2.gui.ui.styletemplate.Sprites;
import com.lowdragmc.lowdraglib2.gui.ui.style.StylesheetManager;
import com.lowdragmc.lowdraglib2.gui.ui.event.UIEvents;
import com.lowdragmc.lowdraglib2.gui.ui.elements.inventory.InventorySlots;
import com.lowdragmc.lowdraglib2.gui.ui.elements.Label;
import com.lowdragmc.lowdraglib2.gui.ui.elements.ItemSlot;
import com.lowdragmc.lowdraglib2.gui.ui.elements.Button;
import com.lowdragmc.lowdraglib2.gui.ui.UIElement;
import com.lowdragmc.lowdraglib2.gui.ui.UI;
import com.lowdragmc.lowdraglib2.gui.ui.ModularUI;
import com.lowdragmc.lowdraglib2.gui.factory.PlayerUIMenuType;
import com.lowdragmc.lowdraglib2.gui.ui.event.UIEvents;
import net.mcreator.sweetapocalpysebiome.procedures.PurifyLDLibPurifyButtonClickProcedure;
import com.lowdragmc.lowdraglib2.gui.ui.elements.ProgressBar;

@EventBusSubscriber
public class PurifyLDLibGUIUI {
	public static final ResourceLocation UI_ID = ResourceLocation.fromNamespaceAndPath(SweetapocalpysebiomeMod.MODID, "purify_ld_lib_gui");

	@SubscribeEvent
	public static void onCommonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> PlayerUIMenuType.register(UI_ID, openPlayer -> new PlayerUIMenuType.PlayerUIHolder() {
			@Override
			public ModularUI createUI(Player p) {
				LDLibGUIContext.setOpenUI(p, UI_ID);
				return createModularUI(p);
			}

			@Override
			public boolean isStillValid(Player p) {
				var ctx = LDLibGUIContext.getContext(p);
				if (ctx != null && ctx.source == LDLibGUIContext.OpenSource.ITEM && ctx.hand != null) {
					// Held-item open: close when the hand is empty
					return !p.getItemInHand(ctx.hand).isEmpty();
				}
				if (ctx == null)
					return true;
				if (ctx.source == LDLibGUIContext.OpenSource.ITEM)
					return true;
				BlockPos bp = ctx.blockPos;
				if (bp == null)
					return true;
				// Block broken / replaced - close
				if (p.level().isEmptyBlock(bp))
					return false;
				// Walked away - close (default 8 blocks)
				double maxDistSq = 64;
				return p.distanceToSqr(bp.getX() + 0.5, bp.getY() + 0.5, bp.getZ() + 0.5) <= maxDistSq;
			}
		}));
	}

	/** Open from a procedure (server-side). Stores x/y/z for block-bound capabilities. */
	public static boolean open(Player player, int x, int y, int z) {
		if (!(player instanceof ServerPlayer))
			return false;
		LDLibGUIContext.set(player, x, y, z, UI_ID);
		return PlayerUIMenuType.openUI(player, UI_ID);
	}

	/** Convenience for block right-click procedures. */
	public static boolean openAtBlock(Player player, BlockPos pos) {
		PurifyGUIElementCustom.bindInventoryToBlock(player, pos);
		return open(player, pos.getX(), pos.getY(), pos.getZ());
	}

	/**
	 * Open from a held item (item right-click procedures). Uses PlayerUI under the hood so
	 * no HeldItemUI interface is required on the Item class (true no-code). Item/fluid slots
	 * bind to the held stack's capabilities when present.
	 */
	public static boolean openFromHeldItem(Player player, InteractionHand hand) {
		if (!(player instanceof ServerPlayer))
			return false;
		if (hand == null)
			hand = InteractionHand.MAIN_HAND;
		if (player.getItemInHand(hand).isEmpty())
			return false;
		LDLibGUIContext.setFromItem(player, hand, UI_ID);
		return PlayerUIMenuType.openUI(player, UI_ID);
	}

	/** Load UITemplate NBT from /ldlib2_ui_editor exports (multiple formats supported). */
	@Nullable
	private static UI loadUiTemplate() {
		return null;
	}

	public static ModularUI createModularUI(Player player) {
		int[] pos = LDLibGUIContext.get(player);
		final int x = pos[0];
		final int y = pos[1];
		final int z = pos[2];
		final Level world = player.level();
		final Entity entity = player;
		final BlockPos blockPos = new BlockPos(x, y, z);
		// Prefer bound handlers: block (open at block), then held item, then local buffer
		IItemHandler boundItems = LDLibGUIContext.getBoundItemHandler(player);
		IFluidHandler boundFluids = LDLibGUIContext.getBoundFluidHandler(player);
		if (boundItems == null) {
			// Legacy fallback: direct block capability at open pos
			boundItems = world.getCapability(Capabilities.ItemHandler.BLOCK, blockPos, null);
		}
		if (boundFluids == null) {
			boundFluids = world.getCapability(Capabilities.FluidHandler.BLOCK, blockPos, null);
		}
		final IItemHandlerModifiable itemHandler = PurifyGUIElementCustom.getOrCreatePurifyInventory(player);
		final java.util.Map<Integer, FluidTank> fluidTanks = new java.util.HashMap<>();
		final IFluidHandler fluidHandlerRoot = boundFluids;
		// Free-window mode (move/resize): full-screen absolute root + absolute window panel.
		// RELATIVE fixed-size roots are centered by ModularUI as a fixed viewport, so left/top
		// from WindowDragHelper never move the panel on screen (only right/bottom width/height work).
		// Matches LDLib Dialog.windowMode / UIDebugger free-window pattern.
		final boolean freeWindow = true;
		final float _ldWinW = 250;
		final float _ldWinH = 200;
		final float _ldResizeBorder = (float) 6;
		UIElement root;
		UIElement window = new UIElement().setId("window");
		if (freeWindow) {
			root = new UIElement().setId("root");
			root.layout(layout -> layout.positionType(TaffyPosition.ABSOLUTE).widthPercent(100f).heightPercent(100f));
			window.layout(layout -> layout.positionType(TaffyPosition.ABSOLUTE).width(_ldWinW).height(_ldWinH).flexDirection(FlexDirection.COLUMN).gapAll(2f));
		} else {
			root = window;
			window.layout(layout -> layout.width(_ldWinW).height(_ldWinH).flexDirection(FlexDirection.COLUMN).gapAll(2f));
		}
		window.addClass("panel_bg");
		window.style(style -> style.backgroundTexture(Sprites.BORDER));
		UIElement titleBar = new UIElement().setId("title_bar");
		titleBar.layout(layout -> layout.widthPercent(100f).height(14f).flexDirection(FlexDirection.ROW).alignItems(AlignItems.CENTER).justifyContent(AlignContent.FLEX_START).paddingAll(2f).gapAll(3f));
		titleBar.style(style -> style.backgroundTexture(Sprites.RECT_RD_DARK));
		Label titleLabel = new Label();
		titleLabel.setId("window_title");
		titleLabel.setText(Component.translatable("gui.sweetapocalpysebiome.purify_ld_lib_gui.window_title"));
		// No flexGrow — size to text so LEFT/CENTER/RIGHT justify on the title bar works
		titleLabel.layout(layout -> layout.heightAuto().widthAuto());
		titleBar.addChild(titleLabel);
		window.addChild(titleBar);
		// Drag from title chrome AND title text (labels can receive the click first)
		WindowDragHelper.setDragMove(titleBar, window, e -> e.button == 0, e -> window.adaptPositionToScreen());
		WindowDragHelper.setDragMove(titleLabel, window, e -> e.button == 0, e -> window.adaptPositionToScreen());
		UIElement content = new UIElement().setId("content");
		content.layout(layout -> layout.widthPercent(100f).flexGrow(1f).flexDirection(FlexDirection.COLUMN).alignItems(AlignItems.FLEX_START).gapAll(2f).paddingAll(4f));
		// Keep nested designer content inside the window content well
		content.setOverflowVisible(false);
		window.setOverflowVisible(false);
		UI templateUi = loadUiTemplate();
		{
			// Chamber title
			Label _n_chamber_title = new Label();
			_n_chamber_title.setId("chamber_title");
			_n_chamber_title.setText(Component.translatable("gui.sweetapocalpysebiome.purify_ld_lib_gui.chamber_title"));
			_n_chamber_title.layout(layout -> {
				layout.positionType(TaffyPosition.RELATIVE);
				layout.widthAuto();
				layout.height((float) 10);
				layout.flexGrow((float) 0);
				layout.flexShrink((float) 0);
				layout.gapAll((float) 4);
				layout.paddingAll((float) 0);
				layout.marginAll((float) 0);
				layout.left((float) 0);
				layout.top((float) 0);
			});
			content.addChild(_n_chamber_title);
		}
		{
			// Chamber panel with row layout
			UIElement _n_chamber_panel = new UIElement();
			_n_chamber_panel.setId("chamber_panel");
			_n_chamber_panel.layout(layout -> {
				layout.positionType(TaffyPosition.RELATIVE);
				layout.widthPercent((float) 100);
				layout.height((float) 42);
				layout.flexGrow((float) 0);
				layout.flexShrink((float) 0);
				layout.gapAll((float) 4);
				layout.paddingAll((float) 3);
				layout.marginAll((float) 0);
				layout.left((float) 0);
				layout.top((float) 0);
				layout.flexDirection(FlexDirection.ROW);
			});
			_n_chamber_panel.style(style -> style.backgroundTexture(Sprites.BORDER));
			
			// Potion panel (left side)
			UIElement _n_potion_panel = new UIElement();
			_n_potion_panel.setId("potion_panel");
			_n_potion_panel.layout(layout -> {
				layout.positionType(TaffyPosition.RELATIVE);
				layout.width((float) 46);
				layout.height((float) 34);
				layout.flexGrow((float) 0);
				layout.flexShrink((float) 0);
				layout.gapAll((float) 2);
				layout.paddingAll((float) 2);
				layout.marginAll((float) 0);
				layout.left((float) 0);
				layout.top((float) 0);
				layout.flexDirection(FlexDirection.COLUMN);
			});
			
			Label _n_potion_label = new Label();
			_n_potion_label.setId("potion_label");
			_n_potion_label.setText(Component.translatable("gui.sweetapocalpysebiome.purify_ld_lib_gui.potion_label"));
			_n_potion_label.layout(layout -> {
				layout.positionType(TaffyPosition.RELATIVE);
				layout.widthPercent((float) 100);
				layout.height((float) 10);
				layout.flexGrow((float) 0);
				layout.flexShrink((float) 0);
				layout.gapAll((float) 2);
				layout.paddingAll((float) 2);
				layout.marginAll((float) 0);
				layout.left((float) 0);
				layout.top((float) 0);
			});
			_n_potion_panel.addChild(_n_potion_label);
			
			ItemSlot _n_potion_slot = new ItemSlot();
			_n_potion_slot.setId("potion_slot");
			if (itemHandler.getSlots() > 0) {
				_n_potion_slot.bind(new SlotItemHandler(itemHandler, 0, 0, 0));
			}
			_n_potion_slot.layout(layout -> {
				layout.positionType(TaffyPosition.RELATIVE);
				layout.width((float) 18);
				layout.height((float) 18);
				layout.flexGrow((float) 0);
				layout.flexShrink((float) 0);
				layout.gapAll((float) 4);
				layout.paddingAll((float) 4);
				layout.marginAll((float) 0);
				layout.left((float) 0);
				layout.top((float) 0);
			});
			_n_potion_panel.addChild(_n_potion_slot);
			_n_chamber_panel.addChild(_n_potion_panel);
			
			// Core panel (center)
			UIElement _n_core_panel = new UIElement();
			_n_core_panel.setId("core_panel");
			_n_core_panel.layout(layout -> {
				layout.positionType(TaffyPosition.RELATIVE);
				layout.width((float) 74);
				layout.height((float) 34);
				layout.flexGrow((float) 0);
				layout.flexShrink((float) 0);
				layout.gapAll((float) 1);
				layout.paddingAll((float) 2);
				layout.marginAll((float) 0);
				layout.left((float) 0);
				layout.top((float) 0);
				layout.flexDirection(FlexDirection.COLUMN);
			});
			
			Label _n_core_label = new Label();
			_n_core_label.setId("core_label");
			_n_core_label.setText(Component.translatable("gui.sweetapocalpysebiome.purify_ld_lib_gui.core_label"));
			_n_core_label.layout(layout -> {
				layout.positionType(TaffyPosition.RELATIVE);
				layout.widthPercent((float) 100);
				layout.height((float) 10);
				layout.flexGrow((float) 0);
				layout.flexShrink((float) 0);
				layout.gapAll((float) 2);
				layout.paddingAll((float) 2);
				layout.marginAll((float) 0);
				layout.left((float) 0);
				layout.top((float) 0);
			});
			_n_core_panel.addChild(_n_core_label);
			
			ProgressBar _n_progress_bar = new ProgressBar();
			_n_progress_bar.setId("progress_bar");
			_n_progress_bar.layout(layout -> {
				layout.positionType(TaffyPosition.RELATIVE);
				layout.width((float) 60);
				layout.height((float) 8);
				layout.flexGrow((float) 0);
				layout.flexShrink((float) 0);
				layout.gapAll((float) 1);
				layout.paddingAll((float) 1);
				layout.marginAll((float) 0);
				layout.left((float) 0);
				layout.top((float) 0);
			});
			_n_core_panel.addChild(_n_progress_bar);
			
			Label _n_progress_percent = new Label();
			_n_progress_percent.setId("progress_percent");
			_n_progress_percent.setText(Component.translatable("gui.sweetapocalpysebiome.purify_ld_lib_gui.progress_percent"));
			_n_progress_percent.layout(layout -> {
				layout.positionType(TaffyPosition.RELATIVE);
				layout.widthPercent((float) 100);
				layout.height((float) 10);
				layout.flexGrow((float) 0);
				layout.flexShrink((float) 0);
				layout.gapAll((float) 2);
				layout.paddingAll((float) 2);
				layout.marginAll((float) 0);
				layout.left((float) 0);
				layout.top((float) 0);
			});
			_n_core_panel.addChild(_n_progress_percent);
			_n_chamber_panel.addChild(_n_core_panel);
			
			// Corrupted panel (right side)
			UIElement _n_corrupted_panel = new UIElement();
			_n_corrupted_panel.setId("corrupted_panel");
			_n_corrupted_panel.layout(layout -> {
				layout.positionType(TaffyPosition.RELATIVE);
				layout.width((float) 46);
				layout.height((float) 34);
				layout.flexGrow((float) 0);
				layout.flexShrink((float) 0);
				layout.gapAll((float) 2);
				layout.paddingAll((float) 2);
				layout.marginAll((float) 0);
				layout.left((float) 0);
				layout.top((float) 0);
				layout.flexDirection(FlexDirection.COLUMN);
			});
			
			Label _n_corrupted_label = new Label();
			_n_corrupted_label.setId("corrupted_label");
			_n_corrupted_label.setText(Component.translatable("gui.sweetapocalpysebiome.purify_ld_lib_gui.corrupted_label"));
			_n_corrupted_label.layout(layout -> {
				layout.positionType(TaffyPosition.RELATIVE);
				layout.widthPercent((float) 100);
				layout.height((float) 10);
				layout.flexGrow((float) 0);
				layout.flexShrink((float) 0);
				layout.gapAll((float) 2);
				layout.paddingAll((float) 2);
				layout.marginAll((float) 0);
				layout.left((float) 0);
				layout.top((float) 0);
			});
			_n_corrupted_panel.addChild(_n_corrupted_label);
			
			ItemSlot _n_corrupted_food = new ItemSlot();
			_n_corrupted_food.setId("corrupted_food");
			if (itemHandler.getSlots() > 1) {
				_n_corrupted_food.bind(new SlotItemHandler(itemHandler, 1, 0, 0));
			}
			_n_corrupted_food.layout(layout -> {
				layout.positionType(TaffyPosition.RELATIVE);
				layout.width((float) 18);
				layout.height((float) 18);
				layout.flexGrow((float) 0);
				layout.flexShrink((float) 0);
				layout.gapAll((float) 4);
				layout.paddingAll((float) 4);
				layout.marginAll((float) 0);
				layout.left((float) 0);
				layout.top((float) 0);
			});
			_n_corrupted_panel.addChild(_n_corrupted_food);
			_n_chamber_panel.addChild(_n_corrupted_panel);
			content.addChild(_n_chamber_panel);
		}
		{
			// Output panel
			UIElement _n_output_panel = new UIElement();
			_n_output_panel.setId("output_panel");
			_n_output_panel.layout(layout -> {
				layout.positionType(TaffyPosition.RELATIVE);
				layout.widthPercent((float) 100);
				layout.height((float) 20);
				layout.flexGrow((float) 0);
				layout.flexShrink((float) 0);
				layout.gapAll((float) 4);
				layout.paddingAll((float) 1);
				layout.marginAll((float) 0);
				layout.left((float) 0);
				layout.top((float) 0);
				layout.flexDirection(FlexDirection.ROW);
			});
			_n_output_panel.style(style -> style.backgroundTexture(Sprites.BORDER));
			
			Label _n_output_label = new Label();
			_n_output_label.setId("output_label");
			_n_output_label.setText(Component.translatable("gui.sweetapocalpysebiome.purify_ld_lib_gui.output_label"));
			_n_output_label.layout(layout -> {
				layout.positionType(TaffyPosition.RELATIVE);
				layout.width((float) 46);
				layout.height((float) 14);
				layout.flexGrow((float) 0);
				layout.flexShrink((float) 0);
				layout.gapAll((float) 2);
				layout.paddingAll((float) 2);
				layout.marginAll((float) 0);
				layout.left((float) 0);
				layout.top((float) 0);
			});
			_n_output_panel.addChild(_n_output_label);
			
			ItemSlot _n_output_slot = new ItemSlot();
			_n_output_slot.setId("output_slot");
			if (itemHandler.getSlots() > 2) {
				_n_output_slot.bind(new SlotItemHandler(itemHandler, 2, 0, 0));
			}
			_n_output_slot.layout(layout -> {
				layout.positionType(TaffyPosition.RELATIVE);
				layout.width((float) 18);
				layout.height((float) 18);
				layout.flexGrow((float) 0);
				layout.flexShrink((float) 0);
				layout.gapAll((float) 4);
				layout.paddingAll((float) 4);
				layout.marginAll((float) 0);
				layout.left((float) 0);
				layout.top((float) 0);
			});
			_n_output_panel.addChild(_n_output_slot);
			content.addChild(_n_output_panel);
		}
		{
			Button _n_purify_button = new Button();
			_n_purify_button.setId("purify_button");
			_n_purify_button.setText(Component.translatable("gui.sweetapocalpysebiome.purify_ld_lib_gui.purify_button"));
			_n_purify_button.layout(layout -> {
				layout.positionType(TaffyPosition.RELATIVE);
				layout.widthPercent((float) 100);
				layout.height((float) 14);
				layout.flexGrow((float) 0);
				layout.flexShrink((float) 0);
				layout.gapAll((float) 4);
				layout.paddingAll((float) 1);
				layout.marginAll((float) 0);
				layout.left((float) 0);
				layout.top((float) 0);
			});
			content.addChild(_n_purify_button);
		}
		{
			InventorySlots inventorySlots = new InventorySlots();
			inventorySlots.setId("player_inventory");
			inventorySlots.layout(layout -> layout.widthPercent(100f));
			content.addChild(inventorySlots);
		}
		window.addChild(content);
		if (freeWindow) {
			root.addChild(window);
			// Center once ModularUI has a screen size (Dialog.windowMode pattern)
			root.addEventListener(UIEvents.LAYOUT_CHANGED, e -> {
				var mui = root.getModularUI();
				if (mui != null) {
					float sw = mui.getScreenWidth();
					float sh = mui.getScreenHeight();
					float ww = window.getSizeWidth() > 1f ? window.getSizeWidth() : _ldWinW;
					float wh = window.getSizeHeight() > 1f ? window.getSizeHeight() : _ldWinH;
					float left = Math.max(0f, (sw - ww) / 2f);
					float top = Math.max(0f, (sh - wh) / 2f);
					window.layout(l -> l.left(left).top(top));
				}
				e.currentElement.removeEventListener(UIEvents.LAYOUT_CHANGED, e.currentListener);
			});
		}
		// All edges/corners (EnumSet.allOf). Requires absolute free-window layout for LEFT/TOP.
		WindowDragHelper.setBorderResize(window, window, _ldResizeBorder, new Vector2f((float) 100, (float) 80), new Vector2f((float) 800, (float) 600), null, null, e -> window.adaptPositionToScreen());
		UI ui = UI.of(root, StylesheetManager.INSTANCE.getStylesheetSafe(StylesheetManager.MC));
		ModularUI modularUI = ModularUI.of(ui, player);
		modularUI.shouldCloseOnEsc(true);
		// Attach procedures from the MCreator tree by element id (works with templates + tree)
		attachTreeProcedures(window, player, x, y, z, world, entity);
		root.addEventListener(UIEvents.REMOVED, e -> LDLibGUIContext.clear(player));
		return modularUI;
	}

	/**
	 * Wire MCreator procedures onto elements by id after the UI tree (or template) is built.
	 * Works for both designer nodes and ids present only in imported NBT templates.
	 */
	private static void attachTreeProcedures(UIElement root, Player player, int x, int y, int z, Level world, Entity entity) {
		// Attach purify button click handler - find button by ID in the tree
		UIElement purifyButton = root.selectId("purify_button").findFirst().orElse(null);
		if (purifyButton instanceof Button button) {
			button.setOnClick(event -> {
				PurifyLDLibPurifyButtonClickProcedure.execute(player);
			});
		}
	}
}