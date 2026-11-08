package net.mcreator.sweetapocalpysebiome.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import net.mcreator.sweetapocalpysebiome.SweetapocalpysebiomeMod;
import net.mcreator.sweetapocalpysebiome.client.gui.PurifyLDLibGUIUI;
import net.mcreator.sweetapocalpysebiome.client.gui.ldlib.LDLibGUIContext;
import net.mcreator.sweetapocalpysebiome.world.inventory.PurifyGUIMenu;

public record PurifyGUIStatePacket(float progress, String percent, String core, String status, String button, boolean buttonActive) implements CustomPacketPayload {
	public static final Type<PurifyGUIStatePacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(SweetapocalpysebiomeMod.MODID, "purify_gui_state"));
	public static final StreamCodec<RegistryFriendlyByteBuf, PurifyGUIStatePacket> STREAM_CODEC = StreamCodec.of((buffer, message) -> {
		buffer.writeFloat(message.progress);
		buffer.writeUtf(message.percent);
		buffer.writeUtf(message.core);
		buffer.writeUtf(message.status);
		buffer.writeUtf(message.button);
		buffer.writeBoolean(message.buttonActive);
	}, buffer -> new PurifyGUIStatePacket(buffer.readFloat(), buffer.readUtf(), buffer.readUtf(), buffer.readUtf(), buffer.readUtf(), buffer.readBoolean()));

	@Override
	public Type<PurifyGUIStatePacket> type() {
		return TYPE;
	}

	public static void handleData(PurifyGUIStatePacket message, IPayloadContext context) {
		context.enqueueWork(() -> {
			if (context.flow() != PacketFlow.CLIENTBOUND || !(context.player() instanceof Player player)) {
				return;
			}
			if (LDLibGUIContext.isOpen(player, PurifyLDLibGUIUI.UI_ID)) {
				LDLibGUIContext.setProgress(player, "PurifyLDLibGUI/progress_bar", message.progress);
				LDLibGUIContext.setLabelText(player, "PurifyLDLibGUI/progress_percent", message.percent);
				LDLibGUIContext.setLabelText(player, "PurifyLDLibGUI/core_label", message.core);
				LDLibGUIContext.setLabelText(player, "PurifyLDLibGUI/chamber_title", message.status);
				LDLibGUIContext.setButtonText(player, "PurifyLDLibGUI/purify_button", message.button);
				LDLibGUIContext.setElementActive(player, "PurifyLDLibGUI/purify_button", message.buttonActive);
			} else if (player.containerMenu instanceof PurifyGUIMenu menu) {
				menu.menuState.put("purify_state", message);
			}
		}).exceptionally(error -> {
			context.connection().disconnect(Component.literal(error.getMessage()));
			return null;
		});
	}

	public static void send(ServerPlayer player, float progress, String percent, String core, String status, String button, boolean buttonActive) {
		PacketDistributor.sendToPlayer(player, new PurifyGUIStatePacket(progress, percent, core, status, button, buttonActive));
	}
}
