package net.hibiscus.naturespirit.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.hibiscus.naturespirit.client.render.CheeseArrowEntityRenderer;
import net.hibiscus.naturespirit.client.render.EntityModelLayers;
import net.hibiscus.naturespirit.client.render.PizzaBlockEntityRenderer;
import net.hibiscus.naturespirit.client.render.pizza_models.Topping0;
import net.hibiscus.naturespirit.client.render.pizza_models.Topping1;
import net.hibiscus.naturespirit.client.render.pizza_models.Topping2;
import net.hibiscus.naturespirit.client.render.pizza_models.Topping3;
import net.hibiscus.naturespirit.config.HibiscusConfig;
import net.hibiscus.naturespirit.entity.HibiscusBoatEntity;
import net.hibiscus.naturespirit.registration.HibiscusEntityTypes;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusMiscBlocks;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusWoods;
import net.hibiscus.naturespirit.registration.HibiscusRegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.client.particle.SuspendParticle;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.entity.ArrowEntityRenderer;
import net.minecraft.client.render.entity.model.BoatEntityModel;
import net.minecraft.client.render.entity.model.ChestBoatEntityModel;
import net.minecraft.util.math.BlockPos;

import static net.hibiscus.naturespirit.NatureSpirit.*;

@Environment(EnvType.CLIENT) public class NatureSpiritClient implements ClientModInitializer {

//   public static final EntityModelLayer BISON_MODEL_LAYER = new EntityModelLayer(new Identifier(MOD_ID, "bison"), "main");
   @Override public void onInitializeClient() {


//      EntityRendererRegistry.register(HibiscusEntityTypes.BISON, BisonEntityRenderer::new);
//
//      new ImmutableMap.Builder<EntityModelLayer, EntityModelLayerRegistry.TexturedModelDataProvider>()
//              .put(BISON_MODEL_LAYER, new BisonTexturedModelDataProvider())
//              .build().forEach(EntityModelLayerRegistry::registerModelLayer);


      BlockEntityRendererFactories.register(HibiscusMiscBlocks.PIZZA_BLOCK_ENTITY_TYPE, PizzaBlockEntityRenderer::new);

      ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getGrassColor(blockAndTintGetter,
              blockState.get(TallPlantBlock.HALF) == DoubleBlockHalf.UPPER ? blockPos.down() : blockPos
      ) : -1, HibiscusMiscBlocks.CATTAIL);
      ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getFoliageColor(blockAndTintGetter,
              blockPos
      ) : -1, HibiscusWoods.SUGI.getLeaves());
      ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getFoliageColor(blockAndTintGetter,
              blockPos
      ) : -1, HibiscusWoods.MAHOGANY.getLeaves());
      ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getFoliageColor(blockAndTintGetter,
              blockPos
      ) : -1, HibiscusWoods.LARCH.getLeaves());
      ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getGrassColor(blockAndTintGetter,
              blockPos
      ) : -1, HibiscusMiscBlocks.LOTUS_STEM);
      ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getGrassColor(blockAndTintGetter,
              blockPos
      ) : -1, HibiscusMiscBlocks.LARGE_LUSH_FERN);
      ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getGrassColor(blockAndTintGetter,
              blockPos
      ) : -1, HibiscusMiscBlocks.LUSH_FERN);
      ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getGrassColor(blockAndTintGetter,
              blockPos
      ) : -1, HibiscusMiscBlocks.POTTED_LUSH_FERN);
      ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getGrassColor(blockAndTintGetter,
              blockPos
      ) : -1, HibiscusMiscBlocks.LOTUS_STEM);
      ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getGrassColor(blockAndTintGetter,
              new BlockPos(blockPos.getX(), -64, blockPos.getZ())
      ) : -1, HibiscusMiscBlocks.LOTUS_FLOWER);

      ColorProviderRegistry.ITEM.register((stack, tintIndex) -> FoliageColors.getDefaultColor(), HibiscusWoods.SUGI.getLeaves());
      ColorProviderRegistry.ITEM.register((stack, tintIndex) -> FoliageColors.getDefaultColor(), HibiscusWoods.LARCH.getLeaves());
      ColorProviderRegistry.ITEM.register((stack, tintIndex) -> FoliageColors.getDefaultColor(), HibiscusWoods.MAHOGANY.getLeaves());
      ColorProviderRegistry.ITEM.register((stack, tintIndex) -> GrassColors.getDefaultColor(), HibiscusMiscBlocks.LUSH_FERN);
      ColorProviderRegistry.ITEM.register((stack, tintIndex) -> GrassColors.getDefaultColor(), HibiscusMiscBlocks.LARGE_LUSH_FERN);

      EntityModelLayers.registerEntityModelLayers();
      EntityModelLayerRegistry.registerModelLayer(EntityModelLayers.TOPPING_0, Topping0::getTexturedModelData);
      EntityModelLayerRegistry.registerModelLayer(EntityModelLayers.TOPPING_1, Topping1::getTexturedModelData);
      EntityModelLayerRegistry.registerModelLayer(EntityModelLayers.TOPPING_2, Topping2::getTexturedModelData);
      EntityModelLayerRegistry.registerModelLayer(EntityModelLayers.TOPPING_3, Topping3::getTexturedModelData);



      BlockRenderLayerMap.INSTANCE.putBlock(HibiscusMiscBlocks.PIZZA_BLOCK, RenderLayer.getCutout());
      BlockRenderLayerMap.INSTANCE.putBlock(HibiscusMiscBlocks.LARGE_CALCITE_BUD, RenderLayer.getCutout());
      BlockRenderLayerMap.INSTANCE.putBlock(HibiscusMiscBlocks.SMALL_CALCITE_BUD, RenderLayer.getCutout());
      BlockRenderLayerMap.INSTANCE.putBlock(HibiscusMiscBlocks.CALCITE_CLUSTER, RenderLayer.getCutout());

      BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), HibiscusRegistryHelper.RenderLayerHashMap.values().toArray(new Block[0]));

      BlockRenderLayerMap.INSTANCE.putBlock(HibiscusWoods.COCONUT_THATCH_CARPET, RenderLayer.getCutout());
      BlockRenderLayerMap.INSTANCE.putBlock(HibiscusWoods.COCONUT_THATCH_SLAB, RenderLayer.getCutout());
      BlockRenderLayerMap.INSTANCE.putBlock(HibiscusWoods.COCONUT_THATCH, RenderLayer.getCutout());
      BlockRenderLayerMap.INSTANCE.putBlock(HibiscusWoods.COCONUT_THATCH_STAIRS, RenderLayer.getCutout());

      BlockRenderLayerMap.INSTANCE.putBlock(HibiscusWoods.EVERGREEN_THATCH_CARPET, RenderLayer.getCutout());
      BlockRenderLayerMap.INSTANCE.putBlock(HibiscusWoods.EVERGREEN_THATCH_SLAB, RenderLayer.getCutout());
      BlockRenderLayerMap.INSTANCE.putBlock(HibiscusWoods.EVERGREEN_THATCH, RenderLayer.getCutout());
      BlockRenderLayerMap.INSTANCE.putBlock(HibiscusWoods.EVERGREEN_THATCH_STAIRS, RenderLayer.getCutout());

      BlockRenderLayerMap.INSTANCE.putBlock(HibiscusWoods.XERIC_THATCH_CARPET, RenderLayer.getCutout());
      BlockRenderLayerMap.INSTANCE.putBlock(HibiscusWoods.XERIC_THATCH_SLAB, RenderLayer.getCutout());
      BlockRenderLayerMap.INSTANCE.putBlock(HibiscusWoods.XERIC_THATCH, RenderLayer.getCutout());
      BlockRenderLayerMap.INSTANCE.putBlock(HibiscusWoods.XERIC_THATCH_STAIRS, RenderLayer.getCutout());

      if (HibiscusConfig.cheese_arrow) {
         EntityRendererRegistry.register(HibiscusEntityTypes.CHEESE_ARROW, CheeseArrowEntityRenderer::new);
      }



      ParticleFactoryRegistry.getInstance().register(RED_MAPLE_LEAVES_PARTICLE,
              ((spriteProvider) -> (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> new MapleLeavesParticle(world, x, y, z, spriteProvider))
      );
      ParticleFactoryRegistry.getInstance().register(ORANGE_MAPLE_LEAVES_PARTICLE,
              ((spriteProvider) -> (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> new MapleLeavesParticle(world, x, y, z, spriteProvider))
      );
      ParticleFactoryRegistry.getInstance().register(YELLOW_MAPLE_LEAVES_PARTICLE,
              ((spriteProvider) -> (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> new MapleLeavesParticle(world, x, y, z, spriteProvider))
      );
      ParticleFactoryRegistry.getInstance().register(MILK_PARTICLE, SuspendParticle.Factory::new);
      ParticleFactoryRegistry.getInstance().register(CALCITE_BUBBLE_PARTICLE, CalciteBubbleParticle.BubbleFactory::new);


      for(HibiscusBoatEntity.HibiscusBoat boat : HibiscusBoatEntity.HibiscusBoat.values()) {
         registerBoatModel(true, boat);
         registerBoatModel(false, boat);
      }
   }

   private static void registerBoatModel(boolean chest, HibiscusBoatEntity.HibiscusBoat boat) {
      var type = boat.entityType(chest);
      EntityRendererRegistry.register(type, context -> new HibiscusBoatEntityRenderer(context, chest, boat));
      EntityModelLayerRegistry.registerModelLayer(HibiscusBoatEntityRenderer.getModelLayer(boat, chest),
              () -> chest ? ChestBoatEntityModel.getTexturedModelData() : BoatEntityModel.getTexturedModelData()
      );
   }
}

