package net.hibiscus.naturespirit.world.tree;

import net.hibiscus.naturespirit.datagen.NSConfiguredFeatures;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class OliveSaplingGenerator extends SaplingGenerator {
   @Override protected RegistryKey <ConfiguredFeature <?, ?>> getTreeFeature(Random randomSource, boolean bl) {
      return NSConfiguredFeatures.OLIVE_TREE_SPAWN;
   }
}
