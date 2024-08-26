package net.hibiscus.naturespirit.registration;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.hibiscus.naturespirit.config.HibiscusConfig;
import net.hibiscus.naturespirit.entity.CheeseArrowEntity;
import net.hibiscus.naturespirit.entity.HibiscusBoatEntity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.projectile.SpectralArrowEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;

public class HibiscusEntityTypes {

   private static final FabricEntityTypeBuilder<CheeseArrowEntity> CHEESE_ARROW_ENTITY_BUILDER = FabricEntityTypeBuilder.create(SpawnGroup.MISC).entityFactory(CheeseArrowEntity::new);
   public static final EntityType<CheeseArrowEntity> CHEESE_ARROW = registerEntityType("cheese_arrow", CHEESE_ARROW_ENTITY_BUILDER.dimensions(EntityDimensions.changing(0.5F, 0.5F)).trackRangeChunks(4).trackedUpdateRate(20).build());

   public static void registerEntityTypes() {
   }
   //   public static EntityType<BisonEntity> BISON = registerMobEntityType("bison", FabricEntityTypeBuilder.createMob().spawnGroup(SpawnGroup.CREATURE).defaultAttributes(BisonEntity::createBisonAttributes).entityFactory(BisonEntity::new).dimensions(EntityDimensions.fixed(2.0F, 1.75F)).trackRangeChunks(10));


   public static <T extends EntityType <?>> T registerEntityType(String id, T type) {
      return Registry.register(Registries.ENTITY_TYPE, new Identifier(MOD_ID, id), type);
   }
//   public static <T extends Entity> EntityType<T> registerMobEntityType(String id, FabricEntityTypeBuilder<T> type) {
//      EntityType<T> entityType = Registry.register(Registries.ENTITY_TYPE, new Identifier(MOD_ID, id), type.build());
//         registerItem(id + "spawn_egg", new SpawnEggItem((EntityType <? extends MobEntity>) entityType, 15771042, 14377823, new FabricItemSettings().maxCount(64)), HibiscusItemGroups.NS_ITEM_GROUP, Items.COW_SPAWN_EGG,
//                 ItemGroups.SPAWN_EGGS);
//      return entityType;
//   }

   public static EntityType <BoatEntity> createBoatType(boolean chest, HibiscusBoatEntity.HibiscusBoat boat) {
      return FabricEntityTypeBuilder.create(SpawnGroup.MISC, boat.factory(chest)).dimensions(EntityDimensions.changing(1.375f, 0.5625f)).trackRangeChunks(10).build();
   }

}
