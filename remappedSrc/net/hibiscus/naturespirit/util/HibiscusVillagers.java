package net.hibiscus.naturespirit.util;

import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerTypeHelper;
import net.hibiscus.naturespirit.world.HibiscusBiomes;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerType;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;
import static net.minecraft.village.VillagerType.BIOME_TO_TYPE;
import static net.minecraft.village.VillagerType.DESERT;

public class HibiscusVillagers {
   public static final VillagerType WISTERIA = VillagerTypeHelper.register(new Identifier(MOD_ID, "wisteria"));
   public static final VillagerType CYPRESS = VillagerTypeHelper.register(new Identifier(MOD_ID, "cypress"));
   public static final VillagerType ADOBE = VillagerTypeHelper.register(new Identifier(MOD_ID, "adobe"));

   public static void registerVillagers() {
      BIOME_TO_TYPE.put(HibiscusBiomes.WISTERIA_FOREST, WISTERIA);
      BIOME_TO_TYPE.put(HibiscusBiomes.CYPRESS_FIELDS, CYPRESS);
      BIOME_TO_TYPE.put(HibiscusBiomes.CARNATION_FIELDS, CYPRESS);
      BIOME_TO_TYPE.put(HibiscusBiomes.LAVENDER_FIELDS, CYPRESS);
      BIOME_TO_TYPE.put(HibiscusBiomes.STRATIFIED_DESERT, ADOBE);
      BIOME_TO_TYPE.put(HibiscusBiomes.LIVELY_DUNES, ADOBE);
      BIOME_TO_TYPE.put(HibiscusBiomes.BLOOMING_DUNES, ADOBE);
      BIOME_TO_TYPE.put(HibiscusBiomes.DRYLANDS, DESERT);
      BIOME_TO_TYPE.put(HibiscusBiomes.WOODED_DRYLANDS, DESERT);
   }

}
