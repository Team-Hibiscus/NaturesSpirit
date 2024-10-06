package net.hibiscus.naturespirit.world.biolith;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.CriterionBuilder;
import com.terraformersmc.biolith.api.biome.sub.RatioTargets;
import net.hibiscus.naturespirit.config.NSConfig;
import net.hibiscus.naturespirit.registration.NSBiomes;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.world.biome.BiomeKeys;

import static com.terraformersmc.biolith.api.biome.sub.CriterionBuilder.*;
import static com.terraformersmc.biolith.api.biome.sub.CriterionBuilder.neighbor;

public class NSBiomeGen {
    public static void createBiomePlacement() {

        // BIOME GENERATION

        // Fir Forest Region
        if (NSConfig.has_fir_forest) {
            BiomePlacement.replaceOverworld(BiomeKeys.TAIGA, NSBiomes.FIR_FOREST, 0.25D);
        }
        if (NSConfig.has_snowy_fir_forest) {
            BiomePlacement.addSubOverworld(NSBiomes.FIR_FOREST, NSBiomes.SNOWY_FIR_FOREST, CriterionBuilder.neighbor(BiomeKeys.SNOWY_TAIGA));
        }
        if (NSConfig.has_redwood_forest) {
            BiomePlacement.addSubOverworld(NSBiomes.FIR_FOREST, NSBiomes.REDWOOD_FOREST, anyOf(CriterionBuilder.neighbor(BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA),CriterionBuilder.neighbor(BiomeKeys.OLD_GROWTH_PINE_TAIGA)));
        }
        if (NSConfig.has_prairie) {
            BiomePlacement.addSubOverworld(NSBiomes.FIR_FOREST, NSBiomes.PRAIRIE, anyOf(CriterionBuilder.neighbor(BiomeTags.IS_HILL),CriterionBuilder.neighbor(BiomeKeys.PLAINS),CriterionBuilder.neighbor(BiomeKeys.SUNFLOWER_PLAINS),CriterionBuilder.neighbor(BiomeKeys.MEADOW)));
        }

        // Tundra Region
        if (NSConfig.has_tundra) {
            BiomePlacement.replaceOverworld(BiomeKeys.SNOWY_PLAINS, NSBiomes.TUNDRA, 0.2D);
        }
        if (NSConfig.has_snowy_fir_forest) {
            BiomePlacement.addSubOverworld(NSBiomes.TUNDRA, NSBiomes.SNOWY_FIR_FOREST, CriterionBuilder.neighbor(BiomeKeys.SNOWY_TAIGA));
        }
        if (NSConfig.has_fir_forest) {
            BiomePlacement.addSubOverworld(NSBiomes.TUNDRA, NSBiomes.FIR_FOREST, CriterionBuilder.neighbor(BiomeKeys.FOREST));
        }

        // Wisteria Forest Region
        if (NSConfig.has_wisteria_forest) {
            BiomePlacement.replaceOverworld(BiomeKeys.SWAMP, NSBiomes.WISTERIA_FOREST, 0.25D);
            BiomePlacement.addSubOverworld(NSBiomes.WISTERIA_FOREST, NSBiomes.WISTERIA_FOREST, CriterionBuilder.neighbor(BiomeKeys.MANGROVE_SWAMP));
        }

        // Marsh
        if (NSConfig.has_marsh) {
            BiomePlacement.addSubOverworld(BiomeKeys.SWAMP, NSBiomes.MARSH, anyOf((allOf(ratioMax(RatioTargets.EDGE, 0.3F), neighbor(BiomeTags.IS_OCEAN))),(CriterionBuilder.RIVERSIDE),CriterionBuilder.allOf(NEAR_BORDER, neighbor(BiomeKeys.MANGROVE_SWAMP))));
            BiomePlacement.addSubOverworld(BiomeKeys.MANGROVE_SWAMP, NSBiomes.MARSH, anyOf((allOf(ratioMax(RatioTargets.EDGE, 0.3F), neighbor(BiomeTags.IS_OCEAN))),(CriterionBuilder.RIVERSIDE),CriterionBuilder.allOf(NEAR_BORDER, neighbor(BiomeKeys.MANGROVE_SWAMP))));
            BiomePlacement.addSubOverworld(BiomeKeys.DESERT, NSBiomes.MARSH, anyOf(CriterionBuilder.neighbor(BiomeKeys.SWAMP)));
        }

        // Sugi Forest Region
        if (NSConfig.has_sugi_forest) {
            BiomePlacement.replaceOverworld(BiomeKeys.DARK_FOREST, NSBiomes.SUGI_FOREST, 0.2D);
        }
        if (NSConfig.has_blooming_sugi_forest) {
            BiomePlacement.addSubOverworld(NSBiomes.SUGI_FOREST, NSBiomes.BLOOMING_SUGI_FOREST, CriterionBuilder.ratioMax(RatioTargets.CENTER, 0.4F));
        }
        if (NSConfig.has_windswept_sugi_forest) {
            BiomePlacement.addSubOverworld(NSBiomes.SUGI_FOREST, NSBiomes.WINDSWEPT_SUGI_FOREST, CriterionBuilder.neighbor(BiomeTags.IS_HILL));
        }

        // Cypress Fields Region
        if (NSConfig.has_cypress_fields) {
            BiomePlacement.replaceOverworld(BiomeKeys.PLAINS, NSBiomes.CYPRESS_FIELDS, 0.1D);
        }
        if (NSConfig.has_lavender_fields) {
            BiomePlacement.addSubOverworld(NSBiomes.CYPRESS_FIELDS, NSBiomes.LAVENDER_FIELDS, anyOf(CriterionBuilder.neighbor(BiomeKeys.CHERRY_GROVE),CriterionBuilder.neighbor(BiomeKeys.MEADOW)));
        }
        if (NSConfig.has_carnation_fields) {
            BiomePlacement.addSubOverworld(NSBiomes.CYPRESS_FIELDS, NSBiomes.CARNATION_FIELDS, anyOf(CriterionBuilder.neighbor(BiomeKeys.PLAINS),CriterionBuilder.neighbor(BiomeKeys.SUNFLOWER_PLAINS)));
        }
        if (NSConfig.has_xeric_plains) {
            BiomePlacement.addSubOverworld(NSBiomes.CYPRESS_FIELDS, NSBiomes.XERIC_PLAINS, anyOf(CriterionBuilder.neighbor(BiomeKeys.SAVANNA),CriterionBuilder.neighbor(BiomeKeys.SAVANNA_PLATEAU),CriterionBuilder.neighbor(BiomeKeys.DESERT)));
        }

        // Maple Woodlands Region
        if (NSConfig.has_maple_woodlands) {
            BiomePlacement.replaceOverworld(BiomeKeys.FOREST, NSBiomes.MAPLE_WOODLANDS, 0.2D);
            BiomePlacement.addSubOverworld(NSBiomes.MAPLE_WOODLANDS, NSBiomes.MAPLE_WOODLANDS, anyOf(CriterionBuilder.neighbor(BiomeKeys.BIRCH_FOREST)));
        }
        if (NSConfig.has_golden_wilds) {
            BiomePlacement.addSubOverworld(NSBiomes.MAPLE_WOODLANDS, NSBiomes.GOLDEN_WILDS, anyOf(CriterionBuilder.neighbor(BiomeKeys.PLAINS),CriterionBuilder.neighbor(BiomeKeys.SUNFLOWER_PLAINS)));
        }
        if (NSConfig.has_marigold_meadows) {
            BiomePlacement.addSubOverworld(NSBiomes.MAPLE_WOODLANDS, NSBiomes.MARIGOLD_MEADOWS, anyOf(CriterionBuilder.neighbor(BiomeKeys.MEADOW),CriterionBuilder.neighbor(BiomeKeys.FLOWER_FOREST),CriterionBuilder.neighbor(BiomeKeys.CHERRY_GROVE)));
        }
        if (NSConfig.has_aspen_forest) {
            BiomePlacement.addSubOverworld(NSBiomes.MAPLE_WOODLANDS, NSBiomes.ASPEN_FOREST, CriterionBuilder.neighbor(BiomeKeys.OLD_GROWTH_BIRCH_FOREST));
        }

        // Aspen Forest Region
        if (NSConfig.has_aspen_forest) {
            BiomePlacement.replaceOverworld(BiomeKeys.OLD_GROWTH_PINE_TAIGA, NSBiomes.ASPEN_FOREST, 0.3D);
            BiomePlacement.addSubOverworld(NSBiomes.ASPEN_FOREST, NSBiomes.ASPEN_FOREST, CriterionBuilder.neighbor(BiomeKeys.OLD_GROWTH_BIRCH_FOREST));
        }

        // Coniferous Covert Region
        if (NSConfig.has_boreal_taiga) {
            BiomePlacement.replaceOverworld(BiomeKeys.SNOWY_TAIGA, NSBiomes.CONIFEROUS_COVERT, 0.2D);
        }
        if (NSConfig.has_alpine_clearings) {
            BiomePlacement.addSubOverworld(NSBiomes.BOREAL_TAIGA, NSBiomes.ALPINE_CLEARINGS, anyOf(CriterionBuilder.neighbor(BiomeKeys.SNOWY_PLAINS)));
        }
        if (NSConfig.has_coniferous_covert) {
            BiomePlacement.addSubOverworld(NSBiomes.BOREAL_TAIGA, NSBiomes.CONIFEROUS_COVERT, anyOf(CriterionBuilder.neighbor(BiomeKeys.TAIGA),CriterionBuilder.neighbor(BiomeKeys.OLD_GROWTH_PINE_TAIGA),CriterionBuilder.neighbor(BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA)));
        }
        if (NSConfig.has_alpine_highlands) {
            BiomePlacement.addSubOverworld(NSBiomes.BOREAL_TAIGA, NSBiomes.ALPINE_HIGHLANDS, anyOf(CriterionBuilder.neighbor(BiomeKeys.SNOWY_SLOPES),CriterionBuilder.neighbor(BiomeKeys.MEADOW)));
        }
        if (NSConfig.has_woody_highlands) {
            BiomePlacement.addSubOverworld(NSBiomes.BOREAL_TAIGA, NSBiomes.WOODY_HIGHLANDS, anyOf(CriterionBuilder.neighbor(BiomeTags.IS_HILL),CriterionBuilder.neighbor(BiomeKeys.GROVE)));
        }

        // Tropical Woods Region
        if (NSConfig.has_tropical_woods) {
            BiomePlacement.replaceOverworld(BiomeKeys.JUNGLE, NSBiomes.TROPICAL_WOODS, 0.25D);
        }
        if (NSConfig.has_sparse_tropical_woods) {
            BiomePlacement.addSubOverworld(NSBiomes.TROPICAL_WOODS, NSBiomes.SPARSE_TROPICAL_WOODS, CriterionBuilder.ratioMax(RatioTargets.EDGE, 0.3F));
        }
        if (NSConfig.has_tropical_basin) {
            BiomePlacement.addSubOverworld(NSBiomes.TROPICAL_WOODS, NSBiomes.TROPICAL_BASIN, CriterionBuilder.ratioMax(RatioTargets.CENTER, 0.3F));
        }
        if (NSConfig.has_tropical_shores) {
            BiomePlacement.addSubOverworld(NSBiomes.TROPICAL_WOODS, NSBiomes.TROPICAL_SHORES, CriterionBuilder.neighbor(BiomeKeys.BEACH));
        }

        // Bamboo Wetlands
        if (NSConfig.has_bamboo_wetlands) {
            BiomePlacement.addSubOverworld(BiomeKeys.JUNGLE, NSBiomes.BAMBOO_WETLANDS, anyOf((CriterionBuilder.OCEANSIDE),CriterionBuilder.allOf(NEAR_BORDER, neighbor(BiomeKeys.SWAMP)),CriterionBuilder.allOf(NEAR_BORDER, neighbor(BiomeKeys.MANGROVE_SWAMP))));
            BiomePlacement.addSubOverworld(BiomeKeys.BAMBOO_JUNGLE, NSBiomes.BAMBOO_WETLANDS, anyOf((CriterionBuilder.OCEANSIDE),CriterionBuilder.allOf(NEAR_BORDER, neighbor(BiomeKeys.SWAMP)),CriterionBuilder.allOf(NEAR_BORDER, neighbor(BiomeKeys.MANGROVE_SWAMP))));
            BiomePlacement.addSubOverworld(BiomeKeys.SPARSE_JUNGLE, NSBiomes.BAMBOO_WETLANDS, anyOf((CriterionBuilder.OCEANSIDE),CriterionBuilder.allOf(NEAR_BORDER, neighbor(BiomeKeys.SWAMP)),CriterionBuilder.allOf(NEAR_BORDER, neighbor(BiomeKeys.MANGROVE_SWAMP))));
        }
    }
}
