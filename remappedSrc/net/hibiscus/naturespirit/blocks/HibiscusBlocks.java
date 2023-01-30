package net.hibiscus.naturespirit.blocks;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fabricmc.fabric.impl.itemgroup.FabricItemGroup;
import net.fabricmc.fabric.mixin.itemgroup.ItemGroupsAccessor;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.items.HibiscusItemGroups;
import net.hibiscus.naturespirit.mixin.WoodTypeAccessor;
import net.hibiscus.naturespirit.world.feature.tree.*;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ButtonBlock;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SignBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.TallFlowerBlock;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.item.SignItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

public class HibiscusBlocks {

    public static final Block LAVENDER = registerPlantBlock("lavender", new LargeTallFlowerBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XZ)), HibiscusItemGroups.NatureSpiritItemGroupResourceLocation, Blocks.PEONY, 0.5f);
    public static final Block CARNATION = registerPlantBlock("carnation", new TallFlowerBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XZ)), HibiscusItemGroups.NatureSpiritItemGroupResourceLocation, LAVENDER, 0.4f);
    public static final Block GARDENIA = registerPlantBlock("gardenia", new TallFlowerBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XZ)), HibiscusItemGroups.NatureSpiritItemGroupResourceLocation, CARNATION, 0.4f);
    public static final Block SNAPDRAGON = registerPlantBlock("snapdragon", new TallFlowerBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XZ)), HibiscusItemGroups.NatureSpiritItemGroupResourceLocation, GARDENIA, 0.4f);
    public static final Block MARIGOLD = registerPlantBlock("marigold", new TallFlowerBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XZ)), HibiscusItemGroups.NatureSpiritItemGroupResourceLocation, SNAPDRAGON, 0.4f);
    public static final Block CATTAIL = registerPlantBlock("cattail", new Cattails(FabricBlockSettings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XZ)), HibiscusItemGroups.NatureSpiritItemGroupResourceLocation, Blocks.LARGE_FERN, 0.4f);
    public static final Block BLUEBELL = registerPlantBlock("bluebell", new LargeFlowerBlock(StatusEffects.HASTE, 7, FabricBlockSettings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XZ)), HibiscusItemGroups.NatureSpiritItemGroupResourceLocation, Blocks.LILY_OF_THE_VALLEY, 0.4f);
    public static final Block TIGER_LILY = registerPlantBlock("tiger_lily", new LargeFlowerBlock(StatusEffects.HASTE, 7, FabricBlockSettings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XZ)), HibiscusItemGroups.NatureSpiritItemGroupResourceLocation, BLUEBELL, 0.4f);
    public static final Block ANEMONE = registerPlantBlock("anemone", new MidFlowerBlock(StatusEffects.RESISTANCE, 4, FabricBlockSettings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XZ)), HibiscusItemGroups.NatureSpiritItemGroupResourceLocation, TIGER_LILY, 0.4f);
    public static final Block HIBISCUS = registerPlantBlock("hibiscus", new FlowerBlock(StatusEffects.LUCK, 7, FabricBlockSettings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XZ)), HibiscusItemGroups.NatureSpiritItemGroupResourceLocation, ANEMONE, 0.3f);

    public static final Block POTTED_ANEMONE = registerBlockWithoutItem("potted_anemone", new FlowerPotBlock(ANEMONE, FabricBlockSettings.of(Material.DECORATION).breakInstantly().nonOpaque()));
    public static final Block POTTED_HIBISCUS = registerBlockWithoutItem("potted_hibiscus", new FlowerPotBlock(HIBISCUS, FabricBlockSettings.of(Material.DECORATION).breakInstantly().nonOpaque()));


    public static final Block[] REDWOOD = registerWoodBlocks("redwood", MapColor.RED, MapColor.TERRACOTTA_BROWN, Blocks.MANGROVE_BUTTON, Blocks.MANGROVE_LOG, Items.MANGROVE_SIGN);
    public static final Block REDWOOD_LEAVES = registerLeafBlock("redwood_leaves", MapColor.LIME, Blocks.MANGROVE_LEAVES);
    public static final Block[] REDWOOD_SAPLING = registerSapling("redwood", new RedwoodSaplingGenerator(), Blocks.MANGROVE_PROPAGULE);

    public static final Block[] SAKURA = registerWoodBlocks("sakura", MapColor.DIRT_BROWN, MapColor.DEEPSLATE_GRAY, REDWOOD[12], REDWOOD[2], REDWOOD[13].asItem());
    public static final Block PINK_SAKURA_LEAVES = registerLeafBlock("pink_sakura_leaves", MapColor.PINK, REDWOOD_LEAVES);
    public static final Block[] PINK_SAKURA_SAPLING = registerSapling("pink_sakura", new PinkSakuraSaplingGenerator(), REDWOOD_SAPLING[0]);
    public static final Block WHITE_SAKURA_LEAVES = registerLeafBlock("white_sakura_leaves", MapColor.TERRACOTTA_WHITE, PINK_SAKURA_LEAVES);
    public static final Block[] WHITE_SAKURA_SAPLING = registerSapling("white_sakura", new WhiteSakuraSaplingGenerator(), PINK_SAKURA_SAPLING[0]);
    public static final Block BLOOMING_SAKURA_DOOR = registerSecondaryDoorBlock("blooming_sakura_door", new DoorBlock(FabricBlockSettings.copy(SAKURA[4]).nonOpaque(), SoundEvents.BLOCK_WOODEN_DOOR_CLOSE, SoundEvents.BLOCK_WOODEN_DOOR_OPEN), HibiscusItemGroups.NatureSpiritItemGroupResourceLocation, SAKURA[8]);
    public static final Block BLOOMING_SAKURA_TRAPDOOR = registerSecondaryDoorBlock("blooming_sakura_trapdoor", new TrapdoorBlock(FabricBlockSettings.of(Material.WOOD).strength(3.0f).sounds(BlockSoundGroup.WOOD).nonOpaque(), SoundEvents.BLOCK_WOODEN_TRAPDOOR_CLOSE, SoundEvents.BLOCK_WOODEN_TRAPDOOR_OPEN), HibiscusItemGroups.NatureSpiritItemGroupResourceLocation, BLOOMING_SAKURA_DOOR);
    public static final Block FRAMED_SAKURA_DOOR = registerSecondaryDoorBlock("framed_sakura_door", new DoorBlock(FabricBlockSettings.copy(SAKURA[4]).nonOpaque(), SoundEvents.BLOCK_WOODEN_DOOR_CLOSE, SoundEvents.BLOCK_WOODEN_DOOR_OPEN), HibiscusItemGroups.NatureSpiritItemGroupResourceLocation, BLOOMING_SAKURA_TRAPDOOR);
    public static final Block FRAMED_SAKURA_TRAPDOOR = registerSecondaryDoorBlock("framed_sakura_trapdoor", new TrapdoorBlock(FabricBlockSettings.of(Material.WOOD).strength(3.0f).sounds(BlockSoundGroup.WOOD).nonOpaque(), SoundEvents.BLOCK_WOODEN_TRAPDOOR_CLOSE, SoundEvents.BLOCK_WOODEN_TRAPDOOR_OPEN), HibiscusItemGroups.NatureSpiritItemGroupResourceLocation, FRAMED_SAKURA_DOOR);


    public static final Block[] WISTERIA = registerWoodBlocks("wisteria", MapColor.TERRACOTTA_WHITE, MapColor.GRAY, SAKURA[12], SAKURA[2], SAKURA[13].asItem());

    public static final Block WHITE_WISTERIA_LEAVES = registerWisteriaLeafBlock("white_wisteria_leaves", MapColor.TERRACOTTA_WHITE, WHITE_SAKURA_LEAVES);
    public static final Block WHITE_WISTERIA_VINES = registerPlantBlock("white_wisteria_vines", new WisteriaVine(FabricBlockSettings.of(Material.PLANT, MapColor.TERRACOTTA_WHITE).ticksRandomly().noCollision().nonOpaque().breakInstantly().sounds(BlockSoundGroup.WEEPING_VINES)), HibiscusItemGroups.NatureSpiritItemGroupResourceLocation, Blocks.VINE, 0.5f);
    public static final Block WHITE_WISTERIA_VINES_PLANT = registerBlockWithoutItem("white_wisteria_vines_plant", new WisteriaVinePlant(FabricBlockSettings.of(Material.PLANT, MapColor.TERRACOTTA_WHITE).noCollision().nonOpaque().breakInstantly().sounds(BlockSoundGroup.WEEPING_VINES).dropsLike(WHITE_WISTERIA_VINES), WHITE_WISTERIA_VINES));
    public static final Block[] WHITE_WISTERIA_SAPLING = registerSapling("white_wisteria", new WhiteWisteriaSaplingGenerator(), WHITE_SAKURA_SAPLING[0]);

    public static final Block BLUE_WISTERIA_LEAVES = registerWisteriaLeafBlock("blue_wisteria_leaves", MapColor.CYAN, WHITE_WISTERIA_LEAVES);
    public static final Block BLUE_WISTERIA_VINES = registerPlantBlock("blue_wisteria_vines", new WisteriaVine(FabricBlockSettings.of(Material.PLANT, MapColor.CYAN).ticksRandomly().noCollision().breakInstantly().nonOpaque().sounds(BlockSoundGroup.WEEPING_VINES)), HibiscusItemGroups.NatureSpiritItemGroupResourceLocation, WHITE_WISTERIA_VINES, 0.5f);
    public static final Block BLUE_WISTERIA_VINES_PLANT = registerBlockWithoutItem("blue_wisteria_vines_plant", new WisteriaVinePlant(FabricBlockSettings.of(Material.PLANT, MapColor.CYAN).noCollision().nonOpaque().breakInstantly().sounds(BlockSoundGroup.WEEPING_VINES).dropsLike(BLUE_WISTERIA_VINES), BLUE_WISTERIA_VINES));
    public static final Block[] BLUE_WISTERIA_SAPLING = registerSapling("blue_wisteria", new BlueWisteriaSaplingGenerator(), WHITE_WISTERIA_SAPLING[0]);

    public static final Block PINK_WISTERIA_LEAVES = registerWisteriaLeafBlock("pink_wisteria_leaves", MapColor.PINK, BLUE_WISTERIA_LEAVES);
    public static final Block PINK_WISTERIA_VINES = registerPlantBlock("pink_wisteria_vines", new WisteriaVine(FabricBlockSettings.of(Material.PLANT, MapColor.PINK).ticksRandomly().noCollision().breakInstantly().nonOpaque().sounds(BlockSoundGroup.WEEPING_VINES)), HibiscusItemGroups.NatureSpiritItemGroupResourceLocation, BLUE_WISTERIA_VINES, 0.5f);
    public static final Block PINK_WISTERIA_VINES_PLANT = registerBlockWithoutItem("pink_wisteria_vines_plant", new WisteriaVinePlant(FabricBlockSettings.of(Material.PLANT, MapColor.PINK).noCollision().nonOpaque().breakInstantly().sounds(BlockSoundGroup.WEEPING_VINES).dropsLike(PINK_WISTERIA_VINES), PINK_WISTERIA_VINES));
    public static final Block[] PINK_WISTERIA_SAPLING = registerSapling("pink_wisteria", new PinkWisteriaSaplingGenerator(), BLUE_WISTERIA_SAPLING[0]);

    public static final Block PURPLE_WISTERIA_LEAVES = registerWisteriaLeafBlock("purple_wisteria_leaves", MapColor.PURPLE, PINK_WISTERIA_LEAVES);
    public static final Block PURPLE_WISTERIA_VINES = registerPlantBlock("purple_wisteria_vines", new WisteriaVine(FabricBlockSettings.of(Material.PLANT, MapColor.PURPLE).ticksRandomly().noCollision().breakInstantly().nonOpaque().sounds(BlockSoundGroup.WEEPING_VINES)), HibiscusItemGroups.NatureSpiritItemGroupResourceLocation, PINK_WISTERIA_VINES, 0.5f);
    public static final Block PURPLE_WISTERIA_VINES_PLANT = registerBlockWithoutItem("purple_wisteria_vines_plant", new WisteriaVinePlant(FabricBlockSettings.of(Material.PLANT, MapColor.PURPLE).noCollision().nonOpaque().breakInstantly().sounds(BlockSoundGroup.WEEPING_VINES).dropsLike(PURPLE_WISTERIA_VINES), PURPLE_WISTERIA_VINES));
    public static final Block[] PURPLE_WISTERIA_SAPLING = registerSapling("purple_wisteria", new PurpleWisteriaSaplingGenerator(), PINK_WISTERIA_SAPLING[0]);


    public static final Block[] FIR = registerWoodBlocks("fir", MapColor.DIRT_BROWN, MapColor.GRAY, WISTERIA[12], WISTERIA[2], WISTERIA[13].asItem());
    public static final Block FIR_LEAVES = registerLeafBlock("fir_leaves", MapColor.LIME, PURPLE_WISTERIA_LEAVES);
    public static final Block[] FIR_SAPLING = registerSapling("fir", new RedwoodSaplingGenerator(), PURPLE_WISTERIA_SAPLING[0]);

    public static final Block[] WILLOW = registerWoodBlocks("willow", MapColor.TERRACOTTA_BROWN, MapColor.TERRACOTTA_BLACK, FIR[12], FIR[2], FIR[13].asItem());
    public static final Block WILLOW_LEAVES = registerWillowLeafBlock("willow_leaves", MapColor.GREEN, FIR_LEAVES);
    public static final Block WILLOW_VINES = registerPlantBlock("willow_vines", new WillowVine(FabricBlockSettings.of(Material.PLANT, MapColor.TERRACOTTA_WHITE).ticksRandomly().noCollision().nonOpaque().breakInstantly().sounds(BlockSoundGroup.WEEPING_VINES)), HibiscusItemGroups.NatureSpiritItemGroupResourceLocation, PURPLE_WISTERIA_VINES, 0.5f);
    public static final Block WILLOW_VINES_PLANT = registerBlockWithoutItem("willow_vines_plant", new WillowVinePlant(FabricBlockSettings.of(Material.PLANT, MapColor.TERRACOTTA_WHITE).noCollision().nonOpaque().breakInstantly().sounds(BlockSoundGroup.WEEPING_VINES).dropsLike(WILLOW_VINES)));
    public static final Block[] WILLOW_SAPLING = registerSapling("willow", new WillowSaplingGenerator(), FIR_SAPLING[0]);


    public static Block[] registerWoodBlocks(String name, MapColor topMaterialColor, MapColor sideMaterialColor, Block buttonPlacement, Block logPlacement, Item signPlacement) {
        SignType woodType = WoodTypeAccessor.registerNew(WoodTypeAccessor.newWoodType(name));
        Block[] ARRAY = new Block[15];
        ARRAY[0] = registerBlock(name + "_wood", new PillarBlock(FabricBlockSettings.of(Material.WOOD, sideMaterialColor).strength(2.0F).sounds(BlockSoundGroup.WOOD)), HibiscusItemGroups.NatureSpiritItemGroupResourceLocation);
        ARRAY[1] = registerBlock("stripped_" + name + "_wood", new PillarBlock(FabricBlockSettings.of(Material.WOOD, topMaterialColor).strength(2.0F).sounds(BlockSoundGroup.WOOD)), HibiscusItemGroups.NatureSpiritItemGroupResourceLocation);
        ARRAY[2] = registerBlock(name + "_log", new PillarBlock(FabricBlockSettings.of(Material.WOOD, (state) -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMaterialColor : sideMaterialColor).strength(2.0F).sounds(BlockSoundGroup.WOOD)), HibiscusItemGroups.NatureSpiritItemGroupResourceLocation);
        ARRAY[3] = registerBlock("stripped_" + name + "_log", new PillarBlock(FabricBlockSettings.of(Material.WOOD, topMaterialColor).strength(2.0F).sounds(BlockSoundGroup.WOOD)), HibiscusItemGroups.NatureSpiritItemGroupResourceLocation);
        ARRAY[4] = registerBlock(name + "_planks", new Block(FabricBlockSettings.of(Material.WOOD, topMaterialColor).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)), HibiscusItemGroups.NatureSpiritItemGroupResourceLocation);
        ARRAY[5] = registerBlock(name + "_stairs", new StairsBlock(ARRAY[4].getDefaultState(), FabricBlockSettings.copy(ARRAY[4])), HibiscusItemGroups.NatureSpiritItemGroupResourceLocation);
        ARRAY[6] = registerBlock(name + "_slab", new SlabBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)), HibiscusItemGroups.NatureSpiritItemGroupResourceLocation);
        ARRAY[7] = registerBlock(name + "_door", new DoorBlock(FabricBlockSettings.copy(ARRAY[4]).nonOpaque(), SoundEvents.BLOCK_WOODEN_DOOR_CLOSE, SoundEvents.BLOCK_WOODEN_DOOR_OPEN), HibiscusItemGroups.NatureSpiritItemGroupResourceLocation);
        ARRAY[8] = registerBlock(name + "_trapdoor", new TrapdoorBlock(FabricBlockSettings.of(Material.WOOD).strength(3.0f).sounds(BlockSoundGroup.WOOD).nonOpaque(), SoundEvents.BLOCK_WOODEN_TRAPDOOR_CLOSE, SoundEvents.BLOCK_WOODEN_TRAPDOOR_OPEN), HibiscusItemGroups.NatureSpiritItemGroupResourceLocation);
        ARRAY[9] = registerBlock(name + "_fence", new FenceBlock(FabricBlockSettings.copy(ARRAY[4]).nonOpaque()), HibiscusItemGroups.NatureSpiritItemGroupResourceLocation);
        ARRAY[10] = registerBlock(name + "_fence_gate", new FenceGateBlock(FabricBlockSettings.copy(ARRAY[4]).nonOpaque(), SoundEvents.BLOCK_FENCE_GATE_CLOSE, SoundEvents.BLOCK_FENCE_GATE_OPEN), HibiscusItemGroups.NatureSpiritItemGroupResourceLocation);
        ARRAY[11] = registerBlock(name + "_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.of(Material.WOOD, ARRAY[4].getDefaultMapColor()).noCollision().strength(0.5f).sounds(BlockSoundGroup.WOOD), SoundEvents.BLOCK_WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundEvents.BLOCK_WOODEN_PRESSURE_PLATE_CLICK_ON), HibiscusItemGroups.NatureSpiritItemGroupResourceLocation);
        ARRAY[12] = registerBlock(name + "_button", new ButtonBlock(FabricBlockSettings.of(Material.WOOD, topMaterialColor).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD), 30, true, SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_OFF, SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_ON), HibiscusItemGroups.NatureSpiritItemGroupResourceLocation);
        ARRAY[13] = registerBlockWithoutItem(name + "_sign", new SignBlock(FabricBlockSettings.copy(Blocks.OAK_SIGN), woodType));
        ARRAY[14] = registerBlockWithoutItem(name + "_wall_sign", new WallSignBlock(FabricBlockSettings.copy(Blocks.OAK_WALL_SIGN).dropsLike(ARRAY[13]), woodType));
        registerItemWithoutBlock(name + "_sign",
                new SignItem(new FabricItemSettings().maxCount(16),
                        ARRAY[13], ARRAY[14]), HibiscusItemGroups.NatureSpiritItemGroupResourceLocation);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> entries.addAfter(buttonPlacement, ARRAY[2], ARRAY[0], ARRAY[3], ARRAY[1], ARRAY[4], ARRAY[5], ARRAY[6], ARRAY[9], ARRAY[10], ARRAY[7], ARRAY[8], ARRAY[11], ARRAY[12]));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(logPlacement, ARRAY[2]));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> entries.addAfter(signPlacement, ARRAY[13].asItem()));
        BlockRenderLayerMap.INSTANCE.putBlock(ARRAY[7], RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ARRAY[8], RenderLayer.getCutout());
        StrippableBlockRegistry.register(ARRAY[0], ARRAY[1]);
        StrippableBlockRegistry.register(ARRAY[2], ARRAY[3]);
        FlammableBlockRegistry.getDefaultInstance().add(ARRAY[0], 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ARRAY[1], 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ARRAY[2], 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ARRAY[3], 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ARRAY[4], 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ARRAY[5], 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ARRAY[6], 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ARRAY[9], 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ARRAY[10], 5, 20);
        FuelRegistry.INSTANCE.add(ARRAY[9], 300);
        FuelRegistry.INSTANCE.add(ARRAY[10], 300);


        return ARRAY;
    }

    private static boolean never(BlockState state, BlockView world, BlockPos pos) {
        return false;
    }

    private static Boolean canSpawnUponLeaves(BlockState state, BlockView world, BlockPos pos, EntityType <?> type) {
        return type == EntityType.OCELOT || type == EntityType.PARROT;
    }

    public static Block registerLeafBlock(String name, MapColor color, Block blockBefore) {
        Block LEAVES = registerBlock(name, new LeavesBlock(FabricBlockSettings.of(Material.LEAVES).strength(0.2F).ticksRandomly().nonOpaque().sounds(BlockSoundGroup.AZALEA_LEAVES).mapColor(color).allowsSpawning(HibiscusBlocks::canSpawnUponLeaves).suffocates(HibiscusBlocks::never).blockVision(HibiscusBlocks::never)), HibiscusItemGroups.NatureSpiritItemGroupResourceLocation);
        BlockRenderLayerMap.INSTANCE.putBlock(LEAVES, RenderLayer.getCutout());
        FlammableBlockRegistry.getDefaultInstance().add(LEAVES, 5, 20);
        CompostingChanceRegistry.INSTANCE.add(LEAVES, 0.3F);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(blockBefore, LEAVES.asItem()));
        return LEAVES;
    }

    public static Block registerWillowLeafBlock(String name, MapColor color, Block blockBefore) {
        Block LEAVES = registerBlock(name, new WillowLeaves(FabricBlockSettings.of(Material.LEAVES).strength(0.2F).ticksRandomly().nonOpaque().sounds(BlockSoundGroup.AZALEA_LEAVES).mapColor(color).allowsSpawning(HibiscusBlocks::canSpawnUponLeaves).suffocates(HibiscusBlocks::never).blockVision(HibiscusBlocks::never)), HibiscusItemGroups.NatureSpiritItemGroupResourceLocation);
        BlockRenderLayerMap.INSTANCE.putBlock(LEAVES, RenderLayer.getCutout());
        FlammableBlockRegistry.getDefaultInstance().add(LEAVES, 5, 20);
        CompostingChanceRegistry.INSTANCE.add(LEAVES, 0.3F);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(blockBefore, LEAVES.asItem()));
        return LEAVES;
    }

    public static Block registerWisteriaLeafBlock(String name, MapColor color, Block blockBefore) {
        Block LEAVES = registerBlock(name, new WisteriaLeaves(FabricBlockSettings.of(Material.LEAVES).strength(0.2F).ticksRandomly().nonOpaque().sounds(BlockSoundGroup.AZALEA_LEAVES).mapColor(color).allowsSpawning(HibiscusBlocks::canSpawnUponLeaves).suffocates(HibiscusBlocks::never).blockVision(HibiscusBlocks::never)), HibiscusItemGroups.NatureSpiritItemGroupResourceLocation);
        BlockRenderLayerMap.INSTANCE.putBlock(LEAVES, RenderLayer.getCutout());
        FlammableBlockRegistry.getDefaultInstance().add(LEAVES, 5, 20);
        CompostingChanceRegistry.INSTANCE.add(LEAVES, 0.3F);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(blockBefore, LEAVES.asItem()));
        return LEAVES;
    }

    public static Block[] registerSapling(String name, SaplingGenerator sapling_generator, Block blockBefore) {
        Block[] Plant = new Block[2];
        Plant[0] = registerBlock(name + "_sapling", new SaplingBlock(sapling_generator, FabricBlockSettings.copy(Blocks.SPRUCE_SAPLING)), HibiscusItemGroups.NatureSpiritItemGroupResourceLocation);
        BlockRenderLayerMap.INSTANCE.putBlock(Plant[0], RenderLayer.getCutout());
        Plant[1] = registerBlockWithoutItem("potted_" + name + "_sapling", new FlowerPotBlock(Plant[0], FabricBlockSettings.of(Material.DECORATION).breakInstantly().nonOpaque()));
        BlockRenderLayerMap.INSTANCE.putBlock(Plant[1], RenderLayer.getCutout());
        CompostingChanceRegistry.INSTANCE.add(Plant[0], 0.3F);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(blockBefore, Plant[0].asItem()));
        return Plant;
    }

    public static Block registerBlock(String name, Block block, Identifier tab) {
        registerBlockItem(name, block, tab);
        return Registry.register(Registries.BLOCK, new Identifier(NatureSpirit.MOD_ID, name), block);
    }

    public static Block registerSecondaryDoorBlock(String name, Block block, Identifier tab, Block blockBefore) {
        Block block1 = Registry.register(Registries.BLOCK, new Identifier(NatureSpirit.MOD_ID, name), block);
        BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());
        registerBlockItem(name, block, tab, blockBefore, ItemGroups.BUILDING_BLOCKS);
        return block1;
    }

    public static Block registerBlock(String name, Block block, Identifier tab, Block blockBefore, ItemGroup secondaryTab) {
        Block block1 = registerBlock(name, block, tab);
        ItemGroupEvents.modifyEntriesEvent(secondaryTab).register(entries -> entries.addAfter(blockBefore, block1.asItem()));
        return block1;
    }

    public static Block registerPlantBlock(String name, Block block, Identifier tab, Block blockBefore, float compost) {
        Block Flower = registerBlock(name, block, tab, blockBefore, ItemGroups.NATURAL);
        BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());
        CompostingChanceRegistry.INSTANCE.add(block, compost);
        return Flower;
    }

    public static Block registerBlockWithoutItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(NatureSpirit.MOD_ID, name), block);
    }

    public static Item registerBlockItem(String name, Block block, Identifier tab) {
        Item item = Registry.register(Registries.ITEM, new Identifier(NatureSpirit.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
        ItemGroupEvents.modifyEntriesEvent(tab).register(entries -> entries.add(item.asItem()));
        return item;
    }

    public static Item registerBlockItem(String name, Block block, Identifier tab, Block blockBefore, ItemGroup secondaryTab) {
        Item item = Registry.register(Registries.ITEM, new Identifier(NatureSpirit.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
        ItemGroupEvents.modifyEntriesEvent(secondaryTab).register(entries -> entries.addAfter(blockBefore, item.asItem()));
        ItemGroupEvents.modifyEntriesEvent(tab).register(entries -> entries.addAfter(blockBefore, item.asItem()));
        return item;
    }

    public static Item registerItemWithoutBlock(String name, Item item, Identifier tab) {
        ItemGroupEvents.modifyEntriesEvent(tab).register(entries -> entries.add(item.asItem()));
        return Registry.register(Registries.ITEM, new Identifier(NatureSpirit.MOD_ID, name), item);
    }

    public static void registerHibiscusBlocks() {
        NatureSpirit.LOGGER.debug("Registering ModBlocks for " + NatureSpirit.MOD_ID);
    }
}
