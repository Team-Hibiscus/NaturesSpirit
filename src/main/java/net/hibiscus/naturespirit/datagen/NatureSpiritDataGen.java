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
import net.minecraft.block.Block;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.data.client.*;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static net.minecraft.data.client.BlockStateModelGenerator.*;
import static net.minecraft.data.family.BlockFamilies.register;

public class NatureSpiritDataGen implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
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

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, HibiscusConfiguredFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, HibiscusPlacedFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.BIOME, HibiscusBiomes::bootstrap);
        System.out.println("Built Registry");
    }


    public static Block [][] woodArrays = new Block[8][];
    public static Block [] leavesArrays = new Block[11];
    public static Block [][] saplingArrays = new Block[11][];
    public static TagKey <Block>[] blockLogTags = new TagKey[8];
    public static TagKey <Item>[] itemLogTags = new TagKey[8];
    private void registerWoodTypes () {
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

        blockLogTags[0] = TagKey.of(RegistryKeys.BLOCK, new Identifier(NatureSpirit.MOD_ID, "redwood_logs"));
        blockLogTags[1] = TagKey.of(RegistryKeys.BLOCK, new Identifier(NatureSpirit.MOD_ID, "sugi_logs"));
        blockLogTags[2] = TagKey.of(RegistryKeys.BLOCK, new Identifier(NatureSpirit.MOD_ID, "wisteria_logs"));
        blockLogTags[3] = TagKey.of(RegistryKeys.BLOCK, new Identifier(NatureSpirit.MOD_ID, "fir_logs"));
        blockLogTags[4] = TagKey.of(RegistryKeys.BLOCK, new Identifier(NatureSpirit.MOD_ID, "willow_logs"));
        blockLogTags[5] = TagKey.of(RegistryKeys.BLOCK, new Identifier(NatureSpirit.MOD_ID, "aspen_logs"));
        blockLogTags[6] = TagKey.of(RegistryKeys.BLOCK, new Identifier(NatureSpirit.MOD_ID, "cypress_logs"));
        blockLogTags[7] = TagKey.of(RegistryKeys.BLOCK, new Identifier(NatureSpirit.MOD_ID, "olive_logs"));

        itemLogTags[0] = TagKey.of(RegistryKeys.ITEM, new Identifier(NatureSpirit.MOD_ID, "redwood_logs"));
        itemLogTags[1] = TagKey.of(RegistryKeys.ITEM, new Identifier(NatureSpirit.MOD_ID, "sugi_logs"));
        itemLogTags[2] = TagKey.of(RegistryKeys.ITEM, new Identifier(NatureSpirit.MOD_ID, "wisteria_logs"));
        itemLogTags[3] = TagKey.of(RegistryKeys.ITEM, new Identifier(NatureSpirit.MOD_ID, "fir_logs"));
        itemLogTags[4] = TagKey.of(RegistryKeys.ITEM, new Identifier(NatureSpirit.MOD_ID, "willow_logs"));
        itemLogTags[5] = TagKey.of(RegistryKeys.ITEM, new Identifier(NatureSpirit.MOD_ID, "aspen_logs"));
        itemLogTags[6] = TagKey.of(RegistryKeys.ITEM, new Identifier(NatureSpirit.MOD_ID, "cypress_logs"));
        itemLogTags[7] = TagKey.of(RegistryKeys.ITEM, new Identifier(NatureSpirit.MOD_ID, "olive_logs"));
    }


    @Override
    public String getEffectiveModId() {
        return NatureSpirit.MOD_ID;
    }


    private static class NatureSpiritBlockLootTableProvider extends FabricBlockLootTableProvider {
        private static final LootCondition.Builder WITH_SILK_TOUCH_OR_SHEARS = WITH_SHEARS.or(WITH_SILK_TOUCH);
        private static final LootCondition.Builder WITHOUT_SILK_TOUCH_NOR_SHEARS = WITH_SILK_TOUCH_OR_SHEARS.invert();
        private final Map<Identifier, LootTable.Builder> map = new HashMap();

        protected NatureSpiritBlockLootTableProvider(FabricDataOutput dataOutput) {
            super(dataOutput);
        }

        private void addWoodTable(Block[][] array) {
            for (int i = 0; i < array.length; i++) {
                addDrop(array[i][0]);
                addDrop(array[i][1]);
                addDrop(array[i][2]);
                addDrop(array[i][3]);
                addDrop(array[i][4]);
                addDrop(array[i][12]);
                this.addDrop(array[i][7], this::doorDrops);
                addDrop(array[i][5]);
                addDrop(array[i][6]);
                addDrop(array[i][9]);
                addDrop(array[i][2]);
                addDrop(array[i][8]);
                addDrop(array[i][13]);
                addDrop(array[i][15]);
                addDrop(array[i][11]);
                addDrop(array[i][10]);
            }
        }

        public net.minecraft.loot.LootTable.Builder blackOlivesDrop(Block leaves, Block drop, float... chance) {
            return this.leavesDrops(leaves, drop, chance)
                    .pool(LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1.0F))
                            .conditionally(WITHOUT_SILK_TOUCH_NOR_SHEARS)
                            .with(((net.minecraft.loot.entry.LeafEntry.Builder <?>)this.addSurvivesExplosionCondition(leaves, ItemEntry.builder(HibiscusBlocks.BLACK_OLIVES)))
                                    .conditionally(TableBonusLootCondition.builder(Enchantments.FORTUNE, new float[]{0.01F, 0.0111111114F, 0.0125F, 0.016666668F, 0.05F}))));
        }
        public net.minecraft.loot.LootTable.Builder greenOlivesDrop(Block leaves, Block drop, float... chance) {
            return this.blackOlivesDrop(leaves, drop, chance)
                    .pool(LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1.0F))
                            .conditionally(WITHOUT_SILK_TOUCH_NOR_SHEARS)
                            .with(((net.minecraft.loot.entry.LeafEntry.Builder <?>)this.addSurvivesExplosionCondition(leaves, ItemEntry.builder(HibiscusBlocks.GREEN_OLIVES)))
                                    .conditionally(TableBonusLootCondition.builder(Enchantments.FORTUNE, new float[]{0.01F, 0.0111111114F, 0.0125F, 0.016666668F, 0.05F}))));
        }

        private void addTreeTable(Block[][] array, Block[] array2) {
            for (int i = 0; i < array.length; i++) {
                addDrop(array[i][0]);
                addPottedPlantDrops(array[i][1]);
                int finalI = i;
                if (i!=10) {
                    this.addDrop(array2[i], (block) -> {
                        return this.leavesDrops(block, array[finalI][0], SAPLING_DROP_CHANCE);
                    });
                }
                if (i==10) {
                    this.addDrop(array2[i], (block) -> {
                        return this.greenOlivesDrop(block, array[finalI][0], SAPLING_DROP_CHANCE);
                    });
                }
            }
        }

        private void addVinesTable(Block vine, Block vinePlant) {
            this.addVinePlantDrop(vine, vinePlant);
        }

        @Override
        public void generate() {
            addWoodTable(woodArrays);
            addTreeTable(saplingArrays, leavesArrays);

            addVinesTable(HibiscusBlocks.WHITE_WISTERIA_VINES, HibiscusBlocks.WHITE_WISTERIA_VINES_PLANT);
            addVinesTable(HibiscusBlocks.BLUE_WISTERIA_VINES, HibiscusBlocks.BLUE_WISTERIA_VINES_PLANT);
            addVinesTable(HibiscusBlocks.PURPLE_WISTERIA_VINES, HibiscusBlocks.PURPLE_WISTERIA_VINES_PLANT);
            addVinesTable(HibiscusBlocks.PINK_WISTERIA_VINES, HibiscusBlocks.PINK_WISTERIA_VINES_PLANT);
            addVinesTable(HibiscusBlocks.WILLOW_VINES, HibiscusBlocks.WILLOW_VINES_PLANT);

            this.addDrop(HibiscusBlocks.CARNATION, (block) -> {
                return this.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER);
            });
            this.addDrop(HibiscusBlocks.CATTAIL, (block) -> {
                return this.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER);
            });
            this.addDrop(HibiscusBlocks.GARDENIA, (block) -> {
                return this.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER);
            });
            this.addDrop(HibiscusBlocks.SNAPDRAGON, (block) -> {
                return this.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER);
            });
            this.addDrop(HibiscusBlocks.MARIGOLD, (block) -> {
                return this.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER);
            });
            this.addDrop(HibiscusBlocks.LAVENDER, (block) -> {
                return this.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER);
            });
            this.addDrop(HibiscusBlocks.BLEEDING_HEART, (block) -> {
                return this.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER);
            });
            this.addDrop(HibiscusBlocks.TIGER_LILY, (block) -> {
                return this.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER);
            });

            this.addDrop(HibiscusBlocks.ANEMONE);
            addPottedPlantDrops(HibiscusBlocks.POTTED_ANEMONE);
            this.addDrop(HibiscusBlocks.HIBISCUS);
            addPottedPlantDrops(HibiscusBlocks.POTTED_HIBISCUS);
            this.addDrop(HibiscusBlocks.BLUEBELL);
            this.addDrop(HibiscusBlocks.TIGER_LILY);
            this.addDrop(HibiscusBlocks.FRAMED_SUGI_DOOR, this::doorDrops);
            this.addDrop(HibiscusBlocks.FRAMED_SUGI_TRAPDOOR);
        }
    }

    private static class NatureSpiritModelGenerator extends FabricModelProvider {

        private static Model block(String parent, TextureKey... requiredTextureKeys) {
            return new Model(Optional.of(new Identifier("natures_spirit", "block/" + parent)), Optional.empty(), requiredTextureKeys);
        }

        private static final Model TALL_LARGE_CROSS = block("tall_large_cross", TextureKey.CROSS);
        private static final Model LARGE_CROSS = block("large_cross", TextureKey.CROSS);
        private static final Model TALL_CROSS = block("tall_cross", TextureKey.CROSS);
        private static final Model FLOWER_POT_TALL_CROSS = block("flower_pot_tall_cross", TextureKey.PLANT);
        private static final Model CROP = block("crop", TextureKey.CROP);

        public NatureSpiritModelGenerator(FabricDataOutput output) {
            super(output);
        }

        public static Identifier getId(Block block) {
            Identifier identifier = Registries.BLOCK.getId(block);
            return identifier.withPrefixedPath("block/");
        }

        private void createWoodSlab(Block planks, Block slab, BlockStateModelGenerator blockStateModelGenerator) {
            Identifier resourceLocation = ModelIds.getBlockModelId(planks);
            TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(planks);
            Identifier resourceLocation2 = Models.SLAB.upload(slab, texturedModel.getTextures(), blockStateModelGenerator.modelCollector);
            Identifier resourceLocation3 = Models.SLAB_TOP.upload(slab, texturedModel.getTextures(), blockStateModelGenerator.modelCollector);
            blockStateModelGenerator.blockStateCollector.accept(createSlabBlockState(slab, resourceLocation2, resourceLocation3, resourceLocation));
        }

        private void createWoodStairs(Block planks, Block stairs, BlockStateModelGenerator blockStateModelGenerator) {
            TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(planks);
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
            blockStateModelGenerator.blockStateCollector.accept(createDoorBlockState(doorBlock, resourceLocation, resourceLocation2, resourceLocation3, resourceLocation4, resourceLocation5, resourceLocation6, resourceLocation7, resourceLocation8));
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
            blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createFenceGateBlockState(fenceGateBlock, resourceLocation, resourceLocation2, resourceLocation3, resourceLocation4, true));
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

        private void generateWoodBlockStateModels (Block[][] ARRAY, BlockStateModelGenerator blockStateModelGenerator) {
            for (int i = 0; i < ARRAY.length; i++) {
                blockStateModelGenerator.registerLog(ARRAY[i][2]).log(ARRAY[i][2]).wood(ARRAY[i][0]);
                blockStateModelGenerator.registerLog(ARRAY[i][3]).log(ARRAY[i][3]).wood(ARRAY[i][1]);
                blockStateModelGenerator.registerSingleton(ARRAY[i][4], TexturedModel.CUBE_ALL);
                createWoodSlab(ARRAY[i][4], ARRAY[i][6], blockStateModelGenerator);
                createWoodStairs(ARRAY[i][4], ARRAY[i][5], blockStateModelGenerator);
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
        private void generateTreeBlockStateModels (Block[][] Sapling, Block[] Leaves, BlockStateModelGenerator blockStateModelGenerator) {
            for (int i = 0; i < Leaves.length; i++) {
                    blockStateModelGenerator.registerSingleton(Leaves[i], TexturedModel.LEAVES);
                blockStateModelGenerator.registerFlowerPotPlant(Sapling[i][0], Sapling[i][1], TintType.NOT_TINTED);
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
        private void generateFlowerBlockStateModels (Block block, Block block2, BlockStateModelGenerator blockStateModelGenerator) {
            blockStateModelGenerator.registerFlowerPotPlant(block, block2, TintType.NOT_TINTED);
        }
        private void generateFlowerPotBlockStateModels(Block block, Block flowerPot,BlockStateModelGenerator blockStateModelGenerators) {
            registerSpecificFlowerItemModel(block, blockStateModelGenerators);
            TextureMap textureMap1 = TextureMap.cross(block);
            registerTallCrossBlockState(block, textureMap1, blockStateModelGenerators);
            TextureMap textureMap = TextureMap.plant(block);
            Identifier identifier =  FLOWER_POT_TALL_CROSS.upload(flowerPot, textureMap, blockStateModelGenerators.modelCollector);
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

        @Override
        public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
            generateWoodBlockStateModels(woodArrays, blockStateModelGenerator);
            generateTreeBlockStateModels(saplingArrays, leavesArrays, blockStateModelGenerator);
            generateFlowerBlockStateModels(HibiscusBlocks.HIBISCUS, HibiscusBlocks.POTTED_HIBISCUS, blockStateModelGenerator);
            blockStateModelGenerator.registerDoubleBlock(HibiscusBlocks.CARNATION, TintType.NOT_TINTED);
            blockStateModelGenerator.registerDoubleBlock(HibiscusBlocks.GARDENIA, TintType.NOT_TINTED);
            blockStateModelGenerator.registerDoubleBlock(HibiscusBlocks.SNAPDRAGON, TintType.NOT_TINTED);
            blockStateModelGenerator.registerDoubleBlock(HibiscusBlocks.MARIGOLD, TintType.NOT_TINTED);
            generateTallLargeFlower(HibiscusBlocks.LAVENDER, blockStateModelGenerator);
            generateTallLargeFlower(HibiscusBlocks.BLEEDING_HEART, blockStateModelGenerator);
            generateLargeFlower(HibiscusBlocks.BLUEBELL, blockStateModelGenerator);
            generateLargeFlower(HibiscusBlocks.TIGER_LILY, blockStateModelGenerator);
            generateFlowerPotBlockStateModels(HibiscusBlocks.ANEMONE, HibiscusBlocks.POTTED_ANEMONE,blockStateModelGenerator);
            generateVineBlockStateModels(HibiscusBlocks.BLUE_WISTERIA_VINES, HibiscusBlocks.BLUE_WISTERIA_VINES_PLANT, blockStateModelGenerator);
            generateVineBlockStateModels(HibiscusBlocks.WHITE_WISTERIA_VINES, HibiscusBlocks.WHITE_WISTERIA_VINES_PLANT, blockStateModelGenerator);
            generateVineBlockStateModels(HibiscusBlocks.PURPLE_WISTERIA_VINES, HibiscusBlocks.PURPLE_WISTERIA_VINES_PLANT, blockStateModelGenerator);
            generateVineBlockStateModels(HibiscusBlocks.PINK_WISTERIA_VINES, HibiscusBlocks.PINK_WISTERIA_VINES_PLANT, blockStateModelGenerator);
            generateVineBlockStateModels(HibiscusBlocks.WILLOW_VINES, HibiscusBlocks.WILLOW_VINES_PLANT, blockStateModelGenerator);
        }

        @Override
        public void generateItemModels(ItemModelGenerator itemModelGenerator) {
            itemModelGenerator.register(HibiscusBlocks.GREEN_OLIVES, Models.GENERATED);
            itemModelGenerator.register(HibiscusBlocks.BLACK_OLIVES, Models.GENERATED);
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
            for (Block[] blocks : array) {
                for (int g = 0; g < blocks.length; g++) {
                    if (!(g == 14 || g == 16)) {
                        String temp = capitalizeString(blocks[g].toString()
                                .replace("Block{natures_spirit:", "")
                                .replace("_", " ")
                                .replace("}", "")
                        );
                        translationBuilder.add(blocks[g], temp);
                    }
                }
            }
        }

        private void generateTreeTranslations (Block[][] array, Block[] block, TranslationBuilder translationBuilder) {
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
            translationBuilder.add(HibiscusBlocks.GREEN_OLIVES, "Green Olives");
            translationBuilder.add(HibiscusBlocks.BLACK_OLIVES, "Black Olives");
            translationBuilder.add(NatureSpirit.EAT_PIZZA_SLICE, "Pizza Slices Eaten");
            generateBlockTranslations(HibiscusBlocks.ANEMONE, translationBuilder);
            generateBlockTranslations(HibiscusBlocks.POTTED_ANEMONE, translationBuilder);
            generateBlockTranslations(HibiscusBlocks.LAVENDER, translationBuilder);
            generateBlockTranslations(HibiscusBlocks.BLEEDING_HEART, translationBuilder);
            generateBlockTranslations(HibiscusBlocks.BLUEBELL, translationBuilder);
            generateBlockTranslations(HibiscusBlocks.TIGER_LILY, translationBuilder);
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

            private  void generateWoodRecipes(Block[][] array, TagKey <Item>[] tag,Consumer <RecipeJsonProvider> consumer) {
                for (int i = 0; i < array.length ; i++) {
                    offerPlanksRecipe(consumer, array[i][4], tag[i], 4);
                    offerBarkBlockRecipe(consumer, array[i][0], array[i][2]);
                    offerBarkBlockRecipe(consumer, array[i][1], array[i][3]);
                    offerHangingSignRecipe(consumer, array[i][15], array[i][3]);
                    BlockFamily family = register(array[i][4]).button(array[i][12]).fence(array[i][9]).fenceGate(array[i][10]).pressurePlate(array[i][11]).sign(array[i][13], array[i][14]).slab(array[i][6]).stairs(array[i][5]).door(array[i][7]).trapdoor(array[i][8]).group("wooden").unlockCriterionName("has_planks").build();
                    generateFamily(consumer, family);
                }
            }

            private void generateFlowerRecipes (Block block, Item dye, String group, int amount, Consumer <RecipeJsonProvider> consumer) {
                offerShapelessRecipe(consumer, dye, block, group, amount);
            }

            @Override
            public void generate(Consumer <RecipeJsonProvider> exporter) {
                generateWoodRecipes(woodArrays, itemLogTags ,exporter);
                generateFlowerRecipes(HibiscusBlocks.ANEMONE, Items.MAGENTA_DYE, "magenta_dye",1, exporter);
                generateFlowerRecipes(HibiscusBlocks.BLEEDING_HEART, Items.PINK_DYE, "pink_dye",4, exporter);
                generateFlowerRecipes(HibiscusBlocks.LAVENDER, Items.PURPLE_DYE, "purple_dye",4, exporter);
                generateFlowerRecipes(HibiscusBlocks.BLUEBELL, Items.BLUE_DYE, "blue_dye",2, exporter);
                generateFlowerRecipes(HibiscusBlocks.TIGER_LILY, Items.ORANGE_DYE, "orange_dye",2, exporter);
                generateFlowerRecipes(HibiscusBlocks.CARNATION, Items.RED_DYE, "red_dye",2, exporter);
                generateFlowerRecipes(HibiscusBlocks.SNAPDRAGON, Items.PINK_DYE, "pink_dye",2, exporter);
                generateFlowerRecipes(HibiscusBlocks.CATTAIL, Items.BROWN_DYE, "brown_dye",2, exporter);
                generateFlowerRecipes(HibiscusBlocks.MARIGOLD, Items.ORANGE_DYE, "orange_dye",2, exporter);
                generateFlowerRecipes(HibiscusBlocks.HIBISCUS, Items.RED_DYE, "red_dye",1, exporter);
                generateFlowerRecipes(HibiscusBlocks.GARDENIA, Items.WHITE_DYE, "white_dye",2, exporter);
            }
        }

    public static class NatureSpiritItemTagGenerator extends FabricTagProvider.ItemTagProvider  {

            public NatureSpiritItemTagGenerator(FabricDataOutput output, CompletableFuture <RegistryWrapper.WrapperLookup> completableFuture, @Nullable BlockTagProvider blockTagProvider) {
                super(output, completableFuture, blockTagProvider);
            }

            @Override
            protected void configure(RegistryWrapper.WrapperLookup arg) {

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
                this.copy(BlockTags.CEILING_HANGING_SIGNS, ItemTags.HANGING_SIGNS);
                this.copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);
                this.copy(BlockTags.TALL_FLOWERS, ItemTags.TALL_FLOWERS);
            }
        }

    public static class NatureSpiritBlockTagGenerator extends FabricTagProvider.BlockTagProvider  {

        public NatureSpiritBlockTagGenerator(FabricDataOutput output, CompletableFuture <RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, registriesFuture);
        }

        private void addWoodTags(Block[][] array, TagKey <Block>[] tag, RegistryWrapper.WrapperLookup arg) {
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
                getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS).add(array[i][15]);
                getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS).add(array[i][16]);

            }
        }
        private void addTreeTags(Block[][] array, Block[] block, RegistryWrapper.WrapperLookup arg) {
            for (int i = 0; i < array.length; i++) {
                getOrCreateTagBuilder(BlockTags.HOE_MINEABLE).add(block[i]);
                getOrCreateTagBuilder(BlockTags.SAPLINGS).add(new Block[]{array[i][0]});
                getOrCreateTagBuilder(BlockTags.FLOWER_POTS).add(new Block[]{array[i][1]});
                getOrCreateTagBuilder(BlockTags.LEAVES).add(block[i]);
            }
        }

        private void addFlowerTags(Block block, Block flowerPot, Boolean isTall, RegistryWrapper.WrapperLookup arg) {
            getOrCreateTagBuilder(BlockTags.FLOWER_POTS).add(new Block[]{flowerPot});

            if (isTall) {
                getOrCreateTagBuilder(BlockTags.TALL_FLOWERS).add(block);
            } else {
                getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS).add(block);
            }

        }

        private void addFlowerTags(Block block, Boolean isTall, RegistryWrapper.WrapperLookup arg) {

            if (isTall) {
                getOrCreateTagBuilder(BlockTags.TALL_FLOWERS).add(block);
            } else {
                getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS).add(block);
            }

        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup arg) {
            addWoodTags(woodArrays, blockLogTags, arg);
            addTreeTags(saplingArrays, leavesArrays, arg);
            addFlowerTags(HibiscusBlocks.HIBISCUS, HibiscusBlocks.POTTED_HIBISCUS, false, arg);
            addFlowerTags(HibiscusBlocks.ANEMONE, HibiscusBlocks.POTTED_ANEMONE, false, arg);
            addFlowerTags(HibiscusBlocks.BLUEBELL, false, arg);
            addFlowerTags(HibiscusBlocks.TIGER_LILY, false, arg);
            addFlowerTags(HibiscusBlocks.LAVENDER, true, arg);
            addFlowerTags(HibiscusBlocks.BLEEDING_HEART, true, arg);
            addFlowerTags(HibiscusBlocks.CARNATION, true, arg);
            addFlowerTags(HibiscusBlocks.GARDENIA, true, arg);
            addFlowerTags(HibiscusBlocks.CATTAIL, true, arg);
            addFlowerTags(HibiscusBlocks.SNAPDRAGON, true, arg);
            addFlowerTags(HibiscusBlocks.MARIGOLD, true, arg);
            getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add(new Block[]{HibiscusBlocks.FRAMED_SUGI_DOOR});
            getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add(new Block[]{HibiscusBlocks.FRAMED_SUGI_TRAPDOOR});
            getOrCreateTagBuilder(BlockTags.CLIMBABLE).add(HibiscusBlocks.BLUE_WISTERIA_VINES_PLANT, HibiscusBlocks.BLUE_WISTERIA_VINES, HibiscusBlocks.WHITE_WISTERIA_VINES, HibiscusBlocks.WHITE_WISTERIA_VINES_PLANT, HibiscusBlocks.PINK_WISTERIA_VINES, HibiscusBlocks.PINK_WISTERIA_VINES_PLANT, HibiscusBlocks.PURPLE_WISTERIA_VINES, HibiscusBlocks.PURPLE_WISTERIA_VINES_PLANT, HibiscusBlocks.WILLOW_VINES_PLANT, HibiscusBlocks.WILLOW_VINES);
            getOrCreateTagBuilder(BlockTags.BEE_GROWABLES).add(HibiscusBlocks.BLUE_WISTERIA_VINES_PLANT, HibiscusBlocks.BLUE_WISTERIA_VINES, HibiscusBlocks.WHITE_WISTERIA_VINES, HibiscusBlocks.WHITE_WISTERIA_VINES_PLANT, HibiscusBlocks.PINK_WISTERIA_VINES, HibiscusBlocks.PINK_WISTERIA_VINES_PLANT, HibiscusBlocks.PURPLE_WISTERIA_VINES, HibiscusBlocks.PURPLE_WISTERIA_VINES_PLANT, HibiscusBlocks.WILLOW_VINES_PLANT, HibiscusBlocks.WILLOW_VINES);
        }
    }
}

