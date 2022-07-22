package net.hibiscus.naturespirit.world.feature.tree;

import net.hibiscus.naturespirit.world.feature.HibiscusConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class RedwoodSaplingGenerator extends AbstractMegaTreeGrower {

    @Nullable
    @Override
    protected Holder <? extends ConfiguredFeature <?, ?>> getConfiguredMegaFeature(RandomSource randomSource) {
        return HibiscusConfiguredFeatures.REDWOOD_TREE;
    }

    @Nullable
    @Override
    protected Holder <? extends ConfiguredFeature <?, ?>> getConfiguredFeature(RandomSource randomSource, boolean bl) {
        return HibiscusConfiguredFeatures.LARGE_REDWOOD_TREE;
    }
}
