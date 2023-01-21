package net.hibiscus.naturespirit.terrablender;


import net.hibiscus.naturespirit.NatureSpirit;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class HibiscusBiomes {

    public static final ResourceKey <Biome> SAKURA_GROVE = register("sakura_grove");
    public static final ResourceKey <Biome> WISTERIA_FOREST = register("wisteria_forest");
    public static final ResourceKey <Biome> BAMBOO_SAKURA = register("bamboo_sakura");
    public static final ResourceKey <Biome> REDWOOD_FOREST = register("redwood_forest");

    private static ResourceKey <Biome> register(String name) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(NatureSpirit.MOD_ID, name));
    }


    public static void bootstrap(BootstapContext <Biome> bootstapContext) {
        HolderGetter <PlacedFeature> holderGetter = bootstapContext.lookup(Registries.PLACED_FEATURE);
        HolderGetter <ConfiguredWorldCarver <?>> holderGetter2 = bootstapContext.lookup(Registries.CONFIGURED_CARVER);


        bootstapContext.register(SAKURA_GROVE, NatureSpiritOverworldBiomes.sakuraGrove(holderGetter, holderGetter2));
        bootstapContext.register(BAMBOO_SAKURA, NatureSpiritOverworldBiomes.bambooSakura(holderGetter, holderGetter2));
        bootstapContext.register(WISTERIA_FOREST, NatureSpiritOverworldBiomes.wisteriaForest(holderGetter, holderGetter2));
        bootstapContext.register(REDWOOD_FOREST, NatureSpiritOverworldBiomes.redwoodForest(holderGetter, holderGetter2));
    }


    public static void registerBiomes() {
    }
}