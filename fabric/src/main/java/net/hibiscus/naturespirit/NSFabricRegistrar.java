package net.hibiscus.naturespirit;

import net.hibiscus.naturespirit.registration.NSRegistrar;
import net.hibiscus.naturespirit.registration.NSRegistryHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

public final class NSFabricRegistrar {

    private NSFabricRegistrar() {
    }

    public static void run() {
        NSRegistryHelper.bootstrap();
        for (NSRegistrar<?> registrar : NSRegistrar.allRegistrars()) {
            flush(registrar);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> void flush(NSRegistrar<T> registrar) {
        Registry<T> registry = (Registry<T>) BuiltInRegistries.REGISTRY.getValue(registrar.registryKey().identifier());
        if (registry == null) {
            throw new IllegalStateException("Unknown registry " + registrar.registryKey().identifier());
        }
        registrar.flush((key, value) -> Registry.register(registry, key, value));
    }
}
