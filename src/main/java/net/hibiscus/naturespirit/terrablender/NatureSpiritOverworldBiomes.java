package net.hibiscus.naturespirit.terrablender;

import net.hibiscus.naturespirit.world.gen.HibiscusTreeGeneration;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import javax.annotation.Nullable;

import static net.hibiscus.naturespirit.world.feature.HibiscusPlacedFeatures.WISTERIA_WATER;

public class NatureSpiritOverworldBiomes {
    @Nullable
    private static final Music NORMAL_MUSIC = null;

    protected static int calculateSkyColor(float color) {
        float $$1 = color / 3.0F;
        $$1 = Mth.clamp($$1, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
    }

    private static Biome biome(Biome.Precipitation precipitation, float temperature, float downfall, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder, @Nullable Music music) {
        return biome(precipitation, temperature, downfall, 4159204, 329011, spawnBuilder, biomeBuilder, music);
    }

    private static Biome biome(Biome.Precipitation precipitation, float temperature, float downfall, int waterColor, int waterFogColor, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder, @Nullable Music music) {
        return (new Biome.BiomeBuilder()).precipitation(precipitation).temperature(temperature).downfall(downfall).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(waterColor).waterFogColor(waterFogColor).fogColor(12638463).skyColor(calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music).build()).mobSpawnSettings(spawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
    }

    private static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    public static Biome wisteriaForest(HolderGetter <PlacedFeature> holderGetter, HolderGetter <ConfiguredWorldCarver <?>> holderGetter2) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
//        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.COW, 5, 2, 5));
//        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.SHEEP, 5, 2, 5));
//        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.CHICKEN, 5, 2, 5));
//        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PIG, 5, 2, 5));
//        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 5, 2, 5));

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(holderGetter, holderGetter2).addFeature(GenerationStep.Decoration.FLUID_SPRINGS, WISTERIA_WATER);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addPlainGrass(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        HibiscusTreeGeneration.addWisteriaTrees(biomeBuilder);
        HibiscusTreeGeneration.addWisteriaFlowers(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_WATERLILY);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_WARM);
        return biome(Biome.Precipitation.RAIN, 0.4F, 0.7F, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome sakuraGrove(HolderGetter <PlacedFeature> holderGetter, HolderGetter <ConfiguredWorldCarver <?>> holderGetter2) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(holderGetter, holderGetter2);
        globalOverworldGeneration(biomeBuilder);
        HibiscusTreeGeneration.addSakuraVegetation(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addLightBambooVegetation(biomeBuilder);
        BiomeDefaultFeatures.addPlainGrass(biomeBuilder);
        HibiscusTreeGeneration.addSakuraTrees(biomeBuilder);
        HibiscusTreeGeneration.addOakTrees(biomeBuilder);
        HibiscusTreeGeneration.addSakuraSecondaryVegetation(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);
        return biome(Biome.Precipitation.RAIN, 0.95F, 0.6F, spawnBuilder, biomeBuilder, Musics.createGameMusic(SoundEvents.MUSIC_BIOME_JUNGLE_AND_FOREST));
    }

    public static Biome bambooSakura(HolderGetter <PlacedFeature> holderGetter, HolderGetter <ConfiguredWorldCarver <?>> holderGetter2) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(holderGetter, holderGetter2);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PANDA, 1, 3, 2));
        globalOverworldGeneration(biomeBuilder);
        HibiscusTreeGeneration.addSakuraVegetation(biomeBuilder);
        BiomeDefaultFeatures.addFrozenSprings(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addPlainGrass(biomeBuilder);
        HibiscusTreeGeneration.addSakuraTrees(biomeBuilder);
        HibiscusTreeGeneration.addBamboo(biomeBuilder);
        HibiscusTreeGeneration.addSakuraSecondaryVegetation(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);
        return biome(Biome.Precipitation.RAIN, 0.95F, 0.6F, spawnBuilder, biomeBuilder, Musics.createGameMusic(SoundEvents.MUSIC_BIOME_JUNGLE_AND_FOREST));
    }

    public static Biome redwoodForest(HolderGetter <PlacedFeature> holderGetter, HolderGetter <ConfiguredWorldCarver <?>> holderGetter2) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(holderGetter, holderGetter2);
        globalOverworldGeneration(biomeBuilder);
        HibiscusTreeGeneration.addRedwoodRock(biomeBuilder);
        BiomeDefaultFeatures.addFrozenSprings(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addPlainGrass(biomeBuilder);
        HibiscusTreeGeneration.addLargeRedwoodTrees(biomeBuilder);
        HibiscusTreeGeneration.addRedwoodTrees(biomeBuilder);
        HibiscusTreeGeneration.addSpruceBush(biomeBuilder);
        HibiscusTreeGeneration.addRedwoodSecondaryVegetation(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);
        return biome(Biome.Precipitation.RAIN, 0.4F, 0.6F, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }
}
