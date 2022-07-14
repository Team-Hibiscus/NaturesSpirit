package net.hibiscus.naturespirit.blocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.hibiscus.naturespirit.NatureSpirit;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;

public class HibiscusBlocks {

    public static final Block REDWOOD = registerWoodBlocks("redwood", MapColor.DARK_CRIMSON, MapColor.TERRACOTTA_BROWN);

    public static Block registerWoodBlocks(String name, MapColor topMapColor, MapColor sideMapColor) {
        registerBlock(name + "_wood", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD, sideMapColor).strength(2.0F).sounds(BlockSoundGroup.WOOD)), ItemGroup.BUILDING_BLOCKS);
        registerBlock("stripped_" + name + "_wood", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD, topMapColor).strength(2.0F).sounds(BlockSoundGroup.WOOD)), ItemGroup.BUILDING_BLOCKS);
        registerBlock(name + "_log", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD, (state) -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor).strength(2.0F).sounds(BlockSoundGroup.WOOD)), ItemGroup.BUILDING_BLOCKS);
        registerBlock("stripped_" + name + "_log", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD, topMapColor).strength(2.0F).sounds(BlockSoundGroup.WOOD)), ItemGroup.BUILDING_BLOCKS);
        final Block PLANKS = registerBlock(name + "_planks", new Block(FabricBlockSettings.of(Material.WOOD, topMapColor).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)), ItemGroup.BUILDING_BLOCKS);
        registerBlock(name + "_stairs", new StairsBlock(PLANKS.getDefaultState(), FabricBlockSettings.copy(PLANKS)), ItemGroup.BUILDING_BLOCKS);
        registerBlock(name + "_slab", new SlabBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)), ItemGroup.BUILDING_BLOCKS);
        registerBlock(name + "_door", new DoorBlock(FabricBlockSettings.copy(PLANKS).nonOpaque()), ItemGroup.BUILDING_BLOCKS);
        registerBlock(name + "_trapdoor", new TrapdoorBlock(FabricBlockSettings.of(Material.WOOD).strength(3.0f).sounds(BlockSoundGroup.WOOD).nonOpaque()), ItemGroup.BUILDING_BLOCKS);
        registerBlock(name + "_fence", new FenceBlock(FabricBlockSettings.copy(PLANKS).nonOpaque()), ItemGroup.BUILDING_BLOCKS);
        registerBlock(name + "_fence_gate", new FenceGateBlock(FabricBlockSettings.copy(PLANKS).nonOpaque()), ItemGroup.BUILDING_BLOCKS);
        registerBlock(name + "_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.of(Material.WOOD, PLANKS.getDefaultMapColor()).noCollision().strength(0.5f).sounds(BlockSoundGroup.WOOD)), ItemGroup.BUILDING_BLOCKS);
        registerBlock(name + "_button", new Block(FabricBlockSettings.of(Material.WOOD, topMapColor).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)), ItemGroup.BUILDING_BLOCKS);
        return PLANKS;
        
    }


    public static Block registerBlock(String name, Block block, ItemGroup tab) {
        registerBlockItem(name, block, tab);
        return Registry.register(Registry.BLOCK, new Identifier(NatureSpirit.MOD_ID, name), block);
    }

    public static Item registerBlockItem(String name, Block block, ItemGroup tab) {
        return Registry.register(Registry.ITEM, new Identifier(NatureSpirit.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(tab)));
    }

    public static void registerHibiscusBlocks() {
        NatureSpirit.LOGGER.debug("Registering ModBlocks for " + NatureSpirit.MOD_ID);
    }
}
