package net.hibiscus.naturespirit.mixin;

import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.config.NSConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilBlock.class)
public class AnvilBlockMixin {

  @Inject(method = "onLand", at = @At("HEAD"))
  private void onLanding(Level world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingBlockEntity fallingBlockEntity, CallbackInfo info) {
    if (NSConfig.deepslateGenerator) {
      if (world.getBlockState(pos.below()).is(Blocks.STONE) && world.getBlockState(pos.below(2)).is(Blocks.MAGMA_BLOCK)) {
        world.setBlock(
            pos.below(),
            Blocks.DEEPSLATE.defaultBlockState(),
            AnvilBlock.UPDATE_CLIENTS
        );
      }
    }
  }
}
