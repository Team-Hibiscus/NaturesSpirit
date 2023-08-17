package net.hibiscus.naturespirit;

import net.fabricmc.api.ModInitializer;
import net.hibiscus.naturespirit.datagen.HibiscusBiomes;
import net.hibiscus.naturespirit.mixin.StatsTypeAccessor;
import net.hibiscus.naturespirit.registration.RegistrationProvider;
import net.hibiscus.naturespirit.registration.RegistryObject;
import net.hibiscus.naturespirit.util.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.world.entity.animal.CatVariant;

public class FabricNatureSpirit implements ModInitializer {
    public static ResourceLocation EAT_PIZZA_SLICE = StatsTypeAccessor.registerNew("eat_pizza_slice",
            StatFormatter.DEFAULT
    );
    public static final RegistrationProvider <CatVariant> CAT_VARIANTS = RegistrationProvider.get(Registries.CAT_VARIANT, Constants.MOD_ID);
    public static void registerCatVariant(String name, CatVariant catVariant) {
        assert CAT_VARIANTS != null;
        RegistryObject <CatVariant> catVariant2 = CAT_VARIANTS.register(name, () -> catVariant);
    }
    @Override
    public void onInitialize() {
        CommonNatureSpirit.init();
        HibiscusBiomes.registerBiomes();
        HibiscusFabricWorldGen.registerWorldGen();
        HibiscusItemGroups.registerItemGroup();
        HibiscusEvents.registerEvents();
        HibiscusVillagers.registerVillagers();
        HibiscusRegister.registerBlocks();
        HibiscusRegister.registerItems();
        registerCatVariant(
                "trans",
                new CatVariant(new ResourceLocation("textures/entity/cat/trans" + ".png" + Constants.MOD_ID))
        );
    }
}
