package net.hibiscus.naturespirit.blocks;

import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class WisteriaLeaves extends LeavesBlock implements Fertilizable {
    public WisteriaLeaves(Settings settings) {
        super(settings);
    }

    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return world.getBlockState(pos.down()).isAir();
    }

    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {

        if (this.asBlock() == HibiscusBlocks.BLUE_WISTERIA_LEAVES) {
            world.setBlockState(pos.down(), HibiscusBlocks.BLUE_WISTERIA_VINES.getDefaultState());
        } else
        if (this.asBlock() == HibiscusBlocks.PINK_WISTERIA_LEAVES) {
            world.setBlockState(pos.down(), HibiscusBlocks.PINK_WISTERIA_VINES.getDefaultState());
        } else
        if (this.asBlock() == HibiscusBlocks.PURPLE_WISTERIA_LEAVES) {
            world.setBlockState(pos.down(), HibiscusBlocks.PURPLE_WISTERIA_VINES.getDefaultState());
        } else
        if (this.asBlock() == HibiscusBlocks.WHITE_WISTERIA_LEAVES) {
            world.setBlockState(pos.down(), HibiscusBlocks.WHITE_WISTERIA_VINES.getDefaultState());
        }
    }
}
