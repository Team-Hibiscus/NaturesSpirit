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

public class SaxaulTrunkPlacer extends TrunkPlacer {

  public static final MapCodec<SaxaulTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
    return trunkPlacerParts(instance).and(instance.group(IntProvider.POSITIVE_CODEC.fieldOf("extra_branch_steps").forGetter((trunkPlacer) -> {
      return trunkPlacer.extraBranchSteps;
    }), Codec.floatRange(0.0F, 1.0F).fieldOf("place_branch_per_log_probability").forGetter((trunkPlacer) -> {
      return trunkPlacer.placeBranchPerLogProbability;
    }), IntProvider.NON_NEGATIVE_CODEC.fieldOf("extra_branch_length").forGetter((trunkPlacer) -> {
      return trunkPlacer.extraBranchLength;
    }), RegistryCodecs.homogeneousList(Registries.BLOCK).fieldOf("can_grow_through").forGetter((trunkPlacer) -> {
      return trunkPlacer.canGrowThrough;
    }))).apply(instance, SaxaulTrunkPlacer::new);
  });
  private final IntProvider extraBranchSteps;
  private final float placeBranchPerLogProbability;
  private final IntProvider extraBranchLength;
  private final HolderSet<Block> canGrowThrough;

  public SaxaulTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight, IntProvider extraBranchSteps, float placeBranchPerLogProbability,
      IntProvider extraBranchLength, HolderSet<Block> canGrowThrough) {
    super(baseHeight, firstRandomHeight, secondRandomHeight);
    this.extraBranchSteps = extraBranchSteps;
    this.placeBranchPerLogProbability = placeBranchPerLogProbability;
    this.extraBranchLength = extraBranchLength;
    this.canGrowThrough = canGrowThrough;
  }

  protected TrunkPlacerType<?> type() {
    return NSWorldGen.SAXAUL_TRUNK_PLACER.get();
  }

  public List<FoliageAttachment> placeTrunk(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> replacer, RandomSource random, int height, BlockPos startPos, TreeConfiguration config) {
    List<FoliageAttachment> list = Lists.newArrayList();
    MutableBlockPos mutable = new MutableBlockPos();
    MutableBlockPos mutable2 = new MutableBlockPos();
    Direction trunkOffset = Plane.HORIZONTAL.getRandomDirection(random);
    Direction trunkOffset2 = Plane.HORIZONTAL.getRandomDirection(random);
    Direction directionUnvalidated = Plane.HORIZONTAL.getRandomDirection(random);
    Direction direction = directionUnvalidated == trunkOffset.getOpposite() ? directionUnvalidated.getOpposite() : directionUnvalidated;
    Direction forkedDirectionUnvalidated = Plane.HORIZONTAL.getRandomDirection(random);
    Direction forkedDirection =
        forkedDirectionUnvalidated == direction || forkedDirectionUnvalidated == trunkOffset ? forkedDirectionUnvalidated.getOpposite() : forkedDirectionUnvalidated;

    for (int i = 0; i < height; ++i) {
      int j = startPos.getY() + i;
      MutableBlockPos mutablePos1 = mutable.set(startPos.getX(), j, startPos.getZ());
      BlockPos pos2 = mutablePos1.relative(trunkOffset);
      BlockPos pos3 = pos2.relative(trunkOffset2);
      BlockPos blockPos = i >= 2 ? (trunkOffset != trunkOffset2 ? pos3 : pos2) : mutablePos1;
      int l = Math.max(1, Math.toIntExact(Math.round(Math.pow(.4, i - 1) * (height - 1))));
      float float1 = random.nextFloat();

      MutableBlockPos forkedMutablePos1 = mutable2.set(startPos.getX(), j, startPos.getZ());
      BlockPos forkedPos2 = forkedMutablePos1.relative(trunkOffset.getOpposite()).relative(Direction.DOWN);
      BlockPos forkedPos3 = forkedPos2.relative(trunkOffset2.getOpposite());
      BlockPos forkedBlockPos = trunkOffset.getOpposite() != trunkOffset2.getOpposite() ? forkedPos3 : forkedPos2;
      boolean validator = l < 3;

      if (i == 2) {
        this.placeLog(world, replacer, random, blockPos, config);
        this.placeLog(world, replacer, random, forkedBlockPos, config);
      }

      this.placeLog(world, replacer, random, blockPos, config);
      if (i >= 2) {
        this.placeLog(world, replacer, random, forkedBlockPos, config);
      }
      if (validator) {
        if (float1 < this.placeBranchPerLogProbability) {
          this.generateExtraBranch(world, replacer, random, config, list, blockPos, j, direction, l);
          this.generateExtraBranch(world, replacer, random, config, list, forkedBlockPos, j, forkedDirection, l);
        }
        if (float1 < this.placeBranchPerLogProbability - 0.25) {
          this.generateExtraBranch(world, replacer, random, config, list, blockPos, j, direction.getClockWise().getClockWise(), l);
          this.generateExtraBranch(world, replacer, random, config, list, forkedBlockPos, j, forkedDirection.getClockWise().getClockWise(), l);
        }
      }
      if (i == height - 1) {
        list.add(new FoliageAttachment(blockPos.above(1), 0, false));
      }
      direction = direction.getClockWise().getClockWise();
      forkedDirection = forkedDirection.getClockWise().getClockWise();
    }

    return list;
  }

  private void generateExtraBranch(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> replacer, RandomSource random, TreeConfiguration config, List<FoliageAttachment> nodes, BlockPos pos,
      int yOffset, Direction direction, int length) {
    int j = pos.getX();
    int k = pos.getZ();
    MutableBlockPos mutable = new MutableBlockPos();
    Direction direction2 = random.nextFloat() < .5F ? direction.getClockWise() : direction.getCounterClockWise();
    int nextBetween = random.nextIntBetweenInclusive(3, 5);
    boolean bl = random.nextFloat() < .5F;
    boolean bl3 = random.nextBoolean();
    boolean bl4 = false;

    for (int l = 0; l < length; ++l) {
      int m = yOffset + (l / nextBetween);
      Direction.Axis axis;
      if (bl) {
        k += direction2.getStepZ();
        if (bl3) {
          j += direction2.getStepX();
        }
        bl = random.nextFloat() < .8F;
        axis = direction2.getAxis();
      } else {
        axis = direction.getAxis();
        k += direction.getStepZ();
        if (bl3) {
          j += direction.getStepX();
        }
      }
      this.placeLog(world, replacer, random, mutable.set(j, m, k), config, blockState -> blockState.trySetValue(RotatedPillarBlock.AXIS, axis));
      if (l + 1 == length) {
        BlockPos blockPos = new BlockPos(j, m + 1, k);
        nodes.add(new FoliageAttachment(blockPos, 0, false));
      }
      if (random.nextFloat() < .4f && !bl4 && l > length / 3) {
        this.generateSecondaryBranch(world, replacer, random, config, nodes, mutable, m, direction2);
        bl4 = true;
      }
    }


  }

  private void generateSecondaryBranch(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> replacer, RandomSource random, TreeConfiguration config, List<FoliageAttachment> nodes, BlockPos pos,
      int yOffset, Direction direction) {
    int j = pos.getX();
    int k = pos.getZ();
    MutableBlockPos mutable = new MutableBlockPos();

    for (int l = 0; l < 1; ++l) {
      j += direction.getStepX();
      k += direction.getStepZ();
      this.placeLog(world, replacer, random, mutable.set(j, yOffset, k), config, blockState -> blockState.trySetValue(RotatedPillarBlock.AXIS, direction.getAxis()));
      BlockPos blockPos = new BlockPos(j, yOffset + 1, k);
      nodes.add(new FoliageAttachment(blockPos, 0, false));
    }


  }

  protected boolean validTreePos(LevelSimulatedReader world, BlockPos pos) {
    return super.validTreePos(world, pos) || world.isStateAtPosition(pos, (state) -> state.is(this.canGrowThrough));
  }
}
