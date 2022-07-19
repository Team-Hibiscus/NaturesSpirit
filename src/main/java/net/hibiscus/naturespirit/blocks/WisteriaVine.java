package net.hibiscus.naturespirit.blocks;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.*;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.WorldView;

public class WisteriaVine extends AbstractPlantStemBlock {
    protected static final VoxelShape SHAPE = Block.createCuboidShape(4.0D, 9.0D, 4.0D, 12.0D, 16.0D, 12.0D);

    public WisteriaVine(Settings settings) {
        super(settings, Direction.DOWN, SHAPE, false, 0.1D);
    }

    protected int getGrowthLength(Random random) {
        return VineLogic.getGrowthLength(random);
    }

    protected AbstractPlantBlock getPlant() {
        if (this.asBlock() == HibiscusBlocks.BLUE_WISTERIA_VINES) {
            return (AbstractPlantBlock) HibiscusBlocks.BLUE_WISTERIA_VINES_PLANT;
        }
        if (this.asBlock() == HibiscusBlocks.PINK_WISTERIA_VINES) {
            return (AbstractPlantBlock) HibiscusBlocks.PINK_WISTERIA_VINES_PLANT;
        }
        if (this.asBlock() == HibiscusBlocks.PURPLE_WISTERIA_VINES) {
            return (AbstractPlantBlock) HibiscusBlocks.PURPLE_WISTERIA_VINES_PLANT;
        }
        else
            return (AbstractPlantBlock) HibiscusBlocks.WHITE_WISTERIA_VINES_PLANT;
    }

    protected boolean chooseStemState(BlockState state) {
        return VineLogic.isValidForWeepingStem(state);
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
