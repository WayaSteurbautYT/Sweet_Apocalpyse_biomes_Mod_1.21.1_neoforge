package net.mcreator.sweetapocalpysebiome.system;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.sweetapocalpysebiome.SweetapocalpysebiomeMod;

public enum ApocalypseAbility {
	TONGUE_GRAB("tongue_grab", 60, 8.0D),
	DASH_JUMP("dash_jump", 40, 0.0D),
	ROOT_SNARE("root_snare", 80, 12.0D),
	WALL_CLIMB("wall_climb", 0, 0.0D),
	POLLINATION("pollination", 120, 0.0D),
	BOUNCE("bounce", 30, 0.0D),
	INFECTION("infection", 100, 6.0D),
	PLANT_HEAL("plant_heal", 0, 0.0D);

	private final ResourceLocation id;
	private final int cooldownTicks;
	private final double range;

	ApocalypseAbility(String path, int cooldownTicks, double range) {
		this.id = ResourceLocation.fromNamespaceAndPath(SweetapocalpysebiomeMod.MODID, path);
		this.cooldownTicks = cooldownTicks;
		this.range = range;
	}

	public ResourceLocation id() {
		return id;
	}

	public String path() {
		return id.getPath();
	}

	public int cooldownTicks() {
		return cooldownTicks;
	}

	public double range() {
		return range;
	}

	public static ApocalypseAbility fromPath(String path) {
		if (path == null || path.isEmpty()) {
			return null;
		}
		for (ApocalypseAbility ability : values()) {
			if (ability.path().equals(path)) {
				return ability;
			}
		}
		return null;
	}
}
