/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.sweetapocalpysebiome.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.mcreator.sweetapocalpysebiome.SweetapocalpysebiomeMod;

@EventBusSubscriber
public class SweetapocalpysebiomeModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SweetapocalpysebiomeMod.MODID);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SWEET_APOCALYPSE = REGISTRY.register("sweet_apocalypse",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.sweetapocalpysebiome.sweet_apocalypse")).icon(() -> new ItemStack(SweetapocalpysebiomeModItems.TITANIUM_CANDY_INGOT.get())).displayItems((parameters, tabData) -> {
				tabData.accept(SweetapocalpysebiomeModBlocks.COOKY_COBBLE_STONE.get().asItem());
				tabData.accept(SweetapocalpysebiomeModBlocks.COOKY_STONE.get().asItem());
				tabData.accept(SweetapocalpysebiomeModBlocks.COOKY_SMOOTH_STONE.get().asItem());
				tabData.accept(SweetapocalpysebiomeModItems.TITANIUM_CANDY_INGOT.get());
				tabData.accept(SweetapocalpysebiomeModBlocks.CANDY_TITANIUM_ORE.get().asItem());
				tabData.accept(SweetapocalpysebiomeModBlocks.CANDY_TITANIUM_BLOCK.get().asItem());
				tabData.accept(SweetapocalpysebiomeModBlocks.CANDY_GRASS.get().asItem());
				tabData.accept(SweetapocalpysebiomeModBlocks.CANDY_D_IRT.get().asItem());
				tabData.accept(SweetapocalpysebiomeModBlocks.PURIFIER_BLOCK.get().asItem());
			}).build());
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BLOCKS_SPECIAL = REGISTRY.register("blocks_special",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.sweetapocalpysebiome.blocks_special")).icon(() -> new ItemStack(SweetapocalpysebiomeModBlocks.CANDY_GRASS.get())).displayItems((parameters, tabData) -> {
				tabData.accept(SweetapocalpysebiomeModBlocks.COOKY_STONE.get().asItem());
				tabData.accept(SweetapocalpysebiomeModBlocks.CANDY_TITANIUM_ORE.get().asItem());
				tabData.accept(SweetapocalpysebiomeModBlocks.CANDY_TITANIUM_BLOCK.get().asItem());
				tabData.accept(SweetapocalpysebiomeModBlocks.CANDY_GRASS.get().asItem());
				tabData.accept(SweetapocalpysebiomeModBlocks.STRIPPED_CHOCOLATE_LOG.get().asItem());
				tabData.accept(SweetapocalpysebiomeModBlocks.PURIFY_ORE_BLOCK.get().asItem());
			}).withTabsBefore(SWEET_APOCALYPSE.getId()).build());
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SWEET_APOCALYPSE_FOOD = REGISTRY.register("sweet_apocalypse_food", () -> CreativeModeTab.builder()
			.title(Component.translatable("item_group.sweetapocalpysebiome.sweet_apocalypse_food")).icon(() -> new ItemStack(SweetapocalpysebiomeModItems.COOKIES_NOTCH_APPLE.get())).displayItems((parameters, tabData) -> {
				tabData.accept(SweetapocalpysebiomeModItems.CHOCOLATECORRUPTED.get());
				tabData.accept(SweetapocalpysebiomeModItems.CHOCOLATEFRAGMENT.get());
				tabData.accept(SweetapocalpysebiomeModItems.COOKIES_NOTCH_APPLE.get());
				tabData.accept(SweetapocalpysebiomeModItems.CHOCOLATE_BAR.get());
				tabData.accept(SweetapocalpysebiomeModItems.CORRUPTED_COOKIE.get());
				tabData.accept(SweetapocalpysebiomeModItems.CARAMEL_LAVA_BUCKET.get());
			}).withTabsBefore(BLOCKS_SPECIAL.getId()).build());
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SWEET_APOCALYPSE_ARMOUR = REGISTRY.register("sweet_apocalypse_armour", () -> CreativeModeTab.builder()
			.title(Component.translatable("item_group.sweetapocalpysebiome.sweet_apocalypse_armour")).icon(() -> new ItemStack(SweetapocalpysebiomeModItems.COOKIE_ARMOR_CHESTPLATE.get())).displayItems((parameters, tabData) -> {
				tabData.accept(SweetapocalpysebiomeModItems.TITANIUM_PICKAXE.get());
				tabData.accept(SweetapocalpysebiomeModItems.TITANIUM_AXE.get());
				tabData.accept(SweetapocalpysebiomeModItems.TITANIUM_SWORD.get());
				tabData.accept(SweetapocalpysebiomeModItems.TITANIUM_SHOVEL.get());
				tabData.accept(SweetapocalpysebiomeModItems.TITANIUM_HOE.get());
				tabData.accept(SweetapocalpysebiomeModItems.CHOCOLATEFRAGMENT_1_PICKAXE.get());
				tabData.accept(SweetapocalpysebiomeModItems.CHOCOLATEFRAGMENT_1_AXE.get());
				tabData.accept(SweetapocalpysebiomeModItems.CHOCOLATEFRAGMENT_1_SWORD.get());
				tabData.accept(SweetapocalpysebiomeModItems.CHOCOLATEFRAGMENT_1_SHOVEL.get());
				tabData.accept(SweetapocalpysebiomeModItems.CHOCOLATEFRAGMENT_1_HOE.get());
				tabData.accept(SweetapocalpysebiomeModItems.TITANIUM_ARMOR_HELMET.get());
				tabData.accept(SweetapocalpysebiomeModItems.TITANIUM_ARMOR_CHESTPLATE.get());
				tabData.accept(SweetapocalpysebiomeModItems.TITANIUM_ARMOR_LEGGINGS.get());
				tabData.accept(SweetapocalpysebiomeModItems.TITANIUM_ARMOR_BOOTS.get());
				tabData.accept(SweetapocalpysebiomeModItems.CHOCOLATEFRAGMENT_1_ARMOR_HELMET.get());
				tabData.accept(SweetapocalpysebiomeModItems.CHOCOLATEFRAGMENT_1_ARMOR_CHESTPLATE.get());
				tabData.accept(SweetapocalpysebiomeModItems.CHOCOLATEFRAGMENT_1_ARMOR_LEGGINGS.get());
				tabData.accept(SweetapocalpysebiomeModItems.CHOCOLATEFRAGMENT_1_ARMOR_BOOTS.get());
				tabData.accept(SweetapocalpysebiomeModItems.COOKIE_ARMOR_HELMET.get());
				tabData.accept(SweetapocalpysebiomeModItems.COOKIE_ARMOR_CHESTPLATE.get());
				tabData.accept(SweetapocalpysebiomeModItems.COOKIE_ARMOR_LEGGINGS.get());
				tabData.accept(SweetapocalpysebiomeModItems.COOKIE_ARMOR_BOOTS.get());
				tabData.accept(SweetapocalpysebiomeModItems.COOKIES_PICKAXE.get());
				tabData.accept(SweetapocalpysebiomeModItems.COOKIES_AXE.get());
				tabData.accept(SweetapocalpysebiomeModItems.COOKIES_SWORD.get());
				tabData.accept(SweetapocalpysebiomeModItems.COOKIES_SHOVEL.get());
				tabData.accept(SweetapocalpysebiomeModItems.COOKIES_HOE.get());
			}).withTabsBefore(SWEET_APOCALYPSE_FOOD.getId()).build());

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
		if (tabData.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
			tabData.accept(SweetapocalpysebiomeModBlocks.CHOCOLATE_LOG.get().asItem());
			tabData.accept(SweetapocalpysebiomeModBlocks.CHOCOLATE_WOOD.get().asItem());
			tabData.accept(SweetapocalpysebiomeModBlocks.STRIPPED_CHOCOLATE_LOG.get().asItem());
			tabData.accept(SweetapocalpysebiomeModBlocks.STRIPPED_CHOCOLATE_WOOD.get().asItem());
			tabData.accept(SweetapocalpysebiomeModBlocks.CHOCOLATE_PLANKS.get().asItem());
			tabData.accept(SweetapocalpysebiomeModBlocks.CHOCOLATE_STAIRS.get().asItem());
			tabData.accept(SweetapocalpysebiomeModBlocks.CHOCOLATE_SLAB.get().asItem());
			tabData.accept(SweetapocalpysebiomeModBlocks.CHOCOLATE_FENCE.get().asItem());
			tabData.accept(SweetapocalpysebiomeModBlocks.CHOCOLATE_FENCE_GATE.get().asItem());
			tabData.accept(SweetapocalpysebiomeModBlocks.CHOCOLATE_DOOR.get().asItem());
			tabData.accept(SweetapocalpysebiomeModBlocks.CHOCOLATE_TRAPDOOR.get().asItem());
			tabData.accept(SweetapocalpysebiomeModBlocks.CHOCOLATE_PRESSURE_PLATE.get().asItem());
			tabData.accept(SweetapocalpysebiomeModBlocks.CHOCOLATE_BUTTON.get().asItem());
			tabData.accept(SweetapocalpysebiomeModBlocks.STRIPPED_CHOCOLATE_PLANKS.get().asItem());
		} else if (tabData.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
			tabData.accept(SweetapocalpysebiomeModBlocks.CHOCOLATE_LEAVES.get().asItem());
		} else if (tabData.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
			tabData.accept(SweetapocalpysebiomeModBlocks.CHOCOLATE_SIGN.get().asItem());
			tabData.accept(SweetapocalpysebiomeModBlocks.CHOCOLATE_HANGING_SIGN.get().asItem());
		} else if (tabData.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			tabData.accept(SweetapocalpysebiomeModItems.CHOCOLATE_BOAT.get());
			tabData.accept(SweetapocalpysebiomeModItems.CHOCOLATE_CHEST_BOAT.get());
			tabData.accept(SweetapocalpysebiomeModItems.SWEETAPOCALPYSEDIMENSION.get());
			tabData.accept(SweetapocalpysebiomeModItems.TITANIUM_PICKAXE.get());
			tabData.accept(SweetapocalpysebiomeModItems.TITANIUM_AXE.get());
			tabData.accept(SweetapocalpysebiomeModItems.TITANIUM_SHOVEL.get());
			tabData.accept(SweetapocalpysebiomeModItems.TITANIUM_HOE.get());
			tabData.accept(SweetapocalpysebiomeModItems.CHOCOLATEFRAGMENT_1_PICKAXE.get());
			tabData.accept(SweetapocalpysebiomeModItems.CHOCOLATEFRAGMENT_1_AXE.get());
			tabData.accept(SweetapocalpysebiomeModItems.CHOCOLATEFRAGMENT_1_SHOVEL.get());
			tabData.accept(SweetapocalpysebiomeModItems.CHOCOLATEFRAGMENT_1_HOE.get());
			tabData.accept(SweetapocalpysebiomeModItems.COOKIES_PICKAXE.get());
			tabData.accept(SweetapocalpysebiomeModItems.COOKIES_AXE.get());
			tabData.accept(SweetapocalpysebiomeModItems.COOKIES_SHOVEL.get());
			tabData.accept(SweetapocalpysebiomeModItems.COOKIES_HOE.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.COMBAT) {
			tabData.accept(SweetapocalpysebiomeModItems.TITANIUM_SWORD.get());
			tabData.accept(SweetapocalpysebiomeModItems.CHOCOLATEFRAGMENT_1_SWORD.get());
			tabData.accept(SweetapocalpysebiomeModItems.TITANIUM_ARMOR_HELMET.get());
			tabData.accept(SweetapocalpysebiomeModItems.TITANIUM_ARMOR_CHESTPLATE.get());
			tabData.accept(SweetapocalpysebiomeModItems.TITANIUM_ARMOR_LEGGINGS.get());
			tabData.accept(SweetapocalpysebiomeModItems.TITANIUM_ARMOR_BOOTS.get());
			tabData.accept(SweetapocalpysebiomeModItems.CHOCOLATEFRAGMENT_1_ARMOR_HELMET.get());
			tabData.accept(SweetapocalpysebiomeModItems.CHOCOLATEFRAGMENT_1_ARMOR_CHESTPLATE.get());
			tabData.accept(SweetapocalpysebiomeModItems.CHOCOLATEFRAGMENT_1_ARMOR_LEGGINGS.get());
			tabData.accept(SweetapocalpysebiomeModItems.CHOCOLATEFRAGMENT_1_ARMOR_BOOTS.get());
			tabData.accept(SweetapocalpysebiomeModItems.COOKIE_ARMOR_HELMET.get());
			tabData.accept(SweetapocalpysebiomeModItems.COOKIE_ARMOR_CHESTPLATE.get());
			tabData.accept(SweetapocalpysebiomeModItems.COOKIE_ARMOR_LEGGINGS.get());
			tabData.accept(SweetapocalpysebiomeModItems.COOKIE_ARMOR_BOOTS.get());
			tabData.accept(SweetapocalpysebiomeModItems.COOKIES_SWORD.get());
		}
	}
}