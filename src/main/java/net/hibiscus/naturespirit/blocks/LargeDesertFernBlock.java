package net.hibiscus.naturespirit.blocks;


import net.hibiscus.naturespirit.registration.HibiscusBlocksAndItems;
import net.hibiscus.naturespirit.util.HibiscusTags;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class LargeDesertFernBlock extends FernBlock {
   protected static final VoxelShape SHAPE = Block.createCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);

   public LargeDesertFernBlock(Settings properties) {
      super(properties);
   }

   public VoxelShape getOutlineShape(BlockState state, BlockView level, BlockPos pos, ShapeContext context) {
      Vec3d vec3 = state.getModelOffset(level, pos);
      return SHAPE.offset(vec3.x, vec3.y, vec3.z);
   }

   @Override
   protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
      return floor.isIn(HibiscusTags.Blocks.TURNIP_STEM_GROWS_ON) || floor.isOf(Blocks.FARMLAND);
   }

   public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
      TallPlantBlock tallPlantBlock = (TallPlantBlock) HibiscusBlocksAndItems.TALL_SCORCHED_GRASS;
      if (tallPlantBlock.getDefaultState().canPlaceAt(world, pos) && world.isAir(pos.up())) {
         TallPlantBlock.placeAt(world, tallPlantBlock.getDefaultState(), pos, 2);
      }

   }
}
