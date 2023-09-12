package net.hibiscus.naturespirit.client;

import com.google.common.collect.ImmutableMap;
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
import net.hibiscus.naturespirit.registration.HibiscusEntityTypes;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusWoods;
import net.hibiscus.naturespirit.util.HibiscusRegistryHelper;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.particle.SuspendedTownParticle;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

import static net.hibiscus.naturespirit.NatureSpirit.*;

@Environment(EnvType.CLIENT) public class NatureSpiritClient implements ClientModInitializer {

//   public static final EntityModelLayer BISON_MODEL_LAYER = new EntityModelLayer(new Identifier(MOD_ID, "bison"), "main");
   @Override public void onInitializeClient() {


//      EntityRendererRegistry.register(HibiscusEntityTypes.BISON, BisonEntityRenderer::new);
//
//      new ImmutableMap.Builder<EntityModelLayer, EntityModelLayerRegistry.TexturedModelDataProvider>()
//              .put(BISON_MODEL_LAYER, new BisonTexturedModelDataProvider())
//              .build().forEach(EntityModelLayerRegistry::registerModelLayer);



      ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageGrassColor(blockAndTintGetter,
              blockState.getValue(DoublePlantBlock.HALF) == DoubleBlockHalf.UPPER ? blockPos.below() : blockPos
      ) : -1, HibiscusBlocksAndItems.CATTAIL);
      ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageFoliageColor(blockAndTintGetter,
              blockPos
      ) : -1, HibiscusWoods.SUGI.getLeaves());
      ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageGrassColor(blockAndTintGetter,
              blockPos
      ) : -1, HibiscusBlocksAndItems.LOTUS_STEM);
      ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageGrassColor(blockAndTintGetter,
              new BlockPos(blockPos.getX(), -64, blockPos.getZ())
      ) : -1, HibiscusBlocksAndItems.LOTUS_FLOWER);

      ColorProviderRegistry.ITEM.register((stack, tintIndex) -> FoliageColor.getDefaultColor(), HibiscusWoods.SUGI.getLeaves());




      BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocksAndItems.PIZZA_BLOCK, RenderType.cutout());
      BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocksAndItems.LARGE_CALCITE_BUD, RenderType.cutout());
      BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocksAndItems.SMALL_CALCITE_BUD, RenderType.cutout());
      BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocksAndItems.CALCITE_CLUSTER, RenderType.cutout());
         BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), HibiscusRegistryHelper.RenderLayerHashMap.values().toArray(new Block[0]));




      ParticleFactoryRegistry.getInstance().register(RED_MAPLE_LEAVES_PARTICLE,
              ((spriteProvider) -> (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> new MapleLeavesParticle(world, x, y, z, spriteProvider))
      );
      ParticleFactoryRegistry.getInstance().register(ORANGE_MAPLE_LEAVES_PARTICLE,
              ((spriteProvider) -> (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> new MapleLeavesParticle(world, x, y, z, spriteProvider))
      );
      ParticleFactoryRegistry.getInstance().register(YELLOW_MAPLE_LEAVES_PARTICLE,
              ((spriteProvider) -> (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> new MapleLeavesParticle(world, x, y, z, spriteProvider))
      );
      ParticleFactoryRegistry.getInstance().register(MILK_PARTICLE, SuspendedTownParticle.ComposterFillProvider::new);
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
              () -> chest ? ChestBoatModel.createBodyModel() : BoatModel.createBodyModel()
      );
   }
}

