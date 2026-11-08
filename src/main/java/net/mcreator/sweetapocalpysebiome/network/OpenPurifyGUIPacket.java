package net.mcreator.sweetapocalpysebiome.network;

import io.netty.buffer.Unpooled;

import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;

import net.mcreator.sweetapocalpysebiome.SweetapocalpysebiomeMod;
import net.mcreator.sweetapocalpysebiome.world.inventory.PurifyGUIMenu;

@EventBusSubscriber
public class OpenPurifyGUIPacket implements CustomPacketPayload {
	public static final Type<OpenPurifyGUIPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(SweetapocalpysebiomeMod.MODID, "open_purify_gui"));
	public static final StreamCodec<RegistryFriendlyByteBuf, OpenPurifyGUIPacket> STREAM_CODEC = StreamCodec.of((buffer, message) -> {
	}, buffer -> new OpenPurifyGUIPacket());

	@Override
	public Type<OpenPurifyGUIPacket> type() {
		return TYPE;
	}

	public static void handleData(final OpenPurifyGUIPacket message, final IPayloadContext context) {
		context.enqueueWork(() -> {
			if (context.flow() == PacketFlow.SERVERBOUND && context.player() instanceof ServerPlayer player) {
				// Keybind GUI: deliberately not bound to a block, so it stays valid anywhere.
				open(player, null);
			}
		}).exceptionally(e -> {
			context.connection().disconnect(Component.literal(e.getMessage()));
			return null;
		});
	}

	public static void open(ServerPlayer player, BlockPos pos) {
		if (player == null) {
			return;
		}
		player.openMenu(new SimpleMenuProvider((id, inventory, p) -> new PurifyGUIMenu(id, inventory, pos), Component.literal("Purify")), buf -> {
			// The buffer has to mirror what PurifyGUIMenu(int, Inventory, FriendlyByteBuf) reads.
			buf.writeBoolean(pos != null);
			if (pos != null) {
				buf.writeBlockPos(pos);
			}
		});
	}

	public static void send() {
		PacketDistributor.sendToServer(new OpenPurifyGUIPacket());
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		SweetapocalpysebiomeMod.addNetworkMessage(TYPE, STREAM_CODEC, OpenPurifyGUIPacket::handleData);
	}
}
