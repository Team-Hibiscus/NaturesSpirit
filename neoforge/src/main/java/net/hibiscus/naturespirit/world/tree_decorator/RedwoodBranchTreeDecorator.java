package net.hibiscus.naturespirit.world.tree_decorator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.hibiscus.naturespirit.registration.NSWorldGen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import java.util.List;

public class RedwoodBranchTreeDecorator extends TreeDecorator {

  public static final MapCodec<RedwoodBranchTreeDecorator> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
    return instance.group(
        Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter((treeDecorator) -> treeDecorator.probability),
        BlockStateProvider.CODEC.fieldOf("leaf_provider").forGetter((treeDecorator) -> treeDecorator.leaf_provider)
    ).apply(instance, RedwoodBranchTreeDecorator::new);
  });
  private final float probability;
  private final BlockStateProvider leaf_provider;

  public RedwoodBranchTreeDecorator(float probability, BlockStateProvider leaf_provider) {
    this.probability = probability;
    this.leaf_provider = leaf_provider;
  }

  protected TreeDecoratorType<?> type() {
    return NSWorldGen.REDWOOD_BRANCH_DECORATOR.get();
  }

  public void place(Context generator) {
    RandomSource random = generator.random();
    List<BlockPos> list = generator.logs();
    List<BlockPos> list2 = generator.leaves();
    list.stream().filter((pos) -> pos.getY() < list2.get(0).getY() - 2 && pos.getY() > list.get(0).getY() + 2).forEach((pos) -> {

      if (random.nextFloat() <= this.probability) {
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        BlockPos.MutableBlockPos blockPos = pos.offset(direction.getStepX(), 0, direction.getStepZ()).mutable();
        if (generator.isAir(blockPos)) {
          generator.setBlock(blockPos, leaf_provider.getState(random, blockPos));
        }
        blockPos.move(direction.getClockWise());
        if (generator.isAir(blockPos)) {
          generator.setBlock(blockPos, leaf_provider.getState(random, blockPos));
        }
        blockPos.move(direction.getClockWise().getOpposite(), 2);
        if (generator.isAir(blockPos)) {
          generator.setBlock(blockPos, leaf_provider.getState(random, blockPos));
        }
        blockPos.move(direction.getClockWise()).move(direction);
        if (generator.isAir(blockPos)) {
          generator.setBlock(blockPos, leaf_provider.getState(random, blockPos));
        }
        blockPos.move(direction.getOpposite()).move(Direction.UP);
        if (generator.isAir(blockPos)) {
          generator.setBlock(blockPos, leaf_provider.getState(random, blockPos));
        }
      }
    });
  }
}

