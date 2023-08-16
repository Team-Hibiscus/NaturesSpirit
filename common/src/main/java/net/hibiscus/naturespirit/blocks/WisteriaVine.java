package net.hibiscus.naturespirit.blocks;

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


public class WisteriaVine extends GrowingPlantHeadBlock {
   protected static final VoxelShape SHAPE = Block.box(4.0D, 9.0D, 4.0D, 12.0D, 16.0D, 12.0D);

   public WisteriaVine(Properties properties) {
      super(properties, Direction.DOWN, SHAPE, false, 0.1D);
   }

   protected int getBlocksToGrowWhenBonemealed(RandomSource randomSource) {
      return NetherVines.getBlocksToGrowWhenBonemealed(randomSource);
   }

   public Block getBodyBlock() {
      if(this.asBlock() == HibiscusBlocks.BLUE_WISTERIA_VINES) {
         return HibiscusBlocks.BLUE_WISTERIA_VINES_PLANT;
      }
      if(this.asBlock() == HibiscusBlocks.PINK_WISTERIA_VINES) {
         return HibiscusBlocks.PINK_WISTERIA_VINES_PLANT;
      }
      if(this.asBlock() == HibiscusBlocks.PURPLE_WISTERIA_VINES) {
         return HibiscusBlocks.PURPLE_WISTERIA_VINES_PLANT;
      }
      else {
         return HibiscusBlocks.WHITE_WISTERIA_VINES_PLANT;
      }
   }

   public boolean canGrowInto(BlockState state) {
      return state.isAir();
   }

   @Override public boolean canSurvive(BlockState state, LevelReader levelReader, BlockPos pos) {
      BlockPos blockPos = pos.relative(this.growthDirection.getOpposite());
      BlockState blockState = levelReader.getBlockState(blockPos);
      if(!this.canAttachTo(blockState)) {
         return false;
      }
      else {
         return blockState.is(this.getHeadBlock()) || blockState.is(this.getBodyBlock()) || blockState.isFaceSturdy(levelReader,
                 blockPos,
                 this.growthDirection
         ) || blockState.is(BlockTags.LEAVES);
      }
   }
}
