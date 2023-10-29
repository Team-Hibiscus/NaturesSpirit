package net.hibiscus.naturespirit.world;

import com.google.common.collect.ImmutableList;
import net.hibiscus.naturespirit.datagen.HibiscusBiomes;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusMiscBlocks;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusColoredBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.noise.NoiseParametersKeys;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

import static net.minecraft.world.gen.surfacebuilder.MaterialRules.*;

public class NatureSpiritSurfaceRules {
   private static final MaterialRules.MaterialRule GRAVEL = makeStateRule(Blocks.GRAVEL);

   private static final MaterialRules.MaterialRule STONE = makeStateRule(Blocks.STONE);

   private static final MaterialRules.MaterialRule GRASS = makeStateRule(Blocks.GRASS_BLOCK);

   private static final MaterialRules.MaterialRule DIRT = makeStateRule(Blocks.DIRT);
   private static final MaterialRules.MaterialRule SAND = makeStateRule(Blocks.SAND);
   private static final MaterialRules.MaterialRule SANDSTONE = makeStateRule(Blocks.SANDSTONE);

   private static final MaterialRules.MaterialRule CALCITE = makeStateRule(Blocks.CALCITE);

   private static final MaterialRules.MaterialRule SANDY_SOIL = makeStateRule(HibiscusMiscBlocks.SANDY_SOIL);

   private static final MaterialRules.MaterialRule COARSE_DIRT = makeStateRule(Blocks.COARSE_DIRT);

   private static final MaterialRules.MaterialRule WHITE_KAOLIN = makeStateRule(HibiscusColoredBlocks.WHITE_KAOLIN);

   private static final MaterialRules.MaterialRule PINK_SANDSTONE = makeStateRule(HibiscusMiscBlocks.PINK_SANDSTONE);

   private static final MaterialRules.MaterialRule WHITE_CHALK = makeStateRule(HibiscusColoredBlocks.WHITE_CHALK);

   private static final MaterialRules.MaterialRule PINK_SAND = makeStateRule(HibiscusMiscBlocks.PINK_SAND);

   private static final MaterialRules.MaterialRule YELLOW_KAOLIN = makeStateRule(HibiscusColoredBlocks.YELLOW_KAOLIN);

   public static MaterialRules.MaterialRule makeRules() {
      MaterialRules.MaterialCondition materialCondition = MaterialRules.aboveY(YOffset.fixed(256), 0);
      MaterialRules.MaterialCondition materialCondition2 = MaterialRules.aboveYWithStoneDepth(YOffset.fixed(63), -1);
      MaterialRules.MaterialCondition materialCondition3 = MaterialRules.aboveYWithStoneDepth(YOffset.fixed(70), 1);
      MaterialRules.MaterialCondition materialCondition4 = MaterialRules.aboveY(YOffset.fixed(63), 0); MaterialRules.MaterialCondition materialCondition5 = MaterialRules.water(
              -1,
              0
      ); MaterialRules.MaterialCondition materialCondition6 = MaterialRules.waterWithStoneDepth(-6, -1); MaterialRules.MaterialCondition materialCondition7 = MaterialRules.hole();
      MaterialRules.MaterialCondition materialCondition8 = MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, -0.909D, -0.5454D);
      MaterialRules.MaterialCondition materialCondition9 = MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, -0.5454D, -0.3818D);
      MaterialRules.MaterialCondition materialCondition10 = MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, 0.5454D, 0.909D);
      MaterialRules.MaterialCondition materialCondition11 = MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, -0.5454D, 0.0454D);

      MaterialRules.MaterialCondition materialCondition12 = MaterialRules.aboveYWithStoneDepth(YOffset.fixed(76), 1);
      MaterialRules.MaterialCondition materialCondition13 = MaterialRules.waterWithStoneDepth(-6, -1);

      MaterialRules.MaterialRule materialRule2 = MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, SANDY_SOIL), SANDY_SOIL);
      MaterialRules.MaterialRule materialRule3 = MaterialRules.sequence(
              MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, STONE),
              GRAVEL
      );
      MaterialRules.MaterialRule materialRule4 = MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, PINK_SANDSTONE), PINK_SAND);
      MaterialRules.MaterialRule materialRule5 = MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, PINK_SANDSTONE), SANDY_SOIL);
      MaterialRules.MaterialRule beachSandRule = MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, SANDSTONE), SAND);




      MaterialRules.MaterialRule materialRule9 = MaterialRules.sequence(MaterialRules.condition(MaterialRules.biome(HibiscusBiomes.STRATIFIED_DESERT),
              MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, MaterialRules.sequence(MaterialRules.condition(materialCondition, YELLOW_KAOLIN),
                              MaterialRules.condition(materialCondition3, MaterialRules.sequence(MaterialRules.condition(MaterialRules.not(materialCondition12),
                                      MaterialRules.sequence(MaterialRules.condition(materialCondition8, GRASS), MaterialRules.condition(materialCondition9, COARSE_DIRT))
                              ), MaterialRules.condition(materialCondition10, SANDY_SOIL), MaterialRules.terracottaBands())),
                              MaterialRules.condition(
                                      materialCondition5,
                                      MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, YELLOW_KAOLIN), SANDY_SOIL)
                              ),
                              MaterialRules.condition(MaterialRules.not(materialCondition7), YELLOW_KAOLIN),
                              MaterialRules.condition(materialCondition6, WHITE_KAOLIN),
                              materialRule3
                      )),
                      MaterialRules.condition(materialCondition2,
                              MaterialRules.sequence(MaterialRules.condition(materialCondition4, MaterialRules.condition(MaterialRules.not(materialCondition3), YELLOW_KAOLIN)),
                                      MaterialRules.terracottaBands()
                              )
                      ),
                      MaterialRules.condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, MaterialRules.condition(materialCondition6, WHITE_KAOLIN))
              )
      ));


      MaterialRules.MaterialRule materialRule10 = MaterialRules.condition(MaterialRules.biome(HibiscusBiomes.BLOOMING_DUNES),
              MaterialRules.condition(materialCondition13, MaterialRules.sequence(
                      MaterialRules.condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, materialRule2), MaterialRules.condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH_RANGE_6, SANDY_SOIL)
              ))
      );
      MaterialRules.MaterialRule materialRule11 = MaterialRules.condition(MaterialRules.biome(HibiscusBiomes.LIVELY_DUNES),
              MaterialRules.condition(materialCondition13, MaterialRules.sequence(
                      MaterialRules.condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, materialRule2), MaterialRules.condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH_RANGE_6, SANDY_SOIL)
              ))
      );
      MaterialRules.MaterialRule materialRule12 = MaterialRules.condition(MaterialRules.biome(HibiscusBiomes.WHITE_CLIFFS), MaterialRules.sequence(MaterialRules.sequence(
                      MaterialRules.condition(materialCondition4, MaterialRules.condition(MaterialRules.stoneDepth(0, false, VerticalSurfaceType.FLOOR), GRASS)),
                      WHITE_CHALK
              ),
              MaterialRules.sequence(
                      MaterialRules.condition(materialCondition4, MaterialRules.condition(MaterialRules.stoneDepth(1, false, VerticalSurfaceType.FLOOR), DIRT)),
                      WHITE_CHALK
              ),
              MaterialRules.condition(MaterialRules.stoneDepth(1, false, 12, VerticalSurfaceType.FLOOR), CALCITE),
              MaterialRules.condition(MaterialRules.stoneDepth(2, true, 6, VerticalSurfaceType.FLOOR), WHITE_CHALK)
      ));
      MaterialRules.MaterialRule materialRule17 = MaterialRules.condition(MaterialRules.biome(HibiscusBiomes.DRYLANDS),
              MaterialRules.condition(materialCondition13,
                      MaterialRules.sequence(MaterialRules.condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, materialRule4), MaterialRules.condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH_RANGE_30, PINK_SANDSTONE)
              )
              )
      );
      MaterialRules.MaterialRule materialRule19 = MaterialRules.condition(MaterialRules.biome(HibiscusBiomes.WOODED_DRYLANDS),
              MaterialRules.condition(materialCondition13,
                      MaterialRules.condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, MaterialRules.condition(materialCondition11, materialRule5))
              )
      );
      MaterialRules.MaterialRule materialRule20 = MaterialRules.condition(MaterialRules.biome(HibiscusBiomes.TROPICAL_SHORES),
              MaterialRules.condition(materialCondition13,
                      MaterialRules.sequence(MaterialRules.condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, beachSandRule), MaterialRules.condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH_RANGE_6, SANDSTONE)
                      )
              )
      );


      ImmutableList.Builder <MaterialRules.MaterialRule> builder = ImmutableList.builder();
      MaterialRules.MaterialRule materialRule13 = MaterialRules.condition(MaterialRules.surface(), materialRule9);
      MaterialRules.MaterialRule materialRule14 = MaterialRules.condition(MaterialRules.surface(), materialRule10);
      MaterialRules.MaterialRule materialRule15 = MaterialRules.condition(MaterialRules.surface(), materialRule11);
      MaterialRules.MaterialRule materialRule16 = MaterialRules.condition(MaterialRules.surface(), materialRule12);
      MaterialRules.MaterialRule materialRule18 = MaterialRules.condition(MaterialRules.surface(), materialRule17);
      MaterialRules.MaterialRule materialRule21 = MaterialRules.condition(MaterialRules.surface(), materialRule19);
      MaterialRules.MaterialRule materialRule22 = MaterialRules.condition(MaterialRules.surface(), materialRule20);
      builder.add(materialRule13);
      builder.add(materialRule14);
      builder.add(materialRule15);
      builder.add(materialRule16);
      builder.add(materialRule18);
      builder.add(materialRule21);
      builder.add(materialRule22);
      return MaterialRules.sequence(builder
              .build()
              .toArray(MaterialRules.MaterialRule[]::new));
   }

   private static MaterialRules.MaterialRule makeStateRule(Block block) {
      return MaterialRules.block(block.getDefaultState());
   }
}