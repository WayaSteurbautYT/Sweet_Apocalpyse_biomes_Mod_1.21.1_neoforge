package net.mcreator.sweetapocalpysebiome;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.CustomData;

import net.mcreator.sweetapocalpysebiome.init.SweetapocalpysebiomeModItems;
import net.mcreator.sweetapocalpysebiome.init.SweetapocalpysebiomeModPotions;

public final class FoodCorruptionHelper {
	// Resolved once instead of on every call (these run in tick, eat and GUI hot paths).
	private static final TagKey<Item> CORRUPTED_FOODS = ItemTags.create(ResourceLocation.fromNamespaceAndPath(SweetapocalpysebiomeMod.MODID, "corrupted_foods"));
	private static final TagKey<Item> PURIFIED_FOODS = ItemTags.create(ResourceLocation.fromNamespaceAndPath(SweetapocalpysebiomeMod.MODID, "purified_foods"));
	private static final TagKey<Item> PURIFIABLE_FOODS = ItemTags.create(ResourceLocation.fromNamespaceAndPath(SweetapocalpysebiomeMod.MODID, "purifiable_foods"));

	private FoodCorruptionHelper() {
	}

	/**
	 * Marks a stack as purified (normal food). Kept in one place so every code path writes exactly
	 * the same tags.
	 */
	public static void markPurified(ItemStack stack) {
		if (stack == null || stack.isEmpty()) {
			return;
		}
		CompoundTag tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
		tag.putBoolean("Corrupted", false);
		tag.putBoolean("Purified", true);
		tag.putInt("PurifyTimer", 0);
		stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
	}

	public static boolean isPurifyPotion(ItemStack stack) {
		if (stack == null || stack.isEmpty()) {
			return false;
		}
		if (stack.is(SweetapocalpysebiomeModItems.PURIFICATION_POTION.get())) {
			return true;
		}
		if (stack.is(Items.POTION) || stack.is(Items.SPLASH_POTION) || stack.is(Items.LINGERING_POTION)) {
			PotionContents contents = stack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
			return contents.potion().isPresent() && contents.potion().get().is(SweetapocalpysebiomeModPotions.PURIFY_POTION);
		}
		return false;
	}

	public static boolean isDedicatedCorruptedItem(ItemStack stack) {
		return stack.is(SweetapocalpysebiomeModItems.CORRUPTED_COOKIE.get()) || stack.is(SweetapocalpysebiomeModItems.CHOCOLATECORRUPTED.get());
	}

	public static boolean isDedicatedNormalItem(ItemStack stack) {
		return stack.is(Items.COOKIE) || stack.is(SweetapocalpysebiomeModItems.CHOCOLATEFRAGMENT.get()) || stack.is(SweetapocalpysebiomeModItems.CHOCOLATE_BAR.get())
				|| stack.is(SweetapocalpysebiomeModItems.COOKIES_NOTCH_APPLE.get());
	}

	public static boolean hasFoodComponent(ItemStack stack) {
		return !stack.isEmpty() && stack.has(DataComponents.FOOD);
	}

	public static boolean isMarkedPurified(ItemStack stack) {
		CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
		return customData != null && customData.copyTag().getBoolean("Purified") && !customData.copyTag().getBoolean("Corrupted");
	}

	public static boolean isMarkedCorrupted(ItemStack stack) {
		CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
		if (customData == null) {
			return false;
		}
		CompoundTag tag = customData.copyTag();
		if (tag.getBoolean("Purified")) {
			return false;
		}
		return tag.getBoolean("Corrupted");
	}

	public static boolean isCorruptedFood(ItemStack stack) {
		if (stack == null || stack.isEmpty()) {
			return false;
		}
		if (isMarkedPurified(stack)) {
			return false;
		}
		if (isDedicatedCorruptedItem(stack)) {
			return true;
		}
		if (stack.is(CORRUPTED_FOODS) && !isMarkedPurified(stack)) {
			return true;
		}
		return hasFoodComponent(stack) && isMarkedCorrupted(stack);
	}

	public static boolean isNormalFood(ItemStack stack) {
		if (stack == null || stack.isEmpty() || isCorruptedFood(stack) || isDedicatedCorruptedItem(stack)) {
			return false;
		}
		if (isMarkedPurified(stack) && hasFoodComponent(stack)) {
			return true;
		}
		if (stack.is(PURIFIED_FOODS)) {
			return true;
		}
		if (stack.is(PURIFIABLE_FOODS) && !isMarkedCorrupted(stack)) {
			return true;
		}
		if (isDedicatedNormalItem(stack) && !isMarkedCorrupted(stack)) {
			return true;
		}
		return hasFoodComponent(stack) && !isMarkedCorrupted(stack);
	}

	public static ItemStack toNormalFood(ItemStack corruptedStack) {
		if (!isCorruptedFood(corruptedStack)) {
			return ItemStack.EMPTY;
		}
		ItemStack result;
		if (corruptedStack.is(SweetapocalpysebiomeModItems.CORRUPTED_COOKIE.get())) {
			result = new ItemStack(Items.COOKIE, corruptedStack.getCount());
		} else if (corruptedStack.is(SweetapocalpysebiomeModItems.CHOCOLATECORRUPTED.get())) {
			result = new ItemStack(SweetapocalpysebiomeModItems.CHOCOLATEFRAGMENT.get(), corruptedStack.getCount());
		} else {
			result = corruptedStack.copy();
		}
		markPurified(result);
		return result;
	}

	public static void markDedicatedCorrupted(ItemStack stack) {
		if (!isDedicatedCorruptedItem(stack)) {
			return;
		}
		CompoundTag tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
		if (tag.getBoolean("Purified")) {
			return;
		}
		if (!tag.getBoolean("Corrupted")) {
			tag.putBoolean("Corrupted", true);
			tag.putBoolean("Purified", false);
			stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
		}
	}

	public static boolean canPurify(ItemStack stack) {
		return isCorruptedFood(stack) && !isMarkedPurified(stack);
	}
}
