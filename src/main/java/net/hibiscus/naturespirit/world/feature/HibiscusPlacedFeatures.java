package net.hibiscus.naturespirit.world.feature;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

public class HibiscusPlacedFeatures {
    public static final RegistryEntry<PlacedFeature> LARGE_REDWOOD_PLACED = PlacedFeatures.register("large_redwood_placed",
            HibiscusConfiguredFeatures.LARGE_REDWOOD_SPAWN, VegetationPlacedFeatures.modifiers(
                    PlacedFeatures.createCountExtraModifier(1, 0.1f, 2)));
    public static final RegistryEntry<PlacedFeature> REDWOOD_PLACED = PlacedFeatures.register("redwood_placed",
            HibiscusConfiguredFeatures.REDWOOD_SPAWN, VegetationPlacedFeatures.modifiers(
                    PlacedFeatures.createCountExtraModifier(1, 0.1f, 2)));
    public static final RegistryEntry<PlacedFeature> WHITE_WISTERIA_PLACED = PlacedFeatures.register("white_wisteria_placed",
            HibiscusConfiguredFeatures.WHITE_WISTERIA_SPAWN, VegetationPlacedFeatures.modifiers(
                    PlacedFeatures.createCountExtraModifier(1, 0.1f, 2)));
    public static final RegistryEntry<PlacedFeature> BLUE_WISTERIA_PLACED = PlacedFeatures.register("blue_wisteria_placed",
            HibiscusConfiguredFeatures.BLUE_WISTERIA_SPAWN, VegetationPlacedFeatures.modifiers(
                    PlacedFeatures.createCountExtraModifier(1, 0.1f, 2)));
    public static final RegistryEntry<PlacedFeature> PINK_WISTERIA_PLACED = PlacedFeatures.register("pink_wisteria_placed",
            HibiscusConfiguredFeatures.PINK_WISTERIA_SPAWN, VegetationPlacedFeatures.modifiers(
                    PlacedFeatures.createCountExtraModifier(1, 0.1f, 2)));
    public static final RegistryEntry<PlacedFeature> PURPLE_WISTERIA_PLACED = PlacedFeatures.register("purple_wisteria_placed",
            HibiscusConfiguredFeatures.PURPLE_WISTERIA_SPAWN, VegetationPlacedFeatures.modifiers(
                    PlacedFeatures.createCountExtraModifier(1, 0.1f, 2)));
}
