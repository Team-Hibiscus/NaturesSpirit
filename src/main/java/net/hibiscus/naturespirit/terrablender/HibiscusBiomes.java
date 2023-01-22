package net.hibiscus.naturespirit.terrablender;


import net.hibiscus.naturespirit.NatureSpirit;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class HibiscusBiomes {

    public static final ResourceKey <Biome> SAKURA_GROVE = register("sakura_grove");
    public static final ResourceKey <Biome> WISTERIA_FOREST = register("wisteria_forest");
    public static final ResourceKey <Biome> BAMBOO_SAKURA = register("bamboo_sakura");
    public static final ResourceKey <Biome> REDWOOD_FOREST = register("redwood_forest");

    private static ResourceKey <Biome> register(String name) {
        System.out.println("Registered Resource Key for biome: " + name);
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(NatureSpirit.MOD_ID, name));
    }


    public static void bootstrap(BootstapContext <Biome> bootstapContext) {
        HolderGetter <PlacedFeature> holderGetter = bootstapContext.lookup(Registries.PLACED_FEATURE);
        HolderGetter <ConfiguredWorldCarver <?>> holderGetter2 = bootstapContext.lookup(Registries.CONFIGURED_CARVER);


        register(bootstapContext, SAKURA_GROVE, NatureSpiritOverworldBiomes.sakuraGrove(holderGetter, holderGetter2));
        register(bootstapContext, BAMBOO_SAKURA, NatureSpiritOverworldBiomes.bambooSakura(holderGetter, holderGetter2));
        register(bootstapContext, WISTERIA_FOREST, NatureSpiritOverworldBiomes.wisteriaForest(holderGetter, holderGetter2));
        register(bootstapContext, REDWOOD_FOREST, NatureSpiritOverworldBiomes.redwoodForest(holderGetter, holderGetter2));
    }

    private static void register(BootstapContext <Biome> context, ResourceKey <Biome> key, Biome biome) {
        System.out.println("Registered Bootstrap for Biome");
        context.register(key, biome);
    }


    public static void registerBiomes() {
    }
}