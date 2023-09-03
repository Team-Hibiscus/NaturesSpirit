package net.hibiscus.naturespirit.registration;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.blocks.*;
import net.hibiscus.naturespirit.datagen.HibiscusConfiguredFeatures;
import net.hibiscus.naturespirit.items.PizzaItem;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusColoredBlocks;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusWoods;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PlaceOnWaterBlockItem;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import static net.hibiscus.naturespirit.util.HibiscusRegistryHelper.*;

public class HibiscusBlocksAndItems {
   public static final Block SANDY_SOIL = registerBlock("sandy_soil",
           new Block(FabricBlockSettings.of()
                                        .mapColor(MapColor.DIRT)
                                        .instrument(NoteBlockInstrument.BASEDRUM)
                                        .strength(0.5F)
                                        .sound(SoundType.GRAVEL)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           Blocks.FARMLAND,
           CreativeModeTabs.NATURAL_BLOCKS);

   public static final Block TALL_SCORCHED_GRASS = registerPlantBlock("tall_scorched_grass",
           new TallLargeDesertFernBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.GLOW_LICHEN)
                   .noCollission()
                   .instabreak()
                   .sound(SoundType.GRASS)
                   .offsetType(BlockBehaviour.OffsetType.XZ)
                   .pushReaction(PushReaction.DESTROY)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           Blocks.LARGE_FERN,
           0.3f
   );
   public static final Block SCORCHED_GRASS = registerPlantBlock("scorched_grass",
           new LargeDesertFernBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.GLOW_LICHEN)
                   .noCollission()
                   .instabreak()
                   .sound(SoundType.GRASS)
                   .offsetType(BlockBehaviour.OffsetType.XZ)
                   .pushReaction(PushReaction.DESTROY)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           Blocks.FERN,
           0.3f
   );

   public static final Block TALL_SEDGE_GRASS = registerPlantBlock("tall_sedge_grass",
           new TallSedgeGrassBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.PLANT)
                   .noCollission()
                   .instabreak()
                   .sound(SoundType.GRASS)
                   .offsetType(BlockBehaviour.OffsetType.XZ)
                   .pushReaction(PushReaction.DESTROY)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           TALL_SCORCHED_GRASS,
           0.3f
   );
   public static final Block SEDGE_GRASS = registerPlantBlock("sedge_grass",
           new SedgeGrassBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.PLANT)
                   .noCollission()
                   .instabreak()
                   .sound(SoundType.GRASS)
                   .offsetType(BlockBehaviour.OffsetType.XZ)
                   .pushReaction(PushReaction.DESTROY)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           SCORCHED_GRASS,
           0.3f
   );

   public static final Block LARGE_FLAXEN_FERN = registerPlantBlock("large_flaxen_fern",
           new DoublePlantBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.GOLD)
                   .noCollission()
                   .instabreak()
                   .sound(SoundType.GRASS)
                   .offsetType(BlockBehaviour.OffsetType.XZ)
                   .pushReaction(PushReaction.DESTROY)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           TALL_SCORCHED_GRASS,
           0.3f
   );
   public static final Block FLAXEN_FERN = registerPlantBlock("flaxen_fern",
           new HibiscusFernBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.GOLD)
                   .noCollission()
                   .instabreak()
                   .sound(SoundType.GRASS)
                   .offsetType(BlockBehaviour.OffsetType.XZ)
                   .pushReaction(PushReaction.DESTROY)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           SCORCHED_GRASS,
           0.3f
   );
   public static final Block POTTED_FLAXEN_FERN = registerPottedPlant("flaxen_fern", FLAXEN_FERN);

   public static final Block SHIITAKE_MUSHROOM = registerPlantBlock("shiitake_mushroom",
           new ShiitakeMushroomPlantBlock(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.COLOR_BROWN)
                   .noCollission()
                   .randomTicks()
                   .instabreak()
                   .sound(SoundType.GRASS)
                   .lightLevel((state) -> 1)
                   .hasPostProcess(Blocks::always)
                   .pushReaction(PushReaction.DESTROY), HibiscusConfiguredFeatures.HUGE_SHIITAKE_MUSHROOM),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           Blocks.RED_MUSHROOM,
           0.1F
   );
   public static final Block SHIITAKE_MUSHROOM_BLOCK = registerBlock(
           "shiitake_mushroom_block",
           new HugeMushroomBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.DIRT)
                   .instrument(NoteBlockInstrument.BASS)
                   .strength(0.2F)
                   .sound(SoundType.WOOD)
                   .ignitedByLava()),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           Blocks.RED_MUSHROOM_BLOCK,
           CreativeModeTabs.NATURAL_BLOCKS
   );

   public static final Block LAVENDER = registerPlantBlock("lavender",
           new LargeTallFlowerBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.PLANT)
                   .noCollission()
                   .instabreak()
                   .sound(SoundType.GRASS)
                   .ignitedByLava()
                   .offsetType(BlockBehaviour.OffsetType.XZ)
                   .pushReaction(PushReaction.DESTROY)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           Blocks.PEONY,
           0.5f
   );
   public static final Block BLEEDING_HEART = registerPlantBlock("bleeding_heart",
           new LargeTallFlowerBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.PLANT)
                   .noCollission()
                   .instabreak()
                   .sound(SoundType.GRASS)
                   .ignitedByLava()
                   .offsetType(BlockBehaviour.OffsetType.XZ)
                   .pushReaction(PushReaction.DESTROY)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           LAVENDER,
           0.5f
   );
   public static final Block CARNATION = registerPlantBlock("carnation",
           new TallFlowerBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.PLANT)
                   .noCollission()
                   .instabreak()
                   .sound(SoundType.GRASS)
                   .ignitedByLava()
                   .offsetType(BlockBehaviour.OffsetType.XZ)
                   .pushReaction(PushReaction.DESTROY)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           BLEEDING_HEART,
           0.4f
   );
   public static final Block GARDENIA = registerPlantBlock("gardenia",
           new TallFlowerBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.PLANT)
                   .noCollission()
                   .instabreak()
                   .sound(SoundType.GRASS)
                   .ignitedByLava()
                   .offsetType(BlockBehaviour.OffsetType.XZ)
                   .pushReaction(PushReaction.DESTROY)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           CARNATION,
           0.4f
   );
   public static final Block SNAPDRAGON = registerPlantBlock("snapdragon",
           new TallFlowerBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.PLANT)
                   .noCollission()
                   .instabreak()
                   .sound(SoundType.GRASS)
                   .ignitedByLava()
                   .offsetType(BlockBehaviour.OffsetType.XZ)
                   .pushReaction(PushReaction.DESTROY)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           GARDENIA,
           0.4f
   );
   public static final Block MARIGOLD = registerPlantBlock("marigold",
           new TallFlowerBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.PLANT)
                   .noCollission()
                   .instabreak()
                   .sound(SoundType.GRASS)
                   .ignitedByLava()
                   .offsetType(BlockBehaviour.OffsetType.XZ)
                   .pushReaction(PushReaction.DESTROY)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           SNAPDRAGON,
           0.4f
   );
   public static final Block FOXGLOVE = registerPlantBlock("foxglove",
           new TallFlowerBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.PLANT)
                   .noCollission()
                   .instabreak()
                   .sound(SoundType.GRASS)
                   .ignitedByLava()
                   .offsetType(BlockBehaviour.OffsetType.XZ)
                   .pushReaction(PushReaction.DESTROY)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           MARIGOLD,
           0.4f
   );
   public static final Block CATTAIL = registerPlantBlock("cattail",
           new Cattails(FabricBlockSettings.of()
                   .mapColor(MapColor.PLANT)
                   .noCollission()
                   .instabreak()
                   .sound(SoundType.GRASS)
                   .ignitedByLava()
                   .offsetType(BlockBehaviour.OffsetType.XZ)
                   .pushReaction(PushReaction.DESTROY)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           Blocks.LARGE_FERN,
           0.4f
   );
   public static final Block BLUEBELL = registerPlantBlock("bluebell", new LargeFlowerBlock(MobEffects.DIG_SPEED,
           7,
           FabricBlockSettings.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(
                   SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)
   ), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, Blocks.LILY_OF_THE_VALLEY, 0.4f);
   public static final Block TIGER_LILY = registerPlantBlock("tiger_lily",
           new LargeFlowerBlock(MobEffects.FIRE_RESISTANCE,
                   7,
                   FabricBlockSettings.of()
                           .mapColor(MapColor.PLANT)
                           .noCollission()
                           .instabreak()
                           .sound(SoundType.GRASS)
                           .offsetType(BlockBehaviour.OffsetType.XZ)
                           .pushReaction(PushReaction.DESTROY)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           BLUEBELL,
           0.4f
   );
   public static final Block PURPLE_WILDFLOWER = registerPlantBlock("purple_wildflower", new LargeFlowerBlock(
           MobEffects.SLOW_FALLING,
           7,
           FabricBlockSettings.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(
                   SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)
   ), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, TIGER_LILY, 0.4f);
   public static final Block YELLOW_WILDFLOWER = registerPlantBlock("yellow_wildflower", new LargeFlowerBlock(
           MobEffects.SLOW_FALLING,
           7,
           FabricBlockSettings.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(
                   SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)
   ), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, PURPLE_WILDFLOWER, 0.4f);
   public static final Block ANEMONE = registerPlantBlock("anemone", new MidFlowerBlock(MobEffects.DAMAGE_RESISTANCE,
           4,
           FabricBlockSettings.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(
                   SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)
   ), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, TIGER_LILY, 0.4f);
   public static final Block HIBISCUS = registerPlantBlock("hibiscus", new FlowerBlock(MobEffects.LUCK,
           7,
           FabricBlockSettings.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(
                   SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)
   ), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, ANEMONE, 0.3f);
   public static final Block POTTED_HIBISCUS = registerPottedPlant("hibiscus", HIBISCUS);
   public static final Block POTTED_ANEMONE = registerPottedPlant("anemone", ANEMONE);


   public static final Block LOTUS_FLOWER = registerPlantBlockWithoutItem("lotus_flower",
           new LotusFlowerBlock(FabricBlockSettings.of()
                   .mapColor(MapColor.COLOR_PINK)
                   .pushReaction(PushReaction.DESTROY)
                   .randomTicks()
                   .noOcclusion()
                   .instabreak()
                   .sound(SoundType.LILY_PAD)),
           0.5f
   );
   public static final Item LOTUS_FLOWER_ITEM = registerItem(
           "lotus_flower", new PlaceOnWaterBlockItem(LOTUS_FLOWER, new Item.Properties()),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           Items.LILY_PAD,
           CreativeModeTabs.NATURAL_BLOCKS
   );
   public static final Block LOTUS_STEM = registerPlantBlock("lotus_stem", new LotusStem(
           FabricBlockSettings.of()
                   .mapColor(MapColor.COLOR_GREEN)
                   .pushReaction(PushReaction.DESTROY)
                   .noCollission()
                   .noOcclusion()
                   .instabreak()
                   .sound(SoundType.LILY_PAD),
           LOTUS_FLOWER
   ), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, LOTUS_FLOWER, 0.2F);
   public static final Block POTTED_SHIITAKE_MUSHROOM = registerPottedPlant("shiitake_mushroom", SHIITAKE_MUSHROOM);
   public static final FoodProperties GREEN_OLIVE_COMPONENT = (new FoodProperties.Builder()).nutrition(2).saturationMod(
           0.4F).build();
   public static final FoodProperties BLACK_OLIVE_COMPONENT = (new FoodProperties.Builder()).nutrition(3).saturationMod(
           0.5F).build();
   public static final Item GREEN_OLIVES = registerPlantItem("green_olives",
           new Item(new FabricItemSettings().food(GREEN_OLIVE_COMPONENT)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           Items.BEETROOT,
           CreativeModeTabs.FOOD_AND_DRINKS,
           0.3F
   );
   public static final Item BLACK_OLIVES = registerPlantItem("black_olives",
           new Item(new FabricItemSettings().food(BLACK_OLIVE_COMPONENT)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           GREEN_OLIVES,
           CreativeModeTabs.FOOD_AND_DRINKS,
           0.3F
   );
   public static final Block DESERT_TURNIP_ROOT_BLOCK = registerBlock("desert_turnip_root_block",
           new RotatedPillarBlock(FabricBlockSettings.of()
                   .ignitedByLava()
                   .instrument(NoteBlockInstrument.BASS)
                   .mapColor(MapColor.PODZOL)
                   .strength(2.0F)
                   .sound(SoundType.ROOTS)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           Blocks.SHROOMLIGHT,
           CreativeModeTabs.NATURAL_BLOCKS
   );
   public static final Block DESERT_TURNIP_BLOCK = registerBlock("desert_turnip_block",
           new DesertTurnipBlock(FabricBlockSettings.of()
                   .ignitedByLava()
                   .instrument(NoteBlockInstrument.BASS)
                   .mapColor(MapColor.ICE)
                   .strength(2.0F)
                   .sound(SoundType.ROOTS)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           HibiscusBlocksAndItems.DESERT_TURNIP_ROOT_BLOCK,
           CreativeModeTabs.NATURAL_BLOCKS
   );
   public static final Block DESERT_TURNIP_STEM = registerPlantBlockWithoutItem("desert_turnip_stem",
           new DesertPlantBlock((DesertTurnipBlock) DESERT_TURNIP_BLOCK,
                   DESERT_TURNIP_ROOT_BLOCK,
                   FabricBlockSettings.of()
                           .noCollission()
                           .instabreak()
                           .randomTicks()
                           .sound(SoundType.GRASS)
                           .pushReaction(PushReaction.DESTROY)
           ),
           0.3f
   );
   public static final FoodProperties DESERT_TURNIP_FOOD_COMPONENT = (new FoodProperties.Builder()).nutrition(4)
                                                                                                 .saturationMod(0.6F)
                                                                                                 .build();
   public static final Item DESERT_TURNIP = registerItem("desert_turnip",
           new ItemNameBlockItem(DESERT_TURNIP_STEM, (new Item.Properties()).food(DESERT_TURNIP_FOOD_COMPONENT)),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           Items.BEETROOT,
           CreativeModeTabs.FOOD_AND_DRINKS
   );

   public static final FoodProperties STANDARD_PIZZA_COMPONENT = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.2F).build();
   public static final Block PIZZA_BLOCK = registerBlock("pizza_block", new PizzaBlock(FabricBlockSettings.copy(Blocks.CAKE)));
   public static final Item WHOLE_PIZZA = registerItem("whole_pizza", new PizzaItem(PIZZA_BLOCK, new Item.Properties().stacksTo(1).food(STANDARD_PIZZA_COMPONENT)), HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, Items.BREAD, CreativeModeTabs.FOOD_AND_DRINKS);
   public static final Item THREE_QUARTERS_PIZZA = registerItem("three_quarters_pizza", new PizzaItem(PIZZA_BLOCK, new Item.Properties().stacksTo(1).food(STANDARD_PIZZA_COMPONENT)));
   public static final Item HALF_PIZZA = registerItem("half_pizza", new PizzaItem(PIZZA_BLOCK, new Item.Properties().stacksTo(1).food(STANDARD_PIZZA_COMPONENT)));
   public static final Item QUARTER_PIZZA = registerItem("quarter_pizza", new PizzaItem(PIZZA_BLOCK, new Item.Properties().stacksTo(1).food(STANDARD_PIZZA_COMPONENT)));

   public static final Item CALCITE_SHARD = registerItem(
           "calcite_shard",
           new Item(new Item.Properties()),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           Items.AMETHYST_SHARD,
           CreativeModeTabs.INGREDIENTS
   );
   public static final Block CALCITE_CLUSTER = registerBlock(
           "calcite_cluster",
           new AmethystClusterBlock(7,
                   3,
                   FabricBlockSettings.of().mapColor(MapColor.SNOW).forceSolidOn().noOcclusion().randomTicks().sound(
                           SoundType.CALCITE).strength(1.5F).pushReaction(PushReaction.DESTROY)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           Blocks.AMETHYST_CLUSTER,
           CreativeModeTabs.NATURAL_BLOCKS
   );
   public static final Block LARGE_CALCITE_BUD = registerBlock(
           "large_calcite_bud",
           new AmethystClusterBlock(4,
                   3,
                   FabricBlockSettings.copy(CALCITE_CLUSTER)
                                      .sound(SoundType.CALCITE)
                                      .forceSolidOn()
                                      .pushReaction(PushReaction.DESTROY)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           CALCITE_CLUSTER,
           CreativeModeTabs.NATURAL_BLOCKS
   );
   public static final Block SMALL_CALCITE_BUD = registerBlock(
           "small_calcite_bud",
           new AmethystClusterBlock(3,
                   4,
                   FabricBlockSettings.copy(CALCITE_CLUSTER)
                                      .sound(SoundType.CALCITE)
                                      .forceSolidOn()
                                      .pushReaction(PushReaction.DESTROY)
           ),
           HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP,
           LARGE_CALCITE_BUD,
           CreativeModeTabs.NATURAL_BLOCKS
   );
   public static void registerHibiscusBlocks() {
      HibiscusColoredBlocks.registerColoredBlocks();
      HibiscusWoods.registerWoods();
      NatureSpirit.LOGGER.debug("Registering ModBlocks for " + NatureSpirit.MOD_ID);
   }
}
