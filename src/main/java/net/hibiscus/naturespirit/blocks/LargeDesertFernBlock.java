package net.hibiscus.naturespirit.blocks;


import net.hibiscus.naturespirit.util.HibiscusTags;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class LargeDesertFernBlock extends FernBlock {
   protected static final VoxelShape SHAPE = Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
   private final TallPlantBlock tallBlock;
   public LargeDesertFernBlock(Settings properties, TallPlantBlock tallBlock) {
      super(properties);
      this.tallBlock = tallBlock;
   }

   @Override protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
      return floor.isIn(HibiscusTags.Blocks.TURNIP_STEM_GROWS_ON) || floor.isOf(Blocks.FARMLAND);
   }

   public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
      TallPlantBlock tallPlantBlock = (TallPlantBlock) tallBlock;
      if(tallPlantBlock.getDefaultState().canPlaceAt(world, pos) && world.isAir(pos.up())) {
         TallPlantBlock.placeAt(world, tallPlantBlock.getDefaultState(), pos, 2);
      }

   }
}
