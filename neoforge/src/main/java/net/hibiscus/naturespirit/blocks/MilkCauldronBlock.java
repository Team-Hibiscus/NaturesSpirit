package net.hibiscus.naturespirit.blocks;

import com.mojang.serialization.MapCodec;
import net.hibiscus.naturespirit.registration.NSBlocks;
import net.hibiscus.naturespirit.registration.NSParticleTypes;
import net.hibiscus.naturespirit.registration.NSTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class MilkCauldronBlock extends AbstractCauldronBlock {

  public static final BooleanProperty AGE_INTO_CHEESE = BooleanProperty.create("age_into_cheese");

  public MilkCauldronBlock(Properties settings) {
    super(settings, NSCauldronBehavior.MILK_CAULDRON_BEHAVIOR);
    this.registerDefaultState(this.stateDefinition.any().setValue(AGE_INTO_CHEESE, false));
  }


  @Override
  protected MapCodec<? extends AbstractCauldronBlock> codec() {
    return null;
  }

  @Override
  protected double getContentHeight(BlockState state) {
    return 0.9375D;
  }

  @Override
  public boolean isRandomlyTicking(@NotNull BlockState state) {
    return state.getValue(AGE_INTO_CHEESE);
  }

  @Override
  public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
    if (stack.is(NSTags.Items.CHEESE_MAKER) && !state.getValue(AGE_INTO_CHEESE)) {
      world.setBlock(pos, state.setValue(AGE_INTO_CHEESE, true), Block.UPDATE_CLIENTS);
      BlockState blockState = world.getBlockState(pos);
      world.playSound(null, pos, SoundEvents.BUBBLE_COLUMN_BUBBLE_POP, SoundSource.BLOCKS, 1F, 1F);
      double d = blockState.getShape(world, pos).max(Direction.Axis.Y, 0.5D, 0.5D) + 0.03125D;
      RandomSource random = world.getRandom();

      for (int i = 0; i < 10; ++i) {
        double g = random.nextGaussian() * 0.02D;
        double h = random.nextGaussian() * 0.02D;
        double j = random.nextGaussian() * 0.02D;
        world.addParticle(
                NSParticleTypes.MILK_PARTICLE.get(),
                (double) pos.getX() + 0.13124999403953552D + 0.737500011920929D * random.nextDouble(),
                (double) pos.getY() + d + 1D + random.nextDouble() * (1D - d),
                (double) pos.getZ() + 0.13124999403953552D + 0.737500011920929D * random.nextDouble(),
                g,
                h,
                j
        );
      }
      if (!player.isCreative() && !player.isSpectator()) {
        player.getItemInHand(player.getUsedItemHand()).shrink(1);
      }
      return ItemInteractionResult.SUCCESS;
    }
    return super.useItemOn(stack, state, world, pos, player, hand, hit);
  }

  @Override
  public void randomTick(BlockState state, ServerLevel world, BlockPos pos, @NotNull RandomSource random) {
    if (random.nextInt(25) == 0) {
      world.setBlock(pos, NSBlocks.CHEESE_CAULDRON.get().defaultBlockState(), Block.UPDATE_CLIENTS);
    }
    super.randomTick(state, world, pos, random);
  }

  @Override
  public ItemStack getCloneItemStack(LevelReader world, BlockPos pos, BlockState state) {
    return new ItemStack(Blocks.CAULDRON);
  }

  @Override
  public boolean isFull(BlockState state) {
    return true;
  }

  @Override
  public int getAnalogOutputSignal(BlockState state, Level world, BlockPos pos) {
    return 3;
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
    builder.add(AGE_INTO_CHEESE);
  }
}
