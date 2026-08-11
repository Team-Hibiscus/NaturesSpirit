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
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer.FoliageAttachment;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import java.util.List;
import java.util.function.BiConsumer;

public class PaloVerdeTrunkPlacer extends TrunkPlacer {

  public static final MapCodec<PaloVerdeTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
    return trunkPlacerParts(instance).and(instance.group(IntProvider.POSITIVE_CODEC.fieldOf("extra_branch_steps").forGetter((trunkPlacer) -> {
      return trunkPlacer.extraBranchSteps;
    }), Codec.floatRange(0.0F, 1.0F).fieldOf("place_branch_per_log_probability").forGetter((trunkPlacer) -> {
      return trunkPlacer.placeBranchPerLogProbability;
    }), IntProvider.NON_NEGATIVE_CODEC.fieldOf("extra_branch_length").forGetter((trunkPlacer) -> {
      return trunkPlacer.extraBranchLength;
    }), RegistryCodecs.homogeneousList(Registries.BLOCK).fieldOf("can_grow_through").forGetter((trunkPlacer) -> {
      return trunkPlacer.canGrowThrough;
    }))).apply(instance, PaloVerdeTrunkPlacer::new);
  });
  private final IntProvider extraBranchSteps;
  private final float placeBranchPerLogProbability;
  private final IntProvider extraBranchLength;
  private final HolderSet<Block> canGrowThrough;

  public PaloVerdeTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight, IntProvider extraBranchSteps, float placeBranchPerLogProbability,
      IntProvider extraBranchLength, HolderSet<Block> canGrowThrough) {
    super(baseHeight, firstRandomHeight, secondRandomHeight);
    this.extraBranchSteps = extraBranchSteps;
    this.placeBranchPerLogProbability = placeBranchPerLogProbability;
    this.extraBranchLength = extraBranchLength;
    this.canGrowThrough = canGrowThrough;
  }

  protected TrunkPlacerType<?> type() {
    return NSWorldGen.PALO_VERDE_TRUNK_PLACER.get();
  }

  public List<FoliageAttachment> placeTrunk(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> replacer, RandomSource random, int height, BlockPos startPos, TreeConfiguration config) {
    List<FoliageAttachment> list = Lists.newArrayList();
    MutableBlockPos mutable = new MutableBlockPos();
    MutableBlockPos mutable2 = new MutableBlockPos();
    Direction trunkOffset = Plane.HORIZONTAL.getRandomDirection(random);
    Direction trunkOffset2 = Plane.HORIZONTAL.getRandomDirection(random);
    boolean bl = random.nextBoolean();

    for (int i = 0; i < height; ++i) {
      int j = startPos.getY() + i;
      Direction direction = Plane.HORIZONTAL.getRandomDirection(random);
      MutableBlockPos mutablePos1 = mutable.set(startPos.getX(), j, startPos.getZ());
      BlockPos pos2 = mutablePos1.relative(trunkOffset);
      BlockPos pos3 = pos2.relative(trunkOffset2);
      BlockPos blockPos = i >= 2 ? (trunkOffset != trunkOffset2 ? pos3 : pos2) : mutablePos1;
      int l = Math.toIntExact(Math.round(Math.pow(random.nextGaussian(), .15F) * 3.5)) - 1;
      float float1 = random.nextFloat();
      float float2 = random.nextFloat();

      if (i == 2) {
        this.placeLog(world, replacer, random, mutablePos1, config);
      }
      if (this.placeLog(world, replacer, random, blockPos, config) && i < height - 1) {
        int m = height + i - 5;
        if (i > 1 && l > 0) {
          this.generateExtraBranch(world, replacer, random, config, list, blockPos, height, j, direction, l, m);

          if (float1 < this.placeBranchPerLogProbability - 0.35) {
            this.generateExtraBranch(world, replacer, random, config, list, blockPos, height, j, direction.getClockWise(), l, m);
          }
          if (float1 < this.placeBranchPerLogProbability - 0.65) {
            this.generateExtraBranch(world, replacer, random, config, list, blockPos, height, j, direction.getClockWise().getClockWise(), l, m);
          }
        }
      }
      if (i == height - 1) {
        list.add(new FoliageAttachment(blockPos.above(1), 0, false));
      }
      if (bl && i >= 2) {
        Direction forkedDirection = Plane.HORIZONTAL.getRandomDirection(random);

        MutableBlockPos forkedMutablePos1 = mutable2.set(startPos.getX(), j, startPos.getZ());
        BlockPos forkedPos2 = forkedMutablePos1.relative(trunkOffset.getOpposite()).relative(Direction.DOWN);
        BlockPos forkedPos3 = forkedPos2.relative(trunkOffset2.getOpposite());
        BlockPos forkedBlockPos = trunkOffset.getOpposite() != trunkOffset2.getOpposite() ? forkedPos3 : forkedPos2;

        if (i == 2) {
          this.placeLog(world, replacer, random, forkedMutablePos1, config);
        }
        if (this.placeLog(world, replacer, random, forkedBlockPos, config) && i < height - 1) {
          int m = height + i - 5;
          if (i > 1 && l > 0) {
            this.generateExtraBranch(world, replacer, random, config, list, forkedBlockPos, height, j, forkedDirection, l, m);

            if (float2 < this.placeBranchPerLogProbability - 0.35) {
              this.generateExtraBranch(world, replacer, random, config, list, forkedBlockPos, height, j, forkedDirection.getClockWise(), l, m);
            }
            if (float2 < this.placeBranchPerLogProbability - 0.65) {
              this.generateExtraBranch(world, replacer, random, config, list, forkedBlockPos, height, j, forkedDirection.getClockWise().getClockWise(), l, m);
            }
          }
        }
        if (i == height - 2) {
          list.add(new FoliageAttachment(forkedBlockPos.above(1), 0, false));
        }
      }
    }

    return list;
  }

  private void generateExtraBranch(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> replacer, RandomSource random, TreeConfiguration config, List<FoliageAttachment> nodes, BlockPos pos,
      int height, int yOffset, Direction direction, int length, int steps) {
    int i = yOffset + length;
    int j = pos.getX();
    int k = pos.getZ();
    MutableBlockPos mutable = new MutableBlockPos();
    boolean bl = random.nextFloat() < .5F;
    Direction direction2 = random.nextFloat() < .5F ? direction.getClockWise() : direction.getCounterClockWise();

    for (int l = length; l < height - 3 && steps > 0; --steps) {
      if (l >= 1) {
        int m = Math.round(yOffset + l / 5);
        j += direction.getStepX();
        if (bl) {
          k += Math.max(0, direction2.getStepZ() - random.nextInt(2));
        } else {
          k += direction.getStepZ();
        }
        if (this.placeLog(world, replacer, random, mutable.set(j, m, k), config, blockState -> blockState.trySetValue(RotatedPillarBlock.AXIS, direction.getAxis()))) {
          i = m + 1;
        }
      }
      ++l;
    }
    if (i - yOffset >= 1) {
      BlockPos blockPos = new BlockPos(j, i, k);
      nodes.add(new FoliageAttachment(blockPos, 0, false));
    }

  }

  protected boolean validTreePos(LevelSimulatedReader world, BlockPos pos) {
    return super.validTreePos(world, pos) || world.isStateAtPosition(pos, (state) -> {
      return state.is(this.canGrowThrough);
    });
  }
}
