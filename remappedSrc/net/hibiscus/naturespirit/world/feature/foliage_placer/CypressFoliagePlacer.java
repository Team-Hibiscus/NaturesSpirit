package net.hibiscus.naturespirit.world.feature.foliage_placer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.util.HibiscusWorldGen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class CypressFoliagePlacer extends FoliagePlacer {
   public static final Codec <CypressFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) -> {
      return foliagePlacerParts(instance).and(IntProvider.codec(0, 24)
              .fieldOf("trunk_height")
              .forGetter((placer) -> {
                 return placer.trunkHeight;
              })).apply(instance, CypressFoliagePlacer::new);
   });
   private final IntProvider trunkHeight;

   public CypressFoliagePlacer(IntProvider intProvider, IntProvider intProvider2, IntProvider trunkHeight) {
      super(intProvider, intProvider2);
      this.trunkHeight = trunkHeight;
   }

   protected FoliagePlacerType <?> type() {
      return HibiscusWorldGen.CYPRESS_FOLIAGE_PLACER_TYPE;
   }

   protected void createFoliage(LevelSimulatedReader world, FoliageSetter placer, RandomSource random, TreeConfiguration config, int trunkHeight, FoliageAttachment treeNode, int foliageHeight, int radius, int offset) {
      BlockPos blockPos = treeNode.pos();
      BlockPos.MutableBlockPos mutable = blockPos.mutable();
      boolean nextBoolean = random.nextBoolean();
      boolean nextBoolean2 = random.nextBoolean();
      boolean nextBoolean3 = random.nextBoolean();
      int i = random.nextInt(3);
      int j = 1;
      int k = 0;

      for(int l = offset; l >= -foliageHeight - 5; --l) {
         if(l <= offset - 4 && l >= offset - 14) {
            mutable.setWithOffset(blockPos, 0, l, 0);
            this.placeLeavesRow(world, placer, random, config, blockPos, 1, l, treeNode.doubleTrunk());
         }
         if(l >= offset - 5) {
            mutable.setWithOffset(blockPos, 0, l, 0);
            if((nextBoolean) && l == offset) {
               tryPlaceLeaf(world, placer, random, config, mutable);
            }
            if((nextBoolean || nextBoolean2) && l == offset - 1) {
               tryPlaceLeaf(world, placer, random, config, mutable);
            }
            if((nextBoolean || nextBoolean2 || nextBoolean3) && l == offset - 2) {
               tryPlaceLeaf(world, placer, random, config, mutable);
            }
            if(l <= offset - 3) {
               tryPlaceLeaf(world, placer, random, config, mutable);
            }
         }
         else if(l == offset - 5) {
            mutable.setWithOffset(blockPos, 0, l, 0);
            if(random.nextBoolean()) {
               tryPlaceLeaf(world, placer, random, config, mutable.relative(Direction.getRandom(random), 1)
                       .relative(Direction.getRandom(random), 1)
                       .above(1));
            }
         }
         else if(l >= offset - 10) {
            mutable.setWithOffset(blockPos, 0, l, 0);
            if(random.nextBoolean()) {
               tryPlaceLeaf(world,
                       placer,
                       random,
                       config,
                       mutable.relative(Direction.NORTH, 1).relative(Direction.EAST, 1)
               );
            }
            if(random.nextBoolean()) {
               tryPlaceLeaf(world,
                       placer,
                       random,
                       config,
                       mutable.relative(Direction.NORTH, 1).relative(Direction.WEST, 1)
               );
            }
            if(random.nextBoolean()) {
               tryPlaceLeaf(world,
                       placer,
                       random,
                       config,
                       mutable.relative(Direction.SOUTH, 1).relative(Direction.EAST, 1)
               );
            }
            if(random.nextBoolean()) {
               tryPlaceLeaf(world,
                       placer,
                       random,
                       config,
                       mutable.relative(Direction.SOUTH, 1).relative(Direction.WEST, 1)
               );
            }
         }
         else if(l >= offset - 13) {
            this.generateDiagonalSqaure(world, placer, random, config, blockPos, 2, l, treeNode.doubleTrunk());
         }
      }

   }

   protected void placeLeavesRow(LevelSimulatedReader world, FoliageSetter placer, RandomSource random, TreeConfiguration config, BlockPos centerPos, int radius, int y, boolean giantTrunk) {
      int i = giantTrunk ? 1 : 0;
      BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

      for(int j = -radius; j <= radius + i; ++j) {
         for(int k = -radius; k <= radius + i; ++k) {
            if(!this.shouldSkipLocationSigned(random, j, y, k, radius, giantTrunk)) {
               mutable.setWithOffset(centerPos, j, y, k);
               tryPlaceLeaf(world, placer, random, config, mutable);
            }
         }
      }

   }

   protected void generateDiagonalSqaure(LevelSimulatedReader world, FoliageSetter placer, RandomSource random, TreeConfiguration config, BlockPos centerPos, int radius, int y, boolean giantTrunk) {
      int i = giantTrunk ? 1 : 0;
      BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

      for(int j = -radius; j <= radius + i; ++j) {
         for(int k = -radius; k <= radius + i; ++k) {
            if(!this.isPositionInvalid2(random, j, y, k, radius, giantTrunk)) {
               mutable.setWithOffset(centerPos, j, y, k);
               tryPlaceLeaf(world, placer, random, config, mutable);
            }
         }
      }

   }

   protected boolean isPositionInvalid2(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
      int i;
      int j;
      if(giantTrunk) {
         i = Math.min(Math.abs(dx), Math.abs(dx - 1));
         j = Math.min(Math.abs(dz), Math.abs(dz - 1));
      }
      else {
         i = Math.abs(dx);
         j = Math.abs(dz);
      }

      return this.isInvalidForLeaves2(random, i, y, j, radius, giantTrunk);
   }


   public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
      return Math.max(8, trunkHeight - this.trunkHeight.sample(random));
   }

   protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
      return dx == radius && dz == radius && radius > 0;
   }

   protected boolean isInvalidForLeaves2(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
      int trunkHeight = this.trunkHeight.sample(random);
      return (dx > 1 || dz > 1) && dx != 0 && dz != 0 && trunkHeight - y != trunkHeight;
   }
}
