package net.hibiscus.naturespirit.entity;

import net.hibiscus.naturespirit.registration.HibiscusEntityTypes;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusWoods;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.ChestBoatEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.Identifier;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.world.World;

import java.util.function.Supplier;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;

public class HibiscusBoatEntity extends BoatEntity {
   private final HibiscusBoat boatData;

   public HibiscusBoatEntity(EntityType <? extends BoatEntity> type, World world, HibiscusBoat boatData) {
      super(type, world);
      this.boatData = boatData;
   }

   public HibiscusBoat getBoatData() {
      return boatData;
   }

   @Override public Type getVariant() {
      return Type.OAK;
   }

   @Override public void setVariant(Type type) {
   }

   @Override public Item asItem() {
      return boatData.boat().asItem();
   }

   public static BoatEntity copy(BoatEntity original, HibiscusBoat boatData) {
      var chest = original instanceof ChestBoatEntity;
      var boat = boatData.factory(chest).create(boatData.entityType(chest), original.getEntityWorld());
      boat.updatePosition(original.getX(), original.getY(), original.getZ());
      return boat;
   }

   public enum HibiscusBoat implements StringIdentifiable {
      REDWOOD(
              "redwood",
              () -> HibiscusWoods.REDWOOD[4],
              () -> HibiscusWoods.REDWOOD_BOAT,
              () -> HibiscusWoods.REDWOOD_CHEST_BOAT,
              () -> HibiscusEntityTypes.REDWOOD_BOAT,
              () -> HibiscusEntityTypes.REDWOOD_CHEST_BOAT
      ),
      SUGI("sugi", () -> HibiscusWoods.SUGI[4], () -> HibiscusWoods.SUGI_BOAT, () -> HibiscusWoods.SUGI_CHEST_BOAT, () -> HibiscusEntityTypes.SUGI_BOAT, () -> HibiscusEntityTypes.SUGI_CHEST_BOAT),
      WISTERIA(
              "wisteria",
              () -> HibiscusWoods.WISTERIA[4],
              () -> HibiscusWoods.WISTERIA_BOAT,
              () -> HibiscusWoods.WISTERIA_CHEST_BOAT,
              () -> HibiscusEntityTypes.WISTERIA_BOAT,
              () -> HibiscusEntityTypes.WISTERIA_CHEST_BOAT
      ),
      FIR("fir", () -> HibiscusWoods.FIR[4], () -> HibiscusWoods.FIR_BOAT, () -> HibiscusWoods.FIR_CHEST_BOAT, () -> HibiscusEntityTypes.FIR_BOAT, () -> HibiscusEntityTypes.FIR_CHEST_BOAT),
      WILLOW(
              "willow",
              () -> HibiscusWoods.WILLOW[4],
              () -> HibiscusWoods.WILLOW_BOAT,
              () -> HibiscusWoods.WILLOW_CHEST_BOAT,
              () -> HibiscusEntityTypes.WILLOW_BOAT,
              () -> HibiscusEntityTypes.WILLOW_CHEST_BOAT
      ),
      ASPEN(
              "aspen",
              () -> HibiscusWoods.ASPEN[4],
              () -> HibiscusWoods.ASPEN_BOAT,
              () -> HibiscusWoods.ASPEN_CHEST_BOAT,
              () -> HibiscusEntityTypes.ASPEN_BOAT,
              () -> HibiscusEntityTypes.ASPEN_CHEST_BOAT
      ),
      MAPLE(
              "maple",
              () -> HibiscusWoods.MAPLE[4],
              () -> HibiscusWoods.MAPLE_BOAT,
              () -> HibiscusWoods.MAPLE_CHEST_BOAT,
              () -> HibiscusEntityTypes.MAPLE_BOAT,
              () -> HibiscusEntityTypes.MAPLE_CHEST_BOAT
      ),
      CYPRESS(
              "cypress",
              () -> HibiscusWoods.CYPRESS[4],
              () -> HibiscusWoods.CYPRESS_BOAT,
              () -> HibiscusWoods.CYPRESS_CHEST_BOAT,
              () -> HibiscusEntityTypes.CYPRESS_BOAT,
              () -> HibiscusEntityTypes.CYPRESS_CHEST_BOAT
      ),
      OLIVE(
              "olive",
              () -> HibiscusWoods.OLIVE[4],
              () -> HibiscusWoods.OLIVE_BOAT,
              () -> HibiscusWoods.OLIVE_CHEST_BOAT,
              () -> HibiscusEntityTypes.OLIVE_BOAT,
              () -> HibiscusEntityTypes.OLIVE_CHEST_BOAT
      ),
      JOSHUA(
              "joshua",
              () -> HibiscusWoods.JOSHUA[4],
              () -> HibiscusWoods.JOSHUA_BOAT,
              () -> HibiscusWoods.JOSHUA_CHEST_BOAT,
              () -> HibiscusEntityTypes.JOSHUA_BOAT,
              () -> HibiscusEntityTypes.JOSHUA_CHEST_BOAT
      );

      private final String name;
      private final Supplier <ItemConvertible> planks;
      private final Supplier <ItemConvertible> boat;
      private final Supplier <ItemConvertible> chestBoat;
      private final Supplier <EntityType <BoatEntity>> entityType;
      private final Supplier <EntityType <BoatEntity>> chestEntityType;
      public static final StringIdentifiable.Codec <HibiscusBoat> CODEC = StringIdentifiable.createCodec(HibiscusBoatEntity.HibiscusBoat::values);

      HibiscusBoat(String name, Supplier <ItemConvertible> planks, Supplier <ItemConvertible> boat, Supplier <ItemConvertible> chestBoat, Supplier <EntityType <BoatEntity>> entityType, Supplier <EntityType <BoatEntity>> chestEntityType
      ) {
         this.name = name;
         this.planks = planks;
         this.boat = boat;
         this.chestBoat = chestBoat;
         this.entityType = entityType;
         this.chestEntityType = chestEntityType;
      }

      public ItemConvertible planks() {
         return planks.get();
      }

      public ItemConvertible boat() {
         return boat.get();
      }

      public ItemConvertible chestBoat() {
         return chestBoat.get();
      }

      public EntityType <BoatEntity> entityType(boolean chest) {
         return chest ? chestEntityType.get() : entityType.get();
      }

      public static HibiscusBoat getType(String name) {
         return CODEC.byId(name, REDWOOD);
      }

      public EntityType.EntityFactory <BoatEntity> factory(boolean chest) {
         return (type, world) -> chest ? new HibiscusChestBoatEntity(type, world, this) : new HibiscusBoatEntity(type, world, this);
      }

      public Identifier id() {
         return new Identifier(MOD_ID, name);
      }

      @Override public String asString() {
         return name;
      }
   }
}