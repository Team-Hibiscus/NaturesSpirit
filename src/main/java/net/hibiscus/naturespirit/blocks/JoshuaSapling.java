package net.hibiscus.naturespirit.blocks;

import net.hibiscus.naturespirit.util.HibiscusTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class JoshuaSapling extends SaplingBlock {
   public JoshuaSapling(SaplingGenerator generator, Settings settings) {
      super(generator, settings);
   }
   protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
      return floor.isOf(Blocks.FARMLAND) || floor.isIn(HibiscusTags.Blocks.TURNIP_STEM_GROWS_ON);
   }
}
