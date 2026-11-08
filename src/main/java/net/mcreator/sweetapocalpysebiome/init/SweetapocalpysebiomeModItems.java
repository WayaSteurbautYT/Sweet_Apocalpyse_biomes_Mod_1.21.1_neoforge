/*
*    MCreator note: This file will be REGENERATED on each build.
*/
package net.mcreator.sweetapocalpysebiome.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.fluids.capability.wrappers.FluidBucketWrapper;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.*;

import net.mcreator.sweetapocalpysebiome.item.*;
import net.mcreator.sweetapocalpysebiome.SweetapocalpysebiomeMod;

@EventBusSubscriber
public class SweetapocalpysebiomeModItems {
	public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(SweetapocalpysebiomeMod.MODID);
	public static final DeferredItem<Item> COOKY_COBBLE_STONE;
	public static final DeferredItem<Item> COOKY_STONE;
	public static final DeferredItem<Item> COOKY_SMOOTH_STONE;
	public static final DeferredItem<Item> TITANIUM_CANDY_INGOT;
	public static final DeferredItem<Item> CANDY_TITANIUM_ORE;
	public static final DeferredItem<Item> CANDY_TITANIUM_BLOCK;
	public static final DeferredItem<Item> CANDY_GRASS;
	public static final DeferredItem<Item> CANDY_D_IRT;
	public static final DeferredItem<Item> CHOCOLATE_LOG;
	public static final DeferredItem<Item> CHOCOLATE_WOOD;
	public static final DeferredItem<Item> STRIPPED_CHOCOLATE_LOG;
	public static final DeferredItem<Item> STRIPPED_CHOCOLATE_WOOD;
	public static final DeferredItem<Item> CHOCOLATE_PLANKS;
	public static final DeferredItem<Item> CHOCOLATE_LEAVES;
	public static final DeferredItem<Item> CHOCOLATE_STAIRS;
	public static final DeferredItem<Item> CHOCOLATE_SLAB;
	public static final DeferredItem<Item> CHOCOLATE_FENCE;
	public static final DeferredItem<Item> CHOCOLATE_FENCE_GATE;
	public static final DeferredItem<Item> CHOCOLATE_DOOR;
	public static final DeferredItem<Item> CHOCOLATE_TRAPDOOR;
	public static final DeferredItem<Item> CHOCOLATE_PRESSURE_PLATE;
	public static final DeferredItem<Item> CHOCOLATE_BUTTON;
	public static final DeferredItem<Item> CHOCOLATE_SIGN;
	public static final DeferredItem<Item> CHOCOLATE_HANGING_SIGN;
	public static final DeferredItem<Item> CHOCOLATE_BOAT;
	public static final DeferredItem<Item> CHOCOLATE_CHEST_BOAT;
	public static final DeferredItem<Item> STRIPPED_CHOCOLATE_PLANKS;
	public static final DeferredItem<Item> CHOCOLATECORRUPTED;
	public static final DeferredItem<Item> CHOCOLATEFRAGMENT;
	public static final DeferredItem<Item> SWEETAPOCALPYSEDIMENSION;
	public static final DeferredItem<Item> TITANIUM_PICKAXE;
	public static final DeferredItem<Item> TITANIUM_AXE;
	public static final DeferredItem<Item> TITANIUM_SWORD;
	public static final DeferredItem<Item> TITANIUM_SHOVEL;
	public static final DeferredItem<Item> TITANIUM_HOE;
	public static final DeferredItem<Item> CHOCOLATEFRAGMENT_1_PICKAXE;
	public static final DeferredItem<Item> CHOCOLATEFRAGMENT_1_AXE;
	public static final DeferredItem<Item> CHOCOLATEFRAGMENT_1_SWORD;
	public static final DeferredItem<Item> CHOCOLATEFRAGMENT_1_SHOVEL;
	public static final DeferredItem<Item> CHOCOLATEFRAGMENT_1_HOE;
	public static final DeferredItem<Item> TITANIUM_ARMOR_HELMET;
	public static final DeferredItem<Item> TITANIUM_ARMOR_CHESTPLATE;
	public static final DeferredItem<Item> TITANIUM_ARMOR_LEGGINGS;
	public static final DeferredItem<Item> TITANIUM_ARMOR_BOOTS;
	public static final DeferredItem<Item> CHOCOLATEFRAGMENT_1_ARMOR_HELMET;
	public static final DeferredItem<Item> CHOCOLATEFRAGMENT_1_ARMOR_CHESTPLATE;
	public static final DeferredItem<Item> CHOCOLATEFRAGMENT_1_ARMOR_LEGGINGS;
	public static final DeferredItem<Item> CHOCOLATEFRAGMENT_1_ARMOR_BOOTS;
	public static final DeferredItem<Item> COOKIE_ARMOR_HELMET;
	public static final DeferredItem<Item> COOKIE_ARMOR_CHESTPLATE;
	public static final DeferredItem<Item> COOKIE_ARMOR_LEGGINGS;
	public static final DeferredItem<Item> COOKIE_ARMOR_BOOTS;
	public static final DeferredItem<Item> COOKIES_PICKAXE;
	public static final DeferredItem<Item> COOKIES_AXE;
	public static final DeferredItem<Item> COOKIES_SWORD;
	public static final DeferredItem<Item> COOKIES_SHOVEL;
	public static final DeferredItem<Item> COOKIES_HOE;
	public static final DeferredItem<Item> COOKIES_NOTCH_APPLE;
	public static final DeferredItem<Item> CHOCOLATE_BAR;
	public static final DeferredItem<Item> CORRUPTED_COOKIE;
	public static final DeferredItem<Item> PURIFY_ORE;
	public static final DeferredItem<Item> PURIFY_ORE_BLOCK;
	public static final DeferredItem<Item> PURIFIER_BLOCK;
	public static final DeferredItem<Item> CARAMEL_LAVA_BUCKET;
	static {
		COOKY_COBBLE_STONE = block(SweetapocalpysebiomeModBlocks.COOKY_COBBLE_STONE, new Item.Properties().rarity(Rarity.UNCOMMON));
		COOKY_STONE = block(SweetapocalpysebiomeModBlocks.COOKY_STONE, new Item.Properties().rarity(Rarity.RARE));
		COOKY_SMOOTH_STONE = block(SweetapocalpysebiomeModBlocks.COOKY_SMOOTH_STONE, new Item.Properties().rarity(Rarity.EPIC));
		TITANIUM_CANDY_INGOT = REGISTRY.register("titanium_candy_ingot", TitaniumCandyIngotItem::new);
		CANDY_TITANIUM_ORE = block(SweetapocalpysebiomeModBlocks.CANDY_TITANIUM_ORE, new Item.Properties().rarity(Rarity.RARE));
		CANDY_TITANIUM_BLOCK = block(SweetapocalpysebiomeModBlocks.CANDY_TITANIUM_BLOCK, new Item.Properties().rarity(Rarity.EPIC));
		CANDY_GRASS = block(SweetapocalpysebiomeModBlocks.CANDY_GRASS, new Item.Properties().rarity(Rarity.UNCOMMON));
		CANDY_D_IRT = block(SweetapocalpysebiomeModBlocks.CANDY_D_IRT, new Item.Properties().rarity(Rarity.UNCOMMON));
		CHOCOLATE_LOG = block(SweetapocalpysebiomeModBlocks.CHOCOLATE_LOG);
		CHOCOLATE_WOOD = block(SweetapocalpysebiomeModBlocks.CHOCOLATE_WOOD);
		STRIPPED_CHOCOLATE_LOG = block(SweetapocalpysebiomeModBlocks.STRIPPED_CHOCOLATE_LOG);
		STRIPPED_CHOCOLATE_WOOD = block(SweetapocalpysebiomeModBlocks.STRIPPED_CHOCOLATE_WOOD);
		CHOCOLATE_PLANKS = block(SweetapocalpysebiomeModBlocks.CHOCOLATE_PLANKS);
		CHOCOLATE_LEAVES = block(SweetapocalpysebiomeModBlocks.CHOCOLATE_LEAVES);
		CHOCOLATE_STAIRS = block(SweetapocalpysebiomeModBlocks.CHOCOLATE_STAIRS);
		CHOCOLATE_SLAB = block(SweetapocalpysebiomeModBlocks.CHOCOLATE_SLAB);
		CHOCOLATE_FENCE = block(SweetapocalpysebiomeModBlocks.CHOCOLATE_FENCE);
		CHOCOLATE_FENCE_GATE = block(SweetapocalpysebiomeModBlocks.CHOCOLATE_FENCE_GATE);
		CHOCOLATE_DOOR = doubleBlock(SweetapocalpysebiomeModBlocks.CHOCOLATE_DOOR);
		CHOCOLATE_TRAPDOOR = block(SweetapocalpysebiomeModBlocks.CHOCOLATE_TRAPDOOR);
		CHOCOLATE_PRESSURE_PLATE = block(SweetapocalpysebiomeModBlocks.CHOCOLATE_PRESSURE_PLATE);
		CHOCOLATE_BUTTON = block(SweetapocalpysebiomeModBlocks.CHOCOLATE_BUTTON);
		CHOCOLATE_SIGN = signBlock(SweetapocalpysebiomeModBlocks.CHOCOLATE_SIGN, SweetapocalpysebiomeModBlocks.CHOCOLATE_WALL_SIGN, new Item.Properties().stacksTo(16));
		CHOCOLATE_HANGING_SIGN = hangingSignBlock(SweetapocalpysebiomeModBlocks.CHOCOLATE_HANGING_SIGN, SweetapocalpysebiomeModBlocks.CHOCOLATE_WALL_HANGING_SIGN, new Item.Properties().stacksTo(16));
		CHOCOLATE_BOAT = REGISTRY.register("chocolate_boat", () -> new BoatItem(false, SweetapocalpysebiomeModBoatTypes.CHOCOLATE_BOAT_TYPE.getValue(), new Item.Properties().stacksTo(1)));
		CHOCOLATE_CHEST_BOAT = REGISTRY.register("chocolate_chest_boat", () -> new BoatItem(true, SweetapocalpysebiomeModBoatTypes.CHOCOLATE_CHEST_BOAT_TYPE.getValue(), new Item.Properties().stacksTo(1)));
		STRIPPED_CHOCOLATE_PLANKS = block(SweetapocalpysebiomeModBlocks.STRIPPED_CHOCOLATE_PLANKS);
		CHOCOLATECORRUPTED = REGISTRY.register("chocolatecorrupted", ChocolatecorruptedItem::new);
		CHOCOLATEFRAGMENT = REGISTRY.register("chocolatefragment", ChocolatefragmentItem::new);
		SWEETAPOCALPYSEDIMENSION = REGISTRY.register("sweetapocalpysedimension", SweetapocalpysedimensionItem::new);
		TITANIUM_PICKAXE = REGISTRY.register("titanium_pickaxe", TitaniumPickaxeItem::new);
		TITANIUM_AXE = REGISTRY.register("titanium_axe", TitaniumAxeItem::new);
		TITANIUM_SWORD = REGISTRY.register("titanium_sword", TitaniumSwordItem::new);
		TITANIUM_SHOVEL = REGISTRY.register("titanium_shovel", TitaniumShovelItem::new);
		TITANIUM_HOE = REGISTRY.register("titanium_hoe", TitaniumHoeItem::new);
		CHOCOLATEFRAGMENT_1_PICKAXE = REGISTRY.register("chocolatefragment_1_pickaxe", Chocolatefragment1PickaxeItem::new);
		CHOCOLATEFRAGMENT_1_AXE = REGISTRY.register("chocolatefragment_1_axe", Chocolatefragment1AxeItem::new);
		CHOCOLATEFRAGMENT_1_SWORD = REGISTRY.register("chocolatefragment_1_sword", Chocolatefragment1SwordItem::new);
		CHOCOLATEFRAGMENT_1_SHOVEL = REGISTRY.register("chocolatefragment_1_shovel", Chocolatefragment1ShovelItem::new);
		CHOCOLATEFRAGMENT_1_HOE = REGISTRY.register("chocolatefragment_1_hoe", Chocolatefragment1HoeItem::new);
		TITANIUM_ARMOR_HELMET = REGISTRY.register("titanium_armor_helmet", TitaniumArmorItem.Helmet::new);
		TITANIUM_ARMOR_CHESTPLATE = REGISTRY.register("titanium_armor_chestplate", TitaniumArmorItem.Chestplate::new);
		TITANIUM_ARMOR_LEGGINGS = REGISTRY.register("titanium_armor_leggings", TitaniumArmorItem.Leggings::new);
		TITANIUM_ARMOR_BOOTS = REGISTRY.register("titanium_armor_boots", TitaniumArmorItem.Boots::new);
		CHOCOLATEFRAGMENT_1_ARMOR_HELMET = REGISTRY.register("chocolatefragment_1_armor_helmet", Chocolatefragment1ArmorItem.Helmet::new);
		CHOCOLATEFRAGMENT_1_ARMOR_CHESTPLATE = REGISTRY.register("chocolatefragment_1_armor_chestplate", Chocolatefragment1ArmorItem.Chestplate::new);
		CHOCOLATEFRAGMENT_1_ARMOR_LEGGINGS = REGISTRY.register("chocolatefragment_1_armor_leggings", Chocolatefragment1ArmorItem.Leggings::new);
		CHOCOLATEFRAGMENT_1_ARMOR_BOOTS = REGISTRY.register("chocolatefragment_1_armor_boots", Chocolatefragment1ArmorItem.Boots::new);
		COOKIE_ARMOR_HELMET = REGISTRY.register("cookie_armor_helmet", CookieArmorItem.Helmet::new);
		COOKIE_ARMOR_CHESTPLATE = REGISTRY.register("cookie_armor_chestplate", CookieArmorItem.Chestplate::new);
		COOKIE_ARMOR_LEGGINGS = REGISTRY.register("cookie_armor_leggings", CookieArmorItem.Leggings::new);
		COOKIE_ARMOR_BOOTS = REGISTRY.register("cookie_armor_boots", CookieArmorItem.Boots::new);
		COOKIES_PICKAXE = REGISTRY.register("cookies_pickaxe", CookiesPickaxeItem::new);
		COOKIES_AXE = REGISTRY.register("cookies_axe", CookiesAxeItem::new);
		COOKIES_SWORD = REGISTRY.register("cookies_sword", CookiesSwordItem::new);
		COOKIES_SHOVEL = REGISTRY.register("cookies_shovel", CookiesShovelItem::new);
		COOKIES_HOE = REGISTRY.register("cookies_hoe", CookiesHoeItem::new);
		COOKIES_NOTCH_APPLE = REGISTRY.register("cookies_notch_apple", CookiesNotchAppleItem::new);
		CHOCOLATE_BAR = REGISTRY.register("chocolate_bar", ChocolateBarItem::new);
		CORRUPTED_COOKIE = REGISTRY.register("corrupted_cookie", CorruptedCookieItem::new);
		PURIFY_ORE = REGISTRY.register("purify_ore", PurifyOreItem::new);
		PURIFY_ORE_BLOCK = block(SweetapocalpysebiomeModBlocks.PURIFY_ORE_BLOCK, new Item.Properties().rarity(Rarity.RARE));
		PURIFIER_BLOCK = block(SweetapocalpysebiomeModBlocks.PURIFIER_BLOCK, new Item.Properties().rarity(Rarity.RARE));
		CARAMEL_LAVA_BUCKET = REGISTRY.register("caramel_lava_bucket", CaramelLavaItem::new);
	}
	// Start of user code block custom items
	public static final DeferredItem<Item> TITANIUM_CANDY_SWORD;
	public static final DeferredItem<Item> TITANIUM_CANDY_PICKAXE;
	public static final DeferredItem<Item> TITANIUM_CANDY_SHOVEL;
	public static final DeferredItem<Item> TITANIUM_CANDY_AXE;
	public static final DeferredItem<Item> ENHANCED_CANDY_INGOT;
	public static final DeferredItem<Item> ENHANCED_CANDY_TITANIUM_INGOT;
	public static final DeferredItem<Item> ENHANCED_CANDY_TITANIUM_SWORD;
	public static final DeferredItem<Item> CANDY_DIMENSION_PORTAL;
	public static final DeferredItem<Item> CANDY_COMPRESSOR;
	public static final DeferredItem<Item> PURIFICATION_POTION;
	static {
		TITANIUM_CANDY_SWORD = REGISTRY.register("titanium_candy_sword", net.mcreator.sweetapocalpysebiome.item.TitaniumCandySwordItem::new);
		TITANIUM_CANDY_PICKAXE = REGISTRY.register("titanium_candy_pickaxe", net.mcreator.sweetapocalpysebiome.item.TitaniumCandyPickaxeItem::new);
		TITANIUM_CANDY_SHOVEL = REGISTRY.register("titanium_candy_shovel", net.mcreator.sweetapocalpysebiome.item.TitaniumCandyShovelItem::new);
		TITANIUM_CANDY_AXE = REGISTRY.register("titanium_candy_axe", net.mcreator.sweetapocalpysebiome.item.TitaniumCandyAxeItem::new);
		ENHANCED_CANDY_INGOT = REGISTRY.register("enhanced_candy_ingot", net.mcreator.sweetapocalpysebiome.item.EnhancedCandyIngotItem::new);
		ENHANCED_CANDY_TITANIUM_INGOT = REGISTRY.register("enhanced_candy_titanium_ingot", net.mcreator.sweetapocalpysebiome.item.EnhancedCandyIngotItem::new);
		ENHANCED_CANDY_TITANIUM_SWORD = REGISTRY.register("enhanced_candy_titanium_sword", net.mcreator.sweetapocalpysebiome.item.EnhancedCandyIngotItem::new);
		CANDY_DIMENSION_PORTAL = REGISTRY.register("candy_dimension_portal", net.mcreator.sweetapocalpysebiome.item.CandyDimensionPortalItem::new);
		CANDY_COMPRESSOR = REGISTRY.register("candy_compressor", net.mcreator.sweetapocalpysebiome.item.CandyCompressorItem::new);
		PURIFICATION_POTION = REGISTRY.register("purification_potion", net.mcreator.sweetapocalpysebiome.item.PurificationPotionItem::new);
	}

	// End of user code block custom items
	@SubscribeEvent
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.registerItem(Capabilities.FluidHandler.ITEM, (stack, context) -> new FluidBucketWrapper(stack), CARAMEL_LAVA_BUCKET.get());
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block) {
		return block(block, new Item.Properties());
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block, Item.Properties properties) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), properties));
	}

	private static DeferredItem<Item> doubleBlock(DeferredHolder<Block, Block> block) {
		return doubleBlock(block, new Item.Properties());
	}

	private static DeferredItem<Item> doubleBlock(DeferredHolder<Block, Block> block, Item.Properties properties) {
		return REGISTRY.register(block.getId().getPath(), () -> new DoubleHighBlockItem(block.get(), properties));
	}

	private static DeferredItem<Item> signBlock(DeferredHolder<Block, Block> block, DeferredHolder<Block, Block> wallBlock) {
		return signBlock(block, wallBlock, new Item.Properties());
	}

	private static DeferredItem<Item> signBlock(DeferredHolder<Block, Block> block, DeferredHolder<Block, Block> wallBlock, Item.Properties properties) {
		return REGISTRY.register(block.getId().getPath(), () -> new SignItem(properties, block.get(), wallBlock.get()));
	}

	private static DeferredItem<Item> hangingSignBlock(DeferredHolder<Block, Block> block, DeferredHolder<Block, Block> wallBlock) {
		return hangingSignBlock(block, wallBlock, new Item.Properties());
	}

	private static DeferredItem<Item> hangingSignBlock(DeferredHolder<Block, Block> block, DeferredHolder<Block, Block> wallBlock, Item.Properties properties) {
		return REGISTRY.register(block.getId().getPath(), () -> new HangingSignItem(block.get(), wallBlock.get(), properties));
	}
}