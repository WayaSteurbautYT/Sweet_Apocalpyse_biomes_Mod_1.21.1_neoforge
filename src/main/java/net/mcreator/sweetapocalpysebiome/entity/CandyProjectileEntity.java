package net.mcreator.sweetapocalpysebiome.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

public class CandyProjectileEntity extends Snowball {
	public CandyProjectileEntity(EntityType<? extends CandyProjectileEntity> entityType, Level level) {
		super(entityType, level);
	}

	public CandyProjectileEntity(Level level, LivingEntity owner, Item item) {
		super(EntityType.SNOWBALL, level);
		this.setOwner(owner);
		this.setItem(new ItemStack(item));
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		super.onHitEntity(result);
		if (result.getEntity() instanceof LivingEntity target) {
			target.addEffect(new net.minecraft.world.effect.MobEffectInstance(
				net.minecraft.world.effect.MobEffects.MOVEMENT_SLOWDOWN, 100, 1));
			target.addEffect(new net.minecraft.world.effect.MobEffectInstance(
				net.minecraft.world.effect.MobEffects.POISON, 60, 0));
		}
		this.discard();
	}

	@Override
	protected void onHit(HitResult result) {
		super.onHit(result);
		if (!this.level().isClientSide()) {
			this.level().playSound(null, this.getX(), this.getY(), this.getZ(), 
				SoundEvents.SLIME_BLOCK_PLACE, SoundSource.NEUTRAL, 0.5F, 1.0F);
			
			// Create particle effect
			for (int i = 0; i < 8; ++i) {
				this.level().addParticle(ParticleTypes.ITEM_SNOWBALL, 
					this.getX(), this.getY(), this.getZ(), 
					0.0D, 0.0D, 0.0D);
			}
		}
		this.discard();
	}
}
