package net.hibiscus.naturespirit.blocks;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LargeFlowerBlock extends FlowerBlock {

  protected static final VoxelShape SHAPE = Block.box(2D, 0D, 2D, 14D, 16D, 14D);

  @Override
  public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
    return SHAPE;
  }

  public LargeFlowerBlock(Holder<MobEffect> mobEffect, int i, Properties properties) {
    super(mobEffect, i, properties);
  }
}
