package net.mcreator.sweetapocalpysebiome.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.HashSet;
import java.util.Set;

import net.mcreator.sweetapocalpysebiome.SweetapocalpysebiomeMod;
import net.mcreator.sweetapocalpysebiome.capability.PlayerDnaCapability;
import net.mcreator.sweetapocalpysebiome.system.DnaType;

public record DnaSyncPacket(Set<String> unlockedDna, String newUnlockPath) implements CustomPacketPayload {
	public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(SweetapocalpysebiomeMod.MODID, "dna_sync");

	public DnaSyncPacket(FriendlyByteBuf buffer) {
		this(readDnaSet(buffer), buffer.readUtf());
	}

	public DnaSyncPacket(Set<String> unlockedDna) {
		this(unlockedDna, "");
	}

	private static Set<String> readDnaSet(FriendlyByteBuf buffer) {
		Set<String> set = new HashSet<>();
		int size = buffer.readVarInt();
		for (int i = 0; i < size; i++) {
			set.add(buffer.readUtf());
		}
		return set;
	}

	public void write(FriendlyByteBuf buffer) {
		buffer.writeVarInt(unlockedDna.size());
		for (String path : unlockedDna) {
			buffer.writeUtf(path);
		}
		buffer.writeUtf(newUnlockPath);
	}

	public ResourceLocation id() {
		return ID;
	}

	public CustomPacketPayload.Type<DnaSyncPacket> type() {
		return new CustomPacketPayload.Type<>(ID);
	}

	public static void handle(DnaSyncPacket packet, IPayloadContext context) {
		context.enqueueWork(() -> {
			PlayerDnaCapability cap = context.player().getCapability(PlayerDnaCapability.CAPABILITY);
			if (cap != null) {
				Set<DnaType> dnaSet = new HashSet<>();
				for (String path : packet.unlockedDna()) {
					DnaType dna = DnaType.fromPath(path);
					if (dna != null) {
						dnaSet.add(dna);
					}
				}
				cap.setUnlockedDna(dnaSet);
			}
		});
	}
}
