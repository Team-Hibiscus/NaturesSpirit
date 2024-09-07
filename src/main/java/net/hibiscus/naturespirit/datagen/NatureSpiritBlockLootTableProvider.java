package net.hibiscus.naturespirit.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.hibiscus.naturespirit.registration.*;
import net.hibiscus.naturespirit.registration.HibiscusColoredBlocks;
import net.hibiscus.naturespirit.registration.HibiscusMiscBlocks;
import net.hibiscus.naturespirit.registration.HibiscusWoods;
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

import static net.hibiscus.naturespirit.registration.HibiscusRegistryHelper.registerBlock;
import static net.hibiscus.naturespirit.registration.HibiscusRegistryHelper.registerPaperLanternBlock;
import static net.hibiscus.naturespirit.registration.HibiscusMiscBlocks.*;

class NatureSpiritBlockLootTableProvider extends FabricBlockLootTableProvider {
    private static final LootCondition.Builder WITH_SILK_TOUCH_OR_SHEARS = WITH_SHEARS.or(WITH_SILK_TOUCH);
    private static final LootCondition.Builder WITHOUT_SILK_TOUCH_NOR_SHEARS = WITH_SILK_TOUCH_OR_SHEARS.invert();

    private final float[] SAPLING_DROP_CHANCE_2 = new float[]{0.4F, 0.4533333333F, 0.625F, 0.758F};

    protected NatureSpiritBlockLootTableProvider(FabricDataOutput dataOutput) {
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
                .with(((net.minecraft.loot.entry.LeafEntry.Builder<?>) this.addSurvivesExplosionCondition(leaves, ItemEntry.builder(HibiscusMiscBlocks.BLACK_OLIVES))).conditionally(
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
                .with(((net.minecraft.loot.entry.LeafEntry.Builder<?>) this.addSurvivesExplosionCondition(leaves, ItemEntry.builder(HibiscusMiscBlocks.GREEN_OLIVES))).conditionally(
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
        addFlowerTable(HibiscusRegistryHelper.FlowerHashMap);
        addStoneTable(HibiscusRegistryHelper.StoneHashMap);
        addWoodTable(HibiscusRegistryHelper.WoodHashMap);
        addTreeTable(HibiscusRegistryHelper.SaplingHashMap, HibiscusRegistryHelper.LeavesHashMap);

        this.addDrop(CALCITE_CLUSTER, (block) -> dropsWithSilkTouch(block,
                ItemEntry.builder(CALCITE_SHARD)
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(4.0F)))
                        .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))
                        .conditionally(MatchToolLootCondition.builder(net.minecraft.predicate.item.ItemPredicate.Builder.create().tag(ItemTags.CLUSTER_MAX_HARVESTABLES)))
                        .alternatively(this.applyExplosionDecay(block, ItemEntry.builder(CALCITE_SHARD).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F)))))
        ));
        this.addDropWithSilkTouch(SMALL_CALCITE_BUD);
        this.addDropWithSilkTouch(LARGE_CALCITE_BUD);

        addVinePlantDrop(HibiscusWoods.WISTERIA.getWhiteWisteriaVines(), HibiscusWoods.WISTERIA.getWhiteWisteriaVinesPlant());
        addVinePlantDrop(HibiscusWoods.WISTERIA.getBlueWisteriaVines(), HibiscusWoods.WISTERIA.getBlueWisteriaVinesPlant());
        addVinePlantDrop(HibiscusWoods.WISTERIA.getPurpleWisteriaVines(), HibiscusWoods.WISTERIA.getPurpleWisteriaVinesPlant());
        addVinePlantDrop(HibiscusWoods.WISTERIA.getPinkWisteriaVines(), HibiscusWoods.WISTERIA.getPinkWisteriaVinesPlant());
        addVinePlantDrop(HibiscusWoods.WILLOW.getWillowVines(), HibiscusWoods.WILLOW.getWillowVinesPlant());
        this.addDrop(HibiscusWoods.FIR.getFrostyLeaves(), leavesDrops(HibiscusWoods.FIR.getFrostyLeaves(), HibiscusWoods.FIR.getSapling(), SAPLING_DROP_CHANCE));
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

        this.addDrop(HibiscusWoods.COCONUT_THATCH);
        this.addDrop(HibiscusWoods.COCONUT_THATCH_CARPET);
        this.addDrop(HibiscusWoods.COCONUT_THATCH_STAIRS);
        this.addDrop(HibiscusWoods.COCONUT_THATCH_SLAB, this::slabDrops);

        this.addDrop(HibiscusWoods.EVERGREEN_THATCH);
        this.addDrop(HibiscusWoods.EVERGREEN_THATCH_CARPET);
        this.addDrop(HibiscusWoods.EVERGREEN_THATCH_STAIRS);
        this.addDrop(HibiscusWoods.EVERGREEN_THATCH_SLAB, this::slabDrops);

        this.addDrop(HibiscusWoods.XERIC_THATCH);
        this.addDrop(HibiscusWoods.XERIC_THATCH_CARPET);
        this.addDrop(HibiscusWoods.XERIC_THATCH_STAIRS);
        this.addDrop(HibiscusWoods.XERIC_THATCH_SLAB, this::slabDrops);

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


        this.addDrop(HibiscusColoredBlocks.KAOLIN_BRICKS);
        this.addDrop(HibiscusColoredBlocks.WHITE_KAOLIN_BRICKS);
        this.addDrop(HibiscusColoredBlocks.LIGHT_GRAY_KAOLIN_BRICKS);
        this.addDrop(HibiscusColoredBlocks.GRAY_KAOLIN_BRICKS);
        this.addDrop(HibiscusColoredBlocks.BLACK_KAOLIN_BRICKS);
        this.addDrop(HibiscusColoredBlocks.BROWN_KAOLIN_BRICKS);
        this.addDrop(HibiscusColoredBlocks.RED_KAOLIN_BRICKS);
        this.addDrop(HibiscusColoredBlocks.ORANGE_KAOLIN_BRICKS);
        this.addDrop(HibiscusColoredBlocks.YELLOW_KAOLIN_BRICKS);
        this.addDrop(HibiscusColoredBlocks.LIME_KAOLIN_BRICKS);
        this.addDrop(HibiscusColoredBlocks.GREEN_KAOLIN_BRICKS);
        this.addDrop(HibiscusColoredBlocks.CYAN_KAOLIN_BRICKS);
        this.addDrop(HibiscusColoredBlocks.LIGHT_BLUE_KAOLIN_BRICKS);
        this.addDrop(HibiscusColoredBlocks.BLUE_KAOLIN_BRICKS);
        this.addDrop(HibiscusColoredBlocks.PURPLE_KAOLIN_BRICKS);
        this.addDrop(HibiscusColoredBlocks.MAGENTA_KAOLIN_BRICKS);
        this.addDrop(HibiscusColoredBlocks.PINK_KAOLIN_BRICKS);
        this.addDrop(HibiscusColoredBlocks.KAOLIN_BRICK_SLAB, this::slabDrops);
        this.addDrop(HibiscusColoredBlocks.WHITE_KAOLIN_BRICK_SLAB, this::slabDrops);
        this.addDrop(HibiscusColoredBlocks.LIGHT_GRAY_KAOLIN_BRICK_SLAB, this::slabDrops);
        this.addDrop(HibiscusColoredBlocks.GRAY_KAOLIN_BRICK_SLAB, this::slabDrops);
        this.addDrop(HibiscusColoredBlocks.BLACK_KAOLIN_BRICK_SLAB, this::slabDrops);
        this.addDrop(HibiscusColoredBlocks.BROWN_KAOLIN_BRICK_SLAB, this::slabDrops);
        this.addDrop(HibiscusColoredBlocks.RED_KAOLIN_BRICK_SLAB, this::slabDrops);
        this.addDrop(HibiscusColoredBlocks.ORANGE_KAOLIN_BRICK_SLAB, this::slabDrops);
        this.addDrop(HibiscusColoredBlocks.YELLOW_KAOLIN_BRICK_SLAB, this::slabDrops);
        this.addDrop(HibiscusColoredBlocks.LIME_KAOLIN_BRICK_SLAB, this::slabDrops);
        this.addDrop(HibiscusColoredBlocks.GREEN_KAOLIN_BRICK_SLAB, this::slabDrops);
        this.addDrop(HibiscusColoredBlocks.CYAN_KAOLIN_BRICK_SLAB, this::slabDrops);
        this.addDrop(HibiscusColoredBlocks.LIGHT_BLUE_KAOLIN_BRICK_SLAB, this::slabDrops);
        this.addDrop(HibiscusColoredBlocks.BLUE_KAOLIN_BRICK_SLAB, this::slabDrops);
        this.addDrop(HibiscusColoredBlocks.PURPLE_KAOLIN_BRICK_SLAB, this::slabDrops);
        this.addDrop(HibiscusColoredBlocks.MAGENTA_KAOLIN_BRICK_SLAB, this::slabDrops);
        this.addDrop(HibiscusColoredBlocks.PINK_KAOLIN_BRICK_SLAB, this::slabDrops);
        this.addDrop(HibiscusColoredBlocks.KAOLIN_BRICK_STAIRS);
        this.addDrop(HibiscusColoredBlocks.WHITE_KAOLIN_BRICK_STAIRS);
        this.addDrop(HibiscusColoredBlocks.LIGHT_GRAY_KAOLIN_BRICK_STAIRS);
        this.addDrop(HibiscusColoredBlocks.GRAY_KAOLIN_BRICK_STAIRS);
        this.addDrop(HibiscusColoredBlocks.BLACK_KAOLIN_BRICK_STAIRS);
        this.addDrop(HibiscusColoredBlocks.BROWN_KAOLIN_BRICK_STAIRS);
        this.addDrop(HibiscusColoredBlocks.RED_KAOLIN_BRICK_STAIRS);
        this.addDrop(HibiscusColoredBlocks.ORANGE_KAOLIN_BRICK_STAIRS);
        this.addDrop(HibiscusColoredBlocks.YELLOW_KAOLIN_BRICK_STAIRS);
        this.addDrop(HibiscusColoredBlocks.LIME_KAOLIN_BRICK_STAIRS);
        this.addDrop(HibiscusColoredBlocks.GREEN_KAOLIN_BRICK_STAIRS);
        this.addDrop(HibiscusColoredBlocks.CYAN_KAOLIN_BRICK_STAIRS);
        this.addDrop(HibiscusColoredBlocks.LIGHT_BLUE_KAOLIN_BRICK_STAIRS);
        this.addDrop(HibiscusColoredBlocks.BLUE_KAOLIN_BRICK_STAIRS);
        this.addDrop(HibiscusColoredBlocks.PURPLE_KAOLIN_BRICK_STAIRS);
        this.addDrop(HibiscusColoredBlocks.MAGENTA_KAOLIN_BRICK_STAIRS);
        this.addDrop(HibiscusColoredBlocks.PINK_KAOLIN_BRICK_STAIRS);


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
//
//       this.addDrop(HibiscusMintCompatibility.MAROON_PAPER_LANTERN);
//               this.addDrop(HibiscusMintCompatibility.PEACH_PAPER_LANTERN);
//       this.addDrop(HibiscusMintCompatibility.VERMILION_PAPER_LANTERN);
//               this.addDrop(HibiscusMintCompatibility.AMBER_PAPER_LANTERN);
//       this.addDrop(HibiscusMintCompatibility.BANANA_PAPER_LANTERN);
//               this.addDrop(HibiscusMintCompatibility.MOLD_PAPER_LANTERN);
//       this.addDrop(HibiscusMintCompatibility.ARTICHOKE_PAPER_LANTERN);
//               this.addDrop(HibiscusMintCompatibility.SAGE_PAPER_LANTERN);
//       this.addDrop(HibiscusMintCompatibility.SHAMROCK_PAPER_LANTERN);
//               this.addDrop(HibiscusMintCompatibility.SAP_PAPER_LANTERN);
//       this.addDrop(HibiscusMintCompatibility.MINT_PAPER_LANTERN);
//               this.addDrop(HibiscusMintCompatibility.CERULEAN_PAPER_LANTERN);
//       this.addDrop(HibiscusMintCompatibility.NAVY_PAPER_LANTERN);
//               this.addDrop(HibiscusMintCompatibility.PERIWINKLE_PAPER_LANTERN);
//       this.addDrop(HibiscusMintCompatibility.INDIGO_PAPER_LANTERN);
//               this.addDrop(HibiscusMintCompatibility.GRAPE_PAPER_LANTERN);
//       this.addDrop(HibiscusMintCompatibility.FUCHSIA_PAPER_LANTERN);
//               this.addDrop(HibiscusMintCompatibility.VELVET_PAPER_LANTERN);
//       this.addDrop(HibiscusMintCompatibility.MAUVE_PAPER_LANTERN);
//               this.addDrop(HibiscusMintCompatibility.MAROON_KAOLIN);
//       this.addDrop(HibiscusMintCompatibility.PEACH_KAOLIN);
//               this.addDrop(HibiscusMintCompatibility.VERMILION_KAOLIN);
//       this.addDrop(HibiscusMintCompatibility.AMBER_KAOLIN);
//               this.addDrop(HibiscusMintCompatibility.BANANA_KAOLIN);
//       this.addDrop(HibiscusMintCompatibility.MOLD_KAOLIN);
//               this.addDrop(HibiscusMintCompatibility.ARTICHOKE_KAOLIN);
//       this.addDrop(HibiscusMintCompatibility.SAGE_KAOLIN);
//               this.addDrop(HibiscusMintCompatibility.SHAMROCK_KAOLIN);
//       this.addDrop(HibiscusMintCompatibility.SAP_KAOLIN);
//               this.addDrop(HibiscusMintCompatibility.MINT_KAOLIN);
//       this.addDrop(HibiscusMintCompatibility.CERULEAN_KAOLIN);
//               this.addDrop(HibiscusMintCompatibility.NAVY_KAOLIN);
//       this.addDrop(HibiscusMintCompatibility.PERIWINKLE_KAOLIN);
//               this.addDrop(HibiscusMintCompatibility.INDIGO_KAOLIN);
//       this.addDrop(HibiscusMintCompatibility.GRAPE_KAOLIN);
//               this.addDrop(HibiscusMintCompatibility.FUCHSIA_KAOLIN);
//       this.addDrop(HibiscusMintCompatibility.VELVET_KAOLIN);
//               this.addDrop(HibiscusMintCompatibility.MAUVE_KAOLIN);
//       this.addDrop(HibiscusMintCompatibility.ACORN_KAOLIN);
//               this.addDrop(HibiscusMintCompatibility.MAROON_KAOLIN_STAIRS);
//       this.addDrop(HibiscusMintCompatibility.PEACH_KAOLIN_STAIRS);
//               this.addDrop(HibiscusMintCompatibility.VERMILION_KAOLIN_STAIRS);
//       this.addDrop(HibiscusMintCompatibility.AMBER_KAOLIN_STAIRS);
//               this.addDrop(HibiscusMintCompatibility.BANANA_KAOLIN_STAIRS);
//       this.addDrop(HibiscusMintCompatibility.MOLD_KAOLIN_STAIRS);
//               this.addDrop(HibiscusMintCompatibility.ARTICHOKE_KAOLIN_STAIRS);
//       this.addDrop(HibiscusMintCompatibility.SAGE_KAOLIN_STAIRS);
//               this.addDrop(HibiscusMintCompatibility.SHAMROCK_KAOLIN_STAIRS);
//       this.addDrop(HibiscusMintCompatibility.SAP_KAOLIN_STAIRS);
//               this.addDrop(HibiscusMintCompatibility.MINT_KAOLIN_STAIRS);
//       this.addDrop(HibiscusMintCompatibility.CERULEAN_KAOLIN_STAIRS);
//               this.addDrop(HibiscusMintCompatibility.NAVY_KAOLIN_STAIRS);
//       this.addDrop(HibiscusMintCompatibility.PERIWINKLE_KAOLIN_STAIRS);
//               this.addDrop(HibiscusMintCompatibility.INDIGO_KAOLIN_STAIRS);
//       this.addDrop(HibiscusMintCompatibility.GRAPE_KAOLIN_STAIRS);
//               this.addDrop(HibiscusMintCompatibility.FUCHSIA_KAOLIN_STAIRS);
//       this.addDrop(HibiscusMintCompatibility.VELVET_KAOLIN_STAIRS);
//               this.addDrop(HibiscusMintCompatibility.MAUVE_KAOLIN_STAIRS);
//       this.addDrop(HibiscusMintCompatibility.ACORN_KAOLIN_STAIRS);
//
//               this.addDrop(HibiscusMintCompatibility.MAROON_KAOLIN_SLAB, this::slabDrops);
//       this.addDrop(HibiscusMintCompatibility.PEACH_KAOLIN_SLAB, this::slabDrops);
//               this.addDrop(HibiscusMintCompatibility.VERMILION_KAOLIN_SLAB, this::slabDrops);
//       this.addDrop(HibiscusMintCompatibility.AMBER_KAOLIN_SLAB, this::slabDrops);
//               this.addDrop(HibiscusMintCompatibility.BANANA_KAOLIN_SLAB, this::slabDrops);
//       this.addDrop(HibiscusMintCompatibility.MOLD_KAOLIN_SLAB, this::slabDrops);
//               this.addDrop(HibiscusMintCompatibility.ARTICHOKE_KAOLIN_SLAB, this::slabDrops);
//       this.addDrop(HibiscusMintCompatibility.SAGE_KAOLIN_SLAB, this::slabDrops);
//               this.addDrop(HibiscusMintCompatibility.SHAMROCK_KAOLIN_SLAB, this::slabDrops);
//       this.addDrop(HibiscusMintCompatibility.SAP_KAOLIN_SLAB, this::slabDrops);
//               this.addDrop(HibiscusMintCompatibility.MINT_KAOLIN_SLAB, this::slabDrops);
//       this.addDrop(HibiscusMintCompatibility.CERULEAN_KAOLIN_SLAB, this::slabDrops);
//               this.addDrop(HibiscusMintCompatibility.NAVY_KAOLIN_SLAB, this::slabDrops);
//       this.addDrop(HibiscusMintCompatibility.PERIWINKLE_KAOLIN_SLAB, this::slabDrops);
//               this.addDrop(HibiscusMintCompatibility.INDIGO_KAOLIN_SLAB, this::slabDrops);
//       this.addDrop(HibiscusMintCompatibility.GRAPE_KAOLIN_SLAB, this::slabDrops);
//               this.addDrop(HibiscusMintCompatibility.FUCHSIA_KAOLIN_SLAB, this::slabDrops);
//       this.addDrop(HibiscusMintCompatibility.VELVET_KAOLIN_SLAB, this::slabDrops);
//               this.addDrop(HibiscusMintCompatibility.MAUVE_KAOLIN_SLAB, this::slabDrops);
//       this.addDrop(HibiscusMintCompatibility.ACORN_KAOLIN_SLAB, this::slabDrops);
//
//               this.addDrop(HibiscusMintCompatibility.MAROON_KAOLIN_BRICKS);
//       this.addDrop(HibiscusMintCompatibility.PEACH_KAOLIN_BRICKS);
//               this.addDrop(HibiscusMintCompatibility.VERMILION_KAOLIN_BRICKS);
//       this.addDrop(HibiscusMintCompatibility.AMBER_KAOLIN_BRICKS);
//               this.addDrop(HibiscusMintCompatibility.BANANA_KAOLIN_BRICKS);
//       this.addDrop(HibiscusMintCompatibility.MOLD_KAOLIN_BRICKS);
//               this.addDrop(HibiscusMintCompatibility.ARTICHOKE_KAOLIN_BRICKS);
//       this.addDrop(HibiscusMintCompatibility.SAGE_KAOLIN_BRICKS);
//               this.addDrop(HibiscusMintCompatibility.SHAMROCK_KAOLIN_BRICKS);
//       this.addDrop(HibiscusMintCompatibility.SAP_KAOLIN_BRICKS);
//               this.addDrop(HibiscusMintCompatibility.MINT_KAOLIN_BRICKS);
//       this.addDrop(HibiscusMintCompatibility.CERULEAN_KAOLIN_BRICKS);
//               this.addDrop(HibiscusMintCompatibility.NAVY_KAOLIN_BRICKS);
//       this.addDrop(HibiscusMintCompatibility.PERIWINKLE_KAOLIN_BRICKS);
//               this.addDrop(HibiscusMintCompatibility.INDIGO_KAOLIN_BRICKS);
//       this.addDrop(HibiscusMintCompatibility.GRAPE_KAOLIN_BRICKS);
//               this.addDrop(HibiscusMintCompatibility.FUCHSIA_KAOLIN_BRICKS);
//       this.addDrop(HibiscusMintCompatibility.VELVET_KAOLIN_BRICKS);
//               this.addDrop(HibiscusMintCompatibility.MAUVE_KAOLIN_BRICKS);
//       this.addDrop(HibiscusMintCompatibility.ACORN_KAOLIN_BRICKS);
//               this.addDrop(HibiscusMintCompatibility.MAROON_KAOLIN_BRICK_STAIRS);
//       this.addDrop(HibiscusMintCompatibility.PEACH_KAOLIN_BRICK_STAIRS);
//               this.addDrop(HibiscusMintCompatibility.VERMILION_KAOLIN_BRICK_STAIRS);
//       this.addDrop(HibiscusMintCompatibility.AMBER_KAOLIN_BRICK_STAIRS);
//               this.addDrop(HibiscusMintCompatibility.BANANA_KAOLIN_BRICK_STAIRS);
//       this.addDrop(HibiscusMintCompatibility.MOLD_KAOLIN_BRICK_STAIRS);
//               this.addDrop(HibiscusMintCompatibility.ARTICHOKE_KAOLIN_BRICK_STAIRS);
//       this.addDrop(HibiscusMintCompatibility.SAGE_KAOLIN_BRICK_STAIRS);
//               this.addDrop(HibiscusMintCompatibility.SHAMROCK_KAOLIN_BRICK_STAIRS);
//       this.addDrop(HibiscusMintCompatibility.SAP_KAOLIN_BRICK_STAIRS);
//               this.addDrop(HibiscusMintCompatibility.MINT_KAOLIN_BRICK_STAIRS);
//       this.addDrop(HibiscusMintCompatibility.CERULEAN_KAOLIN_BRICK_STAIRS);
//               this.addDrop(HibiscusMintCompatibility.NAVY_KAOLIN_BRICK_STAIRS);
//       this.addDrop(HibiscusMintCompatibility.PERIWINKLE_KAOLIN_BRICK_STAIRS);
//               this.addDrop(HibiscusMintCompatibility.INDIGO_KAOLIN_BRICK_STAIRS);
//       this.addDrop(HibiscusMintCompatibility.GRAPE_KAOLIN_BRICK_STAIRS);
//               this.addDrop(HibiscusMintCompatibility.FUCHSIA_KAOLIN_BRICK_STAIRS);
//       this.addDrop(HibiscusMintCompatibility.VELVET_KAOLIN_BRICK_STAIRS);
//               this.addDrop(HibiscusMintCompatibility.MAUVE_KAOLIN_BRICK_STAIRS);
//       this.addDrop(HibiscusMintCompatibility.ACORN_KAOLIN_BRICK_STAIRS);
//
//               this.addDrop(HibiscusMintCompatibility.MAROON_KAOLIN_BRICK_SLAB, this::slabDrops);
//       this.addDrop(HibiscusMintCompatibility.PEACH_KAOLIN_BRICK_SLAB, this::slabDrops);
//               this.addDrop(HibiscusMintCompatibility.VERMILION_KAOLIN_BRICK_SLAB, this::slabDrops);
//       this.addDrop(HibiscusMintCompatibility.AMBER_KAOLIN_BRICK_SLAB, this::slabDrops);
//               this.addDrop(HibiscusMintCompatibility.BANANA_KAOLIN_BRICK_SLAB, this::slabDrops);
//       this.addDrop(HibiscusMintCompatibility.MOLD_KAOLIN_BRICK_SLAB, this::slabDrops);
//               this.addDrop(HibiscusMintCompatibility.ARTICHOKE_KAOLIN_BRICK_SLAB, this::slabDrops);
//       this.addDrop(HibiscusMintCompatibility.SAGE_KAOLIN_BRICK_SLAB, this::slabDrops);
//               this.addDrop(HibiscusMintCompatibility.SHAMROCK_KAOLIN_BRICK_SLAB, this::slabDrops);
//       this.addDrop(HibiscusMintCompatibility.SAP_KAOLIN_BRICK_SLAB, this::slabDrops);
//               this.addDrop(HibiscusMintCompatibility.MINT_KAOLIN_BRICK_SLAB, this::slabDrops);
//       this.addDrop(HibiscusMintCompatibility.CERULEAN_KAOLIN_BRICK_SLAB, this::slabDrops);
//               this.addDrop(HibiscusMintCompatibility.NAVY_KAOLIN_BRICK_SLAB, this::slabDrops);
//       this.addDrop(HibiscusMintCompatibility.PERIWINKLE_KAOLIN_BRICK_SLAB, this::slabDrops);
//               this.addDrop(HibiscusMintCompatibility.INDIGO_KAOLIN_BRICK_SLAB, this::slabDrops);
//       this.addDrop(HibiscusMintCompatibility.GRAPE_KAOLIN_BRICK_SLAB, this::slabDrops);
//               this.addDrop(HibiscusMintCompatibility.FUCHSIA_KAOLIN_BRICK_SLAB, this::slabDrops);
//       this.addDrop(HibiscusMintCompatibility.VELVET_KAOLIN_BRICK_SLAB, this::slabDrops);
//               this.addDrop(HibiscusMintCompatibility.MAUVE_KAOLIN_BRICK_SLAB, this::slabDrops);
//       this.addDrop(HibiscusMintCompatibility.ACORN_KAOLIN_BRICK_SLAB, this::slabDrops);
//
//               this.addDrop(HibiscusMintCompatibility.MAROON_CHALK);
//       this.addDrop(HibiscusMintCompatibility.PEACH_CHALK);
//               this.addDrop(HibiscusMintCompatibility.VERMILION_CHALK);
//       this.addDrop(HibiscusMintCompatibility.AMBER_CHALK);
//               this.addDrop(HibiscusMintCompatibility.BANANA_CHALK);
//       this.addDrop(HibiscusMintCompatibility.MOLD_CHALK);
//               this.addDrop(HibiscusMintCompatibility.ARTICHOKE_CHALK);
//       this.addDrop(HibiscusMintCompatibility.SAGE_CHALK);
//               this.addDrop(HibiscusMintCompatibility.SHAMROCK_CHALK);
//       this.addDrop(HibiscusMintCompatibility.SAP_CHALK);
//               this.addDrop(HibiscusMintCompatibility.MINT_CHALK);
//       this.addDrop(HibiscusMintCompatibility.CERULEAN_CHALK);
//               this.addDrop(HibiscusMintCompatibility.NAVY_CHALK);
//       this.addDrop(HibiscusMintCompatibility.PERIWINKLE_CHALK);
//               this.addDrop(HibiscusMintCompatibility.INDIGO_CHALK);
//       this.addDrop(HibiscusMintCompatibility.GRAPE_CHALK);
//               this.addDrop(HibiscusMintCompatibility.FUCHSIA_CHALK);
//       this.addDrop(HibiscusMintCompatibility.VELVET_CHALK);
//               this.addDrop(HibiscusMintCompatibility.MAUVE_CHALK);
//       this.addDrop(HibiscusMintCompatibility.ACORN_CHALK);
//               this.addDrop(HibiscusMintCompatibility.MAROON_CHALK_STAIRS);
//       this.addDrop(HibiscusMintCompatibility.PEACH_CHALK_STAIRS);
//               this.addDrop(HibiscusMintCompatibility.VERMILION_CHALK_STAIRS);
//       this.addDrop(HibiscusMintCompatibility.AMBER_CHALK_STAIRS);
//               this.addDrop(HibiscusMintCompatibility.BANANA_CHALK_STAIRS);
//       this.addDrop(HibiscusMintCompatibility.MOLD_CHALK_STAIRS);
//               this.addDrop(HibiscusMintCompatibility.ARTICHOKE_CHALK_STAIRS);
//       this.addDrop(HibiscusMintCompatibility.SAGE_CHALK_STAIRS);
//               this.addDrop(HibiscusMintCompatibility.SHAMROCK_CHALK_STAIRS);
//       this.addDrop(HibiscusMintCompatibility.SAP_CHALK_STAIRS);
//               this.addDrop(HibiscusMintCompatibility.MINT_CHALK_STAIRS);
//       this.addDrop(HibiscusMintCompatibility.CERULEAN_CHALK_STAIRS);
//               this.addDrop(HibiscusMintCompatibility.NAVY_CHALK_STAIRS);
//       this.addDrop(HibiscusMintCompatibility.PERIWINKLE_CHALK_STAIRS);
//               this.addDrop(HibiscusMintCompatibility.INDIGO_CHALK_STAIRS);
//       this.addDrop(HibiscusMintCompatibility.GRAPE_CHALK_STAIRS);
//               this.addDrop(HibiscusMintCompatibility.FUCHSIA_CHALK_STAIRS);
//       this.addDrop(HibiscusMintCompatibility.VELVET_CHALK_STAIRS);
//               this.addDrop(HibiscusMintCompatibility.MAUVE_CHALK_STAIRS);
//       this.addDrop(HibiscusMintCompatibility.ACORN_CHALK_STAIRS);
//
//               this.addDrop(HibiscusMintCompatibility.MAROON_CHALK_SLAB, this::slabDrops);
//       this.addDrop(HibiscusMintCompatibility.PEACH_CHALK_SLAB, this::slabDrops);
//               this.addDrop(HibiscusMintCompatibility.VERMILION_CHALK_SLAB, this::slabDrops);
//       this.addDrop(HibiscusMintCompatibility.AMBER_CHALK_SLAB, this::slabDrops);
//               this.addDrop(HibiscusMintCompatibility.BANANA_CHALK_SLAB, this::slabDrops);
//       this.addDrop(HibiscusMintCompatibility.MOLD_CHALK_SLAB, this::slabDrops);
//               this.addDrop(HibiscusMintCompatibility.ARTICHOKE_CHALK_SLAB, this::slabDrops);
//       this.addDrop(HibiscusMintCompatibility.SAGE_CHALK_SLAB, this::slabDrops);
//               this.addDrop(HibiscusMintCompatibility.SHAMROCK_CHALK_SLAB, this::slabDrops);
//       this.addDrop(HibiscusMintCompatibility.SAP_CHALK_SLAB, this::slabDrops);
//               this.addDrop(HibiscusMintCompatibility.MINT_CHALK_SLAB, this::slabDrops);
//       this.addDrop(HibiscusMintCompatibility.CERULEAN_CHALK_SLAB, this::slabDrops);
//               this.addDrop(HibiscusMintCompatibility.NAVY_CHALK_SLAB, this::slabDrops);
//       this.addDrop(HibiscusMintCompatibility.PERIWINKLE_CHALK_SLAB, this::slabDrops);
//               this.addDrop(HibiscusMintCompatibility.INDIGO_CHALK_SLAB, this::slabDrops);
//       this.addDrop(HibiscusMintCompatibility.GRAPE_CHALK_SLAB, this::slabDrops);
//               this.addDrop(HibiscusMintCompatibility.FUCHSIA_CHALK_SLAB, this::slabDrops);
//       this.addDrop(HibiscusMintCompatibility.VELVET_CHALK_SLAB, this::slabDrops);
//               this.addDrop(HibiscusMintCompatibility.MAUVE_CHALK_SLAB, this::slabDrops);
//       this.addDrop(HibiscusMintCompatibility.ACORN_CHALK_SLAB, this::slabDrops);

        this.addDrop(HibiscusMiscBlocks.DESERT_TURNIP_ROOT_BLOCK);

        this.addDrop(HibiscusWoods.COCONUT_SPROUT);
        this.addDrop(HibiscusWoods.COCONUT_BLOCK);
        this.addDrop(HibiscusWoods.YOUNG_COCONUT_BLOCK);

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
