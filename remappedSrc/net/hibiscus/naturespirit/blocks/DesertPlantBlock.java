package net.hibiscus.naturespirit.blocks;

import net.hibiscus.naturespirit.registration.HibiscusBlocksAndItems;
import net.hibiscus.naturespirit.util.HibiscusTags;
import net.minecraft.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import java.util.function.Supplier;

public class DesertPlantBlock extends BushBlock implements BonemealableBlock {

   public static final int MAX_AGE = 7;
   public static final IntegerProperty AGE;
   protected static final float field_31256 = 1.0F;
   protected static final VoxelShape[] AGE_TO_SHAPE;

   static {
      AGE = BlockStateProperties.AGE_7;
      AGE_TO_SHAPE = new VoxelShape[]{
              Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(
              0.0D,
              0.0D,
              0.0D,
              16.0D,
              4.0D,
              16.0D
      ), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D), Block.box(
              0.0D,
              0.0D,
              0.0D,
              16.0D,
              8.0D,
              16.0D
      ), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D), Block.box(
              0.0D,
              0.0D,
              0.0D,
              16.0D,
              12.0D,
              16.0D
      ), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D), Block.box(
              0.0D,
              0.0D,
              0.0D,
              16.0D,
              16.0D,
              16.0D
      )
      };
   }

   private final Block rootBlock;
   private final DesertTurnipBlock vegetableBlock;
   private final Supplier <Item> pickBlockItem;

   public DesertPlantBlock(DesertTurnipBlock vegetableBlock, Block rootBlock, Properties settings) {
      super(settings);
      this.rootBlock = rootBlock;
      this.vegetableBlock = vegetableBlock;
      this.pickBlockItem = () -> HibiscusBlocksAndItems.DESERT_TURNIP;
      this.registerDefaultState((BlockState) ((BlockState) this.stateDefinition.any()).setValue(AGE, 0));
   }

   protected static float getAvailableMoisture(Block block, BlockGetter world, BlockPos pos) {
      float f = 1.0F;
      BlockPos blockPos = pos.below();

      for(int i = -1; i <= 1; ++i) {
         for(int j = -1; j <= 1; ++j) {
            float g = 0.0F;
            BlockState blockState = world.getBlockState(blockPos.offset(i, 0, j));
            if(blockState.is(Blocks.FARMLAND)) {
               g = 1.0F;
               if((Integer) blockState.getValue(FarmBlock.MOISTURE) > 0) {
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
      boolean bl = world.getBlockState(blockPos4).is(block) || world.getBlockState(blockPos5).is(block);
      boolean bl2 = world.getBlockState(blockPos2).is(block) || world.getBlockState(blockPos3).is(block);
      if(bl && bl2) {
         f /= 2.0F;
      }
      else {
         boolean bl3 = world.getBlockState(blockPos4.north()).is(block) || world.getBlockState(blockPos5.north())
                 .is(block) || world.getBlockState(blockPos5.south())
                 .is(block) || world.getBlockState(blockPos4.south()).is(block);
         if(bl3) {
            f /= 2.0F;
         }
      }

      return f;
   }

   protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
      return floor.is(Blocks.FARMLAND) || floor.is(HibiscusTags.Blocks.TURNIP_STEM_GROWS_ON);
   }

   public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return AGE_TO_SHAPE[(Integer) state.getValue(AGE)];
   }

   public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
      if(world.getRawBrightness(pos, 0) >= 9) {
         float f = getAvailableMoisture(this, world, pos);
         if(random.nextInt((int) (25.0F / f) + 1) == 0) {
            int i = (Integer) state.getValue(AGE);
            if(i < 7) {
               state = (BlockState) state.setValue(AGE, i + 1);
               world.setBlock(pos, state, 2);
            }
            else {
               Direction direction = Direction.DOWN;
               BlockPos blockPos = pos.relative(direction, 2);
               BlockPos blockPos2 = pos.relative(direction, 3);
               BlockState blockState = world.getBlockState(blockPos);
               BlockState blockState2 = world.getBlockState(blockPos2);
               boolean bl = blockState.is(HibiscusTags.Blocks.TURNIP_ROOT_REPLACEABLE);
               boolean bl2 = blockState2.is(HibiscusTags.Blocks.TURNIP_ROOT_REPLACEABLE);
               if(bl) {
                  if(bl2 || blockState2.is(this.vegetableBlock)) {
                     world.setBlockAndUpdate(blockPos, this.rootBlock.defaultBlockState());
                  }
                  else {
                     world.setBlockAndUpdate(blockPos, this.vegetableBlock.defaultBlockState());
                  }
               }
               if (!blockState.is(this.rootBlock)) {
                  if (!blockState.is(this.vegetableBlock) && blockState2.is(this.vegetableBlock)) {
                     state = (BlockState) state.setValue(AGE, 7);
                     world.setBlock(pos, state, 2);
                  }
               }
               if (blockState.is(this.rootBlock) && !blockState2.is(this.vegetableBlock) && bl2) {
                  world.setBlockAndUpdate(blockPos2, this.vegetableBlock.defaultBlockState());
               }
            }
         }

      }
   }

   public ItemStack getCloneItemStack(BlockGetter world, BlockPos pos, BlockState state) {
      return new ItemStack((ItemLike) this.pickBlockItem.get());
   }

   public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state, boolean isClient) {
      return (Integer) state.getValue(AGE) < 7;
   }

   public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
      return true;
   }


   public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
      int i = Math.min(7, (Integer) state.getValue(AGE) + Mth.nextInt(world.random, 2, 5));
      BlockState blockState = (BlockState) state.setValue(AGE, i);
      world.setBlock(pos, blockState, 2);
      if(i == 7) {
         blockState.randomTick(world, pos, world.random);
      }

   }

   protected void createBlockStateDefinition(StateDefinition.Builder <Block, BlockState> builder) {
      builder.add(new Property[]{AGE});
   }
}
