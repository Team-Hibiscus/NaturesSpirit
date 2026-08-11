package net.hibiscus.naturespirit.world.feature;

import com.mojang.serialization.Codec;
import net.hibiscus.naturespirit.registration.NSBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class PolyporeFeature extends Feature<NoneFeatureConfiguration> {

  public PolyporeFeature(Codec<NoneFeatureConfiguration> codec) {
    super(codec);
  }

  public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
    WorldGenLevel world = context.level();
    BlockPos pos = context.origin();
    RandomSource random = context.random();
    for (Direction direction : Direction.Plane.HORIZONTAL.shuffledCopy(random)) {
      BlockPos pos2 = pos.relative(direction);
      if (!world.isEmptyBlock(pos2)) {
        Direction direction2 = direction.getClockWise();
        Direction direction3 = direction.getCounterClockWise();
        int radius = random.nextIntBetweenInclusive(1, 3);
        if (world.isEmptyBlock(pos2.relative(direction2)) && world.isEmptyBlock(pos.relative(direction2))) {
          generateSquare(world, pos2, radius, direction.getOpposite(), direction2);
          return true;
        } else if (world.isEmptyBlock(pos2.relative(direction3)) && world.isEmptyBlock(pos.relative(direction3))) {
          generateSquare(world, pos2, radius, direction.getOpposite(), direction3);
          return true;
        }
      }
    }
    return false;
  }

  protected static void generateSquare(LevelAccessor world, BlockPos cornerPos, int radius, Direction direction1, Direction direction2) {
    BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(cornerPos);
    BlockPos blockPos;
    for (int j = 0; j <= radius; ++j) {
      for (int k = 0; k <= radius; ++k) {
        blockPos = mutable.relative(direction2, k).relative(direction1, j);
        if (world.isEmptyBlock(blockPos) || world.getBlockState(blockPos).is(NSBlocks.GRAY_POLYPORE)) {
          world.setBlock(blockPos, NSBlocks.GRAY_POLYPORE_BLOCK.get().defaultBlockState().setValue(HugeMushroomBlock.DOWN, false), 2);
        }
      }
    }

  }
}
