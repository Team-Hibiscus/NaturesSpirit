package net.hibiscus.naturespirit.blocks;


import net.hibiscus.naturespirit.registration.HibiscusBlocksAndItems;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class HibiscusFernBlock extends FernBlock {

   public HibiscusFernBlock(Settings properties) {
      super(properties);
   }

   public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
      TallPlantBlock tallPlantBlock = (TallPlantBlock) HibiscusBlocksAndItems.LARGE_FLAXEN_FERN;
      if (tallPlantBlock.getDefaultState().canPlaceAt(world, pos) && world.isAir(pos.up())) {
         TallPlantBlock.placeAt(world, tallPlantBlock.getDefaultState(), pos, 2);
      }

   }
}
