package net.mcreator.sweetapocalpysebiome.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.sweetapocalpysebiome.SweetapocalpysebiomeMod;

public class PlayerSugarCapability {
	private static final int MAX_SUGAR = 100;
	private static final int DEFAULT_SUGAR = 50;
	
	private int currentSugar = DEFAULT_SUGAR;

	public int getCurrentSugar() {
		return currentSugar;
	}

	public void setCurrentSugar(int sugar) {
		this.currentSugar = Math.max(0, Math.min(sugar, MAX_SUGAR));
	}

	public int getMaxSugar() {
		return MAX_SUGAR;
	}

	public void addSugar(int amount) {
		setCurrentSugar(currentSugar + amount);
	}

	public void removeSugar(int amount) {
		setCurrentSugar(currentSugar - amount);
	}

	public float getSugarRatio() {
		return (float) currentSugar / MAX_SUGAR;
	}

	public boolean isSugarRush() {
		return currentSugar >= 80;
	}

	public boolean isHypoglycemia() {
		return currentSugar <= 20;
	}

	public CompoundTag serializeNBT() {
		CompoundTag tag = new CompoundTag();
		tag.putInt("current_sugar", currentSugar);
		return tag;
	}

	public void deserializeNBT(CompoundTag tag) {
		currentSugar = tag.getInt("current_sugar");
		if (currentSugar < 0 || currentSugar > MAX_SUGAR) {
			currentSugar = DEFAULT_SUGAR;
		}
	}

	public static final EntityCapability<PlayerSugarCapability, Void> CAPABILITY = 
		EntityCapability.createVoid(
			ResourceLocation.fromNamespaceAndPath(SweetapocalpysebiomeMod.MODID, "player_sugar"),
			PlayerSugarCapability.class
		);

	public static void register(RegisterCapabilitiesEvent event) {
		event.registerEntity(CAPABILITY, EntityType.PLAYER, (player, context) -> {
			return new PlayerSugarCapability();
		});
	}

	@EventBusSubscriber
	public static class EventHandler {
		@SubscribeEvent
		public static void onPlayerClone(PlayerEvent.Clone event) {
			if (!event.isWasDeath() && event.getOriginal() instanceof Player && event.getEntity() instanceof Player) {
				Player original = (Player) event.getOriginal();
				Player player = (Player) event.getEntity();
				PlayerSugarCapability originalCap = original.getCapability(CAPABILITY);
				PlayerSugarCapability newCap = player.getCapability(CAPABILITY);
				if (originalCap != null && newCap != null) {
					newCap.setCurrentSugar(originalCap.getCurrentSugar());
				}
			}
		}
	}
}
