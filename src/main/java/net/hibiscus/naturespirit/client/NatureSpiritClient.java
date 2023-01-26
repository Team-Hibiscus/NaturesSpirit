package net.hibiscus.naturespirit.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.impl.client.rendering.ColorProviderRegistryImpl;
import net.hibiscus.naturespirit.blocks.HibiscusBlocks;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

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

        ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> {
            return blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageGrassColor(blockAndTintGetter, blockState.getValue(DoublePlantBlock.HALF) == DoubleBlockHalf.UPPER ? blockPos.below() : blockPos) : -1;
        }, HibiscusBlocks.CATTAIL);

        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.WILLOW_VINES, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.WILLOW_VINES_PLANT, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.BLOOMING_SAKURA_DOOR, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.BLOOMING_SAKURA_TRAPDOOR, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.FRAMED_SAKURA_DOOR, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.FRAMED_SAKURA_TRAPDOOR, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.LAVENDER, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.BLUEBELL, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.CARNATION, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.CATTAIL, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.GARDENIA, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.SNAPDRAGON, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.ANEMONE, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.POTTED_ANEMONE, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.HIBISCUS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.POTTED_HIBISCUS, RenderType.cutout());
    }
}
