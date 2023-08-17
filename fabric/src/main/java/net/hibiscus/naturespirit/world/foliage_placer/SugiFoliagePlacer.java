package net.hibiscus.naturespirit.world.foliage_placer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.hibiscus.naturespirit.util.HibiscusFabricWorldGen;
import net.hibiscus.naturespirit.util.HibiscusWorldGen;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class SugiFoliagePlacer extends FoliagePlacer {
   public static final Codec <SugiFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) -> {
      return foliagePlacerParts(instance).apply(instance, SugiFoliagePlacer::new);
   });

   public SugiFoliagePlacer(IntProvider intProvider, IntProvider intProvider2) {
      super(intProvider, intProvider2);
   }

   protected FoliagePlacerType <?> type() {
      return HibiscusFabricWorldGen.SUGI_FOLIAGE_PLACER_TYPE;
   }

   protected void createFoliage(LevelSimulatedReader world, FoliageSetter placer, RandomSource random, TreeConfiguration config, int trunkHeight, FoliagePlacer.FoliageAttachment treeNode, int foliageHeight, int radius, int offset) {
      boolean bl = treeNode.doubleTrunk();
      BlockPos blockPos = treeNode.pos().above(offset);
      this.placeLeavesRow(world, placer, random, config, blockPos, radius + treeNode.radiusOffset(), -1, false);
      this.placeLeavesRow(world, placer, random, config, blockPos, radius + 1 + treeNode.radiusOffset(), 0, false);
      this.placeLeavesRow(world, placer, random, config, blockPos, radius + treeNode.radiusOffset(), 1, false);
   }

   public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
      return 0;
   }

   protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
      return dx == radius && dz == radius && radius > 0;
   }
}
