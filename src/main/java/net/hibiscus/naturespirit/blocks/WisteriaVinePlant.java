package net.hibiscus.naturespirit.blocks;

import net.minecraft.block.AbstractPlantBlock;
import net.minecraft.block.AbstractPlantStemBlock;
import net.minecraft.block.Block;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;

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
        else
            return (AbstractPlantStemBlock) HibiscusBlocks.WHITE_WISTERIA_VINES;
    }
}
