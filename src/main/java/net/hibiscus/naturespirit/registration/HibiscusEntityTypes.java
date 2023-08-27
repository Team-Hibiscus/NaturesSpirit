package net.hibiscus.naturespirit.registration;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.hibiscus.naturespirit.entity.HibiscusBoatEntity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;

public class HibiscusEntityTypes {
   public static final EntityType<BoatEntity> REDWOOD_BOAT = register("redwood_boat", createBoatType(false, HibiscusBoatEntity.HibiscusBoat.REDWOOD));
   public static final EntityType<BoatEntity> REDWOOD_CHEST_BOAT = register("redwood_chest_boat", createBoatType(true, HibiscusBoatEntity.HibiscusBoat.REDWOOD));
   public static final EntityType<BoatEntity> SUGI_BOAT = register("sugi_boat", createBoatType(false, HibiscusBoatEntity.HibiscusBoat.SUGI));
   public static final EntityType<BoatEntity> SUGI_CHEST_BOAT = register("sugi_chest_boat", createBoatType(true, HibiscusBoatEntity.HibiscusBoat.SUGI));
   public static final EntityType<BoatEntity> WISTERIA_BOAT = register("wisteria_boat", createBoatType(false, HibiscusBoatEntity.HibiscusBoat.WISTERIA));
   public static final EntityType<BoatEntity> WISTERIA_CHEST_BOAT = register("wisteria_chest_boat", createBoatType(true, HibiscusBoatEntity.HibiscusBoat.WISTERIA));
   public static final EntityType<BoatEntity> FIR_BOAT = register("fir_boat", createBoatType(false, HibiscusBoatEntity.HibiscusBoat.FIR));
   public static final EntityType<BoatEntity> FIR_CHEST_BOAT = register("fir_chest_boat", createBoatType(true, HibiscusBoatEntity.HibiscusBoat.FIR));
   public static final EntityType<BoatEntity> WILLOW_BOAT = register("willow_boat", createBoatType(false, HibiscusBoatEntity.HibiscusBoat.WILLOW));
   public static final EntityType<BoatEntity> WILLOW_CHEST_BOAT = register("willow_chest_boat", createBoatType(true, HibiscusBoatEntity.HibiscusBoat.WILLOW));
   public static final EntityType<BoatEntity> ASPEN_BOAT = register("aspen_boat", createBoatType(false, HibiscusBoatEntity.HibiscusBoat.ASPEN));
   public static final EntityType<BoatEntity> ASPEN_CHEST_BOAT = register("aspen_chest_boat", createBoatType(true, HibiscusBoatEntity.HibiscusBoat.ASPEN));
   public static final EntityType<BoatEntity> MAPLE_BOAT = register("maple_boat", createBoatType(false, HibiscusBoatEntity.HibiscusBoat.MAPLE));
   public static final EntityType<BoatEntity> MAPLE_CHEST_BOAT = register("maple_chest_boat", createBoatType(true, HibiscusBoatEntity.HibiscusBoat.MAPLE));
   public static final EntityType<BoatEntity> CYPRESS_BOAT = register("cypress_boat", createBoatType(false, HibiscusBoatEntity.HibiscusBoat.CYPRESS));
   public static final EntityType<BoatEntity> CYPRESS_CHEST_BOAT = register("cypress_chest_boat", createBoatType(true, HibiscusBoatEntity.HibiscusBoat.CYPRESS));
   public static final EntityType<BoatEntity> OLIVE_BOAT = register("olive_boat", createBoatType(false, HibiscusBoatEntity.HibiscusBoat.OLIVE));
   public static final EntityType<BoatEntity> OLIVE_CHEST_BOAT = register("olive_chest_boat", createBoatType(true, HibiscusBoatEntity.HibiscusBoat.OLIVE));
   public static final EntityType<BoatEntity> JOSHUA_BOAT = register("joshua_boat", createBoatType(false, HibiscusBoatEntity.HibiscusBoat.JOSHUA));
   public static final EntityType<BoatEntity> JOSHUA_CHEST_BOAT = register("joshua_chest_boat", createBoatType(true, HibiscusBoatEntity.HibiscusBoat.JOSHUA));

   public static void registerEntityTypes() {
   }

   private static <T extends EntityType<?>> T register(String id, T type) {
      return Registry.register(Registries.ENTITY_TYPE, new Identifier(MOD_ID, id), type);
   }

   private static EntityType<BoatEntity> createBoatType(boolean chest, HibiscusBoatEntity.HibiscusBoat boat) {
      return FabricEntityTypeBuilder.create(SpawnGroup.MISC, boat.factory(chest))
              .dimensions(EntityDimensions.changing(1.375f, 0.5625f))
              .trackRangeChunks(10)
              .build();
   }

}
