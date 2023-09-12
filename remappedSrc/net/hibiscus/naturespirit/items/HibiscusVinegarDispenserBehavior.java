package net.hibiscus.naturespirit.items;

import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.registration.HibiscusBlocksAndItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.entity.DispenserBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class HibiscusVinegarDispenserBehavior extends OptionalDispenseItemBehavior {
   protected ItemStack execute(BlockSource pointer, ItemStack stack) {
      ServerLevel serverWorld = pointer.getLevel();
      if (!serverWorld.isClientSide()) {
         BlockPos blockPos = pointer.getPos().relative(pointer.getBlockState().getValue(DispenserBlock.FACING));
         this.setSuccess(tryReaction(serverWorld, blockPos));
         if (this.isSuccess()) {
            ItemStack itemStack2 = new ItemStack(stack.getRecipeRemainder().getItem());
            DispenserBlockEntity blockEntity = pointer.getEntity();
               if (addToAlikeSlot(blockEntity, itemStack2) == -1) {
                  if (blockEntity.addItem(itemStack2) == -1) spawnItem(serverWorld, itemStack2, 0, pointer.getBlockState().getValue(DispenserBlock.FACING).getOpposite(), blockPos.getCenter());
               }
            stack.shrink(1);
         }
      }

      return stack;
   }

   public int addToAlikeSlot(DispenserBlockEntity blockEntity, ItemStack stack) {
      for(int i = 0; i < 9; ++i) {
         if ((blockEntity.getItem(i)).is(stack.getItem()) && (blockEntity.getItem(i)).getCount() < 64) {
            blockEntity.getItem(i).grow(1);
            return i;
         }
      }

      return -1;
   }

   private static boolean tryReaction(ServerLevel world, BlockPos pos) {
      BlockState blockState = world.getBlockState(pos);
      if (blockState.is(Blocks.CALCITE)) {
         Vec3 vec3d = Vec3.atLowerCornerWithOffset(pos, 0.5, 1.01, 0.5).offsetRandom(world.random, 0.2F);
         ItemStack itemStack = new ItemStack(HibiscusBlocksAndItems.CHALK_POWDER);
         itemStack.setCount(world.getRandom().nextIntBetweenInclusive(1, 5));
         ItemEntity itemEntity = new ItemEntity(world, vec3d.x(), vec3d.y(), vec3d.z(), itemStack);
         itemEntity.setDefaultPickUpDelay();
         world.addFreshEntity(itemEntity);
         world.playSound(null, pos, SoundEvents.CALCITE_PLACE, SoundSource.BLOCKS, 1.0F, 1.8F);
         double d = blockState.getShape(world, pos).max(Direction.Axis.Y, 0.5D, 0.5D) + 0.03125D;
         RandomSource random = world.getRandom();

         for(int i = 0; i < 10; ++i) {
            double g = random.nextGaussian() * 0.01D;
            double h = random.nextGaussian() * 0.02D;
            double j = random.nextGaussian() * 0.01D;
            world.sendParticles(
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
