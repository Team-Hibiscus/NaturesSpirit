package net.hibiscus.naturespirit.blocks;

import net.hibiscus.naturespirit.registration.HibiscusBlocksAndItems;
import net.minecraft.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SupportType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LotusFlowerBlock extends BushBlock {
   protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 1.5D, 14.0D);

   public LotusFlowerBlock(Properties properties) {
      super(properties);
   }


   public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
      super.entityInside(state, world, pos, entity);
      if(world instanceof ServerLevel && entity instanceof Boat) {
         world.destroyBlock(new BlockPos(pos), true, entity);
      }

   }

   public ItemStack getCloneItemStack(BlockGetter world, BlockPos pos, BlockState state) {
      return new ItemStack(HibiscusBlocksAndItems.LOTUS_FLOWER_ITEM);
   }

   public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return SHAPE;
   }

   protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
      FluidState fluidState = world.getFluidState(pos);
      FluidState fluidState2 = world.getFluidState(pos.above());
      return ((fluidState.getType() == Fluids.WATER || floor.isFaceSturdy(world, pos, Direction.UP, SupportType.CENTER)) && fluidState2.getType() == Fluids.EMPTY) || floor.is(
              HibiscusBlocksAndItems.LOTUS_STEM);
   }
}
