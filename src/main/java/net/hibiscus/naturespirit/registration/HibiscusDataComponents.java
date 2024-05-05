package net.hibiscus.naturespirit.registration;

import net.hibiscus.naturespirit.NatureSpirit;
import net.minecraft.component.DataComponentType;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.UnaryOperator;

public class HibiscusDataComponents {
   public static final DataComponentType <List <Identifier>> TOPPINGS = register("toppings", (builder) -> {
      return builder.codec(Identifier.CODEC.listOf()).cache();
   });
   private static <T> DataComponentType <T> register(String id, UnaryOperator <DataComponentType.Builder<T>> builderOperator) {
      return (DataComponentType) Registry.register(Registries.DATA_COMPONENT_TYPE, new Identifier(NatureSpirit.MOD_ID, id), ((DataComponentType.Builder)builderOperator.apply(DataComponentType.builder())).build());
   }
   public static void registerDataComponents() {};
}
