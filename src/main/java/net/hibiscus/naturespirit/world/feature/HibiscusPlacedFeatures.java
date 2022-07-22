package net.hibiscus.naturespirit.world.feature;


import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class HibiscusPlacedFeatures {
    public static final Holder <PlacedFeature> LARGE_REDWOOD_PLACED = PlacementUtils.register("large_redwood_placed",
            HibiscusConfiguredFeatures.LARGE_REDWOOD_SPAWN, VegetationPlacements.treePlacement(
                    PlacementUtils.countExtra(1, 0.1f, 2)));
    public static final Holder <PlacedFeature> REDWOOD_PLACED = PlacementUtils.register("redwood_placed",
            HibiscusConfiguredFeatures.REDWOOD_SPAWN, VegetationPlacements.treePlacement(
                    PlacementUtils.countExtra(1, 0.1f, 2)));
    public static final Holder <PlacedFeature> WHITE_WISTERIA_PLACED = PlacementUtils.register("white_wisteria_placed",
            HibiscusConfiguredFeatures.WHITE_WISTERIA_SPAWN, VegetationPlacements.treePlacement(
                    PlacementUtils.countExtra(1, 0.1f, 2)));
    public static final Holder <PlacedFeature> BLUE_WISTERIA_PLACED = PlacementUtils.register("blue_wisteria_placed",
            HibiscusConfiguredFeatures.BLUE_WISTERIA_SPAWN, VegetationPlacements.treePlacement(
                    PlacementUtils.countExtra(1, 0.1f, 2)));
    public static final Holder <PlacedFeature> PINK_WISTERIA_PLACED = PlacementUtils.register("pink_wisteria_placed",
            HibiscusConfiguredFeatures.PINK_WISTERIA_SPAWN, VegetationPlacements.treePlacement(
                    PlacementUtils.countExtra(1, 0.1f, 2)));
    public static final Holder <PlacedFeature> PURPLE_WISTERIA_PLACED = PlacementUtils.register("purple_wisteria_placed",
            HibiscusConfiguredFeatures.PURPLE_WISTERIA_SPAWN, VegetationPlacements.treePlacement(
                    PlacementUtils.countExtra(1, 0.1f, 2)));
}
