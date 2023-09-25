package net.hibiscus.naturespirit.terrablender;

import com.mojang.datafixers.util.Pair;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;

public class HibiscusRegion3 extends Region {
   public HibiscusRegion3(ResourceLocation name, int weight) {
      super(name, RegionType.OVERWORLD, weight);
   }

   @Override public void addBiomes(Registry <Biome> registry, Consumer <Pair <Climate.ParameterPoint, ResourceKey <Biome>>> mapper) {
      (new Region3Parameters()).writeOverworldBiomeParameters(mapper);
   }

}