package net.hibiscus.naturespirit.mixin;

import net.hibiscus.naturespirit.registration.HibiscusBlocksAndItems;
import net.minecraft.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CoralBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Optional;

@Mixin(CoralBlock.class) @Debug(export = true) public abstract class CoralBlockBlockMixin extends Block {
   public CoralBlockBlockMixin(Properties settings) {
      super(settings);
   }

   private static Optional <BlockPos> findColumnEnd(BlockGetter world, BlockPos pos, TagKey <Block> intermediateBlocks, Direction direction, Block endBlock, int searchLimit) {
      BlockPos.MutableBlockPos mutable = pos.mutable();

      BlockState blockState;
      int i = 0;
      do {
         mutable.move(direction);
         blockState = world.getBlockState(mutable);
         ++i;
      } while(blockState.is(intermediateBlocks) && i < searchLimit);

      return blockState.is(endBlock) ? Optional.of(mutable) : Optional.empty();
   }

   @Override public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
      if(findColumnEnd(world, pos, BlockTags.CORAL_BLOCKS, Direction.DOWN, Blocks.BUBBLE_COLUMN, 10).isPresent()) {
         for(Direction direction : Direction.Plane.HORIZONTAL) {
            if(random.nextInt(25) == 0) {
               if(world.getBlockState(pos.relative(direction, 1)).is(Blocks.WATER)) {
                  world.setBlock(pos.relative(direction, 1),
                          HibiscusBlocksAndItems.SMALL_CALCITE_BUD.defaultBlockState().setValue(AmethystClusterBlock.FACING, direction).setValue(AmethystClusterBlock.WATERLOGGED, true),
                          2
                  );
               }
               else if(world.getBlockState(pos.relative(direction, 1)).is(HibiscusBlocksAndItems.SMALL_CALCITE_BUD)) {
                  world.setBlock(pos.relative(direction, 1),
                          HibiscusBlocksAndItems.LARGE_CALCITE_BUD.defaultBlockState().setValue(AmethystClusterBlock.FACING, direction).setValue(AmethystClusterBlock.WATERLOGGED, world.getBlockState(pos.relative(direction,
                                  1
                          )).getFluidState().is(Fluids.WATER)),
                          2
                  );
               }
               else if(world.getBlockState(pos.relative(direction, 1)).is(HibiscusBlocksAndItems.LARGE_CALCITE_BUD)) {
                  world.setBlock(pos.relative(direction, 1),
                          HibiscusBlocksAndItems.CALCITE_CLUSTER.defaultBlockState().setValue(AmethystClusterBlock.FACING, direction).setValue(AmethystClusterBlock.WATERLOGGED, world.getBlockState(pos.relative(direction,
                                  1
                          )).getFluidState().is(Fluids.WATER)),
                          2
                  );
               }
            }
         }
      }

   }

   public boolean isRandomlyTicking(BlockState state) {
      return true;
   }

}
