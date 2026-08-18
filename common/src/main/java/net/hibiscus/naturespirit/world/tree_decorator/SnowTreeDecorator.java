package net.hibiscus.naturespirit.world.tree_decorator;

import com.mojang.serialization.MapCodec;
import net.hibiscus.naturespirit.registration.NSWorldGen;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import java.util.List;

public class SnowTreeDecorator extends TreeDecorator {

  public static final MapCodec<SnowTreeDecorator> CODEC = MapCodec.unit(SnowTreeDecorator::new);

  public SnowTreeDecorator() {
  }

  protected TreeDecoratorType<?> type() {
    return NSWorldGen.SNOW_DECORATOR.get();
  }

  public void place(Context generator) {
    List<BlockPos> list2 = generator.leaves();
    list2.stream().filter((pos) -> generator.isAir(pos.above()))
        .forEach((pos) -> generator.setBlock(pos.above(), Blocks.SNOW.defaultBlockState()));

    list2.stream().filter((pos) -> generator.isAir(pos.below()) && generator.isAir(pos.below().below()))
        .forEach((pos) -> {
          BlockPos blockPos = generator.level().getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, pos);
          if (generator.isAir(blockPos)) {
            generator.setBlock(blockPos, Blocks.SNOW.defaultBlockState());
          }
        });
  }
}

