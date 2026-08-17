package net.hibiscus.naturespirit.blocks;

import java.util.List;
import java.util.function.Supplier;
import net.hibiscus.naturespirit.NaturesSpirit;
import net.hibiscus.naturespirit.registration.NSBlocks;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.cauldron.CauldronInteractions;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public interface NSCauldronBehavior {

  Identifier MILK_DISPATCHER_ID = NaturesSpirit.id("milk");
  Identifier CHEESE_DISPATCHER_ID = NaturesSpirit.id("cheese");
  Identifier EMPTY_DISPATCHER_ID = Identifier.withDefaultNamespace("empty");

  CauldronInteraction.Dispatcher MILK_CAULDRON_BEHAVIOR = newDispatcherWithDefaults();
  CauldronInteraction.Dispatcher CHEESE_CAULDRON_BEHAVIOR = newDispatcherWithDefaults();

  CauldronInteraction FILL_WITH_MILK = (state, world, pos, player, hand, stack) ->
          convertCauldron(world, pos, player, hand, stack, NSBlocks.MILK_CAULDRON.get().defaultBlockState());
  CauldronInteraction FILL_WITH_CHEESE = (state, world, pos, player, hand, stack) ->
          convertCauldron(world, pos, player, hand, stack, NSBlocks.CHEESE_CAULDRON.get().defaultBlockState());

  private static CauldronInteraction.Dispatcher newDispatcherWithDefaults() {
    CauldronInteraction.Dispatcher dispatcher = new CauldronInteraction.Dispatcher();
    CauldronInteractions.addDefaultInteractions(dispatcher);
    return dispatcher;
  }

  private static InteractionResult convertCauldron(Level world, BlockPos pos, Player player, InteractionHand hand, ItemStack stack, BlockState newState) {
    world.playSound(player, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
    if (player instanceof ServerPlayer serverPlayer) {
      CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, stack);
    }
    world.setBlock(pos, newState, 11);
    world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, newState));
    if (!player.isCreative() && !player.isSpectator()) {
      player.setItemInHand(hand, new ItemStack(Items.BUCKET));
    }
    return world.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.SUCCESS_SERVER;
  }

  List<DispatcherEntry> DISPATCHERS = List.of(
          new DispatcherEntry(MILK_DISPATCHER_ID, MILK_CAULDRON_BEHAVIOR),
          new DispatcherEntry(CHEESE_DISPATCHER_ID, CHEESE_CAULDRON_BEHAVIOR)
  );

  List<InteractionEntry> INTERACTIONS = List.of(
          new InteractionEntry(MILK_DISPATCHER_ID, () -> Items.BUCKET, (state, world, pos, player, hand, stack) ->
                  CauldronInteractions.fillBucket(state, world, pos, player, hand, stack, new ItemStack(Items.MILK_BUCKET), s -> true, SoundEvents.COW_MILK)),
          new InteractionEntry(MILK_DISPATCHER_ID, NSBlocks.CHEESE_BUCKET, FILL_WITH_CHEESE),
          new InteractionEntry(CHEESE_DISPATCHER_ID, () -> Items.BUCKET, (state, world, pos, player, hand, stack) ->
                  CauldronInteractions.fillBucket(state, world, pos, player, hand, stack, new ItemStack(NSBlocks.CHEESE_BUCKET.get()), s -> true, SoundEvents.BUCKET_FILL)),
          new InteractionEntry(CHEESE_DISPATCHER_ID, () -> Items.MILK_BUCKET, FILL_WITH_MILK),
          new InteractionEntry(EMPTY_DISPATCHER_ID, () -> Items.MILK_BUCKET, FILL_WITH_MILK),
          new InteractionEntry(EMPTY_DISPATCHER_ID, NSBlocks.CHEESE_BUCKET, FILL_WITH_CHEESE)
  );

  record DispatcherEntry(Identifier id, CauldronInteraction.Dispatcher dispatcher) {
  }

  record InteractionEntry(Identifier dispatcherId, Supplier<? extends Item> item, CauldronInteraction interaction) {
  }
}
