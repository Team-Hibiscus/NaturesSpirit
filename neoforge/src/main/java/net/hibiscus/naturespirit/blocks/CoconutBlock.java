package net.hibiscus.naturespirit.blocks;

import com.mojang.serialization.MapCodec;
import net.hibiscus.naturespirit.registration.NSBlocks;
import net.hibiscus.naturespirit.registration.NSCriteria;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CoconutBlock extends FallingBlock implements BonemealableBlock, SimpleWaterloggedBlock {

  public static final DirectionProperty FACING;
  public static final BooleanProperty FILLED;
  public static final VoxelShape SHAPE;
  public static final VoxelShape NORTH_SHAPE;
  public static final VoxelShape SOUTH_SHAPE;
  public static final BooleanProperty WATERLOGGED;
  public static final VoxelShape WEST_SHAPE;
  public static final VoxelShape EAST_SHAPE;
  public static final VoxelShape CEILING_SHAPE;
  private static final float FALLING_BLOCK_ENTITY_DAMAGE_MULTIPLIER = 1.2F;
  private static final int FALLING_BLOCK_ENTITY_MAX_DAMAGE = 20;

  public CoconutBlock(Properties settings) {
    super(settings);
    this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP).setValue(WATERLOGGED, false).setValue(FILLED, true));
  }

  @Override
  protected MapCodec<? extends FallingBlock> codec() {
    return null;
  }

  @Override
  public BlockState getStateForPlacement(BlockPlaceContext ctx) {
    Direction direction = ctx.getClickedFace();
    FluidState fluidState = ctx.getLevel().getFluidState(ctx.getClickedPos());
    return this.defaultBlockState().setValue(FACING, direction).setValue(WATERLOGGED, fluidState.is(FluidTags.WATER) && fluidState.getAmount() == 8);
  }

  @Override
  protected void falling(FallingBlockEntity entity) {
    entity.setHurtsEntities(FALLING_BLOCK_ENTITY_DAMAGE_MULTIPLIER, FALLING_BLOCK_ENTITY_MAX_DAMAGE);
  }

  public static boolean isFree(BlockState state) {
    return state.isAir() || state.is(BlockTags.FIRE) || state.liquid() || state.canBeReplaced();
  }

  @Override
  public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
    return world.getBlockState(pos.relative(state.getValue(FACING).getOpposite())).isFaceSturdy(world, pos.relative(state.getValue(FACING).getOpposite()), state.getValue(FACING));
  }

  @Override
  public void onProjectileHit(Level world, BlockState state, BlockHitResult hit, Projectile projectile) {
    Entity entity = projectile.getOwner();
    if (entity instanceof ServerPlayer serverPlayerEntity) {
      NSCriteria.COCONUT_HIT_CRITERION.get().trigger(serverPlayerEntity, projectile);
    }
    if (isFree(world.getBlockState(hit.getBlockPos().below())) && !state.getValue(WATERLOGGED)) {
      world.setBlockAndUpdate(hit.getBlockPos(), state.setValue(FACING, Direction.UP));
    } else {
      world.destroyBlock(hit.getBlockPos(), true);
    }
  }

  @Override
  public InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
    ItemStack itemStack = player.getItemInHand(player.getUsedItemHand());
    boolean bl = state.getValue(FILLED);
    Item item = itemStack.getItem();
    if (itemStack.is(Items.BUCKET) && bl) {
      itemStack.shrink(1);
      world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.COW_MILK, SoundSource.BLOCKS, 1.0F, 1.0F);
      if (itemStack.isEmpty()) {
        player.setItemInHand(player.getUsedItemHand(), new ItemStack(Items.MILK_BUCKET));
      } else if (!player.getInventory().add(new ItemStack(Items.MILK_BUCKET))) {
        player.drop(new ItemStack(Items.MILK_BUCKET), false);
      }

      world.setBlockAndUpdate(pos, state.setValue(FILLED, false));
      world.gameEvent(player, GameEvent.FLUID_PICKUP, pos);
    }
    if (!world.isClientSide() && bl) {
      player.awardStat(Stats.ITEM_USED.get(item));
    }
    return super.useWithoutItem(state, world, pos, player, hit);

  }

  @Override
  public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
    if (isFree(world.getBlockState(pos.below())) && pos.getY() >= world.getMinBuildHeight() && world.getBlockState(pos.relative(state.getValue(FACING).getOpposite())).isAir()) {
      state = state.setValue(FACING, Direction.UP);
      FallingBlockEntity fallingBlockEntity = FallingBlockEntity.fall(world, pos, state);
      this.falling(fallingBlockEntity);
    }
    if (!isFree(world.getBlockState(pos.below())) && world.getBlockState(pos.relative(state.getValue(FACING).getOpposite())).isAir()) {
      state.setValue(FACING, Direction.UP);
    }
  }

  @Override
  public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
    return switch (state.getValue(FACING)) {
      case DOWN -> CEILING_SHAPE;
      case UP -> SHAPE;
      case NORTH -> NORTH_SHAPE;
      case SOUTH -> SOUTH_SHAPE;
      case WEST -> WEST_SHAPE;
      case EAST -> EAST_SHAPE;
    };
  }

  @Override
  public FluidState getFluidState(BlockState state) {
    return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
  }

  @Override
  public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
  }

  @Override
  public BlockState rotate(BlockState state, Rotation rotation) {
    return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
  }

  @Override
  public BlockState mirror(BlockState state, Mirror mirror) {
    return state.setValue(FACING, mirror.mirror(state.getValue(FACING)));
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    builder.add(FACING, FILLED, WATERLOGGED);
  }


  @Override
  public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
    return state.getValue(FACING) == Direction.UP;
  }

  @Override
  public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
    return true;
  }

  @Override
  public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
    world.setBlockAndUpdate(pos, NSBlocks.COCONUT_SPROUT.get().defaultBlockState());
  }

  static {
    WATERLOGGED = BlockStateProperties.WATERLOGGED;
    FACING = BlockStateProperties.FACING;
    FILLED = BooleanProperty.create("filled");
    SHAPE = Block.box(3.0, 0.0, 3.0, 13.0, 10.0, 13.0);
    EAST_SHAPE = Block.box(0.0, 3.0, 3.0, 10.0, 13.0, 13.0);
    WEST_SHAPE = Block.box(6.0, 3.0, 3.0, 16.0, 13.0, 13.0);
    NORTH_SHAPE = Block.box(3.0, 3.0, 6.0, 13.0, 13.0, 16.0);
    SOUTH_SHAPE = Block.box(3.0, 3.0, 0.0, 13.0, 13.0, 10.0);
    CEILING_SHAPE = Block.box(3.0, 3.0, 3.0, 13.0, 13.0, 13.0);
  }
}
