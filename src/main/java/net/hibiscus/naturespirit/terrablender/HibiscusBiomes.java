package net.hibiscus.naturespirit.terrablender;


import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public class HibiscusBiomes {
    public static void registerBiomes()
{
    register(NatureSpiritBiomes.SAKURA_GROVE, NatureSpiritOverworldBiomes.sakuraGrove());
//    register(NatureSpiritBiomes.WISTERIA_FOREST, NatureSpiritOverworldBiomes.wisteriaForest());
}

    private static void register(ResourceKey<Biome> key, Biome biome)
    {
        BuiltinRegistries.register(BuiltinRegistries.BIOME, key, biome);
    }
}
