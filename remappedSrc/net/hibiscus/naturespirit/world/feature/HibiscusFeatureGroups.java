package net.hibiscus.naturespirit.world.feature;

import net.hibiscus.naturespirit.datagen.HibiscusPlacedFeatures;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep;

public class HibiscusFeatureGroups {

   public static void addRedwoodRock(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.REDWOOD_ROCK_PLACED);
   }

   public static void addWisteriaTrees(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.WISTERIA_PLACED);
   }
   public static void addRedwoodTrees(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.REDWOOD_PLACED);
   }
   public static void addLargeRedwoodTrees(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.LARGE_REDWOOD_PLACED);
   }

   public static void addSpruceBush(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.SPRUCE_BUSH_PLACED);
   }

   public static void addWisteriaFlowers(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FOREST_FLOWERS);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.FLOWER_WISTERIA_PLACED);
   }
   public static void addErodedRiverFlowers(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.OAK_BUSH_PLACED);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.FLOWER_RIVER_PLACED);
   }
   public static void addRedwoodSecondaryVegetation(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.FLOWER_REDWOOD_PLACED);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FOREST_FLOWERS);
   }
}
