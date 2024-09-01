package net.hibiscus.naturespirit.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.hibiscus.naturespirit.registration.FlowerSet;
import net.hibiscus.naturespirit.registration.HibiscusRegistryHelper;
import net.hibiscus.naturespirit.registration.StoneSet;
import net.hibiscus.naturespirit.registration.WoodSet;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusColoredBlocks;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusMiscBlocks;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusWoods;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerbedBlock;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.entry.LootPoolEntryTypes;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

import static net.hibiscus.naturespirit.registration.block_registration.HibiscusMiscBlocks.*;

class NatureSpiritBlockLootTableProvider extends FabricBlockLootTableProvider {

   private final float[] SAPLING_DROP_CHANCE_2 = new float[]{0.4F, 0.4533333333F, 0.625F, 0.758F};

   private final static float[] LEAVES_STICK_DROP_CHANCE = new float[]{0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F};

   protected NatureSpiritBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
      super(dataOutput, registryLookup);
   }

   private void addWoodTable(HashMap<String, WoodSet> woods) {
      for(WoodSet woodSet : woods.values()) {

         if (woodSet.hasBark())
         {
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
   private void addStoneTable(HashMap <String, StoneSet> stones) {
      for(StoneSet stoneSet : stones.values()) {

         if (stoneSet.hasTiles())
         {
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
         }
         else {
            this.addDrop(flowerSet.getFlowerBlock());
            addPottedPlantDrops(flowerSet.getPottedFlowerBlock());
         }
      }
   }

   public net.minecraft.loot.LootTable.Builder blackOlivesDrop(Block leaves, Block drop, float... chance) {
      RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
      return this.leavesDrops(leaves, drop, chance).pool(LootPool
              .builder()
              .rolls(ConstantLootNumberProvider.create(1.0F))
              .conditionally(createWithoutShearsOrSilkTouchCondition())
              .with(((net.minecraft.loot.entry.LeafEntry.Builder <?>) this.addSurvivesExplosionCondition(leaves, ItemEntry.builder(HibiscusMiscBlocks.BLACK_OLIVES))).conditionally(
                      TableBonusLootCondition.builder(
                              impl.getOrThrow(Enchantments.FORTUNE),
                              0.01F,
                              0.0111111114F,
                              0.0125F,
                              0.016666668F,
                              0.05F
                      ))));
   }

   public net.minecraft.loot.LootTable.Builder greenOlivesDrop(Block leaves, Block drop, float... chance) {
      RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
      return this.blackOlivesDrop(leaves, drop, chance).pool(LootPool
              .builder()
              .rolls(ConstantLootNumberProvider.create(1.0F))
              .conditionally(createWithoutShearsOrSilkTouchCondition())
              .with(((net.minecraft.loot.entry.LeafEntry.Builder <?>) this.addSurvivesExplosionCondition(leaves, ItemEntry.builder(HibiscusMiscBlocks.GREEN_OLIVES))).conditionally(
                      TableBonusLootCondition.builder(
                              impl.getOrThrow(Enchantments.FORTUNE),
                              0.01F,
                              0.0111111114F,
                              0.0125F,
                              0.016666668F,
                              0.05F
                      ))));
   }

   public final LootTable.Builder dropsWithSilkTouchOrShears2(ItemConvertible drop) {
      return LootTable.builder().pool(LootPool.builder().conditionally(this.createWithShearsOrSilkTouchCondition()).rolls(ConstantLootNumberProvider.create(1.0F)).with(ItemEntry.builder(drop)));
   }

   public LootTable.Builder noSaplingLeavesDrop(Block leaves) {
      RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
      return this.dropsWithSilkTouchOrShears2(leaves)
                 .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F)).conditionally(this.createWithoutShearsOrSilkTouchCondition()).with(((LeafEntry.Builder)this.applyExplosionDecay(leaves, ItemEntry.builder(Items.STICK).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))).conditionally(TableBonusLootCondition.builder(impl.getOrThrow(Enchantments.FORTUNE), LEAVES_STICK_DROP_CHANCE))));
   }
   public LootTable.Builder flowerbedDropsWithShears(Block flowerbed) {
      return LootTable.builder().pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F)).with(this.applyExplosionDecay(flowerbed, ItemEntry.builder(flowerbed).apply(
              IntStream.rangeClosed(1, 4).boxed().toList(), (flowerAmount) -> SetCountLootFunction.builder(ConstantLootNumberProvider.create((float)flowerAmount)).conditionally(
                      BlockStatePropertyLootCondition
                              .builder(flowerbed).properties(net.minecraft.predicate.StatePredicate.Builder.create().exactMatch(FlowerbedBlock.FLOWER_AMOUNT, flowerAmount))).conditionally(this.createWithShearsOrSilkTouchCondition())))));
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

   @Override public void generate() {
      addFlowerTable(HibiscusRegistryHelper.FlowerHashMap);
      addStoneTable(HibiscusRegistryHelper.StoneHashMap);
      addWoodTable(HibiscusRegistryHelper.WoodHashMap);
      addTreeTable(HibiscusRegistryHelper.SaplingHashMap, HibiscusRegistryHelper.LeavesHashMap);

      RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
      this.addDrop(CALCITE_CLUSTER, (block) -> dropsWithSilkTouch(block,
              ItemEntry.builder(CALCITE_SHARD)
                      .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(4.0F)))
                      .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))
                      .conditionally(MatchToolLootCondition.builder(net.minecraft.predicate.item.ItemPredicate.Builder.create().tag(ItemTags.CLUSTER_MAX_HARVESTABLES)))
                      .alternatively(this.applyExplosionDecay(block, ItemEntry.builder(CALCITE_SHARD).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F)))))
      ));
      this.addDropWithSilkTouch(SMALL_CALCITE_BUD);
      this.addDropWithSilkTouch(LARGE_CALCITE_BUD);

      this.addDrop(CATTAIL, (block) -> this.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));

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
   
      this.addDrop(AZOLLA, this::flowerbedDropsWithShears);

      this.addDrop(CHERT_COAL_ORE, (Block block) -> this.oreDrops(block, Items.COAL));
      this.addDrop(CHERT_EMERALD_ORE, (Block block) -> this.oreDrops(block, Items.EMERALD));
      this.addDrop(CHERT_DIAMOND_ORE, (Block block) -> this.oreDrops(block, Items.DIAMOND));
      this.addDrop(CHERT_COPPER_ORE, this::copperOreDrops);
      this.addDrop(CHERT_REDSTONE_ORE, this::redstoneOreDrops);
      this.addDrop(CHERT_IRON_ORE, (Block block) -> this.oreDrops(block, Items.RAW_IRON));
      this.addDrop(CHERT_GOLD_ORE, (Block block) -> this.oreDrops(block, Items.RAW_GOLD));
      this.addDrop(CHERT_LAPIS_ORE, this::lapisOreDrops);

      

      this.addDrop(SHIITAKE_MUSHROOM);
      this.addDrop(SHIITAKE_MUSHROOM_BLOCK, this.mushroomBlockDrops(SHIITAKE_MUSHROOM_BLOCK, SHIITAKE_MUSHROOM));
     this.addDrop(GRAY_POLYPORE);
     this.addDrop(GRAY_POLYPORE_BLOCK, this.mushroomBlockDrops(GRAY_POLYPORE_BLOCK, GRAY_POLYPORE));


      addVinePlantDrop(LOTUS_STEM, LOTUS_STEM);
      this.addDrop(LOTUS_FLOWER, LOTUS_FLOWER_ITEM);

      this.addDrop(SHIITAKE_MUSHROOM);
      this.mushroomBlockDrops(SHIITAKE_MUSHROOM_BLOCK, SHIITAKE_MUSHROOM);

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

      this.addDrop(HibiscusMiscBlocks.DESERT_TURNIP_ROOT_BLOCK);

      this.addDrop(HibiscusWoods.COCONUT_SPROUT);
      this.addDrop(HibiscusWoods.COCONUT_BLOCK);
      this.addDrop(HibiscusWoods.YOUNG_COCONUT_BLOCK);

      this.addDrop(FRIGID_GRASS, this::shortPlantDrops);
      this.addDrop(SCORCHED_GRASS, this::shortPlantDrops);
      this.addDrop(BEACH_GRASS, this::shortPlantDrops);
      this.addDrop(SEDGE_GRASS, this::shortPlantDrops);
      this.addDrop(FLAXEN_FERN, this::shortPlantDrops);
      this.addDrop(OAT_GRASS, this::shortPlantDrops);
      this.addDrop(LUSH_FERN, this::shortPlantDrops);
      this.addDrop(MELIC_GRASS, this::shortPlantDrops);
      this.addDrop(RED_BEARBERRIES, this::shortPlantDrops);
      this.addDrop(RED_BITTER_SPROUTS, this::shortPlantDrops);
      this.addDrop(GREEN_BEARBERRIES, this::shortPlantDrops);
      this.addDrop(GREEN_BITTER_SPROUTS, this::shortPlantDrops);
      this.addDrop(PURPLE_BEARBERRIES, this::shortPlantDrops);
      this.addDrop(PURPLE_BITTER_SPROUTS, this::shortPlantDrops);

      this.addDrop(TALL_FRIGID_GRASS, tallPlantDrops(TALL_FRIGID_GRASS, FRIGID_GRASS));
      this.addDrop(TALL_SCORCHED_GRASS, tallPlantDrops(TALL_SCORCHED_GRASS, SCORCHED_GRASS));
      this.addDrop(TALL_BEACH_GRASS, tallPlantDrops(TALL_BEACH_GRASS, BEACH_GRASS));
      this.addDrop(TALL_SEDGE_GRASS, tallPlantDrops(TALL_SEDGE_GRASS, SEDGE_GRASS));
      this.addDrop(LARGE_FLAXEN_FERN, tallPlantDrops(LARGE_FLAXEN_FERN, FLAXEN_FERN));
      this.addDrop(TALL_OAT_GRASS, tallPlantDrops(TALL_OAT_GRASS, OAT_GRASS));
      this.addDrop(LARGE_LUSH_FERN, tallPlantDrops(LARGE_LUSH_FERN, LUSH_FERN));
      this.addDrop(TALL_MELIC_GRASS, tallPlantDrops(TALL_MELIC_GRASS, MELIC_GRASS));

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
