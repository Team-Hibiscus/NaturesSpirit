package net.hibiscus.naturespirit.blocks;

import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockLocating;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Optional;

public class WisteriaLeaves extends LeavesBlock implements Fertilizable {
    public WisteriaLeaves(Settings settings) {
        super(settings);
    }

    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
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
        Optional <BlockPos> optional = BlockLocating.findColumnEnd(world, pos, vineBlock2, Direction.DOWN, vineBlock);
        return (optional.isPresent() && world.getBlockState(((BlockPos)optional.get()).offset(Direction.DOWN)).isAir()) || world.getBlockState(pos.offset(Direction.DOWN)).isAir();
    }

    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
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

        Optional <BlockPos> optional = BlockLocating.findColumnEnd(world, pos, vineBlock2, Direction.DOWN, vineBlock);
        if (optional.isPresent()) {
            BlockState blockState = world.getBlockState((BlockPos)optional.get());
            ((WisteriaVine)blockState.getBlock()).grow(world, random, (BlockPos)optional.get(), blockState);
        }
        if (optional.isEmpty()) {
            world.setBlockState(pos.down(), vineBlock.getDefaultState());
        }
    }
}
