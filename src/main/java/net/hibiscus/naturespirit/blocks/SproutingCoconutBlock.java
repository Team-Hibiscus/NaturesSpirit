package net.hibiscus.naturespirit.blocks;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class SproutingCoconutBlock extends SaplingBlock {

   public SproutingCoconutBlock(SaplingGenerator generator, Settings settings) {
      super(generator, settings);
   }

   protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
      return floor.isSideSolidFullSquare(world, pos, Direction.DOWN);
   }

   public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
      return Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 10.0, 13.0);
   }
}
