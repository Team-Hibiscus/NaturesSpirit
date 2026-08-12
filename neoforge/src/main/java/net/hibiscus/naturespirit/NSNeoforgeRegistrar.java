package net.hibiscus.naturespirit;

import net.hibiscus.naturespirit.registration.NSRegistrar;
import net.neoforged.neoforge.registries.RegisterEvent;

public final class NSNeoforgeRegistrar {

    private NSNeoforgeRegistrar() {
    }

    public static void onRegister(RegisterEvent event) {
        for (NSRegistrar<?> registrar : NSRegistrar.allRegistrars()) {
            if (event.getRegistryKey().equals(registrar.registryKey())) {
                flush(registrar, event);
            }
        }
    }

    private static <T> void flush(NSRegistrar<T> registrar, RegisterEvent event) {
        registrar.flush((key, value) -> event.register(registrar.registryKey(), key.identifier(), () -> value));
    }
}
