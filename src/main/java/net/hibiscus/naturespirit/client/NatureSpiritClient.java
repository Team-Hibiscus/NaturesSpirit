package net.hibiscus.naturespirit.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.hibiscus.naturespirit.blocks.HibiscusBlocks;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.client.color.world.BiomeColors;

@Environment(EnvType.CLIENT)
public class NatureSpiritClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> {
            return blockAndTintGetter != null && blockPos != null ? BiomeColors.getGrassColor(blockAndTintGetter, blockState.get(TallPlantBlock.HALF) == DoubleBlockHalf.UPPER ? blockPos.down() : blockPos) : -1;
        }, HibiscusBlocks.CATTAIL);
    }
}
