package net.hibiscus.naturespirit.world;

import net.hibiscus.naturespirit.registration.HibiscusSounds;
import net.hibiscus.naturespirit.world.feature.HibiscusFeatureGroups;
import net.minecraft.client.sound.MusicType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.MusicSound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.PlacedFeature;

import javax.annotation.Nullable;

import static net.hibiscus.naturespirit.datagen.HibiscusPlacedFeatures.RIVER_WATER;

public class NatureSpiritOverworldBiomes {
   @Nullable private static final MusicSound NORMAL_MUSIC = null;

   protected static int calculateSkyColor(float color) {
      float $$1 = color / 3.0F;
      $$1 = MathHelper.clamp($$1, -1.0F, 1.0F);
      return MathHelper.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
   }

   private static Biome biome(SpawnSettings.Builder spawnBuilder, GenerationSettings.LookupBackedBuilder biomeBuilder) {
      return (new Biome.Builder()).precipitation(true).temperature((float) 0.67).downfall((float) 0.7).effects((new BiomeEffects.Builder())
              .waterColor(4159204)
              .waterFogColor(329011)
              .fogColor(12638463)
              .skyColor(calculateSkyColor((float) 0.67))
              .moodSound(BiomeMoodSound.CAVE)
              .music(NatureSpiritOverworldBiomes.NORMAL_MUSIC)
              .build()).spawnSettings(spawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
   }

   private static Biome biomeWithGrassColor(SpawnSettings.Builder spawnBuilder, GenerationSettings.LookupBackedBuilder biomeBuilder, @Nullable MusicSound music) {
      return (new Biome.Builder()).precipitation(true).temperature((float) 0.4).downfall((float) 0.6).effects((new BiomeEffects.Builder())
              .waterColor(4159204)
              .waterFogColor(329011)
              .grassColor(11451757)
              .fogColor(12638463)
              .skyColor(calculateSkyColor((float) 0.4))
              .moodSound(BiomeMoodSound.CAVE)
              .music(music)
              .build()).spawnSettings(spawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
   }

   private static void globalOverworldGeneration(GenerationSettings.LookupBackedBuilder builder) {
      DefaultBiomeFeatures.addLandCarvers(builder);
      DefaultBiomeFeatures.addAmethystGeodes(builder);
      DefaultBiomeFeatures.addDungeons(builder);
      DefaultBiomeFeatures.addMineables(builder);
      DefaultBiomeFeatures.addSprings(builder);
      DefaultBiomeFeatures.addFrozenTopLayer(builder);
   }

   public static Biome erodedRiver(RegistryEntryLookup <PlacedFeature> featureLookup, RegistryEntryLookup <ConfiguredCarver <?>> carverLookup) {
      SpawnSettings.Builder builder = (new SpawnSettings.Builder()).spawn(SpawnGroup.WATER_CREATURE, new SpawnSettings.SpawnEntry(EntityType.SQUID, 2, 1, 4)).spawn(SpawnGroup.WATER_AMBIENT,
              new SpawnSettings.SpawnEntry(EntityType.SALMON, 5, 1, 5)
      );
      DefaultBiomeFeatures.addBatsAndMonsters(builder);
      builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.DROWNED, 100, 1, 1));
      GenerationSettings.LookupBackedBuilder lookupBackedBuilder = new GenerationSettings.LookupBackedBuilder(featureLookup, carverLookup).feature(GenerationStep.Feature.FLUID_SPRINGS, RIVER_WATER);
      globalOverworldGeneration(lookupBackedBuilder);
      DefaultBiomeFeatures.addDefaultOres(lookupBackedBuilder);
      DefaultBiomeFeatures.addPlainsTallGrass(lookupBackedBuilder);
      DefaultBiomeFeatures.addDefaultDisks(lookupBackedBuilder);
      HibiscusFeatureGroups.addErodedRiverFlowers(lookupBackedBuilder);
      DefaultBiomeFeatures.addDefaultVegetation(lookupBackedBuilder);

      return biome(builder, lookupBackedBuilder);
   }


   public static Biome redwoodForest(RegistryEntryLookup <PlacedFeature> holderGetter, RegistryEntryLookup <ConfiguredCarver <?>> holderGetter2) {
      SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
      DefaultBiomeFeatures.addFarmAnimals(spawnBuilder);
      DefaultBiomeFeatures.addBatsAndMonsters(spawnBuilder);

      GenerationSettings.LookupBackedBuilder biomeBuilder = new GenerationSettings.LookupBackedBuilder(holderGetter, holderGetter2);
      globalOverworldGeneration(biomeBuilder);
      HibiscusFeatureGroups.addRedwoodRock(biomeBuilder);
      DefaultBiomeFeatures.addFrozenLavaSpring(biomeBuilder);
      DefaultBiomeFeatures.addDefaultOres(biomeBuilder);
      DefaultBiomeFeatures.addDefaultDisks(biomeBuilder);
      DefaultBiomeFeatures.addPlainsTallGrass(biomeBuilder);
      HibiscusFeatureGroups.addLargeRedwoodTrees(biomeBuilder);
      HibiscusFeatureGroups.addRedwoodTrees(biomeBuilder);
      HibiscusFeatureGroups.addSpruceBush(biomeBuilder);
      HibiscusFeatureGroups.addRedwoodSecondaryVegetation(biomeBuilder);
      DefaultBiomeFeatures.addDefaultVegetation(biomeBuilder);
      return biomeWithGrassColor(spawnBuilder, biomeBuilder, MusicType.createIngameMusic(HibiscusSounds.MUSIC_OVERWORLD_REDWOOD));
   }

}
