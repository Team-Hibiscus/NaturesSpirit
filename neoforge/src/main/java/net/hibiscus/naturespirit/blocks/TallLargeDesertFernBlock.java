package net.hibiscus.naturespirit.blocks;


import net.hibiscus.naturespirit.registration.NSTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;

public class TallLargeDesertFernBlock extends DoublePlantBlock {

  public TallLargeDesertFernBlock(Properties properties) {
    super(properties);
  }

  @Override
  protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
    return floor.is(NSTags.Blocks.TURNIP_STEM_GROWS_ON) || floor.is(Blocks.FARMLAND);
  }
}
