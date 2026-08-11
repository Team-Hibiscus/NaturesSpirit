package net.hibiscus.naturespirit.registration;

import net.hibiscus.naturespirit.NatureSpirit;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class NSStatTypes {
    public static final DeferredRegister<ResourceLocation> CUSTOM_STATS = DeferredRegister.create(Registries.CUSTOM_STAT, NatureSpirit.MOD_ID);
    public static final Supplier<ResourceLocation> EAT_CHEESE = CUSTOM_STATS.register("eat_cheese", () -> ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "eat_cheese"));
    public static final Supplier<ResourceLocation> EAT_PIZZA_SLICE = CUSTOM_STATS.register("eat_pizza_slice", () -> ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, "eat_pizza_slice"));
}
