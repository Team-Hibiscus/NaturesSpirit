package net.hibiscus.naturespirit.client;

import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.client.render.CheeseArrowEntityRenderer;
import net.hibiscus.naturespirit.client.render.NSBoatRenderer;
import net.hibiscus.naturespirit.client.render.PizzaBlockEntityRenderer;
import net.hibiscus.naturespirit.client.render.PizzaToppingModel;
import net.hibiscus.naturespirit.entity.NSBoatEntity;
import net.hibiscus.naturespirit.registration.NSBlocks;
import net.hibiscus.naturespirit.registration.NSEntityTypes;
import net.hibiscus.naturespirit.registration.NSParticleTypes;
import net.hibiscus.naturespirit.registration.NSRegistryHelper;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.particle.SuspendedTownParticle;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DeferredBlock;

@EventBusSubscriber(modid = NatureSpirit.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class NSClient {

  public static final ModelLayerLocation PIZZA_TOPPING = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "pizza"), "toppings");

  @SubscribeEvent
  public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
    event.register(
            (blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageGrassColor(blockAndTintGetter,
                    blockState.getValue(DoublePlantBlock.HALF) == DoubleBlockHalf.UPPER ? blockPos.below() : blockPos
            ) : -1, NSBlocks.CATTAIL.get());
    event.register(
            (blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageFoliageColor(blockAndTintGetter,
                    blockPos
            ) : -1, NSBlocks.SUGI.getLeaves().get());
    event.register(
            (blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageFoliageColor(blockAndTintGetter,
                    blockPos
            ) : -1, NSBlocks.MAHOGANY.getLeaves().get());
    event.register(
            (blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageFoliageColor(blockAndTintGetter,
                    blockPos
            ) : -1, NSBlocks.LARCH.getLeaves().get());
    event.register(
            (blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageFoliageColor(blockAndTintGetter,
                    blockPos
            ) : -1, NSBlocks.ASPEN.getLeaves().get());
    event.register(
            (blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageGrassColor(blockAndTintGetter,
                    blockPos
            ) : -1, NSBlocks.LOTUS_STEM.get());
    event.register(
            (blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageGrassColor(blockAndTintGetter,
                    blockPos
            ) : -1, NSBlocks.LARGE_LUSH_FERN.get());
    event.register(
            (blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageGrassColor(blockAndTintGetter,
                    blockPos
            ) : -1, NSBlocks.LUSH_FERN.get());
    event.register(
            (blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageGrassColor(blockAndTintGetter,
                    blockPos
            ) : -1, NSBlocks.POTTED_LUSH_FERN.get());
    event.register(
            (blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageGrassColor(blockAndTintGetter,
                    blockPos
            ) : -1, NSBlocks.LOTUS_STEM.get());
    event.register(
            (blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageGrassColor(blockAndTintGetter,
                    new BlockPos(blockPos.getX(), -64, blockPos.getZ())
            ) : -1, NSBlocks.LOTUS_FLOWER.get());
  }

  @SubscribeEvent
  public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
    event.register((stack, tintIndex) -> FoliageColor.getDefaultColor(), NSBlocks.SUGI.getLeaves().get());
    event.register((stack, tintIndex) -> FoliageColor.getDefaultColor(), NSBlocks.LARCH.getLeaves().get());
    event.register((stack, tintIndex) -> FoliageColor.getDefaultColor(), NSBlocks.MAHOGANY.getLeaves().get());
    event.register((stack, tintIndex) -> FoliageColor.getDefaultColor(), NSBlocks.ASPEN.getLeaves().get());
    event.register((stack, tintIndex) -> GrassColor.getDefaultColor(), NSBlocks.LUSH_FERN.get());
    event.register((stack, tintIndex) -> GrassColor.getDefaultColor(), NSBlocks.LARGE_LUSH_FERN.get());
  }

  @SubscribeEvent
  public static void registerClient(FMLClientSetupEvent event) {
    Sheets.addWoodType(NSBlocks.REDWOOD.getWoodType().get());
    Sheets.addWoodType(NSBlocks.SUGI.getWoodType().get());
    Sheets.addWoodType(NSBlocks.WISTERIA.getWoodType().get());
    Sheets.addWoodType(NSBlocks.FIR.getWoodType().get());
    Sheets.addWoodType(NSBlocks.WILLOW.getWoodType().get());
    Sheets.addWoodType(NSBlocks.ASPEN.getWoodType().get());
    Sheets.addWoodType(NSBlocks.MAPLE.getWoodType().get());
    Sheets.addWoodType(NSBlocks.CYPRESS.getWoodType().get());
    Sheets.addWoodType(NSBlocks.OLIVE.getWoodType().get());
    Sheets.addWoodType(NSBlocks.JOSHUA.getWoodType().get());
    Sheets.addWoodType(NSBlocks.GHAF.getWoodType().get());
    Sheets.addWoodType(NSBlocks.PALO_VERDE.getWoodType().get());
    Sheets.addWoodType(NSBlocks.COCONUT.getWoodType().get());
    Sheets.addWoodType(NSBlocks.CEDAR.getWoodType().get());
    Sheets.addWoodType(NSBlocks.LARCH.getWoodType().get());
    Sheets.addWoodType(NSBlocks.MAHOGANY.getWoodType().get());
    Sheets.addWoodType(NSBlocks.SAXAUL.getWoodType().get());
    ItemBlockRenderTypes.setRenderLayer(NSBlocks.PIZZA_BLOCK.get(), RenderType.cutout());
    ItemBlockRenderTypes.setRenderLayer(NSBlocks.LARGE_CALCITE_BUD.get(), RenderType.cutout());
    ItemBlockRenderTypes.setRenderLayer(NSBlocks.SMALL_CALCITE_BUD.get(), RenderType.cutout());
    ItemBlockRenderTypes.setRenderLayer(NSBlocks.CALCITE_CLUSTER.get(), RenderType.cutout());

    for (DeferredBlock<? extends Block> block : NSRegistryHelper.RenderLayerHashMap.values()) {
      ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutout());
    }
    for (DeferredBlock<? extends Block> block : NSRegistryHelper.LeavesHashMap.values()) {
      ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutoutMipped());
    }

    ItemBlockRenderTypes.setRenderLayer(NSBlocks.COCONUT_THATCH_CARPET.get(), RenderType.cutout());
    ItemBlockRenderTypes.setRenderLayer(NSBlocks.COCONUT_THATCH_SLAB.get(), RenderType.cutout());
    ItemBlockRenderTypes.setRenderLayer(NSBlocks.COCONUT_THATCH.get(), RenderType.cutout());
    ItemBlockRenderTypes.setRenderLayer(NSBlocks.COCONUT_THATCH_STAIRS.get(), RenderType.cutout());

    ItemBlockRenderTypes.setRenderLayer(NSBlocks.EVERGREEN_THATCH_CARPET.get(), RenderType.cutout());
    ItemBlockRenderTypes.setRenderLayer(NSBlocks.EVERGREEN_THATCH_SLAB.get(), RenderType.cutout());
    ItemBlockRenderTypes.setRenderLayer(NSBlocks.EVERGREEN_THATCH.get(), RenderType.cutout());
    ItemBlockRenderTypes.setRenderLayer(NSBlocks.EVERGREEN_THATCH_STAIRS.get(), RenderType.cutout());

    ItemBlockRenderTypes.setRenderLayer(NSBlocks.XERIC_THATCH_CARPET.get(), RenderType.cutout());
    ItemBlockRenderTypes.setRenderLayer(NSBlocks.XERIC_THATCH_SLAB.get(), RenderType.cutout());
    ItemBlockRenderTypes.setRenderLayer(NSBlocks.XERIC_THATCH.get(), RenderType.cutout());
    ItemBlockRenderTypes.setRenderLayer(NSBlocks.XERIC_THATCH_STAIRS.get(), RenderType.cutout());
  }

  @SubscribeEvent
  public static void registerParticles(RegisterParticleProvidersEvent event) {
    event.registerSpriteSet(NSParticleTypes.RED_MAPLE_LEAVES_PARTICLE.get(),
            ((spriteProvider) -> (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> new MapleLeavesParticle(world, x, y, z, spriteProvider))
    );
    event.registerSpriteSet(NSParticleTypes.ORANGE_MAPLE_LEAVES_PARTICLE.get(),
            ((spriteProvider) -> (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> new MapleLeavesParticle(world, x, y, z, spriteProvider))
    );
    event.registerSpriteSet(NSParticleTypes.YELLOW_MAPLE_LEAVES_PARTICLE.get(),
            ((spriteProvider) -> (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> new MapleLeavesParticle(world, x, y, z, spriteProvider))
    );
    event.registerSpriteSet(NSParticleTypes.MILK_PARTICLE.get(), SuspendedTownParticle.ComposterFillProvider::new);
  }

  @SubscribeEvent
  public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
    event.registerBlockEntityRenderer(NSBlocks.PIZZA_BLOCK_ENTITY_TYPE.get(), PizzaBlockEntityRenderer::new);
    event.registerEntityRenderer(NSEntityTypes.CHEESE_ARROW.get(), CheeseArrowEntityRenderer::new);
    event.registerEntityRenderer(NSEntityTypes.NS_BOAT.get(), pContext -> new NSBoatRenderer(pContext, false));
    event.registerEntityRenderer(NSEntityTypes.NS_CHEST_BOAT.get(), pContext -> new NSBoatRenderer(pContext, true));
  }
  @SubscribeEvent
  public static void registerEntityRenderLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
    event.registerLayerDefinition(PIZZA_TOPPING, PizzaToppingModel::getTexturedModelData);
    for(NSBoatEntity.Type type: NSBoatEntity.Type.values()) {
      event.registerLayerDefinition(NSBoatRenderer.createBoatModelName(type), BoatModel::createBodyModel);
      event.registerLayerDefinition(NSBoatRenderer.createChestBoatModelName(type), ChestBoatModel::createBodyModel);
    }
  }

}
