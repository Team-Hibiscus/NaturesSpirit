package net.hibiscus.naturespirit.terrablender;

import com.google.common.collect.ImmutableList;
import net.hibiscus.naturespirit.datagen.HibiscusBiomes;
import net.hibiscus.naturespirit.registration.HibiscusBlocksAndItems;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusColoredBlocks;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

import static net.minecraft.world.level.levelgen.SurfaceRules.VERY_DEEP_UNDER_FLOOR;
import static net.minecraft.world.level.levelgen.SurfaceRules.stoneDepthCheck;

public class NatureSpiritSurfaceRules {
   private static final SurfaceRules.RuleSource GRAVEL = makeStateRule(Blocks.GRAVEL);
   private static final SurfaceRules.RuleSource STONE = makeStateRule(Blocks.STONE);
   private static final SurfaceRules.RuleSource GRASS = makeStateRule(Blocks.GRASS_BLOCK);
   private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
   private static final SurfaceRules.RuleSource CALCITE = makeStateRule(Blocks.CALCITE);
   private static final SurfaceRules.RuleSource SANDY_SOIL = makeStateRule(HibiscusBlocksAndItems.SANDY_SOIL);
   private static final SurfaceRules.RuleSource COARSE_DIRT = makeStateRule(Blocks.COARSE_DIRT);
   private static final SurfaceRules.RuleSource WHITE_KAOLIN = makeStateRule(HibiscusColoredBlocks.WHITE_KAOLIN);
   private static final SurfaceRules.RuleSource WHITE_CHALK = makeStateRule(HibiscusColoredBlocks.WHITE_CHALK);
   private static final SurfaceRules.RuleSource LIGHT_GRAY_KAOLIN = makeStateRule(HibiscusColoredBlocks.LIGHT_GRAY_KAOLIN);
   private static final SurfaceRules.RuleSource YELLOW_KAOLIN = makeStateRule(HibiscusColoredBlocks.YELLOW_KAOLIN);

   protected static SurfaceRules.RuleSource makeRules() {
      SurfaceRules.ConditionSource materialCondition = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(256), 0);
      SurfaceRules.ConditionSource materialCondition2 = SurfaceRules.yStartCheck(VerticalAnchor.absolute(63), -1);
      SurfaceRules.ConditionSource materialCondition3 = SurfaceRules.yStartCheck(VerticalAnchor.absolute(70), 1);
      SurfaceRules.ConditionSource materialCondition4 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(63), 0);
      SurfaceRules.ConditionSource materialCondition5 = SurfaceRules.waterBlockCheck(-1, 0);
      SurfaceRules.ConditionSource materialCondition6 = SurfaceRules.waterStartCheck(-6, -1);
      SurfaceRules.ConditionSource materialCondition7 = SurfaceRules.hole();
      SurfaceRules.ConditionSource materialCondition8 = SurfaceRules.noiseCondition(Noises.SURFACE,
              -0.909D,
              -0.5454D
      );
      SurfaceRules.ConditionSource materialCondition9 = SurfaceRules.noiseCondition(Noises.SURFACE,
              -0.5454D,
              -0.3818D
      );
      SurfaceRules.ConditionSource materialCondition10 = SurfaceRules.noiseCondition(Noises.SURFACE,
              0.5454D,
              0.909D
      );
      SurfaceRules.ConditionSource materialCondition11 = SurfaceRules.noiseCondition(Noises.SURFACE,
              0.3818D,
              0.5454D
      );

      SurfaceRules.ConditionSource materialCondition12 = SurfaceRules.yStartCheck(VerticalAnchor.absolute(76), 1);
      SurfaceRules.ConditionSource materialCondition13 = SurfaceRules.waterStartCheck(-6, -1);
      SurfaceRules.ConditionSource materialCondition14 = stoneDepthCheck(0, true, 40, CaveSurface.FLOOR);
      SurfaceRules.ConditionSource materialCondition15 = SurfaceRules.waterBlockCheck(0, 0);

      SurfaceRules.RuleSource materialRule = SurfaceRules.sequence(SurfaceRules.ifTrue(
              materialCondition15,
              GRASS
      ), DIRT);
      SurfaceRules.RuleSource materialRule2 = SurfaceRules.sequence(SurfaceRules.ifTrue(
              SurfaceRules.ON_CEILING,
              SANDY_SOIL
      ), SANDY_SOIL);
      SurfaceRules.RuleSource materialRule3 = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING,
              STONE
      ), GRAVEL);
      SurfaceRules.RuleSource materialRule9 = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(
                      HibiscusBiomes.STRATIFIED_DESERT),
              SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.sequence(
                              SurfaceRules.ifTrue(materialCondition, YELLOW_KAOLIN),
                              SurfaceRules.ifTrue(materialCondition3,
                                      SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.not(materialCondition12),
                                                      SurfaceRules.sequence(
                                                              SurfaceRules.ifTrue(materialCondition8, GRASS),
                                                              SurfaceRules.ifTrue(materialCondition9, COARSE_DIRT)
                                                      )
                                              ),
                                              SurfaceRules.ifTrue(materialCondition10, SANDY_SOIL),
                                              SurfaceRules.bandlands()
                                      )
                              ),
                              SurfaceRules.ifTrue(materialCondition5,
                                      SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING,
                                              YELLOW_KAOLIN
                                      ), SANDY_SOIL)
                              ),
                              SurfaceRules.ifTrue(SurfaceRules.not(materialCondition7), YELLOW_KAOLIN),
                              SurfaceRules.ifTrue(materialCondition6, WHITE_KAOLIN),
                              materialRule3
                      )),
                      SurfaceRules.ifTrue(materialCondition2, SurfaceRules.sequence(SurfaceRules.ifTrue(
                              materialCondition4,
                              SurfaceRules.ifTrue(SurfaceRules.not(materialCondition3), YELLOW_KAOLIN)
                      ), SurfaceRules.bandlands())),
                      SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR,
                              SurfaceRules.ifTrue(materialCondition6, WHITE_KAOLIN)
                      )
              )
      ));

      SurfaceRules.RuleSource materialRule10 = SurfaceRules.ifTrue(
              SurfaceRules.isBiome(HibiscusBiomes.BLOOMING_DUNES),
              SurfaceRules.ifTrue(materialCondition13,
                      SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.DESERT),
                              SurfaceRules.ifTrue(VERY_DEEP_UNDER_FLOOR, SANDY_SOIL)
                      ), materialRule2)
              )
      );
      SurfaceRules.RuleSource materialRule11 = SurfaceRules.ifTrue(
              SurfaceRules.isBiome(HibiscusBiomes.LIVELY_DUNES),
              SurfaceRules.ifTrue(materialCondition13,
                      SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.DESERT),
                              SurfaceRules.ifTrue(VERY_DEEP_UNDER_FLOOR, SANDY_SOIL)
                      ), materialRule2)
              )
      );
      SurfaceRules.RuleSource materialRule12 = SurfaceRules.ifTrue(
              SurfaceRules.isBiome(HibiscusBiomes.WHITE_CLIFFS),
              SurfaceRules.sequence(
                      SurfaceRules.sequence(
                              SurfaceRules.ifTrue(materialCondition4, SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, false, CaveSurface.FLOOR), GRASS)), WHITE_CHALK
                      ),
                      SurfaceRules.sequence(
                              SurfaceRules.ifTrue(materialCondition4, SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(1, false, CaveSurface.FLOOR), DIRT)), WHITE_CHALK
                      ),
                      SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(1, false, 12, CaveSurface.FLOOR),
                              CALCITE
                      ),
                      SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(2, true, 6, CaveSurface.FLOOR),
                              WHITE_CHALK
                      )
              )
      );


      ImmutableList.Builder <SurfaceRules.RuleSource> builder = ImmutableList.builder();
      SurfaceRules.RuleSource materialRule13 = SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), materialRule9);
      SurfaceRules.RuleSource materialRule14 = SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), materialRule10);
      SurfaceRules.RuleSource materialRule15 = SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), materialRule11);
      SurfaceRules.RuleSource materialRule16 = SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), materialRule12);
      builder.add(materialRule13);
      builder.add(materialRule14);
      builder.add(materialRule15);
      builder.add(materialRule16);
      return SurfaceRules.sequence(builder.build().toArray(SurfaceRules.RuleSource[]::new));
   }

   private static SurfaceRules.RuleSource makeStateRule(Block block) {
      return SurfaceRules.state(block.defaultBlockState());
   }
}