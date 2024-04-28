package net.hibiscus.naturespirit.registration.block_registration;

import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.blocks.CoconutBlock;
import net.hibiscus.naturespirit.blocks.SproutingCoconutBlock;
import net.hibiscus.naturespirit.blocks.YoungCoconutBlock;
import net.hibiscus.naturespirit.datagen.HibiscusConfiguredFeatures;
import net.hibiscus.naturespirit.entity.HibiscusBoatEntity;
import net.hibiscus.naturespirit.items.CoconutHalfItem;
import net.hibiscus.naturespirit.registration.HibiscusItemGroups;
import net.hibiscus.naturespirit.registration.WoodSet;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.Optional;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;
import static net.hibiscus.naturespirit.registration.HibiscusRegistryHelper.*;

public class HibiscusWoods {
   public static final WoodSet REDWOOD = new WoodSet(
           new Identifier(MOD_ID, "redwood"),
           MapColor.TERRACOTTA_BROWN,
           MapColor.RED,
           Blocks.CHERRY_LEAVES,
           Blocks.CHERRY_LOG,
           Blocks.BAMBOO_HANGING_SIGN,
           Items.BAMBOO_CHEST_RAFT,
           Blocks.BAMBOO_BUTTON,
           Blocks.CHERRY_SAPLING,
           HibiscusBoatEntity.HibiscusBoat.REDWOOD,
           WoodSet.WoodPreset.DEFAULT,
           false,
           Optional.of(HibiscusConfiguredFeatures.REDWOOD_TREE),
           Optional.of(HibiscusConfiguredFeatures.LARGE_REDWOOD_TREE)
   );
   public static final WoodSet SUGI = new WoodSet(
           new Identifier(MOD_ID, "sugi"),
           MapColor.DEEPSLATE_GRAY,
           MapColor.DIRT_BROWN,
           REDWOOD.getLeaves(),
           REDWOOD.getLog(),
           REDWOOD.getHangingSign(),
           REDWOOD.getChestBoatItem(),
           REDWOOD.getButton(),
           REDWOOD.getSapling(),
           HibiscusBoatEntity.HibiscusBoat.SUGI,
           WoodSet.WoodPreset.FANCY,
           true,
           Optional.of(HibiscusConfiguredFeatures.SUGI_TREE),
           Optional.empty()
   );

   public static final WoodSet WISTERIA = new WoodSet(
           new Identifier(MOD_ID, "wisteria"),
           MapColor.GRAY,
           MapColor.TERRACOTTA_WHITE,
           SUGI.getLeaves(),
           SUGI.getLog(),
           SUGI.getHangingSign(),
           SUGI.getChestBoatItem(),
           SUGI.getButton(),
           SUGI.getSapling(),
           HibiscusBoatEntity.HibiscusBoat.WISTERIA,
           WoodSet.WoodPreset.WISTERIA,
           false,
           HibiscusConfiguredFeatures.WHITE_WISTERIA_TREE
   );
   public static final WoodSet FIR = new WoodSet(
           new Identifier(MOD_ID, "fir"),
           MapColor.GRAY,
           MapColor.DIRT_BROWN,
           WISTERIA.getPurpleLeaves(),
           WISTERIA.getLog(),
           WISTERIA.getHangingSign(),
           WISTERIA.getChestBoatItem(),
           WISTERIA.getButton(),
           WISTERIA.getPurpleSapling(),
           HibiscusBoatEntity.HibiscusBoat.FIR,
           WoodSet.WoodPreset.DEFAULT,
           false,
           HibiscusConfiguredFeatures.FIR_TREE
   );
   public static final WoodSet WILLOW = new WoodSet(
           new Identifier(MOD_ID, "willow"),
           MapColor.TERRACOTTA_BLACK,
           MapColor.TERRACOTTA_BROWN,
           FIR.getLeaves(),
           FIR.getLog(),
           FIR.getHangingSign(),
           FIR.getChestBoatItem(),
           FIR.getButton(),
           FIR.getSapling(),
           HibiscusBoatEntity.HibiscusBoat.WILLOW,
           WoodSet.WoodPreset.WILLOW,
           false,
           HibiscusConfiguredFeatures.WILLOW_TREE
   );
   public static final WoodSet ASPEN = new WoodSet(
           new Identifier(MOD_ID, "aspen"),
           MapColor.WHITE_GRAY,
           MapColor.PALE_YELLOW,
           WILLOW.getLeaves(),
           WILLOW.getLog(),
           WILLOW.getHangingSign(),
           WILLOW.getChestBoatItem(),
           WILLOW.getButton(),
           WILLOW.getSapling(),
           HibiscusBoatEntity.HibiscusBoat.ASPEN,
           WoodSet.WoodPreset.DEFAULT,
           false,
           HibiscusConfiguredFeatures.ASPEN_TREE
   );
   public static final WoodSet MAPLE = new WoodSet(
           new Identifier(MOD_ID,"maple"),
           MapColor.SPRUCE_BROWN,
           MapColor.ORANGE,
           ASPEN.getLeaves(),
           ASPEN.getLog(),
           ASPEN.getHangingSign(),
           ASPEN.getChestBoatItem(),
           ASPEN.getButton(),
           ASPEN.getSapling(),
           HibiscusBoatEntity.HibiscusBoat.MAPLE,
           WoodSet.WoodPreset.MAPLE,
           false,
           HibiscusConfiguredFeatures.RED_MAPLE_TREE
   );
   public static final WoodSet CYPRESS = new WoodSet(
           new Identifier(MOD_ID, "cypress"),
           MapColor.SPRUCE_BROWN,
           MapColor.OAK_TAN,
           MAPLE.getYellowLeaves(),
           MAPLE.getLog(),
           MAPLE.getHangingSign(),
           MAPLE.getChestBoatItem(),
           MAPLE.getButton(),
           MAPLE.getYellowSapling(),
           HibiscusBoatEntity.HibiscusBoat.CYPRESS,
           WoodSet.WoodPreset.DEFAULT,
           false,
           HibiscusConfiguredFeatures.CYPRESS_TREE
   );
   public static final WoodSet OLIVE = new WoodSet(
           new Identifier(MOD_ID, "olive"),
           MapColor.PALE_YELLOW,
           MapColor.PALE_GREEN,
           CYPRESS.getLeaves(),
           CYPRESS.getLog(),
           CYPRESS.getHangingSign(),
           CYPRESS.getChestBoatItem(),
           CYPRESS.getButton(),
           CYPRESS.getSapling(),
           HibiscusBoatEntity.HibiscusBoat.OLIVE,
           WoodSet.WoodPreset.DEFAULT,
           false,
           HibiscusConfiguredFeatures.OLIVE_TREE
   );
   public static final WoodSet JOSHUA = new WoodSet(
           new Identifier(MOD_ID, "joshua"),
           MapColor.PALE_GREEN,
           MapColor.DEEPSLATE_GRAY,
           OLIVE.getLeaves(),
           OLIVE.getLog(),
           OLIVE.getHangingSign(),
           OLIVE.getChestBoatItem(),
           OLIVE.getButton(),
           OLIVE.getSapling(),
           HibiscusBoatEntity.HibiscusBoat.JOSHUA,
           WoodSet.WoodPreset.JOSHUA,
           true,
           HibiscusConfiguredFeatures.JOSHUA_TREE
   );
   public static final WoodSet GHAF = new WoodSet(
           new Identifier(MOD_ID, "ghaf"),
           MapColor.LIGHT_GRAY,
           MapColor.BROWN,
           JOSHUA.getLeaves(),
           JOSHUA.getLog(),
           JOSHUA.getHangingSign(),
           JOSHUA.getChestBoatItem(),
           JOSHUA.getButton(),
           JOSHUA.getSapling(),
           HibiscusBoatEntity.HibiscusBoat.GHAF,
           WoodSet.WoodPreset.SANDY,
           false,
           HibiscusConfiguredFeatures.GHAF_TREE
   );

   public static final Block XERIC_THATCH = registerBlock("xeric_thatch",
           new Block(AbstractBlock.Settings.create().mapColor(MapColor.YELLOW).strength(0.4F).sounds(BlockSoundGroup.GRASS)), HibiscusItemGroups.NS_WOOD_ITEM_GROUP, GHAF.getChestBoatItem());
   public static final Block XERIC_THATCH_STAIRS = registerBlock(
           "xeric_thatch_stairs",
           new StairsBlock(XERIC_THATCH.getDefaultState(), AbstractBlock.Settings.copy(XERIC_THATCH)), HibiscusItemGroups.NS_WOOD_ITEM_GROUP
   );
   public static final Block XERIC_THATCH_SLAB = registerBlock("xeric_thatch_slab", new SlabBlock(AbstractBlock.Settings.create().mapColor(MapColor.YELLOW).sounds(BlockSoundGroup.GRASS).strength(0.4f)), HibiscusItemGroups.NS_WOOD_ITEM_GROUP);
   public static final Block XERIC_THATCH_CARPET = registerBlock("xeric_thatch_carpet",
           new CarpetBlock(AbstractBlock.Settings.create().mapColor(MapColor.YELLOW).strength(0F).pistonBehavior(PistonBehavior.DESTROY).sounds(BlockSoundGroup.GRASS)), HibiscusItemGroups.NS_WOOD_ITEM_GROUP
   );
   public static final WoodSet PALO_VERDE = new WoodSet(
           new Identifier(MOD_ID, "palo_verde"),
           MapColor.YELLOW,
           MapColor.LICHEN_GREEN,
           GHAF.getLeaves(),
           GHAF.getLog(),
           GHAF.getHangingSign(),
           GHAF.getChestBoatItem(),
           GHAF.getButton(),
           GHAF.getSapling(),
           HibiscusBoatEntity.HibiscusBoat.PALO_VERDE,
           WoodSet.WoodPreset.SANDY,
           false,
           HibiscusConfiguredFeatures.PALO_VERDE_TREE
   );
   public static final WoodSet COCONUT = new WoodSet(
           new Identifier(MOD_ID, "coconut"),
           MapColor.DULL_PINK,
           MapColor.BROWN,
           PALO_VERDE.getLeaves(),
           PALO_VERDE.getLog(),
           PALO_VERDE.getHangingSign(),
           PALO_VERDE.getChestBoatItem(),
           PALO_VERDE.getButton(),
           PALO_VERDE.getSapling(),
           HibiscusBoatEntity.HibiscusBoat.COCONUT,
           WoodSet.WoodPreset.NO_SAPLING,
           true,
           HibiscusConfiguredFeatures.COCONUT_TREE
   );

   public static final Block COCONUT_THATCH = registerBlock("coconut_thatch",
           new Block(AbstractBlock.Settings.create().mapColor(MapColor.LIGHT_GRAY).strength(0.4F).sounds(BlockSoundGroup.GRASS)), HibiscusItemGroups.NS_WOOD_ITEM_GROUP, COCONUT.getChestBoatItem());
   public static final Block COCONUT_THATCH_STAIRS = registerBlock(
           "coconut_thatch_stairs",
           new StairsBlock(COCONUT_THATCH.getDefaultState(), AbstractBlock.Settings.copy(COCONUT_THATCH)), HibiscusItemGroups.NS_WOOD_ITEM_GROUP
   );
   public static final Block COCONUT_THATCH_SLAB = registerBlock("coconut_thatch_slab", new SlabBlock(AbstractBlock.Settings.create().mapColor(MapColor.LIGHT_GRAY).sounds(BlockSoundGroup.GRASS).strength(0.4f)), HibiscusItemGroups.NS_WOOD_ITEM_GROUP);
   public static final Block COCONUT_THATCH_CARPET = registerBlock("coconut_thatch_carpet",
           new CarpetBlock(AbstractBlock.Settings.create().mapColor(MapColor.LIGHT_GRAY).strength(0F).pistonBehavior(PistonBehavior.DESTROY).sounds(BlockSoundGroup.GRASS)), HibiscusItemGroups.NS_WOOD_ITEM_GROUP
   );
   public static final Block YOUNG_COCONUT_BLOCK = registerPlantBlock("young_coconut", new YoungCoconutBlock(
           AbstractBlock.Settings.create().strength(1.0F).sounds(BlockSoundGroup.GRASS).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)
   ), HibiscusItemGroups.NS_WOOD_ITEM_GROUP, Items.SWEET_BERRIES, 0.2F);
   public static final Block COCONUT_BLOCK = registerPlantBlock("coconut", new CoconutBlock(
           AbstractBlock.Settings.create().strength(1.0F).sounds(BlockSoundGroup.GRASS).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)
   ), HibiscusItemGroups.NS_WOOD_ITEM_GROUP, Items.SWEET_BERRIES, 0.2F);
   public static final Block COCONUT_SPROUT = registerPlantBlock("coconut_sprout", new SproutingCoconutBlock(
           new SaplingGenerator(NatureSpirit.MOD_ID + "_coconut", Optional.empty(), Optional.of(HibiscusConfiguredFeatures.COCONUT_TREE), Optional.empty()),
           AbstractBlock.Settings.create().strength(1.0F).sounds(BlockSoundGroup.GRASS).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)
   ), HibiscusItemGroups.NS_WOOD_ITEM_GROUP, PALO_VERDE.getSapling(), 0.2F);
   public static final FoodComponent COCONUT_COMPONENT = (new FoodComponent.Builder()).nutrition(6).saturationModifier(0.6F).build();

   public static final FoodComponent YOUNG_COCONUT_COMPONENT = (new FoodComponent.Builder()).nutrition(4).saturationModifier(0.5F).build();

   public static final Item COCONUT_SHELL = registerPlantItem("coconut_shell",
           new Item(new Item.Settings()),
           HibiscusItemGroups.NS_WOOD_ITEM_GROUP,
           Items.BOWL,
           ItemGroups.INGREDIENTS,
           0.1F
   );
   public static final Item YOUNG_COCONUT_SHELL = registerPlantItem("young_coconut_shell",
           new Item(new Item.Settings()),
           HibiscusItemGroups.NS_WOOD_ITEM_GROUP,
           COCONUT_SHELL,
           ItemGroups.INGREDIENTS,
           0.1F
   );
   public static final Item COCONUT_HALF = registerPlantItem("coconut_half",
           new CoconutHalfItem(new Item.Settings().food(COCONUT_COMPONENT), COCONUT_SHELL),
           HibiscusItemGroups.NS_WOOD_ITEM_GROUP,
           Items.BEETROOT,
           ItemGroups.FOOD_AND_DRINK,
           0.1F
   );
   public static final Item YOUNG_COCONUT_HALF = registerPlantItem("young_coconut_half",
           new CoconutHalfItem(new Item.Settings().food(YOUNG_COCONUT_COMPONENT), YOUNG_COCONUT_SHELL),
           HibiscusItemGroups.NS_WOOD_ITEM_GROUP,
           COCONUT_HALF,
           ItemGroups.FOOD_AND_DRINK,
           0.1F
   );
   public static final WoodSet CEDAR = new WoodSet(
           new Identifier(MOD_ID, "cedar"),
           MapColor.TERRACOTTA_MAGENTA,
           MapColor.GRAY,
           COCONUT.getLeaves(),
           COCONUT.getLog(),
           COCONUT.getHangingSign(),
           COCONUT.getChestBoatItem(),
           COCONUT.getButton(),
           COCONUT_SPROUT,
           HibiscusBoatEntity.HibiscusBoat.CEDAR,
           WoodSet.WoodPreset.DEFAULT,
           false,
           HibiscusConfiguredFeatures.CEDAR_TREE
   );

   public static final WoodSet LARCH = new WoodSet(
           new Identifier(MOD_ID, "larch"),
           MapColor.BLUE,
           MapColor.LIGHT_GRAY,
           CEDAR.getLeaves(),
           CEDAR.getLog(),
           CEDAR.getHangingSign(),
           CEDAR.getChestBoatItem(),
           CEDAR.getButton(),
           CEDAR.getSapling(),
           HibiscusBoatEntity.HibiscusBoat.LARCH,
           WoodSet.WoodPreset.LARCH,
           false,
           HibiscusConfiguredFeatures.LARCH_TREE
   );
   public static final Block EVERGREEN_THATCH = registerBlock("evergreen_thatch",
           new Block(AbstractBlock.Settings.create().mapColor(MapColor.LIGHT_GRAY).strength(0.4F).sounds(BlockSoundGroup.GRASS)), HibiscusItemGroups.NS_WOOD_ITEM_GROUP, LARCH.getChestBoatItem());
   public static final Block EVERGREEN_THATCH_STAIRS = registerBlock(
           "evergreen_thatch_stairs",
           new StairsBlock(EVERGREEN_THATCH.getDefaultState(), AbstractBlock.Settings.copy(EVERGREEN_THATCH)), HibiscusItemGroups.NS_WOOD_ITEM_GROUP
   );
   public static final Block EVERGREEN_THATCH_SLAB = registerBlock("evergreen_thatch_slab", new SlabBlock(AbstractBlock.Settings.create().mapColor(MapColor.LIGHT_GRAY).sounds(BlockSoundGroup.GRASS).strength(0.4f)), HibiscusItemGroups.NS_WOOD_ITEM_GROUP);
   public static final Block EVERGREEN_THATCH_CARPET = registerBlock("evergreen_thatch_carpet",
           new CarpetBlock(AbstractBlock.Settings.create().mapColor(MapColor.LIGHT_GRAY).strength(0F).pistonBehavior(PistonBehavior.DESTROY).sounds(BlockSoundGroup.GRASS)), HibiscusItemGroups.NS_WOOD_ITEM_GROUP
   );

      public static final WoodSet MAHOGANY = new WoodSet(
              new Identifier(MOD_ID, "mahogany"),
              MapColor.BROWN,
              MapColor.LIGHT_GRAY,
              LARCH.getLeaves(),
              LARCH.getLog(),
              LARCH.getHangingSign(),
              LARCH.getChestBoatItem(),
              LARCH.getButton(),
              LARCH.getSapling(),
              HibiscusBoatEntity.HibiscusBoat.MAHOGANY,
              WoodSet.WoodPreset.DEFAULT,
              true,
              Optional.empty(),
              Optional.of(HibiscusConfiguredFeatures.MAHOGANY_TREE)
      );

   public static final WoodSet SAXAUL = new WoodSet(
           new Identifier(MOD_ID, "saxaul"),
           MapColor.LIGHT_GRAY,
           MapColor.LIGHT_GRAY,
           MAHOGANY.getLeaves(),
           MAHOGANY.getLog(),
           MAHOGANY.getHangingSign(),
           MAHOGANY.getChestBoatItem(),
           MAHOGANY.getButton(),
           MAHOGANY.getSapling(),
           HibiscusBoatEntity.HibiscusBoat.SAXAUL,
           WoodSet.WoodPreset.SANDY,
           false,
           HibiscusConfiguredFeatures.SAXAUL_TREE
   );

//   public static final WoodSet BANYAN = new WoodSet(
//           new Identifier(MOD_ID, "banyan"),
//           MapColor.BROWN,
//           MapColor.LIGHT_GRAY,
//           LARCH.getLeaves(),
//           LARCH.getLog(),
//           LARCH.getHangingSign(),
//           LARCH.getChestBoatItem(),
//           LARCH.getButton(),
//           LARCH.getSapling(),
//           HibiscusBoatEntity.HibiscusBoat.BANYAN,
//           new BanyanSaplingGenerator(),
//           WoodSet.WoodPreset.DEFAULT,
//           false
//   );

   public static void registerWoods() {
      HibiscusMiscBlocks.registerMiscBlocks();
   }
}
