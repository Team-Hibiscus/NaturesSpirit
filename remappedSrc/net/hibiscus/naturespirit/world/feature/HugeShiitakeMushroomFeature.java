package net.hibiscus.naturespirit.world.feature;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.AbstractHugeMushroomFeature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;

public class HugeShiitakeMushroomFeature extends AbstractHugeMushroomFeature {
   public HugeShiitakeMushroomFeature(Codec <HugeMushroomFeatureConfiguration> codec) {
      super(codec);
   }

   protected void makeCap(LevelAccessor world, RandomSource random, BlockPos start, int y, MutableBlockPos mutable, HugeMushroomFeatureConfiguration config) {
      for(int i = y - 3; i <= y + 1; ++i) {
         int j = i < y ? config.foliageRadius : config.foliageRadius - 1;
         int k = config.foliageRadius - 2;

         for(int l = -j; l <= j; ++l) {
            for(int m = -j; m <= j; ++m) {
               boolean bl = l == -j; //first
               boolean bl2 = l == j; //last
               boolean bl3 = m == -j; //first
               boolean bl4 = m == j; //last
               boolean bl5 = bl || bl2; //first or last
               boolean bl6 = bl3 || bl4; // first or last
               if(i >= y || (bl5 != bl6 && (i == y - 2 || i == y - 1))) {
                  mutable.setWithOffset(start, l, i, m); //-radius, y. positive radius (corner)
                  if(!world.getBlockState(mutable).isSolidRender(world, mutable)) {
                     BlockState blockState = config.capProvider.getState(random, start);
                     if(blockState.hasProperty(HugeMushroomBlock.WEST) && blockState.hasProperty(HugeMushroomBlock.EAST) && blockState.hasProperty(
                             HugeMushroomBlock.NORTH) && blockState.hasProperty(HugeMushroomBlock.SOUTH) && blockState.hasProperty(
                             HugeMushroomBlock.UP)) {
                        blockState = blockState.setValue(HugeMushroomBlock.UP, i >= y - 1)
                                .setValue(HugeMushroomBlock.WEST, l < -k)
                                .setValue(HugeMushroomBlock.EAST, l > k)
                                .setValue(HugeMushroomBlock.NORTH, m < -k)
                                .setValue(HugeMushroomBlock.SOUTH, m > k);
                     }

                     this.setBlock(world, mutable, blockState);
                  }
               }
            }
         }
      }

   }
   protected boolean isValidPosition(LevelAccessor world, BlockPos pos, int height, MutableBlockPos mutablePos, HugeMushroomFeatureConfiguration config) {
      int i = pos.getY();
      if (i >= world.getMinBuildHeight() + 1 && i + height + 1 < world.getMaxBuildHeight()) {
         BlockState blockState = world.getBlockState(pos.below());
         if (!isDirt(blockState) && !blockState.is(BlockTags.MUSHROOM_GROW_BLOCK)) {
            return false;
         } else {
            for(int j = 0; j <= height; ++j) {
               int k = this.getTreeRadiusForHeight(-1, -1, config.foliageRadius, j);
            }

            return true;
         }
      } else {
         return false;
      }
   }

   protected int getTreeRadiusForHeight(int i, int j, int capSize, int y) {
      int k = 0;
      if(y < j && y >= j - 4) {
         k = capSize;
      }
      else if(y == j) {
         k = capSize;
      }

      return k;
   }
}
