package net.hibiscus.naturespirit.registration;

import java.util.function.Supplier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

public class NSItemHolder<I extends Item> extends NSHolder<I> implements ItemLike {

    public NSItemHolder(ResourceKey<I> key, Supplier<? extends I> factory) {
        super(key, factory);
    }

    @Override
    public Item asItem() {
        return get();
    }
}
