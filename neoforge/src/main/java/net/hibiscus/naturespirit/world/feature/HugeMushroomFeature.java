package net.hibiscus.naturespirit.world.feature;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public abstract class HugeMushroomFeature extends Feature<HugeMushroomFeatureConfig> {

  public HugeMushroomFeature(Codec<HugeMushroomFeatureConfig> codec) {
    super(codec);
  }

  protected void generateStem(LevelAccessor world, RandomSource random, BlockPos pos, HugeMushroomFeatureConfig config, int height, BlockPos.MutableBlockPos mutablePos) {
    for (int i = 0; i < height; ++i) {
      mutablePos.set(pos).move(Direction.UP, i);
      if (!world.getBlockState(mutablePos).isSolidRender(world, mutablePos)) {
        this.setBlock(world, mutablePos, config.stemProvider.getState(random, pos));
      }
    }

  }

  protected int getHeight(RandomSource random, HugeMushroomFeatureConfig hugeMushroomFeatureConfig) {
    return random.nextIntBetweenInclusive(hugeMushroomFeatureConfig.firstRandomHeight, hugeMushroomFeatureConfig.secondRandomHeight) + hugeMushroomFeatureConfig.baseHeight;
  }

  protected boolean canGenerate(LevelAccessor world, BlockPos pos, int height, BlockPos.MutableBlockPos mutablePos, HugeMushroomFeatureConfig config) {
    int i = pos.getY();
    if (i >= world.getMinBuildHeight() + 1 && i + height + 1 < world.getMaxBuildHeight()) {
      BlockState blockState = world.getBlockState(pos.below());
      if (!isDirt(blockState) && !blockState.is(BlockTags.MUSHROOM_GROW_BLOCK)) {
        return false;
      } else {
        for (int j = 0; j <= height; ++j) {
          int k = this.getCapSize(-1, -1, config.foliageRadius, j);

          for (int l = -k; l <= k; ++l) {
            for (int m = -k; m <= k; ++m) {
              BlockState blockState2 = world.getBlockState(mutablePos.setWithOffset(pos, l, j, m));
              if (!blockState2.isAir() && !blockState2.is(BlockTags.LEAVES)) {
                return false;
              }
            }
          }
        }

        return true;
      }
    } else {
      return false;
    }
  }

  public boolean place(FeaturePlaceContext<HugeMushroomFeatureConfig> context) {
    WorldGenLevel structureWorldAccess = context.level();
    BlockPos blockPos = context.origin();
    RandomSource random = context.random();
    HugeMushroomFeatureConfig hugeMushroomFeatureConfig = context.config();
    int i = this.getHeight(random, hugeMushroomFeatureConfig);
    BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
    if (!this.canGenerate(structureWorldAccess, blockPos, i, mutable, hugeMushroomFeatureConfig)) {
      return false;
    } else {
      this.generateCap(structureWorldAccess, random, blockPos, i, mutable, hugeMushroomFeatureConfig);
      this.generateStem(structureWorldAccess, random, blockPos, hugeMushroomFeatureConfig, i, mutable);
      return true;
    }
  }

  protected abstract int getCapSize(int i, int j, int capSize, int y);

  protected abstract void generateCap(LevelAccessor world, RandomSource random, BlockPos start, int y, BlockPos.MutableBlockPos mutable, HugeMushroomFeatureConfig config);
}
