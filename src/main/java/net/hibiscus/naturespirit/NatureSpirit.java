package net.hibiscus.naturespirit;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.hibiscus.naturespirit.blocks.HibiscusBlocks;
import net.hibiscus.naturespirit.mixin.BlockStateProviderMixin;
import net.hibiscus.naturespirit.mixin.FoliagePlacerMixin;
import net.hibiscus.naturespirit.mixin.TreeDecoratorMixin;
import net.hibiscus.naturespirit.world.feature.HibiscusConfiguredFeatures;
import net.hibiscus.naturespirit.world.feature.HibiscusSimpleBlockStateProvider;
import net.hibiscus.naturespirit.world.feature.foliage_placer.WisteriaFoliagePlacer;
import net.hibiscus.naturespirit.world.feature.tree_decorator.WisteriaVinesTreeDecorator;
import net.hibiscus.naturespirit.world.feature.trunk.WisteriaTrunkPlacer;
import net.hibiscus.naturespirit.world.gen.HibiscusWorldGeneration;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.stateprovider.BlockStateProviderType;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraft.world.gen.trunk.TrunkPlacerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.hibiscus.naturespirit.mixin.TrunkPlacerTypeMixin.callRegister;
import static net.hibiscus.naturespirit.mixin.TreeDecoratorMixin.callRegister;

public class NatureSpirit implements ModInitializer{

    public static final String MOD_ID = "hibiscus";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final TrunkPlacerType<WisteriaTrunkPlacer> WISTERIA_TRUNK_PLACER = callRegister("wisteria_trunk_placer", WisteriaTrunkPlacer.CODEC);
    public static final TreeDecoratorType<WisteriaVinesTreeDecorator> WISTERIA_VINES_TREE_DECORATOR = TreeDecoratorMixin.callRegister("wisteria_vines_tree_decorator", WisteriaVinesTreeDecorator.CODEC);
    public static final FoliagePlacerType<WisteriaFoliagePlacer> WISTERIA_FOLIAGE_PLACER_TYPE = FoliagePlacerMixin.callRegister("wisteria_foliage_placer", WisteriaFoliagePlacer.CODEC);
    public static final BlockStateProviderType<HibiscusSimpleBlockStateProvider> HIBISCUS_SIMPLE_BLOCK_STATE_PROVIDER = BlockStateProviderMixin.callRegister("hibiscus_simple_block_state_provider", HibiscusSimpleBlockStateProvider.CODEC);


    @Override
    public void onInitialize() {
        HibiscusConfiguredFeatures.registerConfiguredFeatures();
        HibiscusWorldGeneration.generateHibiscusWorldGen();
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
    }
}
