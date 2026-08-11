//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.hibiscus.naturespirit.world.trunk;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.hibiscus.naturespirit.registration.NSWorldGen;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import java.util.List;
import java.util.function.BiConsumer;

public class BushTrunkPlacer extends TrunkPlacer {
  public static final MapCodec<BushTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
    return trunkPlacerParts(instance).apply(instance, BushTrunkPlacer::new);
  });

  public BushTrunkPlacer(int i, int j, int k) {
    super(i, j, k);
  }

  protected TrunkPlacerType<?> type() {
    return NSWorldGen.BUSH_TRUNK_PLACER.get();
  }

  public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> replacer, RandomSource random, int height, BlockPos startPos, TreeConfiguration config) {
    for(int i = 0; i < height; ++i) {
      this.placeLog(world, replacer, random, startPos.above(i), config);
    }

    return ImmutableList.of(new FoliagePlacer.FoliageAttachment(startPos.above(height), 0, false));
  }
}
