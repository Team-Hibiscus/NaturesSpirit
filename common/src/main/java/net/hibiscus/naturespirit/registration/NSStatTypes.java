package net.hibiscus.naturespirit.registration;

import net.hibiscus.naturespirit.NaturesSpirit;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;

public class NSStatTypes {
    public static final NSRegistrar<Identifier> CUSTOM_STATS = NSRegistrar.of(Registries.CUSTOM_STAT);
    public static final NSHolder<Identifier> EAT_CHEESE = CUSTOM_STATS.register("eat_cheese", () -> NaturesSpirit.id("eat_cheese"));
    public static final NSHolder<Identifier> EAT_PIZZA_SLICE = CUSTOM_STATS.register("eat_pizza_slice", () -> NaturesSpirit.id("eat_pizza_slice"));

    public static void bootstrap() {
    }
}
