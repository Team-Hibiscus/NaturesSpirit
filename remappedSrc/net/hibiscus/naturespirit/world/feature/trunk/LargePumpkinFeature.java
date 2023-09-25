package net.hibiscus.naturespirit.world.trunk;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class LargePumpkinFeature extends Feature <NoneFeatureConfiguration> {
   public LargePumpkinFeature(Codec <NoneFeatureConfiguration> configCodec) {
      super(configCodec);
   }

   @Override public boolean place(FeaturePlaceContext context) {
      WorldGenLevel worldAccess = context.level();
      BlockPos origin = context.origin();
      Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(worldAccess.getRandom());
      Direction direction2 = direction.getClockWise();
      if(worldAccess.isEmptyBlock(origin.above()) && worldAccess.isEmptyBlock(origin.relative(direction).above()) && worldAccess.isEmptyBlock(origin.relative(direction2).above()) && worldAccess.isEmptyBlock(origin
              .relative(direction2)
              .relative(direction)
              .above()) && worldAccess.isEmptyBlock(origin) && worldAccess.isEmptyBlock(origin.relative(direction)) && worldAccess.isEmptyBlock(origin.relative(direction2)) && worldAccess.isEmptyBlock(origin
              .relative(direction2)
              .relative(direction))) {
         worldAccess.setBlock(origin, Blocks.PUMPKIN.defaultBlockState(), 1);
         worldAccess.setBlock(origin.relative(direction), Blocks.PUMPKIN.defaultBlockState(), 1);
         worldAccess.setBlock(origin.relative(direction2), Blocks.PUMPKIN.defaultBlockState(), 1);
         worldAccess.setBlock(origin.relative(direction2).relative(direction), Blocks.PUMPKIN.defaultBlockState(), 1);
         worldAccess.setBlock(origin.above(), Blocks.PUMPKIN.defaultBlockState(), 1);
         worldAccess.setBlock(origin.relative(direction).above(), Blocks.PUMPKIN.defaultBlockState(), 1);
         worldAccess.setBlock(origin.relative(direction2).above(), Blocks.PUMPKIN.defaultBlockState(), 1);
         worldAccess.setBlock(origin.relative(direction2).relative(direction).above(), Blocks.PUMPKIN.defaultBlockState(), 1);
         return true;
      }
      return false;
   }
}
