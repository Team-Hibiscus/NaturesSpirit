package net.hibiscus.naturespirit.config;

import net.hibiscus.naturespirit.NaturesSpirit;

import java.nio.file.Path;

/**
 * Nature's Spirit config (TOML).
 * Defaults are tuned for Fabric 26.1 + Terralith / multi-worldgen packs ("Explorer" preset).
 */
public final class NSConfig {

    public static int terraFeraxWeight;
    public static int terraSolarisWeight;
    public static int terraFlavaWeight;
    public static int terraLaetaWeight;
    public static int terraMaterWeight;

    public static boolean calciteGenerator;
    public static boolean deepslateGenerator;
    public static boolean creativeTab;
    public static boolean sugiAndStratifiedPillars;

    public static boolean vanillaTreesToggle;
    public static boolean birchForestToggle;
    public static boolean flowerForestToggle;
    public static boolean jungleToggle;
    public static boolean swampToggle;
    public static boolean desertToggle;
    public static boolean badlandsToggle;
    public static boolean mountainBiomesToggle;
    public static boolean savannaToggle;
    public static boolean darkForestToggle;
    public static boolean windsweptHillsToggle;

    public static boolean hasSugiForest;
    public static boolean hasWindsweptSugiForest;
    public static boolean hasBloomingSugiForest;
    public static boolean hasLavenderFields;
    public static boolean hasMarsh;
    public static boolean hasBambooWetlands;
    public static boolean hasWisteriaForest;
    public static boolean hasRedwoodForest;
    public static boolean hasSnowyRedwoodForest;
    public static boolean hasAspenForest;
    public static boolean hasMapleWoodlands;
    public static boolean hasGoldenWilds;
    public static boolean hasMarigoldMeadows;
    public static boolean hasFirForest;
    public static boolean hasSnowyFirForest;
    public static boolean hasCypressFields;
    public static boolean hasCedarThicket;
    public static boolean hasCarnationFields;
    public static boolean hasStratifiedDesert;
    public static boolean hasBloomingDunes;
    public static boolean hasLivelyDunes;
    public static boolean hasDrylands;
    public static boolean hasWoodedDrylands;
    public static boolean hasXericPlains;
    public static boolean hasWhiteCliffs;
    public static boolean hasPrairie;
    public static boolean hasOakSavanna;
    public static boolean hasHeatherFields;
    public static boolean hasTundra;
    public static boolean hasAlpineClearings;
    public static boolean hasAlpineHighlands;
    public static boolean hasConiferousCovert;
    public static boolean hasBorealTaiga;
    public static boolean hasTropicalShores;
    public static boolean hasTropicalWoods;
    public static boolean hasSparseTropicalWoods;
    public static boolean hasTropicalBasin;
    public static boolean hasAridSavanna;
    public static boolean hasScorchedDunes;
    public static boolean hasFloweringShrubland;
    public static boolean hasShrubland;
    public static boolean hasAridHighlands;
    public static boolean hasShrubbyHighlands;
    public static boolean hasWoodyHighlands;
    public static boolean hasRedPeaks;
    public static boolean hasDustySlopes;
    public static boolean hasSnowcappedRedPeaks;
    public static boolean hasSleetedSlopes;
    public static boolean hasBloomingHighlands;
    public static boolean hasChaparral;
    public static boolean hasFloralRidges;

    private NSConfig() {}

    public static void load(Path configDir) {
        TomlConfigFile file = TomlConfigFile.openOrCreate(configDir.resolve(NaturesSpirit.MOD_ID + ".toml"));

        // Slightly softer than 100 so Terralith keeps more contiguous climate, while NS still injects richly.
        terraFeraxWeight = file.defineInRange("region.terra_ferax_weight", 90, 0, Integer.MAX_VALUE,
                "Weight for Terra Ferax climate region (0 = off). Lower = more Terralith continuity.");
        terraSolarisWeight = file.defineInRange("region.terra_solaris_weight", 90, 0, Integer.MAX_VALUE,
                "Weight for Terra Solaris climate region (0 = off).");
        terraFlavaWeight = file.defineInRange("region.terra_flava_weight", 100, 0, Integer.MAX_VALUE,
                "Weight for Terra Flava climate region (0 = off).");
        terraLaetaWeight = file.defineInRange("region.terra_laeta_weight", 100, 0, Integer.MAX_VALUE,
                "Weight for Terra Laeta climate region (0 = off).");
        terraMaterWeight = file.defineInRange("region.terra_mater_weight", 90, 0, Integer.MAX_VALUE,
                "Weight for Terra Mater climate region (0 = off).");

        calciteGenerator = file.define("misc.calcite_generator", true, "Calcite clusters from coral features");
        deepslateGenerator = file.define("misc.deepslate_generator", true, "Deepslate generator");
        creativeTab = file.define("misc.creative_tab", true, "Extra creative inventory tab");
        sugiAndStratifiedPillars = file.define("misc.sugi_and_stratified_pillars", true,
                "Sugi / stratified desert pillars (disable on low-end devices)");

        vanillaTreesToggle = file.define("datapack.vanilla_trees_toggle", false, "Replace vanilla tree styles");
        birchForestToggle = file.define("datapack.birch_forest_toggle", true, "Restyle birch forests");
        flowerForestToggle = file.define("datapack.flower_forest_toggle", true, "Restyle flower forests");
        jungleToggle = file.define("datapack.jungle_toggle", true, "Restyle jungles");
        swampToggle = file.define("datapack.swamp_toggle", true, "Restyle swamps");
        desertToggle = file.define("datapack.desert_toggle", true, "Restyle deserts");
        badlandsToggle = file.define("datapack.badlands_toggle", true, "Restyle badlands");
        mountainBiomesToggle = file.define("datapack.mountain_biomes_toggle", true,
                "Restyle mountain biomes (includes Terralith-safe meadow feature order)");
        savannaToggle = file.define("datapack.savanna_toggle", true, "Restyle savannas");
        darkForestToggle = file.define("datapack.dark_forest_toggle", true, "Restyle dark forests");
        windsweptHillsToggle = file.define("datapack.windswept_hills_toggle", true, "Restyle windswept hills");

        hasSugiForest = file.define("biome.has_sugi_forest", true, "Sugi forest");
        hasWindsweptSugiForest = file.define("biome.has_windswept_sugi_forest", true, "Windswept sugi forest");
        hasBloomingSugiForest = file.define("biome.has_blooming_sugi_forest", true, "Blooming sugi forest");
        hasLavenderFields = file.define("biome.has_lavender_fields", true, "Lavender fields");
        hasMarsh = file.define("biome.has_marsh", true, "Marsh");
        hasBambooWetlands = file.define("biome.has_bamboo_wetlands", true, "Bamboo wetlands");
        hasWisteriaForest = file.define("biome.has_wisteria_forest", true, "Wisteria forest");
        hasRedwoodForest = file.define("biome.has_redwood_forest", true, "Redwood forest");
        hasSnowyRedwoodForest = file.define("biome.has_snowy_redwood_forest", true, "Snowy redwood forest");
        hasAspenForest = file.define("biome.has_aspen_forest", true, "Aspen forest");
        hasMapleWoodlands = file.define("biome.has_maple_woodlands", true, "Maple woodlands");
        hasGoldenWilds = file.define("biome.has_golden_wilds", true, "Golden wilds");
        hasMarigoldMeadows = file.define("biome.has_marigold_meadows", true, "Marigold meadows");
        hasFirForest = file.define("biome.has_fir_forest", true, "Fir forest");
        hasSnowyFirForest = file.define("biome.has_snowy_fir_forest", true, "Snowy fir forest");
        hasCypressFields = file.define("biome.has_cypress_fields", true, "Cypress fields");
        hasCedarThicket = file.define("biome.has_cedar_thicket", true, "Cedar thicket");
        hasCarnationFields = file.define("biome.has_carnation_fields", true, "Carnation fields");
        hasStratifiedDesert = file.define("biome.has_stratified_desert", true, "Stratified desert");
        hasBloomingDunes = file.define("biome.has_blooming_dunes", true, "Blooming dunes");
        hasLivelyDunes = file.define("biome.has_lively_dunes", true, "Lively dunes");
        hasDrylands = file.define("biome.has_drylands", true, "Drylands");
        hasWoodedDrylands = file.define("biome.has_wooded_drylands", true, "Wooded drylands");
        hasXericPlains = file.define("biome.has_xeric_plains", true, "Xeric plains");
        hasWhiteCliffs = file.define("biome.has_white_cliffs", true, "White cliffs");
        hasPrairie = file.define("biome.has_prairie", true, "Prairie");
        hasOakSavanna = file.define("biome.has_oak_savanna", true, "Oak savanna");
        hasHeatherFields = file.define("biome.has_heather_fields", true, "Heather fields");
        hasTundra = file.define("biome.has_tundra", true, "Tundra");
        hasAlpineClearings = file.define("biome.has_alpine_clearings", true, "Alpine clearings");
        hasAlpineHighlands = file.define("biome.has_alpine_highlands", true, "Alpine highlands");
        hasConiferousCovert = file.define("biome.has_coniferous_covert", true, "Coniferous covert");
        hasBorealTaiga = file.define("biome.has_boreal_taiga", true, "Boreal taiga");
        hasTropicalShores = file.define("biome.has_tropical_shores", true, "Tropical shores");
        hasTropicalWoods = file.define("biome.has_tropical_woods", true, "Tropical woods");
        hasSparseTropicalWoods = file.define("biome.has_sparse_tropical_woods", true, "Sparse tropical woods");
        hasTropicalBasin = file.define("biome.has_tropical_basin", true, "Tropical basin");
        hasAridSavanna = file.define("biome.has_arid_savanna", true, "Arid savanna");
        hasScorchedDunes = file.define("biome.has_scorched_dunes", true, "Scorched dunes");
        hasFloweringShrubland = file.define("biome.has_flowering_shrubland", true, "Flowering shrubland");
        hasShrubland = file.define("biome.has_shrubland", true, "Shrubland");
        hasAridHighlands = file.define("biome.has_arid_highlands", true, "Arid highlands");
        hasShrubbyHighlands = file.define("biome.has_shrubby_highlands", true, "Shrubby highlands");
        hasWoodyHighlands = file.define("biome.has_woody_highlands", true, "Woody highlands");
        hasRedPeaks = file.define("biome.has_red_peaks", true, "Red peaks");
        hasDustySlopes = file.define("biome.has_dusty_slopes", true, "Dusty slopes");
        hasSnowcappedRedPeaks = file.define("biome.has_snowcapped_red_peaks", true, "Snowcapped red peaks");
        hasSleetedSlopes = file.define("biome.has_sleeted_slopes", true, "Sleeted slopes");
        hasBloomingHighlands = file.define("biome.has_blooming_highlands", true, "Blooming highlands");
        hasChaparral = file.define("biome.has_chaparral", true, "Chaparral");
        hasFloralRidges = file.define("biome.has_floral_ridges", true, "Floral ridges");

        file.save();
    }
}
