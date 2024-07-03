package net.hibiscus.naturespirit.blocks;

import net.hibiscus.naturespirit.registration.block_registration.HibiscusWoods;
import net.minecraft.block.AbstractPlantStemBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.WorldView;

public class WillowVinePlant extends WisteriaVinePlant {
   public static final VoxelShape SHAPE = Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
   private static final Block block = HibiscusWoods.WILLOW.getWillowVines();


   public WillowVinePlant(Settings properties) {
      super(properties, block);
   }


   public String getTranslationKey() {
      return this.asItem().getTranslationKey();
   }

   protected AbstractPlantStemBlock getStem() {
      return (AbstractPlantStemBlock) block;
   }

   @Override public boolean canPlaceAt(BlockState state, WorldView levelReader, BlockPos pos) {
      BlockPos blockPos = pos.offset(this.growthDirection.getOpposite());
      BlockState blockState = levelReader.getBlockState(blockPos);
      if(!this.canAttachTo(blockState)) {
         return false;
      }
      else {
         return blockState.isOf(this.getPlant()) || blockState.isOf(this.getStem()) || blockState.isSideSolidFullSquare(levelReader,
                 blockPos,
                 this.growthDirection
         ) || blockState.isIn(BlockTags.LEAVES);
      }
   }
}
