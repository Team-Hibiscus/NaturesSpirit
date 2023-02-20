package net.hibiscus.naturespirit.world.feature;

import com.google.common.collect.ImmutableList;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.blocks.HibiscusBlocks;
import net.hibiscus.naturespirit.blocks.WisteriaVine;
import net.hibiscus.naturespirit.world.feature.foliage_placer.AspenFoliagePlacer;
import net.hibiscus.naturespirit.world.feature.foliage_placer.FirFoliagePlacer;
import net.hibiscus.naturespirit.world.feature.foliage_placer.SugiFoliagePlacer;
import net.hibiscus.naturespirit.world.feature.foliage_placer.WisteriaFoliagePlacer;
import net.hibiscus.naturespirit.world.feature.tree_decorator.WisteriaVinesTreeDecorator;
import net.hibiscus.naturespirit.world.feature.trunk.SugiTrunkPlacer;
import net.hibiscus.naturespirit.world.feature.trunk.WisteriaTrunkPlacer;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.NoiseBlockStateProvider;
import net.minecraft.world.gen.stateprovider.RandomizedIntBlockStateProvider;
import net.minecraft.world.gen.treedecorator.AlterGroundTreeDecorator;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.trunk.GiantTrunkPlacer;
import net.minecraft.world.gen.trunk.LargeOakTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;
import java.util.OptionalInt;

import static net.hibiscus.naturespirit.NatureSpirit.HIBISCUS_DELTA_FEATURE;

public class HibiscusConfiguredFeatures {

    public static final RegistryKey <ConfiguredFeature <?, ?>> WISTERIA_DELTA = registerKey("water_delta");
    public static final RegistryKey <ConfiguredFeature <?, ?>> SWAMP_DELTA = registerKey("swamp_delta");
    public static final RegistryKey <ConfiguredFeature <?, ?>> RIVER_DELTA = registerKey("river_delta");
    public static final RegistryKey <ConfiguredFeature <?, ?>> LARGE_REDWOOD_TREE = registerKey("large_redwood_tree");
    public static final RegistryKey <ConfiguredFeature <?, ?>> REDWOOD_TREE = registerKey("redwood_tree");
    public static final RegistryKey <ConfiguredFeature <?, ?>> LARGE_REDWOOD_TREE_SPAWN = registerKey("large_redwood_tree_spawn");
    public static final RegistryKey <ConfiguredFeature <?, ?>> REDWOOD_TREE_SPAWN = registerKey("redwood_tree_spawn");
    public static final RegistryKey <ConfiguredFeature <?, ?>> WILLOW_TREE = registerKey("willow_tree");
    public static final RegistryKey <ConfiguredFeature <?, ?>> WILLOW_TREE_SPAWN = registerKey("willow_tree_spawn");
    public static final RegistryKey <ConfiguredFeature <?, ?>> WHITE_WISTERIA_TREE = registerKey("white_wisteria_tree");
    public static final RegistryKey <ConfiguredFeature <?, ?>> BLUE_WISTERIA_TREE = registerKey("blue_wisteria_tree");
    public static final RegistryKey <ConfiguredFeature <?, ?>> PURPLE_WISTERIA_TREE = registerKey("purple_wisteria_tree");
    public static final RegistryKey <ConfiguredFeature <?, ?>> PINK_WISTERIA_TREE = registerKey("pink_wisteria_tree");
    public static final RegistryKey <ConfiguredFeature <?, ?>> ASPEN_TREE = registerKey("aspen_tree");
    public static final RegistryKey <ConfiguredFeature <?, ?>> ASPEN_TREE_BEES = registerKey("aspen_tree_bees");
    public static final RegistryKey <ConfiguredFeature <?, ?>> ASPEN_TREE_SPAWN = registerKey("aspen_tree_spawn");
    public static final RegistryKey <ConfiguredFeature <?, ?>> FIR_TREE = registerKey("fir_tree");
    public static final RegistryKey <ConfiguredFeature <?, ?>> FIR_TREE_SPAWN = registerKey("fir_tree_spawn");
    public static final RegistryKey <ConfiguredFeature <?, ?>> WISTERIA_SPAWN = registerKey("wisteria_spawn");
    public static final RegistryKey <ConfiguredFeature <?, ?>> SUGI_TREE = registerKey("sugi_tree");
    public static final RegistryKey <ConfiguredFeature <?, ?>> SUGI_SPAWN = registerKey("sugi_spawn");
    public static final RegistryKey <ConfiguredFeature <?, ?>> OAK_BUSH = registerKey("oak_bush");
    public static final RegistryKey <ConfiguredFeature <?, ?>> SPRUCE_BUSH = registerKey("spruce_bush");
    public static final RegistryKey <ConfiguredFeature <?, ?>> OAK_BUSH_SPAWN = registerKey("oak_bush_spawn");
    public static final RegistryKey <ConfiguredFeature <?, ?>> SPRUCE_BUSH_SPAWN = registerKey("spruce_bush_spawn");
    public static final RegistryKey <ConfiguredFeature <?, ?>> FANCY_OAK_TREE_SPAWN = registerKey("custom_fancy_oak_tree_spawn");
    public static final RegistryKey <ConfiguredFeature <?, ?>> FLOWER_WISTERIA_FOREST = registerKey("flower_wisteria_forest");
    public static final RegistryKey <ConfiguredFeature <?, ?>> FLOWER_SUGI_FOREST = registerKey("flower_sugi_forest");
    public static final RegistryKey <ConfiguredFeature <?, ?>> FLOWER_REDWOOD_FOREST = registerKey("flower_redwood_forest");
    public static final RegistryKey <ConfiguredFeature <?, ?>> FLOWER_LAVENDER_FIELD = registerKey("flower_lavender_field");
    public static final RegistryKey <ConfiguredFeature <?, ?>> FLOWER_ERODED_RIVER = registerKey("flower_eroded_river");
    public static final RegistryKey <ConfiguredFeature <?, ?>> FLOWER_GOLDEN_WILDS = registerKey("flower_golden_wilds");
    public static final RegistryKey <ConfiguredFeature <?, ?>> FLOWER_FIR_FOREST = registerKey("flower_fir_forest");
    public static final RegistryKey <ConfiguredFeature <?, ?>> CATTAILS = registerKey("cattails");


    public static void bootstrap(Registerable <ConfiguredFeature <?, ?>> context) {
        var placedFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.PLACED_FEATURE);
        RegistryEntryLookup <Block> holderGetter = context.getRegistryLookup(RegistryKeys.BLOCK);

        register(context, REDWOOD_TREE, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(HibiscusBlocks.REDWOOD[2]),
                new StraightTrunkPlacer(12, 1, 4),
                BlockStateProvider.of(HibiscusBlocks.REDWOOD_LEAVES),
                new SpruceFoliagePlacer(UniformIntProvider.create(1, 3), UniformIntProvider.create(1, 2), UniformIntProvider.create(14, 15)),
                new TwoLayersFeatureSize(2, 0, 2)).ignoreVines().build());

        register(context, REDWOOD_TREE_SPAWN, Feature.RANDOM_SELECTOR,
                new RandomFeatureConfig(List.of(new RandomFeatureEntry(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.REDWOOD_CHECKED),
                        0.5f)), placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.REDWOOD_CHECKED)));

        register(context, ASPEN_TREE, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(HibiscusBlocks.ASPEN[2]),
                new StraightTrunkPlacer(10, 1, 2),
                BlockStateProvider.of(HibiscusBlocks.ASPEN_LEAVES),
                new AspenFoliagePlacer(UniformIntProvider.create(2, 2), UniformIntProvider.create(2, 3), UniformIntProvider.create(3, 12)),
                new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());
        register(context, ASPEN_TREE_BEES, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(HibiscusBlocks.ASPEN[2]),
                new StraightTrunkPlacer(10, 1, 2),
                BlockStateProvider.of(HibiscusBlocks.ASPEN_LEAVES),
                new AspenFoliagePlacer(UniformIntProvider.create(2, 2), UniformIntProvider.create(2, 3), UniformIntProvider.create(3, 12)),
                new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().decorators(List.of(new BeehiveTreeDecorator(1.0F))).build());

        register(context, ASPEN_TREE_SPAWN, Feature.RANDOM_SELECTOR,
                new RandomFeatureConfig(List.of(new RandomFeatureEntry(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.ASPEN_BEES_CHECKED),
                        0.1f)), placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.ASPEN_CHECKED)));


        register(context, FIR_TREE, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(HibiscusBlocks.FIR[2]),
                new StraightTrunkPlacer(10, 1, 2),
                BlockStateProvider.of(HibiscusBlocks.FIR_LEAVES),
                new FirFoliagePlacer(UniformIntProvider.create(2, 2), UniformIntProvider.create(2, 3), UniformIntProvider.create(3, 12)),
                new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());

        register(context, FIR_TREE_SPAWN, Feature.RANDOM_SELECTOR,
                new RandomFeatureConfig(List.of(new RandomFeatureEntry(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.FIR_CHECKED),
                        0.5f)), placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.FIR_CHECKED)));


        register(context, WISTERIA_DELTA, HIBISCUS_DELTA_FEATURE, new DeltaFeatureConfig(Blocks.WATER.getDefaultState(), Blocks.COARSE_DIRT.getDefaultState(), UniformIntProvider.create(5, 8), UniformIntProvider.create(0, 4)));
        register(context, SWAMP_DELTA, HIBISCUS_DELTA_FEATURE, new DeltaFeatureConfig(Blocks.WATER.getDefaultState(), Blocks.MUD.getDefaultState(), UniformIntProvider.create(2, 12), UniformIntProvider.create(1, 3)));
        register(context, RIVER_DELTA, HIBISCUS_DELTA_FEATURE, new DeltaFeatureConfig(Blocks.WATER.getDefaultState(), Blocks.COARSE_DIRT.getDefaultState(), UniformIntProvider.create(2, 6), UniformIntProvider.create(1, 3)));

        register(context, LARGE_REDWOOD_TREE, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(HibiscusBlocks.REDWOOD[2]),
                new GiantTrunkPlacer(18, 2, 17), BlockStateProvider.of(HibiscusBlocks.REDWOOD_LEAVES),
                new MegaPineFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0), UniformIntProvider.create(6, 12)),
                new TwoLayersFeatureSize(2, 0, 2)).decorators(
                ImmutableList.of(new AlterGroundTreeDecorator(BlockStateProvider.of(Blocks.PODZOL)))).build());

        register(context, LARGE_REDWOOD_TREE_SPAWN, Feature.RANDOM_SELECTOR,
                new RandomFeatureConfig(List.of(new RandomFeatureEntry(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.LARGE_REDWOOD_CHECKED),
                        0.5f)), placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.LARGE_REDWOOD_CHECKED)));


        register(context, WILLOW_TREE, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(HibiscusBlocks.WILLOW[2]),
                new LargeOakTrunkPlacer(10, 3, 5),
                BlockStateProvider.of(HibiscusBlocks.WILLOW_LEAVES),
                new WisteriaFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(0)),
                new TwoLayersFeatureSize(3, 0, 2, OptionalInt.of(5))).decorators(List.of(
                new WisteriaVinesTreeDecorator(0.65F,
                        new HibiscusSimpleBlockStateProvider(HibiscusBlocks.WILLOW_VINES_PLANT.getDefaultState()),
                        new RandomizedIntBlockStateProvider(BlockStateProvider.of(HibiscusBlocks.WILLOW_VINES.getDefaultState()), WisteriaVine.AGE, UniformIntProvider.create(23, 25)),
                        5
                ))).ignoreVines().build());
        register(context, WILLOW_TREE_SPAWN, Feature.RANDOM_SELECTOR,
                new RandomFeatureConfig(List.of(new RandomFeatureEntry(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.WILLOW_CHECKED),
                        0.5f)), placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.WILLOW_CHECKED)));


        register(context, WHITE_WISTERIA_TREE, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(HibiscusBlocks.WISTERIA[2]),
                new WisteriaTrunkPlacer(7, 3, 4, UniformIntProvider.create(1, 6), 0.80F, UniformIntProvider.create(7, 10), holderGetter.getOrThrow(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)),
                BlockStateProvider.of(HibiscusBlocks.WHITE_WISTERIA_LEAVES),
                new WisteriaFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(0)),
                new TwoLayersFeatureSize(2, 0, 2)).decorators(List.of(
                new WisteriaVinesTreeDecorator(0.45F,
                        new HibiscusSimpleBlockStateProvider(HibiscusBlocks.WHITE_WISTERIA_VINES_PLANT.getDefaultState()),
                        new RandomizedIntBlockStateProvider(BlockStateProvider.of(HibiscusBlocks.WHITE_WISTERIA_VINES.getDefaultState()), WisteriaVine.AGE, UniformIntProvider.create(22, 25)),
                        2
                ))).ignoreVines().build());

        register(context, PINK_WISTERIA_TREE, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(HibiscusBlocks.WISTERIA[2]),
                new WisteriaTrunkPlacer(7, 3, 4, UniformIntProvider.create(1, 6), 0.80F, UniformIntProvider.create(7, 10), Registries.BLOCK.getOrCreateEntryList(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)),
                BlockStateProvider.of(HibiscusBlocks.PINK_WISTERIA_LEAVES),
                new WisteriaFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(0)),
                new TwoLayersFeatureSize(2, 0, 2)).decorators(List.of(
                new WisteriaVinesTreeDecorator(0.45F,
                        new HibiscusSimpleBlockStateProvider(HibiscusBlocks.PINK_WISTERIA_VINES_PLANT.getDefaultState()),
                        new RandomizedIntBlockStateProvider(BlockStateProvider.of(HibiscusBlocks.PINK_WISTERIA_VINES.getDefaultState()), WisteriaVine.AGE, UniformIntProvider.create(22, 25)),
                        2
                ))).ignoreVines().build());

        register(context, BLUE_WISTERIA_TREE, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(HibiscusBlocks.WISTERIA[2]),
                new WisteriaTrunkPlacer(7, 3, 4, UniformIntProvider.create(1, 6), 0.80F, UniformIntProvider.create(7, 10), Registries.BLOCK.getOrCreateEntryList(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)),
                BlockStateProvider.of(HibiscusBlocks.BLUE_WISTERIA_LEAVES),
                new WisteriaFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(0)),
                new TwoLayersFeatureSize(2, 0, 2)).decorators(List.of(
                new WisteriaVinesTreeDecorator(0.45F,
                        new HibiscusSimpleBlockStateProvider(HibiscusBlocks.BLUE_WISTERIA_VINES_PLANT.getDefaultState()),
                        new RandomizedIntBlockStateProvider(BlockStateProvider.of(HibiscusBlocks.BLUE_WISTERIA_VINES.getDefaultState()), WisteriaVine.AGE, UniformIntProvider.create(22, 25)),
                        2
                ))).ignoreVines().build());

        register(context, PURPLE_WISTERIA_TREE, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(HibiscusBlocks.WISTERIA[2]),
                new WisteriaTrunkPlacer(7, 3, 4, UniformIntProvider.create(1, 6), 0.80F, UniformIntProvider.create(7, 10), Registries.BLOCK.getOrCreateEntryList(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)),
                BlockStateProvider.of(HibiscusBlocks.PURPLE_WISTERIA_LEAVES),
                new WisteriaFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(0)),
                new TwoLayersFeatureSize(2, 0, 2)).decorators(List.of(
                new WisteriaVinesTreeDecorator(0.45F,
                        new HibiscusSimpleBlockStateProvider(HibiscusBlocks.PURPLE_WISTERIA_VINES_PLANT.getDefaultState()),
                        new RandomizedIntBlockStateProvider(BlockStateProvider.of(HibiscusBlocks.PURPLE_WISTERIA_VINES.getDefaultState()), WisteriaVine.AGE, UniformIntProvider.create(22, 25)),
                        2
                ))).ignoreVines().build());

        register(context, WISTERIA_SPAWN, Feature.RANDOM_SELECTOR,
                new RandomFeatureConfig(List.of(
                        new RandomFeatureEntry(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.WHITE_WISTERIA_CHECKED), 0.10f),
                        new RandomFeatureEntry(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.BLUE_WISTERIA_CHECKED), 0.325f),
                        new RandomFeatureEntry(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.PURPLE_WISTERIA_CHECKED), 0.325f),
                        new RandomFeatureEntry(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.PINK_WISTERIA_CHECKED), 0.25f)),
                        placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.WHITE_WISTERIA_CHECKED)));

        register(context, SUGI_TREE, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(HibiscusBlocks.SUGI[2]),
                new SugiTrunkPlacer(10, 1, 1, UniformIntProvider.create(4, 6), .85F, UniformIntProvider.create(4, 5), Registries.BLOCK.getOrCreateEntryList(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)), BlockStateProvider.of(HibiscusBlocks.SUGI_LEAVES),
                new SugiFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(0)),
                new TwoLayersFeatureSize(1, 0, 1, OptionalInt.of(5))).ignoreVines().build());

        register(context, SUGI_SPAWN, Feature.RANDOM_SELECTOR,
                new RandomFeatureConfig(List.of(new RandomFeatureEntry(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.SUGI_CHECKED), 0.325f)),
                        placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.SUGI_CHECKED)));
//
        register(context, FANCY_OAK_TREE_SPAWN, Feature.RANDOM_SELECTOR,
                new RandomFeatureConfig(List.of(new RandomFeatureEntry(placedFeatureRegistryEntryLookup.getOrThrow(TreePlacedFeatures.FANCY_OAK_BEES), 0.03F)),
                        placedFeatureRegistryEntryLookup.getOrThrow(TreePlacedFeatures.FANCY_OAK_CHECKED)));

        register(context, OAK_BUSH, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.OAK_LOG),
                new StraightTrunkPlacer(1, 0, 0),
                BlockStateProvider.of(Blocks.OAK_LEAVES),
                new BushFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 2),
                new TwoLayersFeatureSize(0, 0, 0)).build());
        register(context, OAK_BUSH_SPAWN, Feature.RANDOM_SELECTOR,
                new RandomFeatureConfig(List.of(new RandomFeatureEntry(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.OAK_BUSH_CHECKED), 0.5f)),
                        placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.OAK_BUSH_CHECKED)));

        register(context, SPRUCE_BUSH, Feature.TREE,
                new TreeFeatureConfig.Builder(
                        BlockStateProvider.of(Blocks.SPRUCE_LOG),
                        new StraightTrunkPlacer(1, 0, 0), BlockStateProvider.of(Blocks.SPRUCE_LEAVES),
                        new RandomSpreadFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0), ConstantIntProvider.create(2), 75),
                        new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, SPRUCE_BUSH_SPAWN, Feature.RANDOM_SELECTOR,
                new RandomFeatureConfig(List.of(
                        new RandomFeatureEntry(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.OAK_BUSH_CHECKED), 0.5f)),
                        placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.SPRUCE_BUSH_CHECKED)));

        register(context, FLOWER_WISTERIA_FOREST, Feature.FLOWER, new RandomPatchFeatureConfig(
                96, 6, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(
                new NoiseBlockStateProvider(2445L, new DoublePerlinNoiseSampler.NoiseParameters(0, 1.0D), 0.030833334F, List.of(
                        Blocks.ALLIUM.getDefaultState(),
                        HibiscusBlocks.BLUEBELL.getDefaultState(),
                        HibiscusBlocks.ANEMONE.getDefaultState(),
                        Blocks.OXEYE_DAISY.getDefaultState(),
                        Blocks.PINK_TULIP.getDefaultState(),
                        HibiscusBlocks.SNAPDRAGON.getDefaultState(),
                        HibiscusBlocks.GARDENIA.getDefaultState(),
                        HibiscusBlocks.LAVENDER.getDefaultState(),
                        HibiscusBlocks.HIBISCUS.getDefaultState(),
                        Blocks.CORNFLOWER.getDefaultState()))))));

        register(context, FLOWER_SUGI_FOREST, Feature.FLOWER, new RandomPatchFeatureConfig(
                45, 8, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(
                new NoiseBlockStateProvider(2445L, new DoublePerlinNoiseSampler.NoiseParameters(0, 1.0D), 0.030833334F, List.of(
                        Blocks.LILY_OF_THE_VALLEY.getDefaultState(),
                        Blocks.LILY_OF_THE_VALLEY.getDefaultState(),
                        HibiscusBlocks.GARDENIA.getDefaultState(),
                        Blocks.AZURE_BLUET.getDefaultState()))))));

        register(context, FLOWER_LAVENDER_FIELD, Feature.FLOWER, new RandomPatchFeatureConfig(
                120, 6, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(
                new NoiseBlockStateProvider(2445L, new DoublePerlinNoiseSampler.NoiseParameters(0, 1.0D), 0.030833334F, List.of(
                        Blocks.LILAC.getDefaultState(),
                        HibiscusBlocks.ANEMONE.getDefaultState(),
                        HibiscusBlocks.GARDENIA.getDefaultState(),
                        HibiscusBlocks.LAVENDER.getDefaultState(),
                        Blocks.PEONY.getDefaultState()
                ))))));

        register(context, FLOWER_ERODED_RIVER, Feature.FLOWER, new RandomPatchFeatureConfig(
                60, 6, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(
                new NoiseBlockStateProvider(2445L, new DoublePerlinNoiseSampler.NoiseParameters(0, 1.0D), 0.030833334F, List.of(
                        Blocks.LILY_OF_THE_VALLEY.getDefaultState(),
                        HibiscusBlocks.BLEEDING_HEART.getDefaultState(),
                        HibiscusBlocks.ANEMONE.getDefaultState(),
                        HibiscusBlocks.HIBISCUS.getDefaultState()
                ))))));

        register(context, FLOWER_REDWOOD_FOREST, Feature.FLOWER, new RandomPatchFeatureConfig(
                36, 6, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(
                new NoiseBlockStateProvider(2445L, new DoublePerlinNoiseSampler.NoiseParameters(0, 1.0D), 0.030833334F, List.of(
                        HibiscusBlocks.BLUEBELL.getDefaultState(),
                        Blocks.ORANGE_TULIP.getDefaultState(),
                        Blocks.GRASS.getDefaultState(),
                        HibiscusBlocks.CARNATION.getDefaultState(),
                        HibiscusBlocks.GARDENIA.getDefaultState()))))));

        register(context, FLOWER_GOLDEN_WILDS, Feature.FLOWER, new RandomPatchFeatureConfig(
                36, 6, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(
                new NoiseBlockStateProvider(2445L, new DoublePerlinNoiseSampler.NoiseParameters(0, 1.0D), 0.030833334F, List.of(
                        HibiscusBlocks.TIGER_LILY.getDefaultState(),
                        Blocks.ORANGE_TULIP.getDefaultState(),
                        Blocks.DANDELION.getDefaultState(),
                        HibiscusBlocks.MARIGOLD.getDefaultState(),
                        Blocks.TALL_GRASS.getDefaultState(),
                        HibiscusBlocks.CARNATION.getDefaultState()))))));

        register(context, FLOWER_FIR_FOREST, Feature.FLOWER, new RandomPatchFeatureConfig(
                36, 6, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(
                new NoiseBlockStateProvider(2445L, new DoublePerlinNoiseSampler.NoiseParameters(0, 1.0D), 0.030833334F, List.of(
                        Blocks.LILY_OF_THE_VALLEY.getDefaultState(),
                        Blocks.OXEYE_DAISY.getDefaultState(),
                        HibiscusBlocks.CARNATION.getDefaultState(),
                        Blocks.ALLIUM.getDefaultState()))))));

        register(context, CATTAILS, Feature.RANDOM_PATCH, new RandomPatchFeatureConfig(
                90, 6, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(BlockStateProvider.of(HibiscusBlocks.CATTAIL)),
                BlockPredicate.allOf(
                        BlockPredicate.wouldSurvive(HibiscusBlocks.CATTAIL.getDefaultState(), BlockPos.ORIGIN),
                        BlockPredicate.IS_AIR_OR_WATER,
                        BlockPredicate.anyOf(
                                BlockPredicate.matchingFluids(new BlockPos(1, -1, 0),
                                        Fluids.WATER, Fluids.FLOWING_WATER),
                                BlockPredicate.matchingFluids(new BlockPos(-1, -1, 0),
                                        Fluids.WATER, Fluids.FLOWING_WATER),
                                BlockPredicate.matchingFluids(new BlockPos(0, -1, 1),
                                        Fluids.WATER, Fluids.FLOWING_WATER),
                                BlockPredicate.matchingFluids(new BlockPos(0, -1, -1),
                                        Fluids.WATER, Fluids.FLOWING_WATER),
                                BlockPredicate.matchingFluids(new BlockPos(0, 0, 0),
                                        Fluids.WATER, Fluids.FLOWING_WATER)
                        )))));

    }

    public static RegistryKey <ConfiguredFeature <?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(NatureSpirit.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature <FC>> void register(Registerable <ConfiguredFeature <?, ?>> context, RegistryKey <ConfiguredFeature <?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature <>(feature, configuration));
    }

    public static void registerConfiguredFeatures() {
        System.out.println("Registering Configured Features For:" + NatureSpirit.MOD_ID);
    }
}


