package net.hibiscus.naturespirit.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.hibiscus.naturespirit.config.NSConfig;
import net.hibiscus.naturespirit.registration.NSBiomes;
import net.hibiscus.naturespirit.registration.NSWorldGen;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.util.Mth;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.BlockColumn;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SurfaceSystem.class)
public class SurfaceBuilderMixin {

  @Final
  @Shadow
  private BlockState defaultBlock;
  @Unique
  private NormalNoise naturespirit$sugiPillarNoise;
  @Unique
  private NormalNoise naturespirit$sugiPillarRoofNoise;
  @Unique
  private NormalNoise naturespirit$sugiSurfaceNoise;
  @Unique
  private NormalNoise naturespirit$stratifiedDesertPillarNoise;
  @Unique
  private NormalNoise naturespirit$stratifiedDesertPillarRoofNoise;
  @Unique
  private NormalNoise naturespirit$stratifiedDesertSurfaceNoise;

  @Inject(method = "<init>", at = @At(value = "TAIL"))
  private void injectNoise(RandomState noiseConfig, BlockState p_224638_, int seaLevel, PositionalRandomFactory randomDeriver, CallbackInfo ci) {
    naturespirit$sugiPillarNoise = noiseConfig.getOrCreateNoise(NSWorldGen.SUGI_PILLAR);
    naturespirit$sugiPillarRoofNoise = noiseConfig.getOrCreateNoise(NSWorldGen.SUGI_PILLAR_ROOF);
    naturespirit$sugiSurfaceNoise = noiseConfig.getOrCreateNoise(NSWorldGen.SUGI_SURFACE);
    naturespirit$stratifiedDesertPillarNoise = noiseConfig.getOrCreateNoise(NSWorldGen.STRATIFIED_DESERT_PILLAR);
    naturespirit$stratifiedDesertPillarRoofNoise = noiseConfig.getOrCreateNoise(NSWorldGen.STRATIFIED_DESERT_PILLAR_ROOF);
    naturespirit$stratifiedDesertSurfaceNoise = noiseConfig.getOrCreateNoise(NSWorldGen.STRATIFIED_DESERT_SURFACE);
  }

  @Inject(method = "buildSurface", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/Holder;is(Lnet/minecraft/resources/ResourceKey;)Z", ordinal = 0))
  private void injectPillars(RandomState noiseConfig, BiomeManager biomeAccess, Registry<Biome> biomeRegistry, boolean useLegacyRandom, WorldGenerationContext heightContext,
      ChunkAccess chunk, NoiseChunk chunkNoiseSampler, SurfaceRules.RuleSource materialRule,
      CallbackInfo ci,
      @Local Holder<Biome> registryEntry, @Local(ordinal = 2) int k, @Local(ordinal = 3) int l, @Local(ordinal = 4) int m, @Local(ordinal = 5) int n,
      @Local BlockColumn blockColumn) {
    if (NSConfig.sugiAndStratifiedPillars) {
      int o = chunk.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, k, l) + 1;
      if (registryEntry.is(NSBiomes.SUGI_FOREST) || registryEntry.is(NSBiomes.BLOOMING_SUGI_FOREST)) {
        this.naturespirit$placeSugiPillar(blockColumn, m, n, o, chunk);
      }
      if (registryEntry.is(NSBiomes.STRATIFIED_DESERT) || registryEntry.is(NSBiomes.LIVELY_DUNES) || registryEntry.is(NSBiomes.BLOOMING_DUNES)) {
        this.naturespirit$placeStratifiedDesertPillar(blockColumn, m, n, o, chunk);
      }
    }
  }

  @Unique
  private void naturespirit$placeSugiPillar(BlockColumn column, int x, int z, int surfaceY, LevelHeightAccessor chunk) {
    double e = Math.min(Math.abs(naturespirit$sugiSurfaceNoise.getValue(x, 0.0, z) * 8.5), naturespirit$sugiPillarRoofNoise.getValue((double) x * 0.2, 0.0, (double) z * 0.2) * 12.0);
    if (e > -10.0) {
      double h = Math.abs(naturespirit$sugiPillarNoise.getValue((double) x * 0.9, 0.0, (double) z * 0.8) * 2.05);
      double i = 32.0 + Math.min(e * e * 6.75, Math.ceil(h * 30.0) + 48.0);
      int j = Mth.floor(i);
      if (surfaceY <= j) {
        int k;
        for (k = j; k >= chunk.getMinBuildHeight(); --k) {
          BlockState blockState = column.getBlock(k);
          if (blockState.is(this.defaultBlock.getBlock())) {
            break;
          }
        }

        for (k = j; k >= chunk.getMinBuildHeight() && (column.getBlock(k).isAir() || column.getBlock(k).is(Blocks.WATER)); --k) {
          column.setBlock(k, this.defaultBlock);
        }


      }
    }
  }

  @Unique
  private void naturespirit$placeStratifiedDesertPillar(BlockColumn column, int x, int z, int surfaceY, LevelHeightAccessor chunk) {
    double e = Math.min(Math.abs(naturespirit$stratifiedDesertSurfaceNoise.getValue(x, 0.0, z) * 8.5), naturespirit$stratifiedDesertPillarNoise.getValue((double) x * 0.2, 0.0, (double) z * 0.2) * 14.0);
    if (!(e <= 0.0)) {
      double h = Math.abs(naturespirit$stratifiedDesertPillarRoofNoise.getValue((double) x * 0.75, 0.0, (double) z * 0.75) * 2.25);
      double i = 54.0 + Math.min(e * e * 3.5, Math.ceil(h * 30.0) + 38.0);
      int j = Mth.floor(i);
      if (surfaceY <= j) {
        int k;
        for (k = j; k >= chunk.getMinBuildHeight(); --k) {
          BlockState blockState = column.getBlock(k);
          if (blockState.is(this.defaultBlock.getBlock())) {
            break;
          }
        }

        for (k = j; k >= chunk.getMinBuildHeight() && (column.getBlock(k).isAir() || column.getBlock(k).is(Blocks.WATER)); --k) {
          column.setBlock(k, this.defaultBlock);
        }

      }
    }
  }

}
