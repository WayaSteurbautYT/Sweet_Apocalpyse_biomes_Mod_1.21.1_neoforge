package net.mcreator.sweetapocalpysebiome;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.block.Block;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.BlockDropsEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = SweetapocalpysebiomeMod.MODID)
public final class SweetApocalypseEnchantmentEffects {
	private static final ResourceKey<Enchantment> CANDY_CRUSHER = enchantmentKey("candy_crusher");
	private static final ResourceKey<Enchantment> SWEET_AURA = enchantmentKey("sweet_aura");
	private static final ResourceKey<Enchantment> CHOCOLATE_FORTUNE = enchantmentKey("chocolate_fortune");
	private static final ResourceKey<Enchantment> DIABETIC_TOUCH = enchantmentKey("diabetic_touch");
	private static final TagKey<Block> CANDY_BLOCKS = BlockTags.create(id("candy_blocks"));
	private static final TagKey<Block> CHOCOLATE_BLOCKS = BlockTags.create(id("chocolate_blocks"));

	private SweetApocalypseEnchantmentEffects() {
	}

	@SubscribeEvent
	public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
		if (!event.getState().is(CANDY_BLOCKS)) {
			return;
		}
		Player player = event.getEntity();
		int level = getEnchantmentLevel(player.level().registryAccess(), player.getMainHandItem(), CANDY_CRUSHER);
		if (level > 0) {
			event.setNewSpeed(event.getNewSpeed() * (1.0F + 0.2F * level));
		}
	}

	@SubscribeEvent
	public static void onChocolateBlockDrops(BlockDropsEvent event) {
		if (!event.getState().is(CHOCOLATE_BLOCKS)) {
			return;
		}
		int level = getEnchantmentLevel(event.getLevel().registryAccess(), event.getTool(), CHOCOLATE_FORTUNE);
		if (level <= 0) {
			return;
		}
		for (ItemEntity drop : event.getDrops()) {
			ItemStack stack = drop.getItem();
			if (!stack.isEmpty()) {
				int bonus = event.getLevel().getRandom().nextInt(level + 1);
				stack.setCount(Math.min(stack.getMaxStackSize(), stack.getCount() * (bonus + 1)));
			}
		}
	}

	@SubscribeEvent
	public static void onLivingDamage(LivingDamageEvent.Pre event) {
		Entity source = event.getSource().getEntity();
		if (!(source instanceof LivingEntity attacker) || attacker == event.getEntity()) {
			return;
		}
		int level = getEnchantmentLevel(attacker.level().registryAccess(), attacker.getMainHandItem(), DIABETIC_TOUCH);
		if (level > 0) {
			int duration = 100 * level;
			event.getEntity().addEffect(new MobEffectInstance(MobEffects.HUNGER, duration, level - 1));
			event.getEntity().addEffect(new MobEffectInstance(MobEffects.WEAKNESS, duration, 0));
		}
	}

	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent.Post event) {
		Player player = event.getEntity();
		if (player.level().isClientSide()) {
			return;
		}
		int level = 0;
		for (ItemStack armor : player.getArmorSlots()) {
			level = Math.max(level, getEnchantmentLevel(player.level().registryAccess(), armor, SWEET_AURA));
		}
		if (level <= 0) {
			return;
		}
		MobEffectInstance currentLuck = player.getEffect(MobEffects.LUCK);
		if (currentLuck == null || (currentLuck.getAmplifier() < level - 1 && currentLuck.getDuration() <= 5)) {
			player.addEffect(new MobEffectInstance(MobEffects.LUCK, 40, level - 1, true, false, false));
		}
	}

	@SubscribeEvent
	public static void addEnchantmentDescriptions(ItemTooltipEvent event) {
		ItemStack stack = event.getItemStack();
		ItemEnchantments enchantments = stack.get(DataComponents.ENCHANTMENTS);
		if (enchantments == null) {
			return;
		}
		for (Holder<Enchantment> enchantment : enchantments.keySet()) {
			ResourceLocation id = enchantment.unwrapKey().map(ResourceKey::location).orElse(null);
			if (id == null || !SweetapocalpysebiomeMod.MODID.equals(id.getNamespace())) {
				continue;
			}
			String translationKey = "enchantment." + id.getNamespace() + "." + id.getPath() + ".desc";
			if (!Component.translatable(translationKey).getString().equals(translationKey)) {
				event.getToolTip().add(Component.translatable(translationKey).withStyle(ChatFormatting.GRAY));
			}
		}
	}

	private static int getEnchantmentLevel(RegistryAccess registries, ItemStack stack, ResourceKey<Enchantment> key) {
		if (stack.isEmpty()) {
			return 0;
		}
		return registries.lookupOrThrow(Registries.ENCHANTMENT).get(key)
				.map(enchantment -> EnchantmentHelper.getItemEnchantmentLevel(enchantment, stack))
				.orElse(0);
	}

	private static ResourceKey<Enchantment> enchantmentKey(String path) {
		return ResourceKey.create(Registries.ENCHANTMENT, id(path));
	}

	private static ResourceLocation id(String path) {
		return ResourceLocation.fromNamespaceAndPath(SweetapocalpysebiomeMod.MODID, path);
	}
}