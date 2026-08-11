package net.hibiscus.naturespirit.blocks;

import com.mojang.serialization.MapCodec;
import net.hibiscus.naturespirit.items.DesertTurnipItem;
import net.hibiscus.naturespirit.registration.NSBlocks;
import net.hibiscus.naturespirit.registration.NSTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Supplier;

public class DesertTurnipStemBlock extends BushBlock implements BonemealableBlock {

  public static final int MAX_AGE = 7;
  public static final IntegerProperty AGE;
  protected static final VoxelShape[] AGE_TO_SHAPE;

  static {
    AGE = BlockStateProperties.AGE_7;
    AGE_TO_SHAPE = new VoxelShape[]{
        Block.box(0D, 0D, 0D, 16D, 2D, 16D),
        Block.box(0D, 0D, 0D, 16D, 4D, 16D),
        Block.box(0D, 0D, 0D, 16D, 6D, 16D),
        Block.box(0D, 0D, 0D, 16D, 8D, 16D),
        Block.box(0D, 0D, 0D, 16D, 10D, 16D),
        Block.box(0D, 0D, 0D, 16D, 12D, 16D),
        Block.box(0D, 0D, 0D, 16D, 14D, 16D),
        Block.box(0D, 0D, 0D, 16D, 16D, 16D)
    };
  }

  private final Block rootBlock;
  private final DesertTurnipBlock vegetableBlock;
  private final Supplier<DesertTurnipItem> pickBlockItem;

  public DesertTurnipStemBlock(DesertTurnipBlock vegetableBlock, Block rootBlock, Properties settings) {
    super(settings);
    this.rootBlock = rootBlock;
    this.vegetableBlock = vegetableBlock;
    this.pickBlockItem = NSBlocks.DESERT_TURNIP;
    this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
  }

  protected static float getAvailableMoisture(Block block, BlockGetter world, BlockPos pos) {
    float f = 1F;
    BlockPos blockPos = pos.below();

    for (int i = -1; i <= 1; ++i) {
      for (int j = -1; j <= 1; ++j) {
        float g = 0F;
        BlockState blockState = world.getBlockState(blockPos.offset(i, 0, j));
        if (blockState.is(Blocks.FARMLAND)) {
          g = 1F;
          if (blockState.getValue(FarmBlock.MOISTURE) > 0) {
            g = 3F;
          }
        }

        if (i != 0 || j != 0) {
          g /= 4F;
        }

        f += g;
      }
    }

    BlockPos blockPos2 = pos.north();
    BlockPos blockPos3 = pos.south();
    BlockPos blockPos4 = pos.west();
    BlockPos blockPos5 = pos.east();
    boolean bl = world.getBlockState(blockPos4).is(block) || world.getBlockState(blockPos5).is(block);
    boolean bl2 = world.getBlockState(blockPos2).is(block) || world.getBlockState(blockPos3).is(block);
    if (bl && bl2) {
      f /= 2F;
    } else {
      boolean bl3 =
          world.getBlockState(blockPos4.north()).is(block) || world.getBlockState(blockPos5.north()).is(block) || world.getBlockState(blockPos5.south()).is(block) || world
              .getBlockState(blockPos4.south())
              .is(block);
      if (bl3) {
        f /= 2F;
      }
    }

    return f;
  }

  @Override
  protected MapCodec<? extends BushBlock> codec() {
    return null;
  }

  @Override
  protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
    return floor.is(Blocks.FARMLAND) || floor.is(NSTags.Blocks.TURNIP_STEM_GROWS_ON);
  }

  @Override
  public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
    return AGE_TO_SHAPE[state.getValue(AGE)];
  }

  @Override
  public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
    if (world.getRawBrightness(pos, 0) >= 9) {
      float f = getAvailableMoisture(this, world, pos);
      if (random.nextInt((int) (25F / f) + 1) == 0) {
        int i = state.getValue(AGE);
        if (i < 7) {
          state = state.setValue(AGE, i + 1);
          world.setBlock(pos, state, Block.UPDATE_CLIENTS);
        } else {
          Direction direction = Direction.DOWN;
          BlockPos blockPos = pos.relative(direction, 2);
          BlockPos blockPos2 = pos.relative(direction, 3);
          BlockState blockState = world.getBlockState(blockPos);
          BlockState blockState2 = world.getBlockState(blockPos2);
          boolean bl = blockState.is(NSTags.Blocks.TURNIP_ROOT_REPLACEABLE);
          boolean bl2 = blockState2.is(NSTags.Blocks.TURNIP_ROOT_REPLACEABLE);
          if (bl) {
            if (bl2 || blockState2.is(this.vegetableBlock)) {
              world.setBlockAndUpdate(blockPos, this.rootBlock.defaultBlockState());
            } else {
              world.setBlockAndUpdate(blockPos, this.vegetableBlock.defaultBlockState());
            }
          }
          if (!blockState.is(this.rootBlock)) {
            if (!blockState.is(this.vegetableBlock) && blockState2.is(this.vegetableBlock)) {
              state = state.setValue(AGE, MAX_AGE);
              world.setBlock(pos, state, Block.UPDATE_CLIENTS);
            }
          }
          if (blockState.is(this.rootBlock) && !blockState2.is(this.vegetableBlock) && bl2) {
            world.setBlockAndUpdate(blockPos2, this.vegetableBlock.defaultBlockState());
          }
        }
      }

    }
  }

  @Override
  public ItemStack getCloneItemStack(LevelReader world, BlockPos pos, BlockState state) {
    return new ItemStack(this.pickBlockItem.get());
  }

  @Override
  public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
    return state.getValue(AGE) < MAX_AGE;
  }

  @Override
  public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
    return true;
  }

  public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
    int i = Math.min(MAX_AGE, state.getValue(AGE) + Mth.nextInt(world.random, 2, 5));
    BlockState blockState = state.setValue(AGE, i);
    world.setBlock(pos, blockState, Block.UPDATE_CLIENTS);
    if (i == MAX_AGE) {
      blockState.randomTick(world, pos, world.random);
    }
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    builder.add(AGE);
  }
}
