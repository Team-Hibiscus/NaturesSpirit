package net.hibiscus.naturespirit.registration.block_registration;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.hibiscus.naturespirit.blocks.WillowVine;
import net.hibiscus.naturespirit.blocks.WillowVinePlant;
import net.hibiscus.naturespirit.blocks.WisteriaVine;
import net.hibiscus.naturespirit.blocks.WisteriaVinePlant;
import net.hibiscus.naturespirit.entity.HibiscusBoatEntity;
import net.hibiscus.naturespirit.items.HibiscusBoatItem;
import net.hibiscus.naturespirit.mixin.BlockSetTypeAccessor;
import net.hibiscus.naturespirit.mixin.WoodTypeAccessor;
import net.hibiscus.naturespirit.registration.HibiscusItemGroups;
import net.hibiscus.naturespirit.util.HibiscusRegistryHelper;
import net.hibiscus.naturespirit.world.feature.tree.*;
import net.minecraft.block.*;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import static net.hibiscus.naturespirit.NatureSpirit.*;
import static net.hibiscus.naturespirit.util.HibiscusRegistryHelper.*;

public class HibiscusWoods {

   public static final Block REDWOOD_LEAVES = registerLeafBlock("redwood", MapColor.COLOR_LIGHT_GREEN, Blocks.CHERRY_LEAVES);
   public static final Block SUGI_LEAVES = registerLeafBlock("sugi", MapColor.COLOR_GREEN, REDWOOD_LEAVES);
   public static final Block WHITE_WISTERIA_LEAVES = registerLeafBlock("white_wisteria", MapColor.TERRACOTTA_WHITE, SUGI_LEAVES, true);
   public static final Block BLUE_WISTERIA_LEAVES = registerLeafBlock("blue_wisteria", MapColor.COLOR_CYAN, WHITE_WISTERIA_LEAVES, true);
   public static final Block PINK_WISTERIA_LEAVES = registerLeafBlock("pink_wisteria", MapColor.COLOR_PINK, BLUE_WISTERIA_LEAVES, true);
   public static final Block PURPLE_WISTERIA_LEAVES = registerLeafBlock("purple_wisteria", MapColor.COLOR_PURPLE, PINK_WISTERIA_LEAVES, true);
   public static final Block FIR_LEAVES = registerLeafBlock("fir", MapColor.COLOR_LIGHT_GREEN, PURPLE_WISTERIA_LEAVES);
   public static final Block WILLOW_LEAVES = registerLeafBlock("willow", MapColor.COLOR_GREEN, FIR_LEAVES, false);
   public static final Block ASPEN_LEAVES = registerLeafBlock("aspen", MapColor.COLOR_LIGHT_GREEN, WILLOW_LEAVES);
   public static final Block CYPRESS_LEAVES = registerLeafBlock("cypress", MapColor.PLANT, ASPEN_LEAVES);
   public static final Block OLIVE_LEAVES = registerLeafBlock("olive", MapColor.PLANT, CYPRESS_LEAVES);
   public static final Block JOSHUA_LEAVES = registerLeafBlock("joshua", MapColor.SAND, OLIVE_LEAVES);
   public static final Block RED_MAPLE_LEAVES = registerMapleLeafBlock("red_maple", MapColor.NETHER, RED_MAPLE_LEAVES_PARTICLE, ASPEN_LEAVES);
   public static final Block ORANGE_MAPLE_LEAVES = registerMapleLeafBlock("orange_maple", MapColor.COLOR_ORANGE, ORANGE_MAPLE_LEAVES_PARTICLE, RED_MAPLE_LEAVES);
   public static final Block YELLOW_MAPLE_LEAVES = registerMapleLeafBlock("yellow_maple", MapColor.SAND, YELLOW_MAPLE_LEAVES_PARTICLE, ORANGE_MAPLE_LEAVES);
   public static final Block[] REDWOOD_SAPLING = registerSapling("redwood", new RedwoodSaplingGenerator(), Blocks.CHERRY_SAPLING);
   public static final Block[] SUGI_SAPLING = registerSapling("sugi", new SugiSaplingGenerator(), REDWOOD_SAPLING[0]);
   public static final Block[] WHITE_WISTERIA_SAPLING = registerSapling("white_wisteria", new WhiteWisteriaSaplingGenerator(), SUGI_SAPLING[0]);
   public static final Block[] BLUE_WISTERIA_SAPLING = registerSapling("blue_wisteria", new BlueWisteriaSaplingGenerator(), WHITE_WISTERIA_SAPLING[0]);
   public static final Block[] PINK_WISTERIA_SAPLING = registerSapling("pink_wisteria", new PinkWisteriaSaplingGenerator(), BLUE_WISTERIA_SAPLING[0]);
   public static final Block[] PURPLE_WISTERIA_SAPLING = registerSapling("purple_wisteria", new PurpleWisteriaSaplingGenerator(), PINK_WISTERIA_SAPLING[0]);
   public static final Block[] FIR_SAPLING = registerSapling("fir", new FirSaplingGenerator(), PURPLE_WISTERIA_SAPLING[0]);
   public static final Block[] WILLOW_SAPLING = registerSapling("willow", new WillowSaplingGenerator(), FIR_SAPLING[0]);
   public static final Block[] ASPEN_SAPLING = registerSapling("aspen", new AspenSaplingGenerator(), WILLOW_SAPLING[0]);
   public static final Block[] RED_MAPLE_SAPLING = registerSapling("red_maple", new RedMapleSaplingGenerator(), ASPEN_SAPLING[0]);
   public static final Block[] ORANGE_MAPLE_SAPLING = registerSapling("orange_maple", new OrangeMapleSaplingGenerator(), RED_MAPLE_SAPLING[0]);
   public static final Block[] YELLOW_MAPLE_SAPLING = registerSapling("yellow_maple", new YellowMapleSaplingGenerator(), ORANGE_MAPLE_SAPLING[0]);
   public static final Block[] CYPRESS_SAPLING = registerSapling("cypress", new CypressSaplingGenerator(), YELLOW_MAPLE_SAPLING[0]);
   public static final Block[] OLIVE_SAPLING = registerSapling("olive", new OliveSaplingGenerator(), CYPRESS_SAPLING[0]);
   public static final Block[] JOSHUA_SAPLING = registerJoshuaSapling("joshua", new JoshuaSaplingGenerator(), OLIVE_SAPLING[0]);
   public static final BlockSetType REDWOOD_BLOCK_SET_TYPE = BlockSetTypeAccessor.registerNew(new BlockSetType("redwood"));
   public static final WoodType REDWOOD_WOOD_TYPE = WoodTypeAccessor.registerNew(new WoodType("redwood",
           REDWOOD_BLOCK_SET_TYPE
   ));
   public static final Block[] REDWOOD = registerWoodBlocks("redwood",
           MapColor.COLOR_RED,
           MapColor.TERRACOTTA_BROWN,
           Blocks.CHERRY_BUTTON,
           Blocks.CHERRY_LOG,
           getPreviousVanillaWood(),
           REDWOOD_BLOCK_SET_TYPE,
           REDWOOD_WOOD_TYPE
   );
   public static final BlockSetType SUGI_BLOCK_SET_TYPE = BlockSetTypeAccessor.registerNew(new BlockSetType("sugi"));
   public static final WoodType SUGI_WOOD_TYPE = WoodTypeAccessor.registerNew(new WoodType("sugi",
           SUGI_BLOCK_SET_TYPE
   ));
   public static final Block[] SUGI = registerWoodBlocks("sugi",
           MapColor.DIRT,
           MapColor.DEEPSLATE,
           REDWOOD[12],
           REDWOOD[2],
           REDWOOD,
           SUGI_BLOCK_SET_TYPE,
           SUGI_WOOD_TYPE
   );
   public static final BlockSetType WISTERIA_BLOCK_SET_TYPE = BlockSetTypeAccessor.registerNew(new BlockSetType(
           "wisteria"));
   public static final WoodType WISTERIA_WOOD_TYPE = WoodTypeAccessor.registerNew(new WoodType("wisteria",
           WISTERIA_BLOCK_SET_TYPE
   ));
   public static final Block[] WISTERIA = registerWoodBlocks("wisteria",
           MapColor.TERRACOTTA_WHITE,
           MapColor.COLOR_GRAY,
           SUGI[12],
           SUGI[2],
           SUGI,
           WISTERIA_BLOCK_SET_TYPE,
           WISTERIA_WOOD_TYPE
   );
   public static final BlockSetType FIR_BLOCK_SET_TYPE = BlockSetTypeAccessor.registerNew(new BlockSetType("fir"));
   public static final WoodType FIR_WOOD_TYPE = WoodTypeAccessor.registerNew(new WoodType("fir", FIR_BLOCK_SET_TYPE));
   public static final Block[] FIR = registerWoodBlocks("fir",
           MapColor.DIRT,
           MapColor.COLOR_GRAY,
           WISTERIA[12],
           WISTERIA[2],
           WISTERIA,
           FIR_BLOCK_SET_TYPE,
           FIR_WOOD_TYPE
   );
   public static final BlockSetType WILLOW_BLOCK_SET_TYPE = BlockSetTypeAccessor.registerNew(new BlockSetType("willow"));
   public static final WoodType WILLOW_WOOD_TYPE = WoodTypeAccessor.registerNew(new WoodType("willow",
           WILLOW_BLOCK_SET_TYPE
   ));
   public static final Block[] WILLOW = registerWoodBlocks("willow",
           MapColor.TERRACOTTA_BROWN,
           MapColor.TERRACOTTA_BLACK,
           FIR[12],
           FIR[2],
           FIR,
           WILLOW_BLOCK_SET_TYPE,
           WILLOW_WOOD_TYPE
   );
   public static final BlockSetType ASPEN_BLOCK_SET_TYPE = BlockSetTypeAccessor.registerNew(new BlockSetType("aspen"));
   public static final WoodType MAPLE_WOOD_TYPE = WoodTypeAccessor.registerNew(new WoodType("maple",
           ASPEN_BLOCK_SET_TYPE
   ));
   public static final WoodType ASPEN_WOOD_TYPE = WoodTypeAccessor.registerNew(new WoodType("aspen",
           ASPEN_BLOCK_SET_TYPE
   ));
   public static final Block[] ASPEN = registerWoodBlocks("aspen",
           MapColor.SAND,
           MapColor.WOOL,
           WILLOW[12],
           WILLOW[2],
           WILLOW,
           ASPEN_BLOCK_SET_TYPE,
           ASPEN_WOOD_TYPE
   );
   public static final BlockSetType MAPLE_BLOCK_SET_TYPE = BlockSetTypeAccessor.registerNew(new BlockSetType("maple"));
   public static final Block[] MAPLE = registerWoodBlocks("maple",
           MapColor.COLOR_ORANGE,
           MapColor.PODZOL,
           ASPEN[12],
           ASPEN[2],
           ASPEN,
           MAPLE_BLOCK_SET_TYPE,
           MAPLE_WOOD_TYPE
   );
   public static final BlockSetType CYPRESS_BLOCK_SET_TYPE = BlockSetTypeAccessor.registerNew(new BlockSetType("cypress"));
   public static final WoodType CYPRESS_WOOD_TYPE = WoodTypeAccessor.registerNew(new WoodType("cypress",
           CYPRESS_BLOCK_SET_TYPE
   ));
   public static final Block[] CYPRESS = registerWoodBlocks("cypress",
           MapColor.WOOD,
           MapColor.PODZOL,
           MAPLE[12],
           MAPLE[2],
           MAPLE,
           CYPRESS_BLOCK_SET_TYPE,
           CYPRESS_WOOD_TYPE
   );
   public static final BlockSetType OLIVE_BLOCK_SET_TYPE = BlockSetTypeAccessor.registerNew(new BlockSetType("olive"));
   public static final WoodType OLIVE_WOOD_TYPE = WoodTypeAccessor.registerNew(new WoodType("olive",
           OLIVE_BLOCK_SET_TYPE
   ));
   public static final Block[] OLIVE = registerWoodBlocks("olive",
           MapColor.GRASS,
           MapColor.SAND,
           CYPRESS[12],
           CYPRESS[2],
           CYPRESS,
           OLIVE_BLOCK_SET_TYPE,
           OLIVE_WOOD_TYPE
   );
   public static final BlockSetType JOSHUA_BLOCK_SET_TYPE = BlockSetTypeAccessor.registerNew(new BlockSetType("joshua"));
   public static final WoodType JOSHUA_WOOD_TYPE = WoodTypeAccessor.registerNew(new WoodType("joshua",
           JOSHUA_BLOCK_SET_TYPE
   ));
   public static final Block[] JOSHUA = registerJoshuaWoodBlocks("joshua",
           MapColor.GRASS,
           OLIVE[12],
           OLIVE[2],
           OLIVE,
           JOSHUA_BLOCK_SET_TYPE,
           JOSHUA_WOOD_TYPE
   );
   public static final Block FRAMED_SUGI_DOOR = registerSecondaryDoorBlock("framed_sugi_door",
           new DoorBlock(FabricBlockSettings.copy(SUGI[4]).noOcclusion(), SUGI_BLOCK_SET_TYPE),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           SUGI[8]
   );
   public static final Block FRAMED_SUGI_TRAPDOOR = registerSecondaryDoorBlock("framed_sugi_trapdoor",
           new TrapDoorBlock(
                   FabricBlockSettings.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(3.0f).sound(
                           SoundType.WOOD).isValidSpawn(HibiscusRegistryHelper::never).noOcclusion(),
                   SUGI_BLOCK_SET_TYPE
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           FRAMED_SUGI_DOOR
   );
   public static final Item REDWOOD_BOAT = registerItem("redwood_boat",
           new HibiscusBoatItem(false, HibiscusBoatEntity.HibiscusBoat.REDWOOD, new Item.Properties()),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           Items.CHERRY_CHEST_BOAT,
           CreativeModeTabs.TOOLS_AND_UTILITIES
   );
   public static final Item REDWOOD_CHEST_BOAT = registerItem("redwood_chest_boat",
           new HibiscusBoatItem(true, HibiscusBoatEntity.HibiscusBoat.REDWOOD, new Item.Properties()),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           REDWOOD_BOAT,
           CreativeModeTabs.TOOLS_AND_UTILITIES
   );
   public static final Item SUGI_BOAT = registerItem("sugi_boat",
           new HibiscusBoatItem(false, HibiscusBoatEntity.HibiscusBoat.SUGI, new Item.Properties()),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           REDWOOD_CHEST_BOAT,
           CreativeModeTabs.TOOLS_AND_UTILITIES
   );
   public static final Item SUGI_CHEST_BOAT = registerItem("sugi_chest_boat",
           new HibiscusBoatItem(true, HibiscusBoatEntity.HibiscusBoat.SUGI, new Item.Properties()),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           SUGI_BOAT,
           CreativeModeTabs.TOOLS_AND_UTILITIES
   );
   public static final Item WISTERIA_BOAT = registerItem("wisteria_boat",
           new HibiscusBoatItem(false, HibiscusBoatEntity.HibiscusBoat.WISTERIA, new Item.Properties()),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           SUGI_CHEST_BOAT,
           CreativeModeTabs.TOOLS_AND_UTILITIES
   );
   public static final Item WISTERIA_CHEST_BOAT = registerItem("wisteria_chest_boat",
           new HibiscusBoatItem(true, HibiscusBoatEntity.HibiscusBoat.WISTERIA, new Item.Properties()),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           WISTERIA_BOAT,
           CreativeModeTabs.TOOLS_AND_UTILITIES
   );
   public static final Item FIR_BOAT = registerItem("fir_boat",
           new HibiscusBoatItem(false, HibiscusBoatEntity.HibiscusBoat.FIR, new Item.Properties()),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           WISTERIA_CHEST_BOAT,
           CreativeModeTabs.TOOLS_AND_UTILITIES
   );
   public static final Item FIR_CHEST_BOAT = registerItem("fir_chest_boat",
           new HibiscusBoatItem(true, HibiscusBoatEntity.HibiscusBoat.FIR, new Item.Properties()),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           FIR_BOAT,
           CreativeModeTabs.TOOLS_AND_UTILITIES
   );
   public static final Item WILLOW_BOAT = registerItem("willow_boat",
           new HibiscusBoatItem(false, HibiscusBoatEntity.HibiscusBoat.WILLOW, new Item.Properties()),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           FIR_CHEST_BOAT,
           CreativeModeTabs.TOOLS_AND_UTILITIES
   );
   public static final Item WILLOW_CHEST_BOAT = registerItem("willow_chest_boat",
           new HibiscusBoatItem(true, HibiscusBoatEntity.HibiscusBoat.WILLOW, new Item.Properties()),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           WILLOW_BOAT,
           CreativeModeTabs.TOOLS_AND_UTILITIES
   );
   public static final Item ASPEN_BOAT = registerItem("aspen_boat",
           new HibiscusBoatItem(false, HibiscusBoatEntity.HibiscusBoat.ASPEN, new Item.Properties()),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           WILLOW_CHEST_BOAT,
           CreativeModeTabs.TOOLS_AND_UTILITIES
   );
   public static final Item ASPEN_CHEST_BOAT = registerItem("aspen_chest_boat",
           new HibiscusBoatItem(true, HibiscusBoatEntity.HibiscusBoat.ASPEN, new Item.Properties()),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           ASPEN_BOAT,
           CreativeModeTabs.TOOLS_AND_UTILITIES
   );
   public static final Item MAPLE_BOAT = registerItem("maple_boat",
           new HibiscusBoatItem(false, HibiscusBoatEntity.HibiscusBoat.MAPLE, new Item.Properties()),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           ASPEN_CHEST_BOAT,
           CreativeModeTabs.TOOLS_AND_UTILITIES
   );
   public static final Item CYPRESS_CHEST_BOAT = registerItem("cypress_chest_boat",
           new HibiscusBoatItem(true, HibiscusBoatEntity.HibiscusBoat.CYPRESS, new Item.Properties()),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           MAPLE_BOAT,
           CreativeModeTabs.TOOLS_AND_UTILITIES
   );
   public static final Item OLIVE_BOAT = registerItem("olive_boat",
           new HibiscusBoatItem(false, HibiscusBoatEntity.HibiscusBoat.OLIVE, new Item.Properties()),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           CYPRESS_CHEST_BOAT,
           CreativeModeTabs.TOOLS_AND_UTILITIES
   );
   public static final Item OLIVE_CHEST_BOAT = registerItem("olive_chest_boat",
           new HibiscusBoatItem(true, HibiscusBoatEntity.HibiscusBoat.OLIVE, new Item.Properties()),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           OLIVE_BOAT,
           CreativeModeTabs.TOOLS_AND_UTILITIES
   );
   public static final Item JOSHUA_BOAT = registerItem("joshua_boat",
           new HibiscusBoatItem(false, HibiscusBoatEntity.HibiscusBoat.JOSHUA, new Item.Properties()),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           OLIVE_CHEST_BOAT,
           CreativeModeTabs.TOOLS_AND_UTILITIES
   );
   public static final Item JOSHUA_CHEST_BOAT = registerItem("joshua_chest_boat",
           new HibiscusBoatItem(true, HibiscusBoatEntity.HibiscusBoat.JOSHUA, new Item.Properties()),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           JOSHUA_BOAT,
           CreativeModeTabs.TOOLS_AND_UTILITIES
   );
   public static final Item MAPLE_CHEST_BOAT = registerItem("maple_chest_boat",
           new HibiscusBoatItem(true, HibiscusBoatEntity.HibiscusBoat.MAPLE, new Item.Properties()),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           MAPLE_BOAT,
           CreativeModeTabs.TOOLS_AND_UTILITIES
   );
   public static final Item CYPRESS_BOAT = registerItem("cypress_boat",
           new HibiscusBoatItem(false, HibiscusBoatEntity.HibiscusBoat.CYPRESS, new Item.Properties()),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           MAPLE_CHEST_BOAT,
           CreativeModeTabs.TOOLS_AND_UTILITIES
   );
   public static final Block WHITE_WISTERIA_VINES = registerPlantBlock("white_wisteria_vines",
           new WisteriaVine(FabricBlockSettings.of()
                                               .mapColor(MapColor.TERRACOTTA_WHITE)
                                               .pushReaction(PushReaction.DESTROY)
                                               .randomTicks()
                                               .noCollission()
                                               .noOcclusion()
                                               .instabreak()
                                               .sound(SoundType.WEEPING_VINES)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           Blocks.VINE,
           0.5f
   );
   public static final Block BLUE_WISTERIA_VINES = registerPlantBlock("blue_wisteria_vines",
           new WisteriaVine(FabricBlockSettings.of()
                   .mapColor(MapColor.COLOR_CYAN)
                   .pushReaction(PushReaction.DESTROY)
                   .randomTicks()
                   .noCollission()
                   .instabreak()
                   .noOcclusion()
                   .sound(SoundType.WEEPING_VINES)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           WHITE_WISTERIA_VINES,
           0.5f
   );
   public static final Block PINK_WISTERIA_VINES = registerPlantBlock("pink_wisteria_vines",
           new WisteriaVine(FabricBlockSettings.of()
                   .mapColor(MapColor.COLOR_PINK)
                   .pushReaction(PushReaction.DESTROY)
                   .randomTicks()
                   .noCollission()
                   .instabreak()
                   .noOcclusion()
                   .sound(SoundType.WEEPING_VINES)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           BLUE_WISTERIA_VINES,
           0.5f
   );
   public static final Block PURPLE_WISTERIA_VINES = registerPlantBlock("purple_wisteria_vines",
           new WisteriaVine(FabricBlockSettings.of()
                   .mapColor(MapColor.COLOR_PURPLE)
                   .pushReaction(PushReaction.DESTROY)
                   .randomTicks()
                   .noCollission()
                   .instabreak()
                   .noOcclusion()
                   .sound(SoundType.WEEPING_VINES)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           PINK_WISTERIA_VINES,
           0.5f
   );
   public static final Block WILLOW_VINES = registerPlantBlock("willow_vines",
           new WillowVine(FabricBlockSettings.of()
                                             .mapColor(MapColor.PLANT)
                                             .pushReaction(PushReaction.DESTROY)
                                             .randomTicks()
                                             .noCollission()
                                             .noOcclusion()
                                             .instabreak()
                                             .sound(SoundType.WEEPING_VINES)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           PURPLE_WISTERIA_VINES,
           0.5f
   );
   public static final Block WILLOW_VINES_PLANT = registerPlantBlock("willow_vines_plant",
           new WillowVinePlant(FabricBlockSettings.of()
                                                  .mapColor(MapColor.PLANT)
                                                  .pushReaction(PushReaction.DESTROY)
                                                  .noCollission()
                                                  .noOcclusion()
                                                  .instabreak()
                                                  .sound(SoundType.WEEPING_VINES)
                                                  .dropsLike(WILLOW_VINES))
   );
   public static final Block PURPLE_WISTERIA_VINES_PLANT = registerPlantBlock("purple_wisteria_vines_plant",
           new WisteriaVinePlant(FabricBlockSettings.of()
                   .mapColor(MapColor.COLOR_PURPLE)
                   .pushReaction(PushReaction.DESTROY)
                   .noCollission()
                   .noOcclusion()
                   .instabreak()
                   .sound(SoundType.WEEPING_VINES)
                   .dropsLike(PURPLE_WISTERIA_VINES), PURPLE_WISTERIA_VINES)
   );
   public static final Block PINK_WISTERIA_VINES_PLANT = registerPlantBlock("pink_wisteria_vines_plant",
           new WisteriaVinePlant(FabricBlockSettings.of()
                   .mapColor(MapColor.COLOR_PINK)
                   .pushReaction(PushReaction.DESTROY)
                   .noCollission()
                   .noOcclusion()
                   .instabreak()
                   .sound(SoundType.WEEPING_VINES)
                   .dropsLike(PINK_WISTERIA_VINES), PINK_WISTERIA_VINES)
   );
   public static final Block BLUE_WISTERIA_VINES_PLANT = registerPlantBlock("blue_wisteria_vines_plant",
           new WisteriaVinePlant(FabricBlockSettings.of()
                   .mapColor(MapColor.COLOR_CYAN)
                   .pushReaction(PushReaction.DESTROY)
                   .noCollission()
                   .noOcclusion()
                   .instabreak()
                   .sound(SoundType.WEEPING_VINES)
                   .dropsLike(BLUE_WISTERIA_VINES), BLUE_WISTERIA_VINES)
   );
   public static final Block WHITE_WISTERIA_VINES_PLANT = registerPlantBlock("white_wisteria_vines_plant",
           new WisteriaVinePlant(FabricBlockSettings.of()
                   .mapColor(MapColor.TERRACOTTA_WHITE)
                   .pushReaction(PushReaction.DESTROY)
                   .noCollission()
                   .noOcclusion()
                   .instabreak()
                   .sound(SoundType.WEEPING_VINES)
                   .dropsLike(WHITE_WISTERIA_VINES), WHITE_WISTERIA_VINES)
   );

   private static Block[] getPreviousVanillaWood() {
      Block[] Array = new Block[16];
      Array[13] = Blocks.CHERRY_SIGN;
      Array[15] = Blocks.CHERRY_HANGING_SIGN;
      return Array;
   }
   public static void registerWoods() {}
}
