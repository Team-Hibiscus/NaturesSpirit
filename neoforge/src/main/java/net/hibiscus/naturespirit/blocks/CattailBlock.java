package net.hibiscus.naturespirit.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CattailBlock extends DoublePlantBlock implements SimpleWaterloggedBlock, BonemealableBlock {

  public static final EnumProperty<DoubleBlockHalf> HALF;
  public static final BooleanProperty WATERLOGGED;

  protected static final VoxelShape SHAPE;

  static {
    WATERLOGGED = BlockStateProperties.WATERLOGGED;
    HALF = DoublePlantBlock.HALF;
    SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
  }

  public CattailBlock(Properties properties) {
    super(properties);
    this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false).setValue(HALF, DoubleBlockHalf.LOWER));
  }

  public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
    return false;
  }

  public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
    return true;
  }

  public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
    return true;
  }

  public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
    popResource(serverLevel, blockPos, new ItemStack(this));
  }

  protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
    if (level.getFluidState(pos.above()).is(FluidTags.WATER)) {
      return state.isFaceSturdy(level, pos, Direction.UP) && !state.is(Blocks.MAGMA_BLOCK);
    } else {
      return state.is(BlockTags.DIRT) || state.is(Blocks.FARMLAND) || state.is(Blocks.SAND) || state.is(Blocks.RED_SAND);
    }
  }

  public BlockState getStateForPlacement(BlockPlaceContext context) {
    FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
    return super.getStateForPlacement(context) != null ? this.defaultBlockState().setValue(WATERLOGGED, fluidState.is(FluidTags.WATER) && fluidState.getAmount() == 8) : null;
  }

  public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
    if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
      BlockState blockState = level.getBlockState(pos.below());
      return blockState.is(this) && blockState.getValue(HALF) == DoubleBlockHalf.LOWER;
    } else {
      BlockPos blockPos = pos.below();
      BlockPos blockPos2 = pos.above();
      if (state.getValue(WATERLOGGED)) {
        return super.canSurvive(state, level, pos) && this.mayPlaceOn(level.getBlockState(blockPos), level, blockPos) && !level.getFluidState(blockPos2).is(FluidTags.WATER);
      } else {
        return super.canSurvive(state, level, pos) && this.mayPlaceOn(level.getBlockState(blockPos), level, blockPos);
      }
    }
  }

  public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
    if (state.getValue(WATERLOGGED)) {
      world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
    }

    return direction == Direction.DOWN && !this.canSurvive(state, world, pos) ? Blocks.AIR.defaultBlockState()
        : super.updateShape(state, direction, neighborState, world, pos, neighborPos);
  }

  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    builder.add(WATERLOGGED);
    builder.add(HALF);
  }

  public FluidState getFluidState(BlockState state) {
    return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
  }
}
