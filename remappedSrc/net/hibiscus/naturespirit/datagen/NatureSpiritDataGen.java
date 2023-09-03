package net.hibiscus.naturespirit.datagen;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.blocks.DesertPlantBlock;
import net.hibiscus.naturespirit.registration.HibiscusBlocksAndItems;
import net.hibiscus.naturespirit.entity.HibiscusBoatEntity;
import net.hibiscus.naturespirit.registration.HibiscusItemGroups;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusColoredBlocks;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusWoods;
import net.hibiscus.naturespirit.util.HibiscusRegistryHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.client.*;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.BlockModelGenerators.TintState;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.Condition;
import net.minecraft.data.models.blockstates.MultiPartGenerator;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.data.models.model.TexturedModel;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.loot.condition.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static net.hibiscus.naturespirit.registration.HibiscusBlocksAndItems.*;
import static net.minecraft.data.models.BlockModelGenerators.*;
import static net.minecraft.data.BlockFamilies.familyBuilder;

public class NatureSpiritDataGen implements DataGeneratorEntrypoint {
   public static TagKey <Item> joshuaItemLogtag = TagKey.create(
           Registries.ITEM,
           new ResourceLocation(NatureSpirit.MOD_ID, "joshua_logs")
   );
   public static TagKey <Block> joshuaBlockLogtag = TagKey.create(
           Registries.BLOCK,
           new ResourceLocation(NatureSpirit.MOD_ID, "joshua_logs")
   );
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

   @Override public void buildRegistry(RegistrySetBuilder registryBuilder) {
      registryBuilder.add(Registries.CONFIGURED_FEATURE, HibiscusConfiguredFeatures::bootstrap);
      registryBuilder.add(Registries.PLACED_FEATURE, HibiscusPlacedFeatures::bootstrap);
      registryBuilder.add(Registries.BIOME, HibiscusBiomes::bootstrap);
      System.out.println("Built Registry");
   }

   private void registerWoodTypes() {
      for(String i : HibiscusRegistryHelper.WoodHashMap.keySet()) {
         blockLogTags.put(i, TagKey.create(Registries.BLOCK, new ResourceLocation(NatureSpirit.MOD_ID, i + "_logs")));
         itemLogTags.put(i, TagKey.create(Registries.ITEM, new ResourceLocation(NatureSpirit.MOD_ID, i + "_logs")));
      }
   }


   @Override public String getEffectiveModId() {
      return NatureSpirit.MOD_ID;
   }


   private static class NatureSpiritBlockLootTableProvider extends FabricBlockLootTableProvider {
      private static final LootItemCondition.Builder WITH_SILK_TOUCH_OR_SHEARS = HAS_SHEARS.or(HAS_SILK_TOUCH);
      private static final LootItemCondition.Builder WITHOUT_SILK_TOUCH_NOR_SHEARS = WITH_SILK_TOUCH_OR_SHEARS.invert();
      private final HashMap <ResourceLocation, LootTable.Builder> map = new HashMap();
      private final float[] SAPLING_DROP_CHANCE_2 = new float[]{0.4F, 0.4333333333F, 0.5025F, 0.5888F};

      protected NatureSpiritBlockLootTableProvider(FabricDataOutput dataOutput) {
         super(dataOutput);
      }

      public static LootTable.Builder createShearsOnlyDrop(ItemLike drop) {
         LootTable.lootTable().withPool(LootPool.lootPool()
                 .setRolls(ConstantValue.exactly(1.0F))
                 .when(HAS_SHEARS)
                 .add(LootItem.lootTableItem(drop)));
         return null;
      }

      private void addWoodTable(HashMap <String, Block[]> woods) {
         for(String i : woods.keySet()) {
            Block[] woodType = woods.get(i);
            dropSelf(woodType[0]);
            dropSelf(woodType[1]);
            dropSelf(woodType[2]);
            dropSelf(woodType[3]);
            dropSelf(woodType[4]);
            dropSelf(woodType[12]);
            this.add(woodType[7], this::createDoorTable);
            dropSelf(woodType[5]);
            this.createSlabItemTable(woodType[6]);
            dropSelf(woodType[9]);
            dropSelf(woodType[2]);
            dropSelf(woodType[8]);
            dropSelf(woodType[13]);
            dropSelf(woodType[15]);
            dropSelf(woodType[11]);
            dropSelf(woodType[10]);
         }
      }

      private void addJoshuaWoodTable() {
         dropSelf(HibiscusWoods.JOSHUA[0]);
         dropSelf(HibiscusWoods.JOSHUA[1]);
         dropSelf(HibiscusWoods.JOSHUA[2]);
         dropSelf(HibiscusWoods.JOSHUA[10]);
         this.add(HibiscusWoods.JOSHUA[5], this::createDoorTable);
         dropSelf(HibiscusWoods.JOSHUA[3]);
         this.createSlabItemTable(HibiscusWoods.JOSHUA[4]);
         dropSelf(HibiscusWoods.JOSHUA[7]);
         dropSelf(HibiscusWoods.JOSHUA[6]);
         dropSelf(HibiscusWoods.JOSHUA[11]);
         dropSelf(HibiscusWoods.JOSHUA[13]);
         dropSelf(HibiscusWoods.JOSHUA[9]);
         dropSelf(HibiscusWoods.JOSHUA[8]);
      }

      public net.minecraft.world.level.storage.loot.LootTable.Builder blackOlivesDrop(Block leaves, Block drop, float... chance) {
         return this.createLeavesDrops(leaves, drop, chance).withPool(LootPool.lootPool()
                 .setRolls(ConstantValue.exactly(1.0F))
                 .when(HAS_NO_SHEARS_OR_SILK_TOUCH)
                 .add(((net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer.Builder <?>) this.applyExplosionCondition(leaves,
                         LootItem.lootTableItem(HibiscusBlocksAndItems.BLACK_OLIVES)
                 )).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE,
                         0.01F,
                         0.0111111114F,
                         0.0125F,
                         0.016666668F,
                         0.05F
                 ))));
      }

      public net.minecraft.world.level.storage.loot.LootTable.Builder greenOlivesDrop(Block leaves, Block drop, float... chance) {
         return this.blackOlivesDrop(leaves, drop, chance).withPool(LootPool.lootPool()
                 .setRolls(ConstantValue.exactly(1.0F))
                 .when(HAS_NO_SHEARS_OR_SILK_TOUCH)
                 .add(((net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer.Builder <?>) this.applyExplosionCondition(leaves,
                         LootItem.lootTableItem(HibiscusBlocksAndItems.GREEN_OLIVES)
                 )).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE,
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
            dropSelf(saplingType[0]);
            dropPottedContents(saplingType[1]);
            if(i.equals("olive")) {
               this.add(leavesType, (block) -> this.greenOlivesDrop(block, saplingType[0], NORMAL_LEAVES_SAPLING_CHANCES));
            }
            else if(i.equals("joshua")) {
               this.add(leavesType, (block) -> this.createLeavesDrops(block, saplingType[0], SAPLING_DROP_CHANCE_2));
            }
            else {
               this.add(leavesType, (block) -> this.createLeavesDrops(block, saplingType[0], NORMAL_LEAVES_SAPLING_CHANCES));
            }
         }
      }

      private void addVinesTable(Block vine, Block vinePlant) {
         this.addNetherVinesDropTable(vine, vinePlant);
      }

      public void tallPlantDrop(Block tallGrass, Block grass) {
         net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer.Builder <?> builder = LootItem.lootTableItem(grass).apply(
                 SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)));
         LootTable.lootTable().withPool(LootPool.lootPool()
                 .add(builder)
                 .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(tallGrass)
                         .setProperties(net.minecraft.advancements.critereon.StatePropertiesPredicate.Builder.properties()
                                 .hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)))
                 .when(LocationCheck.checkLocation(net.minecraft.advancements.critereon.LocationPredicate.Builder.location()
                         .setBlock(net.minecraft.advancements.critereon.BlockPredicate.Builder.block()
                                 .of(new Block[]{tallGrass})
                                 .setProperties(net.minecraft.advancements.critereon.StatePropertiesPredicate.Builder.properties()
                                         .hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER)
                                         .build())
                                 .build()), new BlockPos(0, 1, 0)))).withPool(LootPool.lootPool()
                 .add(builder)
                 .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(tallGrass)
                         .setProperties(net.minecraft.advancements.critereon.StatePropertiesPredicate.Builder.properties()
                                 .hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER)))
                 .when(LocationCheck.checkLocation(net.minecraft.advancements.critereon.LocationPredicate.Builder.location()
                         .setBlock(net.minecraft.advancements.critereon.BlockPredicate.Builder.block()
                                 .of(new Block[]{tallGrass})
                                 .setProperties(net.minecraft.advancements.critereon.StatePropertiesPredicate.Builder.properties()
                                         .hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)
                                         .build())
                                 .build()), new BlockPos(0, -1, 0))));
      }

      @Override public void generate() {
         addWoodTable(HibiscusRegistryHelper.WoodHashMap);
         addJoshuaWoodTable();
         addTreeTable(HibiscusRegistryHelper.SaplingHashMap, HibiscusRegistryHelper.LeavesHashMap);

         this.add(CALCITE_CLUSTER, (block) -> {
            return createSilkTouchDispatchTable(
                    block,
                    LootItem.lootTableItem(CALCITE_SHARD)
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F)))
                            .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))
                            .when(MatchTool.toolMatches(net.minecraft.advancements.critereon.ItemPredicate.Builder.item()
                                    .of(ItemTags.CLUSTER_MAX_HARVESTABLES))).otherwise(this.applyExplosionDecay(
                            block,
                            LootItem.lootTableItem(CALCITE_SHARD)
                                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))
                    ))
            );
         });
         this.dropWhenSilkTouch(SMALL_CALCITE_BUD);
         this.dropWhenSilkTouch(LARGE_CALCITE_BUD);

         addVinesTable(HibiscusWoods.WHITE_WISTERIA_VINES, HibiscusWoods.WHITE_WISTERIA_VINES_PLANT);
         addVinesTable(HibiscusWoods.BLUE_WISTERIA_VINES, HibiscusWoods.BLUE_WISTERIA_VINES_PLANT);
         addVinesTable(HibiscusWoods.PURPLE_WISTERIA_VINES, HibiscusWoods.PURPLE_WISTERIA_VINES_PLANT);
         addVinesTable(HibiscusWoods.PINK_WISTERIA_VINES, HibiscusWoods.PINK_WISTERIA_VINES_PLANT);
         addVinesTable(HibiscusWoods.WILLOW_VINES, HibiscusWoods.WILLOW_VINES_PLANT);

         addVinesTable(LOTUS_STEM, LOTUS_STEM);
         this.dropOther(LOTUS_FLOWER, LOTUS_FLOWER_ITEM);

         this.dropSelf(SHIITAKE_MUSHROOM);
         this.createMushroomBlockDrop(SHIITAKE_MUSHROOM_BLOCK, SHIITAKE_MUSHROOM);

         this.add(
                 HibiscusBlocksAndItems.CARNATION,
                 (block) -> this.createSinglePropConditionTable(block, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)
         );
         this.add(
                 HibiscusBlocksAndItems.CATTAIL,
                 (block) -> this.createSinglePropConditionTable(block, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)
         );
         this.add(
                 HibiscusBlocksAndItems.GARDENIA,
                 (block) -> this.createSinglePropConditionTable(block, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)
         );
         this.add(
                 HibiscusBlocksAndItems.SNAPDRAGON,
                 (block) -> this.createSinglePropConditionTable(block, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)
         );
         this.add(
                 HibiscusBlocksAndItems.MARIGOLD,
                 (block) -> this.createSinglePropConditionTable(block, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)
         );
         this.add(
                 HibiscusBlocksAndItems.FOXGLOVE,
                 (block) -> this.createSinglePropConditionTable(block, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)
         );
         this.add(
                 HibiscusBlocksAndItems.LAVENDER,
                 (block) -> this.createSinglePropConditionTable(block, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)
         );
         this.add(
                 HibiscusBlocksAndItems.BLEEDING_HEART,
                 (block) -> this.createSinglePropConditionTable(block, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)
         );
         this.add(
                 HibiscusBlocksAndItems.TIGER_LILY,
                 (block) -> this.createSinglePropConditionTable(block, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)
         );

         this.dropSelf(HibiscusBlocksAndItems.ANEMONE);
         dropPottedContents(HibiscusBlocksAndItems.POTTED_ANEMONE);
         this.dropSelf(HibiscusBlocksAndItems.HIBISCUS);
         dropPottedContents(HibiscusBlocksAndItems.POTTED_HIBISCUS);
         this.dropSelf(HibiscusBlocksAndItems.BLUEBELL);
         this.dropSelf(HibiscusBlocksAndItems.TIGER_LILY);
         this.dropSelf(HibiscusBlocksAndItems.PURPLE_WILDFLOWER);
         this.dropSelf(HibiscusBlocksAndItems.YELLOW_WILDFLOWER);


         this.add(HibiscusWoods.FRAMED_SUGI_DOOR, this::createDoorTable);
         this.dropSelf(HibiscusWoods.FRAMED_SUGI_TRAPDOOR);

         this.dropSelf(HibiscusBlocksAndItems.SANDY_SOIL);

         this.dropSelf(HibiscusColoredBlocks.KAOLIN);
         this.dropSelf(HibiscusColoredBlocks.WHITE_KAOLIN);
         this.dropSelf(HibiscusColoredBlocks.LIGHT_GRAY_KAOLIN);
         this.dropSelf(HibiscusColoredBlocks.GRAY_KAOLIN);
         this.dropSelf(HibiscusColoredBlocks.BLACK_KAOLIN);
         this.dropSelf(HibiscusColoredBlocks.BROWN_KAOLIN);
         this.dropSelf(HibiscusColoredBlocks.RED_KAOLIN);
         this.dropSelf(HibiscusColoredBlocks.ORANGE_KAOLIN);
         this.dropSelf(HibiscusColoredBlocks.YELLOW_KAOLIN);
         this.dropSelf(HibiscusColoredBlocks.LIME_KAOLIN);
         this.dropSelf(HibiscusColoredBlocks.GREEN_KAOLIN);
         this.dropSelf(HibiscusColoredBlocks.CYAN_KAOLIN);
         this.dropSelf(HibiscusColoredBlocks.LIGHT_BLUE_KAOLIN);
         this.dropSelf(HibiscusColoredBlocks.BLUE_KAOLIN);
         this.dropSelf(HibiscusColoredBlocks.PURPLE_KAOLIN);
         this.dropSelf(HibiscusColoredBlocks.MAGENTA_KAOLIN);
         this.dropSelf(HibiscusColoredBlocks.PINK_KAOLIN);
         this.createSlabItemTable(HibiscusColoredBlocks.KAOLIN_SLAB);
         this.createSlabItemTable(HibiscusColoredBlocks.WHITE_KAOLIN_SLAB);
         this.createSlabItemTable(HibiscusColoredBlocks.LIGHT_GRAY_KAOLIN_SLAB);
         this.createSlabItemTable(HibiscusColoredBlocks.GRAY_KAOLIN_SLAB);
         this.createSlabItemTable(HibiscusColoredBlocks.BLACK_KAOLIN_SLAB);
         this.createSlabItemTable(HibiscusColoredBlocks.BROWN_KAOLIN_SLAB);
         this.createSlabItemTable(HibiscusColoredBlocks.RED_KAOLIN_SLAB);
         this.createSlabItemTable(HibiscusColoredBlocks.ORANGE_KAOLIN_SLAB);
         this.createSlabItemTable(HibiscusColoredBlocks.YELLOW_KAOLIN_SLAB);
         this.createSlabItemTable(HibiscusColoredBlocks.LIME_KAOLIN_SLAB);
         this.createSlabItemTable(HibiscusColoredBlocks.GREEN_KAOLIN_SLAB);
         this.createSlabItemTable(HibiscusColoredBlocks.CYAN_KAOLIN_SLAB);
         this.createSlabItemTable(HibiscusColoredBlocks.LIGHT_BLUE_KAOLIN_SLAB);
         this.createSlabItemTable(HibiscusColoredBlocks.BLUE_KAOLIN_SLAB);
         this.createSlabItemTable(HibiscusColoredBlocks.PURPLE_KAOLIN_SLAB);
         this.createSlabItemTable(HibiscusColoredBlocks.MAGENTA_KAOLIN_SLAB);
         this.createSlabItemTable(HibiscusColoredBlocks.PINK_KAOLIN_SLAB);
         this.dropSelf(HibiscusColoredBlocks.KAOLIN_STAIRS);
         this.dropSelf(HibiscusColoredBlocks.WHITE_KAOLIN_STAIRS);
         this.dropSelf(HibiscusColoredBlocks.LIGHT_GRAY_KAOLIN_STAIRS);
         this.dropSelf(HibiscusColoredBlocks.GRAY_KAOLIN_STAIRS);
         this.dropSelf(HibiscusColoredBlocks.BLACK_KAOLIN_STAIRS);
         this.dropSelf(HibiscusColoredBlocks.BROWN_KAOLIN_STAIRS);
         this.dropSelf(HibiscusColoredBlocks.RED_KAOLIN_STAIRS);
         this.dropSelf(HibiscusColoredBlocks.ORANGE_KAOLIN_STAIRS);
         this.dropSelf(HibiscusColoredBlocks.YELLOW_KAOLIN_STAIRS);
         this.dropSelf(HibiscusColoredBlocks.LIME_KAOLIN_STAIRS);
         this.dropSelf(HibiscusColoredBlocks.GREEN_KAOLIN_STAIRS);
         this.dropSelf(HibiscusColoredBlocks.CYAN_KAOLIN_STAIRS);
         this.dropSelf(HibiscusColoredBlocks.LIGHT_BLUE_KAOLIN_STAIRS);
         this.dropSelf(HibiscusColoredBlocks.BLUE_KAOLIN_STAIRS);
         this.dropSelf(HibiscusColoredBlocks.PURPLE_KAOLIN_STAIRS);
         this.dropSelf(HibiscusColoredBlocks.MAGENTA_KAOLIN_STAIRS);
         this.dropSelf(HibiscusColoredBlocks.PINK_KAOLIN_STAIRS);

         this.dropSelf(HibiscusColoredBlocks.WHITE_CHALK);
         this.dropSelf(HibiscusColoredBlocks.LIGHT_GRAY_CHALK);
         this.dropSelf(HibiscusColoredBlocks.GRAY_CHALK);
         this.dropSelf(HibiscusColoredBlocks.BLACK_CHALK);
         this.dropSelf(HibiscusColoredBlocks.BROWN_CHALK);
         this.dropSelf(HibiscusColoredBlocks.RED_CHALK);
         this.dropSelf(HibiscusColoredBlocks.ORANGE_CHALK);
         this.dropSelf(HibiscusColoredBlocks.YELLOW_CHALK);
         this.dropSelf(HibiscusColoredBlocks.LIME_CHALK);
         this.dropSelf(HibiscusColoredBlocks.GREEN_CHALK);
         this.dropSelf(HibiscusColoredBlocks.CYAN_CHALK);
         this.dropSelf(HibiscusColoredBlocks.LIGHT_BLUE_CHALK);
         this.dropSelf(HibiscusColoredBlocks.BLUE_CHALK);
         this.dropSelf(HibiscusColoredBlocks.PURPLE_CHALK);
         this.dropSelf(HibiscusColoredBlocks.MAGENTA_CHALK);
         this.dropSelf(HibiscusColoredBlocks.PINK_CHALK);
         this.createSlabItemTable(HibiscusColoredBlocks.WHITE_CHALK_SLAB);
         this.createSlabItemTable(HibiscusColoredBlocks.LIGHT_GRAY_CHALK_SLAB);
         this.createSlabItemTable(HibiscusColoredBlocks.GRAY_CHALK_SLAB);
         this.createSlabItemTable(HibiscusColoredBlocks.BLACK_CHALK_SLAB);
         this.createSlabItemTable(HibiscusColoredBlocks.BROWN_CHALK_SLAB);
         this.createSlabItemTable(HibiscusColoredBlocks.RED_CHALK_SLAB);
         this.createSlabItemTable(HibiscusColoredBlocks.ORANGE_CHALK_SLAB);
         this.createSlabItemTable(HibiscusColoredBlocks.YELLOW_CHALK_SLAB);
         this.createSlabItemTable(HibiscusColoredBlocks.LIME_CHALK_SLAB);
         this.createSlabItemTable(HibiscusColoredBlocks.GREEN_CHALK_SLAB);
         this.createSlabItemTable(HibiscusColoredBlocks.CYAN_CHALK_SLAB);
         this.createSlabItemTable(HibiscusColoredBlocks.LIGHT_BLUE_CHALK_SLAB);
         this.createSlabItemTable(HibiscusColoredBlocks.BLUE_CHALK_SLAB);
         this.createSlabItemTable(HibiscusColoredBlocks.PURPLE_CHALK_SLAB);
         this.createSlabItemTable(HibiscusColoredBlocks.MAGENTA_CHALK_SLAB);
         this.createSlabItemTable(HibiscusColoredBlocks.PINK_CHALK_SLAB);
         this.dropSelf(HibiscusColoredBlocks.WHITE_CHALK_STAIRS);
         this.dropSelf(HibiscusColoredBlocks.LIGHT_GRAY_CHALK_STAIRS);
         this.dropSelf(HibiscusColoredBlocks.GRAY_CHALK_STAIRS);
         this.dropSelf(HibiscusColoredBlocks.BLACK_CHALK_STAIRS);
         this.dropSelf(HibiscusColoredBlocks.BROWN_CHALK_STAIRS);
         this.dropSelf(HibiscusColoredBlocks.RED_CHALK_STAIRS);
         this.dropSelf(HibiscusColoredBlocks.ORANGE_CHALK_STAIRS);
         this.dropSelf(HibiscusColoredBlocks.YELLOW_CHALK_STAIRS);
         this.dropSelf(HibiscusColoredBlocks.LIME_CHALK_STAIRS);
         this.dropSelf(HibiscusColoredBlocks.GREEN_CHALK_STAIRS);
         this.dropSelf(HibiscusColoredBlocks.CYAN_CHALK_STAIRS);
         this.dropSelf(HibiscusColoredBlocks.LIGHT_BLUE_CHALK_STAIRS);
         this.dropSelf(HibiscusColoredBlocks.BLUE_CHALK_STAIRS);
         this.dropSelf(HibiscusColoredBlocks.PURPLE_CHALK_STAIRS);
         this.dropSelf(HibiscusColoredBlocks.MAGENTA_CHALK_STAIRS);
         this.dropSelf(HibiscusColoredBlocks.PINK_CHALK_STAIRS);

         this.dropSelf(HibiscusBlocksAndItems.DESERT_TURNIP_ROOT_BLOCK);

         createShearsOnlyDrop(HibiscusBlocksAndItems.SCORCHED_GRASS);
         tallPlantDrop(HibiscusBlocksAndItems.TALL_SCORCHED_GRASS, HibiscusBlocksAndItems.SCORCHED_GRASS);

         createShearsOnlyDrop(SEDGE_GRASS);
         tallPlantDrop(TALL_SEDGE_GRASS, SEDGE_GRASS);

         createShearsOnlyDrop(FLAXEN_FERN);
         dropPottedContents(POTTED_FLAXEN_FERN);
         tallPlantDrop(LARGE_FLAXEN_FERN, FLAXEN_FERN);

      }
   }

   private static class NatureSpiritModelGenerator extends FabricModelProvider {

      private static final ModelTemplate TALL_LARGE_CROSS = block("tall_large_cross", TextureSlot.CROSS);
      private static final ModelTemplate LARGE_CROSS = block("large_cross", TextureSlot.CROSS);
      private static final ModelTemplate TALL_CROSS = block("tall_cross", TextureSlot.CROSS);
      private static final ModelTemplate FLOWER_POT_TALL_CROSS = block("flower_pot_tall_cross", TextureSlot.PLANT);
      private static final ModelTemplate CROP = block("crop", TextureSlot.CROP);

      public NatureSpiritModelGenerator(FabricDataOutput output) {
         super(output);
      }

      private static ModelTemplate block(String parent, TextureSlot... requiredTextureKeys) {
         return new ModelTemplate(Optional.of(new ResourceLocation("natures_spirit", "block/" + parent)),
                 Optional.empty(),
                 requiredTextureKeys
         );
      }

      public static ResourceLocation getId(Block block) {
         ResourceLocation identifier = BuiltInRegistries.BLOCK.getKey(block);
         return identifier.withPrefix("block/");
      }

      private void createSlab(Block block, Block slab, BlockModelGenerators blockStateModelGenerator) {
         ResourceLocation resourceLocation = ModelLocationUtils.getModelLocation(block);
         TexturedModel texturedModel = TexturedModel.CUBE.get(block);
         ResourceLocation resourceLocation2 = ModelTemplates.SLAB_BOTTOM.create(slab,
                 texturedModel.getMapping(),
                 blockStateModelGenerator.modelOutput
         );
         ResourceLocation resourceLocation3 = ModelTemplates.SLAB_TOP.create(slab,
                 texturedModel.getMapping(),
                 blockStateModelGenerator.modelOutput
         );
         blockStateModelGenerator.blockStateOutput.accept(createSlab(slab,
                 resourceLocation2,
                 resourceLocation3,
                 resourceLocation
         ));
      }

      private void createStairs(Block block, Block stairs, BlockModelGenerators blockStateModelGenerator) {
         TexturedModel texturedModel = TexturedModel.CUBE.get(block);
         ResourceLocation resourceLocation = ModelTemplates.STAIRS_INNER.create(stairs,
                 texturedModel.getMapping(),
                 blockStateModelGenerator.modelOutput
         );
         ResourceLocation resourceLocation2 = ModelTemplates.STAIRS_STRAIGHT.create(stairs,
                 texturedModel.getMapping(),
                 blockStateModelGenerator.modelOutput
         );
         ResourceLocation resourceLocation3 = ModelTemplates.STAIRS_OUTER.create(stairs,
                 texturedModel.getMapping(),
                 blockStateModelGenerator.modelOutput
         );
         blockStateModelGenerator.blockStateOutput.accept(createStairs(stairs,
                 resourceLocation,
                 resourceLocation2,
                 resourceLocation3
         ));
         blockStateModelGenerator.delegateItemModel(stairs, resourceLocation2);
      }

      public void createWoodDoor(Block doorBlock, BlockModelGenerators blockStateModelGenerator) {
         TextureMapping textureMapping = TextureMapping.door(doorBlock);
         ResourceLocation resourceLocation = ModelTemplates.DOOR_BOTTOM_LEFT.create(doorBlock,
                 textureMapping,
                 blockStateModelGenerator.modelOutput
         );
         ResourceLocation resourceLocation2 = ModelTemplates.DOOR_BOTTOM_LEFT_OPEN.create(doorBlock,
                 textureMapping,
                 blockStateModelGenerator.modelOutput
         );
         ResourceLocation resourceLocation3 = ModelTemplates.DOOR_BOTTOM_RIGHT.create(doorBlock,
                 textureMapping,
                 blockStateModelGenerator.modelOutput
         );
         ResourceLocation resourceLocation4 = ModelTemplates.DOOR_BOTTOM_RIGHT_OPEN.create(doorBlock,
                 textureMapping,
                 blockStateModelGenerator.modelOutput
         );
         ResourceLocation resourceLocation5 = ModelTemplates.DOOR_TOP_LEFT.create(doorBlock,
                 textureMapping,
                 blockStateModelGenerator.modelOutput
         );
         ResourceLocation resourceLocation6 = ModelTemplates.DOOR_TOP_LEFT_OPEN.create(doorBlock,
                 textureMapping,
                 blockStateModelGenerator.modelOutput
         );
         ResourceLocation resourceLocation7 = ModelTemplates.DOOR_TOP_RIGHT.create(doorBlock,
                 textureMapping,
                 blockStateModelGenerator.modelOutput
         );
         ResourceLocation resourceLocation8 = ModelTemplates.DOOR_TOP_RIGHT_OPEN.create(doorBlock,
                 textureMapping,
                 blockStateModelGenerator.modelOutput
         );
         blockStateModelGenerator.createSimpleFlatItemModel(doorBlock.asItem());
         blockStateModelGenerator.blockStateOutput.accept(createDoor(doorBlock,
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

      public void createWoodTrapdoor(Block orientableTrapdoorBlock, BlockModelGenerators blockStateModelGenerators) {
         TextureMapping textureMapping = TextureMapping.defaultTexture(orientableTrapdoorBlock);
         ResourceLocation resourceLocation = ModelTemplates.ORIENTABLE_TRAPDOOR_TOP.create(orientableTrapdoorBlock,
                 textureMapping,
                 blockStateModelGenerators.modelOutput
         );
         ResourceLocation resourceLocation2 = ModelTemplates.ORIENTABLE_TRAPDOOR_BOTTOM.create(orientableTrapdoorBlock,
                 textureMapping,
                 blockStateModelGenerators.modelOutput
         );
         ResourceLocation resourceLocation3 = ModelTemplates.ORIENTABLE_TRAPDOOR_OPEN.create(orientableTrapdoorBlock,
                 textureMapping,
                 blockStateModelGenerators.modelOutput
         );
         blockStateModelGenerators.blockStateOutput.accept(createOrientableTrapdoor(orientableTrapdoorBlock,
                 resourceLocation,
                 resourceLocation2,
                 resourceLocation3
         ));
         blockStateModelGenerators.delegateItemModel(orientableTrapdoorBlock, resourceLocation2);
      }

      public void createWoodFenceGate(Block planks, Block fenceGateBlock, BlockModelGenerators blockStateModelGenerator) {
         TexturedModel texturedModel = TexturedModel.CUBE.get(planks);
         ResourceLocation resourceLocation = ModelTemplates.FENCE_GATE_OPEN.create(fenceGateBlock,
                 texturedModel.getMapping(),
                 blockStateModelGenerator.modelOutput
         );
         ResourceLocation resourceLocation2 = ModelTemplates.FENCE_GATE_CLOSED.create(fenceGateBlock,
                 texturedModel.getMapping(),
                 blockStateModelGenerator.modelOutput
         );
         ResourceLocation resourceLocation3 = ModelTemplates.FENCE_GATE_WALL_OPEN.create(fenceGateBlock,
                 texturedModel.getMapping(),
                 blockStateModelGenerator.modelOutput
         );
         ResourceLocation resourceLocation4 = ModelTemplates.FENCE_GATE_WALL_CLOSED.create(fenceGateBlock,
                 texturedModel.getMapping(),
                 blockStateModelGenerator.modelOutput
         );
         blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createFenceGate(fenceGateBlock,
                 resourceLocation,
                 resourceLocation2,
                 resourceLocation3,
                 resourceLocation4,
                 true
         ));
      }

      public void createWoodFence(Block planks, Block fenceBlock, BlockModelGenerators blockStateModelGenerators) {
         TexturedModel texturedModel = TexturedModel.CUBE.get(planks);
         ResourceLocation resourceLocation = ModelTemplates.FENCE_POST.create(fenceBlock,
                 texturedModel.getMapping(),
                 blockStateModelGenerators.modelOutput
         );
         ResourceLocation resourceLocation2 = ModelTemplates.FENCE_SIDE.create(fenceBlock,
                 texturedModel.getMapping(),
                 blockStateModelGenerators.modelOutput
         );
         blockStateModelGenerators.blockStateOutput.accept(BlockModelGenerators.createFence(fenceBlock,
                 resourceLocation,
                 resourceLocation2
         ));
         ResourceLocation resourceLocation3 = ModelTemplates.FENCE_INVENTORY.create(fenceBlock,
                 texturedModel.getMapping(),
                 blockStateModelGenerators.modelOutput
         );
         blockStateModelGenerators.delegateItemModel(fenceBlock, resourceLocation3);
      }

      public void createWoodPressurePlate(Block planks, Block pressurePlateBlock, BlockModelGenerators blockStateModelGenerator) {
         TexturedModel texturedModel = TexturedModel.CUBE.get(planks);
         ResourceLocation resourceLocation = ModelTemplates.PRESSURE_PLATE_UP.create(pressurePlateBlock,
                 texturedModel.getMapping(),
                 blockStateModelGenerator.modelOutput
         );
         ResourceLocation resourceLocation2 = ModelTemplates.PRESSURE_PLATE_DOWN.create(pressurePlateBlock,
                 texturedModel.getMapping(),
                 blockStateModelGenerator.modelOutput
         );
         blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createPressurePlate(pressurePlateBlock,
                 resourceLocation,
                 resourceLocation2
         ));
      }

      public void createWoodSign(Block signBlock, Block wallSignBlock, BlockModelGenerators blockStateModelGenerator) {
         TextureMapping textureMapping = TextureMapping.defaultTexture(signBlock);
         ResourceLocation resourceLocation = ModelTemplates.PARTICLE_ONLY.create(signBlock,
                 textureMapping,
                 blockStateModelGenerator.modelOutput
         );
         blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(signBlock,
                 resourceLocation
         ));
         blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(wallSignBlock,
                 resourceLocation
         ));
         blockStateModelGenerator.createSimpleFlatItemModel(signBlock.asItem());
         blockStateModelGenerator.skipAutoItemBlock(wallSignBlock);
      }

      public void createWoodButton(Block planks, Block buttonBlock, BlockModelGenerators blockStateModelGenerators) {
         TexturedModel texturedModel = TexturedModel.CUBE.get(planks);
         ResourceLocation resourceLocation = ModelTemplates.BUTTON.create(buttonBlock,
                 texturedModel.getMapping(),
                 blockStateModelGenerators.modelOutput
         );
         ResourceLocation resourceLocation2 = ModelTemplates.BUTTON_PRESSED.create(buttonBlock,
                 texturedModel.getMapping(),
                 blockStateModelGenerators.modelOutput
         );
         blockStateModelGenerators.blockStateOutput.accept(BlockModelGenerators.createButton(buttonBlock,
                 resourceLocation,
                 resourceLocation2
         ));
         ResourceLocation resourceLocation3 = ModelTemplates.BUTTON_INVENTORY.create(buttonBlock,
                 texturedModel.getMapping(),
                 blockStateModelGenerators.modelOutput
         );
         blockStateModelGenerators.delegateItemModel(buttonBlock, resourceLocation3);
      }

      public void createHangingSign(Block strippedLog, Block hangingSign, Block wallHangingSign, BlockModelGenerators blockStateModelGenerator) {
         TextureMapping textureMap = TextureMapping.particle(strippedLog);
         ResourceLocation identifier = ModelTemplates.PARTICLE_ONLY.create(hangingSign,
                 textureMap,
                 blockStateModelGenerator.modelOutput
         );
         blockStateModelGenerator.blockStateOutput.accept(createSimpleBlock(hangingSign, identifier));
         blockStateModelGenerator.blockStateOutput.accept(createSimpleBlock(wallHangingSign, identifier));
         blockStateModelGenerator.createSimpleFlatItemModel(hangingSign.asItem());
         blockStateModelGenerator.skipAutoItemBlock(wallHangingSign);
      }

      private void generateWoodBlockStateModels(HashMap <String, Block[]> woods, BlockModelGenerators blockStateModelGenerator) {
         for(String i : woods.keySet()) {
            Block[] woodType = woods.get(i);
            blockStateModelGenerator.woodProvider(woodType[2]).logWithHorizontal(woodType[2]).wood(woodType[0]);
            blockStateModelGenerator.woodProvider(woodType[3]).logWithHorizontal(woodType[3]).wood(woodType[1]);
            blockStateModelGenerator.createTrivialBlock(woodType[4], TexturedModel.CUBE);
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

      private void generateJoshuaWoodBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
         blockStateModelGenerator.createTrivialBlock(HibiscusWoods.JOSHUA[2], TexturedModel.CUBE);
         createSlab(HibiscusWoods.JOSHUA[2], HibiscusWoods.JOSHUA[4], blockStateModelGenerator);
         createStairs(HibiscusWoods.JOSHUA[2], HibiscusWoods.JOSHUA[3], blockStateModelGenerator);
         createWoodDoor(HibiscusWoods.JOSHUA[5], blockStateModelGenerator);
         createWoodTrapdoor(HibiscusWoods.JOSHUA[6], blockStateModelGenerator);
         createWoodFenceGate(HibiscusWoods.JOSHUA[2], HibiscusWoods.JOSHUA[8], blockStateModelGenerator);
         createWoodFence(HibiscusWoods.JOSHUA[2], HibiscusWoods.JOSHUA[7], blockStateModelGenerator);
         createWoodButton(HibiscusWoods.JOSHUA[2], HibiscusWoods.JOSHUA[10], blockStateModelGenerator);
         createWoodPressurePlate(HibiscusWoods.JOSHUA[2], HibiscusWoods.JOSHUA[9], blockStateModelGenerator);
         createWoodSign(HibiscusWoods.JOSHUA[11], HibiscusWoods.JOSHUA[12], blockStateModelGenerator);
         createHangingSign(
                 HibiscusWoods.JOSHUA[1],
                 HibiscusWoods.JOSHUA[13],
                 HibiscusWoods.JOSHUA[14],
                 blockStateModelGenerator
         );
      }

      private void generateTreeBlockStateModels(HashMap <String, Block[]> saplings, HashMap <String, Block> leaves, BlockModelGenerators blockStateModelGenerator) {
         for(String i : leaves.keySet()) {
            Block[] saplingType = saplings.get(i);
            Block leavesType = leaves.get(i);
            blockStateModelGenerator.createTrivialBlock(leavesType, TexturedModel.LEAVES);
            blockStateModelGenerator.createPlant(saplingType[0], saplingType[1], TintState.NOT_TINTED);
         }
      }

      public final void registerTallCrossBlockState(Block block, TextureMapping crossTexture, BlockModelGenerators blockStateModelGenerators) {
         ResourceLocation identifier = TALL_CROSS.create(block, crossTexture, blockStateModelGenerators.modelOutput);
         blockStateModelGenerators.blockStateOutput.accept(createSimpleBlock(block, identifier));
      }

      public final void registerVineBlockState(Block block, TextureMapping crossTexture, BlockModelGenerators blockStateModelGenerators) {
         ResourceLocation identifier = CROP.create(block, crossTexture, blockStateModelGenerators.modelOutput);
         blockStateModelGenerators.blockStateOutput.accept(createSimpleBlock(block, identifier));
      }

      public final void registerTallLargeBlockState(Block block, TextureMapping crossTexture, BlockModelGenerators blockStateModelGenerators) {
         ResourceLocation identifier = TALL_LARGE_CROSS.create(block, crossTexture, blockStateModelGenerators.modelOutput);
         blockStateModelGenerators.blockStateOutput.accept(createSimpleBlock(block, identifier));
      }

      public final void registerSpecificFlowerItemModel(Block block, BlockModelGenerators blockStateModelGenerators) {
         Item item = block.asItem();
         ModelTemplates.FLAT_ITEM.create(ModelLocationUtils.getModelLocation(item),
                 TextureMapping.layer0(item),
                 blockStateModelGenerators.modelOutput
         );
      }

      private void generateFlowerBlockStateModels(Block block, Block block2, BlockModelGenerators blockStateModelGenerator) {
         blockStateModelGenerator.createPlant(block, block2, TintState.NOT_TINTED);
      }

      private void generatePottedAnemone(Block block, Block flowerPot, BlockModelGenerators blockStateModelGenerators) {
         registerSpecificFlowerItemModel(block, blockStateModelGenerators);
         TextureMapping textureMap1 = TextureMapping.cross(block);
         registerTallCrossBlockState(block, textureMap1, blockStateModelGenerators);
         TextureMapping textureMap = TextureMapping.plant(block);
         ResourceLocation identifier = FLOWER_POT_TALL_CROSS.create(flowerPot,
                 textureMap,
                 blockStateModelGenerators.modelOutput
         );
         blockStateModelGenerators.blockStateOutput.accept(createSimpleBlock(flowerPot, identifier));
      }

      public final void generateVineBlockStateModels(Block plant, Block plantStem, BlockModelGenerators blockStateModelGenerators) {
         TextureMapping textureMap1 = TextureMapping.crop(getId(plant));
         this.registerVineBlockState(plant, textureMap1, blockStateModelGenerators);
         TextureMapping textureMap2 = TextureMapping.crop(getId(plantStem));
         this.registerVineBlockState(plantStem, textureMap2, blockStateModelGenerators);
         blockStateModelGenerators.createSimpleFlatItemModel(plant, "_plant");
      }

      public final void generateTallLargeFlower(Block doubleBlock, BlockModelGenerators blockStateModelGenerators) {
         registerSpecificFlowerItemModel(doubleBlock, blockStateModelGenerators);
         ResourceLocation identifier = blockStateModelGenerators.createSuffixedVariant(doubleBlock,
                 "_top",
                 LARGE_CROSS,
                 TextureMapping::cross
         );
         ResourceLocation identifier2 = blockStateModelGenerators.createSuffixedVariant(doubleBlock,
                 "_bottom",
                 LARGE_CROSS,
                 TextureMapping::cross
         );
         blockStateModelGenerators.createDoubleBlock(doubleBlock, identifier, identifier2);
      }

      public final void generateLargeFlower(Block block, BlockModelGenerators blockStateModelGenerators) {
         registerSpecificFlowerItemModel(block, blockStateModelGenerators);
         registerTallLargeBlockState(block, TextureMapping.cross(block), blockStateModelGenerators);
      }

      public final void registerCropWithoutItem(Block crop, Property <Integer> ageProperty, BlockModelGenerators blockStateModelGenerator, int... ageTextureIndices) {
         if(ageProperty.getPossibleValues().size() != ageTextureIndices.length) {
            throw new IllegalArgumentException();
         }
         else {
            Int2ObjectMap <ResourceLocation> int2ObjectMap = new Int2ObjectOpenHashMap();
            PropertyDispatch blockStateVariantMap = PropertyDispatch.property(ageProperty).generate((integer) -> {
               int i = ageTextureIndices[integer];
               ResourceLocation identifier = (ResourceLocation) int2ObjectMap.computeIfAbsent(i, (j) -> {
                  return blockStateModelGenerator.createSuffixedVariant(crop, "_stage" + i, ModelTemplates.CROP, TextureMapping::crop);
               });
               return Variant.variant().with(VariantProperties.MODEL, identifier);
            });
            blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(crop)
                    .with(blockStateVariantMap));
         }
      }

      public final void registerMushroomBlock(Block mushroomBlock, BlockModelGenerators blockStateModelGenerator) {
         ResourceLocation identifier = ModelTemplates.SINGLE_FACE.create(
                 mushroomBlock,
                 TextureMapping.defaultTexture(mushroomBlock),
                 blockStateModelGenerator.modelOutput
         );
         ResourceLocation identifier2 = ModelLocationUtils.decorateBlockModelLocation("mushroom_block_inside");
         blockStateModelGenerator.blockStateOutput.accept(MultiPartGenerator.multiPart(mushroomBlock)
                 .with(
                         Condition.condition().term(BlockStateProperties.NORTH, true),
                         Variant.variant().with(VariantProperties.MODEL, identifier)
                 )
                 .with(Condition.condition().term(BlockStateProperties.EAST, true), Variant.variant().with(
                         VariantProperties.MODEL,
                         identifier
                 ).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true))
                 .with(Condition.condition().term(BlockStateProperties.SOUTH, true), Variant.variant().with(
                         VariantProperties.MODEL,
                         identifier
                 ).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true))
                 .with(Condition.condition().term(BlockStateProperties.WEST, true), Variant.variant().with(
                         VariantProperties.MODEL,
                         identifier
                 ).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true))
                 .with(Condition.condition().term(BlockStateProperties.UP, true), Variant.variant().with(
                         VariantProperties.MODEL,
                         identifier
                 ).with(VariantProperties.X_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true))
                 .with(Condition.condition().term(BlockStateProperties.DOWN, true), Variant.variant().with(
                         VariantProperties.MODEL,
                         identifier
                 ).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true))
                 .with(
                         Condition.condition().term(BlockStateProperties.NORTH, false),
                         Variant.variant().with(VariantProperties.MODEL, identifier2)
                 )
                 .with(Condition.condition().term(BlockStateProperties.EAST, false), Variant.variant().with(
                         VariantProperties.MODEL,
                         identifier2
                 ).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, false))
                 .with(Condition.condition().term(BlockStateProperties.SOUTH, false), Variant.variant().with(
                         VariantProperties.MODEL,
                         identifier2
                 ).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, false))
                 .with(Condition.condition().term(BlockStateProperties.WEST, false), Variant.variant().with(
                         VariantProperties.MODEL,
                         identifier2
                 ).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, false))
                 .with(Condition.condition().term(BlockStateProperties.UP, false), Variant.variant().with(
                         VariantProperties.MODEL,
                         identifier2
                 ).with(VariantProperties.X_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, false))
                 .with(Condition.condition().term(BlockStateProperties.DOWN, false), Variant.variant().with(
                         VariantProperties.MODEL,
                         identifier2
                 ).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, false)));
         blockStateModelGenerator.delegateItemModel(
                 mushroomBlock,
                 TexturedModel.CUBE.createWithSuffix(mushroomBlock, "_inventory", blockStateModelGenerator.modelOutput)
         );
      }

      @Override public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
         generateWoodBlockStateModels(HibiscusRegistryHelper.WoodHashMap, blockStateModelGenerator);
         generateJoshuaWoodBlockStateModels(blockStateModelGenerator);
         generateTreeBlockStateModels(HibiscusRegistryHelper.SaplingHashMap, HibiscusRegistryHelper.LeavesHashMap, blockStateModelGenerator);

         blockStateModelGenerator.createAmethystCluster(CALCITE_CLUSTER);
         blockStateModelGenerator.createAmethystCluster(SMALL_CALCITE_BUD);
         blockStateModelGenerator.createAmethystCluster(LARGE_CALCITE_BUD);

         generateFlowerBlockStateModels(
                 HibiscusBlocksAndItems.HIBISCUS,
                 HibiscusBlocksAndItems.POTTED_HIBISCUS,
                 blockStateModelGenerator
         );
         generateFlowerBlockStateModels(FLAXEN_FERN, POTTED_FLAXEN_FERN, blockStateModelGenerator);
         generateFlowerBlockStateModels(SHIITAKE_MUSHROOM, POTTED_SHIITAKE_MUSHROOM, blockStateModelGenerator);
         registerMushroomBlock(SHIITAKE_MUSHROOM_BLOCK, blockStateModelGenerator);
         registerCropWithoutItem(
                 HibiscusBlocksAndItems.DESERT_TURNIP_STEM,
                 DesertPlantBlock.AGE,
                 blockStateModelGenerator,
                 0,
                 1,
                 2,
                 3,
                 4,
                 5,
                 6,
                 7
         );
         blockStateModelGenerator.createDoublePlant(HibiscusBlocksAndItems.CARNATION, TintState.NOT_TINTED);
         blockStateModelGenerator.createDoublePlant(HibiscusBlocksAndItems.GARDENIA, TintState.NOT_TINTED);
         blockStateModelGenerator.createDoublePlant(HibiscusBlocksAndItems.SNAPDRAGON, TintState.NOT_TINTED);
         blockStateModelGenerator.createDoublePlant(HibiscusBlocksAndItems.MARIGOLD, TintState.NOT_TINTED);
         blockStateModelGenerator.createDoublePlant(HibiscusBlocksAndItems.FOXGLOVE, TintState.NOT_TINTED);
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
         generateVineBlockStateModels(
                 HibiscusWoods.BLUE_WISTERIA_VINES,
                 HibiscusWoods.BLUE_WISTERIA_VINES_PLANT,
                 blockStateModelGenerator
         );
         generateVineBlockStateModels(
                 HibiscusWoods.WHITE_WISTERIA_VINES,
                 HibiscusWoods.WHITE_WISTERIA_VINES_PLANT,
                 blockStateModelGenerator
         );
         generateVineBlockStateModels(
                 HibiscusWoods.PURPLE_WISTERIA_VINES,
                 HibiscusWoods.PURPLE_WISTERIA_VINES_PLANT,
                 blockStateModelGenerator
         );
         generateVineBlockStateModels(
                 HibiscusWoods.PINK_WISTERIA_VINES,
                 HibiscusWoods.PINK_WISTERIA_VINES_PLANT,
                 blockStateModelGenerator
         );
         generateVineBlockStateModels(
                 HibiscusWoods.WILLOW_VINES,
                 HibiscusWoods.WILLOW_VINES_PLANT,
                 blockStateModelGenerator
         );

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
         createStairs(
                 HibiscusColoredBlocks.LIGHT_GRAY_KAOLIN,
                 HibiscusColoredBlocks.LIGHT_GRAY_KAOLIN_STAIRS,
                 blockStateModelGenerator
         );
         createStairs(HibiscusColoredBlocks.GRAY_KAOLIN, HibiscusColoredBlocks.GRAY_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.BLACK_KAOLIN, HibiscusColoredBlocks.BLACK_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.BROWN_KAOLIN, HibiscusColoredBlocks.BROWN_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.RED_KAOLIN, HibiscusColoredBlocks.RED_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.ORANGE_KAOLIN, HibiscusColoredBlocks.ORANGE_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.YELLOW_KAOLIN, HibiscusColoredBlocks.YELLOW_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.LIME_KAOLIN, HibiscusColoredBlocks.LIME_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.GREEN_KAOLIN, HibiscusColoredBlocks.GREEN_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.CYAN_KAOLIN, HibiscusColoredBlocks.CYAN_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(
                 HibiscusColoredBlocks.LIGHT_BLUE_KAOLIN,
                 HibiscusColoredBlocks.LIGHT_BLUE_KAOLIN_STAIRS,
                 blockStateModelGenerator
         );
         createStairs(HibiscusColoredBlocks.BLUE_KAOLIN, HibiscusColoredBlocks.BLUE_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.PURPLE_KAOLIN, HibiscusColoredBlocks.PURPLE_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.MAGENTA_KAOLIN, HibiscusColoredBlocks.MAGENTA_KAOLIN_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.PINK_KAOLIN, HibiscusColoredBlocks.PINK_KAOLIN_STAIRS, blockStateModelGenerator);

         blockStateModelGenerator.createTrivialBlock(HibiscusColoredBlocks.KAOLIN, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusColoredBlocks.WHITE_KAOLIN, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusColoredBlocks.LIGHT_GRAY_KAOLIN, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusColoredBlocks.GRAY_KAOLIN, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusColoredBlocks.BLACK_KAOLIN, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusColoredBlocks.BROWN_KAOLIN, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusColoredBlocks.RED_KAOLIN, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusColoredBlocks.ORANGE_KAOLIN, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusColoredBlocks.YELLOW_KAOLIN, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusColoredBlocks.LIME_KAOLIN, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusColoredBlocks.GREEN_KAOLIN, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusColoredBlocks.CYAN_KAOLIN, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusColoredBlocks.LIGHT_BLUE_KAOLIN, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusColoredBlocks.BLUE_KAOLIN, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusColoredBlocks.PURPLE_KAOLIN, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusColoredBlocks.MAGENTA_KAOLIN, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusColoredBlocks.PINK_KAOLIN, TexturedModel.CUBE);

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
         createStairs(
                 HibiscusColoredBlocks.LIGHT_GRAY_CHALK,
                 HibiscusColoredBlocks.LIGHT_GRAY_CHALK_STAIRS,
                 blockStateModelGenerator
         );
         createStairs(HibiscusColoredBlocks.GRAY_CHALK, HibiscusColoredBlocks.GRAY_CHALK_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.BLACK_CHALK, HibiscusColoredBlocks.BLACK_CHALK_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.BROWN_CHALK, HibiscusColoredBlocks.BROWN_CHALK_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.RED_CHALK, HibiscusColoredBlocks.RED_CHALK_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.ORANGE_CHALK, HibiscusColoredBlocks.ORANGE_CHALK_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.YELLOW_CHALK, HibiscusColoredBlocks.YELLOW_CHALK_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.LIME_CHALK, HibiscusColoredBlocks.LIME_CHALK_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.GREEN_CHALK, HibiscusColoredBlocks.GREEN_CHALK_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.CYAN_CHALK, HibiscusColoredBlocks.CYAN_CHALK_STAIRS, blockStateModelGenerator);
         createStairs(
                 HibiscusColoredBlocks.LIGHT_BLUE_CHALK,
                 HibiscusColoredBlocks.LIGHT_BLUE_CHALK_STAIRS,
                 blockStateModelGenerator
         );
         createStairs(HibiscusColoredBlocks.BLUE_CHALK, HibiscusColoredBlocks.BLUE_CHALK_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.PURPLE_CHALK, HibiscusColoredBlocks.PURPLE_CHALK_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.MAGENTA_CHALK, HibiscusColoredBlocks.MAGENTA_CHALK_STAIRS, blockStateModelGenerator);
         createStairs(HibiscusColoredBlocks.PINK_CHALK, HibiscusColoredBlocks.PINK_CHALK_STAIRS, blockStateModelGenerator);

         blockStateModelGenerator.createTrivialBlock(HibiscusColoredBlocks.WHITE_CHALK, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusColoredBlocks.LIGHT_GRAY_CHALK, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusColoredBlocks.GRAY_CHALK, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusColoredBlocks.BLACK_CHALK, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusColoredBlocks.BROWN_CHALK, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusColoredBlocks.RED_CHALK, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusColoredBlocks.ORANGE_CHALK, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusColoredBlocks.YELLOW_CHALK, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusColoredBlocks.LIME_CHALK, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusColoredBlocks.GREEN_CHALK, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusColoredBlocks.CYAN_CHALK, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusColoredBlocks.LIGHT_BLUE_CHALK, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusColoredBlocks.BLUE_CHALK, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusColoredBlocks.PURPLE_CHALK, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusColoredBlocks.MAGENTA_CHALK, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusColoredBlocks.PINK_CHALK, TexturedModel.CUBE);

         blockStateModelGenerator.createRotatedPillarWithHorizontalVariant(
                 HibiscusBlocksAndItems.DESERT_TURNIP_ROOT_BLOCK,
                 TexturedModel.COLUMN_ALT,
                 TexturedModel.COLUMN_HORIZONTAL_ALT
         );
      }

      @Override public void generateItemModels(ItemModelGenerators itemModelGenerator) {
         itemModelGenerator.generateFlatItem(HibiscusBlocksAndItems.GREEN_OLIVES, ModelTemplates.FLAT_ITEM);
         itemModelGenerator.generateFlatItem(HibiscusBlocksAndItems.BLACK_OLIVES, ModelTemplates.FLAT_ITEM);
         itemModelGenerator.generateFlatItem(HibiscusBlocksAndItems.DESERT_TURNIP, ModelTemplates.FLAT_ITEM);
         itemModelGenerator.generateFlatItem(CALCITE_SHARD, ModelTemplates.FLAT_ITEM);
         for(HibiscusBoatEntity.HibiscusBoat boat : HibiscusBoatEntity.HibiscusBoat.values()) {
            itemModelGenerator.generateFlatItem(boat.boat().asItem(), ModelTemplates.FLAT_ITEM);
            itemModelGenerator.generateFlatItem(boat.chestBoat().asItem(), ModelTemplates.FLAT_ITEM);
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
            String temp = capitalizeString(saplings.get(i)[0].toString()
                    .replace("Block{natures_spirit:", "")
                    .replace("_", " ")
                    .replace("}", ""));

            translationBuilder.add(saplings.get(i)[0], temp);
            String temp2 = capitalizeString(leaves.get(i)
                    .toString()
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

      private void generateBoatTranslations(TranslationBuilder translationBuilder) {
         for(HibiscusBoatEntity.HibiscusBoat boat : HibiscusBoatEntity.HibiscusBoat.values()) {
            translationBuilder.add(boat.boat().asItem(), capitalizeString(boat.getSerializedName()) + " Boat");
            translationBuilder.add(boat.chestBoat().asItem(), capitalizeString(boat.getSerializedName()) + " Boat With Chest");
         }
      }

      @Override public void generateTranslations(TranslationBuilder translationBuilder) {
         generateWoodTranslations(HibiscusRegistryHelper.WoodHashMap, translationBuilder);
         generateBoatTranslations(translationBuilder);
         generateJoshuaTranslations(translationBuilder);
         generateTreeTranslations(HibiscusRegistryHelper.SaplingHashMap, HibiscusRegistryHelper.LeavesHashMap, translationBuilder);
         translationBuilder.add(HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP, "Nature's Spirit Blocks & Items");
         translationBuilder.add(HibiscusBlocksAndItems.GREEN_OLIVES, "Green Olives");
         translationBuilder.add(HibiscusBlocksAndItems.BLACK_OLIVES, "Black Olives");
         translationBuilder.add(HibiscusBlocksAndItems.DESERT_TURNIP, "Desert Turnip");
         translationBuilder.add(CALCITE_SHARD, "Calcite Shard");
         translationBuilder.add(SMALL_CALCITE_BUD, "Small Calcite Bud");
         translationBuilder.add(LARGE_CALCITE_BUD, "Large Calcite Bud");
         translationBuilder.add(CALCITE_CLUSTER, "Calcite Cluster");
         translationBuilder.add("stat.minecraft.eat_pizza_slice", "Pizza Slices Eaten");
         generateBlockTranslations(HibiscusBlocksAndItems.ANEMONE, translationBuilder);
         generateBlockTranslations(HibiscusBlocksAndItems.POTTED_ANEMONE, translationBuilder);
         generateBlockTranslations(HibiscusBlocksAndItems.LAVENDER, translationBuilder);
         generateBlockTranslations(HibiscusBlocksAndItems.BLEEDING_HEART, translationBuilder);
         generateBlockTranslations(HibiscusBlocksAndItems.BLUEBELL, translationBuilder);
         generateBlockTranslations(HibiscusBlocksAndItems.TIGER_LILY, translationBuilder);
         generateBlockTranslations(HibiscusBlocksAndItems.PURPLE_WILDFLOWER, translationBuilder);
         generateBlockTranslations(HibiscusBlocksAndItems.YELLOW_WILDFLOWER, translationBuilder);
         generateBlockTranslations(HibiscusBlocksAndItems.TALL_SCORCHED_GRASS, translationBuilder);
         generateBlockTranslations(HibiscusBlocksAndItems.SCORCHED_GRASS, translationBuilder);
         generateBlockTranslations(TALL_SEDGE_GRASS, translationBuilder);
         generateBlockTranslations(SEDGE_GRASS, translationBuilder);
         generateBlockTranslations(LARGE_FLAXEN_FERN, translationBuilder);
         generateBlockTranslations(FLAXEN_FERN, translationBuilder);
         generateBlockTranslations(SHIITAKE_MUSHROOM, translationBuilder);
         generateBlockTranslations(SHIITAKE_MUSHROOM_BLOCK, translationBuilder);
         generateBlockTranslations(HibiscusBlocksAndItems.CARNATION, translationBuilder);
         generateBlockTranslations(HibiscusBlocksAndItems.HIBISCUS, translationBuilder);
         generateBlockTranslations(HibiscusBlocksAndItems.POTTED_HIBISCUS, translationBuilder);
         generateBlockTranslations(HibiscusBlocksAndItems.GARDENIA, translationBuilder);
         generateBlockTranslations(HibiscusBlocksAndItems.SNAPDRAGON, translationBuilder);
         generateBlockTranslations(HibiscusWoods.FRAMED_SUGI_DOOR, translationBuilder);
         generateBlockTranslations(HibiscusWoods.FRAMED_SUGI_TRAPDOOR, translationBuilder);
         generateBlockTranslations(HibiscusWoods.PINK_WISTERIA_VINES, translationBuilder);
         generateBlockTranslations(HibiscusWoods.BLUE_WISTERIA_VINES, translationBuilder);
         generateBlockTranslations(HibiscusWoods.WHITE_WISTERIA_VINES, translationBuilder);
         generateBlockTranslations(HibiscusWoods.PURPLE_WISTERIA_VINES, translationBuilder);
         generateBlockTranslations(HibiscusWoods.WILLOW_VINES, translationBuilder);
         generateBlockTranslations(HibiscusBlocksAndItems.CATTAIL, translationBuilder);
         generateBlockTranslations(HibiscusBlocksAndItems.MARIGOLD, translationBuilder);
         generateBlockTranslations(HibiscusBlocksAndItems.FOXGLOVE, translationBuilder);
         generateBlockTranslations(HibiscusBlocksAndItems.DESERT_TURNIP_ROOT_BLOCK, translationBuilder);
         generateBlockTranslations(HibiscusBlocksAndItems.DESERT_TURNIP_BLOCK, translationBuilder);
         generateBlockTranslations(LOTUS_FLOWER, translationBuilder);
         generateBlockTranslations(LOTUS_STEM, translationBuilder);

         generateBlockTranslations(HibiscusBlocksAndItems.SANDY_SOIL, translationBuilder);

         generateBlockTranslations(HibiscusColoredBlocks.KAOLIN, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.WHITE_KAOLIN, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.LIGHT_GRAY_KAOLIN, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.GRAY_KAOLIN, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.BLACK_KAOLIN, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.BROWN_KAOLIN, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.RED_KAOLIN, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.ORANGE_KAOLIN, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.YELLOW_KAOLIN, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.LIME_KAOLIN, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.GREEN_KAOLIN, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.CYAN_KAOLIN, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.LIGHT_BLUE_KAOLIN, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.BLUE_KAOLIN, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.PURPLE_KAOLIN, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.MAGENTA_KAOLIN, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.PINK_KAOLIN, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.KAOLIN_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.WHITE_KAOLIN_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.LIGHT_GRAY_KAOLIN_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.GRAY_KAOLIN_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.BLACK_KAOLIN_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.BROWN_KAOLIN_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.RED_KAOLIN_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.ORANGE_KAOLIN_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.YELLOW_KAOLIN_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.LIME_KAOLIN_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.GREEN_KAOLIN_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.CYAN_KAOLIN_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.LIGHT_BLUE_KAOLIN_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.BLUE_KAOLIN_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.PURPLE_KAOLIN_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.MAGENTA_KAOLIN_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.PINK_KAOLIN_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.KAOLIN_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.WHITE_KAOLIN_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.LIGHT_GRAY_KAOLIN_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.GRAY_KAOLIN_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.BLACK_KAOLIN_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.BROWN_KAOLIN_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.RED_KAOLIN_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.ORANGE_KAOLIN_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.YELLOW_KAOLIN_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.LIME_KAOLIN_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.GREEN_KAOLIN_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.CYAN_KAOLIN_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.LIGHT_BLUE_KAOLIN_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.BLUE_KAOLIN_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.PURPLE_KAOLIN_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.MAGENTA_KAOLIN_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.PINK_KAOLIN_SLAB, translationBuilder);

         generateBlockTranslations(HibiscusColoredBlocks.WHITE_CHALK, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.LIGHT_GRAY_CHALK, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.GRAY_CHALK, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.BLACK_CHALK, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.BROWN_CHALK, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.RED_CHALK, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.ORANGE_CHALK, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.YELLOW_CHALK, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.LIME_CHALK, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.GREEN_CHALK, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.CYAN_CHALK, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.LIGHT_BLUE_CHALK, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.BLUE_CHALK, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.PURPLE_CHALK, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.MAGENTA_CHALK, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.PINK_CHALK, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.WHITE_CHALK_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.LIGHT_GRAY_CHALK_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.GRAY_CHALK_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.BLACK_CHALK_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.BROWN_CHALK_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.RED_CHALK_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.ORANGE_CHALK_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.YELLOW_CHALK_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.LIME_CHALK_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.GREEN_CHALK_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.CYAN_CHALK_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.LIGHT_BLUE_CHALK_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.BLUE_CHALK_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.PURPLE_CHALK_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.MAGENTA_CHALK_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.PINK_CHALK_STAIRS, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.WHITE_CHALK_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.LIGHT_GRAY_CHALK_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.GRAY_CHALK_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.BLACK_CHALK_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.BROWN_CHALK_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.RED_CHALK_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.ORANGE_CHALK_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.YELLOW_CHALK_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.LIME_CHALK_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.GREEN_CHALK_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.CYAN_CHALK_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.LIGHT_BLUE_CHALK_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.BLUE_CHALK_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.PURPLE_CHALK_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.MAGENTA_CHALK_SLAB, translationBuilder);
         generateBlockTranslations(HibiscusColoredBlocks.PINK_CHALK_SLAB, translationBuilder);

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

      private void generateWoodRecipes(HashMap <String, Block[]> woods, HashMap <String, TagKey <Item>> tag, Consumer <FinishedRecipe> consumer) {
         for(String i : woods.keySet()) {
            Block[] woodType = woods.get(i);
            planksFromLogs(consumer, woodType[4], tag.get(i), 4);
            woodFromLogs(consumer, woodType[0], woodType[2]);
            woodFromLogs(consumer, woodType[1], woodType[3]);
            hangingSign(consumer, woodType[15], woodType[3]);
            woodenBoat(consumer, HibiscusBoatEntity.HibiscusBoat.getType(i).boat().asItem(), woodType[4]);
            chestBoat(
                    consumer,
                    HibiscusBoatEntity.HibiscusBoat.getType(i).chestBoat().asItem(),
                    HibiscusBoatEntity.HibiscusBoat.getType(i).boat().asItem()
            );
            BlockFamily family = familyBuilder(woodType[4]).button(woodType[12])
                    .fence(woodType[9])
                    .fenceGate(woodType[10])
                    .pressurePlate(woodType[11])
                    .sign(woodType[13], woodType[14])
                    .slab(woodType[6])
                    .stairs(woodType[5])
                    .door(woodType[7])
                    .trapdoor(woodType[8])
                    .recipeGroupPrefix("wooden")
                    .recipeUnlockedBy("has_planks")
                    .getFamily();
            generateRecipes(consumer, family);
         }
      }

      private void generateJoshuaWoodRecipes(TagKey <Item> tag, Consumer <FinishedRecipe> consumer) {
         planksFromLogs(consumer, HibiscusWoods.JOSHUA[2], tag, 2);
         hangingSign(consumer, HibiscusWoods.JOSHUA[13], HibiscusWoods.JOSHUA[1]);
         woodenBoat(consumer, HibiscusWoods.JOSHUA_BOAT, HibiscusWoods.JOSHUA[4]);
         chestBoat(consumer, HibiscusWoods.JOSHUA_CHEST_BOAT, HibiscusWoods.JOSHUA_BOAT);
         BlockFamily family = familyBuilder(HibiscusWoods.JOSHUA[2]).button(HibiscusWoods.JOSHUA[10])
                                                               .fence(HibiscusWoods.JOSHUA[7])
                                                               .fenceGate(HibiscusWoods.JOSHUA[8])
                                                               .pressurePlate(HibiscusWoods.JOSHUA[9])
                                                               .sign(HibiscusWoods.JOSHUA[11], HibiscusWoods.JOSHUA[12])
                                                               .slab(HibiscusWoods.JOSHUA[4])
                                                               .stairs(HibiscusWoods.JOSHUA[3])
                                                               .door(HibiscusWoods.JOSHUA[5])
                                                               .trapdoor(HibiscusWoods.JOSHUA[6])
                                                               .recipeGroupPrefix("wooden")
                                                               .recipeUnlockedBy("has_planks")
                                                               .getFamily();
         generateRecipes(consumer, family);
      }

      private void generateFlowerRecipes(Block block, Item dye, String group, int amount, Consumer <FinishedRecipe> consumer) {
         oneToOneConversionRecipe(consumer, dye, block, group, amount);
      }

      @Override public void buildRecipes(Consumer <FinishedRecipe> exporter) {
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
         oneToOneConversionRecipe(exporter, Items.PINK_DYE, LOTUS_FLOWER, "pink_dye", 1);
         threeByThreePacker(
                 exporter,
                 RecipeCategory.FOOD,
                 HibiscusBlocksAndItems.DESERT_TURNIP_BLOCK,
                 HibiscusBlocksAndItems.DESERT_TURNIP,
                 "desert_turnip"
         );
      }
   }

   public static class NatureSpiritItemTagGenerator extends FabricTagProvider.ItemTagProvider {

      public NatureSpiritItemTagGenerator(FabricDataOutput output, CompletableFuture <HolderLookup.Provider> completableFuture, @Nullable BlockTagProvider blockTagProvider) {
         super(output, completableFuture, blockTagProvider);
      }


      @Override protected void addTags(HolderLookup.Provider arg) {

         for(String i : HibiscusRegistryHelper.WoodHashMap.keySet()) {
            this.copy(blockLogTags.get(i), itemLogTags.get(i));
            tag(ItemTags.BOATS).add(HibiscusBoatEntity.HibiscusBoat.getType(i).boat().asItem());
            tag(ItemTags.CHEST_BOATS).add(HibiscusBoatEntity.HibiscusBoat.getType(i)
                    .chestBoat()
                    .asItem());
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

      public NatureSpiritBlockTagGenerator(FabricDataOutput output, CompletableFuture <HolderLookup.Provider> registriesFuture) {
         super(output, registriesFuture);
      }

      private void addWoodTags(HashMap <String, Block[]> woods, HashMap <String, TagKey <Block>> tag) {
         for(String i : woods.keySet()) {
            Block[] woodType = woods.get(i);
            tag(BlockTags.PLANKS).add(new Block[]{woodType[4]});
            tag(BlockTags.WOODEN_BUTTONS).add(new Block[]{woodType[12]});
            tag(BlockTags.WOODEN_DOORS).add(new Block[]{woodType[7]});
            tag(BlockTags.WOODEN_STAIRS).add(new Block[]{woodType[5]});
            tag(BlockTags.WOODEN_SLABS).add(new Block[]{woodType[6]});
            tag(BlockTags.WOODEN_FENCES).add(new Block[]{woodType[9]});
            tag(tag.get(i)).add(woodType[3], woodType[2], woodType[1], woodType[0]);
            tag(BlockTags.OVERWORLD_NATURAL_LOGS).add(new Block[]{woodType[2]});
            tag(BlockTags.LOGS_THAT_BURN).addTag(tag.get(i));
            tag(BlockTags.WOODEN_TRAPDOORS).add(new Block[]{woodType[8]});
            tag(BlockTags.STANDING_SIGNS).add(new Block[]{woodType[13]});
            tag(BlockTags.WALL_SIGNS).add(new Block[]{woodType[14]});
            tag(BlockTags.WOODEN_PRESSURE_PLATES).add(new Block[]{woodType[11]});
            tag(BlockTags.FENCE_GATES).add(new Block[]{woodType[10]});
            tag(BlockTags.CEILING_HANGING_SIGNS).add(woodType[15]);
            tag(BlockTags.WALL_HANGING_SIGNS).add(woodType[16]);

         }
      }

      private void addJoshuaWoodTags(TagKey <Block> tag) {
         tag(BlockTags.PLANKS).add(new Block[]{HibiscusWoods.JOSHUA[2]});
         tag(BlockTags.WOODEN_BUTTONS).add(new Block[]{HibiscusWoods.JOSHUA[10]});
         tag(BlockTags.WOODEN_DOORS).add(new Block[]{HibiscusWoods.JOSHUA[5]});
         tag(BlockTags.WOODEN_STAIRS).add(new Block[]{HibiscusWoods.JOSHUA[3]});
         tag(BlockTags.WOODEN_SLABS).add(new Block[]{HibiscusWoods.JOSHUA[4]});
         tag(BlockTags.WOODEN_FENCES).add(new Block[]{HibiscusWoods.JOSHUA[7]});
         tag(tag).add(HibiscusWoods.JOSHUA[1], HibiscusWoods.JOSHUA[0]);
         tag(BlockTags.OVERWORLD_NATURAL_LOGS).add(new Block[]{HibiscusWoods.JOSHUA[0]});
         tag(BlockTags.LOGS_THAT_BURN).addTag(tag);
         tag(BlockTags.WOODEN_TRAPDOORS).add(new Block[]{HibiscusWoods.JOSHUA[6]});
         tag(BlockTags.STANDING_SIGNS).add(new Block[]{HibiscusWoods.JOSHUA[11]});
         tag(BlockTags.WALL_SIGNS).add(new Block[]{HibiscusWoods.JOSHUA[12]});
         tag(BlockTags.WOODEN_PRESSURE_PLATES).add(new Block[]{HibiscusWoods.JOSHUA[9]});
         tag(BlockTags.FENCE_GATES).add(new Block[]{HibiscusWoods.JOSHUA[8]});
         tag(BlockTags.CEILING_HANGING_SIGNS).add(HibiscusWoods.JOSHUA[13]);
         tag(BlockTags.WALL_HANGING_SIGNS).add(HibiscusWoods.JOSHUA[14]);
      }

      private void addTreeTags(HashMap <String, Block[]> saplings, HashMap <String, Block> leaves) {
         for(String i : saplings.keySet()) {
            Block leavesType = leaves.get(i);
            Block[] saplingType = saplings.get(i);
            tag(BlockTags.MINEABLE_WITH_HOE).add(leavesType);
            tag(BlockTags.SAPLINGS).add(new Block[]{saplingType[0]});
            tag(BlockTags.FLOWER_POTS).add(new Block[]{saplingType[1]});
            tag(BlockTags.LEAVES).add(leavesType);
         }
      }

      private void addFlowerTags(Block block, Block flowerPot, Boolean isTall) {
         tag(BlockTags.FLOWER_POTS).add(new Block[]{flowerPot});

         if(isTall) {
            tag(BlockTags.TALL_FLOWERS).add(block);
         }
         else {
            tag(BlockTags.SMALL_FLOWERS).add(block);
         }

      }

      private void addFlowerTags(Block block, Boolean isTall) {

         if(isTall) {
            tag(BlockTags.TALL_FLOWERS).add(block);
         }
         else {
            tag(BlockTags.SMALL_FLOWERS).add(block);
         }

      }

      @Override protected void addTags(HolderLookup.Provider arg) {
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
         tag(BlockTags.WOODEN_DOORS).add(new Block[]{HibiscusWoods.FRAMED_SUGI_DOOR});
         tag(BlockTags.WOODEN_TRAPDOORS).add(new Block[]{HibiscusWoods.FRAMED_SUGI_TRAPDOOR});
         tag(BlockTags.CLIMBABLE).add(
                 HibiscusWoods.BLUE_WISTERIA_VINES_PLANT,
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
         tag(BlockTags.BEE_GROWABLES).add(
                 HibiscusWoods.BLUE_WISTERIA_VINES_PLANT,
                 HibiscusWoods.BLUE_WISTERIA_VINES,
                 HibiscusWoods.WHITE_WISTERIA_VINES,
                 HibiscusWoods.WHITE_WISTERIA_VINES_PLANT,
                 HibiscusWoods.PINK_WISTERIA_VINES,
                 HibiscusWoods.PINK_WISTERIA_VINES_PLANT,
                 HibiscusWoods.PURPLE_WISTERIA_VINES,
                 HibiscusWoods.PURPLE_WISTERIA_VINES_PLANT,
                 LOTUS_FLOWER
         );
         tag(BlockTags.CROPS).add(HibiscusBlocksAndItems.DESERT_TURNIP_STEM);
         tag(BlockTags.MAINTAINS_FARMLAND).add(HibiscusBlocksAndItems.DESERT_TURNIP_STEM);
         tag(BlockTags.MINEABLE_WITH_HOE).add(
                 HibiscusBlocksAndItems.SCORCHED_GRASS,
                 HibiscusBlocksAndItems.TALL_SCORCHED_GRASS,
                 SEDGE_GRASS,
                 TALL_SEDGE_GRASS,
                 LARGE_FLAXEN_FERN,
                 FLAXEN_FERN
         );
         tag(BlockTags.SWORD_EFFICIENT).add(
                 HibiscusBlocksAndItems.SCORCHED_GRASS,
                 HibiscusBlocksAndItems.TALL_SCORCHED_GRASS,
                 SEDGE_GRASS,
                 TALL_SEDGE_GRASS,
                 LARGE_FLAXEN_FERN,
                 FLAXEN_FERN,
                 SHIITAKE_MUSHROOM
         );
         tag(BlockTags.REPLACEABLE_BY_TREES).add(
                 HibiscusBlocksAndItems.SCORCHED_GRASS,
                 HibiscusBlocksAndItems.TALL_SCORCHED_GRASS,
                 SEDGE_GRASS,
                 TALL_SEDGE_GRASS,
                 LARGE_FLAXEN_FERN,
                 FLAXEN_FERN
         );
         tag(BlockTags.FLOWER_POTS).add(POTTED_FLAXEN_FERN, POTTED_SHIITAKE_MUSHROOM);
         tag(BlockTags.ENDERMAN_HOLDABLE).add(SHIITAKE_MUSHROOM);
      }
   }
}

