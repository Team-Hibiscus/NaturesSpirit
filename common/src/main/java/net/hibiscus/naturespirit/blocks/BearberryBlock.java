package net.hibiscus.naturespirit.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockState;

public class BearberryBlock extends TallGrassBlock {


  public BearberryBlock(Properties properties) {
    super(properties);
  }
  public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
    return false;
  }

  public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
    return false;
  }
}