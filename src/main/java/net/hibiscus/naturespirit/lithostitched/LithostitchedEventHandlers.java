package net.hibiscus.naturespirit.lithostitched;

import com.mojang.datafixers.util.Pair;
import dev.worldgen.lithostitched.api.event.AddBiomeInjectorsEvent;
import dev.worldgen.lithostitched.api.event.AddRegionsEvent;
import dev.worldgen.lithostitched.api.event.AddWorldgenModifiersEvent;
import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries;
import dev.worldgen.lithostitched.api.util.InjectionType;
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector;
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.ParameterBuilder;
import dev.worldgen.lithostitched.api.worldgen.modifier.WorldgenModifier;
import dev.worldgen.lithostitched.impl.worldgen.biomeinjector.region.Region;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.config.NSConfig;
import net.hibiscus.naturespirit.world.NSSurfaceRules;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.dimension.LevelStem;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public final class LithostitchedEventHandlers {
    private static final ResourceKey<Region> TERRA_FERAX = ResourceKey.create(LithostitchedRegistries.REGION, ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "terra_ferax"));
    private static final ResourceKey<Region> TERRA_FLAVA = ResourceKey.create(LithostitchedRegistries.REGION, ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "terra_flava"));
    private static final ResourceKey<Region> TERRA_LAETA = ResourceKey.create(LithostitchedRegistries.REGION, ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "terra_laeta"));
    private static final ResourceKey<Region> TERRA_MATER = ResourceKey.create(LithostitchedRegistries.REGION, ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "terra_mater"));
    private static final ResourceKey<Region> TERRA_SOLARIS = ResourceKey.create(LithostitchedRegistries.REGION, ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "terra_solaris"));

    public static void handleAddRegionsEvent() {
        AddRegionsEvent.EVENT.register((registries, consumer) -> {
            HolderSet<Biome> isOverworld = registries.lookupOrThrow(Registries.BIOME).getOrThrow(BiomeTags.IS_OVERWORLD);
            consumer.accept(TERRA_FERAX, Level.OVERWORLD, isOverworld, NSConfig.terraFeraxWeight);
            consumer.accept(TERRA_FLAVA, Level.OVERWORLD, isOverworld, NSConfig.terraFlavaWeight);
            consumer.accept(TERRA_LAETA, Level.OVERWORLD, isOverworld, NSConfig.terraLaetaWeight);
            consumer.accept(TERRA_MATER, Level.OVERWORLD, isOverworld, NSConfig.terraMaterWeight);
            consumer.accept(TERRA_SOLARIS, Level.OVERWORLD, isOverworld, NSConfig.terraSolarisWeight);
        });
    }

    public static void handleAddBiomeInjectorsEvent() {
        AddBiomeInjectorsEvent.EVENT.register((registries, consumer) -> {
            addBiomeInjector(registries, consumer, "terra_ferax", new TerraFeraxParameters()::addBiomes, TERRA_FERAX);
            addBiomeInjector(registries, consumer, "terra_flava", new TerraFlavaParameters()::addBiomes, TERRA_FLAVA);
            addBiomeInjector(registries, consumer, "terra_laeta", new TerraLaetaParameters()::addBiomes, TERRA_LAETA);
            addBiomeInjector(registries, consumer, "terra_mater", new TerraMaterParameters()::addBiomes, TERRA_MATER);
            addBiomeInjector(registries, consumer, "terra_solaris", new TerraSolarisParameters()::addBiomes, TERRA_SOLARIS);
        });
    }

    public static void handleAddWorldgenModifiersEvent() {
        AddWorldgenModifiersEvent.EVENT.register((registries, consumer) -> {
            consumer.accept(
                    ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "surface_rules"),
                    WorldgenModifier.builder().addSurfaceRule(
                            LevelStem.OVERWORLD,
                            InjectionType.PREPEND,
                            NSSurfaceRules.makeRules()
                    )
            );
        });
    }

    private static void addBiomeInjector(RegistryAccess registries, BiConsumer<ResourceLocation, BiomeInjector> consumer, String name,
            BiomeParameterProvider parameterProvider, ResourceKey<Region> region) {
        List<Pair<Climate.ParameterPoint, Holder<Biome>>> regionPoints = new ArrayList<>();
        parameterProvider.addBiomes(point -> {
            Holder<Biome> holder = registries.lookupOrThrow(Registries.BIOME).getOrThrow(point.getSecond());
            regionPoints.add(Pair.of(point.getFirst(), holder));
        });

        Climate.ParameterList<Holder<Biome>> regionLayout = new Climate.ParameterList<>(regionPoints);
        consumer.accept(
                ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, name),
                BiomeInjector.builder(Level.OVERWORLD).dispatchAlternateLayout(
                        ParameterBuilder.create().region(region),
                        regionLayout)
        );
    }

    // This is feels really hacky honestly
    @FunctionalInterface
    private interface BiomeParameterProvider {
        void addBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> parameters);
    }
}
