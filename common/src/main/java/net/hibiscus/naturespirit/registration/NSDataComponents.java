package net.hibiscus.naturespirit.registration;

import net.hibiscus.naturespirit.blocks.block_entities.PizzaToppingVariant;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;

import java.util.List;

public class NSDataComponents {

  public static final NSRegistrar<DataComponentType<?>> DATA_COMPONENTS = NSRegistrar.of(Registries.DATA_COMPONENT_TYPE);
  public static final NSHolder<DataComponentType<List<PizzaToppingVariant>>> TOPPINGS = DATA_COMPONENTS.register("toppings", () ->
          DataComponentType.<List<PizzaToppingVariant>>builder().persistent(PizzaToppingVariant.CODEC.listOf()).cacheEncoding().build());

  public static void bootstrap() {
  }
}
