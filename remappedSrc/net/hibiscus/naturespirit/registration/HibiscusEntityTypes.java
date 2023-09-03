package net.hibiscus.naturespirit.registration;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.hibiscus.naturespirit.entity.HibiscusBoatEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.Boat;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;

public class HibiscusEntityTypes {
   public static final EntityType<Boat> REDWOOD_BOAT = register("redwood_boat", createBoatType(false, HibiscusBoatEntity.HibiscusBoat.REDWOOD));
   public static final EntityType<Boat> REDWOOD_CHEST_BOAT = register("redwood_chest_boat", createBoatType(true, HibiscusBoatEntity.HibiscusBoat.REDWOOD));
   public static final EntityType<Boat> SUGI_BOAT = register("sugi_boat", createBoatType(false, HibiscusBoatEntity.HibiscusBoat.SUGI));
   public static final EntityType<Boat> SUGI_CHEST_BOAT = register("sugi_chest_boat", createBoatType(true, HibiscusBoatEntity.HibiscusBoat.SUGI));
   public static final EntityType<Boat> WISTERIA_BOAT = register("wisteria_boat", createBoatType(false, HibiscusBoatEntity.HibiscusBoat.WISTERIA));
   public static final EntityType<Boat> WISTERIA_CHEST_BOAT = register("wisteria_chest_boat", createBoatType(true, HibiscusBoatEntity.HibiscusBoat.WISTERIA));
   public static final EntityType<Boat> FIR_BOAT = register("fir_boat", createBoatType(false, HibiscusBoatEntity.HibiscusBoat.FIR));
   public static final EntityType<Boat> FIR_CHEST_BOAT = register("fir_chest_boat", createBoatType(true, HibiscusBoatEntity.HibiscusBoat.FIR));
   public static final EntityType<Boat> WILLOW_BOAT = register("willow_boat", createBoatType(false, HibiscusBoatEntity.HibiscusBoat.WILLOW));
   public static final EntityType<Boat> WILLOW_CHEST_BOAT = register("willow_chest_boat", createBoatType(true, HibiscusBoatEntity.HibiscusBoat.WILLOW));
   public static final EntityType<Boat> ASPEN_BOAT = register("aspen_boat", createBoatType(false, HibiscusBoatEntity.HibiscusBoat.ASPEN));
   public static final EntityType<Boat> ASPEN_CHEST_BOAT = register("aspen_chest_boat", createBoatType(true, HibiscusBoatEntity.HibiscusBoat.ASPEN));
   public static final EntityType<Boat> MAPLE_BOAT = register("maple_boat", createBoatType(false, HibiscusBoatEntity.HibiscusBoat.MAPLE));
   public static final EntityType<Boat> MAPLE_CHEST_BOAT = register("maple_chest_boat", createBoatType(true, HibiscusBoatEntity.HibiscusBoat.MAPLE));
   public static final EntityType<Boat> CYPRESS_BOAT = register("cypress_boat", createBoatType(false, HibiscusBoatEntity.HibiscusBoat.CYPRESS));
   public static final EntityType<Boat> CYPRESS_CHEST_BOAT = register("cypress_chest_boat", createBoatType(true, HibiscusBoatEntity.HibiscusBoat.CYPRESS));
   public static final EntityType<Boat> OLIVE_BOAT = register("olive_boat", createBoatType(false, HibiscusBoatEntity.HibiscusBoat.OLIVE));
   public static final EntityType<Boat> OLIVE_CHEST_BOAT = register("olive_chest_boat", createBoatType(true, HibiscusBoatEntity.HibiscusBoat.OLIVE));
   public static final EntityType<Boat> JOSHUA_BOAT = register("joshua_boat", createBoatType(false, HibiscusBoatEntity.HibiscusBoat.JOSHUA));
   public static final EntityType<Boat> JOSHUA_CHEST_BOAT = register("joshua_chest_boat", createBoatType(true, HibiscusBoatEntity.HibiscusBoat.JOSHUA));

   public static void registerEntityTypes() {
   }

   private static <T extends EntityType<?>> T register(String id, T type) {
      return Registry.register(BuiltInRegistries.ENTITY_TYPE, new ResourceLocation(MOD_ID, id), type);
   }

   private static EntityType<Boat> createBoatType(boolean chest, HibiscusBoatEntity.HibiscusBoat boat) {
      return FabricEntityTypeBuilder.create(MobCategory.MISC, boat.factory(chest))
              .dimensions(EntityDimensions.scalable(1.375f, 0.5625f))
              .trackRangeChunks(10)
              .build();
   }

}
