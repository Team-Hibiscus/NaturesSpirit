package net.hibiscus.naturespirit.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.hibiscus.naturespirit.blocks.HibiscusBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.registry.Registries;
import net.minecraft.util.collection.IdList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT) public class NatureSpiritClient implements ClientModInitializer {
   private final IdList <BlockColorProvider> providers = new IdList(32);

   @Override public void onInitializeClient() {
      ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> {
         return blockAndTintGetter != null && blockPos != null ? BiomeColors.getGrassColor(blockAndTintGetter,
                 blockState.get(TallPlantBlock.HALF) == DoubleBlockHalf.UPPER ? blockPos.down() : blockPos
         ) : -1;
      }, HibiscusBlocks.CATTAIL);
      ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> {
         return blockAndTintGetter != null && blockPos != null ? BiomeColors.getFoliageColor(blockAndTintGetter,
                 blockPos
         ) : -1;
      }, HibiscusBlocks.SUGI_LEAVES);

      ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
         return FoliageColors.getDefaultColor();
      }, HibiscusBlocks.SUGI_LEAVES);

      BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.POTTED_HIBISCUS, RenderLayer.getCutout());
      BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.POTTED_ANEMONE, RenderLayer.getCutout());
      BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.PIZZA_BLOCK, RenderLayer.getCutout());

   }

   public int getColor(BlockState state, @Nullable BlockRenderView world, @Nullable BlockPos pos, int tintIndex) {
      BlockColorProvider blockColorProvider = this.providers.get(Registries.BLOCK.getRawId(state.getBlock()));
      return blockColorProvider == null ? -1 : blockColorProvider.getColor(state, world, pos, tintIndex);
   }
}

