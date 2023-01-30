package net.hibiscus.naturespirit.util;

import net.hibiscus.naturespirit.NatureSpirit;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class HibiscusTags {
    public static class Biomes {
        public static final TagKey <Biome> IS_WISTERIA =
                createTag("is_wisteria");

        private static TagKey <Biome> createTag(String name) {
            return TagKey.of(RegistryKeys.BIOME, new Identifier(NatureSpirit.MOD_ID, name));
        }
    }
}
