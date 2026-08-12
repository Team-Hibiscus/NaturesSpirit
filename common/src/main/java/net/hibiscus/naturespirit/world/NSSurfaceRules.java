package net.hibiscus.naturespirit.world;

import com.google.common.collect.ImmutableList;
import net.hibiscus.naturespirit.registration.NSBiomes;
import net.hibiscus.naturespirit.registration.NSBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

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
  private static final RuleSource RED_SANDSTONE = makeStateRule(Blocks.RED_SANDSTONE);
  private static final RuleSource RED_SAND = makeStateRule(Blocks.RED_SAND);
  private static final RuleSource TRAVERTINE = makeStateRule(NSBlocks.TRAVERTINE.getBase().get());
  private static final RuleSource WHITE_CHALK = makeStateRule(NSBlocks.WHITE_CHALK.get());
  private static final RuleSource PINK_SAND = makeStateRule(NSBlocks.PINK_SAND.get());
  private static final RuleSource CHERT = makeStateRule(NSBlocks.CHERT.getBase().get());
  private static final RuleSource SNOW_BLOCK = makeStateRule(Blocks.SNOW_BLOCK);
  private static final RuleSource WATER = makeStateRule(Blocks.WATER);
  private static final RuleSource POWDER_SNOW = makeStateRule(Blocks.POWDER_SNOW);
  
  private static final ConditionSource STONE_DEPTH_FLOOR_DOWN_1 = SurfaceRules.stoneDepthCheck(1, false, CaveSurface.FLOOR);
  private static final ConditionSource STONE_DEPTH_FLOOR_DOWN_1_WITH_DEPTH = SurfaceRules.stoneDepthCheck(1, true, CaveSurface.FLOOR);

  public static RuleSource makeRules() {
    ConditionSource above25 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(25), -1);
    ConditionSource above60 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(60), 0);
    ConditionSource above63 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(63), 0);
    ConditionSource above65 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(65), 0);
    ConditionSource above70 = SurfaceRules.yStartCheck(VerticalAnchor.absolute(70), 1);
    ConditionSource above76 = SurfaceRules.yStartCheck(VerticalAnchor.absolute(76), 1);
    ConditionSource above80 = SurfaceRules.yStartCheck(VerticalAnchor.absolute(80), 1);
    ConditionSource above256 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(256), 0);
    ConditionSource materialCondition5 = SurfaceRules.waterBlockCheck(
        -1,
        0
    );
    ConditionSource materialCondition7 = SurfaceRules.waterBlockCheck(
        0,
        0
    );
    ConditionSource holeCondition = SurfaceRules.hole();
    ConditionSource noiseCondition1 = SurfaceRules.noiseCondition(Noises.SURFACE, -0.909D, -0.5454D);
    ConditionSource noiseCondition2 = SurfaceRules.noiseCondition(Noises.SURFACE, -0.5454D, -0.3818D);
    ConditionSource noiseCondition3 = SurfaceRules.noiseCondition(Noises.SURFACE, 0.5454D, 0.909D);
    ConditionSource noiseCondition4 = SurfaceRules.noiseCondition(Noises.SURFACE, -0.5454D, 0.0454D);
    ConditionSource noiseCondition5 = SurfaceRules.noiseCondition(Noises.SURFACE, 0.2454D, 6D);
    ConditionSource noiseCondition6 = SurfaceRules.noiseCondition(Noises.SURFACE, -0.0454D, 6D);

    ConditionSource belowWater = SurfaceRules.waterStartCheck(-6, -1);

    RuleSource stoneOrGravel = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, STONE), GRAVEL);
    RuleSource pinkSandstoneOrPinkSand = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, PINK_SANDSTONE), PINK_SAND);
    RuleSource redSandstoneOrRedSand = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, RED_SANDSTONE), RED_SAND);
    RuleSource pinkSandstoneOrSoil = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, PINK_SANDSTONE), SANDY_SOIL);
    RuleSource travertineOrSoil = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, TRAVERTINE), SANDY_SOIL);
    RuleSource chertOrSoil = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, CHERT), SANDY_SOIL);
    RuleSource stoneOrMoss = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, STONE), RED_MOSS_BLOCK);
    RuleSource stoneOrSnow = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, STONE), SNOW_BLOCK);
    RuleSource powderSnow = SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.POWDER_SNOW, 0.35, 0.6), SurfaceRules.ifTrue(materialCondition7, POWDER_SNOW));

    RuleSource stratifiedDesertRule = SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
        SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.STRATIFIED_DESERT), SurfaceRules.sequence(
            SurfaceRules.ifTrue(above256, CHERT),
            SurfaceRules.ifTrue(above70, SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.not(above76),
                    SurfaceRules.sequence(
                        SurfaceRules.ifTrue(noiseCondition1, GRASS),
                        SurfaceRules.ifTrue(noiseCondition2, COARSE_DIRT)
                    )),
                SurfaceRules.ifTrue(noiseCondition3, SANDY_SOIL),
                SurfaceRules.bandlands()
            )),
            SurfaceRules.ifTrue(materialCondition5, SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, CHERT),
                SANDY_SOIL
            )),
            SurfaceRules.ifTrue(SurfaceRules.not(holeCondition), CHERT),
            SurfaceRules.ifTrue(belowWater, WHITE_KAOLIN),
            stoneOrGravel
        ))

    );
    RuleSource steppeUndergroundRule = SurfaceRules.ifTrue(
        SurfaceRules.isBiome(NSBiomes.SLEETED_SLOPES, NSBiomes.BLOOMING_HIGHLANDS, NSBiomes.SNOWCAPPED_RED_PEAKS, NSBiomes.SHRUBBY_HIGHLANDS, NSBiomes.WOODY_HIGHLANDS,
            NSBiomes.ARID_HIGHLANDS, NSBiomes.STRATIFIED_DESERT),
        SurfaceRules.ifTrue(above25, SurfaceRules.bandlands())
    );

    RuleSource desertSteppeRule = SurfaceRules.sequence(
        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,  SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.ARID_HIGHLANDS, NSBiomes.SHRUBBY_HIGHLANDS, NSBiomes.WOODY_HIGHLANDS, NSBiomes.BLOOMING_HIGHLANDS), SurfaceRules.sequence(
                SurfaceRules.ifTrue(above256, CHERT),
                SurfaceRules.ifTrue(above70, SurfaceRules.sequence(
                    SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.SHRUBBY_HIGHLANDS, NSBiomes.ARID_HIGHLANDS), SurfaceRules.ifTrue(noiseCondition3, SANDY_SOIL)),
                    SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.WOODY_HIGHLANDS, NSBiomes.BLOOMING_HIGHLANDS), SurfaceRules.ifTrue(noiseCondition3, GRASS)),
                    SurfaceRules.bandlands(),
                    SurfaceRules.ifTrue(SurfaceRules.not(above80), SurfaceRules.sequence(
                        SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.SHRUBBY_HIGHLANDS), SurfaceRules.ifTrue(noiseCondition6, GRASS)),
                        SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.WOODY_HIGHLANDS, NSBiomes.BLOOMING_HIGHLANDS), SurfaceRules.ifTrue(noiseCondition6, SANDY_SOIL))
                    ))
                )),
                SurfaceRules.ifTrue(materialCondition5, SurfaceRules.sequence(
                    SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, CHERT),
                    SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.SHRUBBY_HIGHLANDS), SANDY_SOIL),
                    SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.ARID_HIGHLANDS), PINK_SAND)
                )),
                SurfaceRules.ifTrue(SurfaceRules.not(holeCondition), CHERT)
            ))),
        SurfaceRules.ifTrue(UNDER_FLOOR, SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.ARID_HIGHLANDS, NSBiomes.SHRUBBY_HIGHLANDS, NSBiomes.WOODY_HIGHLANDS, NSBiomes.BLOOMING_HIGHLANDS), SurfaceRules.ifTrue(belowWater, CHERT)))
        );
    
    RuleSource aspenRule = SurfaceRules.sequence(
            SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(4, false, CaveSurface.FLOOR),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.ASPEN_FOREST),
                    SurfaceRules.sequence(
                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SURFACE, 2.4 / 8.25, Double.MAX_VALUE), COARSE_DIRT),
                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SURFACE, 1.6 / 8.25, Double.MAX_VALUE), ROOTED_DIRT),
                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SURFACE, -1.4 / 8.25, Double.MAX_VALUE), ROOTED_DIRT),
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.ifTrue(materialCondition7, GRASS)),
                        DIRT
                    ))),
            SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(36, false, CaveSurface.FLOOR), SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.ASPEN_FOREST), GRANITE)));
    
    RuleSource xericRule =
        SurfaceRules.ifTrue(belowWater,
            SurfaceRules.sequence(
                SurfaceRules.ifTrue(UNDER_FLOOR,
                    SurfaceRules.sequence(
                        SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.XERIC_PLAINS, NSBiomes.CEDAR_THICKET), SurfaceRules.ifTrue(noiseCondition4, travertineOrSoil)),
                        SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.ARID_SAVANNA), SurfaceRules.ifTrue(noiseCondition4, chertOrSoil)),
                        SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.WOODED_DRYLANDS), SurfaceRules.ifTrue(noiseCondition4, pinkSandstoneOrSoil)),
                        SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.TUNDRA, NSBiomes.BOREAL_TAIGA), SurfaceRules.ifTrue(noiseCondition4, stoneOrMoss)),
                        SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.SCORCHED_DUNES), SurfaceRules.sequence(SurfaceRules.ifTrue(noiseCondition4, redSandstoneOrRedSand), chertOrSoil)),
                        SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.TROPICAL_SHORES, NSBiomes.DRYLANDS), pinkSandstoneOrPinkSand),
                        SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.SNOWY_FIR_FOREST, NSBiomes.TUNDRA), SurfaceRules.ifTrue(noiseCondition5, stoneOrSnow)),
                        SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.CHAPARRAL), SANDY_SOIL),
                        SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.SLEETED_SLOPES, NSBiomes.SNOWCAPPED_RED_PEAKS), SurfaceRules.ifTrue(materialCondition7, SNOW_BLOCK)),
                        SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.SLEETED_SLOPES, NSBiomes.SNOWCAPPED_RED_PEAKS, NSBiomes.DUSTY_SLOPES, NSBiomes.RED_PEAKS),
                            SurfaceRules.sequence(
                                SurfaceRules.ifTrue(above256, CHERT),
                                SurfaceRules.ifTrue(above70, SurfaceRules.bandlands()),
                                SurfaceRules.ifTrue(materialCondition5, CHERT),
                                SurfaceRules.ifTrue(SurfaceRules.not(holeCondition), CHERT)
                            ))
                    )),
                SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(8, true, CaveSurface.FLOOR),
                    SurfaceRules.sequence(
                        SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.LIVELY_DUNES, NSBiomes.BLOOMING_DUNES, NSBiomes.CHAPARRAL), SurfaceRules.bandlands()),
                        SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.DUSTY_SLOPES, NSBiomes.RED_PEAKS), SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(256), 1), SNOW_BLOCK)),
                        SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.SLEETED_SLOPES, NSBiomes.SNOWCAPPED_RED_PEAKS, NSBiomes.DUSTY_SLOPES, NSBiomes.RED_PEAKS),
                            SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.steep(), CHERT),
                                SurfaceRules.ifTrue(belowWater, CHERT),
                                SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.SLEETED_SLOPES), powderSnow)
                            ))
                    )),
                SurfaceRules.ifTrue(DEEP_UNDER_FLOOR, SurfaceRules.sequence(
                    SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.TROPICAL_SHORES), PINK_SANDSTONE),
                    SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.LIVELY_DUNES, NSBiomes.BLOOMING_DUNES), SANDY_SOIL)
                )),
                SurfaceRules.ifTrue(VERY_DEEP_UNDER_FLOOR, SurfaceRules.sequence(
                    SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.SCORCHED_DUNES), RED_SANDSTONE),
                    SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.DRYLANDS), PINK_SANDSTONE)
                ))
            )
        );
    RuleSource chaparralRule = SurfaceRules.ifTrue(belowWater, SurfaceRules.sequence(
        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.CHAPARRAL), SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.PATCH, 0.0), GRASS))),
        SurfaceRules.ifTrue(STONE_DEPTH_FLOOR_DOWN_1, SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.CHAPARRAL), SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.PATCH, 0.0), DIRT)))
    ));

    RuleSource tropicalBasinRule = SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
        SurfaceRules.ifTrue(above60, SurfaceRules.ifTrue(SurfaceRules.not(above63),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.MARSH, NSBiomes.TROPICAL_BASIN, NSBiomes.BAMBOO_WETLANDS), SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SWAMP, 0.0), WATER))))
        );
    
    RuleSource chalkCliffsRule = SurfaceRules.ifTrue(above65,
        SurfaceRules.sequence(
            SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.WHITE_CLIFFS), GRASS)),
            SurfaceRules.ifTrue(STONE_DEPTH_FLOOR_DOWN_1, SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.WHITE_CLIFFS), DIRT))
        )
    );
    RuleSource chalkUndergroundRule =
        SurfaceRules.sequence(
            SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(60), -1), SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.WHITE_CLIFFS), WHITE_CHALK)),
            SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(45), -1), SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.WHITE_CLIFFS), CALCITE))
        );
    RuleSource redwoodForestRule =
        SurfaceRules.sequence(
            SurfaceRules.ifTrue(STONE_DEPTH_FLOOR_DOWN_1_WITH_DEPTH,
                SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.REDWOOD_FOREST, NSBiomes.MAPLE_WOODLANDS),
                    SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SURFACE, 1.75 / 8.25, Double.MAX_VALUE), COARSE_DIRT))),
            SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                SurfaceRules.ifTrue(materialCondition7,
                    SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.REDWOOD_FOREST, NSBiomes.MAPLE_WOODLANDS), SurfaceRules.sequence(
                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SURFACE, -.95 / 8.25, Double.MAX_VALUE), PODZOL),
                        GRASS
                )))),
            SurfaceRules.ifTrue(STONE_DEPTH_FLOOR_DOWN_1_WITH_DEPTH, SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.REDWOOD_FOREST, NSBiomes.MAPLE_WOODLANDS), DIRT))
        );

    RuleSource alpineRule = SurfaceRules.sequence(
            SurfaceRules.ifTrue(STONE_DEPTH_FLOOR_DOWN_1_WITH_DEPTH, SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.ALPINE_CLEARINGS, NSBiomes.ALPINE_HIGHLANDS, NSBiomes.CONIFEROUS_COVERT, NSBiomes.HEATHER_FIELDS, NSBiomes.GOLDEN_WILDS, NSBiomes.SUGI_FOREST),
                SurfaceRules.ifTrue(noiseCondition2, COARSE_DIRT))),
            SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.ifTrue(materialCondition7,
                SurfaceRules.ifTrue(SurfaceRules.isBiome(NSBiomes.ALPINE_CLEARINGS, NSBiomes.ALPINE_HIGHLANDS, NSBiomes.CONIFEROUS_COVERT, NSBiomes.HEATHER_FIELDS, NSBiomes.GOLDEN_WILDS, NSBiomes.SUGI_FOREST), GRASS))));

    ImmutableList.Builder<RuleSource> builder = ImmutableList.builder();
    RuleSource stratifiedDesertSurfaceRule = SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), stratifiedDesertRule);
    RuleSource chaparralSurfaceRule = SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), chaparralRule);
    RuleSource chalkCliffsSurfaceRule = SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), chalkCliffsRule);
    RuleSource xericSurfaceRule = SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), xericRule);
    RuleSource tropicalBasinSurfaceRule = SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), tropicalBasinRule);
    RuleSource aspenSurfaceRule = SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), aspenRule);
    RuleSource desertSteppeSurfaceRule = SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), desertSteppeRule);
    RuleSource redwoodForestSurfaceRule = SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), redwoodForestRule);
    RuleSource alpineSurfaceRule = SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), alpineRule);
    builder.add(stratifiedDesertSurfaceRule);
    builder.add(chaparralSurfaceRule);
    builder.add(chalkCliffsSurfaceRule);
    builder.add(chalkUndergroundRule);
    builder.add(xericSurfaceRule);
    builder.add(tropicalBasinSurfaceRule);
    builder.add(aspenSurfaceRule);
    builder.add(desertSteppeSurfaceRule);
    builder.add(steppeUndergroundRule);
    builder.add(redwoodForestSurfaceRule);
    builder.add(alpineSurfaceRule);
    return SurfaceRules.sequence(builder
        .build()
        .toArray(RuleSource[]::new));
  }

  private static RuleSource makeStateRule(Block block) {
    return SurfaceRules.state(block.defaultBlockState());
  }
}
