package net.hibiscus.naturespirit.world;

import com.google.common.collect.ImmutableList;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusMiscBlocks;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusColoredBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.noise.NoiseParametersKeys;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

import static net.minecraft.world.gen.surfacebuilder.MaterialRules.*;

public class NatureSpiritSurfaceRules {
   private static final MaterialRules.MaterialRule GRAVEL = makeStateRule(Blocks.GRAVEL);

   private static final MaterialRules.MaterialRule STONE = makeStateRule(Blocks.STONE);

   private static final MaterialRules.MaterialRule GRASS = makeStateRule(Blocks.GRASS_BLOCK);

   private static final MaterialRules.MaterialRule DIRT = makeStateRule(Blocks.DIRT);
   private static final MaterialRules.MaterialRule ROOTED_DIRT = makeStateRule(Blocks.ROOTED_DIRT);
   private static final MaterialRules.MaterialRule GRANITE = makeStateRule(Blocks.GRANITE);
//   private static final MaterialRules.MaterialRule SAND = makeStateRule(Blocks.SAND);
//   private static final MaterialRules.MaterialRule SANDSTONE = makeStateRule(Blocks.SANDSTONE);

   private static final MaterialRules.MaterialRule CALCITE = makeStateRule(Blocks.CALCITE);

   private static final MaterialRules.MaterialRule SANDY_SOIL = makeStateRule(HibiscusMiscBlocks.SANDY_SOIL);
   private static final MaterialRules.MaterialRule RED_MOSS_BLOCK = makeStateRule(HibiscusMiscBlocks.RED_MOSS_BLOCK);

   private static final MaterialRules.MaterialRule COARSE_DIRT = makeStateRule(Blocks.COARSE_DIRT);

   private static final MaterialRules.MaterialRule WHITE_KAOLIN = makeStateRule(HibiscusColoredBlocks.WHITE_KAOLIN);

   private static final MaterialRules.MaterialRule PINK_SANDSTONE = makeStateRule(HibiscusMiscBlocks.PINK_SANDSTONE);
   private static final MaterialRules.MaterialRule RED_SANDSTONE = makeStateRule(Blocks.RED_SANDSTONE);
   private static final MaterialRules.MaterialRule RED_SAND = makeStateRule(Blocks.RED_SAND);
   private static final MaterialRules.MaterialRule TRAVERTINE = makeStateRule(HibiscusMiscBlocks.TRAVERTINE.getBase());

   private static final MaterialRules.MaterialRule KAOLIN = makeStateRule(HibiscusColoredBlocks.KAOLIN);
   private static final MaterialRules.MaterialRule WHITE_CHALK = makeStateRule(HibiscusColoredBlocks.WHITE_CHALK);

   private static final MaterialRules.MaterialRule PINK_SAND = makeStateRule(HibiscusMiscBlocks.PINK_SAND);

   private static final MaterialRules.MaterialRule YELLOW_KAOLIN = makeStateRule(HibiscusColoredBlocks.YELLOW_KAOLIN);
   private static final MaterialRules.MaterialRule SNOW_BLOCK = makeStateRule(Blocks.SNOW_BLOCK);
   private static final MaterialRules.MaterialRule WATER = makeStateRule(Blocks.WATER);

   public static MaterialRules.MaterialRule makeRules() {
      MaterialRules.MaterialCondition materialCondition = MaterialRules.aboveY(YOffset.fixed(256), 0);
      MaterialRules.MaterialCondition materialCondition2 = MaterialRules.aboveYWithStoneDepth(YOffset.fixed(63), -1);
      MaterialRules.MaterialCondition materialCondition3 = MaterialRules.aboveYWithStoneDepth(YOffset.fixed(70), 1);
      MaterialRules.MaterialCondition materialCondition4 = MaterialRules.aboveY(YOffset.fixed(63), 0);
      MaterialRules.MaterialCondition above62 = MaterialRules.aboveY(YOffset.fixed(62), 0);
      MaterialRules.MaterialCondition chalkGrassCondition = MaterialRules.aboveY(YOffset.fixed(65), 0);
      MaterialRules.MaterialCondition materialCondition5 = MaterialRules.water(
              -1,
              0
      );
      MaterialRules.MaterialCondition materialCondition6 = MaterialRules.aboveY(YOffset.fixed(60), 0);
      MaterialRules.MaterialCondition holeCondition = MaterialRules.hole();
      MaterialRules.MaterialCondition noiseCondition1 = MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, -0.909D, -0.5454D);
      MaterialRules.MaterialCondition noiseCondition2 = MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, -0.5454D, -0.3818D);
      MaterialRules.MaterialCondition noiseCondition3 = MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, 0.5454D, 0.909D);
      MaterialRules.MaterialCondition noiseCondition4 = MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, -0.5454D, 0.0454D);
      MaterialRules.MaterialCondition noiseCondition5 = MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, 0.2454D, 6D);

      MaterialRules.MaterialCondition above76 = MaterialRules.aboveYWithStoneDepth(YOffset.fixed(76), 1);
      MaterialRules.MaterialCondition belowWater = MaterialRules.waterWithStoneDepth(-6, -1);

      MaterialRules.MaterialRule sandySoilRule = MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, SANDY_SOIL), SANDY_SOIL);
      MaterialRules.MaterialRule stoneOrGravel = MaterialRules.sequence(
              MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, STONE),
              GRAVEL
      );
      MaterialRules.MaterialRule pinkSandstoneOrPinkSand = MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, PINK_SANDSTONE), PINK_SAND);
      MaterialRules.MaterialRule redSandstoneOrRedSand = MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, RED_SANDSTONE), RED_SAND);
      MaterialRules.MaterialRule pinkSandstoneOrSoil = MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, PINK_SANDSTONE), SANDY_SOIL);
      MaterialRules.MaterialRule travertineOrSoil = MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, TRAVERTINE), SANDY_SOIL);
      MaterialRules.MaterialRule kaolinOrSoil = MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, KAOLIN), SANDY_SOIL);
      MaterialRules.MaterialRule stoneOrMoss = MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, STONE), RED_MOSS_BLOCK);
      MaterialRules.MaterialRule stoneOrSnow = MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, STONE), SNOW_BLOCK);




      MaterialRules.MaterialRule stratifiedDesertRule = MaterialRules.sequence(MaterialRules.condition(MaterialRules.biome(HibiscusBiomes.STRATIFIED_DESERT),
              MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, MaterialRules.sequence(MaterialRules.condition(materialCondition, YELLOW_KAOLIN),
                              MaterialRules.condition(materialCondition3, MaterialRules.sequence(MaterialRules.condition(MaterialRules.not(above76),
                                      MaterialRules.sequence(MaterialRules.condition(noiseCondition1, GRASS), MaterialRules.condition(noiseCondition2, COARSE_DIRT))
                              ), MaterialRules.condition(noiseCondition3, SANDY_SOIL), MaterialRules.terracottaBands())),
                              MaterialRules.condition(
                                      materialCondition5,
                                      MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, YELLOW_KAOLIN), SANDY_SOIL)
                              ),
                              MaterialRules.condition(MaterialRules.not(holeCondition), YELLOW_KAOLIN),
                              MaterialRules.condition(belowWater, WHITE_KAOLIN),
                              stoneOrGravel
                      )),
                      MaterialRules.condition(materialCondition2,
                              MaterialRules.sequence(MaterialRules.condition(materialCondition4, MaterialRules.condition(MaterialRules.not(materialCondition3), YELLOW_KAOLIN)),
                                      MaterialRules.terracottaBands()
                              )
                      ),
                      MaterialRules.condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, MaterialRules.condition(belowWater, WHITE_KAOLIN))
              )
      ));


      MaterialRules.MaterialRule bloomingDunesRule = MaterialRules.condition(MaterialRules.biome(HibiscusBiomes.BLOOMING_DUNES),
              MaterialRules.condition(belowWater, MaterialRules.sequence(
                      MaterialRules.condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, sandySoilRule), MaterialRules.condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH_RANGE_6, SANDY_SOIL)
              ))
      );
      MaterialRules.MaterialRule livelyDunesRule = MaterialRules.condition(MaterialRules.biome(HibiscusBiomes.LIVELY_DUNES),
              MaterialRules.condition(belowWater, MaterialRules.sequence(
                      MaterialRules.condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, sandySoilRule), MaterialRules.condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH_RANGE_6, SANDY_SOIL)
              ))
      );
      MaterialRules.MaterialRule chalkCliffsRule = MaterialRules.condition(MaterialRules.biome(HibiscusBiomes.WHITE_CLIFFS),
              MaterialRules.sequence(
                      MaterialRules.condition(chalkGrassCondition, MaterialRules.sequence(
                              MaterialRules.condition(MaterialRules.stoneDepth(0, false, VerticalSurfaceType.FLOOR), GRASS),
                              MaterialRules.condition(MaterialRules.stoneDepth(1, false, VerticalSurfaceType.FLOOR), DIRT)
                      )),
                      MaterialRules.condition(MaterialRules.stoneDepth(8, false, VerticalSurfaceType.FLOOR), WHITE_CHALK),
                      MaterialRules.condition(MaterialRules.stoneDepth(24, false, VerticalSurfaceType.FLOOR), CALCITE)
              )
      );
      MaterialRules.MaterialRule aspenRule = MaterialRules.condition(MaterialRules.biome(HibiscusBiomes.ASPEN_FOREST),
              MaterialRules.sequence(
                      MaterialRules.condition(above62, MaterialRules.sequence(
                              MaterialRules.condition(MaterialRules.stoneDepth(0, false, VerticalSurfaceType.FLOOR), GRASS),
                              MaterialRules.condition(MaterialRules.stoneDepth(4, false, VerticalSurfaceType.FLOOR), MaterialRules.sequence(MaterialRules.condition(noiseCondition2, ROOTED_DIRT), MaterialRules.condition(noiseCondition3, ROOTED_DIRT), DIRT))
                      )),
                      MaterialRules.condition(MaterialRules.stoneDepth(36, false, VerticalSurfaceType.FLOOR), GRANITE)
              )
      );
      MaterialRules.MaterialRule drylandsRule = MaterialRules.condition(MaterialRules.biome(HibiscusBiomes.DRYLANDS),
              MaterialRules.condition(belowWater,
                      MaterialRules.sequence(MaterialRules.condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, pinkSandstoneOrPinkSand), MaterialRules.condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH_RANGE_30, PINK_SANDSTONE)
              )
              )
      );
      MaterialRules.MaterialRule scorchedDunesRule = MaterialRules.condition(MaterialRules.biome(HibiscusBiomes.SCORCHED_DUNES),
              MaterialRules.condition(belowWater,
                      MaterialRules.sequence(MaterialRules.condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, MaterialRules.sequence(MaterialRules.condition(noiseCondition4, redSandstoneOrRedSand), kaolinOrSoil)), MaterialRules.condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH_RANGE_30, RED_SANDSTONE)
                      )
              )
      );
      MaterialRules.MaterialRule woodedDrylandsRule = MaterialRules.condition(MaterialRules.biome(HibiscusBiomes.WOODED_DRYLANDS),
              MaterialRules.condition(belowWater,
                      MaterialRules.condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, MaterialRules.condition(noiseCondition4, pinkSandstoneOrSoil))
              )
      );
      MaterialRules.MaterialRule shoresRule = MaterialRules.condition(MaterialRules.biome(HibiscusBiomes.TROPICAL_SHORES),
              MaterialRules.condition(belowWater,
                      MaterialRules.sequence(MaterialRules.condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, pinkSandstoneOrPinkSand), MaterialRules.condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH_RANGE_6, PINK_SANDSTONE)
                      )
              )
      );
      MaterialRules.MaterialRule xericRule = MaterialRules.condition(MaterialRules.biome(HibiscusBiomes.XERIC_PLAINS),
              MaterialRules.condition(belowWater,
                      MaterialRules.condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, MaterialRules.condition(noiseCondition4, travertineOrSoil))
              )
      );
      MaterialRules.MaterialRule aridRule = MaterialRules.condition(MaterialRules.biome(HibiscusBiomes.ARID_SAVANNA),
              MaterialRules.condition(belowWater,
                      MaterialRules.condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, MaterialRules.condition(noiseCondition4, kaolinOrSoil))
              )
      );
      MaterialRules.MaterialRule tundraRule = MaterialRules.condition(belowWater,
                      MaterialRules.condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH,
                              MaterialRules.condition(MaterialRules.biome(HibiscusBiomes.SNOWY_FIR_FOREST, HibiscusBiomes.TUNDRA, HibiscusBiomes.BOREAL_TAIGA),
                                      MaterialRules.sequence(MaterialRules.condition(noiseCondition4, MaterialRules.condition(MaterialRules.biome(HibiscusBiomes.TUNDRA, HibiscusBiomes.BOREAL_TAIGA), stoneOrMoss)), MaterialRules.condition(MaterialRules.biome(HibiscusBiomes.SNOWY_FIR_FOREST, HibiscusBiomes.TUNDRA) ,MaterialRules.condition(noiseCondition5, stoneOrSnow)))))
              );
      MaterialRules.MaterialRule tropicalBasinRule = MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, MaterialRules.sequence(
                      MaterialRules.condition(MaterialRules.biome(HibiscusBiomes.MARSH, HibiscusBiomes.TROPICAL_BASIN, HibiscusBiomes.BAMBOO_WETLANDS), MaterialRules.condition(materialCondition6, MaterialRules.condition(MaterialRules.not(materialCondition4), MaterialRules.condition(MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE_SWAMP, 0.0), WATER))))
              ))
      );



      ImmutableList.Builder <MaterialRules.MaterialRule> builder = ImmutableList.builder();
      MaterialRules.MaterialRule stratifiedDesertSurfaceRule = MaterialRules.condition(MaterialRules.surface(), stratifiedDesertRule);
      MaterialRules.MaterialRule bloomingDunesSurfaceRule = MaterialRules.condition(MaterialRules.surface(), bloomingDunesRule);
      MaterialRules.MaterialRule livelyDunesSurfaceRule = MaterialRules.condition(MaterialRules.surface(), livelyDunesRule);
      MaterialRules.MaterialRule chalkCliffsSurfaceRule = MaterialRules.condition(MaterialRules.surface(), chalkCliffsRule);
      MaterialRules.MaterialRule drylandsSurfaceRule = MaterialRules.condition(MaterialRules.surface(), drylandsRule);
      MaterialRules.MaterialRule woodedDrylandsSurfaceRule = MaterialRules.condition(MaterialRules.surface(), woodedDrylandsRule);
      MaterialRules.MaterialRule shoresSurfaceRule = MaterialRules.condition(MaterialRules.surface(), shoresRule);
      MaterialRules.MaterialRule xericSurfaceRule = MaterialRules.condition(MaterialRules.surface(), xericRule);
      MaterialRules.MaterialRule aridSurfaceRule = MaterialRules.condition(MaterialRules.surface(), aridRule);
      MaterialRules.MaterialRule scorchedDunesSurfaceRule = MaterialRules.condition(MaterialRules.surface(), scorchedDunesRule);
      MaterialRules.MaterialRule tundraSurfaceRule = MaterialRules.condition(MaterialRules.surface(), tundraRule);
      MaterialRules.MaterialRule tropicalBasinSurfaceRule = MaterialRules.condition(MaterialRules.surface(), tropicalBasinRule);
      MaterialRules.MaterialRule aspenSurfaceRule = MaterialRules.condition(MaterialRules.surface(), aspenRule);
      builder.add(stratifiedDesertSurfaceRule);
      builder.add(bloomingDunesSurfaceRule);
      builder.add(livelyDunesSurfaceRule);
      builder.add(chalkCliffsSurfaceRule);
      builder.add(drylandsSurfaceRule);
      builder.add(woodedDrylandsSurfaceRule);
      builder.add(shoresSurfaceRule);
      builder.add(xericSurfaceRule);
      builder.add(aridSurfaceRule);
      builder.add(tundraSurfaceRule);
      builder.add(tropicalBasinSurfaceRule);
      builder.add(scorchedDunesSurfaceRule);
      builder.add(aspenSurfaceRule);
      return MaterialRules.sequence(builder
              .build()
              .toArray(MaterialRules.MaterialRule[]::new));
   }

   private static MaterialRules.MaterialRule makeStateRule(Block block) {
      return MaterialRules.block(block.getDefaultState());
   }
}