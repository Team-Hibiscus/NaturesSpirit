package net.hibiscus.naturespirit.terrablender;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;
import terrablender.api.TerraBlenderApi;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;

public class NatureSpiritTerraBlender implements TerraBlenderApi, ModInitializer {
    @Override
    public void onTerraBlenderInitialized() {
        {
            Regions.register(new HibiscusRegion(new ResourceLocation(MOD_ID, "overworld"), 2));
        }
    }

    @Override
    public void onInitialize() {
        HibiscusBiomes.registerBiomes();
    }
}
