package net.hibiscus.naturespirit.blocks;

import net.hibiscus.naturespirit.NaturesSpirit;
import java.util.List;
import net.hibiscus.naturespirit.registration.NSBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public interface NSCauldronBehavior {

  Identifier MILK_DISPATCHER_ID = NaturesSpirit.id("milk");
  Identifier CHEESE_DISPATCHER_ID = NaturesSpirit.id("cheese");

  CauldronInteraction.Dispatcher MILK_CAULDRON_BEHAVIOR = new CauldronInteraction.Dispatcher();
  CauldronInteraction FILL_WITH_MILK = (state, world, pos, player, hand, stack) -> emptyBucket(
          world,
          pos,
          player,
          hand,
          stack,
          NSBlocks.MILK_CAULDRON.get().defaultBlockState(),
          SoundEvents.BUCKET_EMPTY
  );
  CauldronInteraction.Dispatcher CHEESE_CAULDRON_BEHAVIOR = new CauldronInteraction.Dispatcher();
  CauldronInteraction FILL_WITH_CHEESE = (state, world, pos, player, hand, stack) -> emptyBucket(
          world,
          pos,
          player,
          hand,
          stack,
          NSBlocks.CHEESE_CAULDRON.get().defaultBlockState(),
          SoundEvents.BUCKET_EMPTY
  );

  private static InteractionResult emptyBucket(Level world, BlockPos pos, Player player, InteractionHand hand, ItemStack stack, BlockState newState, SoundEvent sound) {
    if (!world.isClientSide()) {
      Item itemUsed = stack.getItem();
      player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, new ItemStack(Items.BUCKET)));
      player.awardStat(Stats.FILL_CAULDRON);
      player.awardStat(Stats.ITEM_USED.get(itemUsed));
      world.setBlockAndUpdate(pos, newState);
      world.playSound(null, pos, sound, SoundSource.BLOCKS, 1.0F, 1.0F);
      world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
    }
    return InteractionResult.SUCCESS;
  }

  List<DispatcherEntry> DISPATCHERS = List.of(
          new DispatcherEntry(MILK_DISPATCHER_ID, MILK_CAULDRON_BEHAVIOR),
          new DispatcherEntry(CHEESE_DISPATCHER_ID, CHEESE_CAULDRON_BEHAVIOR)
  );

  List<InteractionEntry> INTERACTIONS = List.of(
          new InteractionEntry(MILK_DISPATCHER_ID, Items.BUCKET, (state, world, pos, player, hand, stack) ->
                  fillBucket(world, pos, player, hand, stack, new ItemStack(Items.MILK_BUCKET), SoundEvents.COW_MILK)),
          new InteractionEntry(MILK_DISPATCHER_ID, Items.LAVA_BUCKET, NSCauldronBehavior::fillWithLava),
          new InteractionEntry(MILK_DISPATCHER_ID, Items.WATER_BUCKET, NSCauldronBehavior::fillWithWater),
          new InteractionEntry(MILK_DISPATCHER_ID, Items.POWDER_SNOW_BUCKET, NSCauldronBehavior::fillWithPowderSnow),
          new InteractionEntry(CHEESE_DISPATCHER_ID, Items.BUCKET, (state, world, pos, player, hand, stack) ->
                  fillBucket(world, pos, player, hand, stack, new ItemStack(NSBlocks.CHEESE_BUCKET.get()), SoundEvents.BUCKET_FILL)),
          new InteractionEntry(CHEESE_DISPATCHER_ID, Items.LAVA_BUCKET, NSCauldronBehavior::fillWithLava),
          new InteractionEntry(CHEESE_DISPATCHER_ID, Items.WATER_BUCKET, NSCauldronBehavior::fillWithWater),
          new InteractionEntry(CHEESE_DISPATCHER_ID, Items.POWDER_SNOW_BUCKET, NSCauldronBehavior::fillWithPowderSnow)
  );

  private static InteractionResult fillBucket(Level world, BlockPos pos, Player player, InteractionHand hand, ItemStack stack, ItemStack filled, SoundEvent sound) {
    if (!world.isClientSide()) {
      Item itemUsed = stack.getItem();
      player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, filled));
      player.awardStat(Stats.USE_CAULDRON);
      player.awardStat(Stats.ITEM_USED.get(itemUsed));
      world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
      world.playSound(null, pos, sound, SoundSource.BLOCKS, 1.0F, 1.0F);
      world.gameEvent(null, GameEvent.FLUID_PICKUP, pos);
    }
    return InteractionResult.SUCCESS;
  }

  private static InteractionResult fillWithWater(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, ItemStack stack) {
    return emptyBucket(world, pos, player, hand, stack, Blocks.WATER_CAULDRON.defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, 3), SoundEvents.BUCKET_EMPTY);
  }

  private static InteractionResult fillWithLava(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, ItemStack stack) {
    return isUnderWater(world, pos) ? InteractionResult.CONSUME : emptyBucket(world, pos, player, hand, stack, Blocks.LAVA_CAULDRON.defaultBlockState(), SoundEvents.BUCKET_EMPTY_LAVA);
  }

  private static InteractionResult fillWithPowderSnow(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, ItemStack stack) {
    return isUnderWater(world, pos) ? InteractionResult.CONSUME : emptyBucket(world, pos, player, hand, stack, Blocks.POWDER_SNOW_CAULDRON.defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, 3), SoundEvents.BUCKET_EMPTY_POWDER_SNOW);
  }

  private static boolean isUnderWater(Level world, BlockPos pos) {
    return world.getFluidState(pos.above()).is(FluidTags.WATER);
  }

  record DispatcherEntry(Identifier id, CauldronInteraction.Dispatcher dispatcher) {
  }

  record InteractionEntry(Identifier dispatcherId, Item item, CauldronInteraction interaction) {
  }
}
