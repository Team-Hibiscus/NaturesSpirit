package net.hibiscus.naturespirit.items;

import net.hibiscus.naturespirit.entity.HibiscusBoatEntity;
import net.minecraft.core.dispenser.BoatDispenseItemBehavior;
import net.minecraft.world.entity.vehicle.Boat;

public final class HibiscusBoatDispensorBehavior extends BoatDispenseItemBehavior {
   private final HibiscusBoatEntity.HibiscusBoat boatData;

   public HibiscusBoatDispensorBehavior(HibiscusBoatEntity.HibiscusBoat boatData, boolean chest) {
      super(Boat.Type.OAK, chest);
      this.boatData = boatData;
   }

   public HibiscusBoatEntity.HibiscusBoat getBoatData() {
      return boatData;
   }
}