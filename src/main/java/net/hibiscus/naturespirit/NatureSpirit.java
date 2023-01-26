package net.hibiscus.naturespirit;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerTypeHelper;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.impl.client.rendering.ColorProviderRegistryImpl;
import net.hibiscus.naturespirit.blocks.HibiscusBlocks;
import net.hibiscus.naturespirit.items.HibiscusItemGroups;
import net.hibiscus.naturespirit.mixin.BlockStateProviderMixin;
import net.hibiscus.naturespirit.mixin.FoliagePlacerMixin;
import net.hibiscus.naturespirit.mixin.TreeDecoratorMixin;
import net.hibiscus.naturespirit.terrablender.HibiscusBiomes;
import net.hibiscus.naturespirit.terrablender.NatureSpiritBiomes;
import net.hibiscus.naturespirit.world.feature.HibiscusConfiguredFeatures;
import net.hibiscus.naturespirit.world.feature.HibiscusDeltaFeature;
import net.hibiscus.naturespirit.world.feature.HibiscusSimpleBlockStateProvider;
import net.hibiscus.naturespirit.world.feature.foliage_placer.WisteriaFoliagePlacer;
import net.hibiscus.naturespirit.world.feature.tree_decorator.WisteriaVinesTreeDecorator;
import net.hibiscus.naturespirit.world.feature.trunk.SakuraTrunkPlacer;
import net.hibiscus.naturespirit.world.feature.trunk.SakuraTrunkPlacerSapling;
import net.hibiscus.naturespirit.world.feature.trunk.WisteriaTrunkPlacer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.CatVariant;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.DeltaFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProviderType;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
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
    public static final VillagerType WISTERIA = VillagerTypeHelper.register(new ResourceLocation(MOD_ID, "wisteria"));
    public static final Feature <DeltaFeatureConfiguration> HIBISCUS_DELTA_FEATURE = Registry.register(BuiltInRegistries.FEATURE, "water_delta_feature", new HibiscusDeltaFeature(DeltaFeatureConfiguration.CODEC));


    @Override
    public void onInitialize() {
        HibiscusItemGroups.registerItemGroup();
        VillagerTypeHelper.addVillagerTypeToBiome(HibiscusBiomes.WISTERIA_FOREST, WISTERIA);
        HibiscusConfiguredFeatures.registerConfiguredFeatures();
        HibiscusBiomes.registerBiomes();
        HibiscusBlocks.registerHibiscusBlocks();
        CompostingChanceRegistry.INSTANCE.add(HibiscusBlocks.BLUE_WISTERIA_VINES, 0.5F);
        CompostingChanceRegistry.INSTANCE.add(HibiscusBlocks.PINK_WISTERIA_VINES, 0.5F);
        CompostingChanceRegistry.INSTANCE.add(HibiscusBlocks.WHITE_WISTERIA_VINES, 0.5F);
        CompostingChanceRegistry.INSTANCE.add(HibiscusBlocks.PURPLE_WISTERIA_VINES, 0.5F);
        CompostingChanceRegistry.INSTANCE.add(HibiscusBlocks.LAVENDER, 0.5F);
        CompostingChanceRegistry.INSTANCE.add(HibiscusBlocks.BLUEBELL, 0.5F);
        CompostingChanceRegistry.INSTANCE.add(HibiscusBlocks.CARNATION, 0.4F);
        CompostingChanceRegistry.INSTANCE.add(HibiscusBlocks.GARDENIA, 0.4F);
        CompostingChanceRegistry.INSTANCE.add(HibiscusBlocks.ANEMONE, 0.4F);
        CompostingChanceRegistry.INSTANCE.add(HibiscusBlocks.HIBISCUS, 0.3F);
        Registry.register(BuiltInRegistries.CAT_VARIANT, "trans", new CatVariant(new ResourceLocation("textures/entity/cat/trans.png")));
    }
}
