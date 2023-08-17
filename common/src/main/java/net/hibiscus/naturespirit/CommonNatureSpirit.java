package net.hibiscus.naturespirit;

import net.hibiscus.naturespirit.blocks.HibiscusBlocks;
import net.hibiscus.naturespirit.mixin.StatsTypeAccessor;
import net.hibiscus.naturespirit.platform.Services;
import net.hibiscus.naturespirit.registration.RegistrationProvider;
import net.hibiscus.naturespirit.registration.RegistryObject;
import net.hibiscus.naturespirit.util.HibiscusWorldGen;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.world.entity.animal.CatVariant;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.levelgen.feature.Feature;

public class CommonNatureSpirit {
    public static final ResourceLocation EAT_PIZZA_SLICE = StatsTypeAccessor.registerNew("eat_pizza_slice",
            StatFormatter.DEFAULT
    );
    public static final RegistrationProvider <CatVariant> CAT_VARIANTS = RegistrationProvider.get(BuiltInRegistries.CAT_VARIANT, Constants.MOD_ID);
    public static CatVariant registerCatVariant(String name, CatVariant catVariant) {
        assert CAT_VARIANTS != null;
        RegistryObject <CatVariant> catVariant2 = CAT_VARIANTS.register(name, () -> catVariant);
        return catVariant2.get();
    }
    public static void init() {

        Constants.LOG.info("Hello from Common init on {}! we are currently in a {} environment!", Services.PLATFORM.getPlatformName(), Services.PLATFORM.getEnvironmentName());
        Constants.LOG.info("The ID for diamonds is {}", BuiltInRegistries.ITEM.getKey(Items.DIAMOND));

        if (Services.PLATFORM.isModLoaded("natures_spirit")) {

            Constants.LOG.info("Hello to Nature's Spirit");
        }
        HibiscusBlocks.registerHibiscusBlocks();
//        HibiscusWorldGen.registerWorldGen();
        registerCatVariant(
                "trans",
                new CatVariant(new ResourceLocation("textures/entity/cat/trans" + ".png" + Constants.MOD_ID))
        );
    }
}