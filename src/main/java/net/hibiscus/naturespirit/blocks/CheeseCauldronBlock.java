package net.hibiscus.naturespirit.blocks;

import com.mojang.serialization.MapCodec;
import net.hibiscus.naturespirit.util.HibiscusCauldronBehavior;
import net.minecraft.block.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class CheeseCauldronBlock extends AbstractCauldronBlock {
   public CheeseCauldronBlock(AbstractBlock.Settings settings) {
      super(settings, HibiscusCauldronBehavior.CHEESE_CAULDRON_BEHAVIOR);
   }

   @Override protected MapCodec <? extends AbstractCauldronBlock> getCodec() {
      return null;
   }

   public boolean isFull(BlockState state) {
      return true;
   }
   public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
      return VoxelShapes.fullCube();
   }

   @Override public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
      return new ItemStack(Blocks.CAULDRON);
   }
   public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
      return 4;
   }
}
