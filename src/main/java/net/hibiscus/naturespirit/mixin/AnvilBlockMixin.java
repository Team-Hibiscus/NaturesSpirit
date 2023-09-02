package net.hibiscus.naturespirit.mixin;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AnvilBlock.class)
public class AnvilBlockMixin {

   @Inject(method = "onLanding", at = @At("HEAD"))
   private void onLanding(World world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingBlockEntity fallingBlockEntity, CallbackInfo ci) {
      if (world.getBlockState(pos.down()).isOf(Blocks.STONE) && world.getBlockState(pos.down(2)).isOf(Blocks.MAGMA_BLOCK)) world.setBlockState(pos.down(), Blocks.DEEPSLATE.getDefaultState(), 2);
   }
}
