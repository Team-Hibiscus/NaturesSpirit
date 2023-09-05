package net.hibiscus.naturespirit.blocks;

import net.hibiscus.naturespirit.registration.HibiscusBlocksAndItems;
import net.hibiscus.naturespirit.util.HibiscusCauldronBehavior;
import net.hibiscus.naturespirit.util.HibiscusTags;
import net.minecraft.block.AbstractCauldronBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class MilkCauldronBlock extends AbstractCauldronBlock {

 public static BooleanProperty ageIntoCheese = BooleanProperty.of("age_into_cheese");

   public MilkCauldronBlock(Settings settings) {
      super(settings, HibiscusCauldronBehavior.MILK_CAULDRON_BEHAVIOR);
   }

   protected double getFluidHeight(BlockState state) {
      return 0.9375D;
   }

   @Override public boolean hasRandomTicks(BlockState state) {
      return state.get(ageIntoCheese);
   }

   @Override public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
      if (player.getStackInHand(hand).isIn(HibiscusTags.Items.CHEESE_MAKER))
      {
         world.setBlockState(pos, state.with(ageIntoCheese, true), 2);
         if (!player.isCreative() && !player.isSpectator())
         {
            player.setStackInHand(hand, new ItemStack(Items.GLASS_BOTTLE));
         }
      }
      return super.onUse(state, world, pos, player, hand, hit);
   }

   @Override public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
      if (random.nextInt(50) == 50) {
         world.setBlockState(pos, HibiscusBlocksAndItems.CHEESE_CAULDRON.getDefaultState(), 2);
      }
      super.randomTick(state, world, pos, random);
   }

   public boolean isFull(BlockState state) {
      return true;
   }

   public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
      return 3;
   }
}
