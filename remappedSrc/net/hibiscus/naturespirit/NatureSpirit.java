package net.hibiscus.naturespirit;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerTypeHelper;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.hibiscus.naturespirit.blocks.HibiscusBlocks;
import net.hibiscus.naturespirit.items.HibiscusItemGroups;
import net.hibiscus.naturespirit.mixin.BlockStateProviderMixin;
import net.hibiscus.naturespirit.mixin.FoliagePlacerMixin;
import net.hibiscus.naturespirit.mixin.TreeDecoratorMixin;
import net.hibiscus.naturespirit.terrablender.HibiscusBiomes;
import net.hibiscus.naturespirit.world.feature.HibiscusConfiguredFeatures;
import net.hibiscus.naturespirit.world.feature.HibiscusDeltaFeature;
import net.hibiscus.naturespirit.world.feature.HibiscusSimpleBlockStateProvider;
import net.hibiscus.naturespirit.world.feature.foliage_placer.WisteriaFoliagePlacer;
import net.hibiscus.naturespirit.world.feature.tree_decorator.WisteriaVinesTreeDecorator;
import net.hibiscus.naturespirit.world.feature.trunk.SugiTrunkPlacer;
import net.hibiscus.naturespirit.world.feature.trunk.WisteriaTrunkPlacer;
import net.minecraft.entity.passive.CatVariant;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
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

public class NatureSpirit implements ModInitializer {

    public static final String MOD_ID = "natures_spirit";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final TrunkPlacerType <WisteriaTrunkPlacer> WISTERIA_TRUNK_PLACER = callRegister("wisteria_trunk_placer", WisteriaTrunkPlacer.CODEC);
    public static final TrunkPlacerType <SakuraTrunkPlacer> SAKURA_TRUNK_PLACER = callRegister("sakura_trunk_placer", SakuraTrunkPlacer.CODEC);
    public static final TrunkPlacerType <SakuraTrunkPlacerSapling> SAKURA_TRUNK_PLACER_SAPLING = callRegister("sakura_trunk_placer_sapling", SakuraTrunkPlacerSapling.CODEC);
    public static final TreeDecoratorType <WisteriaVinesTreeDecorator> WISTERIA_VINES_TREE_DECORATOR = TreeDecoratorMixin.callRegister("wisteria_vines_tree_decorator", WisteriaVinesTreeDecorator.CODEC);
    public static final FoliagePlacerType <WisteriaFoliagePlacer> WISTERIA_FOLIAGE_PLACER_TYPE = FoliagePlacerMixin.callRegister("wisteria_foliage_placer", WisteriaFoliagePlacer.CODEC);
    public static final BlockStateProviderType <HibiscusSimpleBlockStateProvider> HIBISCUS_SIMPLE_BLOCK_STATE_PROVIDER = BlockStateProviderMixin.callRegister("hibiscus_simple_block_state_provider", HibiscusSimpleBlockStateProvider.CODEC);
    public static final VillagerType WISTERIA = VillagerTypeHelper.register(new Identifier(MOD_ID, "wisteria"));
    public static final Feature <DeltaFeatureConfig> HIBISCUS_DELTA_FEATURE = Registry.register(Registries.FEATURE, "water_delta_feature", new HibiscusDeltaFeature(DeltaFeatureConfig.CODEC));


    @Override
    public void onInitialize() {
        HibiscusItemGroups.registerItemGroup();
        VillagerTypeHelper.addVillagerTypeToBiome(HibiscusBiomes.WISTERIA_FOREST, WISTERIA);
        HibiscusConfiguredFeatures.registerConfiguredFeatures();
        HibiscusBiomes.registerBiomes();
        HibiscusBlocks.registerHibiscusBlocks();
        Registry.register(Registries.CAT_VARIANT, "trans", new CatVariant(new Identifier("textures/entity/cat/trans.png")));
    }
}
