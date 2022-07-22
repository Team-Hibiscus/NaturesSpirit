package net.hibiscus.naturespirit.world.feature;

import com.google.common.collect.ImmutableList;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.blocks.HibiscusBlocks;
import net.hibiscus.naturespirit.blocks.WisteriaVine;
import net.hibiscus.naturespirit.world.feature.foliage_placer.WisteriaFoliagePlacer;
import net.hibiscus.naturespirit.world.feature.tree_decorator.WisteriaVinesTreeDecorator;
import net.hibiscus.naturespirit.world.feature.trunk.WisteriaTrunkPlacer;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaPineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RandomizedIntStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AlterGroundDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public class HibiscusConfiguredFeatures {
    public static final Holder <ConfiguredFeature <TreeConfiguration, ?>> LARGE_REDWOOD_TREE = FeatureUtils.register("large_redwood_tree", Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(HibiscusBlocks.REDWOOD[2]), new GiantTrunkPlacer(15, 2, 17), BlockStateProvider.simple(HibiscusBlocks.REDWOOD_LEAVES), new MegaPineFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), UniformInt.of(15, 20)), new TwoLayersFeatureSize(1, 1, 2))).decorators(ImmutableList.of(new AlterGroundDecorator(BlockStateProvider.simple(Blocks.PODZOL)))).build());

    public static final Holder<PlacedFeature> LARGE_REDWOOD_CHECKED =
            PlacementUtils.register("large_redwood_checked", LARGE_REDWOOD_TREE,
                    PlacementUtils.filteredByBlockSurvival(HibiscusBlocks.REDWOOD_SAPLING));

    public static final Holder <ConfiguredFeature <RandomFeatureConfiguration, ?>> LARGE_REDWOOD_SPAWN =
            FeatureUtils.register("large_redwood_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(LARGE_REDWOOD_CHECKED, 0.5f)),
                            LARGE_REDWOOD_CHECKED));

    public static final Holder <ConfiguredFeature <TreeConfiguration, ?>> REDWOOD_TREE = FeatureUtils.register("redwood_tree", Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(HibiscusBlocks.REDWOOD[2]), new StraightTrunkPlacer(10, 2, 1), BlockStateProvider.simple(HibiscusBlocks.REDWOOD_LEAVES), new SpruceFoliagePlacer(UniformInt.of(2, 3), UniformInt.of(2, 6), UniformInt.of(2, 10)), new TwoLayersFeatureSize(2, 0, 2))).ignoreVines().build());

    public static final Holder <PlacedFeature> REDWOOD_CHECKED =
            PlacementUtils.register("redwood_checked", REDWOOD_TREE,
                    PlacementUtils.filteredByBlockSurvival(HibiscusBlocks.REDWOOD_SAPLING));

    public static final Holder <ConfiguredFeature <RandomFeatureConfiguration, ?>> REDWOOD_SPAWN =
            FeatureUtils.register("redwood_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(REDWOOD_CHECKED, 0.5f)),
                            REDWOOD_CHECKED));

    public static final Holder <ConfiguredFeature <TreeConfiguration, ?>> WHITE_WISTERIA_TREE = FeatureUtils.register
            ("white_wisteria_tree", Feature.TREE,
                    (new TreeConfiguration.TreeConfigurationBuilder
                            (BlockStateProvider.simple(HibiscusBlocks.WISTERIA[2]),
                                    new WisteriaTrunkPlacer(7, 3, 4, UniformInt.of(1, 6), 0.80F, UniformInt.of(7, 10), Registry.BLOCK.getOrCreateTag(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)),
                                    BlockStateProvider.simple(HibiscusBlocks.WHITE_WISTERIA_LEAVES),
                                    new WisteriaFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)),
                                    new TwoLayersFeatureSize(2, 0, 2))).decorators(List.of(
                            new WisteriaVinesTreeDecorator(0.45F,
                                    new HibiscusSimpleBlockStateProvider(HibiscusBlocks.WHITE_WISTERIA_VINES_PLANT.defaultBlockState()),
                                    new RandomizedIntStateProvider(BlockStateProvider.simple(HibiscusBlocks.WHITE_WISTERIA_VINES.defaultBlockState()), WisteriaVine.AGE, UniformInt.of(22, 25))
                            )
                    )).ignoreVines().build());
    public static final Holder <ConfiguredFeature <TreeConfiguration, ?>> PINK_WISTERIA_TREE = FeatureUtils.register
            ("pink_wisteria_tree", Feature.TREE,
                    (new TreeConfiguration.TreeConfigurationBuilder
                            (BlockStateProvider.simple(HibiscusBlocks.WISTERIA[2]),
                                    new WisteriaTrunkPlacer(7, 3, 4, UniformInt.of(1, 6), 0.80F, UniformInt.of(7, 10), Registry.BLOCK.getOrCreateTag(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)),
                                    BlockStateProvider.simple(HibiscusBlocks.PINK_WISTERIA_LEAVES),
                                    new WisteriaFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)),
                                    new TwoLayersFeatureSize(2, 0, 2))).decorators(List.of(
                            new WisteriaVinesTreeDecorator(0.45F,
                                    new HibiscusSimpleBlockStateProvider(HibiscusBlocks.PINK_WISTERIA_VINES_PLANT.defaultBlockState()),
                                    new RandomizedIntStateProvider(BlockStateProvider.simple(HibiscusBlocks.PINK_WISTERIA_VINES.defaultBlockState()), WisteriaVine.AGE, UniformInt.of(22, 25))
                            )
                    )).ignoreVines().build());
    public static final Holder <ConfiguredFeature <TreeConfiguration, ?>> BLUE_WISTERIA_TREE = FeatureUtils.register
            ("blue_wisteria_tree", Feature.TREE,
                    (new TreeConfiguration.TreeConfigurationBuilder
                            (BlockStateProvider.simple(HibiscusBlocks.WISTERIA[2]),
                                    new WisteriaTrunkPlacer(7, 3, 4, UniformInt.of(1, 6), 0.80F, UniformInt.of(7, 10), Registry.BLOCK.getOrCreateTag(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)),
                                    BlockStateProvider.simple(HibiscusBlocks.BLUE_WISTERIA_LEAVES),
                                    new WisteriaFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)),
                                    new TwoLayersFeatureSize(2, 0, 2))).decorators(List.of(
                            new WisteriaVinesTreeDecorator(0.45F,
                                    new HibiscusSimpleBlockStateProvider(HibiscusBlocks.BLUE_WISTERIA_VINES_PLANT.defaultBlockState()),
                                    new RandomizedIntStateProvider(BlockStateProvider.simple(HibiscusBlocks.BLUE_WISTERIA_VINES.defaultBlockState()), WisteriaVine.AGE, UniformInt.of(22, 25))
                            )
                    )).ignoreVines().build());
    public static final Holder <ConfiguredFeature <TreeConfiguration, ?>> PURPLE_WISTERIA_TREE = FeatureUtils.register
            ("purple_wisteria_tree", Feature.TREE,
                    (new TreeConfiguration.TreeConfigurationBuilder
                            (BlockStateProvider.simple(HibiscusBlocks.WISTERIA[2]),
                                    new WisteriaTrunkPlacer(7, 3, 4, UniformInt.of(1, 6), 0.80F, UniformInt.of(7, 10), Registry.BLOCK.getOrCreateTag(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)),
                                    BlockStateProvider.simple(HibiscusBlocks.PURPLE_WISTERIA_LEAVES),
                                    new WisteriaFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)),
                                    new TwoLayersFeatureSize(2, 0, 2))).decorators(List.of(
                            new WisteriaVinesTreeDecorator(0.45F,
                                    new HibiscusSimpleBlockStateProvider(HibiscusBlocks.PURPLE_WISTERIA_VINES_PLANT.defaultBlockState()),
                                    new RandomizedIntStateProvider(BlockStateProvider.simple(HibiscusBlocks.PURPLE_WISTERIA_VINES.defaultBlockState()), WisteriaVine.AGE, UniformInt.of(22, 25))
                            )
                    )).ignoreVines().build());

    public static final Holder <PlacedFeature> WHITE_WISTERIA_CHECKED =
            PlacementUtils.register("white_wisteria_checked", WHITE_WISTERIA_TREE,
                    PlacementUtils.filteredByBlockSurvival(HibiscusBlocks.WHITE_WISTERIA_SAPLING));
    public static final Holder <ConfiguredFeature <RandomFeatureConfiguration, ?>> WHITE_WISTERIA_SPAWN =
            FeatureUtils.register("white_wisteria_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(WHITE_WISTERIA_CHECKED, 0.20f)),
                            WHITE_WISTERIA_CHECKED));
    public static final Holder <PlacedFeature> BLUE_WISTERIA_CHECKED =
            PlacementUtils.register("blue_wisteria_checked", BLUE_WISTERIA_TREE,
                    PlacementUtils.filteredByBlockSurvival(HibiscusBlocks.BLUE_WISTERIA_SAPLING));
    public static final Holder <ConfiguredFeature <RandomFeatureConfiguration, ?>> BLUE_WISTERIA_SPAWN =
            FeatureUtils.register("blue_wisteria_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(BLUE_WISTERIA_CHECKED, 0.20f)),
                            BLUE_WISTERIA_CHECKED));
    public static final Holder <PlacedFeature> PINK_WISTERIA_CHECKED =
            PlacementUtils.register("pink_wisteria_checked", PINK_WISTERIA_TREE,
                    PlacementUtils.filteredByBlockSurvival(HibiscusBlocks.PINK_WISTERIA_SAPLING));
    public static final Holder <ConfiguredFeature <RandomFeatureConfiguration, ?>> PINK_WISTERIA_SPAWN =
            FeatureUtils.register("pink_wisteria_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PINK_WISTERIA_CHECKED, 0.20f)),
                            PINK_WISTERIA_CHECKED));
    public static final Holder <PlacedFeature> PURPLE_WISTERIA_CHECKED =
            PlacementUtils.register("purple_wisteria_checked", PURPLE_WISTERIA_TREE,
                    PlacementUtils.filteredByBlockSurvival(HibiscusBlocks.PURPLE_WISTERIA_SAPLING));
    public static final Holder <ConfiguredFeature <RandomFeatureConfiguration, ?>> PURPLE_WISTERIA_SPAWN =
            FeatureUtils.register("purple_wisteria_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PURPLE_WISTERIA_CHECKED, 0.20f)),
                            PURPLE_WISTERIA_CHECKED));

    public static void registerConfiguredFeatures() {
        System.out.println("Registering Configured Features For:" + NatureSpirit.MOD_ID);
    }
}
