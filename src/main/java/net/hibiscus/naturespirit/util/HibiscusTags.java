package net.hibiscus.naturespirit.util;

import net.hibiscus.naturespirit.NatureSpirit;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class HibiscusTags {
    public static class Items {

        public static final TagKey<Item> PIZZA_TOPPINGS = createTag("pizza_toppings");
        public static final TagKey<Item> DISABLED_PIZZA_TOPPINGS = createTag("disabled_pizza_toppings");

        private static TagKey <Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(NatureSpirit.MOD_ID, name));
        }
    }
}
