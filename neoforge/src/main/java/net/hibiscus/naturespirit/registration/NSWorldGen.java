package net.hibiscus.naturespirit.registration;

import net.hibiscus.naturespirit.world.carver.ReplaceableCaveCarver;
import net.hibiscus.naturespirit.world.carver.ReplaceableCaveCarverConfig;
import net.hibiscus.naturespirit.world.carver.ReplaceableRavineCarver;
import net.hibiscus.naturespirit.world.carver.ReplaceableRavineCarverConfig;
import net.hibiscus.naturespirit.world.feature.*;
import net.hibiscus.naturespirit.world.foliage_placer.*;
import net.hibiscus.naturespirit.world.tree_decorator.*;
import net.hibiscus.naturespirit.world.trunk.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;

public class NSWorldGen {

  public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS = DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, MOD_ID);
  public static final Supplier<TrunkPlacerType<WisteriaTrunkPlacer>> WISTERIA_TRUNK_PLACER = TRUNK_PLACERS.register("wisteria_trunk_placer", () -> new TrunkPlacerType(WisteriaTrunkPlacer.CODEC));
  public static final Supplier<TrunkPlacerType<BushTrunkPlacer>> BUSH_TRUNK_PLACER = TRUNK_PLACERS.register("bush_trunk_placer", () -> new TrunkPlacerType(BushTrunkPlacer.CODEC));
  public static final Supplier<TrunkPlacerType<SugiTrunkPlacer>> SUGI_TRUNK_PLACER = TRUNK_PLACERS.register("sugi_trunk_placer", () -> new TrunkPlacerType(SugiTrunkPlacer.CODEC));
  public static final Supplier<TrunkPlacerType<OliveTrunkPlacer>> OLIVE_TRUNK_PLACER = TRUNK_PLACERS.register("olive_trunk_placer", () -> new TrunkPlacerType(OliveTrunkPlacer.CODEC));
  public static final Supplier<TrunkPlacerType<PaloVerdeTrunkPlacer>> PALO_VERDE_TRUNK_PLACER = TRUNK_PLACERS.register("palo_verde_trunk_placer", () -> new TrunkPlacerType(PaloVerdeTrunkPlacer.CODEC));
  public static final Supplier<TrunkPlacerType<SaxaulTrunkPlacer>> SAXAUL_TRUNK_PLACER = TRUNK_PLACERS.register("saxaul_trunk_placer", () -> new TrunkPlacerType(SaxaulTrunkPlacer.CODEC));
  public static final Supplier<TrunkPlacerType<GhafTrunkPlacer>> GHAF_TRUNK_PLACER = TRUNK_PLACERS.register("ghaf_trunk_placer", () -> new TrunkPlacerType(GhafTrunkPlacer.CODEC));
  public static final Supplier<TrunkPlacerType<MapleTrunkPlacer>> MAPLE_TRUNK_PLACER = TRUNK_PLACERS.register("maple_trunk_placer", () -> new TrunkPlacerType(MapleTrunkPlacer.CODEC));
  public static final Supplier<TrunkPlacerType<MahoganyTrunkPlacer>> MAHOGANY_TRUNK_PLACER = TRUNK_PLACERS.register("mahogany_trunk_placer", () -> new TrunkPlacerType(MahoganyTrunkPlacer.CODEC));
  public static final Supplier<TrunkPlacerType<CoconutTrunkPlacer>> COCONUT_TRUNK_PLACER = TRUNK_PLACERS.register("coconut_trunk_placer", () -> new TrunkPlacerType(CoconutTrunkPlacer.CODEC));
  public static final Supplier<TrunkPlacerType<RedwoodTrunkPlacer>> REDWOOD_TRUNK_PLACER = TRUNK_PLACERS.register("redwood_trunk_placer", () -> new TrunkPlacerType(RedwoodTrunkPlacer.CODEC));


  public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATORS = DeferredRegister.create(Registries.TREE_DECORATOR_TYPE, MOD_ID);
  public static final Supplier<TreeDecoratorType<WisteriaVinesTreeDecorator>> WISTERIA_VINES_TREE_DECORATOR = TREE_DECORATORS.register("wisteria_vines_tree_decorator", () -> new TreeDecoratorType(WisteriaVinesTreeDecorator.CODEC));
  public static final Supplier<TreeDecoratorType<MapleGroundTreeDecorator>> MAPLE_GROUND_TREE_DECORATOR = TREE_DECORATORS.register("maple_ground_tree_decorator", () -> new TreeDecoratorType(MapleGroundTreeDecorator.CODEC));
  public static final Supplier<TreeDecoratorType<CoconutTreeDecorator>> COCONUT_TREE_DECORATOR = TREE_DECORATORS.register("coconut_tree_decorator", () -> new TreeDecoratorType(CoconutTreeDecorator.CODEC));
  public static final Supplier<TreeDecoratorType<RedwoodBranchTreeDecorator>> REDWOOD_BRANCH_DECORATOR = TREE_DECORATORS.register("redwood_branch_decorator", () -> new TreeDecoratorType(RedwoodBranchTreeDecorator.CODEC));
  public static final Supplier<TreeDecoratorType<OliveBranchTreeDecorator>> OLIVE_BRANCH_DECORATOR = TREE_DECORATORS.register("olive_branch_decorator", () -> new TreeDecoratorType(OliveBranchTreeDecorator.CODEC));
  public static final Supplier<TreeDecoratorType<SnowTreeDecorator>> SNOW_DECORATOR = TREE_DECORATORS.register("snow_decorator", () -> new TreeDecoratorType(SnowTreeDecorator.CODEC));
  public static final Supplier<TreeDecoratorType<PolyporeTreeDecorator>> POLYPORE_DECORATOR = TREE_DECORATORS.register("polypore_decorator", () -> new TreeDecoratorType(PolyporeTreeDecorator.CODEC));


  public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, MOD_ID);
  public static final Supplier<FoliagePlacerType<WisteriaFoliagePlacer>> WISTERIA_FOLIAGE_PLACER_TYPE = FOLIAGE_PLACERS.register("wisteria_foliage_placer", () -> new FoliagePlacerType(WisteriaFoliagePlacer.CODEC));
  public static final Supplier<FoliagePlacerType<SugiFoliagePlacer>> SUGI_FOLIAGE_PLACER_TYPE = FOLIAGE_PLACERS.register("sugi_foliage_placer", () -> new FoliagePlacerType(SugiFoliagePlacer.CODEC));
  public static final Supplier<FoliagePlacerType<AspenFoliagePlacer>> ASPEN_FOLIAGE_PLACER_TYPE = FOLIAGE_PLACERS.register("aspen_foliage_placer", () -> new FoliagePlacerType(AspenFoliagePlacer.CODEC));
  public static final Supplier<FoliagePlacerType<FirFoliagePlacer>> FIR_FOLIAGE_PLACER_TYPE = FOLIAGE_PLACERS.register("fir_foliage_placer", () -> new FoliagePlacerType(FirFoliagePlacer.CODEC));
  public static final Supplier<FoliagePlacerType<LarchFoliagePlacer>> LARCH_FOLIAGE_PLACER_TYPE = FOLIAGE_PLACERS.register("larch_foliage_placer", () -> new FoliagePlacerType(LarchFoliagePlacer.CODEC));
  public static final Supplier<FoliagePlacerType<RedwoodFoliagePlacer>> REDWOOD_FOLIAGE_PLACER_TYPE = FOLIAGE_PLACERS.register("redwood_foliage_placer", () -> new FoliagePlacerType(RedwoodFoliagePlacer.CODEC));
  public static final Supplier<FoliagePlacerType<CypressFoliagePlacer>> CYPRESS_FOLIAGE_PLACER_TYPE = FOLIAGE_PLACERS.register("cypress_foliage_placer", () -> new FoliagePlacerType(CypressFoliagePlacer.CODEC));
  public static final Supplier<FoliagePlacerType<MapleFoliagePlacer>> MAPLE_FOLIAGE_PLACER_TYPE = FOLIAGE_PLACERS.register("maple_foliage_placer", () -> new FoliagePlacerType(MapleFoliagePlacer.CODEC));
  public static final Supplier<FoliagePlacerType<CoconutFoliagePlacer>> COCONUT_FOLIAGE_PLACER_TYPE = FOLIAGE_PLACERS.register("coconut_foliage_placer", () -> new FoliagePlacerType(CoconutFoliagePlacer.CODEC));
  public static final Supplier<FoliagePlacerType<BirchFoliagePlacer>> BIRCH_FOLIAGE_PLACER_TYPE = FOLIAGE_PLACERS.register("birch_foliage_placer", () -> new FoliagePlacerType(BirchFoliagePlacer.CODEC));
  public static final Supplier<FoliagePlacerType<GroundedBushFoliagePlacer>> GROUNDED_BUSH_PLACER_TYPE = FOLIAGE_PLACERS.register("grounded_bush_foliage_placer", () -> new FoliagePlacerType(GroundedBushFoliagePlacer.CODEC));


  public static final DeferredRegister<WorldCarver<?>> CARVERS = DeferredRegister.create(Registries.CARVER, MOD_ID);
  public static final Supplier<ReplaceableCaveCarver> REPLACEABLE_CAVE_CARVER = CARVERS.register("replaceable_cave", () -> new ReplaceableCaveCarver(ReplaceableCaveCarverConfig.CAVE_CODEC));
  public static final Supplier<ReplaceableRavineCarver> REPLACEABLE_RAVINE_CARVER = CARVERS.register("replaceable_canyon", () -> new ReplaceableRavineCarver(ReplaceableRavineCarverConfig.RAVINE_CODEC));

  public static final ResourceKey<NormalNoise.NoiseParameters> SUGI_PILLAR = ResourceKey.create(Registries.NOISE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "sugi_pillar"));
  public static final ResourceKey<NormalNoise.NoiseParameters> SUGI_PILLAR_ROOF = ResourceKey.create(Registries.NOISE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "sugi_pillar_roof"));
  public static final ResourceKey<NormalNoise.NoiseParameters> SUGI_SURFACE = ResourceKey.create(Registries.NOISE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "sugi_surface"));

  public static final ResourceKey<NormalNoise.NoiseParameters> STRATIFIED_DESERT_PILLAR = ResourceKey.create(Registries.NOISE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "stratified_desert_pillar"));
  public static final ResourceKey<NormalNoise.NoiseParameters> STRATIFIED_DESERT_PILLAR_ROOF = ResourceKey.create(Registries.NOISE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "stratified_desert_pillar_roof"));
  public static final ResourceKey<NormalNoise.NoiseParameters> STRATIFIED_DESERT_SURFACE = ResourceKey.create(Registries.NOISE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "stratified_desert_surface"));

  public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, MOD_ID);
  public static final Supplier<NSDeltaFeature> HIBISCUS_DELTA_FEATURE = FEATURES.register("water_delta_feature", () -> new NSDeltaFeature(DeltaFeatureConfiguration.CODEC));
  public static final Supplier<PumpkinPatchFeature> HIBISCUS_PUMPKIN_PATCH_FEATURE = FEATURES.register("pumpkin_patch_feature", () -> new PumpkinPatchFeature(OreConfiguration.CODEC));
  public static final Supplier<LargePumpkinFeature> HIBISCUS_LARGE_PUMPKIN_FEATURE = FEATURES.register("large_pumpkin_feature", () -> new LargePumpkinFeature(BlockPileConfiguration.CODEC));
  public static final Supplier<TurnipRootFeature> HIBISCUS_TURNIP_ROOT_FEATURE = FEATURES.register("turnip_root_feature", () -> new TurnipRootFeature(TurnipRootFeatureConfig.CODEC));
  public static final Supplier<JoshuaTreeFeature> JOSHUA_TREE_FEATURE = FEATURES.register("joshua_tree_feature", () -> new JoshuaTreeFeature(NoneFeatureConfiguration.CODEC));
  public static final Supplier<AlluaudiaFeature> ALLUAUDIA_FEATURE = FEATURES.register("alluaudia_feature", () -> new AlluaudiaFeature(NoneFeatureConfiguration.CODEC));
  public static final Supplier<HugeShiitakeMushroomFeature> HUGE_SHIITAKE_MUSHROOM_FEATURE = FEATURES.register("huge_shiitake_mushroom_feature", () -> new HugeShiitakeMushroomFeature(HugeMushroomFeatureConfiguration.CODEC));
  public static final Supplier<HugeRedMushroomFeature> HUGE_RED_MUSHROOM_FEATURE = FEATURES.register("huge_red_mushroom_feature", () -> new HugeRedMushroomFeature(HugeMushroomFeatureConfig.CODEC));
  public static final Supplier<HugeBrownMushroomFeature> HUGE_BROWN_MUSHROOM_FEATURE = FEATURES.register("huge_brown_mushroom_feature", () -> new HugeBrownMushroomFeature(HugeMushroomFeatureConfig.CODEC));
  public static final Supplier<PolyporeFeature> POLYPORE_FEATURE = FEATURES.register("polypore_feature", () -> new PolyporeFeature(NoneFeatureConfiguration.CODEC));
  public static final Supplier<LotusPlantFeature> LOTUS_PLANT_FEATURE = FEATURES.register("lotus_plant_feature", () -> new LotusPlantFeature(NoneFeatureConfiguration.CODEC));
  public static final Supplier<LeveledRandomPatch> LEVELED_RANDOM_PATCH_FEATURE = FEATURES.register("leveled_random_patch_feature", () -> new LeveledRandomPatch(RandomPatchConfiguration.CODEC));

}
