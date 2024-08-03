package net.hibiscus.naturespirit.registration.block_registration;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.blocks.*;
import net.hibiscus.naturespirit.blocks.block_entities.PizzaBlockEntity;
import net.hibiscus.naturespirit.blocks.SemiTallGrassBlock;
import net.hibiscus.naturespirit.datagen.HibiscusConfiguredFeatures;
import net.hibiscus.naturespirit.items.DesertTurnipItem;
import net.hibiscus.naturespirit.items.PizzaItem;
import net.hibiscus.naturespirit.registration.FlowerSet;
import net.hibiscus.naturespirit.registration.HibiscusItemGroups;
import net.hibiscus.naturespirit.registration.HibiscusRegistryHelper;
import net.hibiscus.naturespirit.registration.StoneSet;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;
import static net.hibiscus.naturespirit.registration.HibiscusRegistryHelper.*;

public class HibiscusMiscBlocks {

   public static final Block RED_MOSS_BLOCK = registerBlock("red_moss_block", new RedMossBlock(AbstractBlock.Settings.create().mapColor(MapColor.RED).strength(0.1F).sounds(BlockSoundGroup.MOSS_BLOCK).pistonBehavior(PistonBehavior.DESTROY)), HibiscusItemGroups.NS_MISC_ITEM_GROUP, Items.MOSS_BLOCK, ItemGroups.NATURAL);
   public static final Block RED_MOSS_CARPET = registerBlock("red_moss_carpet", new CarpetBlock(AbstractBlock.Settings.create().mapColor(MapColor.RED).strength(0.1F).sounds(BlockSoundGroup.MOSS_CARPET).pistonBehavior(PistonBehavior.DESTROY)), HibiscusItemGroups.NS_MISC_ITEM_GROUP, Items.MOSS_CARPET, ItemGroups.NATURAL);

   public static final Block SANDY_SOIL = registerBlock("sandy_soil",
           new Block(FabricBlockSettings.create().mapColor(MapColor.DIRT_BROWN).instrument(Instrument.BASEDRUM).strength(0.5F).sounds(BlockSoundGroup.GRAVEL)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           Blocks.FARMLAND,
           ItemGroups.NATURAL
   );
   public static final Block PINK_SAND = registerBlock("pink_sand",
           new SandBlock(14331784, FabricBlockSettings.create().mapColor(MapColor.RAW_IRON_PINK).instrument(Instrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)), HibiscusItemGroups.NS_MISC_ITEM_GROUP
   );

   public static final Block PINK_SANDSTONE = registerBlock("pink_sandstone",
           new Block(FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).instrument(Instrument.BASEDRUM).requiresTool().strength(0.8F)), HibiscusItemGroups.NS_MISC_ITEM_GROUP
   );

   public static final Block CHISELED_PINK_SANDSTONE = registerBlock("chiseled_pink_sandstone", new Block(FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).instrument(
           Instrument.BASEDRUM).requiresTool().strength(0.8F)), HibiscusItemGroups.NS_MISC_ITEM_GROUP);

   public static final Block CUT_PINK_SANDSTONE = registerBlock("cut_pink_sandstone",
           new Block(FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).instrument(Instrument.BASEDRUM).requiresTool().strength(0.8F)), HibiscusItemGroups.NS_MISC_ITEM_GROUP
   );

   public static final Block SMOOTH_PINK_SANDSTONE = registerBlock("smooth_pink_sandstone", new Block(FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).instrument(
           Instrument.BASEDRUM).requiresTool().strength(2.0F, 6.0F)), HibiscusItemGroups.NS_MISC_ITEM_GROUP);

   public static final Block PINK_SANDSTONE_STAIRS = registerBlock(
           "pink_sandstone_stairs",
           new StairsBlock(PINK_SANDSTONE.getDefaultState(), FabricBlockSettings.copy(PINK_SANDSTONE)), HibiscusItemGroups.NS_MISC_ITEM_GROUP
   );

   public static final Block SMOOTH_PINK_SANDSTONE_STAIRS = registerBlock("smooth_pink_sandstone_stairs",
           new StairsBlock(SMOOTH_PINK_SANDSTONE.getDefaultState(), FabricBlockSettings.copy(SMOOTH_PINK_SANDSTONE)), HibiscusItemGroups.NS_MISC_ITEM_GROUP
   );

   public static final Block PINK_SANDSTONE_SLAB = registerBlock("pink_sandstone_slab", new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).instrument(
           Instrument.BASEDRUM).requiresTool().strength(2.0F, 6.0F)), HibiscusItemGroups.NS_MISC_ITEM_GROUP);

   public static final Block CUT_PINK_SANDSTONE_SLAB = registerBlock("cut_pink_sandstone_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).instrument(Instrument.BASEDRUM).requiresTool().strength(2.0F, 6.0F)), HibiscusItemGroups.NS_MISC_ITEM_GROUP
   );

   public static final Block SMOOTH_PINK_SANDSTONE_SLAB = registerBlock("smooth_pink_sandstone_slab", new SlabBlock(FabricBlockSettings.copy(SMOOTH_PINK_SANDSTONE)), HibiscusItemGroups.NS_MISC_ITEM_GROUP);

   public static final Block PINK_SANDSTONE_WALL = registerBlock("pink_sandstone_wall", new WallBlock(FabricBlockSettings.copy(PINK_SANDSTONE).solid()), HibiscusItemGroups.NS_MISC_ITEM_GROUP);

   public static final Block TALL_FRIGID_GRASS = registerPlantBlock("tall_frigid_grass",
           new SemiTallGrassBlock(FabricBlockSettings
                   .create()
                   .mapColor(MapColor.LICHEN_GREEN)
                   .noCollision()
                   .replaceable()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XZ)
                   .pistonBehavior(PistonBehavior.DESTROY)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           Blocks.LARGE_FERN,
           0.3f
   );

   public static final Block FRIGID_GRASS = registerPlantBlock("frigid_grass",
           new HibiscusFernBlock(FabricBlockSettings
                   .create()
                   .mapColor(MapColor.LICHEN_GREEN)
                   .noCollision()
                   .replaceable()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XYZ)
                   .pistonBehavior(PistonBehavior.DESTROY), (TallPlantBlock) TALL_FRIGID_GRASS),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           Blocks.FERN,
           0.3f
   );

   public static final Block TALL_SCORCHED_GRASS = registerPlantBlock("tall_scorched_grass",
           new TallLargeDesertFernBlock(FabricBlockSettings
                   .create()
                   .mapColor(MapColor.LICHEN_GREEN)
                   .noCollision()
                   .replaceable()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XZ)
                   .pistonBehavior(PistonBehavior.DESTROY)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           Blocks.LARGE_FERN,
           0.3f
   );

   public static final Block SCORCHED_GRASS = registerPlantBlock("scorched_grass",
           new LargeDesertFernBlock(FabricBlockSettings
                   .create()
                   .mapColor(MapColor.LICHEN_GREEN)
                   .noCollision()
                   .replaceable()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XYZ)
                   .pistonBehavior(PistonBehavior.DESTROY), (TallPlantBlock) TALL_SCORCHED_GRASS),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           Blocks.FERN,
           0.3f
   );
   public static final Block TALL_BEACH_GRASS = registerPlantBlock("tall_beach_grass",
           new TallLargeDesertFernBlock(FabricBlockSettings
                   .create()
                   .mapColor(MapColor.YELLOW)
                   .noCollision()
                   .replaceable()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XZ)
                   .pistonBehavior(PistonBehavior.DESTROY)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           Blocks.LARGE_FERN,
           0.3f
   );

   public static final Block BEACH_GRASS = registerPlantBlock("beach_grass",
           new LargeDesertFernBlock(FabricBlockSettings
                   .create()
                   .mapColor(MapColor.YELLOW)
                   .noCollision()
                   .replaceable()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XYZ)
                   .pistonBehavior(PistonBehavior.DESTROY), (TallPlantBlock) TALL_BEACH_GRASS),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           Blocks.FERN,
           0.3f
   );

   public static final Block TALL_SEDGE_GRASS = registerPlantBlock("tall_sedge_grass",
           new TallSedgeGrassBlock(FabricBlockSettings
                   .create()
                   .mapColor(MapColor.DARK_GREEN)
                   .noCollision()
                   .replaceable()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XZ)
                   .pistonBehavior(PistonBehavior.DESTROY)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           TALL_SCORCHED_GRASS,
           0.3f
   );

   public static final Block SEDGE_GRASS = registerPlantBlock("sedge_grass",
           new SedgeGrassBlock(FabricBlockSettings
                   .create()
                   .mapColor(MapColor.DARK_GREEN)
                   .noCollision()
                   .replaceable()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XYZ)
                   .pistonBehavior(PistonBehavior.DESTROY)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           SCORCHED_GRASS,
           0.3f
   );

   public static final Block LARGE_FLAXEN_FERN = registerPlantBlock("large_flaxen_fern",
           new TallPlantBlock(FabricBlockSettings
                   .create()
                   .noCollision()
                   .replaceable()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XZ)
                   .pistonBehavior(PistonBehavior.DESTROY)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           TALL_SEDGE_GRASS,
           0.3f
   );

   public static final Block FLAXEN_FERN = registerPlantBlock("flaxen_fern",
           new HibiscusFernBlock(FabricBlockSettings
                   .create()
                   .noCollision()
                   .replaceable()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XYZ)
                   .pistonBehavior(PistonBehavior.DESTROY), (TallPlantBlock) LARGE_FLAXEN_FERN),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           SEDGE_GRASS,
           0.3f
   );
   public static final Block TALL_OAT_GRASS = registerPlantBlock("tall_oat_grass",
           new SemiTallGrassBlock(FabricBlockSettings
                   .create()
                   .mapColor(MapColor.PALE_YELLOW)
                   .noCollision()
                   .replaceable()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XZ)
                   .pistonBehavior(PistonBehavior.DESTROY)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           LARGE_FLAXEN_FERN,
           0.3f
   );

   public static final Block OAT_GRASS = registerPlantBlock("oat_grass",
           new HibiscusFernBlock(FabricBlockSettings
                   .create()
                   .mapColor(MapColor.PALE_YELLOW)
                   .noCollision()
                   .replaceable()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XYZ)
                   .pistonBehavior(PistonBehavior.DESTROY), (TallPlantBlock) TALL_OAT_GRASS),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           FLAXEN_FERN,
           0.3f
   );

   public static final Block LARGE_LUSH_FERN = registerPlantBlock("large_lush_fern",
           new TallPlantBlock(FabricBlockSettings
                   .create()
                   .noCollision()
                   .replaceable()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XZ)
                   .pistonBehavior(PistonBehavior.DESTROY)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           TALL_OAT_GRASS,
           0.3f
   );

   public static final Block LUSH_FERN = registerPlantBlock("lush_fern",
           new HibiscusFernBlock(FabricBlockSettings
                   .create()
                   .noCollision()
                   .replaceable()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XYZ)
                   .pistonBehavior(PistonBehavior.DESTROY), (TallPlantBlock) LARGE_LUSH_FERN),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           OAT_GRASS,
           0.3f
   );
   public static final Block TALL_MELIC_GRASS = registerPlantBlock("tall_melic_grass",
           new TallPlantBlock(FabricBlockSettings
                   .create()
                   .mapColor(MapColor.GREEN)
                   .noCollision()
                   .replaceable()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XZ)
                   .pistonBehavior(PistonBehavior.DESTROY)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           LARGE_LUSH_FERN,
           0.3f
   );

   public static final Block MELIC_GRASS = registerPlantBlock("melic_grass",
           new HibiscusFernBlock(FabricBlockSettings
                   .create()
                   .mapColor(MapColor.GREEN)
                   .noCollision()
                   .replaceable()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XYZ)
                   .pistonBehavior(PistonBehavior.DESTROY), (TallPlantBlock) TALL_MELIC_GRASS),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           LUSH_FERN,
           0.3f
   );
   public static final Block GREEN_BEARBERRIES = registerPlantBlock("green_bearberries",
           new HibiscusBearberriesBlock(FabricBlockSettings
                   .create()
                   .noCollision()
                   .replaceable()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XYZ)
                   .pistonBehavior(PistonBehavior.DESTROY)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           MELIC_GRASS,
           0.3f
   );
   public static final Block RED_BEARBERRIES = registerPlantBlock("red_bearberries",
           new HibiscusBearberriesBlock(FabricBlockSettings
                   .create()
                   .noCollision()
                   .replaceable()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XYZ)
                   .pistonBehavior(PistonBehavior.DESTROY)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           GREEN_BEARBERRIES,
           0.3f
   );
   public static final Block PURPLE_BEARBERRIES = registerPlantBlock("purple_bearberries",
           new HibiscusBearberriesBlock(FabricBlockSettings
                   .create()
                   .noCollision()
                   .replaceable()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XYZ)
                   .pistonBehavior(PistonBehavior.DESTROY)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           RED_BEARBERRIES,
           0.3f
   );
   public static final Block GREEN_BITTER_SPROUTS = registerPlantBlock("green_bitter_sprouts",
           new HibiscusLargeSproutsBlock(FabricBlockSettings
                   .create()
                   .noCollision()
                   .replaceable()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XZ)
                   .pistonBehavior(PistonBehavior.DESTROY)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           PURPLE_BEARBERRIES,
           0.3f
   );
   public static final Block RED_BITTER_SPROUTS = registerPlantBlock("red_bitter_sprouts",
           new HibiscusLargeSproutsBlock(FabricBlockSettings
                   .create()
                   .noCollision()
                   .replaceable()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XZ)
                   .pistonBehavior(PistonBehavior.DESTROY)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           GREEN_BITTER_SPROUTS,
           0.3f
   );
   public static final Block PURPLE_BITTER_SPROUTS = registerPlantBlock("purple_bitter_sprouts",
           new HibiscusLargeSproutsBlock(FabricBlockSettings
                   .create()
                   .noCollision()
                   .replaceable()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XZ)
                   .pistonBehavior(PistonBehavior.DESTROY)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           RED_BITTER_SPROUTS,
           0.3f
   );
   public static final Block CATTAIL = registerPlantBlock("cattail",
           new Cattails(FabricBlockSettings
                   .create()
                   .mapColor(MapColor.DARK_GREEN)
                   .noCollision()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .burnable()
                   .pistonBehavior(PistonBehavior.DESTROY)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           Blocks.LARGE_FERN,
           0.4f
   );

   public static final Block ORNATE_SUCCULENT = registerPlantBlock("ornate_succulent", new SucculentBlock(AbstractBlock.Settings.create().mapColor(MapColor.RED).sounds(BlockSoundGroup.GRASS).noCollision().breakInstantly()));
   public static final Block DROWSY_SUCCULENT = registerPlantBlock("drowsy_succulent", new SucculentBlock(AbstractBlock.Settings.create().mapColor(MapColor.GREEN).sounds(BlockSoundGroup.GRASS).noCollision().breakInstantly()));
   public static final Block AUREATE_SUCCULENT = registerPlantBlock("aureate_succulent", new SucculentBlock(AbstractBlock.Settings.create().mapColor(MapColor.YELLOW).sounds(BlockSoundGroup.GRASS).noCollision().breakInstantly()));
   public static final Block SAGE_SUCCULENT = registerPlantBlock("sage_succulent", new SucculentBlock(AbstractBlock.Settings.create().mapColor(MapColor.DARK_GREEN).sounds(BlockSoundGroup.GRASS).noCollision().breakInstantly()));
   public static final Block FOAMY_SUCCULENT = registerPlantBlock("foamy_succulent", new SucculentBlock(AbstractBlock.Settings.create().mapColor(MapColor.LIGHT_BLUE).sounds(BlockSoundGroup.GRASS).noCollision().breakInstantly()));
   public static final Block IMPERIAL_SUCCULENT = registerPlantBlock("imperial_succulent", new SucculentBlock(AbstractBlock.Settings.create().mapColor(MapColor.PURPLE).sounds(BlockSoundGroup.GRASS).noCollision().breakInstantly()));
   public static final Block REGAL_SUCCULENT = registerPlantBlock("regal_succulent", new SucculentBlock(AbstractBlock.Settings.create().mapColor(MapColor.PINK).sounds(BlockSoundGroup.GRASS).noCollision().breakInstantly()));
   public static final Block ORNATE_WALL_SUCCULENT = registerPlantBlock("ornate_wall_succulent", new SucculentWallBlock(AbstractBlock.Settings.create().mapColor(MapColor.RED).sounds(BlockSoundGroup.GRASS).noCollision().breakInstantly().dropsLike(ORNATE_SUCCULENT)));
   public static final Block DROWSY_WALL_SUCCULENT = registerPlantBlock("drowsy_wall_succulent", new SucculentWallBlock(AbstractBlock.Settings.create().mapColor(MapColor.GREEN).sounds(BlockSoundGroup.GRASS).noCollision().breakInstantly().dropsLike(DROWSY_SUCCULENT)));
   public static final Block AUREATE_WALL_SUCCULENT = registerPlantBlock("aureate_wall_succulent", new SucculentWallBlock(AbstractBlock.Settings.create().mapColor(MapColor.YELLOW).sounds(BlockSoundGroup.GRASS).noCollision().breakInstantly().dropsLike(AUREATE_SUCCULENT)));
   public static final Block SAGE_WALL_SUCCULENT = registerPlantBlock("sage_wall_succulent", new SucculentWallBlock(AbstractBlock.Settings.create().mapColor(MapColor.DARK_GREEN).sounds(BlockSoundGroup.GRASS).noCollision().breakInstantly().dropsLike(SAGE_SUCCULENT)));
   public static final Block FOAMY_WALL_SUCCULENT = registerPlantBlock("foamy_wall_succulent", new SucculentWallBlock(AbstractBlock.Settings.create().mapColor(MapColor.LIGHT_BLUE).sounds(BlockSoundGroup.GRASS).noCollision().breakInstantly().dropsLike(FOAMY_SUCCULENT)));
   public static final Block IMPERIAL_WALL_SUCCULENT = registerPlantBlock("imperial_wall_succulent", new SucculentWallBlock(AbstractBlock.Settings.create().mapColor(MapColor.PURPLE).sounds(BlockSoundGroup.GRASS).noCollision().breakInstantly().dropsLike(IMPERIAL_SUCCULENT)));
   public static final Block REGAL_WALL_SUCCULENT = registerPlantBlock("regal_wall_succulent", new SucculentWallBlock(AbstractBlock.Settings.create().mapColor(MapColor.PINK).sounds(BlockSoundGroup.GRASS).noCollision().breakInstantly().dropsLike(REGAL_SUCCULENT)));

   public static final Item ORNATE_SUCCULENT_ITEM = registerPlantWallBlockItem("ornate_succulent", ORNATE_SUCCULENT, ORNATE_WALL_SUCCULENT, HibiscusItemGroups.NS_MISC_ITEM_GROUP, Items.PITCHER_PLANT, .5F);
   public static final Item DROWSY_SUCCULENT_ITEM = registerPlantWallBlockItem("drowsy_succulent", DROWSY_SUCCULENT, DROWSY_WALL_SUCCULENT, HibiscusItemGroups.NS_MISC_ITEM_GROUP, ORNATE_SUCCULENT_ITEM, .5F);
   public static final Item AUREATE_SUCCULENT_ITEM = registerPlantWallBlockItem("aureate_succulent", AUREATE_SUCCULENT, AUREATE_WALL_SUCCULENT, HibiscusItemGroups.NS_MISC_ITEM_GROUP, DROWSY_SUCCULENT_ITEM, .5F);
   public static final Item SAGE_SUCCULENT_ITEM = registerPlantWallBlockItem("sage_succulent", SAGE_SUCCULENT, SAGE_WALL_SUCCULENT, HibiscusItemGroups.NS_MISC_ITEM_GROUP, AUREATE_SUCCULENT_ITEM, .5F);
   public static final Item FOAMY_SUCCULENT_ITEM = registerPlantWallBlockItem("foamy_succulent", FOAMY_SUCCULENT, FOAMY_WALL_SUCCULENT, HibiscusItemGroups.NS_MISC_ITEM_GROUP, SAGE_SUCCULENT_ITEM, .5F);
   public static final Item IMPERIAL_SUCCULENT_ITEM = registerPlantWallBlockItem("imperial_succulent", IMPERIAL_SUCCULENT, IMPERIAL_WALL_SUCCULENT, HibiscusItemGroups.NS_MISC_ITEM_GROUP, FOAMY_SUCCULENT_ITEM, .5F);
   public static final Item REGAL_SUCCULENT_ITEM = registerPlantWallBlockItem("regal_succulent", REGAL_SUCCULENT, REGAL_WALL_SUCCULENT, HibiscusItemGroups.NS_MISC_ITEM_GROUP, IMPERIAL_SUCCULENT_ITEM, .5F);

   public static final Block POTTED_SCORCHED_GRASS = registerPottedPlant("scorched_grass", SCORCHED_GRASS);
   public static final Block POTTED_BEACH_GRASS = registerPottedPlant("beach_grass", BEACH_GRASS);
   public static final Block POTTED_SEDGE_GRASS = registerPottedPlant("sedge_grass", SEDGE_GRASS);
   public static final Block POTTED_FLAXEN_FERN = registerPottedPlant("flaxen_fern", FLAXEN_FERN);
   public static final Block POTTED_OAT_GRASS = registerPottedPlant("oat_grass", OAT_GRASS);
   public static final Block POTTED_MELIC_GRASS = registerPottedPlant("melic_grass", MELIC_GRASS);
   public static final Block POTTED_LUSH_FERN = registerPottedPlant("lush_fern", LUSH_FERN);
   public static final Block POTTED_FRIGID_GRASS = registerPottedPlant("frigid_grass", FRIGID_GRASS);
   public static final Block POTTED_GREEN_BEARBERRIES = registerPottedPlant("potted_green_bearberries", GREEN_BEARBERRIES);
   public static final Block POTTED_RED_BEARBERRIES = registerPottedPlant("potted_red_bearberries", RED_BEARBERRIES);
   public static final Block POTTED_PURPLE_BEARBERRIES = registerPottedPlant("potted_purple_bearberries", PURPLE_BEARBERRIES);


   public static final Block POTTED_ORNATE_SUCCULENT = registerPottedPlant("ornate_succulent", ORNATE_SUCCULENT);
   public static final Block POTTED_DROWSY_SUCCULENT = registerPottedPlant("drowsy_succulent", DROWSY_SUCCULENT);
   public static final Block POTTED_AUREATE_SUCCULENT = registerPottedPlant("aureate_succulent", AUREATE_SUCCULENT);
   public static final Block POTTED_SAGE_SUCCULENT = registerPottedPlant("sage_succulent", SAGE_SUCCULENT);
   public static final Block POTTED_FOAMY_SUCCULENT = registerPottedPlant("foamy_succulent", FOAMY_SUCCULENT);
   public static final Block POTTED_IMPERIAL_SUCCULENT = registerPottedPlant("imperial_succulent", IMPERIAL_SUCCULENT);
   public static final Block POTTED_REGAL_SUCCULENT = registerPottedPlant("regal_succulent", REGAL_SUCCULENT);

   public static final Block SHIITAKE_MUSHROOM = registerPlantBlock("shiitake_mushroom", new ShiitakeMushroomPlantBlock(
           AbstractBlock.Settings
                   .create()
                   .mapColor(MapColor.BROWN)
                   .noCollision()
                   .ticksRandomly()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .luminance((state) -> 1)
                   .postProcess(Blocks::always)
                   .pistonBehavior(PistonBehavior.DESTROY),
           HibiscusConfiguredFeatures.HUGE_SHIITAKE_MUSHROOM
   ), HibiscusItemGroups.NS_MISC_ITEM_GROUP, Blocks.RED_MUSHROOM, 0.1F);

   public static final Block SHIITAKE_MUSHROOM_BLOCK = registerBlock("shiitake_mushroom_block",
           new MushroomBlock(FabricBlockSettings.create().mapColor(MapColor.DIRT_BROWN).instrument(Instrument.BASS).strength(0.2F).sounds(BlockSoundGroup.WOOD).burnable()),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           Blocks.RED_MUSHROOM_BLOCK,
           ItemGroups.NATURAL
   );
   public static final Block GRAY_POLYPORE = registerPlantBlock("gray_polypore", new PolyporeBlock(
           AbstractBlock.Settings
                   .create()
                   .mapColor(MapColor.BROWN)
                   .noCollision()
                   .ticksRandomly()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .luminance((state) -> 1)
                   .postProcess(Blocks::always)
                   .pistonBehavior(PistonBehavior.DESTROY),
           HibiscusConfiguredFeatures.GRAY_POLYPORE
   ), HibiscusItemGroups.NS_MISC_ITEM_GROUP, SHIITAKE_MUSHROOM, 0.1F);

   public static final Block GRAY_POLYPORE_BLOCK = registerBlock("gray_polypore_block",
           new MushroomBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(Instrument.BASS).strength(0.2F).sounds(BlockSoundGroup.WOOD).burnable()),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           SHIITAKE_MUSHROOM_BLOCK,
           ItemGroups.NATURAL
   );


   public static final FlowerSet LAVENDER = new FlowerSet("lavender", Items.PURPLE_DYE, Items.PEONY, FlowerSet.FlowerPreset.BIG_TALL);
   public static final FlowerSet BLEEDING_HEART = new FlowerSet("bleeding_heart", Items.PINK_DYE, LAVENDER.getFlowerBlock().asItem(), FlowerSet.FlowerPreset.BIG_TALL);
   public static final FlowerSet BLUE_BULBS = new FlowerSet("blue_bulbs", Items.BLUE_DYE, BLEEDING_HEART.getFlowerBlock().asItem(), FlowerSet.FlowerPreset.BIG_TALL);
   public static final FlowerSet CARNATION = new FlowerSet("carnation", Items.RED_DYE, BLUE_BULBS.getFlowerBlock().asItem(), FlowerSet.FlowerPreset.BIG_TALL);

   public static final FlowerSet GARDENIA = new FlowerSet("gardenia", Items.WHITE_DYE, CARNATION.getFlowerBlock().asItem(), FlowerSet.FlowerPreset.TALL);
   public static final FlowerSet SNAPDRAGON = new FlowerSet("snapdragon", Items.PINK_DYE, GARDENIA.getFlowerBlock().asItem(), FlowerSet.FlowerPreset.TALL);
   public static final FlowerSet MARIGOLD = new FlowerSet("marigold", Items.ORANGE_DYE, SNAPDRAGON.getFlowerBlock().asItem(), FlowerSet.FlowerPreset.TALL);
   public static final FlowerSet FOXGLOVE = new FlowerSet("foxglove", Items.PURPLE_DYE, MARIGOLD.getFlowerBlock().asItem(), FlowerSet.FlowerPreset.TALL);

   public static final FlowerSet BLUEBELL = new FlowerSet("bluebell", Items.BLUE_DYE, StatusEffects.HASTE, Items.LILY_OF_THE_VALLEY, FlowerSet.FlowerPreset.BIG_SMALL);
   public static final FlowerSet TIGER_LILY = new FlowerSet("tiger_lily", Items.ORANGE_DYE, StatusEffects.FIRE_RESISTANCE, BLUEBELL.getFlowerBlock().asItem(), FlowerSet.FlowerPreset.BIG_SMALL);
   public static final FlowerSet PURPLE_WILDFLOWER = new FlowerSet("purple_wildflower", Items.PURPLE_DYE, StatusEffects.SLOW_FALLING, TIGER_LILY.getFlowerBlock().asItem(), FlowerSet.FlowerPreset.BIG_SMALL);
   public static final FlowerSet YELLOW_WILDFLOWER = new FlowerSet("yellow_wildflower", Items.YELLOW_DYE, StatusEffects.SLOW_FALLING, PURPLE_WILDFLOWER.getFlowerBlock().asItem(), FlowerSet.FlowerPreset.BIG_SMALL);
   public static final FlowerSet RED_HEATHER = new FlowerSet("red_heather", Items.RED_DYE, StatusEffects.FIRE_RESISTANCE, YELLOW_WILDFLOWER.getFlowerBlock().asItem(), FlowerSet.FlowerPreset.BIG_SMALL);
   public static final FlowerSet WHITE_HEATHER = new FlowerSet("white_heather", Items.WHITE_DYE, StatusEffects.FIRE_RESISTANCE, RED_HEATHER.getFlowerBlock().asItem(), FlowerSet.FlowerPreset.BIG_SMALL);
   public static final FlowerSet PURPLE_HEATHER = new FlowerSet("purple_heather", Items.PURPLE_DYE, StatusEffects.FIRE_RESISTANCE, WHITE_HEATHER.getFlowerBlock().asItem(), FlowerSet.FlowerPreset.BIG_SMALL);
   public static final FlowerSet ANEMONE = new FlowerSet("anemone", Items.MAGENTA_DYE, StatusEffects.RESISTANCE, PURPLE_HEATHER.getFlowerBlock().asItem(), FlowerSet.FlowerPreset.ANEMONE);
   public static final FlowerSet DWARF_BLOSSOMS = new FlowerSet("dwarf_blossoms", Items.PINK_DYE, StatusEffects.RESISTANCE, ANEMONE.getFlowerBlock().asItem(), FlowerSet.FlowerPreset.ANEMONE);
   public static final FlowerSet PROTEA = new FlowerSet("protea", Items.PINK_DYE, StatusEffects.WATER_BREATHING, DWARF_BLOSSOMS.getFlowerBlock().asItem(), FlowerSet.FlowerPreset.ANEMONE);
   public static final FlowerSet HIBISCUS = new FlowerSet("hibiscus", Items.RED_DYE, StatusEffects.LUCK, PROTEA.getFlowerBlock().asItem(), FlowerSet.FlowerPreset.SMALL);
   public static final FlowerSet BLUE_IRIS = new FlowerSet("blue_iris", Items.BLUE_DYE, StatusEffects.STRENGTH, HIBISCUS.getFlowerBlock().asItem(), FlowerSet.FlowerPreset.SMALL);
   public static final FlowerSet BLACK_IRIS = new FlowerSet("black_iris", Items.BLACK_DYE, StatusEffects.STRENGTH, BLUE_IRIS.getFlowerBlock().asItem(), FlowerSet.FlowerPreset.SMALL);
   public static final FlowerSet RUBY_BLOSSOMS = new FlowerSet("ruby_blossoms", Items.RED_DYE, StatusEffects.JUMP_BOOST, BLACK_IRIS.getFlowerBlock().asItem(), FlowerSet.FlowerPreset.BIG_SMALL);
   public static final FlowerSet SILVERBUSH = new FlowerSet("silverbush", RUBY_BLOSSOMS.getFlowerBlock().asItem(), FlowerSet.FlowerPreset.BIG_TALL);


   public static final Block HELVOLA = HibiscusRegistryHelper.registerPlantBlock("helvola",
           new WaterFlowerbedBlock(FabricBlockSettings
                   .create()
                   .pistonBehavior(PistonBehavior.DESTROY)
                   .ticksRandomly()
                   .nonOpaque()
                   .breakInstantly().slipperiness(0.8F)
                   .sounds(BlockSoundGroup.LILY_PAD))
   );

   public static final Item HELVOLA_PAD_ITEM = registerItem("helvola_pad",
           new PlaceableOnWaterItem(HELVOLA, new Item.Settings()),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           Items.LILY_PAD,
           ItemGroups.NATURAL
   );

   public static final Item HELVOLA_FLOWER_ITEM = registerItem("helvola_flower",
           new Item(new Item.Settings()),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           HELVOLA_PAD_ITEM,
           ItemGroups.NATURAL
   );

   public static final Block LOTUS_FLOWER = HibiscusRegistryHelper.registerPlantBlock("lotus_flower",
           new LotusFlowerBlock(FabricBlockSettings
                   .create()
                   .mapColor(MapColor.PINK)
                   .pistonBehavior(PistonBehavior.DESTROY)
                   .ticksRandomly()
                   .nonOpaque()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.LILY_PAD))
   );

   public static final Item LOTUS_FLOWER_ITEM = registerItem("lotus_flower",
           new PlaceableOnWaterItem(LOTUS_FLOWER, new Item.Settings()),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           HELVOLA_FLOWER_ITEM,
           ItemGroups.NATURAL
   );

   public static final Block LOTUS_STEM = registerPlantBlock("lotus_stem", new LotusStem(FabricBlockSettings
           .create()
           .mapColor(MapColor.GREEN)
           .pistonBehavior(PistonBehavior.DESTROY)
           .noCollision()
           .nonOpaque()
           .breakInstantly()
           .sounds(BlockSoundGroup.LILY_PAD), LOTUS_FLOWER), HibiscusItemGroups.NS_MISC_ITEM_GROUP, LOTUS_FLOWER, 0.2F);

   public static final Block ALLUAUDIA = registerPlantBlock("alluaudia", new GrowingBranchingTrunkBlock(FabricBlockSettings.create().pistonBehavior(PistonBehavior.DESTROY).mapColor(MapColor.GREEN).nonOpaque().sounds(BlockSoundGroup.VINE).hardness(.5f).strength(.5f).notSolid()), HibiscusItemGroups.NS_MISC_ITEM_GROUP, Items.CACTUS, .2f);
   public static final Block STRIPPED_ALLUAUDIA = registerPlantBlock("stripped_alluaudia", new GrowingBranchingTrunkBlock(FabricBlockSettings.create().pistonBehavior(PistonBehavior.DESTROY).mapColor(MapColor.OAK_TAN).nonOpaque().sounds(BlockSoundGroup.VINE).hardness(.5f).strength(.5f).notSolid()), HibiscusItemGroups.NS_MISC_ITEM_GROUP, HibiscusMiscBlocks.ALLUAUDIA, .2f);
   public static final Block ALLUAUDIA_BUNDLE = registerPlantBlock("alluaudia_bundle", new PillarBlock(FabricBlockSettings.create().mapColor(MapColor.GREEN).nonOpaque().sounds(BlockSoundGroup.VINE).hardness(.6f).strength(.6f)), HibiscusItemGroups.NS_MISC_ITEM_GROUP, STRIPPED_ALLUAUDIA, .2f);
   public static final Block STRIPPED_ALLUAUDIA_BUNDLE = registerPlantBlock("stripped_alluaudia_bundle", new PillarBlock(FabricBlockSettings.create().mapColor(MapColor.OAK_TAN).nonOpaque().sounds(BlockSoundGroup.VINE).hardness(.6f).strength(.6f)), HibiscusItemGroups.NS_MISC_ITEM_GROUP, ALLUAUDIA_BUNDLE, .2f);



   public static final Block POTTED_SHIITAKE_MUSHROOM = registerPottedPlant("shiitake_mushroom", SHIITAKE_MUSHROOM);

   public static final FoodComponent GREEN_OLIVE_COMPONENT = (new FoodComponent.Builder()).hunger(2).saturationModifier(0.4F).build();

   public static final FoodComponent BLACK_OLIVE_COMPONENT = (new FoodComponent.Builder()).hunger(3).saturationModifier(0.5F).build();

   public static final Item GREEN_OLIVES = registerPlantItem("green_olives",
           new Item(new FabricItemSettings().food(GREEN_OLIVE_COMPONENT)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           Items.BEETROOT,
           ItemGroups.FOOD_AND_DRINK,
           0.3F
   );

   public static final Item BLACK_OLIVES = registerPlantItem("black_olives",
           new Item(new FabricItemSettings().food(BLACK_OLIVE_COMPONENT)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           GREEN_OLIVES,
           ItemGroups.FOOD_AND_DRINK,
           0.3F
   );

   public static final Block DESERT_TURNIP_ROOT_BLOCK = registerBlock("desert_turnip_root_block",
           new PillarBlock(FabricBlockSettings.create().burnable().instrument(Instrument.BASS).mapColor(MapColor.SPRUCE_BROWN).strength(1.0F).sounds(BlockSoundGroup.ROOTS)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           Blocks.SHROOMLIGHT,
           ItemGroups.NATURAL
   );

   public static final Block DESERT_TURNIP_BLOCK = registerBlock("desert_turnip_block",
           new DesertTurnipBlock(FabricBlockSettings.create().burnable().instrument(Instrument.BASS).mapColor(MapColor.PALE_PURPLE).strength(1.0F).sounds(BlockSoundGroup.ROOTS)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           HibiscusMiscBlocks.DESERT_TURNIP_ROOT_BLOCK,
           ItemGroups.NATURAL
   );

   public static final Block DESERT_TURNIP_STEM = HibiscusRegistryHelper.registerPlantBlock("desert_turnip_stem", new DesertPlantBlock((DesertTurnipBlock) DESERT_TURNIP_BLOCK,
           DESERT_TURNIP_ROOT_BLOCK,
           FabricBlockSettings.create().noCollision().breakInstantly().ticksRandomly().sounds(BlockSoundGroup.GRASS).pistonBehavior(PistonBehavior.DESTROY)
   ));

   public static final FoodComponent DESERT_TURNIP_FOOD_COMPONENT = (new FoodComponent.Builder()).hunger(2).saturationModifier(0.3F).snack().build();

   public static final Item DESERT_TURNIP = registerItem("desert_turnip",
           new DesertTurnipItem(DESERT_TURNIP_STEM, (new Item.Settings()).food(DESERT_TURNIP_FOOD_COMPONENT)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           Items.BEETROOT,
           ItemGroups.FOOD_AND_DRINK
   );

   public static final Item CHALK_POWDER = registerItem(
           "chalk_powder",
           new Item((new Item.Settings())),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           Items.HONEYCOMB,
           ItemGroups.INGREDIENTS
   );

   public static final Block CHEESE_BLOCK = registerBlock("cheese_block",
           new CheeseBlock(AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW).strength(2.0F, 1.0F).sounds(BlockSoundGroup.AZALEA_LEAVES))
   );

   public static final Item CHEESE_BUCKET = registerItem("cheese_bucket",
           new PowderSnowBucketItem(CHEESE_BLOCK, SoundEvents.ITEM_BUCKET_EMPTY, (new Item.Settings()).maxCount(1).recipeRemainder(Items.BUCKET)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           Items.MILK_BUCKET,
           ItemGroups.FOOD_AND_DRINK
   );

   public static final Block MILK_CAULDRON = registerBlock("milk_cauldron", new MilkCauldronBlock(AbstractBlock.Settings.copy(Blocks.CAULDRON).dropsLike(Blocks.CAULDRON)));

   public static final Block CHEESE_CAULDRON = registerBlock("cheese_cauldron", new CheeseCauldronBlock(AbstractBlock.Settings.copy(Blocks.CAULDRON).dropsLike(Blocks.CAULDRON)));

   public static final FoodComponent STANDARD_PIZZA_COMPONENT = (new FoodComponent.Builder()).hunger(2).saturationModifier(0.2F).build();

   public static final Block PIZZA_BLOCK = registerBlock("pizza_block", new PizzaBlock(FabricBlockSettings.copy(Blocks.CAKE)));
   public static final BlockEntityType <PizzaBlockEntity> PIZZA_BLOCK_ENTITY_TYPE = HibiscusRegistryHelper.registerBlockEntity("pizza_block_entity", FabricBlockEntityTypeBuilder.create(PizzaBlockEntity::new, PIZZA_BLOCK));

   public static final Item WHOLE_PIZZA = registerItem("whole_pizza",
           new PizzaItem(PIZZA_BLOCK, new Item.Settings().maxCount(1).food(STANDARD_PIZZA_COMPONENT)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           Items.BREAD,
           ItemGroups.FOOD_AND_DRINK
   );

   public static final Item THREE_QUARTERS_PIZZA = registerItem("three_quarters_pizza", new PizzaItem(PIZZA_BLOCK, new Item.Settings().maxCount(1).food(STANDARD_PIZZA_COMPONENT)));

   public static final Item HALF_PIZZA = registerItem("half_pizza", new PizzaItem(PIZZA_BLOCK, new Item.Settings().maxCount(1).food(STANDARD_PIZZA_COMPONENT)));

   public static final Item QUARTER_PIZZA = registerItem("quarter_pizza", new PizzaItem(PIZZA_BLOCK, new Item.Settings().maxCount(1).food(STANDARD_PIZZA_COMPONENT)));

   public static final Item CALCITE_SHARD = registerItem(
           "calcite_shard",
           new Item(new Item.Settings()),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           Items.AMETHYST_SHARD,
           ItemGroups.INGREDIENTS
   );

   public static final Block CALCITE_CLUSTER = registerBlock("calcite_cluster", new AmethystClusterBlock(7,
           3,
           FabricBlockSettings
                   .create()
                   .mapColor(MapColor.WHITE)
                   .solid()
                   .nonOpaque()
                   .ticksRandomly()
                   .sounds(BlockSoundGroup.CALCITE)
                   .strength(1.5F)
                   .pistonBehavior(PistonBehavior.DESTROY)
   ), HibiscusItemGroups.NS_MISC_ITEM_GROUP, Blocks.AMETHYST_CLUSTER, ItemGroups.NATURAL);

   public static final Block LARGE_CALCITE_BUD = registerBlock("large_calcite_bud",
           new AmethystClusterBlock(4, 3, FabricBlockSettings.copy(CALCITE_CLUSTER).sounds(BlockSoundGroup.CALCITE).solid().pistonBehavior(PistonBehavior.DESTROY)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           CALCITE_CLUSTER,
           ItemGroups.NATURAL
   );

   public static final Block SMALL_CALCITE_BUD = registerBlock("small_calcite_bud",
           new AmethystClusterBlock(3, 4, FabricBlockSettings.copy(CALCITE_CLUSTER).sounds(BlockSoundGroup.CALCITE).solid().pistonBehavior(PistonBehavior.DESTROY)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           LARGE_CALCITE_BUD,
           ItemGroups.NATURAL
   );


   public static final StoneSet TRAVERTINE = new StoneSet(new Identifier(MOD_ID, "travertine"), MapColor.LIGHT_GRAY, Items.POLISHED_ANDESITE_SLAB, Items.ANDESITE, 1.5F, true, false, true, true);
   public static final StoneSet CHERT = new StoneSet(new Identifier(MOD_ID, "chert"), MapColor.OAK_TAN, TRAVERTINE.getTilesSlab().asItem(), TRAVERTINE.getBase().asItem(), .9F, false, false, false, true, true);

   public static final Block CHERT_GOLD_ORE = registerBlock("chert_gold_ore",
           new ExperienceDroppingBlock(FabricBlockSettings.copy(Blocks.GOLD_ORE).mapColor(MapColor.OAK_TAN).strength(.9f), ConstantIntProvider.create(0)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           Items.GOLD_ORE,
           ItemGroups.NATURAL
   );
   public static final Block CHERT_IRON_ORE = registerBlock("chert_iron_ore",
           new ExperienceDroppingBlock(FabricBlockSettings.copy(Blocks.IRON_ORE).mapColor(MapColor.OAK_TAN).strength(.9f), ConstantIntProvider.create(0)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           Items.IRON_ORE,
           ItemGroups.NATURAL
   );
   public static final Block CHERT_COAL_ORE = registerBlock("chert_coal_ore",
           new ExperienceDroppingBlock(FabricBlockSettings.copy(Blocks.COAL_ORE).mapColor(MapColor.OAK_TAN).strength(.9f), UniformIntProvider.create(0, 2)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           Items.COAL_ORE,
           ItemGroups.NATURAL
   );
   public static final Block CHERT_LAPIS_ORE = registerBlock("chert_lapis_ore",
           new ExperienceDroppingBlock(FabricBlockSettings.copy(Blocks.LAPIS_ORE).mapColor(MapColor.OAK_TAN).strength(.9f), UniformIntProvider.create(2, 5)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           Items.LAPIS_ORE,
           ItemGroups.NATURAL
   );
   public static final Block CHERT_DIAMOND_ORE = registerBlock("chert_diamond_ore",
           new ExperienceDroppingBlock(FabricBlockSettings.copy(Blocks.DIAMOND_ORE).mapColor(MapColor.OAK_TAN).strength(.9f), UniformIntProvider.create(3, 7)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           Items.DIAMOND_ORE,
           ItemGroups.NATURAL
   );
   public static final Block CHERT_REDSTONE_ORE = registerBlock("chert_redstone_ore",
           new RedstoneOreBlock(FabricBlockSettings.copy(Blocks.REDSTONE_ORE).mapColor(MapColor.OAK_TAN).strength(.6f)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           Items.REDSTONE_ORE,
           ItemGroups.NATURAL
   );
   public static final Block CHERT_EMERALD_ORE = registerBlock("chert_emerald_ore",
           new ExperienceDroppingBlock(FabricBlockSettings.copy(Blocks.EMERALD_ORE).mapColor(MapColor.OAK_TAN).strength(.6f), UniformIntProvider.create(3, 7)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           Items.EMERALD_ORE,
           ItemGroups.NATURAL
   );
   public static final Block CHERT_COPPER_ORE = registerBlock("chert_copper_ore",
           new ExperienceDroppingBlock(FabricBlockSettings.copy(Blocks.COPPER_ORE).mapColor(MapColor.OAK_TAN).strength(.6f), ConstantIntProvider.create(0)),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           Items.COPPER_ORE,
           ItemGroups.NATURAL
   );


   public static final BlockSetType PAPER_BLOCK_SET = BlockSetTypeBuilder.copyOf(BlockSetType.CHERRY).register(new Identifier(MOD_ID, "paper"));
   public static final WoodType PAPER_WOOD_TYPE = new WoodTypeBuilder().register(new Identifier(MOD_ID, "paper"), PAPER_BLOCK_SET);

   public static final Block PAPER_BLOCK = registerBlock("paper_block",
           new Block(FabricBlockSettings.copy(HibiscusWoods.SUGI.getPlanks())),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           Items.WARPED_BUTTON,
           ItemGroups.BUILDING_BLOCKS
   );
   public static final Block PAPER_PANEL = registerBlock("paper_panel",
           new PaneBlock(FabricBlockSettings.copy(HibiscusWoods.SUGI.getPlanks())),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           PAPER_BLOCK,
           ItemGroups.BUILDING_BLOCKS
   );
   public static final Block PAPER_DOOR = registerDoorBlock("paper_door",
           new DoorBlock(FabricBlockSettings.copy(HibiscusWoods.SUGI.getDoor()), PAPER_BLOCK_SET),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           PAPER_PANEL
   );
   public static final Block PAPER_TRAPDOOR = registerDoorBlock("paper_trapdoor", new TrapdoorBlock(
           FabricBlockSettings.create().burnable().instrument(Instrument.BASS).strength(3.0f).sounds(BlockSoundGroup.WOOD).allowsSpawning(HibiscusRegistryHelper::never).nonOpaque(),
           PAPER_BLOCK_SET
   ), HibiscusItemGroups.NS_MISC_ITEM_GROUP, PAPER_DOOR);
   public static final Block FRAMED_PAPER_BLOCK = registerBlock("framed_paper_block",
           new Block(FabricBlockSettings.copy(HibiscusWoods.SUGI.getPlanks())),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           PAPER_TRAPDOOR,
           ItemGroups.BUILDING_BLOCKS
   );
   public static final Block FRAMED_PAPER_PANEL = registerBlock("framed_paper_panel",
           new PaneBlock(FabricBlockSettings.copy(HibiscusWoods.SUGI.getPlanks())),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           FRAMED_PAPER_BLOCK,
           ItemGroups.BUILDING_BLOCKS
   );
   public static final Block FRAMED_PAPER_DOOR = registerDoorBlock("framed_paper_door",
           new DoorBlock(FabricBlockSettings.copy(HibiscusWoods.SUGI.getDoor()), PAPER_BLOCK_SET),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           FRAMED_PAPER_PANEL
   );
   public static final Block FRAMED_PAPER_TRAPDOOR = registerDoorBlock("framed_paper_trapdoor", new TrapdoorBlock(
           FabricBlockSettings.create().burnable().instrument(Instrument.BASS).strength(3.0f).sounds(BlockSoundGroup.WOOD).allowsSpawning(HibiscusRegistryHelper::never).nonOpaque(),
           PAPER_BLOCK_SET
   ), HibiscusItemGroups.NS_MISC_ITEM_GROUP, FRAMED_PAPER_DOOR);
   public static final Block BLOOMING_PAPER_BLOCK = registerBlock("blooming_paper_block",
           new GlazedTerracottaBlock(FabricBlockSettings.copy(HibiscusWoods.SUGI.getPlanks())),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           FRAMED_PAPER_TRAPDOOR,
           ItemGroups.BUILDING_BLOCKS
   );
   public static final Block BLOOMING_PAPER_PANEL = registerBlock("blooming_paper_panel",
           new PaneBlock(FabricBlockSettings.copy(HibiscusWoods.SUGI.getPlanks())),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           BLOOMING_PAPER_BLOCK,
           ItemGroups.BUILDING_BLOCKS
   );
   public static final Block BLOOMING_PAPER_DOOR = registerDoorBlock("blooming_paper_door",
           new DoorBlock(FabricBlockSettings.copy(HibiscusWoods.SUGI.getDoor()), PAPER_BLOCK_SET),
           HibiscusItemGroups.NS_MISC_ITEM_GROUP,
           BLOOMING_PAPER_PANEL
   );
   public static final Block BLOOMING_PAPER_TRAPDOOR = registerDoorBlock("blooming_paper_trapdoor", new TrapdoorBlock(
           FabricBlockSettings.create().burnable().instrument(Instrument.BASS).strength(3.0f).sounds(BlockSoundGroup.WOOD).allowsSpawning(HibiscusRegistryHelper::never).nonOpaque(),
           PAPER_BLOCK_SET
   ), HibiscusItemGroups.NS_MISC_ITEM_GROUP, BLOOMING_PAPER_DOOR);


   public static final Block PAPER_SIGN = registerBlock("paper_sign", new SignBlock(AbstractBlock.Settings.copy(HibiscusWoods.SUGI.getSign()), PAPER_WOOD_TYPE));
   public static final Block PAPER_WALL_SIGN = registerBlock("paper_wall_sign", new WallSignBlock(AbstractBlock.Settings.copy(HibiscusWoods.SUGI.getSign()).dropsLike(PAPER_SIGN), PAPER_WOOD_TYPE));
   public static final Block PAPER_HANGING_SIGN =  registerBlock("paper_hanging_sign", new HangingSignBlock(AbstractBlock.Settings.copy(HibiscusWoods.SUGI.getHangingSign()), PAPER_WOOD_TYPE));
   public static final Block PAPER_WALL_HANGING_SIGN = registerBlock("paper_wall_hanging_sign", new WallHangingSignBlock(AbstractBlock.Settings.copy(HibiscusWoods.SUGI.getHangingSign()).dropsLike(PAPER_HANGING_SIGN), PAPER_WOOD_TYPE));
   public static final Item PAPER_SIGN_ITEM = registerItem( "paper_sign", new SignItem(new FabricItemSettings().maxCount(16), PAPER_SIGN, PAPER_WALL_SIGN), HibiscusItemGroups.NS_MISC_ITEM_GROUP, Items.WARPED_SIGN, ItemGroups.FUNCTIONAL);
   public static final Item PAPER_HANGING_SIGN_ITEM = registerItem( "paper_hanging_sign", new HangingSignItem(PAPER_HANGING_SIGN, PAPER_WALL_HANGING_SIGN, new FabricItemSettings().maxCount(16)), HibiscusItemGroups.NS_MISC_ITEM_GROUP, PAPER_SIGN_ITEM, ItemGroups.FUNCTIONAL);

   public static void registerMiscBlocks() {
      HibiscusColoredBlocks.registerColoredBlocks();
      NatureSpirit.LOGGER.debug("Registering ModBlocks for " + MOD_ID);
      FlammableBlockRegistry.getDefaultInstance().add(HibiscusWoods.COCONUT_THATCH, 5, 20);
      FlammableBlockRegistry.getDefaultInstance().add(HibiscusWoods.COCONUT_THATCH_SLAB, 5, 20);
      FlammableBlockRegistry.getDefaultInstance().add(HibiscusWoods.COCONUT_THATCH_STAIRS, 5, 20);
      FlammableBlockRegistry.getDefaultInstance().add(HibiscusWoods.COCONUT_THATCH_CARPET, 5, 20);
      FlammableBlockRegistry.getDefaultInstance().add(HibiscusWoods.XERIC_THATCH, 5, 20);
      FlammableBlockRegistry.getDefaultInstance().add(HibiscusWoods.XERIC_THATCH_SLAB, 5, 20);
      FlammableBlockRegistry.getDefaultInstance().add(HibiscusWoods.XERIC_THATCH_STAIRS, 5, 20);
      FlammableBlockRegistry.getDefaultInstance().add(HibiscusWoods.XERIC_THATCH_CARPET, 5, 20);
      FlammableBlockRegistry.getDefaultInstance().add(HibiscusWoods.EVERGREEN_THATCH, 5, 20);
      FlammableBlockRegistry.getDefaultInstance().add(HibiscusWoods.EVERGREEN_THATCH_SLAB, 5, 20);
      FlammableBlockRegistry.getDefaultInstance().add(HibiscusWoods.EVERGREEN_THATCH_STAIRS, 5, 20);
      FlammableBlockRegistry.getDefaultInstance().add(HibiscusWoods.EVERGREEN_THATCH_CARPET, 5, 20);
      CompostingChanceRegistry.INSTANCE.add(HibiscusWoods.COCONUT_THATCH, 0.65F);
      CompostingChanceRegistry.INSTANCE.add(HibiscusWoods.COCONUT_THATCH_CARPET, 0.1F);
      CompostingChanceRegistry.INSTANCE.add(HibiscusWoods.COCONUT_THATCH_STAIRS, 0.5F);
      CompostingChanceRegistry.INSTANCE.add(HibiscusWoods.COCONUT_THATCH_SLAB, 0.3F);
      CompostingChanceRegistry.INSTANCE.add(HibiscusWoods.XERIC_THATCH, 0.65F);
      CompostingChanceRegistry.INSTANCE.add(HibiscusWoods.XERIC_THATCH_CARPET, 0.1F);
      CompostingChanceRegistry.INSTANCE.add(HibiscusWoods.XERIC_THATCH_STAIRS, 0.5F);
      CompostingChanceRegistry.INSTANCE.add(HibiscusWoods.XERIC_THATCH_SLAB, 0.3F);
      CompostingChanceRegistry.INSTANCE.add(HibiscusWoods.EVERGREEN_THATCH, 0.65F);
      CompostingChanceRegistry.INSTANCE.add(HibiscusWoods.EVERGREEN_THATCH_CARPET, 0.1F);
      CompostingChanceRegistry.INSTANCE.add(HibiscusWoods.EVERGREEN_THATCH_STAIRS, 0.5F);
      CompostingChanceRegistry.INSTANCE.add(HibiscusWoods.EVERGREEN_THATCH_SLAB, 0.3F);
      CompostingChanceRegistry.INSTANCE.add(RED_MOSS_BLOCK, 0.65F);
      CompostingChanceRegistry.INSTANCE.add(RED_MOSS_CARPET, 0.3F);
      CompostingChanceRegistry.INSTANCE.add(LOTUS_FLOWER_ITEM, 0.65F);
      CompostingChanceRegistry.INSTANCE.add(HELVOLA_FLOWER_ITEM, 0.3F);
      CompostingChanceRegistry.INSTANCE.add(HELVOLA_PAD_ITEM, 0.3F);
      CompostingChanceRegistry.INSTANCE.add(DESERT_TURNIP, 0.3F);
      CompostingChanceRegistry.INSTANCE.add(DESERT_TURNIP_BLOCK, 0.65F);
      CompostingChanceRegistry.INSTANCE.add(DESERT_TURNIP_ROOT_BLOCK, 0.5F);
      CompostingChanceRegistry.INSTANCE.add(WHOLE_PIZZA, 1.0F);
      CompostingChanceRegistry.INSTANCE.add(THREE_QUARTERS_PIZZA, .85F);
      CompostingChanceRegistry.INSTANCE.add(HALF_PIZZA, .5F);
      CompostingChanceRegistry.INSTANCE.add(QUARTER_PIZZA, .35F);
      CompostingChanceRegistry.INSTANCE.add(AUREATE_SUCCULENT_ITEM, .65F);
      CompostingChanceRegistry.INSTANCE.add(DROWSY_SUCCULENT_ITEM, .65F);
      CompostingChanceRegistry.INSTANCE.add(FOAMY_SUCCULENT_ITEM, .65F);
      CompostingChanceRegistry.INSTANCE.add(SAGE_SUCCULENT_ITEM, .65F);
      CompostingChanceRegistry.INSTANCE.add(IMPERIAL_SUCCULENT_ITEM, .65F);
      CompostingChanceRegistry.INSTANCE.add(ORNATE_SUCCULENT_ITEM, .65F);
      CompostingChanceRegistry.INSTANCE.add(REGAL_SUCCULENT_ITEM, .65F);
      StrippableBlockRegistry.register(ALLUAUDIA_BUNDLE, STRIPPED_ALLUAUDIA_BUNDLE);
   }
}
