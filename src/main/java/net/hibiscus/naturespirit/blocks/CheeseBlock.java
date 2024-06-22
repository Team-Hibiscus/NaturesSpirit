package net.hibiscus.naturespirit.blocks;

import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusMiscBlocks;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;

import java.util.Optional;

public class CheeseBlock extends CakeBlock implements FluidDrainable {
   public CheeseBlock(Settings settings) {
      super(settings);
   }

   @Override public ItemStack tryDrainFluid(PlayerEntity player, WorldAccess world, BlockPos pos, BlockState state) {
      if (world.getBlockState(pos).get(BITES) == 0) {
         world.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
         if (!world.isClient()) {
            world.syncWorldEvent(2001, pos, Block.getRawIdFromState(state));
         }

         return new ItemStack(HibiscusMiscBlocks.CHEESE_BUCKET);
      }
      return  new ItemStack(Items.BUCKET);
   }

   @Override public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
      if (world.isClient) {
         if (tryEat(world, pos, state, player).isAccepted()) {
            return ActionResult.SUCCESS;
         }

         if (player.getStackInHand(Hand.MAIN_HAND).isEmpty()) {
            return ActionResult.CONSUME;
         }
      }

      return tryEat(world, pos, state, player);
   }
   protected static ActionResult tryEat(WorldAccess world, BlockPos pos, BlockState state, PlayerEntity player) {
      if (!player.canConsume(false)) {
         return ActionResult.PASS;
      } else {
         player.incrementStat(NatureSpirit.EAT_CHEESE);
         player.getHungerManager().add(2, 0.1F);
         int i = (Integer)state.get(BITES);
         world.emitGameEvent(player, GameEvent.EAT, pos);
         if (i < 6) {
            world.setBlockState(pos, (BlockState)state.with(BITES, i + 1), 3);
         } else {
            world.removeBlock(pos, false);
            world.emitGameEvent(player, GameEvent.BLOCK_DESTROY, pos);
         }

         return ActionResult.SUCCESS;
      }
   }

   @Override public Optional <SoundEvent> getBucketFillSound() {
      return Optional.of(SoundEvents.ITEM_BUCKET_FILL);
   }
}
