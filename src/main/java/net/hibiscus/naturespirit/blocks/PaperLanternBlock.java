package net.hibiscus.naturespirit.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LanternBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

public class PaperLanternBlock extends LanternBlock {
   protected static final VoxelShape STANDING_SHAPE;
   public PaperLanternBlock(Settings settings) {
      super(settings);
   }
   public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
      return STANDING_SHAPE;
   }
   static {
      STANDING_SHAPE = VoxelShapes.union(Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 14.0, 11.0), Block.createCuboidShape(2.0, 2.0, 2.0, 14.0, 12.0, 14.0));
   }
   @Override
   public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
      Direction direction = LanternBlock.attachedDirection(state).getOpposite();
      return Block.sideCoversSmallSquare(world, pos.offset(direction), direction.getOpposite()) || world.getBlockState(pos.offset(direction)).isIn(BlockTags.LEAVES);
   }
}
