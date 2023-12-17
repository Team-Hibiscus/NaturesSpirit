package net.hibiscus.naturespirit.blocks;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.ShortPlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class HibiscusLargeSproutsBlock extends ShortPlantBlock {


   protected static final VoxelShape SHAPE = Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 4.0, 14.0);
   public HibiscusLargeSproutsBlock(Settings properties) {
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
