package net.hibiscus.naturespirit.terrablender;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class HibiscusRegion4 extends Region {
   public HibiscusRegion4(ResourceLocation name, int weight) {
      super(name, RegionType.OVERWORLD, weight);
   }

   @Override
   public void addBiomes(Registry <Biome> registry, Consumer <Pair <Climate.ParameterPoint, ResourceKey <Biome>>> mapper) {

//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.19F, 0.3F),
//              MultiNoiseUtil.ParameterRange.of(-0.2225F, 0.05F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(-1F, -0.26666668F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.11F, 0.03F),
//              MultiNoiseUtil.ParameterRange.of(-1F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(-1F, -0.7666667F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0.1F, 0.3F),
//              MultiNoiseUtil.ParameterRange.of(-0.11F, 0.03F),
//              MultiNoiseUtil.ParameterRange.of(-1F, -0.78F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(-1F, -0.7666667F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.11F, 0.03F),
//              MultiNoiseUtil.ParameterRange.of(-0.78F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(-1F, -0.05F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-1F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(-1F, -0.93333334F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0.1F, 0.3F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-1F, -0.78F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(-1F, -0.93333334F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 0.3F),
//              MultiNoiseUtil.ParameterRange.of(-0.78F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(-1F, -0.93333334F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0.1F, 0.3F),
//              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.78F, -0.2225F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(-1F, -0.7666667F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.2225F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(-1F, -0.93333334F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.19F, -0.11F),
//              MultiNoiseUtil.ParameterRange.of(-1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(-0.93333334F, -0.7666667F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.11F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.55F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(-0.93333334F, -0.4F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.78F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(-0.93333334F, -0.7666667F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0.1F, 0.3F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.78F, -0.2225F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(-0.93333334F, -0.7666667F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.05F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(-0.93333334F, -0.4F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0.1F, 0.3F),
//              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.2225F, 0.05F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(-0.93333334F, -0.4F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.19F, 0.03F),
//              MultiNoiseUtil.ParameterRange.of(-0.78F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(-0.7666667F, -0.4F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.375F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(-0.7666667F, -0.4F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0.1F, 0.3F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.375F, -0.2225F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(-0.7666667F, -0.4F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.19F, -0.11F),
//              MultiNoiseUtil.ParameterRange.of(-1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(-0.56666666F, -0.4F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.11F, 0.03F),
//              MultiNoiseUtil.ParameterRange.of(-1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(-0.56666666F, -0.4F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0.1F, 0.3F),
//              MultiNoiseUtil.ParameterRange.of(-0.11F, 0.03F),
//              MultiNoiseUtil.ParameterRange.of(-1F, -0.78F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(-0.56666666F, -0.26666668F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.78F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(-0.56666666F, -0.4F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0.1F, 0.3F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.78F, -0.2225F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(-0.56666666F, -0.4F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.11F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-1F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(-0.4F, -0.05F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0.1F, 0.3F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-1F, -0.78F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(-0.4F, -0.26666668F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 0.3F),
//              MultiNoiseUtil.ParameterRange.of(-0.78F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(-0.4F, -0.05F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0.1F, 0.3F),
//              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.78F, -0.2225F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(-0.4F, -0.26666668F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.2225F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(-0.4F, -0.05F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.11F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-1F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(-0.26666668F, -0.05F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, -0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-1F, -0.375F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(-0.05F, 0.26666668F),
//              0
//      ), HibiscusBiomes.CARNATION_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-1F, -0.375F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(-0.05F, 0.26666668F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, -0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.11F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-1F, 0.45F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(0.05F, 0.26666668F),
//              0
//      ), HibiscusBiomes.CARNATION_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.11F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-1F, 0.45F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(0.05F, 0.26666668F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, -0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.45F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(0.05F, 1F),
//              0
//      ), HibiscusBiomes.CARNATION_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.45F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(0.05F, 1F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, -0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.19F, 0.3F),
//              MultiNoiseUtil.ParameterRange.of(-0.2225F, 0.45F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(0.26666668F, 1F),
//              0
//      ), HibiscusBiomes.CARNATION_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.19F, 0.3F),
//              MultiNoiseUtil.ParameterRange.of(-0.2225F, 0.45F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(0.26666668F, 1F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, -0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.19F, -0.11F),
//              MultiNoiseUtil.ParameterRange.of(0.55F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(0.26666668F, 1F),
//              0
//      ), HibiscusBiomes.CARNATION_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.19F, -0.11F),
//              MultiNoiseUtil.ParameterRange.of(0.55F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(0.26666668F, 1F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, -0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.11F, 0.03F),
//              MultiNoiseUtil.ParameterRange.of(-0.78F, 0.45F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(0.26666668F, 1F),
//              0
//      ), HibiscusBiomes.CARNATION_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.11F, 0.03F),
//              MultiNoiseUtil.ParameterRange.of(-0.78F, 0.45F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(0.26666668F, 1F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, -0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 0.3F),
//              MultiNoiseUtil.ParameterRange.of(-0.78F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(0.26666668F, 0.4F),
//              0
//      ), HibiscusBiomes.CARNATION_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 0.3F),
//              MultiNoiseUtil.ParameterRange.of(-0.78F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(0.26666668F, 0.4F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, -0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.2225F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(0.26666668F, 0.4F),
//              0
//      ), HibiscusBiomes.CARNATION_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.2225F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(0.26666668F, 0.4F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, -0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.19F, -0.11F),
//              MultiNoiseUtil.ParameterRange.of(-1F, 0.45F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(0.4F, 0.56666666F),
//              0
//      ), HibiscusBiomes.CARNATION_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.19F, -0.11F),
//              MultiNoiseUtil.ParameterRange.of(-1F, 0.45F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(0.4F, 0.56666666F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, -0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.11F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.55F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(0.4F, 0.93333334F),
//              0
//      ), HibiscusBiomes.CARNATION_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.11F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.55F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(0.4F, 0.93333334F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, -0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.05F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(0.4F, 0.93333334F),
//              0
//      ), HibiscusBiomes.CARNATION_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.05F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(0.4F, 0.93333334F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, -0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.19F, 0.03F),
//              MultiNoiseUtil.ParameterRange.of(-0.78F, 0.45F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(0.56666666F, 0.93333334F),
//              0
//      ), HibiscusBiomes.CARNATION_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.19F, 0.03F),
//              MultiNoiseUtil.ParameterRange.of(-0.78F, 0.45F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(0.56666666F, 0.93333334F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, -0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.19F, -0.11F),
//              MultiNoiseUtil.ParameterRange.of(-1F, 0.45F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(0.7666667F, 0.93333334F),
//              0
//      ), HibiscusBiomes.CARNATION_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.19F, -0.11F),
//              MultiNoiseUtil.ParameterRange.of(-1F, 0.45F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(0.7666667F, 0.93333334F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, -0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 0.3F),
//              MultiNoiseUtil.ParameterRange.of(-0.78F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(0.93333334F, 1F),
//              0
//      ), HibiscusBiomes.CARNATION_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 0.3F),
//              MultiNoiseUtil.ParameterRange.of(-0.78F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(0.93333334F, 1F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, -0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.2225F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(0.93333334F, 1F),
//              0
//      ), HibiscusBiomes.CARNATION_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.2225F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0, 0),
//              MultiNoiseUtil.ParameterRange.of(0.93333334F, 1F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.19F, 0.3F),
//              MultiNoiseUtil.ParameterRange.of(-0.2225F, 0.05F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-1F, -0.26666668F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.11F, 0.03F),
//              MultiNoiseUtil.ParameterRange.of(-1F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-1F, -0.7666667F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0.1F, 0.3F),
//              MultiNoiseUtil.ParameterRange.of(-0.11F, 0.03F),
//              MultiNoiseUtil.ParameterRange.of(-1F, -0.78F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-1F, -0.7666667F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.11F, 0.03F),
//              MultiNoiseUtil.ParameterRange.of(-0.78F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-1F, -0.05F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-1F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-1F, -0.93333334F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0.1F, 0.3F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-1F, -0.78F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-1F, -0.93333334F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 0.3F),
//              MultiNoiseUtil.ParameterRange.of(-0.78F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-1F, -0.93333334F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0.1F, 0.3F),
//              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.78F, -0.2225F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-1F, -0.7666667F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.2225F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-1F, -0.93333334F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.19F, -0.11F),
//              MultiNoiseUtil.ParameterRange.of(-1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.93333334F, -0.7666667F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.11F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.55F, 1F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.93333334F, -0.4F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.78F, 1F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.93333334F, -0.7666667F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0.1F, 0.3F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.78F, -0.2225F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.93333334F, -0.7666667F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.05F, 1F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.93333334F, -0.4F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0.1F, 0.3F),
//              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.2225F, 0.05F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.93333334F, -0.4F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.19F, 0.03F),
//              MultiNoiseUtil.ParameterRange.of(-0.78F, 1F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.7666667F, -0.4F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.375F, 1F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.7666667F, -0.4F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0.1F, 0.3F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.375F, -0.2225F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.7666667F, -0.4F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.19F, -0.11F),
//              MultiNoiseUtil.ParameterRange.of(-1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.56666666F, -0.4F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.11F, 0.03F),
//              MultiNoiseUtil.ParameterRange.of(-1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.56666666F, -0.4F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0.1F, 0.3F),
//              MultiNoiseUtil.ParameterRange.of(-0.11F, 0.03F),
//              MultiNoiseUtil.ParameterRange.of(-1F, -0.78F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.56666666F, -0.26666668F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.78F, 1F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.56666666F, -0.4F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0.1F, 0.3F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.78F, -0.2225F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.56666666F, -0.4F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.11F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-1F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.4F, -0.05F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0.1F, 0.3F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-1F, -0.78F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.4F, -0.26666668F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 0.3F),
//              MultiNoiseUtil.ParameterRange.of(-0.78F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.4F, -0.05F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(0.1F, 0.3F),
//              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.78F, -0.2225F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.4F, -0.26666668F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.2225F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.4F, -0.05F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.11F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-1F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.26666668F, -0.05F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, -0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-1F, -0.375F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.05F, 0.26666668F),
//              0
//      ), HibiscusBiomes.CARNATION_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-1F, -0.375F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.05F, 0.26666668F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, -0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.11F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-1F, 0.45F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.05F, 0.26666668F),
//              0
//      ), HibiscusBiomes.CARNATION_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.11F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-1F, 0.45F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.05F, 0.26666668F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, -0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.45F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.05F, 1F),
//              0
//      ), HibiscusBiomes.CARNATION_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.45F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.05F, 1F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, -0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.19F, 0.3F),
//              MultiNoiseUtil.ParameterRange.of(-0.2225F, 0.45F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.26666668F, 1F),
//              0
//      ), HibiscusBiomes.CARNATION_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.19F, 0.3F),
//              MultiNoiseUtil.ParameterRange.of(-0.2225F, 0.45F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.26666668F, 1F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, -0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.19F, -0.11F),
//              MultiNoiseUtil.ParameterRange.of(0.55F, 1F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.26666668F, 1F),
//              0
//      ), HibiscusBiomes.CARNATION_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.19F, -0.11F),
//              MultiNoiseUtil.ParameterRange.of(0.55F, 1F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.26666668F, 1F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, -0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.11F, 0.03F),
//              MultiNoiseUtil.ParameterRange.of(-0.78F, 0.45F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.26666668F, 1F),
//              0
//      ), HibiscusBiomes.CARNATION_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.11F, 0.03F),
//              MultiNoiseUtil.ParameterRange.of(-0.78F, 0.45F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.26666668F, 1F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, -0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 0.3F),
//              MultiNoiseUtil.ParameterRange.of(-0.78F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.26666668F, 0.4F),
//              0
//      ), HibiscusBiomes.CARNATION_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 0.3F),
//              MultiNoiseUtil.ParameterRange.of(-0.78F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.26666668F, 0.4F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, -0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.2225F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.26666668F, 0.4F),
//              0
//      ), HibiscusBiomes.CARNATION_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.2225F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.26666668F, 0.4F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, -0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.19F, -0.11F),
//              MultiNoiseUtil.ParameterRange.of(-1F, 0.45F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.4F, 0.56666666F),
//              0
//      ), HibiscusBiomes.CARNATION_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.19F, -0.11F),
//              MultiNoiseUtil.ParameterRange.of(-1F, 0.45F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.4F, 0.56666666F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, -0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.11F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.55F, 1F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.4F, 0.93333334F),
//              0
//      ), HibiscusBiomes.CARNATION_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.11F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.55F, 1F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.4F, 0.93333334F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, -0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.05F, 1F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.4F, 0.93333334F),
//              0
//      ), HibiscusBiomes.CARNATION_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.05F, 1F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.4F, 0.93333334F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, -0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.19F, 0.03F),
//              MultiNoiseUtil.ParameterRange.of(-0.78F, 0.45F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.56666666F, 0.93333334F),
//              0
//      ), HibiscusBiomes.CARNATION_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.19F, 0.03F),
//              MultiNoiseUtil.ParameterRange.of(-0.78F, 0.45F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.56666666F, 0.93333334F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, -0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.19F, -0.11F),
//              MultiNoiseUtil.ParameterRange.of(-1F, 0.45F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.7666667F, 0.93333334F),
//              0
//      ), HibiscusBiomes.CARNATION_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(-0.19F, -0.11F),
//              MultiNoiseUtil.ParameterRange.of(-1F, 0.45F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.7666667F, 0.93333334F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, -0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 0.3F),
//              MultiNoiseUtil.ParameterRange.of(-0.78F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.93333334F, 1F),
//              0
//      ), HibiscusBiomes.CARNATION_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.03F, 0.3F),
//              MultiNoiseUtil.ParameterRange.of(-0.78F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.93333334F, 1F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.35F, -0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.2225F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.93333334F, 1F),
//              0
//      ), HibiscusBiomes.CARNATION_FIELDS);
//      this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F),
//              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
//              MultiNoiseUtil.ParameterRange.of(-0.2225F, 0.55F),
//              MultiNoiseUtil.ParameterRange.of(1F, 1F),
//              MultiNoiseUtil.ParameterRange.of(0.93333334F, 1F),
//              0
//      ), HibiscusBiomes.CYPRESS_FIELDS);
   }

}
