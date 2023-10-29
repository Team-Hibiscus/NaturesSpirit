package net.hibiscus.naturespirit.util;

import net.hibiscus.naturespirit.NatureSpirit;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class HibiscusTags {
   public static class Items {

      public static final TagKey <Item> PIZZA_TOPPINGS = createTag("pizza_toppings");
      public static final TagKey <Item> DISABLED_PIZZA_TOPPINGS = createTag("disabled_pizza_toppings");
      public static final TagKey <Item> CHEESE_MAKER = createTag("cheese_maker");
      public static final TagKey <Item> COCONUT_ITEMS = createTag("coconut_items");

      private static TagKey <Item> createTag(String name) {
         return TagKey.of(RegistryKeys.ITEM, new Identifier(NatureSpirit.MOD_ID, name));
      }
   }

   public static class Blocks {

      public static final TagKey <Block> TURNIP_STEM_GROWS_ON = createTag("turnip_stem_grows_on");
      public static final TagKey <Block> TURNIP_ROOT_REPLACEABLE = createTag("turnip_root_replaceable");
      public static final TagKey <Block> SHIITAKE_MUSHROOM_GROW_BLOCK = createTag("shiitake_mushroom_grow_block");

      private static TagKey <Block> createTag(String name) {
         return TagKey.of(RegistryKeys.BLOCK, new Identifier(NatureSpirit.MOD_ID, name));
      }
   }
}
