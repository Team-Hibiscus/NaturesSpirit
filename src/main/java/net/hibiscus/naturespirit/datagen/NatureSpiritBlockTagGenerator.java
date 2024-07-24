package net.hibiscus.naturespirit.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.hibiscus.naturespirit.registration.FlowerSet;
import net.hibiscus.naturespirit.registration.HibiscusRegistryHelper;
import net.hibiscus.naturespirit.registration.StoneSet;
import net.hibiscus.naturespirit.registration.WoodSet;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusMiscBlocks;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusWoods;
import net.hibiscus.naturespirit.util.HibiscusTags;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static net.hibiscus.naturespirit.registration.block_registration.HibiscusMiscBlocks.*;

public class NatureSpiritBlockTagGenerator extends FabricTagProvider.BlockTagProvider {

    public NatureSpiritBlockTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    private void addWoodTags(HashMap<String, WoodSet> woods) {
        for (WoodSet woodSet : woods.values()) {

            getOrCreateTagBuilder(BlockTags.PLANKS).add(new Block[]{woodSet.getPlanks()});
            getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS).add(new Block[]{woodSet.getButton()});
            getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add(new Block[]{woodSet.getDoor()});
            getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS).add(new Block[]{woodSet.getStairs()});
            getOrCreateTagBuilder(BlockTags.WOODEN_SLABS).add(new Block[]{woodSet.getSlab()});
            getOrCreateTagBuilder(BlockTags.WOODEN_FENCES).add(new Block[]{woodSet.getFence()});
            getOrCreateTagBuilder(woodSet.getBlockLogsTag()).add(woodSet.getStrippedLog(), woodSet.getLog());
            getOrCreateTagBuilder(HibiscusTags.Blocks.STRIPPED_LOGS).add(woodSet.getStrippedLog());
            if (woodSet.hasBark()) {
                getOrCreateTagBuilder(woodSet.getBlockLogsTag()).add(woodSet.getStrippedWood(), woodSet.getWood());
                getOrCreateTagBuilder(HibiscusTags.Blocks.STRIPPED_LOGS).add(woodSet.getStrippedWood());
            }
            if (woodSet.hasMosaic()) {
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

    private void addStoneTags(HashMap<String, StoneSet> stones) {
        for (StoneSet stoneSet : stones.values()) {


            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(stoneSet.getRegisteredBlocksList().toArray(new Block[]{}));
            if (stoneSet.hasTiles()) {
                getOrCreateTagBuilder(BlockTags.SLABS).add(stoneSet.getTilesSlab());
                getOrCreateTagBuilder(BlockTags.STAIRS).add(stoneSet.getTilesStairs());
                getOrCreateTagBuilder(BlockTags.WALLS).add(stoneSet.getTilesWall());
            }
            if (stoneSet.hasCobbled()) {
                getOrCreateTagBuilder(BlockTags.SLABS).add(stoneSet.getCobbledSlab());
                getOrCreateTagBuilder(BlockTags.STAIRS).add(stoneSet.getCobbledStairs());
                getOrCreateTagBuilder(BlockTags.WALLS).add(stoneSet.getCobbledWall());
                if (stoneSet.hasMossy()) {
                    getOrCreateTagBuilder(BlockTags.SLABS).add(stoneSet.getMossyCobbledSlab());
                    getOrCreateTagBuilder(BlockTags.STAIRS).add(stoneSet.getMossyCobbledStairs());
                    getOrCreateTagBuilder(BlockTags.WALLS).add(stoneSet.getMossyCobbledWall());
                }
            }
            if (stoneSet.hasMossy()) {
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

    private void addTreeTags(HashMap<String, Block[]> saplings, HashMap<String, Block> leaves) {
        for (String i : leaves.keySet()) {
            Block leavesType = leaves.get(i);
            getOrCreateTagBuilder(BlockTags.HOE_MINEABLE).add(leavesType);
            getOrCreateTagBuilder(BlockTags.LEAVES).add(leavesType);
            if (!Objects.equals(i, "wisteria") && !Objects.equals(i, "coconut") && !i.startsWith("part") && !i.startsWith("frosty")) {
                Block[] saplingType = saplings.get(i);
                getOrCreateTagBuilder(BlockTags.SAPLINGS).add(new Block[]{saplingType[0]});
                getOrCreateTagBuilder(BlockTags.FLOWER_POTS).add(new Block[]{saplingType[1]});
            }
        }
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
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
         getOrCreateTagBuilder(BlockTags.TALL_FLOWERS).add(CATTAIL);
         getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS).add(LOTUS_FLOWER);
         getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS).add(HELVOLA);
        getOrCreateTagBuilder(HibiscusTags.Blocks.ALLUAUDIA_BUNDLES).add(STRIPPED_ALLUAUDIA_BUNDLE, ALLUAUDIA_BUNDLE);
        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN).addTag(HibiscusTags.Blocks.ALLUAUDIA_BUNDLES);
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
                LARGE_LUSH_FERN,
                LUSH_FERN,
                TALL_MELIC_GRASS,
                MELIC_GRASS,
                FRIGID_GRASS,
                TALL_FRIGID_GRASS,
                OAT_GRASS,
                TALL_OAT_GRASS,
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
                HibiscusWoods.EVERGREEN_THATCH,
                HibiscusWoods.EVERGREEN_THATCH_STAIRS,
                HibiscusWoods.EVERGREEN_THATCH_CARPET,
                HibiscusWoods.EVERGREEN_THATCH_SLAB,
                HibiscusWoods.XERIC_THATCH,
                HibiscusWoods.XERIC_THATCH_STAIRS,
                HibiscusWoods.XERIC_THATCH_CARPET,
                HibiscusWoods.XERIC_THATCH_SLAB,
                RED_MOSS_BLOCK,
                RED_MOSS_CARPET, ORNATE_SUCCULENT, AUREATE_SUCCULENT, SAGE_SUCCULENT, FOAMY_SUCCULENT, IMPERIAL_SUCCULENT, REGAL_SUCCULENT
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
                GRAY_POLYPORE,
                FRIGID_GRASS,
                TALL_FRIGID_GRASS,
                OAT_GRASS,
                TALL_OAT_GRASS,
                LARGE_LUSH_FERN,
                LUSH_FERN,
                TALL_MELIC_GRASS,
                MELIC_GRASS,
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
                TALL_FRIGID_GRASS,
                OAT_GRASS,
                TALL_OAT_GRASS,
                LARGE_LUSH_FERN,
                LUSH_FERN,
                TALL_MELIC_GRASS,
                MELIC_GRASS, AUREATE_SUCCULENT, SAGE_SUCCULENT, FOAMY_SUCCULENT, IMPERIAL_SUCCULENT, REGAL_SUCCULENT
        );
        getOrCreateTagBuilder(BlockTags.SAND).add(PINK_SAND, SANDY_SOIL);
        getOrCreateTagBuilder(BlockTags.SMELTS_TO_GLASS).add(PINK_SAND);
        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE).add(PINK_SAND, SANDY_SOIL);
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .forceAddTag(HibiscusTags.Blocks.KAOLIN)
                .forceAddTag(HibiscusTags.Blocks.KAOLIN_STAIRS)
                .forceAddTag(HibiscusTags.Blocks.KAOLIN_SLABS)
                .forceAddTag(HibiscusTags.Blocks.KAOLIN_BRICKS)
                .forceAddTag(HibiscusTags.Blocks.KAOLIN_BRICK_STAIRS)
                .forceAddTag(HibiscusTags.Blocks.KAOLIN_BRICK_SLABS)
                .forceAddTag(HibiscusTags.Blocks.CHALK)
                .forceAddTag(HibiscusTags.Blocks.CHALK_STAIRS)
                .forceAddTag(HibiscusTags.Blocks.CHALK_SLABS)
                .add(HibiscusMiscBlocks.CHERT_COAL_ORE, HibiscusMiscBlocks.CHERT_COPPER_ORE, HibiscusMiscBlocks.CHERT_DIAMOND_ORE, HibiscusMiscBlocks.CHERT_GOLD_ORE, HibiscusMiscBlocks.CHERT_EMERALD_ORE, HibiscusMiscBlocks.CHERT_IRON_ORE, HibiscusMiscBlocks.CHERT_LAPIS_ORE, HibiscusMiscBlocks.CHERT_REDSTONE_ORE, PINK_SANDSTONE, SMOOTH_PINK_SANDSTONE, CUT_PINK_SANDSTONE, PINK_SANDSTONE_STAIRS, SMOOTH_PINK_SANDSTONE_STAIRS, PINK_SANDSTONE_SLAB, SMOOTH_PINK_SANDSTONE_SLAB, CUT_PINK_SANDSTONE_SLAB, PINK_SANDSTONE_WALL, CHISELED_PINK_SANDSTONE);
        getOrCreateTagBuilder(BlockTags.STAIRS).add(PINK_SANDSTONE_STAIRS, SMOOTH_PINK_SANDSTONE_STAIRS, HibiscusWoods.EVERGREEN_THATCH_STAIRS, HibiscusWoods.COCONUT_THATCH_STAIRS);
        getOrCreateTagBuilder(BlockTags.SLABS).add(PINK_SANDSTONE_SLAB, SMOOTH_PINK_SANDSTONE_SLAB, CUT_PINK_SANDSTONE_SLAB, HibiscusWoods.EVERGREEN_THATCH_SLAB, HibiscusWoods.COCONUT_THATCH_SLAB);
        getOrCreateTagBuilder(BlockTags.WALLS).add(PINK_SANDSTONE_WALL);
        getOrCreateTagBuilder(BlockTags.CAULDRONS).add(CHEESE_CAULDRON, MILK_CAULDRON);
        getOrCreateTagBuilder(BlockTags.FLOWER_POTS).add(
                POTTED_MELIC_GRASS,
                POTTED_FLAXEN_FERN,
                POTTED_FRIGID_GRASS,
                POTTED_SHIITAKE_MUSHROOM,
                POTTED_BEACH_GRASS,
                POTTED_SEDGE_GRASS,
                POTTED_SCORCHED_GRASS,
                POTTED_OAT_GRASS,
                POTTED_LUSH_FERN,
                POTTED_ORNATE_SUCCULENT,
                POTTED_DROWSY_SUCCULENT,
                POTTED_AUREATE_SUCCULENT,
                POTTED_SAGE_SUCCULENT,
                POTTED_FOAMY_SUCCULENT,
                POTTED_IMPERIAL_SUCCULENT);
        getOrCreateTagBuilder(BlockTags.ENDERMAN_HOLDABLE).add(SHIITAKE_MUSHROOM);
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(SHIITAKE_MUSHROOM, SHIITAKE_MUSHROOM_BLOCK, GRAY_POLYPORE, GRAY_POLYPORE_BLOCK, DESERT_TURNIP_BLOCK, DESERT_TURNIP_ROOT_BLOCK, DESERT_TURNIP_STEM, PAPER_BLOCK, PAPER_PANEL, PAPER_DOOR, PAPER_SIGN, PAPER_WALL_SIGN, PAPER_HANGING_SIGN, PAPER_WALL_HANGING_SIGN, FRAMED_PAPER_BLOCK, FRAMED_PAPER_PANEL, FRAMED_PAPER_DOOR, FRAMED_PAPER_TRAPDOOR, BLOOMING_PAPER_BLOCK, BLOOMING_PAPER_DOOR, BLOOMING_PAPER_TRAPDOOR, BLOOMING_PAPER_PANEL);
        getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS).add(PAPER_HANGING_SIGN);
        getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS).add(PAPER_WALL_HANGING_SIGN);
        getOrCreateTagBuilder(BlockTags.STANDING_SIGNS).add(new Block[]{PAPER_SIGN});
        getOrCreateTagBuilder(BlockTags.WALL_SIGNS).add(new Block[]{PAPER_WALL_SIGN});
        getOrCreateTagBuilder(BlockTags.COAL_ORES).add(HibiscusMiscBlocks.CHERT_COAL_ORE);
        getOrCreateTagBuilder(BlockTags.COPPER_ORES).add(HibiscusMiscBlocks.CHERT_COPPER_ORE);
        getOrCreateTagBuilder(BlockTags.DIAMOND_ORES).add(HibiscusMiscBlocks.CHERT_DIAMOND_ORE);
        getOrCreateTagBuilder(BlockTags.GOLD_ORES).add(HibiscusMiscBlocks.CHERT_GOLD_ORE);
        getOrCreateTagBuilder(BlockTags.EMERALD_ORES).add(HibiscusMiscBlocks.CHERT_EMERALD_ORE);
        getOrCreateTagBuilder(BlockTags.IRON_ORES).add(HibiscusMiscBlocks.CHERT_IRON_ORE);
        getOrCreateTagBuilder(BlockTags.LAPIS_ORES).add(HibiscusMiscBlocks.CHERT_LAPIS_ORE);
        getOrCreateTagBuilder(BlockTags.REDSTONE_ORES).add(HibiscusMiscBlocks.CHERT_REDSTONE_ORE);
        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL).add(CHERT_EMERALD_ORE, CHERT_DIAMOND_ORE, CHERT_GOLD_ORE, CHERT_REDSTONE_ORE);
        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL).add(CHERT_COPPER_ORE, CHERT_IRON_ORE, CHERT_LAPIS_ORE);
        getOrCreateTagBuilder(BlockTags.STONE_ORE_REPLACEABLES).add(TRAVERTINE.getBase());
        getOrCreateTagBuilder(BlockTags.BASE_STONE_OVERWORLD).add(TRAVERTINE.getBase());
        getOrCreateTagBuilder(BlockTags.BASE_STONE_OVERWORLD).add(CHERT.getBase());
        getOrCreateTagBuilder(BlockTags.STAIRS).forceAddTag(HibiscusTags.Blocks.CHALK_STAIRS).forceAddTag(HibiscusTags.Blocks.KAOLIN_STAIRS).forceAddTag(HibiscusTags.Blocks.KAOLIN_BRICK_STAIRS).add(PINK_SANDSTONE_STAIRS, SMOOTH_PINK_SANDSTONE_STAIRS);
        getOrCreateTagBuilder(BlockTags.SLABS).forceAddTag(HibiscusTags.Blocks.CHALK_SLABS).forceAddTag(HibiscusTags.Blocks.KAOLIN_SLABS).forceAddTag(HibiscusTags.Blocks.KAOLIN_SLABS).add(PINK_SANDSTONE_SLAB, SMOOTH_PINK_SANDSTONE_SLAB, CUT_PINK_SANDSTONE_SLAB);
        getOrCreateTagBuilder(BlockTags.WALLS).add(PINK_SANDSTONE_WALL);
    }
}
