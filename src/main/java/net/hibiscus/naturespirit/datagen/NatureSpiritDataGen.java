package net.hibiscus.naturespirit.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.blocks.DesertPlantBlock;
import net.hibiscus.naturespirit.blocks.HibiscusBlocks;
import net.hibiscus.naturespirit.items.HibiscusItemGroups;
import net.hibiscus.naturespirit.terrablender.HibiscusBiomes;
import net.hibiscus.naturespirit.world.feature.HibiscusConfiguredFeatures;
import net.hibiscus.naturespirit.world.feature.HibiscusPlacedFeatures;
import net.minecraft.block.Block;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.data.client.*;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.*;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static net.hibiscus.naturespirit.blocks.HibiscusBlocks.*;
import static net.hibiscus.naturespirit.blocks.HibiscusBlocks.LeavesHashMap;
import static net.minecraft.data.client.BlockStateModelGenerator.*;
import static net.minecraft.data.family.BlockFamilies.register;

public class NatureSpiritDataGen implements DataGeneratorEntrypoint {
   static HashMap<String, TagKey <Block>> blockLogTags = new HashMap<>();
   static HashMap<String, TagKey <Item>> itemLogTags = new HashMap<>();
   public static TagKey <Item> joshuaItemLogtag = TagKey.of(RegistryKeys.ITEM, new Identifier(NatureSpirit.MOD_ID, "joshua_logs"));
   public static TagKey <Block> joshuaBlockLogtag = TagKey.of(RegistryKeys.BLOCK, new Identifier(NatureSpirit.MOD_ID, "joshua_logs"));

   @Override public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
      FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

      pack.addProvider(NatureSpiritWorldGenerator::new);
      pack.addProvider(NatureSpiritModelGenerator::new);
      pack.addProvider(NatureSpiritLangGenerator::new);
      pack.addProvider(NatureSpiritRecipeGenerator::new);
      pack.addProvider(NatureSpiritBlockLootTableProvider::new);
      NatureSpiritBlockTagGenerator blockTagProvider = pack.addProvider(NatureSpiritBlockTagGenerator::new);
      pack.addProvider((output, registries) -> new NatureSpiritItemTagGenerator(output, registries, blockTagProvider));
      System.out.println("Initialized Data Generator");
      registerWoodTypes();
   }

   @Override public void buildRegistry(RegistryBuilder registryBuilder) {
      registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, HibiscusConfiguredFeatures::bootstrap);
      registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, HibiscusPlacedFeatures::bootstrap);
      registryBuilder.addRegistry(RegistryKeys.BIOME, HibiscusBiomes::bootstrap);
      System.out.println("Built Registry");
   }

   private void registerWoodTypes() {
      for(String i: WoodHashMap.keySet()) {
         blockLogTags.put(i, TagKey.of(RegistryKeys.BLOCK, new Identifier(NatureSpirit.MOD_ID, i + "_logs")));
         itemLogTags.put(i, TagKey.of(RegistryKeys.ITEM, new Identifier(NatureSpirit.MOD_ID, i + "_logs")));
      }
   }


   @Override public String getEffectiveModId() {
      return NatureSpirit.MOD_ID;
   }


   private static class NatureSpiritBlockLootTableProvider extends FabricBlockLootTableProvider {
      private static final LootCondition.Builder WITH_SILK_TOUCH_OR_SHEARS = WITH_SHEARS.or(WITH_SILK_TOUCH);
      private static final LootCondition.Builder WITHOUT_SILK_TOUCH_NOR_SHEARS = WITH_SILK_TOUCH_OR_SHEARS.invert();
      private final HashMap <Identifier, LootTable.Builder> map = new HashMap();

      protected NatureSpiritBlockLootTableProvider(FabricDataOutput dataOutput) {
         super(dataOutput);
      }

      private void addWoodTable(HashMap<String, Block[]> woods) {
         for(String i: woods.keySet()) {
            Block[] woodType = woods.get(i);
            addDrop(woodType[0]);
            addDrop(woodType[1]);
            addDrop(woodType[2]);
            addDrop(woodType[3]);
            addDrop(woodType[4]);
            addDrop(woodType[12]);
            this.addDrop(woodType[7], this::doorDrops);
            addDrop(woodType[5]);
            this.slabDrops(woodType[6]);
            addDrop(woodType[9]);
            addDrop(woodType[2]);
            addDrop(woodType[8]);
            addDrop(woodType[13]);
            addDrop(woodType[15]);
            addDrop(woodType[11]);
            addDrop(woodType[10]);
         }
      }

      private void addJoshuaWoodTable() {
            addDrop(HibiscusBlocks.JOSHUA[0]);
            addDrop(HibiscusBlocks.JOSHUA[1]);
            addDrop(HibiscusBlocks.JOSHUA[2]);
            addDrop(HibiscusBlocks.JOSHUA[10]);
            this.addDrop(HibiscusBlocks.JOSHUA[5], this::doorDrops);
            addDrop(HibiscusBlocks.JOSHUA[3]);
            this.slabDrops(HibiscusBlocks.JOSHUA[4]);
            addDrop(HibiscusBlocks.JOSHUA[7]);
            addDrop(HibiscusBlocks.JOSHUA[6]);
            addDrop(HibiscusBlocks.JOSHUA[11]);
            addDrop(HibiscusBlocks.JOSHUA[13]);
            addDrop(HibiscusBlocks.JOSHUA[9]);
            addDrop(HibiscusBlocks.JOSHUA[8]);
      }

      public net.minecraft.loot.LootTable.Builder blackOlivesDrop(Block leaves, Block drop, float... chance) {
         return this.leavesDrops(leaves, drop, chance).pool(LootPool.builder()
                 .rolls(ConstantLootNumberProvider.create(1.0F))
                 .conditionally(WITHOUT_SILK_TOUCH_NOR_SHEARS)
                 .with(((net.minecraft.loot.entry.LeafEntry.Builder <?>) this.addSurvivesExplosionCondition(leaves,
                         ItemEntry.builder(HibiscusBlocks.BLACK_OLIVES)
                 )).conditionally(TableBonusLootCondition.builder(Enchantments.FORTUNE,
                         0.01F,
                         0.0111111114F,
                         0.0125F,
                         0.016666668F,
                         0.05F
                 ))));
      }

      public net.minecraft.loot.LootTable.Builder greenOlivesDrop(Block leaves, Block drop, float... chance) {
         return this.blackOlivesDrop(leaves, drop, chance).pool(LootPool.builder()
                 .rolls(ConstantLootNumberProvider.create(1.0F))
                 .conditionally(WITHOUT_SILK_TOUCH_NOR_SHEARS)
                 .with(((net.minecraft.loot.entry.LeafEntry.Builder <?>) this.addSurvivesExplosionCondition(leaves,
                         ItemEntry.builder(HibiscusBlocks.GREEN_OLIVES)
                 )).conditionally(TableBonusLootCondition.builder(Enchantments.FORTUNE,
                         0.01F,
                         0.0111111114F,
                         0.0125F,
                         0.016666668F,
                         0.05F
                 ))));
      }

      private final float[] SAPLING_DROP_CHANCE_2 = new float[]{0.08333333336F, 0.1F, 0.133333F, 0.1625F};
      private void addTreeTable(HashMap<String, Block[]> saplings, HashMap<String, Block> leaves) {
         for(String i: saplings.keySet()) {
            Block[] saplingType = saplings.get(i);
            Block leavesType = leaves.get(i);
            addDrop(saplingType[0]);
            addPottedPlantDrops(saplingType[1]);
            if(i.equals("olive")) {
               this.addDrop(leavesType, (block) -> this.greenOlivesDrop(block, saplingType[0], SAPLING_DROP_CHANCE));
            } else
            if(i.equals("joshua")) {
               this.addDrop(leavesType, (block) -> this.leavesDrops(block, saplingType[0], SAPLING_DROP_CHANCE_2));
            }
            else {
               this.addDrop(leavesType, (block) -> this.leavesDrops(block, saplingType[0], SAPLING_DROP_CHANCE));
            }
         }
      }

      private void addVinesTable(Block vine, Block vinePlant) {
         this.addVinePlantDrop(vine, vinePlant);
      }

      public static LootTable.Builder dropsWithShears(ItemConvertible drop) {
         LootTable.builder().pool(LootPool.builder()
                 .rolls(ConstantLootNumberProvider.create(1.0F))
                 .conditionally(WITH_SHEARS)
                 .with(ItemEntry.builder(drop)));
         return null;
      }

      public void tallScorchedGrassDrops(Block tallGrass, Block grass) {
         net.minecraft.loot.entry.LootPoolEntry.Builder<?> builder = ItemEntry.builder(grass).apply(
                 SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F)));
         LootTable.builder().pool(LootPool.builder()
                 .with(builder)
                 .conditionally(BlockStatePropertyLootCondition.builder(tallGrass)
                         .properties(net.minecraft.predicate.StatePredicate.Builder.create()
                                 .exactMatch(TallPlantBlock.HALF, DoubleBlockHalf.LOWER)))
                 .conditionally(LocationCheckLootCondition.builder(net.minecraft.predicate.entity.LocationPredicate.Builder.create()
                         .block(net.minecraft.predicate.BlockPredicate.Builder.create()
                                 .blocks(new Block[]{tallGrass})
                                 .state(net.minecraft.predicate.StatePredicate.Builder.create()
                                         .exactMatch(TallPlantBlock.HALF, DoubleBlockHalf.UPPER)
                                         .build())
                                 .build()), new BlockPos(0, 1, 0)))).pool(LootPool.builder()
                 .with(builder)
                 .conditionally(BlockStatePropertyLootCondition.builder(tallGrass)
                         .properties(net.minecraft.predicate.StatePredicate.Builder.create()
                                 .exactMatch(TallPlantBlock.HALF, DoubleBlockHalf.UPPER)))
                 .conditionally(LocationCheckLootCondition.builder(net.minecraft.predicate.entity.LocationPredicate.Builder.create()
                         .block(net.minecraft.predicate.BlockPredicate.Builder.create()
                                 .blocks(new Block[]{tallGrass})
                                 .state(net.minecraft.predicate.StatePredicate.Builder.create()
                                         .exactMatch(TallPlantBlock.HALF, DoubleBlockHalf.LOWER)
                                         .build())
                                 .build()), new BlockPos(0, -1, 0))));
      }

      @Override public void generate() {
         addWoodTable(WoodHashMap);
         addJoshuaWoodTable();
         addTreeTable(SaplingHashmap, LeavesHashMap);

         addVinesTable(HibiscusBlocks.WHITE_WISTERIA_VINES, HibiscusBlocks.WHITE_WISTERIA_VINES_PLANT);
         addVinesTable(HibiscusBlocks.BLUE_WISTERIA_VINES, HibiscusBlocks.BLUE_WISTERIA_VINES_PLANT);
         addVinesTable(HibiscusBlocks.PURPLE_WISTERIA_VINES, HibiscusBlocks.PURPLE_WISTERIA_VINES_PLANT);
         addVinesTable(HibiscusBlocks.PINK_WISTERIA_VINES, HibiscusBlocks.PINK_WISTERIA_VINES_PLANT);
         addVinesTable(HibiscusBlocks.WILLOW_VINES, HibiscusBlocks.WILLOW_VINES_PLANT);

         this.addDrop(HibiscusBlocks.CARNATION, (block) -> this.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
         this.addDrop(HibiscusBlocks.CATTAIL, (block) -> this.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
         this.addDrop(HibiscusBlocks.GARDENIA, (block) -> this.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
         this.addDrop(HibiscusBlocks.SNAPDRAGON, (block) -> this.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
         this.addDrop(HibiscusBlocks.MARIGOLD, (block) -> this.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
         this.addDrop(HibiscusBlocks.FOXGLOVE, (block) -> this.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
         this.addDrop(HibiscusBlocks.LAVENDER, (block) -> this.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
         this.addDrop(HibiscusBlocks.BLEEDING_HEART, (block) -> this.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
         this.addDrop(HibiscusBlocks.TIGER_LILY, (block) -> this.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));

         this.addDrop(HibiscusBlocks.ANEMONE);
         addPottedPlantDrops(HibiscusBlocks.POTTED_ANEMONE);
         this.addDrop(HibiscusBlocks.HIBISCUS);
         addPottedPlantDrops(HibiscusBlocks.POTTED_HIBISCUS);
         this.addDrop(HibiscusBlocks.BLUEBELL);
         this.addDrop(HibiscusBlocks.TIGER_LILY);
         this.addDrop(HibiscusBlocks.PURPLE_WILDFLOWER);
         this.addDrop(HibiscusBlocks.YELLOW_WILDFLOWER);


         this.addDrop(HibiscusBlocks.FRAMED_SUGI_DOOR, this::doorDrops);
         this.addDrop(HibiscusBlocks.FRAMED_SUGI_TRAPDOOR);
         this.addDrop(HibiscusBlocks.SANDY_SOIL);
         this.addDrop(HibiscusBlocks.KAOLIN);
         this.addDrop(HibiscusBlocks.WHITE_KAOLIN);
         this.addDrop(HibiscusBlocks.LIGHT_GRAY_KAOLIN);
         this.addDrop(HibiscusBlocks.GRAY_KAOLIN);
         this.addDrop(HibiscusBlocks.BLACK_KAOLIN);
         this.addDrop(HibiscusBlocks.BROWN_KAOLIN);
         this.addDrop(HibiscusBlocks.RED_KAOLIN);
         this.addDrop(HibiscusBlocks.ORANGE_KAOLIN);
         this.addDrop(HibiscusBlocks.YELLOW_KAOLIN);
         this.addDrop(HibiscusBlocks.LIME_KAOLIN);
         this.addDrop(HibiscusBlocks.GREEN_KAOLIN);
         this.addDrop(HibiscusBlocks.CYAN_KAOLIN);
         this.addDrop(HibiscusBlocks.LIGHT_BLUE_KAOLIN);
         this.addDrop(HibiscusBlocks.BLUE_KAOLIN);
         this.addDrop(HibiscusBlocks.PURPLE_KAOLIN);
         this.addDrop(HibiscusBlocks.MAGENTA_KAOLIN);
         this.addDrop(HibiscusBlocks.PINK_KAOLIN);
         this.slabDrops(HibiscusBlocks.KAOLIN_SLAB);
         this.slabDrops(HibiscusBlocks.WHITE_KAOLIN_SLAB);
         this.slabDrops(HibiscusBlocks.LIGHT_GRAY_KAOLIN_SLAB);
         this.slabDrops(HibiscusBlocks.GRAY_KAOLIN_SLAB);
         this.slabDrops(HibiscusBlocks.BLACK_KAOLIN_SLAB);
         this.slabDrops(HibiscusBlocks.BROWN_KAOLIN_SLAB);
         this.slabDrops(HibiscusBlocks.RED_KAOLIN_SLAB);
         this.slabDrops(HibiscusBlocks.ORANGE_KAOLIN_SLAB);
         this.slabDrops(HibiscusBlocks.YELLOW_KAOLIN_SLAB);
         this.slabDrops(HibiscusBlocks.LIME_KAOLIN_SLAB);
         this.slabDrops(HibiscusBlocks.GREEN_KAOLIN_SLAB);
         this.slabDrops(HibiscusBlocks.CYAN_KAOLIN_SLAB);
         this.slabDrops(HibiscusBlocks.LIGHT_BLUE_KAOLIN_SLAB);
         this.slabDrops(HibiscusBlocks.BLUE_KAOLIN_SLAB);
         this.slabDrops(HibiscusBlocks.PURPLE_KAOLIN_SLAB);
         this.slabDrops(HibiscusBlocks.MAGENTA_KAOLIN_SLAB);
         this.slabDrops(HibiscusBlocks.PINK_KAOLIN_SLAB);
         this.addDrop(HibiscusBlocks.KAOLIN_STAIRS);
         this.addDrop(HibiscusBlocks.WHITE_KAOLIN_STAIRS);
         this.addDrop(HibiscusBlocks.LIGHT_GRAY_KAOLIN_STAIRS);
         this.addDrop(HibiscusBlocks.GRAY_KAOLIN_STAIRS);
         this.addDrop(HibiscusBlocks.BLACK_KAOLIN_STAIRS);
         this.addDrop(HibiscusBlocks.BROWN_KAOLIN_STAIRS);
         this.addDrop(HibiscusBlocks.RED_KAOLIN_STAIRS);
         this.addDrop(HibiscusBlocks.ORANGE_KAOLIN_STAIRS);
         this.addDrop(HibiscusBlocks.YELLOW_KAOLIN_STAIRS);
         this.addDrop(HibiscusBlocks.LIME_KAOLIN_STAIRS);
         this.addDrop(HibiscusBlocks.GREEN_KAOLIN_STAIRS);
         this.addDrop(HibiscusBlocks.CYAN_KAOLIN_STAIRS);
         this.addDrop(HibiscusBlocks.LIGHT_BLUE_KAOLIN_STAIRS);
         this.addDrop(HibiscusBlocks.BLUE_KAOLIN_STAIRS);
         this.addDrop(HibiscusBlocks.PURPLE_KAOLIN_STAIRS);
         this.addDrop(HibiscusBlocks.MAGENTA_KAOLIN_STAIRS);
         this.addDrop(HibiscusBlocks.PINK_KAOLIN_STAIRS);

         this.addDrop(HibiscusBlocks.DESERT_TURNIP_ROOT_BLOCK);

         dropsWithShears(HibiscusBlocks.SCORCHED_GRASS);
         tallScorchedGrassDrops(HibiscusBlocks.TALL_SCORCHED_GRASS, HibiscusBlocks.SCORCHED_GRASS);

      }
   }

   private static class NatureSpiritModelGenerator extends FabricModelProvider {

      private static final Model TALL_LARGE_CROSS = block("tall_large_cross", TextureKey.CROSS);
      private static final Model LARGE_CROSS = block("large_cross", TextureKey.CROSS);
      private static final Model TALL_CROSS = block("tall_cross", TextureKey.CROSS);
      private static final Model FLOWER_POT_TALL_CROSS = block("flower_pot_tall_cross", TextureKey.PLANT);
      private static final Model CROP = block("crop", TextureKey.CROP);

      public NatureSpiritModelGenerator(FabricDataOutput output) {
         super(output);
      }

      private static Model block(String parent, TextureKey... requiredTextureKeys) {
         return new Model(Optional.of(new Identifier("natures_spirit", "block/" + parent)),
                 Optional.empty(),
                 requiredTextureKeys
         );
      }

      public static Identifier getId(Block block) {
         Identifier identifier = Registries.BLOCK.getId(block);
         return identifier.withPrefixedPath("block/");
      }

      private void createSlab(Block block, Block slab, BlockStateModelGenerator blockStateModelGenerator) {
         Identifier resourceLocation = ModelIds.getBlockModelId(block);
         TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(block);
         Identifier resourceLocation2 = Models.SLAB.upload(slab,
                 texturedModel.getTextures(),
                 blockStateModelGenerator.modelCollector
         );
         Identifier resourceLocation3 = Models.SLAB_TOP.upload(slab,
                 texturedModel.getTextures(),
                 blockStateModelGenerator.modelCollector
         );
         blockStateModelGenerator.blockStateCollector.accept(createSlabBlockState(slab,
                 resourceLocation2,
                 resourceLocation3,
                 resourceLocation
         ));
      }

      private void createStairs(Block block, Block stairs, BlockStateModelGenerator blockStateModelGenerator) {
         TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(block);
         Identifier resourceLocation = Models.INNER_STAIRS.upload(stairs,
                 texturedModel.getTextures(),
                 blockStateModelGenerator.modelCollector
         );
         Identifier resourceLocation2 = Models.STAIRS.upload(stairs,
                 texturedModel.getTextures(),
                 blockStateModelGenerator.modelCollector
         );
         Identifier resourceLocation3 = Models.OUTER_STAIRS.upload(stairs,
                 texturedModel.getTextures(),
                 blockStateModelGenerator.modelCollector
         );
         blockStateModelGenerator.blockStateCollector.accept(createStairsBlockState(stairs,
                 resourceLocation,
                 resourceLocation2,
                 resourceLocation3
         ));
         blockStateModelGenerator.registerParentedItemModel(stairs, resourceLocation2);
      }

      public void createWoodDoor(Block doorBlock, BlockStateModelGenerator blockStateModelGenerator) {
         TextureMap textureMapping = TextureMap.topBottom(doorBlock);
         Identifier resourceLocation = Models.DOOR_BOTTOM_LEFT.upload(doorBlock,
                 textureMapping,
                 blockStateModelGenerator.modelCollector
         );
         Identifier resourceLocation2 = Models.DOOR_BOTTOM_LEFT_OPEN.upload(doorBlock,
                 textureMapping,
                 blockStateModelGenerator.modelCollector
         );
         Identifier resourceLocation3 = Models.DOOR_BOTTOM_RIGHT.upload(doorBlock,
                 textureMapping,
                 blockStateModelGenerator.modelCollector
         );
         Identifier resourceLocation4 = Models.DOOR_BOTTOM_RIGHT_OPEN.upload(doorBlock,
                 textureMapping,
                 blockStateModelGenerator.modelCollector
         );
         Identifier resourceLocation5 = Models.DOOR_TOP_LEFT.upload(doorBlock,
                 textureMapping,
                 blockStateModelGenerator.modelCollector
         );
         Identifier resourceLocation6 = Models.DOOR_TOP_LEFT_OPEN.upload(doorBlock,
                 textureMapping,
                 blockStateModelGenerator.modelCollector
         );
         Identifier resourceLocation7 = Models.DOOR_TOP_RIGHT.upload(doorBlock,
                 textureMapping,
                 blockStateModelGenerator.modelCollector
         );
         Identifier resourceLocation8 = Models.DOOR_TOP_RIGHT_OPEN.upload(doorBlock,
                 textureMapping,
                 blockStateModelGenerator.modelCollector
         );
         blockStateModelGenerator.registerItemModel(doorBlock.asItem());
         blockStateModelGenerator.blockStateCollector.accept(createDoorBlockState(doorBlock,
                 resourceLocation,
                 resourceLocation2,
                 resourceLocation3,
                 resourceLocation4,
                 resourceLocation5,
                 resourceLocation6,
                 resourceLocation7,
                 resourceLocation8
         ));
      }

      public void createWoodTrapdoor(Block orientableTrapdoorBlock, BlockStateModelGenerator blockStateModelGenerators) {
         TextureMap textureMapping = TextureMap.texture(orientableTrapdoorBlock);
         Identifier resourceLocation = Models.TEMPLATE_ORIENTABLE_TRAPDOOR_TOP.upload(orientableTrapdoorBlock,
                 textureMapping,
                 blockStateModelGenerators.modelCollector
         );
         Identifier resourceLocation2 = Models.TEMPLATE_ORIENTABLE_TRAPDOOR_BOTTOM.upload(orientableTrapdoorBlock,
                 textureMapping,
                 blockStateModelGenerators.modelCollector
         );
         Identifier resourceLocation3 = Models.TEMPLATE_ORIENTABLE_TRAPDOOR_OPEN.upload(orientableTrapdoorBlock,
                 textureMapping,
                 blockStateModelGenerators.modelCollector
         );
         blockStateModelGenerators.blockStateCollector.accept(createOrientableTrapdoorBlockState(orientableTrapdoorBlock,
                 resourceLocation,
                 resourceLocation2,
                 resourceLocation3
         ));
         blockStateModelGenerators.registerParentedItemModel(orientableTrapdoorBlock, resourceLocation2);
      }

      public void createWoodFenceGate(Block planks, Block fenceGateBlock, BlockStateModelGenerator blockStateModelGenerator) {
         TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(planks);
         Identifier resourceLocation = Models.TEMPLATE_FENCE_GATE_OPEN.upload(fenceGateBlock,
                 texturedModel.getTextures(),
                 blockStateModelGenerator.modelCollector
         );
         Identifier resourceLocation2 = Models.TEMPLATE_FENCE_GATE.upload(fenceGateBlock,
                 texturedModel.getTextures(),
                 blockStateModelGenerator.modelCollector
         );
         Identifier resourceLocation3 = Models.TEMPLATE_FENCE_GATE_WALL_OPEN.upload(fenceGateBlock,
                 texturedModel.getTextures(),
                 blockStateModelGenerator.modelCollector
         );
         Identifier resourceLocation4 = Models.TEMPLATE_FENCE_GATE_WALL.upload(fenceGateBlock,
                 texturedModel.getTextures(),
                 blockStateModelGenerator.modelCollector
         );
         blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createFenceGateBlockState(fenceGateBlock,
                 resourceLocation,
                 resourceLocation2,
                 resourceLocation3,
                 resourceLocation4,
                 true
         ));
      }

      public void createWoodFence(Block planks, Block fenceBlock, BlockStateModelGenerator blockStateModelGenerators) {
         TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(planks);
         Identifier resourceLocation = Models.FENCE_POST.upload(fenceBlock,
                 texturedModel.getTextures(),
                 blockStateModelGenerators.modelCollector
         );
         Identifier resourceLocation2 = Models.FENCE_SIDE.upload(fenceBlock,
                 texturedModel.getTextures(),
                 blockStateModelGenerators.modelCollector
         );
         blockStateModelGenerators.blockStateCollector.accept(BlockStateModelGenerator.createFenceBlockState(fenceBlock,
                 resourceLocation,
                 resourceLocation2
         ));
         Identifier resourceLocation3 = Models.FENCE_INVENTORY.upload(fenceBlock,
                 texturedModel.getTextures(),
                 blockStateModelGenerators.modelCollector
         );
         blockStateModelGenerators.registerParentedItemModel(fenceBlock, resourceLocation3);
      }

      public void createWoodPressurePlate(Block planks, Block pressurePlateBlock, BlockStateModelGenerator blockStateModelGenerator) {
         TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(planks);
         Identifier resourceLocation = Models.PRESSURE_PLATE_UP.upload(pressurePlateBlock,
                 texturedModel.getTextures(),
                 blockStateModelGenerator.modelCollector
         );
         Identifier resourceLocation2 = Models.PRESSURE_PLATE_DOWN.upload(pressurePlateBlock,
                 texturedModel.getTextures(),
                 blockStateModelGenerator.modelCollector
         );
         blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createPressurePlateBlockState(pressurePlateBlock,
                 resourceLocation,
                 resourceLocation2
         ));
      }

      public void createWoodSign(Block signBlock, Block wallSignBlock, BlockStateModelGenerator blockStateModelGenerator) {
         TextureMap textureMapping = TextureMap.texture(signBlock);
         Identifier resourceLocation = Models.PARTICLE.upload(signBlock,
                 textureMapping,
                 blockStateModelGenerator.modelCollector
         );
         blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(signBlock,
                 resourceLocation
         ));
         blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(wallSignBlock,
                 resourceLocation
         ));
         blockStateModelGenerator.registerItemModel(signBlock.asItem());
         blockStateModelGenerator.excludeFromSimpleItemModelGeneration(wallSignBlock);
      }

      public void createWoodButton(Block planks, Block buttonBlock, BlockStateModelGenerator blockStateModelGenerators) {
         TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(planks);
         Identifier resourceLocation = Models.BUTTON.upload(buttonBlock,
                 texturedModel.getTextures(),
                 blockStateModelGenerators.modelCollector
         );
         Identifier resourceLocation2 = Models.BUTTON_PRESSED.upload(buttonBlock,
                 texturedModel.getTextures(),
                 blockStateModelGenerators.modelCollector
         );
         blockStateModelGenerators.blockStateCollector.accept(BlockStateModelGenerator.createButtonBlockState(buttonBlock,
                 resourceLocation,
                 resourceLocation2
         ));
         Identifier resourceLocation3 = Models.BUTTON_INVENTORY.upload(buttonBlock,
                 texturedModel.getTextures(),
                 blockStateModelGenerators.modelCollector
         );
         blockStateModelGenerators.registerParentedItemModel(buttonBlock, resourceLocation3);
      }

      public void createHangingSign(Block strippedLog, Block hangingSign, Block wallHangingSign, BlockStateModelGenerator blockStateModelGenerator) {
         TextureMap textureMap = TextureMap.particle(strippedLog);
         Identifier identifier = Models.PARTICLE.upload(hangingSign,
                 textureMap,
                 blockStateModelGenerator.modelCollector
         );
         blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(hangingSign, identifier));
         blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(wallHangingSign, identifier));
         blockStateModelGenerator.registerItemModel(hangingSign.asItem());
         blockStateModelGenerator.excludeFromSimpleItemModelGeneration(wallHangingSign);
      }

      private void generateWoodBlockStateModels(HashMap<String, Block[]> woods, BlockStateModelGenerator blockStateModelGenerator) {
         for(String i: woods.keySet()) {
            Block[] woodType = woods.get(i);
            blockStateModelGenerator.registerLog(woodType[2]).log(woodType[2]).wood(woodType[0]);
            blockStateModelGenerator.registerLog(woodType[3]).log(woodType[3]).wood(woodType[1]);
            blockStateModelGenerator.registerSingleton(woodType[4], TexturedModel.CUBE_ALL);
            createSlab(woodType[4], woodType[6], blockStateModelGenerator);
            createStairs(woodType[4], woodType[5], blockStateModelGenerator);
            createWoodDoor(woodType[7], blockStateModelGenerator);
            createWoodTrapdoor(woodType[8], blockStateModelGenerator);
            createWoodFenceGate(woodType[4], woodType[10], blockStateModelGenerator);
            createWoodFence(woodType[4], woodType[9], blockStateModelGenerator);
            createWoodButton(woodType[4], woodType[12], blockStateModelGenerator);
            createWoodPressurePlate(woodType[4], woodType[11], blockStateModelGenerator);
            createWoodSign(woodType[13], woodType[14], blockStateModelGenerator);
            createHangingSign(woodType[3], woodType[15], woodType[16], blockStateModelGenerator);
         }
      }

      private void generateJoshuaWoodBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
            blockStateModelGenerator.registerSingleton(HibiscusBlocks.JOSHUA[2], TexturedModel.CUBE_ALL);
            createSlab(HibiscusBlocks.JOSHUA[2], HibiscusBlocks.JOSHUA[4], blockStateModelGenerator);
            createStairs(HibiscusBlocks.JOSHUA[2], HibiscusBlocks.JOSHUA[3], blockStateModelGenerator);
            createWoodDoor(HibiscusBlocks.JOSHUA[5], blockStateModelGenerator);
            createWoodTrapdoor(HibiscusBlocks.JOSHUA[6], blockStateModelGenerator);
            createWoodFenceGate(HibiscusBlocks.JOSHUA[2], HibiscusBlocks.JOSHUA[8], blockStateModelGenerator);
            createWoodFence(HibiscusBlocks.JOSHUA[2], HibiscusBlocks.JOSHUA[7], blockStateModelGenerator);
            createWoodButton(HibiscusBlocks.JOSHUA[2], HibiscusBlocks.JOSHUA[10], blockStateModelGenerator);
            createWoodPressurePlate(HibiscusBlocks.JOSHUA[2], HibiscusBlocks.JOSHUA[9], blockStateModelGenerator);
            createWoodSign(HibiscusBlocks.JOSHUA[11], HibiscusBlocks.JOSHUA[12], blockStateModelGenerator);
            createHangingSign(HibiscusBlocks.JOSHUA[1], HibiscusBlocks.JOSHUA[13], HibiscusBlocks.JOSHUA[14], blockStateModelGenerator);
      }

      private void generateTreeBlockStateModels(HashMap<String, Block[]> saplings, HashMap<String, Block> leaves, BlockStateModelGenerator blockStateModelGenerator) {
         for(String i: leaves.keySet()) {
            Block[] saplingType = saplings.get(i);
            Block leavesType = leaves.get(i);
            blockStateModelGenerator.registerSingleton(leavesType, TexturedModel.LEAVES);
            blockStateModelGenerator.registerFlowerPotPlant(saplingType[0], saplingType[1], TintType.NOT_TINTED);
         }
      }

      public final void registerTallCrossBlockState(Block block, TextureMap crossTexture, BlockStateModelGenerator blockStateModelGenerators) {
         Identifier identifier = TALL_CROSS.upload(block, crossTexture, blockStateModelGenerators.modelCollector);
         blockStateModelGenerators.blockStateCollector.accept(createSingletonBlockState(block, identifier));
      }

      public final void registerVineBlockState(Block block, TextureMap crossTexture, BlockStateModelGenerator blockStateModelGenerators) {
         Identifier identifier = CROP.upload(block, crossTexture, blockStateModelGenerators.modelCollector);
         blockStateModelGenerators.blockStateCollector.accept(createSingletonBlockState(block, identifier));
      }

      public final void registerTallLargeBlockState(Block block, TextureMap crossTexture, BlockStateModelGenerator blockStateModelGenerators) {
         Identifier identifier = TALL_LARGE_CROSS.upload(block, crossTexture, blockStateModelGenerators.modelCollector);
         blockStateModelGenerators.blockStateCollector.accept(createSingletonBlockState(block, identifier));
      }

      public final void registerSpecificFlowerItemModel(Block block, BlockStateModelGenerator blockStateModelGenerators) {
         Item item = block.asItem();
         Models.GENERATED.upload(ModelIds.getItemModelId(item),
                 TextureMap.layer0(item),
                 blockStateModelGenerators.modelCollector
         );
      }

      private void generateFlowerBlockStateModels(Block block, Block block2, BlockStateModelGenerator blockStateModelGenerator) {
         blockStateModelGenerator.registerFlowerPotPlant(block, block2, TintType.NOT_TINTED);
      }

      private void generatePottedAnemone(Block block, Block flowerPot, BlockStateModelGenerator blockStateModelGenerators) {
         registerSpecificFlowerItemModel(block, blockStateModelGenerators);
         TextureMap textureMap1 = TextureMap.cross(block);
         registerTallCrossBlockState(block, textureMap1, blockStateModelGenerators);
         TextureMap textureMap = TextureMap.plant(block);
         Identifier identifier = FLOWER_POT_TALL_CROSS.upload(flowerPot,
                 textureMap,
                 blockStateModelGenerators.modelCollector
         );
         blockStateModelGenerators.blockStateCollector.accept(createSingletonBlockState(flowerPot, identifier));
      }

      public final void generateVineBlockStateModels(Block plant, Block plantStem, BlockStateModelGenerator blockStateModelGenerators) {
         TextureMap textureMap1 = TextureMap.crop(getId(plant));
         this.registerVineBlockState(plant, textureMap1, blockStateModelGenerators);
         TextureMap textureMap2 = TextureMap.crop(getId(plantStem));
         this.registerVineBlockState(plantStem, textureMap2, blockStateModelGenerators);
         blockStateModelGenerators.registerItemModel(plant, "_plant");
      }

      public final void generateTallLargeFlower(Block doubleBlock, BlockStateModelGenerator blockStateModelGenerators) {
         registerSpecificFlowerItemModel(doubleBlock, blockStateModelGenerators);
         Identifier identifier = blockStateModelGenerators.createSubModel(doubleBlock,
                 "_top",
                 LARGE_CROSS,
                 TextureMap::cross
         );
         Identifier identifier2 = blockStateModelGenerators.createSubModel(doubleBlock,
                 "_bottom",
                 LARGE_CROSS,
                 TextureMap::cross
         );
         blockStateModelGenerators.registerDoubleBlock(doubleBlock, identifier, identifier2);
      }

      public final void generateLargeFlower(Block block, BlockStateModelGenerator blockStateModelGenerators) {
         registerSpecificFlowerItemModel(block, blockStateModelGenerators);
         registerTallLargeBlockState(block, TextureMap.cross(block), blockStateModelGenerators);
      }

      @Override public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
         generateWoodBlockStateModels(WoodHashMap, blockStateModelGenerator);
         generateJoshuaWoodBlockStateModels(blockStateModelGenerator);
         generateTreeBlockStateModels(SaplingHashmap, LeavesHashMap, blockStateModelGenerator);
         generateFlowerBlockStateModels(HibiscusBlocks.HIBISCUS,
                 HibiscusBlocks.POTTED_HIBISCUS,
                 blockStateModelGenerator
         );
         blockStateModelGenerator.registerCrop(HibiscusBlocks.DESERT_TURNIP_STEM, DesertPlantBlock.AGE, 0, 1, 2, 3, 4, 5, 6, 7);
         blockStateModelGenerator.registerDoubleBlock(HibiscusBlocks.CARNATION, TintType.NOT_TINTED);
         blockStateModelGenerator.registerDoubleBlock(HibiscusBlocks.GARDENIA, TintType.NOT_TINTED);
         blockStateModelGenerator.registerDoubleBlock(HibiscusBlocks.SNAPDRAGON, TintType.NOT_TINTED);
         blockStateModelGenerator.registerDoubleBlock(HibiscusBlocks.MARIGOLD, TintType.NOT_TINTED);
         blockStateModelGenerator.registerDoubleBlock(HibiscusBlocks.FOXGLOVE, TintType.NOT_TINTED);
         generateTallLargeFlower(HibiscusBlocks.TALL_SCORCHED_GRASS, blockStateModelGenerator);
         generateTallLargeFlower(HibiscusBlocks.LAVENDER, blockStateModelGenerator);
         generateTallLargeFlower(HibiscusBlocks.BLEEDING_HEART, blockStateModelGenerator);
         generateLargeFlower(HibiscusBlocks.BLUEBELL, blockStateModelGenerator);
         generateLargeFlower(HibiscusBlocks.TIGER_LILY, blockStateModelGenerator);
         generateLargeFlower(HibiscusBlocks.PURPLE_WILDFLOWER, blockStateModelGenerator);
         generateLargeFlower(HibiscusBlocks.YELLOW_WILDFLOWER, blockStateModelGenerator);
         generateLargeFlower(HibiscusBlocks.SCORCHED_GRASS, blockStateModelGenerator);
         generatePottedAnemone(HibiscusBlocks.ANEMONE,
                 HibiscusBlocks.POTTED_ANEMONE,
                 blockStateModelGenerator
         );
         generateVineBlockStateModels(HibiscusBlocks.BLUE_WISTERIA_VINES,
                 HibiscusBlocks.BLUE_WISTERIA_VINES_PLANT,
                 blockStateModelGenerator
         );
         generateVineBlockStateModels(HibiscusBlocks.WHITE_WISTERIA_VINES,
                 HibiscusBlocks.WHITE_WISTERIA_VINES_PLANT,
                 blockStateModelGenerator
         );
         generateVineBlockStateModels(HibiscusBlocks.PURPLE_WISTERIA_VINES,
                 HibiscusBlocks.PURPLE_WISTERIA_VINES_PLANT,
                 blockStateModelGenerator
         );
         generateVineBlockStateModels(HibiscusBlocks.PINK_WISTERIA_VINES,
                 HibiscusBlocks.PINK_WISTERIA_VINES_PLANT,
                 blockStateModelGenerator
         );
         generateVineBlockStateModels(HibiscusBlocks.WILLOW_VINES,
                 HibiscusBlocks.WILLOW_VINES_PLANT,
                 blockStateModelGenerator
         );

         createSlab(HibiscusBlocks.KAOLIN, HibiscusBlocks.KAOLIN_SLAB, blockStateModelGenerator);
         createSlab(HibiscusBlocks.WHITE_KAOLIN, HibiscusBlocks.WHITE_KAOLIN_SLAB, blockStateModelGenerator);
         createSlab(HibiscusBlocks.LIGHT_GRAY_KAOLIN, HibiscusBlocks.LIGHT_GRAY_KAOLIN_SLAB, blockStateModelGenerator);
         createSlab(HibiscusBlocks.GRAY_KAOLIN, HibiscusBlocks.GRAY_KAOLIN_SLAB, blockStateModelGenerator);
         createSlab(HibiscusBlocks.BLACK_KAOLIN, HibiscusBlocks.BLACK_KAOLIN_SLAB, blockStateModelGenerator);
         createSlab(HibiscusBlocks.BROWN_KAOLIN, HibiscusBlocks.BROWN_KAOLIN_SLAB, blockStateModelGenerator);
         createSlab(HibiscusBlocks.RED_KAOLIN, HibiscusBlocks.RED_KAOLIN_SLAB, blockStateModelGenerator);
         createSlab(HibiscusBlocks.ORANGE_KAOLIN, HibiscusBlocks.ORANGE_KAOLIN_SLAB, blockStateModelGenerator);
         createSlab(HibiscusBlocks.YELLOW_KAOLIN, HibiscusBlocks.YELLOW_KAOLIN_SLAB, blockStateModelGenerator);
         createSlab(HibiscusBlocks.LIME_KAOLIN, HibiscusBlocks.LIME_KAOLIN_SLAB, blockStateModelGenerator);
         createSlab(HibiscusBlocks.GREEN_KAOLIN, HibiscusBlocks.GREEN_KAOLIN_SLAB, blockStateModelGenerator);
         createSlab(HibiscusBlocks.CYAN_KAOLIN, HibiscusBlocks.CYAN_KAOLIN_SLAB, blockStateModelGenerator);
         createSlab(HibiscusBlocks.LIGHT_BLUE_KAOLIN, HibiscusBlocks.LIGHT_BLUE_KAOLIN_SLAB, blockStateModelGenerator);
         createSlab(HibiscusBlocks.BLUE_KAOLIN, HibiscusBlocks.BLUE_KAOLIN_SLAB, blockStateModelGenerator);
         createSlab(HibiscusBlocks.PURPLE_KAOLIN, HibiscusBlocks.PURPLE_KAOLIN_SLAB, blockStateModelGenerator);
         createSlab(HibiscusBlocks.MAGENTA_KAOLIN, HibiscusBlocks.MAGENTA_KAOLIN_SLAB, blockStateModelGenerator);
         createSlab(HibiscusBlocks.PINK_KAOLIN, HibiscusBlocks.PINK_KAOLIN_SLAB, blockStateModelGenerator);

         createStairs(HibiscusBlocks.KAOLIN, HibiscusBlocks.KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusBlocks.WHITE_KAOLIN, HibiscusBlocks.WHITE_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusBlocks.LIGHT_GRAY_KAOLIN,
                 HibiscusBlocks.LIGHT_GRAY_KAOLIN_STAIRS,
                 blockStateModelGenerator
         );
         createStairs(HibiscusBlocks.GRAY_KAOLIN, HibiscusBlocks.GRAY_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusBlocks.BLACK_KAOLIN, HibiscusBlocks.BLACK_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusBlocks.BROWN_KAOLIN, HibiscusBlocks.BROWN_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusBlocks.RED_KAOLIN, HibiscusBlocks.RED_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusBlocks.ORANGE_KAOLIN, HibiscusBlocks.ORANGE_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusBlocks.YELLOW_KAOLIN, HibiscusBlocks.YELLOW_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusBlocks.LIME_KAOLIN, HibiscusBlocks.LIME_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusBlocks.GREEN_KAOLIN, HibiscusBlocks.GREEN_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusBlocks.CYAN_KAOLIN, HibiscusBlocks.CYAN_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusBlocks.LIGHT_BLUE_KAOLIN,
                 HibiscusBlocks.LIGHT_BLUE_KAOLIN_STAIRS,
                 blockStateModelGenerator
         );
         createStairs(HibiscusBlocks.BLUE_KAOLIN, HibiscusBlocks.BLUE_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusBlocks.PURPLE_KAOLIN, HibiscusBlocks.PURPLE_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusBlocks.MAGENTA_KAOLIN, HibiscusBlocks.MAGENTA_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusBlocks.PINK_KAOLIN, HibiscusBlocks.PINK_KAOLIN_STAIRS, blockStateModelGenerator);

         blockStateModelGenerator.registerSingleton(HibiscusBlocks.KAOLIN, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusBlocks.WHITE_KAOLIN, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusBlocks.LIGHT_GRAY_KAOLIN, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusBlocks.GRAY_KAOLIN, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusBlocks.BLACK_KAOLIN, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusBlocks.BROWN_KAOLIN, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusBlocks.RED_KAOLIN, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusBlocks.ORANGE_KAOLIN, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusBlocks.YELLOW_KAOLIN, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusBlocks.LIME_KAOLIN, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusBlocks.GREEN_KAOLIN, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusBlocks.CYAN_KAOLIN, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusBlocks.LIGHT_BLUE_KAOLIN, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusBlocks.BLUE_KAOLIN, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusBlocks.PURPLE_KAOLIN, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusBlocks.MAGENTA_KAOLIN, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusBlocks.PINK_KAOLIN, TexturedModel.CUBE_ALL);

         blockStateModelGenerator.registerAxisRotated(HibiscusBlocks.DESERT_TURNIP_ROOT_BLOCK,
                 TexturedModel.END_FOR_TOP_CUBE_COLUMN,
                 TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL
         );
      }

      @Override public void generateItemModels(ItemModelGenerator itemModelGenerator) {
         itemModelGenerator.register(HibiscusBlocks.GREEN_OLIVES, Models.GENERATED);
         itemModelGenerator.register(HibiscusBlocks.BLACK_OLIVES, Models.GENERATED);
         itemModelGenerator.register(HibiscusBlocks.DESERT_TURNIP, Models.GENERATED);
      }
   }

   private static class NatureSpiritLangGenerator extends FabricLanguageProvider {

      protected NatureSpiritLangGenerator(FabricDataOutput dataOutput) {
         super(dataOutput);
      }

      public static String capitalizeString(String string) {
         char[] chars = string.toLowerCase().toCharArray();
         boolean found = false;
         for(int i = 0; i < chars.length; i++) {
            if(!found && Character.isLetter(chars[i])) {
               chars[i] = Character.toUpperCase(chars[i]);
               found = true;
            }
            else if(Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == '\'') {
               found = false;
            }
         }
         return String.valueOf(chars);
      }

      private void generateWoodTranslations(HashMap<String, Block[]> woods, TranslationBuilder translationBuilder) {
         for(Block[] blocks : woods.values()) {
            for(int g = 0; g < blocks.length; g++) {
               if(!(g == 14 || g == 16)) {
                  generateBlockTranslations(blocks[g], translationBuilder);
               }
            }
         }
      }

      private void generateJoshuaTranslations(TranslationBuilder translationBuilder) {
         for(int g = 0; g < HibiscusBlocks.JOSHUA.length; g++) {
            if(!(g == 12 || g == 14)) {
               generateBlockTranslations(HibiscusBlocks.JOSHUA[g], translationBuilder);
            }
         }
      }

      private void generateTreeTranslations(HashMap<String, Block[]> saplings, HashMap<String, Block> leaves, TranslationBuilder translationBuilder) {
         for(String i: saplings.keySet()) {
               String temp = capitalizeString(saplings.get(i)[0].toString()
                       .replace("Block{natures_spirit:", "")
                       .replace("_", " ")
                       .replace("}", ""));

               translationBuilder.add(saplings.get(i)[0], temp);
            String temp2 = capitalizeString(leaves.get(i).toString()
                    .replace("Block{natures_spirit:", "")
                    .replace("_", " ")
                    .replace("}", ""));
            translationBuilder.add(leaves.get(i), temp2);
         }
      }

      private void generateBlockTranslations(Block block, TranslationBuilder translationBuilder) {
         String temp = capitalizeString(block.toString()
                 .replace("Block{natures_spirit:", "")
                 .replace("_", " ")
                 .replace("}", ""));
         translationBuilder.add(block, temp);
      }

      @Override public void generateTranslations(TranslationBuilder translationBuilder) {
         generateWoodTranslations(WoodHashMap, translationBuilder);
         generateJoshuaTranslations(translationBuilder);
         generateTreeTranslations(SaplingHashmap, LeavesHashMap, translationBuilder);
         translationBuilder.add(HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, "Nature's Spirit Blocks & Items");
         translationBuilder.add(HibiscusBlocks.GREEN_OLIVES, "Green Olives");
         translationBuilder.add(HibiscusBlocks.BLACK_OLIVES, "Black Olives");
         translationBuilder.add(HibiscusBlocks.DESERT_TURNIP, "Desert Turnip");
         translationBuilder.add("stat.minecraft.eat_pizza_slice", "Pizza Slices Eaten");
         generateBlockTranslations(HibiscusBlocks.ANEMONE, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.POTTED_ANEMONE, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.LAVENDER, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.BLEEDING_HEART, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.BLUEBELL, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.TIGER_LILY, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.PURPLE_WILDFLOWER, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.YELLOW_WILDFLOWER, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.TALL_SCORCHED_GRASS, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.SCORCHED_GRASS, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.CARNATION, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.HIBISCUS, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.POTTED_HIBISCUS, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.GARDENIA, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.SNAPDRAGON, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.FRAMED_SUGI_DOOR, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.FRAMED_SUGI_TRAPDOOR, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.PINK_WISTERIA_VINES, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.BLUE_WISTERIA_VINES, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.WHITE_WISTERIA_VINES, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.PURPLE_WISTERIA_VINES, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.WILLOW_VINES, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.CATTAIL, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.MARIGOLD, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.FOXGLOVE, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.DESERT_TURNIP_ROOT_BLOCK, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.DESERT_TURNIP_BLOCK, translationBuilder);

         generateBlockTranslations(HibiscusBlocks.SANDY_SOIL, translationBuilder);

         generateBlockTranslations(HibiscusBlocks.KAOLIN, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.WHITE_KAOLIN, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.LIGHT_GRAY_KAOLIN, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.GRAY_KAOLIN, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.BLACK_KAOLIN, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.BROWN_KAOLIN, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.RED_KAOLIN, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.ORANGE_KAOLIN, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.YELLOW_KAOLIN, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.LIME_KAOLIN, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.GREEN_KAOLIN, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.CYAN_KAOLIN, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.LIGHT_BLUE_KAOLIN, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.BLUE_KAOLIN, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.PURPLE_KAOLIN, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.MAGENTA_KAOLIN, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.PINK_KAOLIN, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.KAOLIN_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.WHITE_KAOLIN_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.LIGHT_GRAY_KAOLIN_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.GRAY_KAOLIN_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.BLACK_KAOLIN_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.BROWN_KAOLIN_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.RED_KAOLIN_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.ORANGE_KAOLIN_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.YELLOW_KAOLIN_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.LIME_KAOLIN_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.GREEN_KAOLIN_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.CYAN_KAOLIN_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.LIGHT_BLUE_KAOLIN_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.BLUE_KAOLIN_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.PURPLE_KAOLIN_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.MAGENTA_KAOLIN_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.PINK_KAOLIN_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.KAOLIN_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.WHITE_KAOLIN_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.LIGHT_GRAY_KAOLIN_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.GRAY_KAOLIN_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.BLACK_KAOLIN_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.BROWN_KAOLIN_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.RED_KAOLIN_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.ORANGE_KAOLIN_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.YELLOW_KAOLIN_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.LIME_KAOLIN_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.GREEN_KAOLIN_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.CYAN_KAOLIN_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.LIGHT_BLUE_KAOLIN_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.BLUE_KAOLIN_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.PURPLE_KAOLIN_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.MAGENTA_KAOLIN_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusBlocks.PINK_KAOLIN_SLAB, translationBuilder);

         translationBuilder.add("block.natures_spirit.pizza.chicken_topping", "With Cooked Chicken");
         translationBuilder.add("block.natures_spirit.pizza.green_olives_topping", "With Green Olives");
         translationBuilder.add("block.natures_spirit.pizza.black_olives_topping", "With Black Olives");
         translationBuilder.add("block.natures_spirit.pizza.mushroom_topping", "With Mushrooms");
         translationBuilder.add("block.natures_spirit.pizza.beetroot_topping", "With Beetroots");
         translationBuilder.add("block.natures_spirit.pizza.carrot_topping", "With Carrots");
         translationBuilder.add("block.natures_spirit.pizza.cod_topping", "With Cooked Cod");
         translationBuilder.add("block.natures_spirit.pizza.pork_topping", "With Cooked Pork");
         translationBuilder.add("block.natures_spirit.pizza.rabbit_topping", "With Cooked Rabbit");
         translationBuilder.add(HibiscusBlocks.HALF_PIZZA, "Half of a Pizza");
         translationBuilder.add(HibiscusBlocks.THREE_QUARTERS_PIZZA, "Three Quarters of a Pizza");
         translationBuilder.add(HibiscusBlocks.QUARTER_PIZZA, "Quarter of a Pizza");
         translationBuilder.add(HibiscusBlocks.WHOLE_PIZZA, "Pizza");
      }
   }

   public static class NatureSpiritRecipeGenerator extends FabricRecipeProvider {
      public NatureSpiritRecipeGenerator(FabricDataOutput output) {
         super(output);
      }

      private void generateWoodRecipes(HashMap<String, Block[]> woods, HashMap<String, TagKey <Item>> tag, Consumer <RecipeJsonProvider> consumer) {
         for(String i: woods.keySet()) {
            Block[] woodType = woods.get(i);
            offerPlanksRecipe(consumer, woodType[4], tag.get(i), 4);
            offerBarkBlockRecipe(consumer, woodType[0], woodType[2]);
            offerBarkBlockRecipe(consumer, woodType[1], woodType[3]);
            offerHangingSignRecipe(consumer, woodType[15], woodType[3]);
            BlockFamily family = register(woodType[4]).button(woodType[12])
                    .fence(woodType[9])
                    .fenceGate(woodType[10])
                    .pressurePlate(woodType[11])
                    .sign(woodType[13], woodType[14])
                    .slab(woodType[6])
                    .stairs(woodType[5])
                    .door(woodType[7])
                    .trapdoor(woodType[8])
                    .group("wooden")
                    .unlockCriterionName("has_planks")
                    .build();
            generateFamily(consumer, family);
         }
      }
      private void generateJoshuaWoodRecipes(TagKey <Item> tag, Consumer <RecipeJsonProvider> consumer) {
            offerPlanksRecipe(consumer, HibiscusBlocks.JOSHUA[2], tag, 2);
            offerHangingSignRecipe(consumer, HibiscusBlocks.JOSHUA[13], HibiscusBlocks.JOSHUA[1]);
            BlockFamily family = register(HibiscusBlocks.JOSHUA[2]).button(HibiscusBlocks.JOSHUA[10])
                    .fence(HibiscusBlocks.JOSHUA[7])
                    .fenceGate(HibiscusBlocks.JOSHUA[8])
                    .pressurePlate(HibiscusBlocks.JOSHUA[9])
                    .sign(HibiscusBlocks.JOSHUA[11], HibiscusBlocks.JOSHUA[12])
                    .slab(HibiscusBlocks.JOSHUA[4])
                    .stairs(HibiscusBlocks.JOSHUA[3])
                    .door(HibiscusBlocks.JOSHUA[5])
                    .trapdoor(HibiscusBlocks.JOSHUA[6])
                    .group("wooden")
                    .unlockCriterionName("has_planks")
                    .build();
            generateFamily(consumer, family);
      }

      private void generateFlowerRecipes(Block block, Item dye, String group, int amount, Consumer <RecipeJsonProvider> consumer) {
         offerShapelessRecipe(consumer, dye, block, group, amount);
      }

      @Override public void generate(Consumer <RecipeJsonProvider> exporter) {
         generateWoodRecipes(WoodHashMap, itemLogTags, exporter);
         generateJoshuaWoodRecipes(joshuaItemLogtag, exporter);
         generateFlowerRecipes(HibiscusBlocks.ANEMONE, Items.MAGENTA_DYE, "magenta_dye", 1, exporter);
         generateFlowerRecipes(HibiscusBlocks.BLEEDING_HEART, Items.PINK_DYE, "pink_dye", 4, exporter);
         generateFlowerRecipes(HibiscusBlocks.LAVENDER, Items.PURPLE_DYE, "purple_dye", 4, exporter);
         generateFlowerRecipes(HibiscusBlocks.BLUEBELL, Items.BLUE_DYE, "blue_dye", 2, exporter);
         generateFlowerRecipes(HibiscusBlocks.TIGER_LILY, Items.ORANGE_DYE, "orange_dye", 2, exporter);
         generateFlowerRecipes(HibiscusBlocks.PURPLE_WILDFLOWER, Items.PURPLE_DYE, "purple_dye", 2, exporter);
         generateFlowerRecipes(HibiscusBlocks.YELLOW_WILDFLOWER, Items.YELLOW_DYE, "yellow_dye", 2, exporter);
         generateFlowerRecipes(HibiscusBlocks.CARNATION, Items.RED_DYE, "red_dye", 2, exporter);
         generateFlowerRecipes(HibiscusBlocks.SNAPDRAGON, Items.PINK_DYE, "pink_dye", 2, exporter);
         generateFlowerRecipes(HibiscusBlocks.CATTAIL, Items.BROWN_DYE, "brown_dye", 2, exporter);
         generateFlowerRecipes(HibiscusBlocks.MARIGOLD, Items.ORANGE_DYE, "orange_dye", 2, exporter);
         generateFlowerRecipes(HibiscusBlocks.FOXGLOVE, Items.PURPLE_DYE, "purple_dye", 2, exporter);
         generateFlowerRecipes(HibiscusBlocks.HIBISCUS, Items.RED_DYE, "red_dye", 1, exporter);
         generateFlowerRecipes(HibiscusBlocks.GARDENIA, Items.WHITE_DYE, "white_dye", 2, exporter);
         offerCompactingRecipe(exporter, RecipeCategory.FOOD, HibiscusBlocks.DESERT_TURNIP_BLOCK, HibiscusBlocks.DESERT_TURNIP, "desert_turnip");
      }
   }

   public static class NatureSpiritItemTagGenerator extends FabricTagProvider.ItemTagProvider {

      public NatureSpiritItemTagGenerator(FabricDataOutput output, CompletableFuture <RegistryWrapper.WrapperLookup> completableFuture, @Nullable BlockTagProvider blockTagProvider) {
         super(output, completableFuture, blockTagProvider);
      }

      @Override protected void configure(RegistryWrapper.WrapperLookup arg) {

         for(String i: WoodHashMap.keySet()) {
            this.copy(blockLogTags.get(i), itemLogTags.get(i));
         }
         this.copy(joshuaBlockLogtag, joshuaItemLogtag);

         this.copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
         this.copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
         this.copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
         this.copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
         this.copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
         this.copy(BlockTags.PLANKS, ItemTags.PLANKS);
         this.copy(BlockTags.FENCE_GATES, ItemTags.FENCE_GATES);
         this.copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
         this.copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);
         this.copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);
         this.copy(BlockTags.LEAVES, ItemTags.LEAVES);
         this.copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);
         this.copy(BlockTags.SIGNS, ItemTags.SIGNS);
         this.copy(BlockTags.CEILING_HANGING_SIGNS, ItemTags.HANGING_SIGNS);
         this.copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);
         this.copy(BlockTags.TALL_FLOWERS, ItemTags.TALL_FLOWERS);
      }
   }

   public static class NatureSpiritBlockTagGenerator extends FabricTagProvider.BlockTagProvider {

      public NatureSpiritBlockTagGenerator(FabricDataOutput output, CompletableFuture <RegistryWrapper.WrapperLookup> registriesFuture) {
         super(output, registriesFuture);
      }

      private void addWoodTags(HashMap <String, Block[]> woods, HashMap<String, TagKey <Block>> tag) {
         for(String i: woods.keySet()) {
            Block[] woodType = woods.get(i);
            getOrCreateTagBuilder(BlockTags.PLANKS).add(new Block[]{woodType[4]});
            getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS).add(new Block[]{woodType[12]});
            getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add(new Block[]{woodType[7]});
            getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS).add(new Block[]{woodType[5]});
            getOrCreateTagBuilder(BlockTags.WOODEN_SLABS).add(new Block[]{woodType[6]});
            getOrCreateTagBuilder(BlockTags.WOODEN_FENCES).add(new Block[]{woodType[9]});
            getOrCreateTagBuilder(tag.get(i)).add(woodType[3], woodType[2], woodType[1], woodType[0]);
            getOrCreateTagBuilder(BlockTags.OVERWORLD_NATURAL_LOGS).add(new Block[]{woodType[2]});
            getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN).addTag(tag.get(i));
            getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add(new Block[]{woodType[8]});
            getOrCreateTagBuilder(BlockTags.STANDING_SIGNS).add(new Block[]{woodType[13]});
            getOrCreateTagBuilder(BlockTags.WALL_SIGNS).add(new Block[]{woodType[14]});
            getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(new Block[]{woodType[11]});
            getOrCreateTagBuilder(BlockTags.FENCE_GATES).add(new Block[]{woodType[10]});
            getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS).add(woodType[15]);
            getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS).add(woodType[16]);

         }
      }

      private void addJoshuaWoodTags(TagKey <Block> tag) {
            getOrCreateTagBuilder(BlockTags.PLANKS).add(new Block[]{HibiscusBlocks.JOSHUA[2]});
            getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS).add(new Block[]{HibiscusBlocks.JOSHUA[10]});
            getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add(new Block[]{HibiscusBlocks.JOSHUA[5]});
            getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS).add(new Block[]{HibiscusBlocks.JOSHUA[3]});
            getOrCreateTagBuilder(BlockTags.WOODEN_SLABS).add(new Block[]{HibiscusBlocks.JOSHUA[4]});
            getOrCreateTagBuilder(BlockTags.WOODEN_FENCES).add(new Block[]{HibiscusBlocks.JOSHUA[7]});
            getOrCreateTagBuilder(tag).add(HibiscusBlocks.JOSHUA[1], HibiscusBlocks.JOSHUA[0]);
            getOrCreateTagBuilder(BlockTags.OVERWORLD_NATURAL_LOGS).add(new Block[]{HibiscusBlocks.JOSHUA[0]});
            getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN).addTag(tag);
            getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add(new Block[]{HibiscusBlocks.JOSHUA[6]});
            getOrCreateTagBuilder(BlockTags.STANDING_SIGNS).add(new Block[]{HibiscusBlocks.JOSHUA[11]});
            getOrCreateTagBuilder(BlockTags.WALL_SIGNS).add(new Block[]{HibiscusBlocks.JOSHUA[12]});
            getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(new Block[]{HibiscusBlocks.JOSHUA[9]});
            getOrCreateTagBuilder(BlockTags.FENCE_GATES).add(new Block[]{HibiscusBlocks.JOSHUA[8]});
            getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS).add(HibiscusBlocks.JOSHUA[13]);
            getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS).add(HibiscusBlocks.JOSHUA[14]);
      }

      private void addTreeTags(HashMap <String, Block[]> saplings, HashMap <String, Block> leaves) {
         for(String i: saplings.keySet()) {
            Block leavesType = leaves.get(i);
            Block[] saplingType = saplings.get(i);
            getOrCreateTagBuilder(BlockTags.HOE_MINEABLE).add(leavesType);
            getOrCreateTagBuilder(BlockTags.SAPLINGS).add(new Block[]{saplingType[0]});
            getOrCreateTagBuilder(BlockTags.FLOWER_POTS).add(new Block[]{saplingType[1]});
            getOrCreateTagBuilder(BlockTags.LEAVES).add(leavesType);
         }
      }

      private void addFlowerTags(Block block, Block flowerPot, Boolean isTall) {
         getOrCreateTagBuilder(BlockTags.FLOWER_POTS).add(new Block[]{flowerPot});

         if(isTall) {
            getOrCreateTagBuilder(BlockTags.TALL_FLOWERS).add(block);
         }
         else {
            getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS).add(block);
         }

      }

      private void addFlowerTags(Block block, Boolean isTall) {

         if(isTall) {
            getOrCreateTagBuilder(BlockTags.TALL_FLOWERS).add(block);
         }
         else {
            getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS).add(block);
         }

      }

      @Override protected void configure(RegistryWrapper.WrapperLookup arg) {
         addWoodTags(WoodHashMap, blockLogTags);
         addJoshuaWoodTags(joshuaBlockLogtag);
         addTreeTags(SaplingHashmap, LeavesHashMap);
         addFlowerTags(HibiscusBlocks.HIBISCUS, HibiscusBlocks.POTTED_HIBISCUS, false);
         addFlowerTags(HibiscusBlocks.ANEMONE, HibiscusBlocks.POTTED_ANEMONE, false);
         addFlowerTags(HibiscusBlocks.BLUEBELL, false);
         addFlowerTags(HibiscusBlocks.TIGER_LILY, false);
         addFlowerTags(HibiscusBlocks.PURPLE_WILDFLOWER, false);
         addFlowerTags(HibiscusBlocks.YELLOW_WILDFLOWER, false);
         addFlowerTags(HibiscusBlocks.LAVENDER, true);
         addFlowerTags(HibiscusBlocks.BLEEDING_HEART, true);
         addFlowerTags(HibiscusBlocks.CARNATION, true);
         addFlowerTags(HibiscusBlocks.GARDENIA, true);
         addFlowerTags(HibiscusBlocks.CATTAIL, true);
         addFlowerTags(HibiscusBlocks.SNAPDRAGON, true);
         addFlowerTags(HibiscusBlocks.MARIGOLD, true);
         addFlowerTags(HibiscusBlocks.FOXGLOVE, true);
         getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add(new Block[]{HibiscusBlocks.FRAMED_SUGI_DOOR});
         getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add(new Block[]{HibiscusBlocks.FRAMED_SUGI_TRAPDOOR});
         getOrCreateTagBuilder(BlockTags.CLIMBABLE).add(HibiscusBlocks.BLUE_WISTERIA_VINES_PLANT,
                 HibiscusBlocks.BLUE_WISTERIA_VINES,
                 HibiscusBlocks.WHITE_WISTERIA_VINES,
                 HibiscusBlocks.WHITE_WISTERIA_VINES_PLANT,
                 HibiscusBlocks.PINK_WISTERIA_VINES,
                 HibiscusBlocks.PINK_WISTERIA_VINES_PLANT,
                 HibiscusBlocks.PURPLE_WISTERIA_VINES,
                 HibiscusBlocks.PURPLE_WISTERIA_VINES_PLANT,
                 HibiscusBlocks.WILLOW_VINES_PLANT,
                 HibiscusBlocks.WILLOW_VINES
         );
         getOrCreateTagBuilder(BlockTags.BEE_GROWABLES).add(HibiscusBlocks.BLUE_WISTERIA_VINES_PLANT,
                 HibiscusBlocks.BLUE_WISTERIA_VINES,
                 HibiscusBlocks.WHITE_WISTERIA_VINES,
                 HibiscusBlocks.WHITE_WISTERIA_VINES_PLANT,
                 HibiscusBlocks.PINK_WISTERIA_VINES,
                 HibiscusBlocks.PINK_WISTERIA_VINES_PLANT,
                 HibiscusBlocks.PURPLE_WISTERIA_VINES,
                 HibiscusBlocks.PURPLE_WISTERIA_VINES_PLANT,
                 HibiscusBlocks.WILLOW_VINES_PLANT,
                 HibiscusBlocks.WILLOW_VINES
         );
         getOrCreateTagBuilder(BlockTags.CROPS).add(HibiscusBlocks.DESERT_TURNIP_STEM);
         getOrCreateTagBuilder(BlockTags.MAINTAINS_FARMLAND).add(HibiscusBlocks.DESERT_TURNIP_STEM);
         getOrCreateTagBuilder(BlockTags.HOE_MINEABLE).add(HibiscusBlocks.SCORCHED_GRASS, HibiscusBlocks.TALL_SCORCHED_GRASS);
         getOrCreateTagBuilder(BlockTags.SWORD_EFFICIENT).add(HibiscusBlocks.SCORCHED_GRASS, HibiscusBlocks.TALL_SCORCHED_GRASS);
         getOrCreateTagBuilder(BlockTags.REPLACEABLE_BY_TREES).add(HibiscusBlocks.SCORCHED_GRASS, HibiscusBlocks.TALL_SCORCHED_GRASS);
      }
   }
}

