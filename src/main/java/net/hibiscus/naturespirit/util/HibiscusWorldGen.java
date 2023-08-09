package net.hibiscus.naturespirit.util;

import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.impl.biome.modification.BiomeSelectionContextImpl;
import net.hibiscus.naturespirit.mixin.BlockStateProviderMixin;
import net.hibiscus.naturespirit.mixin.FoliagePlacerMixin;
import net.hibiscus.naturespirit.mixin.TreeDecoratorMixin;
import net.hibiscus.naturespirit.world.feature.*;
import net.hibiscus.naturespirit.world.feature.foliage_placer.*;
import net.hibiscus.naturespirit.world.feature.tree_decorator.WisteriaVinesTreeDecorator;
import net.hibiscus.naturespirit.world.feature.trunk.OliveTrunkPlacer;
import net.hibiscus.naturespirit.world.feature.trunk.SugiTrunkPlacer;
import net.hibiscus.naturespirit.world.feature.trunk.WisteriaTrunkPlacer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.DeltaFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.stateprovider.BlockStateProviderType;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import static net.hibiscus.naturespirit.mixin.TrunkPlacerTypeMixin.callRegister;

public class HibiscusWorldGen {
   public static final TrunkPlacerType <WisteriaTrunkPlacer> WISTERIA_TRUNK_PLACER = callRegister("wisteria_trunk_placer",
           WisteriaTrunkPlacer.CODEC
   );
   public static final TrunkPlacerType <SugiTrunkPlacer> SUGI_TRUNK_PLACER = callRegister("sugi_trunk_placer",
           SugiTrunkPlacer.CODEC
   );
   public static final TrunkPlacerType <OliveTrunkPlacer> OLIVE_TRUNK_PLACER = callRegister("olive_trunk_placer",
           OliveTrunkPlacer.CODEC
   );
   public static final TreeDecoratorType <WisteriaVinesTreeDecorator> WISTERIA_VINES_TREE_DECORATOR = TreeDecoratorMixin.callRegister("wisteria_vines_tree_decorator",
           WisteriaVinesTreeDecorator.CODEC
   );
   public static final FoliagePlacerType <WisteriaFoliagePlacer> WISTERIA_FOLIAGE_PLACER_TYPE = FoliagePlacerMixin.callRegister("wisteria_foliage_placer",
           WisteriaFoliagePlacer.CODEC
   );
   public static final FoliagePlacerType <SugiFoliagePlacer> SUGI_FOLIAGE_PLACER_TYPE = FoliagePlacerMixin.callRegister("sugi_foliage_placer",
           SugiFoliagePlacer.CODEC
   );
   public static final FoliagePlacerType <AspenFoliagePlacer> ASPEN_FOLIAGE_PLACER_TYPE = FoliagePlacerMixin.callRegister("aspen_foliage_placer",
           AspenFoliagePlacer.CODEC
   );
   public static final FoliagePlacerType <FirFoliagePlacer> FIR_FOLIAGE_PLACER_TYPE = FoliagePlacerMixin.callRegister("fir_foliage_placer",
           FirFoliagePlacer.CODEC
   );
   public static final FoliagePlacerType <CypressFoliagePlacer> CYPRESS_FOLIAGE_PLACER_TYPE = FoliagePlacerMixin.callRegister("cypress_foliage_placer",
           CypressFoliagePlacer.CODEC
   );
   public static final BlockStateProviderType <HibiscusSimpleBlockStateProvider> HIBISCUS_SIMPLE_BLOCK_STATE_PROVIDER = BlockStateProviderMixin.callRegister("hibiscus_simple_block_state_provider",
           HibiscusSimpleBlockStateProvider.CODEC
   );
   public static final Feature <DeltaFeatureConfig> HIBISCUS_DELTA_FEATURE = Registry.register(
           Registries.FEATURE,
           "water_delta_feature",
           new HibiscusDeltaFeature(DeltaFeatureConfig.CODEC)
   );
   public static final Feature <TurnipRootFeatureConfig> HIBISCUS_TURNIP_ROOT_FEATURE = Registry.register(Registries.FEATURE,
           "turnip_root_feature",
           new TurnipRootFeature(TurnipRootFeatureConfig.CODEC)
   );
   public static final Feature <DefaultFeatureConfig> JOSHUA_TREE_FEATURE = Registry.register(Registries.FEATURE,
           "joshua_tree_feature",
           new JoshuaTreeFeature(DefaultFeatureConfig.CODEC)
   );
   public static void registerWorldGen() {
//      BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.SWAMP), GenerationStep.Feature.VEGETAL_DECORATION, HibiscusPlacedFeatures.WILLOW_PLACED);
   };
}
