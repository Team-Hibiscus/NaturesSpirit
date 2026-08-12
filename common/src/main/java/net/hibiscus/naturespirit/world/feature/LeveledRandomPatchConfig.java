package net.hibiscus.naturespirit.world.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public record LeveledRandomPatchConfig(int tries, int xzSpread, int ySpread, Holder<PlacedFeature> feature) implements FeatureConfiguration {

  public static final Codec<LeveledRandomPatchConfig> CODEC = RecordCodecBuilder.create((instance) -> {
    return instance.group(ExtraCodecs.POSITIVE_INT.fieldOf("tries").orElse(128).forGetter((config) -> {
          return config.tries;
        }), ExtraCodecs.NON_NEGATIVE_INT.fieldOf("xz_spread").orElse(7).forGetter((config) -> {
          return config.xzSpread;
        }), ExtraCodecs.NON_NEGATIVE_INT.fieldOf("y_spread").orElse(3).forGetter((config) -> {
          return config.ySpread;
        }), PlacedFeature.CODEC.fieldOf("feature").forGetter((config) -> {
          return config.feature;
        })
    ).apply(instance, LeveledRandomPatchConfig::new);
  });
}
