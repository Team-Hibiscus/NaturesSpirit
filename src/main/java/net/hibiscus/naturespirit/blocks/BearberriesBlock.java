package net.hibiscus.naturespirit.blocks;


import net.minecraft.block.BlockState;
import net.minecraft.block.ShortPlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class BearberriesBlock extends ShortPlantBlock {


   public BearberriesBlock(Settings properties) {
      super(properties);
   }
   public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
      return false;
   }

   public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
      return false;
   }
}
