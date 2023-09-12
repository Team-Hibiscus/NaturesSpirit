package net.hibiscus.naturespirit.blocks;

import net.hibiscus.naturespirit.registration.block_registration.HibiscusWoods;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.NetherVines;
import net.minecraft.world.phys.shapes.VoxelShape;


public class WillowVine extends WisteriaVine {
   protected static final VoxelShape SHAPE = Block.box(4.0D, 9.0D, 4.0D, 12.0D, 16.0D, 12.0D);

   public WillowVine(Properties properties) {
      super(properties);
   }


   protected int getBlocksToGrowWhenBonemealed(RandomSource randomSource) {
      return NetherVines.getBlocksToGrowWhenBonemealed(randomSource);
   }

   public Block getBodyBlock() {
      return HibiscusWoods.WILLOW.getWillowVinesPlant();
   }
}
