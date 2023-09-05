package net.hibiscus.naturespirit.datagen;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.blocks.DesertPlantBlock;
import net.hibiscus.naturespirit.entity.HibiscusBoatEntity;
import net.hibiscus.naturespirit.registration.HibiscusBlocksAndItems;
import net.hibiscus.naturespirit.registration.HibiscusItemGroups;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusColoredBlocks;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusWoods;
import net.hibiscus.naturespirit.util.HibiscusRegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
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
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;
import static net.hibiscus.naturespirit.datagen.HibiscusBiomes.BiomesHashMap;
import static net.hibiscus.naturespirit.registration.HibiscusBlocksAndItems.*;
import static net.minecraft.data.client.BlockStateModelGenerator.*;
import static net.minecraft.data.family.BlockFamilies.register;

public class NatureSpiritDataGen implements DataGeneratorEntrypoint {
   public static TagKey <Item> joshuaItemLogtag = TagKey.of(RegistryKeys.ITEM, new Identifier(MOD_ID, "joshua_logs"));
   public static TagKey <Block> joshuaBlockLogtag = TagKey.of(RegistryKeys.BLOCK, new Identifier(MOD_ID, "joshua_logs"));
   static HashMap <String, TagKey <Block>> blockLogTags = new HashMap <>();
   static HashMap <String, TagKey <Item>> itemLogTags = new HashMap <>();

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
      for(String i : HibiscusRegistryHelper.WoodHashMap.keySet()) {
         blockLogTags.put(i, TagKey.of(RegistryKeys.BLOCK, new Identifier(MOD_ID, i + "_logs")));
         itemLogTags.put(i, TagKey.of(RegistryKeys.ITEM, new Identifier(MOD_ID, i + "_logs")));
      }
   }


   @Override public String getEffectiveModId() {
      return MOD_ID;
   }


   private static class NatureSpiritBlockLootTableProvider extends FabricBlockLootTableProvider {
      private static final LootCondition.Builder WITH_SILK_TOUCH_OR_SHEARS = WITH_SHEARS.or(WITH_SILK_TOUCH);
      private static final LootCondition.Builder WITHOUT_SILK_TOUCH_NOR_SHEARS = WITH_SILK_TOUCH_OR_SHEARS.invert();
      private final HashMap <Identifier, LootTable.Builder> map = new HashMap();
      private final float[] SAPLING_DROP_CHANCE_2 = new float[]{0.4F, 0.4333333333F, 0.5025F, 0.5888F};

      protected NatureSpiritBlockLootTableProvider(FabricDataOutput dataOutput) {
         super(dataOutput);
      }

      public static LootTable.Builder dropsWithShears(ItemConvertible drop) {
         LootTable.builder().pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F)).conditionally(WITH_SHEARS).with(ItemEntry.builder(drop)));
         return null;
      }

      private void addWoodTable(HashMap <String, Block[]> woods) {
         for(String i : woods.keySet()) {
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
         addDrop(HibiscusWoods.JOSHUA[0]);
         addDrop(HibiscusWoods.JOSHUA[1]);
         addDrop(HibiscusWoods.JOSHUA[2]);
         addDrop(HibiscusWoods.JOSHUA[10]);
         this.addDrop(HibiscusWoods.JOSHUA[5], this::doorDrops);
         addDrop(HibiscusWoods.JOSHUA[3]);
         this.slabDrops(HibiscusWoods.JOSHUA[4]);
         addDrop(HibiscusWoods.JOSHUA[7]);
         addDrop(HibiscusWoods.JOSHUA[6]);
         addDrop(HibiscusWoods.JOSHUA[11]);
         addDrop(HibiscusWoods.JOSHUA[13]);
         addDrop(HibiscusWoods.JOSHUA[9]);
         addDrop(HibiscusWoods.JOSHUA[8]);
      }

      public net.minecraft.loot.LootTable.Builder blackOlivesDrop(Block leaves, Block drop, float... chance) {
         return this.leavesDrops(leaves, drop, chance).pool(LootPool
                 .builder()
                 .rolls(ConstantLootNumberProvider.create(1.0F))
                 .conditionally(WITHOUT_SILK_TOUCH_NOR_SHEARS)
                 .with(((net.minecraft.loot.entry.LeafEntry.Builder <?>) this.addSurvivesExplosionCondition(leaves, ItemEntry.builder(HibiscusBlocksAndItems.BLACK_OLIVES))).conditionally(
                         TableBonusLootCondition.builder(
                                 Enchantments.FORTUNE,
                                 0.01F,
                                 0.0111111114F,
                                 0.0125F,
                                 0.016666668F,
                                 0.05F
                         ))));
      }

      public net.minecraft.loot.LootTable.Builder greenOlivesDrop(Block leaves, Block drop, float... chance) {
         return this.blackOlivesDrop(leaves, drop, chance).pool(LootPool
                 .builder()
                 .rolls(ConstantLootNumberProvider.create(1.0F))
                 .conditionally(WITHOUT_SILK_TOUCH_NOR_SHEARS)
                 .with(((net.minecraft.loot.entry.LeafEntry.Builder <?>) this.addSurvivesExplosionCondition(leaves, ItemEntry.builder(HibiscusBlocksAndItems.GREEN_OLIVES))).conditionally(
                         TableBonusLootCondition.builder(
                                 Enchantments.FORTUNE,
                                 0.01F,
                                 0.0111111114F,
                                 0.0125F,
                                 0.016666668F,
                                 0.05F
                         ))));
      }

      private void addTreeTable(HashMap <String, Block[]> saplings, HashMap <String, Block> leaves) {
         for(String i : saplings.keySet()) {
            Block[] saplingType = saplings.get(i);
            Block leavesType = leaves.get(i);
            addDrop(saplingType[0]);
            addPottedPlantDrops(saplingType[1]);
            if(i.equals("olive")) {
               this.addDrop(leavesType, (block) -> this.greenOlivesDrop(block, saplingType[0], SAPLING_DROP_CHANCE));
            }
            else if(i.equals("joshua")) {
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

      public void tallPlantDrop(Block tallGrass, Block grass) {
         net.minecraft.loot.entry.LootPoolEntry.Builder <?> builder = ItemEntry.builder(grass).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F)));
         LootTable.builder().pool(LootPool
                 .builder()
                 .with(builder)
                 .conditionally(BlockStatePropertyLootCondition
                         .builder(tallGrass)
                         .properties(net.minecraft.predicate.StatePredicate.Builder.create().exactMatch(TallPlantBlock.HALF, DoubleBlockHalf.LOWER)))
                 .conditionally(LocationCheckLootCondition.builder(net.minecraft.predicate.entity.LocationPredicate.Builder
                         .create()
                         .block(net.minecraft.predicate.BlockPredicate.Builder.create().blocks(new Block[]{
                                 tallGrass
                         }).state(net.minecraft.predicate.StatePredicate.Builder.create().exactMatch(
                                 TallPlantBlock.HALF,
                                 DoubleBlockHalf.UPPER
                         ).build()).build()), new BlockPos(0, 1, 0)))).pool(LootPool
                 .builder()
                 .with(builder)
                 .conditionally(BlockStatePropertyLootCondition
                         .builder(tallGrass)
                         .properties(net.minecraft.predicate.StatePredicate.Builder.create().exactMatch(TallPlantBlock.HALF, DoubleBlockHalf.UPPER)))
                 .conditionally(LocationCheckLootCondition.builder(net.minecraft.predicate.entity.LocationPredicate.Builder
                         .create()
                         .block(net.minecraft.predicate.BlockPredicate.Builder.create().blocks(new Block[]{
                                 tallGrass
                         }).state(net.minecraft.predicate.StatePredicate.Builder.create().exactMatch(
                                 TallPlantBlock.HALF,
                                 DoubleBlockHalf.LOWER
                         ).build()).build()), new BlockPos(0, -1, 0))));
      }

      @Override public void generate() {
         addWoodTable(HibiscusRegistryHelper.WoodHashMap);
         addJoshuaWoodTable();
         addTreeTable(HibiscusRegistryHelper.SaplingHashMap, HibiscusRegistryHelper.LeavesHashMap);

         this.addDrop(CALCITE_CLUSTER, (block) -> dropsWithSilkTouch(block,
                 ItemEntry
                         .builder(CALCITE_SHARD)
                         .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(4.0F)))
                         .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))
                         .conditionally(MatchToolLootCondition.builder(net.minecraft.predicate.item.ItemPredicate.Builder.create().tag(ItemTags.CLUSTER_MAX_HARVESTABLES)))
                         .alternatively(this.applyExplosionDecay(block, ItemEntry.builder(CALCITE_SHARD).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F)))))
         ));
         this.addDropWithSilkTouch(SMALL_CALCITE_BUD);
         this.addDropWithSilkTouch(LARGE_CALCITE_BUD);

         addVinesTable(HibiscusWoods.WHITE_WISTERIA_VINES, HibiscusWoods.WHITE_WISTERIA_VINES_PLANT);
         addVinesTable(HibiscusWoods.BLUE_WISTERIA_VINES, HibiscusWoods.BLUE_WISTERIA_VINES_PLANT);
         addVinesTable(HibiscusWoods.PURPLE_WISTERIA_VINES, HibiscusWoods.PURPLE_WISTERIA_VINES_PLANT);
         addVinesTable(HibiscusWoods.PINK_WISTERIA_VINES, HibiscusWoods.PINK_WISTERIA_VINES_PLANT);
         addVinesTable(HibiscusWoods.WILLOW_VINES, HibiscusWoods.WILLOW_VINES_PLANT);

         addVinesTable(LOTUS_STEM, LOTUS_STEM);
         this.addDrop(LOTUS_FLOWER, LOTUS_FLOWER_ITEM);

         this.addDrop(SHIITAKE_MUSHROOM);
         this.mushroomBlockDrops(SHIITAKE_MUSHROOM_BLOCK, SHIITAKE_MUSHROOM);

         this.addDrop(HibiscusBlocksAndItems.CARNATION, (block) -> this.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
         this.addDrop(HibiscusBlocksAndItems.CATTAIL, (block) -> this.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
         this.addDrop(HibiscusBlocksAndItems.GARDENIA, (block) -> this.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
         this.addDrop(HibiscusBlocksAndItems.SNAPDRAGON, (block) -> this.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
         this.addDrop(HibiscusBlocksAndItems.MARIGOLD, (block) -> this.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
         this.addDrop(HibiscusBlocksAndItems.FOXGLOVE, (block) -> this.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
         this.addDrop(HibiscusBlocksAndItems.LAVENDER, (block) -> this.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
         this.addDrop(HibiscusBlocksAndItems.BLEEDING_HEART, (block) -> this.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
         this.addDrop(HibiscusBlocksAndItems.TIGER_LILY, (block) -> this.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));

         this.addDrop(HibiscusBlocksAndItems.ANEMONE);
         addPottedPlantDrops(HibiscusBlocksAndItems.POTTED_ANEMONE);
         this.addDrop(HibiscusBlocksAndItems.HIBISCUS);
         addPottedPlantDrops(HibiscusBlocksAndItems.POTTED_HIBISCUS);
         this.addDrop(HibiscusBlocksAndItems.BLUEBELL);
         this.addDrop(HibiscusBlocksAndItems.TIGER_LILY);
         this.addDrop(HibiscusBlocksAndItems.PURPLE_WILDFLOWER);
         this.addDrop(HibiscusBlocksAndItems.YELLOW_WILDFLOWER);


         this.addDrop(HibiscusWoods.FRAMED_SUGI_DOOR, this::doorDrops);
         this.addDrop(HibiscusWoods.FRAMED_SUGI_TRAPDOOR);

         this.addDrop(HibiscusBlocksAndItems.SANDY_SOIL);

         this.addDrop(HibiscusColoredBlocks.KAOLIN);
         this.addDrop(HibiscusColoredBlocks.WHITE_KAOLIN);
         this.addDrop(HibiscusColoredBlocks.LIGHT_GRAY_KAOLIN);
         this.addDrop(HibiscusColoredBlocks.GRAY_KAOLIN);
         this.addDrop(HibiscusColoredBlocks.BLACK_KAOLIN);
         this.addDrop(HibiscusColoredBlocks.BROWN_KAOLIN);
         this.addDrop(HibiscusColoredBlocks.RED_KAOLIN);
         this.addDrop(HibiscusColoredBlocks.ORANGE_KAOLIN);
         this.addDrop(HibiscusColoredBlocks.YELLOW_KAOLIN);
         this.addDrop(HibiscusColoredBlocks.LIME_KAOLIN);
         this.addDrop(HibiscusColoredBlocks.GREEN_KAOLIN);
         this.addDrop(HibiscusColoredBlocks.CYAN_KAOLIN);
         this.addDrop(HibiscusColoredBlocks.LIGHT_BLUE_KAOLIN);
         this.addDrop(HibiscusColoredBlocks.BLUE_KAOLIN);
         this.addDrop(HibiscusColoredBlocks.PURPLE_KAOLIN);
         this.addDrop(HibiscusColoredBlocks.MAGENTA_KAOLIN);
         this.addDrop(HibiscusColoredBlocks.PINK_KAOLIN);
         this.slabDrops(HibiscusColoredBlocks.KAOLIN_SLAB);
         this.slabDrops(HibiscusColoredBlocks.WHITE_KAOLIN_SLAB);
         this.slabDrops(HibiscusColoredBlocks.LIGHT_GRAY_KAOLIN_SLAB);
         this.slabDrops(HibiscusColoredBlocks.GRAY_KAOLIN_SLAB);
         this.slabDrops(HibiscusColoredBlocks.BLACK_KAOLIN_SLAB);
         this.slabDrops(HibiscusColoredBlocks.BROWN_KAOLIN_SLAB);
         this.slabDrops(HibiscusColoredBlocks.RED_KAOLIN_SLAB);
         this.slabDrops(HibiscusColoredBlocks.ORANGE_KAOLIN_SLAB);
         this.slabDrops(HibiscusColoredBlocks.YELLOW_KAOLIN_SLAB);
         this.slabDrops(HibiscusColoredBlocks.LIME_KAOLIN_SLAB);
         this.slabDrops(HibiscusColoredBlocks.GREEN_KAOLIN_SLAB);
         this.slabDrops(HibiscusColoredBlocks.CYAN_KAOLIN_SLAB);
         this.slabDrops(HibiscusColoredBlocks.LIGHT_BLUE_KAOLIN_SLAB);
         this.slabDrops(HibiscusColoredBlocks.BLUE_KAOLIN_SLAB);
         this.slabDrops(HibiscusColoredBlocks.PURPLE_KAOLIN_SLAB);
         this.slabDrops(HibiscusColoredBlocks.MAGENTA_KAOLIN_SLAB);
         this.slabDrops(HibiscusColoredBlocks.PINK_KAOLIN_SLAB);
         this.addDrop(HibiscusColoredBlocks.KAOLIN_STAIRS);
         this.addDrop(HibiscusColoredBlocks.WHITE_KAOLIN_STAIRS);
         this.addDrop(HibiscusColoredBlocks.LIGHT_GRAY_KAOLIN_STAIRS);
         this.addDrop(HibiscusColoredBlocks.GRAY_KAOLIN_STAIRS);
         this.addDrop(HibiscusColoredBlocks.BLACK_KAOLIN_STAIRS);
         this.addDrop(HibiscusColoredBlocks.BROWN_KAOLIN_STAIRS);
         this.addDrop(HibiscusColoredBlocks.RED_KAOLIN_STAIRS);
         this.addDrop(HibiscusColoredBlocks.ORANGE_KAOLIN_STAIRS);
         this.addDrop(HibiscusColoredBlocks.YELLOW_KAOLIN_STAIRS);
         this.addDrop(HibiscusColoredBlocks.LIME_KAOLIN_STAIRS);
         this.addDrop(HibiscusColoredBlocks.GREEN_KAOLIN_STAIRS);
         this.addDrop(HibiscusColoredBlocks.CYAN_KAOLIN_STAIRS);
         this.addDrop(HibiscusColoredBlocks.LIGHT_BLUE_KAOLIN_STAIRS);
         this.addDrop(HibiscusColoredBlocks.BLUE_KAOLIN_STAIRS);
         this.addDrop(HibiscusColoredBlocks.PURPLE_KAOLIN_STAIRS);
         this.addDrop(HibiscusColoredBlocks.MAGENTA_KAOLIN_STAIRS);
         this.addDrop(HibiscusColoredBlocks.PINK_KAOLIN_STAIRS);

         this.addDrop(HibiscusColoredBlocks.WHITE_CHALK);
         this.addDrop(HibiscusColoredBlocks.LIGHT_GRAY_CHALK);
         this.addDrop(HibiscusColoredBlocks.GRAY_CHALK);
         this.addDrop(HibiscusColoredBlocks.BLACK_CHALK);
         this.addDrop(HibiscusColoredBlocks.BROWN_CHALK);
         this.addDrop(HibiscusColoredBlocks.RED_CHALK);
         this.addDrop(HibiscusColoredBlocks.ORANGE_CHALK);
         this.addDrop(HibiscusColoredBlocks.YELLOW_CHALK);
         this.addDrop(HibiscusColoredBlocks.LIME_CHALK);
         this.addDrop(HibiscusColoredBlocks.GREEN_CHALK);
         this.addDrop(HibiscusColoredBlocks.CYAN_CHALK);
         this.addDrop(HibiscusColoredBlocks.LIGHT_BLUE_CHALK);
         this.addDrop(HibiscusColoredBlocks.BLUE_CHALK);
         this.addDrop(HibiscusColoredBlocks.PURPLE_CHALK);
         this.addDrop(HibiscusColoredBlocks.MAGENTA_CHALK);
         this.addDrop(HibiscusColoredBlocks.PINK_CHALK);
         this.slabDrops(HibiscusColoredBlocks.WHITE_CHALK_SLAB);
         this.slabDrops(HibiscusColoredBlocks.LIGHT_GRAY_CHALK_SLAB);
         this.slabDrops(HibiscusColoredBlocks.GRAY_CHALK_SLAB);
         this.slabDrops(HibiscusColoredBlocks.BLACK_CHALK_SLAB);
         this.slabDrops(HibiscusColoredBlocks.BROWN_CHALK_SLAB);
         this.slabDrops(HibiscusColoredBlocks.RED_CHALK_SLAB);
         this.slabDrops(HibiscusColoredBlocks.ORANGE_CHALK_SLAB);
         this.slabDrops(HibiscusColoredBlocks.YELLOW_CHALK_SLAB);
         this.slabDrops(HibiscusColoredBlocks.LIME_CHALK_SLAB);
         this.slabDrops(HibiscusColoredBlocks.GREEN_CHALK_SLAB);
         this.slabDrops(HibiscusColoredBlocks.CYAN_CHALK_SLAB);
         this.slabDrops(HibiscusColoredBlocks.LIGHT_BLUE_CHALK_SLAB);
         this.slabDrops(HibiscusColoredBlocks.BLUE_CHALK_SLAB);
         this.slabDrops(HibiscusColoredBlocks.PURPLE_CHALK_SLAB);
         this.slabDrops(HibiscusColoredBlocks.MAGENTA_CHALK_SLAB);
         this.slabDrops(HibiscusColoredBlocks.PINK_CHALK_SLAB);
         this.addDrop(HibiscusColoredBlocks.WHITE_CHALK_STAIRS);
         this.addDrop(HibiscusColoredBlocks.LIGHT_GRAY_CHALK_STAIRS);
         this.addDrop(HibiscusColoredBlocks.GRAY_CHALK_STAIRS);
         this.addDrop(HibiscusColoredBlocks.BLACK_CHALK_STAIRS);
         this.addDrop(HibiscusColoredBlocks.BROWN_CHALK_STAIRS);
         this.addDrop(HibiscusColoredBlocks.RED_CHALK_STAIRS);
         this.addDrop(HibiscusColoredBlocks.ORANGE_CHALK_STAIRS);
         this.addDrop(HibiscusColoredBlocks.YELLOW_CHALK_STAIRS);
         this.addDrop(HibiscusColoredBlocks.LIME_CHALK_STAIRS);
         this.addDrop(HibiscusColoredBlocks.GREEN_CHALK_STAIRS);
         this.addDrop(HibiscusColoredBlocks.CYAN_CHALK_STAIRS);
         this.addDrop(HibiscusColoredBlocks.LIGHT_BLUE_CHALK_STAIRS);
         this.addDrop(HibiscusColoredBlocks.BLUE_CHALK_STAIRS);
         this.addDrop(HibiscusColoredBlocks.PURPLE_CHALK_STAIRS);
         this.addDrop(HibiscusColoredBlocks.MAGENTA_CHALK_STAIRS);
         this.addDrop(HibiscusColoredBlocks.PINK_CHALK_STAIRS);

         this.addDrop(HibiscusBlocksAndItems.DESERT_TURNIP_ROOT_BLOCK);

         dropsWithShears(FRIGID_GRASS);
         tallPlantDrop(TALL_FRIGID_GRASS, FRIGID_GRASS);
         addPottedPlantDrops(POTTED_FRIGID_GRASS);

         dropsWithShears(HibiscusBlocksAndItems.SCORCHED_GRASS);
         tallPlantDrop(HibiscusBlocksAndItems.TALL_SCORCHED_GRASS, HibiscusBlocksAndItems.SCORCHED_GRASS);

         dropsWithShears(SEDGE_GRASS);
         tallPlantDrop(TALL_SEDGE_GRASS, SEDGE_GRASS);

         dropsWithShears(FLAXEN_FERN);
         addPottedPlantDrops(POTTED_FLAXEN_FERN);
         tallPlantDrop(LARGE_FLAXEN_FERN, FLAXEN_FERN);

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
         return new Model(Optional.of(new Identifier("natures_spirit", "block/" + parent)), Optional.empty(), requiredTextureKeys);
      }

      public static Identifier getId(Block block) {
         Identifier identifier = Registries.BLOCK.getId(block);
         return identifier.withPrefixedPath("block/");
      }

      private void createSlab(Block block, Block slab, BlockStateModelGenerator blockStateModelGenerator) {
         Identifier resourceLocation = ModelIds.getBlockModelId(block);
         TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(block);
         Identifier resourceLocation2 = Models.SLAB.upload(slab, texturedModel.getTextures(), blockStateModelGenerator.modelCollector);
         Identifier resourceLocation3 = Models.SLAB_TOP.upload(slab, texturedModel.getTextures(), blockStateModelGenerator.modelCollector);
         blockStateModelGenerator.blockStateCollector.accept(createSlabBlockState(slab, resourceLocation2, resourceLocation3, resourceLocation));
      }

      private void createStairs(Block block, Block stairs, BlockStateModelGenerator blockStateModelGenerator) {
         TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(block);
         Identifier resourceLocation = Models.INNER_STAIRS.upload(stairs, texturedModel.getTextures(), blockStateModelGenerator.modelCollector);
         Identifier resourceLocation2 = Models.STAIRS.upload(stairs, texturedModel.getTextures(), blockStateModelGenerator.modelCollector);
         Identifier resourceLocation3 = Models.OUTER_STAIRS.upload(stairs, texturedModel.getTextures(), blockStateModelGenerator.modelCollector);
         blockStateModelGenerator.blockStateCollector.accept(createStairsBlockState(stairs, resourceLocation, resourceLocation2, resourceLocation3));
         blockStateModelGenerator.registerParentedItemModel(stairs, resourceLocation2);
      }

      public void createWoodDoor(Block doorBlock, BlockStateModelGenerator blockStateModelGenerator) {
         TextureMap textureMapping = TextureMap.topBottom(doorBlock);
         Identifier resourceLocation = Models.DOOR_BOTTOM_LEFT.upload(doorBlock, textureMapping, blockStateModelGenerator.modelCollector);
         Identifier resourceLocation2 = Models.DOOR_BOTTOM_LEFT_OPEN.upload(doorBlock, textureMapping, blockStateModelGenerator.modelCollector);
         Identifier resourceLocation3 = Models.DOOR_BOTTOM_RIGHT.upload(doorBlock, textureMapping, blockStateModelGenerator.modelCollector);
         Identifier resourceLocation4 = Models.DOOR_BOTTOM_RIGHT_OPEN.upload(doorBlock, textureMapping, blockStateModelGenerator.modelCollector);
         Identifier resourceLocation5 = Models.DOOR_TOP_LEFT.upload(doorBlock, textureMapping, blockStateModelGenerator.modelCollector);
         Identifier resourceLocation6 = Models.DOOR_TOP_LEFT_OPEN.upload(doorBlock, textureMapping, blockStateModelGenerator.modelCollector);
         Identifier resourceLocation7 = Models.DOOR_TOP_RIGHT.upload(doorBlock, textureMapping, blockStateModelGenerator.modelCollector);
         Identifier resourceLocation8 = Models.DOOR_TOP_RIGHT_OPEN.upload(doorBlock, textureMapping, blockStateModelGenerator.modelCollector);
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
         Identifier resourceLocation = Models.TEMPLATE_ORIENTABLE_TRAPDOOR_TOP.upload(orientableTrapdoorBlock, textureMapping, blockStateModelGenerators.modelCollector);
         Identifier resourceLocation2 = Models.TEMPLATE_ORIENTABLE_TRAPDOOR_BOTTOM.upload(orientableTrapdoorBlock, textureMapping, blockStateModelGenerators.modelCollector);
         Identifier resourceLocation3 = Models.TEMPLATE_ORIENTABLE_TRAPDOOR_OPEN.upload(orientableTrapdoorBlock, textureMapping, blockStateModelGenerators.modelCollector);
         blockStateModelGenerators.blockStateCollector.accept(createOrientableTrapdoorBlockState(orientableTrapdoorBlock, resourceLocation, resourceLocation2, resourceLocation3));
         blockStateModelGenerators.registerParentedItemModel(orientableTrapdoorBlock, resourceLocation2);
      }

      public void createWoodFenceGate(Block planks, Block fenceGateBlock, BlockStateModelGenerator blockStateModelGenerator) {
         TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(planks);
         Identifier resourceLocation = Models.TEMPLATE_FENCE_GATE_OPEN.upload(fenceGateBlock, texturedModel.getTextures(), blockStateModelGenerator.modelCollector);
         Identifier resourceLocation2 = Models.TEMPLATE_FENCE_GATE.upload(fenceGateBlock, texturedModel.getTextures(), blockStateModelGenerator.modelCollector);
         Identifier resourceLocation3 = Models.TEMPLATE_FENCE_GATE_WALL_OPEN.upload(fenceGateBlock, texturedModel.getTextures(), blockStateModelGenerator.modelCollector);
         Identifier resourceLocation4 = Models.TEMPLATE_FENCE_GATE_WALL.upload(fenceGateBlock, texturedModel.getTextures(), blockStateModelGenerator.modelCollector);
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
         Identifier resourceLocation = Models.FENCE_POST.upload(fenceBlock, texturedModel.getTextures(), blockStateModelGenerators.modelCollector);
         Identifier resourceLocation2 = Models.FENCE_SIDE.upload(fenceBlock, texturedModel.getTextures(), blockStateModelGenerators.modelCollector);
         blockStateModelGenerators.blockStateCollector.accept(BlockStateModelGenerator.createFenceBlockState(fenceBlock, resourceLocation, resourceLocation2));
         Identifier resourceLocation3 = Models.FENCE_INVENTORY.upload(fenceBlock, texturedModel.getTextures(), blockStateModelGenerators.modelCollector);
         blockStateModelGenerators.registerParentedItemModel(fenceBlock, resourceLocation3);
      }

      public void createWoodPressurePlate(Block planks, Block pressurePlateBlock, BlockStateModelGenerator blockStateModelGenerator) {
         TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(planks);
         Identifier resourceLocation = Models.PRESSURE_PLATE_UP.upload(pressurePlateBlock, texturedModel.getTextures(), blockStateModelGenerator.modelCollector);
         Identifier resourceLocation2 = Models.PRESSURE_PLATE_DOWN.upload(pressurePlateBlock, texturedModel.getTextures(), blockStateModelGenerator.modelCollector);
         blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createPressurePlateBlockState(pressurePlateBlock, resourceLocation, resourceLocation2));
      }

      public void createWoodSign(Block signBlock, Block wallSignBlock, BlockStateModelGenerator blockStateModelGenerator) {
         TextureMap textureMapping = TextureMap.texture(signBlock);
         Identifier resourceLocation = Models.PARTICLE.upload(signBlock, textureMapping, blockStateModelGenerator.modelCollector);
         blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(signBlock, resourceLocation));
         blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(wallSignBlock, resourceLocation));
         blockStateModelGenerator.registerItemModel(signBlock.asItem());
         blockStateModelGenerator.excludeFromSimpleItemModelGeneration(wallSignBlock);
      }

      public void createWoodButton(Block planks, Block buttonBlock, BlockStateModelGenerator blockStateModelGenerators) {
         TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(planks);
         Identifier resourceLocation = Models.BUTTON.upload(buttonBlock, texturedModel.getTextures(), blockStateModelGenerators.modelCollector);
         Identifier resourceLocation2 = Models.BUTTON_PRESSED.upload(buttonBlock, texturedModel.getTextures(), blockStateModelGenerators.modelCollector);
         blockStateModelGenerators.blockStateCollector.accept(BlockStateModelGenerator.createButtonBlockState(buttonBlock, resourceLocation, resourceLocation2));
         Identifier resourceLocation3 = Models.BUTTON_INVENTORY.upload(buttonBlock, texturedModel.getTextures(), blockStateModelGenerators.modelCollector);
         blockStateModelGenerators.registerParentedItemModel(buttonBlock, resourceLocation3);
      }

      public void createHangingSign(Block strippedLog, Block hangingSign, Block wallHangingSign, BlockStateModelGenerator blockStateModelGenerator) {
         TextureMap textureMap = TextureMap.particle(strippedLog);
         Identifier identifier = Models.PARTICLE.upload(hangingSign, textureMap, blockStateModelGenerator.modelCollector);
         blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(hangingSign, identifier));
         blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(wallHangingSign, identifier));
         blockStateModelGenerator.registerItemModel(hangingSign.asItem());
         blockStateModelGenerator.excludeFromSimpleItemModelGeneration(wallHangingSign);
      }

      private void generateWoodBlockStateModels(HashMap <String, Block[]> woods, BlockStateModelGenerator blockStateModelGenerator) {
         for(String i : woods.keySet()) {
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
         blockStateModelGenerator.registerSingleton(HibiscusWoods.JOSHUA[2], TexturedModel.CUBE_ALL);
         createSlab(HibiscusWoods.JOSHUA[2], HibiscusWoods.JOSHUA[4], blockStateModelGenerator);
         createStairs(HibiscusWoods.JOSHUA[2], HibiscusWoods.JOSHUA[3], blockStateModelGenerator);
         createWoodDoor(HibiscusWoods.JOSHUA[5], blockStateModelGenerator);
         createWoodTrapdoor(HibiscusWoods.JOSHUA[6], blockStateModelGenerator);
         createWoodFenceGate(HibiscusWoods.JOSHUA[2], HibiscusWoods.JOSHUA[8], blockStateModelGenerator);
         createWoodFence(HibiscusWoods.JOSHUA[2], HibiscusWoods.JOSHUA[7], blockStateModelGenerator);
         createWoodButton(HibiscusWoods.JOSHUA[2], HibiscusWoods.JOSHUA[10], blockStateModelGenerator);
         createWoodPressurePlate(HibiscusWoods.JOSHUA[2], HibiscusWoods.JOSHUA[9], blockStateModelGenerator);
         createWoodSign(HibiscusWoods.JOSHUA[11], HibiscusWoods.JOSHUA[12], blockStateModelGenerator);
         createHangingSign(HibiscusWoods.JOSHUA[1], HibiscusWoods.JOSHUA[13], HibiscusWoods.JOSHUA[14], blockStateModelGenerator);
      }

      private void generateTreeBlockStateModels(HashMap <String, Block[]> saplings, HashMap <String, Block> leaves, BlockStateModelGenerator blockStateModelGenerator) {
         for(String i : leaves.keySet()) {
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
         Models.GENERATED.upload(ModelIds.getItemModelId(item), TextureMap.layer0(item), blockStateModelGenerators.modelCollector);
      }

      private void generateFlowerBlockStateModels(Block block, Block block2, BlockStateModelGenerator blockStateModelGenerator) {
         blockStateModelGenerator.registerFlowerPotPlant(block, block2, TintType.NOT_TINTED);
      }

      private void generatePottedAnemone(Block block, Block flowerPot, BlockStateModelGenerator blockStateModelGenerators) {
         registerSpecificFlowerItemModel(block, blockStateModelGenerators);
         TextureMap textureMap1 = TextureMap.cross(block);
         registerTallCrossBlockState(block, textureMap1, blockStateModelGenerators);
         TextureMap textureMap = TextureMap.plant(block);
         Identifier identifier = FLOWER_POT_TALL_CROSS.upload(flowerPot, textureMap, blockStateModelGenerators.modelCollector);
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
         Identifier identifier = blockStateModelGenerators.createSubModel(doubleBlock, "_top", LARGE_CROSS, TextureMap::cross);
         Identifier identifier2 = blockStateModelGenerators.createSubModel(doubleBlock, "_bottom", LARGE_CROSS, TextureMap::cross);
         blockStateModelGenerators.registerDoubleBlock(doubleBlock, identifier, identifier2);
      }

      public final void generateLargeFlower(Block block, BlockStateModelGenerator blockStateModelGenerators) {
         registerSpecificFlowerItemModel(block, blockStateModelGenerators);
         registerTallLargeBlockState(block, TextureMap.cross(block), blockStateModelGenerators);
      }

      public final void registerCropWithoutItem(Block crop, Property <Integer> ageProperty, BlockStateModelGenerator blockStateModelGenerator, int... ageTextureIndices) {
         if(ageProperty.getValues().size() != ageTextureIndices.length) {
            throw new IllegalArgumentException();
         }
         else {
            Int2ObjectMap <Identifier> int2ObjectMap = new Int2ObjectOpenHashMap();
            BlockStateVariantMap blockStateVariantMap = BlockStateVariantMap.create(ageProperty).register((integer) -> {
               int i = ageTextureIndices[integer];
               Identifier identifier = int2ObjectMap.computeIfAbsent(i, (j) -> blockStateModelGenerator.createSubModel(crop, "_stage" + i, Models.CROP, TextureMap::crop));
               return BlockStateVariant.create().put(VariantSettings.MODEL, identifier);
            });
            blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(crop).coordinate(blockStateVariantMap));
         }
      }

      public final void registerMushroomBlock(Block mushroomBlock, BlockStateModelGenerator blockStateModelGenerator) {
         Identifier identifier = Models.TEMPLATE_SINGLE_FACE.upload(mushroomBlock, TextureMap.texture(mushroomBlock), blockStateModelGenerator.modelCollector);
         Identifier identifier2 = ModelIds.getMinecraftNamespacedBlock("mushroom_block_inside");
         blockStateModelGenerator.blockStateCollector.accept(MultipartBlockStateSupplier
                 .create(mushroomBlock)
                 .with(When.create().set(Properties.NORTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, identifier))
                 .with(When.create().set(Properties.EAST, true), BlockStateVariant
                         .create()
                         .put(VariantSettings.MODEL, identifier)
                         .put(VariantSettings.Y, VariantSettings.Rotation.R90)
                         .put(VariantSettings.UVLOCK, true))
                 .with(When.create().set(Properties.SOUTH, true), BlockStateVariant
                         .create()
                         .put(VariantSettings.MODEL, identifier)
                         .put(VariantSettings.Y, VariantSettings.Rotation.R180)
                         .put(VariantSettings.UVLOCK, true))
                 .with(When.create().set(Properties.WEST, true), BlockStateVariant
                         .create()
                         .put(VariantSettings.MODEL, identifier)
                         .put(VariantSettings.Y, VariantSettings.Rotation.R270)
                         .put(VariantSettings.UVLOCK, true))
                 .with(When.create().set(Properties.UP, true), BlockStateVariant
                         .create()
                         .put(VariantSettings.MODEL, identifier)
                         .put(VariantSettings.X, VariantSettings.Rotation.R270)
                         .put(VariantSettings.UVLOCK, true))
                 .with(When.create().set(Properties.DOWN, true), BlockStateVariant
                         .create()
                         .put(VariantSettings.MODEL, identifier)
                         .put(VariantSettings.X, VariantSettings.Rotation.R90)
                         .put(VariantSettings.UVLOCK, true))
                 .with(When.create().set(Properties.NORTH, false), BlockStateVariant.create().put(VariantSettings.MODEL, identifier2))
                 .with(When.create().set(Properties.EAST, false), BlockStateVariant
                         .create()
                         .put(VariantSettings.MODEL, identifier2)
                         .put(VariantSettings.Y, VariantSettings.Rotation.R90)
                         .put(VariantSettings.UVLOCK, false))
                 .with(When.create().set(Properties.SOUTH, false), BlockStateVariant
                         .create()
                         .put(VariantSettings.MODEL, identifier2)
                         .put(VariantSettings.Y, VariantSettings.Rotation.R180)
                         .put(VariantSettings.UVLOCK, false))
                 .with(When.create().set(Properties.WEST, false), BlockStateVariant
                         .create()
                         .put(VariantSettings.MODEL, identifier2)
                         .put(VariantSettings.Y, VariantSettings.Rotation.R270)
                         .put(VariantSettings.UVLOCK, false))
                 .with(When.create().set(Properties.UP, false), BlockStateVariant
                         .create()
                         .put(VariantSettings.MODEL, identifier2)
                         .put(VariantSettings.X, VariantSettings.Rotation.R270)
                         .put(VariantSettings.UVLOCK, false))
                 .with(When.create().set(Properties.DOWN, false), BlockStateVariant
                         .create()
                         .put(VariantSettings.MODEL, identifier2)
                         .put(VariantSettings.X, VariantSettings.Rotation.R90)
                         .put(VariantSettings.UVLOCK, false)));
         blockStateModelGenerator.registerParentedItemModel(mushroomBlock, TexturedModel.CUBE_ALL.upload(mushroomBlock, "_inventory", blockStateModelGenerator.modelCollector));
      }

      @Override public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
         generateWoodBlockStateModels(HibiscusRegistryHelper.WoodHashMap, blockStateModelGenerator);
         generateJoshuaWoodBlockStateModels(blockStateModelGenerator);
         generateTreeBlockStateModels(HibiscusRegistryHelper.SaplingHashMap, HibiscusRegistryHelper.LeavesHashMap, blockStateModelGenerator);

         blockStateModelGenerator.registerAmethyst(CALCITE_CLUSTER);
         blockStateModelGenerator.registerAmethyst(SMALL_CALCITE_BUD);
         blockStateModelGenerator.registerAmethyst(LARGE_CALCITE_BUD);

         blockStateModelGenerator.registerSimpleState(CHEESE_BLOCK);
         blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(MILK_CAULDRON, Models.TEMPLATE_CAULDRON_FULL.upload(MILK_CAULDRON, TextureMap.cauldron(new Identifier(MOD_ID, "block/milk")), blockStateModelGenerator.modelCollector)));
         blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(CHEESE_CAULDRON, Models.TEMPLATE_CAULDRON_FULL.upload(CHEESE_CAULDRON, TextureMap.cauldron(TextureMap.getId(CHEESE_BLOCK)), blockStateModelGenerator.modelCollector)));


         generateFlowerBlockStateModels(HibiscusBlocksAndItems.HIBISCUS, HibiscusBlocksAndItems.POTTED_HIBISCUS, blockStateModelGenerator);
         generateFlowerBlockStateModels(FLAXEN_FERN, POTTED_FLAXEN_FERN, blockStateModelGenerator);
         generateFlowerBlockStateModels(FRIGID_GRASS, POTTED_FRIGID_GRASS, blockStateModelGenerator);
         generateFlowerBlockStateModels(SHIITAKE_MUSHROOM, POTTED_SHIITAKE_MUSHROOM, blockStateModelGenerator);
         registerMushroomBlock(SHIITAKE_MUSHROOM_BLOCK, blockStateModelGenerator);
         registerCropWithoutItem(HibiscusBlocksAndItems.DESERT_TURNIP_STEM, DesertPlantBlock.AGE, blockStateModelGenerator, 0, 1, 2, 3, 4, 5, 6, 7);
         blockStateModelGenerator.registerDoubleBlock(HibiscusBlocksAndItems.CARNATION, TintType.NOT_TINTED);
         blockStateModelGenerator.registerDoubleBlock(HibiscusBlocksAndItems.GARDENIA, TintType.NOT_TINTED);
         blockStateModelGenerator.registerDoubleBlock(HibiscusBlocksAndItems.SNAPDRAGON, TintType.NOT_TINTED);
         blockStateModelGenerator.registerDoubleBlock(HibiscusBlocksAndItems.MARIGOLD, TintType.NOT_TINTED);
         blockStateModelGenerator.registerDoubleBlock(HibiscusBlocksAndItems.FOXGLOVE, TintType.NOT_TINTED);
         blockStateModelGenerator.registerDoubleBlock(TALL_FRIGID_GRASS, TintType.NOT_TINTED);
         generateTallLargeFlower(HibiscusBlocksAndItems.TALL_SCORCHED_GRASS, blockStateModelGenerator);
         generateTallLargeFlower(TALL_SEDGE_GRASS, blockStateModelGenerator);
         generateTallLargeFlower(HibiscusBlocksAndItems.LAVENDER, blockStateModelGenerator);
         generateTallLargeFlower(HibiscusBlocksAndItems.BLEEDING_HEART, blockStateModelGenerator);
         generateLargeFlower(HibiscusBlocksAndItems.BLUEBELL, blockStateModelGenerator);
         generateLargeFlower(HibiscusBlocksAndItems.TIGER_LILY, blockStateModelGenerator);
         generateLargeFlower(HibiscusBlocksAndItems.PURPLE_WILDFLOWER, blockStateModelGenerator);
         generateLargeFlower(HibiscusBlocksAndItems.YELLOW_WILDFLOWER, blockStateModelGenerator);
         generateLargeFlower(HibiscusBlocksAndItems.SCORCHED_GRASS, blockStateModelGenerator);
         generateLargeFlower(SEDGE_GRASS, blockStateModelGenerator);
         generatePottedAnemone(HibiscusBlocksAndItems.ANEMONE, HibiscusBlocksAndItems.POTTED_ANEMONE, blockStateModelGenerator);
         generateVineBlockStateModels(HibiscusWoods.BLUE_WISTERIA_VINES, HibiscusWoods.BLUE_WISTERIA_VINES_PLANT, blockStateModelGenerator);
         generateVineBlockStateModels(HibiscusWoods.WHITE_WISTERIA_VINES, HibiscusWoods.WHITE_WISTERIA_VINES_PLANT, blockStateModelGenerator);
         generateVineBlockStateModels(HibiscusWoods.PURPLE_WISTERIA_VINES, HibiscusWoods.PURPLE_WISTERIA_VINES_PLANT, blockStateModelGenerator);
         generateVineBlockStateModels(HibiscusWoods.PINK_WISTERIA_VINES, HibiscusWoods.PINK_WISTERIA_VINES_PLANT, blockStateModelGenerator);
         generateVineBlockStateModels(HibiscusWoods.WILLOW_VINES, HibiscusWoods.WILLOW_VINES_PLANT, blockStateModelGenerator);

         createSlab(HibiscusColoredBlocks.KAOLIN, HibiscusColoredBlocks.KAOLIN_SLAB, blockStateModelGenerator);
         createSlab(HibiscusColoredBlocks.WHITE_KAOLIN, HibiscusColoredBlocks.WHITE_KAOLIN_SLAB, blockStateModelGenerator);
         createSlab(HibiscusColoredBlocks.LIGHT_GRAY_KAOLIN, HibiscusColoredBlocks.LIGHT_GRAY_KAOLIN_SLAB, blockStateModelGenerator);
         createSlab(HibiscusColoredBlocks.GRAY_KAOLIN, HibiscusColoredBlocks.GRAY_KAOLIN_SLAB, blockStateModelGenerator);
         createSlab(HibiscusColoredBlocks.BLACK_KAOLIN, HibiscusColoredBlocks.BLACK_KAOLIN_SLAB, blockStateModelGenerator);
         createSlab(HibiscusColoredBlocks.BROWN_KAOLIN, HibiscusColoredBlocks.BROWN_KAOLIN_SLAB, blockStateModelGenerator);
         createSlab(HibiscusColoredBlocks.RED_KAOLIN, HibiscusColoredBlocks.RED_KAOLIN_SLAB, blockStateModelGenerator);
         createSlab(HibiscusColoredBlocks.ORANGE_KAOLIN, HibiscusColoredBlocks.ORANGE_KAOLIN_SLAB, blockStateModelGenerator);
         createSlab(HibiscusColoredBlocks.YELLOW_KAOLIN, HibiscusColoredBlocks.YELLOW_KAOLIN_SLAB, blockStateModelGenerator);
         createSlab(HibiscusColoredBlocks.LIME_KAOLIN, HibiscusColoredBlocks.LIME_KAOLIN_SLAB, blockStateModelGenerator);
         createSlab(HibiscusColoredBlocks.GREEN_KAOLIN, HibiscusColoredBlocks.GREEN_KAOLIN_SLAB, blockStateModelGenerator);
         createSlab(HibiscusColoredBlocks.CYAN_KAOLIN, HibiscusColoredBlocks.CYAN_KAOLIN_SLAB, blockStateModelGenerator);
         createSlab(HibiscusColoredBlocks.LIGHT_BLUE_KAOLIN, HibiscusColoredBlocks.LIGHT_BLUE_KAOLIN_SLAB, blockStateModelGenerator);
         createSlab(HibiscusColoredBlocks.BLUE_KAOLIN, HibiscusColoredBlocks.BLUE_KAOLIN_SLAB, blockStateModelGenerator);
         createSlab(HibiscusColoredBlocks.PURPLE_KAOLIN, HibiscusColoredBlocks.PURPLE_KAOLIN_SLAB, blockStateModelGenerator);
         createSlab(HibiscusColoredBlocks.MAGENTA_KAOLIN, HibiscusColoredBlocks.MAGENTA_KAOLIN_SLAB, blockStateModelGenerator);
         createSlab(HibiscusColoredBlocks.PINK_KAOLIN, HibiscusColoredBlocks.PINK_KAOLIN_SLAB, blockStateModelGenerator);

         createStairs(HibiscusColoredBlocks.KAOLIN, HibiscusColoredBlocks.KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.WHITE_KAOLIN, HibiscusColoredBlocks.WHITE_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.LIGHT_GRAY_KAOLIN, HibiscusColoredBlocks.LIGHT_GRAY_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.GRAY_KAOLIN, HibiscusColoredBlocks.GRAY_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.BLACK_KAOLIN, HibiscusColoredBlocks.BLACK_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.BROWN_KAOLIN, HibiscusColoredBlocks.BROWN_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.RED_KAOLIN, HibiscusColoredBlocks.RED_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.ORANGE_KAOLIN, HibiscusColoredBlocks.ORANGE_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.YELLOW_KAOLIN, HibiscusColoredBlocks.YELLOW_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.LIME_KAOLIN, HibiscusColoredBlocks.LIME_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.GREEN_KAOLIN, HibiscusColoredBlocks.GREEN_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.CYAN_KAOLIN, HibiscusColoredBlocks.CYAN_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.LIGHT_BLUE_KAOLIN, HibiscusColoredBlocks.LIGHT_BLUE_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.BLUE_KAOLIN, HibiscusColoredBlocks.BLUE_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.PURPLE_KAOLIN, HibiscusColoredBlocks.PURPLE_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.MAGENTA_KAOLIN, HibiscusColoredBlocks.MAGENTA_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.PINK_KAOLIN, HibiscusColoredBlocks.PINK_KAOLIN_STAIRS, blockStateModelGenerator);

         blockStateModelGenerator.registerSingleton(HibiscusColoredBlocks.KAOLIN, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusColoredBlocks.WHITE_KAOLIN, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusColoredBlocks.LIGHT_GRAY_KAOLIN, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusColoredBlocks.GRAY_KAOLIN, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusColoredBlocks.BLACK_KAOLIN, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusColoredBlocks.BROWN_KAOLIN, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusColoredBlocks.RED_KAOLIN, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusColoredBlocks.ORANGE_KAOLIN, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusColoredBlocks.YELLOW_KAOLIN, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusColoredBlocks.LIME_KAOLIN, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusColoredBlocks.GREEN_KAOLIN, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusColoredBlocks.CYAN_KAOLIN, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusColoredBlocks.LIGHT_BLUE_KAOLIN, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusColoredBlocks.BLUE_KAOLIN, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusColoredBlocks.PURPLE_KAOLIN, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusColoredBlocks.MAGENTA_KAOLIN, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusColoredBlocks.PINK_KAOLIN, TexturedModel.CUBE_ALL);

         createSlab(HibiscusColoredBlocks.WHITE_CHALK, HibiscusColoredBlocks.WHITE_CHALK_SLAB, blockStateModelGenerator);
         createSlab(HibiscusColoredBlocks.LIGHT_GRAY_CHALK, HibiscusColoredBlocks.LIGHT_GRAY_CHALK_SLAB, blockStateModelGenerator);
         createSlab(HibiscusColoredBlocks.GRAY_CHALK, HibiscusColoredBlocks.GRAY_CHALK_SLAB, blockStateModelGenerator);
         createSlab(HibiscusColoredBlocks.BLACK_CHALK, HibiscusColoredBlocks.BLACK_CHALK_SLAB, blockStateModelGenerator);
         createSlab(HibiscusColoredBlocks.BROWN_CHALK, HibiscusColoredBlocks.BROWN_CHALK_SLAB, blockStateModelGenerator);
         createSlab(HibiscusColoredBlocks.RED_CHALK, HibiscusColoredBlocks.RED_CHALK_SLAB, blockStateModelGenerator);
         createSlab(HibiscusColoredBlocks.ORANGE_CHALK, HibiscusColoredBlocks.ORANGE_CHALK_SLAB, blockStateModelGenerator);
         createSlab(HibiscusColoredBlocks.YELLOW_CHALK, HibiscusColoredBlocks.YELLOW_CHALK_SLAB, blockStateModelGenerator);
         createSlab(HibiscusColoredBlocks.LIME_CHALK, HibiscusColoredBlocks.LIME_CHALK_SLAB, blockStateModelGenerator);
         createSlab(HibiscusColoredBlocks.GREEN_CHALK, HibiscusColoredBlocks.GREEN_CHALK_SLAB, blockStateModelGenerator);
         createSlab(HibiscusColoredBlocks.CYAN_CHALK, HibiscusColoredBlocks.CYAN_CHALK_SLAB, blockStateModelGenerator);
         createSlab(HibiscusColoredBlocks.LIGHT_BLUE_CHALK, HibiscusColoredBlocks.LIGHT_BLUE_CHALK_SLAB, blockStateModelGenerator);
         createSlab(HibiscusColoredBlocks.BLUE_CHALK, HibiscusColoredBlocks.BLUE_CHALK_SLAB, blockStateModelGenerator);
         createSlab(HibiscusColoredBlocks.PURPLE_CHALK, HibiscusColoredBlocks.PURPLE_CHALK_SLAB, blockStateModelGenerator);
         createSlab(HibiscusColoredBlocks.MAGENTA_CHALK, HibiscusColoredBlocks.MAGENTA_CHALK_SLAB, blockStateModelGenerator);
         createSlab(HibiscusColoredBlocks.PINK_CHALK, HibiscusColoredBlocks.PINK_CHALK_SLAB, blockStateModelGenerator);

         createStairs(HibiscusColoredBlocks.WHITE_CHALK, HibiscusColoredBlocks.WHITE_CHALK_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.LIGHT_GRAY_CHALK, HibiscusColoredBlocks.LIGHT_GRAY_CHALK_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.GRAY_CHALK, HibiscusColoredBlocks.GRAY_CHALK_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.BLACK_CHALK, HibiscusColoredBlocks.BLACK_CHALK_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.BROWN_CHALK, HibiscusColoredBlocks.BROWN_CHALK_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.RED_CHALK, HibiscusColoredBlocks.RED_CHALK_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.ORANGE_CHALK, HibiscusColoredBlocks.ORANGE_CHALK_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.YELLOW_CHALK, HibiscusColoredBlocks.YELLOW_CHALK_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.LIME_CHALK, HibiscusColoredBlocks.LIME_CHALK_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.GREEN_CHALK, HibiscusColoredBlocks.GREEN_CHALK_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.CYAN_CHALK, HibiscusColoredBlocks.CYAN_CHALK_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.LIGHT_BLUE_CHALK, HibiscusColoredBlocks.LIGHT_BLUE_CHALK_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.BLUE_CHALK, HibiscusColoredBlocks.BLUE_CHALK_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.PURPLE_CHALK, HibiscusColoredBlocks.PURPLE_CHALK_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.MAGENTA_CHALK, HibiscusColoredBlocks.MAGENTA_CHALK_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.PINK_CHALK, HibiscusColoredBlocks.PINK_CHALK_STAIRS, blockStateModelGenerator);

         blockStateModelGenerator.registerSingleton(HibiscusColoredBlocks.WHITE_CHALK, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusColoredBlocks.LIGHT_GRAY_CHALK, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusColoredBlocks.GRAY_CHALK, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusColoredBlocks.BLACK_CHALK, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusColoredBlocks.BROWN_CHALK, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusColoredBlocks.RED_CHALK, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusColoredBlocks.ORANGE_CHALK, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusColoredBlocks.YELLOW_CHALK, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusColoredBlocks.LIME_CHALK, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusColoredBlocks.GREEN_CHALK, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusColoredBlocks.CYAN_CHALK, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusColoredBlocks.LIGHT_BLUE_CHALK, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusColoredBlocks.BLUE_CHALK, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusColoredBlocks.PURPLE_CHALK, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusColoredBlocks.MAGENTA_CHALK, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(HibiscusColoredBlocks.PINK_CHALK, TexturedModel.CUBE_ALL);

         blockStateModelGenerator.registerAxisRotated(HibiscusBlocksAndItems.DESERT_TURNIP_ROOT_BLOCK, TexturedModel.END_FOR_TOP_CUBE_COLUMN, TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL);
      }

      @Override public void generateItemModels(ItemModelGenerator itemModelGenerator) {
         itemModelGenerator.register(HibiscusBlocksAndItems.GREEN_OLIVES, Models.GENERATED);
         itemModelGenerator.register(HibiscusBlocksAndItems.BLACK_OLIVES, Models.GENERATED);
         itemModelGenerator.register(HibiscusBlocksAndItems.DESERT_TURNIP, Models.GENERATED);
         itemModelGenerator.register(CALCITE_SHARD, Models.GENERATED);
         itemModelGenerator.register(CHEESE_BUCKET, Models.GENERATED);
         for(HibiscusBoatEntity.HibiscusBoat boat : HibiscusBoatEntity.HibiscusBoat.values()) {
            itemModelGenerator.register(boat.boat().asItem(), Models.GENERATED);
            itemModelGenerator.register(boat.chestBoat().asItem(), Models.GENERATED);
         }
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

      private void generateWoodTranslations(HashMap <String, Block[]> woods, TranslationBuilder translationBuilder) {
         for(Block[] blocks : woods.values()) {
            for(int g = 0; g < blocks.length; g++) {
               if(!(g == 14 || g == 16)) {
                  generateBlockTranslations(blocks[g], translationBuilder);
               }
            }
         }
      }

      private void generateJoshuaTranslations(TranslationBuilder translationBuilder) {
         for(int g = 0; g < HibiscusWoods.JOSHUA.length; g++) {
            if(!(g == 12 || g == 14)) {
               generateBlockTranslations(HibiscusWoods.JOSHUA[g], translationBuilder);
            }
         }
      }

      private void generateTreeTranslations(HashMap <String, Block[]> saplings, HashMap <String, Block> leaves, TranslationBuilder translationBuilder) {
         for(String i : saplings.keySet()) {
            String temp = capitalizeString(saplings.get(i)[0].toString().replace("Block{natures_spirit:", "").replace("_", " ").replace("}", ""));

            translationBuilder.add(saplings.get(i)[0], temp);
            String temp2 = capitalizeString(leaves.get(i).toString().replace("Block{natures_spirit:", "").replace("_", " ").replace("}", ""));
            translationBuilder.add(leaves.get(i), temp2);
         }
      }

      private void generateBlockTranslations(Block block, TranslationBuilder translationBuilder) {
         String temp = capitalizeString(block.toString().replace("Block{natures_spirit:", "").replace("_", " ").replace("}", ""));
         translationBuilder.add(block, temp);
      }

      private void generateBoatTranslations(TranslationBuilder translationBuilder) {
         for(HibiscusBoatEntity.HibiscusBoat boat : HibiscusBoatEntity.HibiscusBoat.values()) {
            translationBuilder.add(boat.boat().asItem(), capitalizeString(boat.asString()) + " Boat");
            translationBuilder.add(boat.chestBoat().asItem(), capitalizeString(boat.asString()) + " Boat With Chest");
         }
      }
      private void generateBiomeTranslations(TranslationBuilder translationBuilder) {
         for(String name : BiomesHashMap.keySet()) {
            RegistryKey <Biome> biome = BiomesHashMap.get(name);
            translationBuilder.add(biome.toString().replace("ResourceKey[minecraft:worldgen/biome / natures_spirit:", "biome.natures_spirit.").replace("]", ""), capitalizeString(name.replace("_", " ")));
         }
      }

      @Override public void generateTranslations(TranslationBuilder translationBuilder) {
         generateBiomeTranslations(translationBuilder);
         translationBuilder.add(HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, "Nature's Spirit Blocks & Items");
         translationBuilder.add("stat.minecraft.eat_pizza_slice", "Pizza Slices Eaten");



         translationBuilder.add("block.natures_spirit.pizza.chicken_topping", "With Cooked Chicken");
         translationBuilder.add("block.natures_spirit.pizza.green_olives_topping", "With Green Olives");
         translationBuilder.add("block.natures_spirit.pizza.black_olives_topping", "With Black Olives");
         translationBuilder.add("block.natures_spirit.pizza.mushroom_topping", "With Mushrooms");
         translationBuilder.add("block.natures_spirit.pizza.beetroot_topping", "With Beetroots");
         translationBuilder.add("block.natures_spirit.pizza.carrot_topping", "With Carrots");
         translationBuilder.add("block.natures_spirit.pizza.cod_topping", "With Cooked Cod");
         translationBuilder.add("block.natures_spirit.pizza.pork_topping", "With Cooked Pork");
         translationBuilder.add("block.natures_spirit.pizza.rabbit_topping", "With Cooked Rabbit");
         translationBuilder.add(HibiscusBlocksAndItems.HALF_PIZZA, "Half of a Pizza");
         translationBuilder.add(HibiscusBlocksAndItems.THREE_QUARTERS_PIZZA, "Three Quarters of a Pizza");
         translationBuilder.add(HibiscusBlocksAndItems.QUARTER_PIZZA, "Quarter of a Pizza");
         translationBuilder.add(HibiscusBlocksAndItems.WHOLE_PIZZA, "Pizza");
      }
   }

   public static class NatureSpiritRecipeGenerator extends FabricRecipeProvider {
      public NatureSpiritRecipeGenerator(FabricDataOutput output) {
         super(output);
      }

      private void generateWoodRecipes(HashMap <String, Block[]> woods, HashMap <String, TagKey <Item>> tag, Consumer <RecipeJsonProvider> consumer) {
         for(String i : woods.keySet()) {
            Block[] woodType = woods.get(i);
            offerPlanksRecipe(consumer, woodType[4], tag.get(i), 4);
            offerBarkBlockRecipe(consumer, woodType[0], woodType[2]);
            offerBarkBlockRecipe(consumer, woodType[1], woodType[3]);
            offerHangingSignRecipe(consumer, woodType[15], woodType[3]);
            offerBoatRecipe(consumer, HibiscusBoatEntity.HibiscusBoat.getType(i).boat().asItem(), woodType[4]);
            offerChestBoatRecipe(consumer, HibiscusBoatEntity.HibiscusBoat.getType(i).chestBoat().asItem(), HibiscusBoatEntity.HibiscusBoat.getType(i).boat().asItem());
            BlockFamily family = register(woodType[4])
                    .button(woodType[12])
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
         offerPlanksRecipe(consumer, HibiscusWoods.JOSHUA[2], tag, 2);
         offerHangingSignRecipe(consumer, HibiscusWoods.JOSHUA[13], HibiscusWoods.JOSHUA[1]);
         offerBoatRecipe(consumer, HibiscusWoods.JOSHUA_BOAT, HibiscusWoods.JOSHUA[4]);
         offerChestBoatRecipe(consumer, HibiscusWoods.JOSHUA_CHEST_BOAT, HibiscusWoods.JOSHUA_BOAT);
         BlockFamily family = register(HibiscusWoods.JOSHUA[2])
                 .button(HibiscusWoods.JOSHUA[10])
                 .fence(HibiscusWoods.JOSHUA[7])
                 .fenceGate(HibiscusWoods.JOSHUA[8])
                 .pressurePlate(HibiscusWoods.JOSHUA[9])
                 .sign(HibiscusWoods.JOSHUA[11], HibiscusWoods.JOSHUA[12])
                 .slab(HibiscusWoods.JOSHUA[4])
                 .stairs(HibiscusWoods.JOSHUA[3])
                 .door(HibiscusWoods.JOSHUA[5])
                 .trapdoor(HibiscusWoods.JOSHUA[6])
                 .group("wooden")
                 .unlockCriterionName("has_planks")
                 .build();
         generateFamily(consumer, family);
      }

      private void generateFlowerRecipes(Block block, Item dye, String group, int amount, Consumer <RecipeJsonProvider> consumer) {
         offerShapelessRecipe(consumer, dye, block, group, amount);
      }

      @Override public void generate(Consumer <RecipeJsonProvider> exporter) {
         generateWoodRecipes(HibiscusRegistryHelper.WoodHashMap, itemLogTags, exporter);
         generateJoshuaWoodRecipes(joshuaItemLogtag, exporter);
         generateFlowerRecipes(HibiscusBlocksAndItems.ANEMONE, Items.MAGENTA_DYE, "magenta_dye", 1, exporter);
         generateFlowerRecipes(HibiscusBlocksAndItems.BLEEDING_HEART, Items.PINK_DYE, "pink_dye", 4, exporter);
         generateFlowerRecipes(HibiscusBlocksAndItems.LAVENDER, Items.PURPLE_DYE, "purple_dye", 4, exporter);
         generateFlowerRecipes(HibiscusBlocksAndItems.BLUEBELL, Items.BLUE_DYE, "blue_dye", 2, exporter);
         generateFlowerRecipes(HibiscusBlocksAndItems.TIGER_LILY, Items.ORANGE_DYE, "orange_dye", 2, exporter);
         generateFlowerRecipes(HibiscusBlocksAndItems.PURPLE_WILDFLOWER, Items.PURPLE_DYE, "purple_dye", 2, exporter);
         generateFlowerRecipes(HibiscusBlocksAndItems.YELLOW_WILDFLOWER, Items.YELLOW_DYE, "yellow_dye", 2, exporter);
         generateFlowerRecipes(HibiscusBlocksAndItems.CARNATION, Items.RED_DYE, "red_dye", 2, exporter);
         generateFlowerRecipes(HibiscusBlocksAndItems.SNAPDRAGON, Items.PINK_DYE, "pink_dye", 2, exporter);
         generateFlowerRecipes(HibiscusBlocksAndItems.CATTAIL, Items.BROWN_DYE, "brown_dye", 2, exporter);
         generateFlowerRecipes(HibiscusBlocksAndItems.MARIGOLD, Items.ORANGE_DYE, "orange_dye", 2, exporter);
         generateFlowerRecipes(HibiscusBlocksAndItems.FOXGLOVE, Items.PURPLE_DYE, "purple_dye", 2, exporter);
         generateFlowerRecipes(HibiscusBlocksAndItems.HIBISCUS, Items.RED_DYE, "red_dye", 1, exporter);
         generateFlowerRecipes(HibiscusBlocksAndItems.GARDENIA, Items.WHITE_DYE, "white_dye", 2, exporter);
         offerShapelessRecipe(exporter, Items.PINK_DYE, LOTUS_FLOWER, "pink_dye", 1);
         offerCompactingRecipe(exporter, RecipeCategory.FOOD, HibiscusBlocksAndItems.DESERT_TURNIP_BLOCK, HibiscusBlocksAndItems.DESERT_TURNIP, "desert_turnip");
      }
   }

   public static class NatureSpiritItemTagGenerator extends FabricTagProvider.ItemTagProvider {

      public NatureSpiritItemTagGenerator(FabricDataOutput output, CompletableFuture <RegistryWrapper.WrapperLookup> completableFuture, @Nullable BlockTagProvider blockTagProvider) {
         super(output, completableFuture, blockTagProvider);
      }


      @Override protected void configure(RegistryWrapper.WrapperLookup arg) {

         for(String i : HibiscusRegistryHelper.WoodHashMap.keySet()) {
            this.copy(blockLogTags.get(i), itemLogTags.get(i));
            getOrCreateTagBuilder(ItemTags.BOATS).add(HibiscusBoatEntity.HibiscusBoat.getType(i).boat().asItem());
            getOrCreateTagBuilder(ItemTags.CHEST_BOATS).add(HibiscusBoatEntity.HibiscusBoat.getType(i).chestBoat().asItem());
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

      private void addWoodTags(HashMap <String, Block[]> woods, HashMap <String, TagKey <Block>> tag) {
         for(String i : woods.keySet()) {
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
         getOrCreateTagBuilder(BlockTags.PLANKS).add(new Block[]{HibiscusWoods.JOSHUA[2]});
         getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS).add(new Block[]{HibiscusWoods.JOSHUA[10]});
         getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add(new Block[]{HibiscusWoods.JOSHUA[5]});
         getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS).add(new Block[]{HibiscusWoods.JOSHUA[3]});
         getOrCreateTagBuilder(BlockTags.WOODEN_SLABS).add(new Block[]{HibiscusWoods.JOSHUA[4]});
         getOrCreateTagBuilder(BlockTags.WOODEN_FENCES).add(new Block[]{HibiscusWoods.JOSHUA[7]});
         getOrCreateTagBuilder(tag).add(HibiscusWoods.JOSHUA[1], HibiscusWoods.JOSHUA[0]);
         getOrCreateTagBuilder(BlockTags.OVERWORLD_NATURAL_LOGS).add(new Block[]{HibiscusWoods.JOSHUA[0]});
         getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN).addTag(tag);
         getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add(new Block[]{HibiscusWoods.JOSHUA[6]});
         getOrCreateTagBuilder(BlockTags.STANDING_SIGNS).add(new Block[]{HibiscusWoods.JOSHUA[11]});
         getOrCreateTagBuilder(BlockTags.WALL_SIGNS).add(new Block[]{HibiscusWoods.JOSHUA[12]});
         getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(new Block[]{HibiscusWoods.JOSHUA[9]});
         getOrCreateTagBuilder(BlockTags.FENCE_GATES).add(new Block[]{HibiscusWoods.JOSHUA[8]});
         getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS).add(HibiscusWoods.JOSHUA[13]);
         getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS).add(HibiscusWoods.JOSHUA[14]);
      }

      private void addTreeTags(HashMap <String, Block[]> saplings, HashMap <String, Block> leaves) {
         for(String i : saplings.keySet()) {
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
         addWoodTags(HibiscusRegistryHelper.WoodHashMap, blockLogTags);
         addJoshuaWoodTags(joshuaBlockLogtag);
         addTreeTags(HibiscusRegistryHelper.SaplingHashMap, HibiscusRegistryHelper.LeavesHashMap);
         addFlowerTags(HibiscusBlocksAndItems.HIBISCUS, HibiscusBlocksAndItems.POTTED_HIBISCUS, false);
         addFlowerTags(HibiscusBlocksAndItems.ANEMONE, HibiscusBlocksAndItems.POTTED_ANEMONE, false);
         addFlowerTags(HibiscusBlocksAndItems.BLUEBELL, false);
         addFlowerTags(HibiscusBlocksAndItems.TIGER_LILY, false);
         addFlowerTags(HibiscusBlocksAndItems.PURPLE_WILDFLOWER, false);
         addFlowerTags(HibiscusBlocksAndItems.YELLOW_WILDFLOWER, false);
         addFlowerTags(HibiscusBlocksAndItems.LAVENDER, true);
         addFlowerTags(HibiscusBlocksAndItems.BLEEDING_HEART, true);
         addFlowerTags(HibiscusBlocksAndItems.CARNATION, true);
         addFlowerTags(HibiscusBlocksAndItems.GARDENIA, true);
         addFlowerTags(HibiscusBlocksAndItems.CATTAIL, true);
         addFlowerTags(HibiscusBlocksAndItems.SNAPDRAGON, true);
         addFlowerTags(HibiscusBlocksAndItems.MARIGOLD, true);
         addFlowerTags(HibiscusBlocksAndItems.FOXGLOVE, true);
         getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add(new Block[]{HibiscusWoods.FRAMED_SUGI_DOOR});
         getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add(new Block[]{HibiscusWoods.FRAMED_SUGI_TRAPDOOR});
         getOrCreateTagBuilder(BlockTags.CLIMBABLE).add(HibiscusWoods.BLUE_WISTERIA_VINES_PLANT,
                 HibiscusWoods.BLUE_WISTERIA_VINES,
                 HibiscusWoods.WHITE_WISTERIA_VINES,
                 HibiscusWoods.WHITE_WISTERIA_VINES_PLANT,
                 HibiscusWoods.PINK_WISTERIA_VINES,
                 HibiscusWoods.PINK_WISTERIA_VINES_PLANT,
                 HibiscusWoods.PURPLE_WISTERIA_VINES,
                 HibiscusWoods.PURPLE_WISTERIA_VINES_PLANT,
                 HibiscusWoods.WILLOW_VINES_PLANT,
                 HibiscusWoods.WILLOW_VINES
         );
         getOrCreateTagBuilder(BlockTags.BEE_GROWABLES).add(HibiscusWoods.BLUE_WISTERIA_VINES_PLANT,
                 HibiscusWoods.BLUE_WISTERIA_VINES,
                 HibiscusWoods.WHITE_WISTERIA_VINES,
                 HibiscusWoods.WHITE_WISTERIA_VINES_PLANT,
                 HibiscusWoods.PINK_WISTERIA_VINES,
                 HibiscusWoods.PINK_WISTERIA_VINES_PLANT,
                 HibiscusWoods.PURPLE_WISTERIA_VINES,
                 HibiscusWoods.PURPLE_WISTERIA_VINES_PLANT,
                 LOTUS_FLOWER
         );
         getOrCreateTagBuilder(BlockTags.CROPS).add(HibiscusBlocksAndItems.DESERT_TURNIP_STEM);
         getOrCreateTagBuilder(BlockTags.MAINTAINS_FARMLAND).add(HibiscusBlocksAndItems.DESERT_TURNIP_STEM);
         getOrCreateTagBuilder(BlockTags.HOE_MINEABLE).add(HibiscusBlocksAndItems.SCORCHED_GRASS,
                 HibiscusBlocksAndItems.TALL_SCORCHED_GRASS,
                 SEDGE_GRASS,
                 TALL_SEDGE_GRASS,
                 LARGE_FLAXEN_FERN,
                 FLAXEN_FERN,
                 FRIGID_GRASS,
                 TALL_FRIGID_GRASS
         );
         getOrCreateTagBuilder(BlockTags.SWORD_EFFICIENT).add(HibiscusBlocksAndItems.SCORCHED_GRASS,
                 HibiscusBlocksAndItems.TALL_SCORCHED_GRASS,
                 SEDGE_GRASS,
                 TALL_SEDGE_GRASS,
                 LARGE_FLAXEN_FERN,
                 FLAXEN_FERN,
                 SHIITAKE_MUSHROOM,
                 FRIGID_GRASS,
                 TALL_FRIGID_GRASS
         );
         getOrCreateTagBuilder(BlockTags.REPLACEABLE_BY_TREES).add(HibiscusBlocksAndItems.SCORCHED_GRASS,
                 HibiscusBlocksAndItems.TALL_SCORCHED_GRASS,
                 SEDGE_GRASS,
                 TALL_SEDGE_GRASS,
                 LARGE_FLAXEN_FERN,
                 FLAXEN_FERN,
                 FRIGID_GRASS,
                 TALL_FRIGID_GRASS
         );
         getOrCreateTagBuilder(BlockTags.FLOWER_POTS).add(POTTED_FLAXEN_FERN, POTTED_FRIGID_GRASS, POTTED_SHIITAKE_MUSHROOM);
         getOrCreateTagBuilder(BlockTags.ENDERMAN_HOLDABLE).add(SHIITAKE_MUSHROOM);
      }
   }
}

