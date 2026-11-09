package net.hibiscus.naturespirit.world;

import dev.worldgen.lithostitched.api.registry.LithostitchedBuiltInRegistries;
import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries;
import dev.worldgen.lithostitched.impl.worldgen.bandlands.Bandlands;
import dev.worldgen.lithostitched.impl.worldgen.bandlands.BandlandsBuilderImpl;
import dev.worldgen.lithostitched.impl.worldgen.surface.rule.BandlandsRule;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.config.NSConfig;
import net.hibiscus.naturespirit.registration.NSBiomes;
import net.hibiscus.naturespirit.registration.NSBlocks;
import net.hibiscus.naturespirit.registration.NSWorldGen;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static net.minecraft.world.level.levelgen.SurfaceRules.*;

public class NSSurfaceRules {

    private static final RuleSource GRAVEL = makeStateRule(Blocks.GRAVEL);
    private static final RuleSource STONE = makeStateRule(Blocks.STONE);
    private static final RuleSource GRASS = makeStateRule(Blocks.GRASS_BLOCK);
    private static final RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final RuleSource ROOTED_DIRT = makeStateRule(Blocks.ROOTED_DIRT);
    private static final RuleSource GRANITE = makeStateRule(Blocks.GRANITE);
    private static final RuleSource PODZOL = makeStateRule(Blocks.PODZOL);
    private static final RuleSource CALCITE = makeStateRule(Blocks.CALCITE);
    private static final RuleSource SANDY_SOIL = makeStateRule(NSBlocks.SANDY_SOIL.get());
    private static final RuleSource RED_MOSS_BLOCK = makeStateRule(NSBlocks.RED_MOSS_BLOCK.get());
    private static final RuleSource COARSE_DIRT = makeStateRule(Blocks.COARSE_DIRT);
    private static final RuleSource WHITE_KAOLIN = makeStateRule(NSBlocks.WHITE_KAOLIN.get());
    private static final RuleSource PINK_SANDSTONE = makeStateRule(NSBlocks.PINK_SANDSTONE.get());
    private static final RuleSource SANDSTONE = makeStateRule(Blocks.SANDSTONE);
    private static final RuleSource RED_SANDSTONE = makeStateRule(Blocks.RED_SANDSTONE);
    private static final RuleSource RED_SAND = makeStateRule(Blocks.RED_SAND);
    private static final RuleSource SAND = makeStateRule(Blocks.SAND);
    private static final RuleSource TRAVERTINE = makeStateRule(NSBlocks.TRAVERTINE.getBase().get());
    private static final RuleSource WHITE_CHALK = makeStateRule(NSBlocks.WHITE_CHALK.get());
    private static final RuleSource PINK_SAND = makeStateRule(NSBlocks.PINK_SAND.get());
    private static final RuleSource CHERT = makeStateRule(NSBlocks.CHERT.getBase().get());
    private static final RuleSource SNOW_BLOCK = makeStateRule(Blocks.SNOW_BLOCK);
    private static final RuleSource WATER = makeStateRule(Blocks.WATER);
    public static RuleSource makeRules() {
        ConditionSource y25 = yBlockCheck(VerticalAnchor.absolute(25), -1);
        ConditionSource y45 = yBlockCheck(VerticalAnchor.absolute(45), -1);
        ConditionSource y60Chalk = yBlockCheck(VerticalAnchor.absolute(60), -1);
        ConditionSource y60 = yBlockCheck(VerticalAnchor.absolute(60), 0);
        ConditionSource y63 = yBlockCheck(VerticalAnchor.absolute(63), 0);
        ConditionSource y65 = yBlockCheck(VerticalAnchor.absolute(65), 0);
        ConditionSource y70 = yStartCheck(VerticalAnchor.absolute(70), 1);
        ConditionSource y76 = yStartCheck(VerticalAnchor.absolute(76), 1);
        ConditionSource y256 = yBlockCheck(VerticalAnchor.absolute(256), 0);
        ConditionSource y256Offset = yBlockCheck(VerticalAnchor.absolute(256), 1);

        ConditionSource depth1 = stoneDepthCheck(1, false, CaveSurface.FLOOR);
        ConditionSource depth1Surf = stoneDepthCheck(1, true, CaveSurface.FLOOR);
        ConditionSource depth4 = stoneDepthCheck(4, false, CaveSurface.FLOOR);
        ConditionSource depth36 = stoneDepthCheck(36, false, CaveSurface.FLOOR);
        ConditionSource depth8Surf = stoneDepthCheck(8, true, CaveSurface.FLOOR);
        ConditionSource depth30Surf = stoneDepthCheck(30, true, CaveSurface.FLOOR);
        ConditionSource shallow = stoneDepthCheck(3, true, CaveSurface.FLOOR);
        ConditionSource nearSurface = stoneDepthCheck(40, true, CaveSurface.FLOOR);

        ConditionSource water5 = waterBlockCheck(-1, 0);
        ConditionSource water7 = waterBlockCheck(0, 0);
        ConditionSource holeCondition = hole();
        ConditionSource belowWater = waterStartCheck(-6, -1);

        ConditionSource noise1 = noiseCondition(Noises.SURFACE, -0.909D, -0.5454D);
        ConditionSource noise2 = noiseCondition(Noises.SURFACE, -0.5454D, -0.3818D);
        ConditionSource noise3 = noiseCondition(Noises.SURFACE, 0.5454D, 0.909D);
        ConditionSource noise4 = noiseCondition(Noises.SURFACE, -0.5454D, 0.0454D);
        ConditionSource noise5 = noiseCondition(Noises.SURFACE, 0.2454D, 6D);

        ResourceKey<Biome>[] stratified = new ResourceKey[] {NSBiomes.STRATIFIED_DESERT};
        ResourceKey<Biome>[] whiteCliffs = new ResourceKey[] {NSBiomes.WHITE_CLIFFS};
        ResourceKey<Biome>[] wetland = new ResourceKey[] {NSBiomes.MARSH, NSBiomes.TROPICAL_BASIN, NSBiomes.BAMBOO_WETLANDS};
        ResourceKey<Biome>[] aspen = new ResourceKey[] {NSBiomes.ASPEN_FOREST};
        ResourceKey<Biome>[] steppeDunesRiver = new ResourceKey[] {NSBiomes.ARID_HIGHLANDS, NSBiomes.SHRUBBY_HIGHLANDS, NSBiomes.WOODY_HIGHLANDS, NSBiomes.BLOOMING_HIGHLANDS, NSBiomes.ARID_RIVER, NSBiomes.DUNES_RIVER};
        ResourceKey<Biome>[] redwoodMaple = new ResourceKey[] {NSBiomes.REDWOOD_FOREST, NSBiomes.MAPLE_WOODLANDS};
        ResourceKey<Biome>[] alpineSugiGolden = new ResourceKey[] {NSBiomes.ALPINE_CLEARINGS, NSBiomes.ALPINE_HIGHLANDS, NSBiomes.CONIFEROUS_COVERT, NSBiomes.HEATHER_FIELDS, NSBiomes.GOLDEN_WILDS, NSBiomes.SUGI_FOREST};
        ResourceKey<Biome>[] chaparralAndDunes = new ResourceKey[] {NSBiomes.CHAPARRAL, NSBiomes.LIVELY_DUNES, NSBiomes.BLOOMING_DUNES};
        ResourceKey<Biome>[] xericPlains = new ResourceKey[] {NSBiomes.XERIC_PLAINS, NSBiomes.CEDAR_THICKET, NSBiomes.XERIC_RIVER, NSBiomes.ARID_SAVANNA, NSBiomes.WOODED_DRYLANDS};
        ResourceKey<Biome>[] tundra = new ResourceKey[] {NSBiomes.TUNDRA, NSBiomes.TUNDRA_RIVER, NSBiomes.BOREAL_TAIGA, NSBiomes.SNOWY_FIR_FOREST};
        ResourceKey<Biome>[] redSands = new ResourceKey[] {NSBiomes.SCORCHED_DUNES, NSBiomes.RED_SANDY_RIVER, NSBiomes.RED_BEACH};
        ResourceKey<Biome>[] pinkSands = new ResourceKey[] {NSBiomes.TROPICAL_SHORES, NSBiomes.DRYLANDS, NSBiomes.PINK_SANDY_RIVER};
        ResourceKey<Biome>[] yellowSands = new ResourceKey[] {NSBiomes.SANDY_RIVER, NSBiomes.LUKEWARM_BEACH, NSBiomes.COLD_BEACH};
        ResourceKey<Biome>[] peaks = new ResourceKey[] {NSBiomes.SLEETED_SLOPES, NSBiomes.SNOWCAPPED_RED_PEAKS, NSBiomes.DUSTY_SLOPES, NSBiomes.RED_PEAKS};
        ResourceKey<Biome>[] arid = join(chaparralAndDunes, xericPlains, tundra, redSands, pinkSands, yellowSands, peaks);

        RuleSource stoneOrGravel = sequence(ifTrue(ON_CEILING, STONE), GRAVEL);
        RuleSource pinkSandstoneOrPinkSand = sequence(ifTrue(ON_CEILING, PINK_SANDSTONE), PINK_SAND);
        RuleSource sandstoneOrSand = sequence(ifTrue(ON_CEILING, SANDSTONE), SAND);
        RuleSource redSandstoneOrRedSand = sequence(ifTrue(ON_CEILING, RED_SANDSTONE), RED_SAND);
        RuleSource pinkSandstoneOrSoil = sequence(ifTrue(ON_CEILING, PINK_SANDSTONE), SANDY_SOIL);
        RuleSource travertineOrSoil = sequence(ifTrue(ON_CEILING, TRAVERTINE), SANDY_SOIL);
        RuleSource chertOrSoil = sequence(ifTrue(ON_CEILING, CHERT), SANDY_SOIL);
        RuleSource stoneOrMoss = sequence(ifTrue(ON_CEILING, STONE), RED_MOSS_BLOCK);
        RuleSource stoneOrSnow = sequence(ifTrue(ON_CEILING, STONE), SNOW_BLOCK);
        RuleSource stratifiedBandlandsRule = bandlands();
        RuleSource steppeBandlandsRule = bandlands();

        RuleSource stratifiedBody = ifTrue(isBiome(stratified), sequence(
                ifTrue(y256, CHERT),
                ifTrue(y70, sequence(
                        ifTrue(not(y76), sequence(
                                ifTrue(noise1, GRASS),
                                ifTrue(noise2, COARSE_DIRT))),
                        ifTrue(noise3, SANDY_SOIL), stratifiedBandlandsRule
                        )),
                ifTrue(water5, sequence(
                        ifTrue(ON_CEILING, CHERT),
                        SANDY_SOIL)),
                ifTrue(not(holeCondition), CHERT),
                ifTrue(belowWater, WHITE_KAOLIN),
                stoneOrGravel));
        RuleSource tropicalBody = ifTrue(y60, ifTrue(not(y63), ifTrue(isBiome(wetland),
                ifTrue(noiseCondition(Noises.SWAMP, 0.0), WATER))));
        RuleSource floorRules = ifTrue(ON_FLOOR, sequence(stratifiedBody, tropicalBody));

        RuleSource whiteCliffsRule = ifTrue(depth1, ifTrue(y65, ifTrue(isBiome(whiteCliffs), sequence(
                ifTrue(ON_FLOOR, GRASS),
                DIRT))));

        RuleSource steppeRule = ifTrue(isBiome(steppeDunesRiver), sequence(
                ifTrue(ON_FLOOR, sequence(
                        ifTrue(y256, CHERT),
                        ifTrue(y70, sequence(
                                ifTrue(noise3, sequence(
                                        ifTrue(isBiome(NSBiomes.SHRUBBY_HIGHLANDS, NSBiomes.ARID_HIGHLANDS), SANDY_SOIL),
                                        GRASS)),
                                steppeBandlandsRule)),
                        ifTrue(water5, sequence(
                                ifTrue(ON_CEILING, CHERT),
                                ifTrue(isBiome(NSBiomes.SHRUBBY_HIGHLANDS), SANDY_SOIL),
                                ifTrue(isBiome(NSBiomes.ARID_HIGHLANDS), PINK_SAND))),
                        ifTrue(not(holeCondition), CHERT))),
                ifTrue(UNDER_FLOOR, ifTrue(belowWater, ifTrue(not(isBiome(NSBiomes.ARID_RIVER, NSBiomes.DUNES_RIVER)), CHERT)))));
        RuleSource redwoodBody = ifTrue(isBiome(redwoodMaple), sequence(
                ifTrue(noiseCondition(Noises.SURFACE, 1.75 / 8.25, Double.MAX_VALUE), COARSE_DIRT),
                ifTrue(ON_FLOOR, ifTrue(water7, sequence(
                        ifTrue(noiseCondition(Noises.SURFACE, -0.95 / 8.25, Double.MAX_VALUE), PODZOL),
                        GRASS))),
                DIRT));
        RuleSource alpineBody = ifTrue(isBiome(alpineSugiGolden), sequence(
                ifTrue(noise2, COARSE_DIRT),
                ifTrue(ON_FLOOR, ifTrue(water7, GRASS))));
        List<RuleSource> topsoil = new ArrayList<>(3);
        topsoil.add(redwoodBody);
        topsoil.add(alpineBody);
        if (NSConfig.plainsToggle) {
            topsoil.add(ifTrue(isBiome(Biomes.PLAINS), sequence(
                    ifTrue(noiseCondition(Noises.SPAGHETTI_3D_2, -0.5454D, -0.0818D),
                            ifTrue(noiseCondition(Noises.SURFACE, -0.5454D, -0.4818D), COARSE_DIRT)),
                    ifTrue(ON_FLOOR, ifTrue(water7, GRASS)))));
        }
        RuleSource topsoilRules = ifTrue(depth1Surf, sequence(topsoil.toArray(RuleSource[]::new)));

        RuleSource shallowRules = ifTrue(shallow, sequence(
                floorRules,
                whiteCliffsRule,
                steppeRule,
                topsoilRules));

        RuleSource chaparralBody = ifTrue(isBiome(NSBiomes.CHAPARRAL), sequence(
                ifTrue(depth1, ifTrue(noiseCondition(Noises.PATCH, 0.0), sequence(
                        ifTrue(ON_FLOOR, GRASS),
                        DIRT))),
                ifTrue(UNDER_FLOOR, SANDY_SOIL),
                stratifiedBandlandsRule));
        RuleSource dunesBody = ifTrue(isBiome(NSBiomes.LIVELY_DUNES, NSBiomes.BLOOMING_DUNES), stratifiedBandlandsRule);
        RuleSource xericPlainsBody = ifTrue(isBiome(xericPlains), ifTrue(noise4, sequence(
                ifTrue(isBiome(NSBiomes.XERIC_PLAINS, NSBiomes.CEDAR_THICKET, NSBiomes.XERIC_RIVER), travertineOrSoil),
                ifTrue(isBiome(NSBiomes.ARID_SAVANNA), chertOrSoil),
                pinkSandstoneOrSoil)));
        RuleSource tundraBody = ifTrue(isBiome(tundra), sequence(
                ifTrue(noise4, ifTrue(not(isBiome(NSBiomes.SNOWY_FIR_FOREST)), stoneOrMoss)),
                ifTrue(noise5, ifTrue(not(isBiome(NSBiomes.BOREAL_TAIGA)), stoneOrSnow))));
        RuleSource yellowSandsBody = ifTrue(isBiome(yellowSands), sequence(
                ifTrue(UNDER_FLOOR, sandstoneOrSand),
                ifTrue(DEEP_UNDER_FLOOR, SANDSTONE)));
        RuleSource peaksBody = ifTrue(isBiome(peaks), sequence(
                ifTrue(UNDER_FLOOR, sequence(
                        ifTrue(water7, ifTrue(isBiome(NSBiomes.SLEETED_SLOPES, NSBiomes.SNOWCAPPED_RED_PEAKS), SNOW_BLOCK)),
                        ifTrue(y256, CHERT),
                        ifTrue(y70, steppeBandlandsRule),
                        ifTrue(water5, CHERT),
                        ifTrue(not(holeCondition), CHERT))),
                ifTrue(y256Offset, ifTrue(isBiome(NSBiomes.DUSTY_SLOPES, NSBiomes.RED_PEAKS), SNOW_BLOCK)),
                CHERT));
        RuleSource redSandsBody = ifTrue(isBiome(redSands), sequence(
                ifTrue(UNDER_FLOOR, sequence(
                        ifTrue(noise4, redSandstoneOrRedSand),
                        chertOrSoil)),
                ifTrue(DEEP_UNDER_FLOOR, ifTrue(isBiome(NSBiomes.RED_BEACH), RED_SANDSTONE)),
                ifTrue(VERY_DEEP_UNDER_FLOOR, ifTrue(isBiome(NSBiomes.SCORCHED_DUNES), RED_SANDSTONE))));
        RuleSource pinkSandsBody = ifTrue(isBiome(pinkSands), sequence(
                ifTrue(UNDER_FLOOR, pinkSandstoneOrPinkSand),
                ifTrue(DEEP_UNDER_FLOOR, ifTrue(not(isBiome(NSBiomes.DRYLANDS)), PINK_SANDSTONE)),
                ifTrue(VERY_DEEP_UNDER_FLOOR, ifTrue(isBiome(NSBiomes.DRYLANDS), PINK_SANDSTONE))));
        RuleSource aridRules = ifTrue(depth30Surf, ifTrue(belowWater, ifTrue(isBiome(arid), sequence(
                ifTrue(depth8Surf, sequence(
                        ifTrue(UNDER_FLOOR, sequence(xericPlainsBody, tundraBody)),
                        chaparralBody,
                        dunesBody,
                        yellowSandsBody,
                        peaksBody)),
                redSandsBody,
                pinkSandsBody))));
        RuleSource aspenRules = ifTrue(depth36, ifTrue(isBiome(aspen), sequence(
                ifTrue(depth4, sequence(
                        ifTrue(noiseCondition(Noises.SURFACE, 2.4 / 8.25, Double.MAX_VALUE), COARSE_DIRT),
                        ifTrue(noiseCondition(Noises.SURFACE, -1.4 / 8.25, Double.MAX_VALUE), ROOTED_DIRT),
                        ifTrue(ON_FLOOR, ifTrue(water7, GRASS)),
                        DIRT)),
                GRANITE)));

        RuleSource surfaceRules = ifTrue(abovePreliminarySurface(), ifTrue(nearSurface, sequence(shallowRules, aridRules, aspenRules)));
        RuleSource undergroundRules = ifTrue(y25, sequence(
                ifTrue(isBiome(NSBiomes.SLEETED_SLOPES, NSBiomes.BLOOMING_HIGHLANDS, NSBiomes.SNOWCAPPED_RED_PEAKS, NSBiomes.SHRUBBY_HIGHLANDS,
                        NSBiomes.WOODY_HIGHLANDS, NSBiomes.ARID_HIGHLANDS, NSBiomes.STRATIFIED_DESERT), steppeBandlandsRule),
                ifTrue(y45, ifTrue(isBiome(NSBiomes.WHITE_CLIFFS), sequence(
                        ifTrue(y60Chalk, WHITE_CHALK),
                        CALCITE)))));
        return sequence(surfaceRules, undergroundRules);
    }

    @SafeVarargs
    private static ResourceKey<Biome>[] join(ResourceKey<Biome>[]... arrays) {
        Set<ResourceKey<Biome>> set = new LinkedHashSet<>();
        for (ResourceKey<Biome>[] array : arrays) {
            set.addAll(List.of(array));
        }
        return set.toArray(new ResourceKey[0]);
    }
    private static RuleSource makeStateRule(Block block) {
        return state(block.defaultBlockState());
    }

}