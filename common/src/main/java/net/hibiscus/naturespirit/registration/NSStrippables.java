package net.hibiscus.naturespirit.registration;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.world.level.block.Block;

public final class NSStrippables {

    public static final List<Entry> STRIPPABLES = new ArrayList<>();

    static {
        add(() -> NSBlocks.ALLUAUDIA_BUNDLE.get(), () -> NSBlocks.STRIPPED_ALLUAUDIA_BUNDLE.get());
    }

    private NSStrippables() {
    }

    public static void add(Supplier<? extends Block> log, Supplier<? extends Block> stripped) {
        STRIPPABLES.add(new Entry(log, stripped));
    }

    public record Entry(Supplier<? extends Block> log, Supplier<? extends Block> stripped) {
    }
}
