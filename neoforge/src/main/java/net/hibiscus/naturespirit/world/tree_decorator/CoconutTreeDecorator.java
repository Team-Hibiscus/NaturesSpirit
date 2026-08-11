package net.hibiscus.naturespirit.world.tree_decorator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.hibiscus.naturespirit.blocks.CoconutBlock;
import net.hibiscus.naturespirit.registration.NSBlocks;
import net.hibiscus.naturespirit.registration.NSWorldGen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import java.util.List;

public class CoconutTreeDecorator extends TreeDecorator {

  public static final MapCodec<CoconutTreeDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(CoconutTreeDecorator::new, (decorator) -> {
    return decorator.probability;
  });
  private final float probability;

  public CoconutTreeDecorator(float probability) {
    this.probability = probability;
  }

  protected TreeDecoratorType<?> type() {
    return NSWorldGen.COCONUT_TREE_DECORATOR.get();
  }

  public void place(Context generator) {
    RandomSource random = generator.random();
    if (!(random.nextFloat() >= this.probability)) {
      List<BlockPos> list = generator.logs();
      list.stream().filter((pos) -> pos.getY() >= list.get(list.size() - 1).getY() - 2).forEach((pos) -> {

        for (Direction direction : Direction.Plane.HORIZONTAL) {
          if (random.nextFloat() <= 0.5F) {
            Direction direction2 = direction.getOpposite();
            BlockPos blockPos = pos.offset(direction2.getStepX(), 0, direction2.getStepZ());
            if (generator.isAir(blockPos)) {
              generator.setBlock(blockPos, NSBlocks.COCONUT_BLOCK.get().defaultBlockState().setValue(
                  CoconutBlock.FACING,
                  direction.getOpposite()
              ));
            }
          }
        }

      });
    }
  }
}

