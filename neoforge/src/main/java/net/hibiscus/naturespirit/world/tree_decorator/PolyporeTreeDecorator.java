package net.hibiscus.naturespirit.world.tree_decorator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.hibiscus.naturespirit.blocks.PolyporeBlock;
import net.hibiscus.naturespirit.registration.NSWorldGen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import java.util.List;

public class PolyporeTreeDecorator extends TreeDecorator {

  public static final MapCodec<PolyporeTreeDecorator> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
    return instance.group(
        Codec.floatRange(0.0F, 1.0F).fieldOf("big_probability").forGetter((treeDecorator) -> treeDecorator.big_probability),
        Codec.floatRange(0.0F, 1.0F).fieldOf("small_probability").forGetter((treeDecorator) -> treeDecorator.small_probability),
        Codec.floatRange(0.0F, 1.0F).fieldOf("chance").forGetter((treeDecorator) -> treeDecorator.chance),
        BlockStateProvider.CODEC.fieldOf("block_provider").forGetter((treeDecorator) -> treeDecorator.block_provider),
        BlockStateProvider.CODEC.fieldOf("polypore_provider").forGetter((treeDecorator) -> treeDecorator.polypore_provider)
    ).apply(instance, PolyporeTreeDecorator::new);
  });
  private final float big_probability;
  private final float small_probability;
  private final float chance;
  private final BlockStateProvider block_provider;
  private final BlockStateProvider polypore_provider;

  public PolyporeTreeDecorator(float big_probability, float small_probability, float chance, BlockStateProvider block_provider, BlockStateProvider polypore_provider) {
    this.chance = chance;
    this.big_probability = big_probability;
    this.small_probability = small_probability;
    this.block_provider = block_provider;
    this.polypore_provider = polypore_provider;
  }

  protected TreeDecoratorType<?> type() {
    return NSWorldGen.POLYPORE_DECORATOR.get();
  }

  public void place(Context generator) {
    RandomSource random = generator.random();
    if (random.nextFloat() < this.chance) {
      List<BlockPos> list = generator.logs();
      list.stream().filter((pos) -> pos.getY() < list.get(0).getY() + 6 && pos.getY() > list.get(0).getY()).forEach((pos) -> {
        if (random.nextFloat() <= this.big_probability) {
          for (Direction direction : Direction.Plane.HORIZONTAL.shuffledCopy(random)) {
            if (generator.isAir(pos.relative(direction))) {
              Direction direction2 = direction.getClockWise();
              Direction direction3 = direction.getCounterClockWise();
              int radius = random.nextIntBetweenInclusive(1, 2);
              if (generator.isAir(pos.relative(direction2)) && generator.isAir(pos.relative(direction2).relative(direction))) {
                generateSquare(generator, pos, radius, direction, direction2, random);
                break;
              } else if (generator.isAir(pos.relative(direction3)) && generator.isAir(pos.relative(direction3).relative(direction))) {
                generateSquare(generator, pos, radius, direction, direction3, random);
                break;
              }
            }
          }
        }
        if (random.nextFloat() <= this.small_probability) {
          for (Direction direction : Direction.Plane.HORIZONTAL.shuffledCopy(random)) {
            if (generator.isAir(pos.relative(direction))) {
              Direction direction2 = direction.getClockWise();
              generator.setBlock(pos.relative(direction), polypore_provider.getState(random, pos.relative(direction)).trySetValue(PolyporeBlock.FACING, direction));
              if (generator.isAir(pos.relative(direction2))) {
                generator.setBlock(pos.relative(direction2), polypore_provider.getState(random, pos.relative(direction2)).trySetValue(PolyporeBlock.FACING, direction2));
              }
              break;
            }
          }
        }
      });
    }
  }

  protected void generateSquare(Context generator, BlockPos cornerPos, int radius, Direction direction1, Direction direction2, RandomSource random) {
    BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(cornerPos);
    BlockPos blockPos;
    for (int j = 0; j <= radius; ++j) {
      for (int k = 0; k <= radius; ++k) {
        blockPos = mutable.relative(direction2, k).relative(direction1, j);
        if (generator.isAir(blockPos)) {
          generator.setBlock(blockPos, block_provider.getState(random, blockPos).trySetValue(HugeMushroomBlock.DOWN, false));
        }
      }
    }

  }
}

