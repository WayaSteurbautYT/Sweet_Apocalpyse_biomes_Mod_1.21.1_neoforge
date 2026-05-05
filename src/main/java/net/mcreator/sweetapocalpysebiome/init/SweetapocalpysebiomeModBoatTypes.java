/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.sweetapocalpysebiome.init;

import net.neoforged.fml.common.asm.enumextension.EnumProxy;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.vehicle.Boat;

import java.util.function.Supplier;

public class SweetapocalpysebiomeModBoatTypes {
	public static final EnumProxy<Boat.Type> CHOCOLATE_BOAT_TYPE = new EnumProxy<>(Boat.Type.class, (Supplier<Block>) () -> Blocks.OAK_PLANKS, "sweetapocalpysebiome:chocolate_boat", SweetapocalpysebiomeModItems.CHOCOLATE_BOAT,
			(Supplier<Item>) () -> Items.AIR, (Supplier<Item>) () -> Items.STICK, false);
	public static final EnumProxy<Boat.Type> CHOCOLATE_CHEST_BOAT_TYPE = new EnumProxy<>(Boat.Type.class, (Supplier<Block>) () -> Blocks.OAK_PLANKS, "sweetapocalpysebiome:chocolate_chest_boat", (Supplier<Item>) () -> Items.AIR,
			SweetapocalpysebiomeModItems.CHOCOLATE_CHEST_BOAT, (Supplier<Item>) () -> Items.STICK, false);
}