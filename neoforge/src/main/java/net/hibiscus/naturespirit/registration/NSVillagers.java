
package net.hibiscus.naturespirit.registration;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.npc.VillagerType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;

public class NSVillagers {

  public static final DeferredRegister<VillagerType> VILLAGER_TYPES = DeferredRegister.create(Registries.VILLAGER_TYPE, MOD_ID);
  public static final Supplier<VillagerType> WISTERIA = VILLAGER_TYPES.register( "wisteria", () -> new VillagerType("wisteria"));
  public static final Supplier<VillagerType> CYPRESS = VILLAGER_TYPES.register( "cypress", () -> new VillagerType("cypress"));
  public static final Supplier<VillagerType> ADOBE = VILLAGER_TYPES.register( "adobe", () -> new VillagerType("adobe"));
  public static final Supplier<VillagerType> COCONUT = VILLAGER_TYPES.register( "coconut", () -> new VillagerType("coconut"));

}

