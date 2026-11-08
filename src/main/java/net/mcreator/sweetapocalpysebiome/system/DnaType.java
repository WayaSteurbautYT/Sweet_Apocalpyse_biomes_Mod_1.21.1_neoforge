package net.mcreator.sweetapocalpysebiome.system;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.sweetapocalpysebiome.SweetapocalpysebiomeMod;

public enum DnaType {
	FROG("frog", ApocalypseAbility.TONGUE_GRAB),
	RABBIT("rabbit", ApocalypseAbility.DASH_JUMP),
	PLANT("plant", ApocalypseAbility.ROOT_SNARE),
	SPIDER("spider", ApocalypseAbility.WALL_CLIMB),
	BEE("bee", ApocalypseAbility.POLLINATION),
	SLIME("slime", ApocalypseAbility.BOUNCE),
	ZOMBIE("zombie", ApocalypseAbility.INFECTION),
	BLOOM("bloom", ApocalypseAbility.PLANT_HEAL);

	private final ResourceLocation id;
	private final ApocalypseAbility ability;

	DnaType(String path, ApocalypseAbility ability) {
		this.id = ResourceLocation.fromNamespaceAndPath(SweetapocalpysebiomeMod.MODID, path);
		this.ability = ability;
	}

	public ResourceLocation id() {
		return id;
	}

	public String path() {
		return id.getPath();
	}

	public ApocalypseAbility ability() {
		return ability;
	}

	public Component displayName() {
		return Component.translatable("dna.sweetapocalpysebiome." + path());
	}

	public Component abilityName() {
		if (ability == null) {
			return Component.literal("-");
		}
		return Component.translatable("ability.sweetapocalpysebiome." + ability.path());
	}

	public static DnaType fromPath(String path) {
		if (path == null || path.isEmpty()) {
			return null;
		}
		for (DnaType type : values()) {
			if (type.path().equals(path)) {
				return type;
			}
		}
		return null;
	}
}
