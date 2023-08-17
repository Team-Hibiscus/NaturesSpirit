package net.hibiscus.naturespirit.world;


import net.hibiscus.naturespirit.Constants;
import net.hibiscus.naturespirit.blocks.HibiscusBlocks;
import net.hibiscus.naturespirit.world.feature.HibiscusConfiguredFeatureKeys;
import net.hibiscus.naturespirit.world.feature.HibiscusPlacedFeatureKeys;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.MiscOverworldFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class HibiscusPlacedFeatures {

   private static final PlacementModifier TREE_THRESHOLD = SurfaceWaterDepthFilter.forMaxDepth(0);

   public static void bootstrap(BootstapContext <PlacedFeature> context) {
      var configuredFeatureRegistryEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);


      registerKey(context,
              HibiscusPlacedFeatureKeys.LARGE_REDWOOD_CHECKED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.LARGE_REDWOOD_TREE),
              List.of(PlacementUtils.filteredByBlockSurvival(HibiscusBlocks.REDWOOD_SAPLING[0]))
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.REDWOOD_CHECKED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.REDWOOD_TREE),
              PlacementUtils.filteredByBlockSurvival(HibiscusBlocks.REDWOOD_SAPLING[0])
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.ASPEN_CHECKED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.ASPEN_TREE),
              PlacementUtils.filteredByBlockSurvival(HibiscusBlocks.ASPEN_SAPLING[0])
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.ASPEN_BEES_CHECKED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.ASPEN_TREE_BEES),
              PlacementUtils.filteredByBlockSurvival(HibiscusBlocks.ASPEN_SAPLING[0])
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.CYPRESS_CHECKED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.CYPRESS_TREE),
              PlacementUtils.filteredByBlockSurvival(HibiscusBlocks.CYPRESS_SAPLING[0])
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.JOSHUA_CHECKED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.JOSHUA_TREE),
              PlacementUtils.filteredByBlockSurvival(HibiscusBlocks.JOSHUA_SAPLING[0])
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.FIR_CHECKED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.FIR_TREE),
              PlacementUtils.filteredByBlockSurvival(HibiscusBlocks.FIR_SAPLING[0])
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.WILLOW_CHECKED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.WILLOW_TREE),
              PlacementUtils.filteredByBlockSurvival(HibiscusBlocks.WILLOW_SAPLING[0])
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.WHITE_WISTERIA_CHECKED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.WHITE_WISTERIA_TREE),
              PlacementUtils.filteredByBlockSurvival(HibiscusBlocks.WHITE_WISTERIA_SAPLING[0])
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.BLUE_WISTERIA_CHECKED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.BLUE_WISTERIA_TREE),
              PlacementUtils.filteredByBlockSurvival(HibiscusBlocks.BLUE_WISTERIA_SAPLING[0])
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.PINK_WISTERIA_CHECKED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.PINK_WISTERIA_TREE),
              PlacementUtils.filteredByBlockSurvival(HibiscusBlocks.PINK_WISTERIA_SAPLING[0])
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.PURPLE_WISTERIA_CHECKED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.PURPLE_WISTERIA_TREE),
              PlacementUtils.filteredByBlockSurvival(HibiscusBlocks.PURPLE_WISTERIA_SAPLING[0])
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.SUGI_CHECKED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.SUGI_TREE),
              PlacementUtils.filteredByBlockSurvival(HibiscusBlocks.SUGI_SAPLING[0])
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.OLIVE_CHECKED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.OLIVE_TREE),
              PlacementUtils.filteredByBlockSurvival(HibiscusBlocks.OLIVE_SAPLING[0])
      );

      registerKey(context,
              HibiscusPlacedFeatureKeys.OAK_BUSH_CHECKED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.OAK_BUSH),
              PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING)
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.SPRUCE_BUSH_CHECKED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.SPRUCE_BUSH),
              PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING)
      );

      registerKey(context,
              HibiscusPlacedFeatureKeys.FLOWER_WISTERIA_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.FLOWER_WISTERIA_FOREST),
              CountPlacement.of(3),
              RarityFilter.onAverageOnceEvery(2),
              InSquarePlacement.spread(),
              PlacementUtils.HEIGHTMAP,
              BiomeFilter.biome()
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.FLOWER_SUGI_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.FLOWER_SUGI_FOREST),
              CountPlacement.of(3),
              RarityFilter.onAverageOnceEvery(2),
              InSquarePlacement.spread(),
              PlacementUtils.HEIGHTMAP,
              BiomeFilter.biome()
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.FLOWER_REDWOOD_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.FLOWER_REDWOOD_FOREST),
              CountPlacement.of(3),
              RarityFilter.onAverageOnceEvery(10),
              InSquarePlacement.spread(),
              PlacementUtils.HEIGHTMAP,
              BiomeFilter.biome()
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.FLOWER_LAVENDER_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.FLOWER_LAVENDER_FIELD),
              CountPlacement.of(5),
              RarityFilter.onAverageOnceEvery(2),
              InSquarePlacement.spread(),
              PlacementUtils.HEIGHTMAP,
              BiomeFilter.biome()
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.FLOWER_FOXGLOVE_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.FLOWER_FOXGLOVE_FIELD),
              CountPlacement.of(8),
              RarityFilter.onAverageOnceEvery(1),
              InSquarePlacement.spread(),
              PlacementUtils.HEIGHTMAP,
              BiomeFilter.biome()
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.FLOWER_RIVER_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.FLOWER_ERODED_RIVER),
              CountPlacement.of(5),
              RarityFilter.onAverageOnceEvery(2),
              InSquarePlacement.spread(),
              PlacementUtils.HEIGHTMAP,
              BiomeFilter.biome()
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.FLOWER_GOLDEN_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.FLOWER_GOLDEN_WILDS),
              CountPlacement.of(3),
              RarityFilter.onAverageOnceEvery(5),
              InSquarePlacement.spread(),
              PlacementUtils.HEIGHTMAP,
              BiomeFilter.biome()
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.FLOWER_GOLDEN2_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.FLOWER_GOLDEN_WILDS),
              CountPlacement.of(5),
              RarityFilter.onAverageOnceEvery(1),
              InSquarePlacement.spread(),
              PlacementUtils.HEIGHTMAP,
              BiomeFilter.biome()
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.FLOWER_FIR_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.FLOWER_FIR_FOREST),
              CountPlacement.of(5),
              RarityFilter.onAverageOnceEvery(5),
              InSquarePlacement.spread(),
              PlacementUtils.HEIGHTMAP,
              BiomeFilter.biome()
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.FLOWER_CYPRESS_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.FLOWER_CYPRESS_FIELDS),
              CountPlacement.of(3),
              RarityFilter.onAverageOnceEvery(15),
              InSquarePlacement.spread(),
              PlacementUtils.HEIGHTMAP,
              BiomeFilter.biome()
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.FLOWER_CARNATION_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.FLOWER_CYPRESS_FIELDS),
              CountPlacement.of(5),
              RarityFilter.onAverageOnceEvery(2),
              InSquarePlacement.spread(),
              PlacementUtils.HEIGHTMAP,
              BiomeFilter.biome()
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.PATCH_SCORCHED_GRASS_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.PATCH_SCORCHED_GRASS),
              CountPlacement.of(4),
              RarityFilter.onAverageOnceEvery(2),
              InSquarePlacement.spread(),
              PlacementUtils.HEIGHTMAP,
              BiomeFilter.biome()
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.PATCH_TALL_SCORCHED_GRASS_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.PATCH_TALL_SCORCHED_GRASS),
              CountPlacement.of(3),
              RarityFilter.onAverageOnceEvery(2),
              InSquarePlacement.spread(),
              PlacementUtils.HEIGHTMAP,
              BiomeFilter.biome()
      );

      registerKey(context,
              HibiscusPlacedFeatureKeys.FLOWER_BLOOMING_DUNES_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.FLOWER_BLOOMING_DUNES),
              CountPlacement.of(5),
              RarityFilter.onAverageOnceEvery(2),
              InSquarePlacement.spread(),
              PlacementUtils.HEIGHTMAP,
              BiomeFilter.biome()
      );

      registerKey(context,
              HibiscusPlacedFeatureKeys.FLOWER_STRATIFIED_DESERT_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.FLOWER_STRATIFIED_DESERT),
              CountPlacement.of(3),
              RarityFilter.onAverageOnceEvery(15),
              InSquarePlacement.spread(),
              PlacementUtils.HEIGHTMAP,
              BiomeFilter.biome()
      );

      registerKey(context,
              HibiscusPlacedFeatureKeys.WISTERIA_WATER,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.WISTERIA_DELTA),
              CountOnEveryLayerPlacement.of(20),
              BiomeFilter.biome()
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.LAVENDER_WATER,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.WISTERIA_DELTA),
              CountOnEveryLayerPlacement.of(12),
              BiomeFilter.biome()
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.SWAMP_WATER,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.SWAMP_DELTA),
              CountOnEveryLayerPlacement.of(10),
              BiomeFilter.biome()
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.RIVER_WATER,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.RIVER_DELTA),
              CountOnEveryLayerPlacement.of(20),
              BiomeFilter.biome()
      );

      registerKey(context,
              HibiscusPlacedFeatureKeys.LARGE_REDWOOD_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.LARGE_REDWOOD_TREE_SPAWN),
              CountPlacement.of(3),
              InSquarePlacement.spread(),
              TREE_THRESHOLD,
              PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
              BiomeFilter.biome()
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.REDWOOD_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.REDWOOD_TREE_SPAWN),
              CountPlacement.of(7),
              InSquarePlacement.spread(),
              TREE_THRESHOLD,
              PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
              BiomeFilter.biome()
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.ASPEN_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.ASPEN_TREE_SPAWN),
              CountPlacement.of(4),
              InSquarePlacement.spread(),
              TREE_THRESHOLD,
              PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
              BiomeFilter.biome()
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.DENSE_CYPRESS_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.CYPRESS_TREE_SPAWN),
              CountPlacement.of(20),
              RarityFilter.onAverageOnceEvery(80),
              InSquarePlacement.spread(),
              TREE_THRESHOLD,
              PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
              BiomeFilter.biome()
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.CYPRESS_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.CYPRESS_TREE_SPAWN),
              CountPlacement.of(20),
              RarityFilter.onAverageOnceEvery(110),
              InSquarePlacement.spread(),
              TREE_THRESHOLD,
              PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
              BiomeFilter.biome()
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.FEW_ASPEN_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.ASPEN_TREE_SPAWN),
              CountPlacement.of(20),
              RarityFilter.onAverageOnceEvery(80),
              InSquarePlacement.spread(),
              TREE_THRESHOLD,
              PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
              BiomeFilter.biome()
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.FIR_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.FIR_TREE_SPAWN),
              CountPlacement.of(20),
              RarityFilter.onAverageOnceEvery(40),
              InSquarePlacement.spread(),
              TREE_THRESHOLD,
              PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
              BiomeFilter.biome()
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.DENSE_FIR_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.FIR_TREE_SPAWN),
              CountPlacement.of(6),
              InSquarePlacement.spread(),
              TREE_THRESHOLD,
              PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
              BiomeFilter.biome()
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.WILLOW_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.WILLOW_TREE_SPAWN),
              CountPlacement.of(2),
              InSquarePlacement.spread(),
              SurfaceWaterDepthFilter.forMaxDepth(2),
              PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
              BiomeFilter.biome()
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.SPRUCE_BUSH_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.SPRUCE_BUSH_SPAWN),
              CountPlacement.of(4),
              InSquarePlacement.spread(),
              TREE_THRESHOLD,
              PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
              BiomeFilter.biome()
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.REDWOOD_ROCK_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(MiscOverworldFeatures.FOREST_ROCK),
              CountPlacement.of(1),
              InSquarePlacement.spread(),
              TREE_THRESHOLD,
              PlacementUtils.HEIGHTMAP,
              BiomeFilter.biome()
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.WISTERIA_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.WISTERIA_SPAWN),
              CountPlacement.of(15),
              InSquarePlacement.spread(),
              TREE_THRESHOLD,
              PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
              BiomeFilter.biome()
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.SUGI_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.SUGI_SPAWN),
              CountPlacement.of(1),
              InSquarePlacement.spread(),
              TREE_THRESHOLD,
              PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
              BiomeFilter.biome()
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.OLIVE_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.OLIVE_TREE_SPAWN),
              CountPlacement.of(20),
              RarityFilter.onAverageOnceEvery(80),
              InSquarePlacement.spread(),
              TREE_THRESHOLD,
              PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
              BiomeFilter.biome()
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.JOSHUA_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.JOSHUA_TREE_SPAWN),
              CountPlacement.of(20),
              RarityFilter.onAverageOnceEvery(80),
              InSquarePlacement.spread(),
              TREE_THRESHOLD,
              PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
              BiomeFilter.biome()
      );

      registerKey(context,
              HibiscusPlacedFeatureKeys.OAK_BUSH_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.OAK_BUSH_SPAWN),
              CountPlacement.of(1),
              InSquarePlacement.spread(),
              TREE_THRESHOLD,
              PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
              BiomeFilter.biome()
      );

      registerKey(context,
              HibiscusPlacedFeatureKeys.CUSTOM_FANCY_OAK_TREE_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.FANCY_OAK_TREE_SPAWN),
              CountPlacement.of(1),
              InSquarePlacement.spread(),
              TREE_THRESHOLD,
              PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
              BiomeFilter.biome()
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.CUSTOM_FANCY_OAK_TREE2_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.FANCY_OAK_TREE_SPAWN),
              RarityFilter.onAverageOnceEvery(35),
              CountPlacement.of(1),
              InSquarePlacement.spread(),
              TREE_THRESHOLD,
              PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
              BiomeFilter.biome()
      );
      registerKey(context,
              HibiscusPlacedFeatureKeys.CATTAILS,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.CATTAILS),
              RarityFilter.onAverageOnceEvery(1),
              InSquarePlacement.spread(),
              PlacementUtils.HEIGHTMAP,
              BiomeFilter.biome()
      );

      registerKey(context,
              HibiscusPlacedFeatureKeys.ROOTED_DESERT_TURNIP,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatureKeys.ROOTED_DESERT_TURNIP),
              CountPlacement.of(UniformInt.of(1, 2)),
              InSquarePlacement.spread(),
              RarityFilter.onAverageOnceEvery(30),
              HeightRangePlacement.uniform(VerticalAnchor.absolute(30), VerticalAnchor.absolute(226)),
              EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(), 12),
              RandomOffsetPlacement.vertical(ConstantInt.of(-1)),
              BiomeFilter.biome()
      );
   }

   private static void registerKey(BootstapContext <PlacedFeature> context, ResourceKey <PlacedFeature> key, Holder <ConfiguredFeature <?, ?>> configuration, List <PlacementModifier> modifiers) {
      context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
   }

   private static <FC extends FeatureConfiguration, F extends Feature <FC>> void registerKey(BootstapContext <PlacedFeature> context, ResourceKey <PlacedFeature> key, Holder <ConfiguredFeature <?, ?>> configuration, PlacementModifier... modifiers) {
      registerKey(context, key, configuration, List.of(modifiers));
   }

}
