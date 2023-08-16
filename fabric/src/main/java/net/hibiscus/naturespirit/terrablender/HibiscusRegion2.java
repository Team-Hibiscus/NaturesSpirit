package net.hibiscus.naturespirit.terrablender;

import com.mojang.datafixers.util.Pair;
import net.hibiscus.naturespirit.datagen.HibiscusBiomes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class HibiscusRegion2 extends Region {
   public HibiscusRegion2(ResourceLocation name, int weight) {
      super(name, RegionType.OVERWORLD, weight);
   }

   @Override
   public void addBiomes(Registry <Biome> registry, Consumer <Pair <Climate.ParameterPoint, ResourceKey <Biome>>> mapper) {
      this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
         builder.replaceBiome(Biomes.SUNFLOWER_PLAINS, HibiscusBiomes.LAVENDER_FIELDS);
         builder.replaceBiome(Biomes.FLOWER_FOREST, HibiscusBiomes.LAVENDER_FIELDS);
         builder.replaceBiome(Biomes.RIVER, HibiscusBiomes.ERODED_RIVER);
      });


      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, 0.2F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(-0.11F, 0.03F),
              Climate.Parameter.span(-1F, -0.375F),
              Climate.Parameter.span(0F, 0F),
              Climate.Parameter.span(-1F, -0.7666667F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, 0.2F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(0.03F, 1F),
              Climate.Parameter.span(-1F, 0.05F),
              Climate.Parameter.span(0F, 0F),
              Climate.Parameter.span(-1F, -0.93333334F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, -0.15F),
              Climate.Parameter.span(0.1F, 1F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(-0.78F, -0.2225F),
              Climate.Parameter.span(0F, 0F),
              Climate.Parameter.span(-1F, -0.93333334F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, 0.2F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(0.03F, 1F),
              Climate.Parameter.span(-0.78F, 0.05F),
              Climate.Parameter.span(0F, 0F),
              Climate.Parameter.span(-0.93333334F, -0.7666667F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, -0.15F),
              Climate.Parameter.span(0.1F, 1F),
              Climate.Parameter.span(0.03F, 1F),
              Climate.Parameter.span(-0.375F, -0.2225F),
              Climate.Parameter.span(0F, 0F),
              Climate.Parameter.span(-0.93333334F, -0.4F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, -0.15F),
              Climate.Parameter.span(0.1F, 1F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(-0.2225F, 0.05F),
              Climate.Parameter.span(0F, 0F),
              Climate.Parameter.span(-0.93333334F, -0.4F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, 0.2F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(-0.19F, 0.03F),
              Climate.Parameter.span(-0.78F, -0.375F),
              Climate.Parameter.span(0F, 0F),
              Climate.Parameter.span(-0.7666667F, -0.56666666F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.15F, 0.2F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(0.03F, 1F),
              Climate.Parameter.span(-0.375F, -0.2225F),
              Climate.Parameter.span(0F, 0F),
              Climate.Parameter.span(-0.7666667F, -0.05F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.15F, 0.2F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(-0.2225F, 0.05F),
              Climate.Parameter.span(0F, 0F),
              Climate.Parameter.span(-0.7666667F, -0.05F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, 0.2F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(-0.11F, 0.03F),
              Climate.Parameter.span(-1F, -0.375F),
              Climate.Parameter.span(0F, 0F),
              Climate.Parameter.span(-0.56666666F, -0.26666668F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, 0.2F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(0.03F, 1F),
              Climate.Parameter.span(-0.78F, 0.05F),
              Climate.Parameter.span(0F, 0F),
              Climate.Parameter.span(-0.56666666F, -0.05F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, 0.2F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(0.03F, 1F),
              Climate.Parameter.span(-1F, 0.05F),
              Climate.Parameter.span(0F, 0F),
              Climate.Parameter.span(-0.4F, -0.05F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, -0.15F),
              Climate.Parameter.span(0.1F, 1F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(-0.78F, -0.2225F),
              Climate.Parameter.span(0F, 0F),
              Climate.Parameter.span(-0.4F, -0.26666668F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, 0.2F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(0.03F, 1F),
              Climate.Parameter.span(-1F, -0.375F),
              Climate.Parameter.span(0F, 0F),
              Climate.Parameter.span(-0.05F, 0.4F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, 0.2F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(0.03F, 1F),
              Climate.Parameter.span(-0.375F, 0.05F),
              Climate.Parameter.span(0F, 0F),
              Climate.Parameter.span(0.05F, 0.56666666F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, 0.2F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(-0.11F, 0.03F),
              Climate.Parameter.span(-1F, -0.375F),
              Climate.Parameter.span(0F, 0F),
              Climate.Parameter.span(0.26666668F, 0.56666666F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, -0.15F),
              Climate.Parameter.span(-0.1F, 1F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(-0.78F, -0.2225F),
              Climate.Parameter.span(0F, 0F),
              Climate.Parameter.span(0.26666668F, 0.4F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, 0.2F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(0.03F, 1F),
              Climate.Parameter.span(-0.78F, 0.05F),
              Climate.Parameter.span(0F, 0F),
              Climate.Parameter.span(0.4F, 0.56666666F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, -0.15F),
              Climate.Parameter.span(-0.1F, 1F),
              Climate.Parameter.span(0.03F, 1F),
              Climate.Parameter.span(-0.375F, -0.2225F),
              Climate.Parameter.span(0F, 0F),
              Climate.Parameter.span(0.4F, 0.93333334F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, -0.15F),
              Climate.Parameter.span(-0.1F, 1F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(-0.2225F, 0.05F),
              Climate.Parameter.span(0F, 0F),
              Climate.Parameter.span(0.4F, 0.93333334F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, 0.2F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(-0.19F, 0.03F),
              Climate.Parameter.span(-0.78F, -0.375F),
              Climate.Parameter.span(0F, 0F),
              Climate.Parameter.span(0.56666666F, 0.7666667F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.15F, 0.2F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(0.03F, 1F),
              Climate.Parameter.span(-0.375F, -0.2225F),
              Climate.Parameter.span(0F, 0F),
              Climate.Parameter.span(0.56666666F, 1F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.15F, 0.2F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(-0.2225F, 0.05F),
              Climate.Parameter.span(0F, 0F),
              Climate.Parameter.span(0.56666666F, 1F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, 0.2F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(-0.11F, 0.03F),
              Climate.Parameter.span(-1F, -0.375F),
              Climate.Parameter.span(0F, 0F),
              Climate.Parameter.span(0.7666667F, 1F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, 0.2F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(0.03F, 1F),
              Climate.Parameter.span(-0.78F, 0.05F),
              Climate.Parameter.span(0F, 0F),
              Climate.Parameter.span(0.7666667F, 1F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, 0.2F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(0.03F, 1F),
              Climate.Parameter.span(-1F, 0.05F),
              Climate.Parameter.span(0F, 0F),
              Climate.Parameter.span(0.93333334F, 1F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, -0.15F),
              Climate.Parameter.span(-0.1F, 1F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(-0.78F, -0.2225F),
              Climate.Parameter.span(0F, 0F),
              Climate.Parameter.span(0.93333334F, 1F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, 0.2F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(-0.11F, 0.03F),
              Climate.Parameter.span(-1F, -0.375F),
              Climate.Parameter.span(1F, 1F),
              Climate.Parameter.span(-1F, -0.7666667F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, 0.2F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(0.03F, 1F),
              Climate.Parameter.span(-1F, 0.05F),
              Climate.Parameter.span(1F, 1F),
              Climate.Parameter.span(-1F, -0.93333334F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, -0.15F),
              Climate.Parameter.span(0.1F, 1F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(-0.78F, -0.2225F),
              Climate.Parameter.span(1F, 1F),
              Climate.Parameter.span(-1F, -0.93333334F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, 0.2F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(0.03F, 1F),
              Climate.Parameter.span(-0.78F, 0.05F),
              Climate.Parameter.span(1F, 1F),
              Climate.Parameter.span(-0.93333334F, -0.7666667F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, -0.15F),
              Climate.Parameter.span(0.1F, 1F),
              Climate.Parameter.span(0.03F, 1F),
              Climate.Parameter.span(-0.375F, -0.2225F),
              Climate.Parameter.span(1F, 1F),
              Climate.Parameter.span(-0.93333334F, -0.4F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, -0.15F),
              Climate.Parameter.span(0.1F, 1F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(-0.2225F, 0.05F),
              Climate.Parameter.span(1F, 1F),
              Climate.Parameter.span(-0.93333334F, -0.4F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, 0.2F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(-0.19F, 0.03F),
              Climate.Parameter.span(-0.78F, -0.375F),
              Climate.Parameter.span(1F, 1F),
              Climate.Parameter.span(-0.7666667F, -0.56666666F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.15F, 0.2F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(0.03F, 1F),
              Climate.Parameter.span(-0.375F, -0.2225F),
              Climate.Parameter.span(1F, 1F),
              Climate.Parameter.span(-0.7666667F, -0.05F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.15F, 0.2F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(-0.2225F, 0.05F),
              Climate.Parameter.span(1F, 1F),
              Climate.Parameter.span(-0.7666667F, -0.05F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, 0.2F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(-0.11F, 0.03F),
              Climate.Parameter.span(-1F, -0.375F),
              Climate.Parameter.span(1F, 1F),
              Climate.Parameter.span(-0.56666666F, -0.26666668F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, 0.2F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(0.03F, 1F),
              Climate.Parameter.span(-0.78F, 0.05F),
              Climate.Parameter.span(1F, 1F),
              Climate.Parameter.span(-0.56666666F, -0.05F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, 0.2F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(0.03F, 1F),
              Climate.Parameter.span(-1F, 0.05F),
              Climate.Parameter.span(1F, 1F),
              Climate.Parameter.span(-0.4F, -0.05F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, -0.15F),
              Climate.Parameter.span(0.1F, 1F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(-0.78F, -0.2225F),
              Climate.Parameter.span(1F, 1F),
              Climate.Parameter.span(-0.4F, -0.26666668F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, 0.2F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(0.03F, 1F),
              Climate.Parameter.span(-1F, -0.375F),
              Climate.Parameter.span(1F, 1F),
              Climate.Parameter.span(-0.05F, 0.4F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, 0.2F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(0.03F, 1F),
              Climate.Parameter.span(-0.375F, 0.05F),
              Climate.Parameter.span(1F, 1F),
              Climate.Parameter.span(0.05F, 0.56666666F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, 0.2F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(-0.11F, 0.03F),
              Climate.Parameter.span(-1F, -0.375F),
              Climate.Parameter.span(1F, 1F),
              Climate.Parameter.span(0.26666668F, 0.56666666F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, -0.15F),
              Climate.Parameter.span(-0.1F, 1F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(-0.78F, -0.2225F),
              Climate.Parameter.span(1F, 1F),
              Climate.Parameter.span(0.26666668F, 0.4F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, 0.2F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(0.03F, 1F),
              Climate.Parameter.span(-0.78F, 0.05F),
              Climate.Parameter.span(1F, 1F),
              Climate.Parameter.span(0.4F, 0.56666666F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, -0.15F),
              Climate.Parameter.span(-0.1F, 1F),
              Climate.Parameter.span(0.03F, 1F),
              Climate.Parameter.span(-0.375F, -0.2225F),
              Climate.Parameter.span(1F, 1F),
              Climate.Parameter.span(0.4F, 0.93333334F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, -0.15F),
              Climate.Parameter.span(-0.1F, 1F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(-0.2225F, 0.05F),
              Climate.Parameter.span(1F, 1F),
              Climate.Parameter.span(0.4F, 0.93333334F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, 0.2F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(-0.19F, 0.03F),
              Climate.Parameter.span(-0.78F, -0.375F),
              Climate.Parameter.span(1F, 1F),
              Climate.Parameter.span(0.56666666F, 0.7666667F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.15F, 0.2F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(0.03F, 1F),
              Climate.Parameter.span(-0.375F, -0.2225F),
              Climate.Parameter.span(1F, 1F),
              Climate.Parameter.span(0.56666666F, 1F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.15F, 0.2F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(-0.2225F, 0.05F),
              Climate.Parameter.span(1F, 1F),
              Climate.Parameter.span(0.56666666F, 1F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, 0.2F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(-0.11F, 0.03F),
              Climate.Parameter.span(-1F, -0.375F),
              Climate.Parameter.span(1F, 1F),
              Climate.Parameter.span(0.7666667F, 1F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, 0.2F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(0.03F, 1F),
              Climate.Parameter.span(-0.78F, 0.05F),
              Climate.Parameter.span(1F, 1F),
              Climate.Parameter.span(0.7666667F, 1F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, 0.2F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(0.03F, 1F),
              Climate.Parameter.span(-1F, 0.05F),
              Climate.Parameter.span(1F, 1F),
              Climate.Parameter.span(0.93333334F, 1F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
      this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.45F, -0.15F),
              Climate.Parameter.span(-0.1F, 1F),
              Climate.Parameter.span(0.3F, 1F),
              Climate.Parameter.span(-0.78F, -0.2225F),
              Climate.Parameter.span(1F, 1F),
              Climate.Parameter.span(0.93333334F, 1F),
              0
      ), HibiscusBiomes.WISTERIA_FOREST);
   }

}
