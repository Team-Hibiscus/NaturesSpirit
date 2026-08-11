package net.hibiscus.naturespirit.blocks;

import com.mojang.serialization.MapCodec;
import net.hibiscus.naturespirit.registration.NSBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.RodBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEvent.Context;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;

public class OliveBranchBlock extends RodBlock implements BonemealableBlock {

  public OliveBranchBlock(Properties settings) {
    super(settings);
    this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(FACING, Direction.UP).setValue(AGE, 0));
  }
  public static final IntegerProperty AGE = BlockStateProperties.AGE_3;

  public BlockState getStateForPlacement(BlockPlaceContext ctx) {
    Direction direction = ctx.getClickedFace();
    BlockState blockState = ctx.getLevel().getBlockState(ctx.getClickedPos().relative(direction.getOpposite()));
    return blockState.is(this) && blockState.getValue(FACING) == direction ? (BlockState)this.defaultBlockState().setValue(FACING, direction.getOpposite()) : (BlockState)this.defaultBlockState().setValue(FACING, direction);
  }

  @Override
  protected MapCodec<? extends RodBlock> codec() {
    return null;
  }

  @Override
  public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
    return state.getValue(AGE) < 3 || ((world.getBlockState(pos.relative(state.getValue(FACING), 1)).is(Blocks.AIR) || world.getBlockState(pos.relative(state.getValue(FACING).getOpposite(), 1)).is(Blocks.AIR)) && state.getValue(AGE) == 3);
  }

  @Override
  public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
    return true;
  }
  protected boolean isPathfindable(BlockState state, PathComputationType type) {
    return type == PathComputationType.AIR;
  }

  protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
    return stack.is(Items.BONE_MEAL) ? ItemInteractionResult.SKIP_DEFAULT_BLOCK_INTERACTION : super.useItemOn(stack, state, world, pos, player, hand, hit);
  }

  @Override
  public InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
    int i = state.getValue(AGE);
    boolean bl = i == 3;
    if (i > 1) {
      int j = 1 + world.random.nextInt(2);
      popResource(world, pos, new ItemStack(NSBlocks.OLIVES.get(), j + (bl ? 1 : 0)));
      world.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + world.random.nextFloat() * 0.4F);
      world.setBlockAndUpdate(pos, state.setValue(AGE, 0));
      world.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
      return InteractionResult.sidedSuccess(world.isClientSide());
    }
    return super.useWithoutItem(state, world, pos, player, hit);

  }

  @Override
  public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
    int i = state.getValue(AGE);
    if (world.getRawBrightness(pos, 0) >= 9 && i < 3) {
      if (random.nextInt(5) == 0) {
        state = state.setValue(AGE, i + 1);
        world.setBlock(pos, state, Block.UPDATE_CLIENTS);
        world.gameEvent(GameEvent.BLOCK_CHANGE, pos, Context.of(state));
      }
    }
  }

  @Override
  public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
    if (state.getValue(AGE) > 2) {
      BlockPos adjacentPos = pos.relative(state.getValue(FACING), 1);
      BlockState adjacentState = world.getBlockState(adjacentPos);
      BlockPos adjacentPos2 = pos.relative(state.getValue(FACING).getOpposite(), 1);
      BlockState adjacentState2 = world.getBlockState(adjacentPos2);
      if (adjacentState.is(Blocks.AIR)) {
        world.setBlock(adjacentPos, state.setValue(AGE, 0), Block.UPDATE_CLIENTS);
      } else
      if (adjacentState2.is(Blocks.AIR)) {
        world.setBlock(adjacentPos2, state.setValue(AGE, 0), Block.UPDATE_CLIENTS);
      }
    } else {
      world.setBlockAndUpdate(pos, state.setValue(AGE, state.getValue(AGE) + 1));
    }
  }
  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    builder.add(AGE, FACING);
  }
}

