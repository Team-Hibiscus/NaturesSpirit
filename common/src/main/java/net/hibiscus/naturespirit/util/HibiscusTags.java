package net.hibiscus.naturespirit.util;

import net.hibiscus.naturespirit.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class HibiscusTags {
   public static class Items {

      public static final TagKey <Item> PIZZA_TOPPINGS = createTag("pizza_toppings");
      public static final TagKey <Item> DISABLED_PIZZA_TOPPINGS = createTag("disabled_pizza_toppings");

      private static TagKey <Item> createTag(String name) {
         return TagKey.create(Registries.ITEM, new ResourceLocation(Constants.MOD_ID, name));
      }
   }

   public static class Blocks {

      public static final TagKey <Block> TURNIP_STEM_GROWS_ON = createTag("turnip_stem_grows_on");
      public static final TagKey <Block> TURNIP_ROOT_REPLACEABLE = createTag("turnip_root_replaceable");

      private static TagKey <Block> createTag(String name) {
         return TagKey.create(Registries.BLOCK, new ResourceLocation(Constants.MOD_ID, name));
      }
   }
}
