package net.hibiscus.naturespirit.world.foliage_placer;

import com.mojang.datafixers.Products;
import com.mojang.serialization.Codec;
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

public class BirchFoliagePlacer extends FoliagePlacer {

  public static final MapCodec<BirchFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
    return createCodec(instance).apply(instance, BirchFoliagePlacer::new);
  });
  protected final int height;

  protected static <P extends BirchFoliagePlacer> Products.P3<RecordCodecBuilder.Mu<P>, IntProvider, IntProvider, Integer> createCodec(RecordCodecBuilder.Instance<P> builder) {
    return foliagePlacerParts(builder).and(Codec.intRange(0, 16).fieldOf("height").forGetter((placer) -> {
      return placer.height;
    }));
  }

  public BirchFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
    super(radius, offset);
    this.height = height;
  }

  protected FoliagePlacerType<?> type() {
    return NSWorldGen.BIRCH_FOLIAGE_PLACER_TYPE.get();
  }

  protected void createFoliage(LevelSimulatedReader world, FoliageSetter placer, RandomSource random, TreeConfiguration config, int trunkHeight, FoliageAttachment treeNode,
      int foliageHeight, int radius, int offset) {
    for (int i = offset; i >= offset - foliageHeight; --i) {
      int j = Math.max(Math.min(radius + treeNode.radiusOffset() - 1 - i / 2, 3), 0);
      if (i == offset - foliageHeight) {
        this.generateDiagonal(world, placer, random, config, treeNode.pos(), radius, i, treeNode.doubleTrunk());
      } else if (j == 2 && Math.max(Math.min(radius + treeNode.radiusOffset() - 2 - i / 2, 3), 0) == 1) {
        this.generateCircle(world, placer, random, config, treeNode.pos(), j, i, treeNode.doubleTrunk());
      } else if (j == 3) {
        this.generateCircularSquare(world, placer, random, config, treeNode.pos(), j, i, treeNode.doubleTrunk());
      } else {
        this.placeLeavesRow(world, placer, random, config, treeNode.pos(), j, i, treeNode.doubleTrunk());
      }

    }

  }

  protected void generateDiagonal(LevelSimulatedReader world, FoliageSetter placer, RandomSource random, TreeConfiguration config, BlockPos centerPos, int radius, int y, boolean giantTrunk) {
    int i = giantTrunk ? 1 : 0;
    BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

    for (int j = -radius; j <= radius + i; ++j) {
      for (int k = -radius; k <= radius + i; ++k) {
        if (!this.validateDiagonal(random, j, y, k, radius, giantTrunk)) {
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

  protected boolean validateCircularSquare(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
    dx = Math.abs(dx);
    dz = Math.abs(dz);
    return ((dx == radius || dz == radius) && (dx != 0 && dz != 0 || random.nextInt(3) == 0)) || (dx == radius - 1 && dz == radius - 1 && random.nextInt(2) == 0);
  }

  protected boolean validateCircle(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
    dx = Math.abs(dx);
    dz = Math.abs(dz);
    return dx == radius && dz == radius && radius > 0 && random.nextFloat() < .7f;
  }

  protected boolean validateDiagonal(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
    dx = Math.abs(dx);
    dz = Math.abs(dz);
    return (dx > 1 || dz > 1) && dx != 0 && dz != 0;
  }

  public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
    return this.height;
  }

  protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
    return dx == radius && dz == radius && (random.nextInt(2) == 0 || y == 0);
  }
}
