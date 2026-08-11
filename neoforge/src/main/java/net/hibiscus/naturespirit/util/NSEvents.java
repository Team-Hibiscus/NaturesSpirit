package net.hibiscus.naturespirit.util;

import net.hibiscus.naturespirit.NatureSpirit;
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
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.util.TriState;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = NatureSpirit.MOD_ID)
public class NSEvents {
    @SubscribeEvent
    public static void interactEvent(PlayerInteractEvent.RightClickBlock event) {
        BlockPos blockPos = event.getPos();
        BlockState blockState = event.getLevel().getBlockState(blockPos);
        Level world = event.getLevel();
        Player player = event.getEntity();
        InteractionHand hand = event.getHand();
        if (blockState.is(BlockTags.CAULDRONS) && event.getItemStack().is(Items.MILK_BUCKET) && !blockState.is(NSBlocks.MILK_CAULDRON.get())) {
            event.setUseItem(TriState.FALSE);
            world.playSound(player, blockPos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
            if (player instanceof ServerPlayer) {
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, blockPos, event.getItemStack());
            }
            world.setBlock(blockPos, NSBlocks.MILK_CAULDRON.get().defaultBlockState(), 11);
            world.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, NSBlocks.MILK_CAULDRON.get().defaultBlockState()));
            if (!player.isCreative() && !player.isSpectator()) {
                player.setItemInHand(hand, new ItemStack(Items.BUCKET));
            }
            event.setCancellationResult(InteractionResult.sidedSuccess(world.isClientSide));
            event.setCanceled(true);
        }
        if (blockState.is(BlockTags.CAULDRONS) && event.getItemStack().is(NSBlocks.CHEESE_BUCKET.get()) && !blockState.is(NSBlocks.CHEESE_CAULDRON.get())) {
            world.playSound(player, blockPos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
            if (player instanceof ServerPlayer) {
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, blockPos, event.getItemStack());
            }
            world.setBlock(blockPos, NSBlocks.CHEESE_CAULDRON.get().defaultBlockState(), 11);
            world.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, NSBlocks.CHEESE_CAULDRON.get().defaultBlockState()));
            if (!player.isCreative() && !player.isSpectator()) {
                player.setItemInHand(hand, new ItemStack(Items.BUCKET));
            }
            event.setCancellationResult(InteractionResult.sidedSuccess(world.isClientSide));
            event.setCanceled(true);
        }
    }
}
