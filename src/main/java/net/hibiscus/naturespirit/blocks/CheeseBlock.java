package net.hibiscus.naturespirit.blocks;

import net.hibiscus.naturespirit.registration.HibiscusBlocksAndItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidDrainable;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;

import java.util.Optional;

public class CheeseBlock extends Block implements FluidDrainable {
   public CheeseBlock(Settings settings) {
      super(settings);
   }

   @Override public ItemStack tryDrainFluid(WorldAccess world, BlockPos pos, BlockState state) {
      world.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
      if (!world.isClient()) {
         world.syncWorldEvent(2001, pos, Block.getRawIdFromState(state));
      }

      return new ItemStack(HibiscusBlocksAndItems.CHEESE_BUCKET);
   }

   @Override public Optional <SoundEvent> getBucketFillSound() {
      return Optional.of(SoundEvents.ITEM_BUCKET_FILL);
   }
}
