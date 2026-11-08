package net.mcreator.sweetapocalpysebiome.client.gui.ldlib;

import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;
import java.util.Map;
import java.util.Locale;
import java.util.List;
import java.util.ArrayList;

import com.lowdragmc.lowdraglib2.gui.ui.elements.*;
import com.lowdragmc.lowdraglib2.gui.ui.UIElement;
import com.lowdragmc.lowdraglib2.gui.ui.ModularUI;
import com.lowdragmc.lowdraglib2.gui.holder.ModularUIContainerMenu;

/**
 * Open context + live ModularUI lookup for procedure blocks.
 * Stores block position and/or held-item hand so GUIs can bind inventories/fluids.
 * <p>
 * Element ids from Blockly may be scoped as {@code GUIName/elementId}; helpers resolve that.
 */
public final class LDLibGUIContext {
	public enum OpenSource {
		PLAYER, BLOCK, ITEM
	}

	public static final class OpenContext {
		public final int x, y, z;
		public final ResourceLocation uiId;
		@Nullable
		public final BlockPos blockPos;
		/** When non-null, GUI was opened from a held item in this hand. */
		@Nullable
		public final InteractionHand hand;
		public final OpenSource source;

		public OpenContext(int x, int y, int z, ResourceLocation uiId, @Nullable BlockPos blockPos, @Nullable InteractionHand hand, OpenSource source) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.uiId = uiId;
			this.blockPos = blockPos != null ? blockPos : new BlockPos(x, y, z);
			this.hand = hand;
			this.source = source == null ? OpenSource.PLAYER : source;
		}
	}

	private static final Map<UUID, OpenContext> OPEN = new ConcurrentHashMap<>();

	private LDLibGUIContext() {
	}

	/**
	 * Resolves Blockly element id pickers that may be scoped as {@code GUIName/id}.
	 * Bare ids (no slash) are returned as-is.
	 */
	public static String resolveElementId(@Nullable String id) {
		if (id == null)
			return "";
		id = id.trim();
		if (id.isEmpty())
			return "";
		// Strip accidental surrounding quotes from older templates
		if (id.length() >= 2 && id.charAt(0) == '"' && id.charAt(id.length() - 1) == '"')
			id = id.substring(1, id.length() - 1);
		int slash = id.indexOf('/');
		// Only treat as scope if it looks like "Name/id" (not a resource path with many slashes)
		if (slash > 0 && slash < id.length() - 1 && id.indexOf('/', slash + 1) < 0)
			return id.substring(slash + 1);
		return id;
	}

	public static void set(Player player, int x, int y, int z, ResourceLocation uiId) {
		OPEN.put(player.getUUID(), new OpenContext(x, y, z, uiId, new BlockPos(x, y, z), null, OpenSource.BLOCK));
	}

	/** Open context for a held-item procedure (PlayerUI + item capability bind). */
	public static void setFromItem(Player player, InteractionHand hand, ResourceLocation uiId) {
		int x = (int) player.getX();
		int y = (int) player.getY();
		int z = (int) player.getZ();
		OPEN.put(player.getUUID(), new OpenContext(x, y, z, uiId, null, hand == null ? InteractionHand.MAIN_HAND : hand, OpenSource.ITEM));
	}

	public static void setOpenUI(Player player, ResourceLocation id) {
		OpenContext prev = OPEN.get(player.getUUID());
		if (prev != null) {
			OPEN.put(player.getUUID(), new OpenContext(prev.x, prev.y, prev.z, id, prev.blockPos, prev.hand, prev.source));
		} else {
			OPEN.put(player.getUUID(), new OpenContext((int) player.getX(), (int) player.getY(), (int) player.getZ(), id, null, null, OpenSource.PLAYER));
		}
	}

	public static int[] get(Player player) {
		OpenContext ctx = OPEN.get(player.getUUID());
		if (ctx == null)
			return new int[]{(int) player.getX(), (int) player.getY(), (int) player.getZ()};
		return new int[]{ctx.x, ctx.y, ctx.z};
	}

	@Nullable
	public static OpenContext getContext(Player player) {
		return OPEN.get(player.getUUID());
	}

	@Nullable
	public static ResourceLocation getOpenUiId(Player player) {
		OpenContext ctx = OPEN.get(player.getUUID());
		return ctx == null ? null : ctx.uiId;
	}

	public static boolean isOpen(Player player, ResourceLocation id) {
		if (!(player.containerMenu instanceof ModularUIContainerMenu))
			return false;
		ResourceLocation open = getOpenUiId(player);
		return open != null && open.equals(id);
	}

	public static boolean isAnyOpen(Player player) {
		return player.containerMenu instanceof ModularUIContainerMenu && OPEN.containsKey(player.getUUID());
	}

	@Nullable
	public static ModularUI getModularUI(Player player) {
		if (player.containerMenu instanceof ModularUIContainerMenu menu) {
			return menu.modularUI;
		}
		return null;
	}

	@Nullable
	public static UIElement getElement(Player player, String id) {
		ModularUI mui = getModularUI(player);
		if (mui == null || id == null || id.isBlank())
			return null;
		String resolved = resolveElementId(id);
		if (resolved.isBlank())
			return null;
		UIElement el = mui.getElementById(resolved);
		if (el != null)
			return el;
		// Fallback: CSS-like select from root (ModularUI has select, UIElement has selectId)
		try {
			if (mui.ui != null && mui.ui.rootElement != null)
				return mui.ui.rootElement.selectId(resolved).findFirst().orElse(null);
		} catch (Throwable ignored) {
		}
		return mui.getElementsById(resolved).stream().findFirst().orElse(null);
	}

	public static boolean hasElement(Player player, String id) {
		return getElement(player, id) != null;
	}

	@Nullable
	public static BlockEntity getBlockEntity(Player player) {
		OpenContext ctx = getContext(player);
		if (ctx == null || ctx.source == OpenSource.ITEM)
			return null;
		Level level = player.level();
		return level.getBlockEntity(ctx.blockPos);
	}

	/** Live held stack for item-open context, or empty. */
	public static ItemStack getHeldItem(Player player) {
		OpenContext ctx = getContext(player);
		if (ctx == null || ctx.hand == null)
			return ItemStack.EMPTY;
		return player.getItemInHand(ctx.hand);
	}

	@Nullable
	public static IItemHandler getBlockItemHandler(Player player) {
		OpenContext ctx = getContext(player);
		if (ctx == null || ctx.source == OpenSource.ITEM)
			return null;
		Level level = player.level();
		IItemHandler handler = level.getCapability(Capabilities.ItemHandler.BLOCK, ctx.blockPos, null);
		if (handler != null)
			return handler;
		for (Direction dir : Direction.values()) {
			handler = level.getCapability(Capabilities.ItemHandler.BLOCK, ctx.blockPos, dir);
			if (handler != null)
				return handler;
		}
		return null;
	}

	@Nullable
	public static IFluidHandler getBlockFluidHandler(Player player) {
		OpenContext ctx = getContext(player);
		if (ctx == null || ctx.source == OpenSource.ITEM)
			return null;
		Level level = player.level();
		IFluidHandler handler = level.getCapability(Capabilities.FluidHandler.BLOCK, ctx.blockPos, null);
		if (handler != null)
			return handler;
		for (Direction dir : Direction.values()) {
			handler = level.getCapability(Capabilities.FluidHandler.BLOCK, ctx.blockPos, dir);
			if (handler != null)
				return handler;
		}
		return null;
	}

	/** Prefer block item handler, else held-item capability. */
	@Nullable
	public static IItemHandler getBoundItemHandler(Player player) {
		IItemHandler block = getBlockItemHandler(player);
		if (block != null)
			return block;
		ItemStack held = getHeldItem(player);
		if (!held.isEmpty()) {
			try {
				return held.getCapability(Capabilities.ItemHandler.ITEM);
			} catch (Throwable ignored) {
			}
		}
		return null;
	}

	/** Prefer block fluid handler, else held-item capability. */
	@Nullable
	public static IFluidHandler getBoundFluidHandler(Player player) {
		IFluidHandler block = getBlockFluidHandler(player);
		if (block != null)
			return block;
		ItemStack held = getHeldItem(player);
		if (!held.isEmpty()) {
			try {
				return held.getCapability(Capabilities.FluidHandler.ITEM);
			} catch (Throwable ignored) {
			}
		}
		return null;
	}

	// ---- widget helpers used by procedure blocks ----
	private static void warnMissing(Player player, String id, String action) {
		// Soft failure for no-code: never crash procedures; log at debug for pack makers
		if (getModularUI(player) == null) {
			// GUI not open — common when procedure runs without Open LDLib GUI first
			return;
		}
		if (id != null && getElement(player, id) == null) {
			// Unknown element id (typo) — silent in production
		}
	}

	public static void setLabelText(Player player, String id, String text) {
		warnMissing(player, id, "setLabelText");
		UIElement el = getElement(player, id);
		if (el instanceof Label label)
			label.setText(Component.literal(text == null ? "" : text));
	}

	public static String getLabelText(Player player, String id) {
		UIElement el = getElement(player, id);
		if (el instanceof Label label && label.getText() != null)
			return label.getText().getString();
		return "";
	}

	public static void setButtonText(Player player, String id, String text) {
		UIElement el = getElement(player, id);
		if (el instanceof Button button)
			button.setText(Component.literal(text == null ? "" : text));
	}

	public static void setProgress(Player player, String id, double value) {
		UIElement el = getElement(player, id);
		if (el instanceof ProgressBar bar)
			bar.setProgress((float) value);
	}

	public static double getProgress(Player player, String id) {
		UIElement el = getElement(player, id);
		if (el instanceof ProgressBar bar)
			return bar.getNormalizedValue();
		return 0;
	}

	public static void setSliderValue(Player player, String id, double value) {
		UIElement el = getElement(player, id);
		if (el instanceof Scroller scroller)
			scroller.setValue((float) value);
	}

	public static double getSliderValue(Player player, String id) {
		UIElement el = getElement(player, id);
		if (el instanceof Scroller scroller)
			return scroller.getValue();
		return 0;
	}

	public static void setToggle(Player player, String id, boolean on) {
		UIElement el = getElement(player, id);
		if (el instanceof Toggle toggle)
			toggle.setOn(on);
		else if (el instanceof Switch sw)
			sw.setOn(on);
	}

	public static boolean getToggle(Player player, String id) {
		UIElement el = getElement(player, id);
		if (el instanceof Toggle toggle)
			return Boolean.TRUE.equals(toggle.getValue());
		if (el instanceof Switch sw)
			return Boolean.TRUE.equals(sw.getValue());
		return false;
	}

	public static void setTextField(Player player, String id, String text) {
		UIElement el = getElement(player, id);
		if (el instanceof TextField field)
			field.setText(text == null ? "" : text);
	}

	public static String getTextField(Player player, String id) {
		UIElement el = getElement(player, id);
		if (el instanceof TextField field) {
			String v = field.getValue();
			return v == null ? "" : v;
		}
		return "";
	}

	/** Number field (numeric TextField): set value clamped to field range if possible. */
	public static void setNumberField(Player player, String id, double value) {
		UIElement el = getElement(player, id);
		if (el instanceof TextField field) {
			// Prefer plain integer text when whole number for cleaner display
			if (Math.abs(value - Math.rint(value)) < 1e-9)
				field.setText(String.valueOf((long) Math.rint(value)));
			else
				field.setText(String.format(Locale.ROOT, "%s", value));
		}
	}

	public static double getNumberField(Player player, String id) {
		UIElement el = getElement(player, id);
		if (el instanceof TextField field) {
			String v = field.getValue();
			if (v == null || v.isBlank())
				return 0;
			try {
				return Double.parseDouble(v.trim());
			} catch (NumberFormatException ignored) {
				return 0;
			}
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	public static void setSelector(Player player, String id, String value) {
		UIElement el = getElement(player, id);
		if (el instanceof Selector selector) {
			if (value == null)
				value = "";
			selector.setSelected(value, true);
		}
	}

	@SuppressWarnings("unchecked")
	public static String getSelector(Player player, String id) {
		UIElement el = getElement(player, id);
		if (el instanceof Selector selector) {
			Object v = selector.getValue();
			return v == null ? "" : String.valueOf(v);
		}
		return "";
	}

	/** Replace selector candidates from a comma/newline-separated string. */
	@SuppressWarnings("unchecked")
	public static void setSelectorOptions(Player player, String id, String optionsCsv) {
		UIElement el = getElement(player, id);
		if (!(el instanceof Selector selector))
			return;
		List<String> opts = parseOptions(optionsCsv);
		if (opts.isEmpty())
			opts = List.of("Option A");
		selector.setCandidates(opts);
		Object cur = selector.getValue();
		String curS = cur == null ? "" : String.valueOf(cur);
		if (!opts.contains(curS))
			selector.setSelected(opts.get(0), false);
	}

	private static List<String> parseOptions(@Nullable String raw) {
		List<String> out = new ArrayList<>();
		if (raw == null || raw.isBlank())
			return out;
		for (String line : raw.replace('\r', '\n').split("\n")) {
			for (String part : line.split(",")) {
				String t = part.trim();
				if (!t.isEmpty())
					out.add(t);
			}
		}
		return out;
	}

	// ---- data-binding helpers (machine UIs) ----
	/**
	 * Sets a progress bar from current/max (0–1). Safe when max ≤ 0.
	 */
	public static void setProgressRatio(Player player, String id, double current, double max) {
		double ratio = max <= 0 ? 0 : Math.max(0, Math.min(1, current / max));
		setProgress(player, id, ratio);
	}

	/** Format a number onto a label (decimals ≥ 0). */
	public static void setLabelNumber(Player player, String id, double number, double decimals) {
		int d = (int) Math.max(0, Math.min(8, Math.floor(decimals)));
		String text;
		if (d <= 0 && Math.abs(number - Math.rint(number)) < 1e-9)
			text = String.valueOf((long) Math.rint(number));
		else
			text = String.format(Locale.ROOT, "%." + d + "f", number);
		setLabelText(player, id, text);
	}

	/** Sets label to {@code current / max} (common energy/fluid style). */
	public static void setLabelPair(Player player, String id, double current, double max) {
		setLabelText(player, id, formatCompact(current) + " / " + formatCompact(max));
	}

	/**
	 * Sets label to {@code current / max} and progress bar from the same ratio.
	 * Ideal one-block machine tick sync for energy/cook/fluid UIs.
	 */
	public static void bindProgressAndLabel(Player player, String progressId, String labelId, double current, double max) {
		setProgressRatio(player, progressId, current, max);
		setLabelPair(player, labelId, current, max);
	}

	private static String formatCompact(double v) {
		if (Math.abs(v - Math.rint(v)) < 1e-9)
			return String.valueOf((long) Math.rint(v));
		return String.format(Locale.ROOT, "%.1f", v);
	}

	public static void setElementVisible(Player player, String id, boolean visible) {
		UIElement el = getElement(player, id);
		if (el != null)
			el.setDisplay(visible);
	}

	public static boolean isElementVisible(Player player, String id) {
		UIElement el = getElement(player, id);
		return el != null && el.isDisplayed() && el.isVisible();
	}

	public static void setElementActive(Player player, String id, boolean active) {
		UIElement el = getElement(player, id);
		if (el != null)
			el.setActive(active);
	}

	public static void setFluid(Player player, String id, FluidStack stack) {
		UIElement el = getElement(player, id);
		if (el instanceof FluidSlot slot)
			slot.setFluid(stack == null ? FluidStack.EMPTY : stack);
	}

	public static FluidStack getFluid(Player player, String id) {
		UIElement el = getElement(player, id);
		if (el instanceof FluidSlot slot) {
			FluidStack v = slot.getValue();
			return v == null ? FluidStack.EMPTY : v;
		}
		return FluidStack.EMPTY;
	}

	public static void setItem(Player player, String id, ItemStack stack) {
		UIElement el = getElement(player, id);
		if (el instanceof ItemSlot slot)
			slot.setItem(stack == null ? ItemStack.EMPTY : stack.copy());
	}

	public static ItemStack getItem(Player player, String id) {
		UIElement el = getElement(player, id);
		if (el instanceof ItemSlot slot) {
			ItemStack v = slot.getValue();
			return v == null ? ItemStack.EMPTY : v.copy();
		}
		return ItemStack.EMPTY;
	}

	/** Select a TabView tab by the tab element's id. */
	public static void selectTab(Player player, String tabViewId, String tabId) {
		UIElement viewEl = getElement(player, tabViewId);
		UIElement tabEl = getElement(player, tabId);
		if (viewEl instanceof TabView view && tabEl instanceof Tab tab)
			view.selectTab(tab);
	}

	public static void clear(Player player) {
		OPEN.remove(player.getUUID());
	}
}