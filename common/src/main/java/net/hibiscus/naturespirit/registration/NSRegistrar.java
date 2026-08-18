package net.hibiscus.naturespirit.registration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import net.hibiscus.naturespirit.NaturesSpirit;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public final class NSRegistrar<T> {

    private static final Map<ResourceKey<? extends Registry<?>>, NSRegistrar<?>> REGISTRARS = new LinkedHashMap<>();
    private static final List<ResourceKey<? extends Registry<?>>> FLUSH_FIRST =
            List.of(Registries.BLOCK, Registries.ENTITY_TYPE, Registries.BLOCK_ENTITY_TYPE, Registries.ITEM);

    private final ResourceKey<? extends Registry<T>> registryKey;
    private final List<NSHolder<? extends T>> entries = new ArrayList<>();

    private NSRegistrar(ResourceKey<? extends Registry<T>> registryKey) {
        this.registryKey = registryKey;
    }

    @SuppressWarnings("unchecked")
    public static synchronized <T> NSRegistrar<T> of(ResourceKey<? extends Registry<T>> registryKey) {
        return (NSRegistrar<T>) REGISTRARS.computeIfAbsent(registryKey, key -> new NSRegistrar<>(registryKey));
    }

    public static synchronized List<NSRegistrar<?>> allRegistrars() {
        List<NSRegistrar<?>> ordered = new ArrayList<>(REGISTRARS.size());
        for (ResourceKey<? extends Registry<?>> key : FLUSH_FIRST) {
            NSRegistrar<?> registrar = REGISTRARS.get(key);
            if (registrar != null) {
                ordered.add(registrar);
            }
        }
        for (NSRegistrar<?> registrar : REGISTRARS.values()) {
            if (!FLUSH_FIRST.contains(registrar.registryKey)) {
                ordered.add(registrar);
            }
        }
        return ordered;
    }

    public ResourceKey<? extends Registry<T>> registryKey() {
        return registryKey;
    }

    public List<NSHolder<? extends T>> entries() {
        return Collections.unmodifiableList(entries);
    }

    public <R extends T> NSHolder<R> register(String name, Supplier<? extends R> factory) {
        NSHolder<R> holder = new NSHolder<>(cast(elementKey(name)), factory);
        return add(holder);
    }

    public <R extends T> NSHolder<R> registerKeyed(String name, Function<ResourceKey<T>, ? extends R> factory) {
        ResourceKey<T> key = elementKey(name);
        NSHolder<R> holder = new NSHolder<>(cast(key), () -> factory.apply(key));
        return add(holder);
    }

    public <B extends Block> NSBlockHolder<B> registerBlock(String name, Function<BlockBehaviour.Properties, ? extends B> factory, Supplier<BlockBehaviour.Properties> properties) {
        requireRegistry(Registries.BLOCK, "registerBlock");
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, NaturesSpirit.id(name));
        NSBlockHolder<B> holder = new NSBlockHolder<>(cast(key), () -> factory.apply(properties.get().setId(key)));
        return add(holder);
    }

    public <I extends Item> NSItemHolder<I> registerItem(String name, Function<Item.Properties, ? extends I> factory, Supplier<Item.Properties> properties) {
        requireRegistry(Registries.ITEM, "registerItem");
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, NaturesSpirit.id(name));
        NSItemHolder<I> holder = new NSItemHolder<>(cast(key), () -> factory.apply(properties.get().setId(key)));
        return add(holder);
    }

    public NSItemHolder<BlockItem> registerSimpleBlockItem(String name, Supplier<? extends Block> block) {
        return registerItem(name, properties -> new BlockItem(block.get(), properties), () -> new Item.Properties().useBlockDescriptionPrefix());
    }

    public void flush(BiConsumer<ResourceKey<T>, T> sink) {
        for (NSHolder<? extends T> holder : entries) {
            flush(holder, sink);
        }
    }

    private <R extends T> void flush(NSHolder<R> holder, BiConsumer<ResourceKey<T>, T> sink) {
        R value = holder.factory().get();
        holder.bind(value);
        sink.accept(cast(holder.getKey()), value);
    }

    private <H extends NSHolder<?>> H add(H holder) {
        entries.add(castHolder(holder));
        return holder;
    }

    @SuppressWarnings("unchecked")
    private NSHolder<? extends T> castHolder(NSHolder<?> holder) {
        return (NSHolder<? extends T>) holder;
    }

    @SuppressWarnings("unchecked")
    private ResourceKey<T> elementKey(String name) {
        return (ResourceKey<T>) ResourceKey.create(registryKey, NaturesSpirit.id(name));
    }

    @SuppressWarnings("unchecked")
    private static <A, B> ResourceKey<B> cast(ResourceKey<A> key) {
        return (ResourceKey<B>) key;
    }

    private void requireRegistry(ResourceKey<? extends Registry<?>> expected, String method) {
        if (!registryKey.equals(expected)) {
            throw new IllegalStateException(method + " called on registrar for " + registryKey.identifier());
        }
    }
}
