package net.hibiscus.naturespirit.world.feature.foliage_placer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.util.HibiscusWorldGen;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class AspenFoliagePlacer extends FoliagePlacer {
   public static final Codec <AspenFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) -> {
      return foliagePlacerParts(instance).and(IntProvider.codec(0, 24)
              .fieldOf("trunk_height")
              .forGetter((placer) -> {
                 return placer.trunkHeight;
              })).apply(instance, AspenFoliagePlacer::new);
   });
   private final IntProvider trunkHeight;

   public AspenFoliagePlacer(IntProvider intProvider, IntProvider intProvider2, IntProvider trunkHeight) {
      super(intProvider, intProvider2);
      this.trunkHeight = trunkHeight;
   }

   protected FoliagePlacerType <?> type() {
      return HibiscusWorldGen.ASPEN_FOLIAGE_PLACER_TYPE;
   }

   protected void createFoliage(LevelSimulatedReader world, FoliageSetter placer, RandomSource random, TreeConfiguration config, int trunkHeight, FoliageAttachment treeNode, int foliageHeight, int radius, int offset) {
      BlockPos blockPos = treeNode.pos();
      BlockPos.MutableBlockPos mutable = blockPos.above(offset).mutable();
      int i = random.nextIntBetweenInclusive(0, 3);
      int j = 1;
      int k = 0;

      for(int l = offset; l >= -foliageHeight - 2; --l) {
         this.placeLeavesRow(world,
                 placer,
                 random,
                 config,
                 blockPos,
                 (l >= offset ? 0 : i),
                 l,
                 treeNode.doubleTrunk()
         );
         j = Math.min(j + 1, radius + treeNode.radiusOffset());
         i = l <= -foliageHeight - 1 ? Math.max(i - 2,
                 1
         ) : (l >= offset - 1 ? 0 : (l >= offset - 3 ? 1 : (i * 1.25 >= j ? Math.max(i - 1,
                 1
         ) : (l >= offset - 4 ? 2 : i + 1))));
      }

   }


   public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
      return Math.max(6, trunkHeight - this.trunkHeight.sample(random));
   }

   protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
      return dx == radius && dz == radius && radius > 0;
   }
}
