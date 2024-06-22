package net.hibiscus.naturespirit.registration;

import net.hibiscus.naturespirit.NatureSpirit;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.UnaryOperator;

public class HibiscusDataComponents {
   public static final ComponentType<List <Identifier>> TOPPINGS = register("toppings", (builder) -> {
      return builder.codec(Identifier.CODEC.listOf()).cache();
   });
   private static <T> ComponentType <T> register(String id, UnaryOperator <ComponentType.Builder<T>> builderOperator) {
      return (ComponentType) Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(NatureSpirit.MOD_ID, id), ((ComponentType.Builder)builderOperator.apply(ComponentType.builder())).build());
   }
   public static void registerDataComponents() {};
}
