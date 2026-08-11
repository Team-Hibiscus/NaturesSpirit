package net.hibiscus.naturespirit.blocks;


import net.hibiscus.naturespirit.registration.NSTags;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.SupportType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;

public class GrowingBranchingTrunkBlock extends BranchingTrunkBlock implements BonemealableBlock {


  public GrowingBranchingTrunkBlock(Properties settings) {
    super(settings);
    this.registerDefaultState(
        this.stateDefinition.any().setValue(NORTH, false).setValue(EAST, false).setValue(SOUTH, false).setValue(WEST, false).setValue(UP, false).setValue(DOWN, false).setValue(WATERLOGGED, false)
            .setValue(SHEARED, false));
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN, WATERLOGGED, SHEARED);
  }

  @Override
  public boolean isPathfindable(BlockState state, BlockGetter world, BlockPos pos, PathComputationType type) {
    return false;
  }

  @Override
  public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
    boolean isAnythingButDown = PipeBlock.PROPERTY_BY_DIRECTION.values().stream()
        .anyMatch(booleanProperty -> booleanProperty != DOWN && state.getValue(booleanProperty));
    return !isAnythingButDown && (world.getBlockState(pos.above()).isAir() || world.getBlockState(pos.east()).isAir() || world.getBlockState(pos.west()).isAir()
        || world.getBlockState(pos.north()).isAir() || world.getBlockState(pos.south()).isAir());
  }

  @Override
  public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
    boolean bl =
        neighborState.isFaceSturdy(world, pos, direction.getOpposite(), SupportType.CENTER) || neighborState.getBlock() instanceof BranchingTrunkBlock || neighborState.is(
            BlockTags.LEAVES) || (direction == Direction.UP && neighborState.is(
            NSTags.Blocks.SUCCULENTS));
    return !state.getValue(SHEARED) ? state.setValue(PROPERTY_BY_DIRECTION.get(direction), bl) : state;
  }

  @Override
  public ItemInteractionResult useItemOn(ItemStack itemStack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
    if (itemStack.is(Items.SHEARS) && !state.getValue(SHEARED)) {
      if (player instanceof ServerPlayer) {
        CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, pos, itemStack);
      }

      world.playSound(player, pos, SoundEvents.GROWING_PLANT_CROP, SoundSource.BLOCKS, 1.0F, 1.0F);
      BlockState blockState2 = state.setValue(SHEARED, true);
      world.setBlockAndUpdate(pos, blockState2);
      world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, blockState2));
      player.getItemInHand(hand).hurtAndBreak(1, player, LivingEntity.getSlotForHand(player.getUsedItemHand()));

      return ItemInteractionResult.sidedSuccess(world.isClientSide);
    }
    return super.useItemOn(itemStack, state, world, pos, player, hand, hit);
  }

  @Override
  public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
    return random.nextFloat() < 0.35f;
  }

  @Override
  public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
    Direction direction = Direction.getRandom(random);
    if (direction == Direction.DOWN) {
      direction = Direction.UP;
    }
    if (world.getBlockState(pos.relative(direction)).isAir() && (world.getBlockState(pos.relative(direction).below()).isAir() || direction == Direction.UP)) {
      world.setBlockAndUpdate(pos.relative(direction), withConnectionProperties(world, pos.relative(direction)));

      Direction direction2 = Direction.getRandom(random);
      if (world.getBlockState(pos.relative(direction).relative(direction2)).isAir() && direction2 != Direction.DOWN) {

        world.setBlockAndUpdate(pos.relative(direction).relative(direction2), withConnectionProperties(world, pos.relative(direction).relative(direction2)));

        if (random.nextFloat() < 0.3F) {
          world.setBlockAndUpdate(pos.relative(direction).relative(direction2).above(), withConnectionProperties(world, pos.relative(direction).relative(direction2).above()));
        }
      }
    }
  }
}
