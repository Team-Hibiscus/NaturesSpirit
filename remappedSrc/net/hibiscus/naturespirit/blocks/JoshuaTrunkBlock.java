package net.hibiscus.naturespirit.blocks;


import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class JoshuaTrunkBlock extends ConnectingBlock implements Waterloggable {
   public JoshuaTrunkBlock(Settings settings) {
      super(0.3125F, settings);
      this.setDefaultState(this.stateManager.getDefaultState().with(NORTH, false).with(EAST, false).with(SOUTH, false).with(WEST, false).with(UP, false).with(DOWN, false).with(WATERLOGGED, false));
   }

   public static final BooleanProperty WATERLOGGED;

   public BlockState getPlacementState(ItemPlacementContext ctx) {
      FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
      return this.withConnectionProperties(ctx.getWorld(), ctx.getBlockPos()).with(WATERLOGGED, fluidState.isIn(FluidTags.WATER) && fluidState.getLevel() == 8);
   }

   public BlockState withConnectionProperties(BlockView world, BlockPos pos) {
      BlockState blockState = world.getBlockState(pos.down());
      BlockState blockState2 = world.getBlockState(pos.up());
      BlockState blockState3 = world.getBlockState(pos.north());
      BlockState blockState4 = world.getBlockState(pos.east());
      BlockState blockState5 = world.getBlockState(pos.south());
      BlockState blockState6 = world.getBlockState(pos.west());
      return this.getDefaultState().with(DOWN,
              blockState.isIn(BlockTags.LEAVES) || blockState.getBlock() instanceof JoshuaTrunkBlock || blockState.isSideSolid(world, pos, Direction.UP, SideShapeType.CENTER)
      ).with(
              UP,
              blockState2.isIn(BlockTags.LEAVES) || blockState2.getBlock() instanceof JoshuaTrunkBlock || blockState2.isSideSolid(world, pos, Direction.DOWN, SideShapeType.CENTER)
      ).with(NORTH, blockState3.isIn(BlockTags.LEAVES) || blockState3.getBlock() instanceof JoshuaTrunkBlock || blockState3.isSideSolid(world, pos, Direction.SOUTH, SideShapeType.CENTER)).with(
              EAST,
              blockState4.isIn(BlockTags.LEAVES) || blockState4.getBlock() instanceof JoshuaTrunkBlock || blockState4.isSideSolid(world, pos, Direction.WEST, SideShapeType.CENTER)
      ).with(SOUTH, blockState5.isIn(BlockTags.LEAVES) || blockState5.getBlock() instanceof JoshuaTrunkBlock || blockState5.isSideSolid(world, pos, Direction.NORTH, SideShapeType.CENTER)).with(
              WEST,
              blockState6.isIn(BlockTags.LEAVES) || blockState6.getBlock() instanceof JoshuaTrunkBlock || blockState6.isSideSolid(world, pos, Direction.EAST, SideShapeType.CENTER)
      );
   }

   public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
      boolean bl = neighborState.isSideSolid(world, pos, direction.getOpposite(), SideShapeType.CENTER) || neighborState.getBlock() instanceof JoshuaTrunkBlock || neighborState.isIn(BlockTags.LEAVES);
      return state.with(FACING_PROPERTIES.get(direction), bl);
   }

   public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
      if(!state.canPlaceAt(world, pos)) {
         world.breakBlock(pos, true);
      }

   }

   public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
      return true;
   }

   protected void appendProperties(StateManager.Builder <Block, BlockState> builder) {
      builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN, WATERLOGGED);
   }

   public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
      return false;
   }

   public FluidState getFluidState(BlockState state) {
      return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
   }

   static {
      WATERLOGGED = Properties.WATERLOGGED;
   }

   @Override protected MapCodec <? extends ConnectingBlock> getCodec() {
      return null;
   }
}