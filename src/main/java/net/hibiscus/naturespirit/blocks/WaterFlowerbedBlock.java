package net.hibiscus.naturespirit.blocks;

import net.hibiscus.naturespirit.registration.NSMiscBlocks;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class WaterFlowerbedBlock extends PlantBlock implements Fertilizable {

   public static final IntProperty FLOWER_AMOUNT;
   public static final DirectionProperty FACING;
   public WaterFlowerbedBlock(Settings settings) {
      super(settings);
      this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(FLOWER_AMOUNT, 0));
   }

   public BlockState rotate(BlockState state, BlockRotation rotation) {
      return (BlockState)state.with(FACING, rotation.rotate((Direction)state.get(FACING)));
   }

   public BlockState mirror(BlockState state, BlockMirror mirror) {
      return state.rotate(mirror.getRotation((Direction)state.get(FACING)));
   }
   public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
      return Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 1.5, 16.0);
   }

   public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
      return Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 3.0, 16.0);
   }

   @Override public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
      boolean bl = player.getStackInHand(hand).isOf(NSMiscBlocks.HELVOLA_FLOWER_ITEM) && (Integer)state.get(FLOWER_AMOUNT) < 4;
      if (bl) {
         world.setBlockState(pos, state.with(FLOWER_AMOUNT, Math.min(4, (Integer)state.get(FLOWER_AMOUNT) + 1)));
         if (!player.isCreative() && !player.isSpectator()) {
            player.getStackInHand(hand).decrement(1);
         }
      }
      return bl ? ActionResult.SUCCESS : super.onUse(state, world, pos, player, hand, hit);
   }

   public BlockState getPlacementState(ItemPlacementContext ctx) {
      return  (BlockState)this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
   }

   protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
      FluidState fluidState = world.getFluidState(pos);
      FluidState fluidState2 = world.getFluidState(pos.up());
      return (fluidState.getFluid() == Fluids.WATER || floor.getBlock() instanceof IceBlock) && fluidState2.getFluid() == Fluids.EMPTY;
   }
   public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
      super.onEntityCollision(state, world, pos, entity);
      if (world instanceof ServerWorld && entity instanceof BoatEntity) {
         world.breakBlock(new BlockPos(pos), true, entity);
      }

   }

   protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
      builder.add(new Property[]{FACING, FLOWER_AMOUNT});
   }

   public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
      return true;
   }

   public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
      return true;
   }

   public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
      int i = (Integer)state.get(FLOWER_AMOUNT);
      if (i < 4) {
         world.setBlockState(pos, (BlockState)state.with(FLOWER_AMOUNT, i + 1), 2);
      } else {
         dropStack(world, pos, new ItemStack(this));
      }

   }

   static {
      FACING = Properties.HORIZONTAL_FACING;
      FLOWER_AMOUNT = IntProperty.of("water_flower_amount", 0, 4);
   }

}
