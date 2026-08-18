package net.hibiscus.naturespirit;

import net.fabricmc.api.ModInitializer;

public class NaturesSpiritFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        NaturesSpirit.init();
        NSFabricRegistrar.run();
        NSFabricContent.run();
    }
}
