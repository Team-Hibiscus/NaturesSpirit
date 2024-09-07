package net.hibiscus.naturespirit.blocks;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FernBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class LargeSproutsBlock extends FernBlock {


   protected static final VoxelShape SHAPE = Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 4.0, 14.0);
   public LargeSproutsBlock(Settings properties) {
      super(properties);
   }
   public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
      return false;
   }
   public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
      return SHAPE;
   }

   public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
      return false;
   }
}
