package net.hibiscus.naturespirit.world.foliage_placer;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.hibiscus.naturespirit.registration.NSWorldGen;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class RedwoodFoliagePlacer extends FoliagePlacer {

  public static final MapCodec<RedwoodFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
    return foliagePlacerParts(instance).and(IntProvider.codec(0, 32).fieldOf("trunk_height").forGetter((placer) -> {
      return placer.trunkHeight;
    })).apply(instance, RedwoodFoliagePlacer::new);
  });
  private final IntProvider trunkHeight;

  public RedwoodFoliagePlacer(IntProvider intProvider, IntProvider intProvider2, IntProvider trunkHeight) {
    super(intProvider, intProvider2);
    this.trunkHeight = trunkHeight;
  }

  protected FoliagePlacerType<?> type() {
    return NSWorldGen.REDWOOD_FOLIAGE_PLACER_TYPE.get();
  }

  protected void createFoliage(LevelSimulatedReader world, FoliageSetter placer, RandomSource random, TreeConfiguration config, int trunkHeight, FoliageAttachment treeNode, int foliageHeight, int radius,
      int offset) {
    BlockPos blockPos = treeNode.pos();
    BlockPos.MutableBlockPos mutable = blockPos.mutable();
    int layers = random.nextIntBetweenInclusive(1, 6);
    int middleLayers = layers / 2;
    int largeLayers = layers - middleLayers;
    offset++;

    mutable.setWithOffset(blockPos, 0, offset, 0);
    this.placeLeavesRow(world, placer, random, config, mutable, 0, 0, treeNode.doubleTrunk());
    mutable.setWithOffset(blockPos, 0, offset - 1, 0);
    this.placeLeavesRow(world, placer, random, config, mutable, 0, 0, treeNode.doubleTrunk());
    mutable.setWithOffset(blockPos, 0, offset - 2, 0);
    this.placeLeavesRow(world, placer, random, config, mutable, 0, 0, treeNode.doubleTrunk());
    this.generateCross(world, placer, random, config, blockPos, 2, offset - 3, treeNode.doubleTrunk());
    this.generateCross(world, placer, random, config, blockPos, 1, offset - 4, treeNode.doubleTrunk());

    int middeY = offset - 5;
    for (int a = middleLayers; a > 0; --a) {
      this.generateCircle(world, placer, random, config, blockPos, 2, middeY, treeNode.doubleTrunk());
      middeY--;
      this.generateCross(world, placer, random, config, blockPos, 1, middeY, treeNode.doubleTrunk());
      middeY--;
    }
    for (int a = largeLayers; a > 0; --a) {
      this.generateBigCircularSquare(world, placer, random, config, blockPos, 3, middeY, treeNode.doubleTrunk());
      middeY--;
      if (a - 1 != 0) {
        this.generateCross(world, placer, random, config, blockPos, 2, middeY, treeNode.doubleTrunk());
      } else {
        this.generateCircularSquare(world, placer, random, config, blockPos, 3, middeY, treeNode.doubleTrunk());
        middeY--;
        this.generateBigCircularSquare(world, placer, random, config, blockPos, 4, middeY, treeNode.doubleTrunk());
        middeY--;
        this.generateCross(world, placer, random, config, blockPos, 1, middeY, treeNode.doubleTrunk());
        middeY--;
        this.generateCircle(world, placer, random, config, blockPos, 2, middeY, treeNode.doubleTrunk());
      }
      middeY--;
    }
  }

  protected void generateCircle(LevelSimulatedReader world, FoliageSetter placer, RandomSource random, TreeConfiguration config, BlockPos centerPos, int radius, int y, boolean giantTrunk) {
    int i = giantTrunk ? 1 : 0;
    BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

    for (int j = -radius; j <= radius + i; ++j) {
      for (int k = -radius; k <= radius + i; ++k) {
        if (!this.validateCircle(random, j, y, k, radius, giantTrunk)) {
          mutable.setWithOffset(centerPos, j, y, k);
          tryPlaceLeaf(world, placer, random, config, mutable);
        }
      }
    }

  }

  protected void generateCross(LevelSimulatedReader world, FoliageSetter placer, RandomSource random, TreeConfiguration config, BlockPos centerPos, int radius, int y, boolean giantTrunk) {
    int i = giantTrunk ? 1 : 0;
    BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

    for (int j = -radius; j <= radius + i; ++j) {
      for (int k = -radius; k <= radius + i; ++k) {
        if (!this.validateCross(random, j, y, k, radius, giantTrunk)) {
          mutable.setWithOffset(centerPos, j, y, k);
          tryPlaceLeaf(world, placer, random, config, mutable);
        }
      }
    }

  }

  protected void generateCircularSquare(LevelSimulatedReader world, FoliageSetter placer, RandomSource random, TreeConfiguration config, BlockPos centerPos, int radius, int y,
      boolean giantTrunk) {
    int i = giantTrunk ? 1 : 0;
    BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

    for (int j = -radius; j <= radius + i; ++j) {
      for (int k = -radius; k <= radius + i; ++k) {
        if (!this.validateCircularSquare(random, j, y, k, radius, giantTrunk)) {
          mutable.setWithOffset(centerPos, j, y, k);
          tryPlaceLeaf(world, placer, random, config, mutable);
        }
      }
    }

  }

  protected void generateBigCircularSquare(LevelSimulatedReader world, FoliageSetter placer, RandomSource random, TreeConfiguration config, BlockPos centerPos, int radius, int y,
      boolean giantTrunk) {
    int i = giantTrunk ? 1 : 0;
    BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

    for (int j = -radius; j <= radius + i; ++j) {
      for (int k = -radius; k <= radius + i; ++k) {
        if (!this.validateBigCircularSquare(random, j, y, k, radius, giantTrunk)) {
          mutable.setWithOffset(centerPos, j, y, k);
          tryPlaceLeaf(world, placer, random, config, mutable);
        }
      }
    }

  }


  public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
    return Math.max(8, trunkHeight - this.trunkHeight.sample(random));
  }

  @Override
  protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
    return false;
  }

  protected boolean validateCircle(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
    if (giantTrunk) {
      dx = Math.min(Math.abs(dx), Math.abs(dx - 1));
      dz = Math.min(Math.abs(dz), Math.abs(dz - 1));
    } else {
      dx = Math.abs(dx);
      dz = Math.abs(dz);
    }
    return dx == radius && dz == radius && radius > 0;
  }

  protected boolean validateCircularSquare(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
    if (giantTrunk) {
      dx = Math.min(Math.abs(dx), Math.abs(dx - 1));
      dz = Math.min(Math.abs(dz), Math.abs(dz - 1));
    } else {
      dx = Math.abs(dx);
      dz = Math.abs(dz);
    }
    return ((dx == radius || dz == radius) && (dx > 0 && dz > 0));
  }

  protected boolean validateBigCircularSquare(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
    if (giantTrunk) {
      dx = Math.min(Math.abs(dx), Math.abs(dx - 1));
      dz = Math.min(Math.abs(dz), Math.abs(dz - 1));
    } else {
      dx = Math.abs(dx);
      dz = Math.abs(dz);
    }
    return ((dx == radius || dz == radius) && (dx > 1 && dz > 1));
  }

  protected boolean validateCross(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
    if (giantTrunk) {
      dx = Math.min(Math.abs(dx), Math.abs(dx - 1));
      dz = Math.min(Math.abs(dz), Math.abs(dz - 1));
    } else {
      dx = Math.abs(dx);
      dz = Math.abs(dz);
    }
    return (dx > 0 || dz > 0) && dx != 0 && dz != 0;
  }
}
