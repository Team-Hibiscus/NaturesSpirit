package net.hibiscus.naturespirit.util;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

import static net.hibiscus.naturespirit.blocks.HibiscusBlocks.BlockHashMap;
import static net.hibiscus.naturespirit.blocks.HibiscusBlocks.ItemHashMap;

public class HibiscusRegister {
   public static void registerBlocks() {
      for(String i: BlockHashMap.keySet()) {
         Registry.register(BuiltInRegistries.BLOCK, i, BlockHashMap.get(i));
      }
   }
   public static void registerItems() {
      for(String i: ItemHashMap.keySet()) {
         Registry.register(BuiltInRegistries.ITEM, i, ItemHashMap.get(i));
      }
   }
}
