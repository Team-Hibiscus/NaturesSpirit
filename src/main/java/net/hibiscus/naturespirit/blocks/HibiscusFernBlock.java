package net.hibiscus.naturespirit.blocks;


import net.minecraft.block.BlockState;
import net.minecraft.block.ShortPlantBlock;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class HibiscusFernBlock extends ShortPlantBlock {

   TallPlantBlock tallPlantBlock;

   public HibiscusFernBlock(Settings properties, TallPlantBlock tallPlantBlock) {
      super(properties);
      this.tallPlantBlock = tallPlantBlock;
   }

   public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
      if(tallPlantBlock.getDefaultState().canPlaceAt(world, pos) && world.isAir(pos.up())) {
         TallPlantBlock.placeAt(world, tallPlantBlock.getDefaultState(), pos, 2);
      }

   }
}
