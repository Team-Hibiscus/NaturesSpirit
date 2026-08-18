package net.hibiscus.naturespirit.registration;

import java.util.function.Supplier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

public class NSBlockHolder<B extends Block> extends NSHolder<B> implements ItemLike {

    public NSBlockHolder(ResourceKey<B> key, Supplier<? extends B> factory) {
        super(key, factory);
    }

    @Override
    public Item asItem() {
        return get().asItem();
    }
}
