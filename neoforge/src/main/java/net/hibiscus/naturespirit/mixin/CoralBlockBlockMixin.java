package net.hibiscus.naturespirit.mixin;

import net.hibiscus.naturespirit.config.NSConfig;
import net.hibiscus.naturespirit.registration.NSBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CoralBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.Optional;

@Mixin(CoralBlock.class)
public abstract class CoralBlockBlockMixin extends Block {

  public CoralBlockBlockMixin(Properties settings) {
    super(settings);
  }

  @Unique
  private static Optional<BlockPos> naturespirit$findColumnEnd(BlockGetter world, BlockPos pos) {
    BlockPos.MutableBlockPos mutable = pos.mutable();

    BlockState blockState;
    int i = 0;
    do {
      mutable.move(Direction.DOWN);
      blockState = world.getBlockState(mutable);
      ++i;
    } while (blockState.is(BlockTags.CORAL_BLOCKS) && i < 10);

    return blockState.is(Blocks.BUBBLE_COLUMN) ? Optional.of(mutable) : Optional.empty();
  }

  @Override
  public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
    if (NSConfig.calciteGenerator) {
      if (naturespirit$findColumnEnd(world, pos).isPresent()) {
        for (Direction direction : Direction.Plane.HORIZONTAL) {
          if (random.nextInt(25) == 0) {
            if (world.getBlockState(pos.relative(direction, 1)).is(Blocks.WATER)) {
              world.setBlock(pos.relative(direction, 1),
                  NSBlocks.SMALL_CALCITE_BUD.get().defaultBlockState().setValue(AmethystClusterBlock.FACING, direction).setValue(AmethystClusterBlock.WATERLOGGED, true),
                  Block.UPDATE_CLIENTS
              );
            } else if (world.getBlockState(pos.relative(direction, 1)).is(NSBlocks.SMALL_CALCITE_BUD.get())) {
              world.setBlock(pos.relative(direction, 1),
                  NSBlocks.LARGE_CALCITE_BUD.get().defaultBlockState().setValue(AmethystClusterBlock.FACING, direction)
                      .setValue(AmethystClusterBlock.WATERLOGGED, world.getBlockState(pos.relative(direction,
                          1
                      )).getFluidState().is(Fluids.WATER)),
                  Block.UPDATE_CLIENTS
              );
            } else if (world.getBlockState(pos.relative(direction, 1)).is(NSBlocks.LARGE_CALCITE_BUD.get())) {
              world.setBlock(pos.relative(direction, 1),
                  NSBlocks.CALCITE_CLUSTER.get().defaultBlockState().setValue(AmethystClusterBlock.FACING, direction)
                      .setValue(AmethystClusterBlock.WATERLOGGED, world.getBlockState(pos.relative(direction,
                          1
                      )).getFluidState().is(Fluids.WATER)),
                  Block.UPDATE_CLIENTS
              );
            }
          }
        }
      }
    }

  }

  @Override
  public boolean isRandomlyTicking(BlockState state) {
    return true;
  }
}
