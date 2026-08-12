package net.hibiscus.naturespirit.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;

public class SandySaplingBlock extends SaplingBlock {

  public SandySaplingBlock(TreeGrower generator, Properties settings) {
    super(generator, settings);
  }

  @Override
  protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
    return floor.is(BlockTags.DIRT) || floor.is(Blocks.FARMLAND) || floor.is(BlockTags.SAND);
  }
}
