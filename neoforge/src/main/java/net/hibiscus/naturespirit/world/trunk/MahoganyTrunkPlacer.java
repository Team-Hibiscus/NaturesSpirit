package net.hibiscus.naturespirit.world.trunk;

import com.google.common.collect.Lists;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.hibiscus.naturespirit.registration.NSWorldGen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import java.util.List;
import java.util.function.BiConsumer;

public class MahoganyTrunkPlacer extends TrunkPlacer {

  public static final MapCodec<MahoganyTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec((instance) -> trunkPlacerParts(instance).apply(instance, MahoganyTrunkPlacer::new));

  public MahoganyTrunkPlacer(int i, int j, int k) {
    super(i, j, k);
  }

  protected TrunkPlacerType<?> type() {
    return NSWorldGen.MAHOGANY_TRUNK_PLACER.get();
  }

  public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> replacer, RandomSource random, int height, BlockPos startPos,
      TreeConfiguration config) {
    BlockPos blockPos = startPos.below();
    setDirtAt(world, replacer, random, blockPos, config);
    setDirtAt(world, replacer, random, blockPos.east(), config);
    setDirtAt(world, replacer, random, blockPos.south(), config);
    setDirtAt(world, replacer, random, blockPos.south().east(), config);
    BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
    List<FoliagePlacer.FoliageAttachment> nodes = Lists.newArrayList();

    for (int i = 0; i < height; ++i) {
      this.setLog(world, replacer, random, mutable, config, startPos, 0, i, 0);
      if (i < height - 1) {
        this.setLog(world, replacer, random, mutable, config, startPos, 1, i, 0);
        this.setLog(world, replacer, random, mutable, config, startPos, 1, i, 1);
        this.setLog(world, replacer, random, mutable, config, startPos, 0, i, 1);
        if (i > height - 9 && random.nextFloat() < .85 && i % 2 == 0) {
          Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
          this.generateExtraBranch(world, replacer, random, config, nodes, startPos.relative(direction.getClockWise(), 1), i, direction, random.nextIntBetweenInclusive(6, 9), 1);
          if (random.nextFloat() < .75) {
            this.generateExtraBranch(world, replacer, random, config, nodes, startPos, i, direction.getClockWise(), random.nextIntBetweenInclusive(6, 9), 1);
          }
          if (random.nextFloat() < .75) {
            this.generateExtraBranch(world, replacer, random, config, nodes, startPos.relative(direction.getClockWise(), 1), i, direction.getClockWise().getClockWise(),
                random.nextIntBetweenInclusive(6, 9), 1);
          }
          this.generateExtraBranch(world, replacer, random, config, nodes, startPos, i, direction.getClockWise().getClockWise().getClockWise(),
              random.nextIntBetweenInclusive(6, 9), 1);
        }
      }
    }
    this.generateExtraBranch(world, replacer, random, config, nodes, startPos.north(1), height - 1, Direction.EAST, random.nextIntBetweenInclusive(5, 7), 0);
    this.generateExtraBranch(world, replacer, random, config, nodes, startPos, height - 1, Direction.WEST, random.nextIntBetweenInclusive(5, 7), 0);
    this.generateExtraBranch(world, replacer, random, config, nodes, startPos.east(1), height - 1, Direction.SOUTH, random.nextIntBetweenInclusive(5, 7), 0);
    this.generateExtraBranch(world, replacer, random, config, nodes, startPos, height - 1, Direction.NORTH, random.nextIntBetweenInclusive(5, 7), 0);

    return nodes;
  }

  private void generateExtraBranch(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> replacer, RandomSource random, TreeConfiguration config, List<FoliagePlacer.FoliageAttachment> nodes,
      BlockPos pos, int yOffset, Direction direction, int length, int foliageRadius) {
    int j = pos.getX();
    int k = pos.getZ();
    BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
    Direction direction2 = random.nextFloat() < .5F ? direction.getClockWise() : direction.getCounterClockWise();
    int nextBetween = random.nextIntBetweenInclusive(2, 4);
    boolean bl = random.nextFloat() < .5F;
    boolean bl2 = false;
    boolean bl3 = random.nextBoolean();
    boolean bl4 = false;

    for (int l = 0; l < length; ++l) {
      int m = pos.getY() + (yOffset + (l / nextBetween));
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
        BlockPos blockPos = new BlockPos(j, m, k);
        nodes.add(new FoliagePlacer.FoliageAttachment(blockPos, 0, false));
      }
      if (random.nextFloat() < .4f && !bl2 && l > length / 3) {
        this.generateSecondaryBranch(world, replacer, random, config, nodes, mutable, m, direction2.getOpposite());
        bl2 = true;
      }
      if (random.nextFloat() < .4f && !bl4 && l > length / 3) {
        this.generateSecondaryBranch(world, replacer, random, config, nodes, mutable, m, direction2);
        bl4 = true;
      }
    }


  }

  private void generateSecondaryBranch(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> replacer, RandomSource random, TreeConfiguration config, List<FoliagePlacer.FoliageAttachment> nodes,
      BlockPos pos, int yOffset, Direction direction) {
    int j = pos.getX();
    int k = pos.getZ();
    BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

    for (int l = 0; l < 4; ++l) {
      j += direction.getStepX();
      k += direction.getStepZ();
      this.placeLog(world, replacer, random, mutable.set(j, yOffset, k), config, blockState -> blockState.trySetValue(RotatedPillarBlock.AXIS, direction.getAxis()));
      if (l + 1 == 4) {
        BlockPos blockPos = new BlockPos(j, yOffset, k);
        nodes.add(new FoliagePlacer.FoliageAttachment(blockPos, 0, false));
      }
    }


  }

  private void setLog(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> replacer, RandomSource random, BlockPos.MutableBlockPos tmpPos, TreeConfiguration config, BlockPos startPos, int dx,
      int dy, int dz) {
    tmpPos.setWithOffset(startPos, dx, dy, dz);
    this.placeLogIfFree(world, replacer, random, tmpPos, config);
  }
}
