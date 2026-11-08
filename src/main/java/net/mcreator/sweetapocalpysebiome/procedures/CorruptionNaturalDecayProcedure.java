package net.mcreator.sweetapocalpysebiome.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.sweetapocalpysebiome.network.SweetapocalpysebiomeModVariables;

public class CorruptionNaturalDecayProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity.getData(SweetapocalpysebiomeModVariables.PLAYER_VARIABLES).CorruptionTimer > 0) {
			if (entity.getData(SweetapocalpysebiomeModVariables.PLAYER_VARIABLES).CorruptionTimer >= 1200) {
				{
					SweetapocalpysebiomeModVariables.PlayerVariables _vars = entity.getData(SweetapocalpysebiomeModVariables.PLAYER_VARIABLES);
					_vars.CorruptionTimer = 0;
					_vars.CorruptionTimer = -1;
					_vars.markSyncDirty();
				}
			} else {
				{
					SweetapocalpysebiomeModVariables.PlayerVariables _vars = entity.getData(SweetapocalpysebiomeModVariables.PLAYER_VARIABLES);
					_vars.CorruptionTimer = 1;
					_vars.markSyncDirty();
				}
			}
		}
	}
}