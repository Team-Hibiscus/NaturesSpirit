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
import net.minecraft.core.Direction.Axis;
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

public class OliveTrunkPlacer extends TrunkPlacer {

  public static final MapCodec<OliveTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
    return trunkPlacerParts(instance).and(instance.group(IntProvider.POSITIVE_CODEC.fieldOf("extra_branch_steps").forGetter((trunkPlacer) -> {
      return trunkPlacer.extraBranchSteps;
    }), Codec.floatRange(0.0F, 1.0F).fieldOf("place_branch_per_log_probability").forGetter((trunkPlacer) -> {
      return trunkPlacer.placeBranchPerLogProbability;
    }), IntProvider.NON_NEGATIVE_CODEC.fieldOf("extra_branch_length").forGetter((trunkPlacer) -> {
      return trunkPlacer.extraBranchLength;
    }), RegistryCodecs.homogeneousList(Registries.BLOCK).fieldOf("can_grow_through").forGetter((trunkPlacer) -> {
      return trunkPlacer.canGrowThrough;
    }))).apply(instance, OliveTrunkPlacer::new);
  });
  private final IntProvider extraBranchSteps;
  private final float placeBranchPerLogProbability;
  private final IntProvider extraBranchLength;
  private final HolderSet<Block> canGrowThrough;

  public OliveTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight, IntProvider extraBranchSteps, float placeBranchPerLogProbability,
      IntProvider extraBranchLength, HolderSet<Block> canGrowThrough) {
    super(baseHeight, firstRandomHeight, secondRandomHeight);
    this.extraBranchSteps = extraBranchSteps;
    this.placeBranchPerLogProbability = placeBranchPerLogProbability;
    this.extraBranchLength = extraBranchLength;
    this.canGrowThrough = canGrowThrough;
  }

  protected TrunkPlacerType<?> type() {
    return NSWorldGen.OLIVE_TRUNK_PLACER.get();
  }

  public List<FoliageAttachment> placeTrunk(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> replacer, RandomSource random, int height, BlockPos startPos, TreeConfiguration config) {
    List<FoliageAttachment> list = Lists.newArrayList();
    BlockPos blockPos = startPos.below();
    setDirtAt(world, replacer, random, blockPos, config);
    setDirtAt(world, replacer, random, blockPos.east(), config);
    setDirtAt(world, replacer, random, blockPos.south(), config);
    setDirtAt(world, replacer, random, blockPos.south().east(), config);
    MutableBlockPos mutable = new MutableBlockPos();
    for (int i = 0; i < height; ++i) {
      int j = startPos.getY() + i;
      MutableBlockPos mutablePos1 = mutable.set(startPos.getX(), j, startPos.getZ());
      this.placeLog(world, replacer, random, mutablePos1, config);
      this.placeLog(world, replacer, random, mutablePos1.east(), config);
      this.placeLog(world, replacer, random, mutablePos1.south(), config);
      this.placeLog(world, replacer, random, mutablePos1.south().east(), config);

      if (i == 0) {
        this.placeLog(world, replacer, random, getRoot(random, mutablePos1), config);
        this.placeLog(world, replacer, random, getRoot(random, mutablePos1), config);
      }

      if (i == height - 1) {
        this.generateExtraBranch(world, replacer, random, config, list, mutablePos1.north(1), j, Direction.NORTH, this.extraBranchSteps.sample(random));
        if (random.nextFloat() < this.placeBranchPerLogProbability - .65) {
          this.generateExtraBranch(world, replacer, random, config, list, mutablePos1.west(1), j, Direction.WEST, this.extraBranchSteps.sample(random));
        }

        this.generateExtraBranch(world, replacer, random, config, list, mutablePos1.south(2), j, Direction.SOUTH, this.extraBranchSteps.sample(random));
        if (random.nextFloat() <  this.placeBranchPerLogProbability - .65) {
          this.generateExtraBranch(world, replacer, random, config, list, mutablePos1.south(2), j, Direction.WEST, this.extraBranchSteps.sample(random));
        }

        this.generateExtraBranch(world, replacer, random, config, list, mutablePos1.east(2), j, Direction.EAST, this.extraBranchSteps.sample(random));
        if (random.nextFloat() < this.placeBranchPerLogProbability - .65) {
          this.generateExtraBranch(world, replacer, random, config, list, mutablePos1.east(2), j, Direction.NORTH, this.extraBranchSteps.sample(random));
        }

        this.generateExtraBranch(world, replacer, random, config, list, mutablePos1.south(2).east(), j, Direction.WEST, this.extraBranchSteps.sample(random));
        if (random.nextFloat() < this.placeBranchPerLogProbability - .65) {
          this.generateExtraBranch(world, replacer, random, config, list, mutablePos1.south().east(2), j, Direction.SOUTH, this.extraBranchSteps.sample(random));
        }
      }
    }

    return list;
  }

  private void generateExtraBranch(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> replacer, RandomSource random, TreeConfiguration config, List<FoliageAttachment> nodes,
      BlockPos pos, int yOffset, Direction direction, int steps) {
    int m = yOffset;
    int j = pos.getX();
    int k = pos.getZ();
    MutableBlockPos mutable = new MutableBlockPos();
    boolean bl = random.nextBoolean();

    Direction direction2 = random.nextBoolean() ? direction.getClockWise() : direction.getCounterClockWise();

    for (int l = 0; steps > 0; --steps, ++l) {
      boolean bl2 = random.nextBoolean();
      m = yOffset + l;
      if (bl2 && l != 0) {
        j += direction.getStepX();
        if (bl) {
          k += Math.max(0, direction2.getStepZ() - random.nextInt(2));
        } else {
          k += direction.getStepZ();
        }
      }
      int finalL = l;
      this.placeLog(world, replacer, random, mutable.set(j, m, k), config, blockState -> blockState.trySetValue(RotatedPillarBlock.AXIS, bl2 || finalL == 0 ? direction.getAxis() : Axis.Y));
    }
    if (m - yOffset >= 1) {
      nodes.add(new FoliageAttachment(new BlockPos(j, m, k), 0, false));
    }
  }

  protected boolean validTreePos(LevelSimulatedReader world, BlockPos pos) {
    return super.validTreePos(world, pos) || world.isStateAtPosition(pos, (state) -> state.is(this.canGrowThrough));
  }
  protected BlockPos getRoot(RandomSource random, MutableBlockPos mutablePos1) {
    return switch (random.nextInt( 7)) {
      case 1 -> mutablePos1.east(2);
      case 2 -> mutablePos1.south(2);
      case 3 -> mutablePos1.south(2).east();
      case 4 -> mutablePos1.west();
      case 5 -> mutablePos1.east().north();
      case 6 -> mutablePos1.south().east(2);
      case 7 -> mutablePos1.south().west();
      default -> mutablePos1.north();
    };
  }
}
