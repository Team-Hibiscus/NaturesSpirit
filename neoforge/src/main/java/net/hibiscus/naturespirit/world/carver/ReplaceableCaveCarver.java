package net.hibiscus.naturespirit.world.carver;

import com.mojang.serialization.Codec;
import net.hibiscus.naturespirit.registration.NSBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.SectionPos;
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

public class ReplaceableCaveCarver extends WorldCarver<ReplaceableCaveCarverConfig> {

  public ReplaceableCaveCarver(Codec<ReplaceableCaveCarverConfig> codec) {
    super(codec);
  }

  public boolean isStartChunk(ReplaceableCaveCarverConfig replaceableCaveCarverConfig, RandomSource random) {
    return random.nextFloat() <= replaceableCaveCarverConfig.probability;
  }

  public boolean carve(CarvingContext carverContext, ReplaceableCaveCarverConfig replaceableCaveCarverConfig, ChunkAccess chunk, Function<BlockPos, Holder<Biome>> function,
      RandomSource random, Aquifer aquiferSampler, ChunkPos chunkPos, CarvingMask carvingMask) {
    int i = SectionPos.sectionToBlockCoord(this.getRange() * 2 - 1);
    int j = random.nextInt(random.nextInt(random.nextInt(this.getMaxCaveCount()) + 1) + 1);

    for (int k = 0; k < j; ++k) {
      double d = chunkPos.getBlockX(random.nextInt(16));
      double e = replaceableCaveCarverConfig.y.sample(random, carverContext);
      double f = chunkPos.getBlockZ(random.nextInt(16));
      double g = replaceableCaveCarverConfig.horizontalRadiusMultiplier.sample(random);
      double h = replaceableCaveCarverConfig.verticalRadiusMultiplier.sample(random);
      double l = replaceableCaveCarverConfig.floorLevel.sample(random);
      CarveSkipChecker skipPredicate = (contextx, scaledRelativeX, scaledRelativeY, scaledRelativeZ, y) -> {
        return isPositionExcluded(scaledRelativeX, scaledRelativeY, scaledRelativeZ, l);
      };
      int m = 1;
      float o;
      if (random.nextInt(4) == 0) {
        double n = replaceableCaveCarverConfig.yScale.sample(random);
        o = 1.0F + random.nextFloat() * 6.0F;
        this.carveCave(carverContext, replaceableCaveCarverConfig, chunk, function, aquiferSampler, d, e, f, o, n, carvingMask, skipPredicate);
        m += random.nextInt(4);
      }

      for (int p = 0; p < m; ++p) {
        float q = random.nextFloat() * 6.2831855F;
        o = (random.nextFloat() - 0.5F) / 4.0F;
        float r = this.getTunnelSystemWidth(random);
        int s = i - random.nextInt(i / 4);
        this.carveTunnels(carverContext, replaceableCaveCarverConfig, chunk, function, random.nextLong(), aquiferSampler, d, e, f, g, h, r, q, o, 0, s,
            this.getTunnelSystemHeightWidthRatio(), carvingMask, skipPredicate);
      }
    }

    return true;
  }

  protected boolean carveBlock(CarvingContext context, ReplaceableCaveCarverConfig config, ChunkAccess chunk, Function<BlockPos, Holder<Biome>> posToBiome, CarvingMask mask,
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
  private BlockState getState(CarvingContext context, ReplaceableCaveCarverConfig config, BlockPos pos, Aquifer sampler, ChunkAccess chunk) {
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

  protected int getMaxCaveCount() {
    return 15;
  }

  protected float getTunnelSystemWidth(RandomSource random) {
    float f = random.nextFloat() * 2.0F + random.nextFloat();
    if (random.nextInt(10) == 0) {
      f *= random.nextFloat() * random.nextFloat() * 3.0F + 1.0F;
    }

    return f;
  }

  protected double getTunnelSystemHeightWidthRatio() {
    return 1.0;
  }

  protected void carveCave(CarvingContext context, ReplaceableCaveCarverConfig config, ChunkAccess chunk, Function<BlockPos, Holder<Biome>> posToBiome,
      Aquifer aquiferSampler, double d, double e, double f, float g, double h, CarvingMask mask, CarveSkipChecker skipPredicate) {
    double i = 1.5 + (double) (Mth.sin(1.5707964F) * g);
    double j = i * h;
    this.carveEllipsoid(context, config, chunk, posToBiome, aquiferSampler, d + 1.0, e, f, i, j, mask, skipPredicate);
  }

  protected void carveTunnels(CarvingContext context, ReplaceableCaveCarverConfig config, ChunkAccess chunk, Function<BlockPos, Holder<Biome>> posToBiome, long seed,
      Aquifer aquiferSampler, double x, double y, double z, double horizontalScale, double verticalScale, float width, float yaw, float pitch, int branchStartIndex,
      int branchCount, double yawPitchRatio, CarvingMask mask, CarveSkipChecker skipPredicate) {
    RandomSource random = RandomSource.create(seed);
    int i = random.nextInt(branchCount / 2) + branchCount / 4;
    boolean bl = random.nextInt(6) == 0;
    float f = 0.0F;
    float g = 0.0F;

    for (int j = branchStartIndex; j < branchCount; ++j) {
      double d = 1.5 + (double) (Mth.sin(3.1415927F * (float) j / (float) branchCount) * width);
      double e = d * yawPitchRatio;
      float h = Mth.cos(pitch);
      x += Mth.cos(yaw) * h;
      y += Mth.sin(pitch);
      z += Mth.sin(yaw) * h;
      pitch *= bl ? 0.92F : 0.7F;
      pitch += g * 0.1F;
      yaw += f * 0.1F;
      g *= 0.9F;
      f *= 0.75F;
      g += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
      f += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;
      if (j == i && width > 1.0F) {
        this.carveTunnels(context, config, chunk, posToBiome, random.nextLong(), aquiferSampler, x, y, z, horizontalScale, verticalScale, random.nextFloat() * 0.5F + 0.5F,
            yaw - 1.5707964F, pitch / 3.0F, j, branchCount, 1.0, mask, skipPredicate);
        this.carveTunnels(context, config, chunk, posToBiome, random.nextLong(), aquiferSampler, x, y, z, horizontalScale, verticalScale, random.nextFloat() * 0.5F + 0.5F,
            yaw + 1.5707964F, pitch / 3.0F, j, branchCount, 1.0, mask, skipPredicate);
        return;
      }

      if (random.nextInt(4) != 0) {
        if (!canReach(chunk.getPos(), x, z, j, branchCount, width)) {
          return;
        }

        this.carveEllipsoid(context, config, chunk, posToBiome, aquiferSampler, x, y, z, d * horizontalScale, e * verticalScale, mask, skipPredicate);
      }
    }

  }

  private static boolean isPositionExcluded(double scaledRelativeX, double scaledRelativeY, double scaledRelativeZ, double floorY) {
    if (scaledRelativeY <= floorY) {
      return true;
    } else {
      return scaledRelativeX * scaledRelativeX + scaledRelativeY * scaledRelativeY + scaledRelativeZ * scaledRelativeZ >= 1.0;
    }
  }
}
