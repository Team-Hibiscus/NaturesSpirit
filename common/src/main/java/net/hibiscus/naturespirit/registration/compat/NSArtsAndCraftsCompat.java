package net.hibiscus.naturespirit.registration.compat;

import net.hibiscus.naturespirit.registration.NSBlockHolder;
import net.hibiscus.naturespirit.registration.NSRegistryHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class NSArtsAndCraftsCompat {

    public static final NSBlockHolder<Block> BLEACHED_CHALK = NSRegistryHelper.registerBlock(
            "bleached_chalk",
            Block::new,
            () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)
    );
    public static final NSBlockHolder<StairBlock> BLEACHED_CHALK_STAIRS = NSRegistryHelper.registerBlock(
            "bleached_chalk_stairs",
            props -> new StairBlock(BLEACHED_CHALK.get().defaultBlockState(), props),
            () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)
    );
    public static final NSBlockHolder<SlabBlock> BLEACHED_CHALK_SLAB = NSRegistryHelper.registerBlock(
            "bleached_chalk_slab",
            SlabBlock::new,
            () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)
    );

    public static void bootstrap() {
    }
}
