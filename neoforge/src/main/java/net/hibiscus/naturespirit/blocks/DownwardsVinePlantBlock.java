package net.hibiscus.naturespirit.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.registries.DeferredBlock;

public class DownwardsVinePlantBlock extends GrowingPlantBodyBlock {

  public static final VoxelShape SHAPE = Block.box(1D, 0D, 1D, 15D, 16D, 15D);
  public DeferredBlock<DownwardVineBlock> headBlock;

  public DownwardsVinePlantBlock(Properties properties, DeferredBlock<DownwardVineBlock> headBlock) {
    super(properties, Direction.DOWN, SHAPE, false);
    this.headBlock = headBlock;
  }

  @Override
  public String getDescriptionId() {
    return headBlock.get().getDescriptionId();
  }

  @Override
  protected GrowingPlantHeadBlock getHeadBlock() {
    return (GrowingPlantHeadBlock) headBlock.get();
  }

  @Override
  public boolean canSurvive(BlockState state, LevelReader levelReader, BlockPos pos) {
    BlockPos blockPos = pos.relative(this.growthDirection.getOpposite());
    BlockState blockState = levelReader.getBlockState(blockPos);
    if (!this.canAttachTo(blockState)) {
      return false;
    } else {
      return blockState.is(this.getBodyBlock()) || blockState.is(this.getHeadBlock()) || blockState.isFaceSturdy(levelReader,
          blockPos,
          this.growthDirection
      ) || blockState.is(BlockTags.LEAVES);
    }
  }

  @Override
  protected MapCodec<? extends GrowingPlantBodyBlock> codec() {
    return null;
  }
}
