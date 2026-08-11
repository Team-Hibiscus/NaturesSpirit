package net.hibiscus.naturespirit.world.carver;


import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.CarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CarverDebugSettings;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;

public class ReplaceableRavineCarverConfig extends CarverConfiguration {

  public static final Codec<ReplaceableRavineCarverConfig> RAVINE_CODEC = RecordCodecBuilder.create((instance) -> {
    return instance.group(CarverConfiguration.CODEC.forGetter((replaceableRavineCarverConfig) -> {
      return replaceableRavineCarverConfig;
    }), FloatProvider.CODEC.fieldOf("vertical_rotation").forGetter((config) -> {
      return config.verticalRotation;
    }), Shape.CODEC.fieldOf("shape").forGetter((config) -> {
      return config.shape;
    })).apply(instance, ReplaceableRavineCarverConfig::new);
  });
  public final FloatProvider verticalRotation;
  public final Shape shape;

  public ReplaceableRavineCarverConfig(float probability, HeightProvider y, FloatProvider yScale, VerticalAnchor lavaLevel, CarverDebugSettings debugConfig,
      HolderSet<Block> replaceable, FloatProvider verticalRotation, Shape shape) {
    super(probability, y, yScale, lavaLevel, debugConfig, replaceable);
    this.verticalRotation = verticalRotation;
    this.shape = shape;
  }

  public ReplaceableRavineCarverConfig(CarverConfiguration config, FloatProvider verticalRotation, Shape shape) {
    this(config.probability, config.y, config.yScale, config.lavaLevel, config.debugSettings, config.replaceable, verticalRotation, shape);
  }

  public static class Shape {

    public static final Codec<Shape> CODEC = RecordCodecBuilder.create((instance) -> {
      return instance.group(FloatProvider.CODEC.fieldOf("distance_factor").forGetter((shape) -> {
        return shape.distanceFactor;
      }), FloatProvider.CODEC.fieldOf("thickness").forGetter((shape) -> {
        return shape.thickness;
      }), ExtraCodecs.NON_NEGATIVE_INT.fieldOf("width_smoothness").forGetter((shape) -> {
        return shape.widthSmoothness;
      }), FloatProvider.CODEC.fieldOf("horizontal_radius_factor").forGetter((shape) -> {
        return shape.horizontalRadiusFactor;
      }), Codec.FLOAT.fieldOf("vertical_radius_default_factor").forGetter((shape) -> {
        return shape.verticalRadiusDefaultFactor;
      }), Codec.FLOAT.fieldOf("vertical_radius_center_factor").forGetter((shape) -> {
        return shape.verticalRadiusCenterFactor;
      })).apply(instance, Shape::new);
    });
    public final FloatProvider distanceFactor;
    public final FloatProvider thickness;
    public final int widthSmoothness;
    public final FloatProvider horizontalRadiusFactor;
    public final float verticalRadiusDefaultFactor;
    public final float verticalRadiusCenterFactor;

    public Shape(FloatProvider distanceFactor, FloatProvider thickness, int widthSmoothness, FloatProvider horizontalRadiusFactor, float verticalRadiusDefaultFactor,
        float verticalRadiusCenterFactor) {
      this.widthSmoothness = widthSmoothness;
      this.horizontalRadiusFactor = horizontalRadiusFactor;
      this.verticalRadiusDefaultFactor = verticalRadiusDefaultFactor;
      this.verticalRadiusCenterFactor = verticalRadiusCenterFactor;
      this.distanceFactor = distanceFactor;
      this.thickness = thickness;
    }
  }
}
