package net.hibiscus.naturespirit.blocks;


import com.mojang.serialization.MapCodec;
import net.hibiscus.naturespirit.registration.NSBlocks;
import net.hibiscus.naturespirit.registration.NSTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
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

import org.jetbrains.annotations.Nullable;

public class BranchingTrunkBlock extends PipeBlock implements SimpleWaterloggedBlock {

  public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

  public static final BooleanProperty SHEARED = BooleanProperty.create("sheared");
  public BranchingTrunkBlock(Properties settings) {
    super(10.0F, settings);
    this.registerDefaultState(
        this.stateDefinition.any().setValue(NORTH, false).setValue(EAST, false).setValue(SOUTH, false).setValue(WEST, false).setValue(UP, false).setValue(DOWN, false).setValue(WATERLOGGED, false).setValue(SHEARED, false));
  }

  @Override
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
    return this.defaultBlockState().setValue(
        DOWN,
        blockState.is(BlockTags.LEAVES) || (blockState.getBlock() instanceof BranchingTrunkBlock && !blockState.getValue(SHEARED)) || blockState.isFaceSturdy(world, pos,
            Direction.UP, SupportType.CENTER)).setValue(
        UP,
        blockState2.is(BlockTags.LEAVES) || (blockState2.getBlock() instanceof BranchingTrunkBlock && !blockState2.getValue(SHEARED)) || blockState2.isFaceSturdy(world, pos,
            Direction.DOWN, SupportType.CENTER) || blockState2.is(
            NSTags.Blocks.SUCCULENTS)).setValue(
        NORTH, blockState3.is(BlockTags.LEAVES) || (blockState3.getBlock() instanceof BranchingTrunkBlock && !blockState3.getValue(SHEARED)) || blockState3.isFaceSturdy(world, pos,
            Direction.SOUTH, SupportType.CENTER)).setValue(
        EAST,
        blockState4.is(BlockTags.LEAVES) || (blockState4.getBlock() instanceof BranchingTrunkBlock && !blockState4.getValue(SHEARED)) || blockState4.isFaceSturdy(world, pos,
            Direction.WEST, SupportType.CENTER)).setValue(
        SOUTH, blockState5.is(BlockTags.LEAVES) || (blockState5.getBlock() instanceof BranchingTrunkBlock && !blockState5.getValue(SHEARED)) || blockState5.isFaceSturdy(world, pos,
            Direction.NORTH, SupportType.CENTER)).setValue(
        WEST,
        blockState6.is(BlockTags.LEAVES) || (blockState6.getBlock() instanceof BranchingTrunkBlock && !blockState6.getValue(SHEARED)) || blockState6.isFaceSturdy(world, pos,
            Direction.EAST, SupportType.CENTER)
    );
  }

  public BlockState withHorizontalConnectingProperties(BlockGetter world, BlockPos pos) {
    BlockState blockState3 = world.getBlockState(pos.north());
    BlockState blockState4 = world.getBlockState(pos.east());
    BlockState blockState5 = world.getBlockState(pos.south());
    BlockState blockState6 = world.getBlockState(pos.west());
    BlockState blockState = world.getBlockState(pos).getBlock() instanceof BranchingTrunkBlock ? world.getBlockState(pos) : this.defaultBlockState();
    return blockState.setValue(NORTH,
            blockState3.is(BlockTags.LEAVES) || blockState3.getBlock() instanceof BranchingTrunkBlock || blockState3.isFaceSturdy(world, pos, Direction.SOUTH, SupportType.CENTER))
        .setValue(
            EAST,
            blockState4.is(BlockTags.LEAVES) || blockState4.getBlock() instanceof BranchingTrunkBlock || blockState4.isFaceSturdy(world, pos, Direction.WEST, SupportType.CENTER)
        ).setValue(SOUTH, blockState5.is(BlockTags.LEAVES) || blockState5.getBlock() instanceof BranchingTrunkBlock || blockState5.isFaceSturdy(world, pos, Direction.NORTH,
            SupportType.CENTER)).setValue(
            WEST,
            blockState6.is(BlockTags.LEAVES) || blockState6.getBlock() instanceof BranchingTrunkBlock || blockState6.isFaceSturdy(world, pos, Direction.EAST, SupportType.CENTER)
        );
  }

  public BlockState withConnectionPropertiesVertical(BlockGetter world, BlockPos pos) {
    BlockState blockState = world.getBlockState(pos.below());
    BlockState blockState2 = world.getBlockState(pos.above());
    BlockState blockState3 = world.getBlockState(pos).getBlock() instanceof BranchingTrunkBlock ? world.getBlockState(pos) : this.defaultBlockState();
    return blockState3.setValue(DOWN,
        blockState.is(BlockTags.LEAVES) || blockState.getBlock() instanceof BranchingTrunkBlock || blockState.isFaceSturdy(world, pos, Direction.UP, SupportType.CENTER)
    ).setValue(
        UP,
        blockState2.is(BlockTags.LEAVES) || blockState2.getBlock() instanceof BranchingTrunkBlock || blockState2.isFaceSturdy(world, pos, Direction.DOWN, SupportType.CENTER)
    );
  }

  @Override
  public BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess ticks, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
    boolean bl =
        neighborState.isFaceSturdy(world, pos, direction.getOpposite(), SupportType.CENTER) || neighborState.getBlock() instanceof BranchingTrunkBlock || neighborState.is(
            BlockTags.LEAVES) || (direction == Direction.UP && neighborState.is(
            NSTags.Blocks.SUCCULENTS));
    return state.setValue(PROPERTY_BY_DIRECTION.get(direction), bl);
  }

  @Override
  public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
    if (!state.canSurvive(world, pos)) {
      world.destroyBlock(pos, true);
    }

  }

  @Override
  public InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
    if (itemStack.getItem() instanceof AxeItem || itemStack.is(ItemTags.AXES) || itemStack.is(NSTags.Items.C_AXES)) {
      BlockState strippedState = getStrippedState(state);
      if (strippedState != null) {
        if (player instanceof ServerPlayer serverPlayer) {
          CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, itemStack);
        }
        world.playSound(player, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
        world.setBlock(pos, strippedState, 11);
        world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, strippedState));
        itemStack.hurtAndBreak(1, player, hand.asEquipmentSlot());
        return world.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.SUCCESS_SERVER;
      }
    }
    return super.useItemOn(itemStack, state, world, pos, player, hand, hit);
  }

  private static @Nullable BlockState getStrippedState(BlockState state) {
    if (state.is(NSBlocks.ALLUAUDIA.get())) {
      return NSBlocks.STRIPPED_ALLUAUDIA.get().withPropertiesOf(state);
    }
    if (state.is(NSBlocks.JOSHUA.getLog().get())) {
      return NSBlocks.JOSHUA.getStrippedLog().get().withPropertiesOf(state);
    }
    return null;
  }

  @Override
  public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
    return true;
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN, WATERLOGGED, SHEARED);
  }

  public boolean isPathfindable(BlockState state, BlockGetter world, BlockPos pos, PathComputationType type) {
    return false;
  }

  @Override
  public FluidState getFluidState(BlockState state) {
    return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
  }

  @Override
  protected MapCodec<? extends PipeBlock> codec() {
    return null;
  }
}
