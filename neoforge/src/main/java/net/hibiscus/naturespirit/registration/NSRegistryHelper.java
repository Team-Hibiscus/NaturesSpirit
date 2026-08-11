package net.hibiscus.naturespirit.registration;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.HashMap;
import java.util.function.Supplier;

public class NSRegistryHelper {
  public static HashMap<String, DeferredBlock<?>> RenderLayerHashMap = new HashMap<>();
  public static HashMap<String, DeferredBlock<?>> LeavesHashMap = new HashMap<>();


  public static Boolean never(BlockState state, BlockGetter world, BlockPos pos, EntityType<?> type) {
    return false;
  }
  public static Boolean never(BlockState state, BlockGetter world, BlockPos pos) {
    return false;
  }
  public static boolean always(BlockState p_50775_, BlockGetter p_50776_, BlockPos p_50777_) {
    return true;
  }

  public static Boolean ocelotOrParrot(BlockState p_50822_, BlockGetter p_50823_, BlockPos p_50824_, EntityType<?> p_50825_) {
    return p_50825_ == EntityType.OCELOT || p_50825_ == EntityType.PARROT;
  }

  public static <T extends Block> DeferredBlock<T> registerBlockWithoutItem(String name, Supplier<T> block) {
    return NSBlocks.BLOCKS.register(name, block);
  }

  public static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
    DeferredBlock<T> block1 = registerBlockWithoutItem(name, block);
    registerItem(name, () -> new BlockItem(block1.get(), new Item.Properties()));
    return block1;
  }
  public static <T extends Block> DeferredBlock<T> registerTransparentBlockWithoutItem(String name, Supplier<T> block) {
    DeferredBlock<T> block1 = registerBlockWithoutItem(name, block);
    RenderLayerHashMap.put(name, block1);
    return block1;
  }
  public static <T extends Block> DeferredBlock<T> registerTransparentBlock(String name, Supplier<T> block) {
    DeferredBlock<T> block1 = registerBlock(name, block);
    RenderLayerHashMap.put(name, block1);
    return block1;
  }
  public static <T extends Item> DeferredItem<T> registerItem(String name, Supplier<T> item) {
      return NSBlocks.ITEMS.register(name, item);
  }
  public static <T extends Block> DeferredBlock<T> registerTallPlantBlock(String name, Supplier<T> block) {
    DeferredBlock<T> plant = registerBlockWithoutItem(name, block);
    registerItem(name, () -> new DoubleHighBlockItem(plant.get(), new Item.Properties()));
    RenderLayerHashMap.put(name, plant);
    return plant;
  }
}
