package net.hibiscus.naturespirit.blocks;

import net.hibiscus.naturespirit.registration.HibiscusBlocksAndItems;
import net.hibiscus.naturespirit.util.HibiscusTags;
import net.minecraft.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SedgeGrassBlock extends TallGrassBlock implements SimpleWaterloggedBlock {
   public static final BooleanProperty WATERLOGGED;
   protected static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);
   public SedgeGrassBlock(Properties settings) {
      super(settings);
      this.registerDefaultState(this.stateDefinition.any()
              .setValue(WATERLOGGED, false));
   }

   public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
      Vec3 vec3 = state.getOffset(level, pos);
      return SHAPE.move(vec3.x, vec3.y, vec3.z);
   }

   @Override
   protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
         if(world.getFluidState(pos.above()).is(FluidTags.WATER)) {
            return floor.isFaceSturdy(world, pos, Direction.UP) && !floor.is(Blocks.MAGMA_BLOCK);
         }
         else {
            return floor.is(HibiscusTags.Blocks.TURNIP_STEM_GROWS_ON) || floor.is(Blocks.FARMLAND);
         }
   }


   public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
      return true;
   }
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
      return this.defaultBlockState().setValue(WATERLOGGED, fluidState.is(FluidTags.WATER) && fluidState.getAmount() == 8);
   }

   public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
      DoublePlantBlock tallPlantBlock = (DoublePlantBlock) HibiscusBlocksAndItems.TALL_SEDGE_GRASS;
      if (tallPlantBlock.defaultBlockState().canSurvive(world, pos) && world.isEmptyBlock(pos.above())) {
         DoublePlantBlock.placeAt(world, tallPlantBlock.defaultBlockState(), pos, 2);
      }

   }

   public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {

         BlockPos blockPos = pos.below();
         BlockPos blockPos2 = pos.above();
         if(state.getValue(WATERLOGGED)) {
            return super.canSurvive(state, level, pos) && level.getBlockState(blockPos).isFaceSturdy(level,
                    blockPos,
                    Direction.UP
            ) && !level.getFluidState(blockPos2).is(FluidTags.WATER);
         }
         else {
            return super.canSurvive(state, level, pos) && this.mayPlaceOn(level.getBlockState(blockPos),
                    level,
                    blockPos
            );
         }

   }

   protected void createBlockStateDefinition(StateDefinition.Builder <Block, BlockState> builder) {
      builder.add(WATERLOGGED);
   }
   public FluidState getFluidState(BlockState state) {
      return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
   }

   static {
      WATERLOGGED = BlockStateProperties.WATERLOGGED;
   }
}
