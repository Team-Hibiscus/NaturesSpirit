package net.hibiscus.naturespirit.util;

import com.mojang.serialization.Codec;
import net.hibiscus.naturespirit.mixin.BlockStateProviderMixin;
import net.hibiscus.naturespirit.world.feature.*;
import net.hibiscus.naturespirit.world.foliage_placer.*;
import net.hibiscus.naturespirit.world.tree_decorator.MapleGroundTreeDecorator;
import net.hibiscus.naturespirit.world.tree_decorator.WisteriaVinesTreeDecorator;
import net.hibiscus.naturespirit.world.trunk.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.DeltaFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProviderType;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;

public class HibiscusWorldGen {
   public static final TrunkPlacerType <WisteriaTrunkPlacer> WISTERIA_TRUNK_PLACER = registerTrunkPlacer("wisteria_trunk_placer", WisteriaTrunkPlacer.CODEC);
   public static final TrunkPlacerType <SugiTrunkPlacer> SUGI_TRUNK_PLACER = registerTrunkPlacer("sugi_trunk_placer", SugiTrunkPlacer.CODEC);
   public static final TrunkPlacerType <OliveTrunkPlacer> OLIVE_TRUNK_PLACER = registerTrunkPlacer("olive_trunk_placer", OliveTrunkPlacer.CODEC);
   public static final TrunkPlacerType <MapleTrunkPlacer> MAPLE_TRUNK_PLACER = registerTrunkPlacer("maple_trunk_placer", MapleTrunkPlacer.CODEC);


   public static final TreeDecoratorType <WisteriaVinesTreeDecorator> WISTERIA_VINES_TREE_DECORATOR = registerTreeDecorator("wisteria_vines_tree_decorator",
           WisteriaVinesTreeDecorator.CODEC
   );
   public static final TreeDecoratorType <MapleGroundTreeDecorator> MAPLE_GROUND_TREE_DECORATOR = registerTreeDecorator("maple_ground_tree_decorator", MapleGroundTreeDecorator.CODEC);


   public static final FoliagePlacerType <WisteriaFoliagePlacer> WISTERIA_FOLIAGE_PLACER_TYPE = registerFoliagePlacer("wisteria_foliage_placer", WisteriaFoliagePlacer.CODEC);
   public static final FoliagePlacerType <SugiFoliagePlacer> SUGI_FOLIAGE_PLACER_TYPE = registerFoliagePlacer("sugi_foliage_placer", SugiFoliagePlacer.CODEC);
   public static final FoliagePlacerType <AspenFoliagePlacer> ASPEN_FOLIAGE_PLACER_TYPE = registerFoliagePlacer("aspen_foliage_placer", AspenFoliagePlacer.CODEC);
   public static final FoliagePlacerType <FirFoliagePlacer> FIR_FOLIAGE_PLACER_TYPE = registerFoliagePlacer("fir_foliage_placer", FirFoliagePlacer.CODEC);
   public static final FoliagePlacerType <CypressFoliagePlacer> CYPRESS_FOLIAGE_PLACER_TYPE = registerFoliagePlacer("cypress_foliage_placer", CypressFoliagePlacer.CODEC);
   public static final FoliagePlacerType <MapleFoliagePlacer> MAPLE_FOLIAGE_PLACER_TYPE = registerFoliagePlacer("maple_foliage_placer", MapleFoliagePlacer.CODEC);

   public static final BlockStateProviderType <HibiscusSimpleBlockStateProvider> HIBISCUS_SIMPLE_BLOCK_STATE_PROVIDER = BlockStateProviderMixin.callRegister("hibiscus_simple_block_state_provider",
           HibiscusSimpleBlockStateProvider.CODEC
   );
   public static final Feature <DeltaFeatureConfiguration> HIBISCUS_DELTA_FEATURE = Registry.register(BuiltInRegistries.FEATURE,
           new ResourceLocation(MOD_ID, "water_delta_feature"),
           new HibiscusDeltaFeature(DeltaFeatureConfiguration.CODEC)
   );
   public static final Feature <OreConfiguration> HIBISCUS_PUMPKIN_PATCH_FEATURE = Registry.register(BuiltInRegistries.FEATURE,
           new ResourceLocation(MOD_ID, "pumpkin_patch_feature"),
           new PumpkinPatchFeature(OreConfiguration.CODEC)
   );
   public static final Feature <NoneFeatureConfiguration> HIBISCUS_LARGE_PUMPKIN_FEATURE = Registry.register(BuiltInRegistries.FEATURE,
           new ResourceLocation(MOD_ID, "large_pumpkin_feature"),
           new LargePumpkinFeature(NoneFeatureConfiguration.CODEC)
   );
   public static final Feature <TurnipRootFeatureConfig> HIBISCUS_TURNIP_ROOT_FEATURE = Registry.register(BuiltInRegistries.FEATURE,
           new ResourceLocation(MOD_ID, "turnip_root_feature"),
           new TurnipRootFeature(TurnipRootFeatureConfig.CODEC)
   );
   public static final Feature <NoneFeatureConfiguration> JOSHUA_TREE_FEATURE = Registry.register(BuiltInRegistries.FEATURE,
           new ResourceLocation(MOD_ID, "joshua_tree_feature"),
           new JoshuaTreeFeature(NoneFeatureConfiguration.CODEC)
   );
   public static final Feature <HugeMushroomFeatureConfiguration> HUGE_SHIITAKE_MUSHROOM_FEATURE = Registry.register(BuiltInRegistries.FEATURE,
           new ResourceLocation(MOD_ID, "huge_shiitake_mushroom_feature"),
           new HugeShiitakeMushroomFeature(HugeMushroomFeatureConfiguration.CODEC)
   );
   public static final Feature <NoneFeatureConfiguration> LOTUS_PLANT_FEATURE = Registry.register(BuiltInRegistries.FEATURE,
           new ResourceLocation(MOD_ID, "lotus_plant_feature"),
           new LotusPlantFeature(NoneFeatureConfiguration.CODEC)
   );

   private static <P extends FoliagePlacer> FoliagePlacerType<P> registerFoliagePlacer(String id, Codec <P> codec) {
      return (FoliagePlacerType)Registry.register(BuiltInRegistries.FOLIAGE_PLACER_TYPE, new ResourceLocation (MOD_ID, id), new FoliagePlacerType(codec));
   }
   private static <P extends TrunkPlacer> TrunkPlacerType<P> registerTrunkPlacer(String id, Codec<P> codec) {
      return (TrunkPlacerType)Registry.register(BuiltInRegistries.TRUNK_PLACER_TYPE,  new ResourceLocation (MOD_ID, id), new TrunkPlacerType(codec));
   }
   private static <P extends TreeDecorator> TreeDecoratorType<P> registerTreeDecorator(String id, Codec<P> codec) {
      return (TreeDecoratorType)Registry.register(BuiltInRegistries.TREE_DECORATOR_TYPE,  new ResourceLocation (MOD_ID, id), new TreeDecoratorType(codec));
   }

   public static void registerWorldGen() {
      //      BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.SWAMP), GenerationStep.Feature.VEGETAL_DECORATION, HibiscusPlacedFeatures.WILLOW_PLACED);
   }

}
