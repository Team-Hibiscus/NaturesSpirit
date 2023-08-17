package net.hibiscus.naturespirit.util;

import net.hibiscus.naturespirit.Constants;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.CatVariant;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.hibiscus.naturespirit.blocks.HibiscusBlocks.BlockHashMap;
import static net.hibiscus.naturespirit.blocks.HibiscusBlocks.ItemHashMap;

public class HibiscusRegister {
   private static final DeferredRegister <Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.MOD_ID);
   private static final DeferredRegister <Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);
   public static void registerBlocks() {
      for(String i: BlockHashMap.keySet()) {
         BLOCKS.register(i, () -> BlockHashMap.get(i));
      }
   }
   public static void registerItems() {
      for(String i: ItemHashMap.keySet()) {
         ITEMS.register(i, () -> ItemHashMap.get(i));
      }
   }
}
