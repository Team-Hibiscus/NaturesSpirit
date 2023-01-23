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
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootTable;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
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
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.CONFIGURED_FEATURE, HibiscusConfiguredFeatures::bootstrap);
        registryBuilder.add(Registries.PLACED_FEATURE, HibiscusPlacedFeatures::bootstrap);
        registryBuilder.add(Registries.BIOME, HibiscusBiomes::bootstrap);
        System.out.println("Built Registry");
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
            this.dropSelf(HibiscusBlocks.WISTERIA[2]);
            for (int i = 0; i < Arrays.stream(HibiscusBlocks.REDWOOD).count(); i++) {
                System.out.println("For Loop Block so far: " + HibiscusBlocks.REDWOOD[2].toString());
                this.dropSelf(HibiscusBlocks.REDWOOD[i]);
            }
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

        private void generateWoodBlockStateModels (Block[] ARRAY, BlockModelGenerators blockStateModelGenerator) {
            blockStateModelGenerator.woodProvider(ARRAY[2]).logWithHorizontal(ARRAY[2]).wood(ARRAY[0]);
            blockStateModelGenerator.woodProvider(ARRAY[3]).logWithHorizontal(ARRAY[3]).wood(ARRAY[1]);
            blockStateModelGenerator.createTrivialBlock(ARRAY[4], TexturedModel.CUBE);
            createWoodSlab(ARRAY[4], ARRAY[6], blockStateModelGenerator);
            createWoodStairs(ARRAY[4], ARRAY[5], blockStateModelGenerator);
            createWoodDoor(ARRAY[7], blockStateModelGenerator);
            createWoodTrapdoor(ARRAY[8], blockStateModelGenerator);
            createWoodFenceGate(ARRAY[4], ARRAY[10], blockStateModelGenerator);
            createWoodFence(ARRAY[4], ARRAY[9], blockStateModelGenerator);
            createWoodButton(ARRAY[4], ARRAY[12], blockStateModelGenerator);
            createWoodPressurePlate(ARRAY[4], ARRAY[11], blockStateModelGenerator);
            createWoodSign(ARRAY[13], ARRAY[14], blockStateModelGenerator);
        }
        private void generateTreeBlockStateModels (Block[] Sapling, Block Leaves, BlockModelGenerators blockStateModelGenerator) {
            blockStateModelGenerator.createTrivialBlock(Leaves, TexturedModel.LEAVES);
            blockStateModelGenerator.createPlant(Sapling[0], Sapling[1], BlockModelGenerators.TintState.NOT_TINTED);
        }

        @Override
        public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
            generateWoodBlockStateModels(HibiscusBlocks.REDWOOD, blockStateModelGenerator);
            generateTreeBlockStateModels( HibiscusBlocks.REDWOOD_SAPLING, HibiscusBlocks.REDWOOD_LEAVES, blockStateModelGenerator);
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

        private void generateWoodTranslations (Block[] array, TranslationBuilder translationBuilder) {
            for (int i = 0; i < array.length - 1; i++) {

                String temp = capitalizeString(array[i].toString()
                        .replace("Block{natures_spirit:", "")
                        .replace("_", " ")
                        .replace("}", "")
                );

                translationBuilder.add(array[i], temp);
            }
        }

        private void generateTreeTranslations (Block[] array, Block block, TranslationBuilder translationBuilder) {
            for (Block value : array) {

                String temp = capitalizeString(value.toString()
                        .replace("Block{natures_spirit:", "")
                        .replace("_", " ")
                        .replace("}", "")
                );

                translationBuilder.add(value, temp);
            }

            String temp2 = capitalizeString(block.toString()
                    .replace("Block{natures_spirit:", "")
                    .replace("_", " ")
                    .replace("}", "")
            );

            translationBuilder.add(block, temp2);
        }

        @Override
        public void generateTranslations(TranslationBuilder translationBuilder) {
            generateWoodTranslations(HibiscusBlocks.REDWOOD, translationBuilder);
            generateTreeTranslations(HibiscusBlocks.REDWOOD_SAPLING, HibiscusBlocks.REDWOOD_LEAVES , translationBuilder);
            translationBuilder.add(HibiscusItemGroups.NatureSpiritItemGroup, "Nature's Spirit Blocks & Items");
        }
    }

        public static class NatureSpiritRecipeGenerator extends FabricRecipeProvider {

            public static BlockFamily REDWOOD_PLANKS = familyBuilder(HibiscusBlocks.REDWOOD[4]).button(HibiscusBlocks.REDWOOD[12]).fence(HibiscusBlocks.REDWOOD[9]).fenceGate(HibiscusBlocks.REDWOOD[10]).pressurePlate(HibiscusBlocks.REDWOOD[11]).sign(HibiscusBlocks.REDWOOD[13], HibiscusBlocks.REDWOOD[14]).slab(HibiscusBlocks.REDWOOD[6]).stairs(HibiscusBlocks.REDWOOD[5]).door(HibiscusBlocks.REDWOOD[7]).trapdoor(HibiscusBlocks.REDWOOD[8]).recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks").getFamily();


            public NatureSpiritRecipeGenerator(FabricDataOutput output) {
                super(output);
            }

            public void generateWoodRecipes(Block[] array, TagKey <Item> tag,Consumer <FinishedRecipe> consumer) {
                planksFromLogs(consumer, array[4], tag, 4);
                woodFromLogs(consumer, array[0], array[2]);
                woodFromLogs(consumer, array[1], array[3]);
            }

            @Override
            public void buildRecipes(Consumer <FinishedRecipe> exporter) {
                generateRecipes(exporter, REDWOOD_PLANKS);
                generateWoodRecipes(HibiscusBlocks.REDWOOD, NatureSpiritItemTagGenerator.REDWOOD_LOGS ,exporter);
            }
        }

        public static class NatureSpiritItemTagGenerator extends FabricTagProvider.ItemTagProvider  {


            public static final TagKey <Item> REDWOOD_LOGS = TagKey.create(Registries.ITEM, new ResourceLocation(NatureSpirit.MOD_ID, "redwood_logs"));
            public static final TagKey <Item> SAKURA_LOGS = TagKey.create(Registries.ITEM, new ResourceLocation(NatureSpirit.MOD_ID, "sakura_logs"));
            public static final TagKey <Item> WISTERIA_LOGS = TagKey.create(Registries.ITEM, new ResourceLocation(NatureSpirit.MOD_ID, "wisteria_logs"));

            public NatureSpiritItemTagGenerator(FabricDataOutput output, CompletableFuture <HolderLookup.Provider> completableFuture, @Nullable BlockTagProvider blockTagProvider) {
                super(output, completableFuture, blockTagProvider);
            }

            @Override
            protected void addTags(HolderLookup.Provider arg) {
                this.copy(NatureSpiritBlockTagGenerator.REDWOOD_LOGS, REDWOOD_LOGS);
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
            }
        }
    public static class NatureSpiritBlockTagGenerator extends FabricTagProvider.BlockTagProvider  {


        public static final TagKey <Block> REDWOOD_LOGS = TagKey.create(Registries.BLOCK, new ResourceLocation(NatureSpirit.MOD_ID, "redwood_logs"));
        public static final TagKey <Block> SAKURA_LOGS = TagKey.create(Registries.BLOCK, new ResourceLocation(NatureSpirit.MOD_ID, "sakura_logs"));
        public static final TagKey <Block> WISTERIA_LOGS = TagKey.create(Registries.BLOCK, new ResourceLocation(NatureSpirit.MOD_ID, "wisteria_logs"));

        public NatureSpiritBlockTagGenerator(FabricDataOutput output, CompletableFuture <HolderLookup.Provider> registriesFuture) {
            super(output, registriesFuture);
        }

        private void addWoodTags(Block[] array, TagKey <Block> tag, HolderLookup.Provider arg) {
            getOrCreateTagBuilder(BlockTags.PLANKS).add(new Block[]{array[4]});
            getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS).add(new Block[]{array[12]});
            getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add(new Block[]{array[7]});
            getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS).add(new Block[]{array[5]});
            getOrCreateTagBuilder(BlockTags.WOODEN_SLABS).add(new Block[]{array[6]});
            getOrCreateTagBuilder(BlockTags.WOODEN_FENCES).add(new Block[]{array[9]});
            getOrCreateTagBuilder(tag).add(array[3], array[2], array[1], array[0]);
            getOrCreateTagBuilder(BlockTags.OVERWORLD_NATURAL_LOGS).add(new Block[]{array[2]});
            getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN).addTag(tag);
            getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add(new Block[]{array[8]});
            getOrCreateTagBuilder(BlockTags.STANDING_SIGNS).add(new Block[]{array[13]});
            getOrCreateTagBuilder(BlockTags.WALL_SIGNS).add(new Block[]{array[14]});
            getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(new Block[]{array[11]});
            getOrCreateTagBuilder(BlockTags.FENCE_GATES).add(new Block[]{array[10]});
        }
        private void addTreeTags(Block[] array, Block block, HolderLookup.Provider arg) {
            getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_HOE).add(block);
            getOrCreateTagBuilder(BlockTags.SAPLINGS).add(new Block[]{array[0]});
            getOrCreateTagBuilder(BlockTags.FLOWER_POTS).add(new Block[]{array[1]});
            getOrCreateTagBuilder(BlockTags.LEAVES).add(block);
        }

        @Override
        protected void addTags(HolderLookup.Provider arg) {
            addWoodTags(HibiscusBlocks.REDWOOD, REDWOOD_LOGS, arg);
            addTreeTags(HibiscusBlocks.REDWOOD_SAPLING, HibiscusBlocks.REDWOOD_LEAVES, arg);
        }
    }
}

