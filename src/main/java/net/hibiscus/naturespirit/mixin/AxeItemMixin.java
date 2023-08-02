package net.hibiscus.naturespirit.mixin;

import com.google.common.collect.ImmutableMap;
import net.hibiscus.naturespirit.blocks.HibiscusBlocks;
import net.hibiscus.naturespirit.blocks.JoshuaTrunkBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.AxeItem;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Debug(export = true) @Mixin(AxeItem.class) public class AxeItemMixin {
   private static ImmutableMap<Block, Block> STRIPPED_JOSHUA_LOGS = (new ImmutableMap.Builder()).put(
           HibiscusBlocks.JOSHUA_LOG,
           HibiscusBlocks.STRIPPED_JOSHUA_LOG
   ).build();

   @Inject(method = "getStrippedState", at = @At("HEAD"), cancellable = true)
   private void isValid(BlockState state, CallbackInfoReturnable <Optional <BlockState>> cir) {
      if(state.isOf(HibiscusBlocks.JOSHUA_LOG)) {
         cir.setReturnValue(Optional.ofNullable((Block) STRIPPED_JOSHUA_LOGS.get(state.getBlock()))
                 .map((block) -> (BlockState) block.getDefaultState()
                         .with(JoshuaTrunkBlock.DOWN, state.get(JoshuaTrunkBlock.DOWN))
                         .with(JoshuaTrunkBlock.UP, state.get(JoshuaTrunkBlock.UP))
                         .with(JoshuaTrunkBlock.NORTH, state.get(JoshuaTrunkBlock.NORTH))
                         .with(JoshuaTrunkBlock.SOUTH, state.get(JoshuaTrunkBlock.SOUTH))
                         .with(JoshuaTrunkBlock.EAST, state.get(JoshuaTrunkBlock.EAST))
                         .with(JoshuaTrunkBlock.WEST, state.get(JoshuaTrunkBlock.WEST))
                         .with(JoshuaTrunkBlock.WATERLOGGED, state.get(JoshuaTrunkBlock.WATERLOGGED))));
      }
   }
}
