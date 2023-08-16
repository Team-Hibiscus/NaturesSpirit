package net.hibiscus.naturespirit.blocks;

import net.hibiscus.naturespirit.util.HibiscusTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;

public class JoshuaSapling extends SaplingBlock {
   public JoshuaSapling(AbstractTreeGrower generator, Properties settings) {
      super(generator, settings);
   }
   protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
      return floor.is(Blocks.FARMLAND) || floor.is(HibiscusTags.Blocks.TURNIP_STEM_GROWS_ON);
   }
}
