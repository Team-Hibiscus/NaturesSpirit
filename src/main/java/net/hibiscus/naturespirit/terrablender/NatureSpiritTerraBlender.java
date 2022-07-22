package net.hibiscus.naturespirit.terrablender;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;
import terrablender.api.TerraBlenderApi;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;

public class NatureSpiritTerraBlender implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {
        {
            // Given we only add two biomes, we should keep our weight relatively low.
            Regions.register(new HibiscusRegion(new ResourceLocation(MOD_ID, "overworld"), 2));
        }
    }
}
