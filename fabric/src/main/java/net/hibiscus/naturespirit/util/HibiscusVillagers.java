package net.hibiscus.naturespirit.util;

import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerTypeHelper;
import net.hibiscus.naturespirit.datagen.HibiscusBiomes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.npc.VillagerType;

import static net.hibiscus.naturespirit.Constants.MOD_ID;
import static net.minecraft.world.entity.npc.VillagerType.BY_BIOME;

public class HibiscusVillagers {
   public static final VillagerType WISTERIA = VillagerTypeHelper.register(new ResourceLocation(MOD_ID, "wisteria"));
   public static final VillagerType CYPRESS = VillagerTypeHelper.register(new ResourceLocation(MOD_ID, "cypress"));
   public static final VillagerType ADOBE = VillagerTypeHelper.register(new ResourceLocation(MOD_ID, "adobe"));
   public static void registerVillagers() {
      BY_BIOME.put(HibiscusBiomes.WISTERIA_FOREST, WISTERIA);
      BY_BIOME.put(HibiscusBiomes.CYPRESS_FIELDS, CYPRESS);
      BY_BIOME.put(HibiscusBiomes.CARNATION_FIELDS, CYPRESS);
      BY_BIOME.put(HibiscusBiomes.STRATIFIED_DESERT, ADOBE);
      BY_BIOME.put(HibiscusBiomes.LIVELY_DUNES, ADOBE);
      BY_BIOME.put(HibiscusBiomes.BLOOMING_DUNES, ADOBE);
   };
}
