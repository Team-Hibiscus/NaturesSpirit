package net.hibiscus.naturespirit.world.tree_decorator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.hibiscus.naturespirit.blocks.OliveBranchBlock;
import net.hibiscus.naturespirit.registration.NSBlocks;
import net.hibiscus.naturespirit.registration.NSWorldGen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import java.util.List;

public class OliveBranchTreeDecorator extends TreeDecorator {

  public static final MapCodec<OliveBranchTreeDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(OliveBranchTreeDecorator::new, (decorator) -> {
    return decorator.probability;
  });
  private final float probability;

  public OliveBranchTreeDecorator(float probability) {
    this.probability = probability;
  }

  protected TreeDecoratorType<?> type() {
    return NSWorldGen.OLIVE_BRANCH_DECORATOR.get();
  }

  public void place(Context generator) {
    RandomSource random = generator.random();
      List<BlockPos> list = generator.logs();
      list.stream().filter((pos) -> pos.getY() > list.getFirst().getY() + 2).forEach((pos) -> {

        for (Direction direction : Direction.values()) {
          if (random.nextFloat() < this.probability) {
            Direction direction2 = direction.getOpposite();
            BlockPos blockPos = pos.offset(direction2.getStepX(), direction2.getStepY(), direction2.getStepZ());
            if (generator.isAir(blockPos)) {
              generator.setBlock(blockPos, NSBlocks.OLIVE_BRANCH.get().defaultBlockState().setValue(
                  OliveBranchBlock.FACING,
                  direction.getOpposite()
              ).setValue(
                  OliveBranchBlock.AGE,
                  random.nextInt(4)
              ));
            }
          }
        }

      });
  }
}

