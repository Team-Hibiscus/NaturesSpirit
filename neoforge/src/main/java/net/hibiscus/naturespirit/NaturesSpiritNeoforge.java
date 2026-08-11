package net.hibiscus.naturespirit;


import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(NaturesSpirit.MOD_ID)
public class NaturesSpiritNeoforge {

    public NaturesSpiritNeoforge(IEventBus eventBus) {

        // This method is invoked by the NeoForge mod loader when it is ready
        // to load your mod. You can access NeoForge and Common code in this
        // project.

        // Use NeoForge to bootstrap the Common mod.
        NaturesSpirit.LOG.info("Hello NeoForge world!");
        NaturesSpirit.init();

    }
}