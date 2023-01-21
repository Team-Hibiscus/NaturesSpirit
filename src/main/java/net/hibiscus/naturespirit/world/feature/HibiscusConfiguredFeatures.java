package net.hibiscus.naturespirit.world.feature;

import com.google.common.collect.ImmutableList;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.blocks.HibiscusBlocks;
import net.hibiscus.naturespirit.blocks.WisteriaVine;
import net.hibiscus.naturespirit.world.feature.foliage_placer.WisteriaFoliagePlacer;
import net.hibiscus.naturespirit.world.feature.tree_decorator.WisteriaVinesTreeDecorator;
import net.hibiscus.naturespirit.world.feature.trunk.SakuraTrunkPlacer;
import net.hibiscus.naturespirit.world.feature.trunk.SakuraTrunkPlacerSapling;
import net.hibiscus.naturespirit.world.feature.trunk.WisteriaTrunkPlacer;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
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
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.NoiseProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RandomizedIntStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AlterGroundDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.List;
import java.util.OptionalInt;

import static net.hibiscus.naturespirit.NatureSpirit.HIBISCUS_DELTA_FEATURE;

public class HibiscusConfiguredFeatures {

    public static final ResourceKey <ConfiguredFeature <?, ?>> WISTERIA_DELTA = registerKey("water_delta");
    public static final ResourceKey <ConfiguredFeature <?, ?>> LARGE_REDWOOD_TREE = registerKey("large_redwood_tree");
    public static final ResourceKey <ConfiguredFeature <?, ?>> REDWOOD_TREE = registerKey("redwood_tree");
    public static final ResourceKey <ConfiguredFeature <?, ?>> LARGE_REDWOOD_TREE_SPAWN = registerKey("large_redwood_tree_spawn");
    public static final ResourceKey <ConfiguredFeature <?, ?>> REDWOOD_TREE_SPAWN = registerKey("redwood_tree_spawn");
    public static final ResourceKey <ConfiguredFeature <?, ?>> WHITE_WISTERIA_TREE = registerKey("white_wisteria_tree");
    public static final ResourceKey <ConfiguredFeature <?, ?>> BLUE_WISTERIA_TREE = registerKey("blue_wisteria_tree");
    public static final ResourceKey <ConfiguredFeature <?, ?>> PURPLE_WISTERIA_TREE = registerKey("purple_wisteria_tree");
    public static final ResourceKey <ConfiguredFeature <?, ?>> PINK_WISTERIA_TREE = registerKey("pink_wisteria_tree");
    public static final ResourceKey <ConfiguredFeature <?, ?>> WISTERIA_SPAWN = registerKey("wisteria_spawn");
    public static final ResourceKey <ConfiguredFeature <?, ?>> PINK_SAKURA_TREE = registerKey("pink_sakura_tree");
    public static final ResourceKey <ConfiguredFeature <?, ?>> WHITE_SAKURA_TREE = registerKey("white_sakura_tree");
    public static final ResourceKey <ConfiguredFeature <?, ?>> PINK_SAKURA_TREE_SAPLING = registerKey("pink_sakura_tree_sapling");
    public static final ResourceKey <ConfiguredFeature <?, ?>> WHITE_SAKURA_TREE_SAPLING = registerKey("white_sakura_tree_sapling");
    public static final ResourceKey <ConfiguredFeature <?, ?>> SAKURA_SPAWN = registerKey("sakura_spawn");
    public static final ResourceKey <ConfiguredFeature <?, ?>> OAK_BUSH = registerKey("oak_bush");
    public static final ResourceKey <ConfiguredFeature <?, ?>> SPRUCE_BUSH = registerKey("spruce_bush");
    public static final ResourceKey <ConfiguredFeature <?, ?>> OAK_BUSH_SPAWN = registerKey("oak_bush_spawn");
    public static final ResourceKey <ConfiguredFeature <?, ?>> SPRUCE_BUSH_SPAWN = registerKey("spruce_bush_spawn");
    public static final ResourceKey <ConfiguredFeature <?, ?>> FANCY_OAK_TREE_SPAWN = registerKey("custom_fancy_oak_tree_spawn");
    public static final ResourceKey <ConfiguredFeature <?, ?>> FLOWER_WISTERIA_FOREST = registerKey("flower_wisteria_forest");
    public static final ResourceKey <ConfiguredFeature <?, ?>> FLOWER_SAKURA_GROVE = registerKey("flower_sakura_grove");
    public static final ResourceKey <ConfiguredFeature <?, ?>> FLOWER_REDWOOD_FOREST = registerKey("flower_redwood_forest");


    public static void bootstrap(BootstapContext <ConfiguredFeature <?, ?>> context) {
        var placedFeatureRegistryEntryLookup = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter <Block> holderGetter = context.lookup(Registries.BLOCK);

        register(context, REDWOOD_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(HibiscusBlocks.REDWOOD[2]),
                new StraightTrunkPlacer(12, 1, 4),
                BlockStateProvider.simple(HibiscusBlocks.REDWOOD_LEAVES),
                new SpruceFoliagePlacer(UniformInt.of(1, 3), UniformInt.of(1, 2), UniformInt.of(14, 15)),
                new TwoLayersFeatureSize(2, 0, 2)).ignoreVines().build());

        register(context, REDWOOD_TREE_SPAWN, Feature.RANDOM_SELECTOR,
                new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.REDWOOD_PLACED),
                        0.5f)), placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.REDWOOD_CHECKED)));

        register(context, WISTERIA_DELTA, HIBISCUS_DELTA_FEATURE, new DeltaFeatureConfiguration(Blocks.WATER.defaultBlockState(), Blocks.COARSE_DIRT.defaultBlockState(), UniformInt.of(4, 8), UniformInt.of(0, 4)));

        register(context, LARGE_REDWOOD_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(HibiscusBlocks.REDWOOD[2]),
                new GiantTrunkPlacer(18, 2, 17), BlockStateProvider.simple(HibiscusBlocks.REDWOOD_LEAVES),
                new MegaPineFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), UniformInt.of(6, 12)),
                new TwoLayersFeatureSize(2, 0, 2)).decorators(
                ImmutableList.of(new AlterGroundDecorator(BlockStateProvider.simple(Blocks.PODZOL)))).build());

        register(context, LARGE_REDWOOD_TREE_SPAWN, Feature.RANDOM_SELECTOR,
                new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.LARGE_REDWOOD_PLACED),
                        0.5f)), placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.LARGE_REDWOOD_CHECKED)));


        register(context, WHITE_WISTERIA_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(HibiscusBlocks.WISTERIA[2]),
                new WisteriaTrunkPlacer(7, 3, 4, UniformInt.of(1, 6), 0.80F, UniformInt.of(7, 10), holderGetter.getOrThrow(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)),
                BlockStateProvider.simple(HibiscusBlocks.WHITE_WISTERIA_LEAVES),
                new WisteriaFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)),
                new TwoLayersFeatureSize(2, 0, 2)).decorators(List.of(
                new WisteriaVinesTreeDecorator(0.45F,
                        new HibiscusSimpleBlockStateProvider(HibiscusBlocks.WHITE_WISTERIA_VINES_PLANT.defaultBlockState()),
                        new RandomizedIntStateProvider(BlockStateProvider.simple(HibiscusBlocks.WHITE_WISTERIA_VINES.defaultBlockState()), WisteriaVine.AGE, UniformInt.of(22, 25))
                ))).ignoreVines().build());

        register(context, PINK_WISTERIA_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(HibiscusBlocks.WISTERIA[2]),
                new WisteriaTrunkPlacer(7, 3, 4, UniformInt.of(1, 6), 0.80F, UniformInt.of(7, 10), BuiltInRegistries.BLOCK.getOrCreateTag(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)),
                BlockStateProvider.simple(HibiscusBlocks.PINK_WISTERIA_LEAVES),
                new WisteriaFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)),
                new TwoLayersFeatureSize(2, 0, 2)).decorators(List.of(
                new WisteriaVinesTreeDecorator(0.45F,
                        new HibiscusSimpleBlockStateProvider(HibiscusBlocks.PINK_WISTERIA_VINES_PLANT.defaultBlockState()),
                        new RandomizedIntStateProvider(BlockStateProvider.simple(HibiscusBlocks.PINK_WISTERIA_VINES.defaultBlockState()), WisteriaVine.AGE, UniformInt.of(22, 25))
                ))).ignoreVines().build());

        register(context, BLUE_WISTERIA_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(HibiscusBlocks.WISTERIA[2]),
                new WisteriaTrunkPlacer(7, 3, 4, UniformInt.of(1, 6), 0.80F, UniformInt.of(7, 10), BuiltInRegistries.BLOCK.getOrCreateTag(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)),
                BlockStateProvider.simple(HibiscusBlocks.BLUE_WISTERIA_LEAVES),
                new WisteriaFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)),
                new TwoLayersFeatureSize(2, 0, 2)).decorators(List.of(
                new WisteriaVinesTreeDecorator(0.45F,
                        new HibiscusSimpleBlockStateProvider(HibiscusBlocks.BLUE_WISTERIA_VINES_PLANT.defaultBlockState()),
                        new RandomizedIntStateProvider(BlockStateProvider.simple(HibiscusBlocks.BLUE_WISTERIA_VINES.defaultBlockState()), WisteriaVine.AGE, UniformInt.of(22, 25))
                ))).ignoreVines().build());

        register(context, PURPLE_WISTERIA_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(HibiscusBlocks.WISTERIA[2]),
                new WisteriaTrunkPlacer(7, 3, 4, UniformInt.of(1, 6), 0.80F, UniformInt.of(7, 10), BuiltInRegistries.BLOCK.getOrCreateTag(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)),
                BlockStateProvider.simple(HibiscusBlocks.PURPLE_WISTERIA_LEAVES),
                new WisteriaFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)),
                new TwoLayersFeatureSize(2, 0, 2)).decorators(List.of(
                new WisteriaVinesTreeDecorator(0.45F,
                        new HibiscusSimpleBlockStateProvider(HibiscusBlocks.PURPLE_WISTERIA_VINES_PLANT.defaultBlockState()),
                        new RandomizedIntStateProvider(BlockStateProvider.simple(HibiscusBlocks.PURPLE_WISTERIA_VINES.defaultBlockState()), WisteriaVine.AGE, UniformInt.of(22, 25))
                ))).ignoreVines().build());

        register(context, WISTERIA_SPAWN, Feature.RANDOM_SELECTOR,
                new RandomFeatureConfiguration(List.of(
                        new WeightedPlacedFeature(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.WHITE_WISTERIA_CHECKED), 0.10f),
                        new WeightedPlacedFeature(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.BLUE_WISTERIA_CHECKED), 0.325f),
                        new WeightedPlacedFeature(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.PURPLE_WISTERIA_CHECKED), 0.325f),
                        new WeightedPlacedFeature(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.PINK_WISTERIA_CHECKED), 0.25f)),
                        placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.WHITE_WISTERIA_CHECKED)));

        register(context, PINK_SAKURA_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(HibiscusBlocks.SAKURA[2]),
                new SakuraTrunkPlacer(13, 3, 3), BlockStateProvider.simple(HibiscusBlocks.PINK_SAKURA_LEAVES),
                new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4),
                new TwoLayersFeatureSize(1, 0, 1, OptionalInt.of(5))).ignoreVines().build());
        register(context, WHITE_SAKURA_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(HibiscusBlocks.SAKURA[2]),
                new SakuraTrunkPlacer(13, 3, 3), BlockStateProvider.simple(HibiscusBlocks.WHITE_SAKURA_LEAVES),
                new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4),
                new TwoLayersFeatureSize(1, 0, 1, OptionalInt.of(5))).ignoreVines().build());

        register(context, PINK_SAKURA_TREE_SAPLING, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(HibiscusBlocks.SAKURA[2]),
                new SakuraTrunkPlacerSapling(9, 3, 3), BlockStateProvider.simple(HibiscusBlocks.PINK_SAKURA_LEAVES),
                new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4),
                new TwoLayersFeatureSize(1, 0, 1, OptionalInt.of(5))).ignoreVines().build());
        register(context, WHITE_SAKURA_TREE_SAPLING, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(HibiscusBlocks.SAKURA[2]),
                new SakuraTrunkPlacerSapling(9, 3, 3), BlockStateProvider.simple(HibiscusBlocks.WHITE_SAKURA_LEAVES),
                new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4),
                new TwoLayersFeatureSize(1, 0, 1, OptionalInt.of(5))).ignoreVines().build());

        register(context, SAKURA_SPAWN, Feature.RANDOM_SELECTOR,
                new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.WHITE_SAKURA_CHECKED), 0.325f)),
                        placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.PINK_SAKURA_CHECKED)));
//
        register(context, FANCY_OAK_TREE_SPAWN, Feature.RANDOM_SELECTOR,
                new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(placedFeatureRegistryEntryLookup.getOrThrow(TreePlacements.FANCY_OAK_BEES), 0.03F)),
                        placedFeatureRegistryEntryLookup.getOrThrow(TreePlacements.FANCY_OAK_CHECKED)));

        register(context, OAK_BUSH, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new StraightTrunkPlacer(1, 0, 0),
                BlockStateProvider.simple(Blocks.OAK_LEAVES),
                new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2),
                new TwoLayersFeatureSize(0, 0, 0)).build());
        register(context, OAK_BUSH_SPAWN, Feature.RANDOM_SELECTOR,
                new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.OAK_BUSH_PLACED), 0.5f)),
                        placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.OAK_BUSH_CHECKED)));

        register(context, SPRUCE_BUSH, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.SPRUCE_LOG),
                        new StraightTrunkPlacer(1, 0, 0), BlockStateProvider.simple(Blocks.SPRUCE_LEAVES),
                        new RandomSpreadFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), ConstantInt.of(2), 75),
                        new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, SPRUCE_BUSH_SPAWN, Feature.RANDOM_SELECTOR,
                new RandomFeatureConfiguration(List.of(
                        new WeightedPlacedFeature(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.SPRUCE_BUSH_PLACED), 0.5f)),
                        placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.SPRUCE_BUSH_CHECKED)));

        register(context, FLOWER_WISTERIA_FOREST, Feature.FLOWER, new RandomPatchConfiguration(
                96, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                new NoiseProvider(2445L, new NormalNoise.NoiseParameters(0, 1.0D), 0.030833334F, List.of(
                        Blocks.ALLIUM.defaultBlockState(),
                        HibiscusBlocks.BLUEBELL.defaultBlockState(),
                        HibiscusBlocks.ANEMONE.defaultBlockState(),
                        Blocks.OXEYE_DAISY.defaultBlockState(),
                        Blocks.PINK_TULIP.defaultBlockState(),
                        HibiscusBlocks.GARDENIA.defaultBlockState(),
                        HibiscusBlocks.LAVENDER.defaultBlockState(),
                        HibiscusBlocks.HIBISCUS.defaultBlockState(),
                        Blocks.CORNFLOWER.defaultBlockState()))))));

        register(context, FLOWER_SAKURA_GROVE, Feature.FLOWER, new RandomPatchConfiguration(
                96, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                new NoiseProvider(2445L, new NormalNoise.NoiseParameters(0, 1.0D), 0.030833334F, List.of(
                        Blocks.ALLIUM.defaultBlockState(),
                        Blocks.LILAC.defaultBlockState(),
                        HibiscusBlocks.ANEMONE.defaultBlockState(),
                        Blocks.LILY_OF_THE_VALLEY.defaultBlockState(),
                        Blocks.PINK_TULIP.defaultBlockState(),
                        HibiscusBlocks.GARDENIA.defaultBlockState(),
                        HibiscusBlocks.LAVENDER.defaultBlockState(),
                        Blocks.PEONY.defaultBlockState()))))));

        register(context, FLOWER_REDWOOD_FOREST, Feature.FLOWER, new RandomPatchConfiguration(
                36, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                new NoiseProvider(2445L, new NormalNoise.NoiseParameters(0, 1.0D), 0.030833334F, List.of(
                        HibiscusBlocks.BLUEBELL.defaultBlockState(),
                        Blocks.ORANGE_TULIP.defaultBlockState(),
                        Blocks.GRASS.defaultBlockState(),
                        HibiscusBlocks.CARNATION.defaultBlockState(),
                        HibiscusBlocks.GARDENIA.defaultBlockState()))))));

    }

    public static ResourceKey <ConfiguredFeature <?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(NatureSpirit.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature <FC>> void register(BootstapContext <ConfiguredFeature <?, ?>> context, ResourceKey <ConfiguredFeature <?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature <>(feature, configuration));
    }

    public static void registerConfiguredFeatures() {
        System.out.println("Registering Configured Features For:" + NatureSpirit.MOD_ID);
    }
}


