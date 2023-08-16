package net.hibiscus.naturespirit.world.feature.tree;

import net.hibiscus.naturespirit.world.HibiscusConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class FirSaplingGenerator extends AbstractTreeGrower {
   @Override protected ResourceKey <ConfiguredFeature <?, ?>> getConfiguredFeature(RandomSource randomSource, boolean bl) {
      return HibiscusConfiguredFeatures.FIR_TREE;
   }
}
