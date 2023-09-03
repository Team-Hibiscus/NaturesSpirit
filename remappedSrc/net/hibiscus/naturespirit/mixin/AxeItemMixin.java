package net.hibiscus.naturespirit.mixin;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Debug(export = true) @Mixin(AxeItem.class) public class AxeItemMixin {

   @Inject(method = "useOnBlock", at = @At("HEAD"))
   private void isValid(UseOnContext context, CallbackInfoReturnable <InteractionResult> cir) {
      InteractionResult result = UseBlockCallback.EVENT.invoker().interact(context.getPlayer(), context.getLevel(),context.getHand(), new BlockHitResult(context.getClickLocation(), context.getClickedFace(), context.getClickedPos(),
              context.isInside()));

      if(result == InteractionResult.SUCCESS) {
         cir.cancel();
      }
   }
}
