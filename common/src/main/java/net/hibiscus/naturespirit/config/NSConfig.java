package net.hibiscus.naturespirit.config;

import net.hibiscus.naturespirit.NaturesSpirit;

import java.nio.file.Path;

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

        terraFeraxWeight = file.defineInRange("region.terra_ferax_weight", 100, 0, Integer.MAX_VALUE);
        terraSolarisWeight = file.defineInRange("region.terra_solaris_weight", 100, 0, Integer.MAX_VALUE);
        terraFlavaWeight = file.defineInRange("region.terra_flava_weight", 100, 0, Integer.MAX_VALUE);
        terraLaetaWeight = file.defineInRange("region.terra_laeta_weight", 100, 0, Integer.MAX_VALUE);
        terraMaterWeight = file.defineInRange("region.terra_mater_weight", 100, 0, Integer.MAX_VALUE);

        calciteGenerator = file.define("misc.calcite_generator", true, "Calcite clusters from coral feature");
        deepslateGenerator = file.define("misc.deepslate_generator", true, "Toggle the Deepslate Generator");
        creativeTab = file.define("misc.creative_tab", true, "Toggle the additional creative inventory tab");
        sugiAndStratifiedPillars = file.define("misc.sugi_and_stratified_pillars", true, "Toggle the Pillar Generation (turn off for lower end devices)");

        vanillaTreesToggle = file.define("datapack.vanilla_trees_toggle", false);
        birchForestToggle = file.define("datapack.birch_forest_toggle", true);
        flowerForestToggle = file.define("datapack.flower_forest_toggle", true);
        jungleToggle = file.define("datapack.jungle_toggle", true);
        swampToggle = file.define("datapack.swamp_toggle", true);
        desertToggle = file.define("datapack.desert_toggle", true);
        badlandsToggle = file.define("datapack.badlands_toggle", true);
        mountainBiomesToggle = file.define("datapack.mountain_biomes_toggle", true);
        savannaToggle = file.define("datapack.savanna_toggle", true);
        darkForestToggle = file.define("datapack.dark_forest_toggle", true);
        windsweptHillsToggle = file.define("datapack.windswept_hills_toggle", true);

        hasSugiForest = file.define("biome.has_sugi_forest", true);
        hasWindsweptSugiForest = file.define("biome.has_windswept_sugi_forest", true);
        hasBloomingSugiForest = file.define("biome.has_blooming_sugi_forest", true);
        hasLavenderFields = file.define("biome.has_lavender_fields", true);
        hasMarsh = file.define("biome.has_marsh", true);
        hasBambooWetlands = file.define("biome.has_bamboo_wetlands", true);
        hasWisteriaForest = file.define("biome.has_wisteria_forest", true);
        hasRedwoodForest = file.define("biome.has_redwood_forest", true);
        hasSnowyRedwoodForest = file.define("biome.has_snowy_redwood_forest", true);
        hasAspenForest = file.define("biome.has_aspen_forest", true);
        hasMapleWoodlands = file.define("biome.has_maple_woodlands", true);
        hasGoldenWilds = file.define("biome.has_golden_wilds", true);
        hasMarigoldMeadows = file.define("biome.has_marigold_meadows", true);
        hasFirForest = file.define("biome.has_fir_forest", true);
        hasSnowyFirForest = file.define("biome.has_snowy_fir_forest", true);
        hasCypressFields = file.define("biome.has_cypress_fields", true);
        hasCedarThicket = file.define("biome.has_cedar_thicket", true);
        hasCarnationFields = file.define("biome.has_carnation_fields", true);
        hasStratifiedDesert = file.define("biome.has_stratified_desert", true);
        hasBloomingDunes = file.define("biome.has_blooming_dunes", true);
        hasLivelyDunes = file.define("biome.has_lively_dunes", true);
        hasDrylands = file.define("biome.has_drylands", true);
        hasWoodedDrylands = file.define("biome.has_wooded_drylands", true);
        hasXericPlains = file.define("biome.has_xeric_plains", true);
        hasWhiteCliffs = file.define("biome.has_white_cliffs", true);
        hasPrairie = file.define("biome.has_prairie", true);
        hasOakSavanna = file.define("biome.has_oak_savanna", true);
        hasHeatherFields = file.define("biome.has_heather_fields", true);
        hasTundra = file.define("biome.has_tundra", true);
        hasAlpineClearings = file.define("biome.has_alpine_clearings", true);
        hasAlpineHighlands = file.define("biome.has_alpine_highlands", true);
        hasConiferousCovert = file.define("biome.has_coniferous_covert", true);
        hasBorealTaiga = file.define("biome.has_boreal_taiga", true);
        hasTropicalShores = file.define("biome.has_tropical_shores", true);
        hasTropicalWoods = file.define("biome.has_tropical_woods", true);
        hasSparseTropicalWoods = file.define("biome.has_sparse_tropical_woods", true);
        hasTropicalBasin = file.define("biome.has_tropical_basin", true);
        hasAridSavanna = file.define("biome.has_arid_savanna", true);
        hasScorchedDunes = file.define("biome.has_scorched_dunes", true);
        hasFloweringShrubland = file.define("biome.has_flowering_shrubland", true);
        hasShrubland = file.define("biome.has_shrubland", true);
        hasAridHighlands = file.define("biome.has_arid_highlands", true);
        hasShrubbyHighlands = file.define("biome.has_shrubby_highlands", true);
        hasWoodyHighlands = file.define("biome.has_woody_highlands", true);
        hasRedPeaks = file.define("biome.has_red_peaks", true);
        hasDustySlopes = file.define("biome.has_dusty_slopes", true);
        hasSnowcappedRedPeaks = file.define("biome.has_snowcapped_red_peaks", true);
        hasSleetedSlopes = file.define("biome.has_sleeted_slopes", true);
        hasBloomingHighlands = file.define("biome.has_blooming_highlands", true);
        hasChaparral = file.define("biome.has_chaparral", true);
        hasFloralRidges = file.define("biome.has_floral_ridges", true);

        file.save();
    }
}
