package net.hibiscus.naturespirit.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SproutingCoconutBlock extends SaplingBlock {

  private static final VoxelShape OUTLINE_SHAPE = Block.box(3D, 0D, 3D, 13D, 10D, 13D);

  public SproutingCoconutBlock(TreeGrower generator, Properties settings) {
    super(generator, settings);
  }

  @Override
  protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
    return floor.isFaceSturdy(world, pos, Direction.DOWN);
  }

  @Override
  public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
    return OUTLINE_SHAPE;
  }
}
