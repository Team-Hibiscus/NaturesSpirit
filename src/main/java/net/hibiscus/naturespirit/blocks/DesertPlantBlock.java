package net.hibiscus.naturespirit.blocks;

import net.hibiscus.naturespirit.registration.block_registration.HibiscusMiscBlocks;
import net.hibiscus.naturespirit.util.HibiscusTags;
import net.minecraft.block.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import java.util.function.Supplier;

public class DesertPlantBlock extends PlantBlock implements Fertilizable {

   public static final int MAX_AGE = 7;
   public static final IntProperty AGE;
   protected static final float field_31256 = 1.0F;
   protected static final VoxelShape[] AGE_TO_SHAPE;

   static {
      AGE = Properties.AGE_7;
      AGE_TO_SHAPE = new VoxelShape[]{
              Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
              Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
              Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
              Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
              Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D),
              Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D),
              Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D),
              Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)
      };
   }

   private final Block rootBlock;
   private final DesertTurnipBlock vegetableBlock;
   private final Supplier <Item> pickBlockItem;

   public DesertPlantBlock(DesertTurnipBlock vegetableBlock, Block rootBlock, Settings settings) {
      super(settings);
      this.rootBlock = rootBlock;
      this.vegetableBlock = vegetableBlock;
      this.pickBlockItem = () -> HibiscusMiscBlocks.DESERT_TURNIP;
      this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0));
   }

   protected static float getAvailableMoisture(Block block, BlockView world, BlockPos pos) {
      float f = 1.0F;
      BlockPos blockPos = pos.down();

      for(int i = -1; i <= 1; ++i) {
         for(int j = -1; j <= 1; ++j) {
            float g = 0.0F;
            BlockState blockState = world.getBlockState(blockPos.add(i, 0, j));
            if(blockState.isOf(Blocks.FARMLAND)) {
               g = 1.0F;
               if(blockState.get(FarmlandBlock.MOISTURE) > 0) {
                  g = 3.0F;
               }
            }

            if(i != 0 || j != 0) {
               g /= 4.0F;
            }

            f += g;
         }
      }

      BlockPos blockPos2 = pos.north();
      BlockPos blockPos3 = pos.south();
      BlockPos blockPos4 = pos.west();
      BlockPos blockPos5 = pos.east();
      boolean bl = world.getBlockState(blockPos4).isOf(block) || world.getBlockState(blockPos5).isOf(block);
      boolean bl2 = world.getBlockState(blockPos2).isOf(block) || world.getBlockState(blockPos3).isOf(block);
      if(bl && bl2) {
         f /= 2.0F;
      }
      else {
         boolean bl3 = world.getBlockState(blockPos4.north()).isOf(block) || world.getBlockState(blockPos5.north()).isOf(block) || world.getBlockState(blockPos5.south()).isOf(block) || world
                 .getBlockState(blockPos4.south())
                 .isOf(block);
         if(bl3) {
            f /= 2.0F;
         }
      }

      return f;
   }

   protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
      return floor.isOf(Blocks.FARMLAND) || floor.isIn(HibiscusTags.Blocks.TURNIP_STEM_GROWS_ON);
   }

   public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
      return AGE_TO_SHAPE[state.get(AGE)];
   }

   public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
      if(world.getBaseLightLevel(pos, 0) >= 9) {
         float f = getAvailableMoisture(this, world, pos);
         if(random.nextInt((int) (25.0F / f) + 1) == 0) {
            int i = state.get(AGE);
            if(i < 7) {
               state = state.with(AGE, i + 1);
               world.setBlockState(pos, state, 2);
            }
            else {
               Direction direction = Direction.DOWN;
               BlockPos blockPos = pos.offset(direction, 2);
               BlockPos blockPos2 = pos.offset(direction, 3);
               BlockState blockState = world.getBlockState(blockPos);
               BlockState blockState2 = world.getBlockState(blockPos2);
               boolean bl = blockState.isIn(HibiscusTags.Blocks.TURNIP_ROOT_REPLACEABLE);
               boolean bl2 = blockState2.isIn(HibiscusTags.Blocks.TURNIP_ROOT_REPLACEABLE);
               if(bl) {
                  if(bl2 || blockState2.isOf(this.vegetableBlock)) {
                     world.setBlockState(blockPos, this.rootBlock.getDefaultState());
                  }
                  else {
                     world.setBlockState(blockPos, this.vegetableBlock.getDefaultState());
                  }
               }
               if(!blockState.isOf(this.rootBlock)) {
                  if(!blockState.isOf(this.vegetableBlock) && blockState2.isOf(this.vegetableBlock)) {
                     state = state.with(AGE, 7);
                     world.setBlockState(pos, state, 2);
                  }
               }
               if(blockState.isOf(this.rootBlock) && !blockState2.isOf(this.vegetableBlock) && bl2) {
                  world.setBlockState(blockPos2, this.vegetableBlock.getDefaultState());
               }
            }
         }

      }
   }

   public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
      return new ItemStack(this.pickBlockItem.get());
   }

   public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
      return state.get(AGE) < 7;
   }

   public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
      return true;
   }


   public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
      int i = Math.min(7, state.get(AGE) + MathHelper.nextInt(world.random, 2, 5));
      BlockState blockState = state.with(AGE, i);
      world.setBlockState(pos, blockState, 2);
      if(i == 7) {
         blockState.randomTick(world, pos, world.random);
      }

   }

   protected void appendProperties(StateManager.Builder <Block, BlockState> builder) {
      builder.add(AGE);
   }
}
