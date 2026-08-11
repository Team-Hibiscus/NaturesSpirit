package net.hibiscus.naturespirit.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PaperLanternBlock extends LanternBlock {

  protected static final VoxelShape STANDING_SHAPE = Shapes.or(
      Block.box(5D, 0D, 5D, 11D, 14D, 11D),
      Block.box(2D, 2D, 2D, 14D, 12D, 14D)
  );

  public PaperLanternBlock(Properties settings) {
    super(settings);
  }

  @Override
  public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
    return AABB;
  }

  @Override
  public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
    Direction direction = LanternBlock.getConnectedDirection(state).getOpposite();
    return Block.canSupportCenter(world, pos.relative(direction), direction.getOpposite()) || world.getBlockState(pos.relative(direction)).is(BlockTags.LEAVES);
  }
}
