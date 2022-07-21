package net.hibiscus.naturespirit.blocks;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.items.HibiscusItemGroups;
import net.hibiscus.naturespirit.mixin.SignTypeAccessor;
import net.hibiscus.naturespirit.world.feature.tree.*;
import net.minecraft.block.*;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SignItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;

public class HibiscusBlocks {

    public static final Block LAVENDER = registerBlock("lavender", new LargeTallFlowerBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XZ)), HibiscusItemGroups.NatureSpiritItemGroup);
    public static final Block CARNATION = registerBlock("carnation", new TallFlowerBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XZ)), HibiscusItemGroups.NatureSpiritItemGroup);
    public static final Block GARDENIA = registerBlock("gardenia", new TallFlowerBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XZ)), HibiscusItemGroups.NatureSpiritItemGroup);
    public static final Block BLUEBELL = registerBlock("bluebell", new LargeFlowerBlock(StatusEffects.HASTE, 7, FabricBlockSettings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XZ)), HibiscusItemGroups.NatureSpiritItemGroup);
    public static final Block ANEMONE = registerBlock("anemone", new MidFlowerBlock(StatusEffects.RESISTANCE, 4, FabricBlockSettings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XZ)), HibiscusItemGroups.NatureSpiritItemGroup);
    public static final Block HIBISCUS = registerBlock("hibiscus", new FlowerBlock(StatusEffects.LUCK, 7, FabricBlockSettings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XZ)), HibiscusItemGroups.NatureSpiritItemGroup);

    public static final Block POTTED_ANEMONE = registerBlockWithoutItem("potted_anemone", new FlowerPotBlock(ANEMONE, FabricBlockSettings.of(Material.DECORATION).breakInstantly().nonOpaque()));
    public static final Block POTTED_HIBISCUS = registerBlockWithoutItem("potted_hibiscus", new FlowerPotBlock(HIBISCUS, FabricBlockSettings.of(Material.DECORATION).breakInstantly().nonOpaque()));


    public static final Block[] REDWOOD = registerWoodBlocks("redwood", MapColor.DARK_CRIMSON, MapColor.TERRACOTTA_BROWN);
    public static final Block REDWOOD_LEAVES = registerLeafBlock("redwood_leaves", MapColor.PALE_GREEN);
    public static final Block REDWOOD_SAPLING = registerSapling("redwood", new RedwoodSaplingGenerator());

    public static final Block[] SAKURA = registerWoodBlocks("sakura", MapColor.DIRT_BROWN, MapColor.DEEPSLATE_GRAY);
    public static final Block PINK_SAKURA_LEAVES = registerLeafBlock("pink_sakura_leaves", MapColor.PINK);
    public static final Block WHITE_SAKURA_LEAVES = registerLeafBlock("white_sakura_leaves", MapColor.OFF_WHITE);
    public static final Block BLOOMING_SAKURA_DOOR = registerBlock( "blooming_sakura_door", new DoorBlock(FabricBlockSettings.copy(SAKURA[4]).nonOpaque()),  HibiscusItemGroups.NatureSpiritItemGroup);
    public static final Block BLOOMING_SAKURA_TRAPDOOR  = registerBlock("blooming_sakura_trapdoor", new TrapdoorBlock(FabricBlockSettings.of(Material.WOOD).strength(3.0f).sounds(BlockSoundGroup.WOOD).nonOpaque()),  HibiscusItemGroups.NatureSpiritItemGroup);
    public static final Block FRAMED_SAKURA_DOOR = registerBlock( "framed_sakura_door", new DoorBlock(FabricBlockSettings.copy(SAKURA[4]).nonOpaque()),  HibiscusItemGroups.NatureSpiritItemGroup);
    public static final Block FRAMED_SAKURA_TRAPDOOR  = registerBlock("framed_sakura_trapdoor", new TrapdoorBlock(FabricBlockSettings.of(Material.WOOD).strength(3.0f).sounds(BlockSoundGroup.WOOD).nonOpaque()),  HibiscusItemGroups.NatureSpiritItemGroup);


    public static final Block[] WISTERIA = registerWoodBlocks("wisteria", MapColor.OFF_WHITE, MapColor.GRAY);

    public static final Block WHITE_WISTERIA_LEAVES = registerWisteriaLeafBlock("white_wisteria_leaves", MapColor.OFF_WHITE);
    public static final Block WHITE_WISTERIA_VINES = registerBlock("white_wisteria_vines", new WisteriaVine(FabricBlockSettings.of(Material.PLANT, MapColor.OFF_WHITE).ticksRandomly().noCollision().nonOpaque().breakInstantly().sounds(BlockSoundGroup.WEEPING_VINES)), HibiscusItemGroups.NatureSpiritItemGroup);
    public static final Block WHITE_WISTERIA_VINES_PLANT = registerBlockWithoutItem("white_wisteria_vines_plant", new WisteriaVinePlant(FabricBlockSettings.of(Material.PLANT, MapColor.OFF_WHITE).noCollision().nonOpaque().breakInstantly().sounds(BlockSoundGroup.WEEPING_VINES).dropsLike(WHITE_WISTERIA_VINES)));
    public static final Block WHITE_WISTERIA_SAPLING = registerSapling("white_wisteria", new WhiteWisteriaSaplingGenerator());

    public static final Block BLUE_WISTERIA_LEAVES = registerWisteriaLeafBlock("blue_wisteria_leaves", MapColor.BRIGHT_TEAL);
    public static final Block BLUE_WISTERIA_VINES = registerBlock("blue_wisteria_vines", new WisteriaVine(FabricBlockSettings.of(Material.PLANT, MapColor.BRIGHT_TEAL).ticksRandomly().noCollision().breakInstantly().nonOpaque().sounds(BlockSoundGroup.WEEPING_VINES)),  HibiscusItemGroups.NatureSpiritItemGroup);
    public static final Block BLUE_WISTERIA_VINES_PLANT = registerBlockWithoutItem("blue_wisteria_vines_plant", new WisteriaVinePlant(FabricBlockSettings.of(Material.PLANT, MapColor.BRIGHT_TEAL).noCollision().nonOpaque().breakInstantly().sounds(BlockSoundGroup.WEEPING_VINES).dropsLike(BLUE_WISTERIA_VINES)));
    public static final Block BLUE_WISTERIA_SAPLING = registerSapling("blue_wisteria", new BlueWisteriaSaplingGenerator());

    public static final Block PINK_WISTERIA_LEAVES = registerWisteriaLeafBlock("pink_wisteria_leaves", MapColor.PINK);
    public static final Block PINK_WISTERIA_VINES = registerBlock("pink_wisteria_vines", new WisteriaVine(FabricBlockSettings.of(Material.PLANT, MapColor.PINK).ticksRandomly().noCollision().breakInstantly().nonOpaque().sounds(BlockSoundGroup.WEEPING_VINES)),  HibiscusItemGroups.NatureSpiritItemGroup);
    public static final Block PINK_WISTERIA_VINES_PLANT = registerBlockWithoutItem("pink_wisteria_vines_plant", new WisteriaVinePlant(FabricBlockSettings.of(Material.PLANT, MapColor.PINK).noCollision().nonOpaque().breakInstantly().sounds(BlockSoundGroup.WEEPING_VINES).dropsLike(PINK_WISTERIA_VINES)));
    public static final Block PINK_WISTERIA_SAPLING = registerSapling("pink_wisteria", new PinkWisteriaSaplingGenerator());

    public static final Block PURPLE_WISTERIA_LEAVES = registerWisteriaLeafBlock("purple_wisteria_leaves", MapColor.PALE_PURPLE);
    public static final Block PURPLE_WISTERIA_VINES = registerBlock("purple_wisteria_vines", new WisteriaVine(FabricBlockSettings.of(Material.PLANT, MapColor.PALE_PURPLE).ticksRandomly().noCollision().breakInstantly().nonOpaque().sounds(BlockSoundGroup.WEEPING_VINES)),  HibiscusItemGroups.NatureSpiritItemGroup);
    public static final Block PURPLE_WISTERIA_VINES_PLANT = registerBlockWithoutItem("purple_wisteria_vines_plant", new WisteriaVinePlant(FabricBlockSettings.of(Material.PLANT, MapColor.PALE_PURPLE).noCollision().nonOpaque().breakInstantly().sounds(BlockSoundGroup.WEEPING_VINES).dropsLike(PURPLE_WISTERIA_VINES)));
    public static final Block PURPLE_WISTERIA_SAPLING = registerSapling("purple_wisteria", new PurpleWisteriaSaplingGenerator());

    public static Block[] registerWoodBlocks(String name, MapColor topMapColor, MapColor sideMapColor) {
        SignType signType = SignTypeAccessor.registerNew(SignTypeAccessor.newSignType(name));
         Block[] ARRAY = new Block[15];
         ARRAY[0] = registerBlock(name + "_wood", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD, sideMapColor).strength(2.0F).sounds(BlockSoundGroup.WOOD)),  HibiscusItemGroups.NatureSpiritItemGroup);
         ARRAY[1] = registerBlock("stripped_" + name + "_wood", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD, topMapColor).strength(2.0F).sounds(BlockSoundGroup.WOOD)),  HibiscusItemGroups.NatureSpiritItemGroup);
         ARRAY[2] = registerBlock(name + "_log", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD, (state) -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor).strength(2.0F).sounds(BlockSoundGroup.WOOD)),  HibiscusItemGroups.NatureSpiritItemGroup);
         ARRAY[3] = registerBlock("stripped_" + name + "_log", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD, topMapColor).strength(2.0F).sounds(BlockSoundGroup.WOOD)),  HibiscusItemGroups.NatureSpiritItemGroup);
         ARRAY[4] = registerBlock(name + "_planks", new Block(FabricBlockSettings.of(Material.WOOD, topMapColor).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)),  HibiscusItemGroups.NatureSpiritItemGroup);
         ARRAY[5] = registerBlock(name + "_stairs", new StairsBlock(ARRAY[4].getDefaultState(), FabricBlockSettings.copy(ARRAY[4])),  HibiscusItemGroups.NatureSpiritItemGroup);
         ARRAY[6] = registerBlock(name + "_slab", new SlabBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)),  HibiscusItemGroups.NatureSpiritItemGroup);
         ARRAY[7] = registerBlock(name + "_door", new DoorBlock(FabricBlockSettings.copy(ARRAY[4]).nonOpaque()),  HibiscusItemGroups.NatureSpiritItemGroup);
         ARRAY[8] = registerBlock(name + "_trapdoor", new TrapdoorBlock(FabricBlockSettings.of(Material.WOOD).strength(3.0f).sounds(BlockSoundGroup.WOOD).nonOpaque()),  HibiscusItemGroups.NatureSpiritItemGroup);
         ARRAY[9] = registerBlock(name + "_fence", new FenceBlock(FabricBlockSettings.copy(ARRAY[4]).nonOpaque()),  HibiscusItemGroups.NatureSpiritItemGroup);
         ARRAY[10] = registerBlock(name + "_fence_gate", new FenceGateBlock(FabricBlockSettings.copy(ARRAY[4]).nonOpaque()),  HibiscusItemGroups.NatureSpiritItemGroup);
         ARRAY[11] = registerBlock(name + "_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.of(Material.WOOD, ARRAY[4].getDefaultMapColor()).noCollision().strength(0.5f).sounds(BlockSoundGroup.WOOD)),  HibiscusItemGroups.NatureSpiritItemGroup);
         ARRAY[12] = registerBlock(name + "_button", new WoodenButtonBlock(FabricBlockSettings.of(Material.WOOD, topMapColor).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)),  HibiscusItemGroups.NatureSpiritItemGroup);
         ARRAY[13] = registerBlockWithoutItem(name + "_sign", new SignBlock(FabricBlockSettings.copy(Blocks.OAK_SIGN), signType));
        ARRAY[14] = registerBlockWithoutItem(name + "_wall_sign", new WallSignBlock(FabricBlockSettings.copy(Blocks.OAK_WALL_SIGN).dropsLike(ARRAY[13]), signType));
         registerItemWithoutBlock(name + "_sign",
                 new SignItem(new FabricItemSettings().group( HibiscusItemGroups.NatureSpiritItemGroup).maxCount(16),
                         ARRAY[13], ARRAY[14]));

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

    private static Boolean canSpawnUponLeaves(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        return type == EntityType.OCELOT || type == EntityType.PARROT;
    }

    public static Block registerLeafBlock(String name, MapColor color) {
        Block LEAVES = registerBlock(name, new LeavesBlock(FabricBlockSettings.of(Material.LEAVES).strength(0.2F).ticksRandomly().nonOpaque().sounds(BlockSoundGroup.AZALEA_LEAVES).mapColor(color).allowsSpawning(HibiscusBlocks::canSpawnUponLeaves).suffocates(HibiscusBlocks::never).blockVision(HibiscusBlocks::never)),  HibiscusItemGroups.NatureSpiritItemGroup);
        BlockRenderLayerMap.INSTANCE.putBlock(LEAVES, RenderLayer.getCutout());
        FlammableBlockRegistry.getDefaultInstance().add(LEAVES, 5, 20);
        CompostingChanceRegistry.INSTANCE.add(LEAVES, 0.3F);
        return LEAVES;
    }

    public static Block registerWisteriaLeafBlock(String name, MapColor color) {
        Block LEAVES = registerBlock(name, new WisteriaLeaves(FabricBlockSettings.of(Material.LEAVES).strength(0.2F).ticksRandomly().nonOpaque().sounds(BlockSoundGroup.AZALEA_LEAVES).mapColor(color).allowsSpawning(HibiscusBlocks::canSpawnUponLeaves).suffocates(HibiscusBlocks::never).blockVision(HibiscusBlocks::never)),  HibiscusItemGroups.NatureSpiritItemGroup);
        BlockRenderLayerMap.INSTANCE.putBlock(LEAVES, RenderLayer.getCutout());
        FlammableBlockRegistry.getDefaultInstance().add(LEAVES, 5, 20);
        CompostingChanceRegistry.INSTANCE.add(LEAVES, 0.3F);
        return LEAVES;
    }

    public static Block registerSapling(String name, SaplingGenerator sapling_generator) {
        Block SAPLING = registerBlock(name + "_sapling", new SaplingBlock(sapling_generator, FabricBlockSettings.copy(Blocks.SPRUCE_SAPLING)), HibiscusItemGroups.NatureSpiritItemGroup);
        BlockRenderLayerMap.INSTANCE.putBlock(SAPLING, RenderLayer.getCutout());
        Block POTTED_SAPLING = registerBlockWithoutItem("potted_" + name + "_sapling", new FlowerPotBlock(SAPLING, FabricBlockSettings.of(Material.DECORATION).breakInstantly().nonOpaque()));
        BlockRenderLayerMap.INSTANCE.putBlock(POTTED_SAPLING, RenderLayer.getCutout());
        CompostingChanceRegistry.INSTANCE.add(SAPLING, 0.3F);
        return SAPLING;
    }

    public static Block registerBlock(String name, Block block, ItemGroup tab) {
        registerBlockItem(name, block, tab);
        return Registry.register(Registry.BLOCK, new Identifier(NatureSpirit.MOD_ID, name), block);
    }
    public static Block registerBlockWithoutItem(String name, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(NatureSpirit.MOD_ID, name), block);
    }

    public static Item registerBlockItem(String name, Block block, ItemGroup tab) {
        return Registry.register(Registry.ITEM, new Identifier(NatureSpirit.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(tab)));
    }

    public static Item registerItemWithoutBlock(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(NatureSpirit.MOD_ID, name), item);
    }

    public static void registerHibiscusBlocks() {
        NatureSpirit.LOGGER.debug("Registering ModBlocks for " + NatureSpirit.MOD_ID);
    }
}
