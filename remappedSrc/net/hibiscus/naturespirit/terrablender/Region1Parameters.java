package net.hibiscus.naturespirit.terrablender;

import com.mojang.datafixers.util.Pair;
import net.hibiscus.naturespirit.datagen.HibiscusBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import java.util.function.Consumer;


public class Region1Parameters {
   private final Climate.Parameter defaultParameter = Climate.Parameter.span(-1.0F, 1.0F);
   private final Climate.Parameter[] temperatureParameters = new Climate.Parameter[]{
           Climate.Parameter.span(-1.0F, -0.45F),
           Climate.Parameter.span(-0.45F, -0.15F),
           Climate.Parameter.span(-0.15F, 0.2F),
           Climate.Parameter.span(0.2F, 0.55F),
           Climate.Parameter.span(0.55F, 1.0F)
   };
   private final Climate.Parameter[] humidityParameters = new Climate.Parameter[]{
           Climate.Parameter.span(-1.0F, -0.35F),
           Climate.Parameter.span(-0.35F, -0.1F),
           Climate.Parameter.span(-0.1F, 0.1F),
           Climate.Parameter.span(0.1F, 0.3F),
           Climate.Parameter.span(0.3F, 1.0F)
   };
   private final Climate.Parameter[] erosionParameters = new Climate.Parameter[]{
           Climate.Parameter.span(-1.0F, -0.78F),
           Climate.Parameter.span(-0.78F, -0.375F),
           Climate.Parameter.span(-0.375F, -0.2225F),
           Climate.Parameter.span(-0.2225F, 0.05F),
           Climate.Parameter.span(0.05F, 0.45F),
           Climate.Parameter.span(0.45F, 0.55F),
           Climate.Parameter.span(0.55F, 1.0F)
   };
   private final Climate.Parameter frozenTemperature;
   private final Climate.Parameter nonFrozenTemperatureParameters;
   private final Climate.Parameter mushroomFieldsContinentalness;
   private final Climate.Parameter deepOceanContinentalness;
   private final Climate.Parameter oceanContinentalness;
   private final Climate.Parameter coastContinentalness;
   private final Climate.Parameter riverContinentalness;
   private final Climate.Parameter nearInlandContinentalness;
   private final Climate.Parameter midInlandContinentalness;
   private final Climate.Parameter farInlandContinentalness;
   private final ResourceKey <Biome>[][] oceanBiomes;
   private final ResourceKey <Biome>[][] commonBiomes;
   private final ResourceKey <Biome>[][] uncommonBiomes;
   private final ResourceKey <Biome>[][] nearMountainBiomes;
   private final ResourceKey <Biome>[][] specialNearMountainBiomes;
   private final ResourceKey <Biome>[][] windsweptBiomes;

   public Region1Parameters() {
      this.frozenTemperature = this.temperatureParameters[0];
      this.nonFrozenTemperatureParameters = Climate.Parameter.span(this.temperatureParameters[1],
              this.temperatureParameters[4]
      );
      this.mushroomFieldsContinentalness = Climate.Parameter.span(-1.2F, -1.05F);
      this.deepOceanContinentalness = Climate.Parameter.span(-1.05F, -0.455F);
      this.oceanContinentalness = Climate.Parameter.span(-0.455F, -0.19F);
      this.coastContinentalness = Climate.Parameter.span(-0.19F, -0.11F);
      this.riverContinentalness = Climate.Parameter.span(-0.11F, 0.55F);
      this.nearInlandContinentalness = Climate.Parameter.span(-0.11F, 0.03F);
      this.midInlandContinentalness = Climate.Parameter.span(0.03F, 0.3F);
      this.farInlandContinentalness = Climate.Parameter.span(0.3F, 1.0F);
      this.oceanBiomes = new ResourceKey[][]{
              {
                      Biomes.DEEP_FROZEN_OCEAN,
                      Biomes.DEEP_COLD_OCEAN,
                      Biomes.DEEP_OCEAN,
                      Biomes.DEEP_LUKEWARM_OCEAN,
                      Biomes.WARM_OCEAN
              },
              {
                      Biomes.FROZEN_OCEAN,
                      Biomes.COLD_OCEAN,
                      Biomes.OCEAN,
                      Biomes.LUKEWARM_OCEAN,
                      Biomes.WARM_OCEAN
              }
      };
      this.commonBiomes = new ResourceKey[][]{
              {
                      Biomes.SNOWY_PLAINS,
                      Biomes.SNOWY_PLAINS,
                      Biomes.SNOWY_PLAINS,
                      Biomes.SNOWY_TAIGA,
                      Biomes.TAIGA
              },
              {
                      Biomes.PLAINS,
                      Biomes.PLAINS,
                      Biomes.FOREST,
                      Biomes.TAIGA,
                      HibiscusBiomes.REDWOOD_FOREST
              },
              {
                      Biomes.FLOWER_FOREST,
                      Biomes.PLAINS,
                      Biomes.FOREST,
                      Biomes.BIRCH_FOREST,
                      Biomes.DARK_FOREST
              },
              {
                      Biomes.SAVANNA,
                      Biomes.SAVANNA,
                      Biomes.FOREST,
                      Biomes.JUNGLE,
                      Biomes.JUNGLE
              },
              {
                      Biomes.DESERT,
                      Biomes.DESERT,
                      Biomes.DESERT,
                      Biomes.DESERT,
                      Biomes.DESERT
              }
      };
      this.uncommonBiomes = new ResourceKey[][]{
              {Biomes.ICE_SPIKES, null, Biomes.SNOWY_TAIGA, null, null},
              {null, null, null, null, null},
              {
                      Biomes.SUNFLOWER_PLAINS,
                      null,
                      null,
                      Biomes.OLD_GROWTH_BIRCH_FOREST,
                      null
              },
              {
                      null,
                      null,
                      Biomes.PLAINS,
                      Biomes.SPARSE_JUNGLE,
                      Biomes.BAMBOO_JUNGLE
              },
              {null, null, null, null, null}
      };
      this.nearMountainBiomes = new ResourceKey[][]{
              {
                      Biomes.SNOWY_PLAINS,
                      Biomes.SNOWY_PLAINS,
                      Biomes.SNOWY_PLAINS,
                      Biomes.SNOWY_TAIGA,
                      Biomes.SNOWY_TAIGA
              },
              {
                      Biomes.MEADOW,
                      Biomes.MEADOW,
                      Biomes.FOREST,
                      Biomes.TAIGA,
                      HibiscusBiomes.REDWOOD_FOREST
              },
              {
                      Biomes.MEADOW,
                      Biomes.MEADOW,
                      Biomes.MEADOW,
                      Biomes.MEADOW,
                      Biomes.DARK_FOREST
              },
              {
                      Biomes.SAVANNA_PLATEAU,
                      Biomes.SAVANNA_PLATEAU,
                      Biomes.FOREST,
                      Biomes.FOREST,
                      Biomes.JUNGLE
              },
              {
                      Biomes.BADLANDS,
                      Biomes.BADLANDS,
                      Biomes.BADLANDS,
                      Biomes.WOODED_BADLANDS,
                      Biomes.WOODED_BADLANDS
              }
      };
      this.specialNearMountainBiomes = new ResourceKey[][]{
              {Biomes.ICE_SPIKES, null, null, null, null},
              {
                      Biomes.CHERRY_GROVE,
                      null,
                      Biomes.MEADOW,
                      Biomes.MEADOW,
                      null
              },
              {
                      Biomes.CHERRY_GROVE,
                      Biomes.CHERRY_GROVE,
                      Biomes.FOREST,
                      Biomes.BIRCH_FOREST,
                      null
              },
              {null, null, null, null, null},
              {
                      Biomes.ERODED_BADLANDS,
                      Biomes.ERODED_BADLANDS,
                      null,
                      null,
                      null
              }
      };
      this.windsweptBiomes = new ResourceKey[][]{
              {
                      Biomes.WINDSWEPT_GRAVELLY_HILLS,
                      Biomes.WINDSWEPT_GRAVELLY_HILLS,
                      Biomes.WINDSWEPT_HILLS,
                      Biomes.WINDSWEPT_FOREST,
                      Biomes.WINDSWEPT_FOREST
              },
              {
                      Biomes.WINDSWEPT_GRAVELLY_HILLS,
                      Biomes.WINDSWEPT_GRAVELLY_HILLS,
                      Biomes.WINDSWEPT_HILLS,
                      Biomes.WINDSWEPT_FOREST,
                      Biomes.WINDSWEPT_FOREST
              },
              {
                      Biomes.WINDSWEPT_HILLS,
                      Biomes.WINDSWEPT_HILLS,
                      Biomes.WINDSWEPT_HILLS,
                      Biomes.WINDSWEPT_FOREST,
                      Biomes.WINDSWEPT_FOREST
              },
              {null, null, null, null, null},
              {null, null, null, null, null}
      };
   }

   protected void writeOverworldBiomeParameters(Consumer <Pair <Climate.ParameterPoint, ResourceKey <Biome>>> parameters) {
      this.writeOceanBiomes(parameters);
      this.writeLandBiomes(parameters);
      this.writeCaveBiomes(parameters);
   }


   private void writeOceanBiomes(Consumer <Pair <Climate.ParameterPoint, ResourceKey <Biome>>> parameters) {
      this.writeBiomeParameters(parameters,
              this.defaultParameter,
              this.defaultParameter,
              this.mushroomFieldsContinentalness,
              this.defaultParameter,
              this.defaultParameter,
              0.0F,
              Biomes.MUSHROOM_FIELDS
      );

      for(int i = 0; i < this.temperatureParameters.length; ++i) {
         Climate.Parameter parameterRange = this.temperatureParameters[i];
         this.writeBiomeParameters(parameters,
                 parameterRange,
                 this.defaultParameter,
                 this.deepOceanContinentalness,
                 this.defaultParameter,
                 this.defaultParameter,
                 0.0F,
                 this.oceanBiomes[0][i]
         );
         this.writeBiomeParameters(parameters,
                 parameterRange,
                 this.defaultParameter,
                 this.oceanContinentalness,
                 this.defaultParameter,
                 this.defaultParameter,
                 0.0F,
                 this.oceanBiomes[1][i]
         );
      }

   }

   private void writeLandBiomes(Consumer <Pair <Climate.ParameterPoint, ResourceKey <Biome>>> parameters) {
      this.writeMidBiomes(parameters, Climate.Parameter.span(-1.0F, -0.93333334F));
      this.writeHighBiomes(parameters, Climate.Parameter.span(-0.93333334F, -0.7666667F));
      this.writePeakBiomes(parameters, Climate.Parameter.span(-0.7666667F, -0.56666666F));
      this.writeHighBiomes(parameters, Climate.Parameter.span(-0.56666666F, -0.4F));
      this.writeMidBiomes(parameters, Climate.Parameter.span(-0.4F, -0.26666668F));
      this.writeLowBiomes(parameters, Climate.Parameter.span(-0.26666668F, -0.05F));
      this.writeValleyBiomes(parameters, Climate.Parameter.span(-0.05F, 0.05F));
      this.writeLowBiomes(parameters, Climate.Parameter.span(0.05F, 0.26666668F));
      this.writeMidBiomes(parameters, Climate.Parameter.span(0.26666668F, 0.4F));
      this.writeHighBiomes(parameters, Climate.Parameter.span(0.4F, 0.56666666F));
      this.writePeakBiomes(parameters, Climate.Parameter.span(0.56666666F, 0.7666667F));
      this.writeHighBiomes(parameters, Climate.Parameter.span(0.7666667F, 0.93333334F));
      this.writeMidBiomes(parameters, Climate.Parameter.span(0.93333334F, 1.0F));
   }

   private void writePeakBiomes(Consumer <Pair <Climate.ParameterPoint, ResourceKey <Biome>>> parameters, Climate.Parameter weirdness) {
      for(int i = 0; i < this.temperatureParameters.length; ++i) {
         Climate.Parameter parameterRange = this.temperatureParameters[i];

         for(int j = 0; j < this.humidityParameters.length; ++j) {
            Climate.Parameter parameterRange2 = this.humidityParameters[j];
            ResourceKey <Biome> registryKey = this.getRegularBiome(i, j, weirdness);
            ResourceKey <Biome> registryKey2 = this.getBadlandsOrRegularBiome(i, j, weirdness);
            ResourceKey <Biome> registryKey3 = this.getMountainStartBiome(i, j, weirdness);
            ResourceKey <Biome> registryKey4 = this.getNearMountainBiome(i, j, weirdness);
            ResourceKey <Biome> registryKey5 = this.getWindsweptOrRegularBiome(i, j, weirdness);
            ResourceKey <Biome> registryKey6 = this.getBiomeOrWindsweptSavanna(i, j, weirdness, registryKey5);
            ResourceKey <Biome> registryKey7 = this.getPeakBiome(i, j, weirdness);
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness),
                    this.erosionParameters[0],
                    weirdness,
                    0.0F,
                    registryKey7
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness),
                    this.erosionParameters[1],
                    weirdness,
                    0.0F,
                    registryKey3
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness),
                    this.erosionParameters[1],
                    weirdness,
                    0.0F,
                    registryKey7
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness),
                    Climate.Parameter.span(this.erosionParameters[2], this.erosionParameters[3]),
                    weirdness,
                    0.0F,
                    registryKey
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness),
                    this.erosionParameters[2],
                    weirdness,
                    0.0F,
                    registryKey4
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    this.midInlandContinentalness,
                    this.erosionParameters[3],
                    weirdness,
                    0.0F,
                    registryKey2
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    this.farInlandContinentalness,
                    this.erosionParameters[3],
                    weirdness,
                    0.0F,
                    registryKey4
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness),
                    this.erosionParameters[4],
                    weirdness,
                    0.0F,
                    registryKey
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness),
                    this.erosionParameters[5],
                    weirdness,
                    0.0F,
                    registryKey6
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness),
                    this.erosionParameters[5],
                    weirdness,
                    0.0F,
                    registryKey5
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness),
                    this.erosionParameters[6],
                    weirdness,
                    0.0F,
                    registryKey
            );
         }
      }

   }

   private void writeHighBiomes(Consumer <Pair <Climate.ParameterPoint, ResourceKey <Biome>>> parameters, Climate.Parameter weirdness) {
      for(int i = 0; i < this.temperatureParameters.length; ++i) {
         Climate.Parameter parameterRange = this.temperatureParameters[i];

         for(int j = 0; j < this.humidityParameters.length; ++j) {
            Climate.Parameter parameterRange2 = this.humidityParameters[j];
            ResourceKey <Biome> registryKey = this.getRegularBiome(i, j, weirdness);
            ResourceKey <Biome> registryKey2 = this.getBadlandsOrRegularBiome(i, j, weirdness);
            ResourceKey <Biome> registryKey3 = this.getMountainStartBiome(i, j, weirdness);
            ResourceKey <Biome> registryKey4 = this.getNearMountainBiome(i, j, weirdness);
            ResourceKey <Biome> registryKey5 = this.getWindsweptOrRegularBiome(i, j, weirdness);
            ResourceKey <Biome> registryKey6 = this.getBiomeOrWindsweptSavanna(i, j, weirdness, registryKey);
            ResourceKey <Biome> registryKey7 = this.getMountainSlopeBiome(i, j, weirdness);
            ResourceKey <Biome> registryKey8 = this.getPeakBiome(i, j, weirdness);
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    this.coastContinentalness,
                    Climate.Parameter.span(this.erosionParameters[0], this.erosionParameters[1]),
                    weirdness,
                    0.0F,
                    registryKey
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    this.nearInlandContinentalness,
                    this.erosionParameters[0],
                    weirdness,
                    0.0F,
                    registryKey7
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness),
                    this.erosionParameters[0],
                    weirdness,
                    0.0F,
                    registryKey8
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    this.nearInlandContinentalness,
                    this.erosionParameters[1],
                    weirdness,
                    0.0F,
                    registryKey3
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness),
                    this.erosionParameters[1],
                    weirdness,
                    0.0F,
                    registryKey7
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness),
                    Climate.Parameter.span(this.erosionParameters[2], this.erosionParameters[3]),
                    weirdness,
                    0.0F,
                    registryKey
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness),
                    this.erosionParameters[2],
                    weirdness,
                    0.0F,
                    registryKey4
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    this.midInlandContinentalness,
                    this.erosionParameters[3],
                    weirdness,
                    0.0F,
                    registryKey2
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    this.farInlandContinentalness,
                    this.erosionParameters[3],
                    weirdness,
                    0.0F,
                    registryKey4
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness),
                    this.erosionParameters[4],
                    weirdness,
                    0.0F,
                    registryKey
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness),
                    this.erosionParameters[5],
                    weirdness,
                    0.0F,
                    registryKey6
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness),
                    this.erosionParameters[5],
                    weirdness,
                    0.0F,
                    registryKey5
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness),
                    this.erosionParameters[6],
                    weirdness,
                    0.0F,
                    registryKey
            );
         }
      }

   }

   private void writeMidBiomes(Consumer <Pair <Climate.ParameterPoint, ResourceKey <Biome>>> parameters, Climate.Parameter weirdness) {
      this.writeBiomeParameters(parameters,
              this.defaultParameter,
              this.defaultParameter,
              this.coastContinentalness,
              Climate.Parameter.span(this.erosionParameters[0], this.erosionParameters[2]),
              weirdness,
              0.0F,
              Biomes.STONY_SHORE
      );

      for(int i = 0; i < this.temperatureParameters.length; ++i) {
         Climate.Parameter parameterRange = this.temperatureParameters[i];

         for(int j = 0; j < this.humidityParameters.length; ++j) {
            Climate.Parameter parameterRange2 = this.humidityParameters[j];
            ResourceKey <Biome> registryKey = this.getRegularBiome(i, j, weirdness);
            ResourceKey <Biome> registryKey2 = this.getBadlandsOrRegularBiome(i, j, weirdness);
            ResourceKey <Biome> registryKey3 = this.getMountainStartBiome(i, j, weirdness);
            ResourceKey <Biome> registryKey4 = this.getWindsweptOrRegularBiome(i, j, weirdness);
            ResourceKey <Biome> registryKey5 = this.getNearMountainBiome(i, j, weirdness);
            ResourceKey <Biome> registryKey6 = this.getShoreBiome(i, j);
            ResourceKey <Biome> registryKey7 = this.getBiomeOrWindsweptSavanna(i, j, weirdness, registryKey);
            ResourceKey <Biome> registryKey8 = this.getErodedShoreBiome(i, j, weirdness);
            ResourceKey <Biome> registryKey9 = this.getMountainSlopeBiome(i, j, weirdness);


            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness),
                    Climate.Parameter.span(this.erosionParameters[5], this.erosionParameters[6]),
                    weirdness,
                    0.0F,
                    this.getWetlandType(i, j, weirdness)
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness),
                    this.erosionParameters[6],
                    weirdness,
                    0.0F,
                    this.getWetlandType(i, j, weirdness)
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    Climate.Parameter.span(this.nearInlandContinentalness,
                            this.farInlandContinentalness
                    ),
                    this.erosionParameters[0],
                    weirdness,
                    0.0F,
                    registryKey9
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    Climate.Parameter.span(this.nearInlandContinentalness,
                            this.midInlandContinentalness
                    ),
                    this.erosionParameters[1],
                    weirdness,
                    0.0F,
                    registryKey3
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    this.farInlandContinentalness,
                    this.erosionParameters[1],
                    weirdness,
                    0.0F,
                    i == 0 ? registryKey9 : registryKey5
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    this.nearInlandContinentalness,
                    this.erosionParameters[2],
                    weirdness,
                    0.0F,
                    registryKey
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    this.midInlandContinentalness,
                    this.erosionParameters[2],
                    weirdness,
                    0.0F,
                    registryKey2
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    this.farInlandContinentalness,
                    this.erosionParameters[2],
                    weirdness,
                    0.0F,
                    registryKey5
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness),
                    this.erosionParameters[3],
                    weirdness,
                    0.0F,
                    registryKey
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness),
                    this.erosionParameters[3],
                    weirdness,
                    0.0F,
                    registryKey2
            );
            if(weirdness.max() < 0L) {
               this.writeBiomeParameters(parameters,
                       parameterRange,
                       parameterRange2,
                       this.coastContinentalness,
                       this.erosionParameters[4],
                       weirdness,
                       0.0F,
                       registryKey6
               );
               this.writeBiomeParameters(parameters,
                       parameterRange,
                       parameterRange2,
                       Climate.Parameter.span(this.nearInlandContinentalness,
                               this.farInlandContinentalness
                       ),
                       this.erosionParameters[4],
                       weirdness,
                       0.0F,
                       registryKey
               );
            }
            else {
               this.writeBiomeParameters(parameters,
                       parameterRange,
                       parameterRange2,
                       Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness),
                       this.erosionParameters[4],
                       weirdness,
                       0.0F,
                       registryKey
               );
            }
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness),
                    this.erosionParameters[5],
                    weirdness,
                    0.0F,
                    registryKey4
            );

            if(i == 0) {
               this.writeBiomeParameters(parameters,
                       parameterRange,
                       parameterRange2,
                       Climate.Parameter.span(this.nearInlandContinentalness,
                               this.farInlandContinentalness
                       ),
                       this.erosionParameters[6],
                       weirdness,
                       0.0F,
                       registryKey
               );
               if(weirdness.max() < 0L) {
                  this.writeBiomeParameters(parameters,
                          parameterRange,
                          parameterRange2,
                          this.coastContinentalness,
                          this.erosionParameters[6],
                          weirdness,
                          0.0F,
                          registryKey6
                  );
               }
               else {
                  this.writeBiomeParameters(parameters,
                          parameterRange,
                          parameterRange2,
                          this.coastContinentalness,
                          this.erosionParameters[6],
                          weirdness,
                          0.0F,
                          registryKey
                  );
               }
               this.writeBiomeParameters(parameters,
                       parameterRange,
                       parameterRange2,
                       this.coastContinentalness,
                       this.erosionParameters[5],
                       weirdness,
                       0.0F,
                       registryKey8
               );
               this.writeBiomeParameters(parameters,
                       parameterRange,
                       parameterRange2,
                       this.nearInlandContinentalness,
                       this.erosionParameters[5],
                       weirdness,
                       0.0F,
                       registryKey7
               );
            }
         }
      }

   }

   private void writeLowBiomes(Consumer <Pair <Climate.ParameterPoint, ResourceKey <Biome>>> parameters, Climate.Parameter weirdness) {
      this.writeBiomeParameters(parameters,
              this.defaultParameter,
              this.defaultParameter,
              this.coastContinentalness,
              Climate.Parameter.span(this.erosionParameters[0], this.erosionParameters[2]),
              weirdness,
              0.0F,
              Biomes.STONY_SHORE
      );

      for(int i = 0; i < this.temperatureParameters.length; ++i) {
         Climate.Parameter parameterRange = this.temperatureParameters[i];

         for(int j = 0; j < this.humidityParameters.length; ++j) {
            Climate.Parameter parameterRange2 = this.humidityParameters[j];
            ResourceKey <Biome> registryKey = this.getRegularBiome(i, j, weirdness);
            ResourceKey <Biome> registryKey2 = this.getBadlandsOrRegularBiome(i, j, weirdness);
            ResourceKey <Biome> registryKey3 = this.getMountainStartBiome(i, j, weirdness);
            ResourceKey <Biome> registryKey4 = this.getShoreBiome(i, j);
            ResourceKey <Biome> registryKey5 = this.getBiomeOrWindsweptSavanna(i, j, weirdness, registryKey);
            ResourceKey <Biome> registryKey6 = this.getErodedShoreBiome(i, j, weirdness);
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness),
                    Climate.Parameter.span(this.erosionParameters[5], this.erosionParameters[6]),
                    weirdness,
                    0.0F,
                    this.getWetlandType(i, j, weirdness)
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness),
                    this.erosionParameters[6],
                    weirdness,
                    0.0F,
                    this.getWetlandType(i, j, weirdness)
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    this.nearInlandContinentalness,
                    Climate.Parameter.span(this.erosionParameters[0], this.erosionParameters[1]),
                    weirdness,
                    0.0F,
                    registryKey2
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness),
                    Climate.Parameter.span(this.erosionParameters[0], this.erosionParameters[1]),
                    weirdness,
                    0.0F,
                    registryKey3
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    this.nearInlandContinentalness,
                    Climate.Parameter.span(this.erosionParameters[2], this.erosionParameters[3]),
                    weirdness,
                    0.0F,
                    registryKey
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness),
                    Climate.Parameter.span(this.erosionParameters[2], this.erosionParameters[3]),
                    weirdness,
                    0.0F,
                    registryKey2
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    this.coastContinentalness,
                    Climate.Parameter.span(this.erosionParameters[3], this.erosionParameters[4]),
                    weirdness,
                    0.0F,
                    registryKey4
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    Climate.Parameter.span(this.nearInlandContinentalness,
                            this.farInlandContinentalness
                    ),
                    this.erosionParameters[4],
                    weirdness,
                    0.0F,
                    registryKey
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness),
                    this.erosionParameters[5],
                    weirdness,
                    0.0F,
                    registryKey
            );
            if(i == 0) {
               this.writeBiomeParameters(parameters,
                       parameterRange,
                       parameterRange2,
                       Climate.Parameter.span(this.nearInlandContinentalness,
                               this.farInlandContinentalness
                       ),
                       this.erosionParameters[6],
                       weirdness,
                       0.0F,
                       registryKey
               );
               this.writeBiomeParameters(parameters,
                       parameterRange,
                       parameterRange2,
                       this.coastContinentalness,
                       this.erosionParameters[6],
                       weirdness,
                       0.0F,
                       registryKey4
               );
               this.writeBiomeParameters(parameters,
                       parameterRange,
                       parameterRange2,
                       this.coastContinentalness,
                       this.erosionParameters[5],
                       weirdness,
                       0.0F,
                       registryKey6
               );
               this.writeBiomeParameters(parameters,
                       parameterRange,
                       parameterRange2,
                       this.nearInlandContinentalness,
                       this.erosionParameters[5],
                       weirdness,
                       0.0F,
                       registryKey5
               );
            }
         }
      }

   }

   private void writeValleyBiomes(Consumer <Pair <Climate.ParameterPoint, ResourceKey <Biome>>> parameters, Climate.Parameter weirdness) {
      this.writeBiomeParameters(parameters,
              this.frozenTemperature,
              this.defaultParameter,
              this.coastContinentalness,
              Climate.Parameter.span(this.erosionParameters[0], this.erosionParameters[1]),
              weirdness,
              0.0F,
              weirdness.max() < 0L ? Biomes.STONY_SHORE : Biomes.FROZEN_RIVER
      );
      this.writeBiomeParameters(parameters,
              this.nonFrozenTemperatureParameters,
              this.defaultParameter,
              this.coastContinentalness,
              Climate.Parameter.span(this.erosionParameters[0], this.erosionParameters[1]),
              weirdness,
              0.0F,
              weirdness.max() < 0L ? Biomes.STONY_SHORE : Biomes.RIVER
      );
      this.writeBiomeParameters(parameters,
              this.frozenTemperature,
              this.defaultParameter,
              this.nearInlandContinentalness,
              Climate.Parameter.span(this.erosionParameters[0], this.erosionParameters[1]),
              weirdness,
              0.0F,
              Biomes.FROZEN_RIVER
      );
      this.writeBiomeParameters(parameters,
              this.nonFrozenTemperatureParameters,
              this.defaultParameter,
              this.nearInlandContinentalness,
              Climate.Parameter.span(this.erosionParameters[0], this.erosionParameters[1]),
              weirdness,
              0.0F,
              Biomes.RIVER
      );
      this.writeBiomeParameters(parameters,
              this.frozenTemperature,
              this.defaultParameter,
              Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness),
              Climate.Parameter.span(this.erosionParameters[2], this.erosionParameters[5]),
              weirdness,
              0.0F,
              Biomes.FROZEN_RIVER
      );
      this.writeBiomeParameters(parameters,
              this.nonFrozenTemperatureParameters,
              this.defaultParameter,
              Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness),
              Climate.Parameter.span(this.erosionParameters[2], this.erosionParameters[4]),
              weirdness,
              0.0F,
              Biomes.RIVER
      );
      this.writeBiomeParameters(parameters,
              this.frozenTemperature,
              this.defaultParameter,
              this.coastContinentalness,
              this.erosionParameters[6],
              weirdness,
              0.0F,
              Biomes.FROZEN_RIVER
      );
      this.writeBiomeParameters(parameters,
              this.frozenTemperature,
              this.defaultParameter,
              Climate.Parameter.span(this.riverContinentalness, this.farInlandContinentalness),
              this.erosionParameters[6],
              weirdness,
              0.0F,
              Biomes.FROZEN_RIVER
      );

      for(int i = 0; i < this.temperatureParameters.length; ++i) {
         Climate.Parameter parameterRange = this.temperatureParameters[i];

         for(int j = 0; j < this.humidityParameters.length; ++j) {
            Climate.Parameter parameterRange2 = this.humidityParameters[j];
            ResourceKey <Biome> registryKey = this.getBadlandsOrRegularBiome(i, j, weirdness);
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness),
                    Climate.Parameter.span(this.erosionParameters[0], this.erosionParameters[1]),
                    weirdness,
                    0.0F,
                    registryKey
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness),
                    Climate.Parameter.span(this.erosionParameters[5], this.erosionParameters[6]),
                    weirdness,
                    0.0F,
                    this.getWetlandType(i, j, weirdness)
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness),
                    this.erosionParameters[6],
                    weirdness,
                    0.0F,
                    this.getWetlandType(i, j, weirdness)
            );
         }
      }

   }

   private void writeCaveBiomes(Consumer <Pair <Climate.ParameterPoint, ResourceKey <Biome>>> parameters) {
      this.writeCaveBiomeParameters(parameters,
              this.defaultParameter,
              this.defaultParameter,
              Climate.Parameter.span(0.8F, 1.0F),
              this.defaultParameter,
              this.defaultParameter,
              0.0F,
              Biomes.DRIPSTONE_CAVES
      );
      this.writeCaveBiomeParameters(parameters,
              this.defaultParameter,
              Climate.Parameter.span(0.7F, 1.0F),
              this.defaultParameter,
              this.defaultParameter,
              this.defaultParameter,
              0.0F,
              Biomes.LUSH_CAVES
      );
      this.writeDeepDarkParameters(parameters,
              this.defaultParameter,
              this.defaultParameter,
              this.defaultParameter,
              Climate.Parameter.span(this.erosionParameters[0], this.erosionParameters[1]),
              this.defaultParameter,
              0.0F,
              Biomes.DEEP_DARK
      );
   }

   private ResourceKey <Biome> getRegularBiome(int temperature, int humidity, Climate.Parameter weirdness) {
      if(weirdness.max() < 0L) {
         return this.commonBiomes[temperature][humidity];
      }
      else {
         ResourceKey <Biome> registryKey = this.uncommonBiomes[temperature][humidity];
         return registryKey == null ? this.commonBiomes[temperature][humidity] : registryKey;
      }
   }

   private ResourceKey <Biome> getBadlandsOrRegularBiome(int temperature, int humidity, Climate.Parameter weirdness) {
      return temperature == 4 ? this.getBadlandsBiome(humidity, weirdness) : this.getRegularBiome(temperature,
              humidity,
              weirdness
      );
   }

   private ResourceKey <Biome> getMountainStartBiome(int temperature, int humidity, Climate.Parameter weirdness) {
      return temperature == 0 ? this.getMountainSlopeBiome(temperature,
              humidity,
              weirdness
      ) : this.getBadlandsOrRegularBiome(temperature, humidity, weirdness);
   }

   private ResourceKey <Biome> getBiomeOrWindsweptSavanna(int temperature, int humidity, Climate.Parameter weirdness, ResourceKey <Biome> biomeKey) {
      return temperature > 1 && humidity < 4 && weirdness.max() >= 0L ? Biomes.WINDSWEPT_SAVANNA : biomeKey;
   }

   private ResourceKey <Biome> getErodedShoreBiome(int temperature, int humidity, Climate.Parameter weirdness) {
      ResourceKey <Biome> registryKey = weirdness.max() >= 0L ? this.getRegularBiome(temperature,
              humidity,
              weirdness
      ) : this.getShoreBiome(temperature, humidity);
      return this.getBiomeOrWindsweptSavanna(temperature, humidity, weirdness, registryKey);
   }

   private ResourceKey <Biome> getShoreBiome(int temperature, int humidity) {
      if(temperature == 0) {
         return Biomes.SNOWY_BEACH;
      }
      else {
         return temperature == 4 ? Biomes.DESERT : Biomes.BEACH;
      }
   }

   private ResourceKey <Biome> getBadlandsBiome(int humidity, Climate.Parameter weirdness) {
      if(humidity < 2) {
         return weirdness.max() < 0L ? Biomes.BADLANDS : Biomes.ERODED_BADLANDS;
      }
      else {
         return humidity < 3 ? Biomes.BADLANDS : Biomes.WOODED_BADLANDS;
      }
   }

   private ResourceKey <Biome> getNearMountainBiome(int temperature, int humidity, Climate.Parameter weirdness) {
      if(weirdness.max() >= 0L) {
         ResourceKey <Biome> registryKey = this.specialNearMountainBiomes[temperature][humidity];
         if(registryKey != null) {
            return registryKey;
         }
      }

      return this.nearMountainBiomes[temperature][humidity];
   }

   private ResourceKey <Biome> getPeakBiome(int temperature, int humidity, Climate.Parameter weirdness) {
      if(temperature <= 2) {
         return weirdness.max() < 0L ? Biomes.JAGGED_PEAKS : Biomes.FROZEN_PEAKS;
      }
      else {
         return temperature == 3 ? Biomes.STONY_PEAKS : this.getBadlandsBiome(humidity, weirdness);
      }
   }

   private ResourceKey <Biome> getMountainSlopeBiome(int temperature, int humidity, Climate.Parameter weirdness) {
      if(temperature >= 3) {
         return this.getNearMountainBiome(temperature, humidity, weirdness);
      }
      else {
         return humidity <= 1 ? Biomes.SNOWY_SLOPES : Biomes.GROVE;
      }
   }
   private ResourceKey <Biome> getWetlandType(int temperature, int humidity, Climate.Parameter weirdness) {
      if(temperature == 0) {
         return  this.getRegularBiome(temperature, humidity, weirdness);
      } else
      if(temperature == 3 && humidity > 2) {
         return  HibiscusBiomes.BAMBOO_WETLANDS;
      } else
      if(humidity <= 3 || temperature == 4) {
         return  HibiscusBiomes.MARSH;
      } else {
         return Biomes.SWAMP;
      }
   }

   private ResourceKey <Biome> getWindsweptOrRegularBiome(int temperature, int humidity, Climate.Parameter weirdness) {
      ResourceKey <Biome> registryKey = this.windsweptBiomes[temperature][humidity];
      return registryKey == null ? this.getRegularBiome(temperature, humidity, weirdness) : registryKey;
   }

   private void writeBiomeParameters(Consumer <Pair <Climate.ParameterPoint, ResourceKey <Biome>>> parameters, Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter continentalness, Climate.Parameter erosion, Climate.Parameter weirdness, float offset, ResourceKey <Biome> biome) {
      parameters.accept(Pair.of(Climate.parameters(temperature,
              humidity,
              continentalness,
              erosion,
              Climate.Parameter.point(0.0F),
              weirdness,
              offset
      ), biome));
      parameters.accept(Pair.of(Climate.parameters(temperature,
              humidity,
              continentalness,
              erosion,
              Climate.Parameter.point(1.0F),
              weirdness,
              offset
      ), biome));
   }

   private void writeCaveBiomeParameters(Consumer <Pair <Climate.ParameterPoint, ResourceKey <Biome>>> parameters, Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter continentalness, Climate.Parameter erosion, Climate.Parameter weirdness, float offset, ResourceKey <Biome> biome) {
      parameters.accept(Pair.of(Climate.parameters(temperature,
              humidity,
              continentalness,
              erosion,
              Climate.Parameter.span(0.2F, 0.9F),
              weirdness,
              offset
      ), biome));
   }

   private void writeDeepDarkParameters(Consumer <Pair <Climate.ParameterPoint, ResourceKey <Biome>>> parameters, Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter continentalness, Climate.Parameter erosion, Climate.Parameter weirdness, float offset, ResourceKey <Biome> biome) {
      parameters.accept(Pair.of(Climate.parameters(temperature,
              humidity,
              continentalness,
              erosion,
              Climate.Parameter.point(1.1F),
              weirdness,
              offset
      ), biome));
   }
}
