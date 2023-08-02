package net.hibiscus.naturespirit.terrablender;

import com.mojang.datafixers.util.Pair;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class HibiscusRegion2 extends Region {
   public HibiscusRegion2(Identifier name, int weight) {
      super(name, RegionType.OVERWORLD, weight);
   }

   @Override
   public void addBiomes(Registry <Biome> registry, Consumer <Pair <MultiNoiseUtil.NoiseHypercube, RegistryKey <Biome>>> mapper) {
      this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
         builder.replaceBiome(BiomeKeys.SUNFLOWER_PLAINS, HibiscusBiomes.LAVENDER_FIELDS);
         builder.replaceBiome(BiomeKeys.FLOWER_FOREST, HibiscusBiomes.LAVENDER_FIELDS);
         builder.replaceBiome(BiomeKeys.RIVER, HibiscusBiomes.ERODED_RIVER);
      });


      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, 0.2F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.11F, 0.03F),
              MultiNoiseUtil.ParameterRange.of(-1F, -0.375F),
              MultiNoiseUtil.ParameterRange.of(0F, 0F),
              MultiNoiseUtil.ParameterRange.of(-1F, -0.7666667F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, 0.2F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
              MultiNoiseUtil.ParameterRange.of(-1F, 0.05F),
              MultiNoiseUtil.ParameterRange.of(0F, 0F),
              MultiNoiseUtil.ParameterRange.of(-1F, -0.93333334F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, -0.15F),
              MultiNoiseUtil.ParameterRange.of(0.1F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.78F, -0.2225F),
              MultiNoiseUtil.ParameterRange.of(0F, 0F),
              MultiNoiseUtil.ParameterRange.of(-1F, -0.93333334F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, 0.2F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.78F, 0.05F),
              MultiNoiseUtil.ParameterRange.of(0F, 0F),
              MultiNoiseUtil.ParameterRange.of(-0.93333334F, -0.7666667F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, -0.15F),
              MultiNoiseUtil.ParameterRange.of(0.1F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.375F, -0.2225F),
              MultiNoiseUtil.ParameterRange.of(0F, 0F),
              MultiNoiseUtil.ParameterRange.of(-0.93333334F, -0.4F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, -0.15F),
              MultiNoiseUtil.ParameterRange.of(0.1F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.2225F, 0.05F),
              MultiNoiseUtil.ParameterRange.of(0F, 0F),
              MultiNoiseUtil.ParameterRange.of(-0.93333334F, -0.4F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, 0.2F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.19F, 0.03F),
              MultiNoiseUtil.ParameterRange.of(-0.78F, -0.375F),
              MultiNoiseUtil.ParameterRange.of(0F, 0F),
              MultiNoiseUtil.ParameterRange.of(-0.7666667F, -0.56666666F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.15F, 0.2F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.375F, -0.2225F),
              MultiNoiseUtil.ParameterRange.of(0F, 0F),
              MultiNoiseUtil.ParameterRange.of(-0.7666667F, -0.05F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.15F, 0.2F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.2225F, 0.05F),
              MultiNoiseUtil.ParameterRange.of(0F, 0F),
              MultiNoiseUtil.ParameterRange.of(-0.7666667F, -0.05F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, 0.2F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.11F, 0.03F),
              MultiNoiseUtil.ParameterRange.of(-1F, -0.375F),
              MultiNoiseUtil.ParameterRange.of(0F, 0F),
              MultiNoiseUtil.ParameterRange.of(-0.56666666F, -0.26666668F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, 0.2F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.78F, 0.05F),
              MultiNoiseUtil.ParameterRange.of(0F, 0F),
              MultiNoiseUtil.ParameterRange.of(-0.56666666F, -0.05F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, 0.2F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
              MultiNoiseUtil.ParameterRange.of(-1F, 0.05F),
              MultiNoiseUtil.ParameterRange.of(0F, 0F),
              MultiNoiseUtil.ParameterRange.of(-0.4F, -0.05F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, -0.15F),
              MultiNoiseUtil.ParameterRange.of(0.1F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.78F, -0.2225F),
              MultiNoiseUtil.ParameterRange.of(0F, 0F),
              MultiNoiseUtil.ParameterRange.of(-0.4F, -0.26666668F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, 0.2F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
              MultiNoiseUtil.ParameterRange.of(-1F, -0.375F),
              MultiNoiseUtil.ParameterRange.of(0F, 0F),
              MultiNoiseUtil.ParameterRange.of(-0.05F, 0.4F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, 0.2F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.375F, 0.05F),
              MultiNoiseUtil.ParameterRange.of(0F, 0F),
              MultiNoiseUtil.ParameterRange.of(0.05F, 0.56666666F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, 0.2F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.11F, 0.03F),
              MultiNoiseUtil.ParameterRange.of(-1F, -0.375F),
              MultiNoiseUtil.ParameterRange.of(0F, 0F),
              MultiNoiseUtil.ParameterRange.of(0.26666668F, 0.56666666F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, -0.15F),
              MultiNoiseUtil.ParameterRange.of(-0.1F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.78F, -0.2225F),
              MultiNoiseUtil.ParameterRange.of(0F, 0F),
              MultiNoiseUtil.ParameterRange.of(0.26666668F, 0.4F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, 0.2F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.78F, 0.05F),
              MultiNoiseUtil.ParameterRange.of(0F, 0F),
              MultiNoiseUtil.ParameterRange.of(0.4F, 0.56666666F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, -0.15F),
              MultiNoiseUtil.ParameterRange.of(-0.1F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.375F, -0.2225F),
              MultiNoiseUtil.ParameterRange.of(0F, 0F),
              MultiNoiseUtil.ParameterRange.of(0.4F, 0.93333334F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, -0.15F),
              MultiNoiseUtil.ParameterRange.of(-0.1F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.2225F, 0.05F),
              MultiNoiseUtil.ParameterRange.of(0F, 0F),
              MultiNoiseUtil.ParameterRange.of(0.4F, 0.93333334F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, 0.2F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.19F, 0.03F),
              MultiNoiseUtil.ParameterRange.of(-0.78F, -0.375F),
              MultiNoiseUtil.ParameterRange.of(0F, 0F),
              MultiNoiseUtil.ParameterRange.of(0.56666666F, 0.7666667F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.15F, 0.2F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.375F, -0.2225F),
              MultiNoiseUtil.ParameterRange.of(0F, 0F),
              MultiNoiseUtil.ParameterRange.of(0.56666666F, 1F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.15F, 0.2F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.2225F, 0.05F),
              MultiNoiseUtil.ParameterRange.of(0F, 0F),
              MultiNoiseUtil.ParameterRange.of(0.56666666F, 1F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, 0.2F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.11F, 0.03F),
              MultiNoiseUtil.ParameterRange.of(-1F, -0.375F),
              MultiNoiseUtil.ParameterRange.of(0F, 0F),
              MultiNoiseUtil.ParameterRange.of(0.7666667F, 1F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, 0.2F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.78F, 0.05F),
              MultiNoiseUtil.ParameterRange.of(0F, 0F),
              MultiNoiseUtil.ParameterRange.of(0.7666667F, 1F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, 0.2F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
              MultiNoiseUtil.ParameterRange.of(-1F, 0.05F),
              MultiNoiseUtil.ParameterRange.of(0F, 0F),
              MultiNoiseUtil.ParameterRange.of(0.93333334F, 1F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, -0.15F),
              MultiNoiseUtil.ParameterRange.of(-0.1F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.78F, -0.2225F),
              MultiNoiseUtil.ParameterRange.of(0F, 0F),
              MultiNoiseUtil.ParameterRange.of(0.93333334F, 1F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, 0.2F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.11F, 0.03F),
              MultiNoiseUtil.ParameterRange.of(-1F, -0.375F),
              MultiNoiseUtil.ParameterRange.of(1F, 1F),
              MultiNoiseUtil.ParameterRange.of(-1F, -0.7666667F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, 0.2F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
              MultiNoiseUtil.ParameterRange.of(-1F, 0.05F),
              MultiNoiseUtil.ParameterRange.of(1F, 1F),
              MultiNoiseUtil.ParameterRange.of(-1F, -0.93333334F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, -0.15F),
              MultiNoiseUtil.ParameterRange.of(0.1F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.78F, -0.2225F),
              MultiNoiseUtil.ParameterRange.of(1F, 1F),
              MultiNoiseUtil.ParameterRange.of(-1F, -0.93333334F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, 0.2F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.78F, 0.05F),
              MultiNoiseUtil.ParameterRange.of(1F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.93333334F, -0.7666667F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, -0.15F),
              MultiNoiseUtil.ParameterRange.of(0.1F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.375F, -0.2225F),
              MultiNoiseUtil.ParameterRange.of(1F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.93333334F, -0.4F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, -0.15F),
              MultiNoiseUtil.ParameterRange.of(0.1F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.2225F, 0.05F),
              MultiNoiseUtil.ParameterRange.of(1F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.93333334F, -0.4F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, 0.2F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.19F, 0.03F),
              MultiNoiseUtil.ParameterRange.of(-0.78F, -0.375F),
              MultiNoiseUtil.ParameterRange.of(1F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.7666667F, -0.56666666F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.15F, 0.2F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.375F, -0.2225F),
              MultiNoiseUtil.ParameterRange.of(1F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.7666667F, -0.05F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.15F, 0.2F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.2225F, 0.05F),
              MultiNoiseUtil.ParameterRange.of(1F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.7666667F, -0.05F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, 0.2F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.11F, 0.03F),
              MultiNoiseUtil.ParameterRange.of(-1F, -0.375F),
              MultiNoiseUtil.ParameterRange.of(1F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.56666666F, -0.26666668F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, 0.2F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.78F, 0.05F),
              MultiNoiseUtil.ParameterRange.of(1F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.56666666F, -0.05F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, 0.2F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
              MultiNoiseUtil.ParameterRange.of(-1F, 0.05F),
              MultiNoiseUtil.ParameterRange.of(1F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.4F, -0.05F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, -0.15F),
              MultiNoiseUtil.ParameterRange.of(0.1F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.78F, -0.2225F),
              MultiNoiseUtil.ParameterRange.of(1F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.4F, -0.26666668F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, 0.2F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
              MultiNoiseUtil.ParameterRange.of(-1F, -0.375F),
              MultiNoiseUtil.ParameterRange.of(1F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.05F, 0.4F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, 0.2F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.375F, 0.05F),
              MultiNoiseUtil.ParameterRange.of(1F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.05F, 0.56666666F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, 0.2F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.11F, 0.03F),
              MultiNoiseUtil.ParameterRange.of(-1F, -0.375F),
              MultiNoiseUtil.ParameterRange.of(1F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.26666668F, 0.56666666F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, -0.15F),
              MultiNoiseUtil.ParameterRange.of(-0.1F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.78F, -0.2225F),
              MultiNoiseUtil.ParameterRange.of(1F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.26666668F, 0.4F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, 0.2F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.78F, 0.05F),
              MultiNoiseUtil.ParameterRange.of(1F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.4F, 0.56666666F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, -0.15F),
              MultiNoiseUtil.ParameterRange.of(-0.1F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.375F, -0.2225F),
              MultiNoiseUtil.ParameterRange.of(1F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.4F, 0.93333334F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, -0.15F),
              MultiNoiseUtil.ParameterRange.of(-0.1F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.2225F, 0.05F),
              MultiNoiseUtil.ParameterRange.of(1F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.4F, 0.93333334F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, 0.2F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.19F, 0.03F),
              MultiNoiseUtil.ParameterRange.of(-0.78F, -0.375F),
              MultiNoiseUtil.ParameterRange.of(1F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.56666666F, 0.7666667F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.15F, 0.2F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.375F, -0.2225F),
              MultiNoiseUtil.ParameterRange.of(1F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.56666666F, 1F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.15F, 0.2F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.2225F, 0.05F),
              MultiNoiseUtil.ParameterRange.of(1F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.56666666F, 1F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, 0.2F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.11F, 0.03F),
              MultiNoiseUtil.ParameterRange.of(-1F, -0.375F),
              MultiNoiseUtil.ParameterRange.of(1F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.7666667F, 1F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, 0.2F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.78F, 0.05F),
              MultiNoiseUtil.ParameterRange.of(1F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.7666667F, 1F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, 0.2F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
              MultiNoiseUtil.ParameterRange.of(-1F, 0.05F),
              MultiNoiseUtil.ParameterRange.of(1F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.93333334F, 1F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(-0.45F, -0.15F),
              MultiNoiseUtil.ParameterRange.of(-0.1F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
              MultiNoiseUtil.ParameterRange.of(-0.78F, -0.2225F),
              MultiNoiseUtil.ParameterRange.of(1F, 1F),
              MultiNoiseUtil.ParameterRange.of(0.93333334F, 1F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
   }

}
