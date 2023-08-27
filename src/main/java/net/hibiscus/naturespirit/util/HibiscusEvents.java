package net.hibiscus.naturespirit.util;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.hibiscus.naturespirit.blocks.JoshuaTrunkBlock;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusWoods;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.BlockState;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.event.GameEvent;

public class HibiscusEvents {
   public static void registerEvents() {
      UseBlockCallback.EVENT.register(((player, world, hand, hitResult) -> {
         BlockPos blockPos = hitResult.getBlockPos();
         BlockState blockState = world.getBlockState(blockPos);
         if(blockState.isOf(HibiscusWoods.JOSHUA[0]) && player.getStackInHand(hand).isIn(ItemTags.AXES)) {
            BlockState blockState2 = HibiscusWoods.JOSHUA[1].getDefaultState()
                                                            .with(JoshuaTrunkBlock.DOWN, blockState.get(JoshuaTrunkBlock.DOWN))
                                                            .with(JoshuaTrunkBlock.UP, blockState.get(JoshuaTrunkBlock.UP))
                                                            .with(JoshuaTrunkBlock.NORTH, blockState.get(JoshuaTrunkBlock.NORTH))
                                                            .with(JoshuaTrunkBlock.SOUTH, blockState.get(JoshuaTrunkBlock.SOUTH))
                                                            .with(JoshuaTrunkBlock.EAST, blockState.get(JoshuaTrunkBlock.EAST))
                                                            .with(JoshuaTrunkBlock.WEST, blockState.get(JoshuaTrunkBlock.WEST))
                                                            .with(JoshuaTrunkBlock.WATERLOGGED, blockState.get(JoshuaTrunkBlock.WATERLOGGED));
            world.playSound(player, blockPos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (player instanceof ServerPlayerEntity) {
               Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity)player, blockPos, player.getStackInHand(hand));
            }

            world.setBlockState(blockPos, blockState2, 11);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Emitter.of(player, (BlockState)blockState2));
            if (player != null && !player.isCreative() && !player.isSpectator()) {
               player.getStackInHand(hand).damage(1, player, (p) -> {
                  p.sendToolBreakStatus(hand);
               });
            }

            return ActionResult.success(world.isClient);
         }
         return ActionResult.PASS;
      }));
   }
}
