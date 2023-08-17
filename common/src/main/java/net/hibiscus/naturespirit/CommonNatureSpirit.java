package net.hibiscus.naturespirit;

import net.hibiscus.naturespirit.blocks.HibiscusBlocks;
import net.hibiscus.naturespirit.platform.Services;
import net.hibiscus.naturespirit.registration.RegistrationProvider;
import net.hibiscus.naturespirit.registration.RegistryObject;
import net.hibiscus.naturespirit.util.HibiscusWorldGen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.world.entity.animal.CatVariant;
import net.minecraft.world.item.Items;

public class CommonNatureSpirit {
    public static final ResourceLocation EAT_PIZZA_SLICE = new ResourceLocation("eat_pizza_slice");
    public static void init() {

        Constants.LOG.info("Hello from Common init on {}! we are currently in a {} environment!", Services.PLATFORM.getPlatformName(), Services.PLATFORM.getEnvironmentName());
        Constants.LOG.info("The ID for diamonds is {}", BuiltInRegistries.ITEM.getKey(Items.DIAMOND));

        if (Services.PLATFORM.isModLoaded("natures_spirit")) {

            Constants.LOG.info("Hello to Nature's Spirit");
        }
        HibiscusBlocks.registerHibiscusBlocks();
        HibiscusWorldGen.registerWorldGen();
    }
}