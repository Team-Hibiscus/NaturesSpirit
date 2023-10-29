package net.hibiscus.naturespirit.blocks;

import net.hibiscus.naturespirit.registration.block_registration.HibiscusWoods;
import net.minecraft.block.*;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;

public class YoungCoconutBlock extends CoconutBlock {
   public static final VoxelShape SHAPE;
   public static final VoxelShape NORTH_SHAPE;
   public static final VoxelShape SOUTH_SHAPE;
   public static final VoxelShape WEST_SHAPE;
   public static final VoxelShape EAST_SHAPE;
   public static final VoxelShape CEILING_SHAPE;
   private static final float FALLING_BLOCK_ENTITY_DAMAGE_MULTIPLIER = 1.1F;
   private static final int FALLING_BLOCK_ENTITY_MAX_DAMAGE = 15;
   public YoungCoconutBlock(Settings settings) {
      super(settings);
      this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.UP).with(FILLED, true).with(WATERLOGGED, false));
   }

   protected void configureFallingBlockEntity(FallingBlockEntity entity) {
      entity.setHurtEntities(FALLING_BLOCK_ENTITY_DAMAGE_MULTIPLIER, FALLING_BLOCK_ENTITY_MAX_DAMAGE);
   }
   public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
      ItemStack itemStack = player.getStackInHand(hand);
      boolean bl = state.get(FILLED);
      Item item = itemStack.getItem();
      if(itemStack.isOf(Items.BUCKET) && bl) {
         itemStack.decrement(1); world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
         if(itemStack.isEmpty()) {
            player.setStackInHand(hand, new ItemStack(Items.WATER_BUCKET));
         }
         else if(!player.getInventory().insertStack(new ItemStack(Items.WATER_BUCKET))) {
            player.dropItem(new ItemStack(Items.WATER_BUCKET), false);
         }

         state.with(FILLED, false);
         world.emitGameEvent(player, GameEvent.FLUID_PICKUP, pos);
      } if(!world.isClient() && bl) {
         player.incrementStat(Stats.USED.getOrCreateStat(item));
      } return ActionResult.PASS;

   }

   public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
      return switch(state.get(FACING)) {
         case DOWN -> CEILING_SHAPE;
         case UP -> SHAPE;
         case NORTH -> NORTH_SHAPE;
         case SOUTH -> SOUTH_SHAPE;
         case WEST -> WEST_SHAPE;
         case EAST -> EAST_SHAPE;
      };
   }

   @Override public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
      return true;
   }

   @Override public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
      return true;
   }

   @Override public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
      world.setBlockState(pos, HibiscusWoods.COCONUT_BLOCK.getDefaultState().with(CoconutBlock.FACING, state.get(FACING)));
   }
   static {
      SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 8.0, 12.0);
      EAST_SHAPE = Block.createCuboidShape(0.0, 4.0, 4.0, 8.0, 12.0, 12.0);
      WEST_SHAPE = Block.createCuboidShape(8.0, 4.0, 4.0, 16.0, 12.0, 12.0);
      NORTH_SHAPE = Block.createCuboidShape(4.0, 4.0, 8.0, 12.0, 12.0, 16.0);
      SOUTH_SHAPE = Block.createCuboidShape(4.0, 4.0, 0.0, 12.0, 12.0, 8.0);
      CEILING_SHAPE = Block.createCuboidShape(4.0, 4.0, 4.0, 12.0, 12.0, 12.0);
   }
}
