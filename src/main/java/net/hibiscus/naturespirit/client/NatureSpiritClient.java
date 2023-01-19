package net.hibiscus.naturespirit.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.hibiscus.naturespirit.blocks.HibiscusBlocks;
import net.minecraft.client.renderer.RenderType;

@Environment(EnvType.CLIENT)
public class NatureSpiritClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.WHITE_WISTERIA_VINES, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.WHITE_WISTERIA_VINES_PLANT, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.BLUE_WISTERIA_VINES, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.BLUE_WISTERIA_VINES_PLANT, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.PINK_WISTERIA_VINES, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.PINK_WISTERIA_VINES_PLANT, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.PURPLE_WISTERIA_VINES, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.PURPLE_WISTERIA_VINES_PLANT, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.BLOOMING_SAKURA_DOOR, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.BLOOMING_SAKURA_TRAPDOOR, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.FRAMED_SAKURA_DOOR, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.FRAMED_SAKURA_TRAPDOOR, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.LAVENDER, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.BLUEBELL, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.CARNATION, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.GARDENIA, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.ANEMONE, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.POTTED_ANEMONE, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.HIBISCUS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.POTTED_HIBISCUS, RenderType.cutout());
    }
}
