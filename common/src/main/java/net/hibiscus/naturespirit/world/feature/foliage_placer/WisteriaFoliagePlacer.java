package net.hibiscus.naturespirit.world.feature.foliage_placer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.hibiscus.naturespirit.util.HibiscusWorldGen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class WisteriaFoliagePlacer extends FoliagePlacer {
   public static final Codec <WisteriaFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) -> {
      return foliagePlacerParts(instance).apply(instance, WisteriaFoliagePlacer::new);
   });

   public WisteriaFoliagePlacer(IntProvider intProvider, IntProvider intProvider2) {
      super(intProvider, intProvider2);
   }

   protected FoliagePlacerType <?> type() {
      return HibiscusWorldGen.WISTERIA_FOLIAGE_PLACER_TYPE;
   }

   protected void createFoliage(LevelSimulatedReader world, FoliageSetter placer, RandomSource random, TreeConfiguration config, int trunkHeight, FoliageAttachment treeNode, int foliageHeight, int radius, int offset) {
      BlockPos blockPos = treeNode.pos().above(offset);
      BlockPos.MutableBlockPos mutable = blockPos.mutable();
      this.placeLeavesRow(world, placer, random, config, blockPos, radius, -1, true);
      this.placeLeavesRow(world, placer, random, config, blockPos, radius + 1, 0, true);
      this.placeLeavesRow(world, placer, random, config, blockPos, radius, 1, true);
      for(int i = 0; i < 60; ++i) {
         mutable.setWithOffset(blockPos,
                 random.nextInt(radius) - random.nextInt(radius),
                 -2,
                 random.nextInt(radius) - random.nextInt(radius)
         );
         tryPlaceLeaf(world, placer, random, config, mutable);
         tryPlaceLeaf(world, placer, random, config, mutable.relative(Direction.DOWN, 1));
         tryPlaceLeaf(world, placer, random, config, mutable.relative(Direction.DOWN, 2));
      }
      for(int i = 0; i < 10; ++i) {
         mutable.setWithOffset(blockPos,
                 random.nextInt(radius + 2) - random.nextInt(radius + 2),
                 0,
                 random.nextInt(radius + 2) - random.nextInt(radius + 2)
         );
         tryPlaceLeaf(world, placer, random, config, mutable);
         tryPlaceLeaf(world, placer, random, config, mutable.relative(Direction.DOWN, 1));
         tryPlaceLeaf(world, placer, random, config, mutable.relative(Direction.DOWN, 2));
      }
      for(int i = 0; i < 10; ++i) {
         mutable.setWithOffset(blockPos,
                 random.nextInt(radius + 2) - random.nextInt(radius + 2),
                 0,
                 random.nextInt(radius + 2) - random.nextInt(radius + 2)
         );
         tryPlaceLeaf(world, placer, random, config, mutable);
         tryPlaceLeaf(world, placer, random, config, mutable.relative(Direction.DOWN, 1));
      }
      for(int i = 0; i < 80; ++i) {
         mutable.setWithOffset(blockPos,
                 random.nextInt(radius + 2) - random.nextInt(radius + 2),
                 0,
                 random.nextInt(radius + 2) - random.nextInt(radius + 2)
         );
         tryPlaceLeaf(world, placer, random, config, mutable);
      }

   }

   public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
      return 3;
   }

   protected boolean shouldSkipLocationSigned(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
      return y == 0 && (dx == -radius || dx >= radius) && (dz == -radius || dz >= radius) || super.shouldSkipLocationSigned(random,
              dx,
              y,
              dz,
              radius,
              true
      );
   }

   protected boolean shouldSkipLocation(RandomSource randomSource, int dx, int y, int dz, int radius, boolean giantTrunk) {
      if(y == -1) {
         return dx == radius && dz == radius;
      }
      else if(y == 1) {
         return dx + dz > radius * 2 - 2;
      }
      else {
         return false;
      }
   }
}
