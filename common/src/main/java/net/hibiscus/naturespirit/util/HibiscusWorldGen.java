package net.hibiscus.naturespirit.util;

import net.hibiscus.naturespirit.Constants;
import net.hibiscus.naturespirit.mixin.BlockStateProviderMixin;
import net.hibiscus.naturespirit.mixin.FoliagePlacerMixin;
import net.hibiscus.naturespirit.mixin.TreeDecoratorMixin;
import net.hibiscus.naturespirit.registration.RegistrationProvider;
import net.hibiscus.naturespirit.registration.RegistryObject;
import net.hibiscus.naturespirit.world.feature.*;
import net.hibiscus.naturespirit.world.feature.foliage_placer.*;
import net.hibiscus.naturespirit.world.feature.tree_decorator.WisteriaVinesTreeDecorator;
import net.hibiscus.naturespirit.world.feature.trunk.OliveTrunkPlacer;
import net.hibiscus.naturespirit.world.feature.trunk.SugiTrunkPlacer;
import net.hibiscus.naturespirit.world.feature.trunk.WisteriaTrunkPlacer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.DeltaFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProviderType;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

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

   public static final Feature <DeltaFeatureConfiguration> HIBISCUS_DELTA_FEATURE = (Feature <DeltaFeatureConfiguration>) registerFeature(
           "water_delta_feature",
           new HibiscusDeltaFeature(DeltaFeatureConfiguration.CODEC)
   );
   public static final Feature <TurnipRootFeatureConfig> HIBISCUS_TURNIP_ROOT_FEATURE = (Feature <TurnipRootFeatureConfig>) registerFeature(
           "turnip_root_feature",
           new TurnipRootFeature(TurnipRootFeatureConfig.CODEC)
   );
   public static final Feature <NoneFeatureConfiguration> JOSHUA_TREE_FEATURE = (Feature <NoneFeatureConfiguration>) registerFeature(
           "joshua_tree_feature",
           new JoshuaTreeFeature(NoneFeatureConfiguration.CODEC)
   );

   public static final RegistrationProvider <Feature<?>> FEATURES = RegistrationProvider.get(BuiltInRegistries.FEATURE, Constants.MOD_ID);
   public static Feature<?> registerFeature(String name, Feature<?> feature) {
      assert FEATURES != null;
      RegistryObject <Feature<?>> feature2 = FEATURES.register(name, () -> feature);
      return feature2.get();
   }
   public static void registerWorldGen() {
   };
}
