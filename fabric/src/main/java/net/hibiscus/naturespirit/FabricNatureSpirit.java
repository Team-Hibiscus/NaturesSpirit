package net.hibiscus.naturespirit;

import net.fabricmc.api.ModInitializer;
import net.hibiscus.naturespirit.datagen.HibiscusBiomes;
import net.hibiscus.naturespirit.util.HibiscusEvents;
import net.hibiscus.naturespirit.util.HibiscusItemGroups;
import net.hibiscus.naturespirit.util.HibiscusVillagers;

public class FabricNatureSpirit implements ModInitializer {
    
    @Override
    public void onInitialize() {
        CommonNatureSpirit.init();
        HibiscusBiomes.registerBiomes();
        HibiscusItemGroups.registerItemGroup();
        HibiscusEvents.registerEvents();
        HibiscusVillagers.registerVillagers();
    }
}
