package net.hibiscus.naturespirit;

import net.hibiscus.naturespirit.registration.NSRegistryHelper;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(NaturesSpirit.MOD_ID)
public class NaturesSpiritNeoforge {

    public NaturesSpiritNeoforge(IEventBus modBus) {
        NSRegistryHelper.bootstrap();
        modBus.addListener(NSNeoforgeRegistrar::onRegister);
        NaturesSpirit.init();
    }
}
