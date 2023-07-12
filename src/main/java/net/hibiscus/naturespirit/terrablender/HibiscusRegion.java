package net.hibiscus.naturespirit.terrablender;import com.mojang.datafixers.util.Pair;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.core.TerraBlender;import java.util.function.Consumer;public class HibiscusRegion extends Region {
    public HibiscusRegion(Identifier name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }    @Override
    public void addBiomes(Registry <Biome> registry, Consumer <Pair <MultiNoiseUtil.NoiseHypercube, RegistryKey <Biome>>> mapper) {        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.3F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(-0.11F, 0.03F),
                        MultiNoiseUtil.ParameterRange.of(-1F, -0.78F),
                        MultiNoiseUtil.ParameterRange.of(0F, 0F),
                        MultiNoiseUtil.ParameterRange.of(-1F, -0.7666667F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );
        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.3F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-1F, -0.78F),
                        MultiNoiseUtil.ParameterRange.of(0F, 0F),
                        MultiNoiseUtil.ParameterRange.of(-1F, -0.93333334F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );
        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.3F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-0.78F, -0.2225F),
                        MultiNoiseUtil.ParameterRange.of(0F, 0F),
                        MultiNoiseUtil.ParameterRange.of(-1F, -0.7666667F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );
        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.3F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-0.78F, -0.2225F),
                        MultiNoiseUtil.ParameterRange.of(0F, 0F),
                        MultiNoiseUtil.ParameterRange.of(-0.93333334F, -0.7666667F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.3F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-0.2225F, 0.05F),
                        MultiNoiseUtil.ParameterRange.of(0F, 0F),
                        MultiNoiseUtil.ParameterRange.of(-0.93333334F, -0.4F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );
        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.3F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-0.375F, -0.2225F),
                        MultiNoiseUtil.ParameterRange.of(0F, 0F),
                        MultiNoiseUtil.ParameterRange.of(-0.7666667F, -0.4F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.3F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(-0.11F, 0.03F),
                        MultiNoiseUtil.ParameterRange.of(-1F, -0.78F),
                        MultiNoiseUtil.ParameterRange.of(0F, 0F),
                        MultiNoiseUtil.ParameterRange.of(-0.56666666F, -0.26666668F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );
        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.3F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-0.78F, -0.2225F),
                        MultiNoiseUtil.ParameterRange.of(0F, 0F),
                        MultiNoiseUtil.ParameterRange.of(-0.56666666F, -0.4F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.3F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-1F, -0.78F),
                        MultiNoiseUtil.ParameterRange.of(0F, 0F),
                        MultiNoiseUtil.ParameterRange.of(-0.4F, -0.26666668F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );
        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.3F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-0.78F, -0.2225F),
                        MultiNoiseUtil.ParameterRange.of(0F, 0F),
                        MultiNoiseUtil.ParameterRange.of(-0.4F, -0.26666668F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );
        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.1F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(-0.11F, 0.03F),
                        MultiNoiseUtil.ParameterRange.of(-1F, -0.78F),
                        MultiNoiseUtil.ParameterRange.of(0F, 0F),
                        MultiNoiseUtil.ParameterRange.of(0.26666668F, 0.56666666F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.1F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-1F, -0.78F),
                        MultiNoiseUtil.ParameterRange.of(0F, 0F),
                        MultiNoiseUtil.ParameterRange.of(0.26666668F, 0.4F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.1F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-0.78F, -0.2225F),
                        MultiNoiseUtil.ParameterRange.of(0F, 0F),
                        MultiNoiseUtil.ParameterRange.of(0.26666668F, 0.56666666F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.1F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-0.78F, -0.2225F),
                        MultiNoiseUtil.ParameterRange.of(0F, 0F),
                        MultiNoiseUtil.ParameterRange.of(0.4F, 0.56666666F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );
        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.1F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-0.2225F, 0.05F),
                        MultiNoiseUtil.ParameterRange.of(0F, 0F),
                        MultiNoiseUtil.ParameterRange.of(0.4F, 0.93333334F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );
        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.1F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-0.375F, -0.2225F),
                        MultiNoiseUtil.ParameterRange.of(0F, 0F),
                        MultiNoiseUtil.ParameterRange.of(0.56666666F, 0.93333334F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.1F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(-0.11F, 0.03F),
                        MultiNoiseUtil.ParameterRange.of(-1F, -0.78F),
                        MultiNoiseUtil.ParameterRange.of(0F, 0F),
                        MultiNoiseUtil.ParameterRange.of(0.7666667F, 1F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );
        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.1F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-0.78F, -0.2225F),
                        MultiNoiseUtil.ParameterRange.of(0F, 0F),
                        MultiNoiseUtil.ParameterRange.of(0.7666667F, 0.93333334F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );
        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.1F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-1F, -0.78F),
                        MultiNoiseUtil.ParameterRange.of(0F, 0F),
                        MultiNoiseUtil.ParameterRange.of(0.93333334F, 1F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.1F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-0.78F, -0.2225F),
                        MultiNoiseUtil.ParameterRange.of(0F, 0F),
                        MultiNoiseUtil.ParameterRange.of(0.93333334F, 1F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );
        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.3F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(-0.11F, 0.03F),
                        MultiNoiseUtil.ParameterRange.of(-1F, -0.78F),
                        MultiNoiseUtil.ParameterRange.of(1F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-1F, -0.7666667F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );
        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.3F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-1F, -0.78F),
                        MultiNoiseUtil.ParameterRange.of(1F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-1F, -0.93333334F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );
        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.3F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-0.78F, -0.2225F),
                        MultiNoiseUtil.ParameterRange.of(1F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-1F, -0.7666667F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );
        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.3F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-0.78F, -0.2225F),
                        MultiNoiseUtil.ParameterRange.of(1F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-0.93333334F, -0.7666667F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.3F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-0.2225F, 0.05F),
                        MultiNoiseUtil.ParameterRange.of(1F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-0.93333334F, -0.4F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );
        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.3F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-0.375F, -0.2225F),
                        MultiNoiseUtil.ParameterRange.of(1F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-0.7666667F, -0.4F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.3F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(-0.11F, 0.03F),
                        MultiNoiseUtil.ParameterRange.of(-1F, -0.78F),
                        MultiNoiseUtil.ParameterRange.of(1F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-0.56666666F, -0.26666668F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );
        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.3F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-0.78F, -0.2225F),
                        MultiNoiseUtil.ParameterRange.of(1F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-0.56666666F, -0.4F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.3F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-1F, -0.78F),
                        MultiNoiseUtil.ParameterRange.of(1F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-0.4F, -0.26666668F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );
        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.3F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-0.78F, -0.2225F),
                        MultiNoiseUtil.ParameterRange.of(1F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-0.4F, -0.26666668F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );
        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.1F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(-0.11F, 0.03F),
                        MultiNoiseUtil.ParameterRange.of(-1F, -0.78F),
                        MultiNoiseUtil.ParameterRange.of(1F, 1F),
                        MultiNoiseUtil.ParameterRange.of(0.26666668F, 0.56666666F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.1F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-1F, -0.78F),
                        MultiNoiseUtil.ParameterRange.of(1F, 1F),
                        MultiNoiseUtil.ParameterRange.of(0.26666668F, 0.4F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.1F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-0.78F, -0.2225F),
                        MultiNoiseUtil.ParameterRange.of(1F, 1F),
                        MultiNoiseUtil.ParameterRange.of(0.26666668F, 0.56666666F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.1F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-0.78F, -0.2225F),
                        MultiNoiseUtil.ParameterRange.of(1F, 1F),
                        MultiNoiseUtil.ParameterRange.of(0.4F, 0.56666666F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );
        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.1F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-0.2225F, 0.05F),
                        MultiNoiseUtil.ParameterRange.of(1F, 1F),
                        MultiNoiseUtil.ParameterRange.of(0.4F, 0.93333334F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );
        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.1F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-0.375F, -0.2225F),
                        MultiNoiseUtil.ParameterRange.of(1F, 1F),
                        MultiNoiseUtil.ParameterRange.of(0.56666666F, 0.93333334F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.1F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(-0.11F, 0.03F),
                        MultiNoiseUtil.ParameterRange.of(-1F, -0.78F),
                        MultiNoiseUtil.ParameterRange.of(1F, 1F),
                        MultiNoiseUtil.ParameterRange.of(0.7666667F, 1F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );
        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.1F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-0.78F, -0.2225F),
                        MultiNoiseUtil.ParameterRange.of(1F, 1F),
                        MultiNoiseUtil.ParameterRange.of(0.7666667F, 0.93333334F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );
        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.1F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(0.03F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-1F, -0.78F),
                        MultiNoiseUtil.ParameterRange.of(1F, 1F),
                        MultiNoiseUtil.ParameterRange.of(0.93333334F, 1F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );        this.addBiome(mapper, new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.1F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(0.3F, 1F),
                        MultiNoiseUtil.ParameterRange.of(-0.78F, -0.2225F),
                        MultiNoiseUtil.ParameterRange.of(1F, 1F),
                        MultiNoiseUtil.ParameterRange.of(0.93333334F, 1F),
                        0
                ),
                HibiscusBiomes.SUGI_FOREST
        );        
    }}
