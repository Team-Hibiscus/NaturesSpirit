package net.hibiscus.naturespirit.blocks;


import net.hibiscus.naturespirit.util.HibiscusTags;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class TallLargeDesertFernBlock extends TallPlantBlock {

   public TallLargeDesertFernBlock(Settings properties) {
      super(properties);
   }

   @Override protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
      return floor.isIn(HibiscusTags.Blocks.TURNIP_STEM_GROWS_ON) || floor.isOf(Blocks.FARMLAND);
   }

}
