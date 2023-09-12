package net.hibiscus.naturespirit.blocks;


import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockState;

public class HibiscusFernBlock extends TallGrassBlock {

   DoublePlantBlock tallPlantBlock;

   public HibiscusFernBlock(Properties properties, DoublePlantBlock tallPlantBlock) {
      super(properties);
      this.tallPlantBlock = tallPlantBlock;
   }

   public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
      if(tallPlantBlock.defaultBlockState().canSurvive(world, pos) && world.isEmptyBlock(pos.above())) {
         DoublePlantBlock.placeAt(world, tallPlantBlock.defaultBlockState(), pos, 2);
      }

   }
}
