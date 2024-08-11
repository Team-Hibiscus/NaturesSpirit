package net.hibiscus.naturespirit.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.hibiscus.naturespirit.registration.*;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusColoredBlocks;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusMiscBlocks;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusWoods;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

import java.util.HashMap;
import java.util.List;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;
import static net.hibiscus.naturespirit.registration.block_registration.HibiscusMiscBlocks.*;
import static net.hibiscus.naturespirit.world.HibiscusBiomes.BiomesHashMap;

class NatureSpiritLangGenerator extends FabricLanguageProvider {

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
            } else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == '\'') {
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

    private void generateWoodTranslations(HashMap<String, WoodSet> woods, TranslationBuilder translationBuilder) {
        for (WoodSet woodSet : woods.values()) {
            for (Block block : woodSet.getRegisteredBlocksList()) {
                generateBlockTranslations(block, translationBuilder);
            }
            generateBlockTranslations(woodSet.getSign(), translationBuilder);
            generateBlockTranslations(woodSet.getHangingSign(), translationBuilder);
            translationBuilder.add(woodSet.getBoatItem(), capitalizeString(woodSet.getName().replace("_", " ")) + " Boat");
            translationBuilder.add(woodSet.getChestBoatItem(), capitalizeString(woodSet.getName().replace("_", " ")) + " Boat with Chest");
            translationBuilder.add(woodSet.getChestBoatEntityType(), "Boat with Chest");
            translationBuilder.add(woodSet.getBoatEntityType(), "Boat");
            generateArchExTranslations(woodSet.getName(), translationBuilder);
        }
    }

    private void generateStoneTranslations(HashMap<String, StoneSet> stones, TranslationBuilder translationBuilder) {
        for (StoneSet stoneSet : stones.values()) {
            for (Block block : stoneSet.getRegisteredBlocksList()) {
                generateBlockTranslations(block, translationBuilder);
            }
        }
    }

    private void generateFlowerTranslations(HashMap<String, FlowerSet> flowers, TranslationBuilder translationBuilder) {
        for (FlowerSet flowerSet : flowers.values()) {
            for (Block block : flowerSet.getRegisteredBlocksList()) {
                generateBlockTranslations(block, translationBuilder);
            }
        }
    }

    private void generateBiomeTranslations(TranslationBuilder translationBuilder) {
        for (String name : BiomesHashMap.keySet()) {
            RegistryKey<Biome> biome = BiomesHashMap.get(name);
            translationBuilder.add(biome.toString().replace("ResourceKey[minecraft:worldgen/biome / natures_spirit:", "biome.natures_spirit.").replace("]", ""), capitalizeString(name.replace("_", " ")));
        }
    }

    private void generateArchExTranslations(String group, TranslationBuilder translationBuilder) {
        translationBuilder.add("architecture_extensions.grouped_block." + MOD_ID + "." + group, capitalizeString(group.replace("_", " ")));
    }

    private void generateColoredTranslations(String group, TranslationBuilder translationBuilder) {
        translationBuilder.add("block." + MOD_ID + "." + group, capitalizeString(group.replace("_", " ")));
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        generateBiomeTranslations(translationBuilder);
        generateWoodTranslations(HibiscusRegistryHelper.WoodHashMap, translationBuilder);
        generateStoneTranslations(HibiscusRegistryHelper.StoneHashMap, translationBuilder);
        generateFlowerTranslations(HibiscusRegistryHelper.FlowerHashMap, translationBuilder);
        translationBuilder.add(HibiscusItemGroups.NS_ITEM_GROUP, "Nature's Spirit");
        translationBuilder.add("stat.minecraft.eat_pizza_slice", "Pizza Slices Eaten");


        translationBuilder.add("advancements.adventure.kaolin_kill.description", "Kill a Zombie with a Kaolin block");
        translationBuilder.add("advancements.adventure.kaolin_kill.title", "You're Kaolin This!");

        translationBuilder.add("advancements.adventure.shoot_coconut.description", "Hit a Coconut with a projectile");
        translationBuilder.add("advancements.adventure.shoot_coconut.title", "Tropical Blast!");

        translationBuilder.add("advancements.adventure.collect_all_flowers.description", "Collect all flowers.");
        translationBuilder.add("advancements.adventure.collect_all_flowers.title", "Flower Power");

        translationBuilder.add("advancements.adventure.sandy_forage.description", "Follow the Roots of a Turnip to a Turnip block");
        translationBuilder.add("advancements.adventure.sandy_forage.title", "Sandy Forage");

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
        generateBlockTranslations(TALL_OAT_GRASS, translationBuilder);
        generateBlockTranslations(OAT_GRASS, translationBuilder);
        generateBlockTranslations(LARGE_LUSH_FERN, translationBuilder);
        generateBlockTranslations(LUSH_FERN, translationBuilder);
        generateBlockTranslations(TALL_MELIC_GRASS, translationBuilder);
        generateBlockTranslations(MELIC_GRASS, translationBuilder);
        generateBlockTranslations(SHIITAKE_MUSHROOM, translationBuilder);
        generateBlockTranslations(SHIITAKE_MUSHROOM_BLOCK, translationBuilder);
        generateBlockTranslations(GRAY_POLYPORE, translationBuilder);
        generateBlockTranslations(GRAY_POLYPORE_BLOCK, translationBuilder);
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
        translationBuilder.add(HELVOLA_PAD_ITEM, "Helvola Pad");
        generateItemTranslations(HELVOLA_FLOWER_ITEM, translationBuilder);
        generateBlockTranslations(LOTUS_FLOWER, translationBuilder);
        generateBlockTranslations(LOTUS_STEM, translationBuilder);
        generateBlockTranslations(RED_MOSS_BLOCK, translationBuilder);
        generateBlockTranslations(RED_MOSS_CARPET, translationBuilder);
        generateBlockTranslations(ALLUAUDIA, translationBuilder);
        generateBlockTranslations(ALLUAUDIA_BUNDLE, translationBuilder);
        generateBlockTranslations(STRIPPED_ALLUAUDIA, translationBuilder);
        generateBlockTranslations(STRIPPED_ALLUAUDIA_BUNDLE, translationBuilder);

        generateBlockTranslations(CHERT_COAL_ORE, translationBuilder);
        generateBlockTranslations(CHERT_COPPER_ORE, translationBuilder);
        generateBlockTranslations(CHERT_DIAMOND_ORE, translationBuilder);
        generateBlockTranslations(CHERT_GOLD_ORE, translationBuilder);
        generateBlockTranslations(CHERT_EMERALD_ORE, translationBuilder);
        generateBlockTranslations(CHERT_IRON_ORE, translationBuilder);
        generateBlockTranslations(CHERT_LAPIS_ORE, translationBuilder);
        generateBlockTranslations(CHERT_REDSTONE_ORE, translationBuilder);

        generateBlockTranslations(HibiscusColoredBlocks.KAOLIN, translationBuilder);
        generateBlockTranslations(HibiscusColoredBlocks.KAOLIN_BRICKS, translationBuilder);
        generateBlockTranslations(HibiscusColoredBlocks.KAOLIN_SLAB, translationBuilder);
        generateBlockTranslations(HibiscusColoredBlocks.KAOLIN_BRICK_SLAB, translationBuilder);
        generateBlockTranslations(HibiscusColoredBlocks.KAOLIN_STAIRS, translationBuilder);
        generateBlockTranslations(HibiscusColoredBlocks.KAOLIN_BRICK_STAIRS, translationBuilder);

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

        generateBlockTranslations(HibiscusWoods.EVERGREEN_THATCH, translationBuilder);
        generateBlockTranslations(HibiscusWoods.EVERGREEN_THATCH_SLAB, translationBuilder);
        generateBlockTranslations(HibiscusWoods.EVERGREEN_THATCH_STAIRS, translationBuilder);
        generateBlockTranslations(HibiscusWoods.EVERGREEN_THATCH_CARPET, translationBuilder);

        generateBlockTranslations(HibiscusWoods.XERIC_THATCH, translationBuilder);
        generateBlockTranslations(HibiscusWoods.XERIC_THATCH_SLAB, translationBuilder);
        generateBlockTranslations(HibiscusWoods.XERIC_THATCH_STAIRS, translationBuilder);
        generateBlockTranslations(HibiscusWoods.XERIC_THATCH_CARPET, translationBuilder);

        generateBlockTranslations(ORNATE_SUCCULENT, translationBuilder);
        generateBlockTranslations(DROWSY_SUCCULENT, translationBuilder);
        generateBlockTranslations(AUREATE_SUCCULENT, translationBuilder);
        generateBlockTranslations(SAGE_SUCCULENT, translationBuilder);
        generateBlockTranslations(FOAMY_SUCCULENT, translationBuilder);
        generateBlockTranslations(IMPERIAL_SUCCULENT, translationBuilder);
        generateBlockTranslations(REGAL_SUCCULENT, translationBuilder);

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
        translationBuilder.add(CHEESE_ARROW, "Cheese Arrow");
        translationBuilder.add("block.natures_spirit.pizza.minecraft.cooked_chicken", "With Cooked Chicken");
        translationBuilder.add("block.natures_spirit.pizza.natures_spirit.green_olives", "With Green Olives");
        translationBuilder.add("block.natures_spirit.pizza.natures_spirit.black_olives", "With Black Olives");
        translationBuilder.add("block.natures_spirit.pizza.minecraft.brown_mushroom", "With Mushrooms");
        translationBuilder.add("block.natures_spirit.pizza.minecraft.beetroot", "With Beetroots");
        translationBuilder.add("block.natures_spirit.pizza.minecraft.carrot", "With Carrots");
        translationBuilder.add("block.natures_spirit.pizza.minecraft.cooked_cod", "With Cooked Cod");
        translationBuilder.add("block.natures_spirit.pizza.minecraft.cooked_porkchop", "With Cooked Porkchop");
        translationBuilder.add("block.natures_spirit.pizza.minecraft.cooked_rabbit", "With Cooked Rabbit");
        translationBuilder.add(HibiscusMiscBlocks.HALF_PIZZA, "Half of a Pizza");
        translationBuilder.add(HibiscusMiscBlocks.THREE_QUARTERS_PIZZA, "Three Quarters of a Pizza");
        translationBuilder.add(HibiscusMiscBlocks.QUARTER_PIZZA, "Quarter of a Pizza");
        translationBuilder.add(HibiscusMiscBlocks.WHOLE_PIZZA, "Pizza");

        translationBuilder.add("pack.natures_spirit.bushy_leaves_compatibility", "Bushy Leaves Compat");
        translationBuilder.add("pack.natures_spirit.plank_consistency", "Plank Consistency");
        translationBuilder.add("pack.natures_spirit.emissive_ores_compatibility", "Emissive Ores Compat");
        translationBuilder.add("pack.natures_spirit.modified_swamp", "Modified Swamp");
        translationBuilder.add("pack.natures_spirit.modified_desert", "Modified Desert");
        translationBuilder.add("pack.natures_spirit.modified_birch_forest", "Modified Birch Forest");
        translationBuilder.add("pack.natures_spirit.modified_badlands", "Modified Badlands");
        translationBuilder.add("pack.natures_spirit.modified_savannas", "Modified Savannas");
        translationBuilder.add("pack.natures_spirit.modified_dark_forest", "Modified Dark Forests");
        translationBuilder.add("pack.natures_spirit.modified_mountain_biomes", "Modified Mountain Biomes");
        translationBuilder.add("pack.natures_spirit.modified_vanilla_trees", "Modified Vanilla Trees");
        translationBuilder.add("pack.natures_spirit.modified_jungle", "Modified Jungles");
        translationBuilder.add("pack.natures_spirit.modified_windswept_hills", "Modified Windswept Hills");
        translationBuilder.add("pack.natures_spirit.dye_depot_compatibility", "Dye Depot Compatibility");
        translationBuilder.add("pack.natures_spirit.mint_compatibility", "El's and L's Dyes Compatibility");
        generateArchExTranslations("kaolin", translationBuilder);
        generateArchExTranslations("kaolin_bricks", translationBuilder);

        for (var color : List.of(NatureSpiritDataGen.DYE_COLORS)) {


            generateArchExTranslations(color + "_kaolin", translationBuilder);
            generateArchExTranslations(color + "_kaolin_bricks", translationBuilder);
            generateArchExTranslations(color + "_chalk", translationBuilder);


            generateColoredTranslations(color + "_paper_lantern", translationBuilder);
            generateColoredTranslations(color + "_kaolin", translationBuilder);
            generateColoredTranslations(color + "_kaolin_bricks", translationBuilder);
            generateColoredTranslations(color + "_kaolin_slab", translationBuilder);
            generateColoredTranslations(color + "_kaolin_brick_slab", translationBuilder);
            generateColoredTranslations(color + "_kaolin_stairs", translationBuilder);
            generateColoredTranslations(color + "_kaolin_brick_stairs", translationBuilder);
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
