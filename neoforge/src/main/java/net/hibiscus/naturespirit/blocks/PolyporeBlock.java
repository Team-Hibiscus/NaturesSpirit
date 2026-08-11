package net.hibiscus.naturespirit.blocks;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.hibiscus.naturespirit.registration.NSTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

public class PolyporeBlock extends MushroomBlock {

  public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
  private static final Map<Direction, VoxelShape> FACING_TO_SHAPE = Maps.newEnumMap(
      ImmutableMap.of(Direction.NORTH, Block.box(0.0, 4.0, 10.0, 16.0, 12.0, 16.0), Direction.SOUTH, Block.box(0.0, 4.0, 0.0, 16.0, 12.0, 6.0),
          Direction.WEST, Block.box(10.0, 4.0, 0.0, 16.0, 12.0, 16.0), Direction.EAST, Block.box(0.0, 4.0, 0.0, 6.0, 12.0, 16.0)));

  public PolyporeBlock(Properties settings, ResourceKey<ConfiguredFeature<?, ?>> featureKey) {
    super(featureKey, settings);
    this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
  }

  @Override
  public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
    Direction direction = state.getValue(FACING);
    BlockPos blockPos = pos.relative(direction.getOpposite());
    BlockState blockState = world.getBlockState(blockPos);
    return blockState.isFaceSturdy(world, blockPos, direction, SupportType.CENTER) || blockState.is(NSTags.Blocks.SUCCULENT_HORIZONTAL_PLACEMENT_OVERRIDE);
  }

  @Override
  public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
    return FACING_TO_SHAPE.get(state.getValue(FACING));
  }

  @Override
  public BlockState rotate(BlockState state, Rotation rotation) {
    return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
  }

  @Override
  public BlockState mirror(BlockState state, Mirror mirror) {
    return state.rotate(mirror.getRotation(state.getValue(FACING)));
  }

  @Override
  public BlockState getStateForPlacement(BlockPlaceContext ctx) {
    BlockState blockState = super.getStateForPlacement(ctx);
    Level worldView = ctx.getLevel();
    BlockPos blockPos = ctx.getClickedPos();
    for (Direction direction : ctx.getNearestLookingDirections()) {
      if (!direction.getAxis().isHorizontal() || !(blockState = blockState.setValue(FACING, direction.getOpposite())).canSurvive(worldView, blockPos)) {
        continue;
      }
      return blockState;
    }
    return null;
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    builder.add(FACING);
  }
}
