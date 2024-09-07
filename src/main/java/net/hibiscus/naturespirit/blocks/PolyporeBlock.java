package net.hibiscus.naturespirit.blocks;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.hibiscus.naturespirit.registration.HibiscusTags;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.RegistryKey;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.Map;

public class PolyporeBlock extends MushroomPlantBlock {
   public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
   private static final Map <Direction, VoxelShape> FACING_TO_SHAPE = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH, Block.createCuboidShape(0.0, 4.0, 10.0, 16.0, 12.0, 16.0), Direction.SOUTH, Block.createCuboidShape(0.0, 4.0, 0.0, 16.0, 12.0, 6.0), Direction.WEST, Block.createCuboidShape(10.0, 4.0, 0.0, 16.0, 12.0, 16.0), Direction.EAST, Block.createCuboidShape(0.0, 4.0, 0.0, 6.0, 12.0, 16.0)));

   public PolyporeBlock(Settings settings, RegistryKey <ConfiguredFeature <?, ?>> featureKey) {
      super(featureKey, settings);
      this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH)));
   }

   public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
      Direction direction = state.get(FACING);
      BlockPos blockPos = pos.offset(direction.getOpposite());
      BlockState blockState = world.getBlockState(blockPos);
      return blockState.isSideSolid(world, blockPos, direction, SideShapeType.CENTER) || blockState.isIn(HibiscusTags.Blocks.SUCCULENT_HORIZONTAL_PLACEMENT_OVERRIDE);
   }

   @Override
   public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
      return FACING_TO_SHAPE.get(state.get(FACING));
   }
   @Override
   public BlockState rotate(BlockState state, BlockRotation rotation) {
      return state.with(FACING, rotation.rotate(state.get(FACING)));
   }

   @Override
   public BlockState mirror(BlockState state, BlockMirror mirror) {
      return state.rotate(mirror.getRotation(state.get(FACING)));
   }
   public BlockState getPlacementState(ItemPlacementContext ctx) {
      BlockState blockState = super.getPlacementState(ctx);
      World worldView = ctx.getWorld();
      BlockPos blockPos = ctx.getBlockPos();
      for (Direction direction : ctx.getPlacementDirections()) {
         if (!direction.getAxis().isHorizontal() || !(blockState = blockState.with(FACING, direction.getOpposite())).canPlaceAt(worldView, blockPos)) continue;
         return blockState;
      }
      return null;
   }
   @Override
   protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
      builder.add(FACING);
   }
}
