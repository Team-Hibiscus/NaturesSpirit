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

public class RedwoodTrunkPlacer extends TrunkPlacer {

  public static final MapCodec<RedwoodTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
    return trunkPlacerParts(instance).apply(instance, RedwoodTrunkPlacer::new);
  });

  public RedwoodTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
    super(baseHeight, firstRandomHeight, secondRandomHeight);
  }

  @Override
  protected TrunkPlacerType<?> type() {
    return NSWorldGen.REDWOOD_TRUNK_PLACER.get();
  }

  public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> replacer, RandomSource random, int height, BlockPos startPos,
      TreeConfiguration config) {
    BlockPos blockPos = startPos.below();
    setDirtAt(world, replacer, random, blockPos.north().east(), config);
    setDirtAt(world, replacer, random, blockPos.north().west(), config);
    setDirtAt(world, replacer, random, blockPos.north(), config);
    setDirtAt(world, replacer, random, blockPos.west(), config);
    setDirtAt(world, replacer, random, blockPos, config);
    setDirtAt(world, replacer, random, blockPos.east(), config);
    setDirtAt(world, replacer, random, blockPos.south(), config);
    setDirtAt(world, replacer, random, blockPos.south().east(), config);
    setDirtAt(world, replacer, random, blockPos.south().west(), config);
    BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

    for (int i = 0; i < height; ++i) {
      this.setLog(world, replacer, random, mutable, config, startPos, 0, i, 0);
      if (i < height - 1) {
        this.setLog(world, replacer, random, mutable, config, startPos, 1, i, 0);
        this.setLog(world, replacer, random, mutable, config, startPos, 1, i, -1);
        this.setLog(world, replacer, random, mutable, config, startPos, 1, i, 1);
        this.setLog(world, replacer, random, mutable, config, startPos, 0, i, 1);
        this.setLog(world, replacer, random, mutable, config, startPos, -1, i, 1);
        this.setLog(world, replacer, random, mutable, config, startPos, -1, i, 0);
        this.setLog(world, replacer, random, mutable, config, startPos, 0, i, -1);
        this.setLog(world, replacer, random, mutable, config, startPos, -1, i, -1);
      }
    }

    return ImmutableList.of(new FoliagePlacer.FoliageAttachment(startPos.above(height), 0, false));
  }

  private void setLog(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> replacer, RandomSource random, BlockPos.MutableBlockPos tmpPos, TreeConfiguration config, BlockPos startPos, int dx,
      int dy, int dz) {
    tmpPos.setWithOffset(startPos, dx, dy, dz);
    this.placeLogIfFree(world, replacer, random, tmpPos, config);
  }
}
