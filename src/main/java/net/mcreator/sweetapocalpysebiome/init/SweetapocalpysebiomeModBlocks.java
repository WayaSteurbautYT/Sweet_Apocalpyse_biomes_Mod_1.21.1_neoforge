/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.sweetapocalpysebiome.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.client.renderer.Sheets;

import net.mcreator.sweetapocalpysebiome.block.*;
import net.mcreator.sweetapocalpysebiome.SweetapocalpysebiomeMod;

@EventBusSubscriber
public class SweetapocalpysebiomeModBlocks {
	public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(SweetapocalpysebiomeMod.MODID);
	public static final DeferredBlock<Block> COOKY_COBBLE_STONE;
	public static final DeferredBlock<Block> COOKY_STONE;
	public static final DeferredBlock<Block> COOKY_SMOOTH_STONE;
	public static final DeferredBlock<Block> CANDY_TITANIUM_ORE;
	public static final DeferredBlock<Block> CANDY_TITANIUM_BLOCK;
	public static final DeferredBlock<Block> CANDY_GRASS;
	public static final DeferredBlock<Block> CANDY_D_IRT;
	public static final DeferredBlock<Block> CHOCOLATE_LOG;
	public static final DeferredBlock<Block> CHOCOLATE_WOOD;
	public static final DeferredBlock<Block> STRIPPED_CHOCOLATE_LOG;
	public static final DeferredBlock<Block> STRIPPED_CHOCOLATE_WOOD;
	public static final DeferredBlock<Block> CHOCOLATE_PLANKS;
	public static final DeferredBlock<Block> CHOCOLATE_LEAVES;
	public static final DeferredBlock<Block> CHOCOLATE_STAIRS;
	public static final DeferredBlock<Block> CHOCOLATE_SLAB;
	public static final DeferredBlock<Block> CHOCOLATE_FENCE;
	public static final DeferredBlock<Block> CHOCOLATE_FENCE_GATE;
	public static final DeferredBlock<Block> CHOCOLATE_DOOR;
	public static final DeferredBlock<Block> CHOCOLATE_TRAPDOOR;
	public static final DeferredBlock<Block> CHOCOLATE_PRESSURE_PLATE;
	public static final DeferredBlock<Block> CHOCOLATE_BUTTON;
	public static final DeferredBlock<Block> CHOCOLATE_SIGN;
	public static final DeferredBlock<Block> CHOCOLATE_WALL_SIGN;
	public static final DeferredBlock<Block> CHOCOLATE_HANGING_SIGN;
	public static final DeferredBlock<Block> CHOCOLATE_WALL_HANGING_SIGN;
	public static final DeferredBlock<Block> STRIPPED_CHOCOLATE_PLANKS;
	static {
		COOKY_COBBLE_STONE = REGISTRY.register("cooky_cobble_stone", CookyCobbleStoneBlock::new);
		COOKY_STONE = REGISTRY.register("cooky_stone", CookyStoneBlock::new);
		COOKY_SMOOTH_STONE = REGISTRY.register("cooky_smooth_stone", CookySmoothStoneBlock::new);
		CANDY_TITANIUM_ORE = REGISTRY.register("candy_titanium_ore", CandyTitaniumOreBlock::new);
		CANDY_TITANIUM_BLOCK = REGISTRY.register("candy_titanium_block", CandyTitaniumBlockBlock::new);
		CANDY_GRASS = REGISTRY.register("candy_grass", CandyGrassBlock::new);
		CANDY_D_IRT = REGISTRY.register("candy_d_irt", CandyDIrtBlock::new);
		CHOCOLATE_LOG = REGISTRY.register("chocolate_log", ChocolateLogBlock::new);
		CHOCOLATE_WOOD = REGISTRY.register("chocolate_wood", ChocolateWoodBlock::new);
		STRIPPED_CHOCOLATE_LOG = REGISTRY.register("stripped_chocolate_log", StrippedChocolateLogBlock::new);
		STRIPPED_CHOCOLATE_WOOD = REGISTRY.register("stripped_chocolate_wood", StrippedChocolateWoodBlock::new);
		CHOCOLATE_PLANKS = REGISTRY.register("chocolate_planks", ChocolatePlanksBlock::new);
		CHOCOLATE_LEAVES = REGISTRY.register("chocolate_leaves", ChocolateLeavesBlock::new);
		CHOCOLATE_STAIRS = REGISTRY.register("chocolate_stairs", ChocolateStairsBlock::new);
		CHOCOLATE_SLAB = REGISTRY.register("chocolate_slab", ChocolateSlabBlock::new);
		CHOCOLATE_FENCE = REGISTRY.register("chocolate_fence", ChocolateFenceBlock::new);
		CHOCOLATE_FENCE_GATE = REGISTRY.register("chocolate_fence_gate", ChocolateFenceGateBlock::new);
		CHOCOLATE_DOOR = REGISTRY.register("chocolate_door", ChocolateDoorBlock::new);
		CHOCOLATE_TRAPDOOR = REGISTRY.register("chocolate_trapdoor", ChocolateTrapdoorBlock::new);
		CHOCOLATE_PRESSURE_PLATE = REGISTRY.register("chocolate_pressure_plate", ChocolatePressurePlateBlock::new);
		CHOCOLATE_BUTTON = REGISTRY.register("chocolate_button", ChocolateButtonBlock::new);
		CHOCOLATE_SIGN = REGISTRY.register("chocolate_sign", ChocolateSignBlock::new);
		CHOCOLATE_WALL_SIGN = REGISTRY.register("chocolate_wall_sign", ChocolateWallSignBlock::new);
		CHOCOLATE_HANGING_SIGN = REGISTRY.register("chocolate_hanging_sign", ChocolateHangingSignBlock::new);
		CHOCOLATE_WALL_HANGING_SIGN = REGISTRY.register("chocolate_wall_hanging_sign", ChocolateWallHangingSignBlock::new);
		STRIPPED_CHOCOLATE_PLANKS = REGISTRY.register("stripped_chocolate_planks", StrippedChocolatePlanksBlock::new);
	}

	// Start of user code block custom blocks
	// End of user code block custom blocks
	@EventBusSubscriber(Dist.CLIENT)
	public static class BlocksClientSideHandler {
		@SubscribeEvent
		public static void blockColorLoad(RegisterColorHandlersEvent.Block event) {
			CandyGrassBlock.blockColorLoad(event);
			CandyDIrtBlock.blockColorLoad(event);
		}

		@SubscribeEvent
		public static void itemColorLoad(RegisterColorHandlersEvent.Item event) {
			CandyGrassBlock.itemColorLoad(event);
			CandyDIrtBlock.itemColorLoad(event);
		}

		@SubscribeEvent
		public static void clientSetup(FMLClientSetupEvent event) {
			Sheets.addWoodType(SweetapocalpysebiomeModWoodTypes.CHOCOLATE_SIGN_WOOD_TYPE);
			Sheets.addWoodType(SweetapocalpysebiomeModWoodTypes.CHOCOLATE_HANGING_SIGN_WOOD_TYPE);
		}
	}

	@SubscribeEvent
	public static void registerSigns(BlockEntityTypeAddBlocksEvent event) {
		event.modify(BlockEntityType.SIGN, CHOCOLATE_SIGN.get(), CHOCOLATE_WALL_SIGN.get());
		event.modify(BlockEntityType.HANGING_SIGN, CHOCOLATE_HANGING_SIGN.get(), CHOCOLATE_WALL_HANGING_SIGN.get());
	}
}