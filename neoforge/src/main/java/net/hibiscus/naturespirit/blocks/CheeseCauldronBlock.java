package net.hibiscus.naturespirit.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CheeseCauldronBlock extends AbstractCauldronBlock {

  public CheeseCauldronBlock(Properties settings) {
    super(settings, NSCauldronBehavior.CHEESE_CAULDRON_BEHAVIOR);
  }

  @Override
  protected MapCodec<? extends AbstractCauldronBlock> codec() {
    return null;
  }

  @Override
  public boolean isFull(BlockState state) {
    return true;
  }

  @Override
  public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
    return Shapes.block();
  }
  @Override
  public ItemStack getCloneItemStack(LevelReader world, BlockPos pos, BlockState state) {
    return new ItemStack(Blocks.CAULDRON);
  }

  @Override
  public int getAnalogOutputSignal(BlockState state, Level world, BlockPos pos) {
    return 4;
  }
}
