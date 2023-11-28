package net.hibiscus.naturespirit.entity;

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
              () -> HibiscusWoods.REDWOOD.getPlanks(),
              () -> HibiscusWoods.REDWOOD.getBoatItem(),
              () -> HibiscusWoods.REDWOOD.getChestBoatItem(),
              () -> HibiscusWoods.REDWOOD.getBoatEntityType(),
              () -> HibiscusWoods.REDWOOD.getChestBoatEntityType()
      ),
      SUGI("sugi", () -> HibiscusWoods.SUGI.getPlanks(), () -> HibiscusWoods.SUGI.getBoatItem(), () -> HibiscusWoods.SUGI.getChestBoatItem(), () -> HibiscusWoods.SUGI.getBoatEntityType(), () -> HibiscusWoods.SUGI.getChestBoatEntityType()),
      WISTERIA(
              "wisteria",
              () -> HibiscusWoods.WISTERIA.getPlanks(),
              () -> HibiscusWoods.WISTERIA.getBoatItem(),
              () -> HibiscusWoods.WISTERIA.getChestBoatItem(),
              () -> HibiscusWoods.WISTERIA.getBoatEntityType(),
              () -> HibiscusWoods.WISTERIA.getChestBoatEntityType()
      ),
      FIR("fir", () -> HibiscusWoods.FIR.getPlanks(), () -> HibiscusWoods.FIR.getBoatItem(), () -> HibiscusWoods.FIR.getChestBoatItem(), () -> HibiscusWoods.FIR.getBoatEntityType(), () -> HibiscusWoods.FIR.getChestBoatEntityType()),
      WILLOW(
              "willow",
              () -> HibiscusWoods.WILLOW.getPlanks(),
              () -> HibiscusWoods.WILLOW.getBoatItem(),
              () -> HibiscusWoods.WILLOW.getChestBoatItem(),
              () -> HibiscusWoods.WILLOW.getBoatEntityType(),
              () -> HibiscusWoods.WILLOW.getChestBoatEntityType()
      ),
      ASPEN(
              "aspen",
              () -> HibiscusWoods.ASPEN.getPlanks(),
              () -> HibiscusWoods.ASPEN.getBoatItem(),
              () -> HibiscusWoods.ASPEN.getChestBoatItem(),
              () -> HibiscusWoods.ASPEN.getBoatEntityType(),
              () -> HibiscusWoods.ASPEN.getChestBoatEntityType()
      ),
      MAPLE(
              "maple",
              () -> HibiscusWoods.MAPLE.getPlanks(),
              () -> HibiscusWoods.MAPLE.getBoatItem(),
              () -> HibiscusWoods.MAPLE.getChestBoatItem(),
              () -> HibiscusWoods.MAPLE.getBoatEntityType(),
              () -> HibiscusWoods.MAPLE.getChestBoatEntityType()
      ),
      CYPRESS(
              "cypress",
              () -> HibiscusWoods.CYPRESS.getPlanks(),
              () -> HibiscusWoods.CYPRESS.getBoatItem(),
              () -> HibiscusWoods.CYPRESS.getChestBoatItem(),
              () -> HibiscusWoods.CYPRESS.getBoatEntityType(),
              () -> HibiscusWoods.CYPRESS.getChestBoatEntityType()
      ),
      OLIVE(
              "olive",
              () -> HibiscusWoods.OLIVE.getPlanks(),
              () -> HibiscusWoods.OLIVE.getBoatItem(),
              () -> HibiscusWoods.OLIVE.getChestBoatItem(),
              () -> HibiscusWoods.OLIVE.getBoatEntityType(),
              () -> HibiscusWoods.OLIVE.getChestBoatEntityType()
      ),
      JOSHUA(
              "joshua",
              () -> HibiscusWoods.JOSHUA.getPlanks(),
              () -> HibiscusWoods.JOSHUA.getBoatItem(),
              () -> HibiscusWoods.JOSHUA.getChestBoatItem(),
              () -> HibiscusWoods.JOSHUA.getBoatEntityType(),
              () -> HibiscusWoods.JOSHUA.getChestBoatEntityType()
      ),
      GHAF(
              "ghaf",
                      () -> HibiscusWoods.GHAF.getPlanks(),
              () -> HibiscusWoods.GHAF.getBoatItem(),
              () -> HibiscusWoods.GHAF.getChestBoatItem(),
              () -> HibiscusWoods.GHAF.getBoatEntityType(),
              () -> HibiscusWoods.GHAF.getChestBoatEntityType()
              ),
      PALO_VERDE(
              "palo_verde",
              () -> HibiscusWoods.PALO_VERDE.getPlanks(),
              () -> HibiscusWoods.PALO_VERDE.getBoatItem(),
              () -> HibiscusWoods.PALO_VERDE.getChestBoatItem(),
              () -> HibiscusWoods.PALO_VERDE.getBoatEntityType(),
              () -> HibiscusWoods.PALO_VERDE.getChestBoatEntityType()
      ),
      CEDAR(
              "cedar",
              () -> HibiscusWoods.CEDAR.getPlanks(),
              () -> HibiscusWoods.CEDAR.getBoatItem(),
              () -> HibiscusWoods.CEDAR.getChestBoatItem(),
              () -> HibiscusWoods.CEDAR.getBoatEntityType(),
              () -> HibiscusWoods.CEDAR.getChestBoatEntityType()
      ),
      LARCH(
              "larch",
              () -> HibiscusWoods.LARCH.getPlanks(),
              () -> HibiscusWoods.LARCH.getBoatItem(),
              () -> HibiscusWoods.LARCH.getChestBoatItem(),
              () -> HibiscusWoods.LARCH.getBoatEntityType(),
              () -> HibiscusWoods.LARCH.getChestBoatEntityType()
      ),
//      BANYAN(
//              "banyan",
//              () -> HibiscusWoods.BANYAN.getPlanks(),
//              () -> HibiscusWoods.BANYAN.getBoatItem(),
//              () -> HibiscusWoods.BANYAN.getChestBoatItem(),
//              () -> HibiscusWoods.BANYAN.getBoatEntityType(),
//              () -> HibiscusWoods.BANYAN.getChestBoatEntityType()
//      ),
      COCONUT(
              "coconut",
                      () -> HibiscusWoods.COCONUT.getPlanks(),
              () -> HibiscusWoods.COCONUT.getBoatItem(),
              () -> HibiscusWoods.COCONUT.getChestBoatItem(),
              () -> HibiscusWoods.COCONUT.getBoatEntityType(),
              () -> HibiscusWoods.COCONUT.getChestBoatEntityType()
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