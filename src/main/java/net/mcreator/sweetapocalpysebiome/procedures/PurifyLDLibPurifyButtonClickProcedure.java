package net.mcreator.sweetapocalpysebiome.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.mcreator.sweetapocalpysebiome.FoodCorruptionHelper;
import net.mcreator.sweetapocalpysebiome.PurifyGUIElementCustom;
import net.mcreator.sweetapocalpysebiome.client.gui.ldlib.LDLibGUIContext;
import net.mcreator.sweetapocalpysebiome.network.PurifyGUICraftPacket;

public class PurifyLDLibPurifyButtonClickProcedure {
	public static void execute(Player player) {
		// GUI button callbacks run on the client: only the client sends the request to the server,
		// and the server decides (after validating the open GUI) whether anything happens.
		if (player == null || !player.level().isClientSide()) {
			return;
		}

		// Use existing purification logic
		PurifyGUICraftPacket.send();
	}
}