package net.hibiscus.naturespirit.mixin;

import net.hibiscus.naturespirit.entity.HibiscusBoatEntity;
import net.hibiscus.naturespirit.items.HibiscusBoatDispensorBehavior;
import net.minecraft.block.dispenser.BoatDispenserBehavior;
import net.minecraft.entity.vehicle.BoatEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(BoatDispenserBehavior.class) abstract class BoatDispenserBehaviorMixin {
   @ModifyVariable(method = "dispenseSilently", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/vehicle/BoatEntity;setVariant(Lnet/minecraft/entity/vehicle/BoatEntity$Type;)V"), allow = 1)
   private BoatEntity modifyBoat(BoatEntity original) {
      // noinspection ConstantConditions
      if((Object) this instanceof HibiscusBoatDispensorBehavior boat) {
         return HibiscusBoatEntity.copy(original, boat.getBoatData());
      }

      return original;
   }
}