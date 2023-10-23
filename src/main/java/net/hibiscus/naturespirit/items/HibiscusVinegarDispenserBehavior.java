package net.hibiscus.naturespirit.items;

import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusMiscBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;

public class HibiscusVinegarDispenserBehavior extends FallibleItemDispenserBehavior {
   protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
      ServerWorld serverWorld = pointer.getWorld();
      if (!serverWorld.isClient()) {
         BlockPos blockPos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
         this.setSuccess(tryReaction(serverWorld, blockPos));
         if (this.isSuccess()) {
            ItemStack itemStack2 = new ItemStack(stack.getRecipeRemainder().getItem());
            DispenserBlockEntity blockEntity = pointer.getBlockEntity();
               if (addToAlikeSlot(blockEntity, itemStack2) == -1) {
                  if (blockEntity.addToFirstFreeSlot(itemStack2) == -1) spawnItem(serverWorld, itemStack2, 0, pointer.getBlockState().get(DispenserBlock.FACING).getOpposite(), blockPos.toCenterPos());
               }
            stack.decrement(1);
         }
      }

      return stack;
   }

   public int addToAlikeSlot(DispenserBlockEntity blockEntity, ItemStack stack) {
      for(int i = 0; i < 9; ++i) {
         if ((blockEntity.getStack(i)).isOf(stack.getItem()) && (blockEntity.getStack(i)).getCount() < 64) {
            blockEntity.getStack(i).increment(1);
            return i;
         }
      }

      return -1;
   }

   private static boolean tryReaction(ServerWorld world, BlockPos pos) {
      BlockState blockState = world.getBlockState(pos);
      if (blockState.isOf(Blocks.CALCITE)) {
         Vec3d vec3d = Vec3d.add(pos, 0.5, 1.01, 0.5).addRandom(world.random, 0.2F);
         ItemStack itemStack = new ItemStack(HibiscusMiscBlocks.CHALK_POWDER);
         itemStack.setCount(world.getRandom().nextBetween(1, 5));
         ItemEntity itemEntity = new ItemEntity(world, vec3d.getX(), vec3d.getY(), vec3d.getZ(), itemStack);
         itemEntity.setToDefaultPickupDelay();
         world.spawnEntity(itemEntity);
         world.playSound(null, pos, SoundEvents.BLOCK_CALCITE_PLACE, SoundCategory.BLOCKS, 1.0F, 1.8F);
         double d = blockState.getOutlineShape(world, pos).getEndingCoord(Direction.Axis.Y, 0.5D, 0.5D) + 0.03125D;
         Random random = world.getRandom();

         for(int i = 0; i < 10; ++i) {
            double g = random.nextGaussian() * 0.01D;
            double h = random.nextGaussian() * 0.02D;
            double j = random.nextGaussian() * 0.01D;
            world.spawnParticles(
                    NatureSpirit.CALCITE_BUBBLE_PARTICLE,
                    (double)pos.getX() + 0.13124999403953552D + 0.737500011920929D * (double)random.nextFloat(),
                    (double)pos.getY() + d + (double)random.nextFloat() * (1.0D - d),
                    (double)pos.getZ() + 0.13124999403953552D + 0.737500011920929D * (double)random.nextFloat(),
                    1,
                    g, h, j, random.nextGaussian() * 0.01D);
         }
         return true;
      }

      return false;
   }
}
