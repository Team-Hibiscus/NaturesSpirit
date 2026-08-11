package net.hibiscus.naturespirit.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MidFlowerBlock extends FlowerBlock {

  protected static final VoxelShape SHAPE = Block.box(5D, 0D, 5D, 11D, 16D, 11D);

  public MidFlowerBlock(Holder<MobEffect> mobEffect, int i, Properties properties) {
    super(mobEffect, i, properties);
  }

  @Override
  public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
    Vec3 vec3 = state.getOffset(level, pos);
    return SHAPE.move(vec3.x, vec3.y, vec3.z);
  }
}
