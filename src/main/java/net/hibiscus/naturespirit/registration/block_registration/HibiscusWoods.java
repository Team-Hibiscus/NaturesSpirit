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
import net.hibiscus.naturespirit.util.WoodSet;
import net.hibiscus.naturespirit.world.feature.tree.*;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import static net.hibiscus.naturespirit.NatureSpirit.*;
import static net.hibiscus.naturespirit.util.HibiscusRegistryHelper.*;

public class HibiscusWoods {
   public static WoodSet REDWOOD = new WoodSet(
           new Identifier(MOD_ID, "redwood"),
           MapColor.TERRACOTTA_BROWN,
           MapColor.RED,
           Blocks.CHERRY_LEAVES,
           Blocks.CHERRY_LOG,
           Blocks.CHERRY_HANGING_SIGN,
           Items.CHERRY_CHEST_BOAT,
           Blocks.CHERRY_BUTTON,
           Blocks.CHERRY_SAPLING,
           HibiscusBoatEntity.HibiscusBoat.REDWOOD,
           new RedwoodSaplingGenerator(),
           WoodSet.WoodPreset.DEFAULT
   );
   public static WoodSet SUGI = new WoodSet(
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
           new SugiSaplingGenerator(),
           WoodSet.WoodPreset.DEFAULT
   );
   public static Block FRAMED_SUGI_DOOR = registerSecondaryDoorBlock("framed_sugi_door",
           new DoorBlock(FabricBlockSettings.copy(SUGI.getDoor()), SUGI.getBlockSetType()),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           SUGI.getDoor()
   );
   public static Block FRAMED_SUGI_TRAPDOOR = registerSecondaryDoorBlock("framed_sugi_trapdoor", new TrapdoorBlock(
           FabricBlockSettings.create().burnable().instrument(Instrument.BASS).strength(3.0f).sounds(BlockSoundGroup.WOOD).allowsSpawning(HibiscusRegistryHelper::never).nonOpaque(),
           SUGI.getBlockSetType()
   ), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, FRAMED_SUGI_DOOR);

   public static WoodSet WISTERIA = new WoodSet(
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
           new WhiteWisteriaSaplingGenerator(),
           WoodSet.WoodPreset.WISTERIA
   );
   public static WoodSet FIR = new WoodSet(
           new Identifier(MOD_ID, "fir"),
           MapColor.GRAY,
           MapColor.DIRT_BROWN,
           WISTERIA.getPurpleWisteriaLeaves(),
           WISTERIA.getLog(),
           WISTERIA.getHangingSign(),
           WISTERIA.getChestBoatItem(),
           WISTERIA.getButton(),
           WISTERIA.getPurpleWisteriaSapling(),
           HibiscusBoatEntity.HibiscusBoat.FIR,
           new FirSaplingGenerator(),
           WoodSet.WoodPreset.DEFAULT
   );
   public static WoodSet WILLOW = new WoodSet(
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
           new WillowSaplingGenerator(),
           WoodSet.WoodPreset.WILLOW
   );
   public static WoodSet ASPEN = new WoodSet(
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
           new AspenSaplingGenerator(),
           WoodSet.WoodPreset.DEFAULT
   );
   public static WoodSet MAPLE = new WoodSet(
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
           new RedMapleSaplingGenerator(),
           WoodSet.WoodPreset.MAPLE
   );
   public static WoodSet CYPRESS = new WoodSet(
           new Identifier(MOD_ID, "cypress"),
           MapColor.SPRUCE_BROWN,
           MapColor.OAK_TAN,
           MAPLE.getYellowMapleLeaves(),
           MAPLE.getLog(),
           MAPLE.getHangingSign(),
           MAPLE.getChestBoatItem(),
           MAPLE.getButton(),
           MAPLE.getYellowMapleSapling(),
           HibiscusBoatEntity.HibiscusBoat.CYPRESS,
           new CypressSaplingGenerator(),
           WoodSet.WoodPreset.DEFAULT
   );
   public static WoodSet OLIVE = new WoodSet(
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
           new OliveSaplingGenerator(),
           WoodSet.WoodPreset.DEFAULT
   );
   public static WoodSet JOSHUA = new WoodSet(
           new Identifier(MOD_ID, "joshua"),
           MapColor.PALE_GREEN,
           MapColor.PALE_GREEN,
           OLIVE.getLeaves(),
           OLIVE.getLog(),
           OLIVE.getHangingSign(),
           OLIVE.getChestBoatItem(),
           OLIVE.getButton(),
           OLIVE.getSapling(),
           HibiscusBoatEntity.HibiscusBoat.JOSHUA,
           new JoshuaSaplingGenerator(),
           WoodSet.WoodPreset.JOSHUA
   );
   public static void registerWoods() {}
}
