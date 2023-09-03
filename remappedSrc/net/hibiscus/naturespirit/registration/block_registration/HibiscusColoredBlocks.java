package net.hibiscus.naturespirit.registration.block_registration;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.hibiscus.naturespirit.registration.HibiscusItemGroups;
import net.minecraft.block.*;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

import static net.hibiscus.naturespirit.util.HibiscusRegistryHelper.registerBlock;

public class HibiscusColoredBlocks {

   public static final Block KAOLIN = registerBlock("kaolin",
           new Block(FabricBlockSettings.of()
                   .mapColor(MapColor.COLOR_ORANGE)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           Blocks.PINK_TERRACOTTA,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block WHITE_KAOLIN = registerBlock("white_kaolin",
           new Block(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_WHITE)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, KAOLIN,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block LIGHT_GRAY_KAOLIN = registerBlock("light_gray_kaolin",
           new Block(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, WHITE_KAOLIN,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block GRAY_KAOLIN = registerBlock("gray_kaolin",
           new Block(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_GRAY)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, LIGHT_GRAY_KAOLIN,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block BLACK_KAOLIN = registerBlock("black_kaolin",
           new Block(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_BLACK)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, GRAY_KAOLIN,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block BROWN_KAOLIN = registerBlock("brown_kaolin",
           new Block(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_BROWN)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, BLACK_KAOLIN,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block RED_KAOLIN = registerBlock("red_kaolin",
           new Block(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_RED)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, BROWN_KAOLIN,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block ORANGE_KAOLIN = registerBlock("orange_kaolin",
           new Block(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_ORANGE)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, RED_KAOLIN,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block YELLOW_KAOLIN = registerBlock("yellow_kaolin",
           new Block(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_YELLOW)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, ORANGE_KAOLIN,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block LIME_KAOLIN = registerBlock("lime_kaolin",
           new Block(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_LIGHT_GREEN)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, YELLOW_KAOLIN,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block GREEN_KAOLIN = registerBlock("green_kaolin",
           new Block(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_GREEN)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, LIME_KAOLIN,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block CYAN_KAOLIN = registerBlock("cyan_kaolin",
           new Block(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_CYAN)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, GREEN_KAOLIN,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block LIGHT_BLUE_KAOLIN = registerBlock("light_blue_kaolin",
           new Block(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_LIGHT_BLUE)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, CYAN_KAOLIN,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block BLUE_KAOLIN = registerBlock("blue_kaolin",
           new Block(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_BLUE)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, LIGHT_BLUE_KAOLIN,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block PURPLE_KAOLIN = registerBlock("purple_kaolin",
           new Block(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_PURPLE)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, BLUE_KAOLIN,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block MAGENTA_KAOLIN = registerBlock("magenta_kaolin",
           new Block(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_MAGENTA)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, PURPLE_KAOLIN,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block PINK_KAOLIN = registerBlock("pink_kaolin",
           new Block(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_PINK)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, MAGENTA_KAOLIN,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block KAOLIN_STAIRS = registerBlock("kaolin_stairs",
           new StairBlock(KAOLIN.defaultBlockState(), FabricBlockSettings.of().mapColor(MapColor.COLOR_ORANGE).instrument(
                   NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, PINK_KAOLIN,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block WHITE_KAOLIN_STAIRS = registerBlock("white_kaolin_stairs",
           new StairBlock(WHITE_KAOLIN.defaultBlockState(),
                   FabricBlockSettings.of()
                           .mapColor(MapColor.TERRACOTTA_WHITE)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, KAOLIN_STAIRS,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block LIGHT_GRAY_KAOLIN_STAIRS = registerBlock("light_gray_kaolin_stairs",
           new StairBlock(LIGHT_GRAY_KAOLIN.defaultBlockState(),
                   FabricBlockSettings.of()
                           .mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, WHITE_KAOLIN_STAIRS,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block GRAY_KAOLIN_STAIRS = registerBlock("gray_kaolin_stairs",
           new StairBlock(GRAY_KAOLIN.defaultBlockState(),
                   FabricBlockSettings.of()
                           .mapColor(MapColor.TERRACOTTA_GRAY)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, LIGHT_GRAY_KAOLIN_STAIRS,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block BLACK_KAOLIN_STAIRS = registerBlock("black_kaolin_stairs",
           new StairBlock(BLACK_KAOLIN.defaultBlockState(),
                   FabricBlockSettings.of()
                           .mapColor(MapColor.TERRACOTTA_BLACK)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, GRAY_KAOLIN_STAIRS,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block BROWN_KAOLIN_STAIRS = registerBlock("brown_kaolin_stairs",
           new StairBlock(BROWN_KAOLIN.defaultBlockState(),
                   FabricBlockSettings.of()
                           .mapColor(MapColor.TERRACOTTA_BROWN)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, BLACK_KAOLIN_STAIRS,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block RED_KAOLIN_STAIRS = registerBlock("red_kaolin_stairs",
           new StairBlock(RED_KAOLIN.defaultBlockState(),
                   FabricBlockSettings.of()
                           .mapColor(MapColor.TERRACOTTA_RED)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, BROWN_KAOLIN_STAIRS,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block ORANGE_KAOLIN_STAIRS = registerBlock("orange_kaolin_stairs",
           new StairBlock(ORANGE_KAOLIN.defaultBlockState(),
                   FabricBlockSettings.of()
                           .mapColor(MapColor.TERRACOTTA_ORANGE)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, RED_KAOLIN_STAIRS,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block YELLOW_KAOLIN_STAIRS = registerBlock("yellow_kaolin_stairs",
           new StairBlock(YELLOW_KAOLIN.defaultBlockState(),
                   FabricBlockSettings.of()
                           .mapColor(MapColor.TERRACOTTA_YELLOW)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, ORANGE_KAOLIN_STAIRS,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block LIME_KAOLIN_STAIRS = registerBlock("lime_kaolin_stairs",
           new StairBlock(LIME_KAOLIN.defaultBlockState(),
                   FabricBlockSettings.of()
                           .mapColor(MapColor.TERRACOTTA_LIGHT_GREEN)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, YELLOW_KAOLIN_STAIRS,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block GREEN_KAOLIN_STAIRS = registerBlock("green_kaolin_stairs",
           new StairBlock(GREEN_KAOLIN.defaultBlockState(),
                   FabricBlockSettings.of()
                           .mapColor(MapColor.TERRACOTTA_GREEN)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, LIME_KAOLIN_STAIRS,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block CYAN_KAOLIN_STAIRS = registerBlock("cyan_kaolin_stairs",
           new StairBlock(CYAN_KAOLIN.defaultBlockState(),
                   FabricBlockSettings.of()
                           .mapColor(MapColor.TERRACOTTA_CYAN)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, GREEN_KAOLIN_STAIRS,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block LIGHT_BLUE_KAOLIN_STAIRS = registerBlock("light_blue_kaolin_stairs",
           new StairBlock(LIGHT_BLUE_KAOLIN.defaultBlockState(),
                   FabricBlockSettings.of()
                           .mapColor(MapColor.TERRACOTTA_LIGHT_BLUE)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, CYAN_KAOLIN_STAIRS,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block BLUE_KAOLIN_STAIRS = registerBlock("blue_kaolin_stairs",
           new StairBlock(BLUE_KAOLIN.defaultBlockState(),
                   FabricBlockSettings.of()
                           .mapColor(MapColor.TERRACOTTA_BLUE)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, LIGHT_BLUE_KAOLIN_STAIRS,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block PURPLE_KAOLIN_STAIRS = registerBlock("purple_kaolin_stairs",
           new StairBlock(PURPLE_KAOLIN.defaultBlockState(),
                   FabricBlockSettings.of()
                           .mapColor(MapColor.TERRACOTTA_PURPLE)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, BLUE_KAOLIN_STAIRS,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block MAGENTA_KAOLIN_STAIRS = registerBlock("magenta_kaolin_stairs",
           new StairBlock(MAGENTA_KAOLIN.defaultBlockState(),
                   FabricBlockSettings.of()
                           .mapColor(MapColor.TERRACOTTA_MAGENTA)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, PURPLE_KAOLIN_STAIRS,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block PINK_KAOLIN_STAIRS = registerBlock("pink_kaolin_stairs",
           new StairBlock(PINK_KAOLIN.defaultBlockState(),
                   FabricBlockSettings.of()
                           .mapColor(MapColor.TERRACOTTA_PINK)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, MAGENTA_KAOLIN_STAIRS,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block KAOLIN_SLAB = registerBlock("kaolin_slab",
           new SlabBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.COLOR_ORANGE)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, PINK_KAOLIN_STAIRS,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block WHITE_KAOLIN_SLAB = registerBlock("white_kaolin_slab",
           new SlabBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_WHITE)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, KAOLIN_SLAB,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block LIGHT_GRAY_KAOLIN_SLAB = registerBlock("light_gray_kaolin_slab",
           new SlabBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, WHITE_KAOLIN_SLAB,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block GRAY_KAOLIN_SLAB = registerBlock("gray_kaolin_slab",
           new SlabBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_GRAY)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, LIGHT_GRAY_KAOLIN_SLAB,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block BLACK_KAOLIN_SLAB = registerBlock("black_kaolin_slab",
           new SlabBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_BLACK)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, GRAY_KAOLIN_SLAB,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block BROWN_KAOLIN_SLAB = registerBlock("brown_kaolin_slab",
           new SlabBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_BROWN)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, BLACK_KAOLIN_SLAB,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block RED_KAOLIN_SLAB = registerBlock("red_kaolin_slab",
           new SlabBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_RED)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, BROWN_KAOLIN_SLAB,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block ORANGE_KAOLIN_SLAB = registerBlock("orange_kaolin_slab",
           new SlabBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_ORANGE)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, RED_KAOLIN_SLAB,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block YELLOW_KAOLIN_SLAB = registerBlock("yellow_kaolin_slab",
           new SlabBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_YELLOW)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, ORANGE_KAOLIN_SLAB,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block LIME_KAOLIN_SLAB = registerBlock("lime_kaolin_slab",
           new SlabBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_LIGHT_GREEN)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, YELLOW_KAOLIN_SLAB,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block GREEN_KAOLIN_SLAB = registerBlock("green_kaolin_slab",
           new SlabBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_GREEN)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, LIME_KAOLIN_SLAB,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block CYAN_KAOLIN_SLAB = registerBlock("cyan_kaolin_slab",
           new SlabBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_CYAN)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, GREEN_KAOLIN_SLAB,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block LIGHT_BLUE_KAOLIN_SLAB = registerBlock("light_blue_kaolin_slab",
           new SlabBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_LIGHT_BLUE)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, CYAN_KAOLIN_SLAB,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block BLUE_KAOLIN_SLAB = registerBlock("blue_kaolin_slab",
           new SlabBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_BLUE)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, LIGHT_BLUE_KAOLIN_SLAB,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block PURPLE_KAOLIN_SLAB = registerBlock("purple_kaolin_slab",
           new SlabBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_PURPLE)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, BLUE_KAOLIN_SLAB,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block MAGENTA_KAOLIN_SLAB = registerBlock("magenta_kaolin_slab",
           new SlabBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_MAGENTA)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, PURPLE_KAOLIN_SLAB,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block PINK_KAOLIN_SLAB = registerBlock("pink_kaolin_slab",
           new SlabBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_PINK)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, MAGENTA_KAOLIN_SLAB,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block WHITE_CHALK = registerBlock("white_chalk",
           new Block(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_WHITE)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, PINK_KAOLIN_SLAB,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block LIGHT_GRAY_CHALK = registerBlock("light_gray_chalk",
           new Block(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, WHITE_CHALK,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block GRAY_CHALK = registerBlock("gray_chalk",
           new Block(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_GRAY)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, LIGHT_GRAY_CHALK,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block BLACK_CHALK = registerBlock("black_chalk",
           new Block(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_BLACK)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, GRAY_CHALK,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block BROWN_CHALK = registerBlock("brown_chalk",
           new Block(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_BROWN)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, BLACK_CHALK,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block RED_CHALK = registerBlock("red_chalk",
           new Block(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_RED)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, BROWN_CHALK,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block ORANGE_CHALK = registerBlock("orange_chalk",
           new Block(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_ORANGE)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, RED_CHALK,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block YELLOW_CHALK = registerBlock("yellow_chalk",
           new Block(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_YELLOW)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, ORANGE_CHALK,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block LIME_CHALK = registerBlock("lime_chalk",
           new Block(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_LIGHT_GREEN)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, YELLOW_CHALK,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block GREEN_CHALK = registerBlock("green_chalk",
           new Block(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_GREEN)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, LIME_CHALK,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block CYAN_CHALK = registerBlock("cyan_chalk",
           new Block(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_CYAN)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, GREEN_CHALK,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block LIGHT_BLUE_CHALK = registerBlock("light_blue_chalk",
           new Block(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_LIGHT_BLUE)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, CYAN_CHALK,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block BLUE_CHALK = registerBlock("blue_chalk",
           new Block(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_BLUE)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, LIGHT_BLUE_CHALK,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block PURPLE_CHALK = registerBlock("purple_chalk",
           new Block(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_PURPLE)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, BLUE_CHALK,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block MAGENTA_CHALK = registerBlock("magenta_chalk",
           new Block(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_MAGENTA)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, PURPLE_CHALK,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block PINK_CHALK = registerBlock("pink_chalk",
           new Block(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_PINK)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, MAGENTA_CHALK,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block WHITE_CHALK_STAIRS = registerBlock("white_chalk_stairs",
           new StairBlock(WHITE_CHALK.defaultBlockState(),
                   FabricBlockSettings.of()
                           .mapColor(MapColor.TERRACOTTA_WHITE)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, PINK_CHALK,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block LIGHT_GRAY_CHALK_STAIRS = registerBlock("light_gray_chalk_stairs",
           new StairBlock(LIGHT_GRAY_CHALK.defaultBlockState(),
                   FabricBlockSettings.of()
                           .mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, WHITE_CHALK_STAIRS,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block GRAY_CHALK_STAIRS = registerBlock("gray_chalk_stairs",
           new StairBlock(GRAY_CHALK.defaultBlockState(),
                   FabricBlockSettings.of()
                           .mapColor(MapColor.TERRACOTTA_GRAY)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, LIGHT_GRAY_CHALK_STAIRS,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block BLACK_CHALK_STAIRS = registerBlock("black_chalk_stairs",
           new StairBlock(BLACK_CHALK.defaultBlockState(),
                   FabricBlockSettings.of()
                           .mapColor(MapColor.TERRACOTTA_BLACK)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, GRAY_CHALK_STAIRS,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block BROWN_CHALK_STAIRS = registerBlock("brown_chalk_stairs",
           new StairBlock(BROWN_CHALK.defaultBlockState(),
                   FabricBlockSettings.of()
                           .mapColor(MapColor.TERRACOTTA_BROWN)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, BLACK_CHALK_STAIRS,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block RED_CHALK_STAIRS = registerBlock("red_chalk_stairs",
           new StairBlock(RED_CHALK.defaultBlockState(),
                   FabricBlockSettings.of()
                           .mapColor(MapColor.TERRACOTTA_RED)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, BROWN_CHALK_STAIRS,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block ORANGE_CHALK_STAIRS = registerBlock("orange_chalk_stairs",
           new StairBlock(ORANGE_CHALK.defaultBlockState(),
                   FabricBlockSettings.of()
                           .mapColor(MapColor.TERRACOTTA_ORANGE)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, RED_CHALK_STAIRS,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block YELLOW_CHALK_STAIRS = registerBlock("yellow_chalk_stairs",
           new StairBlock(YELLOW_CHALK.defaultBlockState(),
                   FabricBlockSettings.of()
                           .mapColor(MapColor.TERRACOTTA_YELLOW)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, ORANGE_CHALK_STAIRS,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block LIME_CHALK_STAIRS = registerBlock("lime_chalk_stairs",
           new StairBlock(LIME_CHALK.defaultBlockState(),
                   FabricBlockSettings.of()
                           .mapColor(MapColor.TERRACOTTA_LIGHT_GREEN)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, YELLOW_CHALK_STAIRS,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block GREEN_CHALK_STAIRS = registerBlock("green_chalk_stairs",
           new StairBlock(GREEN_CHALK.defaultBlockState(),
                   FabricBlockSettings.of()
                           .mapColor(MapColor.TERRACOTTA_GREEN)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, LIME_CHALK_STAIRS,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block CYAN_CHALK_STAIRS = registerBlock("cyan_chalk_stairs",
           new StairBlock(CYAN_CHALK.defaultBlockState(),
                   FabricBlockSettings.of()
                           .mapColor(MapColor.TERRACOTTA_CYAN)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, GREEN_CHALK_STAIRS,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block LIGHT_BLUE_CHALK_STAIRS = registerBlock("light_blue_chalk_stairs",
           new StairBlock(LIGHT_BLUE_CHALK.defaultBlockState(),
                   FabricBlockSettings.of()
                           .mapColor(MapColor.TERRACOTTA_LIGHT_BLUE)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, CYAN_CHALK_STAIRS,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block BLUE_CHALK_STAIRS = registerBlock("blue_chalk_stairs",
           new StairBlock(BLUE_CHALK.defaultBlockState(),
                   FabricBlockSettings.of()
                           .mapColor(MapColor.TERRACOTTA_BLUE)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, LIGHT_BLUE_CHALK_STAIRS,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block PURPLE_CHALK_STAIRS = registerBlock("purple_chalk_stairs",
           new StairBlock(PURPLE_CHALK.defaultBlockState(),
                   FabricBlockSettings.of()
                           .mapColor(MapColor.TERRACOTTA_PURPLE)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, BLUE_CHALK_STAIRS,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block MAGENTA_CHALK_STAIRS = registerBlock("magenta_chalk_stairs",
           new StairBlock(MAGENTA_CHALK.defaultBlockState(),
                   FabricBlockSettings.of()
                           .mapColor(MapColor.TERRACOTTA_MAGENTA)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, PURPLE_CHALK_STAIRS,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block PINK_CHALK_STAIRS = registerBlock("pink_chalk_stairs",
           new StairBlock(PINK_CHALK.defaultBlockState(),
                   FabricBlockSettings.of()
                           .mapColor(MapColor.TERRACOTTA_PINK)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, MAGENTA_CHALK_STAIRS,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block WHITE_CHALK_SLAB = registerBlock("white_chalk_slab",
           new SlabBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_WHITE)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, PINK_CHALK_STAIRS,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block LIGHT_GRAY_CHALK_SLAB = registerBlock("light_gray_chalk_slab",
           new SlabBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, WHITE_CHALK_SLAB,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block GRAY_CHALK_SLAB = registerBlock("gray_chalk_slab",
           new SlabBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_GRAY)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, LIGHT_GRAY_CHALK_SLAB,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block BLACK_CHALK_SLAB = registerBlock("black_chalk_slab",
           new SlabBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_BLACK)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, GRAY_CHALK_SLAB,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block BROWN_CHALK_SLAB = registerBlock("brown_chalk_slab",
           new SlabBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_BROWN)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, BLACK_CHALK_SLAB,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block RED_CHALK_SLAB = registerBlock("red_chalk_slab",
           new SlabBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_RED)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, BROWN_CHALK_SLAB,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block ORANGE_CHALK_SLAB = registerBlock("orange_chalk_slab",
           new SlabBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_ORANGE)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, RED_CHALK_SLAB,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block YELLOW_CHALK_SLAB = registerBlock("yellow_chalk_slab",
           new SlabBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_YELLOW)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, ORANGE_CHALK_SLAB,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block LIME_CHALK_SLAB = registerBlock("lime_chalk_slab",
           new SlabBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_LIGHT_GREEN)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, YELLOW_CHALK_SLAB,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block GREEN_CHALK_SLAB = registerBlock("green_chalk_slab",
           new SlabBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_GREEN)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, LIME_CHALK_SLAB,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block CYAN_CHALK_SLAB = registerBlock("cyan_chalk_slab",
           new SlabBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_CYAN)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, GREEN_CHALK_SLAB,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block LIGHT_BLUE_CHALK_SLAB = registerBlock("light_blue_chalk_slab",
           new SlabBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_LIGHT_BLUE)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, CYAN_CHALK_SLAB,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block BLUE_CHALK_SLAB = registerBlock("blue_chalk_slab",
           new SlabBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_BLUE)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, LIGHT_BLUE_CHALK_SLAB,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block PURPLE_CHALK_SLAB = registerBlock("purple_chalk_slab",
           new SlabBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_PURPLE)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, BLUE_CHALK_SLAB,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block MAGENTA_CHALK_SLAB = registerBlock("magenta_chalk_slab",
           new SlabBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_MAGENTA)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, PURPLE_CHALK_SLAB,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static final Block PINK_CHALK_SLAB = registerBlock("pink_chalk_slab",
           new SlabBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_PINK)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, MAGENTA_CHALK_SLAB,
           CreativeModeTabs.COLORED_BLOCKS
   );
   public static void registerColoredBlocks() {}
}
