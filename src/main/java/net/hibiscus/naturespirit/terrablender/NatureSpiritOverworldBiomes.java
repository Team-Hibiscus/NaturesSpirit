package net.hibiscus.naturespirit.terrablender;

import net.hibiscus.naturespirit.world.gen.HibiscusTreeGeneration;
import net.minecraft.client.sound.MusicType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.OceanPlacedFeatures;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

import javax.annotation.Nullable;

import static net.hibiscus.naturespirit.world.feature.HibiscusPlacedFeatures.*;

public class NatureSpiritOverworldBiomes {
    @Nullable
    private static final MusicSound NORMAL_MUSIC = null;

    protected static int calculateSkyColor(float color) {
        float $$1 = color / 3.0F;
        $$1 = MathHelper.clamp($$1, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
    }

    private static Biome biome(Biome.Precipitation precipitation, float temperature, float downfall, SpawnSettings.Builder spawnBuilder, GenerationSettings.LookupBackedBuilder biomeBuilder, @Nullable MusicSound music) {
        return biome(precipitation, temperature, downfall, 4159204, 329011, spawnBuilder, biomeBuilder, music);
    }

    private static Biome biome(Biome.Precipitation precipitation, float temperature, float downfall, int waterColor, int waterFogColor, SpawnSettings.Builder spawnBuilder, GenerationSettings.LookupBackedBuilder biomeBuilder, @Nullable MusicSound music) {
        return (new Biome.Builder()).precipitation(precipitation).temperature(temperature).downfall(downfall).effects((new BiomeEffects.Builder()).waterColor(waterColor).waterFogColor(waterFogColor).fogColor(12638463).skyColor(calculateSkyColor(temperature)).moodSound(BiomeMoodSound.CAVE).music(music).build()).spawnSettings(spawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
    }

    private static Biome biomeWithGrassColor(Biome.Precipitation precipitation, float temperature, float downfall, int grassColor,SpawnSettings.Builder spawnBuilder, GenerationSettings.LookupBackedBuilder biomeBuilder, @Nullable MusicSound music) {
        return biomeWithGrassColor(precipitation, temperature, downfall, 4159204, 329011, grassColor,spawnBuilder, biomeBuilder, music);
    }

    private static Biome biomeWithGrassColor(Biome.Precipitation precipitation, float temperature, float downfall, int waterColor, int waterFogColor, int grassColor, SpawnSettings.Builder spawnBuilder, GenerationSettings.LookupBackedBuilder biomeBuilder, @Nullable MusicSound music) {
        return (new Biome.Builder()).precipitation(precipitation).temperature(temperature).downfall(downfall).effects((new BiomeEffects.Builder()).waterColor(waterColor).waterFogColor(waterFogColor).grassColor(grassColor).fogColor(12638463).skyColor(calculateSkyColor(temperature)).moodSound(BiomeMoodSound.CAVE).music(music).build()).spawnSettings(spawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
    }

    private static void globalOverworldGeneration(GenerationSettings.LookupBackedBuilder builder) {
        DefaultBiomeFeatures.addLandCarvers(builder);
        DefaultBiomeFeatures.addAmethystGeodes(builder);
        DefaultBiomeFeatures.addDungeons(builder);
        DefaultBiomeFeatures.addMineables(builder);
        DefaultBiomeFeatures.addSprings(builder);
        DefaultBiomeFeatures.addFrozenTopLayer(builder);
    }

    public static Biome erodedRiver(RegistryEntryLookup<PlacedFeature> featureLookup, RegistryEntryLookup<ConfiguredCarver<?>> carverLookup) {
        SpawnSettings.Builder builder = (new SpawnSettings.Builder()).spawn(SpawnGroup.WATER_CREATURE, new SpawnSettings.SpawnEntry(EntityType.SQUID, 2, 1, 4)).spawn(SpawnGroup.WATER_AMBIENT, new SpawnSettings.SpawnEntry(EntityType.SALMON, 5, 1, 5));
        DefaultBiomeFeatures.addBatsAndMonsters(builder);
        builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.DROWNED, 100, 1, 1));
        GenerationSettings.LookupBackedBuilder lookupBackedBuilder = new GenerationSettings.LookupBackedBuilder(featureLookup, carverLookup).feature(GenerationStep.Feature.FLUID_SPRINGS, RIVER_WATER);
        globalOverworldGeneration(lookupBackedBuilder);
        DefaultBiomeFeatures.addDefaultOres(lookupBackedBuilder);
        DefaultBiomeFeatures.addPlainsTallGrass(lookupBackedBuilder);
        DefaultBiomeFeatures.addDefaultDisks(lookupBackedBuilder);
        HibiscusTreeGeneration.addErodedRiverFlowers(lookupBackedBuilder);
        DefaultBiomeFeatures.addDefaultVegetation(lookupBackedBuilder);

        return biome(Biome.Precipitation.RAIN, 0.67F, 0.7F, 4159204, 329011, builder, lookupBackedBuilder, NORMAL_MUSIC);
    }

    public static Biome wisteriaForest(RegistryEntryLookup <PlacedFeature> holderGetter, RegistryEntryLookup <ConfiguredCarver <?>> holderGetter2) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addFarmAnimals(spawnBuilder);
        DefaultBiomeFeatures.addBatsAndMonsters(spawnBuilder);
//        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.COW, 5, 2, 5));
//        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.SHEEP, 5, 2, 5));
//        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.CHICKEN, 5, 2, 5));
//        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PIG, 5, 2, 5));
//        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 5, 2, 5));

        GenerationSettings.LookupBackedBuilder biomeBuilder = new GenerationSettings.LookupBackedBuilder(holderGetter, holderGetter2).feature(GenerationStep.Feature.FLUID_SPRINGS, WISTERIA_WATER);
        globalOverworldGeneration(biomeBuilder);
        DefaultBiomeFeatures.addDefaultOres(biomeBuilder);
        DefaultBiomeFeatures.addPlainsTallGrass(biomeBuilder);
        DefaultBiomeFeatures.addDefaultDisks(biomeBuilder);
        HibiscusTreeGeneration.addWisteriaTrees(biomeBuilder);
        HibiscusTreeGeneration.addWisteriaFlowers(biomeBuilder);
        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_WATERLILY);
        DefaultBiomeFeatures.addDefaultVegetation(biomeBuilder);
        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, OceanPlacedFeatures.SEAGRASS_WARM);
        return biome(Biome.Precipitation.RAIN, 0.4F, 0.7F, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome sugiForest(RegistryEntryLookup <PlacedFeature> holderGetter, RegistryEntryLookup <ConfiguredCarver <?>> holderGetter2) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addFarmAnimals(spawnBuilder);
        DefaultBiomeFeatures.addBatsAndMonsters(spawnBuilder);

        GenerationSettings.LookupBackedBuilder biomeBuilder = new GenerationSettings.LookupBackedBuilder(holderGetter, holderGetter2);
        globalOverworldGeneration(biomeBuilder);
        HibiscusTreeGeneration.addSugiVegetation(biomeBuilder);
        DefaultBiomeFeatures.addDefaultOres(biomeBuilder);
        DefaultBiomeFeatures.addDefaultDisks(biomeBuilder);
        DefaultBiomeFeatures.addPlainsTallGrass(biomeBuilder);
        HibiscusTreeGeneration.addSugiTrees(biomeBuilder);
        HibiscusTreeGeneration.addSugiSecondaryVegetation(biomeBuilder);
        DefaultBiomeFeatures.addDefaultVegetation(biomeBuilder);
        return biome(Biome.Precipitation.RAIN, 0.95F, 0.6F, spawnBuilder, biomeBuilder, MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_JUNGLE_AND_FOREST));
    }

    public static Biome goldenWilds(RegistryEntryLookup <PlacedFeature> holderGetter, RegistryEntryLookup <ConfiguredCarver <?>> holderGetter2) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addFarmAnimals(spawnBuilder);
        DefaultBiomeFeatures.addBatsAndMonsters(spawnBuilder);

        GenerationSettings.LookupBackedBuilder biomeBuilder = new GenerationSettings.LookupBackedBuilder(holderGetter, holderGetter2);
        globalOverworldGeneration(biomeBuilder);
        HibiscusTreeGeneration.addSugiVegetation(biomeBuilder);
        DefaultBiomeFeatures.addDefaultOres(biomeBuilder);
        DefaultBiomeFeatures.addDefaultDisks(biomeBuilder);
        DefaultBiomeFeatures.addPlainsTallGrass(biomeBuilder);
        HibiscusTreeGeneration.addFirTrees(biomeBuilder);
        HibiscusTreeGeneration.addAspenTrees(biomeBuilder);
        HibiscusTreeGeneration.addFewOakTrees(biomeBuilder);
        HibiscusTreeGeneration.addGoldenWildsVegetation(biomeBuilder);
        DefaultBiomeFeatures.addDefaultVegetation(biomeBuilder);
        return biomeWithGrassColor(Biome.Precipitation.RAIN, 0.45F, 0.3F, 14733927,spawnBuilder,biomeBuilder, MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_JUNGLE_AND_FOREST));
    }
    public static Biome goldenFields(RegistryEntryLookup <PlacedFeature> holderGetter, RegistryEntryLookup <ConfiguredCarver <?>> holderGetter2) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addFarmAnimals(spawnBuilder);
        DefaultBiomeFeatures.addBatsAndMonsters(spawnBuilder);

        GenerationSettings.LookupBackedBuilder biomeBuilder = new GenerationSettings.LookupBackedBuilder(holderGetter, holderGetter2);
        globalOverworldGeneration(biomeBuilder);
        HibiscusTreeGeneration.addSugiVegetation(biomeBuilder);
        DefaultBiomeFeatures.addDefaultOres(biomeBuilder);
        DefaultBiomeFeatures.addDefaultDisks(biomeBuilder);
        DefaultBiomeFeatures.addPlainsTallGrass(biomeBuilder);
        HibiscusTreeGeneration.adFewAspenTrees(biomeBuilder);
        HibiscusTreeGeneration.addFewOakTrees(biomeBuilder);
        HibiscusTreeGeneration.addGoldenFieldsVegetation(biomeBuilder);
        DefaultBiomeFeatures.addDefaultVegetation(biomeBuilder);
        return biomeWithGrassColor(Biome.Precipitation.RAIN, 0.45F, 0.3F, 14733927,spawnBuilder,biomeBuilder, MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_JUNGLE_AND_FOREST));
    }
    public static Biome firForest(RegistryEntryLookup <PlacedFeature> holderGetter, RegistryEntryLookup <ConfiguredCarver <?>> holderGetter2) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addFarmAnimals(spawnBuilder);
        DefaultBiomeFeatures.addBatsAndMonsters(spawnBuilder);

        GenerationSettings.LookupBackedBuilder biomeBuilder = new GenerationSettings.LookupBackedBuilder(holderGetter, holderGetter2);
        globalOverworldGeneration(biomeBuilder);
        DefaultBiomeFeatures.addDefaultOres(biomeBuilder);
        DefaultBiomeFeatures.addDefaultDisks(biomeBuilder);
        DefaultBiomeFeatures.addPlainsTallGrass(biomeBuilder);
        HibiscusTreeGeneration.addDenseFirTrees(biomeBuilder);
        HibiscusTreeGeneration.addFirVegetation(biomeBuilder);
        DefaultBiomeFeatures.addDefaultVegetation(biomeBuilder);
        return biomeWithGrassColor(Biome.Precipitation.RAIN, 0.45F, 0.3F, 11977352,spawnBuilder,biomeBuilder, MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_JUNGLE_AND_FOREST));
    }


    public static Biome lavenderFields(RegistryEntryLookup <PlacedFeature> holderGetter, RegistryEntryLookup <ConfiguredCarver <?>> holderGetter2) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addFarmAnimals(spawnBuilder);
        DefaultBiomeFeatures.addBatsAndMonsters(spawnBuilder);

        GenerationSettings.LookupBackedBuilder biomeBuilder = new GenerationSettings.LookupBackedBuilder(holderGetter, holderGetter2).feature(GenerationStep.Feature.FLUID_SPRINGS, LAVENDER_WATER);
        globalOverworldGeneration(biomeBuilder);
        HibiscusTreeGeneration.addSugiVegetation(biomeBuilder);
        DefaultBiomeFeatures.addDefaultOres(biomeBuilder);
        DefaultBiomeFeatures.addDefaultDisks(biomeBuilder);
        DefaultBiomeFeatures.addPlainsTallGrass(biomeBuilder);
        HibiscusTreeGeneration.addFewOakTrees(biomeBuilder);
        HibiscusTreeGeneration.addLavenderFlowers(biomeBuilder);
        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_WATERLILY);
        DefaultBiomeFeatures.addDefaultVegetation(biomeBuilder);
        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, OceanPlacedFeatures.SEAGRASS_WARM);
        return biome(Biome.Precipitation.RAIN, 0.95F, 0.6F, spawnBuilder, biomeBuilder, MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_JUNGLE_AND_FOREST));
    }

    public static Biome redwoodForest(RegistryEntryLookup <PlacedFeature> holderGetter, RegistryEntryLookup <ConfiguredCarver <?>> holderGetter2) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addFarmAnimals(spawnBuilder);
        DefaultBiomeFeatures.addBatsAndMonsters(spawnBuilder);

        GenerationSettings.LookupBackedBuilder biomeBuilder = new GenerationSettings.LookupBackedBuilder(holderGetter, holderGetter2);
        globalOverworldGeneration(biomeBuilder);
        HibiscusTreeGeneration.addRedwoodRock(biomeBuilder);
        DefaultBiomeFeatures.addFrozenLavaSpring(biomeBuilder);
        DefaultBiomeFeatures.addDefaultOres(biomeBuilder);
        DefaultBiomeFeatures.addDefaultDisks(biomeBuilder);
        DefaultBiomeFeatures.addPlainsTallGrass(biomeBuilder);
        HibiscusTreeGeneration.addLargeRedwoodTrees(biomeBuilder);
        HibiscusTreeGeneration.addRedwoodTrees(biomeBuilder);
        HibiscusTreeGeneration.addSpruceBush(biomeBuilder);
        HibiscusTreeGeneration.addRedwoodSecondaryVegetation(biomeBuilder);
        DefaultBiomeFeatures.addDefaultVegetation(biomeBuilder);
        return biomeWithGrassColor(Biome.Precipitation.RAIN, 0.4F, 0.6F, 11451757, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }
}
