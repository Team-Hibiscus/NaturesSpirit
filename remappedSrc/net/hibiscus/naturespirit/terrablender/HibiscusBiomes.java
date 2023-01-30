package net.hibiscus.naturespirit.terrablender;


import net.hibiscus.naturespirit.NatureSpirit;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.feature.PlacedFeature;

public class HibiscusBiomes {

    public static final RegistryKey <Biome> SAKURA_GROVE = register("sakura_grove");
    public static final RegistryKey <Biome> LAVENDER_FIELDS = register("lavender_fields");
    public static final RegistryKey <Biome> WISTERIA_FOREST = register("wisteria_forest");
    public static final RegistryKey <Biome> BAMBOO_SAKURA = register("bamboo_sakura");
    public static final RegistryKey <Biome> REDWOOD_FOREST = register("redwood_forest");

    private static RegistryKey <Biome> register(String name) {
        System.out.println("Registered Resource Key for biome: " + name);
        return RegistryKey.of(RegistryKeys.BIOME, new Identifier(NatureSpirit.MOD_ID, name));
    }


    public static void bootstrap(Registerable <Biome> bootstapContext) {
        RegistryEntryLookup <PlacedFeature> holderGetter = bootstapContext.getRegistryLookup(RegistryKeys.PLACED_FEATURE);
        RegistryEntryLookup <ConfiguredCarver <?>> holderGetter2 = bootstapContext.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER);


        register(bootstapContext, SAKURA_GROVE, NatureSpiritOverworldBiomes.sakuraGrove(holderGetter, holderGetter2));
        register(bootstapContext, BAMBOO_SAKURA, NatureSpiritOverworldBiomes.bambooSakura(holderGetter, holderGetter2));
        register(bootstapContext, WISTERIA_FOREST, NatureSpiritOverworldBiomes.wisteriaForest(holderGetter, holderGetter2));
        register(bootstapContext, LAVENDER_FIELDS, NatureSpiritOverworldBiomes.lavenderFields(holderGetter, holderGetter2));
        register(bootstapContext, REDWOOD_FOREST, NatureSpiritOverworldBiomes.redwoodForest(holderGetter, holderGetter2));
    }

    private static void register(Registerable <Biome> context, RegistryKey <Biome> key, Biome biome) {
        System.out.println("Registered Bootstrap for Biome");
        context.register(key, biome);
    }


    public static void registerBiomes() {
    }
}