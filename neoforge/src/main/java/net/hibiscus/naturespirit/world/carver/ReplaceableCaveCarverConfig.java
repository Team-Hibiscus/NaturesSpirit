package net.hibiscus.naturespirit.world.carver;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.CarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CarverDebugSettings;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;

public class ReplaceableCaveCarverConfig extends CarverConfiguration {

  public static final Codec<ReplaceableCaveCarverConfig> CAVE_CODEC = RecordCodecBuilder.create((instance) -> {
    return instance.group(CarverConfiguration.CODEC.forGetter((config) -> {
      return config;
    }), FloatProvider.CODEC.fieldOf("horizontal_radius_multiplier").forGetter((config) -> {
      return config.horizontalRadiusMultiplier;
    }), FloatProvider.CODEC.fieldOf("vertical_radius_multiplier").forGetter((config) -> {
      return config.verticalRadiusMultiplier;
    }), FloatProvider.codec(-1.0F, 1.0F).fieldOf("floor_level").forGetter((config) -> {
      return config.floorLevel;
    })).apply(instance, ReplaceableCaveCarverConfig::new);
  });
  public final FloatProvider horizontalRadiusMultiplier;
  public final FloatProvider verticalRadiusMultiplier;
  final FloatProvider floorLevel;

  public ReplaceableCaveCarverConfig(float probability, HeightProvider y, FloatProvider yScale, VerticalAnchor lavaLevel, CarverDebugSettings debugConfig,
      HolderSet<Block> replaceable, FloatProvider horizontalRadiusMultiplier, FloatProvider verticalRadiusMultiplier, FloatProvider floorLevel) {
    super(probability, y, yScale, lavaLevel, debugConfig, replaceable);
    this.horizontalRadiusMultiplier = horizontalRadiusMultiplier;
    this.verticalRadiusMultiplier = verticalRadiusMultiplier;
    this.floorLevel = floorLevel;
  }

  public ReplaceableCaveCarverConfig(float probability, HeightProvider y, FloatProvider yScale, VerticalAnchor lavaLevel, HolderSet<Block> replaceable,
      FloatProvider horizontalRadiusMultiplier, FloatProvider verticalRadiusMultiplier, FloatProvider floorLevel) {
    this(probability, y, yScale, lavaLevel, CarverDebugSettings.DEFAULT, replaceable, horizontalRadiusMultiplier, verticalRadiusMultiplier, floorLevel);
  }

  public ReplaceableCaveCarverConfig(CarverConfiguration config, FloatProvider horizontalRadiusMultiplier, FloatProvider verticalRadiusMultiplier, FloatProvider floorLevel) {
    this(config.probability, config.y, config.yScale, config.lavaLevel, config.debugSettings, config.replaceable, horizontalRadiusMultiplier, verticalRadiusMultiplier, floorLevel);
  }
}
