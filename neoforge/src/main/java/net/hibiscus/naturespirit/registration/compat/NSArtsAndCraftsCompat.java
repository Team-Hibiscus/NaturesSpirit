package net.hibiscus.naturespirit.registration.compat;

import com.google.common.base.Supplier;
import net.hibiscus.naturespirit.registration.NSBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;

public class NSArtsAndCraftsCompat {

    public static final DeferredRegister.Blocks ANC_BLOCKS = DeferredRegister.createBlocks(MOD_ID);
    public static final DeferredRegister.Items ANC_ITEMS = DeferredRegister.createItems(MOD_ID);

    public static final DeferredBlock<Block> BLEACHED_CHALK = registerBlock(
            "bleached_chalk",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))
    );
    public static final DeferredBlock<StairBlock> BLEACHED_CHALK_STAIRS = registerBlock(
            "bleached_chalk_stairs",
            () -> new StairBlock(BLEACHED_CHALK.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))
    );
    public static final DeferredBlock<SlabBlock> BLEACHED_CHALK_SLAB = registerBlock(
            "bleached_chalk_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))
    );
    public static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> block1 =  ANC_BLOCKS.register(name, block);
        DeferredItem<BlockItem> item = ANC_ITEMS.register(name, () -> new BlockItem(block1.get(), new Item.Properties()));
        return block1;
    }
}
