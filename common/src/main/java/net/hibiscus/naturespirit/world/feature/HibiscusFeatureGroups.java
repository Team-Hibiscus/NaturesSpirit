package net.hibiscus.naturespirit.world.feature;

import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep;

public class HibiscusFeatureGroups {

   public static void addSugiVegetation(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MiscOverworldPlacements.FOREST_ROCK);
   }

   public static void addRedwoodRock(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatureKeys.REDWOOD_ROCK_PLACED);
   }

   public static void addWisteriaTrees(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatureKeys.WISTERIA_PLACED);
   }

   public static void addSugiTrees(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatureKeys.SUGI_PLACED);
   }

   public static void addFirTrees(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatureKeys.FIR_PLACED);
   }

   public static void addDenseFirTrees(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatureKeys.DENSE_FIR_PLACED);
   }

   public static void addAspenTrees(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatureKeys.ASPEN_PLACED);
   }

   public static void adFewAspenTrees(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatureKeys.FEW_ASPEN_PLACED);
   }

   public static void addRedwoodTrees(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatureKeys.REDWOOD_PLACED);
   }

   public static void addLargeRedwoodTrees(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatureKeys.LARGE_REDWOOD_PLACED);
   }

   public static void addOliveTrees(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatureKeys.OLIVE_PLACED);
   }

   public static void addCypressTrees(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatureKeys.CYPRESS_PLACED);
   }

   public static void addDenseCypressTrees(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatureKeys.CYPRESS_PLACED);
   }

   public static void addOakTrees(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatureKeys.CUSTOM_FANCY_OAK_TREE_PLACED);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatureKeys.OAK_BUSH_PLACED);
   }

   public static void addFewOakTrees(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatureKeys.CUSTOM_FANCY_OAK_TREE2_PLACED);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatureKeys.OAK_BUSH_PLACED);
   }

   public static void addSpruceBush(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatureKeys.SPRUCE_BUSH_PLACED);
   }

   public static void addWisteriaFlowers(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FOREST_FLOWERS);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatureKeys.FLOWER_WISTERIA_PLACED);
   }

   public static void addSugiSecondaryVegetation(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatureKeys.FLOWER_SUGI_PLACED);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FOREST_FLOWERS);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE_SWAMP);
   }

   public static void addGoldenWildsVegetation(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatureKeys.FLOWER_GOLDEN_PLACED);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FOREST_FLOWERS);
   }

   public static void addGoldenFieldsVegetation(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatureKeys.FLOWER_GOLDEN2_PLACED);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FOREST_FLOWERS);
   }

   public static void addLavenderFlowers(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatureKeys.FLOWER_LAVENDER_PLACED);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FOREST_FLOWERS);
   }

   public static void addFoxgloveFlowers(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatureKeys.FLOWER_FOXGLOVE_PLACED);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FOREST_FLOWERS);
   }

   public static void addErodedRiverFlowers(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatureKeys.OAK_BUSH_PLACED);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatureKeys.FLOWER_RIVER_PLACED);
   }

   public static void addRedwoodSecondaryVegetation(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatureKeys.FLOWER_REDWOOD_PLACED);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FOREST_FLOWERS);
   }

   public static void addFirVegetation(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatureKeys.FLOWER_FIR_PLACED);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FOREST_FLOWERS);
   }

   public static void addCypressVegetation(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatureKeys.FLOWER_CYPRESS_PLACED);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FOREST_FLOWERS);
   }

   public static void addCarnationVegetation(BiomeGenerationSettings.Builder builder) {
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatureKeys.FLOWER_CARNATION_PLACED);
      builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FOREST_FLOWERS);
   }
}
