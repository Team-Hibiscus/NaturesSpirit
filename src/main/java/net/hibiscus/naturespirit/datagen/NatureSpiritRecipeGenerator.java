package net.hibiscus.naturespirit.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.hibiscus.naturespirit.registration.FlowerSet;
import net.hibiscus.naturespirit.registration.HibiscusRegistryHelper;
import net.hibiscus.naturespirit.registration.StoneSet;
import net.hibiscus.naturespirit.registration.WoodSet;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusColoredBlocks;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusMiscBlocks;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusWoods;
import net.hibiscus.naturespirit.util.HibiscusTags;
import net.minecraft.block.Blocks;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.function.Consumer;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;
import static net.hibiscus.naturespirit.registration.block_registration.HibiscusMiscBlocks.*;
import static net.minecraft.data.family.BlockFamilies.register;

public class NatureSpiritRecipeGenerator extends FabricRecipeProvider {
    public NatureSpiritRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    public static final BlockFamily PINK_SANDSTONE_FAMILY = register(HibiscusMiscBlocks.PINK_SANDSTONE).wall(PINK_SANDSTONE_WALL).stairs(PINK_SANDSTONE_STAIRS).slab(PINK_SANDSTONE_SLAB).chiseled(CHISELED_PINK_SANDSTONE).cut(CUT_PINK_SANDSTONE).noGenerateModels().noGenerateRecipes().build();
    public static final BlockFamily CUT_PINK_SANDSTONE_FAMILY = register(HibiscusMiscBlocks.CUT_PINK_SANDSTONE).slab(CUT_PINK_SANDSTONE_SLAB).noGenerateModels().build();
    public static final BlockFamily SMOOTH_PINK_SANDSTONE_FAMILY = register(HibiscusMiscBlocks.SMOOTH_PINK_SANDSTONE).slab(SMOOTH_PINK_SANDSTONE_SLAB).stairs(SMOOTH_PINK_SANDSTONE_STAIRS).noGenerateModels().build();


    public static void offerShapelessRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input, @Nullable String group, int outputCount) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, output, outputCount).input(input).group(group).criterion(RecipeProvider.hasItem(input), RecipeProvider.conditionsFromItem(input)).offerTo(exporter, new Identifier(MOD_ID, RecipeProvider.convertBetween(output, input)));
    }

    public static void offerStonecuttingRecipe(Consumer<RecipeJsonProvider> exporter, RecipeCategory category, ItemConvertible output, ItemConvertible input) {
        offerStonecuttingRecipe(exporter, category, output, input, 1);
    }

    public static void offerStonecuttingRecipe(Consumer<RecipeJsonProvider> exporter, RecipeCategory category, ItemConvertible output, ItemConvertible input, int count) {
        SingleItemRecipeJsonBuilder
                .createStonecutting(Ingredient.ofItems(input), category, output, count).criterion(RecipeProvider.hasItem(input), RecipeProvider.conditionsFromItem(input)).offerTo(exporter, new Identifier(MOD_ID, RecipeProvider.convertBetween(output, input) + "_stonecutting"));
    }

    private void generateWoodRecipes(HashMap<String, WoodSet> woods, Consumer<RecipeJsonProvider> consumer) {
        for (WoodSet woodSet : woods.values()) {
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
                offerMosaicRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, woodSet.getMosaic(), woodSet.getSlab());
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

    private void generateFlowerRecipes(HashMap<String, FlowerSet> flowers, Consumer<RecipeJsonProvider> consumer) {
        for (FlowerSet flowerSet : flowers.values()) {
            if (flowerSet.getDyeColor() != null)
                offerShapelessRecipe(consumer, flowerSet.getDyeColor(), flowerSet.getFlowerBlock(), flowerSet.getDyeColor().toString(), flowerSet.getDyeNumber());
        }
    }

    private void generateStoneRecipes(HashMap<String, StoneSet> stoones, Consumer<RecipeJsonProvider> exporter) {
        for (StoneSet stoneSet : stoones.values()) {
            generateFamily(exporter, stoneSet.getBaseFamily());
            generateFamily(exporter, stoneSet.getBrickFamily());
            generateFamily(exporter, stoneSet.getPolishedFamily());
            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, stoneSet.getBricks(), 4)
                    .input('S', stoneSet.getPolished()).pattern("SS")
                    .pattern("SS")
                    .criterion("has_polished_" + stoneSet.getName(), conditionsFromItem(stoneSet.getPolished()))
                    .offerTo(exporter);

            if (stoneSet.hasTiles()) {
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
            if (stoneSet.hasCobbled()) {
                generateFamily(exporter, stoneSet.getCobbledFamily());
                if (stoneSet.hasMossy()) {
                    generateFamily(exporter, stoneSet.getMossyCobbledFamily());
                    ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, stoneSet.getMossyCobbled())
                            .input(stoneSet.getCobbled()).input(Blocks.MOSS_BLOCK)
                            .group("mossy_cobblestone")
                            .criterion("has_moss_block", conditionsFromItem(Blocks.MOSS_BLOCK))
                            .offerTo(exporter, new Identifier(MOD_ID, convertBetween(stoneSet.getMossyCobbled(), Blocks.MOSS_BLOCK)));
                    ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, stoneSet.getMossyCobbled())
                            .input(stoneSet.getCobbled())
                            .input(Blocks.VINE)
                            .group("mossy_cobblestone")
                            .criterion("has_vine", conditionsFromItem(Blocks.VINE))
                            .offerTo(exporter, new Identifier(MOD_ID, convertBetween(stoneSet.getMossyCobbled(), Blocks.VINE)));
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
            if (stoneSet.hasMossy()) {
                generateFamily(exporter, stoneSet.getMossyBrickFamily());
                ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, stoneSet.getMossyBricks())
                        .input(stoneSet.getBricks())
                        .input(Blocks.VINE)
                        .group("mossy_stone_bricks")
                        .criterion("has_vine", conditionsFromItem(Blocks.VINE))
                        .offerTo(exporter, new Identifier(MOD_ID, convertBetween(stoneSet.getMossyBricks(), Blocks.VINE)));
                ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, stoneSet.getMossyBricks())
                        .input(stoneSet.getBricks())
                        .input(Blocks.MOSS_BLOCK)
                        .group("mossy_stone_bricks")
                        .criterion("has_moss_block", conditionsFromItem(Blocks.MOSS_BLOCK))
                        .offerTo(exporter, new Identifier(MOD_ID, convertBetween(stoneSet.getMossyBricks(), Blocks.MOSS_BLOCK)));
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

    public static void offer2x2CompactingTagRecipe(Consumer<RecipeJsonProvider> exporter, RecipeCategory category, ItemConvertible output, TagKey<Item> input) {
        ShapedRecipeJsonBuilder.create(category, output, 1).input('#', input).pattern("##").pattern("##").criterion("has_evergreen_leaves", conditionsFromTag(input)).offerTo(exporter);
    }

    @Override
    protected Identifier getRecipeIdentifier(Identifier identifier) {
        return new Identifier(MOD_ID, identifier.getPath());
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ALLUAUDIA_BUNDLE, ALLUAUDIA);
        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, STRIPPED_ALLUAUDIA_BUNDLE, STRIPPED_ALLUAUDIA);
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
        createStairsRecipe(HibiscusWoods.COCONUT_THATCH_STAIRS, Ingredient.ofItems(HibiscusWoods.COCONUT_THATCH)).criterion(hasItem(HibiscusWoods.COCONUT_THATCH), conditionsFromItem(HibiscusWoods.COCONUT_THATCH)).offerTo(exporter);
        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, HibiscusWoods.COCONUT_THATCH, HibiscusWoods.COCONUT.getLeaves());


        offerCarpetRecipe(exporter, HibiscusWoods.EVERGREEN_THATCH_CARPET, HibiscusWoods.EVERGREEN_THATCH);
        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, HibiscusWoods.EVERGREEN_THATCH_SLAB, HibiscusWoods.EVERGREEN_THATCH);
        createStairsRecipe(HibiscusWoods.EVERGREEN_THATCH_STAIRS, Ingredient.ofItems(HibiscusWoods.EVERGREEN_THATCH)).criterion(hasItem(HibiscusWoods.EVERGREEN_THATCH), conditionsFromItem(HibiscusWoods.EVERGREEN_THATCH)).offerTo(exporter);
        offer2x2CompactingTagRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, HibiscusWoods.EVERGREEN_THATCH, HibiscusTags.Items.EVERGREEN_LEAVES);

        offerCarpetRecipe(exporter, HibiscusWoods.XERIC_THATCH_CARPET, HibiscusWoods.XERIC_THATCH);
        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, HibiscusWoods.XERIC_THATCH_SLAB, HibiscusWoods.XERIC_THATCH);
        createStairsRecipe(HibiscusWoods.XERIC_THATCH_STAIRS, Ingredient.ofItems(HibiscusWoods.XERIC_THATCH)).criterion(hasItem(HibiscusWoods.XERIC_THATCH), conditionsFromItem(HibiscusWoods.XERIC_THATCH)).offerTo(exporter);
        offer2x2CompactingTagRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, HibiscusWoods.XERIC_THATCH, HibiscusTags.Items.XERIC_LEAVES);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HibiscusColoredBlocks.KAOLIN_BRICKS, 4).input(Character.valueOf('S'), HibiscusColoredBlocks.KAOLIN).pattern("SS").pattern("SS").criterion("has_kaolin", conditionsFromItem(HibiscusColoredBlocks.KAOLIN)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HibiscusColoredBlocks.WHITE_KAOLIN_BRICKS, 4).input(Character.valueOf('S'), HibiscusColoredBlocks.WHITE_KAOLIN).pattern("SS").pattern("SS").criterion("has_white_kaolin", conditionsFromItem(HibiscusColoredBlocks.WHITE_KAOLIN)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HibiscusColoredBlocks.LIGHT_GRAY_KAOLIN_BRICKS, 4).input(Character.valueOf('S'), HibiscusColoredBlocks.LIGHT_GRAY_KAOLIN).pattern("SS").pattern("SS").criterion("has_light_gray_kaolin", conditionsFromItem(HibiscusColoredBlocks.LIGHT_GRAY_KAOLIN)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HibiscusColoredBlocks.GRAY_KAOLIN_BRICKS, 4).input(Character.valueOf('S'), HibiscusColoredBlocks.GRAY_KAOLIN).pattern("SS").pattern("SS").criterion("has_gray_kaolin", conditionsFromItem(HibiscusColoredBlocks.GRAY_KAOLIN)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HibiscusColoredBlocks.BLACK_KAOLIN_BRICKS, 4).input(Character.valueOf('S'), HibiscusColoredBlocks.BLACK_KAOLIN).pattern("SS").pattern("SS").criterion("has_black_kaolin", conditionsFromItem(HibiscusColoredBlocks.BLACK_KAOLIN)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HibiscusColoredBlocks.BROWN_KAOLIN_BRICKS, 4).input(Character.valueOf('S'), HibiscusColoredBlocks.BROWN_KAOLIN).pattern("SS").pattern("SS").criterion("has_brown_kaolin", conditionsFromItem(HibiscusColoredBlocks.BROWN_KAOLIN)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HibiscusColoredBlocks.RED_KAOLIN_BRICKS, 4).input(Character.valueOf('S'), HibiscusColoredBlocks.RED_KAOLIN).pattern("SS").pattern("SS").criterion("has_red_kaolin", conditionsFromItem(HibiscusColoredBlocks.RED_KAOLIN)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HibiscusColoredBlocks.ORANGE_KAOLIN_BRICKS, 4).input(Character.valueOf('S'), HibiscusColoredBlocks.ORANGE_KAOLIN).pattern("SS").pattern("SS").criterion("has_orange_kaolin", conditionsFromItem(HibiscusColoredBlocks.ORANGE_KAOLIN)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HibiscusColoredBlocks.YELLOW_KAOLIN_BRICKS, 4).input(Character.valueOf('S'), HibiscusColoredBlocks.YELLOW_KAOLIN).pattern("SS").pattern("SS").criterion("has_yellow_kaolin", conditionsFromItem(HibiscusColoredBlocks.YELLOW_KAOLIN)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HibiscusColoredBlocks.LIME_KAOLIN_BRICKS, 4).input(Character.valueOf('S'), HibiscusColoredBlocks.LIME_KAOLIN).pattern("SS").pattern("SS").criterion("has_lime_kaolin", conditionsFromItem(HibiscusColoredBlocks.LIME_KAOLIN)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HibiscusColoredBlocks.GREEN_KAOLIN_BRICKS, 4).input(Character.valueOf('S'), HibiscusColoredBlocks.GREEN_KAOLIN).pattern("SS").pattern("SS").criterion("has_green_kaolin", conditionsFromItem(HibiscusColoredBlocks.GREEN_KAOLIN)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HibiscusColoredBlocks.CYAN_KAOLIN_BRICKS, 4).input(Character.valueOf('S'), HibiscusColoredBlocks.CYAN_KAOLIN).pattern("SS").pattern("SS").criterion("has_cyan_kaolin", conditionsFromItem(HibiscusColoredBlocks.CYAN_KAOLIN)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HibiscusColoredBlocks.LIGHT_BLUE_KAOLIN_BRICKS, 4).input(Character.valueOf('S'), HibiscusColoredBlocks.LIGHT_BLUE_KAOLIN).pattern("SS").pattern("SS").criterion("has_light_blue_kaolin", conditionsFromItem(HibiscusColoredBlocks.LIGHT_BLUE_KAOLIN)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HibiscusColoredBlocks.BLUE_KAOLIN_BRICKS, 4).input(Character.valueOf('S'), HibiscusColoredBlocks.BLUE_KAOLIN).pattern("SS").pattern("SS").criterion("has_blue_kaolin", conditionsFromItem(HibiscusColoredBlocks.BLUE_KAOLIN)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HibiscusColoredBlocks.PURPLE_KAOLIN_BRICKS, 4).input(Character.valueOf('S'), HibiscusColoredBlocks.PURPLE_KAOLIN).pattern("SS").pattern("SS").criterion("has_purple_kaolin", conditionsFromItem(HibiscusColoredBlocks.PURPLE_KAOLIN)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HibiscusColoredBlocks.MAGENTA_KAOLIN_BRICKS, 4).input(Character.valueOf('S'), HibiscusColoredBlocks.MAGENTA_KAOLIN).pattern("SS").pattern("SS").criterion("has_magenta_kaolin", conditionsFromItem(HibiscusColoredBlocks.MAGENTA_KAOLIN)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HibiscusColoredBlocks.PINK_KAOLIN_BRICKS, 4).input(Character.valueOf('S'), HibiscusColoredBlocks.PINK_KAOLIN).pattern("SS").pattern("SS").criterion("has_pink_kaolin", conditionsFromItem(HibiscusColoredBlocks.PINK_KAOLIN)).offerTo(exporter);


//         ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HibiscusColoredBlocksCompatability.MAROON_KAOLIN_BRICKS, 4).input(Character.valueOf('S'), HibiscusColoredBlocksCompatability.MAROON_KAOLIN).pattern("SS").pattern("SS").criterion("has_maroon_kaolin", conditionsFromItem(HibiscusColoredBlocksCompatability.MAROON_KAOLIN)).offerTo(exporter);
//         ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HibiscusColoredBlocksCompatability.ROSE_KAOLIN_BRICKS, 4).input(Character.valueOf('S'), HibiscusColoredBlocksCompatability.ROSE_KAOLIN).pattern("SS").pattern("SS").criterion("has_rose_kaolin", conditionsFromItem(HibiscusColoredBlocksCompatability.ROSE_KAOLIN)).offerTo(exporter);
//         ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HibiscusColoredBlocksCompatability.CORAL_KAOLIN_BRICKS, 4).input(Character.valueOf('S'), HibiscusColoredBlocksCompatability.CORAL_KAOLIN).pattern("SS").pattern("SS").criterion("has_coral_kaolin", conditionsFromItem(HibiscusColoredBlocksCompatability.CORAL_KAOLIN)).offerTo(exporter);
//         ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HibiscusColoredBlocksCompatability.GINGER_KAOLIN_BRICKS, 4).input(Character.valueOf('S'), HibiscusColoredBlocksCompatability.GINGER_KAOLIN).pattern("SS").pattern("SS").criterion("has_ginger_kaolin", conditionsFromItem(HibiscusColoredBlocksCompatability.GINGER_KAOLIN)).offerTo(exporter);
//         ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HibiscusColoredBlocksCompatability.TAN_KAOLIN_BRICKS, 4).input(Character.valueOf('S'), HibiscusColoredBlocksCompatability.TAN_KAOLIN).pattern("SS").pattern("SS").criterion("has_tan_kaolin", conditionsFromItem(HibiscusColoredBlocksCompatability.TAN_KAOLIN)).offerTo(exporter);
//         ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HibiscusColoredBlocksCompatability.BEIGE_KAOLIN_BRICKS, 4).input(Character.valueOf('S'), HibiscusColoredBlocksCompatability.BEIGE_KAOLIN).pattern("SS").pattern("SS").criterion("has_beige_kaolin", conditionsFromItem(HibiscusColoredBlocksCompatability.BEIGE_KAOLIN)).offerTo(exporter);
//         ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HibiscusColoredBlocksCompatability.AMBER_KAOLIN_BRICKS, 4).input(Character.valueOf('S'), HibiscusColoredBlocksCompatability.AMBER_KAOLIN).pattern("SS").pattern("SS").criterion("has_amber_kaolin", conditionsFromItem(HibiscusColoredBlocksCompatability.AMBER_KAOLIN)).offerTo(exporter);
//         ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HibiscusColoredBlocksCompatability.OLIVE_KAOLIN_BRICKS, 4).input(Character.valueOf('S'), HibiscusColoredBlocksCompatability.OLIVE_KAOLIN).pattern("SS").pattern("SS").criterion("has_olive_kaolin", conditionsFromItem(HibiscusColoredBlocksCompatability.OLIVE_KAOLIN)).offerTo(exporter);
//         ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HibiscusColoredBlocksCompatability.FOREST_KAOLIN_BRICKS, 4).input(Character.valueOf('S'), HibiscusColoredBlocksCompatability.FOREST_KAOLIN).pattern("SS").pattern("SS").criterion("has_forest_kaolin", conditionsFromItem(HibiscusColoredBlocksCompatability.FOREST_KAOLIN)).offerTo(exporter);
//         ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HibiscusColoredBlocksCompatability.VERDANT_KAOLIN_BRICKS, 4).input(Character.valueOf('S'), HibiscusColoredBlocksCompatability.VERDANT_KAOLIN).pattern("SS").pattern("SS").criterion("has_verdant_kaolin", conditionsFromItem(HibiscusColoredBlocksCompatability.VERDANT_KAOLIN)).offerTo(exporter);
//         ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HibiscusColoredBlocksCompatability.TEAL_KAOLIN_BRICKS, 4).input(Character.valueOf('S'), HibiscusColoredBlocksCompatability.TEAL_KAOLIN).pattern("SS").pattern("SS").criterion("has_teal_kaolin", conditionsFromItem(HibiscusColoredBlocksCompatability.TEAL_KAOLIN)).offerTo(exporter);
//         ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HibiscusColoredBlocksCompatability.MINT_KAOLIN_BRICKS, 4).input(Character.valueOf('S'), HibiscusColoredBlocksCompatability.MINT_KAOLIN).pattern("SS").pattern("SS").criterion("has_mint_kaolin", conditionsFromItem(HibiscusColoredBlocksCompatability.MINT_KAOLIN)).offerTo(exporter);
//         ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HibiscusColoredBlocksCompatability.AQUA_KAOLIN_BRICKS, 4).input(Character.valueOf('S'), HibiscusColoredBlocksCompatability.AQUA_KAOLIN).pattern("SS").pattern("SS").criterion("has_aqua_kaolin", conditionsFromItem(HibiscusColoredBlocksCompatability.AQUA_KAOLIN)).offerTo(exporter);
//         ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HibiscusColoredBlocksCompatability.SLATE_KAOLIN_BRICKS, 4).input(Character.valueOf('S'), HibiscusColoredBlocksCompatability.SLATE_KAOLIN).pattern("SS").pattern("SS").criterion("has_slate_kaolin", conditionsFromItem(HibiscusColoredBlocksCompatability.SLATE_KAOLIN)).offerTo(exporter);
//         ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HibiscusColoredBlocksCompatability.NAVY_KAOLIN_BRICKS, 4).input(Character.valueOf('S'), HibiscusColoredBlocksCompatability.NAVY_KAOLIN).pattern("SS").pattern("SS").criterion("has_navy_kaolin", conditionsFromItem(HibiscusColoredBlocksCompatability.NAVY_KAOLIN)).offerTo(exporter);
//         ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HibiscusColoredBlocksCompatability.INDIGO_KAOLIN_BRICKS, 4).input(Character.valueOf('S'), HibiscusColoredBlocksCompatability.INDIGO_KAOLIN).pattern("SS").pattern("SS").criterion("has_indigo_kaolin", conditionsFromItem(HibiscusColoredBlocksCompatability.INDIGO_KAOLIN)).offerTo(exporter);
//


        offerCarpetRecipe(exporter, RED_MOSS_CARPET, RED_MOSS_BLOCK);


        generateFlowerRecipes(HibiscusRegistryHelper.FlowerHashMap, exporter);
        generateWoodRecipes(HibiscusRegistryHelper.WoodHashMap, exporter);
        generateStoneRecipes(HibiscusRegistryHelper.StoneHashMap, exporter);
        offerShapelessRecipe(exporter, Items.BROWN_DYE, HibiscusMiscBlocks.CATTAIL, "brown_dye", 2);
        offerShapelessRecipe(exporter, Items.PINK_DYE, LOTUS_FLOWER, "pink_dye", 1);
        offerShapelessRecipe(exporter, Items.WHITE_DYE, HELVOLA, "white_dye", 1);
        offerShapelessRecipe(exporter, Items.RED_DYE, ORNATE_SUCCULENT, "red_dye", 1);
        offerShapelessRecipe(exporter, Items.LIME_DYE, DROWSY_SUCCULENT, "lime_dye", 1);
        offerShapelessRecipe(exporter, Items.YELLOW_DYE, AUREATE_SUCCULENT, "yellow_dye", 1);
        offerShapelessRecipe(exporter, Items.GREEN_DYE, SAGE_SUCCULENT, "green_dye", 1);
        offerShapelessRecipe(exporter, Items.LIGHT_BLUE_DYE, FOAMY_SUCCULENT, "light_blue_dye", 1);
        offerShapelessRecipe(exporter, Items.PURPLE_DYE, IMPERIAL_SUCCULENT, "purple_dye", 1);
        offerShapelessRecipe(exporter, Items.PINK_DYE, REGAL_SUCCULENT, "pink_dye", 1);
        offerCompactingRecipe(exporter, RecipeCategory.FOOD, HibiscusMiscBlocks.DESERT_TURNIP_BLOCK, HibiscusMiscBlocks.DESERT_TURNIP, "desert_turnip");
        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, HibiscusColoredBlocks.WHITE_CHALK, HibiscusMiscBlocks.CHALK_POWDER);

        offerShapelessRecipe(exporter, HibiscusWoods.COCONUT_HALF, HibiscusWoods.COCONUT_BLOCK, "coconut_half", 2);
        offerShapelessRecipe(exporter, HibiscusWoods.YOUNG_COCONUT_HALF, HibiscusWoods.YOUNG_COCONUT_BLOCK, "coconut_half", 2);
        offerShapelessRecipe(exporter, Items.BOWL, HibiscusWoods.COCONUT_SHELL, "bowl", 1);
        offerShapelessRecipe(exporter, Items.BOWL, HibiscusWoods.YOUNG_COCONUT_SHELL, "bowl", 1);
        CookingRecipeJsonBuilder.createSmelting(Ingredient.fromTag(HibiscusTags.Items.COCONUT_ITEMS), RecipeCategory.MISC, Items.CHARCOAL, 0.15F, 125).criterion("has_coconut", conditionsFromTag(HibiscusTags.Items.COCONUT_ITEMS)).offerTo(exporter, new Identifier(MOD_ID, "charcoal_from_coconuts"));


        generateFamily(exporter, CUT_PINK_SANDSTONE_FAMILY);
        generateFamily(exporter, SMOOTH_PINK_SANDSTONE_FAMILY);

    }
}
