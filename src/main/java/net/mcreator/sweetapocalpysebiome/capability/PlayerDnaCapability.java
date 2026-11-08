package net.mcreator.sweetapocalpysebiome.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.capabilities.ICapabilityProvider;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.minecraft.resources.ResourceLocation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

import net.mcreator.sweetapocalpysebiome.SweetapocalpysebiomeMod;
import net.mcreator.sweetapocalpysebiome.system.DnaType;

public class PlayerDnaCapability {
	private final Set<DnaType> unlockedDna = new HashSet<>();

	public boolean hasDna(DnaType dnaType) {
		return unlockedDna.contains(dnaType);
	}

	public void unlockDna(DnaType dnaType) {
		if (dnaType != null && !unlockedDna.contains(dnaType)) {
			unlockedDna.add(dnaType);
		}
	}

	public Set<DnaType> getUnlockedDna() {
		return new HashSet<>(unlockedDna);
	}

	public void setUnlockedDna(Set<DnaType> dnaSet) {
		unlockedDna.clear();
		unlockedDna.addAll(dnaSet);
	}

	public CompoundTag serializeNBT() {
		CompoundTag tag = new CompoundTag();
		ListTag dnaList = new ListTag();
		for (DnaType dna : unlockedDna) {
			dnaList.add(StringTag.valueOf(dna.path()));
		}
		tag.put("unlocked_dna", dnaList);
		return tag;
	}

	public void deserializeNBT(CompoundTag tag) {
		unlockedDna.clear();
		ListTag dnaList = tag.getList("unlocked_dna", 8);
		for (int i = 0; i < dnaList.size(); i++) {
			String path = dnaList.getString(i);
			DnaType dna = DnaType.fromPath(path);
			if (dna != null) {
				unlockedDna.add(dna);
			}
		}
	}

	public static final EntityCapability<PlayerDnaCapability, Void> CAPABILITY = 
		EntityCapability.createVoid(
			ResourceLocation.fromNamespaceAndPath(SweetapocalpysebiomeMod.MODID, "player_dna"),
			PlayerDnaCapability.class
		);

	public static void register(RegisterCapabilitiesEvent event) {
		event.registerEntity(CAPABILITY, EntityType.PLAYER, (player, context) -> {
			return new PlayerDnaCapability();
		});
	}

	@EventBusSubscriber
	public static class EventHandler {
		@SubscribeEvent
		public static void onPlayerClone(PlayerEvent.Clone event) {
			if (!event.isWasDeath() && event.getOriginal() instanceof Player && event.getEntity() instanceof Player) {
				Player original = (Player) event.getOriginal();
				Player player = (Player) event.getEntity();
				PlayerDnaCapability originalCap = original.getCapability(CAPABILITY);
				PlayerDnaCapability newCap = player.getCapability(CAPABILITY);
				if (originalCap != null && newCap != null) {
					newCap.setUnlockedDna(originalCap.getUnlockedDna());
				}
			}
		}
	}
}
