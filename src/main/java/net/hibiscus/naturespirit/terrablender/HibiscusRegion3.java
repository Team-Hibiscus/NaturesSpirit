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

public class HibiscusRegion3 extends Region {
    public HibiscusRegion3(Identifier name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry <Biome> registry, Consumer <Pair <MultiNoiseUtil.NoiseHypercube, RegistryKey <Biome>>> mapper) {
        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
            builder.replaceBiome(BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA, HibiscusBiomes.REDWOOD_FOREST);
            builder.replaceBiome(BiomeKeys.OLD_GROWTH_PINE_TAIGA, HibiscusBiomes.REDWOOD_FOREST);
            builder.replaceBiome(BiomeKeys.TAIGA, HibiscusBiomes.FIR_FOREST);
            builder.replaceBiome(BiomeKeys.BIRCH_FOREST, HibiscusBiomes.GOLDEN_WILDS);
            builder.replaceBiome(BiomeKeys.PLAINS, HibiscusBiomes.GOLDEN_FIELDS);
            builder.replaceBiome(BiomeKeys.FOREST, HibiscusBiomes.GOLDEN_WILDS);
            builder.replaceBiome(BiomeKeys.FLOWER_FOREST, HibiscusBiomes.GOLDEN_WILDS);
            builder.replaceBiome(BiomeKeys.SUNFLOWER_PLAINS, HibiscusBiomes.GOLDEN_FIELDS);
            builder.replaceBiome(BiomeKeys.OLD_GROWTH_BIRCH_FOREST, HibiscusBiomes.GOLDEN_WILDS);
            builder.replaceBiome(BiomeKeys.WINDSWEPT_GRAVELLY_HILLS, HibiscusBiomes.GOLDEN_WILDS);
            builder.replaceBiome(BiomeKeys.WINDSWEPT_HILLS, HibiscusBiomes.GOLDEN_WILDS);
            builder.replaceBiome(BiomeKeys.WINDSWEPT_FOREST, HibiscusBiomes.GOLDEN_WILDS);
//            builder.replaceBiome(BiomeKeys.SNOWY_TAIGA, HibiscusBiomes.GOLDEN_WILDS);
//            builder.replaceBiome(BiomeKeys.SNOWY_PLAINS, HibiscusBiomes.GOLDEN_WILDS);
            builder.replaceBiome(BiomeKeys.SAVANNA, BiomeKeys.PLAINS);
            builder.replaceBiome(BiomeKeys.SAVANNA_PLATEAU, BiomeKeys.FOREST);
            builder.replaceBiome(BiomeKeys.WINDSWEPT_SAVANNA, BiomeKeys.WINDSWEPT_HILLS);
            builder.replaceBiome(BiomeKeys.GROVE, HibiscusBiomes.FIR_FOREST);
            builder.replaceBiome(BiomeKeys.MEADOW, HibiscusBiomes.GOLDEN_FIELDS);
            builder.replaceBiome(BiomeKeys.FROZEN_PEAKS, BiomeKeys.STONY_PEAKS);
            builder.replaceBiome(BiomeKeys.JAGGED_PEAKS, BiomeKeys.STONY_PEAKS);
            builder.replaceBiome(BiomeKeys.SNOWY_SLOPES, BiomeKeys.STONY_PEAKS);
            builder.replaceBiome(BiomeKeys.JUNGLE, BiomeKeys.FOREST);
            builder.replaceBiome(BiomeKeys.SPARSE_JUNGLE, BiomeKeys.PLAINS);
            builder.replaceBiome(BiomeKeys.BAMBOO_JUNGLE, BiomeKeys.FOREST);
//            builder.replaceBiome(BiomeKeys.SNOWY_BEACH, BiomeKeys.BEACH);
//            builder.replaceBiome(BiomeKeys.FROZEN_RIVER, BiomeKeys.RIVER);
//            builder.replaceBiome(BiomeKeys.FROZEN_OCEAN, BiomeKeys.COLD_OCEAN);
//            builder.replaceBiome(BiomeKeys.DEEP_FROZEN_OCEAN, BiomeKeys.DEEP_COLD_OCEAN);
        });
    }

}
