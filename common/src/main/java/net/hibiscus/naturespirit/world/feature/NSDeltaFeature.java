package net.hibiscus.naturespirit.world.feature;


import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.hibiscus.naturespirit.registration.NSBlocks;
import net.hibiscus.naturespirit.registration.NSBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.DeltaFeatureConfiguration;
import net.minecraft.world.level.material.Fluids;
import java.util.Iterator;

public class NSDeltaFeature extends Feature<DeltaFeatureConfiguration> {

  private static final ImmutableList<Block> CANNOT_REPLACE;
  private static final ImmutableList<Block> CAN_REPLACE;
  private static final Direction[] DIRECTIONS;
  private static final double RIM_SPAWN_CHANCE = 0.9D;

  static {
    CANNOT_REPLACE = ImmutableList.of(
        Blocks.BEDROCK,
        Blocks.NETHER_BRICKS,
        Blocks.NETHER_BRICK_FENCE,
        Blocks.NETHER_BRICK_STAIRS,
        Blocks.NETHER_WART,
        Blocks.CHEST,
        Blocks.SPAWNER,
        NSBlocks.WISTERIA.getBlueLeaves().get(),
        NSBlocks.WISTERIA.getPurpleLeaves().get(),
        NSBlocks.WISTERIA.getWhiteLeaves().get(),
        NSBlocks.WISTERIA.getPinkLeaves().get(),
        NSBlocks.WILLOW.getLeaves().get()
    );
    CAN_REPLACE = ImmutableList.of(Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.GRASS_BLOCK, Blocks.MUD, Blocks.SAND, NSBlocks.PINK_SAND.get());
    DIRECTIONS = Direction.values();
  }

  public NSDeltaFeature(Codec<DeltaFeatureConfiguration> codec) {
    super(codec);
  }

  private static boolean isClear(LevelAccessor level, BlockPos pos, DeltaFeatureConfiguration config) {
    BlockState blockState = level.getBlockState(pos);
    if (blockState.is(config.contents().getBlock())) {
      return false;
    } else if (CANNOT_REPLACE.contains(blockState.getBlock())) {
      return false;
    } else if (CAN_REPLACE.contains(blockState.getBlock()) && !blockState.is(config.contents().getBlock())) {

      for (Direction direction : DIRECTIONS) {
        boolean bl = level.getBlockState(pos.relative(direction)).isAir();
        boolean bl2 = !level.getFluidState(pos.relative(direction).below()).is(FluidTags.WATER);
        if ((bl && direction != Direction.UP && (bl2 || level.getRandom().nextFloat() < .7)) || !bl && direction == Direction.UP) {
          return false;
        }
      }

      return true;
    } else {
      return false;
    }
  }

  public boolean place(FeaturePlaceContext<DeltaFeatureConfiguration> context) {
    boolean bl = false;
    RandomSource randomSource = context.random();
    WorldGenLevel worldGenLevel = context.level();
    DeltaFeatureConfiguration deltaFeatureConfiguration = context.config();
    BlockPos blockPos = context.origin();
    boolean bl2 = randomSource.nextDouble() < 0.9D;
    int i = bl2 ? deltaFeatureConfiguration.rimSize().sample(randomSource) : 0;
    int j = bl2 ? deltaFeatureConfiguration.rimSize().sample(randomSource) : 0;
    boolean bl3 = bl2 && i != 0 && j != 0;
    int k = deltaFeatureConfiguration.size().sample(randomSource);
    int l = deltaFeatureConfiguration.size().sample(randomSource);
    int m = Math.max(k, l);
    Iterator var14 = BlockPos.withinManhattan(blockPos, k, 0, l).iterator();

    while (var14.hasNext()) {
      BlockPos blockPos2 = (BlockPos) var14.next();
      if (blockPos2.distManhattan(blockPos) > m) {
        break;
      }

      if (isClear(worldGenLevel, blockPos2, deltaFeatureConfiguration)) {
        if (bl3) {
          bl = true;
          worldGenLevel.setBlock(blockPos2, deltaFeatureConfiguration.rim(), 3);
        }

        BlockPos blockPos3 = blockPos2.offset(i, 0, j);
        if (isClear(worldGenLevel, blockPos3, deltaFeatureConfiguration)) {
          bl = true;
          worldGenLevel.setBlock(blockPos3, deltaFeatureConfiguration.contents(), 3);
          worldGenLevel.scheduleTick(blockPos3, Fluids.WATER, 1);
        }
      }
    }

    return bl;
  }
}
