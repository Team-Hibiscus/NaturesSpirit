package net.hibiscus.naturespirit.registration.block_registration;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.hibiscus.naturespirit.blocks.PaperLanternBlock;
import net.hibiscus.naturespirit.registration.HibiscusItemGroups;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.sound.BlockSoundGroup;

import static net.hibiscus.naturespirit.registration.HibiscusRegistryHelper.registerBlock;
import static net.hibiscus.naturespirit.registration.HibiscusRegistryHelper.registerPaperLanternBlock;

public class HibiscusColoredBlocks {

   public static final Block PAPER_LANTERN = registerPaperLanternBlock("paper_lantern", new PaperLanternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OFF_WHITE).solid().strength(0.5F).sounds(
                   BlockSoundGroup.LANTERN).luminance((state) -> 15).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)), HibiscusItemGroups.NS_MISC_ITEM_GROUP, Items.PINK_CANDLE, ItemGroups.COLORED_BLOCKS, ItemGroups.FUNCTIONAL,
           Items.SOUL_LANTERN);
   public static final Block WHITE_PAPER_LANTERN = registerPaperLanternBlock("white_paper_lantern", new PaperLanternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OFF_WHITE).solid().strength(0.5F).sounds(
           BlockSoundGroup.LANTERN).luminance((state) -> 15).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)), HibiscusItemGroups.NS_MISC_ITEM_GROUP, PAPER_LANTERN, ItemGroups.COLORED_BLOCKS, ItemGroups.FUNCTIONAL);
   public static final Block LIGHT_GRAY_PAPER_LANTERN = registerPaperLanternBlock("light_gray_paper_lantern", new PaperLanternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OFF_WHITE).solid().strength(0.5F).sounds(
           BlockSoundGroup.LANTERN).luminance((state) -> 15).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)), HibiscusItemGroups.NS_MISC_ITEM_GROUP, WHITE_PAPER_LANTERN, ItemGroups.COLORED_BLOCKS, ItemGroups.FUNCTIONAL);
   public static final Block GRAY_PAPER_LANTERN = registerPaperLanternBlock("gray_paper_lantern", new PaperLanternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OFF_WHITE).solid().strength(0.5F).sounds(
           BlockSoundGroup.LANTERN).luminance((state) -> 15).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)), HibiscusItemGroups.NS_MISC_ITEM_GROUP, LIGHT_GRAY_PAPER_LANTERN, ItemGroups.COLORED_BLOCKS, ItemGroups.FUNCTIONAL);
   public static final Block BLACK_PAPER_LANTERN = registerPaperLanternBlock("black_paper_lantern", new PaperLanternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OFF_WHITE).solid().strength(0.5F).sounds(
           BlockSoundGroup.LANTERN).luminance((state) -> 15).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)), HibiscusItemGroups.NS_MISC_ITEM_GROUP, GRAY_PAPER_LANTERN, ItemGroups.COLORED_BLOCKS, ItemGroups.FUNCTIONAL);
   public static final Block BROWN_PAPER_LANTERN = registerPaperLanternBlock("brown_paper_lantern", new PaperLanternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OFF_WHITE).solid().strength(0.5F).sounds(
           BlockSoundGroup.LANTERN).luminance((state) -> 15).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)), HibiscusItemGroups.NS_MISC_ITEM_GROUP, BLACK_PAPER_LANTERN, ItemGroups.COLORED_BLOCKS, ItemGroups.FUNCTIONAL);
   public static final Block RED_PAPER_LANTERN = registerPaperLanternBlock("red_paper_lantern", new PaperLanternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OFF_WHITE).solid().strength(0.5F).sounds(
           BlockSoundGroup.LANTERN).luminance((state) -> 15).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)), HibiscusItemGroups.NS_MISC_ITEM_GROUP, BROWN_PAPER_LANTERN, ItemGroups.COLORED_BLOCKS, ItemGroups.FUNCTIONAL);
   public static final Block ORANGE_PAPER_LANTERN = registerPaperLanternBlock("orange_paper_lantern", new PaperLanternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OFF_WHITE).solid().strength(0.5F).sounds(
           BlockSoundGroup.LANTERN).luminance((state) -> 15).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)), HibiscusItemGroups.NS_MISC_ITEM_GROUP, RED_PAPER_LANTERN, ItemGroups.COLORED_BLOCKS, ItemGroups.FUNCTIONAL);
   public static final Block YELLOW_PAPER_LANTERN = registerPaperLanternBlock("yellow_paper_lantern", new PaperLanternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OFF_WHITE).solid().strength(0.5F).sounds(
           BlockSoundGroup.LANTERN).luminance((state) -> 15).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)), HibiscusItemGroups.NS_MISC_ITEM_GROUP, ORANGE_PAPER_LANTERN, ItemGroups.COLORED_BLOCKS, ItemGroups.FUNCTIONAL);
   public static final Block LIME_PAPER_LANTERN = registerPaperLanternBlock("lime_paper_lantern", new PaperLanternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OFF_WHITE).solid().strength(0.5F).sounds(
           BlockSoundGroup.LANTERN).luminance((state) -> 15).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)), HibiscusItemGroups.NS_MISC_ITEM_GROUP, YELLOW_PAPER_LANTERN, ItemGroups.COLORED_BLOCKS, ItemGroups.FUNCTIONAL);
   public static final Block GREEN_PAPER_LANTERN = registerPaperLanternBlock("green_paper_lantern", new PaperLanternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OFF_WHITE).solid().strength(0.5F).sounds(
           BlockSoundGroup.LANTERN).luminance((state) -> 15).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)), HibiscusItemGroups.NS_MISC_ITEM_GROUP, LIME_PAPER_LANTERN, ItemGroups.COLORED_BLOCKS, ItemGroups.FUNCTIONAL);
   public static final Block BLUE_PAPER_LANTERN = registerPaperLanternBlock("blue_paper_lantern", new PaperLanternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OFF_WHITE).solid().strength(0.5F).sounds(
           BlockSoundGroup.LANTERN).luminance((state) -> 15).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)), HibiscusItemGroups.NS_MISC_ITEM_GROUP, GREEN_PAPER_LANTERN, ItemGroups.COLORED_BLOCKS, ItemGroups.FUNCTIONAL);
   public static final Block LIGHT_BLUE_PAPER_LANTERN = registerPaperLanternBlock("light_blue_paper_lantern", new PaperLanternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OFF_WHITE).solid().strength(0.5F).sounds(
           BlockSoundGroup.LANTERN).luminance((state) -> 15).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)), HibiscusItemGroups.NS_MISC_ITEM_GROUP, BLUE_PAPER_LANTERN, ItemGroups.COLORED_BLOCKS, ItemGroups.FUNCTIONAL);
   public static final Block CYAN_PAPER_LANTERN = registerPaperLanternBlock("cyan_paper_lantern", new PaperLanternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OFF_WHITE).solid().strength(0.5F).sounds(
           BlockSoundGroup.LANTERN).luminance((state) -> 15).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)), HibiscusItemGroups.NS_MISC_ITEM_GROUP, LIGHT_BLUE_PAPER_LANTERN, ItemGroups.COLORED_BLOCKS, ItemGroups.FUNCTIONAL);
   public static final Block PURPLE_PAPER_LANTERN = registerPaperLanternBlock("purple_paper_lantern", new PaperLanternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OFF_WHITE).solid().strength(0.5F).sounds(
           BlockSoundGroup.LANTERN).luminance((state) -> 15).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)), HibiscusItemGroups.NS_MISC_ITEM_GROUP, CYAN_PAPER_LANTERN, ItemGroups.COLORED_BLOCKS, ItemGroups.FUNCTIONAL);
   public static final Block MAGENTA_PAPER_LANTERN = registerPaperLanternBlock("magenta_paper_lantern", new PaperLanternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OFF_WHITE).solid().strength(0.5F).sounds(
           BlockSoundGroup.LANTERN).luminance((state) -> 15).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)), HibiscusItemGroups.NS_MISC_ITEM_GROUP, PURPLE_PAPER_LANTERN, ItemGroups.COLORED_BLOCKS, ItemGroups.FUNCTIONAL);
   public static final Block PINK_PAPER_LANTERN = registerPaperLanternBlock("pink_paper_lantern", new PaperLanternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OFF_WHITE).solid().strength(0.5F).sounds(
           BlockSoundGroup.LANTERN).luminance((state) -> 15).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)), HibiscusItemGroups.NS_MISC_ITEM_GROUP, MAGENTA_PAPER_LANTERN, ItemGroups.COLORED_BLOCKS, ItemGroups.FUNCTIONAL);

   public static final Block KAOLIN = registerBlock(
           "kaolin",
           new Block(FabricBlockSettings.create().mapColor(MapColor.ORANGE).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           Blocks.PINK_TERRACOTTA,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block WHITE_KAOLIN = registerBlock(
           "white_kaolin",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_WHITE).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           KAOLIN,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block LIGHT_GRAY_KAOLIN = registerBlock(
           "light_gray_kaolin",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           WHITE_KAOLIN,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block GRAY_KAOLIN = registerBlock(
           "gray_kaolin",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_GRAY).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           LIGHT_GRAY_KAOLIN,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block BLACK_KAOLIN = registerBlock(
           "black_kaolin",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_BLACK).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           GRAY_KAOLIN,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block BROWN_KAOLIN = registerBlock(
           "brown_kaolin",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_BROWN).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           BLACK_KAOLIN,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block RED_KAOLIN = registerBlock(
           "red_kaolin",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_RED).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           BROWN_KAOLIN,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block ORANGE_KAOLIN = registerBlock(
           "orange_kaolin",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           RED_KAOLIN,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block YELLOW_KAOLIN = registerBlock(
           "yellow_kaolin",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           ORANGE_KAOLIN,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block LIME_KAOLIN = registerBlock(
           "lime_kaolin",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_LIME).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           YELLOW_KAOLIN,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block GREEN_KAOLIN = registerBlock(
           "green_kaolin",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_GREEN).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           LIME_KAOLIN,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block CYAN_KAOLIN = registerBlock(
           "cyan_kaolin",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_CYAN).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           GREEN_KAOLIN,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block LIGHT_BLUE_KAOLIN = registerBlock(
           "light_blue_kaolin",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           CYAN_KAOLIN,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block BLUE_KAOLIN = registerBlock(
           "blue_kaolin",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_BLUE).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           LIGHT_BLUE_KAOLIN,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block PURPLE_KAOLIN = registerBlock(
           "purple_kaolin",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           BLUE_KAOLIN,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block MAGENTA_KAOLIN = registerBlock(
           "magenta_kaolin",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           PURPLE_KAOLIN,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block PINK_KAOLIN = registerBlock(
           "pink_kaolin",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_PINK).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           MAGENTA_KAOLIN,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block KAOLIN_STAIRS = registerBlock(
           "kaolin_stairs",
           new StairsBlock(KAOLIN.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.ORANGE).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           PINK_KAOLIN,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block WHITE_KAOLIN_STAIRS = registerBlock(
           "white_kaolin_stairs",
           new StairsBlock(WHITE_KAOLIN.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_WHITE).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           KAOLIN_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block LIGHT_GRAY_KAOLIN_STAIRS = registerBlock(
           "light_gray_kaolin_stairs",
           new StairsBlock(LIGHT_GRAY_KAOLIN.getDefaultState(),
                   FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           WHITE_KAOLIN_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block GRAY_KAOLIN_STAIRS = registerBlock(
           "gray_kaolin_stairs",
           new StairsBlock(GRAY_KAOLIN.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_GRAY).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           LIGHT_GRAY_KAOLIN_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block BLACK_KAOLIN_STAIRS = registerBlock(
           "black_kaolin_stairs",
           new StairsBlock(BLACK_KAOLIN.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_BLACK).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           GRAY_KAOLIN_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block BROWN_KAOLIN_STAIRS = registerBlock(
           "brown_kaolin_stairs",
           new StairsBlock(BROWN_KAOLIN.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_BROWN).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           BLACK_KAOLIN_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block RED_KAOLIN_STAIRS = registerBlock(
           "red_kaolin_stairs",
           new StairsBlock(RED_KAOLIN.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_RED).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           BROWN_KAOLIN_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block ORANGE_KAOLIN_STAIRS = registerBlock(
           "orange_kaolin_stairs",
           new StairsBlock(ORANGE_KAOLIN.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           RED_KAOLIN_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block YELLOW_KAOLIN_STAIRS = registerBlock(
           "yellow_kaolin_stairs",
           new StairsBlock(YELLOW_KAOLIN.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           ORANGE_KAOLIN_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block LIME_KAOLIN_STAIRS = registerBlock(
           "lime_kaolin_stairs",
           new StairsBlock(LIME_KAOLIN.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_LIME).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           YELLOW_KAOLIN_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block GREEN_KAOLIN_STAIRS = registerBlock(
           "green_kaolin_stairs",
           new StairsBlock(GREEN_KAOLIN.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_GREEN).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           LIME_KAOLIN_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block CYAN_KAOLIN_STAIRS = registerBlock(
           "cyan_kaolin_stairs",
           new StairsBlock(CYAN_KAOLIN.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_CYAN).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           GREEN_KAOLIN_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block LIGHT_BLUE_KAOLIN_STAIRS = registerBlock(
           "light_blue_kaolin_stairs",
           new StairsBlock(LIGHT_BLUE_KAOLIN.getDefaultState(),
                   FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           CYAN_KAOLIN_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block BLUE_KAOLIN_STAIRS = registerBlock(
           "blue_kaolin_stairs",
           new StairsBlock(BLUE_KAOLIN.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_BLUE).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           LIGHT_BLUE_KAOLIN_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block PURPLE_KAOLIN_STAIRS = registerBlock(
           "purple_kaolin_stairs",
           new StairsBlock(PURPLE_KAOLIN.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           BLUE_KAOLIN_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block MAGENTA_KAOLIN_STAIRS = registerBlock(
           "magenta_kaolin_stairs",
           new StairsBlock(MAGENTA_KAOLIN.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           PURPLE_KAOLIN_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block PINK_KAOLIN_STAIRS = registerBlock(
           "pink_kaolin_stairs",
           new StairsBlock(PINK_KAOLIN.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_PINK).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           MAGENTA_KAOLIN_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block KAOLIN_SLAB = registerBlock(
           "kaolin_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.ORANGE).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           PINK_KAOLIN_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block WHITE_KAOLIN_SLAB = registerBlock(
           "white_kaolin_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_WHITE).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           KAOLIN_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block LIGHT_GRAY_KAOLIN_SLAB = registerBlock(
           "light_gray_kaolin_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           WHITE_KAOLIN_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block GRAY_KAOLIN_SLAB = registerBlock(
           "gray_kaolin_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_GRAY).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           LIGHT_GRAY_KAOLIN_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block BLACK_KAOLIN_SLAB = registerBlock(
           "black_kaolin_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_BLACK).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           GRAY_KAOLIN_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block BROWN_KAOLIN_SLAB = registerBlock(
           "brown_kaolin_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_BROWN).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           BLACK_KAOLIN_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block RED_KAOLIN_SLAB = registerBlock(
           "red_kaolin_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_RED).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           BROWN_KAOLIN_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block ORANGE_KAOLIN_SLAB = registerBlock(
           "orange_kaolin_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           RED_KAOLIN_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block YELLOW_KAOLIN_SLAB = registerBlock(
           "yellow_kaolin_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           ORANGE_KAOLIN_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block LIME_KAOLIN_SLAB = registerBlock(
           "lime_kaolin_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_LIME).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           YELLOW_KAOLIN_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block GREEN_KAOLIN_SLAB = registerBlock(
           "green_kaolin_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_GREEN).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           LIME_KAOLIN_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block CYAN_KAOLIN_SLAB = registerBlock(
           "cyan_kaolin_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_CYAN).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           GREEN_KAOLIN_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block LIGHT_BLUE_KAOLIN_SLAB = registerBlock(
           "light_blue_kaolin_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           CYAN_KAOLIN_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block BLUE_KAOLIN_SLAB = registerBlock(
           "blue_kaolin_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_BLUE).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           LIGHT_BLUE_KAOLIN_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block PURPLE_KAOLIN_SLAB = registerBlock(
           "purple_kaolin_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           BLUE_KAOLIN_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block MAGENTA_KAOLIN_SLAB = registerBlock(
           "magenta_kaolin_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           PURPLE_KAOLIN_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block PINK_KAOLIN_SLAB = registerBlock(
           "pink_kaolin_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_PINK).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           MAGENTA_KAOLIN_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block KAOLIN_BRICKS = registerBlock(
           "kaolin_bricks",
           new Block(FabricBlockSettings.create().mapColor(MapColor.ORANGE).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           PINK_KAOLIN_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block WHITE_KAOLIN_BRICKS = registerBlock(
           "white_kaolin_bricks",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_WHITE).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           KAOLIN_BRICKS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block LIGHT_GRAY_KAOLIN_BRICKS = registerBlock(
           "light_gray_kaolin_bricks",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           WHITE_KAOLIN_BRICKS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block GRAY_KAOLIN_BRICKS = registerBlock(
           "gray_kaolin_bricks",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_GRAY).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           LIGHT_GRAY_KAOLIN_BRICKS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block BLACK_KAOLIN_BRICKS = registerBlock(
           "black_kaolin_bricks",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_BLACK).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           GRAY_KAOLIN_BRICKS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block BROWN_KAOLIN_BRICKS = registerBlock(
           "brown_kaolin_bricks",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_BROWN).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           BLACK_KAOLIN_BRICKS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block RED_KAOLIN_BRICKS = registerBlock(
           "red_kaolin_bricks",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_RED).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           BROWN_KAOLIN_BRICKS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block ORANGE_KAOLIN_BRICKS = registerBlock(
           "orange_kaolin_bricks",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           RED_KAOLIN_BRICKS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block YELLOW_KAOLIN_BRICKS = registerBlock(
           "yellow_kaolin_bricks",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           ORANGE_KAOLIN_BRICKS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block LIME_KAOLIN_BRICKS = registerBlock(
           "lime_kaolin_bricks",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_LIME).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           YELLOW_KAOLIN_BRICKS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block GREEN_KAOLIN_BRICKS = registerBlock(
           "green_kaolin_bricks",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_GREEN).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           LIME_KAOLIN_BRICKS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block CYAN_KAOLIN_BRICKS = registerBlock(
           "cyan_kaolin_bricks",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_CYAN).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           GREEN_KAOLIN_BRICKS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block LIGHT_BLUE_KAOLIN_BRICKS = registerBlock(
           "light_blue_kaolin_bricks",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           CYAN_KAOLIN_BRICKS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block BLUE_KAOLIN_BRICKS = registerBlock(
           "blue_kaolin_bricks",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_BLUE).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           LIGHT_BLUE_KAOLIN_BRICKS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block PURPLE_KAOLIN_BRICKS = registerBlock(
           "purple_kaolin_bricks",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           BLUE_KAOLIN_BRICKS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block MAGENTA_KAOLIN_BRICKS = registerBlock(
           "magenta_kaolin_bricks",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           PURPLE_KAOLIN_BRICKS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block PINK_KAOLIN_BRICKS = registerBlock(
           "pink_kaolin_bricks",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_PINK).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           MAGENTA_KAOLIN_BRICKS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block KAOLIN_BRICK_STAIRS = registerBlock(
           "kaolin_brick_stairs",
           new StairsBlock(KAOLIN_BRICKS.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.ORANGE).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           PINK_KAOLIN_BRICKS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block WHITE_KAOLIN_BRICK_STAIRS = registerBlock(
           "white_kaolin_brick_stairs",
           new StairsBlock(WHITE_KAOLIN_BRICKS.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_WHITE).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           KAOLIN_BRICK_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block LIGHT_GRAY_KAOLIN_BRICK_STAIRS = registerBlock(
           "light_gray_kaolin_brick_stairs",
           new StairsBlock(LIGHT_GRAY_KAOLIN_BRICKS.getDefaultState(),
                   FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           WHITE_KAOLIN_BRICK_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block GRAY_KAOLIN_BRICK_STAIRS = registerBlock(
           "gray_kaolin_brick_stairs",
           new StairsBlock(GRAY_KAOLIN_BRICKS.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_GRAY).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           LIGHT_GRAY_KAOLIN_BRICK_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block BLACK_KAOLIN_BRICK_STAIRS = registerBlock(
           "black_kaolin_brick_stairs",
           new StairsBlock(BLACK_KAOLIN_BRICKS.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_BLACK).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           GRAY_KAOLIN_BRICK_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block BROWN_KAOLIN_BRICK_STAIRS = registerBlock(
           "brown_kaolin_brick_stairs",
           new StairsBlock(BROWN_KAOLIN_BRICKS.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_BROWN).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           BLACK_KAOLIN_BRICK_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block RED_KAOLIN_BRICK_STAIRS = registerBlock(
           "red_kaolin_brick_stairs",
           new StairsBlock(RED_KAOLIN_BRICKS.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_RED).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           BROWN_KAOLIN_BRICK_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block ORANGE_KAOLIN_BRICK_STAIRS = registerBlock(
           "orange_kaolin_brick_stairs",
           new StairsBlock(ORANGE_KAOLIN_BRICKS.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           RED_KAOLIN_BRICK_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block YELLOW_KAOLIN_BRICK_STAIRS = registerBlock(
           "yellow_kaolin_brick_stairs",
           new StairsBlock(YELLOW_KAOLIN_BRICKS.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           ORANGE_KAOLIN_BRICK_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block LIME_KAOLIN_BRICK_STAIRS = registerBlock(
           "lime_kaolin_brick_stairs",
           new StairsBlock(LIME_KAOLIN_BRICKS.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_LIME).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           YELLOW_KAOLIN_BRICK_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block GREEN_KAOLIN_BRICK_STAIRS = registerBlock(
           "green_kaolin_brick_stairs",
           new StairsBlock(GREEN_KAOLIN_BRICKS.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_GREEN).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           LIME_KAOLIN_BRICK_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block CYAN_KAOLIN_BRICK_STAIRS = registerBlock(
           "cyan_kaolin_brick_stairs",
           new StairsBlock(CYAN_KAOLIN_BRICKS.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_CYAN).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           GREEN_KAOLIN_BRICK_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block LIGHT_BLUE_KAOLIN_BRICK_STAIRS = registerBlock(
           "light_blue_kaolin_brick_stairs",
           new StairsBlock(LIGHT_BLUE_KAOLIN_BRICKS.getDefaultState(),
                   FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           CYAN_KAOLIN_BRICK_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block BLUE_KAOLIN_BRICK_STAIRS = registerBlock(
           "blue_kaolin_brick_stairs",
           new StairsBlock(BLUE_KAOLIN_BRICKS.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_BLUE).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           LIGHT_BLUE_KAOLIN_BRICK_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block PURPLE_KAOLIN_BRICK_STAIRS = registerBlock(
           "purple_kaolin_brick_stairs",
           new StairsBlock(PURPLE_KAOLIN_BRICKS.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           BLUE_KAOLIN_BRICK_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block MAGENTA_KAOLIN_BRICK_STAIRS = registerBlock(
           "magenta_kaolin_brick_stairs",
           new StairsBlock(MAGENTA_KAOLIN_BRICKS.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           PURPLE_KAOLIN_BRICK_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block PINK_KAOLIN_BRICK_STAIRS = registerBlock(
           "pink_kaolin_brick_stairs",
           new StairsBlock(PINK_KAOLIN_BRICKS.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_PINK).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           MAGENTA_KAOLIN_BRICK_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block KAOLIN_BRICK_SLAB = registerBlock(
           "kaolin_brick_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.ORANGE).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           PINK_KAOLIN_BRICK_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block WHITE_KAOLIN_BRICK_SLAB = registerBlock(
           "white_kaolin_brick_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_WHITE).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           KAOLIN_BRICK_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block LIGHT_GRAY_KAOLIN_BRICK_SLAB = registerBlock(
           "light_gray_kaolin_brick_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           WHITE_KAOLIN_BRICK_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block GRAY_KAOLIN_BRICK_SLAB = registerBlock(
           "gray_kaolin_brick_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_GRAY).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           LIGHT_GRAY_KAOLIN_BRICK_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block BLACK_KAOLIN_BRICK_SLAB = registerBlock(
           "black_kaolin_brick_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_BLACK).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           GRAY_KAOLIN_BRICK_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block BROWN_KAOLIN_BRICK_SLAB = registerBlock(
           "brown_kaolin_brick_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_BROWN).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           BLACK_KAOLIN_BRICK_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block RED_KAOLIN_BRICK_SLAB = registerBlock(
           "red_kaolin_brick_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_RED).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           BROWN_KAOLIN_BRICK_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block ORANGE_KAOLIN_BRICK_SLAB = registerBlock(
           "orange_kaolin_brick_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           RED_KAOLIN_BRICK_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block YELLOW_KAOLIN_BRICK_SLAB = registerBlock(
           "yellow_kaolin_brick_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           ORANGE_KAOLIN_BRICK_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block LIME_KAOLIN_BRICK_SLAB = registerBlock(
           "lime_kaolin_brick_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_LIME).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           YELLOW_KAOLIN_BRICK_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block GREEN_KAOLIN_BRICK_SLAB = registerBlock(
           "green_kaolin_brick_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_GREEN).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           LIME_KAOLIN_BRICK_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block CYAN_KAOLIN_BRICK_SLAB = registerBlock(
           "cyan_kaolin_brick_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_CYAN).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           GREEN_KAOLIN_BRICK_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block LIGHT_BLUE_KAOLIN_BRICK_SLAB = registerBlock(
           "light_blue_kaolin_brick_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           CYAN_KAOLIN_BRICK_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block BLUE_KAOLIN_BRICK_SLAB = registerBlock(
           "blue_kaolin_brick_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_BLUE).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           LIGHT_BLUE_KAOLIN_BRICK_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block PURPLE_KAOLIN_BRICK_SLAB = registerBlock(
           "purple_kaolin_brick_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           BLUE_KAOLIN_BRICK_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block MAGENTA_KAOLIN_BRICK_SLAB = registerBlock(
           "magenta_kaolin_brick_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           PURPLE_KAOLIN_BRICK_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block PINK_KAOLIN_BRICK_SLAB = registerBlock(
           "pink_kaolin_brick_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_PINK).instrument(Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           MAGENTA_KAOLIN_BRICK_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block WHITE_CHALK = registerBlock(
           "white_chalk",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_WHITE).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           PINK_KAOLIN_BRICK_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block LIGHT_GRAY_CHALK = registerBlock(
           "light_gray_chalk",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           WHITE_CHALK,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block GRAY_CHALK = registerBlock(
           "gray_chalk",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_GRAY).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           LIGHT_GRAY_CHALK,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block BLACK_CHALK = registerBlock(
           "black_chalk",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_BLACK).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           GRAY_CHALK,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block BROWN_CHALK = registerBlock(
           "brown_chalk",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_BROWN).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           BLACK_CHALK,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block RED_CHALK = registerBlock(
           "red_chalk",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_RED).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           BROWN_CHALK,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block ORANGE_CHALK = registerBlock(
           "orange_chalk",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           RED_CHALK,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block YELLOW_CHALK = registerBlock(
           "yellow_chalk",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           ORANGE_CHALK,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block LIME_CHALK = registerBlock(
           "lime_chalk",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_LIME).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           YELLOW_CHALK,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block GREEN_CHALK = registerBlock(
           "green_chalk",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_GREEN).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           LIME_CHALK,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block CYAN_CHALK = registerBlock(
           "cyan_chalk",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_CYAN).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           GREEN_CHALK,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block LIGHT_BLUE_CHALK = registerBlock(
           "light_blue_chalk",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           CYAN_CHALK,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block BLUE_CHALK = registerBlock(
           "blue_chalk",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_BLUE).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           LIGHT_BLUE_CHALK,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block PURPLE_CHALK = registerBlock(
           "purple_chalk",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           BLUE_CHALK,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block MAGENTA_CHALK = registerBlock(
           "magenta_chalk",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           PURPLE_CHALK,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block PINK_CHALK = registerBlock(
           "pink_chalk",
           new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_PINK).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           MAGENTA_CHALK,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block WHITE_CHALK_STAIRS = registerBlock(
           "white_chalk_stairs",
           new StairsBlock(WHITE_CHALK.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_WHITE).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           PINK_CHALK,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block LIGHT_GRAY_CHALK_STAIRS = registerBlock(
           "light_gray_chalk_stairs",
           new StairsBlock(LIGHT_GRAY_CHALK.getDefaultState(),
                   FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)
           ),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           WHITE_CHALK_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block GRAY_CHALK_STAIRS = registerBlock(
           "gray_chalk_stairs",
           new StairsBlock(GRAY_CHALK.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_GRAY).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           LIGHT_GRAY_CHALK_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block BLACK_CHALK_STAIRS = registerBlock(
           "black_chalk_stairs",
           new StairsBlock(BLACK_CHALK.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_BLACK).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           GRAY_CHALK_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block BROWN_CHALK_STAIRS = registerBlock(
           "brown_chalk_stairs",
           new StairsBlock(BROWN_CHALK.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_BROWN).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           BLACK_CHALK_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block RED_CHALK_STAIRS = registerBlock(
           "red_chalk_stairs",
           new StairsBlock(RED_CHALK.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_RED).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           BROWN_CHALK_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block ORANGE_CHALK_STAIRS = registerBlock(
           "orange_chalk_stairs",
           new StairsBlock(ORANGE_CHALK.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           RED_CHALK_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block YELLOW_CHALK_STAIRS = registerBlock(
           "yellow_chalk_stairs",
           new StairsBlock(YELLOW_CHALK.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           ORANGE_CHALK_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block LIME_CHALK_STAIRS = registerBlock(
           "lime_chalk_stairs",
           new StairsBlock(LIME_CHALK.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_LIME).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           YELLOW_CHALK_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block GREEN_CHALK_STAIRS = registerBlock(
           "green_chalk_stairs",
           new StairsBlock(GREEN_CHALK.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_GREEN).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           LIME_CHALK_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block CYAN_CHALK_STAIRS = registerBlock(
           "cyan_chalk_stairs",
           new StairsBlock(CYAN_CHALK.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_CYAN).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           GREEN_CHALK_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block LIGHT_BLUE_CHALK_STAIRS = registerBlock(
           "light_blue_chalk_stairs",
           new StairsBlock(LIGHT_BLUE_CHALK.getDefaultState(),
                   FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           CYAN_CHALK_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block BLUE_CHALK_STAIRS = registerBlock(
           "blue_chalk_stairs",
           new StairsBlock(BLUE_CHALK.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_BLUE).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           LIGHT_BLUE_CHALK_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block PURPLE_CHALK_STAIRS = registerBlock(
           "purple_chalk_stairs",
           new StairsBlock(PURPLE_CHALK.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           BLUE_CHALK_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block MAGENTA_CHALK_STAIRS = registerBlock(
           "magenta_chalk_stairs",
           new StairsBlock(MAGENTA_CHALK.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           PURPLE_CHALK_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block PINK_CHALK_STAIRS = registerBlock(
           "pink_chalk_stairs",
           new StairsBlock(PINK_CHALK.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_PINK).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           MAGENTA_CHALK_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block WHITE_CHALK_SLAB = registerBlock(
           "white_chalk_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_WHITE).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           PINK_CHALK_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block LIGHT_GRAY_CHALK_SLAB = registerBlock(
           "light_gray_chalk_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           WHITE_CHALK_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block GRAY_CHALK_SLAB = registerBlock(
           "gray_chalk_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_GRAY).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           LIGHT_GRAY_CHALK_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block BLACK_CHALK_SLAB = registerBlock(
           "black_chalk_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_BLACK).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           GRAY_CHALK_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block BROWN_CHALK_SLAB = registerBlock(
           "brown_chalk_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_BROWN).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           BLACK_CHALK_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block RED_CHALK_SLAB = registerBlock(
           "red_chalk_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_RED).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           BROWN_CHALK_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block ORANGE_CHALK_SLAB = registerBlock(
           "orange_chalk_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           RED_CHALK_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block YELLOW_CHALK_SLAB = registerBlock(
           "yellow_chalk_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           ORANGE_CHALK_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block LIME_CHALK_SLAB = registerBlock(
           "lime_chalk_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_LIME).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           YELLOW_CHALK_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block GREEN_CHALK_SLAB = registerBlock(
           "green_chalk_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_GREEN).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           LIME_CHALK_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block CYAN_CHALK_SLAB = registerBlock(
           "cyan_chalk_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_CYAN).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           GREEN_CHALK_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block LIGHT_BLUE_CHALK_SLAB = registerBlock(
           "light_blue_chalk_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           CYAN_CHALK_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block BLUE_CHALK_SLAB = registerBlock(
           "blue_chalk_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_BLUE).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           LIGHT_BLUE_CHALK_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block PURPLE_CHALK_SLAB = registerBlock(
           "purple_chalk_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           BLUE_CHALK_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block MAGENTA_CHALK_SLAB = registerBlock(
           "magenta_chalk_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           PURPLE_CHALK_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block PINK_CHALK_SLAB = registerBlock(
           "pink_chalk_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_PINK).instrument(Instrument.BASEDRUM).requiresTool().strength(0.5F)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           MAGENTA_CHALK_SLAB,
           ItemGroups.COLORED_BLOCKS
   );

   public static void registerColoredBlocks() {
   }
}
