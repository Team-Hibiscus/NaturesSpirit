package net.hibiscus.naturespirit.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.hibiscus.naturespirit.blocks.HibiscusBlocks;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class NatureSpiritClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.WHITE_WISTERIA_VINES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.WHITE_WISTERIA_VINES_PLANT, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.BLUE_WISTERIA_VINES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.BLUE_WISTERIA_VINES_PLANT, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.PINK_WISTERIA_VINES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.PINK_WISTERIA_VINES_PLANT, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.PURPLE_WISTERIA_VINES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.PURPLE_WISTERIA_VINES_PLANT, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.BLOOMING_SAKURA_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.BLOOMING_SAKURA_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.FRAMED_SAKURA_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.FRAMED_SAKURA_TRAPDOOR, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.LAVENDER,  RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.BLUEBELL,  RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.CARNATION,  RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.GARDENIA,  RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.ANEMONE,  RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.POTTED_ANEMONE,  RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.HIBISCUS,  RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.POTTED_HIBISCUS,  RenderLayer.getCutout());
    }
}
