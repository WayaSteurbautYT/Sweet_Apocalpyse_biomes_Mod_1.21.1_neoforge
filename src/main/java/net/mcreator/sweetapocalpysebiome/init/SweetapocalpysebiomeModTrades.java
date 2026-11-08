/*
*	MCreator note: This file will be REGENERATED on each build.
*/
package net.mcreator.sweetapocalpysebiome.init;

import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.common.BasicItemListing;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;

@EventBusSubscriber
public class SweetapocalpysebiomeModTrades {
	@SubscribeEvent
	public static void registerTrades(VillagerTradesEvent event) {
		if (event.getType() == SweetapocalpysebiomeModVillagerProfessions.CANDY_APOCALPYSE_VILLAGER.get()) {
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(SweetapocalpysebiomeModItems.TITANIUM_CANDY_INGOT.get()), new ItemStack(SweetapocalpysebiomeModBlocks.STRIPPED_CHOCOLATE_LOG.get(), 64),
					new ItemStack(SweetapocalpysebiomeModItems.TITANIUM_ARMOR_HELMET.get()), 10, 5, 0.05f));
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(SweetapocalpysebiomeModItems.TITANIUM_CANDY_INGOT.get()), new ItemStack(SweetapocalpysebiomeModBlocks.STRIPPED_CHOCOLATE_LOG.get(), 64),
					new ItemStack(SweetapocalpysebiomeModItems.TITANIUM_ARMOR_CHESTPLATE.get()), 10, 5, 0.05f));
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(SweetapocalpysebiomeModItems.TITANIUM_CANDY_INGOT.get()), new ItemStack(SweetapocalpysebiomeModBlocks.STRIPPED_CHOCOLATE_LOG.get(), 64),
					new ItemStack(SweetapocalpysebiomeModItems.TITANIUM_ARMOR_LEGGINGS.get()), 10, 5, 0.05f));
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(SweetapocalpysebiomeModItems.TITANIUM_CANDY_INGOT.get()), new ItemStack(SweetapocalpysebiomeModBlocks.STRIPPED_CHOCOLATE_LOG.get(), 64),
					new ItemStack(SweetapocalpysebiomeModItems.TITANIUM_ARMOR_BOOTS.get()), 10, 5, 0.05f));
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(SweetapocalpysebiomeModItems.TITANIUM_CANDY_INGOT.get()), new ItemStack(SweetapocalpysebiomeModBlocks.STRIPPED_CHOCOLATE_LOG.get(), 64),
					new ItemStack(SweetapocalpysebiomeModItems.TITANIUM_PICKAXE.get()), 10, 5, 0.05f));
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(SweetapocalpysebiomeModItems.TITANIUM_CANDY_INGOT.get()), new ItemStack(SweetapocalpysebiomeModBlocks.STRIPPED_CHOCOLATE_LOG.get(), 64),
					new ItemStack(SweetapocalpysebiomeModItems.TITANIUM_AXE.get()), 10, 5, 0.05f));
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(SweetapocalpysebiomeModItems.TITANIUM_CANDY_INGOT.get()), new ItemStack(SweetapocalpysebiomeModBlocks.STRIPPED_CHOCOLATE_LOG.get(), 64),
					new ItemStack(SweetapocalpysebiomeModItems.TITANIUM_SWORD.get()), 10, 5, 0.05f));
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(SweetapocalpysebiomeModItems.TITANIUM_CANDY_INGOT.get()), new ItemStack(SweetapocalpysebiomeModBlocks.STRIPPED_CHOCOLATE_LOG.get(), 64),
					new ItemStack(SweetapocalpysebiomeModItems.TITANIUM_SHOVEL.get()), 10, 5, 0.05f));
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(SweetapocalpysebiomeModItems.TITANIUM_CANDY_INGOT.get()), new ItemStack(SweetapocalpysebiomeModBlocks.STRIPPED_CHOCOLATE_LOG.get(), 64),
					new ItemStack(SweetapocalpysebiomeModItems.TITANIUM_HOE.get()), 10, 5, 0.05f));
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(SweetapocalpysebiomeModBlocks.CANDY_D_IRT.get(), 64), new ItemStack(SweetapocalpysebiomeModItems.CHOCOLATEFRAGMENT.get(), 20),
					new ItemStack(SweetapocalpysebiomeModItems.TITANIUM_CANDY_INGOT.get(), 16), 10, 5, 0.05f));
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(SweetapocalpysebiomeModBlocks.CANDY_TITANIUM_BLOCK.get()), new ItemStack(SweetapocalpysebiomeModBlocks.STRIPPED_CHOCOLATE_WOOD.get(), 2),
					new ItemStack(SweetapocalpysebiomeModItems.CHOCOLATEFRAGMENT_1_ARMOR_HELMET.get()), 10, 5, 0.05f));
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(SweetapocalpysebiomeModBlocks.CANDY_TITANIUM_BLOCK.get()), new ItemStack(SweetapocalpysebiomeModBlocks.STRIPPED_CHOCOLATE_WOOD.get()),
					new ItemStack(SweetapocalpysebiomeModItems.CHOCOLATEFRAGMENT_1_ARMOR_CHESTPLATE.get()), 10, 5, 0.05f));
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(SweetapocalpysebiomeModBlocks.CANDY_TITANIUM_BLOCK.get()), new ItemStack(SweetapocalpysebiomeModBlocks.STRIPPED_CHOCOLATE_WOOD.get(), 3),
					new ItemStack(SweetapocalpysebiomeModItems.CHOCOLATEFRAGMENT_1_ARMOR_LEGGINGS.get()), 10, 5, 0.05f));
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(SweetapocalpysebiomeModBlocks.CANDY_TITANIUM_BLOCK.get()), new ItemStack(SweetapocalpysebiomeModBlocks.STRIPPED_CHOCOLATE_WOOD.get(), 2),
					new ItemStack(SweetapocalpysebiomeModItems.CHOCOLATEFRAGMENT_1_ARMOR_BOOTS.get()), 10, 5, 0.05f));
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(SweetapocalpysebiomeModBlocks.CANDY_TITANIUM_BLOCK.get()), new ItemStack(SweetapocalpysebiomeModBlocks.STRIPPED_CHOCOLATE_LOG.get(), 10),
					new ItemStack(SweetapocalpysebiomeModItems.CHOCOLATEFRAGMENT_1_PICKAXE.get()), 10, 5, 0.05f));
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(SweetapocalpysebiomeModBlocks.CANDY_TITANIUM_BLOCK.get()), new ItemStack(SweetapocalpysebiomeModBlocks.STRIPPED_CHOCOLATE_LOG.get(), 10),
					new ItemStack(SweetapocalpysebiomeModItems.CHOCOLATEFRAGMENT_1_AXE.get()), 10, 5, 0.05f));
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(SweetapocalpysebiomeModBlocks.CANDY_TITANIUM_BLOCK.get()), new ItemStack(SweetapocalpysebiomeModBlocks.STRIPPED_CHOCOLATE_LOG.get(), 10),
					new ItemStack(SweetapocalpysebiomeModItems.CHOCOLATEFRAGMENT_1_SWORD.get()), 10, 5, 0.05f));
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(SweetapocalpysebiomeModBlocks.CANDY_TITANIUM_BLOCK.get()), new ItemStack(SweetapocalpysebiomeModBlocks.STRIPPED_CHOCOLATE_LOG.get(), 10),
					new ItemStack(SweetapocalpysebiomeModItems.CHOCOLATEFRAGMENT_1_SHOVEL.get()), 10, 5, 0.05f));
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(SweetapocalpysebiomeModBlocks.CANDY_TITANIUM_BLOCK.get()), new ItemStack(SweetapocalpysebiomeModBlocks.STRIPPED_CHOCOLATE_LOG.get(), 10),
					new ItemStack(SweetapocalpysebiomeModItems.CHOCOLATEFRAGMENT_1_HOE.get()), 10, 5, 0.05f));
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(SweetapocalpysebiomeModBlocks.STRIPPED_CHOCOLATE_LOG.get()), new ItemStack(SweetapocalpysebiomeModItems.CHOCOLATECORRUPTED.get()),
					new ItemStack(SweetapocalpysebiomeModItems.CHOCOLATEFRAGMENT.get(), 15), 10, 5, 0.05f));
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(SweetapocalpysebiomeModBlocks.COOKY_STONE.get()), new ItemStack(Items.NETHERITE_INGOT, 5), new ItemStack(Items.COOKIE, 15), 10, 5, 0.05f));
		}
	}
}