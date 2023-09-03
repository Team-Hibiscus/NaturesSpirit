package net.hibiscus.naturespirit.world.feature;

import com.mojang.serialization.Codec;
import net.hibiscus.naturespirit.registration.HibiscusBlocksAndItems;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.hibiscus.naturespirit.blocks.LotusStem;
import java.util.Optional;

public class LotusPlantFeature extends Feature <NoneFeatureConfiguration> {
   public LotusPlantFeature(Codec <NoneFeatureConfiguration> codec) {
      super(codec);
   }

   public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
      int i = 0;
      WorldGenLevel structureWorldAccess = context.level();
      BlockPos blockPos = context.origin();
      RandomSource random = context.random();
      int j = structureWorldAccess.getHeight(Heightmap.Types.OCEAN_FLOOR, blockPos.getX(), blockPos.getZ());
      BlockPos blockPos2 = new BlockPos(blockPos.getX(), j, blockPos.getZ());
      if (structureWorldAccess.getBlockState(blockPos2).is(Blocks.WATER) || structureWorldAccess.isEmptyBlock(blockPos2)) {
         BlockState blockState = HibiscusBlocksAndItems.LOTUS_FLOWER.defaultBlockState();
         BlockState blockState2 = HibiscusBlocksAndItems.LOTUS_STEM.defaultBlockState().setValue(LotusStem.WATERLOGGED, structureWorldAccess.getFluidState(blockPos2).is(
                 FluidTags.WATER));
         Optional<BlockPos> optional = LotusStem.getStemHeadWaterPos(structureWorldAccess, blockPos2, Blocks.AIR);
         int k = optional.map(pos -> pos.getY() - j - random.nextInt(3)).orElseGet(() -> 1 + random.nextInt(10));

         for(int l = 0; l <= k; ++l) {
            if (structureWorldAccess.getBlockState(blockPos2).is(Blocks.WATER) && blockState2.canSurvive(structureWorldAccess, blockPos2)) {
               if (l == k) {
                  structureWorldAccess.setBlock(blockPos2, (BlockState)blockState2.setValue(LotusStem.AGE, 2), 2);
               } else {
                  structureWorldAccess.setBlock(blockPos2, blockState2.setValue(LotusStem.AGE, 3), 2);
               }
               ++i;
            } else if (structureWorldAccess.isEmptyBlock(blockPos2) && l==0) {
               if (blockState2.canSurvive(structureWorldAccess, blockPos2) && structureWorldAccess.isEmptyBlock(blockPos2.above()) && structureWorldAccess.isEmptyBlock(blockPos2.above(2))) {
                  int n = random.nextInt(3);
                  for (int o = 0; o<= n; ++o) {
                     if (o==n) {
                        structureWorldAccess.setBlock(blockPos2.above(o), (BlockState)blockState, 2);
                        break;
                     }
                     structureWorldAccess.setBlock(blockPos2.above(o), (BlockState)blockState2.setValue(LotusStem.WATERLOGGED, false).setValue(LotusStem.AGE, 3), 2);
                  }
                  ++i;
               }
               break;
            } else if (structureWorldAccess.isEmptyBlock(blockPos2)) {
               if (blockState.canSurvive(structureWorldAccess, blockPos2) && structureWorldAccess.getBlockState(blockPos2.below()).is(
                       HibiscusBlocksAndItems.LOTUS_STEM)) {
                  structureWorldAccess.setBlock(blockPos2, (BlockState)blockState, 2);
                  ++i;
               }
               break;
            }

            blockPos2 = blockPos2.above();
         }
      }

      return i > 0;
   }
}
