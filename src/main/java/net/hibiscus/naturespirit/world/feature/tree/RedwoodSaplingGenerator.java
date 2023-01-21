package net.hibiscus.naturespirit.world.feature.tree;

import net.hibiscus.naturespirit.world.feature.HibiscusConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class RedwoodSaplingGenerator extends AbstractMegaTreeGrower {

    @Override
    protected ResourceKey <ConfiguredFeature <?, ?>> getConfiguredMegaFeature(RandomSource randomSource) {
        return HibiscusConfiguredFeatures.LARGE_REDWOOD_TREE;
    }

    @Override
    protected ResourceKey <ConfiguredFeature <?, ?>> getConfiguredFeature(RandomSource randomSource, boolean bl) {
        return HibiscusConfiguredFeatures.REDWOOD_TREE;
    }
}
