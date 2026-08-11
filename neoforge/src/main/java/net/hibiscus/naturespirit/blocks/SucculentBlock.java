package net.hibiscus.naturespirit.blocks;

import com.mojang.serialization.MapCodec;
import net.hibiscus.naturespirit.registration.NSTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseCoralPlantTypeBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SupportType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SucculentBlock extends BaseCoralPlantTypeBlock implements BonemealableBlock {

  private static final VoxelShape SHAPE = Block.box(2D, 0D, 2D, 14D, 4D, 14D);

  public SucculentBlock(Properties settings) {
    super(settings);
    this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false));
  }

  @Override
  protected MapCodec<? extends BaseCoralPlantTypeBlock> codec() {
    return null;
  }

  @Override
  public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
    return SHAPE;
  }

  @Override
  public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
    if (!(entity instanceof LivingEntity) || entity.isShiftKeyDown() || entity.getType().is(NSTags.EntityTypes.CANT_SUCCULENT_SLOWED)) {
      return;
    }
    entity.makeStuckInBlock(state, new Vec3(0.8D, 0.75D, 0.8D));
  }

  @Override
  public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
    BlockPos blockPos = pos.below();
    return world.getBlockState(blockPos).isFaceSturdy(world, blockPos, Direction.UP, SupportType.CENTER) || world.getBlockState(blockPos)
        .is(NSTags.Blocks.SUCCULENT_VERTICAL_PLACEMENT_OVERRIDE);
  }

  @Override
  public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
    return true;
  }

  @Override
  public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
    return true;
  }

  @Override
  public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
    popResource(world, pos, new ItemStack(this));
  }
}

