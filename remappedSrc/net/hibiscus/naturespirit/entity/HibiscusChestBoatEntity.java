package net.hibiscus.naturespirit.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public final class HibiscusChestBoatEntity extends ChestBoat implements HibiscusBoatWithData {
   private final HibiscusBoatEntity.HibiscusBoat boatData;

   public HibiscusChestBoatEntity(EntityType <? extends Boat> entityType, Level world, HibiscusBoatEntity.HibiscusBoat boatData) {
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

   @Override public Item getDropItem() {
      return boatData.chestBoat().asItem();
   }
}