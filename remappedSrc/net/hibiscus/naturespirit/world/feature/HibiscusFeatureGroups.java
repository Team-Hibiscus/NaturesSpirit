package net.hibiscus.naturespirit.world.feature;

import net.hibiscus.naturespirit.datagen.HibiscusPlacedFeatures;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep;

public class HibiscusFeatureGroups {

   public static void addSugiVegetation(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MiscOverworldPlacements.FOREST_ROCK);
   }

   public static void addRedwoodRock(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.REDWOOD_ROCK_PLACED);
   }

   public static void addWisteriaTrees(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.WISTERIA_PLACED);
   }

   public static void addSugiTrees(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.SUGI_PLACED);
   }

   public static void addFirTrees(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.FIR_PLACED);
   }

   public static void addDenseFirTrees(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.DENSE_FIR_PLACED);
   }

   public static void addAspenTrees(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.ASPEN_PLACED);
   }

   public static void adFewAspenTrees(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.FEW_ASPEN_PLACED);
   }

   public static void addRedwoodTrees(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.REDWOOD_PLACED);
   }

   public static void addLargeRedwoodTrees(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.LARGE_REDWOOD_PLACED);
   }

   public static void addOliveTrees(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.OLIVE_PLACED);
   }

   public static void addCypressTrees(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.CYPRESS_PLACED);
   }

   public static void addDenseCypressTrees(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.CYPRESS_PLACED);
   }

   public static void addOakTrees(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.CUSTOM_FANCY_OAK_TREE2_PLACED);
   }

   public static void addFewOakTrees(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.CUSTOM_FANCY_OAK_TREE2_PLACED);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.OAK_BUSH_PLACED);
   }

   public static void addSpruceBush(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.SPRUCE_BUSH_PLACED);
   }

   public static void addWisteriaFlowers(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FOREST_FLOWERS);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.FLOWER_WISTERIA_PLACED);
   }

   public static void addLavenderFlowers(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.FLOWER_LAVENDER_PLACED);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FOREST_FLOWERS);
   }

   public static void addFoxgloveFlowers(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.FLOWER_FOXGLOVE_PLACED);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FOREST_FLOWERS);
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

   public static void addFirVegetation(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.FLOWER_FIR_PLACED);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FOREST_FLOWERS);
   }

   public static void addCypressVegetation(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.FLOWER_CYPRESS_PLACED);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FOREST_FLOWERS);
   }

   public static void addCarnationVegetation(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.FLOWER_CARNATION_PLACED);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FOREST_FLOWERS);
   }
}
