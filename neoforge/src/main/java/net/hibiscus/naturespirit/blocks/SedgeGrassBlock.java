package net.hibiscus.naturespirit.blocks;

import net.hibiscus.naturespirit.registration.NSBlocks;
import net.hibiscus.naturespirit.registration.NSTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SedgeGrassBlock extends TallGrassBlock implements SimpleWaterloggedBlock {

  public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
  protected static final VoxelShape SHAPE = Block.box(2D, 0D, 2D, 14D, 16D, 14D);

  public SedgeGrassBlock(Properties settings) {
    super(settings);
    this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false));
  }

  @Override
  protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
    if (world.getFluidState(pos.above()).is(FluidTags.WATER)) {
      return floor.isFaceSturdy(world, pos, Direction.UP) && !floor.is(Blocks.MAGMA_BLOCK);
    } else {
      return floor.is(NSTags.Blocks.TURNIP_STEM_GROWS_ON) || floor.is(Blocks.FARMLAND);
    }
  }

  @Override
  public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
    return !useContext.getItemInHand().is(NSBlocks.AZOLLA_ITEM.get());
  }

  @Override
  public BlockState getStateForPlacement(BlockPlaceContext context) {
    FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
    return this.defaultBlockState().setValue(WATERLOGGED, fluidState.is(FluidTags.WATER) && fluidState.getAmount() == 8);
  }

  @Override
  public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
    DoublePlantBlock tallPlantBlock = (DoublePlantBlock) NSBlocks.TALL_SEDGE_GRASS.get();
    if (tallPlantBlock.defaultBlockState().canSurvive(world, pos) && world.isEmptyBlock(pos.above())) {
      DoublePlantBlock.placeAt(world, tallPlantBlock.defaultBlockState(), pos, 2);
    }
  }

  @Override
  public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
    BlockPos blockPos = pos.below();
    BlockPos blockPos2 = pos.above();
    if (state.getValue(WATERLOGGED)) {
      return super.canSurvive(state, level, pos) && level.getBlockState(blockPos).isFaceSturdy(level, blockPos, Direction.UP) && !level.getFluidState(blockPos2)
          .is(FluidTags.WATER);
    } else {
      return super.canSurvive(state, level, pos) && this.mayPlaceOn(level.getBlockState(blockPos), level, blockPos);
    }

  }

  @Override
  public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
    if (state.getValue(WATERLOGGED)) {
      world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
    }

    return direction == Direction.DOWN && !this.canSurvive(state, world, pos) ? Blocks.AIR.defaultBlockState()
        : super.updateShape(state, direction, neighborState, world, pos, neighborPos);
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    builder.add(WATERLOGGED);
  }

  @Override
  public FluidState getFluidState(BlockState state) {
    return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
  }
}
