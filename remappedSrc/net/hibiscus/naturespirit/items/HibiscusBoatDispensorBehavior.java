package net.hibiscus.naturespirit.items;

import net.hibiscus.naturespirit.entity.HibiscusBoatEntity;
import net.minecraft.block.dispenser.BoatDispenserBehavior;
import net.minecraft.entity.vehicle.BoatEntity;

public final class HibiscusBoatDispensorBehavior extends BoatDispenserBehavior {
   private final HibiscusBoatEntity.HibiscusBoat boatData;

   public HibiscusBoatDispensorBehavior(HibiscusBoatEntity.HibiscusBoat boatData, boolean chest) {
      super(BoatEntity.Type.OAK, chest);
      this.boatData = boatData;
   }

   public HibiscusBoatEntity.HibiscusBoat getBoatData() {
      return boatData;
   }
}