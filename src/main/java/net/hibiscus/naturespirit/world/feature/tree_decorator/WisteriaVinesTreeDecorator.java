package net.hibiscus.naturespirit.world.feature.tree_decorator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.hibiscus.naturespirit.NatureSpirit;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class WisteriaVinesTreeDecorator extends TreeDecorator {
    public static final Codec<WisteriaVinesTreeDecorator> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter((treeDecorator) -> {
            return treeDecorator.probability;
        }), BlockStateProvider.CODEC.fieldOf("block_provider").forGetter((treeDecorator) -> {
            return treeDecorator.blockProvider;
        }), BlockStateProvider.CODEC.fieldOf("block_provider2").forGetter((treeDecorator) -> {
            return treeDecorator.blockProvider2;
        })).apply(instance, WisteriaVinesTreeDecorator::new);
    });
    private final float probability;
    protected final BlockStateProvider blockProvider;
    protected final BlockStateProvider blockProvider2;

    public WisteriaVinesTreeDecorator(float probability, BlockStateProvider blockProvider, BlockStateProvider blockProvider2) {
        this.probability = probability;
        this.blockProvider = blockProvider;
        this.blockProvider2 = blockProvider2;
    }

    @Override
    protected TreeDecoratorType <?> type() {
        return NatureSpirit.WISTERIA_VINES_TREE_DECORATOR;
    }

    public void place (Context context) {
        RandomSource randomSource = context.random();
        context.leaves().forEach((blockPos) -> {
            BlockPos blockPos2;
            if (randomSource.nextFloat() < this.probability) {
                blockPos2 = blockPos.below();
                if (context.isAir(blockPos2)) {
                    placeVines(blockPos2, blockProvider, blockProvider2, context);
                }
            }

        });
    }

    private static void placeVines(BlockPos pos, BlockStateProvider block, BlockStateProvider block2, Context generator) {
        RandomSource random = generator.random();
        generator.setBlock(pos, block.getState(random, pos));
        int i = 2;

        for(pos = pos.below(); i > 0; --i) {
            if (generator.isAir(pos)) {
                if (i == 1 || !generator.isAir(pos.below()) || random.nextBoolean()) {
                    generator.setBlock(pos, block2.getState(random, pos));
                    break;
                }
                generator.setBlock(pos, block.getState(random, pos));
            }
            pos = pos.below();
        }

    }
}