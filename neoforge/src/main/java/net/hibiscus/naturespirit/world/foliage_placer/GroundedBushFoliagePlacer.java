package net.hibiscus.naturespirit.world.foliage_placer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.hibiscus.naturespirit.registration.NSWorldGen;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import java.util.function.Predicate;

public class GroundedBushFoliagePlacer extends FoliagePlacer {

  public static final MapCodec<GroundedBushFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
    return foliagePlacerParts(instance).and(instance.group(IntProvider.codec(1, 512).fieldOf("foliage_height").forGetter((placer) -> {
      return placer.foliageHeight;
    }), Codec.intRange(0, 256).fieldOf("leaf_placement_attempts").forGetter((placer) -> {
      return placer.leafPlacementAttempts;
    }))).apply(instance, GroundedBushFoliagePlacer::new);
  });
  private final IntProvider foliageHeight;
  private final int leafPlacementAttempts;

  public GroundedBushFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider foliageHeight, int leafPlacementAttempts) {
    super(radius, offset);
    this.foliageHeight = foliageHeight;
    this.leafPlacementAttempts = leafPlacementAttempts;
  }

  protected FoliagePlacerType<?> type() {
    return NSWorldGen.GROUNDED_BUSH_PLACER_TYPE.get();
  }

  protected void createFoliage(LevelSimulatedReader world, FoliageSetter placer, RandomSource random, TreeConfiguration config, int trunkHeight, FoliageAttachment treeNode,
      int foliageHeight, int radius, int offset) {
    BlockPos blockPos = treeNode.pos().below();
    BlockPos.MutableBlockPos mutable = blockPos.mutable();

    for (int i = 0; i <= foliageHeight; i++) {
      for (int j = 0; j < this.leafPlacementAttempts / (i + 1); ++j) {
        mutable.setWithOffset(blockPos, random.nextInt(radius) - random.nextInt(radius), i, random.nextInt(radius) - random.nextInt(radius));
        if (!world.isStateAtPosition(mutable.below(), Predicate.isEqual(Blocks.AIR.defaultBlockState()))) {
          tryPlaceLeaf(world, placer, random, config, mutable);
        }
      }
    }

  }

  public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
    return this.foliageHeight.sample(random);
  }

  protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
    return false;
  }
}
