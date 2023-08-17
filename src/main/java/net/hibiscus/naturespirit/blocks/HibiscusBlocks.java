package net.hibiscus.naturespirit.blocks;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.items.HibiscusItemGroups;
import net.hibiscus.naturespirit.items.PizzaItem;
import net.hibiscus.naturespirit.mixin.BlockSetTypeAccessor;
import net.hibiscus.naturespirit.mixin.WoodTypeAccessor;
import net.hibiscus.naturespirit.world.feature.tree.*;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

import java.util.HashMap;

public class HibiscusBlocks {
   public static HashMap<String, Block[]> WoodHashMap = new HashMap<>();
   public static HashMap<String, Block[]> SaplingHashmap = new HashMap<>();
   public static HashMap<String, Block> LeavesHashMap = new HashMap<>();
   public static final Block TALL_SCORCHED_GRASS = registerPlantBlock("tall_scorched_grass", new TallLargeDesertFernBlock(
           FabricBlockSettings.create()
                   .mapColor(MapColor.LICHEN_GREEN)
                   .noCollision()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XZ)
                   .pistonBehavior(PistonBehavior.DESTROY)
   ), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, Blocks.LARGE_FERN, 0.3f);

   public static final Block SCORCHED_GRASS = registerPlantBlock("scorched_grass", new LargeDesertFernBlock(
           FabricBlockSettings.create()
                   .mapColor(MapColor.LICHEN_GREEN)
                   .noCollision()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XZ)
                   .pistonBehavior(PistonBehavior.DESTROY)
   ), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, Blocks.FERN, 0.3f);

   public static final Block LAVENDER = registerPlantBlock("lavender",
           new LargeTallFlowerBlock(FabricBlockSettings.create()
                   .mapColor(MapColor.DARK_GREEN)
                   .noCollision()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .burnable()
                   .offset(AbstractBlock.OffsetType.XZ)
                   .pistonBehavior(PistonBehavior.DESTROY)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           Blocks.PEONY,
           0.5f
   );
   public static final Block BLEEDING_HEART = registerPlantBlock("bleeding_heart",
           new LargeTallFlowerBlock(FabricBlockSettings.create()
                   .mapColor(MapColor.DARK_GREEN)
                   .noCollision()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .burnable()
                   .offset(AbstractBlock.OffsetType.XZ)
                   .pistonBehavior(PistonBehavior.DESTROY)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           LAVENDER,
           0.5f
   );
   public static final Block CARNATION = registerPlantBlock("carnation",
           new TallFlowerBlock(FabricBlockSettings.create()
                   .mapColor(MapColor.DARK_GREEN)
                   .noCollision()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .burnable()
                   .offset(AbstractBlock.OffsetType.XZ)
                   .pistonBehavior(PistonBehavior.DESTROY)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           BLEEDING_HEART,
           0.4f
   );
   public static final Block GARDENIA = registerPlantBlock("gardenia",
           new TallFlowerBlock(FabricBlockSettings.create()
                   .mapColor(MapColor.DARK_GREEN)
                   .noCollision()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .burnable()
                   .offset(AbstractBlock.OffsetType.XZ)
                   .pistonBehavior(PistonBehavior.DESTROY)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           CARNATION,
           0.4f
   );
   public static final Block SNAPDRAGON = registerPlantBlock("snapdragon",
           new TallFlowerBlock(FabricBlockSettings.create()
                   .mapColor(MapColor.DARK_GREEN)
                   .noCollision()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .burnable()
                   .offset(AbstractBlock.OffsetType.XZ)
                   .pistonBehavior(PistonBehavior.DESTROY)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           GARDENIA,
           0.4f
   );
   public static final Block MARIGOLD = registerPlantBlock("marigold",
           new TallFlowerBlock(FabricBlockSettings.create()
                   .mapColor(MapColor.DARK_GREEN)
                   .noCollision()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .burnable()
                   .offset(AbstractBlock.OffsetType.XZ)
                   .pistonBehavior(PistonBehavior.DESTROY)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           SNAPDRAGON,
           0.4f
   );
   public static final Block FOXGLOVE = registerPlantBlock("foxglove",
           new TallFlowerBlock(FabricBlockSettings.create()
                   .mapColor(MapColor.DARK_GREEN)
                   .noCollision()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .burnable()
                   .offset(AbstractBlock.OffsetType.XZ)
                   .pistonBehavior(PistonBehavior.DESTROY)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           MARIGOLD,
           0.4f
   );
   public static final Block CATTAIL = registerPlantBlock("cattail",
           new Cattails(FabricBlockSettings.create()
                   .mapColor(MapColor.DARK_GREEN)
                   .noCollision()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .burnable()
                   .offset(AbstractBlock.OffsetType.XZ)
                   .pistonBehavior(PistonBehavior.DESTROY)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           Blocks.LARGE_FERN,
           0.4f
   );
   public static final Block BLUEBELL = registerPlantBlock("bluebell", new LargeFlowerBlock(StatusEffects.HASTE, 7,
           FabricBlockSettings.create()
                   .mapColor(MapColor.DARK_GREEN)
                   .noCollision()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XZ)
                   .pistonBehavior(PistonBehavior.DESTROY)
   ), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, Blocks.LILY_OF_THE_VALLEY, 0.4f);

   public static final Block TIGER_LILY = registerPlantBlock("tiger_lily", new LargeFlowerBlock(StatusEffects.FIRE_RESISTANCE, 7,
           FabricBlockSettings.create()
                   .mapColor(MapColor.DARK_GREEN)
                   .noCollision()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XZ)
                   .pistonBehavior(PistonBehavior.DESTROY)
   ), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, BLUEBELL, 0.4f);

   public static final Block PURPLE_WILDFLOWER = registerPlantBlock("purple_wildflower", new LargeFlowerBlock(StatusEffects.SLOW_FALLING, 7,
           FabricBlockSettings.create()
                   .mapColor(MapColor.DARK_GREEN)
                   .noCollision()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XZ)
                   .pistonBehavior(PistonBehavior.DESTROY)
   ), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, TIGER_LILY, 0.4f);

   public static final Block YELLOW_WILDFLOWER = registerPlantBlock("yellow_wildflower", new LargeFlowerBlock(StatusEffects.SLOW_FALLING, 7,
           FabricBlockSettings.create()
                   .mapColor(MapColor.DARK_GREEN)
                   .noCollision()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XZ)
                   .pistonBehavior(PistonBehavior.DESTROY)
   ), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, PURPLE_WILDFLOWER, 0.4f);

   public static final Block ANEMONE = registerPlantBlock("anemone", new MidFlowerBlock(StatusEffects.RESISTANCE, 4,
           FabricBlockSettings.create()
                   .mapColor(MapColor.DARK_GREEN)
                   .noCollision()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XZ)
                   .pistonBehavior(PistonBehavior.DESTROY)
   ), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, TIGER_LILY, 0.4f);

   public static final Block HIBISCUS = registerPlantBlock("hibiscus", new FlowerBlock(StatusEffects.LUCK, 7,
           FabricBlockSettings.create()
                   .mapColor(MapColor.DARK_GREEN)
                   .noCollision()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XZ)
                   .pistonBehavior(PistonBehavior.DESTROY)
   ), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, ANEMONE, 0.3f);
   public static final Block POTTED_HIBISCUS = registerPottedPlant("hibiscus", HIBISCUS);
   public static final Block POTTED_ANEMONE = registerPottedPlant("anemone", ANEMONE);
   public static final Block REDWOOD_LEAVES = registerLeafBlock("redwood", MapColor.LIME, Blocks.CHERRY_LEAVES);
   public static final Block SUGI_LEAVES = registerLeafBlock("sugi", MapColor.PINK, REDWOOD_LEAVES);
   public static final Block WHITE_WISTERIA_LEAVES = registerLeafBlock("white_wisteria",
           MapColor.TERRACOTTA_WHITE,
           SUGI_LEAVES,
           true
   );
   public static final Block BLUE_WISTERIA_LEAVES = registerLeafBlock("blue_wisteria",
           MapColor.CYAN,
           WHITE_WISTERIA_LEAVES,
           true
   );
   public static final Block PINK_WISTERIA_LEAVES = registerLeafBlock("pink_wisteria",
           MapColor.PINK,
           BLUE_WISTERIA_LEAVES,
           true
   );
   public static final Block PURPLE_WISTERIA_LEAVES = registerLeafBlock("purple_wisteria",
           MapColor.PURPLE,
           PINK_WISTERIA_LEAVES,
           true
   );
   public static final Block FIR_LEAVES = registerLeafBlock("fir", MapColor.LIME, PURPLE_WISTERIA_LEAVES);
   public static final Block WILLOW_LEAVES = registerLeafBlock("willow", MapColor.GREEN, FIR_LEAVES, false);
   public static final Block ASPEN_LEAVES = registerLeafBlock("aspen", MapColor.LIME, WILLOW_LEAVES);
   public static final Block CYPRESS_LEAVES = registerLeafBlock("cypress", MapColor.DARK_GREEN, ASPEN_LEAVES);
   public static final Block OLIVE_LEAVES = registerLeafBlock("olive", MapColor.DARK_GREEN, CYPRESS_LEAVES);
   public static final Block JOSHUA_LEAVES = registerLeafBlock("joshua", MapColor.PALE_YELLOW, OLIVE_LEAVES);

   public static final Block[] REDWOOD_SAPLING = registerSapling("redwood",
           new RedwoodSaplingGenerator(),
           Blocks.CHERRY_SAPLING
   );
   public static final Block[] SUGI_SAPLING = registerSapling("sugi", new SugiSaplingGenerator(), REDWOOD_SAPLING[0]);
   public static final Block[] WHITE_WISTERIA_SAPLING = registerSapling("white_wisteria",
           new WhiteWisteriaSaplingGenerator(),
           SUGI_SAPLING[0]
   );
   public static final Block[] BLUE_WISTERIA_SAPLING = registerSapling("blue_wisteria",
           new BlueWisteriaSaplingGenerator(),
           WHITE_WISTERIA_SAPLING[0]
   );
   public static final Block[] PINK_WISTERIA_SAPLING = registerSapling("pink_wisteria",
           new PinkWisteriaSaplingGenerator(),
           BLUE_WISTERIA_SAPLING[0]
   );
   public static final Block[] PURPLE_WISTERIA_SAPLING = registerSapling("purple_wisteria",
           new PurpleWisteriaSaplingGenerator(),
           PINK_WISTERIA_SAPLING[0]
   );
   public static final Block[] FIR_SAPLING = registerSapling("fir",
           new FirSaplingGenerator(),
           PURPLE_WISTERIA_SAPLING[0]
   );
   public static final Block[] WILLOW_SAPLING = registerSapling("willow", new WillowSaplingGenerator(), FIR_SAPLING[0]);
   public static final Block[] ASPEN_SAPLING = registerSapling("aspen", new AspenSaplingGenerator(), WILLOW_SAPLING[0]);
   public static final Block[] CYPRESS_SAPLING = registerSapling("cypress",
           new CypressSaplingGenerator(),
           ASPEN_SAPLING[0]
   );
   public static final Block[] OLIVE_SAPLING = registerSapling("olive",
           new OliveSaplingGenerator(),
           CYPRESS_SAPLING[0]
   );
   public static final Block[] JOSHUA_SAPLING = registerJoshuaSapling("joshua", new JoshuaSaplingGenerator(), OLIVE_SAPLING[0]);

   public static final Block WHITE_WISTERIA_VINES = registerPlantBlock("white_wisteria_vines",
           new WisteriaVine(FabricBlockSettings.create()
                   .mapColor(MapColor.TERRACOTTA_WHITE)
                   .pistonBehavior(PistonBehavior.DESTROY)
                   .ticksRandomly()
                   .noCollision()
                   .nonOpaque()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.WEEPING_VINES)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           Blocks.VINE,
           0.5f
   );
   public static final Block WHITE_WISTERIA_VINES_PLANT = registerPlantBlock("white_wisteria_vines_plant",
           new WisteriaVinePlant(FabricBlockSettings.create()
                   .mapColor(MapColor.TERRACOTTA_WHITE)
                   .pistonBehavior(PistonBehavior.DESTROY)
                   .noCollision()
                   .nonOpaque()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.WEEPING_VINES)
                   .dropsLike(WHITE_WISTERIA_VINES), WHITE_WISTERIA_VINES)
   );
   public static final Block BLUE_WISTERIA_VINES = registerPlantBlock("blue_wisteria_vines",
           new WisteriaVine(FabricBlockSettings.create()
                   .mapColor(MapColor.CYAN)
                   .pistonBehavior(PistonBehavior.DESTROY)
                   .ticksRandomly()
                   .noCollision()
                   .breakInstantly()
                   .nonOpaque()
                   .sounds(BlockSoundGroup.WEEPING_VINES)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           WHITE_WISTERIA_VINES,
           0.5f
   );
   public static final Block BLUE_WISTERIA_VINES_PLANT = registerPlantBlock("blue_wisteria_vines_plant",
           new WisteriaVinePlant(FabricBlockSettings.create()
                   .mapColor(MapColor.CYAN)
                   .pistonBehavior(PistonBehavior.DESTROY)
                   .noCollision()
                   .nonOpaque()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.WEEPING_VINES)
                   .dropsLike(BLUE_WISTERIA_VINES), BLUE_WISTERIA_VINES)
   );
   public static final Block PINK_WISTERIA_VINES = registerPlantBlock("pink_wisteria_vines",
           new WisteriaVine(FabricBlockSettings.create()
                   .mapColor(MapColor.PINK)
                   .pistonBehavior(PistonBehavior.DESTROY)
                   .ticksRandomly()
                   .noCollision()
                   .breakInstantly()
                   .nonOpaque()
                   .sounds(BlockSoundGroup.WEEPING_VINES)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           BLUE_WISTERIA_VINES,
           0.5f
   );
   public static final Block PINK_WISTERIA_VINES_PLANT = registerPlantBlock("pink_wisteria_vines_plant",
           new WisteriaVinePlant(FabricBlockSettings.create()
                   .mapColor(MapColor.PINK)
                   .pistonBehavior(PistonBehavior.DESTROY)
                   .noCollision()
                   .nonOpaque()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.WEEPING_VINES)
                   .dropsLike(PINK_WISTERIA_VINES), PINK_WISTERIA_VINES)
   );
   public static final Block PURPLE_WISTERIA_VINES = registerPlantBlock("purple_wisteria_vines",
           new WisteriaVine(FabricBlockSettings.create()
                   .mapColor(MapColor.PURPLE)
                   .pistonBehavior(PistonBehavior.DESTROY)
                   .ticksRandomly()
                   .noCollision()
                   .breakInstantly()
                   .nonOpaque()
                   .sounds(BlockSoundGroup.WEEPING_VINES)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           PINK_WISTERIA_VINES,
           0.5f
   );
   public static final Block PURPLE_WISTERIA_VINES_PLANT = registerPlantBlock("purple_wisteria_vines_plant",
           new WisteriaVinePlant(FabricBlockSettings.create()
                   .mapColor(MapColor.PURPLE)
                   .pistonBehavior(PistonBehavior.DESTROY)
                   .noCollision()
                   .nonOpaque()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.WEEPING_VINES)
                   .dropsLike(PURPLE_WISTERIA_VINES), PURPLE_WISTERIA_VINES)
   );
   public static final Block WILLOW_VINES = registerPlantBlock("willow_vines",
           new WillowVine(FabricBlockSettings.create()
                   .mapColor(MapColor.DARK_GREEN)
                   .pistonBehavior(PistonBehavior.DESTROY)
                   .ticksRandomly()
                   .noCollision()
                   .nonOpaque()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.WEEPING_VINES)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           PURPLE_WISTERIA_VINES,
           0.5f
   );
   public static final Block WILLOW_VINES_PLANT = registerPlantBlock("willow_vines_plant",
           new WillowVinePlant(FabricBlockSettings.create()
                   .mapColor(MapColor.DARK_GREEN)
                   .pistonBehavior(PistonBehavior.DESTROY)
                   .noCollision()
                   .nonOpaque()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.WEEPING_VINES)
                   .dropsLike(WILLOW_VINES))
   );
   public static final BlockSetType REDWOOD_BLOCK_SET_TYPE = BlockSetTypeAccessor.registerNew(new BlockSetType("redwood"));
   public static final WoodType REDWOOD_WOOD_TYPE = WoodTypeAccessor.registerNew(new WoodType("redwood", REDWOOD_BLOCK_SET_TYPE));
   public static final BlockSetType SUGI_BLOCK_SET_TYPE = BlockSetTypeAccessor.registerNew(new BlockSetType("sugi"));
   public static final WoodType SUGI_WOOD_TYPE = WoodTypeAccessor.registerNew(new WoodType("sugi", SUGI_BLOCK_SET_TYPE));
   public static final BlockSetType FIR_BLOCK_SET_TYPE = BlockSetTypeAccessor.registerNew(new BlockSetType("fir"));
   public static final WoodType FIR_WOOD_TYPE = WoodTypeAccessor.registerNew(new WoodType("fir", FIR_BLOCK_SET_TYPE));
   public static final BlockSetType WISTERIA_BLOCK_SET_TYPE = BlockSetTypeAccessor.registerNew(new BlockSetType("wisteria"));
   public static final WoodType WISTERIA_WOOD_TYPE = WoodTypeAccessor.registerNew(new WoodType("wisteria", WISTERIA_BLOCK_SET_TYPE));
   public static final BlockSetType WILLOW_BLOCK_SET_TYPE = BlockSetTypeAccessor.registerNew(new BlockSetType("willow"));
   public static final WoodType WILLOW_WOOD_TYPE = WoodTypeAccessor.registerNew(new WoodType("willow", WILLOW_BLOCK_SET_TYPE));
   public static final BlockSetType ASPEN_BLOCK_SET_TYPE = BlockSetTypeAccessor.registerNew(new BlockSetType("aspen"));
   public static final WoodType ASPEN_WOOD_TYPE = WoodTypeAccessor.registerNew(new WoodType("aspen", ASPEN_BLOCK_SET_TYPE));
   public static final BlockSetType MAPLE_BLOCK_SET_TYPE = BlockSetTypeAccessor.registerNew(new BlockSetType("maple"));
   public static final WoodType MAPLE_WOOD_TYPE = WoodTypeAccessor.registerNew(new WoodType("maple", ASPEN_BLOCK_SET_TYPE));
   public static final BlockSetType CYPRESS_BLOCK_SET_TYPE = BlockSetTypeAccessor.registerNew(new BlockSetType("cypress"));
   public static final WoodType CYPRESS_WOOD_TYPE = WoodTypeAccessor.registerNew(new WoodType("cypress", CYPRESS_BLOCK_SET_TYPE));
   public static final BlockSetType OLIVE_BLOCK_SET_TYPE = BlockSetTypeAccessor.registerNew(new BlockSetType("olive"));
   public static final WoodType OLIVE_WOOD_TYPE = WoodTypeAccessor.registerNew(new WoodType("olive", OLIVE_BLOCK_SET_TYPE));
   public static final BlockSetType JOSHUA_BLOCK_SET_TYPE = BlockSetTypeAccessor.registerNew(new BlockSetType("joshua"));
   public static final WoodType JOSHUA_WOOD_TYPE = WoodTypeAccessor.registerNew(new WoodType("joshua", JOSHUA_BLOCK_SET_TYPE));

   public static final FoodComponent GREEN_OLIVE_COMPONENT = (new FoodComponent.Builder()).hunger(2).saturationModifier(
           0.4F).build();
   public static final FoodComponent BLACK_OLIVE_COMPONENT = (new FoodComponent.Builder()).hunger(3).saturationModifier(
           0.5F).build();
   public static final FoodComponent DESERT_TURNIP_FOOD_COMPONENT = (new FoodComponent.Builder()).hunger(4)
           .saturationModifier(0.6F)
           .build();

   public static final Item GREEN_OLIVES = registerPlantItem("green_olives",
           new Item(new FabricItemSettings().food(GREEN_OLIVE_COMPONENT)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           Items.BEETROOT,
           ItemGroups.FOOD_AND_DRINK,
           0.3F
   );
   public static final Item BLACK_OLIVES = registerPlantItem("black_olives",
           new Item(new FabricItemSettings().food(BLACK_OLIVE_COMPONENT)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           GREEN_OLIVES,
           ItemGroups.FOOD_AND_DRINK,
           0.3F
   );
   public static final Block DESERT_TURNIP_ROOT_BLOCK = registerBlock("desert_turnip_root_block",
           new PillarBlock(FabricBlockSettings.create()
                   .burnable()
                   .instrument(Instrument.BASS)
                   .mapColor(MapColor.SPRUCE_BROWN)
                   .strength(2.0F)
                   .sounds(BlockSoundGroup.ROOTS)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           Blocks.SHROOMLIGHT,
           ItemGroups.NATURAL
   );
   public static final Block DESERT_TURNIP_BLOCK = registerBlock("desert_turnip_block",
           new DesertTurnipBlock(FabricBlockSettings.create()
                   .burnable()
                   .instrument(Instrument.BASS)
                   .mapColor(MapColor.PALE_PURPLE)
                   .strength(2.0F)
                   .sounds(BlockSoundGroup.ROOTS)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.DESERT_TURNIP_ROOT_BLOCK,
           ItemGroups.NATURAL
   );
   public static final Block DESERT_TURNIP_STEM = registerPlantBlockWithoutItem("desert_turnip_stem",
           new DesertPlantBlock((DesertTurnipBlock) DESERT_TURNIP_BLOCK, DESERT_TURNIP_ROOT_BLOCK, FabricBlockSettings.create()
                   .noCollision()
                   .breakInstantly()
                   .ticksRandomly()
                   .sounds(BlockSoundGroup.GRASS)
                   .pistonBehavior(PistonBehavior.DESTROY)),
           0.3f
   );
   public static final Item DESERT_TURNIP = registerItem("desert_turnip", new AliasedBlockItem(DESERT_TURNIP_STEM, (new Item.Settings()).food(DESERT_TURNIP_FOOD_COMPONENT)), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, Items.BEETROOT, ItemGroups.FOOD_AND_DRINK);
   public static final FoodComponent STANDARD_PIZZA_COMPONENT = (new FoodComponent.Builder()).hunger(2)
           .saturationModifier(0.2F)
           .build();
   public static final Block PIZZA_BLOCK = registerBlock("pizza_block",
           new PizzaBlock(FabricBlockSettings.copy(Blocks.CAKE))
   );
   public static final Item WHOLE_PIZZA = registerItem("whole_pizza",
           new PizzaItem(PIZZA_BLOCK, new Item.Settings().maxCount(1).food(STANDARD_PIZZA_COMPONENT)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           Items.BREAD,
           ItemGroups.FOOD_AND_DRINK
   );
   public static final Item THREE_QUARTERS_PIZZA = registerItem("three_quarters_pizza",
           new PizzaItem(PIZZA_BLOCK, new Item.Settings().maxCount(1).food(STANDARD_PIZZA_COMPONENT))
   );
   public static final Item HALF_PIZZA = registerItem("half_pizza",
           new PizzaItem(PIZZA_BLOCK, new Item.Settings().maxCount(1).food(STANDARD_PIZZA_COMPONENT))
   );
   public static final Item QUARTER_PIZZA = registerItem("quarter_pizza",
           new PizzaItem(PIZZA_BLOCK, new Item.Settings().maxCount(1).food(STANDARD_PIZZA_COMPONENT))
   );


   private static final Block[] Sign = getCherrySign();
   public static final Block[] REDWOOD = registerWoodBlocks("redwood",
           MapColor.RED,
           MapColor.TERRACOTTA_BROWN,
           Blocks.CHERRY_BUTTON,
           Blocks.CHERRY_LOG,
           Sign,
           REDWOOD_BLOCK_SET_TYPE,
           REDWOOD_WOOD_TYPE
   );
   public static final Block[] SUGI = registerWoodBlocks("sugi",
           MapColor.DIRT_BROWN,
           MapColor.DEEPSLATE_GRAY,
           REDWOOD[12],
           REDWOOD[2],
           REDWOOD,
           SUGI_BLOCK_SET_TYPE,
           SUGI_WOOD_TYPE
   );
   public static final Block FRAMED_SUGI_DOOR = registerSecondaryDoorBlock("framed_sugi_door",
           new DoorBlock(FabricBlockSettings.copy(SUGI[4]).nonOpaque(), SUGI_BLOCK_SET_TYPE),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           SUGI[8]
   );
   public static final Block FRAMED_SUGI_TRAPDOOR = registerSecondaryDoorBlock("framed_sugi_trapdoor",
           new TrapdoorBlock(FabricBlockSettings.create().burnable().instrument(Instrument.BASS).strength(3.0f).sounds(
                   BlockSoundGroup.WOOD).allowsSpawning(HibiscusBlocks::never).nonOpaque(),
                   SUGI_BLOCK_SET_TYPE
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           FRAMED_SUGI_DOOR
   );
   public static final Block[] WISTERIA = registerWoodBlocks("wisteria",
           MapColor.TERRACOTTA_WHITE,
           MapColor.GRAY,
           SUGI[12],
           SUGI[2],
           SUGI,
           WISTERIA_BLOCK_SET_TYPE,
           WISTERIA_WOOD_TYPE
   );
   public static final Block[] FIR = registerWoodBlocks("fir",
           MapColor.DIRT_BROWN,
           MapColor.GRAY,
           WISTERIA[12],
           WISTERIA[2],
           WISTERIA,
           FIR_BLOCK_SET_TYPE,
           FIR_WOOD_TYPE
   );
   public static final Block[] WILLOW = registerWoodBlocks("willow",
           MapColor.TERRACOTTA_BROWN,
           MapColor.TERRACOTTA_BLACK,
           FIR[12],
           FIR[2],
           FIR,
           WILLOW_BLOCK_SET_TYPE,
           WILLOW_WOOD_TYPE
   );
   public static final Block[] ASPEN = registerWoodBlocks("aspen",
           MapColor.PALE_YELLOW,
           MapColor.WHITE_GRAY,
           WILLOW[12],
           WILLOW[2],
           WILLOW,
           ASPEN_BLOCK_SET_TYPE,
           ASPEN_WOOD_TYPE
   );
   public static final Block[] MAPLE = registerWoodBlocks("maple",
           MapColor.ORANGE,
           MapColor.SPRUCE_BROWN,
           ASPEN[12],
           ASPEN[2],
           ASPEN,
           MAPLE_BLOCK_SET_TYPE,
           MAPLE_WOOD_TYPE
   );
   public static final Block[] CYPRESS = registerWoodBlocks("cypress",
           MapColor.OAK_TAN,
           MapColor.SPRUCE_BROWN,
           MAPLE[12],
           MAPLE[2],
           MAPLE,
           CYPRESS_BLOCK_SET_TYPE,
           CYPRESS_WOOD_TYPE
   );
   public static final Block[] OLIVE = registerWoodBlocks("olive",
           MapColor.PALE_GREEN,
           MapColor.PALE_YELLOW,
           CYPRESS[12],
           CYPRESS[2],
           CYPRESS,
           OLIVE_BLOCK_SET_TYPE,
           OLIVE_WOOD_TYPE
   );
   public static final Block[] JOSHUA = registerJoshuaWoodBlocks("joshua",
           MapColor.PALE_GREEN,
           MapColor.PALE_YELLOW,
           OLIVE[12],
           OLIVE[2],
           OLIVE,
           JOSHUA_BLOCK_SET_TYPE,
           JOSHUA_WOOD_TYPE
   );

   public static final Block SANDY_SOIL = registerBlock("sandy_soil",
           new Block(FabricBlockSettings.create()
                   .mapColor(MapColor.DIRT_BROWN)
                   .instrument(Instrument.BASEDRUM)
                   .strength(0.5F)
                   .sounds(BlockSoundGroup.GRAVEL)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           Blocks.FARMLAND,
           ItemGroups.NATURAL
   );

   public static final Block KAOLIN = registerBlock("kaolin",
           new Block(FabricBlockSettings.create()
                   .mapColor(MapColor.ORANGE)
                   .instrument(Instrument.BASEDRUM)
                   .requiresTool()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           Blocks.PINK_TERRACOTTA,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block WHITE_KAOLIN = registerBlock("white_kaolin",
           new Block(FabricBlockSettings.create()
                   .mapColor(MapColor.TERRACOTTA_WHITE)
                   .instrument(Instrument.BASEDRUM)
                   .requiresTool()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.KAOLIN,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block LIGHT_GRAY_KAOLIN = registerBlock("light_gray_kaolin",
           new Block(FabricBlockSettings.create()
                   .mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)
                   .instrument(Instrument.BASEDRUM)
                   .requiresTool()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.WHITE_KAOLIN,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block GRAY_KAOLIN = registerBlock("gray_kaolin",
           new Block(FabricBlockSettings.create()
                   .mapColor(MapColor.TERRACOTTA_GRAY)
                   .instrument(Instrument.BASEDRUM)
                   .requiresTool()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.LIGHT_GRAY_KAOLIN,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block BLACK_KAOLIN = registerBlock("black_kaolin",
           new Block(FabricBlockSettings.create()
                   .mapColor(MapColor.TERRACOTTA_BLACK)
                   .instrument(Instrument.BASEDRUM)
                   .requiresTool()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.GRAY_KAOLIN,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block BROWN_KAOLIN = registerBlock("brown_kaolin",
           new Block(FabricBlockSettings.create()
                   .mapColor(MapColor.TERRACOTTA_BROWN)
                   .instrument(Instrument.BASEDRUM)
                   .requiresTool()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.BLACK_KAOLIN,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block RED_KAOLIN = registerBlock("red_kaolin",
           new Block(FabricBlockSettings.create()
                   .mapColor(MapColor.TERRACOTTA_RED)
                   .instrument(Instrument.BASEDRUM)
                   .requiresTool()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.BROWN_KAOLIN,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block ORANGE_KAOLIN = registerBlock("orange_kaolin",
           new Block(FabricBlockSettings.create()
                   .mapColor(MapColor.TERRACOTTA_ORANGE)
                   .instrument(Instrument.BASEDRUM)
                   .requiresTool()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.RED_KAOLIN,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block YELLOW_KAOLIN = registerBlock("yellow_kaolin",
           new Block(FabricBlockSettings.create()
                   .mapColor(MapColor.TERRACOTTA_YELLOW)
                   .instrument(Instrument.BASEDRUM)
                   .requiresTool()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.ORANGE_KAOLIN,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block LIME_KAOLIN = registerBlock("lime_kaolin",
           new Block(FabricBlockSettings.create()
                   .mapColor(MapColor.TERRACOTTA_LIME)
                   .instrument(Instrument.BASEDRUM)
                   .requiresTool()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.YELLOW_KAOLIN,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block GREEN_KAOLIN = registerBlock("green_kaolin",
           new Block(FabricBlockSettings.create()
                   .mapColor(MapColor.TERRACOTTA_GREEN)
                   .instrument(Instrument.BASEDRUM)
                   .requiresTool()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.LIME_KAOLIN,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block CYAN_KAOLIN = registerBlock("cyan_kaolin",
           new Block(FabricBlockSettings.create()
                   .mapColor(MapColor.TERRACOTTA_CYAN)
                   .instrument(Instrument.BASEDRUM)
                   .requiresTool()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.GREEN_KAOLIN,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block LIGHT_BLUE_KAOLIN = registerBlock("light_blue_kaolin",
           new Block(FabricBlockSettings.create()
                   .mapColor(MapColor.TERRACOTTA_LIGHT_BLUE)
                   .instrument(Instrument.BASEDRUM)
                   .requiresTool()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.CYAN_KAOLIN,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block BLUE_KAOLIN = registerBlock("blue_kaolin",
           new Block(FabricBlockSettings.create()
                   .mapColor(MapColor.TERRACOTTA_BLUE)
                   .instrument(Instrument.BASEDRUM)
                   .requiresTool()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.LIGHT_BLUE_KAOLIN,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block PURPLE_KAOLIN = registerBlock("purple_kaolin",
           new Block(FabricBlockSettings.create()
                   .mapColor(MapColor.TERRACOTTA_PURPLE)
                   .instrument(Instrument.BASEDRUM)
                   .requiresTool()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.BLUE_KAOLIN,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block MAGENTA_KAOLIN = registerBlock("magenta_kaolin",
           new Block(FabricBlockSettings.create()
                   .mapColor(MapColor.TERRACOTTA_MAGENTA)
                   .instrument(Instrument.BASEDRUM)
                   .requiresTool()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.PURPLE_KAOLIN,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block PINK_KAOLIN = registerBlock("pink_kaolin",
           new Block(FabricBlockSettings.create()
                   .mapColor(MapColor.TERRACOTTA_PINK)
                   .instrument(Instrument.BASEDRUM)
                   .requiresTool()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.MAGENTA_KAOLIN,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block KAOLIN_STAIRS = registerBlock("kaolin_stairs",
           new StairsBlock(KAOLIN.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.ORANGE).instrument(
                   Instrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.PINK_KAOLIN,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block WHITE_KAOLIN_STAIRS = registerBlock("white_kaolin_stairs",
           new StairsBlock(WHITE_KAOLIN.getDefaultState(),
                   FabricBlockSettings.create()
                           .mapColor(MapColor.TERRACOTTA_WHITE)
                           .instrument(Instrument.BASEDRUM)
                           .requiresTool()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.KAOLIN_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block LIGHT_GRAY_KAOLIN_STAIRS = registerBlock("light_gray_kaolin_stairs",
           new StairsBlock(LIGHT_GRAY_KAOLIN.getDefaultState(),
                   FabricBlockSettings.create()
                           .mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)
                           .instrument(Instrument.BASEDRUM)
                           .requiresTool()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.WHITE_KAOLIN_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block GRAY_KAOLIN_STAIRS = registerBlock("gray_kaolin_stairs",
           new StairsBlock(GRAY_KAOLIN.getDefaultState(),
                   FabricBlockSettings.create()
                           .mapColor(MapColor.TERRACOTTA_GRAY)
                           .instrument(Instrument.BASEDRUM)
                           .requiresTool()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.LIGHT_GRAY_KAOLIN_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block BLACK_KAOLIN_STAIRS = registerBlock("black_kaolin_stairs",
           new StairsBlock(BLACK_KAOLIN.getDefaultState(),
                   FabricBlockSettings.create()
                           .mapColor(MapColor.TERRACOTTA_BLACK)
                           .instrument(Instrument.BASEDRUM)
                           .requiresTool()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.GRAY_KAOLIN_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block BROWN_KAOLIN_STAIRS = registerBlock("brown_kaolin_stairs",
           new StairsBlock(BROWN_KAOLIN.getDefaultState(),
                   FabricBlockSettings.create()
                           .mapColor(MapColor.TERRACOTTA_BROWN)
                           .instrument(Instrument.BASEDRUM)
                           .requiresTool()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.BLACK_KAOLIN_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block RED_KAOLIN_STAIRS = registerBlock("red_kaolin_stairs",
           new StairsBlock(RED_KAOLIN.getDefaultState(),
                   FabricBlockSettings.create()
                           .mapColor(MapColor.TERRACOTTA_RED)
                           .instrument(Instrument.BASEDRUM)
                           .requiresTool()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.BROWN_KAOLIN_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block ORANGE_KAOLIN_STAIRS = registerBlock("orange_kaolin_stairs",
           new StairsBlock(ORANGE_KAOLIN.getDefaultState(),
                   FabricBlockSettings.create()
                           .mapColor(MapColor.TERRACOTTA_ORANGE)
                           .instrument(Instrument.BASEDRUM)
                           .requiresTool()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.RED_KAOLIN_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block YELLOW_KAOLIN_STAIRS = registerBlock("yellow_kaolin_stairs",
           new StairsBlock(YELLOW_KAOLIN.getDefaultState(),
                   FabricBlockSettings.create()
                           .mapColor(MapColor.TERRACOTTA_YELLOW)
                           .instrument(Instrument.BASEDRUM)
                           .requiresTool()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.ORANGE_KAOLIN_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block LIME_KAOLIN_STAIRS = registerBlock("lime_kaolin_stairs",
           new StairsBlock(LIME_KAOLIN.getDefaultState(),
                   FabricBlockSettings.create()
                           .mapColor(MapColor.TERRACOTTA_LIME)
                           .instrument(Instrument.BASEDRUM)
                           .requiresTool()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.YELLOW_KAOLIN_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block GREEN_KAOLIN_STAIRS = registerBlock("green_kaolin_stairs",
           new StairsBlock(GREEN_KAOLIN.getDefaultState(),
                   FabricBlockSettings.create()
                           .mapColor(MapColor.TERRACOTTA_GREEN)
                           .instrument(Instrument.BASEDRUM)
                           .requiresTool()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.LIME_KAOLIN_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block CYAN_KAOLIN_STAIRS = registerBlock("cyan_kaolin_stairs",
           new StairsBlock(CYAN_KAOLIN.getDefaultState(),
                   FabricBlockSettings.create()
                           .mapColor(MapColor.TERRACOTTA_CYAN)
                           .instrument(Instrument.BASEDRUM)
                           .requiresTool()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.GREEN_KAOLIN_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block LIGHT_BLUE_KAOLIN_STAIRS = registerBlock("light_blue_kaolin_stairs",
           new StairsBlock(LIGHT_BLUE_KAOLIN.getDefaultState(),
                   FabricBlockSettings.create()
                           .mapColor(MapColor.TERRACOTTA_LIGHT_BLUE)
                           .instrument(Instrument.BASEDRUM)
                           .requiresTool()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.CYAN_KAOLIN_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block BLUE_KAOLIN_STAIRS = registerBlock("blue_kaolin_stairs",
           new StairsBlock(BLUE_KAOLIN.getDefaultState(),
                   FabricBlockSettings.create()
                           .mapColor(MapColor.TERRACOTTA_BLUE)
                           .instrument(Instrument.BASEDRUM)
                           .requiresTool()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.LIGHT_BLUE_KAOLIN_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block PURPLE_KAOLIN_STAIRS = registerBlock("purple_kaolin_stairs",
           new StairsBlock(PURPLE_KAOLIN.getDefaultState(),
                   FabricBlockSettings.create()
                           .mapColor(MapColor.TERRACOTTA_PURPLE)
                           .instrument(Instrument.BASEDRUM)
                           .requiresTool()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.BLUE_KAOLIN_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block MAGENTA_KAOLIN_STAIRS = registerBlock("magenta_kaolin_stairs",
           new StairsBlock(MAGENTA_KAOLIN.getDefaultState(),
                   FabricBlockSettings.create()
                           .mapColor(MapColor.TERRACOTTA_MAGENTA)
                           .instrument(Instrument.BASEDRUM)
                           .requiresTool()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.PURPLE_KAOLIN_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block PINK_KAOLIN_STAIRS = registerBlock("pink_kaolin_stairs",
           new StairsBlock(PINK_KAOLIN.getDefaultState(),
                   FabricBlockSettings.create()
                           .mapColor(MapColor.TERRACOTTA_PINK)
                           .instrument(Instrument.BASEDRUM)
                           .requiresTool()
                           .strength(1.25F, 4.2F)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.MAGENTA_KAOLIN_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block KAOLIN_SLAB = registerBlock("kaolin_slab",
           new SlabBlock(FabricBlockSettings.create()
                   .mapColor(MapColor.ORANGE)
                   .instrument(Instrument.BASEDRUM)
                   .requiresTool()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.PINK_KAOLIN_STAIRS,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block WHITE_KAOLIN_SLAB = registerBlock("white_kaolin_slab",
           new SlabBlock(FabricBlockSettings.create()
                   .mapColor(MapColor.TERRACOTTA_WHITE)
                   .instrument(Instrument.BASEDRUM)
                   .requiresTool()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.KAOLIN_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block LIGHT_GRAY_KAOLIN_SLAB = registerBlock("light_gray_kaolin_slab",
           new SlabBlock(FabricBlockSettings.create()
                   .mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)
                   .instrument(Instrument.BASEDRUM)
                   .requiresTool()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.WHITE_KAOLIN_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block GRAY_KAOLIN_SLAB = registerBlock("gray_kaolin_slab",
           new SlabBlock(FabricBlockSettings.create()
                   .mapColor(MapColor.TERRACOTTA_GRAY)
                   .instrument(Instrument.BASEDRUM)
                   .requiresTool()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.LIGHT_GRAY_KAOLIN_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block BLACK_KAOLIN_SLAB = registerBlock("black_kaolin_slab",
           new SlabBlock(FabricBlockSettings.create()
                   .mapColor(MapColor.TERRACOTTA_BLACK)
                   .instrument(Instrument.BASEDRUM)
                   .requiresTool()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.GRAY_KAOLIN_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block BROWN_KAOLIN_SLAB = registerBlock("brown_kaolin_slab",
           new SlabBlock(FabricBlockSettings.create()
                   .mapColor(MapColor.TERRACOTTA_BROWN)
                   .instrument(Instrument.BASEDRUM)
                   .requiresTool()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.BLACK_KAOLIN_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block RED_KAOLIN_SLAB = registerBlock("red_kaolin_slab",
           new SlabBlock(FabricBlockSettings.create()
                   .mapColor(MapColor.TERRACOTTA_RED)
                   .instrument(Instrument.BASEDRUM)
                   .requiresTool()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.BROWN_KAOLIN_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block ORANGE_KAOLIN_SLAB = registerBlock("orange_kaolin_slab",
           new SlabBlock(FabricBlockSettings.create()
                   .mapColor(MapColor.TERRACOTTA_ORANGE)
                   .instrument(Instrument.BASEDRUM)
                   .requiresTool()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.RED_KAOLIN_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block YELLOW_KAOLIN_SLAB = registerBlock("yellow_kaolin_slab",
           new SlabBlock(FabricBlockSettings.create()
                   .mapColor(MapColor.TERRACOTTA_YELLOW)
                   .instrument(Instrument.BASEDRUM)
                   .requiresTool()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.ORANGE_KAOLIN_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block LIME_KAOLIN_SLAB = registerBlock("lime_kaolin_slab",
           new SlabBlock(FabricBlockSettings.create()
                   .mapColor(MapColor.TERRACOTTA_LIME)
                   .instrument(Instrument.BASEDRUM)
                   .requiresTool()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.YELLOW_KAOLIN_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block GREEN_KAOLIN_SLAB = registerBlock("green_kaolin_slab",
           new SlabBlock(FabricBlockSettings.create()
                   .mapColor(MapColor.TERRACOTTA_GREEN)
                   .instrument(Instrument.BASEDRUM)
                   .requiresTool()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.LIME_KAOLIN_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block CYAN_KAOLIN_SLAB = registerBlock("cyan_kaolin_slab",
           new SlabBlock(FabricBlockSettings.create()
                   .mapColor(MapColor.TERRACOTTA_CYAN)
                   .instrument(Instrument.BASEDRUM)
                   .requiresTool()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.GREEN_KAOLIN_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block LIGHT_BLUE_KAOLIN_SLAB = registerBlock("light_blue_kaolin_slab",
           new SlabBlock(FabricBlockSettings.create()
                   .mapColor(MapColor.TERRACOTTA_LIGHT_BLUE)
                   .instrument(Instrument.BASEDRUM)
                   .requiresTool()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.CYAN_KAOLIN_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block BLUE_KAOLIN_SLAB = registerBlock("blue_kaolin_slab",
           new SlabBlock(FabricBlockSettings.create()
                   .mapColor(MapColor.TERRACOTTA_BLUE)
                   .instrument(Instrument.BASEDRUM)
                   .requiresTool()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.LIGHT_BLUE_KAOLIN_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block PURPLE_KAOLIN_SLAB = registerBlock("purple_kaolin_slab",
           new SlabBlock(FabricBlockSettings.create()
                   .mapColor(MapColor.TERRACOTTA_PURPLE)
                   .instrument(Instrument.BASEDRUM)
                   .requiresTool()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.BLUE_KAOLIN_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block MAGENTA_KAOLIN_SLAB = registerBlock("magenta_kaolin_slab",
           new SlabBlock(FabricBlockSettings.create()
                   .mapColor(MapColor.TERRACOTTA_MAGENTA)
                   .instrument(Instrument.BASEDRUM)
                   .requiresTool()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.PURPLE_KAOLIN_SLAB,
           ItemGroups.COLORED_BLOCKS
   );
   public static final Block PINK_KAOLIN_SLAB = registerBlock("pink_kaolin_slab",
           new SlabBlock(FabricBlockSettings.create()
                   .mapColor(MapColor.TERRACOTTA_PINK)
                   .instrument(Instrument.BASEDRUM)
                   .requiresTool()
                   .strength(1.25F, 4.2F)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocks.MAGENTA_KAOLIN_SLAB,
           ItemGroups.COLORED_BLOCKS
   );

   public HibiscusBlocks() {
   }

   private static Boolean never(BlockState state, BlockView world, BlockPos pos, EntityType <?> type) {
      return false;
   }

   private static Block[] getCherrySign() {
      Block[] Array = new Block[16];
      Array[13] = Blocks.CHERRY_SIGN;
      Array[15] = Blocks.CHERRY_HANGING_SIGN;
      return Array;
   }

   public static Block[] registerWoodBlocks(String name, MapColor topMaterialColor, MapColor sideMaterialColor, Block buttonPlacement, Block logPlacement, Block[] signPlacement, BlockSetType blockSetType, WoodType woodType) {
      Block[] Array = new Block[17];
      Array[0] = registerBlock(name + "_wood",
              new PillarBlock(FabricBlockSettings.create().burnable().instrument(Instrument.BASS).mapColor(
                      sideMaterialColor).strength(2.0F).sounds(BlockSoundGroup.WOOD)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[1] = registerBlock("stripped_" + name + "_wood",
              new PillarBlock(FabricBlockSettings.create().burnable().instrument(Instrument.BASS).mapColor(
                      topMaterialColor).strength(2.0F).sounds(BlockSoundGroup.WOOD)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[2] = registerBlock(name + "_log",
              new PillarBlock(FabricBlockSettings.create()
                      .burnable()
                      .instrument(Instrument.BASS)
                      .mapColor((state) -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMaterialColor : sideMaterialColor)
                      .strength(2.0F)
                      .sounds(BlockSoundGroup.WOOD)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[3] = registerBlock("stripped_" + name + "_log",
              new PillarBlock(FabricBlockSettings.create().burnable().instrument(Instrument.BASS).mapColor(
                      topMaterialColor).strength(2.0F).sounds(BlockSoundGroup.WOOD)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[4] = registerBlock(name + "_planks",
              new Block(FabricBlockSettings.create()
                      .burnable()
                      .instrument(Instrument.BASS)
                      .mapColor(topMaterialColor)
                      .strength(2.0F, 3.0F)
                      .sounds(BlockSoundGroup.WOOD)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[5] = registerBlock(name + "_stairs",
              new StairsBlock(Array[4].getDefaultState(), FabricBlockSettings.copy(Array[4])),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[6] = registerBlock(name + "_slab",
              new SlabBlock(FabricBlockSettings.create()
                      .instrument(Instrument.BASS)
                      .mapColor(topMaterialColor)
                      .strength(2.0f, 3.0f)
                      .sounds(BlockSoundGroup.WOOD)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[7] = registerBlock(name + "_door",
              new DoorBlock(FabricBlockSettings.copy(Array[4]).nonOpaque(), blockSetType),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[8] = registerBlock(name + "_trapdoor", new TrapdoorBlock(FabricBlockSettings.create()
              .mapColor(topMaterialColor)
              .burnable()
              .instrument(Instrument.BASS)
              .strength(3.0f)
              .sounds(BlockSoundGroup.WOOD)
              .allowsSpawning(HibiscusBlocks::never)
              .nonOpaque(), blockSetType), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP);
      Array[9] = registerBlock(name + "_fence",
              new FenceBlock(FabricBlockSettings.copy(Array[4]).nonOpaque().solid()),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[10] = registerBlock(name + "_fence_gate",
              new FenceGateBlock(FabricBlockSettings.copy(Array[4]).nonOpaque().solid(), woodType),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[11] = registerBlock(name + "_pressure_plate",
              new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,
                      FabricBlockSettings.create()
                              .solid()
                              .mapColor(topMaterialColor)
                              .noCollision()
                              .strength(0.5f)
                              .sounds(BlockSoundGroup.WOOD),
                      blockSetType
              ),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[12] = registerBlock(name + "_button",
              new ButtonBlock(FabricBlockSettings.create()
                      .mapColor(topMaterialColor)
                      .strength(0.5F)
                      .sounds(BlockSoundGroup.WOOD), blockSetType, 30, true),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[13] = registerBlock(name + "_sign", new SignBlock(FabricBlockSettings.create()
              .mapColor(topMaterialColor)
              .solid()
              .instrument(Instrument.BASS)
              .noCollision()
              .strength(1.0F)
              .burnable(), woodType));
      Array[14] = registerBlock(name + "_wall_sign", new WallSignBlock(FabricBlockSettings.create()
              .solid()
              .instrument(Instrument.BASS)
              .noCollision()
              .strength(1.0F)
              .burnable()
              .dropsLike(Array[13]), woodType));
      Array[15] = registerBlock(name + "_hanging_sign", new HangingSignBlock(FabricBlockSettings.create()
              .mapColor(topMaterialColor)
              .solid()
              .instrument(Instrument.BASS)
              .noCollision()
              .strength(1.0F)
              .burnable()
              .sounds(BlockSoundGroup.HANGING_SIGN), woodType));
      Array[16] = registerBlock(name + "_wall_hanging_sign", new WallHangingSignBlock(FabricBlockSettings.create()
              .mapColor(topMaterialColor)
              .solid()
              .instrument(Instrument.BASS)
              .noCollision()
              .strength(1.0F)
              .burnable()
              .sounds(BlockSoundGroup.HANGING_SIGN)
              .dropsLike(Array[15]), woodType));

      registerItem(name + "_sign",
              new SignItem(new FabricItemSettings().maxCount(16), Array[13], Array[14]),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      registerItem(name + "_hanging_sign",
              new HangingSignItem(Array[15], Array[16], new FabricItemSettings().maxCount(16)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );

      ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> entries.addAfter(
              buttonPlacement,
              Array[2],
              Array[0],
              Array[3],
              Array[1],
              Array[4],
              Array[5],
              Array[6],
              Array[9],
              Array[10],
              Array[7],
              Array[8],
              Array[11],
              Array[12]
      ));
      ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(logPlacement,
              Array[2]
      ));
      ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> entries.addAfter(signPlacement[15],
              Array[13].asItem(),
              Array[15].asItem()
      ));

      BlockRenderLayerMap.INSTANCE.putBlock(Array[7], RenderLayer.getCutout());
      BlockRenderLayerMap.INSTANCE.putBlock(Array[8], RenderLayer.getCutout());

      StrippableBlockRegistry.register(Array[0], Array[1]);
      StrippableBlockRegistry.register(Array[2], Array[3]);

      FlammableBlockRegistry.getDefaultInstance().add(Array[0], 5, 5);
      FlammableBlockRegistry.getDefaultInstance().add(Array[1], 5, 5);
      FlammableBlockRegistry.getDefaultInstance().add(Array[2], 5, 5);
      FlammableBlockRegistry.getDefaultInstance().add(Array[3], 5, 5);
      FlammableBlockRegistry.getDefaultInstance().add(Array[4], 5, 20);
      FlammableBlockRegistry.getDefaultInstance().add(Array[5], 5, 20);
      FlammableBlockRegistry.getDefaultInstance().add(Array[6], 5, 20);
      FlammableBlockRegistry.getDefaultInstance().add(Array[9], 5, 20);
      FlammableBlockRegistry.getDefaultInstance().add(Array[10], 5, 20);

      FuelRegistry.INSTANCE.add(Array[9], 300);
      FuelRegistry.INSTANCE.add(Array[10], 300);
      WoodHashMap.put(name, Array);


      return Array;
   }
   public static Block[] registerJoshuaWoodBlocks(String name, MapColor topMaterialColor, MapColor sideMaterialColor, Block buttonPlacement, Block logPlacement, Block[] signPlacement, BlockSetType blockSetType, WoodType woodType) {
      Block[] Array = new Block[15];

      Array[0] = registerBlock(name + "_log",
              new JoshuaTrunkBlock(FabricBlockSettings.create()
                      .burnable()
                      .mapColor(MapColor.GRAY)
                      .instrument(Instrument.BASS)
                      .requiresTool()
                      .strength(2.0F)
                      .sounds(BlockSoundGroup.WOOD)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[1] = registerBlock("stripped_" + name + "_log",
              new JoshuaTrunkBlock(FabricBlockSettings.create()
                      .burnable()
                      .mapColor(MapColor.GRAY)
                      .instrument(Instrument.BASS)
                      .requiresTool()
                      .strength(2.0F)
                      .sounds(BlockSoundGroup.WOOD)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[2] = registerBlock(name + "_planks",
              new Block(FabricBlockSettings.create()
                      .burnable()
                      .instrument(Instrument.BASS)
                      .mapColor(topMaterialColor)
                      .strength(2.0F, 3.0F)
                      .sounds(BlockSoundGroup.WOOD)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[3] = registerBlock(name + "_stairs",
              new StairsBlock(Array[2].getDefaultState(), FabricBlockSettings.copy(Array[2])),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[4] = registerBlock(name + "_slab",
              new SlabBlock(FabricBlockSettings.create()
                      .instrument(Instrument.BASS)
                      .mapColor(topMaterialColor)
                      .strength(2.0f, 3.0f)
                      .sounds(BlockSoundGroup.WOOD)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[5] = registerBlock(name + "_door",
              new DoorBlock(FabricBlockSettings.copy(Array[2]).nonOpaque(), blockSetType),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[6] = registerBlock(name + "_trapdoor", new TrapdoorBlock(FabricBlockSettings.create()
              .mapColor(topMaterialColor)
              .burnable()
              .instrument(Instrument.BASS)
              .strength(3.0f)
              .sounds(BlockSoundGroup.WOOD)
              .allowsSpawning(HibiscusBlocks::never)
              .nonOpaque(), blockSetType), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP);
      Array[7] = registerBlock(name + "_fence",
              new FenceBlock(FabricBlockSettings.copy(Array[2]).nonOpaque().solid()),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[8] = registerBlock(name + "_fence_gate",
              new FenceGateBlock(FabricBlockSettings.copy(Array[2]).nonOpaque().solid(), woodType),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[9] = registerBlock(name + "_pressure_plate",
              new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,
                      FabricBlockSettings.create()
                              .solid()
                              .mapColor(topMaterialColor)
                              .noCollision()
                              .strength(0.5f)
                              .sounds(BlockSoundGroup.WOOD),
                      blockSetType
              ),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[10] = registerBlock(name + "_button",
              new ButtonBlock(FabricBlockSettings.create()
                      .mapColor(topMaterialColor)
                      .strength(0.5F)
                      .sounds(BlockSoundGroup.WOOD), blockSetType, 30, true),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      Array[11] = registerBlock(name + "_sign", new SignBlock(FabricBlockSettings.create()
              .mapColor(topMaterialColor)
              .solid()
              .instrument(Instrument.BASS)
              .noCollision()
              .strength(1.0F)
              .burnable(), woodType));
      Array[12] = registerBlock(name + "_wall_sign", new WallSignBlock(FabricBlockSettings.create()
              .solid()
              .instrument(Instrument.BASS)
              .noCollision()
              .strength(1.0F)
              .burnable()
              .dropsLike(Array[11]), woodType));
      Array[13] = registerBlock(name + "_hanging_sign", new HangingSignBlock(FabricBlockSettings.create()
              .mapColor(topMaterialColor)
              .solid()
              .instrument(Instrument.BASS)
              .noCollision()
              .strength(1.0F)
              .burnable()
              .sounds(BlockSoundGroup.HANGING_SIGN), woodType));
      Array[14] = registerBlock(name + "_wall_hanging_sign", new WallHangingSignBlock(FabricBlockSettings.create()
              .mapColor(topMaterialColor)
              .solid()
              .instrument(Instrument.BASS)
              .noCollision()
              .strength(1.0F)
              .burnable()
              .sounds(BlockSoundGroup.HANGING_SIGN)
              .dropsLike(Array[13]), woodType));

      registerItem(name + "_sign",
              new SignItem(new FabricItemSettings().maxCount(16), Array[11], Array[12]),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      registerItem(name + "_hanging_sign",
              new HangingSignItem(Array[13], Array[14], new FabricItemSettings().maxCount(16)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );

      ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> entries.addAfter(
              buttonPlacement,
              Array[0],
              Array[1],
              Array[2],
              Array[3],
              Array[4],
              Array[7],
              Array[8],
              Array[5],
              Array[6],
              Array[9],
              Array[10]
      ));
      ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(logPlacement,
              Array[2]
      ));
      ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> entries.addAfter(signPlacement[15],
              Array[11].asItem(),
              Array[13].asItem()
      ));

      BlockRenderLayerMap.INSTANCE.putBlock(Array[5], RenderLayer.getCutout());
      BlockRenderLayerMap.INSTANCE.putBlock(Array[6], RenderLayer.getCutout());

      FlammableBlockRegistry.getDefaultInstance().add(Array[0], 5, 5);
      FlammableBlockRegistry.getDefaultInstance().add(Array[1], 5, 5);
      FlammableBlockRegistry.getDefaultInstance().add(Array[3], 5, 20);
      FlammableBlockRegistry.getDefaultInstance().add(Array[4], 5, 20);
      FlammableBlockRegistry.getDefaultInstance().add(Array[5], 5, 20);
      FlammableBlockRegistry.getDefaultInstance().add(Array[7], 5, 20);
      FlammableBlockRegistry.getDefaultInstance().add(Array[8], 5, 20);

      FuelRegistry.INSTANCE.add(Array[7], 300);
      FuelRegistry.INSTANCE.add(Array[8], 300);

      //        List <Block[]> woodArray = new ArrayList <>(Arrays.stream(NatureSpiritDataGen.woodArrays.clone()).toList());
      //        woodArray.add(Array);
      //        NatureSpiritDataGen.woodArrays = (Block[][]) woodArray.toArray();

      //        List <TagKey<Block>> blockLogsArray = new ArrayList <> (Arrays.stream(NatureSpiritDataGen.blockLogTags
      //        .clone()).toList());
      //        blockLogsArray.add(TagKey.of(RegistryKeys.BLOCK, new Identifier(NatureSpirit.MOD_ID, name + "_logs")));
      //        NatureSpiritDataGen.blockLogTags = (TagKey<Block>[]) blockLogsArray.toArray();
      //
      //        List <TagKey<Item>> itemLogsArray = new ArrayList <> (Arrays.stream(NatureSpiritDataGen.itemLogTags.clone()
      //        ).toList());
      //        itemLogsArray.add(TagKey.of(RegistryKeys.ITEM, new Identifier(NatureSpirit.MOD_ID, name + "_logs")));
      //        NatureSpiritDataGen.itemLogTags = (TagKey<Item>[]) itemLogsArray.toArray();


      return Array;
   }


   private static Boolean canSpawnUponLeaves(BlockState state, BlockView world, BlockPos pos, EntityType <?> type) {
      return type == EntityType.OCELOT || type == EntityType.PARROT;
   }

   private static boolean never(BlockState state, BlockView world, BlockPos pos) {
      return false;
   }

   public static Block registerLeafBlock(String name, MapColor color, Block blockBefore) {
      Block Leaves = registerBlock(name + "_leaves", new LeavesBlock(FabricBlockSettings.create()
              .strength(0.2F)
              .ticksRandomly()
              .nonOpaque()
              .sounds(BlockSoundGroup.AZALEA_LEAVES)
              .mapColor(color)
              .burnable()
              .allowsSpawning(HibiscusBlocks::canSpawnUponLeaves)
              .suffocates(HibiscusBlocks::never)
              .blockVision(HibiscusBlocks::never)
              .pistonBehavior(PistonBehavior.DESTROY)
              .solidBlock(HibiscusBlocks::never)), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP);
      BlockRenderLayerMap.INSTANCE.putBlock(Leaves, RenderLayer.getCutout());
      FlammableBlockRegistry.getDefaultInstance().add(Leaves, 5, 20);
      CompostingChanceRegistry.INSTANCE.add(Leaves, 0.3F);
      ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(blockBefore,
              Leaves.asItem()
      ));
      LeavesHashMap.put(name, Leaves);
      return Leaves;
   }

   public static Block registerLeafBlock(String name, MapColor color, Block blockBefore, boolean bl) {
      Block Leaves;
      if(bl) {
         Leaves = registerBlock(name + "_leaves",
                 new WisteriaLeaves(FabricBlockSettings.create()
                         .mapColor(color)
                         .strength(0.2F)
                         .ticksRandomly()
                         .sounds(BlockSoundGroup.GRASS)
                         .nonOpaque()
                         .allowsSpawning(HibiscusBlocks::canSpawnUponLeaves)
                         .suffocates(HibiscusBlocks::never)
                         .blockVision(HibiscusBlocks::never)
                         .burnable()
                         .pistonBehavior(PistonBehavior.DESTROY)
                         .solidBlock(HibiscusBlocks::never)),
                 HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
         );
      }
      else {
         Leaves = registerBlock(name + "_leaves",
                 new WillowLeaves(FabricBlockSettings.create()
                         .mapColor(color)
                         .strength(0.2F)
                         .ticksRandomly()
                         .sounds(BlockSoundGroup.GRASS)
                         .nonOpaque()
                         .allowsSpawning(HibiscusBlocks::canSpawnUponLeaves)
                         .suffocates(HibiscusBlocks::never)
                         .blockVision(HibiscusBlocks::never)
                         .burnable()
                         .pistonBehavior(PistonBehavior.DESTROY)
                         .solidBlock(HibiscusBlocks::never)),
                 HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
         );
      }
      BlockRenderLayerMap.INSTANCE.putBlock(Leaves, RenderLayer.getCutout());
      FlammableBlockRegistry.getDefaultInstance().add(Leaves, 5, 20);
      CompostingChanceRegistry.INSTANCE.add(Leaves, 0.3F);
      ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(blockBefore,
              Leaves.asItem()
      ));
      LeavesHashMap.put(name, Leaves);
      return Leaves;
   }

   public static Block[] registerSapling(String name, SaplingGenerator saplingGenerator, Block blockBefore) {
      Block[] Plant = new Block[2];
      Plant[0] = registerBlock(name + "_sapling",
              new SaplingBlock(saplingGenerator, FabricBlockSettings.copy(Blocks.SPRUCE_SAPLING)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      BlockRenderLayerMap.INSTANCE.putBlock(Plant[0], RenderLayer.getCutout());
      Plant[1] = registerPottedPlant(name + "_sapling", Plant[0]);
      CompostingChanceRegistry.INSTANCE.add(Plant[0], 0.3F);
      ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(blockBefore,
              Plant[0].asItem()
      ));
      SaplingHashmap.put(name, Plant);
      return Plant;
   }

   public static Block[] registerJoshuaSapling(String name, SaplingGenerator saplingGenerator, Block blockBefore) {
      Block[] Plant = new Block[2];
      Plant[0] = registerBlock(name + "_sapling",
              new JoshuaSapling(saplingGenerator, FabricBlockSettings.copy(Blocks.SPRUCE_SAPLING)),
              HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
      );
      BlockRenderLayerMap.INSTANCE.putBlock(Plant[0], RenderLayer.getCutout());
      Plant[1] = registerPottedPlant(name + "_sapling", Plant[0]);
      CompostingChanceRegistry.INSTANCE.add(Plant[0], 0.3F);
      ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(blockBefore,
              Plant[0].asItem()
      ));
      SaplingHashmap.put(name, Plant);
      return Plant;
   }

   public static Block registerPottedPlant(String name, Block plant) {
      Block pottedPlant = registerBlock("potted_" + name, new FlowerPotBlock(plant,
              FabricBlockSettings.create().breakInstantly().nonOpaque().pistonBehavior(PistonBehavior.DESTROY)
      ));
      BlockRenderLayerMap.INSTANCE.putBlock(pottedPlant, RenderLayer.getCutout());
      return pottedPlant;
   }

   //    public static Block registerPorcelainPottedPlant(String name, Block plant, VoxelShape shape, @Nullable Block
   //    type) {
   //        Block pottedPlant = registerBlock(name, new PorcelainPotBlock(plant, shape, type, FabricBlockSettings
   //        .create().breakInstantly().nonOpaque()));
   //        BlockRenderLayerMap.INSTANCE.putBlock(pottedPlant, RenderLayer.getCutout());
   //        if (plant == Blocks.AIR) {
   //            registerBlockItem(name, pottedPlant, HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, Blocks.FLOWER_POT,
   //            ItemGroups.FUNCTIONAL);
   //        }
   //        return pottedPlant;
   //    }
   //    public static Block registerDoubleTallPorcelainPottedPlant(String name, Block plant, VoxelShape shape,
   //    @Nullable Block type) {
   //        Block pottedPlant = registerBlock(name, new DoubleTallPorcelainPotBlock(plant, shape, type,
   //        FabricBlockSettings.create().breakInstantly().nonOpaque()));
   //        BlockRenderLayerMap.INSTANCE.putBlock(pottedPlant, RenderLayer.getCutout());
   //        if (plant == Blocks.AIR) {
   //            registerBlockItem(name, pottedPlant, HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, Blocks.FLOWER_POT,
   //            ItemGroups.FUNCTIONAL);
   //        }
   //        return pottedPlant;
   //    }

   public static Block registerBlock(String name, Block block) {
      return Registry.register(Registries.BLOCK, new Identifier(NatureSpirit.MOD_ID, name), block);
   }

   public static Block registerBlock(String name, Block block, RegistryKey <ItemGroup> tab) {
      registerBlockItem(name, block, tab);
      return registerBlock(name, block);
   }

   public static Block registerBlock(String name, Block block, RegistryKey <ItemGroup> tab, Block blockBefore, RegistryKey <ItemGroup> secondaryTab) {
      Block block1 = registerBlock(name, block, tab);
      ItemGroupEvents.modifyEntriesEvent(secondaryTab).register(entries -> entries.addAfter(blockBefore,
              block1.asItem()
      ));
      return block1;
   }

   public static Block registerSecondaryDoorBlock(String name, Block block, RegistryKey <ItemGroup> tab, Block blockBefore) {
      Block block1 = Registry.register(Registries.BLOCK, new Identifier(NatureSpirit.MOD_ID, name), block);
      BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());
      registerBlockItem(name, block, tab, blockBefore, ItemGroups.BUILDING_BLOCKS);
      return block1;
   }

   public static Block registerPlantBlock(String name, Block block) {
      Block Plant = registerBlock(name, block);
      BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());
      return Plant;
   }

   public static Block registerPlantBlock(String name, Block block, RegistryKey <ItemGroup> tab, Block blockBefore, float compost) {
      Block Plant = registerBlock(name, block, tab, blockBefore, ItemGroups.NATURAL);
      BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());
      CompostingChanceRegistry.INSTANCE.add(block, compost);
      return Plant;
   }

   public static Block registerPlantBlockWithoutItem(String name, Block block, float compost) {
      Block Plant = registerBlock(name, block);
      BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());
      CompostingChanceRegistry.INSTANCE.add(block.asItem(), compost);
      return Plant;
   }

   public static Item registerBlockItem(String name, Block block, RegistryKey <ItemGroup> tab) {
      return registerItem(name, new BlockItem(block, new FabricItemSettings()), tab);
   }

   public static Item registerBlockItem(String name, Block block, RegistryKey <ItemGroup> tab, Block blockBefore, RegistryKey <ItemGroup> secondaryTab) {
      Item item = registerItem(name, new BlockItem(block, new FabricItemSettings()));
      ItemGroupEvents.modifyEntriesEvent(secondaryTab).register(entries -> entries.addAfter(blockBefore,
              item.asItem()
      ));
      ItemGroupEvents.modifyEntriesEvent(tab).register(entries -> entries.addAfter(blockBefore, item.asItem()));
      return item;
   }

   public static Item registerItem(String name, Item item) {
      return Registry.register(Registries.ITEM, new Identifier(NatureSpirit.MOD_ID, name), item);
   }

   public static Item registerItem(String name, Item item, RegistryKey <ItemGroup> tab) {
      ItemGroupEvents.modifyEntriesEvent(tab).register(entries -> entries.add(item.asItem()));
      return registerItem(name, item);
   }

   public static Item registerItem(String name, Item item, RegistryKey <ItemGroup> tab, Item itemBefore, RegistryKey <ItemGroup> secondaryTab) {
      ItemGroupEvents.modifyEntriesEvent(secondaryTab).register(entries -> entries.addAfter(itemBefore, item.asItem()));
      return registerItem(name, item, tab);
   }

   public static Item registerPlantItem(String name, Item item, RegistryKey <ItemGroup> tab, Item itemBefore, RegistryKey <ItemGroup> secondaryTab, float compost) {
      CompostingChanceRegistry.INSTANCE.add(item, compost);
      return registerItem(name, item, tab, itemBefore, secondaryTab);
   }

   public static void registerHibiscusBlocks() {
      NatureSpirit.LOGGER.debug("Registering ModBlocks for " + NatureSpirit.MOD_ID);
   }
}
