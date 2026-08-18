package net.hibiscus.naturespirit.registration;

import net.hibiscus.naturespirit.NaturesSpirit;
import net.hibiscus.naturespirit.entity.CheeseArrowEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;
import net.minecraft.world.item.Item;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;


public class NSEntityTypes {

  public static final NSRegistrar<EntityType<?>> ENTITIES = NSRegistrar.of(Registries.ENTITY_TYPE);

  public static final List<String> BOAT_WOODS = List.of("redwood", "sugi", "wisteria", "fir", "willow", "aspen", "maple", "cypress", "olive", "joshua",
          "ghaf", "palo_verde", "coconut", "cedar", "larch", "mahogany", "saxaul");

  private static final EntityType.Builder<CheeseArrowEntity> CHEESE_ARROW_ENTITY_BUILDER = EntityType.Builder.of(CheeseArrowEntity::new, MobCategory.MISC);
  public static final NSHolder<EntityType<CheeseArrowEntity>> CHEESE_ARROW = ENTITIES.registerKeyed("cheese_arrow", key -> CHEESE_ARROW_ENTITY_BUILDER.sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build(key));

  private static final Map<String, Supplier<EntityType<Boat>>> BOATS = registerBoats();
  private static final Map<String, Supplier<EntityType<ChestBoat>>> CHEST_BOATS = registerChestBoats();

  public static void bootstrap() {
  }

  public static Supplier<EntityType<Boat>> getBoat(String wood) {
    return BOATS.get(wood);
  }

  public static Supplier<EntityType<ChestBoat>> getChestBoat(String wood) {
    return CHEST_BOATS.get(wood);
  }

  private static Map<String, Supplier<EntityType<Boat>>> registerBoats() {
    Map<String, Supplier<EntityType<Boat>>> boats = new LinkedHashMap<>();
    for (String wood : BOAT_WOODS) {
      Supplier<Item> dropItem = dropItem(wood + "_boat");
      boats.put(wood, ENTITIES.registerKeyed(wood + "_boat", key -> EntityType.Builder.<Boat>of((type, level) -> new Boat(type, level, dropItem), MobCategory.MISC)
              .noLootTable()
              .sized(1.375F, 0.5625F)
              .eyeHeight(0.5625F)
              .clientTrackingRange(10)
              .build(key)));
    }
    return boats;
  }

  private static Map<String, Supplier<EntityType<ChestBoat>>> registerChestBoats() {
    Map<String, Supplier<EntityType<ChestBoat>>> chestBoats = new LinkedHashMap<>();
    for (String wood : BOAT_WOODS) {
      Supplier<Item> dropItem = dropItem(wood + "_chest_boat");
      chestBoats.put(wood, ENTITIES.registerKeyed(wood + "_chest_boat", key -> EntityType.Builder.<ChestBoat>of((type, level) -> new ChestBoat(type, level, dropItem), MobCategory.MISC)
              .noLootTable()
              .sized(1.375F, 0.5625F)
              .eyeHeight(0.5625F)
              .clientTrackingRange(10)
              .build(key)));
    }
    return chestBoats;
  }

  private static Supplier<Item> dropItem(String name) {
    return () -> BuiltInRegistries.ITEM.getValue(NaturesSpirit.id(name));
  }
}
