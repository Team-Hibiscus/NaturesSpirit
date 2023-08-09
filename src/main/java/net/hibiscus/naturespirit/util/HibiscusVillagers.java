package net.hibiscus.naturespirit.util;

import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerTypeHelper;
import net.hibiscus.naturespirit.terrablender.HibiscusBiomes;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerType;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;
import static net.minecraft.village.VillagerType.BIOME_TO_TYPE;

public class HibiscusVillagers {
   public static final VillagerType WISTERIA = VillagerTypeHelper.register(new Identifier(MOD_ID, "wisteria"));
   public static final VillagerType CYPRESS = VillagerTypeHelper.register(new Identifier(MOD_ID, "cypress"));
   public static void registerVillagers() {
      BIOME_TO_TYPE.put(HibiscusBiomes.WISTERIA_FOREST, WISTERIA);
      BIOME_TO_TYPE.put(HibiscusBiomes.CYPRESS_FIELDS, CYPRESS);
      BIOME_TO_TYPE.put(HibiscusBiomes.CARNATION_FIELDS, CYPRESS);
   };
}
