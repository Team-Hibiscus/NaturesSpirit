package net.hibiscus.naturespirit.world.feature;


import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.NetherFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.*;

import static net.minecraft.data.worldgen.placement.VegetationPlacements.TREE_THRESHOLD;

public class HibiscusPlacedFeatures {
    public static final Holder <PlacedFeature> WISTERIA_WATER = PlacementUtils.register("wisteria_water", HibiscusConfiguredFeatures.WISTERIA_DELTA, new PlacementModifier[]{CountOnEveryLayerPlacement.of(20), BiomeFilter.biome()});

    public static final Holder <PlacedFeature> LARGE_REDWOOD_PLACED = PlacementUtils.register("large_redwood_placed",
            HibiscusConfiguredFeatures.LARGE_REDWOOD_SPAWN, VegetationPlacements.treePlacement(
                    PlacementUtils.countExtra(1, 0.1f, 2)));
    public static final Holder <PlacedFeature> REDWOOD_PLACED = PlacementUtils.register("redwood_placed",
            HibiscusConfiguredFeatures.REDWOOD_SPAWN, VegetationPlacements.treePlacement(
                    PlacementUtils.countExtra(1, 0.1f, 2)));
    public static final Holder <PlacedFeature> WISTERIA_PLACED = PlacementUtils.register("white_wisteria_placed",
            HibiscusConfiguredFeatures.WISTERIA_SPAWN, new PlacementModifier[]{CountPlacement.of(15), InSquarePlacement.spread(), TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome()});

    public static final Holder <PlacedFeature> SAKURA_PLACED = PlacementUtils.register("sakura_placed",
            HibiscusConfiguredFeatures.SAKURA_SPAWN, new PlacementModifier[]{CountPlacement.of(1), InSquarePlacement.spread(), TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome()});
    public static final Holder <PlacedFeature> OAK_BUSH_PLACED = PlacementUtils.register("oak_bush_placed",
            HibiscusConfiguredFeatures.OAK_BUSH_SPAWN, new PlacementModifier[]{CountPlacement.of(1), InSquarePlacement.spread(), TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome()});
    public static final Holder <PlacedFeature> CUSTOM_FANCY_OAK_TREE_PLACED = PlacementUtils.register("custom_fancy_oak_tree_placed",
            HibiscusConfiguredFeatures.FANCY_OAK_TREE_SPAWN, new PlacementModifier[]{CountPlacement.of(1), InSquarePlacement.spread(), TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome()});
}
