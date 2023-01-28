package net.hibiscus.naturespirit.terrablender;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class HibiscusRegion extends Region {
    public HibiscusRegion(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry <Biome> registry, Consumer <Pair <Climate.ParameterPoint, ResourceKey <Biome>>> mapper) {
        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
            builder.replaceBiome(Biomes.JUNGLE, HibiscusBiomes.SAKURA_GROVE);
            builder.replaceBiome(Biomes.SPARSE_JUNGLE, HibiscusBiomes.SAKURA_GROVE);
            builder.replaceBiome(Biomes.BAMBOO_JUNGLE, HibiscusBiomes.SAKURA_GROVE);
            builder.replaceBiome(Biomes.SUNFLOWER_PLAINS, HibiscusBiomes.LAVENDER_FIELDS);
            builder.replaceBiome(Biomes.FLOWER_FOREST, HibiscusBiomes.LAVENDER_FIELDS);
            builder.replaceBiome(Biomes.TAIGA, HibiscusBiomes.WISTERIA_FOREST);
            builder.replaceBiome(Biomes.OLD_GROWTH_SPRUCE_TAIGA, HibiscusBiomes.REDWOOD_FOREST);
            builder.replaceBiome(Biomes.OLD_GROWTH_PINE_TAIGA, HibiscusBiomes.REDWOOD_FOREST);
        });
    }

}
