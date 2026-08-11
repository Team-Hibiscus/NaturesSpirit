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

public class HugeRedMushroomFeature extends HugeMushroomFeature {

  public HugeRedMushroomFeature(Codec<HugeMushroomFeatureConfig> codec) {
    super(codec);
  }

  protected void generateCap(LevelAccessor world, RandomSource random, BlockPos start, int y, BlockPos.MutableBlockPos mutable, HugeMushroomFeatureConfig config) {
    for (int i = y - 3; i <= y; ++i) {
      int j = i < y ? config.foliageRadius : config.foliageRadius - 1;
      int k = config.foliageRadius - 2;

      for (int l = -j; l <= j; ++l) {
        for (int m = -j; m <= j; ++m) {
          int x = Math.abs(l);
          int z = Math.abs(m);
          boolean bl;
          if (i < y - 1) {
            bl = (x == j) != (z == j);
          } else if (i == y - 1) {
            bl = (x == j && z < j - 1) || (z == j && x < j - 1) || (x == j - 1 && z == j - 1);
          } else {
            bl = !(x == j && z == j);
          }

          if (bl) {
            mutable.setWithOffset(start, l, i, m);
            if (!world.getBlockState(mutable).isSolidRender(world, mutable)) {
              BlockState blockState = config.capProvider.getState(random, start);
              if (blockState.hasProperty(HugeMushroomBlock.WEST) && blockState.hasProperty(HugeMushroomBlock.EAST) && blockState.hasProperty(HugeMushroomBlock.NORTH) && blockState.hasProperty(
                  HugeMushroomBlock.SOUTH) && blockState.hasProperty(HugeMushroomBlock.UP)) {
                blockState = blockState.setValue(HugeMushroomBlock.UP,
                    i >= y - 1 || (i == y - 2 && (x == j - 1 || z == j - 1))).setValue(HugeMushroomBlock.WEST, l < -k || (i >= y - 1 && x == k)).setValue(HugeMushroomBlock.EAST,
                    l > k || (i >= y - 1 && x == k)).setValue(HugeMushroomBlock.NORTH, m < -k || (i >= y - 1 && z == k)).setValue(HugeMushroomBlock.SOUTH, m > k || (i >= y - 1 && z == k));
              }

              this.setBlock(world, mutable, blockState);
            }
          }

        }
      }
    }

  }

  protected int getCapSize(int i, int j, int capSize, int y) {
    int k = 0;
    if (y < j && y >= j - 3) {
      k = capSize;
    } else if (y == j) {
      k = capSize;
    }

    return k;
  }
}
