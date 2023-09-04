package net.hibiscus.naturespirit.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.ChestBoatEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public final class HibiscusChestBoatEntity extends ChestBoatEntity implements HibiscusBoatWithData {
   private final HibiscusBoatEntity.HibiscusBoat boatData;

   public HibiscusChestBoatEntity(EntityType <? extends BoatEntity> entityType, World world, HibiscusBoatEntity.HibiscusBoat boatData) {
      super(entityType, world);
      this.boatData = boatData;
   }

   @Override public HibiscusBoatEntity.HibiscusBoat getBoatData() {
      return boatData;
   }

   @Override public Type getVariant() {
      return Type.OAK;
   }

   @Override public void setVariant(Type type) {
   }

   @Override public Item asItem() {
      return boatData.chestBoat().asItem();
   }
}