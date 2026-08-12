package net.hibiscus.naturespirit.registration;

import java.util.function.Supplier;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

public class NSHolder<T> implements Supplier<T> {

    private final ResourceKey<T> key;
    private final Supplier<? extends T> factory;
    private T value;

    public NSHolder(ResourceKey<T> key, Supplier<? extends T> factory) {
        this.key = key;
        this.factory = factory;
    }

    public ResourceKey<T> getKey() {
        return key;
    }

    public Identifier getId() {
        return key.identifier();
    }

    Supplier<? extends T> factory() {
        return factory;
    }

    void bind(T value) {
        this.value = value;
    }

    public boolean isBound() {
        return value != null;
    }

    @Override
    public T get() {
        if (value == null) {
            throw new IllegalStateException("Holder not bound yet: " + key.identifier());
        }
        return value;
    }
}
