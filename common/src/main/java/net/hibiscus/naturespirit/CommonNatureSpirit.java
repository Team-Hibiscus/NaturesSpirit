package net.hibiscus.naturespirit;

import net.hibiscus.naturespirit.blocks.HibiscusBlocks;
import net.hibiscus.naturespirit.mixin.StatsTypeAccessor;
import net.hibiscus.naturespirit.platform.Services;
import net.hibiscus.naturespirit.util.HibiscusWorldGen;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.world.entity.animal.CatVariant;
import net.minecraft.world.item.Items;

public class CommonNatureSpirit {
    public static final ResourceLocation EAT_PIZZA_SLICE = StatsTypeAccessor.registerNew("eat_pizza_slice",
            StatFormatter.DEFAULT
    );
    public static void init() {

        Constants.LOG.info("Hello from Common init on {}! we are currently in a {} environment!", Services.PLATFORM.getPlatformName(), Services.PLATFORM.getEnvironmentName());
        Constants.LOG.info("The ID for diamonds is {}", BuiltInRegistries.ITEM.getKey(Items.DIAMOND));

        if (Services.PLATFORM.isModLoaded("examplemod")) {

            Constants.LOG.info("Hello to examplemod");
        }
        HibiscusBlocks.registerHibiscusBlocks();
        HibiscusWorldGen.registerWorldGen();
        Registry.register(BuiltInRegistries.CAT_VARIANT,
                "trans",
                new CatVariant(new ResourceLocation("textures/entity/cat/trans" + ".png"))
        );
    }
}