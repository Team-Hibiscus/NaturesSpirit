package net.hibiscus.naturespirit.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.hibiscus.naturespirit.entity.HibiscusBoatEntity;
import net.hibiscus.naturespirit.registration.HibiscusBlocksAndItems;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusWoods;
import net.minecraft.block.BlockState;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.BoatEntityModel;
import net.minecraft.client.render.entity.model.ChestBoatEntityModel;
import net.minecraft.registry.Registries;
import net.minecraft.util.collection.IdList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import org.jetbrains.annotations.Nullable;

import static net.hibiscus.naturespirit.NatureSpirit.*;

@Environment(EnvType.CLIENT) public class NatureSpiritClient implements ClientModInitializer {
   private final IdList <BlockColorProvider> providers = new IdList(32);

   @Override public void onInitializeClient() {
      ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getGrassColor(blockAndTintGetter,
              blockState.get(TallPlantBlock.HALF) == DoubleBlockHalf.UPPER ? blockPos.down() : blockPos
      ) : -1, HibiscusBlocksAndItems.CATTAIL);
      ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getFoliageColor(blockAndTintGetter,
              blockPos
      ) : -1, HibiscusWoods.SUGI_LEAVES);
      ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getGrassColor(blockAndTintGetter,
              blockPos
      ) : -1, HibiscusBlocksAndItems.LOTUS_STEM);
      ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getGrassColor(blockAndTintGetter,
              new BlockPos(blockPos.getX(), -64, blockPos.getZ())
      ) : -1, HibiscusBlocksAndItems.LOTUS_FLOWER);

      ColorProviderRegistry.ITEM.register((stack, tintIndex) -> FoliageColors.getDefaultColor(), HibiscusWoods.SUGI_LEAVES);

      BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocksAndItems.POTTED_HIBISCUS, RenderLayer.getCutout());
      BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocksAndItems.POTTED_ANEMONE, RenderLayer.getCutout());
      BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocksAndItems.PIZZA_BLOCK, RenderLayer.getCutout());
      /* Registers our particle client-side.
       * First argument is our particle's instance, created previously on ExampleMod.
       * Second argument is the particle's factory. The factory controls how the particle behaves.
       * In this example, we'll use FlameParticle's Factory.*/
      ParticleFactoryRegistry.getInstance().register(RED_MAPLE_LEAVES_PARTICLE,
              ((spriteProvider) -> (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> new MapleLeavesParticle(world, x, y, z, spriteProvider))
      );
      ParticleFactoryRegistry.getInstance().register(ORANGE_MAPLE_LEAVES_PARTICLE,
              ((spriteProvider) -> (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> new MapleLeavesParticle(world, x, y, z, spriteProvider))
      );
      ParticleFactoryRegistry.getInstance().register(YELLOW_MAPLE_LEAVES_PARTICLE,
              ((spriteProvider) -> (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> new MapleLeavesParticle(world, x, y, z, spriteProvider))
      );

      for(HibiscusBoatEntity.HibiscusBoat boat : HibiscusBoatEntity.HibiscusBoat.values()) {
         registerBoatModel(true, boat);
         registerBoatModel(false, boat);
      }

      BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocksAndItems.LARGE_CALCITE_BUD, RenderLayer.getCutout());
      BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocksAndItems.SMALL_CALCITE_BUD, RenderLayer.getCutout());
      BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocksAndItems.CALCITE_CLUSTER, RenderLayer.getCutout());
   }

   public int getColor(BlockState state, @Nullable BlockRenderView world, @Nullable BlockPos pos, int tintIndex) {
      BlockColorProvider blockColorProvider = this.providers.get(Registries.BLOCK.getRawId(state.getBlock()));
      return blockColorProvider == null ? -1 : blockColorProvider.getColor(state, world, pos, tintIndex);
   }

   private static void registerBoatModel(boolean chest, HibiscusBoatEntity.HibiscusBoat boat) {
      var type = boat.entityType(chest);
      EntityRendererRegistry.register(type, context -> new HibiscusBoatEntityRenderer(context, chest, boat));
      EntityModelLayerRegistry.registerModelLayer(HibiscusBoatEntityRenderer.getModelLayer(boat, chest),
              () -> chest ? ChestBoatEntityModel.getTexturedModelData() : BoatEntityModel.getTexturedModelData()
      );
   }
}

