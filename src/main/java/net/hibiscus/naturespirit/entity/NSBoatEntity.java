package net.hibiscus.naturespirit.entity;

import net.hibiscus.naturespirit.registration.NSWoods;
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

public class NSBoatEntity extends BoatEntity {
   private final HibiscusBoat boatData;

   public NSBoatEntity(EntityType <? extends BoatEntity> type, World world, HibiscusBoat boatData) {
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
              () -> NSWoods.REDWOOD.getPlanks(),
              () -> NSWoods.REDWOOD.getBoatItem(),
              () -> NSWoods.REDWOOD.getChestBoatItem(),
              () -> NSWoods.REDWOOD.getBoatEntityType(),
              () -> NSWoods.REDWOOD.getChestBoatEntityType()
      ),
      SUGI("sugi", () -> NSWoods.SUGI.getPlanks(), () -> NSWoods.SUGI.getBoatItem(), () -> NSWoods.SUGI.getChestBoatItem(), () -> NSWoods.SUGI.getBoatEntityType(), () -> NSWoods.SUGI.getChestBoatEntityType()),
      WISTERIA(
              "wisteria",
              () -> NSWoods.WISTERIA.getPlanks(),
              () -> NSWoods.WISTERIA.getBoatItem(),
              () -> NSWoods.WISTERIA.getChestBoatItem(),
              () -> NSWoods.WISTERIA.getBoatEntityType(),
              () -> NSWoods.WISTERIA.getChestBoatEntityType()
      ),
      FIR("fir", () -> NSWoods.FIR.getPlanks(), () -> NSWoods.FIR.getBoatItem(), () -> NSWoods.FIR.getChestBoatItem(), () -> NSWoods.FIR.getBoatEntityType(), () -> NSWoods.FIR.getChestBoatEntityType()),
      WILLOW(
              "willow",
              () -> NSWoods.WILLOW.getPlanks(),
              () -> NSWoods.WILLOW.getBoatItem(),
              () -> NSWoods.WILLOW.getChestBoatItem(),
              () -> NSWoods.WILLOW.getBoatEntityType(),
              () -> NSWoods.WILLOW.getChestBoatEntityType()
      ),
      ASPEN(
              "aspen",
              () -> NSWoods.ASPEN.getPlanks(),
              () -> NSWoods.ASPEN.getBoatItem(),
              () -> NSWoods.ASPEN.getChestBoatItem(),
              () -> NSWoods.ASPEN.getBoatEntityType(),
              () -> NSWoods.ASPEN.getChestBoatEntityType()
      ),
      MAPLE(
              "maple",
              () -> NSWoods.MAPLE.getPlanks(),
              () -> NSWoods.MAPLE.getBoatItem(),
              () -> NSWoods.MAPLE.getChestBoatItem(),
              () -> NSWoods.MAPLE.getBoatEntityType(),
              () -> NSWoods.MAPLE.getChestBoatEntityType()
      ),
      CYPRESS(
              "cypress",
              () -> NSWoods.CYPRESS.getPlanks(),
              () -> NSWoods.CYPRESS.getBoatItem(),
              () -> NSWoods.CYPRESS.getChestBoatItem(),
              () -> NSWoods.CYPRESS.getBoatEntityType(),
              () -> NSWoods.CYPRESS.getChestBoatEntityType()
      ),
      OLIVE(
              "olive",
              () -> NSWoods.OLIVE.getPlanks(),
              () -> NSWoods.OLIVE.getBoatItem(),
              () -> NSWoods.OLIVE.getChestBoatItem(),
              () -> NSWoods.OLIVE.getBoatEntityType(),
              () -> NSWoods.OLIVE.getChestBoatEntityType()
      ),
      SAXAUL(
              "saxaul",
              () -> NSWoods.SAXAUL.getPlanks(),
              () -> NSWoods.SAXAUL.getBoatItem(),
              () -> NSWoods.SAXAUL.getChestBoatItem(),
              () -> NSWoods.SAXAUL.getBoatEntityType(),
              () -> NSWoods.SAXAUL.getChestBoatEntityType()
      ),
      JOSHUA(
              "joshua",
              () -> NSWoods.JOSHUA.getPlanks(),
              () -> NSWoods.JOSHUA.getBoatItem(),
              () -> NSWoods.JOSHUA.getChestBoatItem(),
              () -> NSWoods.JOSHUA.getBoatEntityType(),
              () -> NSWoods.JOSHUA.getChestBoatEntityType()
      ),
      GHAF(
              "ghaf",
              () -> NSWoods.GHAF.getPlanks(),
              () -> NSWoods.GHAF.getBoatItem(),
              () -> NSWoods.GHAF.getChestBoatItem(),
              () -> NSWoods.GHAF.getBoatEntityType(),
              () -> NSWoods.GHAF.getChestBoatEntityType()
      ),
      PALO_VERDE(
              "palo_verde",
              () -> NSWoods.PALO_VERDE.getPlanks(),
              () -> NSWoods.PALO_VERDE.getBoatItem(),
              () -> NSWoods.PALO_VERDE.getChestBoatItem(),
              () -> NSWoods.PALO_VERDE.getBoatEntityType(),
              () -> NSWoods.PALO_VERDE.getChestBoatEntityType()
      ),
      CEDAR(
              "cedar",
              () -> NSWoods.CEDAR.getPlanks(),
              () -> NSWoods.CEDAR.getBoatItem(),
              () -> NSWoods.CEDAR.getChestBoatItem(),
              () -> NSWoods.CEDAR.getBoatEntityType(),
              () -> NSWoods.CEDAR.getChestBoatEntityType()
      ),
      LARCH(
              "larch",
              () -> NSWoods.LARCH.getPlanks(),
              () -> NSWoods.LARCH.getBoatItem(),
              () -> NSWoods.LARCH.getChestBoatItem(),
              () -> NSWoods.LARCH.getBoatEntityType(),
              () -> NSWoods.LARCH.getChestBoatEntityType()
      ),
      MAHOGANY(
              "mahogany",
              () -> NSWoods.MAHOGANY.getPlanks(),
              () -> NSWoods.MAHOGANY.getBoatItem(),
              () -> NSWoods.MAHOGANY.getChestBoatItem(),
              () -> NSWoods.MAHOGANY.getBoatEntityType(),
              () -> NSWoods.MAHOGANY.getChestBoatEntityType()
      ),
      //      BANYAN(
      //              "banyan",
      //              () -> NSWoods.BANYAN.getPlanks(),
      //              () -> NSWoods.BANYAN.getBoatItem(),
      //              () -> NSWoods.BANYAN.getChestBoatItem(),
      //              () -> NSWoods.BANYAN.getBoatEntityType(),
      //              () -> NSWoods.BANYAN.getChestBoatEntityType()
      //      ),
      COCONUT(
              "coconut",
              () -> NSWoods.COCONUT.getPlanks(),
              () -> NSWoods.COCONUT.getBoatItem(),
              () -> NSWoods.COCONUT.getChestBoatItem(),
              () -> NSWoods.COCONUT.getBoatEntityType(),
              () -> NSWoods.COCONUT.getChestBoatEntityType()
      );

      private final String name;
      private final Supplier <ItemConvertible> planks;
      private final Supplier <ItemConvertible> boat;
      private final Supplier <ItemConvertible> chestBoat;
      private final Supplier <EntityType <BoatEntity>> entityType;
      private final Supplier <EntityType <BoatEntity>> chestEntityType;
      public static final StringIdentifiable.EnumCodec <HibiscusBoat> CODEC = StringIdentifiable.createCodec(HibiscusBoat::values);

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
         return (type, world) -> chest ? new NSChestBoatEntity(type, world, this) : new NSBoatEntity(type, world, this);
      }

      public Identifier id() {
         return Identifier.of(MOD_ID, name);
      }

      @Override public String asString() {
         return name;
      }
   }
}