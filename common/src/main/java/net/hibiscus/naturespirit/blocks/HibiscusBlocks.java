package net.hibiscus.naturespirit.blocks;

import net.hibiscus.naturespirit.Constants;
import net.hibiscus.naturespirit.blocks.vanilla.*;
import net.hibiscus.naturespirit.items.PizzaItem;
import net.hibiscus.naturespirit.mixin.BlockSetTypeAccessor;
import net.hibiscus.naturespirit.mixin.WoodTypeAccessor;
import net.hibiscus.naturespirit.world.feature.tree.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class HibiscusBlocks {
   public static final Block TALL_SCORCHED_GRASS = registerBlock("tall_scorched_grass", new TallLargeDesertFernBlock(
           BlockBehaviour.Properties.of()
                   .mapColor(MapColor.GLOW_LICHEN)
                   .noCollission()
                   .instabreak()
                   .sound(SoundType.GRASS)
                   .offsetType(BlockBehaviour.OffsetType.XZ)
                   .pushReaction(PushReaction.DESTROY)));

   public static final Block SCORCHED_GRASS = registerBlock("scorched_grass", new LargeDesertFernBlock(
           BlockBehaviour.Properties.of()
                   .mapColor(MapColor.GLOW_LICHEN)
                   .noCollission()
                   .instabreak()
                   .sound(SoundType.GRASS)
                   .offsetType(BlockBehaviour.OffsetType.XZ)
                   .pushReaction(PushReaction.DESTROY)));

   public static final Block LAVENDER = registerBlock("lavender",
           new LargeTallFlowerBlock(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.PLANT)
                   .noCollission()
                   .instabreak()
                   .sound(SoundType.GRASS)
                   .ignitedByLava()
                   .offsetType(BlockBehaviour.OffsetType.XZ)
                   .pushReaction(PushReaction.DESTROY)));
   public static final Block BLEEDING_HEART = registerBlock("bleeding_heart",
           new LargeTallFlowerBlock(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.PLANT)
                   .noCollission()
                   .instabreak()
                   .sound(SoundType.GRASS)
                   .ignitedByLava()
                   .offsetType(BlockBehaviour.OffsetType.XZ)
                   .pushReaction(PushReaction.DESTROY)));
   public static final Block CARNATION = registerBlock("carnation",
           new TallFlowerBlock(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.PLANT)
                   .noCollission()
                   .instabreak()
                   .sound(SoundType.GRASS)
                   .ignitedByLava()
                   .offsetType(BlockBehaviour.OffsetType.XZ)
                   .pushReaction(PushReaction.DESTROY)));
   public static final Block GARDENIA = registerBlock("gardenia",
           new TallFlowerBlock(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.PLANT)
                   .noCollission()
                   .instabreak()
                   .sound(SoundType.GRASS)
                   .ignitedByLava()
                   .offsetType(BlockBehaviour.OffsetType.XZ)
                   .pushReaction(PushReaction.DESTROY)));
   public static final Block SNAPDRAGON = registerBlock("snapdragon",
           new TallFlowerBlock(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.PLANT)
                   .noCollission()
                   .instabreak()
                   .sound(SoundType.GRASS)
                   .ignitedByLava()
                   .offsetType(BlockBehaviour.OffsetType.XZ)
                   .pushReaction(PushReaction.DESTROY)));
   public static final Block MARIGOLD = registerBlock("marigold",
           new TallFlowerBlock(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.PLANT)
                   .noCollission()
                   .instabreak()
                   .sound(SoundType.GRASS)
                   .ignitedByLava()
                   .offsetType(BlockBehaviour.OffsetType.XZ)
                   .pushReaction(PushReaction.DESTROY)));
   public static final Block FOXGLOVE = registerBlock("foxglove",
           new TallFlowerBlock(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.PLANT)
                   .noCollission()
                   .instabreak()
                   .sound(SoundType.GRASS)
                   .ignitedByLava()
                   .offsetType(BlockBehaviour.OffsetType.XZ)
                   .pushReaction(PushReaction.DESTROY)));
   public static final Block CATTAIL = registerBlock("cattail",
           new Cattails(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.PLANT)
                   .noCollission()
                   .instabreak()
                   .sound(SoundType.GRASS)
                   .ignitedByLava()
                   .offsetType(BlockBehaviour.OffsetType.XZ)
                   .pushReaction(PushReaction.DESTROY))
   );
   public static final Block BLUEBELL = registerBlock("bluebell", new LargeFlowerBlock(MobEffects.DIG_SPEED, 7,
           BlockBehaviour.Properties.of()
                   .mapColor(MapColor.PLANT)
                   .noCollission()
                   .instabreak()
                   .sound(SoundType.GRASS)
                   .offsetType(BlockBehaviour.OffsetType.XZ)
                   .pushReaction(PushReaction.DESTROY)));

   public static final Block TIGER_LILY = registerBlock("tiger_lily", new LargeFlowerBlock(MobEffects.FIRE_RESISTANCE, 7,
           BlockBehaviour.Properties.of()
                   .mapColor(MapColor.PLANT)
                   .noCollission()
                   .instabreak()
                   .sound(SoundType.GRASS)
                   .offsetType(BlockBehaviour.OffsetType.XZ)
                   .pushReaction(PushReaction.DESTROY)));

   public static final Block PURPLE_WILDFLOWER = registerBlock("purple_wildflower", new LargeFlowerBlock(MobEffects.SLOW_FALLING, 7,
           BlockBehaviour.Properties.of()
                   .mapColor(MapColor.PLANT)
                   .noCollission()
                   .instabreak()
                   .sound(SoundType.GRASS)
                   .offsetType(BlockBehaviour.OffsetType.XZ)
                   .pushReaction(PushReaction.DESTROY)));

   public static final Block YELLOW_WILDFLOWER = registerBlock("yellow_wildflower", new LargeFlowerBlock(MobEffects.SLOW_FALLING, 7,
           BlockBehaviour.Properties.of()
                   .mapColor(MapColor.PLANT)
                   .noCollission()
                   .instabreak()
                   .sound(SoundType.GRASS)
                   .offsetType(BlockBehaviour.OffsetType.XZ)
                   .pushReaction(PushReaction.DESTROY)));

   public static final Block ANEMONE = registerBlock("anemone", new MidFlowerBlock(MobEffects.DAMAGE_RESISTANCE, 4,
           BlockBehaviour.Properties.of()
                   .mapColor(MapColor.PLANT)
                   .noCollission()
                   .instabreak()
                   .sound(SoundType.GRASS)
                   .offsetType(BlockBehaviour.OffsetType.XZ)
                   .pushReaction(PushReaction.DESTROY)));

   public static final Block HIBISCUS = registerBlock("hibiscus", new FlowerBlock(MobEffects.LUCK, 7,
           BlockBehaviour.Properties.of()
                   .mapColor(MapColor.PLANT)
                   .noCollission()
                   .instabreak()
                   .sound(SoundType.GRASS)
                   .offsetType(BlockBehaviour.OffsetType.XZ)
                   .pushReaction(PushReaction.DESTROY)));
   public static final Block POTTED_HIBISCUS = registerPottedPlant("hibiscus", HIBISCUS);
   public static final Block POTTED_ANEMONE = registerPottedPlant("anemone", ANEMONE);
   public static final Block REDWOOD_LEAVES = registerLeafBlock("redwood_leaves", MapColor.COLOR_LIGHT_GREEN);
   public static final Block SUGI_LEAVES = registerLeafBlock("sugi_leaves", MapColor.COLOR_PINK);
   public static final Block WHITE_WISTERIA_LEAVES = registerLeafBlock("white_wisteria_leaves",
           MapColor.TERRACOTTA_WHITE,
           true
   );
   public static final Block BLUE_WISTERIA_LEAVES = registerLeafBlock("blue_wisteria_leaves",
           MapColor.COLOR_CYAN,
           true
   );
   public static final Block PINK_WISTERIA_LEAVES = registerLeafBlock("pink_wisteria_leaves",
           MapColor.COLOR_PINK,
           true
   );
   public static final Block PURPLE_WISTERIA_LEAVES = registerLeafBlock("purple_wisteria_leaves",
           MapColor.COLOR_PURPLE,
           true
   );
   public static final Block FIR_LEAVES = registerLeafBlock("fir_leaves", MapColor.COLOR_LIGHT_GREEN);
   public static final Block WILLOW_LEAVES = registerLeafBlock("willow_leaves", MapColor.COLOR_GREEN, false);
   public static final Block ASPEN_LEAVES = registerLeafBlock("aspen_leaves", MapColor.COLOR_LIGHT_GREEN);
   public static final Block CYPRESS_LEAVES = registerLeafBlock("cypress_leaves", MapColor.PLANT);
   public static final Block OLIVE_LEAVES = registerLeafBlock("olive_leaves", MapColor.PLANT);
   public static final Block JOSHUA_LEAVES = registerLeafBlock("joshua_leaves", MapColor.SAND);

   public static final Block[] REDWOOD_SAPLING = registerSapling("redwood", new RedwoodSaplingGenerator());
   public static final Block[] SUGI_SAPLING = registerSapling("sugi", new SugiSaplingGenerator());
   public static final Block[] WHITE_WISTERIA_SAPLING = registerSapling("white_wisteria", new WhiteWisteriaSaplingGenerator());
   public static final Block[] BLUE_WISTERIA_SAPLING = registerSapling("blue_wisteria", new BlueWisteriaSaplingGenerator());
   public static final Block[] PINK_WISTERIA_SAPLING = registerSapling("pink_wisteria", new PinkWisteriaSaplingGenerator());
   public static final Block[] PURPLE_WISTERIA_SAPLING = registerSapling("purple_wisteria", new PurpleWisteriaSaplingGenerator());
   public static final Block[] FIR_SAPLING = registerSapling("fir", new FirSaplingGenerator());
   public static final Block[] WILLOW_SAPLING = registerSapling("willow", new WillowSaplingGenerator());
   public static final Block[] ASPEN_SAPLING = registerSapling("aspen", new AspenSaplingGenerator());
   public static final Block[] CYPRESS_SAPLING = registerSapling("cypress", new CypressSaplingGenerator());
   public static final Block[] OLIVE_SAPLING = registerSapling("olive", new OliveSaplingGenerator());
   public static final Block[] JOSHUA_SAPLING = registerJoshuaSapling("joshua", new JoshuaSaplingGenerator());

   public static final Block WHITE_WISTERIA_VINES = registerBlock("white_wisteria_vines",
           new WisteriaVine(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.TERRACOTTA_WHITE)
                   .pushReaction(PushReaction.DESTROY)
                   .randomTicks()
                   .noCollission()
                   .noOcclusion()
                   .instabreak()
                   .sound(SoundType.WEEPING_VINES))
   );
   public static final Block WHITE_WISTERIA_VINES_PLANT = registerBlock("white_wisteria_vines_plant",
           new WisteriaVinePlant(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.TERRACOTTA_WHITE)
                   .pushReaction(PushReaction.DESTROY)
                   .noCollission()
                   .noOcclusion()
                   .instabreak()
                   .sound(SoundType.WEEPING_VINES)
                   .dropsLike(WHITE_WISTERIA_VINES), WHITE_WISTERIA_VINES)
   );
   public static final Block BLUE_WISTERIA_VINES = registerBlock("blue_wisteria_vines",
           new WisteriaVine(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.COLOR_CYAN)
                   .pushReaction(PushReaction.DESTROY)
                   .randomTicks()
                   .noCollission()
                   .instabreak()
                   .noOcclusion()
                   .sound(SoundType.WEEPING_VINES))
   );
   public static final Block BLUE_WISTERIA_VINES_PLANT = registerBlock("blue_wisteria_vines_plant",
           new WisteriaVinePlant(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.COLOR_CYAN)
                   .pushReaction(PushReaction.DESTROY)
                   .noCollission()
                   .noOcclusion()
                   .instabreak()
                   .sound(SoundType.WEEPING_VINES)
                   .dropsLike(BLUE_WISTERIA_VINES), BLUE_WISTERIA_VINES)
   );
   public static final Block PINK_WISTERIA_VINES = registerBlock("pink_wisteria_vines",
           new WisteriaVine(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.COLOR_PINK)
                   .pushReaction(PushReaction.DESTROY)
                   .randomTicks()
                   .noCollission()
                   .instabreak()
                   .noOcclusion()
                   .sound(SoundType.WEEPING_VINES))
   );
   public static final Block PINK_WISTERIA_VINES_PLANT = registerBlock("pink_wisteria_vines_plant",
           new WisteriaVinePlant(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.COLOR_PINK)
                   .pushReaction(PushReaction.DESTROY)
                   .noCollission()
                   .noOcclusion()
                   .instabreak()
                   .sound(SoundType.WEEPING_VINES)
                   .dropsLike(PINK_WISTERIA_VINES), PINK_WISTERIA_VINES)
   );
   public static final Block PURPLE_WISTERIA_VINES = registerBlock("purple_wisteria_vines",
           new WisteriaVine(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.COLOR_PURPLE)
                   .pushReaction(PushReaction.DESTROY)
                   .randomTicks()
                   .noCollission()
                   .instabreak()
                   .noOcclusion()
                   .sound(SoundType.WEEPING_VINES))
   );
   public static final Block PURPLE_WISTERIA_VINES_PLANT = registerBlock("purple_wisteria_vines_plant",
           new WisteriaVinePlant(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.COLOR_PURPLE)
                   .pushReaction(PushReaction.DESTROY)
                   .noCollission()
                   .noOcclusion()
                   .instabreak()
                   .sound(SoundType.WEEPING_VINES)
                   .dropsLike(PURPLE_WISTERIA_VINES), PURPLE_WISTERIA_VINES)
   );
   public static final Block WILLOW_VINES = registerBlock("willow_vines",
           new WillowVine(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.PLANT)
                   .pushReaction(PushReaction.DESTROY)
                   .randomTicks()
                   .noCollission()
                   .noOcclusion()
                   .instabreak()
                   .sound(SoundType.WEEPING_VINES))
   );
   public static final Block WILLOW_VINES_PLANT = registerBlock("willow_vines_plant",
           new WillowVinePlant(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.PLANT)
                   .pushReaction(PushReaction.DESTROY)
                   .noCollission()
                   .noOcclusion()
                   .instabreak()
                   .sound(SoundType.WEEPING_VINES)
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
   public static final BlockSetType CYPRESS_BLOCK_SET_TYPE = BlockSetTypeAccessor.registerNew(new BlockSetType("cypress"));
   public static final WoodType CYPRESS_WOOD_TYPE = WoodTypeAccessor.registerNew(new WoodType("cypress", CYPRESS_BLOCK_SET_TYPE));
   public static final BlockSetType OLIVE_BLOCK_SET_TYPE = BlockSetTypeAccessor.registerNew(new BlockSetType("olive"));
   public static final WoodType OLIVE_WOOD_TYPE = WoodTypeAccessor.registerNew(new WoodType("olive", OLIVE_BLOCK_SET_TYPE));
   public static final BlockSetType JOSHUA_BLOCK_SET_TYPE = BlockSetTypeAccessor.registerNew(new BlockSetType("joshua"));
   public static final WoodType JOSHUA_WOOD_TYPE = WoodTypeAccessor.registerNew(new WoodType("joshua", JOSHUA_BLOCK_SET_TYPE));

   public static final FoodProperties GREEN_OLIVE_COMPONENT = (new FoodProperties.Builder()).nutrition(2).saturationMod(
           0.4F).build();
   public static final FoodProperties BLACK_OLIVE_COMPONENT = (new FoodProperties.Builder()).nutrition(3).saturationMod(
           0.5F).build();
   public static final FoodProperties DESERT_TURNIP_FOOD_COMPONENT = (new FoodProperties.Builder()).nutrition(4)
           .saturationMod(0.6F)
           .build();

   public static final Item GREEN_OLIVES = registerItem("green_olives",
           new Item(new Item.Properties().food(GREEN_OLIVE_COMPONENT))
   );
   public static final Item BLACK_OLIVES = registerItem("black_olives",
           new Item(new Item.Properties().food(BLACK_OLIVE_COMPONENT))
   );
   public static final Block DESERT_TURNIP_ROOT_BLOCK = registerBlock("desert_turnip_root_block",
           new RotatedPillarBlock(BlockBehaviour.Properties.of()
                   .ignitedByLava()
                   .instrument(NoteBlockInstrument.BASS)
                   .mapColor(MapColor.PODZOL)
                   .strength(2.0F)
                   .sound(SoundType.ROOTS))
   );
   public static final Block DESERT_TURNIP_BLOCK = registerBlock("desert_turnip_block",
           new DesertTurnipBlock(BlockBehaviour.Properties.of()
                   .ignitedByLava()
                   .instrument(NoteBlockInstrument.BASS)
                   .mapColor(MapColor.ICE)
                   .strength(2.0F)
                   .sound(SoundType.ROOTS))
   );
   public static final Block DESERT_TURNIP_STEM = registerBlock1("desert_turnip_stem",
           new DesertPlantBlock((DesertTurnipBlock) DESERT_TURNIP_BLOCK, DESERT_TURNIP_ROOT_BLOCK, BlockBehaviour.Properties.of()
                   .noCollission()
                   .instabreak()
                   .randomTicks()
                   .sound(SoundType.GRASS)
                   .pushReaction(PushReaction.DESTROY))
   );
   public static final Item DESERT_TURNIP = registerItem("desert_turnip", new ItemNameBlockItem(DESERT_TURNIP_STEM, (new Item.Properties()).food(DESERT_TURNIP_FOOD_COMPONENT)));
   public static final FoodProperties STANDARD_PIZZA_COMPONENT = (new FoodProperties.Builder()).nutrition(2)
           .saturationMod(0.2F)
           .build();
   public static final Block PIZZA_BLOCK = registerBlock("pizza_block",
           new PizzaBlock(BlockBehaviour.Properties.copy(Blocks.CAKE))
   );
   public static final Item WHOLE_PIZZA = registerItem("whole_pizza",
           new PizzaItem(PIZZA_BLOCK, new Item.Properties().stacksTo(1).food(STANDARD_PIZZA_COMPONENT))
   );
   public static final Item THREE_QUARTERS_PIZZA = registerItem("three_quarters_pizza",
           new PizzaItem(PIZZA_BLOCK, new Item.Properties().stacksTo(1).food(STANDARD_PIZZA_COMPONENT))
   );
   public static final Item HALF_PIZZA = registerItem("half_pizza",
           new PizzaItem(PIZZA_BLOCK, new Item.Properties().stacksTo(1).food(STANDARD_PIZZA_COMPONENT))
   );
   public static final Item QUARTER_PIZZA = registerItem("quarter_pizza",
           new PizzaItem(PIZZA_BLOCK, new Item.Properties().stacksTo(1).food(STANDARD_PIZZA_COMPONENT))
   );

   public static final Block[] REDWOOD = registerWoodBlocks("redwood",
           MapColor.COLOR_RED,
           MapColor.TERRACOTTA_BROWN,
           REDWOOD_BLOCK_SET_TYPE,
           REDWOOD_WOOD_TYPE
   );
   public static final Block[] SUGI = registerWoodBlocks("sugi",
           MapColor.DIRT,
           MapColor.DEEPSLATE,
           SUGI_BLOCK_SET_TYPE,
           SUGI_WOOD_TYPE
   );
   public static final Block FRAMED_SUGI_DOOR = registerBlock("framed_sugi_door",
           new ModdedDoorBlock(BlockBehaviour.Properties.copy(SUGI[4]).noOcclusion(), SUGI_BLOCK_SET_TYPE)
   );
   public static final Block FRAMED_SUGI_TRAPDOOR = registerBlock("framed_sugi_trapdoor",
           new ModdedTrapDoorBlock(BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(3.0f).sound(
                   SoundType.WOOD).isValidSpawn(HibiscusBlocks::never).noOcclusion(),
                   SUGI_BLOCK_SET_TYPE
           )
   );
   public static final Block[] WISTERIA = registerWoodBlocks("wisteria",
           MapColor.TERRACOTTA_WHITE,
           MapColor.COLOR_GRAY,
           WISTERIA_BLOCK_SET_TYPE,
           WISTERIA_WOOD_TYPE
   );
   public static final Block[] FIR = registerWoodBlocks("fir",
           MapColor.DIRT,
           MapColor.COLOR_GRAY,
           FIR_BLOCK_SET_TYPE,
           FIR_WOOD_TYPE
   );
   public static final Block[] WILLOW = registerWoodBlocks("willow",
           MapColor.TERRACOTTA_BROWN,
           MapColor.TERRACOTTA_BLACK,
           WILLOW_BLOCK_SET_TYPE,
           WILLOW_WOOD_TYPE
   );
   public static final Block[] ASPEN = registerWoodBlocks("aspen",
           MapColor.SAND,
           MapColor.WOOL,
           ASPEN_BLOCK_SET_TYPE,
           ASPEN_WOOD_TYPE
   );
   public static final Block[] CYPRESS = registerWoodBlocks("cypress",
           MapColor.WOOD,
           MapColor.PODZOL,
           CYPRESS_BLOCK_SET_TYPE,
           CYPRESS_WOOD_TYPE
   );
   public static final Block[] OLIVE = registerWoodBlocks("olive",
           MapColor.GRASS,
           MapColor.SAND,
           OLIVE_BLOCK_SET_TYPE,
           OLIVE_WOOD_TYPE
   );
   public static final Block[] JOSHUA = registerJoshuaWoodBlocks("joshua",
           MapColor.GRASS,
           JOSHUA_BLOCK_SET_TYPE,
           JOSHUA_WOOD_TYPE
   );

   public static final Block SANDY_SOIL = registerBlock("sandy_soil",
           new Block(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.DIRT)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .strength(0.5F)
                   .sound(SoundType.GRAVEL)
           )
   );

   public static final Block KAOLIN = registerBlock("kaolin",
           new Block(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.COLOR_ORANGE)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F))
   );
   public static final Block WHITE_KAOLIN = registerBlock("white_kaolin",
           new Block(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.TERRACOTTA_WHITE)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F))
   );
   public static final Block LIGHT_GRAY_KAOLIN = registerBlock("light_gray_kaolin",
           new Block(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F))
   );
   public static final Block GRAY_KAOLIN = registerBlock("gray_kaolin",
           new Block(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.TERRACOTTA_GRAY)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F))
   );
   public static final Block BLACK_KAOLIN = registerBlock("black_kaolin",
           new Block(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.TERRACOTTA_BLACK)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F))
   );
   public static final Block BROWN_KAOLIN = registerBlock("brown_kaolin",
           new Block(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.TERRACOTTA_BROWN)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F))
   );
   public static final Block RED_KAOLIN = registerBlock("red_kaolin",
           new Block(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.TERRACOTTA_RED)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F))
   );
   public static final Block ORANGE_KAOLIN = registerBlock("orange_kaolin",
           new Block(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.TERRACOTTA_ORANGE)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F))
   );
   public static final Block YELLOW_KAOLIN = registerBlock("yellow_kaolin",
           new Block(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.TERRACOTTA_YELLOW)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F))
   );
   public static final Block LIME_KAOLIN = registerBlock("lime_kaolin",
           new Block(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.TERRACOTTA_LIGHT_GREEN)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F))
   );
   public static final Block GREEN_KAOLIN = registerBlock("green_kaolin",
           new Block(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.TERRACOTTA_GREEN)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F))
   );
   public static final Block CYAN_KAOLIN = registerBlock("cyan_kaolin",
           new Block(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.TERRACOTTA_CYAN)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F))
   );
   public static final Block LIGHT_BLUE_KAOLIN = registerBlock("light_blue_kaolin",
           new Block(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.TERRACOTTA_LIGHT_BLUE)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F))
   );
   public static final Block BLUE_KAOLIN = registerBlock("blue_kaolin",
           new Block(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.TERRACOTTA_BLUE)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F))
   );
   public static final Block PURPLE_KAOLIN = registerBlock("purple_kaolin",
           new Block(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.TERRACOTTA_PURPLE)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F))
   );
   public static final Block MAGENTA_KAOLIN = registerBlock("magenta_kaolin",
           new Block(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.TERRACOTTA_MAGENTA)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F))
   );
   public static final Block PINK_KAOLIN = registerBlock("pink_kaolin",
           new Block(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.TERRACOTTA_PINK)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F))
   );
   public static final Block KAOLIN_STAIRS = registerBlock("kaolin_stairs",
           new ModdedStairBlock(KAOLIN.defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(
                   NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))
   );
   public static final Block WHITE_KAOLIN_STAIRS = registerBlock("white_kaolin_stairs",
           new ModdedStairBlock(WHITE_KAOLIN.defaultBlockState(),
                   BlockBehaviour.Properties.of()
                           .mapColor(MapColor.TERRACOTTA_WHITE)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           )
   );
   public static final Block LIGHT_GRAY_KAOLIN_STAIRS = registerBlock("light_gray_kaolin_stairs",
           new ModdedStairBlock(LIGHT_GRAY_KAOLIN.defaultBlockState(),
                   BlockBehaviour.Properties.of()
                           .mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           )
   );
   public static final Block GRAY_KAOLIN_STAIRS = registerBlock("gray_kaolin_stairs",
           new ModdedStairBlock(GRAY_KAOLIN.defaultBlockState(),
                   BlockBehaviour.Properties.of()
                           .mapColor(MapColor.TERRACOTTA_GRAY)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           )
   );
   public static final Block BLACK_KAOLIN_STAIRS = registerBlock("black_kaolin_stairs",
           new ModdedStairBlock(BLACK_KAOLIN.defaultBlockState(),
                   BlockBehaviour.Properties.of()
                           .mapColor(MapColor.TERRACOTTA_BLACK)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           )
   );
   public static final Block BROWN_KAOLIN_STAIRS = registerBlock("brown_kaolin_stairs",
           new ModdedStairBlock(BROWN_KAOLIN.defaultBlockState(),
                   BlockBehaviour.Properties.of()
                           .mapColor(MapColor.TERRACOTTA_BROWN)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           )
   );
   public static final Block RED_KAOLIN_STAIRS = registerBlock("red_kaolin_stairs",
           new ModdedStairBlock(RED_KAOLIN.defaultBlockState(),
                   BlockBehaviour.Properties.of()
                           .mapColor(MapColor.TERRACOTTA_RED)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           )
   );
   public static final Block ORANGE_KAOLIN_STAIRS = registerBlock("orange_kaolin_stairs",
           new ModdedStairBlock(ORANGE_KAOLIN.defaultBlockState(),
                   BlockBehaviour.Properties.of()
                           .mapColor(MapColor.TERRACOTTA_ORANGE)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           )
   );
   public static final Block YELLOW_KAOLIN_STAIRS = registerBlock("yellow_kaolin_stairs",
           new ModdedStairBlock(YELLOW_KAOLIN.defaultBlockState(),
                   BlockBehaviour.Properties.of()
                           .mapColor(MapColor.TERRACOTTA_YELLOW)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           )
   );
   public static final Block LIME_KAOLIN_STAIRS = registerBlock("lime_kaolin_stairs",
           new ModdedStairBlock(LIME_KAOLIN.defaultBlockState(),
                   BlockBehaviour.Properties.of()
                           .mapColor(MapColor.TERRACOTTA_LIGHT_GREEN)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           )
   );
   public static final Block GREEN_KAOLIN_STAIRS = registerBlock("green_kaolin_stairs",
           new ModdedStairBlock(GREEN_KAOLIN.defaultBlockState(),
                   BlockBehaviour.Properties.of()
                           .mapColor(MapColor.TERRACOTTA_GREEN)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           )
   );
   public static final Block CYAN_KAOLIN_STAIRS = registerBlock("cyan_kaolin_stairs",
           new ModdedStairBlock(CYAN_KAOLIN.defaultBlockState(),
                   BlockBehaviour.Properties.of()
                           .mapColor(MapColor.TERRACOTTA_CYAN)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           )
   );
   public static final Block LIGHT_BLUE_KAOLIN_STAIRS = registerBlock("light_blue_kaolin_stairs",
           new ModdedStairBlock(LIGHT_BLUE_KAOLIN.defaultBlockState(),
                   BlockBehaviour.Properties.of()
                           .mapColor(MapColor.TERRACOTTA_LIGHT_BLUE)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           )
   );
   public static final Block BLUE_KAOLIN_STAIRS = registerBlock("blue_kaolin_stairs",
           new ModdedStairBlock(BLUE_KAOLIN.defaultBlockState(),
                   BlockBehaviour.Properties.of()
                           .mapColor(MapColor.TERRACOTTA_BLUE)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           )
   );
   public static final Block PURPLE_KAOLIN_STAIRS = registerBlock("purple_kaolin_stairs",
           new ModdedStairBlock(PURPLE_KAOLIN.defaultBlockState(),
                   BlockBehaviour.Properties.of()
                           .mapColor(MapColor.TERRACOTTA_PURPLE)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           )
   );
   public static final Block MAGENTA_KAOLIN_STAIRS = registerBlock("magenta_kaolin_stairs",
           new ModdedStairBlock(MAGENTA_KAOLIN.defaultBlockState(),
                   BlockBehaviour.Properties.of()
                           .mapColor(MapColor.TERRACOTTA_MAGENTA)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           )
   );
   public static final Block PINK_KAOLIN_STAIRS = registerBlock("pink_kaolin_stairs",
           new ModdedStairBlock(PINK_KAOLIN.defaultBlockState(),
                   BlockBehaviour.Properties.of()
                           .mapColor(MapColor.TERRACOTTA_PINK)
                           .instrument(NoteBlockInstrument.BASEDRUM)
                           .requiresCorrectToolForDrops()
                           .strength(1.25F, 4.2F)
           )
   );
   public static final Block KAOLIN_SLAB = registerBlock("kaolin_slab",
           new SlabBlock(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.COLOR_ORANGE)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F))
   );
   public static final Block WHITE_KAOLIN_SLAB = registerBlock("white_kaolin_slab",
           new SlabBlock(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.TERRACOTTA_WHITE)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F))
   );
   public static final Block LIGHT_GRAY_KAOLIN_SLAB = registerBlock("light_gray_kaolin_slab",
           new SlabBlock(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F))
   );
   public static final Block GRAY_KAOLIN_SLAB = registerBlock("gray_kaolin_slab",
           new SlabBlock(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.TERRACOTTA_GRAY)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F))
   );
   public static final Block BLACK_KAOLIN_SLAB = registerBlock("black_kaolin_slab",
           new SlabBlock(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.TERRACOTTA_BLACK)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F))
   );
   public static final Block BROWN_KAOLIN_SLAB = registerBlock("brown_kaolin_slab",
           new SlabBlock(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.TERRACOTTA_BROWN)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F))
   );
   public static final Block RED_KAOLIN_SLAB = registerBlock("red_kaolin_slab",
           new SlabBlock(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.TERRACOTTA_RED)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F))
   );
   public static final Block ORANGE_KAOLIN_SLAB = registerBlock("orange_kaolin_slab",
           new SlabBlock(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.TERRACOTTA_ORANGE)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F))
   );
   public static final Block YELLOW_KAOLIN_SLAB = registerBlock("yellow_kaolin_slab",
           new SlabBlock(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.TERRACOTTA_YELLOW)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F))
   );
   public static final Block LIME_KAOLIN_SLAB = registerBlock("lime_kaolin_slab",
           new SlabBlock(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.TERRACOTTA_LIGHT_GREEN)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F))
   );
   public static final Block GREEN_KAOLIN_SLAB = registerBlock("green_kaolin_slab",
           new SlabBlock(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.TERRACOTTA_GREEN)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F))
   );
   public static final Block CYAN_KAOLIN_SLAB = registerBlock("cyan_kaolin_slab",
           new SlabBlock(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.TERRACOTTA_CYAN)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F))
   );
   public static final Block LIGHT_BLUE_KAOLIN_SLAB = registerBlock("light_blue_kaolin_slab",
           new SlabBlock(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.TERRACOTTA_LIGHT_BLUE)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F))
   );
   public static final Block BLUE_KAOLIN_SLAB = registerBlock("blue_kaolin_slab",
           new SlabBlock(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.TERRACOTTA_BLUE)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F))
   );
   public static final Block PURPLE_KAOLIN_SLAB = registerBlock("purple_kaolin_slab",
           new SlabBlock(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.TERRACOTTA_PURPLE)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F))
   );
   public static final Block MAGENTA_KAOLIN_SLAB = registerBlock("magenta_kaolin_slab",
           new SlabBlock(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.TERRACOTTA_MAGENTA)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F))
   );
   public static final Block PINK_KAOLIN_SLAB = registerBlock("pink_kaolin_slab",
           new SlabBlock(BlockBehaviour.Properties.of()
                   .mapColor(MapColor.TERRACOTTA_PINK)
                   .instrument(NoteBlockInstrument.BASEDRUM)
                   .requiresCorrectToolForDrops()
                   .strength(1.25F, 4.2F))
   );

   public HibiscusBlocks() {
   }

   private static Boolean never(BlockState state, BlockGetter world, BlockPos pos, EntityType <?> type) {
      return false;
   }

   private static Block[] getCherrySign() {
      Block[] Array = new Block[16];
      Array[13] = Blocks.CHERRY_SIGN;
      Array[15] = Blocks.CHERRY_HANGING_SIGN;
      return Array;
   }

   public static Block[] registerWoodBlocks(String name, MapColor topMaterialColor, MapColor sideMaterialColor, BlockSetType blockSetType, WoodType woodType) {
      Block[] Array = new Block[17];
      Array[0] = registerBlock(name + "_wood",
              new RotatedPillarBlock(BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(
                      sideMaterialColor).strength(2.0F).sound(SoundType.WOOD))
      );
      Array[1] = registerBlock("stripped_" + name + "_wood",
              new RotatedPillarBlock(BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(
                      topMaterialColor).strength(2.0F).sound(SoundType.WOOD))
      );
      Array[2] = registerBlock(name + "_log",
              new RotatedPillarBlock(BlockBehaviour.Properties.of()
                      .ignitedByLava()
                      .instrument(NoteBlockInstrument.BASS)
                      .mapColor((state) -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topMaterialColor : sideMaterialColor)
                      .strength(2.0F)
                      .sound(SoundType.WOOD))
      );
      Array[3] = registerBlock("stripped_" + name + "_log",
              new RotatedPillarBlock(BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(
                      topMaterialColor).strength(2.0F).sound(SoundType.WOOD))
      );
      Array[4] = registerBlock(name + "_planks",
              new Block(BlockBehaviour.Properties.of()
                      .ignitedByLava()
                      .instrument(NoteBlockInstrument.BASS)
                      .mapColor(topMaterialColor)
                      .strength(2.0F, 3.0F)
                      .sound(SoundType.WOOD))
      );
      Array[5] = registerBlock(name + "_stairs",
              new ModdedStairBlock(Array[4].defaultBlockState(), BlockBehaviour.Properties.copy(Array[4]))
      );
      Array[6] = registerBlock(name + "_slab",
              new SlabBlock(BlockBehaviour.Properties.of()
                      .instrument(NoteBlockInstrument.BASS)
                      .mapColor(topMaterialColor)
                      .strength(2.0f, 3.0f)
                      .sound(SoundType.WOOD))
      );
      Array[7] = registerBlock(name + "_door",
              new ModdedDoorBlock(BlockBehaviour.Properties.copy(Array[4]).noOcclusion(), blockSetType)
      );
      Array[8] = registerBlock(name + "_trapdoor", new ModdedTrapDoorBlock(BlockBehaviour.Properties.of()
              .mapColor(topMaterialColor)
              .ignitedByLava()
              .instrument(NoteBlockInstrument.BASS)
              .strength(3.0f)
              .sound(SoundType.WOOD)
              .isValidSpawn(HibiscusBlocks::never)
              .noOcclusion(), blockSetType));
      Array[9] = registerBlock(name + "_fence",
              new FenceBlock(BlockBehaviour.Properties.copy(Array[4]).noOcclusion().forceSolidOn())
      );
      Array[10] = registerBlock(name + "_fence_gate",
              new FenceGateBlock(BlockBehaviour.Properties.copy(Array[4]).noOcclusion().forceSolidOn(), woodType)
      );
      Array[11] = registerBlock(name + "_pressure_plate",
              new ModdedPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                      BlockBehaviour.Properties.of()
                              .forceSolidOn()
                              .mapColor(topMaterialColor)
                              .noCollission()
                              .strength(0.5f)
                              .sound(SoundType.WOOD),
                      blockSetType
              )
      );
      Array[12] = registerBlock(name + "_button",
              new ModdedButtonBlock(BlockBehaviour.Properties.of()
                      .mapColor(topMaterialColor)
                      .strength(0.5F)
                      .sound(SoundType.WOOD), blockSetType, 30, true)
      );
      Array[13] = registerBlock1(name + "_sign", new StandingSignBlock(BlockBehaviour.Properties.of()
              .mapColor(topMaterialColor)
              .forceSolidOn()
              .instrument(NoteBlockInstrument.BASS)
              .noCollission()
              .strength(1.0F)
              .ignitedByLava(), woodType));
      Array[14] = registerBlock1(name + "_wall_sign", new WallSignBlock(BlockBehaviour.Properties.of()
              .forceSolidOn()
              .instrument(NoteBlockInstrument.BASS)
              .noCollission()
              .strength(1.0F)
              .ignitedByLava()
              .dropsLike(Array[13]), woodType));
      Array[15] = registerBlock1(name + "_hanging_sign", new CeilingHangingSignBlock(BlockBehaviour.Properties.of()
              .mapColor(topMaterialColor)
              .forceSolidOn()
              .instrument(NoteBlockInstrument.BASS)
              .noCollission()
              .strength(1.0F)
              .ignitedByLava()
              .sound(SoundType.HANGING_SIGN), woodType));
      Array[16] = registerBlock1(name + "_wall_hanging_sign", new WallHangingSignBlock(BlockBehaviour.Properties.of()
              .mapColor(topMaterialColor)
              .forceSolidOn()
              .instrument(NoteBlockInstrument.BASS)
              .noCollission()
              .strength(1.0F)
              .ignitedByLava()
              .sound(SoundType.HANGING_SIGN)
              .dropsLike(Array[15]), woodType));

      registerItem(name + "_sign",
              new SignItem(new Item.Properties().stacksTo(16), Array[13], Array[14])
      );
      registerItem(name + "_hanging_sign",
              new HangingSignItem(Array[15], Array[16], new Item.Properties().stacksTo(16))
      );


      return Array;
   }
   public static Block[] registerJoshuaWoodBlocks(String name, MapColor topMaterialColor, BlockSetType blockSetType, WoodType woodType) {
      Block[] Array = new Block[15];

      Array[0] = registerBlock(name + "_log",
              new JoshuaTrunkBlock(BlockBehaviour.Properties.of()
                      .ignitedByLava()
                      .mapColor(MapColor.COLOR_GRAY)
                      .instrument(NoteBlockInstrument.BASS)
                      .requiresCorrectToolForDrops()
                      .strength(2.0F)
                      .sound(SoundType.WOOD))
      );
      Array[1] = registerBlock("stripped_" + name + "_log",
              new JoshuaTrunkBlock(BlockBehaviour.Properties.of()
                      .ignitedByLava()
                      .mapColor(MapColor.COLOR_GRAY)
                      .instrument(NoteBlockInstrument.BASS)
                      .requiresCorrectToolForDrops()
                      .strength(2.0F)
                      .sound(SoundType.WOOD))
      );
      Array[2] = registerBlock(name + "_planks",
              new Block(BlockBehaviour.Properties.of()
                      .ignitedByLava()
                      .instrument(NoteBlockInstrument.BASS)
                      .mapColor(topMaterialColor)
                      .strength(2.0F, 3.0F)
                      .sound(SoundType.WOOD))
      );
      Array[3] = registerBlock(name + "_stairs",
              new ModdedStairBlock(Array[2].defaultBlockState(), BlockBehaviour.Properties.copy(Array[2]))
      );
      Array[4] = registerBlock(name + "_slab",
              new SlabBlock(BlockBehaviour.Properties.of()
                      .instrument(NoteBlockInstrument.BASS)
                      .mapColor(topMaterialColor)
                      .strength(2.0f, 3.0f)
                      .sound(SoundType.WOOD))
      );
      Array[5] = registerBlock(name + "_door",
              new ModdedDoorBlock(BlockBehaviour.Properties.copy(Array[2]).noOcclusion(), blockSetType)
      );
      Array[6] = registerBlock(name + "_trapdoor", new ModdedTrapDoorBlock(BlockBehaviour.Properties.of()
              .mapColor(topMaterialColor)
              .ignitedByLava()
              .instrument(NoteBlockInstrument.BASS)
              .strength(3.0f)
              .sound(SoundType.WOOD)
              .isValidSpawn(HibiscusBlocks::never)
              .noOcclusion(), blockSetType));
      Array[7] = registerBlock(name + "_fence",
              new FenceBlock(BlockBehaviour.Properties.copy(Array[2]).noOcclusion().forceSolidOn())
      );
      Array[8] = registerBlock(name + "_fence_gate",
              new FenceGateBlock(BlockBehaviour.Properties.copy(Array[2]).noOcclusion().forceSolidOn(), woodType)
      );
      Array[9] = registerBlock(name + "_pressure_plate",
              new ModdedPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                      BlockBehaviour.Properties.of()
                              .forceSolidOn()
                              .mapColor(topMaterialColor)
                              .noCollission()
                              .strength(0.5f)
                              .sound(SoundType.WOOD),
                      blockSetType
              )
      );
      Array[10] = registerBlock(name + "_button",
              new ModdedButtonBlock(BlockBehaviour.Properties.of()
                      .mapColor(topMaterialColor)
                      .strength(0.5F)
                      .sound(SoundType.WOOD), blockSetType, 30, true)
      );
      Array[11] = registerBlock1(name + "_sign", new StandingSignBlock(BlockBehaviour.Properties.of()
              .mapColor(topMaterialColor)
              .forceSolidOn()
              .instrument(NoteBlockInstrument.BASS)
              .noCollission()
              .strength(1.0F)
              .ignitedByLava(), woodType));
      Array[12] = registerBlock1(name + "_wall_sign", new WallSignBlock(BlockBehaviour.Properties.of()
              .forceSolidOn()
              .instrument(NoteBlockInstrument.BASS)
              .noCollission()
              .strength(1.0F)
              .ignitedByLava()
              .dropsLike(Array[11]), woodType));
      Array[13] = registerBlock1(name + "_hanging_sign", new CeilingHangingSignBlock(BlockBehaviour.Properties.of()
              .mapColor(topMaterialColor)
              .forceSolidOn()
              .instrument(NoteBlockInstrument.BASS)
              .noCollission()
              .strength(1.0F)
              .ignitedByLava()
              .sound(SoundType.HANGING_SIGN), woodType));
      Array[14] = registerBlock1(name + "_wall_hanging_sign", new WallHangingSignBlock(BlockBehaviour.Properties.of()
              .mapColor(topMaterialColor)
              .forceSolidOn()
              .instrument(NoteBlockInstrument.BASS)
              .noCollission()
              .strength(1.0F)
              .ignitedByLava()
              .sound(SoundType.HANGING_SIGN)
              .dropsLike(Array[13]), woodType));

      registerItem(name + "_sign",
              new SignItem(new Item.Properties().stacksTo(16), Array[11], Array[12])
      );
      registerItem(name + "_hanging_sign",
              new HangingSignItem(Array[13], Array[14], new Item.Properties().stacksTo(16))
      );


      return Array;
   }


   private static Boolean canSpawnUponLeaves(BlockState state, BlockGetter world, BlockPos pos, EntityType <?> type) {
      return type == EntityType.OCELOT || type == EntityType.PARROT;
   }

   private static boolean never(BlockState state, BlockGetter world, BlockPos pos) {
      return false;
   }

   public static Block registerLeafBlock(String name, MapColor color, boolean bl) {
      Block Leaves;
      if(bl) {
         Leaves = registerBlock(name,
                 new WisteriaLeaves(BlockBehaviour.Properties.of()
                         .mapColor(color)
                         .strength(0.2F)
                         .randomTicks()
                         .sound(SoundType.GRASS)
                         .noOcclusion()
                         .isValidSpawn(HibiscusBlocks::canSpawnUponLeaves)
                         .isSuffocating(HibiscusBlocks::never)
                         .isViewBlocking(HibiscusBlocks::never)
                         .ignitedByLava()
                         .pushReaction(PushReaction.DESTROY)
                         .isRedstoneConductor(HibiscusBlocks::never))
         );
      }
      else {
         Leaves = registerBlock(name,
                 new WillowLeaves(BlockBehaviour.Properties.of()
                         .mapColor(color)
                         .strength(0.2F)
                         .randomTicks()
                         .sound(SoundType.GRASS)
                         .noOcclusion()
                         .isValidSpawn(HibiscusBlocks::canSpawnUponLeaves)
                         .isSuffocating(HibiscusBlocks::never)
                         .isViewBlocking(HibiscusBlocks::never)
                         .ignitedByLava()
                         .pushReaction(PushReaction.DESTROY)
                         .isRedstoneConductor(HibiscusBlocks::never))
         );
      }
      return Leaves;
   }

   public static Block registerLeafBlock(String name, MapColor color) {
      Block Leaves;
         Leaves = registerBlock(name,
                 new LeavesBlock(BlockBehaviour.Properties.of()
                         .mapColor(color)
                         .strength(0.2F)
                         .randomTicks()
                         .sound(SoundType.GRASS)
                         .noOcclusion()
                         .isValidSpawn(HibiscusBlocks::canSpawnUponLeaves)
                         .isSuffocating(HibiscusBlocks::never)
                         .isViewBlocking(HibiscusBlocks::never)
                         .ignitedByLava()
                         .pushReaction(PushReaction.DESTROY)
                         .isRedstoneConductor(HibiscusBlocks::never)));
      return Leaves;
   }

   public static Block[] registerSapling(String name, AbstractTreeGrower saplingGenerator) {
      Block[] Plant = new Block[2];
      Plant[0] = registerBlock(name + "_sapling",
              new ModdedSaplingBlock(saplingGenerator, BlockBehaviour.Properties.copy(Blocks.SPRUCE_SAPLING))
      );
      Plant[1] = registerPottedPlant(name + "_sapling", Plant[0]);
      return Plant;
   }

   public static Block[] registerJoshuaSapling(String name, AbstractTreeGrower saplingGenerator) {
      Block[] Plant = new Block[2];
      Plant[0] = registerBlock(name + "_sapling",
              new JoshuaSapling(saplingGenerator, BlockBehaviour.Properties.copy(Blocks.SPRUCE_SAPLING))
      );
      Plant[1] = registerPottedPlant(name + "_sapling", Plant[0]);
      return Plant;
   }

   public static Block registerPottedPlant(String name, Block plant) {
      Block pottedPlant = registerBlock("potted_" + name, new FlowerPotBlock(plant,
              BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)
      ));
      return pottedPlant;
   }

   public static Block registerBlock1(String name, Block block) {
      return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(Constants.MOD_ID, name), block);
   }

   public static Block registerBlock(String name, Block block) {
      registerBlockItem(name, block);
      return registerBlock1(name, block);
   }

   public static void registerBlockItem(String name, Block block) {
      registerItem(name, new BlockItem(block, new Item.Properties()));
   }

   public static Item registerItem(String name, Item item) {
      return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Constants.MOD_ID, name), item);
   }

   public static void registerHibiscusBlocks() {
   }
}
