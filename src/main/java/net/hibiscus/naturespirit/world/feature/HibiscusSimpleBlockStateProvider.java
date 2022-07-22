package net.hibiscus.naturespirit.world.feature;

import com.mojang.serialization.Codec;
import net.hibiscus.naturespirit.NatureSpirit;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProviderType;

public class HibiscusSimpleBlockStateProvider extends BlockStateProvider {
    public static final Codec<HibiscusSimpleBlockStateProvider> CODEC;
    private final BlockState state;

    public HibiscusSimpleBlockStateProvider(BlockState state) {
        this.state = state;
    }

    static {
        CODEC = BlockState.CODEC.fieldOf("state").xmap(HibiscusSimpleBlockStateProvider::new, (hibiscusSimpleBlockStateProvider) -> {
            return hibiscusSimpleBlockStateProvider.state;
        }).codec();
    }

    @Override
    protected BlockStateProviderType <?> type() {
        return NatureSpirit.HIBISCUS_SIMPLE_BLOCK_STATE_PROVIDER;
    }

    @Override
    public BlockState getState(RandomSource randomSource, BlockPos blockPos) {
        return this.state;
    }
}
