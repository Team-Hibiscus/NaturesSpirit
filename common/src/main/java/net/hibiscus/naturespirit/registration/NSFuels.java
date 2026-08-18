package net.hibiscus.naturespirit.registration;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.world.level.ItemLike;

public final class NSFuels {

    public static final List<Entry> FUELS = new ArrayList<>();

    private NSFuels() {
    }

    public static void add(Supplier<? extends ItemLike> item, int burnTime) {
        FUELS.add(new Entry(item, burnTime));
    }

    public record Entry(Supplier<? extends ItemLike> item, int burnTime) {
    }
}
