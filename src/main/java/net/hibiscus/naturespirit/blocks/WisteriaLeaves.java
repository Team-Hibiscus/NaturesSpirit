package net.hibiscus.naturespirit.blocks;

import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public class WisteriaLeaves extends LeavesBlock implements BonemealableBlock {
    public WisteriaLeaves(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter level, BlockPos pos, BlockState state, boolean isClient) {
        Block vineBlock;
        Block vineBlock2;

        if (this.asBlock() == HibiscusBlocks.BLUE_WISTERIA_LEAVES) {
            vineBlock = HibiscusBlocks.BLUE_WISTERIA_VINES;
            vineBlock2 = HibiscusBlocks.BLUE_WISTERIA_VINES_PLANT;
        } else
        if (this.asBlock() == HibiscusBlocks.PINK_WISTERIA_LEAVES) {
            vineBlock = HibiscusBlocks.PINK_WISTERIA_VINES;
            vineBlock2 = HibiscusBlocks.PINK_WISTERIA_VINES_PLANT;
        } else
        if (this.asBlock() == HibiscusBlocks.PURPLE_WISTERIA_LEAVES) {
            vineBlock = HibiscusBlocks.PURPLE_WISTERIA_VINES;
            vineBlock2 = HibiscusBlocks.PURPLE_WISTERIA_VINES_PLANT;
        } else
        {
            vineBlock = HibiscusBlocks.WHITE_WISTERIA_VINES;
            vineBlock2 = HibiscusBlocks.WHITE_WISTERIA_VINES_PLANT;
        }
        Optional <BlockPos> optional = BlockUtil.getTopConnectedBlock(level, pos, vineBlock2, Direction.DOWN, vineBlock);
        return (optional.isPresent() && level.getBlockState(optional.get().relative(Direction.DOWN)).isAir()) || level.getBlockState(pos.relative(Direction.DOWN)).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        Block vineBlock;
        Block vineBlock2;

        if (this.asBlock() == HibiscusBlocks.BLUE_WISTERIA_LEAVES) {
            vineBlock = HibiscusBlocks.BLUE_WISTERIA_VINES;
            vineBlock2 = HibiscusBlocks.BLUE_WISTERIA_VINES_PLANT;
        } else
        if (this.asBlock() == HibiscusBlocks.PINK_WISTERIA_LEAVES) {
            vineBlock = HibiscusBlocks.PINK_WISTERIA_VINES;
            vineBlock2 = HibiscusBlocks.PINK_WISTERIA_VINES_PLANT;
        } else
        if (this.asBlock() == HibiscusBlocks.PURPLE_WISTERIA_LEAVES) {
            vineBlock = HibiscusBlocks.PURPLE_WISTERIA_VINES;
            vineBlock2 = HibiscusBlocks.PURPLE_WISTERIA_VINES_PLANT;
        } else
        {
            vineBlock = HibiscusBlocks.WHITE_WISTERIA_VINES;
            vineBlock2 = HibiscusBlocks.WHITE_WISTERIA_VINES_PLANT;
        }

        Optional <BlockPos> optional = BlockUtil.getTopConnectedBlock(serverLevel, blockPos, vineBlock2, Direction.DOWN, vineBlock);
        if (optional.isPresent()) {
            BlockState blockState2 = serverLevel.getBlockState((BlockPos)optional.get());
            ((WisteriaVine)blockState2.getBlock()).performBonemeal(serverLevel, randomSource, (BlockPos)optional.get(), blockState2);
        }
        if (optional.isEmpty()) {
            serverLevel.setBlock(blockPos.below(), vineBlock.defaultBlockState(), 2);
        }
    }
}
