package net.hibiscus.naturespirit.blocks;

import net.minecraft.block.*;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;

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
        else
            return (AbstractPlantBlock) HibiscusBlocks.WHITE_WISTERIA_VINES_PLANT;
    }

    protected boolean chooseStemState(BlockState state) {
        return VineLogic.isValidForWeepingStem(state);
    }
}
