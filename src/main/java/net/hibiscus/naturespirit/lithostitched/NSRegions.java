package net.hibiscus.naturespirit.lithostitched;

import dev.worldgen.lithostitched.api.event.AddRegionsEvent;
import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries;
import dev.worldgen.lithostitched.impl.worldgen.biomeinjector.region.Region;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.registration.NSTags;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;

public final class NSRegions {
    public static final ResourceKey<Region> TERRA_FERAX = ResourceKey.create(LithostitchedRegistries.REGION, ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "terra_ferax"));

    public static void init() {
        AddRegionsEvent.EVENT.register((registries, consumer) -> {
            HolderSet<Biome> TERRA_FERAX_BIOMES = registries.lookupOrThrow(Registries.BIOME).getOrThrow(NSTags.Biomes.IS_TERRA_FERAX);
            consumer.accept(
                    TERRA_FERAX,
                    Level.OVERWORLD,
                    TERRA_FERAX_BIOMES,
                    1000
            );
        });
    }
}