package net.hibiscus.naturespirit.blocks;

import com.google.common.collect.Maps;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;

import javax.annotation.Nullable;
import java.util.Map;

public class DoubleTallPorcelainPotBlock extends Block {
   public static final float field_31095 = 3.0F;
   public static final EnumProperty <DoubleBlockHalf> HALF = Properties.DOUBLE_BLOCK_HALF;
   private static final Map <Block, Block> CONTENT_TO_POTTED = Maps.newHashMap();
   protected final VoxelShape SHAPE1;
   protected final VoxelShape SHAPE2 = Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
   private final Block content;
   private final Block type;

   public DoubleTallPorcelainPotBlock(Block content, VoxelShape shape, @Nullable Block type, Settings settings) {
      super(settings);
      this.SHAPE1 = shape;
      this.content = content;
      this.type = type;
      CONTENT_TO_POTTED.put(content, this);
      this.setDefaultState(this.stateManager.getDefaultState().with(HALF, DoubleBlockHalf.LOWER));
   }

   protected static void onBreakInCreative(World world, BlockPos pos, BlockState state, PlayerEntity player) {
      DoubleBlockHalf doubleBlockHalf = state.get(HALF);
      if(doubleBlockHalf == DoubleBlockHalf.UPPER) {
         BlockPos blockPos = pos.down();
         BlockState blockState = world.getBlockState(blockPos);
         if(blockState.isOf(state.getBlock()) && blockState.get(HALF) == DoubleBlockHalf.LOWER) {
            world.setBlockState(blockPos, Blocks.AIR.getDefaultState(), 35);
            world.syncWorldEvent(player, 2001, blockPos, Block.getRawIdFromState(blockState));
         }
      }

   }

   public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
      return state.get(HALF) == DoubleBlockHalf.UPPER ? SHAPE2 : SHAPE1;
   }

   public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
      return state.get(HALF) == DoubleBlockHalf.UPPER ? VoxelShapes.empty() : SHAPE1;
   }

   public BlockRenderType getRenderType(BlockState state) {
      return BlockRenderType.MODEL;
   }

   public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
      ItemStack itemStack = player.getStackInHand(hand);
      Item item = itemStack.getItem();
      BlockState blockState = (item instanceof BlockItem ? CONTENT_TO_POTTED.getOrDefault(((BlockItem) item).getBlock(),
              Blocks.AIR
      ) : Blocks.AIR).getDefaultState();
      boolean bl = blockState.isOf(Blocks.AIR);
      boolean bl2 = this.isEmpty();
      if(bl != bl2 && bl2 && pos.getY() < world.getTopY() - 1 && world.getBlockState(pos.up()) == Blocks.AIR.getDefaultState()) {
         world.setBlockState(pos, blockState, 3);
         world.setBlockState(pos.up(), blockState.with(HALF, DoubleBlockHalf.UPPER), 3);
         player.incrementStat(Stats.POT_FLOWER);
         if(!player.getAbilities().creativeMode) {
            itemStack.decrement(1);
         }
         world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);
         return ActionResult.success(world.isClient);
      }
      else if(bl != bl2 && !bl2) {
         ItemStack itemStack2 = new ItemStack(this.content);
         if(itemStack.isEmpty()) {
            player.setStackInHand(hand, itemStack2);
         }
         else if(!player.giveItemStack(itemStack2)) {
            player.dropItem(itemStack2, false);
         }
         if(state.get(HALF) == DoubleBlockHalf.LOWER) {
            world.setBlockState(pos, type == null ? this.getDefaultState() : type.getDefaultState(), 3);
         }
         if(state.get(HALF) == DoubleBlockHalf.UPPER) {
            world.setBlockState(pos.down(), type == null ? this.getDefaultState() : type.getDefaultState(), 3);
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
         }
         world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);
         return ActionResult.success(world.isClient);
      }
      else {
         return ActionResult.CONSUME;
      }
   }

   private boolean isEmpty() {
      return this.content == Blocks.AIR;
   }

   public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
      return this.isEmpty() ? super.getPickStack(world, pos, state) : new ItemStack(this.content);
   }

   public Block getContent() {
      return this.content;
   }

   public Block getType() {
      return this.type;
   }

   protected void appendProperties(StateManager.Builder <Block, BlockState> builder) {
      builder.add(HALF);
   }

   public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
      if(state.get(HALF) != DoubleBlockHalf.UPPER) {
         return super.canPlaceAt(state, world, pos);
      }
      else {
         BlockState blockState = world.getBlockState(pos.down());
         return blockState.isOf(this) && blockState.get(HALF) == DoubleBlockHalf.LOWER;
      }
   }

   public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
      if(!world.isClient) {
         if(player.isCreative()) {
            onBreakInCreative(world, pos, state, player);
         }
         else {
            dropStacks(state, world, pos, null, player, player.getMainHandStack());
         }
      }

      super.onBreak(world, pos, state, player);
   }

   public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @org.jetbrains.annotations.Nullable BlockEntity blockEntity, ItemStack tool) {
      super.afterBreak(world, player, pos, Blocks.AIR.getDefaultState(), blockEntity, tool);
   }

   public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
      DoubleBlockHalf doubleBlockHalf = state.get(HALF);
      if(direction.getAxis() == Direction.Axis.Y && doubleBlockHalf == DoubleBlockHalf.LOWER == (direction == Direction.UP) && ((!neighborState.isOf(
              this) || neighborState.get(HALF) == doubleBlockHalf) && this.getContent() != Blocks.AIR)) {
         return Blocks.AIR.getDefaultState();
      }
      else {
         return doubleBlockHalf == DoubleBlockHalf.LOWER && direction == Direction.DOWN && !state.canPlaceAt(world,
                 pos
         ) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state,
                 direction,
                 neighborState,
                 world,
                 pos,
                 neighborPos
         );
      }
   }
}