package net.hibiscus.naturespirit.terrablender;

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
//            builder.replaceBiome(Biomes.FOREST, NatureSpiritBiomes.WISTERIA_FOREST);

//            List<Climate.ParameterPoint> sakuraGroveParameterPoints = new ParameterUtils.ParameterPointListBuilder()
//                    .temperature(ParameterUtils.Temperature.WARM, ParameterUtils.Temperature.HOT)
//                    .humidity(ParameterUtils.Humidity.NEUTRAL, ParameterUtils.Humidity.WET)
//                    .continentalness(ParameterUtils.Continentalness.span(ParameterUtils.Continentalness.NEAR_INLAND, ParameterUtils.Continentalness.FAR_INLAND), ParameterUtils.Continentalness.span(ParameterUtils.Continentalness.MID_INLAND, ParameterUtils.Continentalness.FAR_INLAND))
//                    .erosion(ParameterUtils.Erosion.EROSION_3, ParameterUtils.Erosion.EROSION_4)
//                    .depth(ParameterUtils.Depth.SURFACE)
//                    .weirdness(ParameterUtils.Weirdness.FULL_RANGE)
//                    .build();
//
//            sakuraGroveParameterPoints.forEach(point -> builder.replaceBiome(point, NatureSpiritBiomes.SAKURA_GROVE));
        });

    }

}
