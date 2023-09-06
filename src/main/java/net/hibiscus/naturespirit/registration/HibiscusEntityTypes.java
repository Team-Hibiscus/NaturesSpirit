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
   public static void registerEntityTypes() {
   }

   public static <T extends EntityType <?>> T registerEntityType(String id, T type) {
      return Registry.register(Registries.ENTITY_TYPE, new Identifier(MOD_ID, id), type);
   }

   public static EntityType <BoatEntity> createBoatType(boolean chest, HibiscusBoatEntity.HibiscusBoat boat) {
      return FabricEntityTypeBuilder.create(SpawnGroup.MISC, boat.factory(chest)).dimensions(EntityDimensions.changing(1.375f, 0.5625f)).trackRangeChunks(10).build();
   }

}
