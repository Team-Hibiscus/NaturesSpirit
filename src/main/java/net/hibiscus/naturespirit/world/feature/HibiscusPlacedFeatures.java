package net.hibiscus.naturespirit.world.feature;


import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.blocks.HibiscusBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.MiscOverworldFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class HibiscusPlacedFeatures {


    public static final ResourceKey <PlacedFeature> LARGE_REDWOOD_CHECKED = registerKey("large_redwood_checked");
    public static final ResourceKey <PlacedFeature> REDWOOD_CHECKED = registerKey("redwood_checked");
    public static final ResourceKey <PlacedFeature> WHITE_WISTERIA_CHECKED = registerKey("white_wisteria_checked");
    public static final ResourceKey <PlacedFeature> BLUE_WISTERIA_CHECKED = registerKey("blue_wisteria_checked");
    public static final ResourceKey <PlacedFeature> PINK_WISTERIA_CHECKED = registerKey("pink_wisteria_checked");
    public static final ResourceKey <PlacedFeature> PURPLE_WISTERIA_CHECKED = registerKey("purple_wisteria_checked");
    public static final ResourceKey <PlacedFeature> PINK_SAKURA_CHECKED = registerKey("pink_sakura_checked");
    public static final ResourceKey <PlacedFeature> WHITE_SAKURA_CHECKED = registerKey("white_sakura_checked");
    public static final ResourceKey <PlacedFeature> OAK_BUSH_CHECKED = registerKey("oak_bush_checked");
    public static final ResourceKey <PlacedFeature> SPRUCE_BUSH_CHECKED = registerKey("spruce_bush_checked");
    public static final ResourceKey <PlacedFeature> FLOWER_WISTERIA_PLACED = registerKey("flower_wisteria_placed");
    public static final ResourceKey <PlacedFeature> FLOWER_SAKURA_PLACED = registerKey("flower_sakura_placed");
    public static final ResourceKey <PlacedFeature> FLOWER_REDWOOD_PLACED = registerKey("flower_redwood_placed");
    public static final ResourceKey <PlacedFeature> WISTERIA_WATER = registerKey("wisteria_water_placed");
    public static final ResourceKey <PlacedFeature> LARGE_REDWOOD_PLACED = registerKey("large_redwood_placed");
    public static final ResourceKey <PlacedFeature> REDWOOD_PLACED = registerKey("redwood_placed");
    public static final ResourceKey <PlacedFeature> SPRUCE_BUSH_PLACED = registerKey("spruce_bush_placed");
    public static final ResourceKey <PlacedFeature> REDWOOD_ROCK_PLACED = registerKey("redwood_rock_placed");
    public static final ResourceKey <PlacedFeature> WISTERIA_PLACED = registerKey("wisteria_placed");
    public static final ResourceKey <PlacedFeature> SAKURA_PLACED = registerKey("sakura_placed");
    public static final ResourceKey <PlacedFeature> OAK_BUSH_PLACED = registerKey("oak_bush_placed");
    public static final ResourceKey <PlacedFeature> CUSTOM_FANCY_OAK_TREE_PLACED = registerKey("custom_fancy_oak_tree_placed");
    private static final PlacementModifier TREE_THRESHOLD = SurfaceWaterDepthFilter.forMaxDepth(0);

    public static void bootstrap(BootstapContext <PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);


        registerKey(context, LARGE_REDWOOD_CHECKED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.LARGE_REDWOOD_TREE),
                List.of(PlacementUtils.filteredByBlockSurvival(HibiscusBlocks.REDWOOD_SAPLING)));
        registerKey(context, REDWOOD_CHECKED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.REDWOOD_TREE),
                PlacementUtils.filteredByBlockSurvival(HibiscusBlocks.REDWOOD_SAPLING));
        registerKey(context, WHITE_WISTERIA_CHECKED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.WHITE_WISTERIA_TREE),
                PlacementUtils.filteredByBlockSurvival(HibiscusBlocks.WHITE_WISTERIA_SAPLING));
        registerKey(context, BLUE_WISTERIA_CHECKED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.BLUE_WISTERIA_TREE),
                PlacementUtils.filteredByBlockSurvival(HibiscusBlocks.BLUE_WISTERIA_SAPLING));
        registerKey(context, PINK_WISTERIA_CHECKED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.PINK_WISTERIA_TREE),
                PlacementUtils.filteredByBlockSurvival(HibiscusBlocks.PINK_WISTERIA_SAPLING));
        registerKey(context, PURPLE_WISTERIA_CHECKED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.PURPLE_WISTERIA_TREE),
                PlacementUtils.filteredByBlockSurvival(HibiscusBlocks.PURPLE_WISTERIA_SAPLING));
        registerKey(context, PINK_SAKURA_CHECKED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.PINK_SAKURA_TREE),
                PlacementUtils.filteredByBlockSurvival(HibiscusBlocks.PINK_SAKURA_SAPLING));
        registerKey(context, WHITE_SAKURA_CHECKED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.WHITE_SAKURA_TREE),
                PlacementUtils.filteredByBlockSurvival(HibiscusBlocks.WHITE_SAKURA_SAPLING));
        registerKey(context, OAK_BUSH_CHECKED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.OAK_BUSH),
                PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING));
        registerKey(context, SPRUCE_BUSH_CHECKED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.SPRUCE_BUSH),
                PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING));
        registerKey(context, FLOWER_WISTERIA_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.FLOWER_WISTERIA_FOREST), CountPlacement.of(3), RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        registerKey(context, FLOWER_SAKURA_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.FLOWER_SAKURA_GROVE), CountPlacement.of(3), RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        registerKey(context, FLOWER_REDWOOD_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.FLOWER_REDWOOD_FOREST), CountPlacement.of(3), RarityFilter.onAverageOnceEvery(10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        registerKey(context, WISTERIA_WATER, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.WISTERIA_DELTA), CountOnEveryLayerPlacement.of(20), BiomeFilter.biome());
        registerKey(context, LARGE_REDWOOD_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.LARGE_REDWOOD_TREE_SPAWN), CountPlacement.of(3), InSquarePlacement.spread(), TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());
        registerKey(context, REDWOOD_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.REDWOOD_TREE_SPAWN), CountPlacement.of(7), InSquarePlacement.spread(), TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());
        registerKey(context, SPRUCE_BUSH_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.SPRUCE_BUSH_SPAWN), CountPlacement.of(4), InSquarePlacement.spread(), TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());
        registerKey(context, REDWOOD_ROCK_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(MiscOverworldFeatures.FOREST_ROCK), CountPlacement.of(1), InSquarePlacement.spread(), TREE_THRESHOLD, PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        registerKey(context, WISTERIA_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.WISTERIA_SPAWN), CountPlacement.of(15), InSquarePlacement.spread(), TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());
        registerKey(context, SAKURA_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.SAKURA_SPAWN), CountPlacement.of(1), InSquarePlacement.spread(), TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());
        registerKey(context, OAK_BUSH_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.OAK_BUSH_SPAWN), CountPlacement.of(1), InSquarePlacement.spread(), TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());
        registerKey(context, CUSTOM_FANCY_OAK_TREE_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.FANCY_OAK_TREE_SPAWN), CountPlacement.of(1), InSquarePlacement.spread(), TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());

    }

    public static ResourceKey <PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(NatureSpirit.MOD_ID, name));
    }

    private static void registerKey(BootstapContext <PlacedFeature> context, ResourceKey <PlacedFeature> key, Holder <ConfiguredFeature <?, ?>> configuration, List <PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfiguration, F extends Feature <FC>> void registerKey(BootstapContext <PlacedFeature> context, ResourceKey <PlacedFeature> key,
                                                                                              Holder <ConfiguredFeature <?, ?>> configuration, PlacementModifier... modifiers) {
        registerKey(context, key, configuration, List.of(modifiers));
    }

}
