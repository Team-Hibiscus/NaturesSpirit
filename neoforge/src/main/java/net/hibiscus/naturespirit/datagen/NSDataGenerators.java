package net.hibiscus.naturespirit.datagen;

import net.hibiscus.naturespirit.NaturesSpirit;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = NaturesSpirit.MOD_ID)
public class NSDataGenerators {

  // createDatapackRegistryObjects swaps the event lookup provider for one containing our
  // VILLAGER_TRADE entries; the tag provider must be created after it or TagsProvider.run
  // fails to resolve every natures_spirit trade key it writes.
  @SubscribeEvent
  public static void gatherData(GatherDataEvent.Client event) {
    event.createDatapackRegistryObjects(new RegistrySetBuilder()
        .add(Registries.VILLAGER_TRADE, NSVillagerTrades::bootstrap));

    event.createProvider(NSVillagerTradeTagsProvider::new);
    event.createProvider(NSDataMapProvider::new);
    event.createProvider(NSModelProvider::new);
  }
}
