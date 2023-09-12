package net.hibiscus.naturespirit.blocks;

import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.registration.HibiscusBlocksAndItems;
import net.hibiscus.naturespirit.util.HibiscusCauldronBehavior;
import net.hibiscus.naturespirit.util.HibiscusTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class MilkCauldronBlock extends AbstractCauldronBlock {

 public static BooleanProperty ageIntoCheese = BooleanProperty.create("age_into_cheese");

   public MilkCauldronBlock(Properties settings) {
      super(settings, HibiscusCauldronBehavior.MILK_CAULDRON_BEHAVIOR);
      this.registerDefaultState(this.stateDefinition.any().setValue(ageIntoCheese, false));
   }

   protected double getContentHeight(BlockState state) {
      return 0.9375D;
   }

   @Override public boolean isRandomlyTicking(BlockState state) {
      return state.getValue(ageIntoCheese);
   }

   @Override public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
      if (player.getItemInHand(hand).is(HibiscusTags.Items.CHEESE_MAKER) && !state.getValue(ageIntoCheese)) {
         world.setBlock(pos, state.setValue(ageIntoCheese, true), 2);
         BlockState blockState = world.getBlockState(pos);
         world.playSound(null, pos, SoundEvents.BUBBLE_COLUMN_BUBBLE_POP, SoundSource.BLOCKS, 1.0F, 1.0F);
         double d = blockState.getShape(world, pos).max(Direction.Axis.Y, 0.5D, 0.5D) + 0.03125D;
         RandomSource random = world.getRandom();

         for(int i = 0; i < 10; ++i) {
            double g = random.nextGaussian() * 0.02D;
            double h = random.nextGaussian() * 0.02D;
            double j = random.nextGaussian() * 0.02D;
            world.addParticle(NatureSpirit.MILK_PARTICLE, (double)pos.getX() + 0.13124999403953552D + 0.737500011920929D * (double)random.nextFloat(), (double)pos.getY() + d + 1 + (double)random.nextFloat() * (1.0D - d), (double)pos.getZ() + 0.13124999403953552D + 0.737500011920929D * (double)random.nextFloat(), g, h, j);
         }
         if (!player.isCreative() && !player.isSpectator())
         {
            ItemStack itemStack = player.getItemInHand(hand).getRecipeRemainder();
            player.getItemInHand(hand).shrink(1);
            if (player.getItemInHand(hand).isEmpty()) {
               player.setItemInHand(hand, itemStack);
            } else {
                  if(player.getInventory().add(itemStack)) {
                     player.drop(itemStack, false);
                  }
            }
         }
         return InteractionResult.SUCCESS;
      }
      return super.use(state, world, pos, player, hand, hit);
   }

   @Override public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
      if (random.nextInt(50) == 0) {
         world.setBlock(pos, HibiscusBlocksAndItems.CHEESE_CAULDRON.defaultBlockState(), 2);
      }
      super.randomTick(state, world, pos, random);
   }

   @Override public ItemStack getCloneItemStack(BlockGetter world, BlockPos pos, BlockState state) {
      return new ItemStack(Blocks.CAULDRON);
   }

   public boolean isFull(BlockState state) {
      return true;
   }

   public int getAnalogOutputSignal(BlockState state, Level world, BlockPos pos) {
      return 3;
   }
   protected void createBlockStateDefinition(StateDefinition.Builder <Block, BlockState> builder) {
      builder.add(ageIntoCheese);
   }
}
