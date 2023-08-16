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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Map;

public class PorcelainPotBlock extends Block {
   public static final float field_31095 = 3.0F;
   private static final Map <Block, Block> CONTENT_TO_POTTED = Maps.newHashMap();
   protected final VoxelShape SHAPE;
   private final Block content;
   private final Block type;

   public PorcelainPotBlock(Block content, VoxelShape shape, @Nullable Block type, BlockBehaviour.Properties settings) {
      super(settings);
      this.SHAPE = shape;
      this.content = content;
      this.type = type;
      CONTENT_TO_POTTED.put(content, this);
   }

   public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return SHAPE;
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
      if(bl != bl2) {
         if(bl2) {
            world.setBlock(pos, blockState, 3);
            player.awardStat(Stats.POT_FLOWER);
            if(!player.getAbilities().instabuild) {
               itemStack.shrink(1);
            }
         }
         else {
            ItemStack itemStack2 = new ItemStack(this.content);
            if(itemStack.isEmpty()) {
               player.setItemInHand(hand, itemStack2);
            }
            else if(!player.addItem(itemStack2)) {
               player.drop(itemStack2, false);
            }

            world.setBlock(pos, type == null ? this.defaultBlockState() : type.defaultBlockState(), 3);
         }

         world.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
         return InteractionResult.sidedSuccess(world.isClientSide);
      }
      else {
         return InteractionResult.CONSUME;
      }
   }

   public ItemStack getCloneItemStack(BlockGetter world, BlockPos pos, BlockState state) {
      return this.isEmpty() ? super.getCloneItemStack(world, pos, state) : new ItemStack(this.content);
   }

   private boolean isEmpty() {
      return this.content == Blocks.AIR;
   }

   public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
      return direction == Direction.DOWN && !state.canSurvive(world,
              pos
      ) ? Blocks.AIR.defaultBlockState() : super.updateShape(state,
              direction,
              neighborState,
              world,
              pos,
              neighborPos
      );
   }

   public Block getContent() {
      return this.content;
   }

   public Block getType() {
      return this.type;
   }

   public boolean isPathfindable(BlockState state, BlockGetter world, BlockPos pos, PathComputationType type) {
      return false;
   }
}