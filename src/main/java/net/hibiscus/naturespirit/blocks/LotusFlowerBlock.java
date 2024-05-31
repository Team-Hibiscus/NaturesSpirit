package net.hibiscus.naturespirit.blocks;

import net.hibiscus.naturespirit.registration.block_registration.HibiscusMiscBlocks;
import net.minecraft.block.*;
import net.minecraft.block.enums.Tilt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockLocating;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class LotusFlowerBlock extends PlantBlock implements Fertilizable{
   protected static final VoxelShape SHAPE = Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 1.5D, 14.0D);

   public LotusFlowerBlock(Settings properties) {
      super(properties);
   }


   public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
      super.onEntityCollision(state, world, pos, entity);

      if (!world.isClient) {
         if(entity instanceof BoatEntity) {
            world.breakBlock(new BlockPos(pos), true, entity);
         }
         BlockState blockstate = world.getBlockState(pos.down());
         if (blockstate.isOf(HibiscusMiscBlocks.LOTUS_STEM) && isEntityAbove(pos, entity) && !isPowered(world, pos)) {
            world.scheduleBlockTick(pos, this, 4);
         }
      }

   }


   private int getRedstonePower(WorldView world, BlockPos pos) {
      BlockPos.Mutable mutable = pos.mutableCopy();

      BlockState blockState;
       do {
          mutable.move(Direction.DOWN);
          blockState = world.getBlockState(mutable);
          if (world.isReceivingRedstonePower(mutable)) {
             return world.getReceivedRedstonePower(mutable);
          }
       } while(blockState.isOf(HibiscusMiscBlocks.LOTUS_STEM));

      return 0;
   }


   public boolean isPowered(WorldView world, BlockPos pos) {
      return getRedstonePower(world, pos) > 0;
   }

   @Override public boolean hasComparatorOutput(BlockState state) {
      return true;
   }

   @Override public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
      return getRedstonePower(world, pos);
   }

   @Override public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
      super.scheduledTick(state, world, pos, random);

      BlockState blockstate = world.getBlockState(pos.down());
      if (blockstate.isOf(HibiscusMiscBlocks.LOTUS_STEM) && isEntityAbove(pos, world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 1.25D, false)) && !isPowered(world, pos)) {
         world.breakBlock(pos, false);
         world.setBlockState(pos.down(), this.getDefaultState());
      }
   }

   private static boolean isEntityAbove(BlockPos pos, @Nullable Entity entity) {
      if (entity == null) return false;
      return entity.isOnGround() && entity.getPos().y > (double)((float)pos.getY()) && entity.isSneaking();
   }

   public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
      return new ItemStack(HibiscusMiscBlocks.LOTUS_FLOWER_ITEM);
   }

   public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
      return SHAPE;
   }

   protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
      FluidState fluidState = world.getFluidState(pos);
      FluidState fluidState2 = world.getFluidState(pos.up());
      return ((fluidState.getFluid() == Fluids.WATER || floor.isSideSolid(world, pos, Direction.UP, SideShapeType.CENTER)) && fluidState2.getFluid() == Fluids.EMPTY) || floor.isOf(
              HibiscusMiscBlocks.LOTUS_STEM);
   }

   @Override public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
      return world.getBlockState(pos.up()).isAir() && !world.getBlockState(pos.down()).isOf(Blocks.WATER) && !isPowered(world, pos);
   }

   @Override public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
      return true;
   }

   @Override public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {

      PlayerEntity player = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 1.25D, false);
      if (player != null) {
         player.move(MovementType.SHULKER_BOX, new Vec3d(0, 1.01, 0));
      }

      if (world.getBlockState(pos.down()).isOf(HibiscusMiscBlocks.LOTUS_STEM)) {
         LotusStem lotusStem = (LotusStem) world.getBlockState(pos.down()).getBlock();
         lotusStem.grow(world, random, pos.down(), world.getBlockState(pos.down()));
      } else {
         world.setBlockState(pos, HibiscusMiscBlocks.LOTUS_STEM.getDefaultState().with(LotusStem.WATERLOGGED, false));
         world.setBlockState(pos.up(), this.getDefaultState());
      }

   }
}
