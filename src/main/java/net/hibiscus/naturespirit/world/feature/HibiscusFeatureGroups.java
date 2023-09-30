package net.hibiscus.naturespirit.world.feature;

import net.hibiscus.naturespirit.datagen.HibiscusPlacedFeatures;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

public class HibiscusFeatureGroups {

   public static void addRedwoodRock(GenerationSettings.LookupBackedBuilder builder) {
      builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, HibiscusPlacedFeatures.REDWOOD_ROCK_PLACED);
   }

   public static void addRedwoodTrees(GenerationSettings.LookupBackedBuilder builder) {
      builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, HibiscusPlacedFeatures.REDWOOD_PLACED);
   }
   public static void addLargeRedwoodTrees(GenerationSettings.LookupBackedBuilder builder) {
      builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, HibiscusPlacedFeatures.LARGE_REDWOOD_PLACED);
   }

   public static void addSpruceBush(GenerationSettings.LookupBackedBuilder builder) {
      builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, HibiscusPlacedFeatures.SPRUCE_BUSH_PLACED);
   }
   public static void addErodedRiverFlowers(GenerationSettings.LookupBackedBuilder builder) {
      builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, HibiscusPlacedFeatures.OAK_BUSH_PLACED);
      builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_GRASS_PLAIN);
      builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, HibiscusPlacedFeatures.FLOWER_RIVER_PLACED);
   }
   public static void addRedwoodSecondaryVegetation(GenerationSettings.LookupBackedBuilder builder) {
      builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_GRASS_PLAIN);
      builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, HibiscusPlacedFeatures.FLOWER_REDWOOD_PLACED);
      builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.FOREST_FLOWERS);
   }
}
