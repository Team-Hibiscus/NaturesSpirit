package net.hibiscus.naturespirit.compat.lithostitched;

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
import net.hibiscus.naturespirit.registration.NSBiomes;
import net.hibiscus.naturespirit.world.NSSurfaceRules;
import net.hibiscus.naturespirit.world.parameters.*;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class LithostitchedEventHandlers {
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

            if (NSConfig.hasMarsh) {
                consumer.accept(
                        ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "marsh_1"),
                        BiomeInjector.builder(Level.OVERWORLD).replacePartially(
                                registries.holderOrThrow(Biomes.SWAMP),
                                registries.holderOrThrow(NSBiomes.MARSH),
                                ParameterBuilder.create().climateMax(BiomeInjector.ClimateParameter.HUMIDITY, .1F)
                        )
                );
                consumer.accept(
                        ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "marsh_2"),
                        BiomeInjector.builder(Level.OVERWORLD).replacePartially(
                                registries.holderOrThrow(Biomes.SWAMP),
                                registries.holderOrThrow(NSBiomes.MARSH),
                                ParameterBuilder.create().climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, .55F, 1.0F)
                        )
                );
            }

            if (NSConfig.hasAridRiver) {
                consumer.accept(
                        ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "arid_river_1"),
                        BiomeInjector.builder(Level.OVERWORLD).priority(1700).replacePartially(
                                registries.holderOrThrow(Biomes.RIVER),
                                registries.holderOrThrow(NSBiomes.ARID_RIVER),
                                ParameterBuilder.create()
                                        .climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, -.45F, -.15F).region(TERRA_SOLARIS)
                        )
                );
                consumer.accept(
                        ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "arid_river_2"),
                        BiomeInjector.builder(Level.OVERWORLD).priority(1700).replacePartially(
                                registries.holderOrThrow(Biomes.RIVER),
                                registries.holderOrThrow(NSBiomes.ARID_RIVER),
                                ParameterBuilder.create()
                                        .climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, .55F, 1.0F).region(TERRA_FERAX)
                        )
                );
            }

            if (NSConfig.hasAutumnalRiver) {
                consumer.accept(
                        ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "autumnal_river"),
                        BiomeInjector.builder(Level.OVERWORLD).replacePartially(
                                registries.holderOrThrow(Biomes.RIVER),
                                registries.holderOrThrow(NSBiomes.AUTUMNAL_RIVER),
                                ParameterBuilder.create().climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, -.45F, -.15F).region(TERRA_FLAVA)
                        )
                );
            }

            if (NSConfig.hasTundraRiver) {
                consumer.accept(
                        ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "tundra_river_1"),
                        BiomeInjector.builder(Level.OVERWORLD).replacePartially(
                                registries.holderOrThrow(Biomes.RIVER),
                                registries.holderOrThrow(NSBiomes.TUNDRA_RIVER),
                                ParameterBuilder.create().climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, -1F, -.45F).region(TERRA_LAETA)
                        )
                );
                consumer.accept(
                        ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "tundra_river_2"),
                        BiomeInjector.builder(Level.OVERWORLD).replacePartially(
                                registries.holderOrThrow(Biomes.RIVER),
                                registries.holderOrThrow(NSBiomes.TUNDRA_RIVER),
                                ParameterBuilder.create()
                                        .climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, -1F, -.45F)
                                        .climateRange(BiomeInjector.ClimateParameter.HUMIDITY, -1F, .1F).region(TERRA_FERAX)
                        )
                );
            }

            if (NSConfig.hasAlpineRiver) {
                consumer.accept(
                        ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "alpine_river"),
                        BiomeInjector.builder(Level.OVERWORLD).replacePartially(
                                registries.holderOrThrow(Biomes.RIVER),
                                registries.holderOrThrow(NSBiomes.ALPINE_RIVER),
                                ParameterBuilder.create().climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, -.45F, -.15F).region(TERRA_LAETA)
                        )
                );
            }

            if (NSConfig.hasPrairieRiver) {
                consumer.accept(
                        ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "prairie_river"),
                        BiomeInjector.builder(Level.OVERWORLD).replacePartially(
                                registries.holderOrThrow(Biomes.RIVER),
                                registries.holderOrThrow(NSBiomes.PRAIRIE_RIVER),
                                ParameterBuilder.create().climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, -.45F, -.15F).region(TERRA_FERAX)
                        )
                );
            }


            if (NSConfig.hasXericRiver) {
                consumer.accept(
                        ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "xeric_river_1"),
                        BiomeInjector.builder(Level.OVERWORLD).priority(1700).replacePartially(
                                registries.holderOrThrow(Biomes.RIVER),
                                registries.holderOrThrow(NSBiomes.XERIC_RIVER),
                                ParameterBuilder.create()
                                        .climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, .2F, 0.55F)
                                        .climateRange(BiomeInjector.ClimateParameter.HUMIDITY, -1, -0.1F).region(TERRA_LAETA)
                        )
                );
                consumer.accept(
                        ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "xeric_river_2"),
                        BiomeInjector.builder(Level.OVERWORLD).priority(1700).replacePartially(
                                registries.holderOrThrow(Biomes.RIVER),
                                registries.holderOrThrow(NSBiomes.XERIC_RIVER),
                                ParameterBuilder.create()
                                        .climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, .55F, 1.0F)
                                        .climateRange(BiomeInjector.ClimateParameter.EROSION, -.375F, .050F)
                                        .climateRange(BiomeInjector.ClimateParameter.CONTINENTALNESS, .03F, 1F).region(TERRA_LAETA)
                        )
                );
                consumer.accept(
                        ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "xeric_river_3"),
                        BiomeInjector.builder(Level.OVERWORLD).priority(1700).replacePartially(
                                registries.holderOrThrow(Biomes.RIVER),
                                registries.holderOrThrow(NSBiomes.XERIC_RIVER),
                                ParameterBuilder.create()
                                        .climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, .55F, 1.0F)
                                        .climateRange(BiomeInjector.ClimateParameter.EROSION, -1F, -.375F)
                                        .climateRange(BiomeInjector.ClimateParameter.CONTINENTALNESS, -.110F, .03F).region(TERRA_LAETA)
                        )
                );
            }

            if (NSConfig.hasPinkSandyRiver) {
                consumer.accept(
                        ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "pink_sandy_river_1"),
                        BiomeInjector.builder(Level.OVERWORLD).priority(1900).replacePartially(
                                registries.holderOrThrow(Biomes.RIVER),
                                registries.holderOrThrow(NSBiomes.PINK_SANDY_RIVER),
                                ParameterBuilder.create().climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, .55F, 1.0F).region(TERRA_LAETA)
                        )
                );
                consumer.accept(
                        ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "pink_sandy_river_2"),
                        BiomeInjector.builder(Level.OVERWORLD).replacePartially(
                                registries.holderOrThrow(Biomes.RIVER),
                                registries.holderOrThrow(NSBiomes.PINK_SANDY_RIVER),
                                ParameterBuilder.create().climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, .55F, 1.0F).region(TERRA_FLAVA)
                        )
                );
                consumer.accept(
                        ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "pink_sandy_river_3"),
                        BiomeInjector.builder(Level.OVERWORLD).replacePartially(
                                registries.holderOrThrow(Biomes.RIVER),
                                registries.holderOrThrow(NSBiomes.PINK_SANDY_RIVER),
                                ParameterBuilder.create()
                                        .climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, .2F, 0.55F)
                                        .climateRange(BiomeInjector.ClimateParameter.HUMIDITY, -1, 0.1F).region(TERRA_FLAVA)
                        )
                );
            }

            if (NSConfig.hasRedSandyRiver) {
                consumer.accept(
                        ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "scorched_dunes_river"),
                        BiomeInjector.builder(Level.OVERWORLD).replacePartially(
                                registries.holderOrThrow(Biomes.RIVER),
                                registries.holderOrThrow(NSBiomes.RED_SANDY_RIVER),
                                ParameterBuilder.create().climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, .55F, 1.0F).region(TERRA_MATER)
                        )
                );
                consumer.accept(
                        ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "mesa_river_1"),
                        BiomeInjector.builder(Level.OVERWORLD).priority(1800).replacePartially(
                                registries.holderOrThrow(Biomes.RIVER),
                                registries.holderOrThrow(NSBiomes.RED_SANDY_RIVER),
                                ParameterBuilder.create()
                                        .climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, .55F, 1.0F)
                                        .climateRange(BiomeInjector.ClimateParameter.EROSION, -.375F, .050F)
                                        .climateRange(BiomeInjector.ClimateParameter.CONTINENTALNESS, .03F, 1F)
                        )
                );
                consumer.accept(
                        ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "mesa_river_2"),
                        BiomeInjector.builder(Level.OVERWORLD).priority(1800).replacePartially(
                                registries.holderOrThrow(Biomes.RIVER),
                                registries.holderOrThrow(NSBiomes.RED_SANDY_RIVER),
                                ParameterBuilder.create()
                                        .climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, .55F, 1.0F)
                                        .climateRange(BiomeInjector.ClimateParameter.EROSION, -1F, -.375F)
                                        .climateRange(BiomeInjector.ClimateParameter.CONTINENTALNESS, -.110F, .03F)
                        )
                );
            }

            if (NSConfig.hasSandyRiver) {
                consumer.accept(
                        ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "sandy_river"),
                        BiomeInjector.builder(Level.OVERWORLD).priority(2000).replacePartially(
                                registries.holderOrThrow(Biomes.RIVER),
                                registries.holderOrThrow(NSBiomes.SANDY_RIVER),
                                ParameterBuilder.create().climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, .55F, 1.0F)
                        )
                );
            }

            if (NSConfig.hasLushRiver) {
                consumer.accept(
                        ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "lush_river"),
                        BiomeInjector.builder(Level.OVERWORLD).priority(2000).replacePartially(
                                registries.holderOrThrow(Biomes.RIVER),
                                registries.holderOrThrow(NSBiomes.LUSH_RIVER),
                                ParameterBuilder.create()
                                        .climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, .2F, 0.55F)
                                        .climateRange(BiomeInjector.ClimateParameter.HUMIDITY, .1, 1F)
                        )
                );
                if (NSConfig.hasCypressFields) {
                    consumer.accept(
                            ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "cypress_river"),
                            BiomeInjector.builder(Level.OVERWORLD).priority(2500).replacePartially(
                                    registries.holderOrThrow(NSBiomes.LUSH_RIVER),
                                    registries.holderOrThrow(Biomes.RIVER),
                                    ParameterBuilder.create()
                                            .climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, .2F, 0.55F)
                                            .climateRange(BiomeInjector.ClimateParameter.HUMIDITY, .1, 1F).region(TERRA_LAETA)
                            )
                    );
                }
                if (NSConfig.hasChaparral) {
                    consumer.accept(
                            ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "chaparral_river"),
                            BiomeInjector.builder(Level.OVERWORLD).priority(2500).replacePartially(
                                    registries.holderOrThrow(NSBiomes.LUSH_RIVER),
                                    registries.holderOrThrow(Biomes.RIVER),
                                    ParameterBuilder.create()
                                            .climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, .2F, 0.55F)
                                            .climateRange(BiomeInjector.ClimateParameter.HUMIDITY, .1, 1F).region(TERRA_FERAX)
                            )
                    );
                }
                if (NSConfig.hasSparseTropicalWoods) {
                    consumer.accept(
                            ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "sparse_tropical_woods_river"),
                            BiomeInjector.builder(Level.OVERWORLD).priority(2500).replacePartially(
                                    registries.holderOrThrow(Biomes.RIVER),
                                    registries.holderOrThrow(NSBiomes.LUSH_RIVER),
                                    ParameterBuilder.create()
                                            .climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, .2F, 0.55F)
                                            .climateRange(BiomeInjector.ClimateParameter.HUMIDITY, -.1, .1F).region(TERRA_MATER)
                            )
                    );
                }
                if (NSConfig.hasSparseTropicalWoods) {
                    consumer.accept(
                            ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "sparse_tropical_woods_river"),
                            BiomeInjector.builder(Level.OVERWORLD).priority(2500).replacePartially(
                                    registries.holderOrThrow(Biomes.RIVER),
                                    registries.holderOrThrow(NSBiomes.LUSH_RIVER),
                                    ParameterBuilder.create()
                                            .climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, .2F, 0.55F)
                                            .climateRange(BiomeInjector.ClimateParameter.HUMIDITY, -.1, .1F).region(TERRA_MATER)
                            )
                    );
                }
            }

            if (NSConfig.hasSavannaRiver) {
                consumer.accept(
                        ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "savanna_river"),
                        BiomeInjector.builder(Level.OVERWORLD).priority(2000).replacePartially(
                                registries.holderOrThrow(Biomes.RIVER),
                                registries.holderOrThrow(NSBiomes.SAVANNA_RIVER),
                                ParameterBuilder.create()
                                        .climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, .2F, 0.55F)
                                        .climateRange(BiomeInjector.ClimateParameter.HUMIDITY, -1, -0.1F)
                        )
                );
            }

            if (NSConfig.hasBirchRiver) {
                consumer.accept(
                        ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "birch_river"),
                        BiomeInjector.builder(Level.OVERWORLD).replacePartially(
                                registries.holderOrThrow(Biomes.RIVER),
                                registries.holderOrThrow(NSBiomes.BIRCH_RIVER),
                                ParameterBuilder.create()
                                        .climateRange(BiomeInjector.ClimateParameter.HUMIDITY, 0.1F, 0.3F)
                                        .climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, -0.15F, 0.2F)
                        )
                );
                if (NSConfig.hasSugiForest) {
                    consumer.accept(
                            ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "sugi_river"),
                            BiomeInjector.builder(Level.OVERWORLD).priority(1700).replacePartially(
                                    registries.holderOrThrow(NSBiomes.BIRCH_RIVER),
                                    registries.holderOrThrow(Biomes.RIVER),
                                    ParameterBuilder.create()
                                            .climateRange(BiomeInjector.ClimateParameter.HUMIDITY, 0.1F, 0.3F)
                                            .climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, -0.15F, 0.2F).region(TERRA_SOLARIS)
                            )
                    );
                }
            }

            if (NSConfig.hasTaigaRiver) {
                consumer.accept(
                        ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "taiga_river_1"),
                        BiomeInjector.builder(Level.OVERWORLD).priority(2000).replacePartially(
                                registries.holderOrThrow(Biomes.RIVER),
                                registries.holderOrThrow(NSBiomes.TAIGA_RIVER),
                                ParameterBuilder.create()
                                        .climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, -.45F, -.15F)
                                        .climateRange(BiomeInjector.ClimateParameter.HUMIDITY, .1F, 1)
                        )
                );
                consumer.accept(
                        ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "taiga_river_2"),
                        BiomeInjector.builder(Level.OVERWORLD).priority(2000).replacePartially(
                                registries.holderOrThrow(Biomes.RIVER),
                                registries.holderOrThrow(NSBiomes.TAIGA_RIVER),
                                ParameterBuilder.create()
                                        .climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, -1F, -.45F)
                                        .climateRange(BiomeInjector.ClimateParameter.HUMIDITY, .3F, 1)
                        )
                );
            }


            if (NSConfig.hasColdBeach) {
                consumer.accept(
                        ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "cold_beach"),
                        BiomeInjector.builder(Level.OVERWORLD).replacePartially(
                                registries.holderOrThrow(Biomes.BEACH),
                                registries.holderOrThrow(NSBiomes.COLD_BEACH),
                                ParameterBuilder.create()
                                        .climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, -.45F, -.15F)
                        )
                );
            }

            if (NSConfig.hasLukewarmBeach) {
                consumer.accept(
                        ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "lukewarm_beach"),
                        BiomeInjector.builder(Level.OVERWORLD).replacePartially(
                                registries.holderOrThrow(Biomes.BEACH),
                                registries.holderOrThrow(NSBiomes.LUKEWARM_BEACH),
                                ParameterBuilder.create()
                                        .climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, .2F, .55F)
                                        .climateRange(BiomeInjector.ClimateParameter.HUMIDITY, -1, .1F)
                        )
                );
            }

            if (NSConfig.hasRedBeach) {
                consumer.accept(
                        ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "red_beach_1"),
                        BiomeInjector.builder(Level.OVERWORLD).replacePartially(
                                registries.holderOrThrow(Biomes.BEACH),
                                registries.holderOrThrow(NSBiomes.RED_BEACH),
                                ParameterBuilder.create()
                                        .climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, .2F, .55F)
                                        .climateRange(BiomeInjector.ClimateParameter.HUMIDITY, .7F, 1)
                        )
                );
                consumer.accept(
                        ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "red_beach_2"),
                        BiomeInjector.builder(Level.OVERWORLD).replacePartially(
                                registries.holderOrThrow(NSBiomes.TROPICAL_SHORES),
                                registries.holderOrThrow(NSBiomes.RED_BEACH),
                                ParameterBuilder.create()
                                        .climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, .2F, .55F)
                                        .climateRange(BiomeInjector.ClimateParameter.HUMIDITY, .7F, 1)
                        )
                );
            }

            if (NSConfig.hasTropicalShores) {
                consumer.accept(
                        ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "tropical_shores"),
                        BiomeInjector.builder(Level.OVERWORLD).replacePartially(
                                registries.holderOrThrow(Biomes.BEACH),
                                registries.holderOrThrow(NSBiomes.TROPICAL_SHORES),
                                ParameterBuilder.create()
                                        .climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, .2F, .55F)
                                        .climateRange(BiomeInjector.ClimateParameter.HUMIDITY, .1F, .7F)
                        )
                );
            }

        });
    }

    // Only used for surface rules
    public static void handleAddWorldgenModifiersEvent() {
        AddWorldgenModifiersEvent.EVENT.register((registries, consumer) -> {
            consumer.accept(
                    ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "surface_rules"),
                    WorldgenModifier.builder()
                            .priority(3000)  // default is 1000; we set it at 3000 to let datapacks override our surface rules by default
                            .addSurfaceRule(Level.OVERWORLD, InjectionType.PREPEND, NSSurfaceRules.makeRules())
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
                BiomeInjector.builder(Level.OVERWORLD).priority(1).dispatchAlternateLayout(
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
