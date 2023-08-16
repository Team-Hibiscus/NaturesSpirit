package net.hibiscus.naturespirit.util;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.hibiscus.naturespirit.blocks.HibiscusBlocks;
import net.hibiscus.naturespirit.blocks.JoshuaTrunkBlock;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class HibiscusEvents {
   public static void registerEvents() {
      UseBlockCallback.EVENT.register(((player, world, hand, hitResult) -> {
         BlockPos blockPos = hitResult.getBlockPos();
         BlockState blockState = world.getBlockState(blockPos);
         if(blockState.is(HibiscusBlocks.JOSHUA[0]) && player.getItemInHand(hand).is(ItemTags.AXES)) {
            BlockState blockState2 = HibiscusBlocks.JOSHUA[1].defaultBlockState()
                    .setValue(JoshuaTrunkBlock.DOWN, blockState.getValue(JoshuaTrunkBlock.DOWN))
                    .setValue(JoshuaTrunkBlock.UP, blockState.getValue(JoshuaTrunkBlock.UP))
                    .setValue(JoshuaTrunkBlock.NORTH, blockState.getValue(JoshuaTrunkBlock.NORTH))
                    .setValue(JoshuaTrunkBlock.SOUTH, blockState.getValue(JoshuaTrunkBlock.SOUTH))
                    .setValue(JoshuaTrunkBlock.EAST, blockState.getValue(JoshuaTrunkBlock.EAST))
                    .setValue(JoshuaTrunkBlock.WEST, blockState.getValue(JoshuaTrunkBlock.WEST))
                    .setValue(JoshuaTrunkBlock.WATERLOGGED, blockState.getValue(JoshuaTrunkBlock.WATERLOGGED));
            world.playSound(player, blockPos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
            if (player instanceof ServerPlayer) {
               CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, blockPos, player.getItemInHand(hand));
            }

            world.setBlock(blockPos, blockState2, 11);
            world.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, (BlockState)blockState2));
            if (player != null && !player.isCreative() && !player.isSpectator()) {
               player.getItemInHand(hand).hurtAndBreak(1, player, (p) -> {
                  p.broadcastBreakEvent(hand);
               });
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
         }
         return InteractionResult.PASS;
      }));
   }
}
