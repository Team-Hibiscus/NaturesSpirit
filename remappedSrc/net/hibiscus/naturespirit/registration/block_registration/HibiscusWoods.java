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
import net.hibiscus.naturespirit.world.tree.*;
import net.minecraft.block.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

import static net.hibiscus.naturespirit.NatureSpirit.*;
import static net.hibiscus.naturespirit.util.HibiscusRegistryHelper.*;

public class HibiscusWoods {
   public static WoodSet REDWOOD = new WoodSet(
           new ResourceLocation(MOD_ID, "redwood"),
           MapColor.TERRACOTTA_BROWN,
           MapColor.COLOR_RED,
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
           new ResourceLocation(MOD_ID, "sugi"),
           MapColor.DEEPSLATE,
           MapColor.DIRT,
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
   public static Block FRAMED_SUGI_TRAPDOOR = registerSecondaryDoorBlock("framed_sugi_trapdoor", new TrapDoorBlock(
           FabricBlockSettings.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(3.0f).sound(SoundType.WOOD).isValidSpawn(HibiscusRegistryHelper::never).noOcclusion(),
           SUGI.getBlockSetType()
   ), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, FRAMED_SUGI_DOOR);

   public static WoodSet WISTERIA = new WoodSet(
           new ResourceLocation(MOD_ID, "wisteria"),
           MapColor.COLOR_GRAY,
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
           new ResourceLocation(MOD_ID, "fir"),
           MapColor.COLOR_GRAY,
           MapColor.DIRT,
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
           new ResourceLocation(MOD_ID, "willow"),
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
           new ResourceLocation(MOD_ID, "aspen"),
           MapColor.WOOL,
           MapColor.SAND,
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
           new ResourceLocation(MOD_ID,"maple"),
           MapColor.PODZOL,
           MapColor.COLOR_ORANGE,
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
           new ResourceLocation(MOD_ID, "cypress"),
           MapColor.PODZOL,
           MapColor.WOOD,
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
           new ResourceLocation(MOD_ID, "olive"),
           MapColor.SAND,
           MapColor.GRASS,
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
           new ResourceLocation(MOD_ID, "joshua"),
           MapColor.GRASS,
           MapColor.GRASS,
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
