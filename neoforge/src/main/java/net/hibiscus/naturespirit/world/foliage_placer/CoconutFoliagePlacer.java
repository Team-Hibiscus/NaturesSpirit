package net.hibiscus.naturespirit.world.foliage_placer;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.hibiscus.naturespirit.registration.NSWorldGen;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.material.Fluids;

public class CoconutFoliagePlacer extends FoliagePlacer {

  public static final MapCodec<CoconutFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
    return foliagePlacerParts(instance).apply(instance, CoconutFoliagePlacer::new);
  });

  public CoconutFoliagePlacer(IntProvider intProvider, IntProvider intProvider2) {
    super(intProvider, intProvider2);
  }

  protected FoliagePlacerType<?> type() {
    return NSWorldGen.COCONUT_FOLIAGE_PLACER_TYPE.get();
  }

  protected void createFoliage(LevelSimulatedReader world, FoliageSetter placer, RandomSource random, TreeConfiguration config, int trunkHeight, FoliageAttachment treeNode, int foliageHeight, int radius,
      int offset) {
    BlockPos blockPos = treeNode.pos();
    BlockPos.MutableBlockPos mutable = blockPos.mutable();

    for (int l = -2; l <= foliageHeight; ++l) {
      if (l == -2) {
        mutable.setWithOffset(blockPos, 0, l, 0);
        tryPlaceLeaf(world, placer, random, config, mutable.offset(2, 0, 0));
        tryPlaceLeaf(world, placer, random, config, mutable.offset(0, 0, 2));
        tryPlaceLeaf(world, placer, random, config, mutable.offset(0, 0, -2));
        tryPlaceLeaf(world, placer, random, config, mutable.offset(-2, 0, 0));
      } else if (l == -1) {
        mutable.setWithOffset(blockPos, 0, l, 0);

        tryPlaceLeaf(world, placer, random, config, mutable.offset(2, 0, 0));
        tryPlaceLeaf(world, placer, random, config, mutable.offset(0, 0, 2));
        tryPlaceLeaf(world, placer, random, config, mutable.offset(0, 0, -2));
        tryPlaceLeaf(world, placer, random, config, mutable.offset(-2, 0, 0));

        tryPlaceLeaf(world, placer, random, config, mutable.offset(1, 0, 0));
        tryPlaceLeaf(world, placer, random, config, mutable.offset(0, 0, 1));
        tryPlaceLeaf(world, placer, random, config, mutable.offset(0, 0, -1));
        tryPlaceLeaf(world, placer, random, config, mutable.offset(-1, 0, 0));

        placeFoliageBlock2(world, placer, random, config, mutable.offset(2, 0, 2));
        placeFoliageBlock2(world, placer, random, config, mutable.offset(-2, 0, 2));
        placeFoliageBlock2(world, placer, random, config, mutable.offset(-2, 0, -2));
        placeFoliageBlock2(world, placer, random, config, mutable.offset(2, 0, -2));
      } else if (l == 0) {
        mutable.setWithOffset(blockPos, 0, l, 0);
        placeLeavesRow(world, placer, random, config, mutable, 1, 0, false);
        placeFoliageBlock2(world, placer, random, config, mutable.offset(2, 0, 2));
        placeFoliageBlock2(world, placer, random, config, mutable.offset(-2, 0, 2));
        placeFoliageBlock2(world, placer, random, config, mutable.offset(-2, 0, -2));
        placeFoliageBlock2(world, placer, random, config, mutable.offset(2, 0, -2));
      } else if (l == 1) {
        mutable.setWithOffset(blockPos, 0, l, 0);
        placeLeavesRow(world, placer, random, config, mutable, 1, 0, false);
        tryPlaceLeaf(world, placer, random, config, mutable.offset(2, 0, 0));
        tryPlaceLeaf(world, placer, random, config, mutable.offset(0, 0, 2));
        tryPlaceLeaf(world, placer, random, config, mutable.offset(0, 0, -2));
        tryPlaceLeaf(world, placer, random, config, mutable.offset(-2, 0, 0));
      } else if (l == 2) {
        mutable.setWithOffset(blockPos, 0, l, 0);
        tryPlaceLeaf(world, placer, random, config, mutable.offset(2, 0, 0));
        tryPlaceLeaf(world, placer, random, config, mutable.offset(0, 0, 2));
        tryPlaceLeaf(world, placer, random, config, mutable.offset(0, 0, -2));
        tryPlaceLeaf(world, placer, random, config, mutable.offset(-2, 0, 0));

        tryPlaceLeaf(world, placer, random, config, mutable.offset(3, 0, 0));
        tryPlaceLeaf(world, placer, random, config, mutable.offset(0, 0, 3));
        tryPlaceLeaf(world, placer, random, config, mutable.offset(0, 0, -3));
        tryPlaceLeaf(world, placer, random, config, mutable.offset(-3, 0, 0));

        placeFoliageBlock2(world, placer, random, config, mutable.offset(2, 0, 2));
        placeFoliageBlock2(world, placer, random, config, mutable.offset(-2, 0, 2));
        placeFoliageBlock2(world, placer, random, config, mutable.offset(-2, 0, -2));
        placeFoliageBlock2(world, placer, random, config, mutable.offset(2, 0, -2));
      }
    }

  }

  protected void placeLeavesRow(LevelSimulatedReader world, FoliageSetter placer, RandomSource random, TreeConfiguration config, BlockPos centerPos, int radius, int y, boolean giantTrunk) {
    int i = giantTrunk ? 1 : 0;
    BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

    for (int j = -radius; j <= radius + i; ++j) {
      for (int k = -radius; k <= radius + i; ++k) {
        mutable.setWithOffset(centerPos, j, y, k);
        tryPlaceLeaf(world, placer, random, config, mutable);
      }
    }

  }

  protected static boolean placeFoliageBlock2(LevelSimulatedReader world, FoliageSetter placer, RandomSource random, TreeConfiguration config, BlockPos pos) {
    if (!TreeFeature.validTreePos(world, pos)) {
      return false;
    } else {
      BlockState blockState = config.foliageProvider.getState(random, pos);
      if (blockState.hasProperty(BlockStateProperties.WATERLOGGED)) {
        blockState = blockState.setValue(BlockStateProperties.WATERLOGGED, world.isFluidAtPosition(pos, (fluidState) -> fluidState.isSourceOfType(Fluids.WATER)));
      }
      if (blockState.hasProperty(LeavesBlock.DISTANCE)) {
        blockState = blockState.setValue(LeavesBlock.DISTANCE, 5);
      }

      placer.set(pos, blockState);
      return true;
    }
  }


  public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
    return 2;
  }

  protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
    return false;
  }
}
