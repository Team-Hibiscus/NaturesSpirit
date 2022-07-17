package net.hibiscus.naturespirit.world.feature;

import com.mojang.serialization.Codec;
import net.hibiscus.naturespirit.NatureSpirit;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.BlockStateProviderType;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;

public class HibiscusSimpleBlockStateProvider extends BlockStateProvider {
    public static final Codec<HibiscusSimpleBlockStateProvider> CODEC;
    private final BlockState state;

    public HibiscusSimpleBlockStateProvider(BlockState state) {
        this.state = state;
    }

    public BlockStateProviderType<?> getType() {
        return NatureSpirit.HIBISCUS_SIMPLE_BLOCK_STATE_PROVIDER;
    }

    public BlockState getBlockState(Random random, BlockPos pos) {
        return this.state;
    }

    static {
        CODEC = BlockState.CODEC.fieldOf("state").xmap(HibiscusSimpleBlockStateProvider::new, (hibiscusSimpleBlockStateProvider) -> {
            return hibiscusSimpleBlockStateProvider.state;
        }).codec();
    }
}
