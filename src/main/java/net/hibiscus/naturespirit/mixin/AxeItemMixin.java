package net.hibiscus.naturespirit.mixin;

import com.google.common.collect.ImmutableMap;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.hibiscus.naturespirit.blocks.HibiscusBlocks;
import net.hibiscus.naturespirit.blocks.JoshuaTrunkBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Debug(export = true) @Mixin(AxeItem.class) public class AxeItemMixin {

   @Inject(method = "useOnBlock", at = @At("HEAD"))
   private void isValid(ItemUsageContext context, CallbackInfoReturnable <ActionResult> cir) {
      ActionResult result = UseBlockCallback.EVENT.invoker().interact(context.getPlayer(), context.getWorld(),context.getHand(), new BlockHitResult(context.getHitPos(), context.getSide(), context.getBlockPos(),
              context.hitsInsideBlock()));

      if(result == ActionResult.SUCCESS) {
         cir.cancel();
      }
   }
}
