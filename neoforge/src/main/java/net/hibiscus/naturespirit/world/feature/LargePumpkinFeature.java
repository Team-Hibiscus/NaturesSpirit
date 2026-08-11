package net.hibiscus.naturespirit.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.BlockPileConfiguration;

public class LargePumpkinFeature extends Feature<BlockPileConfiguration> {

  public LargePumpkinFeature(Codec<BlockPileConfiguration> configCodec) {
    super(configCodec);
  }

  @Override
  public boolean place(FeaturePlaceContext<BlockPileConfiguration> context) {
    WorldGenLevel worldAccess = context.level();
    BlockPileConfiguration blockPileFeatureConfig = context.config();
    BlockPos origin = context.origin();
    Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(worldAccess.getRandom());
    Direction direction2 = direction.getClockWise();
    BlockState blockState = blockPileFeatureConfig.stateProvider.getState(context.random(), origin);
    if (worldAccess.isEmptyBlock(origin.above()) && worldAccess.isEmptyBlock(origin.relative(direction).above()) && worldAccess.isEmptyBlock(origin.relative(direction2).above()) && worldAccess.isEmptyBlock(
        origin.relative(direction2).relative(direction).above())) {

      worldAccess.setBlock(origin, blockState, 1);
      worldAccess.setBlock(origin.relative(direction), blockState, 1);
      worldAccess.setBlock(origin.relative(direction2), blockState, 1);
      worldAccess.setBlock(origin.relative(direction2).relative(direction), blockState, 1);

      if (worldAccess.isEmptyBlock(origin.below()) || worldAccess.getBlockState(origin.below()).is(BlockTags.DIRT)) {
        worldAccess.setBlock(origin.below(), blockState, 1);
        worldAccess.setBlock(origin.below().relative(direction), blockState, 1);
        worldAccess.setBlock(origin.below().relative(direction2), blockState, 1);
        worldAccess.setBlock(origin.below().relative(direction2).relative(direction), blockState, 1);
      }

      if (context.random().nextBoolean()) {
        worldAccess.setBlock(origin.above(), blockState, 1);
      }
      if (context.random().nextBoolean()) {
        worldAccess.setBlock(origin.relative(direction).above(), blockState, 1);
      }
      if (context.random().nextBoolean()) {
        worldAccess.setBlock(origin.relative(direction2).above(), blockState, 1);
      }
      if (context.random().nextBoolean()) {
        worldAccess.setBlock(origin.relative(direction2).relative(direction).above(), blockState, 1);
      }

      return true;
    }
    return false;
  }
}
