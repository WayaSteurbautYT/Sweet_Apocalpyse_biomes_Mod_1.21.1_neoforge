package net.mcreator.sweetapocalpysebiome.client.gui.ldlib;

import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.NbtIo;
import net.minecraft.nbt.NbtAccounter;
import net.minecraft.nbt.CompoundTag;

import javax.annotation.Nullable;

import java.util.Optional;

import java.nio.file.Files;

import java.io.InputStream;
import java.io.File;
import java.io.DataInputStream;

import com.mojang.serialization.DataResult;

import com.lowdragmc.lowdraglib2.gui.ui.UITemplate;
import com.lowdragmc.lowdraglib2.gui.ui.UI;
import com.lowdragmc.lowdraglib2.editor.resource.UIResource;
import com.lowdragmc.lowdraglib2.editor.resource.FilePath;
import com.lowdragmc.lowdraglib2.Platform;
import com.lowdragmc.lowdraglib2.LDLib2;

/**
 * Loads LDLib2 UI templates from resource packs, editor UIResource providers, and loose NBT files.
 */
public final class LDLibUITemplateLoader {
	private LDLibUITemplateLoader() {
	}

	/**
	 * @param path resource location string, e.g. {@code mymod:resources/ldlib_ui/panel.ui.nbt}
	 *             or a filesystem path to a {@code .ui.nbt} file
	 */
	@Nullable
	public static UI loadUI(@Nullable String path) {
		UITemplate template = loadTemplate(path);
		return template == null ? null : template.createUI();
	}

	@Nullable
	public static UITemplate loadTemplate(@Nullable String path) {
		if (path == null || path.isBlank())
			return null;
		path = path.trim().replace('\\', '/');
		// 1) UIResource provider (editor / pack providers)
		UITemplate fromResource = tryUiResource(path);
		if (fromResource != null && fromResource != UITemplate.missing())
			return fromResource;
		// 2) Minecraft resource manager (assets in mod jar / resource packs)
		UITemplate fromPack = tryResourceManager(path);
		if (fromPack != null)
			return fromPack;
		// 3) Absolute / relative file on disk (dev / editor export)
		UITemplate fromFile = tryFile(path);
		if (fromFile != null)
			return fromFile;
		// 4) LDLib assets dir convention: .minecraft/ldlib2/assets/...
		UITemplate fromLdlibAssets = tryLdlibAssets(path);
		if (fromLdlibAssets != null)
			return fromLdlibAssets;
		LDLib2.LOGGER.warn("[LDLibGUI] Failed to load UI template: {}", path);
		return null;
	}

	@Nullable
	private static UITemplate tryUiResource(String path) {
		try {
			ResourceLocation loc = tryParseRl(path);
			if (loc != null) {
				UITemplate t = UIResource.INSTANCE.getResourceInstance().getResource(new FilePath(loc));
				if (t != null)
					return t;
				// Explicit assets/ path string used by some providers
				t = UIResource.INSTANCE.getResourceInstance().getResource(new FilePath("assets/" + loc.getNamespace() + "/" + loc.getPath()));
				if (t != null)
					return t;
			}
			return UIResource.INSTANCE.getResourceInstance().getResource(new FilePath(path));
		} catch (Exception ex) {
			LDLib2.LOGGER.debug("[LDLibGUI] UIResource load failed for {}: {}", path, ex.toString());
			return null;
		}
	}

	@Nullable
	private static UITemplate tryResourceManager(String path) {
		try {
			ResourceLocation loc = tryParseRl(path);
			if (loc == null)
				return null;
			ResourceManager rm = getClientResourceManager();
			if (rm == null)
				return null;
			Optional<Resource> res = rm.getResource(loc);
			if (res.isEmpty()) {
				// try without forcing path changes
				return null;
			}
			try (InputStream in = res.get().open(); DataInputStream din = new DataInputStream(in)) {
				CompoundTag tag = NbtIo.read(din, NbtAccounter.unlimitedHeap());
				return parseTemplateTag(tag);
			}
		} catch (Exception ex) {
			LDLib2.LOGGER.debug("[LDLibGUI] ResourceManager load failed for {}: {}", path, ex.toString());
			return null;
		}
	}

	@Nullable
	private static UITemplate tryFile(String path) {
		try {
			File file = new File(path);
			if (!file.isFile()) {
				// treat RL-like path as assets-relative under run dir
				ResourceLocation loc = tryParseRl(path);
				if (loc != null) {
					file = new File("src/main/resources/assets/" + loc.getNamespace() + "/" + loc.getPath());
					if (!file.isFile())
						file = new File("assets/" + loc.getNamespace() + "/" + loc.getPath());
				}
			}
			if (!file.isFile())
				return null;
			return readNbtFile(file);
		} catch (Exception ex) {
			LDLib2.LOGGER.debug("[LDLibGUI] File load failed for {}: {}", path, ex.toString());
			return null;
		}
	}

	@Nullable
	private static UITemplate tryLdlibAssets(String path) {
		try {
			File assets = LDLib2.getAssetsDir();
			if (assets == null)
				return null;
			ResourceLocation loc = tryParseRl(path);
			File file;
			if (loc != null) {
				file = new File(assets, loc.getNamespace() + "/" + loc.getPath());
				if (!file.isFile())
					file = new File(assets, "assets/" + loc.getNamespace() + "/" + loc.getPath());
			} else {
				file = new File(assets, path);
			}
			if (!file.isFile())
				return null;
			return readNbtFile(file);
		} catch (Exception ex) {
			return null;
		}
	}

	/** Read gzip or raw compound NBT from disk (NeoForge 1.21.1 NbtIo requires NbtAccounter). */
	@Nullable
	private static UITemplate readNbtFile(File file) {
		try {
			return parseTemplateTag(NbtIo.readCompressed(file.toPath(), NbtAccounter.unlimitedHeap()));
		} catch (Exception compressedFail) {
			try (InputStream in = Files.newInputStream(file.toPath()); DataInputStream din = new DataInputStream(in)) {
				return parseTemplateTag(NbtIo.read(din, NbtAccounter.unlimitedHeap()));
			} catch (Exception rawFail) {
				return null;
			}
		}
	}

	/**
	 * Accepts multiple on-disk NBT layouts used by LDLib2 / editor exports.
	 */
	@Nullable
	public static UITemplate parseTemplateTag(@Nullable CompoundTag tag) {
		if (tag == null || tag.isEmpty())
			return null;
		// Pack / ResourceProvider envelope: { type:"ui", data:<codec or template> }
		if (tag.contains("type") && "ui".equals(tag.getString("type")) && tag.contains("data")) {
			Tag data = tag.get("data");
			UITemplate parsed = parseCodec(data);
			if (parsed != null)
				return parsed;
			if (data instanceof CompoundTag dataTag) {
				// data itself may still be an envelope or raw element
				UITemplate nested = parseTemplateTag(dataTag);
				if (nested != null)
					return nested;
			}
		}
		// UITemplate codec root: { template: CompoundTag, builtin_styles?, stylesheets? }
		if (tag.contains("template")) {
			UITemplate parsed = parseCodec(tag);
			if (parsed != null)
				return parsed;
		}
		// Raw UIElement NBT (has layout/id/children style fields) — wrap
		if (looksLikeElementNbt(tag)) {
			return UITemplate.of(tag, "");
		}
		// Last resort: try codec anyway
		return parseCodec(tag);
	}

	@Nullable
	private static UITemplate parseCodec(Tag tag) {
		try {
			DataResult<UITemplate> result = UITemplate.CODEC.parse(Platform.getFrozenRegistry().createSerializationContext(NbtOps.INSTANCE), tag);
			return result.result().orElse(null);
		} catch (Exception ex) {
			return null;
		}
	}

	private static boolean looksLikeElementNbt(CompoundTag tag) {
		// UIElement persistence typically includes id / children / layout-ish keys
		return tag.contains("id") || tag.contains("children") || tag.contains("class") || tag.contains("Elements") || tag.contains("elements");
	}

	@Nullable
	private static ResourceLocation tryParseRl(String path) {
		try {
			if (path.contains(":"))
				return ResourceLocation.parse(path);
		} catch (Exception ignored) {
		}
		return null;
	}

	@Nullable
	private static ResourceManager getClientResourceManager() {
		try {
			if (net.minecraft.client.Minecraft.getInstance() != null)
				return net.minecraft.client.Minecraft.getInstance().getResourceManager();
		} catch (Throwable ignored) {
		}
		return null;
	}

	/** Collect element ids from a loaded UI tree (for debugging / procedures). */
	public static java.util.List<String> listElementIds(UI ui) {
		java.util.ArrayList<String> ids = new java.util.ArrayList<>();
		if (ui == null || ui.rootElement == null)
			return ids;
		ui.rootElement.selfAndAllChildren().forEach(el -> {
			String id = el.getId();
			if (id != null && !id.isBlank())
				ids.add(id);
		});
		return ids;
	}
}