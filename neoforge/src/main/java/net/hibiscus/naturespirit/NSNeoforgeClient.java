package net.hibiscus.naturespirit;

import java.util.List;
import java.util.function.Supplier;
import net.hibiscus.naturespirit.client.NSClient;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@EventBusSubscriber(modid = NaturesSpirit.MOD_ID, value = Dist.CLIENT)
public final class NSNeoforgeClient {

    private NSNeoforgeClient() {
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            for (Supplier<WoodType> woodType : NSClient.SHEET_WOOD_TYPES) {
                Sheets.addWoodType(woodType.get());
            }
        });
    }

    @SubscribeEvent
    public static void onRegisterBlockTints(RegisterColorHandlersEvent.BlockTintSources event) {
        for (NSClient.TintEntry entry : NSClient.BLOCK_TINTS) {
            event.register(entry.layers(), blocks(entry.blocks()));
        }
    }

    @SubscribeEvent
    public static void onRegisterParticles(RegisterParticleProvidersEvent event) {
        for (NSClient.ParticleEntry<?> entry : NSClient.PARTICLE_PROVIDERS) {
            registerParticle(event, entry);
        }
    }

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        for (NSClient.BlockEntityRendererEntry<?, ?> entry : NSClient.BLOCK_ENTITY_RENDERERS) {
            registerBlockEntityRenderer(event, entry);
        }
        for (NSClient.EntityRendererEntry<?> entry : NSClient.ENTITY_RENDERERS) {
            registerEntityRenderer(event, entry);
        }
    }

    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        for (NSClient.LayerEntry entry : NSClient.LAYER_DEFINITIONS) {
            event.registerLayerDefinition(entry.location(), entry.definition());
        }
    }

    private static <T extends ParticleOptions> void registerParticle(RegisterParticleProvidersEvent event, NSClient.ParticleEntry<T> entry) {
        event.registerSpriteSet(entry.type().get(), entry.factory()::apply);
    }

    private static <T extends Entity> void registerEntityRenderer(EntityRenderersEvent.RegisterRenderers event, NSClient.EntityRendererEntry<T> entry) {
        event.registerEntityRenderer(entry.type().get(), entry.provider());
    }

    private static <T extends BlockEntity, S extends BlockEntityRenderState> void registerBlockEntityRenderer(EntityRenderersEvent.RegisterRenderers event,
            NSClient.BlockEntityRendererEntry<T, S> entry) {
        event.registerBlockEntityRenderer(entry.type().get(), entry.provider());
    }

    private static Block[] blocks(List<Supplier<? extends Block>> suppliers) {
        return suppliers.stream().map(Supplier::get).toArray(Block[]::new);
    }
}
