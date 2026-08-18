package net.hibiscus.naturespirit.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TallSedgeGrassBlock extends DoublePlantBlock implements SimpleWaterloggedBlock {

  public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
  protected static final float AABB_OFFSET = 6F;
  protected static final VoxelShape SHAPE = Block.box(2D, 0D, 2D, 14D, 16D, 14D);

  public TallSedgeGrassBlock(Properties properties) {
    super(properties);
    this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false).setValue(HALF, DoubleBlockHalf.LOWER));
  }

  @Override
  protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
    if (level.getFluidState(pos.above()).is(FluidTags.WATER)) {
      return state.isFaceSturdy(level, pos, Direction.UP) && !state.is(Blocks.MAGMA_BLOCK);
    } else {
      return state.is(BlockTags.DIRT) || state.is(Blocks.FARMLAND) || state.is(Blocks.CLAY);
    }
  }

  @Override
  public BlockState getStateForPlacement(BlockPlaceContext context) {
    FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
    return super.getStateForPlacement(context) != null ? this.defaultBlockState().setValue(WATERLOGGED, fluidState.is(FluidTags.WATER) && fluidState.getAmount() == 8) : null;
  }

  @Override
  public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
    if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
      BlockState blockState = level.getBlockState(pos.below());
      return blockState.is(this) && blockState.getValue(HALF) == DoubleBlockHalf.LOWER;
    } else {
      BlockPos blockPos = pos.below();
      BlockPos blockPos2 = pos.above();
      if (state.getValue(WATERLOGGED)) {
        return super.canSurvive(state, level, pos) && level.getBlockState(blockPos).isFaceSturdy(level, blockPos, Direction.UP) && !level.getFluidState(blockPos2)
            .is(FluidTags.WATER);
      } else {
        return super.canSurvive(state, level, pos) && this.mayPlaceOn(level.getBlockState(blockPos), level, blockPos);
      }
    }
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    super.createBlockStateDefinition(builder);
    builder.add(WATERLOGGED);
  }

  @Override
  public FluidState getFluidState(BlockState state) {
    return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
  }
}
