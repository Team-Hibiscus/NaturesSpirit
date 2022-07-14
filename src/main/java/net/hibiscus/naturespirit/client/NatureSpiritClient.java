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
    }
}
