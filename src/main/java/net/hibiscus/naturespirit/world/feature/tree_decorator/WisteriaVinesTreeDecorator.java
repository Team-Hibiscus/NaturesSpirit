package net.hibiscus.naturespirit.world.feature.tree_decorator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.hibiscus.naturespirit.NatureSpirit;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class WisteriaVinesTreeDecorator extends TreeDecorator{
    public static final Codec<WisteriaVinesTreeDecorator> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter((treeDecorator) -> {
            return treeDecorator.probability;
        }), BlockStateProvider.TYPE_CODEC.fieldOf("block_provider").forGetter((treeDecorator) -> {
            return treeDecorator.blockProvider;
        }), BlockStateProvider.TYPE_CODEC.fieldOf("block_provider2").forGetter((treeDecorator) -> {
            return treeDecorator.blockProvider2;
        })).apply(instance, WisteriaVinesTreeDecorator::new);
    });
    private final float probability;
    protected final BlockStateProvider blockProvider;
    protected final BlockStateProvider blockProvider2;

    protected TreeDecoratorType<?> getType() {
        return NatureSpirit.WISTERIA_VINES_TREE_DECORATOR;
    }

    public WisteriaVinesTreeDecorator(float probability, BlockStateProvider blockProvider, BlockStateProvider blockProvider2) {
        this.probability = probability;
        this.blockProvider = blockProvider;
        this.blockProvider2 = blockProvider2;
    }

    public void generate(TreeDecorator.Generator generator) {
        Random random = generator.getRandom();
        generator.getLeavesPositions().forEach((pos) -> {
            BlockPos blockPos;
            if (random.nextFloat() < this.probability) {
                blockPos = pos.down();
                if (generator.isAir(blockPos)) {
                    placeVines(blockPos, blockProvider, blockProvider2, generator);
                }
            }

        });
    }

    private static void placeVines(BlockPos pos, BlockStateProvider block,  BlockStateProvider block2, TreeDecorator.Generator generator) {
        Random random = generator.getRandom();
        generator.replace(pos, block.getBlockState(random, pos));
        int i = 4;

        for(pos = pos.down(); i > 0; --i) {
            if (generator.isAir(pos)) {
                if (i == 1 || !generator.isAir(pos.down())) {
                    generator.replace(pos, block2.getBlockState(random, pos));
                    break;
                }
                generator.replace(pos, block.getBlockState(random, pos));
            }
            pos = pos.down();
        }

    }
}