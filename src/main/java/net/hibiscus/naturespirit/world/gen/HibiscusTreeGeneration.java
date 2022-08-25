package net.hibiscus.naturespirit.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.hibiscus.naturespirit.util.HibiscusTags;
import net.hibiscus.naturespirit.world.feature.HibiscusConfiguredFeatures;
import net.hibiscus.naturespirit.world.feature.HibiscusPlacedFeatures;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep;

public class HibiscusTreeGeneration {
    public static void generateTrees() {
//        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
//                GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.REDWOOD_PLACED.unwrapKey().get());
//        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
//                GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.LARGE_REDWOOD_PLACED.unwrapKey().get());
//        BiomeModifications.addFeature(BiomeSelectors.tag(HibiscusTags.Biomes.IS_WISTERIA),
//                GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.WISTERIA_PLACED.unwrapKey().get());
    }
    public static void addWisteriaTrees(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.WISTERIA_PLACED);
    }
    public static void addSakuraTrees(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.SAKURA_PLACED);
    }
    public static void addWisteriaFlowers(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FOREST_FLOWERS);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusConfiguredFeatures.FLOWER_WISTERIA_PLACED);
    }
}
