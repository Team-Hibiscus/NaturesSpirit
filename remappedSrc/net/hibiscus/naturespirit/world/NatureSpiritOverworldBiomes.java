package net.hibiscus.naturespirit.world;

import net.hibiscus.naturespirit.world.feature.HibiscusFeatureGroups;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import javax.annotation.Nullable;

import static net.hibiscus.naturespirit.datagen.HibiscusPlacedFeatures.*;

public class NatureSpiritOverworldBiomes {
   @Nullable private static final Music NORMAL_MUSIC = null;

   protected static int calculateSkyColor(float color) {
      float $$1 = color / 3.0F;
      $$1 = Mth.clamp($$1, -1.0F, 1.0F);
      return Mth.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
   }

   private static Biome biome(boolean precipitation, float temperature, float downfall, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder, @Nullable Music music) {
      return biome(precipitation, temperature, downfall, 4159204, 329011, spawnBuilder, biomeBuilder, music);
   }

   private static Biome biome(boolean precipitation, float temperature, float downfall, int waterColor, int waterFogColor, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder, @Nullable Music music) {
      return (new Biome.BiomeBuilder()).hasPrecipitation(precipitation)
              .temperature(temperature)
              .downfall(downfall)
              .specialEffects((new BiomeSpecialEffects.Builder()).waterColor(waterColor)
                      .waterFogColor(waterFogColor)
                      .fogColor(12638463)
                      .skyColor(calculateSkyColor(temperature))
                      .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                      .backgroundMusic(music)
                      .build())
              .mobSpawnSettings(spawnBuilder.build())
              .generationSettings(biomeBuilder.build())
              .build();
   }

   private static Biome biomeWithGrassColor(boolean precipitation, float temperature, float downfall, int grassColor, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder, @Nullable Music music) {
      return biomeWithGrassColor(precipitation,
              temperature,
              downfall,
              4159204,
              329011,
              grassColor,
              spawnBuilder,
              biomeBuilder,
              music
      );
   }

   private static Biome biomeWithGrassColor(boolean precipitation, float temperature, float downfall, int waterColor, int waterFogColor, int grassColor, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder, @Nullable Music music) {
      return (new Biome.BiomeBuilder()).hasPrecipitation(precipitation)
              .temperature(temperature)
              .downfall(downfall)
              .specialEffects((new BiomeSpecialEffects.Builder()).waterColor(waterColor)
                      .waterFogColor(waterFogColor)
                      .grassColorOverride(grassColor)
                      .fogColor(12638463)
                      .skyColor(calculateSkyColor(temperature))
                      .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                      .backgroundMusic(music)
                      .build())
              .mobSpawnSettings(spawnBuilder.build())
              .generationSettings(biomeBuilder.build())
              .build();
   }

   private static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
      BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
      BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
      BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
      BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
      BiomeDefaultFeatures.addDefaultSprings(builder);
      BiomeDefaultFeatures.addSurfaceFreezing(builder);
   }

   public static Biome erodedRiver(HolderGetter <PlacedFeature> featureLookup, HolderGetter <ConfiguredWorldCarver <?>> carverLookup) {
      MobSpawnSettings.Builder builder = (new MobSpawnSettings.Builder()).addSpawn(MobCategory.WATER_CREATURE,
                      new MobSpawnSettings.SpawnerData(EntityType.SQUID, 2, 1, 4)
              )
              .addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(EntityType.SALMON, 5, 1, 5));
      BiomeDefaultFeatures.commonSpawns(builder);
      builder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.DROWNED, 100, 1, 1));
      BiomeGenerationSettings.Builder lookupBackedBuilder = new BiomeGenerationSettings.Builder(featureLookup,
              carverLookup
      ).addFeature(GenerationStep.Decoration.FLUID_SPRINGS, RIVER_WATER);
      globalOverworldGeneration(lookupBackedBuilder);
      BiomeDefaultFeatures.addDefaultOres(lookupBackedBuilder);
      BiomeDefaultFeatures.addPlainGrass(lookupBackedBuilder);
      BiomeDefaultFeatures.addDefaultSoftDisks(lookupBackedBuilder);
      HibiscusFeatureGroups.addErodedRiverFlowers(lookupBackedBuilder);
      BiomeDefaultFeatures.addDefaultExtraVegetation(lookupBackedBuilder);

      return biome(true, 0.67F, 0.7F, 4159204, 329011, builder, lookupBackedBuilder, NORMAL_MUSIC);
   }

   public static Biome wisteriaForest(HolderGetter <PlacedFeature> holderGetter, HolderGetter <ConfiguredWorldCarver <?>> holderGetter2) {
      MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
      BiomeDefaultFeatures.farmAnimals(spawnBuilder);
      BiomeDefaultFeatures.commonSpawns(spawnBuilder);
      //        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.COW, 5, 2, 5));
      //        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.SHEEP, 5, 2, 5));
      //        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.CHICKEN, 5, 2, 5));
      //        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PIG, 5, 2, 5));
      //        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 5, 2, 5));

      BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(holderGetter,
              holderGetter2
      ).addFeature(GenerationStep.Decoration.FLUID_SPRINGS, WISTERIA_WATER);
      globalOverworldGeneration(biomeBuilder);
      BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
      BiomeDefaultFeatures.addPlainGrass(biomeBuilder);
      BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
      HibiscusFeatureGroups.addWisteriaTrees(biomeBuilder);
      HibiscusFeatureGroups.addWisteriaFlowers(biomeBuilder);
      biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_WATERLILY);
      BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);
      biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_WARM);
      return biome(true, 0.4F, 0.7F, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
   }


   public static Biome firForest(HolderGetter <PlacedFeature> holderGetter, HolderGetter <ConfiguredWorldCarver <?>> holderGetter2) {
      MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
      BiomeDefaultFeatures.farmAnimals(spawnBuilder);
      BiomeDefaultFeatures.commonSpawns(spawnBuilder);

      BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(holderGetter,
              holderGetter2
      );
      globalOverworldGeneration(biomeBuilder);
      BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
      BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
      BiomeDefaultFeatures.addPlainGrass(biomeBuilder);
      HibiscusFeatureGroups.addDenseFirTrees(biomeBuilder);
      HibiscusFeatureGroups.addFirVegetation(biomeBuilder);
      BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);
      return biomeWithGrassColor(true,
              0.45F,
              0.3F,
              11977352,
              spawnBuilder,
              biomeBuilder,
              Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FOREST)
      );
   }


   public static Biome redwoodForest(HolderGetter <PlacedFeature> holderGetter, HolderGetter <ConfiguredWorldCarver <?>> holderGetter2) {
      MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
      BiomeDefaultFeatures.farmAnimals(spawnBuilder);
      BiomeDefaultFeatures.commonSpawns(spawnBuilder);

      BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(holderGetter,
              holderGetter2
      );
      globalOverworldGeneration(biomeBuilder);
      HibiscusFeatureGroups.addRedwoodRock(biomeBuilder);
      BiomeDefaultFeatures.addFrozenSprings(biomeBuilder);
      BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
      BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
      BiomeDefaultFeatures.addPlainGrass(biomeBuilder);
      HibiscusFeatureGroups.addLargeRedwoodTrees(biomeBuilder);
      HibiscusFeatureGroups.addRedwoodTrees(biomeBuilder);
      HibiscusFeatureGroups.addSpruceBush(biomeBuilder);
      HibiscusFeatureGroups.addRedwoodSecondaryVegetation(biomeBuilder);
      BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);
      return biomeWithGrassColor(true,
              0.4F,
              0.6F,
              11451757,
              spawnBuilder,
              biomeBuilder,
              Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FOREST)
      );
   }

}
