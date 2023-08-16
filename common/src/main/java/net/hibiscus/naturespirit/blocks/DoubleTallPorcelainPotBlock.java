package net.hibiscus.naturespirit.blocks;

import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Map;

public class DoubleTallPorcelainPotBlock extends Block {
   public static final float field_31095 = 3.0F;
   public static final EnumProperty <DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
   private static final Map <Block, Block> CONTENT_TO_POTTED = Maps.newHashMap();
   protected final VoxelShape SHAPE1;
   protected final VoxelShape SHAPE2 = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
   private final Block content;
   private final Block type;

   public DoubleTallPorcelainPotBlock(Block content, VoxelShape shape, @Nullable Block type, Properties settings) {
      super(settings);
      this.SHAPE1 = shape;
      this.content = content;
      this.type = type;
      CONTENT_TO_POTTED.put(content, this);
      this.registerDefaultState(this.stateDefinition.any().setValue(HALF, DoubleBlockHalf.LOWER));
   }

   protected static void onBreakInCreative(Level world, BlockPos pos, BlockState state, Player player) {
      DoubleBlockHalf doubleBlockHalf = state.getValue(HALF);
      if(doubleBlockHalf == DoubleBlockHalf.UPPER) {
         BlockPos blockPos = pos.below();
         BlockState blockState = world.getBlockState(blockPos);
         if(blockState.is(state.getBlock()) && blockState.getValue(HALF) == DoubleBlockHalf.LOWER) {
            world.setBlock(blockPos, Blocks.AIR.defaultBlockState(), 35);
            world.levelEvent(player, 2001, blockPos, Block.getId(blockState));
         }
      }

   }

   public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return state.getValue(HALF) == DoubleBlockHalf.UPPER ? SHAPE2 : SHAPE1;
   }

   public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return state.getValue(HALF) == DoubleBlockHalf.UPPER ? Shapes.empty() : SHAPE1;
   }

   public RenderShape getRenderShape(BlockState state) {
      return RenderShape.MODEL;
   }

   public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
      ItemStack itemStack = player.getItemInHand(hand);
      Item item = itemStack.getItem();
      BlockState blockState = (item instanceof BlockItem ? CONTENT_TO_POTTED.getOrDefault(((BlockItem) item).getBlock(),
              Blocks.AIR
      ) : Blocks.AIR).defaultBlockState();
      boolean bl = blockState.is(Blocks.AIR);
      boolean bl2 = this.isEmpty();
      if(bl != bl2 && bl2 && pos.getY() < world.getMaxBuildHeight() - 1 && world.getBlockState(pos.above()) == Blocks.AIR.defaultBlockState()) {
         world.setBlock(pos, blockState, 3);
         world.setBlock(pos.above(), blockState.setValue(HALF, DoubleBlockHalf.UPPER), 3);
         player.awardStat(Stats.POT_FLOWER);
         if(!player.getAbilities().instabuild) {
            itemStack.shrink(1);
         }
         world.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
         return InteractionResult.sidedSuccess(world.isClientSide);
      }
      else if(bl != bl2 && !bl2) {
         ItemStack itemStack2 = new ItemStack(this.content);
         if(itemStack.isEmpty()) {
            player.setItemInHand(hand, itemStack2);
         }
         else if(!player.addItem(itemStack2)) {
            player.drop(itemStack2, false);
         }
         if(state.getValue(HALF) == DoubleBlockHalf.LOWER) {
            world.setBlock(pos, type == null ? this.defaultBlockState() : type.defaultBlockState(), 3);
         }
         if(state.getValue(HALF) == DoubleBlockHalf.UPPER) {
            world.setBlock(pos.below(), type == null ? this.defaultBlockState() : type.defaultBlockState(), 3);
            world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
         }
         world.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
         return InteractionResult.sidedSuccess(world.isClientSide);
      }
      else {
         return InteractionResult.CONSUME;
      }
   }

   private boolean isEmpty() {
      return this.content == Blocks.AIR;
   }

   public ItemStack getCloneItemStack(BlockGetter world, BlockPos pos, BlockState state) {
      return this.isEmpty() ? super.getCloneItemStack(world, pos, state) : new ItemStack(this.content);
   }

   public Block getContent() {
      return this.content;
   }

   public Block getType() {
      return this.type;
   }

   protected void createBlockStateDefinition(StateDefinition.Builder <Block, BlockState> builder) {
      builder.add(HALF);
   }

   public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
      if(state.getValue(HALF) != DoubleBlockHalf.UPPER) {
         return super.canSurvive(state, world, pos);
      }
      else {
         BlockState blockState = world.getBlockState(pos.below());
         return blockState.is(this) && blockState.getValue(HALF) == DoubleBlockHalf.LOWER;
      }
   }

   public void playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
      if(!world.isClientSide) {
         if(player.isCreative()) {
            onBreakInCreative(world, pos, state, player);
         }
         else {
            dropResources(state, world, pos, null, player, player.getMainHandItem());
         }
      }

      super.playerWillDestroy(world, pos, state, player);
   }

   public void playerDestroy(Level world, Player player, BlockPos pos, BlockState state, @org.jetbrains.annotations.Nullable BlockEntity blockEntity, ItemStack tool) {
      super.playerDestroy(world, player, pos, Blocks.AIR.defaultBlockState(), blockEntity, tool);
   }

   public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
      DoubleBlockHalf doubleBlockHalf = state.getValue(HALF);
      if(direction.getAxis() == Direction.Axis.Y && doubleBlockHalf == DoubleBlockHalf.LOWER == (direction == Direction.UP) && ((!neighborState.is(
              this) || neighborState.getValue(HALF) == doubleBlockHalf) && this.getContent() != Blocks.AIR)) {
         return Blocks.AIR.defaultBlockState();
      }
      else {
         return doubleBlockHalf == DoubleBlockHalf.LOWER && direction == Direction.DOWN && !state.canSurvive(world,
                 pos
         ) ? Blocks.AIR.defaultBlockState() : super.updateShape(state,
                 direction,
                 neighborState,
                 world,
                 pos,
                 neighborPos
         );
      }
   }
}