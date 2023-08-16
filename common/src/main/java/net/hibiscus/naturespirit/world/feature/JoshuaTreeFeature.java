package net.hibiscus.naturespirit.world.feature;

import com.mojang.serialization.Codec;
import net.hibiscus.naturespirit.blocks.HibiscusBlocks;
import net.hibiscus.naturespirit.blocks.JoshuaTrunkBlock;
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

public class JoshuaTreeFeature extends Feature <NoneFeatureConfiguration> {
   public JoshuaTreeFeature(Codec <NoneFeatureConfiguration> codec) {
      super(codec);
   }

   public boolean place(FeaturePlaceContext <NoneFeatureConfiguration> context) {
      WorldGenLevel structureWorldAccess = context.level();
      BlockPos blockPos = context.origin();
      RandomSource random = context.random();
         generate2(structureWorldAccess, blockPos, random, blockPos,8, 0);
         return true;
   }

   private static boolean isSurroundedByAir(LevelReader world, BlockPos pos, @Nullable Direction exceptDirection) {
      Iterator var3 = Direction.Plane.HORIZONTAL.iterator();

      Direction direction;
      do {
         if (!var3.hasNext()) {
            return true;
         }

         direction = (Direction)var3.next();
      } while(direction == exceptDirection || world.isEmptyBlock(pos.relative(direction)));

      return false;
   }

   private static void generate2(LevelAccessor world, BlockPos pos, RandomSource random, BlockPos rootPos, int size, int layer) {
      JoshuaTrunkBlock joshuaTrunkBlock = (JoshuaTrunkBlock) HibiscusBlocks.JOSHUA[0];
      int i = random.nextInt(4) + 1;
      if (layer == 0) {
         ++i;
      }

      for(int j = 0; j < i; ++j) {
         BlockPos blockPos = pos.above(j + 1);
         if (layer > 0) {
            blockPos = pos.above(j == 0 ? 1 : (int) (j / 1.2));
         }
         if (!isSurroundedByAir(world, blockPos, (Direction)null)) {
            return;
         }
         world.setBlock(blockPos, joshuaTrunkBlock.withConnectionProperties(world, blockPos), 2);
         world.setBlock(blockPos.below(), joshuaTrunkBlock.withConnectionProperties(world, blockPos.below()), 2);
      }

      boolean bl = true;
      if (layer < 4) {
         int k = random.nextInt(5);
         if (layer == 0) {
            ++k;
         }

         for(int l = 0; l < k; ++l) {
            Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
            int m = random.nextInt(2);
            int n = i - m == 0 ? 1 : i - m;
            int o = m == 0 ? 1 : 2;
            BlockPos blockPos2 = pos.above(n).relative(direction, o);
            if (Math.abs(blockPos2.getX() - rootPos.getX()) < size && Math.abs(blockPos2.getZ() - rootPos.getZ()) < size && world.isEmptyBlock(blockPos2) && world.isEmptyBlock(blockPos2.below()) && isSurroundedByAir(world, blockPos2, direction.getOpposite())) {
               world.setBlock(blockPos2, joshuaTrunkBlock.withConnectionProperties(world, blockPos2), 2);
               world.setBlock(blockPos2.relative(direction.getOpposite()), joshuaTrunkBlock.withConnectionProperties(world, blockPos2.relative(direction.getOpposite())), 2);
               for (int p = o; p > 0; --p) {
                  world.setBlock(blockPos2.relative(direction.getOpposite(), p), joshuaTrunkBlock.withConnectionProperties(world, blockPos2.relative(direction.getOpposite(), p)), 2);
               }
               generate2(world, blockPos2, random, rootPos, size, layer + 1);
               bl = false;

               if(world.isEmptyBlock(blockPos2.above())) {

                  world.setBlock(blockPos2.above(), HibiscusBlocks.JOSHUA_LEAVES.defaultBlockState()
                          .setValue(LeavesBlock.DISTANCE, 1), 2);
                  world.setBlock(blockPos2,  ((JoshuaTrunkBlock) HibiscusBlocks.JOSHUA[0]).withConnectionProperties(world, blockPos2), 2);

                  Direction direction2 = Direction.Plane.HORIZONTAL.getRandomDirection(random);
                  if (random.nextBoolean() && world.isEmptyBlock(blockPos2.relative(direction, 1))) {
                     world.setBlock(blockPos2.relative(direction2, 1), HibiscusBlocks.JOSHUA_LEAVES.defaultBlockState()
                             .setValue(LeavesBlock.DISTANCE, 1), 2);
                     world.setBlock(blockPos2, ((JoshuaTrunkBlock) HibiscusBlocks.JOSHUA[0]).withConnectionProperties(world, blockPos2), 2);
                  }
               } else
               if((world.isEmptyBlock(blockPos2.above(2)) && !world.isEmptyBlock(blockPos2.above()))) {

                  world.setBlock(blockPos2.above(2), HibiscusBlocks.JOSHUA_LEAVES.defaultBlockState()
                          .setValue(LeavesBlock.DISTANCE, 1), 2);
                  world.setBlock(blockPos2.above(1),  ((JoshuaTrunkBlock) HibiscusBlocks.JOSHUA[0]).withConnectionProperties(world, blockPos2.above()), 2);

                  Direction direction2 = Direction.Plane.HORIZONTAL.getRandomDirection(random);
                  if (random.nextBoolean() && world.isEmptyBlock(blockPos2.above().relative(direction, 1))) {
                     world.setBlock(blockPos2.above().relative(direction2, 1), HibiscusBlocks.JOSHUA_LEAVES.defaultBlockState()
                             .setValue(LeavesBlock.DISTANCE, 1), 2);
                     world.setBlock(blockPos2.above(), ((JoshuaTrunkBlock) HibiscusBlocks.JOSHUA[0]).withConnectionProperties(world, blockPos2.above()), 2);
                  }
               }
            }
         }
      }
      if(bl) {
         world.setBlock(pos.above(i), HibiscusBlocks.JOSHUA_LEAVES.defaultBlockState()
                 .setValue(LeavesBlock.DISTANCE, 1), 2);
         world.setBlock(pos.above(i - 1), ((JoshuaTrunkBlock) HibiscusBlocks.JOSHUA[0]).withConnectionProperties(world, pos.above(i - 1)), 2);
         Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
         if (random.nextBoolean() && world.isEmptyBlock(pos.above(i-1).relative(direction, 1))) {
            world.setBlock(pos.above(i-1).relative(direction, 1),
                    HibiscusBlocks.JOSHUA_LEAVES.defaultBlockState()
                        .setValue(LeavesBlock.DISTANCE, 1),
                    2);
            world.setBlock(pos.above(i-1), ((JoshuaTrunkBlock) HibiscusBlocks.JOSHUA[0]).withConnectionProperties(world, pos.above(i-1)), 2);
         }
      }

   }
}