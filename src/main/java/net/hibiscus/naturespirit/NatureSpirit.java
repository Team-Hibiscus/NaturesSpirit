package net.hibiscus.naturespirit;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerTypeHelper;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.hibiscus.naturespirit.blocks.HibiscusBlocks;
import net.hibiscus.naturespirit.items.HibiscusItemGroups;
import net.hibiscus.naturespirit.mixin.BlockStateProviderMixin;
import net.hibiscus.naturespirit.mixin.FoliagePlacerMixin;
import net.hibiscus.naturespirit.mixin.StatsTypeAccessor;
import net.hibiscus.naturespirit.mixin.TreeDecoratorMixin;
import net.hibiscus.naturespirit.terrablender.HibiscusBiomes;
import net.hibiscus.naturespirit.world.feature.*;
import net.hibiscus.naturespirit.world.feature.foliage_placer.*;
import net.hibiscus.naturespirit.world.feature.tree_decorator.WisteriaVinesTreeDecorator;
import net.hibiscus.naturespirit.world.feature.trunk.OliveTrunkPlacer;
import net.hibiscus.naturespirit.world.feature.trunk.SugiTrunkPlacer;
import net.hibiscus.naturespirit.world.feature.trunk.WisteriaTrunkPlacer;
import net.minecraft.entity.passive.CatVariant;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.stat.StatFormatter;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerType;
import net.minecraft.world.gen.feature.DeltaFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.stateprovider.BlockStateProviderType;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraft.world.gen.trunk.TrunkPlacerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.hibiscus.naturespirit.mixin.TrunkPlacerTypeMixin.callRegister;
import static net.minecraft.village.VillagerType.BIOME_TO_TYPE;

public class NatureSpirit implements ModInitializer {

   public static final String MOD_ID = "natures_spirit";
   public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
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
   public static final VillagerType WISTERIA = VillagerTypeHelper.register(new Identifier(MOD_ID, "wisteria"));
   public static final VillagerType CYPRESS = VillagerTypeHelper.register(new Identifier(MOD_ID, "cypress"));
   public static final Feature <DeltaFeatureConfig> HIBISCUS_DELTA_FEATURE = Registry.register(Registries.FEATURE,
           "water_delta_feature",
           new HibiscusDeltaFeature(DeltaFeatureConfig.CODEC)
   );
   public static final Feature <TurnipRootFeatureConfig> HIBISCUS_TURNIP_ROOT_FEATURE = Registry.register(Registries.FEATURE,
           "turnip_root_feature",
           new TurnipRootFeature(TurnipRootFeatureConfig.CODEC)
   );
   public static final Identifier EAT_PIZZA_SLICE = StatsTypeAccessor.registerNew("eat_pizza_slice",
           StatFormatter.DEFAULT
   );

   @Override public void onInitialize() {
      HibiscusItemGroups.registerItemGroup();
      BIOME_TO_TYPE.put(HibiscusBiomes.WISTERIA_FOREST, WISTERIA);
      BIOME_TO_TYPE.put(HibiscusBiomes.CYPRESS_FIELDS, CYPRESS);
      BIOME_TO_TYPE.put(HibiscusBiomes.CARNATION_FIELDS, CYPRESS);
      HibiscusConfiguredFeatures.registerConfiguredFeatures();
      HibiscusBiomes.registerBiomes();
      HibiscusBlocks.registerHibiscusBlocks();
      CompostingChanceRegistry.INSTANCE.add(HibiscusBlocks.DESERT_TURNIP_BLOCK, 0.5F);
      CompostingChanceRegistry.INSTANCE.add(HibiscusBlocks.DESERT_TURNIP_ROOT_BLOCK, 0.4F);
      Registry.register(Registries.CAT_VARIANT,
              "trans",
              new CatVariant(new Identifier("textures/entity/cat/trans" + ".png"))
      );
   }
}
