package net.hibiscus.naturespirit.registration;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.npc.villager.VillagerType;

public class NSVillagers {

  public static final NSRegistrar<VillagerType> VILLAGER_TYPES = NSRegistrar.of(Registries.VILLAGER_TYPE);
  public static final NSHolder<VillagerType> WISTERIA = VILLAGER_TYPES.register("wisteria", VillagerType::new);
  public static final NSHolder<VillagerType> CYPRESS = VILLAGER_TYPES.register("cypress", VillagerType::new);
  public static final NSHolder<VillagerType> ADOBE = VILLAGER_TYPES.register("adobe", VillagerType::new);
  public static final NSHolder<VillagerType> COCONUT = VILLAGER_TYPES.register("coconut", VillagerType::new);

  public static void bootstrap() {
  }
}
