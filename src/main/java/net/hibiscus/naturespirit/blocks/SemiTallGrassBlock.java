package net.hibiscus.naturespirit.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class SemiTallGrassBlock extends TallPlantBlock {

   private static final VoxelShape LOWER_SHAPE = Block.createCuboidShape(2, 0, 2, 14, 16, 14);
   private static final VoxelShape UPPER_SHAPE = Block.createCuboidShape(2, 0, 2, 14, 8, 14);
   public SemiTallGrassBlock(Settings settings) {
      super(settings);
   }

   @Override public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
      return state.get(HALF) == DoubleBlockHalf.UPPER ? UPPER_SHAPE : LOWER_SHAPE;
   }
}
