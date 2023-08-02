package net.hibiscus.naturespirit.blocks;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class LargeFlowerBlock extends FlowerBlock {
   protected static final VoxelShape SHAPE = Block.createCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);

   public LargeFlowerBlock(StatusEffect mobEffect, int i, Settings properties) {
      super(mobEffect, i, properties);
   }

   public VoxelShape getOutlineShape(BlockState state, BlockView level, BlockPos pos, ShapeContext context) {
      Vec3d vec3 = state.getModelOffset(level, pos);
      return SHAPE.offset(vec3.x, vec3.y, vec3.z);
   }
}
