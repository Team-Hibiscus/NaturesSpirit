package net.hibiscus.naturespirit.util;

import com.mojang.serialization.Lifecycle;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.registration.HibiscusItemGroups;
import net.minecraft.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import java.util.HashMap;

public class HibiscusRegistryHelper {

   public static HashMap <String, WoodSet> WoodHashMap = new HashMap <>();
   public static HashMap <String, Block[]> SaplingHashMap = new HashMap <>();
   public static HashMap <String, Block> LeavesHashMap = new HashMap <>();
   public static HashMap <String, Block> RenderLayerHashMap = new HashMap <>();
   public static HashMap <String, Item> NatureSpiritItemHashMap = new HashMap <>();


   public static Boolean never(BlockState state, BlockGetter world, BlockPos pos, EntityType <?> type) {
      return false;
   }

   public static Block registerPottedPlant(String name, Block plant) {
      Block pottedPlant = registerBlock("potted_" + name, new FlowerPotBlock(plant, FabricBlockSettings.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
      RenderLayerHashMap.put("potted_" + name, pottedPlant);
      return pottedPlant;
   }

   public static Block registerBlock(String name, Block block) {
      return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(NatureSpirit.MOD_ID, name), block);
   }

   public static Block registerBlock(String name, Block block, ResourceKey <CreativeModeTab> tab) {
      registerBlockItem(name, block, tab);
      return registerBlock(name, block);
   }

   public static Block registerBlock(String name, Block block, ResourceKey <CreativeModeTab> tab, Block blockBefore, ResourceKey <CreativeModeTab> secondaryTab) {
      Block block1 = registerBlock(name, block, tab);
      ItemGroupEvents.modifyEntriesEvent(secondaryTab).register(entries -> entries.addAfter(blockBefore, block1.asItem()));
      return block1;
   }

   public static Block registerSecondaryDoorBlock(String name, Block block, ResourceKey <CreativeModeTab> tab, Block blockBefore) {
      Block block1 = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(NatureSpirit.MOD_ID, name), block);
      RenderLayerHashMap.put(name, block1);
      registerBlockItem(name, block, tab, blockBefore, CreativeModeTabs.BUILDING_BLOCKS);
      return block1;
   }

   public static Block registerPlantBlock(String name, Block block, ResourceKey <CreativeModeTab> tab, Block blockBefore, float compost) {
      Block Plant = registerBlock(name, block, tab, blockBefore, CreativeModeTabs.NATURAL_BLOCKS);
      RenderLayerHashMap.put(name, block);
      CompostingChanceRegistry.INSTANCE.add(block, compost);
      return Plant;
   }

   public static Block registerPlantBlock(String name, Block block) {
      Block Plant = registerBlock(name, block);
      RenderLayerHashMap.put(name, block);
      return Plant;
   }

   public static void registerBlockItem(String name, Block block, ResourceKey <CreativeModeTab> tab) {
      registerItem(name, new BlockItem(block, new FabricItemSettings()), tab);
   }
   public static void registerBlockItem(String name, Block block) {
      registerItem(name, new BlockItem(block, new FabricItemSettings()));
   }

   public static void registerBlockItem(String name, Block block, ResourceKey <CreativeModeTab> tab, Block blockBefore, ResourceKey <CreativeModeTab> secondaryTab) {
      Item item = registerItem(name, new BlockItem(block, new FabricItemSettings()));
      ItemGroupEvents.modifyEntriesEvent(secondaryTab).register(entries -> entries.addAfter(blockBefore, item.asItem()));
      ItemGroupEvents.modifyEntriesEvent(tab).register(entries -> entries.addAfter(blockBefore, item.asItem()));
   }

   public static Item registerItem(String name, Item item) {
      Item item1 = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(NatureSpirit.MOD_ID, name), item);
      NatureSpiritItemHashMap.put(name, item1);
      return item1;
   }

   public static Item registerItem(String name, Item item, ResourceKey <CreativeModeTab> tab) {
      ItemGroupEvents.modifyEntriesEvent(tab).register(entries -> entries.accept(item.asItem()));
      return registerItem(name, item);
   }


   public static Item registerItem(String name, Item item, ResourceKey <CreativeModeTab> tab, Item itemBefore, ResourceKey <CreativeModeTab> secondaryTab) {
      ItemGroupEvents.modifyEntriesEvent(secondaryTab).register(entries -> entries.addAfter(itemBefore, item));
      return registerItem(name, item, tab);
   }

   public static Item registerPlantItem(String name, Item item, ResourceKey <CreativeModeTab> tab, Item itemBefore, ResourceKey <CreativeModeTab> secondaryTab, float compost) {
      CompostingChanceRegistry.INSTANCE.add(item, compost);
      return registerItem(name, item, tab, itemBefore, secondaryTab);
   }
}
