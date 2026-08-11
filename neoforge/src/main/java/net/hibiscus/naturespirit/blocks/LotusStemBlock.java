package net.hibiscus.naturespirit.blocks;

import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class LotusStemBlock extends Block implements SimpleWaterloggedBlock {

  public static final IntegerProperty AGE;
  public static final BooleanProperty WATERLOGGED;
  public static final VoxelShape SHAPE = Block.box(1D, 0D, 1D, 15D, 16D, 15D);
  public static final Direction GROWTH_DIRECTION;

  static {
    GROWTH_DIRECTION = Direction.UP;
    AGE = BlockStateProperties.AGE_3;
    WATERLOGGED = BlockStateProperties.WATERLOGGED;
  }

  public final Block headBlock;

  public LotusStemBlock(Properties properties, Block headBlock) {
    super(properties);
    this.headBlock = headBlock;
    this.registerDefaultState(this.stateDefinition.any());
  }

  @Nullable
  public BlockState getStateForPlacement(BlockPlaceContext ctx) {
    BlockState blockState = ctx.getLevel().getBlockState(ctx.getClickedPos().relative(GROWTH_DIRECTION));
    FluidState fluidState = ctx.getLevel().getFluidState(ctx.getClickedPos());
    boolean waterlogged = fluidState.is(FluidTags.WATER) && fluidState.getAmount() == 8;
    return !blockState.is(this.asBlock()) && !blockState.is(headBlock) ?
        this.getRandomGrowthState(ctx.getLevel()).setValue(WATERLOGGED, waterlogged) :
        this.asBlock().defaultBlockState().setValue(WATERLOGGED, waterlogged);
  }

  @Override
  public boolean canSurvive(BlockState state, LevelReader levelReader, BlockPos pos) {
    BlockPos blockPos = pos.relative(GROWTH_DIRECTION.getOpposite());
    BlockState blockState = levelReader.getBlockState(blockPos);
    if (levelReader.getFluidState(pos).is(FluidTags.WATER)) {
      return (blockState.isFaceSturdy(levelReader, pos, Direction.UP) || blockState.is(this.asBlock())) && !blockState.is(Blocks.MAGMA_BLOCK);
    }
    return blockState.is(this.asBlock()) || blockState.is(BlockTags.DIRT) || blockState.is(Blocks.CLAY) || blockState.is(Blocks.FARMLAND);
  }

  @Override
  public boolean isRandomlyTicking(BlockState state) {
    return state.getValue(AGE) < 3;
  }

  @Override
  public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
    double growthChance = 0.1D;
    if (state.getValue(AGE) < 3 && random.nextDouble() < growthChance && this.isFertilizable(world, pos, state, world.isClientSide())) {
      BlockPos blockPos = pos.relative(GROWTH_DIRECTION);
      if (world.getBlockState(blockPos).isAir()) {
        world.setBlockAndUpdate(blockPos, headBlock.defaultBlockState());
      } else if (world.getFluidState(blockPos).is(FluidTags.WATER)) {
        world.setBlockAndUpdate(blockPos, this.defaultBlockState().setValue(WATERLOGGED, world.getFluidState(blockPos).is(FluidTags.WATER)).setValue(AGE, 2));
        if (world.getBlockState(blockPos.above()).isAir()) {
          world.setBlockAndUpdate(blockPos.above(), headBlock.defaultBlockState());
        }
      } else if (world.getBlockState(blockPos).is(headBlock)) {
        if (!world.getFluidState(pos).is(FluidTags.WATER)) {
          world.setBlockAndUpdate(blockPos, this.defaultBlockState().setValue(WATERLOGGED, world.getFluidState(blockPos).is(FluidTags.WATER)).setValue(AGE, Math.min(state.getValue(AGE) + 1, 3)));
          world.setBlockAndUpdate(blockPos.above(), headBlock.defaultBlockState());
        } else {
          world.setBlockAndUpdate(pos, this.defaultBlockState().setValue(WATERLOGGED, world.getFluidState(pos).is(FluidTags.WATER)).setValue(AGE, 3));
        }
      }
    }

  }

  @Override
  public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
    if (direction == GROWTH_DIRECTION.getOpposite() && !state.canSurvive(world, pos)) {
      world.scheduleTick(pos, this.asBlock(), 1);
    }
    if (direction == GROWTH_DIRECTION && neighborState.isAir()) {
      world.setBlock(pos, state.setValue(LotusStemBlock.AGE, 2), Block.UPDATE_CLIENTS);
    }
    return state;
  }

  public BlockState getRandomGrowthState(LevelAccessor world) {
    return this.defaultBlockState().setValue(AGE, world.getRandom().nextInt(3));
  }

  protected BlockState age(BlockState state, RandomSource random) {
    return state.cycle(AGE);
  }

  public boolean isFertilizable(LevelReader world, BlockPos pos, BlockState state, boolean isClient) {
    Optional<BlockPos> optional = this.getFlowerHeadPos(world, pos, this.asBlock());
    Optional<BlockPos> optional2 = this.getStemHeadPos(world, pos, this.asBlock());
    Optional<BlockPos> optional3 = this.getWaterPos(world, pos, this.asBlock());
    BlockPos blockPos = optional2.isPresent() ? optional2.get().below() : optional3.isPresent() ? optional3.get().below() : optional.orElse(pos);
    boolean lotusPowered = false;
    if (optional.isPresent()) {
      BlockPos blockPos2 = optional.get();
      LotusFlowerBlock lotusFlowerBlock = (LotusFlowerBlock) world.getBlockState(blockPos2).getBlock();
      lotusPowered = lotusFlowerBlock.isPowered(world, blockPos2);
    }
    return (world.getBlockState(blockPos.relative(GROWTH_DIRECTION)).isAir() || world.getFluidState(blockPos.relative(GROWTH_DIRECTION)).is(FluidTags.WATER)) && !lotusPowered;
  }

  public boolean canGrow(Level world, RandomSource random, BlockPos pos, BlockState state) {
    return true;
  }

  public void grow(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
    Optional<BlockPos> optional = this.getFlowerHeadPos(world, pos, this.asBlock());
    Optional<BlockPos> optional2 = this.getStemHeadPos(world, pos, this.asBlock());
    Optional<BlockPos> optional3 = this.getWaterPos(world, pos, this.asBlock());
    BlockPos blockPos = optional2.isPresent() ? optional2.get().below() : optional3.isPresent() ? optional3.get().below() : optional.orElse(pos);
    int i = Math.min(state.getValue(AGE) + 1, 3);

    if (world.getBlockState(blockPos).isAir() || world.getBlockState(blockPos).is(headBlock) || world.getBlockState(blockPos).is(this.asBlock()) || world.getFluidState(
        blockPos).is(FluidTags.WATER)) {
      world.setBlockAndUpdate(blockPos, state.setValue(AGE, i).setValue(WATERLOGGED, world.getFluidState(blockPos).is(FluidTags.WATER)));
      if (world.getBlockState(blockPos.above()).is(Blocks.AIR)) {
        world.setBlockAndUpdate(blockPos.relative(GROWTH_DIRECTION, 1), headBlock.defaultBlockState());
      }
      if (world.getBlockState(blockPos.above()).is(Blocks.WATER)) {
        world.setBlockAndUpdate(blockPos.relative(GROWTH_DIRECTION, 1),
            this.asBlock().defaultBlockState().setValue(AGE, i).setValue(WATERLOGGED, world.getFluidState(blockPos.relative(GROWTH_DIRECTION, 1)).is(FluidTags.WATER)));
      }
      if (optional.isPresent()) {
        BlockPos blockPos2 = optional.get();
        Player player = world.getNearestPlayer(blockPos2.getX(), blockPos2.getY(), blockPos2.getZ(), 1.25D, false);
        if (player != null) {
          player.move(MoverType.SHULKER_BOX, new Vec3(0, 1.01, 0));
        }
      }
    }

  }

  private Optional<BlockPos> getFlowerHeadPos(BlockGetter world, BlockPos pos, Block block) {
    return BlockUtil.getTopConnectedBlock(world, pos, block, GROWTH_DIRECTION, headBlock);
  }

  private Optional<BlockPos> getStemHeadPos(BlockGetter world, BlockPos pos, Block block) {
    return BlockUtil.getTopConnectedBlock(world, pos, block, GROWTH_DIRECTION, Blocks.AIR);
  }

  public static Optional<BlockPos> getStemHeadWaterPos(BlockGetter world, BlockPos pos, Block block) {
    return BlockUtil.getTopConnectedBlock(world, pos, Blocks.WATER, GROWTH_DIRECTION, block);
  }

  private Optional<BlockPos> getWaterPos(BlockGetter world, BlockPos pos, Block block) {
    return BlockUtil.getTopConnectedBlock(world, pos, block, GROWTH_DIRECTION, Blocks.WATER);
  }

  @Override
  public FluidState getFluidState(BlockState state) {
    return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
  }

  @Override
  public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
    if (!state.canSurvive(world, pos)) {
      world.destroyBlock(pos, true);
    }
  }

  @Override
  public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
    return SHAPE;
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    builder.add(AGE).add(WATERLOGGED);
  }

}
