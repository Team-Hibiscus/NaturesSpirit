//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.hibiscus.naturespirit.world.trunk;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.hibiscus.naturespirit.registration.NSWorldGen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Plane;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer.FoliageAttachment;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import java.util.List;
import java.util.function.BiConsumer;

public class CoconutTrunkPlacer extends TrunkPlacer {

  public static final MapCodec<CoconutTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
    return trunkPlacerParts(instance).and(instance.group(IntProvider.POSITIVE_CODEC.fieldOf("trunk_steps").forGetter((trunkPlacer) -> {
      return trunkPlacer.trunkSteps;
    }), Codec.floatRange(0.0F, 1.0F).fieldOf("fork_probability").forGetter((trunkPlacer) -> {
      return trunkPlacer.forkProbability;
    }), RegistryCodecs.homogeneousList(Registries.BLOCK).fieldOf("can_grow_through").forGetter((trunkPlacer) -> {
      return trunkPlacer.canGrowThrough;
    }))).apply(instance, CoconutTrunkPlacer::new);
  });
  private final IntProvider trunkSteps;
  private final float forkProbability;
  private final HolderSet<Block> canGrowThrough;

  public CoconutTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight, IntProvider trunkSteps, float forkProbability, HolderSet<Block> canGrowThrough) {
    super(baseHeight, firstRandomHeight, secondRandomHeight);
    this.trunkSteps = trunkSteps;
    this.forkProbability = forkProbability;
    this.canGrowThrough = canGrowThrough;
  }

  protected TrunkPlacerType<?> type() {
    return NSWorldGen.COCONUT_TRUNK_PLACER.get();
  }

  public List<FoliageAttachment> placeTrunk(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> replacer, RandomSource random, int height, BlockPos startPos, TreeConfiguration config) {
    List<FoliageAttachment> list = Lists.newArrayList();
    MutableBlockPos mutable = new MutableBlockPos().set(startPos.getX(), startPos.getY() - 1, startPos.getZ());
    MutableBlockPos forkedMutable = new MutableBlockPos().set(startPos.getX(), startPos.getY() + 1, startPos.getZ());
    boolean forked = random.nextFloat() < this.forkProbability;
    int steps = this.trunkSteps.sample(random);
    int increment = random.nextIntBetweenInclusive(0, 1);
    int forkedIncrement = random.nextIntBetweenInclusive(0, 1);
    Direction offsetDirection = Plane.HORIZONTAL.getRandomDirection(random);
    Direction offsetDirection2 = Plane.HORIZONTAL.getRandomDirection(random);
    int forkedOffset = random.nextIntBetweenInclusive(0, 2);
    int forkedHeight = height - forkedOffset;

    for (int i = 0; i < height; ++i) {
      BlockPos pos = mutable.move(0, 1, 0);
      this.placeLog(world, replacer, random, pos, config);
      if (i >= 2) {
        if (forked && i < forkedHeight) {
          BlockPos forkedPos = forkedMutable.move(0, 1, 0);
          this.placeLog(world, replacer, random, forkedPos, config);
          if (i == forkedHeight - 1) {
            list.add(new FoliageAttachment(forkedPos.above(1), 0, false));
          }
        }
        if (i == height - 1) {
          list.add(new FoliageAttachment(pos.above(1), 0, false));
        }
        if (increment == 0) {
          mutable.move(offsetDirection);
          if (offsetDirection2 != offsetDirection) {
            mutable.move(offsetDirection2);
          }
          increment = (height / steps) - random.nextIntBetweenInclusive(-1, 1);
        }
        if (forkedIncrement == 0) {
          forkedMutable.move(offsetDirection.getOpposite());
          if (offsetDirection2 != offsetDirection) {
            if (random.nextBoolean()) {
              forkedMutable.move(offsetDirection2.getOpposite());
            }
          }
          forkedIncrement = (forkedHeight / steps) - random.nextIntBetweenInclusive(-1, 1);
        }
        --increment;
        --forkedIncrement;
      }

    }

    return list;
  }

  protected boolean validTreePos(LevelSimulatedReader world, BlockPos pos) {
    return super.validTreePos(world, pos) || world.isStateAtPosition(pos, (state) -> {
      return state.is(this.canGrowThrough);
    });
  }
}
