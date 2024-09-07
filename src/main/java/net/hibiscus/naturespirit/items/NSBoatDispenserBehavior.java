package net.hibiscus.naturespirit.items;

import net.hibiscus.naturespirit.entity.NSBoatEntity;
import net.minecraft.block.dispenser.BoatDispenserBehavior;
import net.minecraft.entity.vehicle.BoatEntity;

public final class NSBoatDispenserBehavior extends BoatDispenserBehavior {
   private final NSBoatEntity.HibiscusBoat boatData;

   public NSBoatDispenserBehavior(NSBoatEntity.HibiscusBoat boatData, boolean chest) {
      super(BoatEntity.Type.OAK, chest);
      this.boatData = boatData;
   }

   public NSBoatEntity.HibiscusBoat getBoatData() {
      return boatData;
   }
}