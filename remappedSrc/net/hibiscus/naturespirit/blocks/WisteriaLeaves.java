package net.hibiscus.naturespirit.blocks;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.LeavesBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockLocating;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class WisteriaLeaves extends LeavesBlock implements Fertilizable {
    public WisteriaLeaves(Settings properties) {
        super(properties);
    }

    @Override
    public boolean isFertilizable(@NotNull WorldView levelReader, @NotNull BlockPos blockPos, @NotNull BlockState blockState, boolean bl) {
        Block vineBlock;
        Block vineBlock2;

        if (this.asBlock() == HibiscusBlocks.BLUE_WISTERIA_LEAVES) {
            vineBlock = HibiscusBlocks.BLUE_WISTERIA_VINES;
            vineBlock2 = HibiscusBlocks.BLUE_WISTERIA_VINES_PLANT;
        } else if (this.asBlock() == HibiscusBlocks.PINK_WISTERIA_LEAVES) {
            vineBlock = HibiscusBlocks.PINK_WISTERIA_VINES;
            vineBlock2 = HibiscusBlocks.PINK_WISTERIA_VINES_PLANT;
        } else if (this.asBlock() == HibiscusBlocks.PURPLE_WISTERIA_LEAVES) {
            vineBlock = HibiscusBlocks.PURPLE_WISTERIA_VINES;
            vineBlock2 = HibiscusBlocks.PURPLE_WISTERIA_VINES_PLANT;
        } else {
            vineBlock = HibiscusBlocks.WHITE_WISTERIA_VINES;
            vineBlock2 = HibiscusBlocks.WHITE_WISTERIA_VINES_PLANT;
        }
        Optional <BlockPos> optional = BlockLocating.findColumnEnd(levelReader, blockPos, vineBlock2, Direction.DOWN, vineBlock);
        return (optional.isPresent() && levelReader.getBlockState(optional.get().offset(Direction.DOWN)).isAir()) || levelReader.getBlockState(blockPos.offset(Direction.DOWN)).isAir();
    }


    @Override
    public boolean canGrow(World level, Random randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public void grow(ServerWorld serverLevel, Random randomSource, BlockPos blockPos, BlockState blockState) {
        Block vineBlock;
        Block vineBlock2;

        if (this.asBlock() == HibiscusBlocks.BLUE_WISTERIA_LEAVES) {
            vineBlock = HibiscusBlocks.BLUE_WISTERIA_VINES;
            vineBlock2 = HibiscusBlocks.BLUE_WISTERIA_VINES_PLANT;
        } else if (this.asBlock() == HibiscusBlocks.PINK_WISTERIA_LEAVES) {
            vineBlock = HibiscusBlocks.PINK_WISTERIA_VINES;
            vineBlock2 = HibiscusBlocks.PINK_WISTERIA_VINES_PLANT;
        } else if (this.asBlock() == HibiscusBlocks.PURPLE_WISTERIA_LEAVES) {
            vineBlock = HibiscusBlocks.PURPLE_WISTERIA_VINES;
            vineBlock2 = HibiscusBlocks.PURPLE_WISTERIA_VINES_PLANT;
        } else {
            vineBlock = HibiscusBlocks.WHITE_WISTERIA_VINES;
            vineBlock2 = HibiscusBlocks.WHITE_WISTERIA_VINES_PLANT;
        }

        Optional <BlockPos> optional = BlockLocating.findColumnEnd(serverLevel, blockPos, vineBlock2, Direction.DOWN, vineBlock);
        if (optional.isPresent()) {
            BlockState blockState2 = serverLevel.getBlockState(optional.get());
            ((WisteriaVine) blockState2.getBlock()).grow(serverLevel, randomSource, optional.get(), blockState2);
        }
        if (optional.isEmpty()) {
            serverLevel.setBlockState(blockPos.down(), vineBlock.getDefaultState(), 2);
        }
    }
}
