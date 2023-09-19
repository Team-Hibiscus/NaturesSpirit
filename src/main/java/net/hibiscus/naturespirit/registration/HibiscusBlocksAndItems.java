package net.hibiscus.naturespirit.registration;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.blocks.*;
import net.hibiscus.naturespirit.blocks.block_entities.PizzaBlockEntity;
import net.hibiscus.naturespirit.datagen.HibiscusConfiguredFeatures;
import net.hibiscus.naturespirit.items.PizzaItem;
import net.hibiscus.naturespirit.items.VinegarItem;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusColoredBlocks;
import net.hibiscus.naturespirit.util.HibiscusRegistryHelper;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import static net.hibiscus.naturespirit.util.HibiscusRegistryHelper.*;

public class HibiscusBlocksAndItems {
   public static final Block SANDY_SOIL = registerBlock("sandy_soil",
           new Block(FabricBlockSettings.create().mapColor(MapColor.DIRT_BROWN).instrument(Instrument.BASEDRUM).strength(0.5F).sounds(BlockSoundGroup.GRAVEL)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           Blocks.FARMLAND,
           ItemGroups.NATURAL
   );

   public static final Block PINK_SAND = registerBlock("pink_sand",
           new SandBlock(14331784, FabricBlockSettings.create().mapColor(MapColor.RAW_IRON_PINK).instrument(Instrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
   );

   public static final Block PINK_SANDSTONE = registerBlock("pink_sandstone",
           new Block(FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).instrument(Instrument.BASEDRUM).requiresTool().strength(0.8F)), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
   );

   public static final Block CHISELED_PINK_SANDSTONE = registerBlock("chiseled_pink_sandstone", new Block(FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).instrument(
           Instrument.BASEDRUM).requiresTool().strength(0.8F)), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP);

   public static final Block CUT_PINK_SANDSTONE = registerBlock("cut_pink_sandstone",
           new Block(FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).instrument(Instrument.BASEDRUM).requiresTool().strength(0.8F)), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
   );

   public static final Block SMOOTH_PINK_SANDSTONE = registerBlock("smooth_pink_sandstone", new Block(FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).instrument(
           Instrument.BASEDRUM).requiresTool().strength(2.0F, 6.0F)), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP);

   public static final Block PINK_SANDSTONE_STAIRS = registerBlock(
           "pink_sandstone_stairs",
           new StairsBlock(PINK_SANDSTONE.getDefaultState(), FabricBlockSettings.copy(PINK_SANDSTONE)), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
   );

   public static final Block SMOOTH_PINK_SANDSTONE_STAIRS = registerBlock("smooth_pink_sandstone_stairs",
           new StairsBlock(SMOOTH_PINK_SANDSTONE.getDefaultState(), FabricBlockSettings.copy(SMOOTH_PINK_SANDSTONE)), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
   );

   public static final Block PINK_SANDSTONE_SLAB = registerBlock("pink_sandstone_slab", new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).instrument(
           Instrument.BASEDRUM).requiresTool().strength(2.0F, 6.0F)), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP);

   public static final Block CUT_PINK_SANDSTONE_SLAB = registerBlock("cut_pink_sandstone_slab",
           new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).instrument(Instrument.BASEDRUM).requiresTool().strength(2.0F, 6.0F)), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP
   );

   public static final Block SMOOTH_PINK_SANDSTONE_SLAB = registerBlock("smooth_pink_sandstone_slab", new SlabBlock(FabricBlockSettings.copy(SMOOTH_PINK_SANDSTONE)), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP);

   public static final Block PINK_SANDSTONE_WALL = registerBlock("pink_sandstone_wall", new WallBlock(FabricBlockSettings.copy(PINK_SANDSTONE).solid()), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP);

   public static final Block TALL_FRIGID_GRASS = registerPlantBlock("tall_frigid_grass",
           new TallPlantBlock(FabricBlockSettings
                   .create()
                   .mapColor(MapColor.LICHEN_GREEN)
                   .noCollision()
                   .replaceable()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XZ)
                   .pistonBehavior(PistonBehavior.DESTROY)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           Blocks.LARGE_FERN,
           0.3f
   );

   public static final Block FRIGID_GRASS = registerPlantBlock("frigid_grass",
           new HibiscusFernBlock(FabricBlockSettings
                   .create()
                   .mapColor(MapColor.LICHEN_GREEN)
                   .noCollision()
                   .replaceable()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XYZ)
                   .pistonBehavior(PistonBehavior.DESTROY), (TallPlantBlock) TALL_FRIGID_GRASS),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           Blocks.FERN,
           0.3f
   );

   public static final Block TALL_SCORCHED_GRASS = registerPlantBlock("tall_scorched_grass",
           new TallLargeDesertFernBlock(FabricBlockSettings
                   .create()
                   .mapColor(MapColor.LICHEN_GREEN)
                   .noCollision()
                   .replaceable()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XZ)
                   .pistonBehavior(PistonBehavior.DESTROY)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           Blocks.LARGE_FERN,
           0.3f
   );

   public static final Block SCORCHED_GRASS = registerPlantBlock("scorched_grass",
           new LargeDesertFernBlock(FabricBlockSettings
                   .create()
                   .mapColor(MapColor.LICHEN_GREEN)
                   .noCollision()
                   .replaceable()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XYZ)
                   .pistonBehavior(PistonBehavior.DESTROY)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           Blocks.FERN,
           0.3f
   );

   public static final Block TALL_SEDGE_GRASS = registerPlantBlock("tall_sedge_grass",
           new TallSedgeGrassBlock(FabricBlockSettings
                   .create()
                   .mapColor(MapColor.DARK_GREEN)
                   .noCollision()
                   .replaceable()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XZ)
                   .pistonBehavior(PistonBehavior.DESTROY)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           TALL_SCORCHED_GRASS,
           0.3f
   );

   public static final Block SEDGE_GRASS = registerPlantBlock("sedge_grass",
           new SedgeGrassBlock(FabricBlockSettings
                   .create()
                   .mapColor(MapColor.DARK_GREEN)
                   .noCollision()
                   .replaceable()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XYZ)
                   .pistonBehavior(PistonBehavior.DESTROY)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           SCORCHED_GRASS,
           0.3f
   );

   public static final Block LARGE_FLAXEN_FERN = registerPlantBlock("large_flaxen_fern",
           new TallPlantBlock(FabricBlockSettings
                   .create()
                   .mapColor(MapColor.GOLD)
                   .noCollision()
                   .replaceable()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XZ)
                   .pistonBehavior(PistonBehavior.DESTROY)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           TALL_SCORCHED_GRASS,
           0.3f
   );

   public static final Block FLAXEN_FERN = registerPlantBlock("flaxen_fern",
           new HibiscusFernBlock(FabricBlockSettings
                   .create()
                   .mapColor(MapColor.GOLD)
                   .noCollision()
                   .replaceable()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XYZ)
                   .pistonBehavior(PistonBehavior.DESTROY), (TallPlantBlock) LARGE_FLAXEN_FERN),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           SCORCHED_GRASS,
           0.3f
   );

   public static final Block POTTED_FLAXEN_FERN = registerPottedPlant("flaxen_fern", FLAXEN_FERN);

   public static final Block POTTED_FRIGID_GRASS = registerPottedPlant("frigid_grass", FRIGID_GRASS);

   public static final Block SHIITAKE_MUSHROOM = registerPlantBlock("shiitake_mushroom", new ShiitakeMushroomPlantBlock(
           AbstractBlock.Settings
                   .create()
                   .mapColor(MapColor.BROWN)
                   .noCollision()
                   .ticksRandomly()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .luminance((state) -> 1)
                   .postProcess(Blocks::always)
                   .pistonBehavior(PistonBehavior.DESTROY),
           HibiscusConfiguredFeatures.HUGE_SHIITAKE_MUSHROOM
   ), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, Blocks.RED_MUSHROOM, 0.1F);

   public static final Block SHIITAKE_MUSHROOM_BLOCK = registerBlock("shiitake_mushroom_block",
           new MushroomBlock(FabricBlockSettings.create().mapColor(MapColor.DIRT_BROWN).instrument(Instrument.BASS).strength(0.2F).sounds(BlockSoundGroup.WOOD).burnable()),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           Blocks.RED_MUSHROOM_BLOCK,
           ItemGroups.NATURAL
   );

   public static final Block LAVENDER = registerPlantBlock("lavender",
           new LargeTallFlowerBlock(FabricBlockSettings
                   .create()
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
           new LargeTallFlowerBlock(FabricBlockSettings
                   .create()
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
           new TallFlowerBlock(FabricBlockSettings
                   .create()
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
           new TallFlowerBlock(FabricBlockSettings
                   .create()
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
           new TallFlowerBlock(FabricBlockSettings
                   .create()
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
           new TallFlowerBlock(FabricBlockSettings
                   .create()
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
           new TallFlowerBlock(FabricBlockSettings
                   .create()
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
           new Cattails(FabricBlockSettings
                   .create()
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

   public static final Block BLUEBELL = registerPlantBlock("bluebell", new LargeFlowerBlock(StatusEffects.HASTE,
           7,
           FabricBlockSettings
                   .create()
                   .mapColor(MapColor.DARK_GREEN)
                   .noCollision()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XZ)
                   .pistonBehavior(PistonBehavior.DESTROY)
   ), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, Blocks.LILY_OF_THE_VALLEY, 0.4f);

   public static final Block TIGER_LILY = registerPlantBlock("tiger_lily", new LargeFlowerBlock(StatusEffects.FIRE_RESISTANCE,
           7,
           FabricBlockSettings
                   .create()
                   .mapColor(MapColor.DARK_GREEN)
                   .noCollision()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XZ)
                   .pistonBehavior(PistonBehavior.DESTROY)
   ), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, BLUEBELL, 0.4f);

   public static final Block PURPLE_WILDFLOWER = registerPlantBlock("purple_wildflower", new LargeFlowerBlock(StatusEffects.SLOW_FALLING,
           7,
           FabricBlockSettings
                   .create()
                   .mapColor(MapColor.DARK_GREEN)
                   .noCollision()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XZ)
                   .pistonBehavior(PistonBehavior.DESTROY)
   ), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, TIGER_LILY, 0.4f);

   public static final Block YELLOW_WILDFLOWER = registerPlantBlock("yellow_wildflower", new LargeFlowerBlock(StatusEffects.SLOW_FALLING,
           7,
           FabricBlockSettings
                   .create()
                   .mapColor(MapColor.DARK_GREEN)
                   .noCollision()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XZ)
                   .pistonBehavior(PistonBehavior.DESTROY)
   ), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, PURPLE_WILDFLOWER, 0.4f);

   public static final Block ANEMONE = registerPlantBlock("anemone", new MidFlowerBlock(StatusEffects.RESISTANCE,
           4,
           FabricBlockSettings
                   .create()
                   .mapColor(MapColor.DARK_GREEN)
                   .noCollision()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XZ)
                   .pistonBehavior(PistonBehavior.DESTROY)
   ), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, TIGER_LILY, 0.4f);

   public static final Block HIBISCUS = registerPlantBlock("hibiscus", new FlowerBlock(StatusEffects.LUCK,
           7,
           FabricBlockSettings
                   .create()
                   .mapColor(MapColor.DARK_GREEN)
                   .noCollision()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.GRASS)
                   .offset(AbstractBlock.OffsetType.XZ)
                   .pistonBehavior(PistonBehavior.DESTROY)
   ), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, ANEMONE, 0.3f);

   public static final Block POTTED_HIBISCUS = registerPottedPlant("hibiscus", HIBISCUS);

   public static final Block POTTED_ANEMONE = registerPottedPlant("anemone", ANEMONE);


   public static final Block LOTUS_FLOWER = HibiscusRegistryHelper.registerPlantBlock("lotus_flower",
           new LotusFlowerBlock(FabricBlockSettings
                   .create()
                   .mapColor(MapColor.PINK)
                   .pistonBehavior(PistonBehavior.DESTROY)
                   .ticksRandomly()
                   .nonOpaque()
                   .breakInstantly()
                   .sounds(BlockSoundGroup.LILY_PAD))
   );

   public static final Item LOTUS_FLOWER_ITEM = registerItem("lotus_flower",
           new PlaceableOnWaterItem(LOTUS_FLOWER, new Item.Settings()),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           Items.LILY_PAD,
           ItemGroups.NATURAL
   );

   public static final Block LOTUS_STEM = registerPlantBlock("lotus_stem", new LotusStem(FabricBlockSettings
           .create()
           .mapColor(MapColor.GREEN)
           .pistonBehavior(PistonBehavior.DESTROY)
           .noCollision()
           .nonOpaque()
           .breakInstantly()
           .sounds(BlockSoundGroup.LILY_PAD), LOTUS_FLOWER), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, LOTUS_FLOWER, 0.2F);

   public static final Block POTTED_SHIITAKE_MUSHROOM = registerPottedPlant("shiitake_mushroom", SHIITAKE_MUSHROOM);

   public static final FoodComponent GREEN_OLIVE_COMPONENT = (new FoodComponent.Builder()).hunger(2).saturationModifier(0.4F).build();

   public static final FoodComponent BLACK_OLIVE_COMPONENT = (new FoodComponent.Builder()).hunger(3).saturationModifier(0.5F).build();

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
           new PillarBlock(FabricBlockSettings.create().burnable().instrument(Instrument.BASS).mapColor(MapColor.SPRUCE_BROWN).strength(2.0F).sounds(BlockSoundGroup.ROOTS)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           Blocks.SHROOMLIGHT,
           ItemGroups.NATURAL
   );

   public static final Block DESERT_TURNIP_BLOCK = registerBlock("desert_turnip_block",
           new DesertTurnipBlock(FabricBlockSettings.create().burnable().instrument(Instrument.BASS).mapColor(MapColor.PALE_PURPLE).strength(2.0F).sounds(BlockSoundGroup.ROOTS)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocksAndItems.DESERT_TURNIP_ROOT_BLOCK,
           ItemGroups.NATURAL
   );

   public static final Block DESERT_TURNIP_STEM = HibiscusRegistryHelper.registerPlantBlock("desert_turnip_stem", new DesertPlantBlock((DesertTurnipBlock) DESERT_TURNIP_BLOCK,
           DESERT_TURNIP_ROOT_BLOCK,
           FabricBlockSettings.create().noCollision().breakInstantly().ticksRandomly().sounds(BlockSoundGroup.GRASS).pistonBehavior(PistonBehavior.DESTROY)
   ));

   public static final FoodComponent DESERT_TURNIP_FOOD_COMPONENT = (new FoodComponent.Builder()).hunger(4).saturationModifier(0.6F).build();

   public static final Item DESERT_TURNIP = registerItem("desert_turnip",
           new AliasedBlockItem(DESERT_TURNIP_STEM, (new Item.Settings()).food(DESERT_TURNIP_FOOD_COMPONENT)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           Items.BEETROOT,
           ItemGroups.FOOD_AND_DRINK
   );

   public static final Item CHALK_POWDER = registerItem(
           "chalk_powder",
           new Item((new Item.Settings())),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           Items.HONEYCOMB,
           ItemGroups.INGREDIENTS
   );

   public static final Item VINEGAR_BOTTLE = registerItem("vinegar_bottle",
           new VinegarItem((new Item.Settings()).recipeRemainder(Items.GLASS_BOTTLE).maxCount(16)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           CHALK_POWDER,
           ItemGroups.INGREDIENTS
   );

   public static final Block CHEESE_BLOCK = registerBlock("cheese_block",
           new CheeseBlock(AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW).strength(2.0F, 1.0F).sounds(BlockSoundGroup.AZALEA_LEAVES))
   );

   public static final Item CHEESE_BUCKET = registerItem("cheese_bucket",
           new PowderSnowBucketItem(CHEESE_BLOCK, SoundEvents.ITEM_BUCKET_EMPTY, (new Item.Settings()).maxCount(1).recipeRemainder(Items.BUCKET)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           Items.MILK_BUCKET,
           ItemGroups.FOOD_AND_DRINK
   );

   public static final Block MILK_CAULDRON = registerBlock("milk_cauldron", new MilkCauldronBlock(AbstractBlock.Settings.copy(Blocks.CAULDRON).dropsLike(Blocks.CAULDRON)));

   public static final Block CHEESE_CAULDRON = registerBlock("cheese_cauldron", new CheeseCauldronBlock(AbstractBlock.Settings.copy(Blocks.CAULDRON).dropsLike(Blocks.CAULDRON)));

   public static final FoodComponent STANDARD_PIZZA_COMPONENT = (new FoodComponent.Builder()).hunger(2).saturationModifier(0.2F).build();

   public static final Block PIZZA_BLOCK = registerBlock("pizza_block", new PizzaBlock(FabricBlockSettings.copy(Blocks.CAKE)));
   public static final BlockEntityType <PizzaBlockEntity> PIZZA_BLOCK_ENTITY_TYPE = HibiscusRegistryHelper.registerBlockEntity("pizza_block_entity", FabricBlockEntityTypeBuilder.create(PizzaBlockEntity::new, PIZZA_BLOCK));

   public static final Item WHOLE_PIZZA = registerItem("whole_pizza",
           new PizzaItem(PIZZA_BLOCK, new Item.Settings().maxCount(1).food(STANDARD_PIZZA_COMPONENT)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           Items.BREAD,
           ItemGroups.FOOD_AND_DRINK
   );

   public static final Item THREE_QUARTERS_PIZZA = registerItem("three_quarters_pizza", new PizzaItem(PIZZA_BLOCK, new Item.Settings().maxCount(1).food(STANDARD_PIZZA_COMPONENT)));

   public static final Item HALF_PIZZA = registerItem("half_pizza", new PizzaItem(PIZZA_BLOCK, new Item.Settings().maxCount(1).food(STANDARD_PIZZA_COMPONENT)));

   public static final Item QUARTER_PIZZA = registerItem("quarter_pizza", new PizzaItem(PIZZA_BLOCK, new Item.Settings().maxCount(1).food(STANDARD_PIZZA_COMPONENT)));

   public static final Item CALCITE_SHARD = registerItem(
           "calcite_shard",
           new Item(new Item.Settings()),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           Items.AMETHYST_SHARD,
           ItemGroups.INGREDIENTS
   );

   public static final Block CALCITE_CLUSTER = registerBlock("calcite_cluster", new AmethystClusterBlock(7,
           3,
           FabricBlockSettings
                   .create()
                   .mapColor(MapColor.WHITE)
                   .solid()
                   .nonOpaque()
                   .ticksRandomly()
                   .sounds(BlockSoundGroup.CALCITE)
                   .strength(1.5F)
                   .pistonBehavior(PistonBehavior.DESTROY)
   ), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, Blocks.AMETHYST_CLUSTER, ItemGroups.NATURAL);

   public static final Block LARGE_CALCITE_BUD = registerBlock("large_calcite_bud",
           new AmethystClusterBlock(4, 3, FabricBlockSettings.copy(CALCITE_CLUSTER).sounds(BlockSoundGroup.CALCITE).solid().pistonBehavior(PistonBehavior.DESTROY)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           CALCITE_CLUSTER,
           ItemGroups.NATURAL
   );

   public static final Block SMALL_CALCITE_BUD = registerBlock("small_calcite_bud",
           new AmethystClusterBlock(3, 4, FabricBlockSettings.copy(CALCITE_CLUSTER).sounds(BlockSoundGroup.CALCITE).solid().pistonBehavior(PistonBehavior.DESTROY)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           LARGE_CALCITE_BUD,
           ItemGroups.NATURAL
   );

   public static void registerHibiscusBlocks() {
      HibiscusColoredBlocks.registerColoredBlocks(); NatureSpirit.LOGGER.debug("Registering ModBlocks for " + NatureSpirit.MOD_ID);
   }
}
