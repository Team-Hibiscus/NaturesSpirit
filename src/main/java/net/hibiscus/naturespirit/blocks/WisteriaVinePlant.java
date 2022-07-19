package net.hibiscus.naturespirit.blocks;

import net.minecraft.block.*;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.WorldView;

public class WisteriaVinePlant extends AbstractPlantBlock {
    public static final VoxelShape SHAPE = Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

    public WisteriaVinePlant(Settings settings) {
        super(settings, Direction.DOWN, SHAPE, false);
    }

    protected AbstractPlantStemBlock getStem() {
        if (this.asBlock() == HibiscusBlocks.BLUE_WISTERIA_VINES_PLANT) {
            return (AbstractPlantStemBlock) HibiscusBlocks.BLUE_WISTERIA_VINES;
        }
        if (this.asBlock() == HibiscusBlocks.PINK_WISTERIA_VINES_PLANT) {
            return (AbstractPlantStemBlock) HibiscusBlocks.PINK_WISTERIA_VINES;
        }
        if (this.asBlock() == HibiscusBlocks.PURPLE_WISTERIA_VINES_PLANT) {
            return (AbstractPlantStemBlock) HibiscusBlocks.PURPLE_WISTERIA_VINES;
        }
        else
            return (AbstractPlantStemBlock) HibiscusBlocks.WHITE_WISTERIA_VINES;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.offset(this.growthDirection.getOpposite());
        BlockState blockState = world.getBlockState(blockPos);
        if (!this.canAttachTo(blockState)) {
            return false;
        } else {
            return blockState.isOf(this.getStem()) || blockState.isOf(this.getPlant()) || blockState.isSideSolidFullSquare(world, blockPos, this.growthDirection) || blockState.isIn(BlockTags.LEAVES);
        }
    }
}
