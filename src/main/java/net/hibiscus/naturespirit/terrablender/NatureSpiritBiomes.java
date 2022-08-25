package net.hibiscus.naturespirit.terrablender;

import net.hibiscus.naturespirit.NatureSpirit;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public class NatureSpiritBiomes {
    public static final ResourceKey<Biome> SAKURA_GROVE = register("sakura_grove");
//    public static final ResourceKey<Biome> WISTERIA_FOREST = register("wisteria_forest");

    private static ResourceKey<Biome> register(String name)
    {
        return ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(NatureSpirit.MOD_ID, name));
    }
}
