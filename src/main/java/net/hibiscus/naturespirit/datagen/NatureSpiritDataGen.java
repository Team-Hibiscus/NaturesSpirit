package net.hibiscus.naturespirit.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.terrablender.HibiscusBiomes;
import net.hibiscus.naturespirit.world.feature.HibiscusConfiguredFeatures;
import net.hibiscus.naturespirit.world.feature.HibiscusPlacedFeatures;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class NatureSpiritDataGen implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(NatureSpiritWorldGenerator::new);
        System.out.println("Initialized Data Generator");
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        System.out.println("Built Registry");
        registryBuilder.add(Registries.CONFIGURED_FEATURE, HibiscusConfiguredFeatures::bootstrap);
        registryBuilder.add(Registries.PLACED_FEATURE, HibiscusPlacedFeatures::bootstrap);
//        registryBuilder.add(Registries.BIOME, HibiscusBiomes::bootstrap);
    }

    @Override
    public String getEffectiveModId() {
        return null;
    }
}

