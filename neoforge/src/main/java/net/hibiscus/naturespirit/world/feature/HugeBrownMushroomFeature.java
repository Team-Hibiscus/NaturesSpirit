package net.hibiscus.naturespirit.world.feature;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;

public class HugeBrownMushroomFeature extends HugeMushroomFeature {

  public HugeBrownMushroomFeature(Codec<HugeMushroomFeatureConfig> codec) {
    super(codec);
  }

  protected void generateCap(LevelAccessor world, RandomSource random, BlockPos start, int y, BlockPos.MutableBlockPos mutable, HugeMushroomFeatureConfig config) {
    int i = config.foliageRadius;

    for (int j = -i; j <= i; ++j) {
      for (int k = -i; k <= i; ++k) {
        boolean bl = j == -i;
        boolean bl2 = j == i;
        boolean bl3 = k == -i;
        boolean bl4 = k == i;
        boolean bl5 = bl || bl2;
        boolean bl6 = bl3 || bl4;
        if (!bl5 || !bl6) {
          mutable.setWithOffset(start, j, y, k);
          if (!world.getBlockState(mutable).isSolidRender(world, mutable)) {
            boolean bl7 = bl || bl6 && j == 1 - i;
            boolean bl8 = bl2 || bl6 && j == i - 1;
            boolean bl9 = bl3 || bl5 && k == 1 - i;
            boolean bl10 = bl4 || bl5 && k == i - 1;
            BlockState blockState = config.capProvider.getState(random, start);
            if (blockState.hasProperty(HugeMushroomBlock.WEST) && blockState.hasProperty(HugeMushroomBlock.EAST) && blockState.hasProperty(HugeMushroomBlock.NORTH) && blockState.hasProperty(
                HugeMushroomBlock.SOUTH)) {
              blockState = blockState.setValue(HugeMushroomBlock.WEST, bl7).setValue(HugeMushroomBlock.EAST, bl8).setValue(HugeMushroomBlock.NORTH,
                  bl9).setValue(HugeMushroomBlock.SOUTH, bl10);
            }

            this.setBlock(world, mutable, blockState);
          }
        }
      }
    }

  }

  protected int getCapSize(int i, int j, int capSize, int y) {
    return y <= 3 ? 0 : capSize;
  }
}
