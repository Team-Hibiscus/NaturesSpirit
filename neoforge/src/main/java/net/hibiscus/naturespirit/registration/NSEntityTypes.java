package net.hibiscus.naturespirit.registration;

import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.entity.CheeseArrowEntity;
import net.hibiscus.naturespirit.entity.NSBoatEntity;
import net.hibiscus.naturespirit.entity.NSChestBoatEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class NSEntityTypes {

  public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, NatureSpirit.MOD_ID);

  private static final EntityType.Builder<CheeseArrowEntity> CHEESE_ARROW_ENTITY_BUILDER = EntityType.Builder.of(CheeseArrowEntity::new, MobCategory.MISC);
  public static final Supplier<EntityType<CheeseArrowEntity>> CHEESE_ARROW = ENTITIES.register("cheese_arrow", () -> CHEESE_ARROW_ENTITY_BUILDER.sized(0.5F, 0.5F).setTrackingRange(4).setUpdateInterval(20).build("cheese_arrow"));

  public static final Supplier<EntityType<NSBoatEntity>> NS_BOAT =
          ENTITIES.register("ns_boat", () -> EntityType.Builder.<NSBoatEntity>of(NSBoatEntity::new, MobCategory.MISC)
                  .sized(1.375f, 0.5625f).build("ns_boat"));
  public static final Supplier<EntityType<NSChestBoatEntity>> NS_CHEST_BOAT =
          ENTITIES.register("ns_chest_boat", () -> EntityType.Builder.<NSChestBoatEntity>of(NSChestBoatEntity::new, MobCategory.MISC)
                  .sized(1.375f, 0.5625f).build("ns_chest_boat"));

}
