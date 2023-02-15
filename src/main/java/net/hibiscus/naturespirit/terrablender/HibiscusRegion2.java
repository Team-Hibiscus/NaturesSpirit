package net.hibiscus.naturespirit.terrablender;

import com.mojang.datafixers.util.Pair;
import net.hibiscus.naturespirit.blocks.HibiscusBlocks;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class HibiscusRegion2 extends Region {
    public HibiscusRegion2(Identifier name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry <Biome> registry, Consumer <Pair <MultiNoiseUtil.NoiseHypercube, RegistryKey <Biome>>> mapper) {
        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
            builder.replaceBiome(BiomeKeys.SUNFLOWER_PLAINS, HibiscusBiomes.LAVENDER_FIELDS);
            builder.replaceBiome(BiomeKeys.FLOWER_FOREST, HibiscusBiomes.LAVENDER_FIELDS);
            builder.replaceBiome(BiomeKeys.TAIGA, HibiscusBiomes.WISTERIA_FOREST);
            builder.replaceBiome(BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA, HibiscusBiomes.WISTERIA_FOREST);
            builder.replaceBiome(BiomeKeys.OLD_GROWTH_PINE_TAIGA, HibiscusBiomes.WISTERIA_FOREST);
            builder.replaceBiome(BiomeKeys.RIVER, HibiscusBiomes.ERODED_RIVER);
        });
    }

}
