package net.hibiscus.naturespirit.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.hibiscus.naturespirit.registration.HibiscusBlocksAndItems;
import net.hibiscus.naturespirit.entity.HibiscusBoatEntity;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusWoods;
<<<<<<< Updated upstream:remappedSrc/net/hibiscus/naturespirit/client/NatureSpiritClient.java
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.IdMapper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
=======
import net.hibiscus.naturespirit.util.HibiscusRegistryHelper;
import net.minecraft.block.Block;
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
>>>>>>> Stashed changes:src/main/java/net/hibiscus/naturespirit/client/NatureSpiritClient.java
import org.jetbrains.annotations.Nullable;

import static net.hibiscus.naturespirit.NatureSpirit.*;

@Environment(EnvType.CLIENT) public class NatureSpiritClient implements ClientModInitializer {
   private final IdMapper <BlockColor> providers = new IdMapper(32);

   @Override public void onInitializeClient() {
      ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageGrassColor(blockAndTintGetter,
              blockState.getValue(DoublePlantBlock.HALF) == DoubleBlockHalf.UPPER ? blockPos.below() : blockPos
      ) : -1, HibiscusBlocksAndItems.CATTAIL);
      ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageFoliageColor(blockAndTintGetter,
              blockPos
      ) : -1, HibiscusWoods.SUGI_LEAVES);
      ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageGrassColor(blockAndTintGetter,
              blockPos
      ) : -1, HibiscusBlocksAndItems.LOTUS_STEM);
      ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageGrassColor(blockAndTintGetter,
              new BlockPos(blockPos.getX(), -64, blockPos.getZ())
      ) : -1, HibiscusBlocksAndItems.LOTUS_FLOWER);

      ColorProviderRegistry.ITEM.register((stack, tintIndex) -> FoliageColor.getDefaultColor(), HibiscusWoods.SUGI_LEAVES);

      BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocksAndItems.POTTED_HIBISCUS, RenderType.cutout());
      BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocksAndItems.POTTED_ANEMONE, RenderType.cutout());
      BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocksAndItems.PIZZA_BLOCK, RenderType.cutout());
      /* Registers our particle client-side.
       * First argument is our particle's instance, created previously on ExampleMod.
       * Second argument is the particle's factory. The factory controls how the particle behaves.
       * In this example, we'll use FlameParticle's Factory.*/
      ParticleFactoryRegistry.getInstance().register(RED_MAPLE_LEAVES_PARTICLE,  ((spriteProvider) -> (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> new MapleLeavesParticle(world, x, y, z, spriteProvider)));
      ParticleFactoryRegistry.getInstance().register(ORANGE_MAPLE_LEAVES_PARTICLE,  ((spriteProvider) -> (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> new MapleLeavesParticle(world, x, y, z, spriteProvider)));
      ParticleFactoryRegistry.getInstance().register(YELLOW_MAPLE_LEAVES_PARTICLE,  ((spriteProvider) -> (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> new MapleLeavesParticle(world, x, y, z, spriteProvider)));

      for (HibiscusBoatEntity.HibiscusBoat boat : HibiscusBoatEntity.HibiscusBoat.values()) {
         registerBoatModel(true, boat);
         registerBoatModel(false, boat);
      }
      for(Block block : HibiscusRegistryHelper.RenderLayerHashMap.values()) {
         BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());
      }

      BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocksAndItems.LARGE_CALCITE_BUD, RenderType.cutout());
      BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocksAndItems.SMALL_CALCITE_BUD, RenderType.cutout());
      BlockRenderLayerMap.INSTANCE.putBlock(HibiscusBlocksAndItems.CALCITE_CLUSTER, RenderType.cutout());
   }

   public int getColor(BlockState state, @Nullable BlockAndTintGetter world, @Nullable BlockPos pos, int tintIndex) {
      BlockColor blockColorProvider = this.providers.byId(BuiltInRegistries.BLOCK.getId(state.getBlock()));
      return blockColorProvider == null ? -1 : blockColorProvider.getColor(state, world, pos, tintIndex);
   }
   private static void registerBoatModel(boolean chest, HibiscusBoatEntity.HibiscusBoat boat) {
      var type = boat.entityType(chest);
      EntityRendererRegistry.register(type, context -> new HibiscusBoatEntityRenderer(context, chest, boat));
      EntityModelLayerRegistry.registerModelLayer(HibiscusBoatEntityRenderer.getModelLayer(boat, chest),
              () -> chest ? ChestBoatModel.createBodyModel() : BoatModel.createBodyModel());
   }
}

