package net.hibiscus.naturespirit.world.tree_decorator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.hibiscus.naturespirit.registration.NSWorldGen;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class WisteriaVinesTreeDecorator extends TreeDecorator {

  public static final MapCodec<WisteriaVinesTreeDecorator> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
    return instance.group(Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter((treeDecorator) -> treeDecorator.probability),
        BlockStateProvider.CODEC.fieldOf("block_provider").forGetter((treeDecorator) -> treeDecorator.blockProvider),
        BlockStateProvider.CODEC.fieldOf("block_provider2").forGetter((treeDecorator) -> treeDecorator.blockProvider2),
        BlockStateProvider.CODEC.fieldOf("block_provider3").forGetter((treeDecorator) -> treeDecorator.blockProvider3),
        BlockStateProvider.CODEC.fieldOf("block_provider4").forGetter((treeDecorator) -> treeDecorator.blockProvider4),
        Codec.intRange(0, 10).fieldOf("number").forGetter((treeDecorator) -> treeDecorator.number)).apply(instance, WisteriaVinesTreeDecorator::new);
  });
  protected final BlockStateProvider blockProvider;
  protected final BlockStateProvider blockProvider2;
  protected final BlockStateProvider blockProvider3;
  protected final BlockStateProvider blockProvider4;
  private final float probability;
  protected int number;

  public WisteriaVinesTreeDecorator(float probability, BlockStateProvider blockProvider, BlockStateProvider blockProvider2, BlockStateProvider blockProvider3,
      BlockStateProvider blockProvider4, int number) {
    this.probability = probability;
    this.blockProvider = blockProvider;
    this.blockProvider2 = blockProvider2;
    this.blockProvider3 = blockProvider3;
    this.blockProvider4 = blockProvider4;
    this.number = number;
  }

  private static void placeVines(BlockPos pos, BlockStateProvider block, BlockStateProvider block2, BlockStateProvider block3, BlockStateProvider block4, Context generator,
      int number) {
    RandomSource random = generator.random();
    generator.setBlock(pos, block3.getState(random, pos));
    if (!generator.isAir(pos.above(2)) || !generator.isAir(pos.above(3))) {
      generator.setBlock(pos.above(), block4.getState(random, pos));
    }
    for (pos = pos.below(); number > 0; --number) {
      if (generator.isAir(pos)) {
        if (number == 1 || !generator.isAir(pos.below()) || random.nextBoolean()) {
          generator.setBlock(pos, block2.getState(random, pos));
          break;
        }
        generator.setBlock(pos, block.getState(random, pos));
      }
      pos = pos.below();
    }

  }

  @Override
  protected TreeDecoratorType<?> type() {
    return NSWorldGen.WISTERIA_VINES_TREE_DECORATOR.get();
  }

  public void place(Context context) {
    RandomSource randomSource = context.random();
    context.leaves().forEach((blockPos) -> {
      BlockPos blockPos2;
      if (randomSource.nextFloat() < this.probability) {
        blockPos2 = blockPos.below();
        if (context.isAir(blockPos2)) {
          placeVines(blockPos2, blockProvider, blockProvider2, blockProvider4, blockProvider3, context, this.number);
        }
      }

    });
  }
}
