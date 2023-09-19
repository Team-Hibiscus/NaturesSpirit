package net.hibiscus.naturespirit.client.render;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.client.render.pizza_models.Topping0;
import net.hibiscus.naturespirit.client.render.pizza_models.Topping1;
import net.hibiscus.naturespirit.client.render.pizza_models.Topping2;
import net.hibiscus.naturespirit.client.render.pizza_models.Topping3;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class EntityModelLayers {

   public static void registerEntityModelLayers() {}
   public static final EntityModelLayer TOPPING_0 = registerMain("topping_0");
   public static final EntityModelLayer TOPPING_1 = registerMain("topping_1");
   public static final EntityModelLayer TOPPING_2 = registerMain("topping_2");
   public static final EntityModelLayer TOPPING_3 = registerMain("topping_3");

   private static EntityModelLayer registerMain(String id) {
      return register(id, "main");
   }
   private static EntityModelLayer register(String id, String layer) {
      return create(id, layer);

   }

   private static EntityModelLayer create(String id, String layer) {
      return new EntityModelLayer(new Identifier(NatureSpirit.MOD_ID, id), layer);
   }
}
