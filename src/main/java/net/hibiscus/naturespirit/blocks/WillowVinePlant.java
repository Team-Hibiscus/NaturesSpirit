package net.hibiscus.naturespirit.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

import static net.hibiscus.naturespirit.blocks.HibiscusBlocks.WILLOW_VINES;

public class WillowVinePlant extends WisteriaVinePlant {
    public static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
    private static final Block block = WILLOW_VINES;


    public WillowVinePlant(Properties properties) {
        super(properties, block);
    }

    protected GrowingPlantHeadBlock getHeadBlock() {
        return (GrowingPlantHeadBlock) block;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader levelReader, BlockPos pos) {
        BlockPos blockPos = pos.relative(this.growthDirection.getOpposite());
        BlockState blockState = levelReader.getBlockState(blockPos);
        if (!this.canAttachTo(blockState)) {
            return false;
        } else {
            return blockState.is(this.getBodyBlock()) || blockState.is(this.getHeadBlock()) || blockState.isFaceSturdy(levelReader, blockPos, this.growthDirection) || blockState.is(BlockTags.LEAVES);
        }
    }
}
