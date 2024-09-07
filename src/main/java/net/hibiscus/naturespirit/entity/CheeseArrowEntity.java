package net.hibiscus.naturespirit.entity;

import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.registration.HibiscusEntityTypes;
import net.hibiscus.naturespirit.registration.HibiscusMiscBlocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public class CheeseArrowEntity extends PersistentProjectileEntity {

   public CheeseArrowEntity(EntityType<? extends CheeseArrowEntity> entityType, World world) {
      super(entityType, world);
   }

   public CheeseArrowEntity(World world, LivingEntity owner) {
      super(HibiscusEntityTypes.CHEESE_ARROW, owner, world);
   }

   public CheeseArrowEntity(World world, double x, double y, double z) {
      super(HibiscusEntityTypes.CHEESE_ARROW, x, y, z, world);
   }

   public void tick() {
      super.tick();
      if (this.getWorld().isClient && !this.inGround) {
         this.getWorld().addParticle(NatureSpirit.MILK_PARTICLE, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
      }

   }

   protected ItemStack asItemStack() {
      return new ItemStack(HibiscusMiscBlocks.CHEESE_ARROW);
   }

   protected void onHit(LivingEntity target) {
      super.onHit(target);
      target.clearStatusEffects();
   }

   public void readCustomDataFromNbt(NbtCompound nbt) {
      super.readCustomDataFromNbt(nbt);
   }

   public void writeCustomDataToNbt(NbtCompound nbt) {
      super.writeCustomDataToNbt(nbt);
   }
}