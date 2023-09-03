package net.hibiscus.naturespirit.blocks;


import net.minecraft.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SupportType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import java.util.Iterator;

public class JoshuaTrunkBlock extends PipeBlock implements SimpleWaterloggedBlock{
   public JoshuaTrunkBlock(Properties settings) {
      super(0.3125F, settings);
      this.registerDefaultState(this.stateDefinition.any()
              .setValue(NORTH, false).setValue(EAST, false).setValue(SOUTH, false).setValue(WEST, false).setValue(UP, false).setValue(DOWN, false)
              .setValue(WATERLOGGED, false));
   }
   public static final BooleanProperty WATERLOGGED;

   public BlockState getStateForPlacement(BlockPlaceContext ctx) {
      FluidState fluidState = ctx.getLevel().getFluidState(ctx.getClickedPos());
      return this.withConnectionProperties(ctx.getLevel(), ctx.getClickedPos()).setValue(WATERLOGGED, fluidState.is(FluidTags.WATER) && fluidState.getAmount() == 8);
   }

   public BlockState withConnectionProperties(BlockGetter world, BlockPos pos) {
      BlockState blockState = world.getBlockState(pos.below());
      BlockState blockState2 = world.getBlockState(pos.above());
      BlockState blockState3 = world.getBlockState(pos.north());
      BlockState blockState4 = world.getBlockState(pos.east());
      BlockState blockState5 = world.getBlockState(pos.south());
      BlockState blockState6 = world.getBlockState(pos.west());
      return this.defaultBlockState()
              .setValue(DOWN, blockState.is(BlockTags.LEAVES) || blockState.getBlock() instanceof JoshuaTrunkBlock || blockState.isFaceSturdy(world, pos, Direction.UP, SupportType.CENTER) )
              .setValue(UP, blockState2.is(BlockTags.LEAVES) || blockState2.getBlock() instanceof JoshuaTrunkBlock || blockState2.isFaceSturdy(world, pos, Direction.DOWN, SupportType.CENTER))
              .setValue(NORTH, blockState3.is(BlockTags.LEAVES) || blockState3.getBlock() instanceof JoshuaTrunkBlock || blockState3.isFaceSturdy(world, pos, Direction.SOUTH, SupportType.CENTER))
              .setValue(EAST, blockState4.is(BlockTags.LEAVES) || blockState4.getBlock() instanceof JoshuaTrunkBlock || blockState4.isFaceSturdy(world, pos, Direction.WEST, SupportType.CENTER))
              .setValue(SOUTH, blockState5.is(BlockTags.LEAVES) || blockState5.getBlock() instanceof JoshuaTrunkBlock || blockState5.isFaceSturdy(world, pos, Direction.NORTH, SupportType.CENTER))
              .setValue(WEST, blockState6.is(BlockTags.LEAVES) || blockState6.getBlock() instanceof JoshuaTrunkBlock || blockState6.isFaceSturdy(world, pos, Direction.EAST, SupportType.CENTER));
   }

   public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
         boolean bl = neighborState.isFaceSturdy(world, pos, direction.getOpposite(), SupportType.CENTER) || neighborState.getBlock() instanceof JoshuaTrunkBlock || neighborState.is(BlockTags.LEAVES);
         return state.setValue(PROPERTY_BY_DIRECTION.get(direction), bl);
   }

   public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
      if(!state.canSurvive(world, pos)) {
         world.destroyBlock(pos, true);
      }

   }

   public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
      return true;
   }

   protected void createBlockStateDefinition(StateDefinition.Builder <Block, BlockState> builder) {
      builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN, WATERLOGGED);
   }

   public boolean isPathfindable(BlockState state, BlockGetter world, BlockPos pos, PathComputationType type) {
      return false;
   }
   public FluidState getFluidState(BlockState state) {
      return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
   }

   static {
      WATERLOGGED = BlockStateProperties.WATERLOGGED;
   }
}