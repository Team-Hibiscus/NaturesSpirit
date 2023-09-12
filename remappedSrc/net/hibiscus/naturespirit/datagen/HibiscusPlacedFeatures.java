package net.hibiscus.naturespirit.datagen;


import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusWoods;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.MiscOverworldFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountOnEveryLayerPlacement;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.EnvironmentScanPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.NoiseBasedCountPlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter;
import java.util.List;

public class HibiscusPlacedFeatures {


   public static final ResourceKey <PlacedFeature> LARGE_REDWOOD_CHECKED = registerKey("large_redwood_checked");
   public static final ResourceKey <PlacedFeature> REDWOOD_CHECKED = registerKey("redwood_checked");
   public static final ResourceKey <PlacedFeature> ASPEN_CHECKED = registerKey("aspen_checked");
   public static final ResourceKey <PlacedFeature> ASPEN_BEES_CHECKED = registerKey("aspen_bees_checked");
   public static final ResourceKey <PlacedFeature> RED_MAPLE_CHECKED = registerKey("red_maple_checked");
   public static final ResourceKey <PlacedFeature> ORANGE_MAPLE_CHECKED = registerKey("orange_maple_checked");
   public static final ResourceKey <PlacedFeature> YELLOW_MAPLE_CHECKED = registerKey("yellow_maple_checked");
   public static final ResourceKey <PlacedFeature> CYPRESS_CHECKED = registerKey("cypress_checked");
   public static final ResourceKey <PlacedFeature> FIR_CHECKED = registerKey("fir_checked");
   public static final ResourceKey <PlacedFeature> WILLOW_CHECKED = registerKey("willow_checked");
   public static final ResourceKey <PlacedFeature> WILLOW_PLACED = registerKey("willow_placed");
   public static final ResourceKey <PlacedFeature> WHITE_WISTERIA_CHECKED = registerKey("white_wisteria_checked");
   public static final ResourceKey <PlacedFeature> BLUE_WISTERIA_CHECKED = registerKey("blue_wisteria_checked");
   public static final ResourceKey <PlacedFeature> PINK_WISTERIA_CHECKED = registerKey("pink_wisteria_checked");
   public static final ResourceKey <PlacedFeature> PURPLE_WISTERIA_CHECKED = registerKey("purple_wisteria_checked");
   public static final ResourceKey <PlacedFeature> SUGI_CHECKED = registerKey("sugi_checked");
   public static final ResourceKey <PlacedFeature> OLIVE_CHECKED = registerKey("olive_checked");
   public static final ResourceKey <PlacedFeature> JOSHUA_CHECKED = registerKey("joshua_checked");

   public static final ResourceKey <PlacedFeature> OAK_BUSH_CHECKED = registerKey("oak_bush_checked");
   public static final ResourceKey <PlacedFeature> SPRUCE_BUSH_CHECKED = registerKey("spruce_bush_checked");

   public static final ResourceKey <PlacedFeature> FLOWER_WISTERIA_PLACED = registerKey("flower_wisteria_placed");
   public static final ResourceKey <PlacedFeature> FLOWER_SUGI_PLACED = registerKey("flower_sugi_placed");
   public static final ResourceKey <PlacedFeature> FLOWER_REDWOOD_PLACED = registerKey("flower_redwood_placed");
   public static final ResourceKey <PlacedFeature> FLOWER_FIR_PLACED = registerKey("flower_fir_placed");
   public static final ResourceKey <PlacedFeature> FLOWER_LAVENDER_PLACED = registerKey("flower_lavender_placed");
   public static final ResourceKey <PlacedFeature> FLOWER_FOXGLOVE_PLACED = registerKey("flower_foxglove_placed");
   public static final ResourceKey <PlacedFeature> FLOWER_RIVER_PLACED = registerKey("flower_river_placed");
   public static final ResourceKey <PlacedFeature> FLOWER_GOLDEN_PLACED = registerKey("flower_golden_placed");
   public static final ResourceKey <PlacedFeature> FLOWER_GOLDEN2_PLACED = registerKey("flower_golden2_placed");
   public static final ResourceKey <PlacedFeature> FLOWER_CYPRESS_PLACED = registerKey("flower_cypress_placed");
   public static final ResourceKey <PlacedFeature> FLOWER_CARNATION_PLACED = registerKey("flower_carnation_placed");
   public static final ResourceKey <PlacedFeature> PATCH_SCORCHED_GRASS_PLACED = registerKey("patch_scorched_grass_placed");
   public static final ResourceKey <PlacedFeature> PATCH_TALL_SCORCHED_GRASS_PLACED = registerKey("patch_tall_scorched_grass_placed");
   public static final ResourceKey <PlacedFeature> FLOWER_BLOOMING_DUNES_PLACED = registerKey("flower_blooming_dunes_placed");
   public static final ResourceKey <PlacedFeature> FLOWER_STRATIFIED_DESERT_PLACED = registerKey("flower_stratified_desert_placed");

   public static final ResourceKey <PlacedFeature> WISTERIA_WATER = registerKey("wisteria_water_placed");
   public static final ResourceKey <PlacedFeature> LAVENDER_WATER = registerKey("lavender_water_placed");
   public static final ResourceKey <PlacedFeature> SWAMP_WATER = registerKey("swamp_water_placed");
   public static final ResourceKey <PlacedFeature> MARSH_WATER = registerKey("marsh_water_placed");
   public static final ResourceKey <PlacedFeature> RIVER_WATER = registerKey("river_water_placed");

   public static final ResourceKey <PlacedFeature> LARGE_REDWOOD_PLACED = registerKey("large_redwood_placed");
   public static final ResourceKey <PlacedFeature> REDWOOD_PLACED = registerKey("redwood_placed");
   public static final ResourceKey <PlacedFeature> ASPEN_PLACED = registerKey("aspen_placed");
   public static final ResourceKey <PlacedFeature> MAPLE_PLACED = registerKey("maple_placed");
   public static final ResourceKey <PlacedFeature> MAPLE2_PLACED = registerKey("maple2_placed");
   public static final ResourceKey <PlacedFeature> FEW_ASPEN_PLACED = registerKey("few_aspen_placed");
   public static final ResourceKey <PlacedFeature> DENSE_CYPRESS_PLACED = registerKey("dense_cypress_placed");
   public static final ResourceKey <PlacedFeature> CYPRESS_PLACED = registerKey("cypress_placed");
   public static final ResourceKey <PlacedFeature> FIR_PLACED = registerKey("fir_placed");
   public static final ResourceKey <PlacedFeature> DENSE_FIR_PLACED = registerKey("dense_fir_placed");
   public static final ResourceKey <PlacedFeature> SPRUCE_BUSH_PLACED = registerKey("spruce_bush_placed");
   public static final ResourceKey <PlacedFeature> REDWOOD_ROCK_PLACED = registerKey("redwood_rock_placed");
   public static final ResourceKey <PlacedFeature> WISTERIA_PLACED = registerKey("wisteria_placed");
   public static final ResourceKey <PlacedFeature> SUGI_PLACED = registerKey("sugi_placed");
   public static final ResourceKey <PlacedFeature> OLIVE_PLACED = registerKey("olive_placed");
   public static final ResourceKey <PlacedFeature> JOSHUA_PLACED = registerKey("joshua_placed");

   public static final ResourceKey <PlacedFeature> OAK_BUSH_PLACED = registerKey("oak_bush_placed");
   public static final ResourceKey <PlacedFeature> CUSTOM_FANCY_OAK_TREE_PLACED = registerKey("custom_fancy_oak_tree_placed");
   public static final ResourceKey <PlacedFeature> CUSTOM_FANCY_OAK_TREE2_PLACED = registerKey("custom_fancy_oak_tree2_placed");
   public static final ResourceKey <PlacedFeature> CATTAILS = registerKey("cattails_placed");
   public static final ResourceKey <PlacedFeature> LOTUS_PLANT_PLACED = registerKey("lotus_plant_placed");
   public static final ResourceKey <PlacedFeature> ROOTED_DESERT_TURNIP = registerKey("rooted_desert_turnip_placed");

   private static final PlacementModifier TREE_THRESHOLD = SurfaceWaterDepthFilter.forMaxDepth(0);

   public static void bootstrap(BootstapContext <PlacedFeature> context) {
      var configuredFeatureRegistryEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);


      registerKey(context,
              LARGE_REDWOOD_CHECKED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.LARGE_REDWOOD_TREE),
              List.of(PlacementUtils.filteredByBlockSurvival(HibiscusWoods.REDWOOD.getSapling()))
      );
      registerKey(context, REDWOOD_CHECKED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.REDWOOD_TREE), PlacementUtils.filteredByBlockSurvival(HibiscusWoods.REDWOOD.getSapling()));
      registerKey(context, ASPEN_CHECKED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.ASPEN_TREE), PlacementUtils.filteredByBlockSurvival(HibiscusWoods.ASPEN.getSapling()));
      registerKey(context,
              ASPEN_BEES_CHECKED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.ASPEN_TREE_BEES),
              PlacementUtils.filteredByBlockSurvival(HibiscusWoods.ASPEN.getSapling())
      );
      registerKey(context,
              RED_MAPLE_CHECKED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.RED_MAPLE_TREE),
              PlacementUtils.filteredByBlockSurvival(HibiscusWoods.MAPLE.getRedMapleSapling())
      );
      registerKey(context,
              ORANGE_MAPLE_CHECKED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.ORANGE_MAPLE_TREE),
              PlacementUtils.filteredByBlockSurvival(HibiscusWoods.MAPLE.getOrangeMapleSapling())
      );
      registerKey(context,
              YELLOW_MAPLE_CHECKED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.YELLOW_MAPLE_TREE),
              PlacementUtils.filteredByBlockSurvival(HibiscusWoods.MAPLE.getYellowMapleSapling())
      );
      registerKey(context, CYPRESS_CHECKED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.CYPRESS_TREE), PlacementUtils.filteredByBlockSurvival(HibiscusWoods.CYPRESS.getSapling()));
      registerKey(context, JOSHUA_CHECKED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.JOSHUA_TREE), PlacementUtils.filteredByBlockSurvival(HibiscusWoods.JOSHUA.getSapling()));
      registerKey(context, FIR_CHECKED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.FIR_TREE), PlacementUtils.filteredByBlockSurvival(HibiscusWoods.FIR.getSapling()));
      registerKey(context, WILLOW_CHECKED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.WILLOW_TREE), PlacementUtils.filteredByBlockSurvival(HibiscusWoods.WILLOW.getSapling()));
      registerKey(context,
              WHITE_WISTERIA_CHECKED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.WHITE_WISTERIA_TREE),
              PlacementUtils.filteredByBlockSurvival(HibiscusWoods.WISTERIA.getWhiteWisteriaSapling())
      );
      registerKey(context,
              BLUE_WISTERIA_CHECKED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.BLUE_WISTERIA_TREE),
              PlacementUtils.filteredByBlockSurvival(HibiscusWoods.WISTERIA.getBlueWisteriaSapling())
      );
      registerKey(context,
              PINK_WISTERIA_CHECKED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.PINK_WISTERIA_TREE),
              PlacementUtils.filteredByBlockSurvival(HibiscusWoods.WISTERIA.getPinkWisteriaSapling())
      );
      registerKey(context,
              PURPLE_WISTERIA_CHECKED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.PURPLE_WISTERIA_TREE),
              PlacementUtils.filteredByBlockSurvival(HibiscusWoods.WISTERIA.getPurpleWisteriaSapling())
      );
      registerKey(context, SUGI_CHECKED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.SUGI_TREE), PlacementUtils.filteredByBlockSurvival(HibiscusWoods.SUGI.getSapling()));
      registerKey(context, OLIVE_CHECKED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.OLIVE_TREE), PlacementUtils.filteredByBlockSurvival(HibiscusWoods.OLIVE.getSapling()));

      registerKey(context, OAK_BUSH_CHECKED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.OAK_BUSH), PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING));
      registerKey(context, SPRUCE_BUSH_CHECKED, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.SPRUCE_BUSH), PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING));

      registerKey(context,
              FLOWER_WISTERIA_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.FLOWER_WISTERIA_FOREST),
              CountPlacement.of(3),
              RarityFilter.onAverageOnceEvery(2),
              InSquarePlacement.spread(),
              PlacementUtils.HEIGHTMAP,
              BiomeFilter.biome()
      );
      registerKey(context,
              FLOWER_SUGI_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.FLOWER_SUGI_FOREST),
              CountPlacement.of(3),
              RarityFilter.onAverageOnceEvery(2),
              InSquarePlacement.spread(),
              PlacementUtils.HEIGHTMAP,
              BiomeFilter.biome()
      );
      registerKey(context,
              FLOWER_REDWOOD_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.FLOWER_REDWOOD_FOREST),
              CountPlacement.of(3),
              RarityFilter.onAverageOnceEvery(10),
              InSquarePlacement.spread(),
              PlacementUtils.HEIGHTMAP,
              BiomeFilter.biome()
      );
      registerKey(context,
              FLOWER_LAVENDER_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.FLOWER_LAVENDER_FIELD),
              CountPlacement.of(8),
              RarityFilter.onAverageOnceEvery(2),
              InSquarePlacement.spread(),
              PlacementUtils.HEIGHTMAP,
              BiomeFilter.biome()
      );
      registerKey(context,
              FLOWER_FOXGLOVE_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.FLOWER_FOXGLOVE_FIELD),
              CountPlacement.of(8),
              RarityFilter.onAverageOnceEvery(1),
              InSquarePlacement.spread(),
              PlacementUtils.HEIGHTMAP,
              BiomeFilter.biome()
      );
      registerKey(context,
              FLOWER_RIVER_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.FLOWER_ERODED_RIVER),
              CountPlacement.of(5),
              RarityFilter.onAverageOnceEvery(2),
              InSquarePlacement.spread(),
              PlacementUtils.HEIGHTMAP,
              BiomeFilter.biome()
      );
      registerKey(context,
              FLOWER_GOLDEN_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.FLOWER_GOLDEN_WILDS),
              CountPlacement.of(3),
              RarityFilter.onAverageOnceEvery(5),
              InSquarePlacement.spread(),
              PlacementUtils.HEIGHTMAP,
              BiomeFilter.biome()
      );
      registerKey(context,
              FLOWER_GOLDEN2_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.FLOWER_GOLDEN_WILDS),
              CountPlacement.of(5),
              RarityFilter.onAverageOnceEvery(1),
              InSquarePlacement.spread(),
              PlacementUtils.HEIGHTMAP,
              BiomeFilter.biome()
      );
      registerKey(context,
              FLOWER_FIR_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.FLOWER_FIR_FOREST),
              CountPlacement.of(5),
              RarityFilter.onAverageOnceEvery(5),
              InSquarePlacement.spread(),
              PlacementUtils.HEIGHTMAP,
              BiomeFilter.biome()
      );
      registerKey(context,
              FLOWER_CYPRESS_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.FLOWER_CYPRESS_FIELDS),
              CountPlacement.of(3),
              RarityFilter.onAverageOnceEvery(15),
              InSquarePlacement.spread(),
              PlacementUtils.HEIGHTMAP,
              BiomeFilter.biome()
      );
      registerKey(context,
              FLOWER_CARNATION_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.FLOWER_CYPRESS_FIELDS),
              CountPlacement.of(5),
              RarityFilter.onAverageOnceEvery(2),
              InSquarePlacement.spread(),
              PlacementUtils.HEIGHTMAP,
              BiomeFilter.biome()
      );
      registerKey(context,
              PATCH_SCORCHED_GRASS_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.PATCH_SCORCHED_GRASS),
              CountPlacement.of(4),
              RarityFilter.onAverageOnceEvery(2),
              InSquarePlacement.spread(),
              PlacementUtils.HEIGHTMAP,
              BiomeFilter.biome()
      );
      registerKey(context,
              PATCH_TALL_SCORCHED_GRASS_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.PATCH_TALL_SCORCHED_GRASS),
              CountPlacement.of(3),
              RarityFilter.onAverageOnceEvery(2),
              InSquarePlacement.spread(),
              PlacementUtils.HEIGHTMAP,
              BiomeFilter.biome()
      );

      registerKey(context,
              FLOWER_BLOOMING_DUNES_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.FLOWER_BLOOMING_DUNES),
              CountPlacement.of(5),
              RarityFilter.onAverageOnceEvery(2),
              InSquarePlacement.spread(),
              PlacementUtils.HEIGHTMAP,
              BiomeFilter.biome()
      );

      registerKey(context,
              FLOWER_STRATIFIED_DESERT_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.FLOWER_STRATIFIED_DESERT),
              CountPlacement.of(3),
              RarityFilter.onAverageOnceEvery(15),
              InSquarePlacement.spread(),
              PlacementUtils.HEIGHTMAP,
              BiomeFilter.biome()
      );

      registerKey(context,
              WISTERIA_WATER,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.WISTERIA_DELTA),
              CountOnEveryLayerPlacement.of(20),
              BiomeFilter.biome()
      );
      registerKey(context,
              LAVENDER_WATER,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.WISTERIA_DELTA),
              CountOnEveryLayerPlacement.of(12),
              BiomeFilter.biome()
      );
      registerKey(context, SWAMP_WATER, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.SWAMP_DELTA), CountOnEveryLayerPlacement.of(10), BiomeFilter.biome());
      registerKey(context, MARSH_WATER, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.MARSH_DELTA), CountOnEveryLayerPlacement.of(20), BiomeFilter.biome());
      registerKey(context, RIVER_WATER, configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.RIVER_DELTA), CountOnEveryLayerPlacement.of(20), BiomeFilter.biome());

      registerKey(context,
              LARGE_REDWOOD_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.LARGE_REDWOOD_TREE_SPAWN),
              CountPlacement.of(3),
              InSquarePlacement.spread(),
              TREE_THRESHOLD,
              PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
              BiomeFilter.biome()
      );
      registerKey(context,
              REDWOOD_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.REDWOOD_TREE_SPAWN),
              CountPlacement.of(7),
              InSquarePlacement.spread(),
              TREE_THRESHOLD,
              PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
              BiomeFilter.biome()
      );
      registerKey(context,
              ASPEN_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.ASPEN_TREE_SPAWN),
              CountPlacement.of(4),
              InSquarePlacement.spread(),
              TREE_THRESHOLD,
              PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
              BiomeFilter.biome()
      );
      registerKey(context,
              DENSE_CYPRESS_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.CYPRESS_TREE_SPAWN),
              CountPlacement.of(20),
              RarityFilter.onAverageOnceEvery(80),
              InSquarePlacement.spread(),
              TREE_THRESHOLD,
              PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
              BiomeFilter.biome()
      );
      registerKey(context,
              CYPRESS_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.CYPRESS_TREE_SPAWN),
              CountPlacement.of(20),
              RarityFilter.onAverageOnceEvery(110),
              InSquarePlacement.spread(),
              TREE_THRESHOLD,
              PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
              BiomeFilter.biome()
      );
      registerKey(context,
              FEW_ASPEN_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.ASPEN_TREE_SPAWN),
              CountPlacement.of(20),
              RarityFilter.onAverageOnceEvery(80),
              InSquarePlacement.spread(),
              TREE_THRESHOLD,
              PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
              BiomeFilter.biome()
      );
      registerKey(context,
              MAPLE2_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.MAPLE_SPAWN),
              CountPlacement.of(7),
              InSquarePlacement.spread(),
              TREE_THRESHOLD,
              PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
              BiomeFilter.biome()
      );
      registerKey(context,
              MAPLE_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.MAPLE_SPAWN),
              NoiseBasedCountPlacement.of(3, 100, 0),
              InSquarePlacement.spread(),
              TREE_THRESHOLD,
              PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
              BiomeFilter.biome()
      );
      registerKey(context,
              FIR_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.FIR_TREE_SPAWN),
              NoiseBasedCountPlacement.of(3, 200, 0),
              InSquarePlacement.spread(),
              TREE_THRESHOLD,
              PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
              BiomeFilter.biome()
      );
      registerKey(context,
              DENSE_FIR_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.FIR_TREE_SPAWN),
              CountPlacement.of(6),
              InSquarePlacement.spread(),
              TREE_THRESHOLD,
              PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
              BiomeFilter.biome()
      );
      registerKey(context,
              WILLOW_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.WILLOW_TREE_SPAWN),
              CountPlacement.of(2),
              InSquarePlacement.spread(),
              SurfaceWaterDepthFilter.forMaxDepth(2),
              PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
              BiomeFilter.biome()
      );
      registerKey(context,
              SPRUCE_BUSH_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.SPRUCE_BUSH_SPAWN),
              CountPlacement.of(4),
              InSquarePlacement.spread(),
              TREE_THRESHOLD,
              PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
              BiomeFilter.biome()
      );
      registerKey(context,
              REDWOOD_ROCK_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(MiscOverworldFeatures.FOREST_ROCK),
              CountPlacement.of(1),
              InSquarePlacement.spread(),
              TREE_THRESHOLD,
              PlacementUtils.HEIGHTMAP,
              BiomeFilter.biome()
      );
      registerKey(context,
              WISTERIA_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.WISTERIA_SPAWN),
              CountPlacement.of(15),
              InSquarePlacement.spread(),
              TREE_THRESHOLD,
              PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
              BiomeFilter.biome()
      );
      registerKey(context,
              SUGI_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.SUGI_SPAWN),
              CountPlacement.of(1),
              InSquarePlacement.spread(),
              TREE_THRESHOLD,
              PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
              BiomeFilter.biome()
      );
      registerKey(context,
              OLIVE_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.OLIVE_TREE_SPAWN),
              CountPlacement.of(20),
              RarityFilter.onAverageOnceEvery(80),
              InSquarePlacement.spread(),
              TREE_THRESHOLD,
              PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
              BiomeFilter.biome()
      );
      registerKey(context,
              JOSHUA_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.JOSHUA_TREE_SPAWN),
              CountPlacement.of(20),
              RarityFilter.onAverageOnceEvery(80),
              InSquarePlacement.spread(),
              TREE_THRESHOLD,
              PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
              BiomeFilter.biome()
      );

      registerKey(context,
              OAK_BUSH_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.OAK_BUSH_SPAWN),
              CountPlacement.of(1),
              InSquarePlacement.spread(),
              TREE_THRESHOLD,
              PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
              BiomeFilter.biome()
      );

      registerKey(context,
              CUSTOM_FANCY_OAK_TREE_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.FANCY_OAK_TREE_SPAWN),
              CountPlacement.of(1),
              InSquarePlacement.spread(),
              TREE_THRESHOLD,
              PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
              BiomeFilter.biome()
      );
      registerKey(context,
              CUSTOM_FANCY_OAK_TREE2_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.FANCY_OAK_TREE_SPAWN),
              RarityFilter.onAverageOnceEvery(35),
              CountPlacement.of(1),
              InSquarePlacement.spread(),
              TREE_THRESHOLD,
              PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
              BiomeFilter.biome()
      );
      registerKey(context,
              CATTAILS,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.CATTAILS),
              RarityFilter.onAverageOnceEvery(1),
              InSquarePlacement.spread(),
              PlacementUtils.HEIGHTMAP,
              BiomeFilter.biome()
      );
      registerKey(context,
              LOTUS_PLANT_PLACED,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.LOTUS_PLANT),
              RarityFilter.onAverageOnceEvery(15),
              InSquarePlacement.spread(),
              PlacementUtils.HEIGHTMAP,
              BiomeFilter.biome()
      );


      registerKey(context,
              ROOTED_DESERT_TURNIP,
              configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.ROOTED_DESERT_TURNIP),
              CountPlacement.of(UniformInt.of(1, 2)),
              InSquarePlacement.spread(),
              RarityFilter.onAverageOnceEvery(30),
              HeightRangePlacement.uniform(VerticalAnchor.absolute(30), VerticalAnchor.absolute(226)),
              EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(), 12),
              RandomOffsetPlacement.vertical(ConstantInt.of(-1)),
              BiomeFilter.biome()
      );
   }

   public static ResourceKey <PlacedFeature> registerKey(String name) {
      return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(NatureSpirit.MOD_ID, name));
   }

   private static void registerKey(BootstapContext <PlacedFeature> context, ResourceKey <PlacedFeature> key, Holder <ConfiguredFeature <?, ?>> configuration, List <PlacementModifier> modifiers) {
      context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
   }

   private static <FC extends FeatureConfiguration, F extends Feature <FC>> void registerKey(BootstapContext <PlacedFeature> context, ResourceKey <PlacedFeature> key, Holder <ConfiguredFeature <?, ?>> configuration, PlacementModifier... modifiers) {
      registerKey(context, key, configuration, List.of(modifiers));
   }

}
