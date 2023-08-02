package net.hibiscus.naturespirit.world.feature.tree;

import net.hibiscus.naturespirit.world.feature.HibiscusConfiguredFeatures;
import net.minecraft.block.sapling.LargeTreeSaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class RedwoodSaplingGenerator extends LargeTreeSaplingGenerator {

   @Override protected RegistryKey <ConfiguredFeature <?, ?>> getLargeTreeFeature(Random randomSource) {
      return HibiscusConfiguredFeatures.LARGE_REDWOOD_TREE;
   }

   @Override protected RegistryKey <ConfiguredFeature <?, ?>> getTreeFeature(Random randomSource, boolean bl) {
      return HibiscusConfiguredFeatures.REDWOOD_TREE;
   }
}
