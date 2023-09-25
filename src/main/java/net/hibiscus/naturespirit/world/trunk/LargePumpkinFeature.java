package net.hibiscus.naturespirit.world.trunk;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class LargePumpkinFeature extends Feature <DefaultFeatureConfig> {
   public LargePumpkinFeature(Codec <DefaultFeatureConfig> configCodec) {
      super(configCodec);
   }

   @Override public boolean generate(FeatureContext context) {
      StructureWorldAccess worldAccess = context.getWorld();
      BlockPos origin = context.getOrigin();
      Direction direction = Direction.Type.HORIZONTAL.random(worldAccess.getRandom());
      Direction direction2 = direction.rotateYClockwise();
      if(worldAccess.isAir(origin.up()) && worldAccess.isAir(origin.offset(direction).up()) && worldAccess.isAir(origin.offset(direction2).up()) && worldAccess.isAir(origin
              .offset(direction2)
              .offset(direction)
              .up()) && worldAccess.isAir(origin) && worldAccess.isAir(origin.offset(direction)) && worldAccess.isAir(origin.offset(direction2)) && worldAccess.isAir(origin
              .offset(direction2)
              .offset(direction))) {
         worldAccess.setBlockState(origin, Blocks.PUMPKIN.getDefaultState(), 1);
         worldAccess.setBlockState(origin.offset(direction), Blocks.PUMPKIN.getDefaultState(), 1);
         worldAccess.setBlockState(origin.offset(direction2), Blocks.PUMPKIN.getDefaultState(), 1);
         worldAccess.setBlockState(origin.offset(direction2).offset(direction), Blocks.PUMPKIN.getDefaultState(), 1);
         worldAccess.setBlockState(origin.up(), Blocks.PUMPKIN.getDefaultState(), 1);
         worldAccess.setBlockState(origin.offset(direction).up(), Blocks.PUMPKIN.getDefaultState(), 1);
         worldAccess.setBlockState(origin.offset(direction2).up(), Blocks.PUMPKIN.getDefaultState(), 1);
         worldAccess.setBlockState(origin.offset(direction2).offset(direction).up(), Blocks.PUMPKIN.getDefaultState(), 1);
         return true;
      }
      return false;
   }
}
