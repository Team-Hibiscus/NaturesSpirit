package net.hibiscus.naturespirit.util;

import net.hibiscus.naturespirit.registration.NSBlocks;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public final class NSEvents {

    private NSEvents() {
    }

    public static InteractionResult onCauldronBucketUse(Player player, Level level, InteractionHand hand, BlockPos pos, BlockState state, ItemStack stack) {
        if (state.is(BlockTags.CAULDRONS) && stack.is(Items.MILK_BUCKET) && !state.is(NSBlocks.MILK_CAULDRON.get())) {
            return convertCauldron(player, level, hand, pos, stack, NSBlocks.MILK_CAULDRON.get().defaultBlockState());
        }
        if (state.is(BlockTags.CAULDRONS) && stack.is(NSBlocks.CHEESE_BUCKET.get()) && !state.is(NSBlocks.CHEESE_CAULDRON.get())) {
            return convertCauldron(player, level, hand, pos, stack, NSBlocks.CHEESE_CAULDRON.get().defaultBlockState());
        }
        return InteractionResult.PASS;
    }

    private static InteractionResult convertCauldron(Player player, Level level, InteractionHand hand, BlockPos pos, ItemStack stack, BlockState newState) {
        level.playSound(player, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
        if (player instanceof ServerPlayer serverPlayer) {
            CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, stack);
        }
        level.setBlock(pos, newState, 11);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, newState));
        if (!player.isCreative() && !player.isSpectator()) {
            player.setItemInHand(hand, new ItemStack(Items.BUCKET));
        }
        return level.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.SUCCESS_SERVER;
    }
}
