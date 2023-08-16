package net.hibiscus.naturespirit.world;

import com.google.common.collect.ImmutableList;
import net.hibiscus.naturespirit.Constants;
import net.hibiscus.naturespirit.blocks.DesertPlantBlock;
import net.hibiscus.naturespirit.blocks.HibiscusBlocks;
import net.hibiscus.naturespirit.blocks.WisteriaVine;
import net.hibiscus.naturespirit.util.HibiscusTags;
import net.hibiscus.naturespirit.world.feature.HibiscusSimpleBlockStateProvider;
import net.hibiscus.naturespirit.world.feature.TurnipRootFeatureConfig;
import net.hibiscus.naturespirit.world.feature.foliage_placer.*;
import net.hibiscus.naturespirit.world.feature.tree_decorator.WisteriaVinesTreeDecorator;
import net.hibiscus.naturespirit.world.feature.trunk.OliveTrunkPlacer;
import net.hibiscus.naturespirit.world.feature.trunk.SugiTrunkPlacer;
import net.hibiscus.naturespirit.world.feature.trunk.WisteriaTrunkPlacer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BushFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaPineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.RandomSpreadFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.NoiseProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RandomizedIntStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AlterGroundDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraft.world.level.material.Fluids;

import java.util.List;
import java.util.OptionalInt;

import static net.hibiscus.naturespirit.util.HibiscusWorldGen.*;

public class HibiscusConfiguredFeatures {

   public static final ResourceKey <ConfiguredFeature <?, ?>> WISTERIA_DELTA = registerKey("water_delta");
   public static final ResourceKey <ConfiguredFeature <?, ?>> SWAMP_DELTA = registerKey("swamp_delta");
   public static final ResourceKey <ConfiguredFeature <?, ?>> RIVER_DELTA = registerKey("river_delta");

   public static final ResourceKey <ConfiguredFeature <?, ?>> LARGE_REDWOOD_TREE = registerKey("large_redwood_tree");
   public static final ResourceKey <ConfiguredFeature <?, ?>> REDWOOD_TREE = registerKey("redwood_tree");
   public static final ResourceKey <ConfiguredFeature <?, ?>> LARGE_REDWOOD_TREE_SPAWN = registerKey(
           "large_redwood_tree_spawn");
   public static final ResourceKey <ConfiguredFeature <?, ?>> REDWOOD_TREE_SPAWN = registerKey("redwood_tree_spawn");
   public static final ResourceKey <ConfiguredFeature <?, ?>> WILLOW_TREE = registerKey("willow_tree");
   public static final ResourceKey <ConfiguredFeature <?, ?>> WILLOW_TREE_SPAWN = registerKey("willow_tree_spawn");
   public static final ResourceKey <ConfiguredFeature <?, ?>> WHITE_WISTERIA_TREE = registerKey("white_wisteria_tree");
   public static final ResourceKey <ConfiguredFeature <?, ?>> BLUE_WISTERIA_TREE = registerKey("blue_wisteria_tree");
   public static final ResourceKey <ConfiguredFeature <?, ?>> PURPLE_WISTERIA_TREE = registerKey("purple_wisteria_tree");
   public static final ResourceKey <ConfiguredFeature <?, ?>> PINK_WISTERIA_TREE = registerKey("pink_wisteria_tree");
   public static final ResourceKey <ConfiguredFeature <?, ?>> ASPEN_TREE = registerKey("aspen_tree");
   public static final ResourceKey <ConfiguredFeature <?, ?>> ASPEN_TREE_BEES = registerKey("aspen_tree_bees");
   public static final ResourceKey <ConfiguredFeature <?, ?>> ASPEN_TREE_SPAWN = registerKey("aspen_tree_spawn");
   public static final ResourceKey <ConfiguredFeature <?, ?>> FIR_TREE = registerKey("fir_tree");
   public static final ResourceKey <ConfiguredFeature <?, ?>> FIR_TREE_SPAWN = registerKey("fir_tree_spawn");
   public static final ResourceKey <ConfiguredFeature <?, ?>> WISTERIA_SPAWN = registerKey("wisteria_spawn");
   public static final ResourceKey <ConfiguredFeature <?, ?>> SUGI_TREE = registerKey("sugi_tree");
   public static final ResourceKey <ConfiguredFeature <?, ?>> SUGI_SPAWN = registerKey("sugi_spawn");
   public static final ResourceKey <ConfiguredFeature <?, ?>> CYPRESS_TREE = registerKey("cypress_tree");
   public static final ResourceKey <ConfiguredFeature <?, ?>> CYPRESS_TREE_SPAWN = registerKey("cypress_tree_spawn");
   public static final ResourceKey <ConfiguredFeature <?, ?>> OLIVE_TREE = registerKey("olive_tree");
   public static final ResourceKey <ConfiguredFeature <?, ?>> OLIVE_TREE_SPAWN = registerKey("olive_tree_spawn");
   public static final ResourceKey <ConfiguredFeature <?, ?>> JOSHUA_TREE = registerKey("joshua_tree");
   public static final ResourceKey <ConfiguredFeature <?, ?>> JOSHUA_TREE_SPAWN = registerKey("joshua_tree_spawn");

   public static final ResourceKey <ConfiguredFeature <?, ?>> OAK_BUSH = registerKey("oak_bush");
   public static final ResourceKey <ConfiguredFeature <?, ?>> SPRUCE_BUSH = registerKey("spruce_bush");
   public static final ResourceKey <ConfiguredFeature <?, ?>> OAK_BUSH_SPAWN = registerKey("oak_bush_spawn");
   public static final ResourceKey <ConfiguredFeature <?, ?>> SPRUCE_BUSH_SPAWN = registerKey("spruce_bush_spawn");
   public static final ResourceKey <ConfiguredFeature <?, ?>> FANCY_OAK_TREE_SPAWN = registerKey("custom_fancy_oak_tree_spawn");

   public static final ResourceKey <ConfiguredFeature <?, ?>> FLOWER_WISTERIA_FOREST = registerKey("flower_wisteria_forest");
   public static final ResourceKey <ConfiguredFeature <?, ?>> FLOWER_SUGI_FOREST = registerKey("flower_sugi_forest");
   public static final ResourceKey <ConfiguredFeature <?, ?>> FLOWER_REDWOOD_FOREST = registerKey("flower_redwood_forest");
   public static final ResourceKey <ConfiguredFeature <?, ?>> FLOWER_LAVENDER_FIELD = registerKey("flower_lavender_field");
   public static final ResourceKey <ConfiguredFeature <?, ?>> FLOWER_FOXGLOVE_FIELD = registerKey("flower_foxglove_field");
   public static final ResourceKey <ConfiguredFeature <?, ?>> FLOWER_ERODED_RIVER = registerKey("flower_eroded_river");
   public static final ResourceKey <ConfiguredFeature <?, ?>> FLOWER_GOLDEN_WILDS = registerKey("flower_golden_wilds");
   public static final ResourceKey <ConfiguredFeature <?, ?>> FLOWER_FIR_FOREST = registerKey("flower_fir_forest");
   public static final ResourceKey <ConfiguredFeature <?, ?>> FLOWER_CYPRESS_FIELDS = registerKey("flower_cypress_fields");
   public static final ResourceKey <ConfiguredFeature <?, ?>> PATCH_SCORCHED_GRASS = registerKey("patch_scorched_grass");
   public static final ResourceKey <ConfiguredFeature <?, ?>> PATCH_TALL_SCORCHED_GRASS = registerKey("patch_tall_scorched_grass");
   public static final ResourceKey <ConfiguredFeature <?, ?>> FLOWER_BLOOMING_DUNES = registerKey("flower_blooming_dunes");
   public static final ResourceKey <ConfiguredFeature <?, ?>> FLOWER_STRATIFIED_DESERT = registerKey("flower_stratified_desert");
   public static final ResourceKey <ConfiguredFeature <?, ?>> CATTAILS = registerKey("cattails");
   public static final ResourceKey <ConfiguredFeature <?, ?>> DESERT_TURNIP_STEM = registerKey("desert_turnip_stem");
   public static final ResourceKey <ConfiguredFeature <?, ?>> ROOTED_DESERT_TURNIP = registerKey("rooted_desert_turnip");


   public static void bootstrap(BootstapContext <ConfiguredFeature <?, ?>> context) {
      var placedFeatureRegistryEntryLookup = context.lookup(Registries.PLACED_FEATURE);
      HolderGetter <Block> holderGetter = context.lookup(Registries.BLOCK);

      register(context,
              REDWOOD_TREE,
              Feature.TREE,
              new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(HibiscusBlocks.REDWOOD[2]),
                      new StraightTrunkPlacer(12, 1, 4),
                      BlockStateProvider.simple(HibiscusBlocks.REDWOOD_LEAVES),
                      new SpruceFoliagePlacer(UniformInt.of(1, 3),
                              UniformInt.of(1, 2),
                              UniformInt.of(14, 15)
                      ),
                      new TwoLayersFeatureSize(2, 0, 2)
              ).ignoreVines().build()
      );

      register(context,
              REDWOOD_TREE_SPAWN,
              Feature.RANDOM_SELECTOR,
              new RandomFeatureConfiguration(
                      List.of(new WeightedPlacedFeature(placedFeatureRegistryEntryLookup.getOrThrow(
                              HibiscusPlacedFeatures.REDWOOD_CHECKED),
                              0.5f
                      )),
                      placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.REDWOOD_CHECKED)
              )
      );

      register(context,
              ASPEN_TREE,
              Feature.TREE,
              new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(HibiscusBlocks.ASPEN[2]),
                      new StraightTrunkPlacer(14, 2, 5),
                      BlockStateProvider.simple(HibiscusBlocks.ASPEN_LEAVES),
                      new AspenFoliagePlacer(UniformInt.of(2, 2),
                              UniformInt.of(2, 3),
                              UniformInt.of(4, 18)
                      ),
                      new TwoLayersFeatureSize(1, 0, 1)
              ).ignoreVines().build()
      );
      register(context, ASPEN_TREE_BEES, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(
              HibiscusBlocks.ASPEN[2]),
              new StraightTrunkPlacer(14, 2, 5),
              BlockStateProvider.simple(HibiscusBlocks.ASPEN_LEAVES),
              new AspenFoliagePlacer(UniformInt.of(2, 2),
                      UniformInt.of(2, 3),
                      UniformInt.of(4, 18)
              ),
              new TwoLayersFeatureSize(1, 0, 1)
      ).ignoreVines().decorators(List.of(new BeehiveDecorator(1.0F))).build());

      register(context,
              ASPEN_TREE_SPAWN,
              Feature.RANDOM_SELECTOR,
              new RandomFeatureConfiguration(
                      List.of(new WeightedPlacedFeature(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.ASPEN_BEES_CHECKED),
                              0.01f
                      )),
                      placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.ASPEN_CHECKED)
              )
      );

      register(context,
              CYPRESS_TREE,
              Feature.TREE,
              new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(HibiscusBlocks.CYPRESS[2]),
                      new StraightTrunkPlacer(11, 1, 2),
                      BlockStateProvider.simple(HibiscusBlocks.CYPRESS_LEAVES),
                      new CypressFoliagePlacer(UniformInt.of(2, 2),
                              UniformInt.of(3, 3),
                              UniformInt.of(11, 13)
                      ),
                      new TwoLayersFeatureSize(1, 0, 1)
              ).ignoreVines().build()
      );


      register(context,
              FIR_TREE,
              Feature.TREE,
              new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(HibiscusBlocks.FIR[2]),
                      new StraightTrunkPlacer(10, 1, 2),
                      BlockStateProvider.simple(HibiscusBlocks.FIR_LEAVES),
                      new FirFoliagePlacer(UniformInt.of(2, 2),
                              UniformInt.of(2, 3),
                              UniformInt.of(3, 12)
                      ),
                      new TwoLayersFeatureSize(1, 0, 1)
              ).ignoreVines().build()
      );

      register(context, FIR_TREE_SPAWN, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
              List.of(new WeightedPlacedFeature(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.FIR_CHECKED),
                      0.5f
              )),
              placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.FIR_CHECKED)
      ));


      register(context, WISTERIA_DELTA, HIBISCUS_DELTA_FEATURE, new DeltaFeatureConfiguration(Blocks.WATER.defaultBlockState(),
              Blocks.COARSE_DIRT.defaultBlockState(),
              UniformInt.of(5, 8),
              UniformInt.of(0, 4)
      ));
      register(context, SWAMP_DELTA, HIBISCUS_DELTA_FEATURE, new DeltaFeatureConfiguration(Blocks.WATER.defaultBlockState(),
              Blocks.MUD.defaultBlockState(),
              UniformInt.of(2, 12),
              UniformInt.of(1, 3)
      ));
      register(context, RIVER_DELTA, HIBISCUS_DELTA_FEATURE, new DeltaFeatureConfiguration(Blocks.WATER.defaultBlockState(),
              Blocks.COARSE_DIRT.defaultBlockState(),
              UniformInt.of(2, 6),
              UniformInt.of(1, 3)
      ));

      register(context, LARGE_REDWOOD_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(
              HibiscusBlocks.REDWOOD[2]),
              new GiantTrunkPlacer(18, 2, 17),
              BlockStateProvider.simple(HibiscusBlocks.REDWOOD_LEAVES),
              new MegaPineFoliagePlacer(ConstantInt.of(0),
                      ConstantInt.of(0),
                      UniformInt.of(6, 12)
              ),
              new TwoLayersFeatureSize(2, 0, 2)
      ).decorators(ImmutableList.of(new AlterGroundDecorator(BlockStateProvider.simple(Blocks.PODZOL)))).build());

      register(context,
              LARGE_REDWOOD_TREE_SPAWN,
              Feature.RANDOM_SELECTOR,
              new RandomFeatureConfiguration(
                      List.of(new WeightedPlacedFeature(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.LARGE_REDWOOD_CHECKED),
                              0.5f
                      )),
                      placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.LARGE_REDWOOD_CHECKED)
              )
      );


      register(context,
              WILLOW_TREE,
              Feature.TREE,
              new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(HibiscusBlocks.WILLOW[2]),
                      new FancyTrunkPlacer(10, 3, 5),
                      BlockStateProvider.simple(HibiscusBlocks.WILLOW_LEAVES),
                      new WisteriaFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)),
                      new TwoLayersFeatureSize(3, 0, 2, OptionalInt.of(5))
              ).decorators(List.of(new WisteriaVinesTreeDecorator(0.65F,
                      new HibiscusSimpleBlockStateProvider(HibiscusBlocks.WILLOW_VINES_PLANT.defaultBlockState()),
                      new RandomizedIntStateProvider(BlockStateProvider.simple(HibiscusBlocks.WILLOW_VINES.defaultBlockState()),
                              WisteriaVine.AGE,
                              UniformInt.of(23, 25)
                      ),
                      5
              ))).ignoreVines().build()
      );
      register(context,
              WILLOW_TREE_SPAWN,
              Feature.RANDOM_SELECTOR,
              new RandomFeatureConfiguration(
                      List.of(new WeightedPlacedFeature(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.WILLOW_CHECKED),
                              0.5f
                      )),
                      placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.WILLOW_CHECKED)
              )
      );


      register(context, WHITE_WISTERIA_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(
              HibiscusBlocks.WISTERIA[2]),
              new WisteriaTrunkPlacer(7,
                      3,
                      4,
                      UniformInt.of(1, 6),
                      0.80F,
                      UniformInt.of(7, 10),
                      holderGetter.getOrThrow(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)
              ),
              BlockStateProvider.simple(HibiscusBlocks.WHITE_WISTERIA_LEAVES),
              new WisteriaFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)),
              new TwoLayersFeatureSize(2, 0, 2)
      ).decorators(List.of(new WisteriaVinesTreeDecorator(0.45F,
              new HibiscusSimpleBlockStateProvider(HibiscusBlocks.WHITE_WISTERIA_VINES_PLANT.defaultBlockState()),
              new RandomizedIntStateProvider(BlockStateProvider.simple(HibiscusBlocks.WHITE_WISTERIA_VINES.defaultBlockState()),
                      WisteriaVine.AGE,
                      UniformInt.of(22, 25)
              ),
              2
      ))).ignoreVines().build());

      register(context, PINK_WISTERIA_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(
              HibiscusBlocks.WISTERIA[2]),
              new WisteriaTrunkPlacer(7,
                      3,
                      4,
                      UniformInt.of(1, 6),
                      0.80F,
                      UniformInt.of(7, 10),
                      BuiltInRegistries.BLOCK.getOrCreateTag(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)
              ),
              BlockStateProvider.simple(HibiscusBlocks.PINK_WISTERIA_LEAVES),
              new WisteriaFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)),
              new TwoLayersFeatureSize(2, 0, 2)
      ).decorators(List.of(new WisteriaVinesTreeDecorator(0.45F,
              new HibiscusSimpleBlockStateProvider(HibiscusBlocks.PINK_WISTERIA_VINES_PLANT.defaultBlockState()),
              new RandomizedIntStateProvider(BlockStateProvider.simple(HibiscusBlocks.PINK_WISTERIA_VINES.defaultBlockState()),
                      WisteriaVine.AGE,
                      UniformInt.of(22, 25)
              ),
              2
      ))).ignoreVines().build());

      register(context, BLUE_WISTERIA_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(
              HibiscusBlocks.WISTERIA[2]),
              new WisteriaTrunkPlacer(7,
                      3,
                      4,
                      UniformInt.of(1, 6),
                      0.80F,
                      UniformInt.of(7, 10),
                      BuiltInRegistries.BLOCK.getOrCreateTag(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)
              ),
              BlockStateProvider.simple(HibiscusBlocks.BLUE_WISTERIA_LEAVES),
              new WisteriaFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)),
              new TwoLayersFeatureSize(2, 0, 2)
      ).decorators(List.of(new WisteriaVinesTreeDecorator(0.45F,
              new HibiscusSimpleBlockStateProvider(HibiscusBlocks.BLUE_WISTERIA_VINES_PLANT.defaultBlockState()),
              new RandomizedIntStateProvider(BlockStateProvider.simple(HibiscusBlocks.BLUE_WISTERIA_VINES.defaultBlockState()),
                      WisteriaVine.AGE,
                      UniformInt.of(22, 25)
              ),
              2
      ))).ignoreVines().build());

      register(context, PURPLE_WISTERIA_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(
              HibiscusBlocks.WISTERIA[2]),
              new WisteriaTrunkPlacer(7,
                      3,
                      4,
                      UniformInt.of(1, 6),
                      0.80F,
                      UniformInt.of(7, 10),
                      BuiltInRegistries.BLOCK.getOrCreateTag(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)
              ),
              BlockStateProvider.simple(HibiscusBlocks.PURPLE_WISTERIA_LEAVES),
              new WisteriaFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)),
              new TwoLayersFeatureSize(2, 0, 2)
      ).decorators(List.of(new WisteriaVinesTreeDecorator(0.45F,
              new HibiscusSimpleBlockStateProvider(HibiscusBlocks.PURPLE_WISTERIA_VINES_PLANT.defaultBlockState()),
              new RandomizedIntStateProvider(BlockStateProvider.simple(HibiscusBlocks.PURPLE_WISTERIA_VINES.defaultBlockState()),
                      WisteriaVine.AGE,
                      UniformInt.of(22, 25)
              ),
              2
      ))).ignoreVines().build());

      register(context, WISTERIA_SPAWN, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(
                      placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.WHITE_WISTERIA_CHECKED),
                      0.10f
              ),
              new WeightedPlacedFeature(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.BLUE_WISTERIA_CHECKED),
                      0.325f
              ),
              new WeightedPlacedFeature(
                      placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.PURPLE_WISTERIA_CHECKED),
                      0.325f
              ),
              new WeightedPlacedFeature(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.PINK_WISTERIA_CHECKED),
                      0.25f
              )
      ), placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.WHITE_WISTERIA_CHECKED)));

      register(context,
              SUGI_TREE,
              Feature.TREE,
              new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(HibiscusBlocks.SUGI[2]),
                      new SugiTrunkPlacer(10,
                              1,
                              1,
                              UniformInt.of(4, 6),
                              .85F,
                              UniformInt.of(4, 5),
                              BuiltInRegistries.BLOCK.getOrCreateTag(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)
                      ),
                      BlockStateProvider.simple(HibiscusBlocks.SUGI_LEAVES),
                      new SugiFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)),
                      new TwoLayersFeatureSize(1, 0, 1, OptionalInt.of(5))
              ).ignoreVines().build()
      );

      register(context, SUGI_SPAWN, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
              List.of(new WeightedPlacedFeature(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.SUGI_CHECKED),
                      0.325f
              )),
              placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.SUGI_CHECKED)
      ));

      register(context,
              OLIVE_TREE,
              Feature.TREE,
              new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(HibiscusBlocks.OLIVE[2]),
                      new OliveTrunkPlacer(4,
                              1,
                              2,
                              UniformInt.of(4, 6),
                              .85F,
                              UniformInt.of(4, 5),
                              BuiltInRegistries.BLOCK.getOrCreateTag(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)
                      ),
                      BlockStateProvider.simple(HibiscusBlocks.OLIVE_LEAVES),
                      new RandomSpreadFoliagePlacer(ConstantInt.of(3),
                              ConstantInt.of(0),
                              ConstantInt.of(2),
                              60
                      ),
                      new TwoLayersFeatureSize(1, 0, 1, OptionalInt.of(5))
              ).ignoreVines().build()
      );

      register(context,
              OLIVE_TREE_SPAWN,
              Feature.RANDOM_SELECTOR,
              new RandomFeatureConfiguration(
                      List.of(new WeightedPlacedFeature(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.OLIVE_CHECKED),
                              0.325f
                      )),
                      placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.OLIVE_CHECKED)
              )
      );

      register(context,
              JOSHUA_TREE,
              JOSHUA_TREE_FEATURE,
              FeatureConfiguration.NONE
      );

      register(context,
              JOSHUA_TREE_SPAWN,
              Feature.RANDOM_SELECTOR,
              new RandomFeatureConfiguration(
                      List.of(new WeightedPlacedFeature(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.JOSHUA_CHECKED),
                              0.325f
                      )),
                      placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.JOSHUA_CHECKED)
              )
      );

      register(context,
              CYPRESS_TREE_SPAWN,
              Feature.RANDOM_SELECTOR,
              new RandomFeatureConfiguration(
                      List.of(new WeightedPlacedFeature(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.CYPRESS_CHECKED),
                              0.325f
                      )),
                      placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.CYPRESS_CHECKED)
              )
      );

      register(context, FANCY_OAK_TREE_SPAWN, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
              List.of(new WeightedPlacedFeature(placedFeatureRegistryEntryLookup.getOrThrow(TreePlacements.FANCY_OAK_BEES),
                      0.03F
              )),
              placedFeatureRegistryEntryLookup.getOrThrow(TreePlacements.FANCY_OAK_CHECKED)
      ));

      register(context, OAK_BUSH, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.OAK_LOG),
              new StraightTrunkPlacer(1, 0, 0),
              BlockStateProvider.simple(Blocks.OAK_LEAVES),
              new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2),
              new TwoLayersFeatureSize(0, 0, 0)
      ).build());
      register(context, OAK_BUSH_SPAWN, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
              List.of(new WeightedPlacedFeature(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.OAK_BUSH_CHECKED),
                      0.5f
              )),
              placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.OAK_BUSH_CHECKED)
      ));

      register(context,
              SPRUCE_BUSH,
              Feature.TREE,
              new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.SPRUCE_LOG),
                      new StraightTrunkPlacer(1, 0, 0),
                      BlockStateProvider.simple(Blocks.SPRUCE_LEAVES),
                      new RandomSpreadFoliagePlacer(ConstantInt.of(3),
                              ConstantInt.of(0),
                              ConstantInt.of(2),
                              75
                      ),
                      new TwoLayersFeatureSize(1, 0, 1)
              ).build()
      );
      register(context,
              SPRUCE_BUSH_SPAWN,
              Feature.RANDOM_SELECTOR,
              new RandomFeatureConfiguration(
                      List.of(new WeightedPlacedFeature(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.OAK_BUSH_CHECKED),
                              0.5f
                      )),
                      placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.SPRUCE_BUSH_CHECKED)
              )
      );

      register(context,
              FLOWER_WISTERIA_FOREST,
              Feature.FLOWER,
              new RandomPatchConfiguration(96,
                      6,
                      2,
                      PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                              new SimpleBlockConfiguration(new NoiseProvider(2445L,
                                      new NormalNoise.NoiseParameters(0, 1.0D),
                                      0.030833334F,
                                      List.of(Blocks.ALLIUM.defaultBlockState(),
                                              HibiscusBlocks.BLUEBELL.defaultBlockState(),
                                              HibiscusBlocks.ANEMONE.defaultBlockState(),
                                              Blocks.OXEYE_DAISY.defaultBlockState(),
                                              Blocks.PINK_TULIP.defaultBlockState(),
                                              HibiscusBlocks.SNAPDRAGON.defaultBlockState(),
                                              HibiscusBlocks.GARDENIA.defaultBlockState(),
                                              HibiscusBlocks.LAVENDER.defaultBlockState(),
                                              HibiscusBlocks.HIBISCUS.defaultBlockState(),
                                              Blocks.CORNFLOWER.defaultBlockState()
                                      )
                              ))
                      )
              )
      );

      register(context,
              FLOWER_SUGI_FOREST,
              Feature.FLOWER,
              new RandomPatchConfiguration(45,
                      8,
                      2,
                      PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                              new SimpleBlockConfiguration(new NoiseProvider(2445L,
                                      new NormalNoise.NoiseParameters(0, 1.0D),
                                      0.030833334F,
                                      List.of(Blocks.LILY_OF_THE_VALLEY.defaultBlockState(),
                                              Blocks.LILY_OF_THE_VALLEY.defaultBlockState(),
                                              HibiscusBlocks.GARDENIA.defaultBlockState(),
                                              Blocks.AZURE_BLUET.defaultBlockState()
                                      )
                              ))
                      )
              )
      );

      register(context,
              FLOWER_LAVENDER_FIELD,
              Feature.FLOWER,
              new RandomPatchConfiguration(120,
                      6,
                      2,
                      PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                              new SimpleBlockConfiguration(new NoiseProvider(2445L,
                                      new NormalNoise.NoiseParameters(0, 1.0D),
                                      0.030833334F,
                                      List.of(Blocks.LILAC.defaultBlockState(),
                                              HibiscusBlocks.ANEMONE.defaultBlockState(),
                                              HibiscusBlocks.GARDENIA.defaultBlockState(),
                                              HibiscusBlocks.LAVENDER.defaultBlockState(),
                                              Blocks.PEONY.defaultBlockState()
                                      )
                              ))
                      )
              )
      );
      register(context,
              FLOWER_FOXGLOVE_FIELD,
              Feature.FLOWER,
              new RandomPatchConfiguration(120,
                      6,
                      2,
                      PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                              new SimpleBlockConfiguration(new NoiseProvider(2445L,
                                      new NormalNoise.NoiseParameters(0, 1.0D),
                                      0.030833334F,
                                      List.of(Blocks.LILAC.defaultBlockState(),
                                              Blocks.GRASS.defaultBlockState(),
                                              HibiscusBlocks.FOXGLOVE.defaultBlockState(),
                                              Blocks.TALL_GRASS.defaultBlockState(),
                                              Blocks.PEONY.defaultBlockState()
                                      )
                              ))
                      )
              )
      );

      register(context,
              FLOWER_ERODED_RIVER,
              Feature.FLOWER,
              new RandomPatchConfiguration(60,
                      6,
                      2,
                      PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                              new SimpleBlockConfiguration(new NoiseProvider(2445L,
                                      new NormalNoise.NoiseParameters(0, 1.0D),
                                      0.030833334F,
                                      List.of(Blocks.LILY_OF_THE_VALLEY.defaultBlockState(),
                                              HibiscusBlocks.BLEEDING_HEART.defaultBlockState(),
                                              HibiscusBlocks.ANEMONE.defaultBlockState(),
                                              HibiscusBlocks.HIBISCUS.defaultBlockState()
                                      )
                              ))
                      )
              )
      );

      register(context,
              FLOWER_REDWOOD_FOREST,
              Feature.FLOWER,
              new RandomPatchConfiguration(36,
                      6,
                      2,
                      PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                              new SimpleBlockConfiguration(new NoiseProvider(2445L,
                                      new NormalNoise.NoiseParameters(0, 1.0D),
                                      0.030833334F,
                                      List.of(HibiscusBlocks.BLUEBELL.defaultBlockState(),
                                              Blocks.ORANGE_TULIP.defaultBlockState(),
                                              Blocks.GRASS.defaultBlockState(),
                                              HibiscusBlocks.CARNATION.defaultBlockState(),
                                              HibiscusBlocks.GARDENIA.defaultBlockState()
                                      )
                              ))
                      )
              )
      );

      register(context,
              FLOWER_GOLDEN_WILDS,
              Feature.FLOWER,
              new RandomPatchConfiguration(36,
                      6,
                      2,
                      PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                              new SimpleBlockConfiguration(new NoiseProvider(2445L,
                                      new NormalNoise.NoiseParameters(0, 1.0D),
                                      0.030833334F,
                                      List.of(HibiscusBlocks.TIGER_LILY.defaultBlockState(),
                                              Blocks.ORANGE_TULIP.defaultBlockState(),
                                              Blocks.DANDELION.defaultBlockState(),
                                              HibiscusBlocks.MARIGOLD.defaultBlockState(),
                                              Blocks.TALL_GRASS.defaultBlockState(),
                                              HibiscusBlocks.CARNATION.defaultBlockState()
                                      )
                              ))
                      )
              )
      );

      register(context,
              FLOWER_FIR_FOREST,
              Feature.FLOWER,
              new RandomPatchConfiguration(36,
                      6,
                      2,
                      PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                              new SimpleBlockConfiguration(new NoiseProvider(2445L,
                                      new NormalNoise.NoiseParameters(0, 1.0D),
                                      0.030833334F,
                                      List.of(Blocks.LILY_OF_THE_VALLEY.defaultBlockState(),
                                              Blocks.OXEYE_DAISY.defaultBlockState(),
                                              HibiscusBlocks.CARNATION.defaultBlockState(),
                                              Blocks.ALLIUM.defaultBlockState()
                                      )
                              ))
                      )
              )
      );

      register(context,
              FLOWER_CYPRESS_FIELDS,
              Feature.FLOWER,
              new RandomPatchConfiguration(120,
                      6,
                      2,
                      PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                              new SimpleBlockConfiguration(new NoiseProvider(2445L,
                                      new NormalNoise.NoiseParameters(0, 1.0D),
                                      0.030833334F,
                                      List.of(Blocks.GRASS.defaultBlockState(),
                                              Blocks.TALL_GRASS.defaultBlockState(),
                                              Blocks.POPPY.defaultBlockState(),
                                              HibiscusBlocks.CARNATION.defaultBlockState(),
                                              Blocks.POPPY.defaultBlockState(),
                                              Blocks.GRASS.defaultBlockState(),
                                              Blocks.POPPY.defaultBlockState(),
                                              HibiscusBlocks.CARNATION.defaultBlockState(),
                                              Blocks.POPPY.defaultBlockState(),
                                              Blocks.TALL_GRASS.defaultBlockState(),
                                              Blocks.GRASS.defaultBlockState()
                                      )
                              ))
                      )
              )
      );

      register(context,
              PATCH_SCORCHED_GRASS,
              Feature.RANDOM_PATCH,
              new RandomPatchConfiguration(32, 7, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(HibiscusBlocks.SCORCHED_GRASS)))));
      register(context,
              PATCH_TALL_SCORCHED_GRASS,
              Feature.RANDOM_PATCH,
              new RandomPatchConfiguration(32, 7, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(HibiscusBlocks.SCORCHED_GRASS)))));


      register(context,
              FLOWER_BLOOMING_DUNES,
              Feature.FLOWER,
              new RandomPatchConfiguration(120,
                      6,
                      2,
                      PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                              new SimpleBlockConfiguration(new NoiseProvider(2445L,
                                      new NormalNoise.NoiseParameters(0, 1.0D),
                                      0.030833334F,
                                      List.of(HibiscusBlocks.SCORCHED_GRASS.defaultBlockState(),
                                              HibiscusBlocks.TALL_SCORCHED_GRASS.defaultBlockState(),
                                              HibiscusBlocks.YELLOW_WILDFLOWER.defaultBlockState(),
                                              HibiscusBlocks.PURPLE_WILDFLOWER.defaultBlockState(),
                                              HibiscusBlocks.YELLOW_WILDFLOWER.defaultBlockState(),
                                              HibiscusBlocks.SCORCHED_GRASS.defaultBlockState(),
                                              HibiscusBlocks.YELLOW_WILDFLOWER.defaultBlockState(),
                                              HibiscusBlocks.PURPLE_WILDFLOWER.defaultBlockState(),
                                              HibiscusBlocks.YELLOW_WILDFLOWER.defaultBlockState(),
                                              HibiscusBlocks.TALL_SCORCHED_GRASS.defaultBlockState(),
                                              HibiscusBlocks.SCORCHED_GRASS.defaultBlockState()
                                      )
                              ))
                      )
              )
      );

      register(context,
              FLOWER_STRATIFIED_DESERT,
              Feature.FLOWER,
              new RandomPatchConfiguration(10,
                      6,
                      2,
                      PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                              new SimpleBlockConfiguration(new NoiseProvider(2445L,
                                      new NormalNoise.NoiseParameters(0, 1.0D),
                                      0.030833334F,
                                      List.of(
                                              HibiscusBlocks.SCORCHED_GRASS.defaultBlockState(),
                                              HibiscusBlocks.YELLOW_WILDFLOWER.defaultBlockState(),
                                              HibiscusBlocks.TALL_SCORCHED_GRASS.defaultBlockState(),
                                              HibiscusBlocks.PURPLE_WILDFLOWER.defaultBlockState(),
                                              HibiscusBlocks.SCORCHED_GRASS.defaultBlockState()
                                      )
                              ))
                      )
              )
      );

      register(context,
              CATTAILS,
              Feature.RANDOM_PATCH,
              new RandomPatchConfiguration(90, 6, 2, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                      new SimpleBlockConfiguration(BlockStateProvider.simple(HibiscusBlocks.CATTAIL)),
                      BlockPredicate.allOf(BlockPredicate.wouldSurvive(HibiscusBlocks.CATTAIL.defaultBlockState(),
                                      BlockPos.ZERO
                              ),
                              BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE,
                              BlockPredicate.anyOf(
                                      BlockPredicate.matchesFluids(new BlockPos(1, -1, 0),
                                              Fluids.WATER,
                                              Fluids.FLOWING_WATER
                                      ),
                                      BlockPredicate.matchesFluids(new BlockPos(-1, -1, 0),
                                              Fluids.WATER,
                                              Fluids.FLOWING_WATER
                                      ),
                                      BlockPredicate.matchesFluids(new BlockPos(0, -1, 1),
                                              Fluids.WATER,
                                              Fluids.FLOWING_WATER
                                      ),
                                      BlockPredicate.matchesFluids(new BlockPos(0, -1, -1),
                                              Fluids.WATER,
                                              Fluids.FLOWING_WATER
                                      ),
                                      BlockPredicate.matchesFluids(new BlockPos(0, 0, 0),
                                              Fluids.WATER,
                                              Fluids.FLOWING_WATER
                                      )
                              )
                      )
              ))
      );

      register(context,
              ROOTED_DESERT_TURNIP,
              HIBISCUS_TURNIP_ROOT_FEATURE,
              new TurnipRootFeatureConfig(PlacementUtils.inlinePlaced(Feature.NO_BONEMEAL_FLOWER,
                      new RandomPatchConfiguration(30, 3, 2, PlacementUtils.inlinePlaced(Feature.SIMPLE_BLOCK,
                              new SimpleBlockConfiguration(BlockStateProvider.simple(HibiscusBlocks.DESERT_TURNIP_STEM.defaultBlockState().setValue(
                                      DesertPlantBlock.AGE, 7))),
                              PlacementUtils.HEIGHTMAP)),
                      new PlacementModifier[0]
              ),
                      3,
                      3,
                      HibiscusTags.Blocks.TURNIP_ROOT_REPLACEABLE,
                      BlockStateProvider.simple(HibiscusBlocks.DESERT_TURNIP_ROOT_BLOCK),
                      BlockStateProvider.simple(HibiscusBlocks.DESERT_TURNIP_BLOCK),
                      20,
                      20,
                      3,
                      2,
                      BlockStateProvider.simple(Blocks.HANGING_ROOTS),
                      20,
                      35,
                      2,
                      BlockPredicate.allOf(BlockPredicate.anyOf(BlockPredicate.matchesBlocks(List.of(Blocks.AIR,
                                              Blocks.CAVE_AIR,
                                              Blocks.VOID_AIR
                                      )),
                                      BlockPredicate.matchesTag(BlockTags.REPLACEABLE)
                              ),
                              BlockPredicate.matchesTag(Direction.DOWN.getNormal(),
                                      HibiscusTags.Blocks.TURNIP_STEM_GROWS_ON
                              )
                      )
              )
      );

   }

   public static ResourceKey <ConfiguredFeature <?, ?>> registerKey(String name) {
      return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Constants.MOD_ID, name));
   }

   private static <FC extends FeatureConfiguration, F extends Feature <FC>> void register(BootstapContext <ConfiguredFeature <?, ?>> context, ResourceKey <ConfiguredFeature <?, ?>> key, F feature, FC configuration) {
      context.register(key, new ConfiguredFeature <>(feature, configuration));
   }

   public static void registerConfiguredFeatures() {
      System.out.println("Registering Configured Features For:" + Constants.MOD_ID);
   }
}


