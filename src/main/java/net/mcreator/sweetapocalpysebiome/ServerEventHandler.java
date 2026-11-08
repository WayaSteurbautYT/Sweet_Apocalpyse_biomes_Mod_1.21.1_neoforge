package net.mcreator.sweetapocalpysebiome;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import net.mcreator.sweetapocalpysebiome.capability.PlayerDnaCapability;
import net.mcreator.sweetapocalpysebiome.capability.PlayerSugarCapability;
import net.mcreator.sweetapocalpysebiome.network.DnaSyncPacket;
import net.mcreator.sweetapocalpysebiome.network.SugarSyncPacket;

@EventBusSubscriber
public class ServerEventHandler {

	@SubscribeEvent
	public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
		if (event.getEntity() instanceof ServerPlayer player) {
			PlayerDnaCapability dnaCap = player.getCapability(PlayerDnaCapability.CAPABILITY);
			if (dnaCap != null) {
				java.util.Set<String> dnaPaths = new java.util.HashSet<>();
				for (var dna : dnaCap.getUnlockedDna()) {
					dnaPaths.add(dna.path());
				}
				DnaSyncPacket packet = new DnaSyncPacket(dnaPaths);
				player.connection.send(packet);
			}

			PlayerSugarCapability sugarCap = player.getCapability(PlayerSugarCapability.CAPABILITY);
			if (sugarCap != null) {
				SugarSyncPacket packet = new SugarSyncPacket(sugarCap.getCurrentSugar(), sugarCap.getMaxSugar());
				player.connection.send(packet);
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerClone(PlayerEvent.Clone event) {
		if (!event.isWasDeath()) {
			if (event.getOriginal() instanceof ServerPlayer original && event.getEntity() instanceof ServerPlayer player) {
				PlayerDnaCapability originalCap = original.getCapability(PlayerDnaCapability.CAPABILITY);
				PlayerDnaCapability newCap = player.getCapability(PlayerDnaCapability.CAPABILITY);
				if (originalCap != null && newCap != null) {
					newCap.setUnlockedDna(originalCap.getUnlockedDna());
				}

				PlayerSugarCapability originalSugar = original.getCapability(PlayerSugarCapability.CAPABILITY);
				PlayerSugarCapability newSugar = player.getCapability(PlayerSugarCapability.CAPABILITY);
				if (originalSugar != null && newSugar != null) {
					newSugar.setCurrentSugar(originalSugar.getCurrentSugar());
				}
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent.Post event) {
		if (event.getEntity() instanceof ServerPlayer player) {
			PlayerSugarCapability cap = player.getCapability(PlayerSugarCapability.CAPABILITY);
			if (cap != null) {
				if (cap.isSugarRush()) {
					player.addEffect(new net.minecraft.world.effect.MobEffectInstance(
						net.minecraft.world.effect.MobEffects.MOVEMENT_SPEED, 20, 0, false, false
					));
					player.addEffect(new net.minecraft.world.effect.MobEffectInstance(
						net.minecraft.world.effect.MobEffects.REGENERATION, 20, 0, false, false
					));
				} else if (cap.isHypoglycemia()) {
					player.addEffect(new net.minecraft.world.effect.MobEffectInstance(
						net.minecraft.world.effect.MobEffects.WEAKNESS, 20, 0, false, false
					));
					player.addEffect(new net.minecraft.world.effect.MobEffectInstance(
						net.minecraft.world.effect.MobEffects.MOVEMENT_SLOWDOWN, 20, 0, false, false
					));
				}
			}
		}
	}
}
