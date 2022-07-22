package net.hibiscus.naturespirit.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.hibiscus.naturespirit.world.feature.HibiscusPlacedFeatures;
import net.minecraft.world.level.levelgen.GenerationStep;

public class HibiscusTreeGeneration {
    public static void generateTrees() {
//        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
//                GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.REDWOOD_PLACED.unwrapKey().get());
//        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
//                GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.LARGE_REDWOOD_PLACED.unwrapKey().get());
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.WHITE_WISTERIA_PLACED.unwrapKey().get());
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.BLUE_WISTERIA_PLACED.unwrapKey().get());
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.PINK_WISTERIA_PLACED.unwrapKey().get());
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.PURPLE_WISTERIA_PLACED.unwrapKey().get());
    }
}
