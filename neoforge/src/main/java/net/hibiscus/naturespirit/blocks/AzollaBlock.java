package net.hibiscus.naturespirit.blocks;


import net.hibiscus.naturespirit.registration.NSTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PinkPetalsBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AzollaBlock extends PinkPetalsBlock {

  public static final VoxelShape COLLISION_SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 1, 16.0);

  public AzollaBlock(Properties settings) {
    super(settings);
    this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(AMOUNT, 1));
  }

  @Override
  public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
    if (!state.canSurvive(world, pos)) {
      world.destroyBlock(pos, true);
    }
  }

  @Override
  protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
    FluidState fluidStateUp = world.getFluidState(pos.above());
    return fluidStateUp.isEmpty() && (super.mayPlaceOn(floor, world, pos) || floor.is(Blocks.FARMLAND) || isWater(world, pos));
  }

  @Override
  public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
    if (context instanceof EntityCollisionContext) {
      Entity entity = ((EntityCollisionContext) context).getEntity();
      if (entity != null && entity.getType().is(NSTags.EntityTypes.IMPERMEABLE_TO_AZOLLA)) {
        return COLLISION_SHAPE;
      }
    }
    return Shapes.empty();
  }

  @Override
  public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
    return true;
  }

  private boolean isWater(BlockGetter world, BlockPos pos) {
    return world.getFluidState(pos).isSourceOfType(Fluids.WATER);
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    super.createBlockStateDefinition(builder);
  }
}
