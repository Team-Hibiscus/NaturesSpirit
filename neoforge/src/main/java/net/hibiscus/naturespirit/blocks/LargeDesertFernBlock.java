package net.hibiscus.naturespirit.blocks;


import net.hibiscus.naturespirit.registration.NSTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Supplier;

public class LargeDesertFernBlock extends TallGrassBlock {

  protected static final VoxelShape SHAPE = Block.box(2D, 0D, 2D, 14D, 16D, 14D);
  private final Supplier<? extends Block> tallBlock;

  public LargeDesertFernBlock(Properties properties, Supplier<? extends Block> tallBlock) {
    super(properties);
    this.tallBlock = tallBlock;
  }

  @Override
  protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
    return floor.is(NSTags.Blocks.TURNIP_STEM_GROWS_ON) || floor.is(Blocks.FARMLAND);
  }

  @Override
  public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
    if (tallBlock.get().defaultBlockState().canSurvive(world, pos) && world.isEmptyBlock(pos.above())) {
      DoublePlantBlock.placeAt(world, tallBlock.get().defaultBlockState(), pos, 2);
    }

  }
}
