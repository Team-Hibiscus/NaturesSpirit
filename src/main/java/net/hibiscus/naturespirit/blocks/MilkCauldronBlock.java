package net.hibiscus.naturespirit.blocks;

import com.mojang.serialization.MapCodec;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.registration.HibiscusMiscBlocks;
import net.hibiscus.naturespirit.util.HibiscusCauldronBehavior;
import net.hibiscus.naturespirit.registration.HibiscusTags;
import net.minecraft.block.AbstractCauldronBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class MilkCauldronBlock extends AbstractCauldronBlock {

 public static BooleanProperty ageIntoCheese = BooleanProperty.of("age_into_cheese");

   public MilkCauldronBlock(Settings settings) {
      super(settings, HibiscusCauldronBehavior.MILK_CAULDRON_BEHAVIOR);
      this.setDefaultState(this.stateManager.getDefaultState().with(ageIntoCheese, false));
   }

   @Override protected MapCodec <? extends AbstractCauldronBlock> getCodec() {
      return null;
   }

   protected double getFluidHeight(BlockState state) {
      return 0.9375D;
   }

   @Override public boolean hasRandomTicks(BlockState state) {
      return state.get(ageIntoCheese);
   }

   @Override public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
      if (player.getStackInHand(player.getActiveHand()).isIn(HibiscusTags.Items.CHEESE_MAKER) && !state.get(ageIntoCheese)) {
         world.setBlockState(pos, state.with(ageIntoCheese, true), 2);
         BlockState blockState = world.getBlockState(pos);
         world.playSound(null, pos, SoundEvents.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, SoundCategory.BLOCKS, 1.0F, 1.0F);
         double d = blockState.getOutlineShape(world, pos).getEndingCoord(Direction.Axis.Y, 0.5D, 0.5D) + 0.03125D;
         Random random = world.getRandom();

         for(int i = 0; i < 10; ++i) {
            double g = random.nextGaussian() * 0.02D;
            double h = random.nextGaussian() * 0.02D;
            double j = random.nextGaussian() * 0.02D;
            world.addParticle(NatureSpirit.MILK_PARTICLE, (double)pos.getX() + 0.13124999403953552D + 0.737500011920929D * (double)random.nextFloat(), (double)pos.getY() + d + 1 + (double)random.nextFloat() * (1.0D - d), (double)pos.getZ() + 0.13124999403953552D + 0.737500011920929D * (double)random.nextFloat(), g, h, j);
         }
         if (!player.isCreative() && !player.isSpectator())
         {
            ItemStack itemStack = player.getStackInHand(player.getActiveHand()).getRecipeRemainder();
            player.getStackInHand(player.getActiveHand()).decrement(1);
            if (player.getStackInHand(player.getActiveHand()).isEmpty()) {
               player.setStackInHand(player.getActiveHand(), itemStack);
            } else {
                  if(player.getInventory().insertStack(itemStack)) {
                     player.dropItem(itemStack, false);
                  }
            }
         }
         return ActionResult.SUCCESS;
      }
      return super.onUse(state, world, pos, player, hit);
   }

   @Override public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
      if (random.nextInt(25) == 0) {
         world.setBlockState(pos, HibiscusMiscBlocks.CHEESE_CAULDRON.getDefaultState(), 2);
      }
      super.randomTick(state, world, pos, random);
   }

   @Override public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
      return new ItemStack(Blocks.CAULDRON);
   }

   public boolean isFull(BlockState state) {
      return true;
   }

   public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
      return 3;
   }
   protected void appendProperties(StateManager.Builder <Block, BlockState> builder) {
      builder.add(ageIntoCheese);
   }
}
