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
			}).build());

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
		}
	}
}