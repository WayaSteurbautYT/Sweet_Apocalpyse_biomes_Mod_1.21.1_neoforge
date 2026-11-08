package net.mcreator.sweetapocalpysebiome.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import net.mcreator.sweetapocalpysebiome.SweetapocalpysebiomeMod;
import net.mcreator.sweetapocalpysebiome.capability.PlayerSugarCapability;

public record SugarSyncPacket(int currentSugar, int maxSugar) implements CustomPacketPayload {
	public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(SweetapocalpysebiomeMod.MODID, "sugar_sync");

	public SugarSyncPacket(FriendlyByteBuf buffer) {
		this(buffer.readVarInt(), buffer.readVarInt());
	}

	public SugarSyncPacket(int currentSugar) {
		this(currentSugar, 100);
	}

	public void write(FriendlyByteBuf buffer) {
		buffer.writeVarInt(currentSugar);
		buffer.writeVarInt(maxSugar);
	}

	public ResourceLocation id() {
		return ID;
	}

	public CustomPacketPayload.Type<SugarSyncPacket> type() {
		return new CustomPacketPayload.Type<>(ID);
	}

	public static void handle(SugarSyncPacket packet, IPayloadContext context) {
		context.enqueueWork(() -> {
			PlayerSugarCapability cap = context.player().getCapability(PlayerSugarCapability.CAPABILITY);
			if (cap != null) {
				cap.setCurrentSugar(packet.currentSugar());
			}
		});
	}
}
