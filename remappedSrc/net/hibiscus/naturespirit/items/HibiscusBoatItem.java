package net.hibiscus.naturespirit.items;

import net.hibiscus.naturespirit.entity.HibiscusBoatEntity;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class HibiscusBoatItem extends BoatItem {
   private final HibiscusBoatEntity.HibiscusBoat boatData;
   private final boolean chest;

   public HibiscusBoatItem(boolean chest, HibiscusBoatEntity.HibiscusBoat boatData, Properties settings) {
      super(chest, Boat.Type.OAK, settings);
      this.chest = chest;
      this.boatData = boatData;
   }

   @Override protected Boat getBoat(Level world, HitResult hitResult) {
      var entity = boatData.factory(chest).create(boatData.entityType(chest), world);
      entity.absMoveTo(hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z);
      return entity;
   }
}
