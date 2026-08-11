package net.hibiscus.naturespirit.blocks;

import com.mojang.serialization.MapCodec;
import net.hibiscus.naturespirit.registration.NSBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class LotusFlowerBlock extends BushBlock implements BonemealableBlock {

  protected static final VoxelShape SHAPE = Block.box(2D, 0D, 2D, 14D, 1.5D, 14D);

  public LotusFlowerBlock(Properties properties) {
    super(properties);
  }

  @Override
  protected MapCodec<? extends BushBlock> codec() {
    return null;
  }

  @Override
  public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
    super.entityInside(state, world, pos, entity);

    if (!world.isClientSide) {
      if (entity instanceof Boat) {
        world.destroyBlock(new BlockPos(pos), true, entity);
      }
      BlockState blockstate = world.getBlockState(pos.below());
      if (blockstate.is(NSBlocks.LOTUS_STEM.get()) && isEntityAbove(pos, entity) && !isPowered(world, pos)) {
        world.scheduleTick(pos, this, 4);
      }
    }

  }


  private int getRedstonePower(LevelReader world, BlockPos pos) {
    BlockPos.MutableBlockPos mutable = pos.mutable();

    BlockState blockState;
    do {
      mutable.move(Direction.DOWN);
      blockState = world.getBlockState(mutable);
      if (world.hasNeighborSignal(mutable)) {
        return world.getBestNeighborSignal(mutable);
      }
    } while (blockState.is(NSBlocks.LOTUS_STEM.get()));

    return 0;
  }


  public boolean isPowered(LevelReader world, BlockPos pos) {
    return getRedstonePower(world, pos) > 0;
  }

  @Override
  public boolean hasAnalogOutputSignal(BlockState state) {
    return true;
  }

  @Override
  public int getAnalogOutputSignal(BlockState state, Level world, BlockPos pos) {
    return getRedstonePower(world, pos);
  }

  @Override
  public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
    super.tick(state, world, pos, random);

    BlockState blockstate = world.getBlockState(pos.below());
    if (blockstate.is(NSBlocks.LOTUS_STEM.get()) && isEntityAbove(pos, world.getNearestPlayer(pos.getX(), pos.getY(), pos.getZ(), 1.25D, false)) && !isPowered(world, pos)) {
      world.destroyBlock(pos, false);
      world.setBlockAndUpdate(pos.below(), this.defaultBlockState());
    }
  }

  private static boolean isEntityAbove(BlockPos pos, @Nullable Entity entity) {
    if (entity == null) {
      return false;
    }
    return entity.onGround() && entity.position().y > (double) ((float) pos.getY()) && entity.isShiftKeyDown();
  }

  @Override
  public ItemStack getCloneItemStack(LevelReader world, BlockPos pos, BlockState state) {
    return new ItemStack(NSBlocks.LOTUS_FLOWER_ITEM.get());
  }

  @Override
  public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
    return SHAPE;
  }

  @Override
  protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
    FluidState fluidState = world.getFluidState(pos);
    FluidState fluidState2 = world.getFluidState(pos.above());
    return ((fluidState.getType() == Fluids.WATER || floor.isFaceSturdy(world, pos, Direction.UP, SupportType.CENTER)) && fluidState2.getType() == Fluids.EMPTY) || floor.is(
        NSBlocks.LOTUS_STEM.get());
  }

  @Override
  public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
    return world.getBlockState(pos.above()).isAir() && !world.getBlockState(pos.below()).is(Blocks.WATER) && !isPowered(world, pos);
  }

  @Override
  public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
    return true;
  }

  @Override
  public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
    Player player = world.getNearestPlayer(pos.getX(), pos.getY(), pos.getZ(), 1.25D, false);
    if (player != null) {
      player.move(MoverType.SHULKER_BOX, new Vec3(0D, 1.01D, 0D));
    }

    if (world.getBlockState(pos.below()).is(NSBlocks.LOTUS_STEM.get())) {
      LotusStemBlock lotusStemBlock = (LotusStemBlock) world.getBlockState(pos.below()).getBlock();
      lotusStemBlock.grow(world, random, pos.below(), world.getBlockState(pos.below()));
    } else {
      world.setBlockAndUpdate(pos, NSBlocks.LOTUS_STEM.get().defaultBlockState().setValue(LotusStemBlock.WATERLOGGED, false));
      world.setBlockAndUpdate(pos.above(), this.defaultBlockState());
    }

  }
}
