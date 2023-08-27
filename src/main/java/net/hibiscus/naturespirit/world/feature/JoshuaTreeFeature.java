package net.hibiscus.naturespirit.world.feature;

import com.mojang.serialization.Codec;
import net.hibiscus.naturespirit.blocks.JoshuaTrunkBlock;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusWoods;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

public class JoshuaTreeFeature extends Feature <DefaultFeatureConfig> {
   public JoshuaTreeFeature(Codec <DefaultFeatureConfig> codec) {
      super(codec);
   }

   public boolean generate(FeatureContext <DefaultFeatureConfig> context) {
      StructureWorldAccess structureWorldAccess = context.getWorld();
      BlockPos blockPos = context.getOrigin();
      Random random = context.getRandom();
         generate2(structureWorldAccess, blockPos, random, blockPos,8, 0);
         return true;
   }

   private static boolean isSurroundedByAir(WorldView world, BlockPos pos, @Nullable Direction exceptDirection) {
      Iterator var3 = Direction.Type.HORIZONTAL.iterator();

      Direction direction;
      do {
         if (!var3.hasNext()) {
            return true;
         }

         direction = (Direction)var3.next();
      } while(direction == exceptDirection || world.isAir(pos.offset(direction)));

      return false;
   }

   private static void generate2(WorldAccess world, BlockPos pos, Random random, BlockPos rootPos, int size, int layer) {
      JoshuaTrunkBlock joshuaTrunkBlock = (JoshuaTrunkBlock) HibiscusWoods.JOSHUA[0];
      int i = random.nextInt(4) + 1;
      if (layer == 0) {
         ++i;
      }

      for(int j = 0; j < i; ++j) {
         BlockPos blockPos = pos.up(j + 1);
         if (layer > 0) {
            blockPos = pos.up(j == 0 ? 1 : (int) (j / 1.2));
         }
         if (!isSurroundedByAir(world, blockPos, (Direction)null)) {
            return;
         }
         world.setBlockState(blockPos, joshuaTrunkBlock.withConnectionProperties(world, blockPos), 2);
         world.setBlockState(blockPos.down(), joshuaTrunkBlock.withConnectionProperties(world, blockPos.down()), 2);
      }

      boolean bl = true;
      if (layer < 4) {
         int k = random.nextInt(5);
         if (layer == 0) {
            ++k;
         }

         for(int l = 0; l < k; ++l) {
            Direction direction = Direction.Type.HORIZONTAL.random(random);
            int m = random.nextInt(2);
            int n = i - m == 0 ? 1 : i - m;
            int o = m == 0 ? 1 : 2;
            BlockPos blockPos2 = pos.up(n).offset(direction, o);
            if (Math.abs(blockPos2.getX() - rootPos.getX()) < size && Math.abs(blockPos2.getZ() - rootPos.getZ()) < size && world.isAir(blockPos2) && world.isAir(blockPos2.down()) && isSurroundedByAir(world, blockPos2, direction.getOpposite())) {
               world.setBlockState(blockPos2, joshuaTrunkBlock.withConnectionProperties(world, blockPos2), 2);
               world.setBlockState(blockPos2.offset(direction.getOpposite()), joshuaTrunkBlock.withConnectionProperties(world, blockPos2.offset(direction.getOpposite())), 2);
               for (int p = o; p > 0; --p) {
                  world.setBlockState(blockPos2.offset(direction.getOpposite(), p), joshuaTrunkBlock.withConnectionProperties(world, blockPos2.offset(direction.getOpposite(), p)), 2);
               }
               generate2(world, blockPos2, random, rootPos, size, layer + 1);
               bl = false;

               if(world.isAir(blockPos2.up())) {

                  world.setBlockState(blockPos2.up(), HibiscusWoods.JOSHUA_LEAVES.getDefaultState()
                                                                                 .with(LeavesBlock.DISTANCE, 1), 2);
                  world.setBlockState(blockPos2,  ((JoshuaTrunkBlock) HibiscusWoods.JOSHUA[0]).withConnectionProperties(world, blockPos2), 2);

                  Direction direction2 = Direction.Type.HORIZONTAL.random(random);
                  if (random.nextBoolean() && world.isAir(blockPos2.offset(direction, 1))) {
                     world.setBlockState(blockPos2.offset(direction2, 1), HibiscusWoods.JOSHUA_LEAVES.getDefaultState()
                                                                                                     .with(LeavesBlock.DISTANCE, 1), 2);
                     world.setBlockState(blockPos2, ((JoshuaTrunkBlock) HibiscusWoods.JOSHUA[0]).withConnectionProperties(world, blockPos2), 2);
                  }
               } else
               if((world.isAir(blockPos2.up(2)) && !world.isAir(blockPos2.up()))) {

                  world.setBlockState(blockPos2.up(2), HibiscusWoods.JOSHUA_LEAVES.getDefaultState()
                                                                                  .with(LeavesBlock.DISTANCE, 1), 2);
                  world.setBlockState(blockPos2.up(1),  ((JoshuaTrunkBlock) HibiscusWoods.JOSHUA[0]).withConnectionProperties(world, blockPos2.up()), 2);

                  Direction direction2 = Direction.Type.HORIZONTAL.random(random);
                  if (random.nextBoolean() && world.isAir(blockPos2.up().offset(direction, 1))) {
                     world.setBlockState(blockPos2.up().offset(direction2, 1), HibiscusWoods.JOSHUA_LEAVES.getDefaultState()
                                                                                                          .with(LeavesBlock.DISTANCE, 1), 2);
                     world.setBlockState(blockPos2.up(), ((JoshuaTrunkBlock) HibiscusWoods.JOSHUA[0]).withConnectionProperties(world, blockPos2.up()), 2);
                  }
               }
            }
         }
      }
      if(bl) {
         world.setBlockState(pos.up(i), HibiscusWoods.JOSHUA_LEAVES.getDefaultState()
                                                                   .with(LeavesBlock.DISTANCE, 1), 2);
         world.setBlockState(pos.up(i - 1), ((JoshuaTrunkBlock) HibiscusWoods.JOSHUA[0]).withConnectionProperties(world, pos.up(i - 1)), 2);
         Direction direction = Direction.Type.HORIZONTAL.random(random);
         if (random.nextBoolean() && world.isAir(pos.up(i-1).offset(direction, 1))) {
            world.setBlockState(pos.up(i-1).offset(direction, 1),
                    HibiscusWoods.JOSHUA_LEAVES.getDefaultState()
                                               .with(LeavesBlock.DISTANCE, 1),
                    2);
            world.setBlockState(pos.up(i-1), ((JoshuaTrunkBlock) HibiscusWoods.JOSHUA[0]).withConnectionProperties(world, pos.up(i-1)), 2);
         }
      }

   }
}