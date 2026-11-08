package net.mcreator.sweetapocalpysebiome.network;

import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.PacketDistributor;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;

import net.mcreator.sweetapocalpysebiome.SweetapocalpysebiomeMod;
import net.mcreator.sweetapocalpysebiome.FoodCorruptionHelper;
import net.mcreator.sweetapocalpysebiome.init.SweetapocalpysebiomeModItems;
import net.mcreator.sweetapocalpysebiome.init.SweetapocalpysebiomeModPotions;

public class PurifyFoodPacket implements CustomPacketPayload {
	public static final Type<PurifyFoodPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(SweetapocalpysebiomeMod.MODID, "purify_food"));
	public static final StreamCodec<RegistryFriendlyByteBuf, PurifyFoodPacket> STREAM_CODEC = StreamCodec.of(
		(RegistryFriendlyByteBuf buffer, PurifyFoodPacket message) -> {
			// No data to write
		},
		(RegistryFriendlyByteBuf buffer) -> new PurifyFoodPacket()
	);

	public PurifyFoodPacket() {
	}

	@Override
	public Type<PurifyFoodPacket> type() {
		return TYPE;
	}

	public static void handleData(final PurifyFoodPacket message, final IPayloadContext context) {
		context.enqueueWork(() -> {
			if (context.flow() == net.minecraft.network.protocol.PacketFlow.SERVERBOUND && context.player() instanceof ServerPlayer player) {
				// Handle food purifying logic server-side
				ItemStack purifyPotionStack = player.getMainHandItem();
				boolean hasPotion = false;
				
				// Check if player has purify potion in main hand
				if (purifyPotionStack.is(Items.POTION)) {
					PotionContents contents = purifyPotionStack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
					if (contents.potion().isPresent() && contents.potion().get().is(SweetapocalpysebiomeModPotions.PURIFY_POTION)) {
						hasPotion = true;
					}
				}
				
				// Check offhand if not in main hand
				if (!hasPotion) {
					ItemStack offhandStack = player.getOffhandItem();
					if (offhandStack.is(Items.POTION)) {
						PotionContents contents = offhandStack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
						if (contents.potion().isPresent() && contents.potion().get().is(SweetapocalpysebiomeModPotions.PURIFY_POTION)) {
							hasPotion = true;
							purifyPotionStack = offhandStack;
						}
					}
				}
				
				if (hasPotion) {
					// Check inventory for corrupted food
					boolean purified = false;
					for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
						ItemStack stack = player.getInventory().getItem(i);
						if (!stack.isEmpty()) {
							CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
							if (customData != null && customData.copyTag().getBoolean("Corrupted")) {
								// Transform corrupted food to normal variant (shared with the purify GUI)
								ItemStack normalStack = FoodCorruptionHelper.toNormalFood(stack);
								if (!normalStack.isEmpty()) {
									player.getInventory().setItem(i, normalStack);
									player.sendSystemMessage(Component.literal("§aPurified " + normalStack.getDisplayName().getString()));
									
									// Consume one purify potion
									purifyPotionStack.shrink(1);
									purified = true;
									break; // Only purify one item per potion
								}
							}
						}
					}
					
					if (!purified) {
						player.sendSystemMessage(Component.literal("§cNo corrupted food found to purify!"));
					}
				} else {
					player.sendSystemMessage(Component.literal("§cNeed Purify Potion in hand to purify food!"));
				}
			}
		}).exceptionally(e -> {
			context.connection().disconnect(Component.literal(e.getMessage()));
			return null;
		});
	}

	private static ItemStack transformToNormal(ItemStack corruptedStack) {
		// Kept as a thin wrapper so any older callers still behave consistently.
		return FoodCorruptionHelper.toNormalFood(corruptedStack);
	}

	public static void send() {
		PacketDistributor.sendToServer(new PurifyFoodPacket());
	}
}