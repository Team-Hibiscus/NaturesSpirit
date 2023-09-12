package net.hibiscus.naturespirit.terrablender;

import com.google.common.collect.ImmutableList;
import net.hibiscus.naturespirit.datagen.HibiscusBiomes;
import net.hibiscus.naturespirit.registration.HibiscusBlocksAndItems;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusColoredBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.noise.NoiseParametersKeys;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

import static net.minecraft.world.gen.surfacebuilder.MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH_RANGE_30;
import static net.minecraft.world.gen.surfacebuilder.MaterialRules.stoneDepth;

public class NatureSpiritSurfaceRules {
   private static final MaterialRules.MaterialRule GRAVEL = makeStateRule(Blocks.GRAVEL);
   private static final MaterialRules.MaterialRule STONE = makeStateRule(Blocks.STONE);
   private static final MaterialRules.MaterialRule GRASS = makeStateRule(Blocks.GRASS_BLOCK);
   private static final MaterialRules.MaterialRule DIRT = makeStateRule(Blocks.DIRT);
   private static final MaterialRules.MaterialRule CALCITE = makeStateRule(Blocks.CALCITE);
   private static final MaterialRules.MaterialRule SANDY_SOIL = makeStateRule(HibiscusBlocksAndItems.SANDY_SOIL);
   private static final MaterialRules.MaterialRule COARSE_DIRT = makeStateRule(Blocks.COARSE_DIRT);
   private static final MaterialRules.MaterialRule WHITE_KAOLIN = makeStateRule(HibiscusColoredBlocks.WHITE_KAOLIN);
   private static final MaterialRules.MaterialRule WHITE_TERRACOTTA = makeStateRule(Blocks.WHITE_TERRACOTTA);
   private static final MaterialRules.MaterialRule WHITE_CHALK = makeStateRule(HibiscusColoredBlocks.WHITE_CHALK);
   private static final MaterialRules.MaterialRule ORANGE_TERRACOTTA = makeStateRule(Blocks.ORANGE_TERRACOTTA);
   private static final MaterialRules.MaterialRule TERRACOTTA = makeStateRule(Blocks.TERRACOTTA);
   private static final MaterialRules.MaterialRule YELLOW_KAOLIN = makeStateRule(HibiscusColoredBlocks.YELLOW_KAOLIN);

   protected static MaterialRules.MaterialRule makeRules() {
      MaterialRules.MaterialCondition materialCondition = MaterialRules.aboveY(YOffset.fixed(256), 0);
      MaterialRules.MaterialCondition materialCondition2 = MaterialRules.aboveYWithStoneDepth(YOffset.fixed(63), -1);
      MaterialRules.MaterialCondition materialCondition3 = MaterialRules.aboveYWithStoneDepth(YOffset.fixed(70), 1);
      MaterialRules.MaterialCondition materialCondition4 = MaterialRules.aboveY(YOffset.fixed(63), 0); MaterialRules.MaterialCondition materialCondition5 = MaterialRules.water(-1, 0);
      MaterialRules.MaterialCondition materialCondition6 = MaterialRules.waterWithStoneDepth(-6, -1); MaterialRules.MaterialCondition materialCondition7 = MaterialRules.hole();
      MaterialRules.MaterialCondition materialCondition8 = MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, -0.909D, -0.5454D);
      MaterialRules.MaterialCondition materialCondition9 = MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, -0.5454D, -0.3818D);
      MaterialRules.MaterialCondition materialCondition10 = MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, 0.5454D, 0.909D);
      MaterialRules.MaterialCondition materialCondition11 = MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, 0.3818D, 0.5454D);

      MaterialRules.MaterialCondition materialCondition12 = MaterialRules.aboveYWithStoneDepth(YOffset.fixed(76), 1);
      MaterialRules.MaterialCondition materialCondition13 = MaterialRules.waterWithStoneDepth(-6, -1); MaterialRules.MaterialCondition materialCondition14 = stoneDepth(0,
              true,
              40,
              VerticalSurfaceType.FLOOR
      ); MaterialRules.MaterialCondition materialCondition15 = MaterialRules.water(0, 0);

      MaterialRules.MaterialRule materialRule = MaterialRules.sequence(MaterialRules.condition(materialCondition15, GRASS), DIRT); MaterialRules.MaterialRule materialRule2 = MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, SANDY_SOIL),
              SANDY_SOIL
      ); MaterialRules.MaterialRule materialRule3 = MaterialRules.sequence(MaterialRules.condition(
              MaterialRules.STONE_DEPTH_CEILING,
              STONE
      ), GRAVEL);

      MaterialRules.MaterialRule materialRule9 = MaterialRules.sequence(MaterialRules.condition(MaterialRules.biome(HibiscusBiomes.STRATIFIED_DESERT),
              MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, MaterialRules.sequence(MaterialRules.condition(materialCondition, YELLOW_KAOLIN),
                              MaterialRules.condition(materialCondition3, MaterialRules.sequence(MaterialRules.condition(MaterialRules.not(materialCondition12),
                                      MaterialRules.sequence(MaterialRules.condition(materialCondition8, GRASS), MaterialRules.condition(materialCondition9, COARSE_DIRT))
                              ), MaterialRules.condition(materialCondition10, SANDY_SOIL), MaterialRules.terracottaBands())),
                              MaterialRules.condition(materialCondition5, MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, YELLOW_KAOLIN), SANDY_SOIL)),
                              MaterialRules.condition(MaterialRules.not(materialCondition7), YELLOW_KAOLIN),
                              MaterialRules.condition(materialCondition6, WHITE_KAOLIN),
                              materialRule3
                      )),
                      MaterialRules.condition(materialCondition2,
                              MaterialRules.sequence(MaterialRules.condition(materialCondition4, MaterialRules.condition(MaterialRules.not(materialCondition3), YELLOW_KAOLIN)),
                                      MaterialRules.terracottaBands()
                              )
                      ),
                      MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, MaterialRules.condition(materialCondition6, WHITE_KAOLIN))
              )
      ));

      MaterialRules.MaterialRule materialRule10 = MaterialRules.condition(MaterialRules.biome(HibiscusBiomes.BLOOMING_DUNES),
              MaterialRules.condition(materialCondition13,
                      MaterialRules.sequence(MaterialRules.condition(MaterialRules.biome(BiomeKeys.DESERT), MaterialRules.condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH_RANGE_30, SANDY_SOIL)),
                              materialRule2
                      )
              )
      ); MaterialRules.MaterialRule materialRule11 = MaterialRules.condition(MaterialRules.biome(HibiscusBiomes.LIVELY_DUNES),
              MaterialRules.condition(materialCondition13,
                      MaterialRules.sequence(MaterialRules.condition(MaterialRules.biome(BiomeKeys.DESERT), MaterialRules.condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH_RANGE_30, SANDY_SOIL)),
                              materialRule2
                      )
              )
      ); MaterialRules.MaterialRule materialRule12 = MaterialRules.condition(MaterialRules.biome(HibiscusBiomes.WHITE_CLIFFS), MaterialRules.sequence(
              MaterialRules.sequence(MaterialRules.condition(materialCondition4, MaterialRules.condition(MaterialRules.stoneDepth(0, false, VerticalSurfaceType.FLOOR), GRASS)), WHITE_CHALK),
              MaterialRules.sequence(MaterialRules.condition(materialCondition4, MaterialRules.condition(MaterialRules.stoneDepth(1, false, VerticalSurfaceType.FLOOR), DIRT)), WHITE_CHALK),
              MaterialRules.condition(MaterialRules.stoneDepth(1, false, 12, VerticalSurfaceType.FLOOR), CALCITE),
              MaterialRules.condition(MaterialRules.stoneDepth(2, true, 6, VerticalSurfaceType.FLOOR), WHITE_CHALK)
      ));



      ImmutableList.Builder <MaterialRules.MaterialRule> builder = ImmutableList.builder(); MaterialRules.MaterialRule materialRule13 = MaterialRules.condition(MaterialRules.surface(), materialRule9);
      MaterialRules.MaterialRule materialRule14 = MaterialRules.condition(MaterialRules.surface(), materialRule10);
      MaterialRules.MaterialRule materialRule15 = MaterialRules.condition(MaterialRules.surface(), materialRule11);
      MaterialRules.MaterialRule materialRule16 = MaterialRules.condition(MaterialRules.surface(), materialRule12);
      builder.add(materialRule13);
      builder.add(materialRule14);
      builder.add(materialRule15);
      builder.add(materialRule16);
      return MaterialRules.sequence(builder.build().toArray(MaterialRules.MaterialRule[]::new));
   }

   private static MaterialRules.MaterialRule makeStateRule(Block block) {
      return MaterialRules.block(block.getDefaultState());
   }
}