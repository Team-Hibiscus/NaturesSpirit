package net.hibiscus.naturespirit.world.feature;

import com.mojang.serialization.Codec;
import net.hibiscus.naturespirit.blocks.LotusStem;
import net.hibiscus.naturespirit.registration.HibiscusBlocksAndItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Optional;

public class LotusPlantFeature extends Feature <DefaultFeatureConfig> {
   public LotusPlantFeature(Codec <DefaultFeatureConfig> codec) {
      super(codec);
   }

   public boolean generate(FeatureContext <DefaultFeatureConfig> context) {
      int i = 0;
      StructureWorldAccess structureWorldAccess = context.getWorld();
      BlockPos blockPos = context.getOrigin();
      Random random = context.getRandom();
      int j = structureWorldAccess.getTopY(Heightmap.Type.OCEAN_FLOOR, blockPos.getX(), blockPos.getZ());
      BlockPos blockPos2 = new BlockPos(blockPos.getX(), j, blockPos.getZ());
      if(structureWorldAccess.getBlockState(blockPos2).isOf(Blocks.WATER) || structureWorldAccess.isAir(blockPos2)) {
         BlockState blockState = HibiscusBlocksAndItems.LOTUS_FLOWER.getDefaultState();
         BlockState blockState2 = HibiscusBlocksAndItems.LOTUS_STEM.getDefaultState().with(LotusStem.WATERLOGGED, structureWorldAccess.getFluidState(blockPos2).isIn(FluidTags.WATER));
         Optional <BlockPos> optional = LotusStem.getStemHeadWaterPos(structureWorldAccess, blockPos2, Blocks.AIR);
         int k = optional.map(pos -> pos.getY() - j - random.nextInt(3)).orElseGet(() -> 1 + random.nextInt(10));

         for(int l = 0; l <= k; ++l) {
            if(structureWorldAccess.getBlockState(blockPos2).isOf(Blocks.WATER) && blockState2.canPlaceAt(structureWorldAccess, blockPos2)) {
               if(l == k) {
                  structureWorldAccess.setBlockState(blockPos2, blockState2.with(LotusStem.AGE, 2), 2);
               }
               else {
                  structureWorldAccess.setBlockState(blockPos2, blockState2.with(LotusStem.AGE, 3), 2);
               }
               ++i;
            }
            else if(structureWorldAccess.isAir(blockPos2) && l == 0) {
               if(blockState2.canPlaceAt(structureWorldAccess, blockPos2) && structureWorldAccess.isAir(blockPos2.up()) && structureWorldAccess.isAir(blockPos2.up(2))) {
                  int n = random.nextInt(3);
                  for(int o = 0; o <= n; ++o) {
                     if(o == n) {
                        structureWorldAccess.setBlockState(blockPos2.up(o), blockState, 2);
                        break;
                     }
                     structureWorldAccess.setBlockState(blockPos2.up(o), blockState2.with(LotusStem.WATERLOGGED, false).with(LotusStem.AGE, 3), 2);
                  }
                  ++i;
               }
               break;
            }
            else if(structureWorldAccess.isAir(blockPos2)) {
               if(blockState.canPlaceAt(structureWorldAccess, blockPos2) && structureWorldAccess.getBlockState(blockPos2.down()).isOf(HibiscusBlocksAndItems.LOTUS_STEM)) {
                  structureWorldAccess.setBlockState(blockPos2, blockState, 2);
                  ++i;
               }
               break;
            }

            blockPos2 = blockPos2.up();
         }
      }

      return i > 0;
   }
}
