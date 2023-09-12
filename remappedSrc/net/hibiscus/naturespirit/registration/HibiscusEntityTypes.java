package net.hibiscus.naturespirit.registration;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
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
import static net.hibiscus.naturespirit.util.HibiscusRegistryHelper.registerItem;

public class HibiscusEntityTypes {
//   public static EntityType<BisonEntity> BISON = registerMobEntityType("bison", FabricEntityTypeBuilder.createMob().spawnGroup(SpawnGroup.CREATURE).defaultAttributes(BisonEntity::createBisonAttributes).entityFactory(BisonEntity::new).dimensions(EntityDimensions.fixed(2.0F, 1.75F)).trackRangeChunks(10));
   public static void registerEntityTypes() {
   }


   public static <T extends EntityType <?>> T registerEntityType(String id, T type) {
      return Registry.register(BuiltInRegistries.ENTITY_TYPE, new ResourceLocation(MOD_ID, id), type);
   }
//   public static <T extends Entity> EntityType<T> registerMobEntityType(String id, FabricEntityTypeBuilder<T> type) {
//      EntityType<T> entityType = Registry.register(Registries.ENTITY_TYPE, new Identifier(MOD_ID, id), type.build());
//         registerItem(id + "spawn_egg", new SpawnEggItem((EntityType <? extends MobEntity>) entityType, 15771042, 14377823, new FabricItemSettings().maxCount(64)), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, Items.COW_SPAWN_EGG,
//                 ItemGroups.SPAWN_EGGS);
//      return entityType;
//   }

   public static EntityType <Boat> createBoatType(boolean chest, HibiscusBoatEntity.HibiscusBoat boat) {
      return FabricEntityTypeBuilder.create(MobCategory.MISC, boat.factory(chest)).dimensions(EntityDimensions.scalable(1.375f, 0.5625f)).trackRangeChunks(10).build();
   }

}
