package net.hibiscus.naturespirit.terrablender;

import com.mojang.datafixers.util.Pair;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class HibiscusRegion extends Region {
    public HibiscusRegion(Identifier name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry <Biome> registry, Consumer <Pair <MultiNoiseUtil.NoiseHypercube, RegistryKey <Biome>>> mapper) {
        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
            builder.replaceBiome(BiomeKeys.JUNGLE, HibiscusBiomes.SAKURA_GROVE);
            builder.replaceBiome(BiomeKeys.SPARSE_JUNGLE, HibiscusBiomes.SAKURA_GROVE);
            builder.replaceBiome(BiomeKeys.BAMBOO_JUNGLE, HibiscusBiomes.SAKURA_GROVE);
            builder.replaceBiome(BiomeKeys.SUNFLOWER_PLAINS, HibiscusBiomes.LAVENDER_FIELDS);
            builder.replaceBiome(BiomeKeys.FLOWER_FOREST, HibiscusBiomes.LAVENDER_FIELDS);
            builder.replaceBiome(BiomeKeys.TAIGA, HibiscusBiomes.WISTERIA_FOREST);
        });
    }

}
