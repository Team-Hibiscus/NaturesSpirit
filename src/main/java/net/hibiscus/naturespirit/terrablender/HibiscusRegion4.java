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

public class HibiscusRegion4 extends Region {
    public HibiscusRegion4(Identifier name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry <Biome> registry, Consumer <Pair <MultiNoiseUtil.NoiseHypercube, RegistryKey <Biome>>> mapper) {
        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
            builder.replaceBiome(BiomeKeys.SAVANNA_PLATEAU, HibiscusBiomes.CYPRESS_FIELDS);
            builder.replaceBiome(BiomeKeys.SAVANNA, HibiscusBiomes.CYPRESS_FIELDS);
            builder.replaceBiome(BiomeKeys.WINDSWEPT_SAVANNA, HibiscusBiomes.CYPRESS_FIELDS);
            builder.replaceBiome(BiomeKeys.PLAINS, HibiscusBiomes.CYPRESS_FIELDS);
            builder.replaceBiome(BiomeKeys.FOREST, HibiscusBiomes.CYPRESS_FIELDS);
            builder.replaceBiome(BiomeKeys.SUNFLOWER_PLAINS, HibiscusBiomes.CYPRESS_FIELDS);
            builder.replaceBiome(BiomeKeys.FLOWER_FOREST, HibiscusBiomes.CYPRESS_FIELDS);
            builder.replaceBiome(BiomeKeys.JUNGLE, BiomeKeys.FOREST);
            builder.replaceBiome(BiomeKeys.BAMBOO_JUNGLE, BiomeKeys.FOREST);
            builder.replaceBiome(BiomeKeys.SPARSE_JUNGLE, BiomeKeys.PLAINS);
        });
    }

}
