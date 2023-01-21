package net.hibiscus.naturespirit.util;

import net.hibiscus.naturespirit.NatureSpirit;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class HibiscusTags {
    public static class Biomes {
        public static final TagKey <Biome> IS_WISTERIA =
                createTag("is_wisteria");

        private static TagKey <Biome> createTag(String name) {
            return TagKey.create(Registries.BIOME, new ResourceLocation(NatureSpirit.MOD_ID, name));
        }
    }
}
