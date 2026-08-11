package net.hibiscus.naturespirit.world.feature;

import com.mojang.serialization.Codec;
import net.hibiscus.naturespirit.blocks.GrowingBranchingTrunkBlock;
import net.hibiscus.naturespirit.registration.NSBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

public class AlluaudiaFeature extends Feature<NoneFeatureConfiguration> {

  public AlluaudiaFeature(Codec<NoneFeatureConfiguration> codec) {
    super(codec);
  }

  public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
    WorldGenLevel structureWorldAccess = context.level();
    BlockPos blockPos = context.origin();
    RandomSource random = context.random();
    generate2(structureWorldAccess, blockPos, random, blockPos, 6, 0);
    return true;
  }

  private static boolean isSurroundedByAir(LevelReader world, BlockPos pos, @Nullable Direction exceptDirection) {
    Iterator var3 = Direction.Plane.HORIZONTAL.iterator();

    Direction direction;
    do {
      if (!var3.hasNext()) {
        return true;
      }

      direction = (Direction) var3.next();
    } while (direction == exceptDirection || world.isEmptyBlock(pos.relative(direction)));

    return false;
  }

  private static void generate2(LevelAccessor world, BlockPos pos, RandomSource random, BlockPos rootPos, int size, int layer) {
    GrowingBranchingTrunkBlock branchingTrunkBlock = NSBlocks.ALLUAUDIA.get();
    int i = random.nextInt(1) + 2;
    if (layer == 0) {
      ++i;
    }

    for (int j = 0; j < i; ++j) {
      BlockPos blockPos = pos.above(j + 1);
      if (layer > 0) {
        blockPos = pos.above(j == 0 ? 1 : (int) (j / .93));
      }
      if (!isSurroundedByAir(world, blockPos, Direction.getRandom(random))) {
        return;
      }
      world.setBlock(blockPos, branchingTrunkBlock.withConnectionProperties(world, blockPos), Block.UPDATE_CLIENTS);
      world.setBlock(blockPos.below(), branchingTrunkBlock.withConnectionProperties(world, blockPos.below()), Block.UPDATE_CLIENTS);
    }

    if (layer < 2) {
      int k = random.nextInt(2) + 4;
      if (layer == 0) {
        ++k;
      }

      for (int l = 0; l < k; ++l) {
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        int m = random.nextInt(2);
        int n = i - m == 0 ? 1 : i - m;
        BlockPos blockPos2 = pos.above(n).relative(direction, 1);
        if (Math.abs(blockPos2.getX() - rootPos.getX()) < size && Math.abs(blockPos2.getZ() - rootPos.getZ()) < size && world.isEmptyBlock(blockPos2) && world.isEmptyBlock(blockPos2.below())) {

          world.setBlock(blockPos2, branchingTrunkBlock.withHorizontalConnectingProperties(world, blockPos2), Block.UPDATE_CLIENTS);

          world.setBlock(blockPos2.relative(direction.getClockWise(), 1).above(),
              branchingTrunkBlock.withConnectionPropertiesVertical(world, blockPos2.relative(direction.getClockWise(), 1).above()), Block.UPDATE_CLIENTS);
          world.setBlock(blockPos2.relative(direction.getClockWise(), 1),
              branchingTrunkBlock.withConnectionPropertiesVertical(world, blockPos2.relative(direction.getClockWise(), 1)), Block.UPDATE_CLIENTS);
          world.setBlock(blockPos2.relative(direction.getClockWise(), 1).above(2),
              branchingTrunkBlock.withConnectionPropertiesVertical(world, blockPos2.relative(direction.getClockWise(), 1).above(2)), Block.UPDATE_CLIENTS);
          world.setBlock(blockPos2.relative(direction.getClockWise(), 1).above(),
              branchingTrunkBlock.withConnectionPropertiesVertical(world, blockPos2.relative(direction.getClockWise(), 1).above()), Block.UPDATE_CLIENTS);
          world.setBlock(blockPos2, branchingTrunkBlock.withConnectionProperties(world, blockPos2), Block.UPDATE_CLIENTS);
          world.setBlock(blockPos2.relative(direction.getOpposite()), branchingTrunkBlock.withConnectionProperties(world, blockPos2.relative(direction.getOpposite())),
              Block.UPDATE_CLIENTS);
          world.setBlock(blockPos2.relative(direction.getClockWise(), 1),
              branchingTrunkBlock.withConnectionProperties(world, blockPos2.relative(direction.getClockWise(), 1)), Block.UPDATE_CLIENTS);
          world.setBlock(blockPos2, branchingTrunkBlock.withHorizontalConnectingProperties(world, blockPos2), Block.UPDATE_CLIENTS);

          generate2(world, pos.above(n + 1), random, rootPos, size, layer + 1);
//               bl = false;
//               if(world.isAir(blockPos2.up())) {
//
//                  world.setBlockState(blockPos2.up(), branchingTrunkBlock.withConnectionProperties(world, blockPos2.up()), Block.NOTIFY_LISTENERS);
//                  world.setBlockState(blockPos2, branchingTrunkBlock.withConnectionProperties(world, blockPos2), Block.NOTIFY_LISTENERS);
//
//                  Direction direction2 = Direction.Type.HORIZONTAL.random(random);
//                  if(random.nextBoolean() && world.isAir(blockPos2.offset(direction2, 1))) {
//                     world.setBlockState(blockPos2.offset(direction2), branchingTrunkBlock.withConnectionProperties(world, blockPos2.offset(direction2)), Block.NOTIFY_LISTENERS);
//                     world.setBlockState(blockPos2.offset(direction), branchingTrunkBlock.withConnectionProperties(world, blockPos2.offset(direction)), Block.NOTIFY_LISTENERS);
//                  }
//               }
//
//               else if((world.isAir(blockPos2.up(2)) && !world.isAir(blockPos2.up()))) {
//
//                  world.setBlockState(blockPos2.up(1), branchingTrunkBlock.withConnectionProperties(world, blockPos2.up(1)), Block.NOTIFY_LISTENERS);
//
          if (random.nextBoolean()) {
            world.setBlock(blockPos2.relative(direction.getClockWise()).relative(direction).above(),
                branchingTrunkBlock.withConnectionProperties(world, blockPos2.relative(direction.getClockWise()).relative(direction).above()), Block.UPDATE_CLIENTS);
            world.setBlock(blockPos2.relative(direction.getClockWise(), 1).above(),
                branchingTrunkBlock.withConnectionProperties(world, blockPos2.relative(direction.getClockWise(), 1).above()), Block.UPDATE_CLIENTS);
          } else {
            world.setBlock(blockPos2.relative(direction.getClockWise(), 2).above(),
                branchingTrunkBlock.withConnectionProperties(world, blockPos2.relative(direction.getClockWise(), 2).above()), Block.UPDATE_CLIENTS);
            world.setBlock(blockPos2.relative(direction.getClockWise(), 1).above(),
                branchingTrunkBlock.withConnectionProperties(world, blockPos2.relative(direction.getClockWise(), 1).above()), Block.UPDATE_CLIENTS);
          }
//               }
        }
      }
    }
//      if(bl) {
//         world.setBlockState(pos.up(i - 2), branchingTrunkBlock.withConnectionProperties(world, pos.up(i - 2)), Block.NOTIFY_LISTENERS);
//
//         Direction direction = Direction.Type.HORIZONTAL.random(random);
//
//         if(random.nextBoolean() && world.isAir(pos.up(i - 1).offset(direction, 1))) {
//            world.setBlockState(pos.up(i - 1).offset(direction), branchingTrunkBlock.withConnectionProperties(world, pos.up(i - 1).offset(direction)), Block.NOTIFY_LISTENERS);
//            world.setBlockState(pos.up(i - 1), branchingTrunkBlock.withConnectionProperties(world, pos.up(i - 1)), Block.NOTIFY_LISTENERS);
//         }
//      }

  }
}
