package net.hibiscus.naturespirit.registration;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.world.level.block.Block;

public final class NSFlammables {

    public static final List<Entry> FLAMMABLES = new ArrayList<>();

    private NSFlammables() {
    }

    public static void add(Supplier<? extends Block> block, int igniteOdds, int burnOdds) {
        FLAMMABLES.add(new Entry(block, igniteOdds, burnOdds));
    }

    public record Entry(Supplier<? extends Block> block, int igniteOdds, int burnOdds) {
    }
}
