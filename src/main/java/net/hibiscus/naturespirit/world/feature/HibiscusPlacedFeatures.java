package net.hibiscus.naturespirit.world.feature;


import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.blocks.HibiscusBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class HibiscusPlacedFeatures {


    public static final RegistryKey <PlacedFeature> LARGE_REDWOOD_CHECKED = registerKey("large_redwood_checked");
    public static final RegistryKey <PlacedFeature> REDWOOD_CHECKED = registerKey("redwood_checked");
    public static final RegistryKey <PlacedFeature> WILLOW_CHECKED = registerKey("willow_checked");
    public static final RegistryKey <PlacedFeature> WILLOW_PLACED = registerKey("willow_placed");
    public static final RegistryKey <PlacedFeature> WHITE_WISTERIA_CHECKED = registerKey("white_wisteria_checked");
    public static final RegistryKey <PlacedFeature> BLUE_WISTERIA_CHECKED = registerKey("blue_wisteria_checked");
    public static final RegistryKey <PlacedFeature> PINK_WISTERIA_CHECKED = registerKey("pink_wisteria_checked");
    public static final RegistryKey <PlacedFeature> PURPLE_WISTERIA_CHECKED = registerKey("purple_wisteria_checked");
    public static final RegistryKey <PlacedFeature> PINK_SAKURA_CHECKED = registerKey("pink_sakura_checked");
    public static final RegistryKey <PlacedFeature> WHITE_SAKURA_CHECKED = registerKey("white_sakura_checked");
    public static final RegistryKey <PlacedFeature> OAK_BUSH_CHECKED = registerKey("oak_bush_checked");
    public static final RegistryKey <PlacedFeature> SPRUCE_BUSH_CHECKED = registerKey("spruce_bush_checked");
    public static final RegistryKey <PlacedFeature> FLOWER_WISTERIA_PLACED = registerKey("flower_wisteria_placed");
    public static final RegistryKey <PlacedFeature> FLOWER_SAKURA_PLACED = registerKey("flower_sakura_placed");
    public static final RegistryKey <PlacedFeature> FLOWER_REDWOOD_PLACED = registerKey("flower_redwood_placed");
    public static final RegistryKey <PlacedFeature> FLOWER_LAVENDER_PLACED = registerKey("flower_lavender_placed");
    public static final RegistryKey <PlacedFeature> FLOWER_RIVER_PLACED = registerKey("flower_river_placed");
    public static final RegistryKey <PlacedFeature> WISTERIA_WATER = registerKey("wisteria_water_placed");
    public static final RegistryKey <PlacedFeature> LAVENDER_WATER = registerKey("lavender_water_placed");
    public static final RegistryKey <PlacedFeature> SWAMP_WATER = registerKey("swamp_water_placed");
    public static final RegistryKey <PlacedFeature> RIVER_WATER = registerKey("river_water_placed");
    public static final RegistryKey <PlacedFeature> LARGE_REDWOOD_PLACED = registerKey("large_redwood_placed");
    public static final RegistryKey <PlacedFeature> REDWOOD_PLACED = registerKey("redwood_placed");
    public static final RegistryKey <PlacedFeature> SPRUCE_BUSH_PLACED = registerKey("spruce_bush_placed");
    public static final RegistryKey <PlacedFeature> REDWOOD_ROCK_PLACED = registerKey("redwood_rock_placed");
    public static final RegistryKey <PlacedFeature> WISTERIA_PLACED = registerKey("wisteria_placed");
    public static final RegistryKey <PlacedFeature> SAKURA_PLACED = registerKey("sakura_placed");
    public static final RegistryKey <PlacedFeature> OAK_BUSH_PLACED = registerKey("oak_bush_placed");
    public static final RegistryKey <PlacedFeature> CUSTOM_FANCY_OAK_TREE_PLACED = registerKey("custom_fancy_oak_tree_placed");
    public static final RegistryKey <PlacedFeature> CUSTOM_FANCY_OAK_TREE2_PLACED = registerKey("custom_fancy_oak_tree2_placed");
    public static final RegistryKey <PlacedFeature> CATTAILS = registerKey("cattails_placed");
    private static final PlacementModifier TREE_THRESHOLD = SurfaceWaterDepthFilterPlacementModifier.of(0);

    public static void bootstrap(Registerable <PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);


        registerKey(context, LARGE_REDWOOD_CHECKED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.LARGE_REDWOOD_TREE),
                List.of(PlacedFeatures.wouldSurvive(HibiscusBlocks.REDWOOD_SAPLING[0])));
        registerKey(context, REDWOOD_CHECKED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.REDWOOD_TREE),
                PlacedFeatures.wouldSurvive(HibiscusBlocks.REDWOOD_SAPLING[0]));
        registerKey(context, WILLOW_CHECKED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.WILLOW_TREE),
                PlacedFeatures.wouldSurvive(HibiscusBlocks.WILLOW_SAPLING[0]));
        registerKey(context, WHITE_WISTERIA_CHECKED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.WHITE_WISTERIA_TREE),
                PlacedFeatures.wouldSurvive(HibiscusBlocks.WHITE_WISTERIA_SAPLING[0]));
        registerKey(context, BLUE_WISTERIA_CHECKED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.BLUE_WISTERIA_TREE),
                PlacedFeatures.wouldSurvive(HibiscusBlocks.BLUE_WISTERIA_SAPLING[0]));
        registerKey(context, PINK_WISTERIA_CHECKED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.PINK_WISTERIA_TREE),
                PlacedFeatures.wouldSurvive(HibiscusBlocks.PINK_WISTERIA_SAPLING[0]));
        registerKey(context, PURPLE_WISTERIA_CHECKED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.PURPLE_WISTERIA_TREE),
                PlacedFeatures.wouldSurvive(HibiscusBlocks.PURPLE_WISTERIA_SAPLING[0]));
        registerKey(context, PINK_SAKURA_CHECKED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.PINK_SAKURA_TREE),
                PlacedFeatures.wouldSurvive(HibiscusBlocks.PINK_SAKURA_SAPLING[0]));
        registerKey(context, WHITE_SAKURA_CHECKED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.WHITE_SAKURA_TREE),
                PlacedFeatures.wouldSurvive(HibiscusBlocks.WHITE_SAKURA_SAPLING[0]));
        registerKey(context, OAK_BUSH_CHECKED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.OAK_BUSH),
                PlacedFeatures.wouldSurvive(Blocks.OAK_SAPLING));
        registerKey(context, SPRUCE_BUSH_CHECKED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.SPRUCE_BUSH),
                PlacedFeatures.wouldSurvive(Blocks.SPRUCE_SAPLING));
        registerKey(context, FLOWER_WISTERIA_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.FLOWER_WISTERIA_FOREST), CountPlacementModifier.of(3), RarityFilterPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
        registerKey(context, FLOWER_SAKURA_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.FLOWER_SAKURA_GROVE), CountPlacementModifier.of(3), RarityFilterPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
        registerKey(context, FLOWER_REDWOOD_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.FLOWER_REDWOOD_FOREST), CountPlacementModifier.of(3), RarityFilterPlacementModifier.of(10), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
        registerKey(context, FLOWER_LAVENDER_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.FLOWER_LAVENDER_FIELD), CountPlacementModifier.of(5), RarityFilterPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
        registerKey(context, FLOWER_RIVER_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.FLOWER_ERODED_RIVER), CountPlacementModifier.of(5), RarityFilterPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

        registerKey(context, WISTERIA_WATER, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.WISTERIA_DELTA), CountMultilayerPlacementModifier.of(20), BiomePlacementModifier.of());
        registerKey(context, LAVENDER_WATER, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.WISTERIA_DELTA), CountMultilayerPlacementModifier.of(12), BiomePlacementModifier.of());
        registerKey(context, SWAMP_WATER, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.SWAMP_DELTA), CountMultilayerPlacementModifier.of(10), BiomePlacementModifier.of());
        registerKey(context, RIVER_WATER, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.RIVER_DELTA), CountMultilayerPlacementModifier.of(20), BiomePlacementModifier.of());

        registerKey(context, LARGE_REDWOOD_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.LARGE_REDWOOD_TREE_SPAWN), CountPlacementModifier.of(3), SquarePlacementModifier.of(), TREE_THRESHOLD, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.of());
        registerKey(context, REDWOOD_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.REDWOOD_TREE_SPAWN), CountPlacementModifier.of(7), SquarePlacementModifier.of(), TREE_THRESHOLD, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.of());
        registerKey(context, WILLOW_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.WILLOW_TREE_SPAWN), CountPlacementModifier.of(2), SquarePlacementModifier.of(), SurfaceWaterDepthFilterPlacementModifier.of(2), PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.of());
        registerKey(context, SPRUCE_BUSH_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.SPRUCE_BUSH_SPAWN), CountPlacementModifier.of(4), SquarePlacementModifier.of(), TREE_THRESHOLD, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.of());
        registerKey(context, REDWOOD_ROCK_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(MiscConfiguredFeatures.FOREST_ROCK), CountPlacementModifier.of(1), SquarePlacementModifier.of(), TREE_THRESHOLD, PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
        registerKey(context, WISTERIA_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.WISTERIA_SPAWN), CountPlacementModifier.of(15), SquarePlacementModifier.of(), TREE_THRESHOLD, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.of());
        registerKey(context, SAKURA_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.SAKURA_SPAWN), CountPlacementModifier.of(1), SquarePlacementModifier.of(), TREE_THRESHOLD, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.of());
        registerKey(context, OAK_BUSH_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.OAK_BUSH_SPAWN), CountPlacementModifier.of(1), SquarePlacementModifier.of(), TREE_THRESHOLD, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.of());
        registerKey(context, CUSTOM_FANCY_OAK_TREE_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.FANCY_OAK_TREE_SPAWN), CountPlacementModifier.of(1), SquarePlacementModifier.of(), TREE_THRESHOLD, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.of());
        registerKey(context, CUSTOM_FANCY_OAK_TREE2_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.FANCY_OAK_TREE_SPAWN), RarityFilterPlacementModifier.of(35), CountPlacementModifier.of(1), SquarePlacementModifier.of(), TREE_THRESHOLD, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.of());
        registerKey(context, CATTAILS, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.CATTAILS), RarityFilterPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    }

    public static RegistryKey <PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(NatureSpirit.MOD_ID, name));
    }

    private static void registerKey(Registerable <PlacedFeature> context, RegistryKey <PlacedFeature> key, RegistryEntry <ConfiguredFeature <?, ?>> configuration, List <PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfig, F extends Feature <FC>> void registerKey(Registerable <PlacedFeature> context, RegistryKey <PlacedFeature> key,
                                                                                              RegistryEntry <ConfiguredFeature <?, ?>> configuration, PlacementModifier... modifiers) {
        registerKey(context, key, configuration, List.of(modifiers));
    }

}
