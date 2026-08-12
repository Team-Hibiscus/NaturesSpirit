package net.hibiscus.naturespirit.world.feature;

import com.mojang.serialization.Codec;
import net.hibiscus.naturespirit.blocks.BranchingTrunkBlock;
import net.hibiscus.naturespirit.registration.NSBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

public class JoshuaTreeFeature extends Feature<NoneFeatureConfiguration> {

  public JoshuaTreeFeature(Codec<NoneFeatureConfiguration> codec) {
    super(codec);
  }

  public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
    WorldGenLevel structureWorldAccess = context.level();
    BlockPos blockPos = context.origin();
    RandomSource random = context.random();
    return generate2(structureWorldAccess, blockPos, random, 0);
  }

  private static boolean isSurroundedByAir(LevelReader world, BlockPos pos, @Nullable Direction exceptDirection) {
    Iterator<Direction> var3 = Direction.Plane.HORIZONTAL.iterator();

    Direction direction;
    do {
      if (!var3.hasNext()) {
        return true;
      }

      direction = var3.next();
    } while (direction == exceptDirection || world.isEmptyBlock(pos.relative(direction)));

    return false;
  }

  private static boolean generate2(LevelAccessor world, BlockPos pos, RandomSource random, int layer) {
    BranchingTrunkBlock branchingTrunkBlock = (BranchingTrunkBlock) NSBlocks.JOSHUA.getLog().get();
    int i = random.nextIntBetweenInclusive(1, 3);
    if (layer == 0) i += 2;


    for (int j = 0; j < i; ++j) {
      BlockPos blockPos = pos.above(j + 1);
      if (!isSurroundedByAir(world, blockPos, Direction.DOWN)) return false;
      world.setBlock(blockPos, branchingTrunkBlock.withConnectionProperties(world, blockPos), 2);
      world.setBlock(blockPos.below(), branchingTrunkBlock.withConnectionProperties(world, blockPos.below()), 2);
      world.setBlock(blockPos, branchingTrunkBlock.withConnectionProperties(world, blockPos), 2);
      if (layer > 0) {
        world.setBlock(blockPos.below(2), branchingTrunkBlock.withConnectionProperties(world, blockPos.below(2)), 2);
      }
    }

    if (layer < 2) {
      int k = random.nextIntBetweenInclusive(3, 5);
      if (layer == 0) ++k;

      for (int l = 0; l < k; ++l) {

        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        int m = random.nextIntBetweenInclusive(1, 2);
        int n = Math.max(1, i - m);
        BlockPos blockPos2 = pos.above(n).relative(direction, m);

        if (world.isEmptyBlock(blockPos2) && isSurroundedByAir(world, blockPos2, direction.getOpposite())) {

          world.setBlock(blockPos2, branchingTrunkBlock.withConnectionProperties(world, blockPos2), 2);
          world.setBlock(blockPos2.relative(direction.getOpposite()), branchingTrunkBlock.withConnectionProperties(world, blockPos2.relative(direction.getOpposite())), 2);
          for (int p = m; p > 0; --p) {
            world.setBlock(blockPos2.relative(direction.getOpposite(), p), branchingTrunkBlock.withConnectionProperties(world, blockPos2.relative(direction.getOpposite(), p)), 2);
          }

          generate2(world, blockPos2.above(), random, layer + 1);

          if (world.isEmptyBlock(blockPos2.above())) {

            world.setBlock(blockPos2.above(), NSBlocks.JOSHUA.getLeaves().get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
            world.setBlock(blockPos2, ((BranchingTrunkBlock) NSBlocks.JOSHUA.getLog().get()).withConnectionProperties(world, blockPos2), 2);

            Direction direction2 = Direction.Plane.HORIZONTAL.getRandomDirection(random);
            if (random.nextFloat() < .65F && world.isEmptyBlock(blockPos2.relative(direction, 1))) {
              world.setBlock(blockPos2.relative(direction2, 1), NSBlocks.JOSHUA.getLeaves().get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
              world.setBlock(blockPos2, ((BranchingTrunkBlock) NSBlocks.JOSHUA.getLog().get()).withConnectionProperties(world, blockPos2), 2);
            }

          }
          else if (world.isEmptyBlock(blockPos2.above(2)) && !world.isEmptyBlock(blockPos2.above())) {

            world.setBlock(blockPos2.above(2), NSBlocks.JOSHUA.getLeaves().get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
            world.setBlock(blockPos2.above(1), ((BranchingTrunkBlock) NSBlocks.JOSHUA.getLog().get()).withConnectionProperties(world, blockPos2.above()), 2);

            Direction direction2 = Direction.Plane.HORIZONTAL.getRandomDirection(random);
            if (random.nextFloat() < .65F && world.isEmptyBlock(blockPos2.above().relative(direction, 1))) {
              world.setBlock(blockPos2.above().relative(direction2, 1), NSBlocks.JOSHUA.getLeaves().get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
              world.setBlock(blockPos2.above(), ((BranchingTrunkBlock) NSBlocks.JOSHUA.getLog().get()).withConnectionProperties(world, blockPos2.above()), 2);
            }

          }
        }
      }
      return true;
    }
    world.setBlock(pos.above(i), NSBlocks.JOSHUA.getLeaves().get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
    world.setBlock(pos.above(i - 1), ((BranchingTrunkBlock) NSBlocks.JOSHUA.getLog().get()).withConnectionProperties(world, pos.above(i - 1)), 2);
    Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
    if (world.isEmptyBlock(pos.above(i - 1).relative(direction, 1))) {
      world.setBlock(pos.above(i - 1).relative(direction, 1), NSBlocks.JOSHUA.getLeaves().get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
      world.setBlock(pos.above(i - 1), ((BranchingTrunkBlock) NSBlocks.JOSHUA.getLog().get()).withConnectionProperties(world, pos.above(i - 1)), 2);
    }
    return true;
  }
}
