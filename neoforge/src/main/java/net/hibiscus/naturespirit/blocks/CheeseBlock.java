package net.hibiscus.naturespirit.blocks;

import net.hibiscus.naturespirit.registration.NSBlocks;
import net.hibiscus.naturespirit.registration.NSStatTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Optional;

public class CheeseBlock extends CakeBlock implements BucketPickup {
  public CheeseBlock(Properties settings) {
    super(settings);
  }

  @Override
  public ItemStack pickupBlock(Player player, LevelAccessor world, BlockPos pos, BlockState state) {
    if (world.getBlockState(pos).getValue(BITES) == 0) {
      world.setBlock(pos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL_IMMEDIATE);
      if (!world.isClientSide()) {
        world.levelEvent(LevelEvent.PARTICLES_DESTROY_BLOCK, pos, Block.getId(state));
      }

      return new ItemStack(NSBlocks.CHEESE_BUCKET.get());
    }
    return new ItemStack(Items.BUCKET);
  }

  protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
    return stack.is(Items.BUCKET) && state.getValue(BITES) == 0 ? ItemInteractionResult.SKIP_DEFAULT_BLOCK_INTERACTION : super.useItemOn(stack, state, world, pos, player, hand, hit);
  }
  @Override
  public InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
    if (world.isClientSide) {
      if (eat(world, pos, state, player).consumesAction()) {
        return InteractionResult.SUCCESS;
      }

      if (player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
        return InteractionResult.CONSUME;
      }
    }

    return eat(world, pos, state, player);
  }

  protected static InteractionResult eat(LevelAccessor world, BlockPos pos, BlockState state, Player player) {
    if (!player.canEat(false)) {
      return InteractionResult.PASS;
    } else {
      player.awardStat(NSStatTypes.EAT_CHEESE.get());
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