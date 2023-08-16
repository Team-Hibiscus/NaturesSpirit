package net.hibiscus.naturespirit;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.hibiscus.naturespirit.blocks.HibiscusBlocks;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.IdMapper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT) public class NatureSpiritClient implements ClientModInitializer {
   private final IdMapper <BlockColor> providers = new IdMapper(32);

   @Override public void onInitializeClient() {
      ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> {
         return blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageGrassColor(blockAndTintGetter,
                 blockState.getValue(DoublePlantBlock.HALF) == DoubleBlockHalf.UPPER ? blockPos.below() : blockPos
         ) : -1;
      }, HibiscusBlocks.CATTAIL);
      ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> {
         return blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageFoliageColor(blockAndTintGetter,
                 blockPos
         ) : -1;
      }, HibiscusBlocks.SUGI_LEAVES);

      ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
         return FoliageColor.getDefaultColor();
      }, HibiscusBlocks.SUGI_LEAVES);

      BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.POTTED_HIBISCUS, RenderType.cutout());
      BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.POTTED_ANEMONE, RenderType.cutout());
      BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocks.PIZZA_BLOCK, RenderType.cutout());

   }

   public int getColor(BlockState state, @Nullable BlockAndTintGetter world, @Nullable BlockPos pos, int tintIndex) {
      BlockColor blockColorProvider = this.providers.byId(BuiltInRegistries.BLOCK.getId(state.getBlock()));
      return blockColorProvider == null ? -1 : blockColorProvider.getColor(state, world, pos, tintIndex);
   }
}

