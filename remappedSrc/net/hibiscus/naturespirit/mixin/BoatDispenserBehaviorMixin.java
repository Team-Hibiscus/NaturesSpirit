package net.hibiscus.naturespirit.mixin;

import net.hibiscus.naturespirit.entity.HibiscusBoatEntity;
import net.hibiscus.naturespirit.items.HibiscusBoatDispensorBehavior;
import net.minecraft.core.dispenser.BoatDispenseItemBehavior;
import net.minecraft.world.entity.vehicle.Boat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(BoatDispenseItemBehavior.class)
abstract class BoatDispenserBehaviorMixin {
   @ModifyVariable(
           method = "dispenseSilently",
           at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/vehicle/BoatEntity;setVariant(Lnet/minecraft/entity/vehicle/BoatEntity$Type;)V"),
           allow = 1
   )
   private Boat modifyBoat(Boat original) {
      // noinspection ConstantConditions
      if ((Object) this instanceof HibiscusBoatDispensorBehavior boat) {
         return HibiscusBoatEntity.copy(original, boat.getBoatData());
      }

      return original;
   }
}