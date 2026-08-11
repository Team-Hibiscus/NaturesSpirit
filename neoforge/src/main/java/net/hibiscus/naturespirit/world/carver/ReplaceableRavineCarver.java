package net.hibiscus.naturespirit.world.carver;


import com.mojang.serialization.Codec;
import net.hibiscus.naturespirit.registration.NSBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.chunk.CarvingMask;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.carver.CarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CarvingContext;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class ReplaceableRavineCarver extends WorldCarver<ReplaceableRavineCarverConfig> {

  public ReplaceableRavineCarver(Codec<ReplaceableRavineCarverConfig> codec) {
    super(codec);
  }

  public boolean isStartChunk(ReplaceableRavineCarverConfig replaceableRavineCarverConfig, RandomSource random) {
    return random.nextFloat() <= replaceableRavineCarverConfig.probability;
  }

  public boolean carve(CarvingContext carverContext, ReplaceableRavineCarverConfig replaceableRavineCarverConfig, ChunkAccess chunk, Function<BlockPos, Holder<Biome>> function,
      RandomSource random, Aquifer aquiferSampler, ChunkPos chunkPos, CarvingMask carvingMask) {
    int i = (this.getRange() * 2 - 1) * 16;
    double d = chunkPos.getBlockX(random.nextInt(16));
    int j = replaceableRavineCarverConfig.y.sample(random, carverContext);
    double e = chunkPos.getBlockZ(random.nextInt(16));
    float f = random.nextFloat() * 6.2831855F;
    float g = replaceableRavineCarverConfig.verticalRotation.sample(random);
    double h = replaceableRavineCarverConfig.yScale.sample(random);
    float k = replaceableRavineCarverConfig.shape.thickness.sample(random);
    int l = (int) ((float) i * replaceableRavineCarverConfig.shape.distanceFactor.sample(random));
    boolean m = false;
    this.carveEllipsoid(carverContext, replaceableRavineCarverConfig, chunk, function, random.nextLong(), aquiferSampler, d, j, e, k, f, g, 0, l, h, carvingMask);
    return true;
  }

  private void carveEllipsoid(CarvingContext context, ReplaceableRavineCarverConfig config, ChunkAccess chunk, Function<BlockPos, Holder<Biome>> posToBiome, long seed,
      Aquifer aquiferSampler, double x, double y, double z, float width, float yaw, float pitch, int branchStartIndex, int branchCount, double yawPitchRatio,
      CarvingMask mask) {
    RandomSource random = RandomSource.create(seed);
    float[] fs = this.createHorizontalStretchFactors(context, config, random);
    float f = 0.0F;
    float g = 0.0F;

    for (int i = branchStartIndex; i < branchCount; ++i) {
      double d = 1.5 + (double) (Mth.sin((float) i * 3.1415927F / (float) branchCount) * width);
      double e = d * yawPitchRatio;
      d *= config.shape.horizontalRadiusFactor.sample(random);
      e = this.getVerticalScale(config, random, e, (float) branchCount, (float) i);
      float h = Mth.cos(pitch);
      float j = Mth.sin(pitch);
      x += Mth.cos(yaw) * h;
      y += j;
      z += Mth.sin(yaw) * h;
      pitch *= 0.7F;
      pitch += g * 0.05F;
      yaw += f * 0.05F;
      g *= 0.8F;
      f *= 0.5F;
      g += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
      f += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;
      if (random.nextInt(4) != 0) {
        if (!canReach(chunk.getPos(), x, z, i, branchCount, width)) {
          return;
        }

        this.carveEllipsoid(context, config, chunk, posToBiome, aquiferSampler, x, y, z, d, e, mask, (contextx, scaledRelativeX, scaledRelativeY, scaledRelativeZ, yx) -> {
          return this.isPositionExcluded(contextx, fs, scaledRelativeX, scaledRelativeY, scaledRelativeZ, yx);
        });
      }
    }

  }

  protected boolean carveBlock(CarvingContext context, ReplaceableRavineCarverConfig config, ChunkAccess chunk, Function<BlockPos, Holder<Biome>> posToBiome, CarvingMask mask,
      BlockPos.MutableBlockPos pos, BlockPos.MutableBlockPos tmp, Aquifer aquiferSampler, MutableBoolean replacedGrassy) {
    BlockState blockState = chunk.getBlockState(pos);
    if (blockState.is(Blocks.GRASS_BLOCK) || blockState.is(Blocks.MYCELIUM) || blockState.is(NSBlocks.RED_MOSS_BLOCK.get())) {
      replacedGrassy.setTrue();
    }
    if (!this.canReplaceBlock(config, blockState) && !isDebugEnabled(config)) {
      return false;
    } else {
      BlockState blockState2 = this.getState(context, config, pos, aquiferSampler, chunk);
      if (blockState2 == null) {
        return false;
      } else {
        chunk.setBlockState(pos, blockState2, false);
        if (aquiferSampler.shouldScheduleFluidUpdate() && !blockState2.getFluidState().isEmpty()) {
          chunk.markPosForPostprocessing(pos);
        }

        if (replacedGrassy.isTrue()) {
          tmp.setWithOffset(pos, Direction.DOWN);
          if (chunk.getBlockState(tmp).is(Blocks.DIRT)) {
            context.topMaterial(posToBiome, chunk, tmp, !blockState2.getFluidState().isEmpty()).ifPresent((state) -> {
              chunk.setBlockState(tmp, state, false);
              if (!state.getFluidState().isEmpty()) {
                chunk.markPosForPostprocessing(tmp);
              }

            });
          }
        }

        return true;
      }
    }
  }

  @Nullable
  private BlockState getState(CarvingContext context, ReplaceableRavineCarverConfig config, BlockPos pos, Aquifer sampler, ChunkAccess chunk) {
    if (pos.getY() <= config.lavaLevel.resolveY(context)) {
      return LAVA.createLegacyBlock();
    } else {
      BlockState blockState = sampler.computeSubstance(new DensityFunction.SinglePointContext(pos.getX(), pos.getY(), pos.getZ()), 0.0);
      if (blockState == null) {
        return isDebugEnabled(config) ? config.debugSettings.getBarrierState() : Blocks.ICE.defaultBlockState();
      } else {
        return isDebugEnabled(config) ? getDebugState(config, blockState) : (
            chunk.getHeight(Heightmap.Types.WORLD_SURFACE_WG, pos.getX(), pos.getZ()) == pos.getY() ? Blocks.ICE.defaultBlockState() : blockState);
      }
    }
  }

  private static BlockState getDebugState(CarverConfiguration config, BlockState state) {
    if (state.is(Blocks.AIR)) {
      return config.debugSettings.getAirState();
    } else if (state.is(Blocks.WATER)) {
      BlockState blockState = config.debugSettings.getWaterState();
      return blockState.hasProperty(BlockStateProperties.WATERLOGGED) ? blockState.setValue(BlockStateProperties.WATERLOGGED, true) : blockState;
    } else {
      return state.is(Blocks.LAVA) ? config.debugSettings.getLavaState() : state;
    }
  }

  private static boolean isDebugEnabled(CarverConfiguration config) {
    return config.debugSettings.isDebugMode();
  }

  private float[] createHorizontalStretchFactors(CarvingContext context, ReplaceableRavineCarverConfig config, RandomSource random) {
    int i = context.getGenDepth();
    float[] fs = new float[i];
    float f = 1.0F;

    for (int j = 0; j < i; ++j) {
      if (j == 0 || random.nextInt(config.shape.widthSmoothness) == 0) {
        f = 1.0F + random.nextFloat() * random.nextFloat();
      }

      fs[j] = f * f;
    }

    return fs;
  }

  private double getVerticalScale(ReplaceableRavineCarverConfig config, RandomSource random, double pitch, float branchCount, float branchIndex) {
    float f = 1.0F - Mth.abs(0.5F - branchIndex / branchCount) * 2.0F;
    float g = config.shape.verticalRadiusDefaultFactor + config.shape.verticalRadiusCenterFactor * f;
    return (double) g * pitch * (double) Mth.randomBetween(random, 0.75F, 1.0F);
  }

  private boolean isPositionExcluded(CarvingContext context, float[] horizontalStretchFactors, double scaledRelativeX, double scaledRelativeY, double scaledRelativeZ, int y) {
    int i = y - context.getMinGenY();
    return (scaledRelativeX * scaledRelativeX + scaledRelativeZ * scaledRelativeZ) * (double) horizontalStretchFactors[i - 1] + scaledRelativeY * scaledRelativeY / 6.0 >= 1.0;
  }
}
