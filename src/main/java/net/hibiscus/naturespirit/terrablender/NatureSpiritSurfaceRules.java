package net.hibiscus.naturespirit.terrablender;

import com.google.common.collect.ImmutableList;
import net.hibiscus.naturespirit.blocks.HibiscusBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.noise.NoiseParametersKeys;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

public class NatureSpiritSurfaceRules {
   private static final MaterialRules.MaterialRule GRAVEL = makeStateRule(Blocks.GRAVEL);
   private static final MaterialRules.MaterialRule STONE = makeStateRule(Blocks.STONE);
   private static final MaterialRules.MaterialRule GRASS = makeStateRule(Blocks.GRASS_BLOCK);
   private static final MaterialRules.MaterialRule SANDY_SOIL = makeStateRule(HibiscusBlocks.SANDY_SOIL);
   private static final MaterialRules.MaterialRule COARSE_DIRT = makeStateRule(Blocks.COARSE_DIRT);
   private static final MaterialRules.MaterialRule WHITE_KAOLIN = makeStateRule(HibiscusBlocks.WHITE_KAOLIN);
   private static final MaterialRules.MaterialRule BROWN_KAOLIN = makeStateRule(HibiscusBlocks.BLUE_KAOLIN);
   private static final MaterialRules.MaterialRule LIGHT_GRAY_KAOLIN = makeStateRule(HibiscusBlocks.LIGHT_GRAY_KAOLIN);
   private static final MaterialRules.MaterialRule YELLOW_KAOLIN = makeStateRule(HibiscusBlocks.YELLOW_KAOLIN);

   protected static MaterialRules.MaterialRule makeRules() {
      MaterialRules.MaterialCondition materialCondition = MaterialRules.aboveY(YOffset.fixed(256), 0);
      MaterialRules.MaterialCondition materialCondition2 = MaterialRules.aboveYWithStoneDepth(YOffset.fixed(63), -1);
      MaterialRules.MaterialCondition materialCondition3 = MaterialRules.aboveYWithStoneDepth(YOffset.fixed(70), 1);
      MaterialRules.MaterialCondition materialCondition4 = MaterialRules.aboveY(YOffset.fixed(63), 0);
      MaterialRules.MaterialCondition materialCondition5 = MaterialRules.water(-1, 0);
      MaterialRules.MaterialCondition materialCondition6 = MaterialRules.waterWithStoneDepth(-6, -1);
      MaterialRules.MaterialCondition materialCondition7 = MaterialRules.hole();
      MaterialRules.MaterialCondition materialCondition8 = MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE,
              -0.909D,
              -0.5454D
      );
      MaterialRules.MaterialCondition materialCondition9 = MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE,
              -0.5454D,
              -0.3818D
      );
      MaterialRules.MaterialCondition materialCondition10 = MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE,
              0.5454D,
              0.909D
      );
      MaterialRules.MaterialCondition materialCondition11 = MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE,
              0.3818D,
              0.5454D
      );
      MaterialRules.MaterialCondition materialCondition12 = MaterialRules.aboveYWithStoneDepth(YOffset.fixed(76), 1);
      MaterialRules.MaterialCondition materialCondition13 = MaterialRules.waterWithStoneDepth(-6, -1);

      MaterialRules.MaterialRule materialRule2 = MaterialRules.sequence(
              MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, SANDY_SOIL),
              SANDY_SOIL
      );
      MaterialRules.MaterialRule materialRule3 = MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING,
              STONE
      ), GRAVEL);
      MaterialRules.MaterialRule materialRule9 = MaterialRules.sequence(MaterialRules.condition(MaterialRules.biome(
                      HibiscusBiomes.STRATIFIED_DESERT),
              MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, MaterialRules.sequence(
                              MaterialRules.condition(materialCondition, YELLOW_KAOLIN),
                              MaterialRules.condition(materialCondition3,
                                      MaterialRules.sequence(MaterialRules.condition(MaterialRules.not(materialCondition12),
                                                      MaterialRules.sequence(
                                                              MaterialRules.condition(materialCondition8, GRASS),
                                                              MaterialRules.condition(materialCondition9, COARSE_DIRT)
                                                      )
                                              ),
                                              MaterialRules.condition(materialCondition10, SANDY_SOIL),
                                              MaterialRules.terracottaBands()
                                      )
                              ),
                              MaterialRules.condition(materialCondition5,
                                      MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING,
                                              YELLOW_KAOLIN
                                      ), SANDY_SOIL)
                              ),
                              MaterialRules.condition(MaterialRules.not(materialCondition7), YELLOW_KAOLIN),
                              MaterialRules.condition(materialCondition6, WHITE_KAOLIN),
                              materialRule3
                      )),
                      MaterialRules.condition(materialCondition2, MaterialRules.sequence(MaterialRules.condition(
                              materialCondition4,
                              MaterialRules.condition(MaterialRules.not(materialCondition3), YELLOW_KAOLIN)
                      ), MaterialRules.terracottaBands())),
                      MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH,
                              MaterialRules.condition(materialCondition6, WHITE_KAOLIN)
                      )
              )
      ));

      MaterialRules.MaterialRule materialRule10 = MaterialRules.condition(
              MaterialRules.biome(HibiscusBiomes.BLOOMING_DUNES), MaterialRules.condition(
                      materialCondition13,
                      MaterialRules.sequence(
                              MaterialRules.condition(
                                      MaterialRules.biome(BiomeKeys.DESERT),
                                      MaterialRules.condition(
                                              MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH_RANGE_30,
                                              SANDY_SOIL
                                      )
                              ), materialRule2
                      )
              )
      );
      MaterialRules.MaterialRule materialRule11 = MaterialRules.condition(
              MaterialRules.biome(HibiscusBiomes.LIVELY_DUNES), MaterialRules.condition(
                      materialCondition13,
                      MaterialRules.sequence(
                              MaterialRules.condition(
                                      MaterialRules.biome(BiomeKeys.DESERT),
                                      MaterialRules.condition(
                                              MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH_RANGE_30,
                                              SANDY_SOIL
                                      )
                              ), materialRule2
                      )
              )
      );
      ImmutableList.Builder <MaterialRules.MaterialRule> builder = ImmutableList.builder();
      MaterialRules.MaterialRule materialRule12 = MaterialRules.condition(MaterialRules.surface(), materialRule9);
      MaterialRules.MaterialRule materialRule13 = MaterialRules.condition(MaterialRules.surface(), materialRule10);
      MaterialRules.MaterialRule materialRule14 = MaterialRules.condition(MaterialRules.surface(), materialRule11);
      builder.add(materialRule12);
      builder.add(materialRule13);
      builder.add(materialRule14);
      return MaterialRules.sequence(builder.build().toArray(MaterialRules.MaterialRule[]::new));
   }

   private static MaterialRules.MaterialRule makeStateRule(Block block) {
      return MaterialRules.block(block.getDefaultState());
   }
}