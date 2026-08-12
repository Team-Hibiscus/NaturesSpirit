package net.hibiscus.naturespirit;

import net.fabricmc.api.ClientModInitializer;

public class NaturesSpiritFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        NSFabricClient.run();
    }
}
