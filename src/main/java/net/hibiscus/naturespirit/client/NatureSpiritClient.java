package net.hibiscus.naturespirit.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
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
    }
}
