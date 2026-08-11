package net.hibiscus.naturespirit.registration;

import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.blocks.block_entities.PizzaToppingVariant;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Supplier;

public class NSDataComponents {

  public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENTS = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, NatureSpirit.MOD_ID);
  public static final Supplier<DataComponentType<List<PizzaToppingVariant>>> TOPPINGS = DATA_COMPONENTS.register("toppings", () ->
          DataComponentType.<List<PizzaToppingVariant>>builder().persistent(PizzaToppingVariant.CODEC.listOf()).cacheEncoding().build());

}
