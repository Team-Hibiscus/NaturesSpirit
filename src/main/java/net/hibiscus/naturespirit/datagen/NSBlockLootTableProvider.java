package net.hibiscus.naturespirit.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.hibiscus.naturespirit.registration.*;
import net.hibiscus.naturespirit.registration.NSColoredBlocks;
import net.hibiscus.naturespirit.registration.NSMiscBlocks;
import net.hibiscus.naturespirit.registration.NSWoods;
import net.hibiscus.naturespirit.registration.sets.FlowerSet;
import net.hibiscus.naturespirit.registration.sets.StoneSet;
import net.hibiscus.naturespirit.registration.sets.WoodSet;
import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.tag.ItemTags;

import java.util.HashMap;
import java.util.stream.IntStream;

import static net.hibiscus.naturespirit.registration.NSRegistryHelper.registerBlock;
import static net.hibiscus.naturespirit.registration.NSRegistryHelper.registerPaperLanternBlock;
import static net.hibiscus.naturespirit.registration.NSMiscBlocks.*;

class NSBlockLootTableProvider extends FabricBlockLootTableProvider {
    private static final LootCondition.Builder WITH_SILK_TOUCH_OR_SHEARS = WITH_SHEARS.or(WITH_SILK_TOUCH);
    private static final LootCondition.Builder WITHOUT_SILK_TOUCH_NOR_SHEARS = WITH_SILK_TOUCH_OR_SHEARS.invert();

    private final float[] SAPLING_DROP_CHANCE_2 = new float[]{0.4F, 0.4533333333F, 0.625F, 0.758F};

    protected NSBlockLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    private void addWoodTable(HashMap<String, WoodSet> woods) {
        for (WoodSet woodSet : woods.values()) {

            if (woodSet.hasBark()) {
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

    private void addStoneTable(HashMap<String, StoneSet> stones) {
        for (StoneSet stoneSet : stones.values()) {

            if (stoneSet.hasTiles()) {
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
            } else {
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
                .with(((net.minecraft.loot.entry.LeafEntry.Builder<?>) this.addSurvivesExplosionCondition(leaves, ItemEntry.builder(NSMiscBlocks.BLACK_OLIVES))).conditionally(
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
                .with(((net.minecraft.loot.entry.LeafEntry.Builder<?>) this.addSurvivesExplosionCondition(leaves, ItemEntry.builder(NSMiscBlocks.GREEN_OLIVES))).conditionally(
                        TableBonusLootCondition.builder(
                                Enchantments.FORTUNE,
                                0.01F,
                                0.0111111114F,
                                0.0125F,
                                0.016666668F,
                                0.05F
                        ))));
    }
   public LootTable.Builder noSaplingLeavesDrop(Block leaves) {
       Item drop = Items.STICK;
      return dropsWithSilkTouchOrShears(leaves, ((LeafEntry.Builder <?>)this.addSurvivesExplosionCondition(leaves, ItemEntry.builder(drop)))
              .conditionally(TableBonusLootCondition.builder(Enchantments.FORTUNE,
              SAPLING_DROP_CHANCE
      ))).pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F))
                       .conditionally(WITHOUT_SILK_TOUCH_NOR_SHEARS)
                       .with(((LeafEntry.Builder <?>)this.applyExplosionDecay(
                               leaves, ItemEntry.builder(Items.STICK).apply(
                                       SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))))
                               .conditionally(TableBonusLootCondition.builder(Enchantments.FORTUNE, LEAVES_STICK_DROP_CHANCE))));
   }
    private void addTreeTable(HashMap<String, Block[]> saplings, HashMap<String, Block> leaves) {
        for (String i : leaves.keySet()) {
           Block leavesType = leaves.get(i);
            if (saplings.get(i) != null) {
               Block[] saplingType = saplings.get(i);
               addDrop(saplingType[0]);
               addPottedPlantDrops(saplingType[1]);
               switch(i) {
                  case "olive" -> this.addDrop(leavesType, (block) -> this.greenOlivesDrop(block, saplingType[0], SAPLING_DROP_CHANCE));
                  case "joshua" -> this.addDrop(leavesType, (block) -> this.leavesDrops(block, saplingType[0], SAPLING_DROP_CHANCE_2));
                  default -> this.addDrop(leavesType, (block) -> this.leavesDrops(block, saplingType[0], SAPLING_DROP_CHANCE));
               }
            } else {
               this.addDrop(leavesType, this::noSaplingLeavesDrop);
            }
        }
    }

   public LootTable.Builder flowerbedDropsWithShears(Block flowerbed) {
      return LootTable.builder().pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F)).with(this.applyExplosionDecay(flowerbed, ItemEntry.builder(flowerbed).apply(
              IntStream.rangeClosed(1, 4).boxed().toList(), (flowerAmount) -> SetCountLootFunction.builder(ConstantLootNumberProvider.create((float)flowerAmount)).conditionally(BlockStatePropertyLootCondition
                      .builder(flowerbed).properties(StatePredicate.Builder.create().exactMatch(FlowerbedBlock.FLOWER_AMOUNT, flowerAmount))).conditionally(WITH_SILK_TOUCH_OR_SHEARS)))));
   }

    @Override
    public void generate() {
        addFlowerTable(NSRegistryHelper.FlowerHashMap);
        addStoneTable(NSRegistryHelper.StoneHashMap);
        addWoodTable(NSRegistryHelper.WoodHashMap);
        addTreeTable(NSRegistryHelper.SaplingHashMap, NSRegistryHelper.LeavesHashMap);

        this.addDrop(CALCITE_CLUSTER, (block) -> dropsWithSilkTouch(block,
                ItemEntry.builder(CALCITE_SHARD)
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(4.0F)))
                        .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))
                        .conditionally(MatchToolLootCondition.builder(net.minecraft.predicate.item.ItemPredicate.Builder.create().tag(ItemTags.CLUSTER_MAX_HARVESTABLES)))
                        .alternatively(this.applyExplosionDecay(block, ItemEntry.builder(CALCITE_SHARD).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F)))))
        ));
        this.addDropWithSilkTouch(SMALL_CALCITE_BUD);
        this.addDropWithSilkTouch(LARGE_CALCITE_BUD);

        addVinePlantDrop(NSWoods.WISTERIA.getWhiteVines(), NSWoods.WISTERIA.getWhiteVinesPlant());
        addVinePlantDrop(NSWoods.WISTERIA.getBlueVines(), NSWoods.WISTERIA.getBlueVinesPlant());
        addVinePlantDrop(NSWoods.WISTERIA.getPurpleVines(), NSWoods.WISTERIA.getPurpleVinesPlant());
        addVinePlantDrop(NSWoods.WISTERIA.getPinkVines(), NSWoods.WISTERIA.getPinkVinesPlant());
        addVinePlantDrop(NSWoods.WILLOW.getVines(), NSWoods.WILLOW.getVinesPlant());
        this.addDrop(NSWoods.FIR.getFrostyLeaves(), leavesDrops(NSWoods.FIR.getFrostyLeaves(), NSWoods.FIR.getSapling(), SAPLING_DROP_CHANCE));
        this.addDrop(ALLUAUDIA);
        this.addDrop(ALLUAUDIA_BUNDLE);
        this.addDrop(STRIPPED_ALLUAUDIA);
        this.addDrop(STRIPPED_ALLUAUDIA_BUNDLE);

        this.addDrop(CHERT_COAL_ORE, (Block block) -> this.oreDrops(block, Items.COAL));
        this.addDrop(CHERT_EMERALD_ORE, (Block block) -> this.oreDrops(block, Items.EMERALD));
        this.addDrop(CHERT_DIAMOND_ORE, (Block block) -> this.oreDrops(block, Items.DIAMOND));
        this.addDrop(CHERT_COPPER_ORE, this::copperOreDrops);
        this.addDrop(CHERT_REDSTONE_ORE, this::redstoneOreDrops);
        this.addDrop(CHERT_IRON_ORE, (Block block) -> this.oreDrops(block, Items.RAW_IRON));
        this.addDrop(CHERT_GOLD_ORE, (Block block) -> this.oreDrops(block, Items.RAW_GOLD));
        this.addDrop(CHERT_LAPIS_ORE, this::lapisOreDrops);

        this.addDrop(CATTAIL, (block) -> this.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));

        addVinePlantDrop(LOTUS_STEM, LOTUS_STEM);
        this.addDrop(LOTUS_FLOWER, LOTUS_FLOWER_ITEM);
        this.addDrop(AZOLLA, this::flowerbedDropsWithShears);

        this.addDrop(SHIITAKE_MUSHROOM);
        this.addDrop(SHIITAKE_MUSHROOM_BLOCK, this.mushroomBlockDrops(SHIITAKE_MUSHROOM_BLOCK, SHIITAKE_MUSHROOM));
       this.addDrop(GRAY_POLYPORE);
       this.addDrop(GRAY_POLYPORE_BLOCK, this.mushroomBlockDrops(GRAY_POLYPORE_BLOCK, GRAY_POLYPORE));

        this.addDrop(NSWoods.COCONUT_THATCH);
        this.addDrop(NSWoods.COCONUT_THATCH_CARPET);
        this.addDrop(NSWoods.COCONUT_THATCH_STAIRS);
        this.addDrop(NSWoods.COCONUT_THATCH_SLAB, this::slabDrops);

        this.addDrop(NSWoods.EVERGREEN_THATCH);
        this.addDrop(NSWoods.EVERGREEN_THATCH_CARPET);
        this.addDrop(NSWoods.EVERGREEN_THATCH_STAIRS);
        this.addDrop(NSWoods.EVERGREEN_THATCH_SLAB, this::slabDrops);

        this.addDrop(NSWoods.XERIC_THATCH);
        this.addDrop(NSWoods.XERIC_THATCH_CARPET);
        this.addDrop(NSWoods.XERIC_THATCH_STAIRS);
        this.addDrop(NSWoods.XERIC_THATCH_SLAB, this::slabDrops);

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
        this.addDrop(NSMiscBlocks.SANDY_SOIL);

        this.addDrop(ORNATE_SUCCULENT);
        this.addDrop(DROWSY_SUCCULENT);
        this.addDrop(AUREATE_SUCCULENT);
        this.addDrop(SAGE_SUCCULENT);
        this.addDrop(FOAMY_SUCCULENT);
        this.addDrop(IMPERIAL_SUCCULENT);
        this.addDrop(REGAL_SUCCULENT);

        addPottedPlantDrops(POTTED_ORNATE_SUCCULENT);
        addPottedPlantDrops(POTTED_DROWSY_SUCCULENT);
        addPottedPlantDrops(POTTED_AUREATE_SUCCULENT);
        addPottedPlantDrops(POTTED_SAGE_SUCCULENT);
        addPottedPlantDrops(POTTED_FOAMY_SUCCULENT);
        addPottedPlantDrops(POTTED_IMPERIAL_SUCCULENT);
        addPottedPlantDrops(POTTED_REGAL_SUCCULENT);

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

        this.addDrop(NSColoredBlocks.KAOLIN);
        this.addDrop(NSColoredBlocks.WHITE_KAOLIN);
        this.addDrop(NSColoredBlocks.LIGHT_GRAY_KAOLIN);
        this.addDrop(NSColoredBlocks.GRAY_KAOLIN);
        this.addDrop(NSColoredBlocks.BLACK_KAOLIN);
        this.addDrop(NSColoredBlocks.BROWN_KAOLIN);
        this.addDrop(NSColoredBlocks.RED_KAOLIN);
        this.addDrop(NSColoredBlocks.ORANGE_KAOLIN);
        this.addDrop(NSColoredBlocks.YELLOW_KAOLIN);
        this.addDrop(NSColoredBlocks.LIME_KAOLIN);
        this.addDrop(NSColoredBlocks.GREEN_KAOLIN);
        this.addDrop(NSColoredBlocks.CYAN_KAOLIN);
        this.addDrop(NSColoredBlocks.LIGHT_BLUE_KAOLIN);
        this.addDrop(NSColoredBlocks.BLUE_KAOLIN);
        this.addDrop(NSColoredBlocks.PURPLE_KAOLIN);
        this.addDrop(NSColoredBlocks.MAGENTA_KAOLIN);
        this.addDrop(NSColoredBlocks.PINK_KAOLIN);
        this.addDrop(NSColoredBlocks.KAOLIN_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.WHITE_KAOLIN_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.LIGHT_GRAY_KAOLIN_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.GRAY_KAOLIN_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.BLACK_KAOLIN_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.BROWN_KAOLIN_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.RED_KAOLIN_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.ORANGE_KAOLIN_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.YELLOW_KAOLIN_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.LIME_KAOLIN_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.GREEN_KAOLIN_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.CYAN_KAOLIN_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.LIGHT_BLUE_KAOLIN_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.BLUE_KAOLIN_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.PURPLE_KAOLIN_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.MAGENTA_KAOLIN_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.PINK_KAOLIN_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.KAOLIN_STAIRS);
        this.addDrop(NSColoredBlocks.WHITE_KAOLIN_STAIRS);
        this.addDrop(NSColoredBlocks.LIGHT_GRAY_KAOLIN_STAIRS);
        this.addDrop(NSColoredBlocks.GRAY_KAOLIN_STAIRS);
        this.addDrop(NSColoredBlocks.BLACK_KAOLIN_STAIRS);
        this.addDrop(NSColoredBlocks.BROWN_KAOLIN_STAIRS);
        this.addDrop(NSColoredBlocks.RED_KAOLIN_STAIRS);
        this.addDrop(NSColoredBlocks.ORANGE_KAOLIN_STAIRS);
        this.addDrop(NSColoredBlocks.YELLOW_KAOLIN_STAIRS);
        this.addDrop(NSColoredBlocks.LIME_KAOLIN_STAIRS);
        this.addDrop(NSColoredBlocks.GREEN_KAOLIN_STAIRS);
        this.addDrop(NSColoredBlocks.CYAN_KAOLIN_STAIRS);
        this.addDrop(NSColoredBlocks.LIGHT_BLUE_KAOLIN_STAIRS);
        this.addDrop(NSColoredBlocks.BLUE_KAOLIN_STAIRS);
        this.addDrop(NSColoredBlocks.PURPLE_KAOLIN_STAIRS);
        this.addDrop(NSColoredBlocks.MAGENTA_KAOLIN_STAIRS);
        this.addDrop(NSColoredBlocks.PINK_KAOLIN_STAIRS);


        this.addDrop(NSColoredBlocks.KAOLIN_BRICKS);
        this.addDrop(NSColoredBlocks.WHITE_KAOLIN_BRICKS);
        this.addDrop(NSColoredBlocks.LIGHT_GRAY_KAOLIN_BRICKS);
        this.addDrop(NSColoredBlocks.GRAY_KAOLIN_BRICKS);
        this.addDrop(NSColoredBlocks.BLACK_KAOLIN_BRICKS);
        this.addDrop(NSColoredBlocks.BROWN_KAOLIN_BRICKS);
        this.addDrop(NSColoredBlocks.RED_KAOLIN_BRICKS);
        this.addDrop(NSColoredBlocks.ORANGE_KAOLIN_BRICKS);
        this.addDrop(NSColoredBlocks.YELLOW_KAOLIN_BRICKS);
        this.addDrop(NSColoredBlocks.LIME_KAOLIN_BRICKS);
        this.addDrop(NSColoredBlocks.GREEN_KAOLIN_BRICKS);
        this.addDrop(NSColoredBlocks.CYAN_KAOLIN_BRICKS);
        this.addDrop(NSColoredBlocks.LIGHT_BLUE_KAOLIN_BRICKS);
        this.addDrop(NSColoredBlocks.BLUE_KAOLIN_BRICKS);
        this.addDrop(NSColoredBlocks.PURPLE_KAOLIN_BRICKS);
        this.addDrop(NSColoredBlocks.MAGENTA_KAOLIN_BRICKS);
        this.addDrop(NSColoredBlocks.PINK_KAOLIN_BRICKS);
        this.addDrop(NSColoredBlocks.KAOLIN_BRICK_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.WHITE_KAOLIN_BRICK_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.LIGHT_GRAY_KAOLIN_BRICK_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.GRAY_KAOLIN_BRICK_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.BLACK_KAOLIN_BRICK_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.BROWN_KAOLIN_BRICK_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.RED_KAOLIN_BRICK_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.ORANGE_KAOLIN_BRICK_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.YELLOW_KAOLIN_BRICK_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.LIME_KAOLIN_BRICK_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.GREEN_KAOLIN_BRICK_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.CYAN_KAOLIN_BRICK_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.LIGHT_BLUE_KAOLIN_BRICK_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.BLUE_KAOLIN_BRICK_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.PURPLE_KAOLIN_BRICK_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.MAGENTA_KAOLIN_BRICK_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.PINK_KAOLIN_BRICK_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.KAOLIN_BRICK_STAIRS);
        this.addDrop(NSColoredBlocks.WHITE_KAOLIN_BRICK_STAIRS);
        this.addDrop(NSColoredBlocks.LIGHT_GRAY_KAOLIN_BRICK_STAIRS);
        this.addDrop(NSColoredBlocks.GRAY_KAOLIN_BRICK_STAIRS);
        this.addDrop(NSColoredBlocks.BLACK_KAOLIN_BRICK_STAIRS);
        this.addDrop(NSColoredBlocks.BROWN_KAOLIN_BRICK_STAIRS);
        this.addDrop(NSColoredBlocks.RED_KAOLIN_BRICK_STAIRS);
        this.addDrop(NSColoredBlocks.ORANGE_KAOLIN_BRICK_STAIRS);
        this.addDrop(NSColoredBlocks.YELLOW_KAOLIN_BRICK_STAIRS);
        this.addDrop(NSColoredBlocks.LIME_KAOLIN_BRICK_STAIRS);
        this.addDrop(NSColoredBlocks.GREEN_KAOLIN_BRICK_STAIRS);
        this.addDrop(NSColoredBlocks.CYAN_KAOLIN_BRICK_STAIRS);
        this.addDrop(NSColoredBlocks.LIGHT_BLUE_KAOLIN_BRICK_STAIRS);
        this.addDrop(NSColoredBlocks.BLUE_KAOLIN_BRICK_STAIRS);
        this.addDrop(NSColoredBlocks.PURPLE_KAOLIN_BRICK_STAIRS);
        this.addDrop(NSColoredBlocks.MAGENTA_KAOLIN_BRICK_STAIRS);
        this.addDrop(NSColoredBlocks.PINK_KAOLIN_BRICK_STAIRS);


        this.addDrop(NSColoredBlocks.PAPER_LANTERN);
        this.addDrop(NSColoredBlocks.WHITE_PAPER_LANTERN);
        this.addDrop(NSColoredBlocks.LIGHT_GRAY_PAPER_LANTERN);
        this.addDrop(NSColoredBlocks.GRAY_PAPER_LANTERN);
        this.addDrop(NSColoredBlocks.BLACK_PAPER_LANTERN);
        this.addDrop(NSColoredBlocks.BROWN_PAPER_LANTERN);
        this.addDrop(NSColoredBlocks.RED_PAPER_LANTERN);
        this.addDrop(NSColoredBlocks.ORANGE_PAPER_LANTERN);
        this.addDrop(NSColoredBlocks.YELLOW_PAPER_LANTERN);
        this.addDrop(NSColoredBlocks.LIME_PAPER_LANTERN);
        this.addDrop(NSColoredBlocks.GREEN_PAPER_LANTERN);
        this.addDrop(NSColoredBlocks.CYAN_PAPER_LANTERN);
        this.addDrop(NSColoredBlocks.LIGHT_BLUE_PAPER_LANTERN);
        this.addDrop(NSColoredBlocks.BLUE_PAPER_LANTERN);
        this.addDrop(NSColoredBlocks.PURPLE_PAPER_LANTERN);
        this.addDrop(NSColoredBlocks.MAGENTA_PAPER_LANTERN);
        this.addDrop(NSColoredBlocks.PINK_PAPER_LANTERN);

        this.addDrop(NSColoredBlocks.WHITE_CHALK);
        this.addDrop(NSColoredBlocks.LIGHT_GRAY_CHALK);
        this.addDrop(NSColoredBlocks.GRAY_CHALK);
        this.addDrop(NSColoredBlocks.BLACK_CHALK);
        this.addDrop(NSColoredBlocks.BROWN_CHALK);
        this.addDrop(NSColoredBlocks.RED_CHALK);
        this.addDrop(NSColoredBlocks.ORANGE_CHALK);
        this.addDrop(NSColoredBlocks.YELLOW_CHALK);
        this.addDrop(NSColoredBlocks.LIME_CHALK);
        this.addDrop(NSColoredBlocks.GREEN_CHALK);
        this.addDrop(NSColoredBlocks.CYAN_CHALK);
        this.addDrop(NSColoredBlocks.LIGHT_BLUE_CHALK);
        this.addDrop(NSColoredBlocks.BLUE_CHALK);
        this.addDrop(NSColoredBlocks.PURPLE_CHALK);
        this.addDrop(NSColoredBlocks.MAGENTA_CHALK);
        this.addDrop(NSColoredBlocks.PINK_CHALK);
        this.addDrop(NSColoredBlocks.WHITE_CHALK_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.LIGHT_GRAY_CHALK_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.GRAY_CHALK_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.BLACK_CHALK_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.BROWN_CHALK_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.RED_CHALK_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.ORANGE_CHALK_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.YELLOW_CHALK_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.LIME_CHALK_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.GREEN_CHALK_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.CYAN_CHALK_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.LIGHT_BLUE_CHALK_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.BLUE_CHALK_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.PURPLE_CHALK_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.MAGENTA_CHALK_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.PINK_CHALK_SLAB, this::slabDrops);
        this.addDrop(NSColoredBlocks.WHITE_CHALK_STAIRS);
        this.addDrop(NSColoredBlocks.LIGHT_GRAY_CHALK_STAIRS);
        this.addDrop(NSColoredBlocks.GRAY_CHALK_STAIRS);
        this.addDrop(NSColoredBlocks.BLACK_CHALK_STAIRS);
        this.addDrop(NSColoredBlocks.BROWN_CHALK_STAIRS);
        this.addDrop(NSColoredBlocks.RED_CHALK_STAIRS);
        this.addDrop(NSColoredBlocks.ORANGE_CHALK_STAIRS);
        this.addDrop(NSColoredBlocks.YELLOW_CHALK_STAIRS);
        this.addDrop(NSColoredBlocks.LIME_CHALK_STAIRS);
        this.addDrop(NSColoredBlocks.GREEN_CHALK_STAIRS);
        this.addDrop(NSColoredBlocks.CYAN_CHALK_STAIRS);
        this.addDrop(NSColoredBlocks.LIGHT_BLUE_CHALK_STAIRS);
        this.addDrop(NSColoredBlocks.BLUE_CHALK_STAIRS);
        this.addDrop(NSColoredBlocks.PURPLE_CHALK_STAIRS);
        this.addDrop(NSColoredBlocks.MAGENTA_CHALK_STAIRS);
        this.addDrop(NSColoredBlocks.PINK_CHALK_STAIRS);
//
//       this.addDrop(NSMintCompatibility.MAROON_PAPER_LANTERN);
//               this.addDrop(NSMintCompatibility.PEACH_PAPER_LANTERN);
//       this.addDrop(NSMintCompatibility.VERMILION_PAPER_LANTERN);
//               this.addDrop(NSMintCompatibility.AMBER_PAPER_LANTERN);
//       this.addDrop(NSMintCompatibility.BANANA_PAPER_LANTERN);
//               this.addDrop(NSMintCompatibility.MOLD_PAPER_LANTERN);
//       this.addDrop(NSMintCompatibility.ARTICHOKE_PAPER_LANTERN);
//               this.addDrop(NSMintCompatibility.SAGE_PAPER_LANTERN);
//       this.addDrop(NSMintCompatibility.SHAMROCK_PAPER_LANTERN);
//               this.addDrop(NSMintCompatibility.SAP_PAPER_LANTERN);
//       this.addDrop(NSMintCompatibility.MINT_PAPER_LANTERN);
//               this.addDrop(NSMintCompatibility.CERULEAN_PAPER_LANTERN);
//       this.addDrop(NSMintCompatibility.NAVY_PAPER_LANTERN);
//               this.addDrop(NSMintCompatibility.PERIWINKLE_PAPER_LANTERN);
//       this.addDrop(NSMintCompatibility.INDIGO_PAPER_LANTERN);
//               this.addDrop(NSMintCompatibility.GRAPE_PAPER_LANTERN);
//       this.addDrop(NSMintCompatibility.FUCHSIA_PAPER_LANTERN);
//               this.addDrop(NSMintCompatibility.VELVET_PAPER_LANTERN);
//       this.addDrop(NSMintCompatibility.MAUVE_PAPER_LANTERN);
//               this.addDrop(NSMintCompatibility.MAROON_KAOLIN);
//       this.addDrop(NSMintCompatibility.PEACH_KAOLIN);
//               this.addDrop(NSMintCompatibility.VERMILION_KAOLIN);
//       this.addDrop(NSMintCompatibility.AMBER_KAOLIN);
//               this.addDrop(NSMintCompatibility.BANANA_KAOLIN);
//       this.addDrop(NSMintCompatibility.MOLD_KAOLIN);
//               this.addDrop(NSMintCompatibility.ARTICHOKE_KAOLIN);
//       this.addDrop(NSMintCompatibility.SAGE_KAOLIN);
//               this.addDrop(NSMintCompatibility.SHAMROCK_KAOLIN);
//       this.addDrop(NSMintCompatibility.SAP_KAOLIN);
//               this.addDrop(NSMintCompatibility.MINT_KAOLIN);
//       this.addDrop(NSMintCompatibility.CERULEAN_KAOLIN);
//               this.addDrop(NSMintCompatibility.NAVY_KAOLIN);
//       this.addDrop(NSMintCompatibility.PERIWINKLE_KAOLIN);
//               this.addDrop(NSMintCompatibility.INDIGO_KAOLIN);
//       this.addDrop(NSMintCompatibility.GRAPE_KAOLIN);
//               this.addDrop(NSMintCompatibility.FUCHSIA_KAOLIN);
//       this.addDrop(NSMintCompatibility.VELVET_KAOLIN);
//               this.addDrop(NSMintCompatibility.MAUVE_KAOLIN);
//       this.addDrop(NSMintCompatibility.ACORN_KAOLIN);
//               this.addDrop(NSMintCompatibility.MAROON_KAOLIN_STAIRS);
//       this.addDrop(NSMintCompatibility.PEACH_KAOLIN_STAIRS);
//               this.addDrop(NSMintCompatibility.VERMILION_KAOLIN_STAIRS);
//       this.addDrop(NSMintCompatibility.AMBER_KAOLIN_STAIRS);
//               this.addDrop(NSMintCompatibility.BANANA_KAOLIN_STAIRS);
//       this.addDrop(NSMintCompatibility.MOLD_KAOLIN_STAIRS);
//               this.addDrop(NSMintCompatibility.ARTICHOKE_KAOLIN_STAIRS);
//       this.addDrop(NSMintCompatibility.SAGE_KAOLIN_STAIRS);
//               this.addDrop(NSMintCompatibility.SHAMROCK_KAOLIN_STAIRS);
//       this.addDrop(NSMintCompatibility.SAP_KAOLIN_STAIRS);
//               this.addDrop(NSMintCompatibility.MINT_KAOLIN_STAIRS);
//       this.addDrop(NSMintCompatibility.CERULEAN_KAOLIN_STAIRS);
//               this.addDrop(NSMintCompatibility.NAVY_KAOLIN_STAIRS);
//       this.addDrop(NSMintCompatibility.PERIWINKLE_KAOLIN_STAIRS);
//               this.addDrop(NSMintCompatibility.INDIGO_KAOLIN_STAIRS);
//       this.addDrop(NSMintCompatibility.GRAPE_KAOLIN_STAIRS);
//               this.addDrop(NSMintCompatibility.FUCHSIA_KAOLIN_STAIRS);
//       this.addDrop(NSMintCompatibility.VELVET_KAOLIN_STAIRS);
//               this.addDrop(NSMintCompatibility.MAUVE_KAOLIN_STAIRS);
//       this.addDrop(NSMintCompatibility.ACORN_KAOLIN_STAIRS);
//
//               this.addDrop(NSMintCompatibility.MAROON_KAOLIN_SLAB, this::slabDrops);
//       this.addDrop(NSMintCompatibility.PEACH_KAOLIN_SLAB, this::slabDrops);
//               this.addDrop(NSMintCompatibility.VERMILION_KAOLIN_SLAB, this::slabDrops);
//       this.addDrop(NSMintCompatibility.AMBER_KAOLIN_SLAB, this::slabDrops);
//               this.addDrop(NSMintCompatibility.BANANA_KAOLIN_SLAB, this::slabDrops);
//       this.addDrop(NSMintCompatibility.MOLD_KAOLIN_SLAB, this::slabDrops);
//               this.addDrop(NSMintCompatibility.ARTICHOKE_KAOLIN_SLAB, this::slabDrops);
//       this.addDrop(NSMintCompatibility.SAGE_KAOLIN_SLAB, this::slabDrops);
//               this.addDrop(NSMintCompatibility.SHAMROCK_KAOLIN_SLAB, this::slabDrops);
//       this.addDrop(NSMintCompatibility.SAP_KAOLIN_SLAB, this::slabDrops);
//               this.addDrop(NSMintCompatibility.MINT_KAOLIN_SLAB, this::slabDrops);
//       this.addDrop(NSMintCompatibility.CERULEAN_KAOLIN_SLAB, this::slabDrops);
//               this.addDrop(NSMintCompatibility.NAVY_KAOLIN_SLAB, this::slabDrops);
//       this.addDrop(NSMintCompatibility.PERIWINKLE_KAOLIN_SLAB, this::slabDrops);
//               this.addDrop(NSMintCompatibility.INDIGO_KAOLIN_SLAB, this::slabDrops);
//       this.addDrop(NSMintCompatibility.GRAPE_KAOLIN_SLAB, this::slabDrops);
//               this.addDrop(NSMintCompatibility.FUCHSIA_KAOLIN_SLAB, this::slabDrops);
//       this.addDrop(NSMintCompatibility.VELVET_KAOLIN_SLAB, this::slabDrops);
//               this.addDrop(NSMintCompatibility.MAUVE_KAOLIN_SLAB, this::slabDrops);
//       this.addDrop(NSMintCompatibility.ACORN_KAOLIN_SLAB, this::slabDrops);
//
//               this.addDrop(NSMintCompatibility.MAROON_KAOLIN_BRICKS);
//       this.addDrop(NSMintCompatibility.PEACH_KAOLIN_BRICKS);
//               this.addDrop(NSMintCompatibility.VERMILION_KAOLIN_BRICKS);
//       this.addDrop(NSMintCompatibility.AMBER_KAOLIN_BRICKS);
//               this.addDrop(NSMintCompatibility.BANANA_KAOLIN_BRICKS);
//       this.addDrop(NSMintCompatibility.MOLD_KAOLIN_BRICKS);
//               this.addDrop(NSMintCompatibility.ARTICHOKE_KAOLIN_BRICKS);
//       this.addDrop(NSMintCompatibility.SAGE_KAOLIN_BRICKS);
//               this.addDrop(NSMintCompatibility.SHAMROCK_KAOLIN_BRICKS);
//       this.addDrop(NSMintCompatibility.SAP_KAOLIN_BRICKS);
//               this.addDrop(NSMintCompatibility.MINT_KAOLIN_BRICKS);
//       this.addDrop(NSMintCompatibility.CERULEAN_KAOLIN_BRICKS);
//               this.addDrop(NSMintCompatibility.NAVY_KAOLIN_BRICKS);
//       this.addDrop(NSMintCompatibility.PERIWINKLE_KAOLIN_BRICKS);
//               this.addDrop(NSMintCompatibility.INDIGO_KAOLIN_BRICKS);
//       this.addDrop(NSMintCompatibility.GRAPE_KAOLIN_BRICKS);
//               this.addDrop(NSMintCompatibility.FUCHSIA_KAOLIN_BRICKS);
//       this.addDrop(NSMintCompatibility.VELVET_KAOLIN_BRICKS);
//               this.addDrop(NSMintCompatibility.MAUVE_KAOLIN_BRICKS);
//       this.addDrop(NSMintCompatibility.ACORN_KAOLIN_BRICKS);
//               this.addDrop(NSMintCompatibility.MAROON_KAOLIN_BRICK_STAIRS);
//       this.addDrop(NSMintCompatibility.PEACH_KAOLIN_BRICK_STAIRS);
//               this.addDrop(NSMintCompatibility.VERMILION_KAOLIN_BRICK_STAIRS);
//       this.addDrop(NSMintCompatibility.AMBER_KAOLIN_BRICK_STAIRS);
//               this.addDrop(NSMintCompatibility.BANANA_KAOLIN_BRICK_STAIRS);
//       this.addDrop(NSMintCompatibility.MOLD_KAOLIN_BRICK_STAIRS);
//               this.addDrop(NSMintCompatibility.ARTICHOKE_KAOLIN_BRICK_STAIRS);
//       this.addDrop(NSMintCompatibility.SAGE_KAOLIN_BRICK_STAIRS);
//               this.addDrop(NSMintCompatibility.SHAMROCK_KAOLIN_BRICK_STAIRS);
//       this.addDrop(NSMintCompatibility.SAP_KAOLIN_BRICK_STAIRS);
//               this.addDrop(NSMintCompatibility.MINT_KAOLIN_BRICK_STAIRS);
//       this.addDrop(NSMintCompatibility.CERULEAN_KAOLIN_BRICK_STAIRS);
//               this.addDrop(NSMintCompatibility.NAVY_KAOLIN_BRICK_STAIRS);
//       this.addDrop(NSMintCompatibility.PERIWINKLE_KAOLIN_BRICK_STAIRS);
//               this.addDrop(NSMintCompatibility.INDIGO_KAOLIN_BRICK_STAIRS);
//       this.addDrop(NSMintCompatibility.GRAPE_KAOLIN_BRICK_STAIRS);
//               this.addDrop(NSMintCompatibility.FUCHSIA_KAOLIN_BRICK_STAIRS);
//       this.addDrop(NSMintCompatibility.VELVET_KAOLIN_BRICK_STAIRS);
//               this.addDrop(NSMintCompatibility.MAUVE_KAOLIN_BRICK_STAIRS);
//       this.addDrop(NSMintCompatibility.ACORN_KAOLIN_BRICK_STAIRS);
//
//               this.addDrop(NSMintCompatibility.MAROON_KAOLIN_BRICK_SLAB, this::slabDrops);
//       this.addDrop(NSMintCompatibility.PEACH_KAOLIN_BRICK_SLAB, this::slabDrops);
//               this.addDrop(NSMintCompatibility.VERMILION_KAOLIN_BRICK_SLAB, this::slabDrops);
//       this.addDrop(NSMintCompatibility.AMBER_KAOLIN_BRICK_SLAB, this::slabDrops);
//               this.addDrop(NSMintCompatibility.BANANA_KAOLIN_BRICK_SLAB, this::slabDrops);
//       this.addDrop(NSMintCompatibility.MOLD_KAOLIN_BRICK_SLAB, this::slabDrops);
//               this.addDrop(NSMintCompatibility.ARTICHOKE_KAOLIN_BRICK_SLAB, this::slabDrops);
//       this.addDrop(NSMintCompatibility.SAGE_KAOLIN_BRICK_SLAB, this::slabDrops);
//               this.addDrop(NSMintCompatibility.SHAMROCK_KAOLIN_BRICK_SLAB, this::slabDrops);
//       this.addDrop(NSMintCompatibility.SAP_KAOLIN_BRICK_SLAB, this::slabDrops);
//               this.addDrop(NSMintCompatibility.MINT_KAOLIN_BRICK_SLAB, this::slabDrops);
//       this.addDrop(NSMintCompatibility.CERULEAN_KAOLIN_BRICK_SLAB, this::slabDrops);
//               this.addDrop(NSMintCompatibility.NAVY_KAOLIN_BRICK_SLAB, this::slabDrops);
//       this.addDrop(NSMintCompatibility.PERIWINKLE_KAOLIN_BRICK_SLAB, this::slabDrops);
//               this.addDrop(NSMintCompatibility.INDIGO_KAOLIN_BRICK_SLAB, this::slabDrops);
//       this.addDrop(NSMintCompatibility.GRAPE_KAOLIN_BRICK_SLAB, this::slabDrops);
//               this.addDrop(NSMintCompatibility.FUCHSIA_KAOLIN_BRICK_SLAB, this::slabDrops);
//       this.addDrop(NSMintCompatibility.VELVET_KAOLIN_BRICK_SLAB, this::slabDrops);
//               this.addDrop(NSMintCompatibility.MAUVE_KAOLIN_BRICK_SLAB, this::slabDrops);
//       this.addDrop(NSMintCompatibility.ACORN_KAOLIN_BRICK_SLAB, this::slabDrops);
//
//               this.addDrop(NSMintCompatibility.MAROON_CHALK);
//       this.addDrop(NSMintCompatibility.PEACH_CHALK);
//               this.addDrop(NSMintCompatibility.VERMILION_CHALK);
//       this.addDrop(NSMintCompatibility.AMBER_CHALK);
//               this.addDrop(NSMintCompatibility.BANANA_CHALK);
//       this.addDrop(NSMintCompatibility.MOLD_CHALK);
//               this.addDrop(NSMintCompatibility.ARTICHOKE_CHALK);
//       this.addDrop(NSMintCompatibility.SAGE_CHALK);
//               this.addDrop(NSMintCompatibility.SHAMROCK_CHALK);
//       this.addDrop(NSMintCompatibility.SAP_CHALK);
//               this.addDrop(NSMintCompatibility.MINT_CHALK);
//       this.addDrop(NSMintCompatibility.CERULEAN_CHALK);
//               this.addDrop(NSMintCompatibility.NAVY_CHALK);
//       this.addDrop(NSMintCompatibility.PERIWINKLE_CHALK);
//               this.addDrop(NSMintCompatibility.INDIGO_CHALK);
//       this.addDrop(NSMintCompatibility.GRAPE_CHALK);
//               this.addDrop(NSMintCompatibility.FUCHSIA_CHALK);
//       this.addDrop(NSMintCompatibility.VELVET_CHALK);
//               this.addDrop(NSMintCompatibility.MAUVE_CHALK);
//       this.addDrop(NSMintCompatibility.ACORN_CHALK);
//               this.addDrop(NSMintCompatibility.MAROON_CHALK_STAIRS);
//       this.addDrop(NSMintCompatibility.PEACH_CHALK_STAIRS);
//               this.addDrop(NSMintCompatibility.VERMILION_CHALK_STAIRS);
//       this.addDrop(NSMintCompatibility.AMBER_CHALK_STAIRS);
//               this.addDrop(NSMintCompatibility.BANANA_CHALK_STAIRS);
//       this.addDrop(NSMintCompatibility.MOLD_CHALK_STAIRS);
//               this.addDrop(NSMintCompatibility.ARTICHOKE_CHALK_STAIRS);
//       this.addDrop(NSMintCompatibility.SAGE_CHALK_STAIRS);
//               this.addDrop(NSMintCompatibility.SHAMROCK_CHALK_STAIRS);
//       this.addDrop(NSMintCompatibility.SAP_CHALK_STAIRS);
//               this.addDrop(NSMintCompatibility.MINT_CHALK_STAIRS);
//       this.addDrop(NSMintCompatibility.CERULEAN_CHALK_STAIRS);
//               this.addDrop(NSMintCompatibility.NAVY_CHALK_STAIRS);
//       this.addDrop(NSMintCompatibility.PERIWINKLE_CHALK_STAIRS);
//               this.addDrop(NSMintCompatibility.INDIGO_CHALK_STAIRS);
//       this.addDrop(NSMintCompatibility.GRAPE_CHALK_STAIRS);
//               this.addDrop(NSMintCompatibility.FUCHSIA_CHALK_STAIRS);
//       this.addDrop(NSMintCompatibility.VELVET_CHALK_STAIRS);
//               this.addDrop(NSMintCompatibility.MAUVE_CHALK_STAIRS);
//       this.addDrop(NSMintCompatibility.ACORN_CHALK_STAIRS);
//
//               this.addDrop(NSMintCompatibility.MAROON_CHALK_SLAB, this::slabDrops);
//       this.addDrop(NSMintCompatibility.PEACH_CHALK_SLAB, this::slabDrops);
//               this.addDrop(NSMintCompatibility.VERMILION_CHALK_SLAB, this::slabDrops);
//       this.addDrop(NSMintCompatibility.AMBER_CHALK_SLAB, this::slabDrops);
//               this.addDrop(NSMintCompatibility.BANANA_CHALK_SLAB, this::slabDrops);
//       this.addDrop(NSMintCompatibility.MOLD_CHALK_SLAB, this::slabDrops);
//               this.addDrop(NSMintCompatibility.ARTICHOKE_CHALK_SLAB, this::slabDrops);
//       this.addDrop(NSMintCompatibility.SAGE_CHALK_SLAB, this::slabDrops);
//               this.addDrop(NSMintCompatibility.SHAMROCK_CHALK_SLAB, this::slabDrops);
//       this.addDrop(NSMintCompatibility.SAP_CHALK_SLAB, this::slabDrops);
//               this.addDrop(NSMintCompatibility.MINT_CHALK_SLAB, this::slabDrops);
//       this.addDrop(NSMintCompatibility.CERULEAN_CHALK_SLAB, this::slabDrops);
//               this.addDrop(NSMintCompatibility.NAVY_CHALK_SLAB, this::slabDrops);
//       this.addDrop(NSMintCompatibility.PERIWINKLE_CHALK_SLAB, this::slabDrops);
//               this.addDrop(NSMintCompatibility.INDIGO_CHALK_SLAB, this::slabDrops);
//       this.addDrop(NSMintCompatibility.GRAPE_CHALK_SLAB, this::slabDrops);
//               this.addDrop(NSMintCompatibility.FUCHSIA_CHALK_SLAB, this::slabDrops);
//       this.addDrop(NSMintCompatibility.VELVET_CHALK_SLAB, this::slabDrops);
//               this.addDrop(NSMintCompatibility.MAUVE_CHALK_SLAB, this::slabDrops);
//       this.addDrop(NSMintCompatibility.ACORN_CHALK_SLAB, this::slabDrops);

        this.addDrop(NSMiscBlocks.DESERT_TURNIP_ROOT_BLOCK);

        this.addDrop(NSWoods.COCONUT_SPROUT);
        this.addDrop(NSWoods.COCONUT_BLOCK);

        this.addDrop(FRIGID_GRASS, this::grassDrops);
        this.addDrop(SCORCHED_GRASS, this::grassDrops);
        this.addDrop(BEACH_GRASS, this::grassDrops);
        this.addDrop(SEDGE_GRASS, this::grassDrops);
        this.addDrop(FLAXEN_FERN, this::grassDrops);
        this.addDrop(OAT_GRASS, this::grassDrops);
        this.addDrop(LUSH_FERN, this::grassDrops);
        this.addDrop(MELIC_GRASS, this::grassDrops);
        this.addDrop(RED_BEARBERRIES, this::grassDrops);
        this.addDrop(RED_BITTER_SPROUTS, this::grassDrops);
        this.addDrop(GREEN_BEARBERRIES, this::grassDrops);
        this.addDrop(GREEN_BITTER_SPROUTS, this::grassDrops);
        this.addDrop(PURPLE_BEARBERRIES, this::grassDrops);
        this.addDrop(PURPLE_BITTER_SPROUTS, this::grassDrops);

        this.addDrop(TALL_FRIGID_GRASS, tallGrassDrops(TALL_FRIGID_GRASS, FRIGID_GRASS));
        this.addDrop(TALL_SCORCHED_GRASS, tallGrassDrops(TALL_SCORCHED_GRASS, SCORCHED_GRASS));
        this.addDrop(TALL_BEACH_GRASS, tallGrassDrops(TALL_BEACH_GRASS, BEACH_GRASS));
        this.addDrop(TALL_SEDGE_GRASS, tallGrassDrops(TALL_SEDGE_GRASS, SEDGE_GRASS));
        this.addDrop(LARGE_FLAXEN_FERN, tallGrassDrops(LARGE_FLAXEN_FERN, FLAXEN_FERN));
        this.addDrop(TALL_OAT_GRASS, tallGrassDrops(TALL_OAT_GRASS, OAT_GRASS));
        this.addDrop(LARGE_LUSH_FERN, tallGrassDrops(LARGE_LUSH_FERN, LUSH_FERN));
        this.addDrop(TALL_MELIC_GRASS, tallGrassDrops(TALL_MELIC_GRASS, MELIC_GRASS));

        addPottedPlantDrops(POTTED_PURPLE_BEARBERRIES);
        addPottedPlantDrops(POTTED_GREEN_BEARBERRIES);
        addPottedPlantDrops(POTTED_RED_BEARBERRIES);
        addPottedPlantDrops(POTTED_MELIC_GRASS);
        addPottedPlantDrops(POTTED_OAT_GRASS);
        addPottedPlantDrops(POTTED_LUSH_FERN);
        addPottedPlantDrops(POTTED_FLAXEN_FERN);
        addPottedPlantDrops(POTTED_SEDGE_GRASS);
        addPottedPlantDrops(POTTED_SCORCHED_GRASS);
        addPottedPlantDrops(POTTED_FRIGID_GRASS);
        addPottedPlantDrops(POTTED_BEACH_GRASS);

    }
}
