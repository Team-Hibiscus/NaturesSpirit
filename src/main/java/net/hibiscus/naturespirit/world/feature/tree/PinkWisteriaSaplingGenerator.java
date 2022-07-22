package net.hibiscus.naturespirit.world.feature.tree;

import net.hibiscus.naturespirit.world.feature.HibiscusConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class PinkWisteriaSaplingGenerator extends AbstractTreeGrower {
    @Nullable
    @Override
    protected Holder <? extends ConfiguredFeature <?, ?>> getConfiguredFeature(RandomSource randomSource, boolean bl) {
        return HibiscusConfiguredFeatures.PINK_WISTERIA_TREE;
    }
}
