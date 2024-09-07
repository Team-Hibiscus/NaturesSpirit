package net.hibiscus.naturespirit.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.ChestBoatEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public final class NSChestBoatEntity extends ChestBoatEntity implements NSBoatWithData {
   private final NSBoatEntity.HibiscusBoat boatData;

   public NSChestBoatEntity(EntityType <? extends BoatEntity> entityType, World world, NSBoatEntity.HibiscusBoat boatData) {
      super(entityType, world);
      this.boatData = boatData;
   }

   @Override public NSBoatEntity.HibiscusBoat getBoatData() {
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