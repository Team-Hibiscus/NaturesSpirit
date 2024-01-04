package net.hibiscus.naturespirit.items;

import net.hibiscus.naturespirit.entity.HibiscusBoatEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.BoatItem;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class HibiscusBoatItem extends BoatItem {
   private final HibiscusBoatEntity.HibiscusBoat boatData;
   private final boolean chest;

   public HibiscusBoatItem(boolean chest, HibiscusBoatEntity.HibiscusBoat boatData, Settings settings) {
      super(chest, BoatEntity.Type.OAK, settings);
      this.chest = chest;
      this.boatData = boatData;
   }

   @Override protected BoatEntity createEntity(World world, HitResult hitResult) {
      var entity = boatData.factory(chest).create(boatData.entityType(chest), world);
      entity.updatePosition(hitResult.getPos().x, hitResult.getPos().y, hitResult.getPos().z);
      return entity;
   }
}
