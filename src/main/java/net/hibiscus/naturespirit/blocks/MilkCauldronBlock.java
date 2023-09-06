package net.hibiscus.naturespirit.blocks;

import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.registration.HibiscusBlocksAndItems;
import net.hibiscus.naturespirit.util.HibiscusCauldronBehavior;
import net.hibiscus.naturespirit.util.HibiscusTags;
import net.minecraft.block.AbstractCauldronBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class MilkCauldronBlock extends AbstractCauldronBlock {

 public static BooleanProperty ageIntoCheese = BooleanProperty.of("age_into_cheese");

   public MilkCauldronBlock(Settings settings) {
      super(settings, HibiscusCauldronBehavior.MILK_CAULDRON_BEHAVIOR);
      this.setDefaultState(this.stateManager.getDefaultState().with(ageIntoCheese, false));
   }

   protected double getFluidHeight(BlockState state) {
      return 0.9375D;
   }

   @Override public boolean hasRandomTicks(BlockState state) {
      return state.get(ageIntoCheese);
   }

   @Override public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
      if (player.getStackInHand(hand).isIn(HibiscusTags.Items.CHEESE_MAKER) && !state.get(ageIntoCheese)) {
         world.setBlockState(pos, state.with(ageIntoCheese, true), 2);
         BlockState blockState = world.getBlockState(pos);
         world.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, SoundCategory.BLOCKS, 1.0F, 1.0F);
         double d = blockState.getOutlineShape(world, pos).getEndingCoord(Direction.Axis.Y, 0.5D, 0.5D) + 0.03125D;
         double e = 0.13124999403953552D;
         double f = 0.737500011920929D;
         Random random = world.getRandom();

         for(int i = 0; i < 10; ++i) {
            double g = random.nextGaussian() * 0.02D;
            double h = random.nextGaussian() * 0.02D;
            double j = random.nextGaussian() * 0.02D;
            world.addParticle(NatureSpirit.MILK_PARTICLE, (double)pos.getX() + 0.13124999403953552D + 0.737500011920929D * (double)random.nextFloat(), (double)pos.getY() + d + 1 + (double)random.nextFloat() * (1.0D - d), (double)pos.getZ() + 0.13124999403953552D + 0.737500011920929D * (double)random.nextFloat(), g, h, j);
         }
         if (!player.isCreative() && !player.isSpectator())
         {
            player.setStackInHand(hand, new ItemStack(Items.GLASS_BOTTLE));
         }
         return ActionResult.SUCCESS;
      }
      return super.onUse(state, world, pos, player, hand, hit);
   }

   @Override public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
      if (random.nextInt(50) == 0) {
         world.setBlockState(pos, HibiscusBlocksAndItems.CHEESE_CAULDRON.getDefaultState(), 2);
      }
      super.randomTick(state, world, pos, random);
   }

   @Override public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
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
