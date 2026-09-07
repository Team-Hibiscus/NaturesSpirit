package net.hibiscus.naturespirit;

import java.util.List;
import java.util.function.Supplier;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.hibiscus.naturespirit.client.NSClient;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.properties.WoodType;

public final class NSFabricClient {

    private NSFabricClient() {
    }

    public static void run() {
        registerWoodTypes();
        registerBlockTints();
        registerLayerDefinitions();
        registerParticles();
        registerEntityRenderers();
    }

    /**
     * MC 26.1 removed Sheets.addWoodType. Sign/hanging-sign sprites are filled from
     * WoodType.values() at Sheets class-init, so mod woods registered later must be put in manually.
     */
    private static void registerWoodTypes() {
        for (Supplier<WoodType> woodTypeSupplier : NSClient.SHEET_WOOD_TYPES) {
            WoodType woodType = woodTypeSupplier.get();
            Identifier id = Identifier.parse(woodType.name());
            Sheets.SIGN_SPRITES.put(woodType, Sheets.SIGN_MAPPER.apply(id));
            Sheets.HANGING_SIGN_SPRITES.put(woodType, Sheets.HANGING_SIGN_MAPPER.apply(id));
        }
    }

    private static void registerBlockTints() {
        for (NSClient.TintEntry entry : NSClient.BLOCK_TINTS) {
            BlockColorRegistry.register(entry.layers(), blocks(entry.blocks()));
        }
    }

    private static void registerLayerDefinitions() {
        for (NSClient.LayerEntry entry : NSClient.LAYER_DEFINITIONS) {
            ModelLayerRegistry.registerModelLayer(entry.location(), entry.definition()::get);
        }
    }

    private static void registerParticles() {
        for (NSClient.ParticleEntry<?> entry : NSClient.PARTICLE_PROVIDERS) {
            registerParticle(entry);
        }
    }

    private static void registerEntityRenderers() {
        for (NSClient.BlockEntityRendererEntry<?, ?> entry : NSClient.BLOCK_ENTITY_RENDERERS) {
            registerBlockEntityRenderer(entry);
        }
        for (NSClient.EntityRendererEntry<?> entry : NSClient.ENTITY_RENDERERS) {
            registerEntityRenderer(entry);
        }
    }

    private static <T extends ParticleOptions> void registerParticle(NSClient.ParticleEntry<T> entry) {
        ParticleProviderRegistry.getInstance().register(entry.type().get(), entry.factory()::apply);
    }

    private static <T extends Entity> void registerEntityRenderer(NSClient.EntityRendererEntry<T> entry) {
        EntityRenderers.register(entry.type().get(), entry.provider());
    }

    private static <T extends BlockEntity, S extends BlockEntityRenderState> void registerBlockEntityRenderer(NSClient.BlockEntityRendererEntry<T, S> entry) {
        BlockEntityRenderers.register(entry.type().get(), entry.provider());
    }

    private static Block[] blocks(List<Supplier<? extends Block>> suppliers) {
        return suppliers.stream().map(Supplier::get).toArray(Block[]::new);
    }
}