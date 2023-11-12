package net.hibiscus.naturespirit.datagen;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.hibiscus.naturespirit.blocks.DesertPlantBlock;
import net.hibiscus.naturespirit.entity.HibiscusBoatEntity;
import net.hibiscus.naturespirit.registration.*;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusMiscBlocks;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusColoredBlocks;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusWoods;
import net.hibiscus.naturespirit.util.HibiscusTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.data.client.*;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.*;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;
import static net.hibiscus.naturespirit.datagen.HibiscusBiomes.BiomesHashMap;
import static net.hibiscus.naturespirit.registration.block_registration.HibiscusMiscBlocks.*;
import static net.minecraft.data.client.BlockStateModelGenerator.*;
import static net.minecraft.data.client.TexturedModel.makeFactory;
import static net.minecraft.data.family.BlockFamilies.register;

public class NatureSpiritDataGen implements DataGeneratorEntrypoint {

   public static final String[] DYE_COLORS = {
           "white", "light_gray", "gray", "black", "brown", "red", "orange", "yellow", "lime",
           "green", "cyan", "light_blue", "blue", "purple", "magenta", "pink"
   };

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
   }

   @Override public void buildRegistry(RegistryBuilder registryBuilder) {
      registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, HibiscusConfiguredFeatures::bootstrap);
      registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, HibiscusPlacedFeatures::bootstrap);
      registryBuilder.addRegistry(RegistryKeys.BIOME, HibiscusBiomes::bootstrap);
      System.out.println("Built Registry");
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

      private void addWoodTable(HashMap <String, WoodSet> woods) {
         for(WoodSet woodSet : woods.values()) {

            if (woodSet.hasBark())
            {
               addDrop(woodSet.getWood());
               addDrop(woodSet.getStrippedWood());
            }
            if (woodSet.hasMosaic()) {
               addDrop(woodSet.getMosaic());
               addDrop(woodSet.getMosaicStairs());
               this.addDrop(woodSet.getMosaicSlab(), this::slabDrops);
            }
            if (woodSet.getWoodPreset() == WoodSet.WoodPreset.JOSHUA) {
               addDrop(woodSet.getBundle());
               addDrop(woodSet.getStrippedBundle());
            }
            addDrop(woodSet.getLog());
            addDrop(woodSet.getStrippedLog());
            addDrop(woodSet.getPlanks());
            addDrop(woodSet.getButton());
            this.addDrop(woodSet.getDoor(), this::doorDrops);
            addDrop(woodSet.getStairs());
            this.addDrop(woodSet.getSlab(), this::slabDrops);
            addDrop(woodSet.getFence());
            addDrop(woodSet.getTrapDoor());
            addDrop(woodSet.getSign());
            addDrop(woodSet.getHangingSign());
            addDrop(woodSet.getPressurePlate());
            addDrop(woodSet.getFenceGate());
         }
      }
      private void addStoneTable(HashMap <String, StoneSet> stones) {
         for(StoneSet stoneSet : stones.values()) {

            if (stoneSet.hasTiles())
            {
               addDrop(stoneSet.getTiles());
               addDrop(stoneSet.getTilesStairs());
               this.addDrop(stoneSet.getTilesSlab(), this::slabDrops);
               addDrop(stoneSet.getTilesWall());
            }
            if (stoneSet.hasMossy()) {
               addDrop(stoneSet.getMossyBricks());
               addDrop(stoneSet.getMossyBricksStairs());
               this.addDrop(stoneSet.getMossyBricksSlab(), this::slabDrops);
               addDrop(stoneSet.getMossyBricksWall());
            }
            if (stoneSet.hasCracked()) {
               addDrop(stoneSet.getCrackedBricks());
            }
            if (stoneSet.hasCobbled()) {
               addDrop(stoneSet.getCobbled());
               addDrop(stoneSet.getCobbledStairs());
               this.addDrop(stoneSet.getCobbledSlab(), this::slabDrops);
               addDrop(stoneSet.getCobbledWall());
               this.addDrop(stoneSet.getBase(), (block) -> this.drops(block, stoneSet.getCobbled()));
               if (stoneSet.hasMossy()) {
                  addDrop(stoneSet.getMossyCobbled());
                  addDrop(stoneSet.getMossyCobbledStairs());
                  this.addDrop(stoneSet.getMossyCobbledSlab(), this::slabDrops);
                  addDrop(stoneSet.getMossyCobbledWall());
               }
            } else {
               addDrop(stoneSet.getBase());
            }
            addDrop(stoneSet.getBaseStairs());
            this.addDrop(stoneSet.getBaseSlab(), this::slabDrops);

            addDrop(stoneSet.getBricks());
            addDrop(stoneSet.getBricksStairs());
            this.addDrop(stoneSet.getBricksSlab(), this::slabDrops);
            addDrop(stoneSet.getBricksWall());

            addDrop(stoneSet.getPolished());
            addDrop(stoneSet.getPolishedStairs());
            this.addDrop(stoneSet.getPolishedSlab(), this::slabDrops);
            addDrop(stoneSet.getPolishedWall());
         }
      }

      private void addFlowerTable(HashMap<String, FlowerSet> flowers) {
         for (FlowerSet flowerSet : flowers.values()) {
            if (flowerSet.isTall()) {
               this.addDrop(flowerSet.getFlowerBlock(), (block) -> this.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
            }
            else {
               this.addDrop(flowerSet.getFlowerBlock());
               addPottedPlantDrops(flowerSet.getPottedFlowerBlock());
            }
         }
      }

      public net.minecraft.loot.LootTable.Builder blackOlivesDrop(Block leaves, Block drop, float... chance) {
         return this.leavesDrops(leaves, drop, chance).pool(LootPool
                 .builder()
                 .rolls(ConstantLootNumberProvider.create(1.0F))
                 .conditionally(WITHOUT_SILK_TOUCH_NOR_SHEARS)
                 .with(((net.minecraft.loot.entry.LeafEntry.Builder <?>) this.addSurvivesExplosionCondition(leaves, ItemEntry.builder(HibiscusMiscBlocks.BLACK_OLIVES))).conditionally(
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
                 .with(((net.minecraft.loot.entry.LeafEntry.Builder <?>) this.addSurvivesExplosionCondition(leaves, ItemEntry.builder(HibiscusMiscBlocks.GREEN_OLIVES))).conditionally(
                         TableBonusLootCondition.builder(
                                 Enchantments.FORTUNE,
                                 0.01F,
                                 0.0111111114F,
                                 0.0125F,
                                 0.016666668F,
                                 0.05F
                         ))));
      }

      public LootTable.Builder coconutLeavesDrops(Block leaves) {
         return dropsWithSilkTouchOrShears(leaves, ((LeafEntry.Builder)this.applyExplosionDecay(leaves, ItemEntry.builder(Items.STICK).apply(SetCountLootFunction.builder(
                 UniformLootNumberProvider.create(1.0F, 2.0F))))).conditionally(TableBonusLootCondition.builder(Enchantments.FORTUNE, LEAVES_STICK_DROP_CHANCE)));
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
            else if(i.equals("coconut")) {
               this.addDrop(leavesType, this::coconutLeavesDrops);
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
         addFlowerTable(HibiscusRegistryHelper.FlowerHashMap);
         addStoneTable(HibiscusRegistryHelper.StoneHashMap);
         addWoodTable(HibiscusRegistryHelper.WoodHashMap);
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

         addVinesTable(HibiscusWoods.WISTERIA.getWhiteWisteriaVines(), HibiscusWoods.WISTERIA.getWhiteWisteriaVinesPlant());
         addVinesTable(HibiscusWoods.WISTERIA.getBlueWisteriaVines(), HibiscusWoods.WISTERIA.getBlueWisteriaVinesPlant());
         addVinesTable(HibiscusWoods.WISTERIA.getPurpleWisteriaVines(), HibiscusWoods.WISTERIA.getPurpleWisteriaVinesPlant());
         addVinesTable(HibiscusWoods.WISTERIA.getPinkWisteriaVines(), HibiscusWoods.WISTERIA.getPinkWisteriaVinesPlant());
         addVinesTable(HibiscusWoods.WILLOW.getWillowVines(), HibiscusWoods.WILLOW.getWillowVinesPlant());

         addVinesTable(LOTUS_STEM, LOTUS_STEM);
         this.addDrop(LOTUS_FLOWER, LOTUS_FLOWER_ITEM);

         this.addDrop(SHIITAKE_MUSHROOM);
         this.mushroomBlockDrops(SHIITAKE_MUSHROOM_BLOCK, SHIITAKE_MUSHROOM);

         this.addDrop(HibiscusWoods.COCONUT_THATCH);
         this.addDrop(HibiscusWoods.COCONUT_THATCH_CARPET);
         this.addDrop(HibiscusWoods.COCONUT_THATCH_STAIRS);
         this.addDrop(HibiscusWoods.COCONUT_THATCH_SLAB, this::slabDrops);


         this.addDrop(PAPER_DOOR, this::doorDrops);
         this.addDrop(PAPER_TRAPDOOR);
         this.addDrop(FRAMED_PAPER_DOOR, this::doorDrops);
         this.addDrop(FRAMED_PAPER_TRAPDOOR);
         this.addDrop(BLOOMING_PAPER_DOOR, this::doorDrops);
         this.addDrop(BLOOMING_PAPER_TRAPDOOR);
         this.addDrop(PAPER_BLOCK);
         this.addDrop(BLOOMING_PAPER_BLOCK);
         this.addDrop(FRAMED_PAPER_BLOCK);
         this.addDrop(PAPER_SIGN);
         this.addDrop(PAPER_HANGING_SIGN);
         this.addDrop(PAPER_PANEL);
         this.addDrop(BLOOMING_PAPER_PANEL);
         this.addDrop(FRAMED_PAPER_PANEL);

         this.addDrop(RED_MOSS_BLOCK);
         this.addDrop(RED_MOSS_CARPET);
         this.addDrop(HibiscusMiscBlocks.SANDY_SOIL);

         this.addDrop(PINK_SAND);
         this.addDrop(PINK_SANDSTONE);
         this.addDrop(PINK_SANDSTONE_SLAB, this::slabDrops);
         this.addDrop(PINK_SANDSTONE_STAIRS);
         this.addDrop(PINK_SANDSTONE_WALL);
         this.addDrop(SMOOTH_PINK_SANDSTONE);
         this.addDrop(SMOOTH_PINK_SANDSTONE_SLAB, this::slabDrops);
         this.addDrop(SMOOTH_PINK_SANDSTONE_STAIRS);
         this.addDrop(CUT_PINK_SANDSTONE);
         this.addDrop(CUT_PINK_SANDSTONE_SLAB, this::slabDrops);
         this.addDrop(CHISELED_PINK_SANDSTONE);

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
         this.addDrop(HibiscusColoredBlocks.KAOLIN_SLAB, this::slabDrops);
         this.addDrop(HibiscusColoredBlocks.WHITE_KAOLIN_SLAB, this::slabDrops);
         this.addDrop(HibiscusColoredBlocks.LIGHT_GRAY_KAOLIN_SLAB, this::slabDrops);
         this.addDrop(HibiscusColoredBlocks.GRAY_KAOLIN_SLAB, this::slabDrops);
         this.addDrop(HibiscusColoredBlocks.BLACK_KAOLIN_SLAB, this::slabDrops);
         this.addDrop(HibiscusColoredBlocks.BROWN_KAOLIN_SLAB, this::slabDrops);
         this.addDrop(HibiscusColoredBlocks.RED_KAOLIN_SLAB, this::slabDrops);
         this.addDrop(HibiscusColoredBlocks.ORANGE_KAOLIN_SLAB, this::slabDrops);
         this.addDrop(HibiscusColoredBlocks.YELLOW_KAOLIN_SLAB, this::slabDrops);
         this.addDrop(HibiscusColoredBlocks.LIME_KAOLIN_SLAB, this::slabDrops);
         this.addDrop(HibiscusColoredBlocks.GREEN_KAOLIN_SLAB, this::slabDrops);
         this.addDrop(HibiscusColoredBlocks.CYAN_KAOLIN_SLAB, this::slabDrops);
         this.addDrop(HibiscusColoredBlocks.LIGHT_BLUE_KAOLIN_SLAB, this::slabDrops);
         this.addDrop(HibiscusColoredBlocks.BLUE_KAOLIN_SLAB, this::slabDrops);
         this.addDrop(HibiscusColoredBlocks.PURPLE_KAOLIN_SLAB, this::slabDrops);
         this.addDrop(HibiscusColoredBlocks.MAGENTA_KAOLIN_SLAB, this::slabDrops);
         this.addDrop(HibiscusColoredBlocks.PINK_KAOLIN_SLAB, this::slabDrops);
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


         this.addDrop(HibiscusColoredBlocks.PAPER_LANTERN);
         this.addDrop(HibiscusColoredBlocks.WHITE_PAPER_LANTERN);
         this.addDrop(HibiscusColoredBlocks.LIGHT_GRAY_PAPER_LANTERN);
         this.addDrop(HibiscusColoredBlocks.GRAY_PAPER_LANTERN);
         this.addDrop(HibiscusColoredBlocks.BLACK_PAPER_LANTERN);
         this.addDrop(HibiscusColoredBlocks.BROWN_PAPER_LANTERN);
         this.addDrop(HibiscusColoredBlocks.RED_PAPER_LANTERN);
         this.addDrop(HibiscusColoredBlocks.ORANGE_PAPER_LANTERN);
         this.addDrop(HibiscusColoredBlocks.YELLOW_PAPER_LANTERN);
         this.addDrop(HibiscusColoredBlocks.LIME_PAPER_LANTERN);
         this.addDrop(HibiscusColoredBlocks.GREEN_PAPER_LANTERN);
         this.addDrop(HibiscusColoredBlocks.CYAN_PAPER_LANTERN);
         this.addDrop(HibiscusColoredBlocks.LIGHT_BLUE_PAPER_LANTERN);
         this.addDrop(HibiscusColoredBlocks.BLUE_PAPER_LANTERN);
         this.addDrop(HibiscusColoredBlocks.PURPLE_PAPER_LANTERN);
         this.addDrop(HibiscusColoredBlocks.MAGENTA_PAPER_LANTERN);
         this.addDrop(HibiscusColoredBlocks.PINK_PAPER_LANTERN);

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
         this.addDrop(HibiscusColoredBlocks.WHITE_CHALK_SLAB, this::slabDrops);
         this.addDrop(HibiscusColoredBlocks.LIGHT_GRAY_CHALK_SLAB, this::slabDrops);
         this.addDrop(HibiscusColoredBlocks.GRAY_CHALK_SLAB, this::slabDrops);
         this.addDrop(HibiscusColoredBlocks.BLACK_CHALK_SLAB, this::slabDrops);
         this.addDrop(HibiscusColoredBlocks.BROWN_CHALK_SLAB, this::slabDrops);
         this.addDrop(HibiscusColoredBlocks.RED_CHALK_SLAB, this::slabDrops);
         this.addDrop(HibiscusColoredBlocks.ORANGE_CHALK_SLAB, this::slabDrops);
         this.addDrop(HibiscusColoredBlocks.YELLOW_CHALK_SLAB, this::slabDrops);
         this.addDrop(HibiscusColoredBlocks.LIME_CHALK_SLAB, this::slabDrops);
         this.addDrop(HibiscusColoredBlocks.GREEN_CHALK_SLAB, this::slabDrops);
         this.addDrop(HibiscusColoredBlocks.CYAN_CHALK_SLAB, this::slabDrops);
         this.addDrop(HibiscusColoredBlocks.LIGHT_BLUE_CHALK_SLAB, this::slabDrops);
         this.addDrop(HibiscusColoredBlocks.BLUE_CHALK_SLAB, this::slabDrops);
         this.addDrop(HibiscusColoredBlocks.PURPLE_CHALK_SLAB, this::slabDrops);
         this.addDrop(HibiscusColoredBlocks.MAGENTA_CHALK_SLAB, this::slabDrops);
         this.addDrop(HibiscusColoredBlocks.PINK_CHALK_SLAB, this::slabDrops);
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

         this.addDrop(HibiscusMiscBlocks.DESERT_TURNIP_ROOT_BLOCK);

         this.addDrop(HibiscusWoods.COCONUT_SPROUT);
         this.addDrop(HibiscusWoods.COCONUT_BLOCK);
         this.addDrop(HibiscusWoods.YOUNG_COCONUT_BLOCK);

         dropsWithShears(FRIGID_GRASS);
         tallPlantDrop(TALL_FRIGID_GRASS, FRIGID_GRASS);
         addPottedPlantDrops(POTTED_FRIGID_GRASS);

         dropsWithShears(HibiscusMiscBlocks.SCORCHED_GRASS);
         tallPlantDrop(HibiscusMiscBlocks.TALL_SCORCHED_GRASS, HibiscusMiscBlocks.SCORCHED_GRASS);
         addPottedPlantDrops(POTTED_SCORCHED_GRASS);

         dropsWithShears(BEACH_GRASS);
         tallPlantDrop(TALL_BEACH_GRASS, BEACH_GRASS);
         addPottedPlantDrops(POTTED_BEACH_GRASS);

         dropsWithShears(SEDGE_GRASS);
         tallPlantDrop(TALL_SEDGE_GRASS, SEDGE_GRASS);
         addPottedPlantDrops(POTTED_SEDGE_GRASS);

         dropsWithShears(FLAXEN_FERN);
         addPottedPlantDrops(POTTED_FLAXEN_FERN);
         tallPlantDrop(LARGE_FLAXEN_FERN, FLAXEN_FERN);

         dropsWithShears(RED_BEARBERRIES);
         dropsWithShears(RED_BITTER_SPROUTS);
         addPottedPlantDrops(POTTED_RED_BEARBERRIES);
         dropsWithShears(GREEN_BEARBERRIES);
         dropsWithShears(GREEN_BITTER_SPROUTS);
         addPottedPlantDrops(POTTED_GREEN_BEARBERRIES);
         dropsWithShears(PURPLE_BEARBERRIES);
         dropsWithShears(PURPLE_BITTER_SPROUTS);
         addPottedPlantDrops(POTTED_PURPLE_BEARBERRIES);

      }
   }

   private static class NatureSpiritModelGenerator extends FabricModelProvider {

      private static final Model TALL_LARGE_CROSS = block("tall_large_cross", TextureKey.CROSS);
      private static final Model LARGE_CROSS = block("large_cross", TextureKey.CROSS);
      private static final Model TALL_CROSS = block("tall_cross", TextureKey.CROSS);
      private static final Model FLOWER_POT_TALL_CROSS = block("flower_pot_tall_cross", TextureKey.PLANT);
      private static final Model FLOWER_POT_LARGE_CROSS = block("flower_pot_large_cross", TextureKey.PLANT);
      private static final Model CROP = block("crop", TextureKey.CROP);
      private static final Model PAPER_LANTERN = block("template_paper_lantern", TextureKey.TOP, TextureKey.SIDE);
      private static final Model HANGING_PAPER_LANTERN = block("template_hanging_paper_lantern", "_hanging", TextureKey.TOP, TextureKey.SIDE);
      public static TextureMap paperLantern(Block block) {
         return (new TextureMap()).put(TextureKey.SIDE, getId(block)).put(TextureKey.TOP, getId(block).withSuffixedPath("_top"));
      }


     public static final TexturedModel.Factory TEMPLATE_PAPER_LANTERN = makeFactory(NatureSpiritModelGenerator::paperLantern, PAPER_LANTERN);
     public static final TexturedModel.Factory TEMPLATE_HANGING_PAPER_LANTERN = makeFactory(NatureSpiritModelGenerator::paperLantern, HANGING_PAPER_LANTERN);


      public NatureSpiritModelGenerator(FabricDataOutput output) {
         super(output);
      }

      private static Model block(String parent, TextureKey... requiredTextureKeys) {
         return new Model(Optional.of(new Identifier("natures_spirit", "block/" + parent)), Optional.empty(), requiredTextureKeys);
      }
      private static Model block(String parent, String variant, TextureKey... requiredTextureKeys) {
         return new Model(Optional.of(new Identifier("natures_spirit", "block/" + parent)), Optional.of(variant), requiredTextureKeys);
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

      public void createWall(Block block, Block wallBlock, BlockStateModelGenerator blockStateModelGenerator) {
         TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(block);
         Identifier identifier = Models.TEMPLATE_WALL_POST.upload(wallBlock, texturedModel.getTextures(), blockStateModelGenerator.modelCollector);
         Identifier identifier2 = Models.TEMPLATE_WALL_SIDE.upload(wallBlock, texturedModel.getTextures(), blockStateModelGenerator.modelCollector);
         Identifier identifier3 = Models.TEMPLATE_WALL_SIDE_TALL.upload(wallBlock, texturedModel.getTextures(), blockStateModelGenerator.modelCollector);
         blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createWallBlockState(wallBlock, identifier, identifier2, identifier3));
         Identifier identifier4 = Models.WALL_INVENTORY.upload(wallBlock, texturedModel.getTextures(), blockStateModelGenerator.modelCollector);
         blockStateModelGenerator.registerParentedItemModel(wallBlock, identifier4);
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

      private void generateWoodBlockStateModels(HashMap <String, WoodSet> woods, BlockStateModelGenerator blockStateModelGenerator) {
         for(WoodSet woodSet : woods.values()) {
            if (woodSet.getWoodPreset() == WoodSet.WoodPreset.BAMBOO) {
               blockStateModelGenerator.registerLog(woodSet.getLog()).log(woodSet.getLog());
               blockStateModelGenerator.registerLog(woodSet.getStrippedLog()).log(woodSet.getStrippedLog());
            } else
            if (woodSet.getWoodPreset() == WoodSet.WoodPreset.JOSHUA) {
               blockStateModelGenerator.registerLog(woodSet.getBundle()).log(woodSet.getBundle());
               blockStateModelGenerator.registerLog(woodSet.getStrippedBundle()).log(woodSet.getStrippedBundle());
            }
            else if (woodSet.hasBark())
            {
               blockStateModelGenerator.registerLog(woodSet.getLog()).log(woodSet.getLog()).wood(woodSet.getWood());
               blockStateModelGenerator.registerLog(woodSet.getStrippedLog()).log(woodSet.getStrippedLog()).wood(woodSet.getStrippedWood());
            }
            if (woodSet.hasMosaic()) {
               blockStateModelGenerator.registerSingleton(woodSet.getMosaic(), TexturedModel.CUBE_ALL);
               createSlab(woodSet.getMosaic(), woodSet.getMosaicSlab(), blockStateModelGenerator);
               createStairs(woodSet.getMosaic(), woodSet.getMosaicStairs(), blockStateModelGenerator);
            }
            blockStateModelGenerator.registerSingleton(woodSet.getPlanks(), TexturedModel.CUBE_ALL);
            createSlab(woodSet.getPlanks(), woodSet.getSlab(), blockStateModelGenerator);
            createStairs(woodSet.getPlanks(), woodSet.getStairs(), blockStateModelGenerator);
            createWoodDoor(woodSet.getDoor(), blockStateModelGenerator);
            createWoodTrapdoor(woodSet.getTrapDoor(), blockStateModelGenerator);
            createWoodFenceGate(woodSet.getPlanks(), woodSet.getFenceGate(), blockStateModelGenerator);
            createWoodFence(woodSet.getPlanks(), woodSet.getFence(), blockStateModelGenerator);
            createWoodButton(woodSet.getPlanks(), woodSet.getButton(), blockStateModelGenerator);
            createWoodPressurePlate(woodSet.getPlanks(), woodSet.getPressurePlate(), blockStateModelGenerator);
            createWoodSign(woodSet.getSign(), woodSet.getWallSign(), blockStateModelGenerator);
            createHangingSign(woodSet.getStrippedLog(), woodSet.getHangingSign(), woodSet.getHangingWallSign(), blockStateModelGenerator);
         }
      }

      private void generateFlowerSetBlockStateModels(HashMap <String, FlowerSet> flowers, BlockStateModelGenerator blockStateModelGenerator) {
         for(FlowerSet flowerSet : flowers.values()) {
            if (flowerSet.getPreset() == FlowerSet.FlowerPreset.SMALL) {
               generateFlowerBlockStateModels(flowerSet.getFlowerBlock(), flowerSet.getPottedFlowerBlock(), blockStateModelGenerator);
            }
            if (flowerSet.getPreset() == FlowerSet.FlowerPreset.ANEMONE) {
               generatePottedAnemone(flowerSet.getFlowerBlock(), flowerSet.getPottedFlowerBlock(), blockStateModelGenerator);
            }
            if (flowerSet.getPreset() == FlowerSet.FlowerPreset.TALL) {
               blockStateModelGenerator.registerDoubleBlock(flowerSet.getFlowerBlock(), TintType.NOT_TINTED);
            }
            if (flowerSet.getPreset() == FlowerSet.FlowerPreset.BIG_TALL) {
               generateTallLargeFlower(flowerSet.getFlowerBlock(), blockStateModelGenerator);
            }
            if (flowerSet.getPreset() == FlowerSet.FlowerPreset.BIG_SMALL) {
               generateLargeFlower(flowerSet.getFlowerBlock(), flowerSet.getPottedFlowerBlock(), blockStateModelGenerator);
            }
         }
      }

      private void generateStoneBlockStateModels(HashMap <String, StoneSet> stones, BlockStateModelGenerator blockStateModelGenerator) {
         for(StoneSet stoneSet : stones.values()) {
            if (stoneSet.hasTiles()) {
               createWall(stoneSet.getTiles(), stoneSet.getTilesWall(), blockStateModelGenerator);
               createSlab(stoneSet.getTiles(), stoneSet.getTilesSlab(), blockStateModelGenerator);
               createStairs(stoneSet.getTiles(), stoneSet.getTilesStairs(), blockStateModelGenerator);
               blockStateModelGenerator.registerSimpleCubeAll(stoneSet.getTiles());
            }
            if (stoneSet.hasCobbled()) {
               createWall(stoneSet.getCobbled(), stoneSet.getCobbledWall(), blockStateModelGenerator);
               createSlab(stoneSet.getCobbled(), stoneSet.getCobbledSlab(), blockStateModelGenerator);
               createStairs(stoneSet.getCobbled(), stoneSet.getCobbledStairs(), blockStateModelGenerator);
               blockStateModelGenerator.registerSimpleCubeAll(stoneSet.getCobbled());
               if (stoneSet.hasMossy()) {
                  createWall(stoneSet.getMossyCobbled(), stoneSet.getMossyCobbledWall(), blockStateModelGenerator);
                  createSlab(stoneSet.getMossyCobbled(), stoneSet.getMossyCobbledSlab(), blockStateModelGenerator);
                  createStairs(stoneSet.getMossyCobbled(), stoneSet.getMossyCobbledStairs(), blockStateModelGenerator);
                  blockStateModelGenerator.registerSimpleCubeAll(stoneSet.getMossyCobbled());
               }
            }
            if (stoneSet.hasMossy()) {
               createWall(stoneSet.getMossyBricks(), stoneSet.getMossyBricksWall(), blockStateModelGenerator);
               createSlab(stoneSet.getMossyBricks(), stoneSet.getMossyBricksSlab(), blockStateModelGenerator);
               createStairs(stoneSet.getMossyBricks(), stoneSet.getMossyBricksStairs(), blockStateModelGenerator);
               blockStateModelGenerator.registerSimpleCubeAll(stoneSet.getMossyBricks());
            }
            if (stoneSet.hasCracked()) {
               blockStateModelGenerator.registerSimpleCubeAll(stoneSet.getCrackedBricks());
            }
            createWall(stoneSet.getBricks(), stoneSet.getBricksWall(), blockStateModelGenerator);
            createSlab(stoneSet.getBricks(), stoneSet.getBricksSlab(), blockStateModelGenerator);
            createStairs(stoneSet.getBricks(), stoneSet.getBricksStairs(), blockStateModelGenerator);
            blockStateModelGenerator.registerSimpleCubeAll(stoneSet.getBricks());
            
            createWall(stoneSet.getPolished(), stoneSet.getPolishedWall(), blockStateModelGenerator);
            createSlab(stoneSet.getPolished(), stoneSet.getPolishedSlab(), blockStateModelGenerator);
            createStairs(stoneSet.getPolished(), stoneSet.getPolishedStairs(), blockStateModelGenerator);
            blockStateModelGenerator.registerSimpleCubeAll(stoneSet.getPolished());

            createSlab(stoneSet.getBase(), stoneSet.getBaseSlab(), blockStateModelGenerator);
            createStairs(stoneSet.getBase(), stoneSet.getBaseStairs(), blockStateModelGenerator);
            blockStateModelGenerator.registerSimpleCubeAll(stoneSet.getBase());
            
            blockStateModelGenerator.registerSimpleCubeAll(stoneSet.getChiseled());
         }
      }

      private void generateTreeBlockStateModels(HashMap <String, Block[]> saplings, HashMap <String, Block> leaves, BlockStateModelGenerator blockStateModelGenerator) {
         for(String i : leaves.keySet()) {
            Block[] saplingType = saplings.get(i);
            Block leavesType = leaves.get(i);
            if (!Objects.equals(i, "coconut")) {
               blockStateModelGenerator.registerSingleton(leavesType, TexturedModel.LEAVES);
               blockStateModelGenerator.registerFlowerPotPlant(saplingType[0], saplingType[1], TintType.NOT_TINTED);
            }
         }
      }

      public final void registerTallCrossBlockState(Block block, TextureMap crossTexture, BlockStateModelGenerator blockStateModelGenerators) {
         Identifier identifier = TALL_CROSS.upload(block, crossTexture, blockStateModelGenerators.modelCollector);
         blockStateModelGenerators.blockStateCollector.accept(createSingletonBlockState(block, identifier));
      }

      public final void registerPaperLantern(Block lantern, BlockStateModelGenerator blockStateModelGenerator) {
         Identifier identifier = TEMPLATE_PAPER_LANTERN.upload(lantern, blockStateModelGenerator.modelCollector);
         Identifier identifier2 = TEMPLATE_HANGING_PAPER_LANTERN.upload(lantern, blockStateModelGenerator.modelCollector);
         blockStateModelGenerator.registerItemModel(lantern.asItem());
         blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(lantern).coordinate(createBooleanModelMap(Properties.HANGING, identifier2, identifier)));
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

      public final void generateLargeFlower(Block block, Block flowerPot, BlockStateModelGenerator blockStateModelGenerators) {
         registerSpecificFlowerItemModel(block, blockStateModelGenerators);
         registerTallLargeBlockState(block, TextureMap.cross(block), blockStateModelGenerators);
         TextureMap textureMap = TextureMap.plant(block);
         Identifier identifier = FLOWER_POT_LARGE_CROSS.upload(flowerPot, textureMap, blockStateModelGenerators.modelCollector);
         blockStateModelGenerators.blockStateCollector.accept(createSingletonBlockState(flowerPot, identifier));
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
      private void registerCheese(BlockStateModelGenerator blockStateModelGenerator) {
         blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(CHEESE_BLOCK).coordinate(BlockStateVariantMap.create(Properties.BITES).register(0, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockModelId(CHEESE_BLOCK))).register(1, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(CHEESE_BLOCK, "_slice1"))).register(2, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(CHEESE_BLOCK, "_slice2"))).register(3, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(CHEESE_BLOCK, "_slice3"))).register(4, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(CHEESE_BLOCK, "_slice4"))).register(5, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(CHEESE_BLOCK, "_slice5"))).register(6, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(CHEESE_BLOCK, "_slice6")))));
         blockStateModelGenerator.excludeFromSimpleItemModelGeneration(CHEESE_BLOCK);
         blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(CHEESE_CAULDRON, Models.TEMPLATE_CAULDRON_FULL.upload(CHEESE_CAULDRON, TextureMap.cauldron(TextureMap.getId(CHEESE_BLOCK)), blockStateModelGenerator.modelCollector)));
         blockStateModelGenerator.excludeFromSimpleItemModelGeneration(CHEESE_CAULDRON);
      }

      public final void registerPaperPanels(Block block, Block paperPanel, BlockStateModelGenerator blockStateModelGenerator) {
         TextureMap textureMap = TextureMap.paneAndTopForEdge(block, paperPanel);
         Identifier identifier = Models.TEMPLATE_GLASS_PANE_POST.upload(paperPanel, textureMap, blockStateModelGenerator.modelCollector);
         Identifier identifier2 = Models.TEMPLATE_GLASS_PANE_SIDE.upload(paperPanel, textureMap, blockStateModelGenerator.modelCollector);
         Identifier identifier3 = Models.TEMPLATE_GLASS_PANE_SIDE_ALT.upload(paperPanel, textureMap, blockStateModelGenerator.modelCollector);
         Identifier identifier4 = Models.TEMPLATE_GLASS_PANE_NOSIDE.upload(paperPanel, textureMap, blockStateModelGenerator.modelCollector);
         Identifier identifier5 = Models.TEMPLATE_GLASS_PANE_NOSIDE_ALT.upload(paperPanel, textureMap, blockStateModelGenerator.modelCollector);
         Item item = paperPanel.asItem();
         Models.GENERATED.upload(ModelIds.getItemModelId(item), TextureMap.layer0(block), blockStateModelGenerator.modelCollector);
         blockStateModelGenerator.blockStateCollector.accept(MultipartBlockStateSupplier.create(paperPanel).with(BlockStateVariant.create().put(VariantSettings.MODEL, identifier)).with(When.create().set(Properties.NORTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, identifier2)).with(When.create().set(Properties.EAST, true), BlockStateVariant.create().put(VariantSettings.MODEL, identifier2).put(VariantSettings.Y, VariantSettings.Rotation.R90)).with(When.create().set(Properties.SOUTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, identifier3)).with(When.create().set(Properties.WEST, true), BlockStateVariant.create().put(VariantSettings.MODEL, identifier3).put(VariantSettings.Y, VariantSettings.Rotation.R90)).with(When.create().set(Properties.NORTH, false), BlockStateVariant.create().put(VariantSettings.MODEL, identifier4)).with(When.create().set(Properties.EAST, false), BlockStateVariant.create().put(VariantSettings.MODEL, identifier5)).with(When.create().set(Properties.SOUTH, false), BlockStateVariant.create().put(VariantSettings.MODEL, identifier5).put(VariantSettings.Y, VariantSettings.Rotation.R90)).with(When.create().set(Properties.WEST, false), BlockStateVariant.create().put(VariantSettings.MODEL, identifier4).put(VariantSettings.Y, VariantSettings.Rotation.R270)));
      }

      public final void registerNorthDefaultHorizontalFacing(TexturedModel.Factory modelFactory, Block block, BlockStateModelGenerator blockStateModelGenerator) {
            Identifier identifier = modelFactory.upload(block, blockStateModelGenerator.modelCollector);
         blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block, BlockStateVariant.create().put(VariantSettings.MODEL, identifier)).coordinate(createNorthDefaultHorizontalRotationStates()));
      }

      @Override public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
         generateWoodBlockStateModels(HibiscusRegistryHelper.WoodHashMap, blockStateModelGenerator);
         generateFlowerSetBlockStateModels(HibiscusRegistryHelper.FlowerHashMap, blockStateModelGenerator);
         generateStoneBlockStateModels(HibiscusRegistryHelper.StoneHashMap, blockStateModelGenerator);
         generateTreeBlockStateModels(HibiscusRegistryHelper.SaplingHashMap, HibiscusRegistryHelper.LeavesHashMap, blockStateModelGenerator);

         blockStateModelGenerator.registerAmethyst(CALCITE_CLUSTER);
         blockStateModelGenerator.registerAmethyst(SMALL_CALCITE_BUD);
         blockStateModelGenerator.registerAmethyst(LARGE_CALCITE_BUD);

         blockStateModelGenerator.registerWoolAndCarpet(RED_MOSS_BLOCK, RED_MOSS_CARPET);

         registerCheese(blockStateModelGenerator);
         generateFlowerBlockStateModels(FLAXEN_FERN, POTTED_FLAXEN_FERN, blockStateModelGenerator);
         generateFlowerBlockStateModels(FRIGID_GRASS, POTTED_FRIGID_GRASS, blockStateModelGenerator);
         generateFlowerBlockStateModels(SHIITAKE_MUSHROOM, POTTED_SHIITAKE_MUSHROOM, blockStateModelGenerator);
         registerMushroomBlock(SHIITAKE_MUSHROOM_BLOCK, blockStateModelGenerator);
         registerCropWithoutItem(HibiscusMiscBlocks.DESERT_TURNIP_STEM, DesertPlantBlock.AGE, blockStateModelGenerator, 0, 1, 2, 3, 4, 5, 6, 7);
         blockStateModelGenerator.registerDoubleBlock(TALL_FRIGID_GRASS, TintType.NOT_TINTED);
         generateTallLargeFlower(HibiscusMiscBlocks.TALL_SCORCHED_GRASS, blockStateModelGenerator);
         generateTallLargeFlower(TALL_BEACH_GRASS, blockStateModelGenerator);
         generateTallLargeFlower(TALL_SEDGE_GRASS, blockStateModelGenerator);
         generateLargeFlower(HibiscusMiscBlocks.SCORCHED_GRASS, POTTED_SCORCHED_GRASS, blockStateModelGenerator);
         generateLargeFlower(RED_BEARBERRIES, POTTED_RED_BEARBERRIES, blockStateModelGenerator);
         generateLargeFlower(PURPLE_BEARBERRIES, POTTED_PURPLE_BEARBERRIES, blockStateModelGenerator);
         generateLargeFlower(GREEN_BEARBERRIES, POTTED_GREEN_BEARBERRIES, blockStateModelGenerator);
         generateLargeFlower(GREEN_BITTER_SPROUTS, blockStateModelGenerator);
         generateLargeFlower(RED_BITTER_SPROUTS, blockStateModelGenerator);
         generateLargeFlower(PURPLE_BITTER_SPROUTS, blockStateModelGenerator);
         generateLargeFlower(BEACH_GRASS, POTTED_BEACH_GRASS, blockStateModelGenerator);
         generateLargeFlower(SEDGE_GRASS, POTTED_SEDGE_GRASS, blockStateModelGenerator);
         generateVineBlockStateModels(HibiscusWoods.WISTERIA.getBlueWisteriaVines(), HibiscusWoods.WISTERIA.getBlueWisteriaVinesPlant(), blockStateModelGenerator);
         generateVineBlockStateModels(HibiscusWoods.WISTERIA.getWhiteWisteriaVines(), HibiscusWoods.WISTERIA.getWhiteWisteriaVinesPlant(), blockStateModelGenerator);
         generateVineBlockStateModels(HibiscusWoods.WISTERIA.getPurpleWisteriaVines(), HibiscusWoods.WISTERIA.getPurpleWisteriaVinesPlant(), blockStateModelGenerator);
         generateVineBlockStateModels(HibiscusWoods.WISTERIA.getPinkWisteriaVines(), HibiscusWoods.WISTERIA.getPinkWisteriaVinesPlant(), blockStateModelGenerator);
         generateVineBlockStateModels(HibiscusWoods.WILLOW.getWillowVines(), HibiscusWoods.WILLOW.getWillowVinesPlant(), blockStateModelGenerator);



         createWoodDoor(PAPER_DOOR, blockStateModelGenerator);
         createWoodTrapdoor(PAPER_TRAPDOOR, blockStateModelGenerator);
         createWoodDoor(FRAMED_PAPER_DOOR, blockStateModelGenerator);
         createWoodTrapdoor(FRAMED_PAPER_TRAPDOOR, blockStateModelGenerator);
         createWoodDoor(BLOOMING_PAPER_DOOR, blockStateModelGenerator);
         createWoodTrapdoor(BLOOMING_PAPER_TRAPDOOR, blockStateModelGenerator);
         createWoodSign(PAPER_SIGN, PAPER_WALL_SIGN, blockStateModelGenerator);
         createHangingSign(PAPER_BLOCK, PAPER_HANGING_SIGN, PAPER_WALL_HANGING_SIGN, blockStateModelGenerator);
         blockStateModelGenerator.registerSingleton(PAPER_BLOCK, TexturedModel.CUBE_ALL);
         blockStateModelGenerator.registerSingleton(FRAMED_PAPER_BLOCK, TexturedModel.CUBE_ALL);
         registerNorthDefaultHorizontalFacing(TexturedModel.TEMPLATE_GLAZED_TERRACOTTA, BLOOMING_PAPER_BLOCK, blockStateModelGenerator);
         registerPaperPanels(PAPER_BLOCK, PAPER_PANEL, blockStateModelGenerator);
         registerPaperPanels(FRAMED_PAPER_BLOCK, FRAMED_PAPER_PANEL, blockStateModelGenerator);
         registerPaperPanels(BLOOMING_PAPER_BLOCK, BLOOMING_PAPER_PANEL, blockStateModelGenerator);

         registerPaperLantern(HibiscusColoredBlocks.PAPER_LANTERN, blockStateModelGenerator);
         registerPaperLantern(HibiscusColoredBlocks.WHITE_PAPER_LANTERN, blockStateModelGenerator);
         registerPaperLantern(HibiscusColoredBlocks.LIGHT_GRAY_PAPER_LANTERN, blockStateModelGenerator);
         registerPaperLantern(HibiscusColoredBlocks.GRAY_PAPER_LANTERN, blockStateModelGenerator);
         registerPaperLantern(HibiscusColoredBlocks.BLACK_PAPER_LANTERN, blockStateModelGenerator);
         registerPaperLantern(HibiscusColoredBlocks.BROWN_PAPER_LANTERN, blockStateModelGenerator);
         registerPaperLantern(HibiscusColoredBlocks.RED_PAPER_LANTERN, blockStateModelGenerator);
         registerPaperLantern(HibiscusColoredBlocks.ORANGE_PAPER_LANTERN, blockStateModelGenerator);
         registerPaperLantern(HibiscusColoredBlocks.YELLOW_PAPER_LANTERN, blockStateModelGenerator);
         registerPaperLantern(HibiscusColoredBlocks.LIME_PAPER_LANTERN, blockStateModelGenerator);
         registerPaperLantern(HibiscusColoredBlocks.GREEN_PAPER_LANTERN, blockStateModelGenerator);
         registerPaperLantern(HibiscusColoredBlocks.CYAN_PAPER_LANTERN, blockStateModelGenerator);
         registerPaperLantern(HibiscusColoredBlocks.LIGHT_BLUE_PAPER_LANTERN, blockStateModelGenerator);
         registerPaperLantern(HibiscusColoredBlocks.BLUE_PAPER_LANTERN, blockStateModelGenerator);
         registerPaperLantern(HibiscusColoredBlocks.PURPLE_PAPER_LANTERN, blockStateModelGenerator);
         registerPaperLantern(HibiscusColoredBlocks.MAGENTA_PAPER_LANTERN, blockStateModelGenerator);
         registerPaperLantern(HibiscusColoredBlocks.PINK_PAPER_LANTERN, blockStateModelGenerator);


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

         blockStateModelGenerator.registerAxisRotated(HibiscusMiscBlocks.DESERT_TURNIP_ROOT_BLOCK, TexturedModel.END_FOR_TOP_CUBE_COLUMN, TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL);
      }

      @Override public void generateItemModels(ItemModelGenerator itemModelGenerator) {
         itemModelGenerator.register(HibiscusMiscBlocks.GREEN_OLIVES, Models.GENERATED);
         itemModelGenerator.register(HibiscusMiscBlocks.BLACK_OLIVES, Models.GENERATED);
         itemModelGenerator.register(HibiscusMiscBlocks.DESERT_TURNIP, Models.GENERATED);
         itemModelGenerator.register(HibiscusWoods.COCONUT_SHELL, Models.GENERATED);
         itemModelGenerator.register(HibiscusWoods.YOUNG_COCONUT_SHELL, Models.GENERATED);
         itemModelGenerator.register(HibiscusWoods.YOUNG_COCONUT_HALF, Models.GENERATED);
         itemModelGenerator.register(HibiscusWoods.COCONUT_HALF, Models.GENERATED);
         itemModelGenerator.register(CALCITE_SHARD, Models.GENERATED);
         itemModelGenerator.register(CHALK_POWDER, Models.GENERATED);
         itemModelGenerator.register(VINEGAR_BOTTLE, Models.GENERATED);
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
      private void generateBlockTranslations(Block block, TranslationBuilder translationBuilder) {
         String temp = capitalizeString(Registries.BLOCK.getId(block).getPath().replace("_", " "));
         translationBuilder.add(block, temp);
      }
      private void generateItemTranslations(Item item, TranslationBuilder translationBuilder) {
         String temp = capitalizeString(Registries.ITEM.getId(item).getPath().replace("_", " "));
         translationBuilder.add(item, temp);
      }

      private void generateWoodTranslations(HashMap <String, WoodSet> woods, TranslationBuilder translationBuilder) {
         for(WoodSet woodSet : woods.values()) {
            for(Block block : woodSet.getRegisteredBlocksList()) {
                  generateBlockTranslations(block, translationBuilder);
            }
            generateBlockTranslations(woodSet.getSign(), translationBuilder);
            generateBlockTranslations(woodSet.getHangingSign(), translationBuilder);
               translationBuilder.add(woodSet.getBoatItem(), capitalizeString(woodSet.getName().replace("_", " ")) + " Boat");
               translationBuilder.add(woodSet.getChestBoatItem(), capitalizeString(woodSet.getName().replace("_", " ")) + " Boat With Chest");
            generateArchExTranslations(woodSet.getName(), translationBuilder);
         }
      }
      private void generateStoneTranslations(HashMap <String, StoneSet> stones, TranslationBuilder translationBuilder) {
         for(StoneSet stoneSet : stones.values()) {
            for(Block block : stoneSet.getRegisteredBlocksList()) {
               generateBlockTranslations(block, translationBuilder);
            }
         }
      }
      private void generateFlowerTranslations(HashMap <String, FlowerSet> flowers, TranslationBuilder translationBuilder) {
         for(FlowerSet flowerSet : flowers.values()) {
            for(Block block : flowerSet.getRegisteredBlocksList()) {
               generateBlockTranslations(block, translationBuilder);
            }
         }
      }
      private void generateBiomeTranslations(TranslationBuilder translationBuilder) {
         for(String name : BiomesHashMap.keySet()) {
            RegistryKey <Biome> biome = BiomesHashMap.get(name);
            translationBuilder.add(biome.toString().replace("ResourceKey[minecraft:worldgen/biome / natures_spirit:", "biome.natures_spirit.").replace("]", ""), capitalizeString(name.replace("_", " ")));
         }
      }

      private void generateArchExTranslations(String group, TranslationBuilder translationBuilder) {
         translationBuilder.add("architecture_extensions.grouped_block." + MOD_ID + "." + group, capitalizeString(group.replace("_", " ")));
      }
      private void generateColoredTranslations(String group, TranslationBuilder translationBuilder) {
         translationBuilder.add("block." + MOD_ID + "." + group, capitalizeString(group.replace("_", " ")));
      }

      @Override public void generateTranslations(TranslationBuilder translationBuilder) {
         generateBiomeTranslations(translationBuilder);
         generateWoodTranslations(HibiscusRegistryHelper.WoodHashMap ,translationBuilder);
         generateStoneTranslations(HibiscusRegistryHelper.StoneHashMap ,translationBuilder);
         generateFlowerTranslations(HibiscusRegistryHelper.FlowerHashMap ,translationBuilder);
         translationBuilder.add(HibiscusItemGroups.NS_MISC_ITEM_GROUP, "Nature's Spirit Misc.");
         translationBuilder.add(HibiscusItemGroups.NS_WOOD_ITEM_GROUP, "Nature's Spirit Woods");
         translationBuilder.add("stat.minecraft.eat_pizza_slice", "Pizza Slices Eaten");
         translationBuilder.add(VINEGAR_BOTTLE, "Vinegar Bottle");
         translationBuilder.add(CHALK_POWDER, "Chalk Powder");
         translationBuilder.add(HibiscusMiscBlocks.GREEN_OLIVES, "Green Olives");
         translationBuilder.add(HibiscusMiscBlocks.BLACK_OLIVES, "Black Olives");
         translationBuilder.add(HibiscusMiscBlocks.DESERT_TURNIP, "Desert Turnip");
         translationBuilder.add(CALCITE_SHARD, "Calcite Shard");
         translationBuilder.add(SMALL_CALCITE_BUD, "Small Calcite Bud");
         translationBuilder.add(LARGE_CALCITE_BUD, "Large Calcite Bud");
         translationBuilder.add(CALCITE_CLUSTER, "Calcite Cluster");
         generateBlockTranslations(TALL_SCORCHED_GRASS, translationBuilder);
         generateBlockTranslations(PURPLE_BEARBERRIES, translationBuilder);
         generateBlockTranslations(RED_BEARBERRIES, translationBuilder);
         generateBlockTranslations(GREEN_BEARBERRIES, translationBuilder);
         generateBlockTranslations(RED_BITTER_SPROUTS, translationBuilder);
         generateBlockTranslations(PURPLE_BITTER_SPROUTS, translationBuilder);
         generateBlockTranslations(GREEN_BITTER_SPROUTS, translationBuilder);
         generateBlockTranslations(SCORCHED_GRASS, translationBuilder);
         generateBlockTranslations(TALL_BEACH_GRASS, translationBuilder);
         generateBlockTranslations(BEACH_GRASS, translationBuilder);
         generateBlockTranslations(TALL_SEDGE_GRASS, translationBuilder);
         generateBlockTranslations(SEDGE_GRASS, translationBuilder);
         generateBlockTranslations(TALL_FRIGID_GRASS, translationBuilder);
         generateBlockTranslations(FRIGID_GRASS, translationBuilder);
         generateBlockTranslations(LARGE_FLAXEN_FERN, translationBuilder);
         generateBlockTranslations(FLAXEN_FERN, translationBuilder);
         generateBlockTranslations(SHIITAKE_MUSHROOM, translationBuilder);
         generateBlockTranslations(SHIITAKE_MUSHROOM_BLOCK, translationBuilder);
         generateBlockTranslations(PAPER_BLOCK, translationBuilder);
         generateBlockTranslations(FRAMED_PAPER_BLOCK, translationBuilder);
         generateBlockTranslations(BLOOMING_PAPER_BLOCK, translationBuilder);
         generateBlockTranslations(PAPER_DOOR, translationBuilder);
         generateBlockTranslations(FRAMED_PAPER_DOOR, translationBuilder);
         generateBlockTranslations(BLOOMING_PAPER_DOOR, translationBuilder);
         generateBlockTranslations(PAPER_TRAPDOOR, translationBuilder);
         generateBlockTranslations(BLOOMING_PAPER_TRAPDOOR, translationBuilder);
         generateBlockTranslations(FRAMED_PAPER_TRAPDOOR, translationBuilder);
         generateBlockTranslations(PAPER_SIGN, translationBuilder);
         generateBlockTranslations(PAPER_HANGING_SIGN, translationBuilder);
         generateBlockTranslations(PAPER_PANEL, translationBuilder);
         generateBlockTranslations(FRAMED_PAPER_PANEL, translationBuilder);
         generateBlockTranslations(BLOOMING_PAPER_PANEL, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.PAPER_LANTERN, translationBuilder);
         generateBlockTranslations(HibiscusMiscBlocks.CATTAIL, translationBuilder);
         generateBlockTranslations(HibiscusMiscBlocks.DESERT_TURNIP_ROOT_BLOCK, translationBuilder);
         generateBlockTranslations(HibiscusMiscBlocks.DESERT_TURNIP_BLOCK, translationBuilder);
         generateBlockTranslations(LOTUS_FLOWER, translationBuilder);
         generateBlockTranslations(LOTUS_STEM, translationBuilder);
         generateBlockTranslations(RED_MOSS_BLOCK, translationBuilder);
         generateBlockTranslations(RED_MOSS_CARPET, translationBuilder);

         generateBlockTranslations(HibiscusColoredBlocks.KAOLIN, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.KAOLIN_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.KAOLIN_STAIRS, translationBuilder);

         generateBlockTranslations(HibiscusWoods.COCONUT_BLOCK, translationBuilder);
         generateBlockTranslations(HibiscusWoods.YOUNG_COCONUT_BLOCK, translationBuilder);
         generateBlockTranslations(HibiscusWoods.COCONUT_SPROUT, translationBuilder);
         generateItemTranslations(HibiscusWoods.COCONUT_SHELL, translationBuilder);
         generateItemTranslations(HibiscusWoods.YOUNG_COCONUT_SHELL, translationBuilder);
         generateItemTranslations(HibiscusWoods.COCONUT_HALF, translationBuilder);
         generateItemTranslations(HibiscusWoods.YOUNG_COCONUT_HALF, translationBuilder);
         generateBlockTranslations(HibiscusWoods.COCONUT_THATCH, translationBuilder);
         generateBlockTranslations(HibiscusWoods.COCONUT_THATCH_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusWoods.COCONUT_THATCH_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusWoods.COCONUT_THATCH_CARPET, translationBuilder);

         generateBlockTranslations(PINK_SAND, translationBuilder);
         generateBlockTranslations(PINK_SANDSTONE, translationBuilder);
         generateBlockTranslations(PINK_SANDSTONE_SLAB, translationBuilder);
         generateBlockTranslations(PINK_SANDSTONE_STAIRS, translationBuilder);
         generateBlockTranslations(PINK_SANDSTONE_WALL, translationBuilder);
         generateBlockTranslations(SMOOTH_PINK_SANDSTONE, translationBuilder);
         generateBlockTranslations(SMOOTH_PINK_SANDSTONE_STAIRS, translationBuilder);
         generateBlockTranslations(SMOOTH_PINK_SANDSTONE_SLAB, translationBuilder);
         generateBlockTranslations(CHISELED_PINK_SANDSTONE, translationBuilder);
         generateBlockTranslations(CUT_PINK_SANDSTONE, translationBuilder);
         generateBlockTranslations(CUT_PINK_SANDSTONE_SLAB, translationBuilder);

         generateBlockTranslations(HibiscusMiscBlocks.SANDY_SOIL, translationBuilder);
         generateBlockTranslations(CHEESE_BLOCK, translationBuilder);
         generateBlockTranslations(CHEESE_CAULDRON, translationBuilder);
         generateBlockTranslations(MILK_CAULDRON, translationBuilder);
         translationBuilder.add(CHEESE_BUCKET, "Cheese Bucket");
         translationBuilder.add("block.natures_spirit.pizza.minecraft.cooked_chicken", "With Cooked Chicken");
         translationBuilder.add("block.natures_spirit.pizza.natures_spirit.green_olives", "With Green Olives");
         translationBuilder.add("block.natures_spirit.pizza.natures_spirit.black_olives", "With Black Olives");
         translationBuilder.add("block.natures_spirit.pizza.minecraft.brown_mushroom", "With Mushrooms");
         translationBuilder.add("block.natures_spirit.pizza.minecraft.beetroot", "With Beetroots");
         translationBuilder.add("block.natures_spirit.pizza.minecraft.carrot", "With Carrots");
         translationBuilder.add("block.natures_spirit.pizza.minecraft.cooked_cod", "With Cooked Cod");
         translationBuilder.add("block.natures_spirit.pizza.minecraft.cooked_pork", "With Cooked Pork");
         translationBuilder.add("block.natures_spirit.pizza.minecraft.cooked_rabbit", "With Cooked Rabbit");
         translationBuilder.add(HibiscusMiscBlocks.HALF_PIZZA, "Half of a Pizza");
         translationBuilder.add(HibiscusMiscBlocks.THREE_QUARTERS_PIZZA, "Three Quarters of a Pizza");
         translationBuilder.add(HibiscusMiscBlocks.QUARTER_PIZZA, "Quarter of a Pizza");
         translationBuilder.add(HibiscusMiscBlocks.WHOLE_PIZZA, "Pizza");

         translationBuilder.add("pack.natures_spirit.bushy_leaves_compatibility", "Bushy Leaves Compat");
         translationBuilder.add("pack.natures_spirit.newworld_compatibility", "Newworld Compat");
         translationBuilder.add("pack.natures_spirit.modified_swamp", "Modified Swamp");
         translationBuilder.add("pack.natures_spirit.modified_badlands", "Modified Badlands");
         translationBuilder.add("pack.natures_spirit.modified_vanilla_trees", "Modified Vanilla Trees");

         for (var color : List.of(DYE_COLORS)) {


            generateArchExTranslations(color + "_kaolin", translationBuilder);
            generateArchExTranslations(color + "_chalk", translationBuilder);


            generateColoredTranslations(color + "_paper_lantern", translationBuilder);
            generateColoredTranslations(color + "_kaolin", translationBuilder);
            generateColoredTranslations(color + "_kaolin_slab", translationBuilder);
            generateColoredTranslations(color + "_kaolin_stairs", translationBuilder);
            generateColoredTranslations(color + "_chalk", translationBuilder);
            generateColoredTranslations(color + "_chalk_slab", translationBuilder);
            generateColoredTranslations(color + "_chalk_stairs", translationBuilder);
         }
         generateArchExTranslations("pink_sandstone", translationBuilder);
         generateArchExTranslations("smooth_pink_sandstone", translationBuilder);
         generateArchExTranslations("travertine", translationBuilder);
         generateArchExTranslations("travertine_bricks", translationBuilder);
         generateArchExTranslations("travertine_tiles", translationBuilder);
         generateArchExTranslations("cobbled_travertine", translationBuilder);
         generateArchExTranslations("mossy_cobbled_travertine", translationBuilder);
         generateArchExTranslations("mossy_travertine_bricks", translationBuilder);
      }
   }

   public static class NatureSpiritRecipeGenerator extends FabricRecipeProvider {
      public NatureSpiritRecipeGenerator(FabricDataOutput output) {
         super(output);
      }

      public static final BlockFamily PINK_SANDSTONE_FAMILY = register(HibiscusMiscBlocks.PINK_SANDSTONE).wall(PINK_SANDSTONE_WALL).stairs(PINK_SANDSTONE_STAIRS).slab(PINK_SANDSTONE_SLAB).chiseled(CHISELED_PINK_SANDSTONE).cut(CUT_PINK_SANDSTONE).noGenerateModels().noGenerateRecipes().build();
      public static final BlockFamily CUT_PINK_SANDSTONE_FAMILY = register(HibiscusMiscBlocks.CUT_PINK_SANDSTONE).slab(CUT_PINK_SANDSTONE_SLAB).noGenerateModels().build();
      public static final BlockFamily SMOOTH_PINK_SANDSTONE_FAMILY = register(HibiscusMiscBlocks.SMOOTH_PINK_SANDSTONE).slab(SMOOTH_PINK_SANDSTONE_SLAB).stairs(SMOOTH_PINK_SANDSTONE_STAIRS).noGenerateModels().build();


      private void generateWoodRecipes(HashMap <String, WoodSet> woods, Consumer <RecipeJsonProvider> consumer) {
         for(WoodSet woodSet : woods.values()) {
            offerPlanksRecipe(consumer, woodSet.getPlanks(), woodSet.getItemLogsTag(), 4);
            if (woodSet.hasBark()) {
               offerBarkBlockRecipe(consumer, woodSet.getWood(), woodSet.getLog());
               offerBarkBlockRecipe(consumer, woodSet.getStrippedWood(), woodSet.getStrippedLog());
            }
            if (woodSet.getWoodPreset() == WoodSet.WoodPreset.JOSHUA) {
               offer2x2CompactingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, woodSet.getBundle(), woodSet.getLog());
               offer2x2CompactingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, woodSet.getStrippedBundle(), woodSet.getStrippedLog());
               offerShapelessRecipe(consumer, woodSet.getPlanks(), woodSet.getBundle(), "planks", 4);
               offerShapelessRecipe(consumer, woodSet.getPlanks(), woodSet.getStrippedBundle(), "planks", 4);
            }
            if (woodSet.hasMosaic()) {
               offerMosaicRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, woodSet.getMosaic(), woodSet.getPlanks());
               createStairsRecipe(woodSet.getMosaicStairs(), Ingredient.ofItems(woodSet.getMosaic()));
               createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, woodSet.getMosaicSlab(), Ingredient.ofItems(woodSet.getMosaic()));
            }
            offerHangingSignRecipe(consumer, woodSet.getHangingSign(), woodSet.getStrippedLog());
            offerBoatRecipe(consumer, woodSet.getBoatItem(), woodSet.getPlanks());
            offerChestBoatRecipe(consumer, woodSet.getChestBoatItem(), woodSet.getBoatItem());
            BlockFamily family = register(woodSet.getPlanks())
                    .button(woodSet.getButton())
                    .fence(woodSet.getFence())
                    .fenceGate(woodSet.getFenceGate())
                    .pressurePlate(woodSet.getPressurePlate())
                    .sign(woodSet.getSign(), woodSet.getWallSign())
                    .slab(woodSet.getSlab())
                    .stairs(woodSet.getStairs())
                    .door(woodSet.getDoor())
                    .trapdoor(woodSet.getTrapDoor())
                    .group("wooden")
                    .unlockCriterionName("has_planks")
                    .build();
            generateFamily(consumer, family);
         }
      }
      private void generateFlowerRecipes(HashMap <String, FlowerSet> flowers, Consumer <RecipeJsonProvider> consumer) {
         for(FlowerSet flowerSet : flowers.values()) {
            offerShapelessRecipe(consumer, flowerSet.getDyeColor(), flowerSet.getFlowerBlock(), flowerSet.getDyeColor().toString(), flowerSet.getDyeNumber());
         }
      }

      private void generateStoneRecipes(HashMap <String, StoneSet> stoones, Consumer <RecipeJsonProvider> exporter) {
         for(StoneSet stoneSet : stoones.values()) {
            generateFamily(exporter, stoneSet.getBaseFamily());
            generateFamily(exporter, stoneSet.getBrickFamily());
            generateFamily(exporter, stoneSet.getPolishedFamily());
            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, stoneSet.getBricks(), 4)
                                   .input('S', stoneSet.getPolished()).pattern("SS")
                                   .pattern("SS")
                                   .criterion("has_polished_" + stoneSet.getName(), conditionsFromItem(stoneSet.getPolished()))
                                   .offerTo(exporter);
            
            if(stoneSet.hasTiles()) {
               generateFamily(exporter, stoneSet.getTileFamily());
               ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, stoneSet.getTiles(), 4)
                                      .input('S', stoneSet.getBricks())
                                      .pattern("SS").pattern("SS")
                                      .criterion("has_" + stoneSet.getName() + "_bricks", conditionsFromItem(stoneSet.getBricks()))
                                      .offerTo(exporter);
               offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getTiles(), stoneSet.getPolished());
               offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getTilesSlab(), stoneSet.getPolished(), 2);
               offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getTilesStairs(), stoneSet.getPolished());
               offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, stoneSet.getTilesWall(), stoneSet.getPolished());
               offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getTiles(), stoneSet.getBricks());
               offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getTilesSlab(), stoneSet.getBricks(), 2);
               offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getTilesStairs(), stoneSet.getBricks());
               offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, stoneSet.getTilesWall(), stoneSet.getBricks());
               offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getTilesSlab(), stoneSet.getTiles(), 2);
               offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getTilesStairs(), stoneSet.getTiles());
               offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, stoneSet.getTilesWall(), stoneSet.getTiles());
            }
            if(stoneSet.hasCobbled()) {
               generateFamily(exporter, stoneSet.getCobbledFamily());
               if(stoneSet.hasMossy()) {
                  generateFamily(exporter, stoneSet.getMossyCobbledFamily());
                  ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, stoneSet.getMossyCobbled())
                                            .input(stoneSet.getCobbled()).input(Blocks.MOSS_BLOCK)
                                            .group("mossy_cobblestone")
                                            .criterion("has_moss_block", conditionsFromItem(Blocks.MOSS_BLOCK))
                                            .offerTo(exporter, convertBetween(stoneSet.getMossyCobbled(), Blocks.MOSS_BLOCK));
                  ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, stoneSet.getMossyCobbled())
                                            .input(stoneSet.getCobbled())
                                            .input(Blocks.VINE)
                                            .group("mossy_cobblestone")
                                            .criterion("has_vine", conditionsFromItem(Blocks.VINE))
                                            .offerTo(exporter, convertBetween(stoneSet.getMossyCobbled(), Blocks.VINE));
                  offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getMossyCobbledSlab(), stoneSet.getMossyCobbled(), 2);
                  offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getMossyCobbledStairs(), stoneSet.getMossyCobbled());
                  offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, stoneSet.getMossyCobbledWall(), stoneSet.getMossyCobbled());
               }
               CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(stoneSet.getCobbled()), RecipeCategory.BUILDING_BLOCKS, stoneSet.getBase(), 0.1F, 200)
                                       .criterion("has_cobbled_" + stoneSet.getName(), conditionsFromItem(stoneSet.getCobbled()))
                                       .offerTo(exporter);
               offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getCobbledSlab(), stoneSet.getCobbled(), 2);
               offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getCobbledStairs(), stoneSet.getCobbled());
               offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, stoneSet.getCobbledWall(), stoneSet.getCobbled());
               offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getChiseled(), stoneSet.getCobbled());
               offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getPolished(), stoneSet.getCobbled());
               offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getPolishedSlab(), stoneSet.getCobbled(), 2);
               offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getPolishedStairs(), stoneSet.getCobbled());
               offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, stoneSet.getPolishedWall(), stoneSet.getCobbled());
               offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getBricks(), stoneSet.getCobbled());
               offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getBricksSlab(), stoneSet.getCobbled(), 2);
               offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getBricksStairs(), stoneSet.getCobbled());
               offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, stoneSet.getBricksWall(), stoneSet.getCobbled());
               if (stoneSet.hasTiles()) {
                  offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getTiles(), stoneSet.getCobbled());
                  offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getTilesSlab(), stoneSet.getCobbled(), 2);
                  offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getTilesStairs(), stoneSet.getCobbled());
                  offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, stoneSet.getTilesWall(), stoneSet.getCobbled());
               }
            } else {
               offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getCobbledSlab(), stoneSet.getBase(), 2);
               offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getCobbledStairs(), stoneSet.getBase());
               offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, stoneSet.getCobbledWall(), stoneSet.getBase());
               offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getChiseled(), stoneSet.getBase());
               offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getPolished(), stoneSet.getBase());
               offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getPolishedSlab(), stoneSet.getBase(), 2);
               offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getPolishedStairs(), stoneSet.getBase());
               offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, stoneSet.getPolishedWall(), stoneSet.getBase());
               offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getBricks(), stoneSet.getBase());
               offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getBricksSlab(), stoneSet.getBase(), 2);
               offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getBricksStairs(), stoneSet.getBase());
               offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, stoneSet.getBricksWall(), stoneSet.getBase());
               if (stoneSet.hasTiles()) {
                  offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getTiles(), stoneSet.getBase());
                  offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getTilesSlab(), stoneSet.getBase(), 2);
                  offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getTilesStairs(), stoneSet.getBase());
                  offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, stoneSet.getTilesWall(), stoneSet.getBase());
               }
            }
            if(stoneSet.hasMossy()) {
               generateFamily(exporter, stoneSet.getMossyBrickFamily());
               ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, stoneSet.getMossyBricks())
                                         .input(stoneSet.getBricks())
                                         .input(Blocks.VINE)
                                         .group("mossy_stone_bricks")
                                         .criterion("has_vine", conditionsFromItem(Blocks.VINE))
                                         .offerTo(exporter, convertBetween(stoneSet.getMossyBricks(), Blocks.VINE));
               ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, stoneSet.getMossyBricks())
                                         .input(stoneSet.getBricks())
                                         .input(Blocks.MOSS_BLOCK)
                                         .group("mossy_stone_bricks")
                                         .criterion("has_moss_block", conditionsFromItem(Blocks.MOSS_BLOCK))
                                         .offerTo(exporter, convertBetween(stoneSet.getMossyBricks(), Blocks.MOSS_BLOCK));
               offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getMossyBricksSlab(), stoneSet.getMossyBricks(), 2);
               offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getMossyBricksStairs(), stoneSet.getMossyBricks());
               offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, stoneSet.getMossyBricksWall(), stoneSet.getMossyBricks());
            }


            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getPolishedSlab(), stoneSet.getPolished(), 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getPolishedStairs(), stoneSet.getPolished());
            offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, stoneSet.getPolishedWall(), stoneSet.getPolished());
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getBricks(), stoneSet.getPolished());
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getBricksSlab(), stoneSet.getPolished(), 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getBricksStairs(), stoneSet.getPolished());
            offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, stoneSet.getBricksWall(), stoneSet.getPolished());
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getBricksSlab(), stoneSet.getBricks(), 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneSet.getBricksStairs(), stoneSet.getBricks());
            offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, stoneSet.getBricksWall(), stoneSet.getBricks());
         }
      }

      @Override public void generate(Consumer <RecipeJsonProvider> exporter) {

         createChiseledBlockRecipe(RecipeCategory.BUILDING_BLOCKS, CHISELED_PINK_SANDSTONE, Ingredient.ofItems(PINK_SANDSTONE_SLAB))
                 .criterion("has_pink_sandstone", conditionsFromItem(HibiscusMiscBlocks.PINK_SANDSTONE))
                 .criterion("has_chiseled_pink_sandstone", conditionsFromItem(CHISELED_PINK_SANDSTONE))
                 .criterion("has_cut_pink_sandstone", conditionsFromItem(HibiscusMiscBlocks.CUT_PINK_SANDSTONE))
                 .offerTo(exporter);
         ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HibiscusMiscBlocks.PINK_SANDSTONE)
                                .input('#', PINK_SAND).pattern("##")
                                .pattern("##").criterion("has_sand", conditionsFromItem(HibiscusMiscBlocks.PINK_SAND))
                                .offerTo(exporter);
         createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, HibiscusMiscBlocks.PINK_SANDSTONE_SLAB, Ingredient.ofItems(
                 HibiscusMiscBlocks.PINK_SANDSTONE,
                 HibiscusMiscBlocks.CHISELED_PINK_SANDSTONE
         ))
                 .criterion("has_pink_sandstone", conditionsFromItem(HibiscusMiscBlocks.PINK_SANDSTONE)).criterion("has_chiseled_pink_sandstone", conditionsFromItem(
                         HibiscusMiscBlocks.CHISELED_PINK_SANDSTONE))
                 .offerTo(exporter);
         createStairsRecipe(HibiscusMiscBlocks.PINK_SANDSTONE_STAIRS, Ingredient.ofItems(
                 HibiscusMiscBlocks.PINK_SANDSTONE,
                 HibiscusMiscBlocks.CHISELED_PINK_SANDSTONE,
                 HibiscusMiscBlocks.CUT_PINK_SANDSTONE
         ))
                 .criterion("has_pink_sandstone", conditionsFromItem(HibiscusMiscBlocks.PINK_SANDSTONE))
                 .criterion("has_chiseled_pink_sandstone", conditionsFromItem(HibiscusMiscBlocks.CHISELED_PINK_SANDSTONE))
                 .criterion("has_cut_pink_sandstone", conditionsFromItem(HibiscusMiscBlocks.CUT_PINK_SANDSTONE))
                 .offerTo(exporter);
         offerCutCopperRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, HibiscusMiscBlocks.CUT_PINK_SANDSTONE, HibiscusMiscBlocks.PINK_SANDSTONE);
         offerWallRecipe(exporter, RecipeCategory.DECORATIONS, HibiscusMiscBlocks.PINK_SANDSTONE_WALL, HibiscusMiscBlocks.PINK_SANDSTONE);
         CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(HibiscusMiscBlocks.PINK_SANDSTONE), RecipeCategory.BUILDING_BLOCKS, HibiscusMiscBlocks.SMOOTH_PINK_SANDSTONE.asItem(), 0.1F, 200)
                                 .criterion("has_pink_sandstone", conditionsFromItem(HibiscusMiscBlocks.PINK_SANDSTONE))
                                 .offerTo(exporter);
         offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, HibiscusMiscBlocks.CUT_PINK_SANDSTONE, HibiscusMiscBlocks.PINK_SANDSTONE);
         offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, HibiscusMiscBlocks.PINK_SANDSTONE_SLAB, HibiscusMiscBlocks.PINK_SANDSTONE, 2);
         offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, HibiscusMiscBlocks.CUT_PINK_SANDSTONE_SLAB, HibiscusMiscBlocks.PINK_SANDSTONE, 2);
         offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, HibiscusMiscBlocks.CUT_PINK_SANDSTONE_SLAB, HibiscusMiscBlocks.CUT_PINK_SANDSTONE, 2);
         offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, HibiscusMiscBlocks.PINK_SANDSTONE_STAIRS, HibiscusMiscBlocks.PINK_SANDSTONE);
         offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, HibiscusMiscBlocks.PINK_SANDSTONE_WALL, HibiscusMiscBlocks.PINK_SANDSTONE);
         offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, HibiscusMiscBlocks.CHISELED_PINK_SANDSTONE, HibiscusMiscBlocks.PINK_SANDSTONE);
         offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, HibiscusMiscBlocks.SMOOTH_PINK_SANDSTONE_SLAB, HibiscusMiscBlocks.SMOOTH_PINK_SANDSTONE, 2);
         offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, HibiscusMiscBlocks.SMOOTH_PINK_SANDSTONE_STAIRS, HibiscusMiscBlocks.SMOOTH_PINK_SANDSTONE);




         offerCarpetRecipe(exporter, HibiscusWoods.COCONUT_THATCH_CARPET, HibiscusWoods.COCONUT_THATCH);
         offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, HibiscusWoods.COCONUT_THATCH_SLAB, HibiscusWoods.COCONUT_THATCH);
         createStairsRecipe(HibiscusWoods.COCONUT_THATCH_STAIRS, Ingredient.ofItems(HibiscusWoods.COCONUT_THATCH));
         offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, HibiscusWoods.COCONUT_THATCH, HibiscusWoods.COCONUT.getLeaves());

         offerCarpetRecipe(exporter, RED_MOSS_CARPET, RED_MOSS_BLOCK);


         generateFlowerRecipes(HibiscusRegistryHelper.FlowerHashMap, exporter);
         generateWoodRecipes(HibiscusRegistryHelper.WoodHashMap, exporter);
         generateStoneRecipes(HibiscusRegistryHelper.StoneHashMap, exporter);
         offerShapelessRecipe(exporter, Items.BROWN_DYE, HibiscusMiscBlocks.CATTAIL, "brown_dye", 2);
         offerShapelessRecipe(exporter, Items.PINK_DYE, LOTUS_FLOWER, "pink_dye", 1);
         offerCompactingRecipe(exporter, RecipeCategory.FOOD, HibiscusMiscBlocks.DESERT_TURNIP_BLOCK, HibiscusMiscBlocks.DESERT_TURNIP, "desert_turnip");
         offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, HibiscusColoredBlocks.WHITE_CHALK, HibiscusMiscBlocks.CHALK_POWDER);

         offerShapelessRecipe(exporter, HibiscusWoods.COCONUT_HALF, HibiscusWoods.COCONUT_BLOCK, "coconut_half", 2);
         offerShapelessRecipe(exporter, HibiscusWoods.YOUNG_COCONUT_HALF, HibiscusWoods.YOUNG_COCONUT_BLOCK, "coconut_half", 2);
         offerShapelessRecipe(exporter, Items.BOWL, HibiscusWoods.COCONUT_SHELL, "bowl", 1);
         offerShapelessRecipe(exporter, Items.BOWL, HibiscusWoods.YOUNG_COCONUT_SHELL, "bowl", 1);
         CookingRecipeJsonBuilder.createSmelting(Ingredient.fromTag(HibiscusTags.Items.COCONUT_ITEMS), RecipeCategory.MISC, Items.CHARCOAL, 0.15F, 125).criterion("has_coconut", conditionsFromTag(HibiscusTags.Items.COCONUT_ITEMS)).offerTo(exporter);


         generateFamily(exporter, CUT_PINK_SANDSTONE_FAMILY);
         generateFamily(exporter, SMOOTH_PINK_SANDSTONE_FAMILY);
         
      }
   }

   public static class NatureSpiritItemTagGenerator extends FabricTagProvider.ItemTagProvider {

      public NatureSpiritItemTagGenerator(FabricDataOutput output, CompletableFuture <RegistryWrapper.WrapperLookup> completableFuture, @Nullable BlockTagProvider blockTagProvider) {
         super(output, completableFuture, blockTagProvider);
      }


      @Override protected void configure(RegistryWrapper.WrapperLookup arg) {

         for(WoodSet woodSet : HibiscusRegistryHelper.WoodHashMap.values()) {
            this.copy(woodSet.getBlockLogsTag(), woodSet.getItemLogsTag());
            getOrCreateTagBuilder(ItemTags.BOATS).add(woodSet.getBoatItem());
            getOrCreateTagBuilder(ItemTags.CHEST_BOATS).add(woodSet.getChestBoatItem());
         }
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
         this.copy(BlockTags.SAND, ItemTags.SAND);
         this.copy(BlockTags.SLABS, ItemTags.SLABS);
         this.copy(BlockTags.STAIRS, ItemTags.STAIRS);
         this.copy(BlockTags.WALLS, ItemTags.WALLS);
         this.getOrCreateTagBuilder(ItemTags.SMELTS_TO_GLASS).add(PINK_SAND.asItem());
         this.getOrCreateTagBuilder(HibiscusTags.Items.COCONUT_ITEMS).add(HibiscusWoods.COCONUT_BLOCK.asItem(), HibiscusWoods.YOUNG_COCONUT_BLOCK.asItem(), HibiscusWoods.COCONUT_HALF, HibiscusWoods.YOUNG_COCONUT_HALF, HibiscusWoods.COCONUT_SHELL, HibiscusWoods.YOUNG_COCONUT_SHELL);
      }
   }

   public static class NatureSpiritBlockTagGenerator extends FabricTagProvider.BlockTagProvider {

      public NatureSpiritBlockTagGenerator(FabricDataOutput output, CompletableFuture <RegistryWrapper.WrapperLookup> registriesFuture) {
         super(output, registriesFuture);
      }

      private void addWoodTags(HashMap <String, WoodSet> woods) {
         for(WoodSet woodSet : woods.values()) {
            
            getOrCreateTagBuilder(BlockTags.PLANKS).add(new Block[]{woodSet.getPlanks()});
            getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS).add(new Block[]{woodSet.getButton()});
            getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add(new Block[]{woodSet.getDoor()});
            getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS).add(new Block[]{woodSet.getStairs()});
            getOrCreateTagBuilder(BlockTags.WOODEN_SLABS).add(new Block[]{woodSet.getSlab()});
            getOrCreateTagBuilder(BlockTags.WOODEN_FENCES).add(new Block[]{woodSet.getFence()});
            getOrCreateTagBuilder(woodSet.getBlockLogsTag()).add(woodSet.getStrippedLog(), woodSet.getLog());
            if (woodSet.hasBark())
               getOrCreateTagBuilder(woodSet.getBlockLogsTag()).add(woodSet.getStrippedWood(), woodSet.getWood());
            if(woodSet.hasMosaic()) {
               getOrCreateTagBuilder(BlockTags.SLABS).add(woodSet.getMosaicSlab());
               getOrCreateTagBuilder(BlockTags.STAIRS).add(woodSet.getMosaicStairs());
               getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(woodSet.getMosaic(), woodSet.getMosaicSlab(), woodSet.getMosaicStairs());
            }
            getOrCreateTagBuilder(BlockTags.OVERWORLD_NATURAL_LOGS).add(new Block[]{woodSet.getLog()});
            getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN).addTag(woodSet.getBlockLogsTag());
            getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add(new Block[]{woodSet.getTrapDoor()});
            getOrCreateTagBuilder(BlockTags.STANDING_SIGNS).add(new Block[]{woodSet.getSign()});
            getOrCreateTagBuilder(BlockTags.WALL_SIGNS).add(new Block[]{woodSet.getWallSign()});
            getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(new Block[]{woodSet.getPressurePlate()});
            getOrCreateTagBuilder(BlockTags.FENCE_GATES).add(new Block[]{woodSet.getFenceGate()});
            getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS).add(woodSet.getHangingSign());
            getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS).add(woodSet.getHangingWallSign());

         }
      }

      private void addStoneTags(HashMap <String, StoneSet> stones) {
         for(StoneSet stoneSet : stones.values()) {

            getOrCreateTagBuilder(BlockTags.STONE_ORE_REPLACEABLES).add(new Block[]{stoneSet.getBase()});
            getOrCreateTagBuilder(BlockTags.BASE_STONE_OVERWORLD).add(new Block[]{stoneSet.getBase()});

            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(stoneSet.getRegisteredBlocksList().toArray(new Block[]{}));
            if (stoneSet.hasTiles()) {
               getOrCreateTagBuilder(BlockTags.SLABS).add(stoneSet.getTilesSlab());
               getOrCreateTagBuilder(BlockTags.STAIRS).add(stoneSet.getTilesStairs());
               getOrCreateTagBuilder(BlockTags.WALLS).add(stoneSet.getTilesWall());
            }
            if(stoneSet.hasCobbled()) {
               getOrCreateTagBuilder(BlockTags.SLABS).add(stoneSet.getCobbledSlab());
               getOrCreateTagBuilder(BlockTags.STAIRS).add(stoneSet.getCobbledStairs());
               getOrCreateTagBuilder(BlockTags.WALLS).add(stoneSet.getCobbledWall());
               if(stoneSet.hasMossy()) {
                  getOrCreateTagBuilder(BlockTags.SLABS).add(stoneSet.getMossyCobbledSlab());
                  getOrCreateTagBuilder(BlockTags.STAIRS).add(stoneSet.getMossyCobbledStairs());
                  getOrCreateTagBuilder(BlockTags.WALLS).add(stoneSet.getMossyCobbledWall());
               }
            }
            if(stoneSet.hasMossy()) {
               getOrCreateTagBuilder(BlockTags.SLABS).add(stoneSet.getMossyBricksSlab());
               getOrCreateTagBuilder(BlockTags.STAIRS).add(stoneSet.getMossyBricksStairs());
               getOrCreateTagBuilder(BlockTags.WALLS).add(stoneSet.getMossyBricksWall());
            }
            getOrCreateTagBuilder(BlockTags.SLABS).add(stoneSet.getPolishedSlab());
            getOrCreateTagBuilder(BlockTags.STAIRS).add(stoneSet.getPolishedStairs());
            getOrCreateTagBuilder(BlockTags.WALLS).add(stoneSet.getPolishedWall());
            getOrCreateTagBuilder(BlockTags.SLABS).add(stoneSet.getBricksSlab());
            getOrCreateTagBuilder(BlockTags.STAIRS).add(stoneSet.getBricksStairs());
            getOrCreateTagBuilder(BlockTags.WALLS).add(stoneSet.getBricksWall());
            getOrCreateTagBuilder(BlockTags.SLABS).add(stoneSet.getBaseSlab());
            getOrCreateTagBuilder(BlockTags.STAIRS).add(stoneSet.getBaseStairs());

         }
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
      @Override protected void configure(RegistryWrapper.WrapperLookup arg) {
         addWoodTags(HibiscusRegistryHelper.WoodHashMap);
         addStoneTags(HibiscusRegistryHelper.StoneHashMap);
         addTreeTags(HibiscusRegistryHelper.SaplingHashMap, HibiscusRegistryHelper.LeavesHashMap);
         for (FlowerSet flowerSet : HibiscusRegistryHelper.FlowerHashMap.values()) {
            if (flowerSet.isTall()) {
               getOrCreateTagBuilder(BlockTags.TALL_FLOWERS).add(flowerSet.getFlowerBlock());
            } else {
               getOrCreateTagBuilder(BlockTags.FLOWER_POTS).add(flowerSet.getPottedFlowerBlock());
               getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS).add(flowerSet.getFlowerBlock());
            }
         }
         getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add(PAPER_DOOR, FRAMED_PAPER_DOOR, BLOOMING_PAPER_DOOR);
         getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add(PAPER_TRAPDOOR, FRAMED_PAPER_TRAPDOOR, BLOOMING_PAPER_TRAPDOOR);
         getOrCreateTagBuilder(BlockTags.CLIMBABLE).add(
                 HibiscusWoods.WISTERIA.getBlueWisteriaVines(),
                 HibiscusWoods.WISTERIA.getBlueWisteriaVinesPlant(),
                 HibiscusWoods.WISTERIA.getWhiteWisteriaVines(),
                 HibiscusWoods.WISTERIA.getWhiteWisteriaVinesPlant(),
                 HibiscusWoods.WISTERIA.getPinkWisteriaVines(),
                 HibiscusWoods.WISTERIA.getPinkWisteriaVinesPlant(),
                 HibiscusWoods.WISTERIA.getPurpleWisteriaVines(),
                 HibiscusWoods.WISTERIA.getPurpleWisteriaVinesPlant(),
                 HibiscusWoods.WILLOW.getWillowVinesPlant(),
                 HibiscusWoods.WILLOW.getWillowVines()
         );
         getOrCreateTagBuilder(BlockTags.BEE_GROWABLES).add(
                 HibiscusWoods.WISTERIA.getBlueWisteriaVines(),
                 HibiscusWoods.WISTERIA.getBlueWisteriaVinesPlant(),
                 HibiscusWoods.WISTERIA.getWhiteWisteriaVines(),
                 HibiscusWoods.WISTERIA.getWhiteWisteriaVinesPlant(),
                 HibiscusWoods.WISTERIA.getPinkWisteriaVines(),
                 HibiscusWoods.WISTERIA.getPinkWisteriaVinesPlant(),
                 HibiscusWoods.WISTERIA.getPurpleWisteriaVines(),
                 HibiscusWoods.WISTERIA.getPurpleWisteriaVinesPlant(),
                 LOTUS_FLOWER
         );
         getOrCreateTagBuilder(BlockTags.CROPS).add(HibiscusMiscBlocks.DESERT_TURNIP_STEM);
         getOrCreateTagBuilder(BlockTags.MAINTAINS_FARMLAND).add(HibiscusMiscBlocks.DESERT_TURNIP_STEM);
         getOrCreateTagBuilder(BlockTags.HOE_MINEABLE).add(
                 HibiscusMiscBlocks.SCORCHED_GRASS,
                 HibiscusMiscBlocks.TALL_SCORCHED_GRASS,
                 BEACH_GRASS,
                 TALL_BEACH_GRASS,
                 SEDGE_GRASS,
                 TALL_SEDGE_GRASS,
                 LARGE_FLAXEN_FERN,
                 FLAXEN_FERN,
                 FRIGID_GRASS,
                 TALL_FRIGID_GRASS,
                 RED_BEARBERRIES,
                 GREEN_BEARBERRIES,
                 PURPLE_BEARBERRIES,
                 RED_BITTER_SPROUTS,
                 PURPLE_BITTER_SPROUTS,
                 GREEN_BITTER_SPROUTS,
                 HibiscusWoods.COCONUT_THATCH,
                 HibiscusWoods.COCONUT_THATCH_STAIRS,
                 HibiscusWoods.COCONUT_THATCH_CARPET,
                 HibiscusWoods.COCONUT_THATCH_SLAB,
                 RED_MOSS_BLOCK,
                 RED_MOSS_CARPET
         );
         getOrCreateTagBuilder(BlockTags.SWORD_EFFICIENT).add(
                 HibiscusMiscBlocks.SCORCHED_GRASS,
                 HibiscusMiscBlocks.TALL_SCORCHED_GRASS,
                 BEACH_GRASS,
                 TALL_BEACH_GRASS,
                 SEDGE_GRASS,
                 TALL_SEDGE_GRASS,
                 LARGE_FLAXEN_FERN,
                 FLAXEN_FERN,
                 SHIITAKE_MUSHROOM,
                 FRIGID_GRASS,
                 TALL_FRIGID_GRASS,
                 RED_BEARBERRIES,
                 GREEN_BEARBERRIES,
                 PURPLE_BEARBERRIES,
                 RED_BITTER_SPROUTS,
                 PURPLE_BITTER_SPROUTS,
                 GREEN_BITTER_SPROUTS
         );
         getOrCreateTagBuilder(BlockTags.REPLACEABLE_BY_TREES).add(
                 HibiscusMiscBlocks.SCORCHED_GRASS,
                 HibiscusMiscBlocks.TALL_SCORCHED_GRASS,
                 BEACH_GRASS,
                 TALL_BEACH_GRASS,
                 SEDGE_GRASS,
                 TALL_SEDGE_GRASS,
                 LARGE_FLAXEN_FERN,
                 FLAXEN_FERN,
                 FRIGID_GRASS,
                 TALL_FRIGID_GRASS
         );
         getOrCreateTagBuilder(BlockTags.SAND).add(PINK_SAND, SANDY_SOIL);
         getOrCreateTagBuilder(BlockTags.SMELTS_TO_GLASS).add(PINK_SAND);
         getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE).add(PINK_SAND, SANDY_SOIL);
         getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                 .forceAddTag(HibiscusTags.Blocks.KAOLIN)
                 .forceAddTag(HibiscusTags.Blocks.KAOLIN_STAIRS)
                 .forceAddTag(HibiscusTags.Blocks.KAOLIN_SLABS)
                 .forceAddTag(HibiscusTags.Blocks.CHALK)
                 .forceAddTag(HibiscusTags.Blocks.CHALK_STAIRS)
                 .forceAddTag(HibiscusTags.Blocks.CHALK_SLABS)
                 .add(PINK_SANDSTONE, SMOOTH_PINK_SANDSTONE, CUT_PINK_SANDSTONE, PINK_SANDSTONE_STAIRS, SMOOTH_PINK_SANDSTONE_STAIRS, PINK_SANDSTONE_SLAB, SMOOTH_PINK_SANDSTONE_SLAB, CUT_PINK_SANDSTONE_SLAB, PINK_SANDSTONE_WALL, CHISELED_PINK_SANDSTONE);
         getOrCreateTagBuilder(BlockTags.STAIRS).add(PINK_SANDSTONE_STAIRS, SMOOTH_PINK_SANDSTONE_STAIRS);
         getOrCreateTagBuilder(BlockTags.SLABS).add(PINK_SANDSTONE_SLAB, SMOOTH_PINK_SANDSTONE_SLAB, CUT_PINK_SANDSTONE_SLAB);
         getOrCreateTagBuilder(BlockTags.WALLS).add(PINK_SANDSTONE_WALL);
         getOrCreateTagBuilder(BlockTags.CAULDRONS).add(CHEESE_CAULDRON, MILK_CAULDRON);
         getOrCreateTagBuilder(BlockTags.FLOWER_POTS).add(POTTED_FLAXEN_FERN, POTTED_FRIGID_GRASS, POTTED_SHIITAKE_MUSHROOM, POTTED_BEACH_GRASS, POTTED_SEDGE_GRASS, POTTED_SCORCHED_GRASS);
         getOrCreateTagBuilder(BlockTags.ENDERMAN_HOLDABLE).add(SHIITAKE_MUSHROOM);
         getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(SHIITAKE_MUSHROOM, SHIITAKE_MUSHROOM_BLOCK, DESERT_TURNIP_BLOCK, DESERT_TURNIP_ROOT_BLOCK, DESERT_TURNIP_STEM, PAPER_BLOCK, PAPER_PANEL, PAPER_DOOR, PAPER_SIGN, PAPER_WALL_SIGN, PAPER_HANGING_SIGN, PAPER_WALL_HANGING_SIGN, FRAMED_PAPER_BLOCK, FRAMED_PAPER_PANEL, FRAMED_PAPER_DOOR, FRAMED_PAPER_TRAPDOOR, BLOOMING_PAPER_BLOCK, BLOOMING_PAPER_DOOR, BLOOMING_PAPER_TRAPDOOR, BLOOMING_PAPER_PANEL);
         getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS).add(PAPER_HANGING_SIGN);
         getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS).add(PAPER_WALL_HANGING_SIGN);
         getOrCreateTagBuilder(BlockTags.STANDING_SIGNS).add(new Block[]{PAPER_SIGN});
         getOrCreateTagBuilder(BlockTags.WALL_SIGNS).add(new Block[]{PAPER_WALL_SIGN});
      }
   }
}

