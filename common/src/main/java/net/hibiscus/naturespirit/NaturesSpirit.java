package net.hibiscus.naturespirit;

import net.hibiscus.naturespirit.blocks.block_entities.PizzaToppingVariant;
import net.hibiscus.naturespirit.config.NSConfig;
import net.hibiscus.naturespirit.lithostitched.LithostitchedEventHandlers;
import net.hibiscus.naturespirit.platform.Services;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NaturesSpirit {

    public static final String MOD_ID = "natures_spirit";
    public static final String MOD_NAME = "Nature's Spirit";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);
    public static final ResourceKey<Registry<PizzaToppingVariant>> PIZZA_TOPPING_VARIANT = ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath(MOD_ID, "pizza_topping_variant"));

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    public static void init() {
        NSConfig.load(Services.PLATFORM.getConfigDir());
        LithostitchedEventHandlers.handleAddRegionsEvent();
        LithostitchedEventHandlers.handleAddBiomeInjectorsEvent();
        LithostitchedEventHandlers.handleAddWorldgenModifiersEvent();
        LOG.info("{} initialized on {} ({})", MOD_NAME, Services.PLATFORM.getPlatformName(), Services.PLATFORM.getEnvironmentName());
    }
}
