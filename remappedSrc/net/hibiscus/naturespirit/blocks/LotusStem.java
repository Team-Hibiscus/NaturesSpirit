package net.hibiscus.naturespirit.blocks;

import net.hibiscus.naturespirit.registration.block_registration.HibiscusWoods;
import net.minecraft.BlockUtil;
import net.minecraft.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.NetherVines;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class LotusStem extends GrowingPlantBodyBlock implements SimpleWaterloggedBlock {
   public static final IntegerProperty AGE;
   public static final BooleanProperty WATERLOGGED;
   public static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

   static {
      AGE = BlockStateProperties.AGE_3;
      WATERLOGGED = BlockStateProperties.WATERLOGGED;
   }

   private final double growthChance;
   public Block headBlock;
   public Direction growthDirection = Direction.UP;

   public LotusStem(Properties properties, Block headBlock) {
      super(properties, Direction.UP, SHAPE, false);
      this.headBlock = headBlock;
      this.growthChance = 0.1D;
      this.registerDefaultState(this.stateDefinition.any());
   }

   protected GrowingPlantHeadBlock getHeadBlock() {
      return (GrowingPlantHeadBlock) HibiscusWoods.WISTERIA.getBlueWisteriaVines();
   }

   @Nullable public BlockState getStateForPlacement(BlockPlaceContext ctx) {
      BlockState blockState = ctx.getLevel().getBlockState(ctx.getClickedPos().relative(this.growthDirection));
      FluidState fluidState = ctx.getLevel().getFluidState(ctx.getClickedPos());
      boolean waterlogged = fluidState.is(FluidTags.WATER) && fluidState.getAmount() == 8;
      return !blockState.is(this.asBlock()) && !blockState.is(this.getBodyBlock()) ? this.getStateForPlacement(ctx.getLevel()).setValue(WATERLOGGED, waterlogged) : this.asBlock().defaultBlockState().setValue(WATERLOGGED,
              waterlogged
      );
   }

   @Override public boolean canSurvive(BlockState state, LevelReader levelReader, BlockPos pos) {
      BlockPos blockPos = pos.relative(this.growthDirection.getOpposite());
      BlockState blockState = levelReader.getBlockState(blockPos);
      if(levelReader.getFluidState(pos).is(FluidTags.WATER)) {
         return (blockState.isFaceSturdy(levelReader, pos, Direction.UP) || blockState.is(this.asBlock())) && !blockState.is(Blocks.MAGMA_BLOCK);
      }
      return blockState.is(this.asBlock()) || blockState.is(BlockTags.DIRT) || blockState.is(Blocks.CLAY) || blockState.is(Blocks.FARMLAND);
   }

   public BlockState getStateForPlacement(LevelAccessor world) {
      return this.defaultBlockState().setValue(AGE, world.getRandom().nextInt(3));
   }

   public boolean isRandomlyTicking(BlockState state) {
      return state.getValue(AGE) < 3;
   }

   public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
      if(state.getValue(AGE) < 3 && random.nextDouble() < this.growthChance) {
         BlockPos blockPos = pos.relative(this.growthDirection);
         if(this.chooseStemState(world.getBlockState(blockPos))) {
            world.setBlockAndUpdate(blockPos, this.getBodyBlock().defaultBlockState());
         }
         else if(world.getFluidState(blockPos).is(FluidTags.WATER)) {
            world.setBlockAndUpdate(blockPos, this.defaultBlockState().setValue(WATERLOGGED, world.getFluidState(blockPos).is(FluidTags.WATER)).setValue(AGE, 2));
            if(this.chooseStemState(world.getBlockState(blockPos.above()))) {
               world.setBlockAndUpdate(blockPos.above(), this.getBodyBlock().defaultBlockState());
            }
         }
         else if(world.getBlockState(blockPos).is(this.getBodyBlock())) {
            if(!world.getFluidState(pos).is(FluidTags.WATER)) {
               world.setBlockAndUpdate(blockPos, this.defaultBlockState().setValue(WATERLOGGED, world.getFluidState(blockPos).is(FluidTags.WATER)).setValue(AGE, Math.min(state.getValue(AGE) + 1, 3)));
               world.setBlockAndUpdate(blockPos.above(), this.getBodyBlock().defaultBlockState());
            }
            else {
               world.setBlockAndUpdate(pos, this.defaultBlockState().setValue(WATERLOGGED, world.getFluidState(pos).is(FluidTags.WATER)).setValue(AGE, 3));
            }
         }
      }

   }

   public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
      if(direction == this.growthDirection.getOpposite() && !state.canSurvive(world, pos)) {
         world.scheduleTick(pos, this.asBlock(), 1);
      }

      if(direction != this.growthDirection || !neighborState.is(this.asBlock()) && !neighborState.is(this.getBodyBlock())) {
         if(this.scheduleFluidTicks) {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
         }
      }
      if(direction == this.growthDirection && neighborState.isAir()) {
         world.setBlock(pos, state.setValue(LotusStem.AGE, 2), 2);
      }
      return state;
   }

   protected void createBlockStateDefinition(StateDefinition.Builder <Block, BlockState> builder) {
      builder.add(AGE).add(WATERLOGGED);
   }

   public ItemStack getCloneItemStack(BlockGetter world, BlockPos pos, BlockState state) {
      return new ItemStack(this.asItem());
   }

   protected BlockState age(BlockState state, RandomSource random) {
      return state.cycle(AGE);
   }

   protected BlockState updateHeadAfterConvertedFromBody(BlockState from, BlockState to) {
      return to;
   }

   public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state, boolean isClient) {
      Optional <BlockPos> optional = this.getFlowerHeadPos(world, pos, this.asBlock());
      Optional <BlockPos> optional2 = this.getHeadPos(world, pos, this.asBlock());
      Optional <BlockPos> optional3 = this.getHeadPos(world, pos, this.asBlock());
      BlockPos blockPos = optional2.isPresent() ? optional2.get().below() : optional3.isPresent() ? optional3.get().below() : optional.orElse(pos);
      return this.chooseStemState(world.getBlockState(blockPos.relative(this.growthDirection))) || world.getFluidState(blockPos.relative(this.growthDirection)).is(FluidTags.WATER);
   }

   public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
      return true;
   }

   public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
      Optional <BlockPos> optional = this.getFlowerHeadPos(world, pos, this.asBlock());
      Optional <BlockPos> optional2 = this.getHeadPos(world, pos, this.asBlock());
      Optional <BlockPos> optional3 = this.getHeadPos(world, pos, this.asBlock());
      BlockPos blockPos = optional2.isPresent() ? optional2.get().below() : optional3.isPresent() ? optional3.get().below() : optional.orElse(pos);
      int i = Math.min(state.getValue(AGE) + 1, 3);
      int j = Math.max(3, this.getGrowthLength(random));

      for(int k = 0; k < j && (this.chooseStemState(world.getBlockState(blockPos)) || world.getBlockState(blockPos).is(this.getBodyBlock()) || world.getBlockState(blockPos).is(this.asBlock()) || world
              .getFluidState(blockPos)
              .is(FluidTags.WATER)); ++k) {
         world.setBlockAndUpdate(blockPos, state.setValue(AGE, i).setValue(WATERLOGGED, world.getFluidState(blockPos).is(FluidTags.WATER)));
         if(world.getBlockState(blockPos.above()).is(Blocks.AIR)) {
            world.setBlockAndUpdate(blockPos.relative(this.growthDirection, 1), this.getBodyBlock().defaultBlockState());
         }
         if(world.getBlockState(blockPos.above()).is(Blocks.WATER)) {
            world.setBlockAndUpdate(blockPos.relative(this.growthDirection, 1),
                    this.asBlock().defaultBlockState().setValue(AGE, i).setValue(WATERLOGGED, world.getFluidState(blockPos.relative(this.growthDirection, 1)).is(FluidTags.WATER))
            );
         }
         blockPos = blockPos.relative(this.growthDirection);
         i = Math.min(i + 1, 3);
      }

   }

   private Optional <BlockPos> getFlowerHeadPos(BlockGetter world, BlockPos pos, Block block) {
      return BlockUtil.getTopConnectedBlock(world, pos, block, this.growthDirection, this.getBodyBlock());
   }

   private Optional <BlockPos> getHeadPos(BlockGetter world, BlockPos pos, Block block) {
      return BlockUtil.getTopConnectedBlock(world, pos, block, this.growthDirection, Blocks.AIR);
   }

   public static Optional <BlockPos> getStemHeadWaterPos(BlockGetter world, BlockPos pos, Block block) {
      return BlockUtil.getTopConnectedBlock(world, pos, Blocks.WATER, Direction.UP, block);
   }

   protected int getGrowthLength(RandomSource randomSource) {
      return NetherVines.getBlocksToGrowWhenBonemealed(randomSource);
   }

   public Block getBodyBlock() {
      return headBlock;
   }

   public boolean chooseStemState(BlockState state) {
      return state.isAir();
   }

   public FluidState getFluidState(BlockState state) {
      return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
   }
}
