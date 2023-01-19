package net.hibiscus.naturespirit.terrablender;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.worldgen.RegionUtils;

import java.util.List;
import java.util.function.Consumer;

public class HibiscusRegion extends Region {
    public HibiscusRegion(ResourceLocation name, int weight)
    {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry <Biome> registry, Consumer <Pair <Climate.ParameterPoint, ResourceKey <Biome>>> mapper)
    {
        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
            builder.replaceBiome(Biomes.JUNGLE, NatureSpiritBiomes.SAKURA_GROVE);
            builder.replaceBiome(Biomes.SPARSE_JUNGLE, NatureSpiritBiomes.SAKURA_GROVE);
            builder.replaceBiome(Biomes.BAMBOO_JUNGLE, NatureSpiritBiomes.BAMBOO_SAKURA);
            builder.replaceBiome(Biomes.TAIGA, NatureSpiritBiomes.WISTERIA_FOREST);
            builder.replaceBiome(Biomes.OLD_GROWTH_SPRUCE_TAIGA, NatureSpiritBiomes.REDWOOD_FOREST);
            builder.replaceBiome(Biomes.OLD_GROWTH_PINE_TAIGA, NatureSpiritBiomes.REDWOOD_FOREST);
            builder.replaceBiome(Biomes.SAVANNA, NatureSpiritBiomes.REDWOOD_FOREST);
        });
    }

}
