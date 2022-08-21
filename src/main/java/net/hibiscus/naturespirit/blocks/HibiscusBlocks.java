package net.hibiscus.naturespirit.blocks;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fabricmc.fabric.impl.datagen.FabricTagBuilder;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.items.HibiscusItemGroups;
import net.hibiscus.naturespirit.mixin.WoodTypeAccessor;
import net.hibiscus.naturespirit.world.feature.tree.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagEntry;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import java.util.function.Consumer;

public class HibiscusBlocks {

    public static final Block LAVENDER = registerBlock("lavender", new LargeTallFlowerBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT).noCollision().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), HibiscusItemGroups.NatureSpiritItemGroup);
    public static final Block CARNATION = registerBlock("carnation", new TallFlowerBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT).noCollision().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), HibiscusItemGroups.NatureSpiritItemGroup);
    public static final Block GARDENIA = registerBlock("gardenia", new TallFlowerBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT).noCollision().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), HibiscusItemGroups.NatureSpiritItemGroup);
    public static final Block BLUEBELL = registerBlock("bluebell", new LargeFlowerBlock(MobEffects.DIG_SPEED, 7, FabricBlockSettings.of(Material.PLANT).noCollision().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), HibiscusItemGroups.NatureSpiritItemGroup);
    public static final Block ANEMONE = registerBlock("anemone", new MidFlowerBlock(MobEffects.DAMAGE_RESISTANCE, 4, FabricBlockSettings.of(Material.PLANT).noCollision().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), HibiscusItemGroups.NatureSpiritItemGroup);
    public static final Block HIBISCUS = registerBlock("hibiscus", new FlowerBlock(MobEffects.LUCK, 7, FabricBlockSettings.of(Material.PLANT).noCollision().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), HibiscusItemGroups.NatureSpiritItemGroup);

    public static final Block POTTED_ANEMONE = registerBlockWithoutItem("potted_anemone", new FlowerPotBlock(ANEMONE, FabricBlockSettings.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final Block POTTED_HIBISCUS = registerBlockWithoutItem("potted_hibiscus", new FlowerPotBlock(HIBISCUS, FabricBlockSettings.of(Material.DECORATION).instabreak().noOcclusion()));


    public static final Block[] REDWOOD = registerWoodBlocks("redwood", MaterialColor.COLOR_RED, MaterialColor.TERRACOTTA_BROWN);
    public static final Block REDWOOD_LEAVES = registerLeafBlock("redwood_leaves", MaterialColor.COLOR_LIGHT_GREEN);
    public static final Block REDWOOD_SAPLING = registerSapling("redwood", new RedwoodSaplingGenerator());

    public static final Block[] SAKURA = registerWoodBlocks("sakura", MaterialColor.DIRT, MaterialColor.DEEPSLATE);
    public static final Block PINK_SAKURA_LEAVES = registerLeafBlock("pink_sakura_leaves", MaterialColor.COLOR_PINK);
    public static final Block PINK_SAKURA_SAPLING = registerSapling("pink_sakura", new PinkSakuraSaplingGenerator());
    public static final Block WHITE_SAKURA_LEAVES = registerLeafBlock("white_sakura_leaves", MaterialColor.TERRACOTTA_WHITE);
    public static final Block WHITE_SAKURA_SAPLING = registerSapling("white_sakura", new WhiteSakuraSaplingGenerator());
    public static final Block BLOOMING_SAKURA_DOOR = registerBlock( "blooming_sakura_door", new DoorBlock(FabricBlockSettings.copy(SAKURA[4]).noOcclusion()),  HibiscusItemGroups.NatureSpiritItemGroup);
    public static final Block BLOOMING_SAKURA_TRAPDOOR  = registerBlock("blooming_sakura_trapdoor", new TrapDoorBlock(FabricBlockSettings.of(Material.WOOD).strength(3.0f).sound(SoundType.WOOD).noOcclusion()),  HibiscusItemGroups.NatureSpiritItemGroup);
    public static final Block FRAMED_SAKURA_DOOR = registerBlock( "framed_sakura_door", new DoorBlock(FabricBlockSettings.copy(SAKURA[4]).noOcclusion()),  HibiscusItemGroups.NatureSpiritItemGroup);
    public static final Block FRAMED_SAKURA_TRAPDOOR  = registerBlock("framed_sakura_trapdoor", new TrapDoorBlock(FabricBlockSettings.of(Material.WOOD).strength(3.0f).sound(SoundType.WOOD).noOcclusion()),  HibiscusItemGroups.NatureSpiritItemGroup);


    public static final Block[] WISTERIA = registerWoodBlocks("wisteria", MaterialColor.TERRACOTTA_WHITE, MaterialColor.COLOR_GRAY);

    public static final Block WHITE_WISTERIA_LEAVES = registerWisteriaLeafBlock("white_wisteria_leaves", MaterialColor.TERRACOTTA_WHITE);
    public static final Block WHITE_WISTERIA_VINES = registerBlock("white_wisteria_vines", new WisteriaVine(FabricBlockSettings.of(Material.PLANT, MaterialColor.TERRACOTTA_WHITE).ticksRandomly().noCollision().noOcclusion().instabreak().sound(SoundType.WEEPING_VINES)), HibiscusItemGroups.NatureSpiritItemGroup);
    public static final Block WHITE_WISTERIA_VINES_PLANT = registerBlockWithoutItem("white_wisteria_vines_plant", new WisteriaVinePlant(FabricBlockSettings.of(Material.PLANT, MaterialColor.TERRACOTTA_WHITE).noCollision().noOcclusion().instabreak().sound(SoundType.WEEPING_VINES).dropsLike(WHITE_WISTERIA_VINES)));
    public static final Block WHITE_WISTERIA_SAPLING = registerSapling("white_wisteria", new WhiteWisteriaSaplingGenerator());

    public static final Block BLUE_WISTERIA_LEAVES = registerWisteriaLeafBlock("blue_wisteria_leaves", MaterialColor.COLOR_CYAN);
    public static final Block BLUE_WISTERIA_VINES = registerBlock("blue_wisteria_vines", new WisteriaVine(FabricBlockSettings.of(Material.PLANT, MaterialColor.COLOR_CYAN).ticksRandomly().noCollision().instabreak().noOcclusion().sound(SoundType.WEEPING_VINES)),  HibiscusItemGroups.NatureSpiritItemGroup);
    public static final Block BLUE_WISTERIA_VINES_PLANT = registerBlockWithoutItem("blue_wisteria_vines_plant", new WisteriaVinePlant(FabricBlockSettings.of(Material.PLANT, MaterialColor.COLOR_CYAN).noCollision().noOcclusion().instabreak().sound(SoundType.WEEPING_VINES).dropsLike(BLUE_WISTERIA_VINES)));
    public static final Block BLUE_WISTERIA_SAPLING = registerSapling("blue_wisteria", new BlueWisteriaSaplingGenerator());

    public static final Block PINK_WISTERIA_LEAVES = registerWisteriaLeafBlock("pink_wisteria_leaves", MaterialColor.COLOR_PINK);
    public static final Block PINK_WISTERIA_VINES = registerBlock("pink_wisteria_vines", new WisteriaVine(FabricBlockSettings.of(Material.PLANT, MaterialColor.COLOR_PINK).ticksRandomly().noCollision().instabreak().noOcclusion().sound(SoundType.WEEPING_VINES)),  HibiscusItemGroups.NatureSpiritItemGroup);
    public static final Block PINK_WISTERIA_VINES_PLANT = registerBlockWithoutItem("pink_wisteria_vines_plant", new WisteriaVinePlant(FabricBlockSettings.of(Material.PLANT, MaterialColor.COLOR_PINK).noCollision().noOcclusion().instabreak().sound(SoundType.WEEPING_VINES).dropsLike(PINK_WISTERIA_VINES)));
    public static final Block PINK_WISTERIA_SAPLING = registerSapling("pink_wisteria", new PinkWisteriaSaplingGenerator());

    public static final Block PURPLE_WISTERIA_LEAVES = registerWisteriaLeafBlock("purple_wisteria_leaves", MaterialColor.COLOR_PURPLE);
    public static final Block PURPLE_WISTERIA_VINES = registerBlock("purple_wisteria_vines", new WisteriaVine(FabricBlockSettings.of(Material.PLANT, MaterialColor.COLOR_PURPLE).ticksRandomly().noCollision().instabreak().noOcclusion().sound(SoundType.WEEPING_VINES)),  HibiscusItemGroups.NatureSpiritItemGroup);
    public static final Block PURPLE_WISTERIA_VINES_PLANT = registerBlockWithoutItem("purple_wisteria_vines_plant", new WisteriaVinePlant(FabricBlockSettings.of(Material.PLANT, MaterialColor.COLOR_PURPLE).noCollision().noOcclusion().instabreak().sound(SoundType.WEEPING_VINES).dropsLike(PURPLE_WISTERIA_VINES)));
    public static final Block PURPLE_WISTERIA_SAPLING = registerSapling("purple_wisteria", new PurpleWisteriaSaplingGenerator());

    public static Block[] registerWoodBlocks(String name, MaterialColor topMaterialColor, MaterialColor sideMaterialColor) {
        WoodType woodType = WoodTypeAccessor.registerNew(WoodTypeAccessor.newWoodType(name));
         Block[] ARRAY = new Block[15];
         ARRAY[0] = registerBlock(name + "_wood", new RotatedPillarBlock(FabricBlockSettings.of(Material.WOOD, sideMaterialColor).strength(2.0F).sound(SoundType.WOOD)),  HibiscusItemGroups.NatureSpiritItemGroup);
         ARRAY[1] = registerBlock("stripped_" + name + "_wood", new RotatedPillarBlock(FabricBlockSettings.of(Material.WOOD, topMaterialColor).strength(2.0F).sound(SoundType.WOOD)),  HibiscusItemGroups.NatureSpiritItemGroup);
         ARRAY[2] = registerBlock(name + "_log", new RotatedPillarBlock(FabricBlockSettings.of(Material.WOOD, (state) -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topMaterialColor : sideMaterialColor).strength(2.0F).sound(SoundType.WOOD)),  HibiscusItemGroups.NatureSpiritItemGroup);
         ARRAY[3] = registerBlock("stripped_" + name + "_log", new RotatedPillarBlock(FabricBlockSettings.of(Material.WOOD, topMaterialColor).strength(2.0F).sound(SoundType.WOOD)),  HibiscusItemGroups.NatureSpiritItemGroup);
         ARRAY[4] = registerBlock(name + "_planks", new Block(FabricBlockSettings.of(Material.WOOD, topMaterialColor).strength(2.0F, 3.0F).sound(SoundType.WOOD)),  HibiscusItemGroups.NatureSpiritItemGroup);
         ARRAY[5] = registerBlock(name + "_stairs", new StairBlock(ARRAY[4].defaultBlockState(), FabricBlockSettings.copy(ARRAY[4])),  HibiscusItemGroups.NatureSpiritItemGroup);
         ARRAY[6] = registerBlock(name + "_slab", new SlabBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0f, 3.0f).sound(SoundType.WOOD)),  HibiscusItemGroups.NatureSpiritItemGroup);
         ARRAY[7] = registerBlock(name + "_door", new DoorBlock(FabricBlockSettings.copy(ARRAY[4]).noOcclusion()),  HibiscusItemGroups.NatureSpiritItemGroup);
         ARRAY[8] = registerBlock(name + "_trapdoor", new TrapDoorBlock(FabricBlockSettings.of(Material.WOOD).strength(3.0f).sound(SoundType.WOOD).noOcclusion()),  HibiscusItemGroups.NatureSpiritItemGroup);
         ARRAY[9] = registerBlock(name + "_fence", new FenceBlock(FabricBlockSettings.copy(ARRAY[4]).noOcclusion()),  HibiscusItemGroups.NatureSpiritItemGroup);
         ARRAY[10] = registerBlock(name + "_fence_gate", new FenceGateBlock(FabricBlockSettings.copy(ARRAY[4]).noOcclusion()),  HibiscusItemGroups.NatureSpiritItemGroup);
         ARRAY[11] = registerBlock(name + "_pressure_plate", new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, FabricBlockSettings.of(Material.WOOD, ARRAY[4].defaultMaterialColor()).noCollision().strength(0.5f).sound(SoundType.WOOD)),  HibiscusItemGroups.NatureSpiritItemGroup);
         ARRAY[12] = registerBlock(name + "_button", new WoodButtonBlock(FabricBlockSettings.of(Material.WOOD, topMaterialColor).strength(2.0F, 3.0F).sound(SoundType.WOOD)),  HibiscusItemGroups.NatureSpiritItemGroup);
         ARRAY[13] = registerBlockWithoutItem(name + "_sign", new StandingSignBlock(FabricBlockSettings.copy(Blocks.OAK_SIGN), woodType));
        ARRAY[14] = registerBlockWithoutItem(name + "_wall_sign", new WallSignBlock(FabricBlockSettings.copy(Blocks.OAK_WALL_SIGN).dropsLike(ARRAY[13]), woodType));
         registerItemWithoutBlock(name + "_sign",
                 new SignItem(new FabricItemSettings().group(HibiscusItemGroups.NatureSpiritItemGroup).maxCount(16),
                         ARRAY[13], ARRAY[14]));

        BlockRenderLayerMap.INSTANCE.putBlock(ARRAY[7], RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ARRAY[8], RenderType.cutout());
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

    private static boolean never(BlockState state, BlockGetter world, BlockPos pos) {
        return false;
    }

    private static Boolean canSpawnUponLeaves(BlockState state, BlockGetter world, BlockPos pos, EntityType <?> type) {
        return type == EntityType.OCELOT || type == EntityType.PARROT;
    }

    public static Block registerLeafBlock(String name, MaterialColor color) {
        Block LEAVES = registerBlock(name, new LeavesBlock(FabricBlockSettings.of(Material.LEAVES).strength(0.2F).ticksRandomly().noOcclusion().sound(SoundType.AZALEA_LEAVES).color(color).isValidSpawn(HibiscusBlocks::canSpawnUponLeaves).isSuffocating(HibiscusBlocks::never).isViewBlocking(HibiscusBlocks::never)),  HibiscusItemGroups.NatureSpiritItemGroup);
        BlockRenderLayerMap.INSTANCE.putBlock(LEAVES, RenderType.cutout());
        FlammableBlockRegistry.getDefaultInstance().add(LEAVES, 5, 20);
        CompostingChanceRegistry.INSTANCE.add(LEAVES, 0.3F);
        return LEAVES;
    }

    public static Block registerWisteriaLeafBlock(String name, MaterialColor color) {
        Block LEAVES = registerBlock(name, new WisteriaLeaves(FabricBlockSettings.of(Material.LEAVES).strength(0.2F).ticksRandomly().noOcclusion().sound(SoundType.AZALEA_LEAVES).color(color).isValidSpawn(HibiscusBlocks::canSpawnUponLeaves).isSuffocating(HibiscusBlocks::never).isViewBlocking(HibiscusBlocks::never)),  HibiscusItemGroups.NatureSpiritItemGroup);
        BlockRenderLayerMap.INSTANCE.putBlock(LEAVES, RenderType.cutout());
        FlammableBlockRegistry.getDefaultInstance().add(LEAVES, 5, 20);
        CompostingChanceRegistry.INSTANCE.add(LEAVES, 0.3F);
        return LEAVES;
    }

    public static Block registerSapling(String name, AbstractTreeGrower sapling_generator) {
        Block SAPLING = registerBlock(name + "_sapling", new SaplingBlock(sapling_generator, FabricBlockSettings.copy(Blocks.SPRUCE_SAPLING)), HibiscusItemGroups.NatureSpiritItemGroup);
        BlockRenderLayerMap.INSTANCE.putBlock(SAPLING, RenderType.cutout());
        Block POTTED_SAPLING = registerBlockWithoutItem("potted_" + name + "_sapling", new FlowerPotBlock(SAPLING, FabricBlockSettings.of(Material.DECORATION).instabreak().noOcclusion()));
        BlockRenderLayerMap.INSTANCE.putBlock(POTTED_SAPLING, RenderType.cutout());
        CompostingChanceRegistry.INSTANCE.add(SAPLING, 0.3F);
        return SAPLING;
    }

    public static Block registerBlock(String name, Block block, CreativeModeTab tab) {
        registerBlockItem(name, block, tab);
        return Registry.register(Registry.BLOCK, new ResourceLocation(NatureSpirit.MOD_ID, name), block);
    }
    public static Block registerBlockWithoutItem(String name, Block block) {
        return Registry.register(Registry.BLOCK, new ResourceLocation(NatureSpirit.MOD_ID, name), block);
    }

    public static Item registerBlockItem(String name, Block block, CreativeModeTab tab) {
        return Registry.register(Registry.ITEM, new ResourceLocation(NatureSpirit.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(tab)));
    }

    public static Item registerItemWithoutBlock(String name, Item item) {
        return Registry.register(Registry.ITEM, new ResourceLocation(NatureSpirit.MOD_ID, name), item);
    }

    public static void registerHibiscusBlocks() {
        NatureSpirit.LOGGER.debug("Registering ModBlocks for " + NatureSpirit.MOD_ID);
    }
}
