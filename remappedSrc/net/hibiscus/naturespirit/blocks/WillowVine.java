package net.hibiscus.naturespirit.blocks;

import net.hibiscus.naturespirit.registration.block_registration.HibiscusWoods;
import net.minecraft.block.Block;
import net.minecraft.block.VineLogic;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;


public class WillowVine extends WisteriaVine {
   protected static final VoxelShape SHAPE = Block.createCuboidShape(4.0D, 9.0D, 4.0D, 12.0D, 16.0D, 12.0D);

   public WillowVine(Settings properties) {
      super(properties);
   }


   protected int getGrowthLength(Random randomSource) {
      return VineLogic.getGrowthLength(randomSource);
   }

   public Block getPlant() {
      return HibiscusWoods.WILLOW.getWillowVinesPlant();
   }
}
