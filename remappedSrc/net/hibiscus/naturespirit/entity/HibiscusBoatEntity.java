package net.hibiscus.naturespirit.entity;

import net.hibiscus.naturespirit.registration.HibiscusEntityTypes;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusWoods;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import java.util.function.Supplier;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;

import Z;

public class HibiscusBoatEntity extends Boat {
   private final HibiscusBoat boatData;

   public HibiscusBoatEntity(EntityType <? extends Boat> type, Level world, HibiscusBoat boatData) {
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

   @Override public Item getDropItem() {
      return boatData.boat().asItem();
   }

   public static Boat copy(Boat original, HibiscusBoat boatData) {
      var chest = original instanceof ChestBoat;
      var boat = boatData.factory(chest).create(boatData.entityType(chest), original.getCommandSenderWorld());
      boat.absMoveTo(original.getX(), original.getY(), original.getZ());
      return boat;
   }

   public enum HibiscusBoat implements StringRepresentable {
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
      );

      private final String name;
      private final Supplier <ItemLike> planks;
      private final Supplier <ItemLike> boat;
      private final Supplier <ItemLike> chestBoat;
      private final Supplier <EntityType <Boat>> entityType;
      private final Supplier <EntityType <Boat>> chestEntityType;
      public static final StringRepresentable.EnumCodec <HibiscusBoat> CODEC = StringRepresentable.fromEnum(HibiscusBoatEntity.HibiscusBoat::values);

      HibiscusBoat(String name, Supplier <ItemLike> planks, Supplier <ItemLike> boat, Supplier <ItemLike> chestBoat, Supplier <EntityType <Boat>> entityType, Supplier <EntityType <Boat>> chestEntityType
      ) {
         this.name = name;
         this.planks = planks;
         this.boat = boat;
         this.chestBoat = chestBoat;
         this.entityType = entityType;
         this.chestEntityType = chestEntityType;
      }

      public ItemLike planks() {
         return planks.get();
      }

      public ItemLike boat() {
         return boat.get();
      }

      public ItemLike chestBoat() {
         return chestBoat.get();
      }

      public EntityType <Boat> entityType(boolean chest) {
         return chest ? chestEntityType.get() : entityType.get();
      }

      public static HibiscusBoat getType(String name) {
         return CODEC.byName(name, REDWOOD);
      }

      public EntityType.EntityFactory <Boat> factory(boolean chest) {
         return (type, world) -> chest ? new HibiscusChestBoatEntity(type, world, this) : new HibiscusBoatEntity(type, world, this);
      }

      public ResourceLocation id() {
         return new ResourceLocation(MOD_ID, name);
      }

      @Override public String getSerializedName() {
         return name;
      }
   }
}