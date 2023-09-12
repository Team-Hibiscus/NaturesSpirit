package net.hibiscus.naturespirit.blocks;

import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.registration.HibiscusBlocksAndItems;
import net.minecraft.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import java.util.Optional;

public class CheeseBlock extends CakeBlock implements BucketPickup {
   public CheeseBlock(Properties settings) {
      super(settings);
   }

   @Override public ItemStack pickupBlock(LevelAccessor world, BlockPos pos, BlockState state) {
      if (world.getBlockState(pos).getValue(BITES) == 0) {
         world.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
         if (!world.isClientSide()) {
            world.levelEvent(2001, pos, Block.getId(state));
         }

         return new ItemStack(HibiscusBlocksAndItems.CHEESE_BUCKET);
      }
      return null;
   }

   @Override public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
      ItemStack itemStack = player.getItemInHand(hand);

      if (world.isClientSide) {
         if (eat(world, pos, state, player).consumesAction()) {
            return InteractionResult.SUCCESS;
         }

         if (itemStack.isEmpty()) {
            return InteractionResult.CONSUME;
         }
      }

      return eat(world, pos, state, player);
   }
   protected static InteractionResult eat(LevelAccessor world, BlockPos pos, BlockState state, Player player) {
      if (!player.canEat(false)) {
         return InteractionResult.PASS;
      } else {
         player.awardStat(NatureSpirit.EAT_CHEESE);
         player.getFoodData().eat(2, 0.1F);
         int i = (Integer)state.getValue(BITES);
         world.gameEvent(player, GameEvent.EAT, pos);
         if (i < 6) {
            world.setBlock(pos, (BlockState)state.setValue(BITES, i + 1), 3);
         } else {
            world.removeBlock(pos, false);
            world.gameEvent(player, GameEvent.BLOCK_DESTROY, pos);
         }

         return InteractionResult.SUCCESS;
      }
   }

   @Override public Optional <SoundEvent> getPickupSound() {
      return Optional.of(SoundEvents.BUCKET_FILL);
   }
}
