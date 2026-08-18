package net.hibiscus.naturespirit.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SemiTallGrassBlock extends DoublePlantBlock {

  private static final VoxelShape LOWER_SHAPE = Block.box(2D, 0D, 2D, 14D, 16D, 14D);
  private static final VoxelShape UPPER_SHAPE = Block.box(2D, 0D, 2D, 14D, 8D, 14D);

  public SemiTallGrassBlock(Properties settings) {
    super(settings);
  }

  @Override
  public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
    return state.getValue(HALF) == DoubleBlockHalf.UPPER ? UPPER_SHAPE : LOWER_SHAPE;
  }
}
