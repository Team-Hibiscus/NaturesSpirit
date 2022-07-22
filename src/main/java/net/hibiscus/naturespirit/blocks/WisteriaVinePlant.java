package net.hibiscus.naturespirit.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WisteriaVinePlant extends GrowingPlantBodyBlock {
    public static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

    public WisteriaVinePlant(BlockBehaviour.Properties properties) {
        super(properties, Direction.DOWN, SHAPE, false);
    }

    protected GrowingPlantHeadBlock getHeadBlock() {
        if (this.asBlock() == HibiscusBlocks.BLUE_WISTERIA_VINES_PLANT) {
            return (GrowingPlantHeadBlock) HibiscusBlocks.BLUE_WISTERIA_VINES;
        }
        if (this.asBlock() == HibiscusBlocks.PINK_WISTERIA_VINES_PLANT) {
            return (GrowingPlantHeadBlock) HibiscusBlocks.PINK_WISTERIA_VINES;
        }
        if (this.asBlock() == HibiscusBlocks.PURPLE_WISTERIA_VINES_PLANT) {
            return (GrowingPlantHeadBlock) HibiscusBlocks.PURPLE_WISTERIA_VINES;
        }
        else
            return (GrowingPlantHeadBlock) HibiscusBlocks.WHITE_WISTERIA_VINES;
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
