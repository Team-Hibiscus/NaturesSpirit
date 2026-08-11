package net.hibiscus.naturespirit.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.NetherVines;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Supplier;


public class DownwardVineBlock extends GrowingPlantHeadBlock {

  protected static final VoxelShape SHAPE = Block.box(4D, 9D, 4D, 12D, 16D, 12D);

  private final Supplier<DeferredBlock<DownwardsVinePlantBlock>> vinesPlantBlock;

  public DownwardVineBlock(Properties properties, Supplier<DeferredBlock<DownwardsVinePlantBlock>> vinesPlantBlock) {
    super(properties, Direction.DOWN, SHAPE, false, 0.1D);
    this.vinesPlantBlock = vinesPlantBlock;
  }

  @Override
  protected MapCodec<? extends GrowingPlantHeadBlock> codec() {
    return null;
  }

  @Override
  protected int getBlocksToGrowWhenBonemealed(RandomSource randomSource) {
    return NetherVines.getBlocksToGrowWhenBonemealed(randomSource);
  }

  @Override
  public Block getBodyBlock() {
    return vinesPlantBlock.get().get();
  }

  @Override
  public boolean canGrowInto(BlockState state) {
    return state.isAir();
  }

  @Override
  public boolean canSurvive(BlockState state, LevelReader levelReader, BlockPos pos) {
    BlockPos blockPos = pos.relative(this.growthDirection.getOpposite());
    BlockState blockState = levelReader.getBlockState(blockPos);
    if (!this.canAttachTo(blockState)) {
      return false;
    } else {
      return blockState.is(this.getHeadBlock()) || blockState.is(this.getBodyBlock()) || blockState.isFaceSturdy(levelReader,
          blockPos,
          this.growthDirection
      ) || blockState.is(BlockTags.LEAVES);
    }
  }
}
