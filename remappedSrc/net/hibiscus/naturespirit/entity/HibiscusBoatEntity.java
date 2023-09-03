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

   public HibiscusBoatEntity(EntityType<? extends Boat> type, Level world, HibiscusBoat boatData) {
      super(type, world);
      this.boatData = boatData;
   }

   public HibiscusBoat getBoatData() {
      return boatData;
   }

   @Override
   public Type getVariant() {
      return Type.OAK;
   }

   @Override
   public void setVariant(Type type) {
   }

   @Override
   public Item getDropItem() {
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
              () -> HibiscusWoods.REDWOOD[4],
              () -> HibiscusWoods.REDWOOD_BOAT,
              () -> HibiscusWoods.REDWOOD_CHEST_BOAT,
              () -> HibiscusEntityTypes.REDWOOD_BOAT,
              () -> HibiscusEntityTypes.REDWOOD_CHEST_BOAT
      ),
      SUGI("sugi",
              () -> HibiscusWoods.SUGI[4],
              () -> HibiscusWoods.SUGI_BOAT,
              () -> HibiscusWoods.SUGI_CHEST_BOAT,
              () -> HibiscusEntityTypes.SUGI_BOAT,
              () -> HibiscusEntityTypes.SUGI_CHEST_BOAT
      ),
      WISTERIA("wisteria",
              () -> HibiscusWoods.WISTERIA[4],
              () -> HibiscusWoods.WISTERIA_BOAT,
              () -> HibiscusWoods.WISTERIA_CHEST_BOAT,
              () -> HibiscusEntityTypes.WISTERIA_BOAT,
              () -> HibiscusEntityTypes.WISTERIA_CHEST_BOAT
      ),
      FIR("fir",
              () -> HibiscusWoods.FIR[4],
              () -> HibiscusWoods.FIR_BOAT,
              () -> HibiscusWoods.FIR_CHEST_BOAT,
              () -> HibiscusEntityTypes.FIR_BOAT,
              () -> HibiscusEntityTypes.FIR_CHEST_BOAT
      ),
      WILLOW("willow",
              () -> HibiscusWoods.WILLOW[4],
              () -> HibiscusWoods.WILLOW_BOAT,
              () -> HibiscusWoods.WILLOW_CHEST_BOAT,
              () -> HibiscusEntityTypes.WILLOW_BOAT,
              () -> HibiscusEntityTypes.WILLOW_CHEST_BOAT
      ),
      ASPEN("aspen",
              () -> HibiscusWoods.ASPEN[4],
              () -> HibiscusWoods.ASPEN_BOAT,
              () -> HibiscusWoods.ASPEN_CHEST_BOAT,
              () -> HibiscusEntityTypes.ASPEN_BOAT,
              () -> HibiscusEntityTypes.ASPEN_CHEST_BOAT
      ),
      MAPLE("maple",
              () -> HibiscusWoods.MAPLE[4],
              () -> HibiscusWoods.MAPLE_BOAT,
              () -> HibiscusWoods.MAPLE_CHEST_BOAT,
              () -> HibiscusEntityTypes.MAPLE_BOAT,
              () -> HibiscusEntityTypes.MAPLE_CHEST_BOAT
      ),
      CYPRESS("cypress",
              () -> HibiscusWoods.CYPRESS[4],
              () -> HibiscusWoods.CYPRESS_BOAT,
              () -> HibiscusWoods.CYPRESS_CHEST_BOAT,
              () -> HibiscusEntityTypes.CYPRESS_BOAT,
              () -> HibiscusEntityTypes.CYPRESS_CHEST_BOAT
      ),
      OLIVE("olive",
              () -> HibiscusWoods.OLIVE[4],
              () -> HibiscusWoods.OLIVE_BOAT,
              () -> HibiscusWoods.OLIVE_CHEST_BOAT,
              () -> HibiscusEntityTypes.OLIVE_BOAT,
              () -> HibiscusEntityTypes.OLIVE_CHEST_BOAT
      ),
      JOSHUA("joshua",
              () -> HibiscusWoods.JOSHUA[4],
              () -> HibiscusWoods.JOSHUA_BOAT,
              () -> HibiscusWoods.JOSHUA_CHEST_BOAT,
              () -> HibiscusEntityTypes.JOSHUA_BOAT,
              () -> HibiscusEntityTypes.JOSHUA_CHEST_BOAT
      );

      private final String name;
      private final Supplier<ItemLike> planks;
      private final Supplier<ItemLike> boat;
      private final Supplier<ItemLike> chestBoat;
      private final Supplier<EntityType<Boat>> entityType;
      private final Supplier<EntityType<Boat>> chestEntityType;
      public static final StringRepresentable.EnumCodec <HibiscusBoat> CODEC = StringRepresentable.fromEnum(HibiscusBoatEntity.HibiscusBoat::values);

      HibiscusBoat(
              String name,
              Supplier<ItemLike> planks,
              Supplier<ItemLike> boat,
              Supplier<ItemLike> chestBoat,
              Supplier<EntityType<Boat>> entityType,
              Supplier<EntityType<Boat>> chestEntityType
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

      public EntityType<Boat> entityType(boolean chest) {
         return chest ? chestEntityType.get() : entityType.get();
      }
      public static HibiscusBoat getType(String name) {
         return CODEC.byName(name, REDWOOD);
      }

      public EntityType.EntityFactory<Boat> factory(boolean chest) {
         return (type, world) -> chest
                 ? new HibiscusChestBoatEntity(type, world, this)
                 : new HibiscusBoatEntity(type, world, this);
      }

      public ResourceLocation id() {
         return new ResourceLocation(MOD_ID, name);
      }

      @Override public String getSerializedName() {
         return name;
      }
   }
}