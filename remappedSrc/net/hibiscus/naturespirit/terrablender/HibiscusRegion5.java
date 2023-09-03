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

public class HibiscusRegion5 extends Region {
   public HibiscusRegion5(ResourceLocation name, int weight) {
      super(name, RegionType.OVERWORLD, weight);
   }

   @Override
   public void addBiomes(Registry <Biome> registry, Consumer <Pair <Climate.ParameterPoint, ResourceKey <Biome>>> mapper) {

      (new Region5Parameters()).writeOverworldBiomeParameters(mapper);
      //      VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();
      //
      //      builder.add(new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.55F, 1F),
      //              MultiNoiseUtil.ParameterRange.of(-1F, 1F),
      //              MultiNoiseUtil.ParameterRange.of(-0.11F, 1F),
      //              MultiNoiseUtil.ParameterRange.of(-0.375F, 0.05F),
      //              MultiNoiseUtil.ParameterRange.of(0F, 0F),
      //              MultiNoiseUtil.ParameterRange.of(-1F, -0.05F),
      //              0
      //      ), HibiscusBiomes.STRATIFIED_DESERT);
      //      builder.add(new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.55F, 1F),
      //              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.3F),
      //              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
      //              MultiNoiseUtil.ParameterRange.of(-0.78F, 0.05F),
      //              MultiNoiseUtil.ParameterRange.of(0F, 0F),
      //              MultiNoiseUtil.ParameterRange.of(-1F, -0.93333334F),
      //              0
      //      ), HibiscusBiomes.STRATIFIED_DESERT);
      //      builder.add(new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.55F, 1F),
      //              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.3F),
      //              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
      //              MultiNoiseUtil.ParameterRange.of(-0.78F, 0.05F),
      //              MultiNoiseUtil.ParameterRange.of(0F, 0F),
      //              MultiNoiseUtil.ParameterRange.of(-0.4F, -0.26666668F),
      //              0
      //      ), HibiscusBiomes.STRATIFIED_DESERT);
      //      builder.add(new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.55F, 1F),
      //              MultiNoiseUtil.ParameterRange.of(-1F, 1F),
      //              MultiNoiseUtil.ParameterRange.of(-0.11F, 1F),
      //              MultiNoiseUtil.ParameterRange.of(-0.375F, 0.05F),
      //              MultiNoiseUtil.ParameterRange.of(0F, 0F),
      //              MultiNoiseUtil.ParameterRange.of(0.05F, 1F),
      //              0
      //      ), HibiscusBiomes.STRATIFIED_DESERT);
      //      builder.add(new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.55F, 1F),
      //              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.3F),
      //              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
      //              MultiNoiseUtil.ParameterRange.of(-0.78F, 0.05F),
      //              MultiNoiseUtil.ParameterRange.of(0F, 0F),
      //              MultiNoiseUtil.ParameterRange.of(0.26666668F, 0.4F),
      //              0
      //      ), HibiscusBiomes.STRATIFIED_DESERT);
      //      builder.add(new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.55F, 1F),
      //              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.3F),
      //              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
      //              MultiNoiseUtil.ParameterRange.of(-0.78F, 0.05F),
      //              MultiNoiseUtil.ParameterRange.of(0F, 0F),
      //              MultiNoiseUtil.ParameterRange.of(0.93333334F, 1F),
      //              0
      //      ), HibiscusBiomes.STRATIFIED_DESERT);
      //      builder.add(new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.55F, 1F),
      //              MultiNoiseUtil.ParameterRange.of(-1F, 1F),
      //              MultiNoiseUtil.ParameterRange.of(-0.11F, 1F),
      //              MultiNoiseUtil.ParameterRange.of(-0.375F, 0.05F),
      //              MultiNoiseUtil.ParameterRange.of(1F, 1F),
      //              MultiNoiseUtil.ParameterRange.of(-1F, -0.05F),
      //              0
      //      ), HibiscusBiomes.STRATIFIED_DESERT);
      //      builder.add(new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.55F, 1F),
      //              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.3F),
      //              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
      //              MultiNoiseUtil.ParameterRange.of(-0.78F, 0.05F),
      //              MultiNoiseUtil.ParameterRange.of(1F, 1F),
      //              MultiNoiseUtil.ParameterRange.of(-1F, -0.93333334F),
      //              0
      //      ), HibiscusBiomes.STRATIFIED_DESERT);
      //      builder.add(new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.55F, 1F),
      //              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.3F),
      //              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
      //              MultiNoiseUtil.ParameterRange.of(-0.78F, 0.05F),
      //              MultiNoiseUtil.ParameterRange.of(1F, 1F),
      //              MultiNoiseUtil.ParameterRange.of(-0.4F, -0.26666668F),
      //              0
      //      ), HibiscusBiomes.STRATIFIED_DESERT);
      //      builder.add(new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.55F, 1F),
      //              MultiNoiseUtil.ParameterRange.of(-1F, 1F),
      //              MultiNoiseUtil.ParameterRange.of(-0.11F, 1F),
      //              MultiNoiseUtil.ParameterRange.of(-0.375F, 0.05F),
      //              MultiNoiseUtil.ParameterRange.of(1F, 1F),
      //              MultiNoiseUtil.ParameterRange.of(0.05F, 1F),
      //              0
      //      ), HibiscusBiomes.STRATIFIED_DESERT);
      //      builder.add(new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.55F, 1F),
      //              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.3F),
      //              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
      //              MultiNoiseUtil.ParameterRange.of(-0.78F, 0.05F),
      //              MultiNoiseUtil.ParameterRange.of(1F, 1F),
      //              MultiNoiseUtil.ParameterRange.of(0.26666668F, 0.4F),
      //              0
      //      ), HibiscusBiomes.STRATIFIED_DESERT);
      //      builder.add(new MultiNoiseUtil.NoiseHypercube(MultiNoiseUtil.ParameterRange.of(0.55F, 1F),
      //              MultiNoiseUtil.ParameterRange.of(-0.1F, 0.3F),
      //              MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
      //              MultiNoiseUtil.ParameterRange.of(-0.78F, 0.05F),
      //              MultiNoiseUtil.ParameterRange.of(1F, 1F),
      //              MultiNoiseUtil.ParameterRange.of(0.93333334F, 1F),
      //              0
      //      ), HibiscusBiomes.STRATIFIED_DESERT);
      //
      //      builder.build().forEach(mapper);
   }

}
