package net.hibiscus.naturespirit.blocks;


import net.hibiscus.naturespirit.registration.HibiscusBlocksAndItems;
import net.hibiscus.naturespirit.util.HibiscusTags;
import net.minecraft.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LargeDesertFernBlock extends TallGrassBlock {
   protected static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);

   public LargeDesertFernBlock(Properties properties) {
      super(properties);
   }

   public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
      Vec3 vec3 = state.getOffset(level, pos);
      return SHAPE.move(vec3.x, vec3.y, vec3.z);
   }

   @Override
   protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
      return floor.is(HibiscusTags.Blocks.TURNIP_STEM_GROWS_ON) || floor.is(Blocks.FARMLAND);
   }

   public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
      DoublePlantBlock tallPlantBlock = (DoublePlantBlock) HibiscusBlocksAndItems.TALL_SCORCHED_GRASS;
      if (tallPlantBlock.defaultBlockState().canSurvive(world, pos) && world.isEmptyBlock(pos.above())) {
         DoublePlantBlock.placeAt(world, tallPlantBlock.defaultBlockState(), pos, 2);
      }

   }
}
