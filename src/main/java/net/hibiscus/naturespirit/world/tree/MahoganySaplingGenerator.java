package net.hibiscus.naturespirit.world.tree;

import net.hibiscus.naturespirit.datagen.NSConfiguredFeatures;
import net.minecraft.block.sapling.LargeTreeSaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class MahoganySaplingGenerator extends LargeTreeSaplingGenerator {

   @Override protected RegistryKey <ConfiguredFeature <?, ?>> getLargeTreeFeature(Random randomSource) {
      return NSConfiguredFeatures.MAHOGANY_TREE;
   }

   @Override protected RegistryKey <ConfiguredFeature <?, ?>> getTreeFeature(Random randomSource, boolean bl) {
      return null;
   }
}
