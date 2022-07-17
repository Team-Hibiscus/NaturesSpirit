package net.hibiscus.naturespirit.world.feature;

import com.google.common.collect.ImmutableList;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.blocks.HibiscusBlocks;
import net.hibiscus.naturespirit.blocks.WisteriaVine;
import net.hibiscus.naturespirit.blocks.WisteriaVinePlant;
import net.hibiscus.naturespirit.world.feature.tree_decorator.WisteriaVinesTreeDecorator;
import net.hibiscus.naturespirit.world.feature.trunk.WisteriaTrunkPlacer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PropaguleBlock;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.AcaciaFoliagePlacer;
import net.minecraft.world.gen.foliage.MegaPineFoliagePlacer;
import net.minecraft.world.gen.foliage.SpruceFoliagePlacer;
import net.minecraft.world.gen.stateprovider.*;
import net.minecraft.world.gen.treedecorator.AlterGroundTreeDecorator;
import net.minecraft.world.gen.treedecorator.AttachedToLeavesTreeDecorator;
import net.minecraft.world.gen.trunk.GiantTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;

public class HibiscusConfiguredFeatures {
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> LARGE_REDWOOD_TREE = ConfiguredFeatures.register("large_redwood_tree", Feature.TREE, (new TreeFeatureConfig.Builder(BlockStateProvider.of(HibiscusBlocks.REDWOOD[2]), new GiantTrunkPlacer(15, 2, 17), BlockStateProvider.of(HibiscusBlocks.REDWOOD_LEAVES), new MegaPineFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0), UniformIntProvider.create(15, 20)), new TwoLayersFeatureSize(1, 1, 2))).decorators(ImmutableList.of(new AlterGroundTreeDecorator(BlockStateProvider.of(Blocks.PODZOL)))).build());

    public static final RegistryEntry<PlacedFeature> LARGE_REDWOOD_CHECKED =
            PlacedFeatures.register("large_redwood_checked", LARGE_REDWOOD_TREE,
                    PlacedFeatures.wouldSurvive(HibiscusBlocks.REDWOOD_SAPLING));

    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> LARGE_REDWOOD_SPAWN =
            ConfiguredFeatures.register("large_redwood_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfig(List.of(new RandomFeatureEntry(LARGE_REDWOOD_CHECKED, 0.5f)),
                            LARGE_REDWOOD_CHECKED));

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> REDWOOD_TREE = ConfiguredFeatures.register("redwood_tree", Feature.TREE, (new TreeFeatureConfig.Builder(BlockStateProvider.of(HibiscusBlocks.REDWOOD[2]), new StraightTrunkPlacer(10, 2, 1), BlockStateProvider.of(HibiscusBlocks.REDWOOD_LEAVES),  new SpruceFoliagePlacer(UniformIntProvider.create(2, 3), UniformIntProvider.create(2, 6), UniformIntProvider.create(2, 10)), new TwoLayersFeatureSize(2, 0, 2))).ignoreVines().build());

    public static final RegistryEntry<PlacedFeature> REDWOOD_CHECKED =
            PlacedFeatures.register("redwood_checked", REDWOOD_TREE,
                    PlacedFeatures.wouldSurvive(HibiscusBlocks.REDWOOD_SAPLING));

    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> REDWOOD_SPAWN =
            ConfiguredFeatures.register("redwood_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfig(List.of(new RandomFeatureEntry(REDWOOD_CHECKED, 0.5f)),
                            REDWOOD_CHECKED));

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> WHITE_WISTERIA_TREE = ConfiguredFeatures.register
            ("white_wisteria_tree", Feature.TREE,
                    (new TreeFeatureConfig.Builder
                            (BlockStateProvider.of(HibiscusBlocks.WISTERIA[2]),
                                    new WisteriaTrunkPlacer(5, 4, 8, UniformIntProvider.create(1, 6), 0.95F, UniformIntProvider.create(2, 7), Registry.BLOCK.getOrCreateEntryList(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)),
                                    BlockStateProvider.of(HibiscusBlocks.WHITE_WISTERIA_LEAVES),
                                    new AcaciaFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0)),
                                    new TwoLayersFeatureSize(2, 0, 2))).decorators(List.of(
                                            new WisteriaVinesTreeDecorator(0.25F,
                                            new HibiscusSimpleBlockStateProvider(HibiscusBlocks.WHITE_WISTERIA_VINES_PLANT.getDefaultState()),
                                    new RandomizedIntBlockStateProvider(BlockStateProvider.of((BlockState)HibiscusBlocks.WHITE_WISTERIA_VINES.getDefaultState()), WisteriaVine.AGE, UniformIntProvider.create(2, 15))
                            )
                    )).ignoreVines().build());
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> PINK_WISTERIA_TREE = ConfiguredFeatures.register
            ("pink_wisteria_tree", Feature.TREE,
                    (new TreeFeatureConfig.Builder
                            (BlockStateProvider.of(HibiscusBlocks.WISTERIA[2]),
                                    new WisteriaTrunkPlacer(5, 4, 8, UniformIntProvider.create(1, 6), 0.95F, UniformIntProvider.create(2, 7), Registry.BLOCK.getOrCreateEntryList(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)),
                                    BlockStateProvider.of(HibiscusBlocks.PINK_WISTERIA_LEAVES),
                                    new AcaciaFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0)),
                                    new TwoLayersFeatureSize(2, 0, 2))).decorators(List.of(
                            new WisteriaVinesTreeDecorator(0.25F,
                                    new HibiscusSimpleBlockStateProvider(HibiscusBlocks.PINK_WISTERIA_VINES_PLANT.getDefaultState()),
                                    new RandomizedIntBlockStateProvider(BlockStateProvider.of((BlockState)HibiscusBlocks.PINK_WISTERIA_VINES.getDefaultState()), WisteriaVine.AGE, UniformIntProvider.create(2, 15))
                            )
                    )).ignoreVines().build());
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> BLUE_WISTERIA_TREE = ConfiguredFeatures.register
            ("blue_wisteria_tree", Feature.TREE,
                    (new TreeFeatureConfig.Builder
                            (BlockStateProvider.of(HibiscusBlocks.WISTERIA[2]),
                                    new WisteriaTrunkPlacer(5, 4, 8, UniformIntProvider.create(1, 6), 0.95F, UniformIntProvider.create(2, 7), Registry.BLOCK.getOrCreateEntryList(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)),
                                    BlockStateProvider.of(HibiscusBlocks.BLUE_WISTERIA_LEAVES),
                                    new AcaciaFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0)),
                                    new TwoLayersFeatureSize(2, 0, 2))).decorators(List.of(
                            new WisteriaVinesTreeDecorator(0.25F,
                                    new HibiscusSimpleBlockStateProvider(HibiscusBlocks.BLUE_WISTERIA_VINES_PLANT.getDefaultState()),
                                    new RandomizedIntBlockStateProvider(BlockStateProvider.of((BlockState)HibiscusBlocks.BLUE_WISTERIA_VINES.getDefaultState()), WisteriaVine.AGE, UniformIntProvider.create(2, 15))
                            )
                    )).ignoreVines().build());

    public static final RegistryEntry<PlacedFeature> WHITE_WISTERIA_CHECKED =
            PlacedFeatures.register("white_wisteria_checked", WHITE_WISTERIA_TREE,
                    PlacedFeatures.wouldSurvive(HibiscusBlocks.WHITE_WISTERIA_SAPLING));

    public static final RegistryEntry<PlacedFeature> BLUE_WISTERIA_CHECKED =
            PlacedFeatures.register("blue_wisteria_checked", BLUE_WISTERIA_TREE,
                    PlacedFeatures.wouldSurvive(HibiscusBlocks.BLUE_WISTERIA_SAPLING));

    public static final RegistryEntry<PlacedFeature> PINK_WISTERIA_CHECKED =
            PlacedFeatures.register("pink_wisteria_checked", PINK_WISTERIA_TREE,
                    PlacedFeatures.wouldSurvive(HibiscusBlocks.PINK_WISTERIA_SAPLING));

    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> WHITE_WISTERIA_SPAWN =
            ConfiguredFeatures.register("white_wisteria_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfig(List.of(new RandomFeatureEntry(WHITE_WISTERIA_CHECKED, 0.20f)),
                            WHITE_WISTERIA_CHECKED));
    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> BLUE_WISTERIA_SPAWN =
            ConfiguredFeatures.register("blue_wisteria_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfig(List.of(new RandomFeatureEntry(BLUE_WISTERIA_CHECKED, 0.20f)),
                            BLUE_WISTERIA_CHECKED));
    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> PINK_WISTERIA_SPAWN =
            ConfiguredFeatures.register("pink_wisteria_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfig(List.of(new RandomFeatureEntry(PINK_WISTERIA_CHECKED, 0.20f)),
                            PINK_WISTERIA_CHECKED));


    public static void registerConfiguredFeatures() {
        System.out.println("Registering Configured Features For:" + NatureSpirit.MOD_ID);
    }
}
