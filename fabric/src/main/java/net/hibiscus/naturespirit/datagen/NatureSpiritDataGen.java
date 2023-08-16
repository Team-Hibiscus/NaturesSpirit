package net.hibiscus.naturespirit.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.blocks.DesertPlantBlock;
import net.hibiscus.naturespirit.blocks.HibiscusBlocks;
import net.hibiscus.naturespirit.util.HibiscusItemGroups;
import net.hibiscus.naturespirit.world.HibiscusConfiguredFeatures;
import net.hibiscus.naturespirit.world.HibiscusPlacedFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.client.*;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.*;
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
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static net.minecraft.data.BlockFamilies.familyBuilder;
import static net.minecraft.data.models.BlockModelGenerators.*;

public class NatureSpiritDataGen implements DataGeneratorEntrypoint {

   public static Block[][] woodArrays = new Block[8][];
   public static Block[] leavesArrays = new Block[12];
   public static Block[][] saplingArrays = new Block[12][];
   public static TagKey <Block>[] blockLogTags = new TagKey[8];
   public static TagKey <Item>[] itemLogTags = new TagKey[8];
   public static TagKey <Item> joshuaItemLogtag = TagKey.create(Registries.ITEM, new ResourceLocation(NatureSpirit.MOD_ID, "joshua_logs"));
   public static TagKey <Block> joshuaBlockLogtag = TagKey.create(Registries.BLOCK, new ResourceLocation(NatureSpirit.MOD_ID, "joshua_logs"));

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
      woodArrays[0] = HibiscusBlocks.REDWOOD;
      woodArrays[1] = HibiscusBlocks.WISTERIA;
      woodArrays[2] = HibiscusBlocks.SUGI;
      woodArrays[3] = HibiscusBlocks.FIR;
      woodArrays[4] = HibiscusBlocks.WILLOW;
      woodArrays[5] = HibiscusBlocks.ASPEN;
      woodArrays[6] = HibiscusBlocks.CYPRESS;
      woodArrays[7] = HibiscusBlocks.OLIVE;

      leavesArrays[0] = HibiscusBlocks.REDWOOD_LEAVES;
      leavesArrays[1] = HibiscusBlocks.WHITE_WISTERIA_LEAVES;
      leavesArrays[2] = HibiscusBlocks.BLUE_WISTERIA_LEAVES;
      leavesArrays[3] = HibiscusBlocks.PURPLE_WISTERIA_LEAVES;
      leavesArrays[4] = HibiscusBlocks.PINK_WISTERIA_LEAVES;
      leavesArrays[5] = HibiscusBlocks.SUGI_LEAVES;
      leavesArrays[6] = HibiscusBlocks.FIR_LEAVES;
      leavesArrays[7] = HibiscusBlocks.WILLOW_LEAVES;
      leavesArrays[8] = HibiscusBlocks.ASPEN_LEAVES;
      leavesArrays[9] = HibiscusBlocks.CYPRESS_LEAVES;
      leavesArrays[10] = HibiscusBlocks.OLIVE_LEAVES;
      leavesArrays[11] = HibiscusBlocks.JOSHUA_LEAVES;

      saplingArrays[0] = HibiscusBlocks.REDWOOD_SAPLING;
      saplingArrays[1] = HibiscusBlocks.WHITE_WISTERIA_SAPLING;
      saplingArrays[2] = HibiscusBlocks.BLUE_WISTERIA_SAPLING;
      saplingArrays[3] = HibiscusBlocks.PURPLE_WISTERIA_SAPLING;
      saplingArrays[4] = HibiscusBlocks.PINK_WISTERIA_SAPLING;
      saplingArrays[5] = HibiscusBlocks.SUGI_SAPLING;
      saplingArrays[6] = HibiscusBlocks.FIR_SAPLING;
      saplingArrays[7] = HibiscusBlocks.WILLOW_SAPLING;
      saplingArrays[8] = HibiscusBlocks.ASPEN_SAPLING;
      saplingArrays[9] = HibiscusBlocks.CYPRESS_SAPLING;
      saplingArrays[10] = HibiscusBlocks.OLIVE_SAPLING;
      saplingArrays[11] = HibiscusBlocks.JOSHUA_SAPLING;

      blockLogTags[0] = TagKey.create(Registries.BLOCK, new ResourceLocation(NatureSpirit.MOD_ID, "redwood_logs"));
      blockLogTags[1] = TagKey.create(Registries.BLOCK, new ResourceLocation(NatureSpirit.MOD_ID, "sugi_logs"));
      blockLogTags[2] = TagKey.create(Registries.BLOCK, new ResourceLocation(NatureSpirit.MOD_ID, "wisteria_logs"));
      blockLogTags[3] = TagKey.create(Registries.BLOCK, new ResourceLocation(NatureSpirit.MOD_ID, "fir_logs"));
      blockLogTags[4] = TagKey.create(Registries.BLOCK, new ResourceLocation(NatureSpirit.MOD_ID, "willow_logs"));
      blockLogTags[5] = TagKey.create(Registries.BLOCK, new ResourceLocation(NatureSpirit.MOD_ID, "aspen_logs"));
      blockLogTags[6] = TagKey.create(Registries.BLOCK, new ResourceLocation(NatureSpirit.MOD_ID, "cypress_logs"));
      blockLogTags[7] = TagKey.create(Registries.BLOCK, new ResourceLocation(NatureSpirit.MOD_ID, "olive_logs"));

      itemLogTags[0] = TagKey.create(Registries.ITEM, new ResourceLocation(NatureSpirit.MOD_ID, "redwood_logs"));
      itemLogTags[1] = TagKey.create(Registries.ITEM, new ResourceLocation(NatureSpirit.MOD_ID, "sugi_logs"));
      itemLogTags[2] = TagKey.create(Registries.ITEM, new ResourceLocation(NatureSpirit.MOD_ID, "wisteria_logs"));
      itemLogTags[3] = TagKey.create(Registries.ITEM, new ResourceLocation(NatureSpirit.MOD_ID, "fir_logs"));
      itemLogTags[4] = TagKey.create(Registries.ITEM, new ResourceLocation(NatureSpirit.MOD_ID, "willow_logs"));
      itemLogTags[5] = TagKey.create(Registries.ITEM, new ResourceLocation(NatureSpirit.MOD_ID, "aspen_logs"));
      itemLogTags[6] = TagKey.create(Registries.ITEM, new ResourceLocation(NatureSpirit.MOD_ID, "cypress_logs"));
      itemLogTags[7] = TagKey.create(Registries.ITEM, new ResourceLocation(NatureSpirit.MOD_ID, "olive_logs"));
   }


   @Override public String getEffectiveModId() {
      return NatureSpirit.MOD_ID;
   }


   private static class NatureSpiritBlockLootTableProvider extends FabricBlockLootTableProvider {
      private static final LootItemCondition.Builder WITH_SILK_TOUCH_OR_SHEARS = HAS_SHEARS.or(HAS_SILK_TOUCH);
      private static final LootItemCondition.Builder WITHOUT_SILK_TOUCH_NOR_SHEARS = WITH_SILK_TOUCH_OR_SHEARS.invert();
      private final Map <ResourceLocation, LootTable.Builder> map = new HashMap();

      protected NatureSpiritBlockLootTableProvider(FabricDataOutput dataOutput) {
         super(dataOutput);
      }

      private void addWoodTable(Block[][] array) {
         for(int i = 0; i < array.length; i++) {
            dropSelf(array[i][0]);
            dropSelf(array[i][1]);
            dropSelf(array[i][2]);
            dropSelf(array[i][3]);
            dropSelf(array[i][4]);
            dropSelf(array[i][12]);
            this.add(array[i][7], this::createDoorTable);
            dropSelf(array[i][5]);
            this.createSlabItemTable(array[i][6]);
            dropSelf(array[i][9]);
            dropSelf(array[i][2]);
            dropSelf(array[i][8]);
            dropSelf(array[i][13]);
            dropSelf(array[i][15]);
            dropSelf(array[i][11]);
            dropSelf(array[i][10]);
         }
      }

      private void addJoshuaWoodTable(Block[] array) {
            dropSelf(array[0]);
            dropSelf(array[1]);
            dropSelf(array[2]);
            dropSelf(array[10]);
            this.add(array[5], this::createDoorTable);
            dropSelf(array[3]);
            this.createSlabItemTable(array[4]);
            dropSelf(array[7]);
            dropSelf(array[6]);
            dropSelf(array[11]);
            dropSelf(array[13]);
            dropSelf(array[9]);
            dropSelf(array[8]);
      }

      public net.minecraft.world.level.storage.loot.LootTable.Builder blackOlivesDrop(Block leaves, Block drop, float... chance) {
         return this.createLeavesDrops(leaves, drop, chance).withPool(LootPool.lootPool()
                 .setRolls(ConstantValue.exactly(1.0F))
                 .when(HAS_NO_SHEARS_OR_SILK_TOUCH)
                 .add(((net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer.Builder <?>) this.applyExplosionCondition(leaves,
                         LootItem.lootTableItem(HibiscusBlocks.BLACK_OLIVES)
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
                         LootItem.lootTableItem(HibiscusBlocks.GREEN_OLIVES)
                 )).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE,
                         0.01F,
                         0.0111111114F,
                         0.0125F,
                         0.016666668F,
                         0.05F
                 ))));
      }

      private final float[] SAPLING_DROP_CHANCE_2 = new float[]{0.08333333336F, 0.1F, 0.133333F, 0.1625F};
      private void addTreeTable(Block[][] array, Block[] array2) {
         for(int i = 0; i < array.length; i++) {
            dropSelf(array[i][0]);
            dropPottedContents(array[i][1]);
            int finalI = i;
            if(i != 10 && i!= 11) {
               this.add(array2[i], (block) -> {
                  return this.createLeavesDrops(block, array[finalI][0], NORMAL_LEAVES_SAPLING_CHANCES);
               });
            }
            if(i == 10) {
               this.add(array2[i], (block) -> {
                  return this.greenOlivesDrop(block, array[finalI][0], NORMAL_LEAVES_SAPLING_CHANCES);
               });
            }
            if(i == 11) {
               this.add(array2[i], (block) -> {
                  return this.createLeavesDrops(block, array[finalI][0], SAPLING_DROP_CHANCE_2);
               });
            }
         }
      }

      private void addVinesTable(Block vine, Block vinePlant) {
         this.addNetherVinesDropTable(vine, vinePlant);
      }

      public static net.minecraft.world.level.storage.loot.LootTable.Builder createShearsOnlyDrop(ItemLike drop) {
         return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(HAS_SHEARS).add(LootItem.lootTableItem(drop)));
      }

      public net.minecraft.world.level.storage.loot.LootTable.Builder tallScorchedGrassDrops(Block tallGrass, Block grass) {
         net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer.Builder<?> builder = ((net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer.Builder)LootItem.lootTableItem(grass).apply(
                 SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))));
         return LootTable.lootTable().withPool(LootPool.lootPool().add(builder).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(tallGrass).setProperties(net.minecraft.advancements.critereon.StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER))).when(
                 LocationCheck.checkLocation(net.minecraft.advancements.critereon.LocationPredicate.Builder.location().setBlock(net.minecraft.advancements.critereon.BlockPredicate.Builder.block().of(new Block[]{tallGrass}).setProperties(net.minecraft.advancements.critereon.StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER).build()).build()), new BlockPos(0, 1, 0)))).withPool(LootPool.lootPool().add(builder).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(tallGrass).setProperties(net.minecraft.advancements.critereon.StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER))).when(LocationCheck.checkLocation(net.minecraft.advancements.critereon.LocationPredicate.Builder.location().setBlock(net.minecraft.advancements.critereon.BlockPredicate.Builder.block().of(new Block[]{tallGrass}).setProperties(net.minecraft.advancements.critereon.StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER).build()).build()), new BlockPos(0, -1, 0))));
      }

      @Override public void generate() {
         addWoodTable(woodArrays);
         addJoshuaWoodTable(HibiscusBlocks.JOSHUA);
         addTreeTable(saplingArrays, leavesArrays);

         addVinesTable(HibiscusBlocks.WHITE_WISTERIA_VINES, HibiscusBlocks.WHITE_WISTERIA_VINES_PLANT);
         addVinesTable(HibiscusBlocks.BLUE_WISTERIA_VINES, HibiscusBlocks.BLUE_WISTERIA_VINES_PLANT);
         addVinesTable(HibiscusBlocks.PURPLE_WISTERIA_VINES, HibiscusBlocks.PURPLE_WISTERIA_VINES_PLANT);
         addVinesTable(HibiscusBlocks.PINK_WISTERIA_VINES, HibiscusBlocks.PINK_WISTERIA_VINES_PLANT);
         addVinesTable(HibiscusBlocks.WILLOW_VINES, HibiscusBlocks.WILLOW_VINES_PLANT);

         this.add(HibiscusBlocks.CARNATION, (block) -> {
            return this.createSinglePropConditionTable(block, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER);
         });
         this.add(HibiscusBlocks.CATTAIL, (block) -> {
            return this.createSinglePropConditionTable(block, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER);
         });
         this.add(HibiscusBlocks.GARDENIA, (block) -> {
            return this.createSinglePropConditionTable(block, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER);
         });
         this.add(HibiscusBlocks.SNAPDRAGON, (block) -> {
            return this.createSinglePropConditionTable(block, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER);
         });
         this.add(HibiscusBlocks.MARIGOLD, (block) -> {
            return this.createSinglePropConditionTable(block, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER);
         });
         this.add(HibiscusBlocks.FOXGLOVE, (block) -> {
            return this.createSinglePropConditionTable(block, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER);
         });
         this.add(HibiscusBlocks.LAVENDER, (block) -> {
            return this.createSinglePropConditionTable(block, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER);
         });
         this.add(HibiscusBlocks.BLEEDING_HEART, (block) -> {
            return this.createSinglePropConditionTable(block, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER);
         });
         this.add(HibiscusBlocks.TIGER_LILY, (block) -> {
            return this.createSinglePropConditionTable(block, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER);
         });

         this.dropSelf(HibiscusBlocks.ANEMONE);
         dropPottedContents(HibiscusBlocks.POTTED_ANEMONE);
         this.dropSelf(HibiscusBlocks.HIBISCUS);
         dropPottedContents(HibiscusBlocks.POTTED_HIBISCUS);
         this.dropSelf(HibiscusBlocks.BLUEBELL);
         this.dropSelf(HibiscusBlocks.TIGER_LILY);
         this.dropSelf(HibiscusBlocks.PURPLE_WILDFLOWER);
         this.dropSelf(HibiscusBlocks.YELLOW_WILDFLOWER);


         this.add(HibiscusBlocks.FRAMED_SUGI_DOOR, this::createDoorTable);
         this.dropSelf(HibiscusBlocks.FRAMED_SUGI_TRAPDOOR);
         this.dropSelf(HibiscusBlocks.SANDY_SOIL);
         this.dropSelf(HibiscusBlocks.KAOLIN);
         this.dropSelf(HibiscusBlocks.WHITE_KAOLIN);
         this.dropSelf(HibiscusBlocks.LIGHT_GRAY_KAOLIN);
         this.dropSelf(HibiscusBlocks.GRAY_KAOLIN);
         this.dropSelf(HibiscusBlocks.BLACK_KAOLIN);
         this.dropSelf(HibiscusBlocks.BROWN_KAOLIN);
         this.dropSelf(HibiscusBlocks.RED_KAOLIN);
         this.dropSelf(HibiscusBlocks.ORANGE_KAOLIN);
         this.dropSelf(HibiscusBlocks.YELLOW_KAOLIN);
         this.dropSelf(HibiscusBlocks.LIME_KAOLIN);
         this.dropSelf(HibiscusBlocks.GREEN_KAOLIN);
         this.dropSelf(HibiscusBlocks.CYAN_KAOLIN);
         this.dropSelf(HibiscusBlocks.LIGHT_BLUE_KAOLIN);
         this.dropSelf(HibiscusBlocks.BLUE_KAOLIN);
         this.dropSelf(HibiscusBlocks.PURPLE_KAOLIN);
         this.dropSelf(HibiscusBlocks.MAGENTA_KAOLIN);
         this.dropSelf(HibiscusBlocks.PINK_KAOLIN);
         this.createSlabItemTable(HibiscusBlocks.KAOLIN_SLAB);
         this.createSlabItemTable(HibiscusBlocks.WHITE_KAOLIN_SLAB);
         this.createSlabItemTable(HibiscusBlocks.LIGHT_GRAY_KAOLIN_SLAB);
         this.createSlabItemTable(HibiscusBlocks.GRAY_KAOLIN_SLAB);
         this.createSlabItemTable(HibiscusBlocks.BLACK_KAOLIN_SLAB);
         this.createSlabItemTable(HibiscusBlocks.BROWN_KAOLIN_SLAB);
         this.createSlabItemTable(HibiscusBlocks.RED_KAOLIN_SLAB);
         this.createSlabItemTable(HibiscusBlocks.ORANGE_KAOLIN_SLAB);
         this.createSlabItemTable(HibiscusBlocks.YELLOW_KAOLIN_SLAB);
         this.createSlabItemTable(HibiscusBlocks.LIME_KAOLIN_SLAB);
         this.createSlabItemTable(HibiscusBlocks.GREEN_KAOLIN_SLAB);
         this.createSlabItemTable(HibiscusBlocks.CYAN_KAOLIN_SLAB);
         this.createSlabItemTable(HibiscusBlocks.LIGHT_BLUE_KAOLIN_SLAB);
         this.createSlabItemTable(HibiscusBlocks.BLUE_KAOLIN_SLAB);
         this.createSlabItemTable(HibiscusBlocks.PURPLE_KAOLIN_SLAB);
         this.createSlabItemTable(HibiscusBlocks.MAGENTA_KAOLIN_SLAB);
         this.createSlabItemTable(HibiscusBlocks.PINK_KAOLIN_SLAB);
         this.dropSelf(HibiscusBlocks.KAOLIN_STAIRS);
         this.dropSelf(HibiscusBlocks.WHITE_KAOLIN_STAIRS);
         this.dropSelf(HibiscusBlocks.LIGHT_GRAY_KAOLIN_STAIRS);
         this.dropSelf(HibiscusBlocks.GRAY_KAOLIN_STAIRS);
         this.dropSelf(HibiscusBlocks.BLACK_KAOLIN_STAIRS);
         this.dropSelf(HibiscusBlocks.BROWN_KAOLIN_STAIRS);
         this.dropSelf(HibiscusBlocks.RED_KAOLIN_STAIRS);
         this.dropSelf(HibiscusBlocks.ORANGE_KAOLIN_STAIRS);
         this.dropSelf(HibiscusBlocks.YELLOW_KAOLIN_STAIRS);
         this.dropSelf(HibiscusBlocks.LIME_KAOLIN_STAIRS);
         this.dropSelf(HibiscusBlocks.GREEN_KAOLIN_STAIRS);
         this.dropSelf(HibiscusBlocks.CYAN_KAOLIN_STAIRS);
         this.dropSelf(HibiscusBlocks.LIGHT_BLUE_KAOLIN_STAIRS);
         this.dropSelf(HibiscusBlocks.BLUE_KAOLIN_STAIRS);
         this.dropSelf(HibiscusBlocks.PURPLE_KAOLIN_STAIRS);
         this.dropSelf(HibiscusBlocks.MAGENTA_KAOLIN_STAIRS);
         this.dropSelf(HibiscusBlocks.PINK_KAOLIN_STAIRS);

         this.dropSelf(HibiscusBlocks.DESERT_TURNIP_ROOT_BLOCK);

         createShearsOnlyDrop(HibiscusBlocks.SCORCHED_GRASS);
         tallScorchedGrassDrops(HibiscusBlocks.TALL_SCORCHED_GRASS, HibiscusBlocks.SCORCHED_GRASS);

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
      private static ModelTemplate block(String parent, String variant, TextureSlot... requiredTextureKeys) {
         return new ModelTemplate(Optional.of(new ResourceLocation("natures_spirit", "block/" + parent)), Optional.of(variant), requiredTextureKeys);
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

      private void generateWoodBlockStateModels(Block[][] ARRAY, BlockModelGenerators blockStateModelGenerator) {
         for(int i = 0; i < ARRAY.length; i++) {
            blockStateModelGenerator.woodProvider(ARRAY[i][2]).logWithHorizontal(ARRAY[i][2]).wood(ARRAY[i][0]);
            blockStateModelGenerator.woodProvider(ARRAY[i][3]).logWithHorizontal(ARRAY[i][3]).wood(ARRAY[i][1]);
            blockStateModelGenerator.createTrivialBlock(ARRAY[i][4], TexturedModel.CUBE);
            createSlab(ARRAY[i][4], ARRAY[i][6], blockStateModelGenerator);
            createStairs(ARRAY[i][4], ARRAY[i][5], blockStateModelGenerator);
            createWoodDoor(ARRAY[i][7], blockStateModelGenerator);
            createWoodTrapdoor(ARRAY[i][8], blockStateModelGenerator);
            createWoodFenceGate(ARRAY[i][4], ARRAY[i][10], blockStateModelGenerator);
            createWoodFence(ARRAY[i][4], ARRAY[i][9], blockStateModelGenerator);
            createWoodButton(ARRAY[i][4], ARRAY[i][12], blockStateModelGenerator);
            createWoodPressurePlate(ARRAY[i][4], ARRAY[i][11], blockStateModelGenerator);
            createWoodSign(ARRAY[i][13], ARRAY[i][14], blockStateModelGenerator);
            createHangingSign(ARRAY[i][3], ARRAY[i][15], ARRAY[i][16], blockStateModelGenerator);
         }
      }

      private void generateJoshuaWoodBlockStateModels(Block[] ARRAY, BlockModelGenerators blockStateModelGenerator) {
            blockStateModelGenerator.createTrivialBlock(ARRAY[2], TexturedModel.CUBE);
            createSlab(ARRAY[2], ARRAY[4], blockStateModelGenerator);
            createStairs(ARRAY[2], ARRAY[3], blockStateModelGenerator);
            createWoodDoor(ARRAY[5], blockStateModelGenerator);
            createWoodTrapdoor(ARRAY[6], blockStateModelGenerator);
            createWoodFenceGate(ARRAY[2], ARRAY[8], blockStateModelGenerator);
            createWoodFence(ARRAY[2], ARRAY[7], blockStateModelGenerator);
            createWoodButton(ARRAY[2], ARRAY[10], blockStateModelGenerator);
            createWoodPressurePlate(ARRAY[2], ARRAY[9], blockStateModelGenerator);
            createWoodSign(ARRAY[11], ARRAY[12], blockStateModelGenerator);
            createHangingSign(ARRAY[1], ARRAY[13], ARRAY[14], blockStateModelGenerator);
      }

      private void generateTreeBlockStateModels(Block[][] Sapling, Block[] Leaves, BlockModelGenerators blockStateModelGenerator) {
         for(int i = 0; i < Leaves.length; i++) {
            blockStateModelGenerator.createTrivialBlock(Leaves[i], TexturedModel.LEAVES);
            blockStateModelGenerator.createPlant(Sapling[i][0], Sapling[i][1], TintState.NOT_TINTED);
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

      @Override public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
         generateWoodBlockStateModels(woodArrays, blockStateModelGenerator);
         generateJoshuaWoodBlockStateModels(HibiscusBlocks.JOSHUA, blockStateModelGenerator);
         generateTreeBlockStateModels(saplingArrays, leavesArrays, blockStateModelGenerator);
         generateFlowerBlockStateModels(HibiscusBlocks.HIBISCUS,
                 HibiscusBlocks.POTTED_HIBISCUS,
                 blockStateModelGenerator
         );
         blockStateModelGenerator.createCropBlock(HibiscusBlocks.DESERT_TURNIP_STEM, DesertPlantBlock.AGE, 0, 1, 2, 3, 4, 5, 6, 7);
         blockStateModelGenerator.createDoublePlant(HibiscusBlocks.CARNATION, TintState.NOT_TINTED);
         blockStateModelGenerator.createDoublePlant(HibiscusBlocks.GARDENIA, TintState.NOT_TINTED);
         blockStateModelGenerator.createDoublePlant(HibiscusBlocks.SNAPDRAGON, TintState.NOT_TINTED);
         blockStateModelGenerator.createDoublePlant(HibiscusBlocks.MARIGOLD, TintState.NOT_TINTED);
         blockStateModelGenerator.createDoublePlant(HibiscusBlocks.FOXGLOVE, TintState.NOT_TINTED);
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

         blockStateModelGenerator.createTrivialBlock(HibiscusBlocks.KAOLIN, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusBlocks.WHITE_KAOLIN, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusBlocks.LIGHT_GRAY_KAOLIN, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusBlocks.GRAY_KAOLIN, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusBlocks.BLACK_KAOLIN, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusBlocks.BROWN_KAOLIN, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusBlocks.RED_KAOLIN, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusBlocks.ORANGE_KAOLIN, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusBlocks.YELLOW_KAOLIN, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusBlocks.LIME_KAOLIN, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusBlocks.GREEN_KAOLIN, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusBlocks.CYAN_KAOLIN, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusBlocks.LIGHT_BLUE_KAOLIN, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusBlocks.BLUE_KAOLIN, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusBlocks.PURPLE_KAOLIN, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusBlocks.MAGENTA_KAOLIN, TexturedModel.CUBE);
         blockStateModelGenerator.createTrivialBlock(HibiscusBlocks.PINK_KAOLIN, TexturedModel.CUBE);

         blockStateModelGenerator.createRotatedPillarWithHorizontalVariant(HibiscusBlocks.DESERT_TURNIP_ROOT_BLOCK,
                 TexturedModel.COLUMN_ALT,
                 TexturedModel.COLUMN_HORIZONTAL_ALT
         );

         //            generatePorcelainPotCrossModels(Blocks.AIR, HibiscusBlocks.WHITE_PORCELAIN_POT, "white_porcelain_pot",
         //            blockStateModelGenerator);
         //            generatePorcelainPotCrossModels(HibiscusBlocks.HIBISCUS, HibiscusBlocks
         //            .WHITE_PORCELAIN_POTTED_HIBISCUS, "white_porcelain_pot", blockStateModelGenerator);
         //            generateDoubleTallPorcelainPotCrossModels(Blocks.AIR, HibiscusBlocks.GREEN_PORCELAIN_POT,
         //            "green_porcelain_pot", blockStateModelGenerator);
         //            generateDoubleTallPorcelainPotCrossModels(HibiscusBlocks.HIBISCUS, HibiscusBlocks
         //            .GREEN_PORCELAIN_POTTED_HIBISCUS, "green_porcelain_pot", blockStateModelGenerator);
      }

      @Override public void generateItemModels(ItemModelGenerators itemModelGenerator) {
         itemModelGenerator.generateFlatItem(HibiscusBlocks.GREEN_OLIVES, ModelTemplates.FLAT_ITEM);
         itemModelGenerator.generateFlatItem(HibiscusBlocks.BLACK_OLIVES, ModelTemplates.FLAT_ITEM);
         itemModelGenerator.generateFlatItem(HibiscusBlocks.DESERT_TURNIP, ModelTemplates.FLAT_ITEM);
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

      private void generateWoodTranslations(Block[][] array, TranslationBuilder translationBuilder) {
         for(Block[] blocks : array) {
            for(int g = 0; g < blocks.length; g++) {
               if(!(g == 14 || g == 16)) {
                  generateBlockTranslations(blocks[g], translationBuilder);
               }
            }
         }
      }

      private void generateJoshuaTranslations(Block[] array, TranslationBuilder translationBuilder) {
         for(int g = 0; g < array.length; g++) {
            if(!(g == 12 || g == 14)) {
               generateBlockTranslations(array[g], translationBuilder);
            }
         }
      }

      private void generateTreeTranslations(Block[][] array, Block[] block, TranslationBuilder translationBuilder) {
         for(int i = 0; i < array.length; i++) {
            for(int g = 0; g < array[i].length; g++) {

               String temp = capitalizeString(array[i][g].toString()
                       .replace("Block{natures_spirit:", "")
                       .replace("_", " ")
                       .replace("}", ""));

               translationBuilder.add(array[i][g], temp);
            }
            String temp = capitalizeString(block[i].toString()
                    .replace("Block{natures_spirit:", "")
                    .replace("_", " ")
                    .replace("}", ""));
            translationBuilder.add(block[i], temp);
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
         generateWoodTranslations(woodArrays, translationBuilder);
         generateJoshuaTranslations(HibiscusBlocks.JOSHUA, translationBuilder);
         generateTreeTranslations(saplingArrays, leavesArrays, translationBuilder);
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

      private void generateWoodRecipes(Block[][] array, TagKey <Item>[] tag, Consumer <FinishedRecipe> consumer) {
         for(int i = 0; i < array.length; i++) {
            planksFromLogs(consumer, array[i][4], tag[i], 4);
            woodFromLogs(consumer, array[i][0], array[i][2]);
            woodFromLogs(consumer, array[i][1], array[i][3]);
            hangingSign(consumer, array[i][15], array[i][3]);
            BlockFamily family = familyBuilder(array[i][4]).button(array[i][12])
                    .fence(array[i][9])
                    .fenceGate(array[i][10])
                    .pressurePlate(array[i][11])
                    .sign(array[i][13], array[i][14])
                    .slab(array[i][6])
                    .stairs(array[i][5])
                    .door(array[i][7])
                    .trapdoor(array[i][8])
                    .recipeGroupPrefix("wooden")
                    .recipeUnlockedBy("has_planks")
                    .getFamily();
            generateRecipes(consumer, family);
         }
      }
      private void generateJoshuaWoodRecipes(Block[] array, TagKey <Item> tag, Consumer <FinishedRecipe> consumer) {
            planksFromLogs(consumer, array[2], tag, 2);
            hangingSign(consumer, array[13], array[1]);
            BlockFamily family = familyBuilder(array[2]).button(array[10])
                    .fence(array[7])
                    .fenceGate(array[8])
                    .pressurePlate(array[9])
                    .sign(array[11], array[12])
                    .slab(array[4])
                    .stairs(array[3])
                    .door(array[5])
                    .trapdoor(array[6])
                    .recipeGroupPrefix("wooden")
                    .recipeUnlockedBy("has_planks")
                    .getFamily();
            generateRecipes(consumer, family);
      }

      private void generateFlowerRecipes(Block block, Item dye, String group, int amount, Consumer <FinishedRecipe> consumer) {
         oneToOneConversionRecipe(consumer, dye, block, group, amount);
      }

      @Override public void buildRecipes(Consumer <FinishedRecipe> exporter) {
         generateWoodRecipes(woodArrays, itemLogTags, exporter);
         generateJoshuaWoodRecipes(HibiscusBlocks.JOSHUA, joshuaItemLogtag, exporter);
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
         threeByThreePacker(exporter, RecipeCategory.FOOD, HibiscusBlocks.DESERT_TURNIP_BLOCK, HibiscusBlocks.DESERT_TURNIP, "desert_turnip");
      }
   }

   public static class NatureSpiritItemTagGenerator extends FabricTagProvider.ItemTagProvider {

      public NatureSpiritItemTagGenerator(FabricDataOutput output, CompletableFuture <HolderLookup.Provider> completableFuture, @Nullable BlockTagProvider blockTagProvider) {
         super(output, completableFuture, blockTagProvider);
      }

      @Override protected void addTags(HolderLookup.Provider arg) {

         for(int i = 0; i < woodArrays.length; i++) {
            this.copy(blockLogTags[i], itemLogTags[i]);
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

      private void addWoodTags(Block[][] array, TagKey <Block>[] tag, HolderLookup.Provider arg) {
         for(int i = 0; i < array.length; i++) {
            tag(BlockTags.PLANKS).add(new Block[]{array[i][4]});
            tag(BlockTags.WOODEN_BUTTONS).add(new Block[]{array[i][12]});
            tag(BlockTags.WOODEN_DOORS).add(new Block[]{array[i][7]});
            tag(BlockTags.WOODEN_STAIRS).add(new Block[]{array[i][5]});
            tag(BlockTags.WOODEN_SLABS).add(new Block[]{array[i][6]});
            tag(BlockTags.WOODEN_FENCES).add(new Block[]{array[i][9]});
            tag(tag[i]).add(array[i][3], array[i][2], array[i][1], array[i][0]);
            tag(BlockTags.OVERWORLD_NATURAL_LOGS).add(new Block[]{array[i][2]});
            tag(BlockTags.LOGS_THAT_BURN).addTag(tag[i]);
            tag(BlockTags.WOODEN_TRAPDOORS).add(new Block[]{array[i][8]});
            tag(BlockTags.STANDING_SIGNS).add(new Block[]{array[i][13]});
            tag(BlockTags.WALL_SIGNS).add(new Block[]{array[i][14]});
            tag(BlockTags.WOODEN_PRESSURE_PLATES).add(new Block[]{array[i][11]});
            tag(BlockTags.FENCE_GATES).add(new Block[]{array[i][10]});
            tag(BlockTags.CEILING_HANGING_SIGNS).add(array[i][15]);
            tag(BlockTags.WALL_HANGING_SIGNS).add(array[i][16]);

         }
      }

      private void addJoshuaWoodTags(Block[] array, TagKey <Block> tag, HolderLookup.Provider arg) {
            tag(BlockTags.PLANKS).add(new Block[]{array[2]});
            tag(BlockTags.WOODEN_BUTTONS).add(new Block[]{array[10]});
            tag(BlockTags.WOODEN_DOORS).add(new Block[]{array[5]});
            tag(BlockTags.WOODEN_STAIRS).add(new Block[]{array[3]});
            tag(BlockTags.WOODEN_SLABS).add(new Block[]{array[4]});
            tag(BlockTags.WOODEN_FENCES).add(new Block[]{array[7]});
            tag(tag).add(array[1], array[0]);
            tag(BlockTags.OVERWORLD_NATURAL_LOGS).add(new Block[]{array[0]});
            tag(BlockTags.LOGS_THAT_BURN).addTag(tag);
            tag(BlockTags.WOODEN_TRAPDOORS).add(new Block[]{array[6]});
            tag(BlockTags.STANDING_SIGNS).add(new Block[]{array[11]});
            tag(BlockTags.WALL_SIGNS).add(new Block[]{array[12]});
            tag(BlockTags.WOODEN_PRESSURE_PLATES).add(new Block[]{array[9]});
            tag(BlockTags.FENCE_GATES).add(new Block[]{array[8]});
            tag(BlockTags.CEILING_HANGING_SIGNS).add(array[13]);
            tag(BlockTags.WALL_HANGING_SIGNS).add(array[14]);
      }

      private void addTreeTags(Block[][] array, Block[] block, HolderLookup.Provider arg) {
         for(int i = 0; i < array.length; i++) {
            tag(BlockTags.MINEABLE_WITH_HOE).add(block[i]);
            tag(BlockTags.SAPLINGS).add(new Block[]{array[i][0]});
            tag(BlockTags.FLOWER_POTS).add(new Block[]{array[i][1]});
            tag(BlockTags.LEAVES).add(block[i]);
         }
      }

      private void addFlowerTags(Block block, Block flowerPot, Boolean isTall, HolderLookup.Provider arg) {
         tag(BlockTags.FLOWER_POTS).add(new Block[]{flowerPot});

         if(isTall) {
            tag(BlockTags.TALL_FLOWERS).add(block);
         }
         else {
            tag(BlockTags.SMALL_FLOWERS).add(block);
         }

      }

      private void addFlowerTags(Block block, Boolean isTall, HolderLookup.Provider arg) {

         if(isTall) {
            tag(BlockTags.TALL_FLOWERS).add(block);
         }
         else {
            tag(BlockTags.SMALL_FLOWERS).add(block);
         }

      }

      @Override protected void addTags(HolderLookup.Provider arg) {
         addWoodTags(woodArrays, blockLogTags, arg);
         addJoshuaWoodTags(HibiscusBlocks.JOSHUA, joshuaBlockLogtag, arg);
         addTreeTags(saplingArrays, leavesArrays, arg);
         addFlowerTags(HibiscusBlocks.HIBISCUS, HibiscusBlocks.POTTED_HIBISCUS, false, arg);
         addFlowerTags(HibiscusBlocks.ANEMONE, HibiscusBlocks.POTTED_ANEMONE, false, arg);
         addFlowerTags(HibiscusBlocks.BLUEBELL, false, arg);
         addFlowerTags(HibiscusBlocks.TIGER_LILY, false, arg);
         addFlowerTags(HibiscusBlocks.PURPLE_WILDFLOWER, false, arg);
         addFlowerTags(HibiscusBlocks.YELLOW_WILDFLOWER, false, arg);
         addFlowerTags(HibiscusBlocks.LAVENDER, true, arg);
         addFlowerTags(HibiscusBlocks.BLEEDING_HEART, true, arg);
         addFlowerTags(HibiscusBlocks.CARNATION, true, arg);
         addFlowerTags(HibiscusBlocks.GARDENIA, true, arg);
         addFlowerTags(HibiscusBlocks.CATTAIL, true, arg);
         addFlowerTags(HibiscusBlocks.SNAPDRAGON, true, arg);
         addFlowerTags(HibiscusBlocks.MARIGOLD, true, arg);
         addFlowerTags(HibiscusBlocks.FOXGLOVE, true, arg);
         tag(BlockTags.WOODEN_DOORS).add(new Block[]{HibiscusBlocks.FRAMED_SUGI_DOOR});
         tag(BlockTags.WOODEN_TRAPDOORS).add(new Block[]{HibiscusBlocks.FRAMED_SUGI_TRAPDOOR});
         tag(BlockTags.CLIMBABLE).add(HibiscusBlocks.BLUE_WISTERIA_VINES_PLANT,
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
         tag(BlockTags.BEE_GROWABLES).add(HibiscusBlocks.BLUE_WISTERIA_VINES_PLANT,
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
         tag(BlockTags.CROPS).add(HibiscusBlocks.DESERT_TURNIP_STEM);
         tag(BlockTags.MAINTAINS_FARMLAND).add(HibiscusBlocks.DESERT_TURNIP_STEM);
         tag(BlockTags.MINEABLE_WITH_HOE).add(HibiscusBlocks.SCORCHED_GRASS, HibiscusBlocks.TALL_SCORCHED_GRASS);
         tag(BlockTags.SWORD_EFFICIENT).add(HibiscusBlocks.SCORCHED_GRASS, HibiscusBlocks.TALL_SCORCHED_GRASS);
         tag(BlockTags.REPLACEABLE_BY_TREES).add(HibiscusBlocks.SCORCHED_GRASS, HibiscusBlocks.TALL_SCORCHED_GRASS);
      }
   }
}

