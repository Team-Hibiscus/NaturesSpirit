package net.hibiscus.naturespirit.util;

import net.hibiscus.naturespirit.NatureSpirit;
import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public class HibiscusTags {
    public static class Biomes {
        public static final TagKey <Biome> IS_WISTERIA =
                createTag("is_wisteria");

        private static TagKey<Biome> createTag(String name) {
            return TagKey.of(Registry.BIOME_KEY, new Identifier(NatureSpirit.MOD_ID, name));
        }
    }
}
