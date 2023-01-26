package net.hibiscus.naturespirit.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.blocks.HibiscusBlocks;
import net.hibiscus.naturespirit.items.HibiscusItemGroups;
import net.hibiscus.naturespirit.terrablender.HibiscusBiomes;
import net.hibiscus.naturespirit.world.feature.HibiscusConfiguredFeatures;
import net.hibiscus.naturespirit.world.feature.HibiscusPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.*;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootTable;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static net.minecraft.data.BlockFamilies.familyBuilder;
import static net.minecraft.data.models.BlockModelGenerators.*;

public class NatureSpiritDataGen implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(NatureSpiritWorldGenerator::new);
        pack.addProvider(NatureSpiritModelGenerator::new);
        pack.addProvider(NatureSpiritLangGenerator::new);
        pack.addProvider(NatureSpiritRecipeGenerator::new);
        NatureSpiritBlockTagGenerator blockTagProvider = pack.addProvider(NatureSpiritBlockTagGenerator::new);
        pack.addProvider((output, registries) -> new NatureSpiritItemTagGenerator(output, registries, blockTagProvider));
        System.out.println("Initialized Data Generator");
        registerWoodTypes();
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.CONFIGURED_FEATURE, HibiscusConfiguredFeatures::bootstrap);
        registryBuilder.add(Registries.PLACED_FEATURE, HibiscusPlacedFeatures::bootstrap);
        registryBuilder.add(Registries.BIOME, HibiscusBiomes::bootstrap);
        System.out.println("Built Registry");
    }


    public static Block [][] woodArrays = new Block[5][];
    public static Block [] leavesArrays = new Block[9];
    public static Block [][] saplingArrays = new Block[9][];
    public static TagKey <Block>[] blockLogTags = new TagKey[5];
    public static TagKey <Item>[] itemLogTags = new TagKey[5];
    private void registerWoodTypes () {
        woodArrays[0] = HibiscusBlocks.REDWOOD;
        woodArrays[1] = HibiscusBlocks.WISTERIA;
        woodArrays[2] = HibiscusBlocks.SAKURA;
        woodArrays[3] = HibiscusBlocks.FIR;
        woodArrays[4] = HibiscusBlocks.WILLOW;

        leavesArrays[0] = HibiscusBlocks.REDWOOD_LEAVES;
        leavesArrays[1] = HibiscusBlocks.WHITE_WISTERIA_LEAVES;
        leavesArrays[2] = HibiscusBlocks.BLUE_WISTERIA_LEAVES;
        leavesArrays[3] = HibiscusBlocks.PURPLE_WISTERIA_LEAVES;
        leavesArrays[4] = HibiscusBlocks.PINK_WISTERIA_LEAVES;
        leavesArrays[5] = HibiscusBlocks.PINK_SAKURA_LEAVES;
        leavesArrays[6] = HibiscusBlocks.WHITE_SAKURA_LEAVES;
        leavesArrays[7] = HibiscusBlocks.FIR_LEAVES;
        leavesArrays[8] = HibiscusBlocks.WILLOW_LEAVES;

        saplingArrays[0] = HibiscusBlocks.REDWOOD_SAPLING;
        saplingArrays[1] = HibiscusBlocks.WHITE_WISTERIA_SAPLING;
        saplingArrays[2] = HibiscusBlocks.BLUE_WISTERIA_SAPLING;
        saplingArrays[3] = HibiscusBlocks.PURPLE_WISTERIA_SAPLING;
        saplingArrays[4] = HibiscusBlocks.PINK_WISTERIA_SAPLING;
        saplingArrays[5] = HibiscusBlocks.PINK_SAKURA_SAPLING;
        saplingArrays[6] = HibiscusBlocks.WHITE_SAKURA_SAPLING;
        saplingArrays[7] = HibiscusBlocks.FIR_SAPLING;
        saplingArrays[8] = HibiscusBlocks.WILLOW_SAPLING;

        blockLogTags[0] = TagKey.create(Registries.BLOCK, new ResourceLocation(NatureSpirit.MOD_ID, "redwood_logs"));
        blockLogTags[1] = TagKey.create(Registries.BLOCK, new ResourceLocation(NatureSpirit.MOD_ID, "sakura_logs"));
        blockLogTags[2] = TagKey.create(Registries.BLOCK, new ResourceLocation(NatureSpirit.MOD_ID, "wisteria_logs"));
        blockLogTags[3] = TagKey.create(Registries.BLOCK, new ResourceLocation(NatureSpirit.MOD_ID, "fir_logs"));
        blockLogTags[4] = TagKey.create(Registries.BLOCK, new ResourceLocation(NatureSpirit.MOD_ID, "willow_logs"));

        itemLogTags[0] = TagKey.create(Registries.ITEM, new ResourceLocation(NatureSpirit.MOD_ID, "redwood_logs"));
        itemLogTags[1] = TagKey.create(Registries.ITEM, new ResourceLocation(NatureSpirit.MOD_ID, "sakura_logs"));
        itemLogTags[2] = TagKey.create(Registries.ITEM, new ResourceLocation(NatureSpirit.MOD_ID, "wisteria_logs"));
        itemLogTags[3] = TagKey.create(Registries.ITEM, new ResourceLocation(NatureSpirit.MOD_ID, "fir_logs"));
        itemLogTags[4] = TagKey.create(Registries.ITEM, new ResourceLocation(NatureSpirit.MOD_ID, "willow_logs"));
    }


    @Override
    public String getEffectiveModId() {
        return NatureSpirit.MOD_ID;
    }


    private static class NatureSpiritLootGenerator extends FabricBlockLootTableProvider {


        protected NatureSpiritLootGenerator(FabricDataOutput dataOutput) {
            super(dataOutput);
        }

        @Override
        public void generate() {
        }

        @Override
        public void accept(BiConsumer <ResourceLocation, LootTable.Builder> resourceLocationBuilderBiConsumer) {
        }
    }

    private static class NatureSpiritModelGenerator extends FabricModelProvider {

        public NatureSpiritModelGenerator(FabricDataOutput output) {
            super(output);
        }



        private void createWoodSlab(Block planks, Block slab, BlockModelGenerators blockStateModelGenerator) {
            ResourceLocation resourceLocation = ModelLocationUtils.getModelLocation(planks);
            TexturedModel texturedModel = TexturedModel.CUBE.get(planks);
            ResourceLocation resourceLocation2 = ModelTemplates.SLAB_BOTTOM.create(slab, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation resourceLocation3 = ModelTemplates.SLAB_TOP.create(slab, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            blockStateModelGenerator.blockStateOutput.accept(createSlab(slab, resourceLocation2, resourceLocation3, resourceLocation));
        }

        private void createWoodStairs(Block planks, Block stairs, BlockModelGenerators blockStateModelGenerator) {
            TexturedModel texturedModel = TexturedModel.CUBE.get(planks);
            ResourceLocation resourceLocation = ModelTemplates.STAIRS_INNER.create(stairs, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation resourceLocation2 = ModelTemplates.STAIRS_STRAIGHT.create(stairs, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation resourceLocation3 = ModelTemplates.STAIRS_OUTER.create(stairs, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            blockStateModelGenerator.blockStateOutput.accept(createStairs(stairs, resourceLocation, resourceLocation2, resourceLocation3));
            blockStateModelGenerator.delegateItemModel(stairs, resourceLocation2);
        }

        public void createWoodDoor(Block doorBlock, BlockModelGenerators blockStateModelGenerator) {
            TextureMapping textureMapping = TextureMapping.door(doorBlock);
            ResourceLocation resourceLocation = ModelTemplates.DOOR_BOTTOM_LEFT.create(doorBlock, textureMapping, blockStateModelGenerator.modelOutput);
            ResourceLocation resourceLocation2 = ModelTemplates.DOOR_BOTTOM_LEFT_OPEN.create(doorBlock, textureMapping, blockStateModelGenerator.modelOutput);
            ResourceLocation resourceLocation3 = ModelTemplates.DOOR_BOTTOM_RIGHT.create(doorBlock, textureMapping, blockStateModelGenerator.modelOutput);
            ResourceLocation resourceLocation4 = ModelTemplates.DOOR_BOTTOM_RIGHT_OPEN.create(doorBlock, textureMapping, blockStateModelGenerator.modelOutput);
            ResourceLocation resourceLocation5 = ModelTemplates.DOOR_TOP_LEFT.create(doorBlock, textureMapping, blockStateModelGenerator.modelOutput);
            ResourceLocation resourceLocation6 = ModelTemplates.DOOR_TOP_LEFT_OPEN.create(doorBlock, textureMapping, blockStateModelGenerator.modelOutput);
            ResourceLocation resourceLocation7 = ModelTemplates.DOOR_TOP_RIGHT.create(doorBlock, textureMapping, blockStateModelGenerator.modelOutput);
            ResourceLocation resourceLocation8 = ModelTemplates.DOOR_TOP_RIGHT_OPEN.create(doorBlock, textureMapping, blockStateModelGenerator.modelOutput);
            blockStateModelGenerator.createSimpleFlatItemModel(doorBlock.asItem());
            blockStateModelGenerator.blockStateOutput.accept(createDoor(doorBlock, resourceLocation, resourceLocation2, resourceLocation3, resourceLocation4, resourceLocation5, resourceLocation6, resourceLocation7, resourceLocation8));
        }

        public void createWoodTrapdoor(Block orientableTrapdoorBlock, BlockModelGenerators blockStateModelGenerators) {
            TextureMapping textureMapping = TextureMapping.defaultTexture(orientableTrapdoorBlock);
            ResourceLocation resourceLocation = ModelTemplates.ORIENTABLE_TRAPDOOR_TOP.create(orientableTrapdoorBlock, textureMapping, blockStateModelGenerators.modelOutput);
            ResourceLocation resourceLocation2 = ModelTemplates.ORIENTABLE_TRAPDOOR_BOTTOM.create(orientableTrapdoorBlock, textureMapping, blockStateModelGenerators.modelOutput);
            ResourceLocation resourceLocation3 = ModelTemplates.ORIENTABLE_TRAPDOOR_OPEN.create(orientableTrapdoorBlock, textureMapping, blockStateModelGenerators.modelOutput);
            blockStateModelGenerators.blockStateOutput.accept(createOrientableTrapdoor(orientableTrapdoorBlock, resourceLocation, resourceLocation2, resourceLocation3));
            blockStateModelGenerators.delegateItemModel(orientableTrapdoorBlock, resourceLocation2);
        }

        public void createWoodFenceGate(Block planks, Block fenceGateBlock, BlockModelGenerators blockStateModelGenerator) {
            TexturedModel texturedModel = TexturedModel.CUBE.get(planks);
            ResourceLocation resourceLocation = ModelTemplates.FENCE_GATE_OPEN.create(fenceGateBlock, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation resourceLocation2 = ModelTemplates.FENCE_GATE_CLOSED.create(fenceGateBlock, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation resourceLocation3 = ModelTemplates.FENCE_GATE_WALL_OPEN.create(fenceGateBlock, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation resourceLocation4 = ModelTemplates.FENCE_GATE_WALL_CLOSED.create(fenceGateBlock, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createFenceGate(fenceGateBlock, resourceLocation, resourceLocation2, resourceLocation3, resourceLocation4, true));
        }

        public void createWoodFence(Block planks, Block fenceBlock, BlockModelGenerators blockStateModelGenerators) {
            TexturedModel texturedModel = TexturedModel.CUBE.get(planks);
            ResourceLocation resourceLocation = ModelTemplates.FENCE_POST.create(fenceBlock, texturedModel.getMapping(), blockStateModelGenerators.modelOutput);
            ResourceLocation resourceLocation2 = ModelTemplates.FENCE_SIDE.create(fenceBlock, texturedModel.getMapping(), blockStateModelGenerators.modelOutput);
            blockStateModelGenerators.blockStateOutput.accept(BlockModelGenerators.createFence(fenceBlock, resourceLocation, resourceLocation2));
            ResourceLocation resourceLocation3 = ModelTemplates.FENCE_INVENTORY.create(fenceBlock, texturedModel.getMapping(), blockStateModelGenerators.modelOutput);
            blockStateModelGenerators.delegateItemModel(fenceBlock, resourceLocation3);
        }

        public void createWoodPressurePlate(Block planks, Block pressurePlateBlock, BlockModelGenerators blockStateModelGenerator) {
            TexturedModel texturedModel = TexturedModel.CUBE.get(planks);
            ResourceLocation resourceLocation = ModelTemplates.PRESSURE_PLATE_UP.create(pressurePlateBlock, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            ResourceLocation resourceLocation2 = ModelTemplates.PRESSURE_PLATE_DOWN.create(pressurePlateBlock, texturedModel.getMapping(), blockStateModelGenerator.modelOutput);
            blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createPressurePlate(pressurePlateBlock, resourceLocation, resourceLocation2));
        }

        public void createWoodSign(Block signBlock, Block wallSignBlock, BlockModelGenerators blockStateModelGenerator) {
            TextureMapping textureMapping = TextureMapping.defaultTexture(signBlock);
                ResourceLocation resourceLocation = ModelTemplates.PARTICLE_ONLY.create(signBlock, textureMapping, blockStateModelGenerator.modelOutput);
            blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(signBlock, resourceLocation));
            blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(wallSignBlock, resourceLocation));
            blockStateModelGenerator.createSimpleFlatItemModel(signBlock.asItem());
            blockStateModelGenerator.skipAutoItemBlock(wallSignBlock);
        }
        public void createWoodButton(Block planks, Block buttonBlock, BlockModelGenerators blockStateModelGenerators) {
            TexturedModel texturedModel = TexturedModel.CUBE.get(planks);
            ResourceLocation resourceLocation = ModelTemplates.BUTTON.create(buttonBlock, texturedModel.getMapping(), blockStateModelGenerators.modelOutput);
            ResourceLocation resourceLocation2 = ModelTemplates.BUTTON_PRESSED.create(buttonBlock, texturedModel.getMapping(), blockStateModelGenerators.modelOutput);
            blockStateModelGenerators.blockStateOutput.accept(BlockModelGenerators.createButton(buttonBlock, resourceLocation, resourceLocation2));
            ResourceLocation resourceLocation3 = ModelTemplates.BUTTON_INVENTORY.create(buttonBlock, texturedModel.getMapping(), blockStateModelGenerators.modelOutput);
            blockStateModelGenerators.delegateItemModel(buttonBlock, resourceLocation3);
        }

        private void generateWoodBlockStateModels (Block[][] ARRAY, BlockModelGenerators blockStateModelGenerator) {
            for (int i = 0; i < ARRAY.length; i++) {
                blockStateModelGenerator.woodProvider(ARRAY[i][2]).logWithHorizontal(ARRAY[i][2]).wood(ARRAY[i][0]);
                blockStateModelGenerator.woodProvider(ARRAY[i][3]).logWithHorizontal(ARRAY[i][3]).wood(ARRAY[i][1]);
                blockStateModelGenerator.createTrivialBlock(ARRAY[i][4], TexturedModel.CUBE);
                createWoodSlab(ARRAY[i][4], ARRAY[i][6], blockStateModelGenerator);
                createWoodStairs(ARRAY[i][4], ARRAY[i][5], blockStateModelGenerator);
                createWoodDoor(ARRAY[i][7], blockStateModelGenerator);
                createWoodTrapdoor(ARRAY[i][8], blockStateModelGenerator);
                createWoodFenceGate(ARRAY[i][4], ARRAY[i][10], blockStateModelGenerator);
                createWoodFence(ARRAY[i][4], ARRAY[i][9], blockStateModelGenerator);
                createWoodButton(ARRAY[i][4], ARRAY[i][12], blockStateModelGenerator);
                createWoodPressurePlate(ARRAY[i][4], ARRAY[i][11], blockStateModelGenerator);
                createWoodSign(ARRAY[i][13], ARRAY[i][14], blockStateModelGenerator);
            }
        }
        private void generateTreeBlockStateModels (Block[][] Sapling, Block[] Leaves, BlockModelGenerators blockStateModelGenerator) {
            for (int i = 0; i < Leaves.length; i++) {
                blockStateModelGenerator.createTrivialBlock(Leaves[i], TexturedModel.LEAVES);
                blockStateModelGenerator.createPlant(Sapling[i][0], Sapling[i][1], BlockModelGenerators.TintState.NOT_TINTED);
            }
        }


        private void generateFlowerBlockStateModels (Block block, Block block2, BlockModelGenerators blockStateModelGenerator) {
            blockStateModelGenerator.createPlant(block, block2, BlockModelGenerators.TintState.NOT_TINTED);
        }

        @Override
        public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
            generateWoodBlockStateModels(woodArrays, blockStateModelGenerator);
            generateTreeBlockStateModels(saplingArrays, leavesArrays, blockStateModelGenerator);
            generateFlowerBlockStateModels(HibiscusBlocks.HIBISCUS, HibiscusBlocks.POTTED_HIBISCUS, blockStateModelGenerator);
            blockStateModelGenerator.createDoublePlant(HibiscusBlocks.CARNATION, BlockModelGenerators.TintState.NOT_TINTED);
            blockStateModelGenerator.createDoublePlant(HibiscusBlocks.GARDENIA, BlockModelGenerators.TintState.NOT_TINTED);
            blockStateModelGenerator.createDoublePlant(HibiscusBlocks.SNAPDRAGON, BlockModelGenerators.TintState.NOT_TINTED);
        }

        @Override
        public void generateItemModels(ItemModelGenerators itemModelGenerator) {

        }
    }

    private static class NatureSpiritLangGenerator extends FabricLanguageProvider {

        protected NatureSpiritLangGenerator(FabricDataOutput dataOutput) {
            super(dataOutput);
        }

        public static String capitalizeString(String string) {
            char[] chars = string.toLowerCase().toCharArray();
            boolean found = false;
            for (int i = 0; i < chars.length; i++) {
                if (!found && Character.isLetter(chars[i])) {
                    chars[i] = Character.toUpperCase(chars[i]);
                    found = true;
                } else if (Character.isWhitespace(chars[i]) || chars[i]=='.' || chars[i]=='\'') {
                    found = false;
                }
            }
            return String.valueOf(chars);
        }

        private void generateWoodTranslations (Block[][] array, TranslationBuilder translationBuilder) {
            for (int i = 0; i < array.length; i++) {
                for (int g = 0; g < array[i].length - 1; g++) {

                    String temp = capitalizeString(array[i][g].toString()
                            .replace("Block{natures_spirit:", "")
                            .replace("_", " ")
                            .replace("}", "")
                    );

                    translationBuilder.add(array[i][g], temp);
                }
            }
        }

        private void generateTreeTranslations (Block[][] array, Block block[], TranslationBuilder translationBuilder) {
            for (int i = 0; i < array.length; i++) {
                for (int g = 0; g < array[i].length; g++) {

                    String temp = capitalizeString(array[i][g].toString()
                            .replace("Block{natures_spirit:", "")
                            .replace("_", " ")
                            .replace("}", "")
                    );

                    translationBuilder.add(array[i][g], temp);
                }
                String temp = capitalizeString(block[i].toString()
                        .replace("Block{natures_spirit:", "")
                        .replace("_", " ")
                        .replace("}", "")
                );
                translationBuilder.add(block[i], temp);
            }
        }

        private void generateBlockTranslations (Block block, TranslationBuilder translationBuilder) {
                String temp = capitalizeString(block.toString()
                        .replace("Block{natures_spirit:", "")
                        .replace("_", " ")
                        .replace("}", "")
                );
                translationBuilder.add(block, temp);
        }

        @Override
        public void generateTranslations(TranslationBuilder translationBuilder) {
            generateWoodTranslations(woodArrays, translationBuilder);
            generateTreeTranslations(saplingArrays, leavesArrays , translationBuilder);
            translationBuilder.add(HibiscusItemGroups.NatureSpiritItemGroup, "Nature's Spirit Blocks & Items");
            generateBlockTranslations(HibiscusBlocks.ANEMONE, translationBuilder);
            generateBlockTranslations(HibiscusBlocks.POTTED_ANEMONE, translationBuilder);
            generateBlockTranslations(HibiscusBlocks.LAVENDER, translationBuilder);
            generateBlockTranslations(HibiscusBlocks.BLUEBELL, translationBuilder);
            generateBlockTranslations(HibiscusBlocks.CARNATION, translationBuilder);
            generateBlockTranslations(HibiscusBlocks.HIBISCUS, translationBuilder);
            generateBlockTranslations(HibiscusBlocks.POTTED_HIBISCUS, translationBuilder);
            generateBlockTranslations(HibiscusBlocks.GARDENIA, translationBuilder);
            generateBlockTranslations(HibiscusBlocks.SNAPDRAGON, translationBuilder);
            generateBlockTranslations(HibiscusBlocks.BLOOMING_SAKURA_TRAPDOOR, translationBuilder);
            generateBlockTranslations(HibiscusBlocks.BLOOMING_SAKURA_DOOR, translationBuilder);
            generateBlockTranslations(HibiscusBlocks.FRAMED_SAKURA_DOOR, translationBuilder);
            generateBlockTranslations(HibiscusBlocks.FRAMED_SAKURA_TRAPDOOR, translationBuilder);
            generateBlockTranslations(HibiscusBlocks.PINK_WISTERIA_VINES, translationBuilder);
            generateBlockTranslations(HibiscusBlocks.BLUE_WISTERIA_VINES, translationBuilder);
            generateBlockTranslations(HibiscusBlocks.WHITE_WISTERIA_VINES, translationBuilder);
            generateBlockTranslations(HibiscusBlocks.PURPLE_WISTERIA_VINES, translationBuilder);
            generateBlockTranslations(HibiscusBlocks.WILLOW_VINES, translationBuilder);
            generateBlockTranslations(HibiscusBlocks.CATTAIL, translationBuilder);
        }
    }

    public static class NatureSpiritRecipeGenerator extends FabricRecipeProvider {
            public NatureSpiritRecipeGenerator(FabricDataOutput output) {
                super(output);
            }

            private  void generateWoodRecipes(Block[][] array, TagKey <Item>[] tag,Consumer <FinishedRecipe> consumer) {
                for (int i = 0; i < array.length ; i++) {
                    planksFromLogs(consumer, array[i][4], tag[i], 4);
                    woodFromLogs(consumer, array[i][0], array[i][2]);
                    woodFromLogs(consumer, array[i][1], array[i][3]);
                    BlockFamily family = familyBuilder(array[i][4]).button(array[i][12]).fence(array[i][9]).fenceGate(array[i][10]).pressurePlate(array[i][11]).sign(array[i][13], array[i][14]).slab(array[i][6]).stairs(array[i][5]).door(array[i][7]).trapdoor(array[i][8]).recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks").getFamily();
                    generateRecipes(consumer, family);
                }
            }

            private void generateFlowerRecipes (Block block, Item dye, String group, int amount, Consumer <FinishedRecipe> consumer) {
                oneToOneConversionRecipe(consumer, dye, block, group, amount);
            }

            @Override
            public void buildRecipes(Consumer <FinishedRecipe> exporter) {
                generateWoodRecipes(woodArrays, itemLogTags ,exporter);
                generateFlowerRecipes(HibiscusBlocks.ANEMONE, Items.PINK_DYE, "pink_dye",1, exporter);
                generateFlowerRecipes(HibiscusBlocks.LAVENDER, Items.PURPLE_DYE, "purple_dye",4, exporter);
                generateFlowerRecipes(HibiscusBlocks.BLUEBELL, Items.BLUE_DYE, "blue_dye",2, exporter);
                generateFlowerRecipes(HibiscusBlocks.CARNATION, Items.RED_DYE, "red_dye",2, exporter);
                generateFlowerRecipes(HibiscusBlocks.SNAPDRAGON, Items.PINK_DYE, "red_dye",2, exporter);
                generateFlowerRecipes(HibiscusBlocks.HIBISCUS, Items.RED_DYE, "red_dye",1, exporter);
                generateFlowerRecipes(HibiscusBlocks.GARDENIA, Items.WHITE_DYE, "white_dye",2, exporter);
            }
        }

    public static class NatureSpiritItemTagGenerator extends FabricTagProvider.ItemTagProvider  {

            public NatureSpiritItemTagGenerator(FabricDataOutput output, CompletableFuture <HolderLookup.Provider> completableFuture, @Nullable BlockTagProvider blockTagProvider) {
                super(output, completableFuture, blockTagProvider);
            }

            @Override
            protected void addTags(HolderLookup.Provider arg) {

                for (int i = 0; i < woodArrays.length; i++) {
                    this.copy(blockLogTags[i], itemLogTags[i]);
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
                this.copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);
                this.copy(BlockTags.TALL_FLOWERS, ItemTags.TALL_FLOWERS);
            }
        }

    public static class NatureSpiritBlockTagGenerator extends FabricTagProvider.BlockTagProvider  {

        public NatureSpiritBlockTagGenerator(FabricDataOutput output, CompletableFuture <HolderLookup.Provider> registriesFuture) {
            super(output, registriesFuture);
        }

        private void addWoodTags(Block[][] array, TagKey <Block>[] tag, HolderLookup.Provider arg) {
            for (int i = 0; i < array.length; i++) {
                getOrCreateTagBuilder(BlockTags.PLANKS).add(new Block[]{array[i][4]});
                getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS).add(new Block[]{array[i][12]});
                getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add(new Block[]{array[i][7]});
                getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS).add(new Block[]{array[i][5]});
                getOrCreateTagBuilder(BlockTags.WOODEN_SLABS).add(new Block[]{array[i][6]});
                getOrCreateTagBuilder(BlockTags.WOODEN_FENCES).add(new Block[]{array[i][9]});
                getOrCreateTagBuilder(tag[i]).add(array[i][3], array[i][2], array[i][1], array[i][0]);
                getOrCreateTagBuilder(BlockTags.OVERWORLD_NATURAL_LOGS).add(new Block[]{array[i][2]});
                getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN).addTag(tag[i]);
                getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add(new Block[]{array[i][8]});
                getOrCreateTagBuilder(BlockTags.STANDING_SIGNS).add(new Block[]{array[i][13]});
                getOrCreateTagBuilder(BlockTags.WALL_SIGNS).add(new Block[]{array[i][14]});
                getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(new Block[]{array[i][11]});
                getOrCreateTagBuilder(BlockTags.FENCE_GATES).add(new Block[]{array[i][10]});
            }
        }
        private void addTreeTags(Block[][] array, Block[] block, HolderLookup.Provider arg) {
            for (int i = 0; i < array.length; i++) {
                getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_HOE).add(block[i]);
                getOrCreateTagBuilder(BlockTags.SAPLINGS).add(new Block[]{array[i][0]});
                getOrCreateTagBuilder(BlockTags.FLOWER_POTS).add(new Block[]{array[i][1]});
                getOrCreateTagBuilder(BlockTags.LEAVES).add(block[i]);
            }
        }

        private void addFlowerTags(Block block, Block flowerPot, Boolean isTall, HolderLookup.Provider arg) {
            getOrCreateTagBuilder(BlockTags.FLOWER_POTS).add(new Block[]{flowerPot});

            if (isTall) {
                getOrCreateTagBuilder(BlockTags.TALL_FLOWERS).add(block);
            } else {
                getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS).add(block);
            }

        }

        private void addFlowerTags(Block block, Boolean isTall, HolderLookup.Provider arg) {

            if (isTall) {
                getOrCreateTagBuilder(BlockTags.TALL_FLOWERS).add(block);
            } else {
                getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS).add(block);
            }

        }

        @Override
        protected void addTags(HolderLookup.Provider arg) {
            addWoodTags(woodArrays, blockLogTags, arg);
            addTreeTags(saplingArrays, leavesArrays, arg);
            addFlowerTags(HibiscusBlocks.HIBISCUS, HibiscusBlocks.POTTED_HIBISCUS, false, arg);
            addFlowerTags(HibiscusBlocks.ANEMONE, HibiscusBlocks.POTTED_ANEMONE, false, arg);
            addFlowerTags(HibiscusBlocks.BLUEBELL, false, arg);
            addFlowerTags(HibiscusBlocks.LAVENDER, true, arg);
            addFlowerTags(HibiscusBlocks.CARNATION, true, arg);
            addFlowerTags(HibiscusBlocks.GARDENIA, true, arg);
            addFlowerTags(HibiscusBlocks.CATTAIL, true, arg);
            addFlowerTags(HibiscusBlocks.SNAPDRAGON, true, arg);
            getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add(new Block[]{HibiscusBlocks.FRAMED_SAKURA_DOOR});
            getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add(new Block[]{HibiscusBlocks.BLOOMING_SAKURA_DOOR});
            getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add(new Block[]{HibiscusBlocks.BLOOMING_SAKURA_TRAPDOOR});
            getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add(new Block[]{HibiscusBlocks.FRAMED_SAKURA_TRAPDOOR});
        }
    }
}

