package net.hibiscus.naturespirit.world.tree;

import net.hibiscus.naturespirit.datagen.NaturesSpiritConfiguredFeatures;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class PinkWisteriaSaplingGenerator extends SaplingGenerator {
   @Override protected RegistryKey <ConfiguredFeature <?, ?>> getTreeFeature(Random randomSource, boolean bl) {
      return NaturesSpiritConfiguredFeatures.PINK_WISTERIA_TREE;
   }
}
