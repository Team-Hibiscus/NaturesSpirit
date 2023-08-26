package net.hibiscus.naturespirit.blocks;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class LotusFlowerBlock extends PlantBlock {
   protected static final VoxelShape SHAPE = Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 1.5D, 14.0D);

   public LotusFlowerBlock(Settings properties) {
      super(properties);
   }


   public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
      super.onEntityCollision(state, world, pos, entity);
      if (world instanceof ServerWorld && entity instanceof BoatEntity) {
         world.breakBlock(new BlockPos(pos), true, entity);
      }

   }

   public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
      return new ItemStack(HibiscusBlocks.LOTUS_FLOWER_ITEM);
   }
   public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
      return SHAPE;
   }

   protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
      FluidState fluidState = world.getFluidState(pos);
      FluidState fluidState2 = world.getFluidState(pos.up());
      return ((fluidState.getFluid() == Fluids.WATER || floor.isSideSolid(world, pos, Direction.UP, SideShapeType.CENTER)) && fluidState2.getFluid() == Fluids.EMPTY) || floor.isOf(HibiscusBlocks.LOTUS_STEM);
   }
}
