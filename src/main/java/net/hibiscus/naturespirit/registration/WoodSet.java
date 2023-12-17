package net.hibiscus.naturespirit.registration;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.blocks.*;
import net.hibiscus.naturespirit.datagen.HibiscusConfiguredFeatures;
import net.hibiscus.naturespirit.entity.HibiscusBoatEntity;
import net.hibiscus.naturespirit.items.HibiscusBoatItem;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.block.SaplingGenerator;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static net.hibiscus.naturespirit.registration.HibiscusRegistryHelper.*;

public class WoodSet {


   private ItemConvertible leavesBefore;
   private ItemConvertible saplingBefore;
   private ItemConvertible logBefore;
   private ItemConvertible signBefore;
   private ItemConvertible boatBefore;
   private ItemConvertible buttonBefore;
   private List<Block> registeredBlocksList = new ArrayList<>();
   private List<Item> registeredItemsList = new ArrayList<>();
   private Identifier name;
   private MapColor sideColor;
   private MapColor topColor;
   private WoodPreset woodPreset;
   private BlockSetType blockSetType;
   private WoodType woodType;
   private Block log;
   private Block strippedLog;
   private Block bundle;
   private Block strippedBundle;
   private Block wood;
   private Block strippedWood;
   private Block leaves;
   private Block sapling;
   private Block pottedSapling;
   private Block redLeaves;
   private Block redSapling;
   private Block pottedRedSapling;
   private Block orangeLeaves;
   private Block orangeSapling;
   private Block pottedOrangeSapling;
   private Block yellowLeaves;
   private Block yellowSapling;
   private Block pottedYellowSapling;
   private Block blueLeaves;
   private Block partBlueLeaves;
   private Block blueSapling;
   private Block pottedBlueSapling;
   private Block purpleLeaves;
   private Block partPurpleLeaves;
   private Block purpleSapling;
   private Block pottedPurpleSapling;
   private Block pinkLeaves;
   private Block partPinkLeaves;
   private Block pinkSapling;
   private Block pottedPinkSapling;
   private Block whiteLeaves;
   private Block partWhiteLeaves;
   private Block whiteSapling;
   private Block pottedWhiteSapling;
   private static Block willowVines;
   private static Block willowVinesPlant;
   private Block blueWisteriaVines;
   private Block purpleWisteriaVines;
   private Block pinkWisteriaVines;
   private Block whiteWisteriaVines;
   private Block blueWisteriaVinesPlant;
   private Block purpleWisteriaVinesPlant;
   private Block pinkWisteriaVinesPlant;
   private Block whiteWisteriaVinesPlant;
   private Block planks;
   private Block stairs;
   private Block slab;
   private Block mosaic;
   private Block mosaicStairs;
   private Block mosaicSlab;
   private Block fence;
   private Block fenceGate;
   private Block pressurePlate;
   private Block button;
   private Block door;
   private Block trapDoor;
   private Block sign;
   private Block wallSign;
   private Block hangingSign;
   private Block hangingWallSign;
   private Item signItem;
   private Item hangingSignItem;
   private Item boatItem;
   private Item chestBoatItem;
   private TagKey <Item> itemLogsTag;
   private TagKey <Block> blockLogsTag;
   private HibiscusBoatEntity.HibiscusBoat boatType;
   private EntityType<BoatEntity> boatEntityType;
   private EntityType<BoatEntity> chestBoatEntityType;
   private SaplingGenerator saplingGenerator;
   private Optional<RegistryKey<ConfiguredFeature<?, ?>>> configuredFeature;
   private Optional<RegistryKey<ConfiguredFeature<?, ?>>> configuredFeature2;
   private boolean hasLargeTree;
   private boolean hasMosaic;



   private void registerWood(){
      blockSetType = createBlockSetType();
      woodType = new WoodTypeBuilder().register(this.getNameID(), this.getBlockSetType());

      log = this.getWoodPreset() == WoodPreset.JOSHUA ? createJoshuaLog() : createLog();
      strippedLog = this.getWoodPreset() == WoodPreset.JOSHUA ? createStrippedJoshuaLog() : createStrippedLog();

      if (this.getWoodPreset() == WoodPreset.JOSHUA) {
         bundle = createBundle();
         strippedBundle = createStrippedBundle();
         StrippableBlockRegistry.register(bundle, strippedBundle);
         FlammableBlockRegistry.getDefaultInstance().add(this.getBundle(), 5, 5);
         FlammableBlockRegistry.getDefaultInstance().add(this.getStrippedBundle(), 5, 5);
      } else {
         StrippableBlockRegistry.register(log, strippedLog);
      }

      if (this.getWoodPreset() != WoodPreset.BAMBOO && this.getWoodPreset() != WoodPreset.JOSHUA) {
         wood = createWood();
         strippedWood = createStrippedWood();
         StrippableBlockRegistry.register(wood, strippedWood);
      }

      if (this.hasDefaultLeaves()){
         leaves = createLeaves();
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(this.getLeavesBefore(), this.getLeaves()));

         if (this.hasDefaultSapling()) {
            saplingGenerator = new SaplingGenerator(NatureSpirit.MOD_ID + "_" + this.getName(), Optional.empty(), configuredFeature, configuredFeature2);
            sapling = this.isSandy() ? createSandySapling(saplingGenerator) : createSapling(saplingGenerator);
            pottedSapling = createPottedSapling(this.getSapling());
            ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(this.getSaplingBefore(), this.getSapling().asItem()));
            SaplingHashMap.put(this.getName(), new Block[]{this.getSapling(), this.getPottedSapling()});
         }
      }

      if (this.getWoodPreset() == WoodPreset.WILLOW){
         leaves = createWillowLeaves();
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(this.getLeavesBefore(), this.getLeaves().asItem()));

         willowVines = createWillowVines();
         willowVinesPlant = createWillowVinesPlant(getWillowVines());
         RenderLayerHashMap.put(this.getName() + "_vines", getWillowVines());
         RenderLayerHashMap.put(this.getName() + "_vines_plant", getWillowVinesPlant());
         CompostingChanceRegistry.INSTANCE.add(getWillowVines(), 0.3F);

         saplingGenerator = new SaplingGenerator(NatureSpirit.MOD_ID + "_" + this.getName(), Optional.empty(), configuredFeature, configuredFeature2);
         sapling = createSapling(saplingGenerator);
         pottedSapling = createPottedSapling(this.getSapling());
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(this.getSaplingBefore(), this.getSapling().asItem()));
         SaplingHashMap.put(this.getName(), new Block[]{this.getSapling(), this.getPottedSapling()});
      }
      if (this.getWoodPreset() == WoodPreset.WISTERIA) {
         whiteLeaves = createWisteriaLeaves("white_");
         blueLeaves = createWisteriaLeaves("blue_");
         pinkLeaves = createWisteriaLeaves("pink_");
         purpleLeaves = createWisteriaLeaves("purple_");
         partWhiteLeaves = createWisteriaLeaves("part_white_");
         partBlueLeaves = createWisteriaLeaves("part_blue_");
         partPinkLeaves = createWisteriaLeaves("part_pink_");
         partPurpleLeaves = createWisteriaLeaves("part_purple_");
         whiteWisteriaVines = createWisteriaVines("white_");
         blueWisteriaVines = createWisteriaVines("blue_");
         pinkWisteriaVines = createWisteriaVines("pink_");
         purpleWisteriaVines = createWisteriaVines("purple_");
         whiteWisteriaVinesPlant = createWisteriaVinesPlant("white_", this.getWhiteWisteriaVines());
         blueWisteriaVinesPlant = createWisteriaVinesPlant("blue_", this.getBlueWisteriaVines());
         pinkWisteriaVinesPlant = createWisteriaVinesPlant("pink_", this.getPinkWisteriaVines());
         purpleWisteriaVinesPlant = createWisteriaVinesPlant("purple_", this.getPurpleWisteriaVines());
         whiteSapling = createSapling("white_", new SaplingGenerator(NatureSpirit.MOD_ID + "_" + this.getName(), Optional.empty(), Optional.of(HibiscusConfiguredFeatures.WHITE_WISTERIA_TREE), Optional.empty()));
         blueSapling = createSapling("blue_", new SaplingGenerator(NatureSpirit.MOD_ID + "_" + this.getName(), Optional.empty(), Optional.of(HibiscusConfiguredFeatures.BLUE_WISTERIA_TREE), Optional.empty()));
         pinkSapling = createSapling("pink_", new SaplingGenerator(NatureSpirit.MOD_ID + "_" + this.getName(), Optional.empty(), Optional.of(HibiscusConfiguredFeatures.PINK_WISTERIA_TREE), Optional.empty()));
         purpleSapling = createSapling("purple_", new SaplingGenerator(NatureSpirit.MOD_ID + "_" + this.getName(), Optional.empty(), Optional.of(HibiscusConfiguredFeatures.PURPLE_WISTERIA_TREE), Optional.empty()));
         pottedWhiteSapling = createPottedSapling("white_",this.getWhiteSapling());
         pottedBlueSapling = createPottedSapling("blue_", this.getBlueSapling());
         pottedPinkSapling = createPottedSapling("pink_", this.getPinkSapling());
         pottedPurpleSapling = createPottedSapling("purple_",this.getPurpleSapling());
         RenderLayerHashMap.put("white_" + this.getName() + "_vines", this.getWhiteWisteriaVines());
         RenderLayerHashMap.put("blue_" + this.getName() + "_vines", this.getBlueWisteriaVines());
         RenderLayerHashMap.put("pink_" + this.getName() + "_vines", this.getPinkWisteriaVines());
         RenderLayerHashMap.put("purple_" + this.getName() + "_vines", this.getPurpleWisteriaVines());
         RenderLayerHashMap.put("white_" + this.getName() + "_vines_plant", this.getWhiteWisteriaVinesPlant());
         RenderLayerHashMap.put("blue_" + this.getName() + "_vines_plant", this.getBlueWisteriaVinesPlant());
         RenderLayerHashMap.put("pink_" + this.getName() + "_vines_plant", this.getPinkWisteriaVinesPlant());
         RenderLayerHashMap.put("purple_" + this.getName() + "_vines_plant", this.getPurpleWisteriaVinesPlant());
         CompostingChanceRegistry.INSTANCE.add(this.getWhiteWisteriaVines(), 0.3F);
         CompostingChanceRegistry.INSTANCE.add(this.getBlueWisteriaVines(), 0.3F);
         CompostingChanceRegistry.INSTANCE.add(this.getPinkWisteriaVines(), 0.3F);
         CompostingChanceRegistry.INSTANCE.add(this.getPurpleWisteriaVines(), 0.3F);
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(this.getLeavesBefore(), this.getWhiteLeaves().asItem(), this.getPartWhiteLeaves(), this.getBlueLeaves(), this.getPartBlueLeaves(), this.getPinkLeaves(), this.getPartPinkLeaves(), this.getPurpleLeaves(), this.getPartPurpleLeaves()));
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(Blocks.VINE, this.getWhiteWisteriaVines().asItem()));
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(this.getWhiteWisteriaVines(), this.getBlueWisteriaVines().asItem()));
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(this.getBlueWisteriaVines(), this.getPinkWisteriaVines().asItem()));
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(this.getPinkWisteriaVines(), this.getPurpleWisteriaVines().asItem()));
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(this.getSaplingBefore(), this.getWhiteSapling().asItem()));
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(this.getWhiteSapling(), this.getBlueSapling().asItem()));
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(this.getBlueSapling(), this.getPinkSapling().asItem()));
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(this.getPinkSapling(), this.getPurpleSapling().asItem()));
         SaplingHashMap.put("white_" + this.getName(), new Block[]{this.getWhiteSapling(), this.getPottedWhiteSapling()});
         SaplingHashMap.put("blue_" + this.getName(), new Block[]{this.getBlueSapling(), this.getPottedBlueSapling()});
         SaplingHashMap.put("pink_" + this.getName(), new Block[]{this.getPinkSapling(), this.getPottedPinkSapling()});
         SaplingHashMap.put("purple_" + this.getName(), new Block[]{this.getPurpleSapling(), this.getPottedPurpleSapling()});
         SaplingHashMap.put("part_white_" + this.getName(), new Block[]{this.getWhiteSapling(), this.getPottedWhiteSapling()});
         SaplingHashMap.put("part_blue_" + this.getName(), new Block[]{this.getBlueSapling(), this.getPottedBlueSapling()});
         SaplingHashMap.put("part_pink_" + this.getName(), new Block[]{this.getPinkSapling(), this.getPottedPinkSapling()});
         SaplingHashMap.put("part_purple_" + this.getName(), new Block[]{this.getPurpleSapling(), this.getPottedPurpleSapling()});
      }
      if (this.getWoodPreset() == WoodPreset.MAPLE) {
         redLeaves = createMapleLeaves("red_", NatureSpirit.RED_MAPLE_LEAVES_PARTICLE);
         orangeLeaves = createMapleLeaves("orange_", NatureSpirit.ORANGE_MAPLE_LEAVES_PARTICLE);
         yellowLeaves = createMapleLeaves("yellow_", NatureSpirit.YELLOW_MAPLE_LEAVES_PARTICLE);
         redSapling = createSapling("red_", new SaplingGenerator(NatureSpirit.MOD_ID + "_" + this.getName(), Optional.empty(), Optional.of(HibiscusConfiguredFeatures.RED_MAPLE_TREE), Optional.empty()));
         orangeSapling = createSapling("orange_", new SaplingGenerator(NatureSpirit.MOD_ID + "_" + this.getName(), Optional.empty(), Optional.of(HibiscusConfiguredFeatures.ORANGE_MAPLE_TREE), Optional.empty()));
         yellowSapling = createSapling("yellow_", new SaplingGenerator(NatureSpirit.MOD_ID + "_" + this.getName(), Optional.empty(), Optional.of(HibiscusConfiguredFeatures.YELLOW_MAPLE_TREE), Optional.empty()));
         pottedRedSapling = createPottedSapling("red_", this.getRedSapling());
         pottedOrangeSapling = createPottedSapling("orange_", this.getOrangeSapling());
         pottedYellowSapling = createPottedSapling("yellow_", this.getYellowSapling());
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(this.getLeavesBefore(), this.getRedLeaves().asItem()));
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(this.getRedLeaves(), this.getOrangeLeaves().asItem()));
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(this.getOrangeLeaves(), this.getYellowLeaves().asItem()));
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(this.getSaplingBefore(), this.getRedSapling().asItem()));
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(this.getRedSapling(), this.getOrangeSapling().asItem()));
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(this.getOrangeSapling(), this.getYellowSapling().asItem()));
         SaplingHashMap.put("red_" + this.getName(), new Block[]{this.getRedSapling(), this.getPottedRedSapling()});
         SaplingHashMap.put("orange_" + this.getName(), new Block[]{this.getOrangeSapling(), this.getPottedOrangeSapling()});
         SaplingHashMap.put("yellow_" + this.getName(), new Block[]{this.getYellowSapling(), this.getPottedYellowSapling()});
      }
      if (this.getWoodPreset() == WoodPreset.LARCH) {
         yellowLeaves = createLeaves("yellow_");
         yellowSapling = createSapling("yellow_", new SaplingGenerator(NatureSpirit.MOD_ID + "_" + this.getName(), Optional.empty(), Optional.of(HibiscusConfiguredFeatures.YELLOW_LARCH_TREE), Optional.empty()));
         pottedYellowSapling = createPottedSapling("yellow_", this.getYellowSapling());
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(this.getLeaves(), this.getYellowLeaves().asItem()));
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(this.getSapling(), this.getYellowSapling().asItem()));
         SaplingHashMap.put("yellow_" + this.getName(), new Block[]{this.getYellowSapling(), this.getPottedYellowSapling()});
      }
      if (this.hasMosaic()){
         mosaic = createMosaic();
         mosaicStairs = createMosaicStairs();
         mosaicSlab = createMosaicSlab();
         FlammableBlockRegistry.getDefaultInstance().add(this.getMosaic(), 5, 20);
         FlammableBlockRegistry.getDefaultInstance().add(this.getMosaicSlab(), 5, 20);
         FlammableBlockRegistry.getDefaultInstance().add(this.getMosaicStairs(), 5, 20);
         FuelRegistry.INSTANCE.add(this.getMosaic(), 300);
         FuelRegistry.INSTANCE.add(this.getMosaicStairs(), 300);
         FuelRegistry.INSTANCE.add(this.getMosaicSlab(), 150);
      }
      planks = createPlanks();
      stairs = createStairs();
      slab = createSlab();
      fence = createFence();
      fenceGate = createFenceGate();
      pressurePlate = createPressurePlate();
      button = createButton();
      door = createDoor();
      trapDoor = createTrapDoor();
      sign = createSign();
      wallSign = createWallSign();
      hangingSign = createHangingSign();
      hangingWallSign = createWallHangingSign();
      signItem = createSignItem();
      hangingSignItem = createHangingSignItem();

      boatEntityType = HibiscusEntityTypes.registerEntityType(this.getName() + "_boat", HibiscusEntityTypes.createBoatType(false, this.getboatType()));
      chestBoatEntityType = HibiscusEntityTypes.registerEntityType(this.getName() + "_chest_boat", HibiscusEntityTypes.createBoatType(true, this.getboatType()));

      boatItem = createItem(this.getName() + "_boat",
              new HibiscusBoatItem(false, this.getboatType(), new Item.Settings())
      );
      chestBoatItem = createItem(this.getName() + "_chest_boat",
              new HibiscusBoatItem(true, this.getboatType(), new Item.Settings())
      );
      RenderLayerHashMap.put(this.getName() + "_door", this.getDoor());
      RenderLayerHashMap.put(this.getName() + "_trapdoor", this.getTrapDoor());

      FlammableBlockRegistry.getDefaultInstance().add(this.getStrippedLog(), 5, 5);
      FlammableBlockRegistry.getDefaultInstance().add(this.getLog(), 5, 5);
      FlammableBlockRegistry.getDefaultInstance().add(this.getStairs(), 5, 20);
      FlammableBlockRegistry.getDefaultInstance().add(this.getSlab(), 5, 20);
      FlammableBlockRegistry.getDefaultInstance().add(this.getPlanks(), 5, 20);
      FlammableBlockRegistry.getDefaultInstance().add(this.getFence(), 5, 20);
      FlammableBlockRegistry.getDefaultInstance().add(this.getFenceGate(), 5, 20);

      FuelRegistry.INSTANCE.add(this.getFence(), 300);
      FuelRegistry.INSTANCE.add(this.getFenceGate(), 300);

      blockLogsTag = TagKey.of(RegistryKeys.BLOCK, new Identifier(this.getModID(), this.getName() + "_logs"));
      itemLogsTag = TagKey.of(RegistryKeys.ITEM, new Identifier(this.getModID(), this.getName() + "_logs"));
      addToBuildingTab(getButtonBefore(), getLogBefore(), getSignBefore(), getBoatBefore(), this);

      for(Block item : this.getRegisteredBlocksList()) ItemGroupEvents.modifyEntriesEvent(HibiscusItemGroups.NS_WOOD_ITEM_GROUP).register(entries -> entries.add(item));
      for(Item item : this.getRegisteredItemsList()) ItemGroupEvents.modifyEntriesEvent(HibiscusItemGroups.NS_WOOD_ITEM_GROUP).register(entries -> entries.add(item));
   }



   public WoodSet(Identifier name, MapColor sideColor, MapColor topColor, ItemConvertible leavesBefore, ItemConvertible logBefore, ItemConvertible signBefore, ItemConvertible boatBefore, ItemConvertible buttonBefore, ItemConvertible saplingBefore,HibiscusBoatEntity.HibiscusBoat boatType, WoodPreset woodPreset, boolean hasMosaic, RegistryKey<ConfiguredFeature<?, ?>> configuredFeature){
      this.woodPreset = woodPreset;
      this.name = name;
      this.sideColor = sideColor;
      this.topColor = topColor;
      this.leavesBefore = leavesBefore;
      this.logBefore = logBefore;
      this.signBefore = signBefore;
      this.boatBefore = boatBefore;
      this.buttonBefore = buttonBefore;
      this.saplingBefore = saplingBefore;
      this.boatType = boatType;
      this.hasMosaic = hasMosaic;
      this.configuredFeature = Optional.of(configuredFeature);
      this.configuredFeature2 = Optional.empty();
      registerWood();
      WoodHashMap.put(this.getName(), this);
   }
   public WoodSet(Identifier name, MapColor sideColor, MapColor topColor, ItemConvertible leavesBefore, ItemConvertible logBefore, ItemConvertible signBefore, ItemConvertible boatBefore, ItemConvertible buttonBefore, ItemConvertible saplingBefore,HibiscusBoatEntity.HibiscusBoat boatType, WoodPreset woodPreset, boolean hasMosaic, Optional<RegistryKey<ConfiguredFeature<?, ?>>> configuredFeature, Optional<RegistryKey<ConfiguredFeature<?, ?>>> configuredFeature2){
      this.woodPreset = woodPreset;
      this.name = name;
      this.sideColor = sideColor;
      this.topColor = topColor;
      this.leavesBefore = leavesBefore;
      this.logBefore = logBefore;
      this.signBefore = signBefore;
      this.boatBefore = boatBefore;
      this.buttonBefore = buttonBefore;
      this.saplingBefore = saplingBefore;
      this.boatType = boatType;
      this.hasMosaic = hasMosaic;
      this.configuredFeature = configuredFeature;
      this.configuredFeature2 = configuredFeature2;
      this.hasLargeTree = true;
      registerWood();
      WoodHashMap.put(this.getName(), this);
   }


   private Block createBlockWithItem(String blockID, Block block){
      registerBlockItem(blockID, block);
      Block listBlock = HibiscusRegistryHelper.registerBlock(blockID, block);
      registeredBlocksList.add(listBlock);
      return listBlock;
   }

   private Item createSignItem(Block sign, Block wallSign) {
      return new SignItem(new FabricItemSettings().maxCount(16), sign, wallSign);
   }
   private Item createHangingSignItem(Block hangingSign, Block hangingWallSign) {
      return new HangingSignItem(hangingSign, hangingWallSign, new FabricItemSettings().maxCount(16));
   }
   public Item createItem(String blockID, Item item){
      Item listItem = registerItem(blockID, item);
      registeredItemsList.add(listItem);
      return listItem;
   }

   private PillarBlock createLogBlock(MapColor topMapColor, MapColor sideMapColor) {
      return new PillarBlock(AbstractBlock.Settings.create().mapColor(state -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor).strength(2.0F).sounds(this.woodType().soundType()));
   }

   private BlockSetType woodType() {
      return this.blockSetType;
   }

   public Identifier getNameID() {
      return name;
   }
   public String getName() {
      return name.getPath();
   }
   public String getModID() {
      return name.getNamespace();
   }
   public BlockSetType getBlockSetType() {
      return this.blockSetType;
   }
   public WoodPreset getWoodPreset() {
      return woodPreset;
   }
   public MapColor getSideColor() {
      return sideColor;
   }
   public MapColor getTopColor() {
      return topColor;
   }
   public WoodType getWoodType() {
      return woodType;
   }
   public Block getButton() {
      return button;
   }
   public Block getFence() {
      return fence;
   }
   public Block getPlanks() {
      return planks;
   }
   public Block getSlab() {
      return slab;
   }
   public Block getFenceGate() {
      return fenceGate;
   }
   public Block getStairs() {
      return stairs;
   }
   public Block getDoor() {
      return door;
   }
   public Block getHangingSign() {
      return hangingSign;
   }
   public Block getHangingWallSign() {
      return hangingWallSign;
   }
   public Block getPressurePlate() {
      return pressurePlate;
   }
   public Block getSign() {
      return sign;
   }
   public Block getTrapDoor() {
      return trapDoor;
   }
   public Block getWallSign() {
      return wallSign;
   }
   public Item getHangingSignItem() {
      return hangingSignItem;
   }
   public Item getSignItem() {
      return signItem;
   }
   public Item getBoatItem() {
      return boatItem;
   }
   public Item getChestBoatItem() {
      return chestBoatItem;
   }
   public Block getLog() {
      return log;
   }
   public Block getStrippedLog() {
      return strippedLog;
   }
   public Block getBundle() {
      return bundle;
   }
   public Block getStrippedBundle() {
      return strippedBundle;
   }
   public Block getWood() {
      return wood;
   }
   public Block getStrippedWood() {
      return strippedWood;
   }
   public Block getMosaic() {
      return mosaic;
   }
   public Block getMosaicStairs() {
      return mosaicStairs;
   }
   public Block getMosaicSlab() {
      return mosaicSlab;
   }
   public Block getLeaves() {return leaves;}
   public Block getSapling() {return sapling;}
   public Block getPottedSapling() {return pottedSapling;}
   public static Block getWillowVines() {return willowVines;}
   public static Block getWillowVinesPlant() {
      return willowVinesPlant;
   }
   public Block getRedLeaves() {
      return redLeaves;
   }
   public Block getOrangeLeaves() {
      return orangeLeaves;
   }
   public Block getYellowLeaves() {
      return yellowLeaves;
   }
   public Block getBlueLeaves() {
      return blueLeaves;
   }
   public Block getPurpleLeaves() {
      return purpleLeaves;
   }
   public Block getPinkLeaves() {
      return pinkLeaves;
   }
   public Block getWhiteLeaves() {
      return whiteLeaves;
   }
   public Block getPottedRedSapling() {
      return pottedRedSapling;
   }
   public Block getPottedOrangeSapling() {
      return pottedOrangeSapling;
   }
   public Block getPottedYellowSapling() {
      return pottedYellowSapling;
   }
   public Block getPottedBlueSapling() {
      return pottedBlueSapling;
   }
   public Block getPottedPurpleSapling() {
      return pottedPurpleSapling;
   }
   public Block getPottedPinkSapling() {
      return pottedPinkSapling;
   }
   public Block getPottedWhiteSapling() {
      return pottedWhiteSapling;
   }
   public Block getRedSapling() {
      return redSapling;
   }
   public Block getOrangeSapling() {
      return orangeSapling;
   }
   public Block getYellowSapling() {
      return yellowSapling;
   }
   public Block getBlueSapling() {
      return blueSapling;
   }
   public Block getPurpleSapling() {
      return purpleSapling;
   }
   public Block getPinkSapling() {
      return pinkSapling;
   }
   public Block getWhiteSapling() {
      return whiteSapling;
   }
   public Block getBlueWisteriaVines() {return blueWisteriaVines;}
   public Block getPurpleWisteriaVines() {return purpleWisteriaVines;}
   public Block getPinkWisteriaVines() {return pinkWisteriaVines;}
   public Block getWhiteWisteriaVines() {return whiteWisteriaVines;}
   public Block getBlueWisteriaVinesPlant() {return blueWisteriaVinesPlant;}
   public Block getPurpleWisteriaVinesPlant() {return purpleWisteriaVinesPlant;}
   public Block getPinkWisteriaVinesPlant() {return pinkWisteriaVinesPlant;}
   public Block getWhiteWisteriaVinesPlant() {return whiteWisteriaVinesPlant;}


   public HibiscusBoatEntity.HibiscusBoat getboatType() {
      return boatType;
   }
   public EntityType <BoatEntity> getBoatEntityType() {
      return boatEntityType;
   }
   public EntityType <BoatEntity> getChestBoatEntityType() {
      return chestBoatEntityType;
   }

   public TagKey <Item> getItemLogsTag() {
      return itemLogsTag;
   }
   public TagKey <Block> getBlockLogsTag() {
      return blockLogsTag;
   }

   public ItemConvertible getButtonBefore() {return buttonBefore;}
   public ItemConvertible getLeavesBefore() {return leavesBefore;}
   public ItemConvertible getSaplingBefore() {return saplingBefore;}
   public ItemConvertible getLogBefore() {return logBefore;}
   public ItemConvertible getSignBefore() {return signBefore;}
   public ItemConvertible getBoatBefore() {return boatBefore;}


   public List<Block> getRegisteredBlocksList() {
      return registeredBlocksList;
   }

   public List<Item> getRegisteredItemsList() {
      return registeredItemsList;
   }

   private Block createLog() {
      return createBlockWithItem(getLogName(), createLogBlock(this.getSideColor(), this.getTopColor()));
   }
   private Block createStrippedLog() {
      return createBlockWithItem("stripped_" + getLogName(), createLogBlock(this.getSideColor(), this.getTopColor()));
   }
   private Block createBundle() {
      return createBlockWithItem( this.getName() + "_bundle", createLogBlock(this.getSideColor(), this.getTopColor()));
   }
   private Block createStrippedBundle() {
      return createBlockWithItem("stripped_" + this.getName() + "_bundle", createLogBlock(this.getSideColor(), this.getTopColor()));
   }
   private Block createJoshuaLog() {
      return createBlockWithItem(getLogName(), new JoshuaTrunkBlock(FabricBlockSettings.create().burnable().mapColor(MapColor.GRAY).instrument(Instrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD)));
   }
   private Block createStrippedJoshuaLog() {
      return createBlockWithItem("stripped_" + getLogName(), new JoshuaTrunkBlock(FabricBlockSettings.create().burnable().mapColor(MapColor.GRAY).instrument(Instrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD)));
   }
   private Block createWood() {
      return createBlockWithItem(getWoodName(), createLogBlock(this.getSideColor(), this.getSideColor()));
   }
   private Block createStrippedWood() {
      return createBlockWithItem("stripped_" + getWoodName(), createLogBlock(this.getTopColor(), this.getTopColor()));
   }
   private Block createLeaves() {
      Block block = createBlockWithItem(this.getName() + "_leaves", new LeavesBlock(AbstractBlock.Settings.copy(getBaseLeaves())));
      RenderLayerHashMap.put(this.getName() + "_leaves", block);
      CompostingChanceRegistry.INSTANCE.add(block, 0.3F);
      FlammableBlockRegistry.getDefaultInstance().add(block, 5, 20);
      LeavesHashMap.put(this.getName(), block);
      return block;
   }
   private Block createLeaves(String prefix) {
      Block block = createBlockWithItem(prefix + this.getName() + "_leaves", new LeavesBlock(AbstractBlock.Settings.copy(getBaseLeaves())));
      RenderLayerHashMap.put(prefix + this.getName() + "_leaves", block);
      CompostingChanceRegistry.INSTANCE.add(block, 0.3F);
      FlammableBlockRegistry.getDefaultInstance().add(block, 5, 20);
      LeavesHashMap.put(prefix + this.getName(), block);
      return block;
   }
   private Block createMapleLeaves(String prefix, ParticleEffect particle) {
      Block block = createBlockWithItem(prefix + this.getName() + "_leaves", new MapleLeavesBlock(AbstractBlock.Settings.copy(getBaseLeaves()), particle));
      RenderLayerHashMap.put(prefix + this.getName() + "_leaves", block);
      CompostingChanceRegistry.INSTANCE.add(block, 0.3F);
      FlammableBlockRegistry.getDefaultInstance().add(block, 5, 20);
      LeavesHashMap.put(prefix + this.getName(), block);
      return block;
   }
   private Block createWisteriaLeaves(String prefix) {
      Block block = createBlockWithItem(prefix + this.getName() + "_leaves", new WisteriaLeaves(AbstractBlock.Settings.copy(getBaseLeaves())));
      RenderLayerHashMap.put(prefix + this.getName() + "_leaves", block);
      CompostingChanceRegistry.INSTANCE.add(block, 0.3F);
      FlammableBlockRegistry.getDefaultInstance().add(block, 5, 20);
      LeavesHashMap.put(prefix + this.getName(), block);
      return block;
   }
   private Block createWisteriaVines(String prefix) {
      return createBlockWithItem(prefix + this.getName() + "_vines",
              new WisteriaVine(FabricBlockSettings
              .create()
              .pistonBehavior(PistonBehavior.DESTROY)
              .ticksRandomly()
              .noCollision()
              .nonOpaque()
              .breakInstantly()
              .sounds(BlockSoundGroup.WEEPING_VINES)));
   }
   private Block createWisteriaVinesPlant(String prefix, Block vines) {
      return registerBlock(prefix + this.getName() + "_vines_plant", new WisteriaVinePlant(FabricBlockSettings
              .create()
              .pistonBehavior(PistonBehavior.DESTROY)
              .noCollision()
              .nonOpaque()
              .breakInstantly()
              .sounds(BlockSoundGroup.WEEPING_VINES)
              .dropsLike(vines), vines));
   }
   private Block createWillowLeaves() {
      Block block = createBlockWithItem(this.getName() + "_leaves", new WillowLeaves(AbstractBlock.Settings.copy(getBaseLeaves())));
      FlammableBlockRegistry.getDefaultInstance().add(block, 5, 20);
      RenderLayerHashMap.put(this.getName() + "_leaves", block);
      CompostingChanceRegistry.INSTANCE.add(block, 0.3F);
      LeavesHashMap.put(this.getName(), block);
      return block;
   }
   private Block createWillowVines() {
      return createBlockWithItem(this.getName() + "_vines",
              new WillowVine(FabricBlockSettings
                      .create()
                      .pistonBehavior(PistonBehavior.DESTROY)
                      .ticksRandomly()
                      .noCollision()
                      .nonOpaque()
                      .breakInstantly()
                      .sounds(BlockSoundGroup.WEEPING_VINES)));
   }
   private Block createWillowVinesPlant(Block vines) {
      return registerBlock(this.getName() + "_vines_plant", new WillowVinePlant(FabricBlockSettings
              .create()
              .pistonBehavior(PistonBehavior.DESTROY)
              .noCollision()
              .nonOpaque()
              .breakInstantly()
              .sounds(BlockSoundGroup.WEEPING_VINES)
              .dropsLike(vines)));
   }
   private Block createPlanks(){
      return createBlockWithItem(this.getName() + "_planks", new Block(AbstractBlock.Settings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor())));
   }
   private Block createStairs(){
      return createBlockWithItem(this.getName() + "_stairs", new StairsBlock(getBase().getDefaultState(), AbstractBlock.Settings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor())));
   }
   private Block createSlab(){
      return createBlockWithItem(this.getName() + "_slab", new SlabBlock(AbstractBlock.Settings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor())));
   }
   private Block createMosaic(){
      return createBlockWithItem(this.getName() + "_mosaic", new Block(AbstractBlock.Settings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor())));
   }
   private Block createMosaicStairs(){
      return createBlockWithItem(this.getName() + "_mosaic_stairs", new StairsBlock(getBase().getDefaultState(), AbstractBlock.Settings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor())));
   }
   private Block createMosaicSlab(){
      return createBlockWithItem(this.getName() + "_mosaic_slab", new SlabBlock(AbstractBlock.Settings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor())));
   }
   private Block createFence(){
      return createBlockWithItem(this.getName() + "_fence", new FenceBlock(AbstractBlock.Settings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor())));
   }
   private Block createFenceGate(){
      return createBlockWithItem(this.getName() + "_fence_gate", new FenceGateBlock(this.getWoodType(), AbstractBlock.Settings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor())));
   }
   private Block createPressurePlate(){
      return createBlockWithItem(this.getName() + "_pressure_plate", new PressurePlateBlock(this.getBlockSetType(), AbstractBlock.Settings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor())));
   }
   private Block createButton(){
      return createBlockWithItem(this.getName() + "_button", new ButtonBlock(this.getBlockSetType(), 30, AbstractBlock.Settings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor())));
   }
   private Block createDoor(){
      return createBlockWithItem(this.getName() + "_door", new DoorBlock(this.getBlockSetType(), AbstractBlock.Settings.copy(getBase()).sounds(getBlockSetType().soundType()).nonOpaque().mapColor(getTopColor())));
   }
   private Block createTrapDoor(){
      return createBlockWithItem(this.getName() + "_trapdoor", new TrapdoorBlock(this.getBlockSetType(), AbstractBlock.Settings.copy(getBase()).sounds(getBlockSetType().soundType()).nonOpaque().mapColor(getTopColor())));
   }
   private Block createSign(){
      return registerBlock(this.getName() + "_sign", new SignBlock(woodType, AbstractBlock.Settings.copy(getSignBase()).mapColor(this.getTopColor())));
   }
   private Block createWallSign(){
      return registerBlock(this.getName() + "_wall_sign", new WallSignBlock(woodType, AbstractBlock.Settings.copy(getSignBase()).mapColor(this.getTopColor()).dropsLike(sign)));
   }
   private Block createHangingSign(){
      return registerBlock(this.getName() + "_hanging_sign", new HangingSignBlock(woodType, AbstractBlock.Settings.copy(getHangingSignBase()).mapColor(this.getTopColor())));
   }
   private Block createWallHangingSign(){
      return registerBlock(this.getName() + "_wall_hanging_sign", new WallHangingSignBlock(woodType, AbstractBlock.Settings.copy(getHangingSignBase()).mapColor(this.getTopColor()).dropsLike(hangingSign)));
   }

   public Block createSapling(SaplingGenerator saplingGenerator) {
      Block block = createBlockWithItem(this.getName() + "_sapling", new SaplingBlock(saplingGenerator, FabricBlockSettings.copy(Blocks.SPRUCE_SAPLING)));
      CompostingChanceRegistry.INSTANCE.add(block, 0.3F);
      RenderLayerHashMap.put(this.getName() + "_sapling", block);
      return block;
   }
   public Block createSandySapling(SaplingGenerator saplingGenerator) {
      Block block = createBlockWithItem(this.getName() + "_sapling", new SandySaplingBlock(saplingGenerator, FabricBlockSettings.copy(Blocks.SPRUCE_SAPLING)));
      CompostingChanceRegistry.INSTANCE.add(block, 0.3F);
      RenderLayerHashMap.put(this.getName() + "_sapling", block);
      return block;
   }
   public Block createPottedSapling(Block sapling) {
      Block pot = registerBlock("potted_" + this.getName() + "_sapling", new FlowerPotBlock(sapling, FabricBlockSettings.create().breakInstantly().nonOpaque().pistonBehavior(PistonBehavior.DESTROY)));
      RenderLayerHashMap.put("potted_" + this.getName() + "_sapling", pot);
      return pot;
   }
   public Block createSapling(String prefix, SaplingGenerator saplingGenerator) {
      Block block = createBlockWithItem(prefix + this.getName() + "_sapling", new SaplingBlock(saplingGenerator, FabricBlockSettings.copy(Blocks.SPRUCE_SAPLING)));
      CompostingChanceRegistry.INSTANCE.add(block, 0.3F);
      RenderLayerHashMap.put(prefix + this.getName() + "_sapling", block);
      return block;
   }
   public Block createPottedSapling(String prefix, Block sapling) {
      Block pot = registerBlock("potted_" + prefix + this.getName() + "_sapling", new FlowerPotBlock(sapling, FabricBlockSettings.create().breakInstantly().nonOpaque().pistonBehavior(PistonBehavior.DESTROY)));
      RenderLayerHashMap.put("potted_" + prefix + this.getName() + "_sapling", pot);
      return pot;
   }

   private Item createSignItem(){
      return createItem(this.getName() + "_sign", createSignItem(this.getSign(), this.getWallSign()));
   }
   private Item createHangingSignItem(){
      return createItem(this.getName() + "_hanging_sign", createHangingSignItem(this.getHangingSign(), this.getHangingWallSign()));
   }
   private String getWoodName(){
      String name;
      if (this.getWoodPreset() == WoodPreset.NETHER){
         name = this.getName() + "_hyphae";
      }
      else{
         name = this.getName() + "_wood";
      }
      return name;
   }
   private String getLogName(){
      String name;
      if (this.getWoodPreset() == WoodPreset.BAMBOO){
         name = this.getName() + "_block";
      }
      else if (this.getWoodPreset() == WoodPreset.NETHER){
         name = this.getName() + "_stem";
      }
      else{
         name = this.getName() + "_log";
      }
      return name;
   }
   private Block getBaseLeaves(){
      Block base;
      if (this.getWoodPreset() == WoodPreset.FANCY){
         base = Blocks.AZALEA_LEAVES;
      }
      else{
         base = Blocks.OAK_LEAVES;
      }
      return base;
   }
   private Block getBase(){
      Block base;
      if (this.getWoodPreset() == WoodPreset.BAMBOO){
         base = Blocks.BAMBOO_PLANKS;
      }
      else if (this.getWoodPreset() == WoodPreset.FANCY){
         base = Blocks.CHERRY_PLANKS;
      }
      else if (this.getWoodPreset() == WoodPreset.NETHER){
         base = Blocks.CRIMSON_PLANKS;
      }
      else{
         base = Blocks.OAK_PLANKS;
      }
      return base;
   }
   private Block getSignBase(){
      Block base;
      if (this.getWoodPreset() == WoodPreset.BAMBOO){
         base = Blocks.BAMBOO_SIGN;
      }
      else if (this.getWoodPreset() == WoodPreset.FANCY){
         base = Blocks.CHERRY_SIGN;
      }
      else if (this.getWoodPreset() == WoodPreset.NETHER){
         base = Blocks.CRIMSON_SIGN;
      }
      else{
         base = Blocks.OAK_SIGN;
      }
      return base;
   }
   private Block getHangingSignBase(){
      Block base;
      if (this.getWoodPreset() == WoodPreset.BAMBOO){
         base = Blocks.BAMBOO_HANGING_SIGN;
      }
      else if (this.getWoodPreset() == WoodPreset.FANCY){
         base = Blocks.CHERRY_HANGING_SIGN;
      }
      else if (this.getWoodPreset() == WoodPreset.NETHER){
         base = Blocks.CRIMSON_HANGING_SIGN;
      }
      else{
         base = Blocks.OAK_HANGING_SIGN;
      }
      return base;
   }

   private BlockSetType createBlockSetType(){
      if (this.woodPreset == WoodPreset.BAMBOO){
         return BlockSetTypeBuilder.copyOf(BlockSetType.BAMBOO).register(this.getNameID());
      }
      else if (this.getWoodPreset() == WoodPreset.FANCY){
         return BlockSetTypeBuilder.copyOf(BlockSetType.CHERRY).register(this.getNameID());
      }
      else if (this.woodPreset == WoodPreset.NETHER){
         return BlockSetTypeBuilder.copyOf(BlockSetType.CRIMSON).register(this.getNameID());
      }
      else{
         return BlockSetTypeBuilder.copyOf(BlockSetType.OAK).register(this.getNameID());
      }
   }

   public boolean isSandy(){
      return this.getWoodPreset() == WoodPreset.JOSHUA || this.getWoodPreset() == WoodPreset.SANDY;
   }
   public boolean hasDefaultLeaves(){
      return this.getWoodPreset() == WoodPreset.DEFAULT || this.getWoodPreset() == WoodPreset.WISTERIA || this.getWoodPreset() == WoodPreset.LARCH || this.getWoodPreset() == WoodPreset.FANCY || this.getWoodPreset() == WoodPreset.JOSHUA || this.getWoodPreset() == WoodPreset.NO_SAPLING|| this.getWoodPreset() == WoodPreset.SANDY;
   }
   public boolean hasDefaultSapling() {
      return this.getWoodPreset() != WoodPreset.NO_SAPLING && this.getWoodPreset() != WoodPreset.WISTERIA;
   }
   public boolean hasBark(){
      return this.getWoodPreset() != WoodPreset.JOSHUA && this.getWoodPreset() != WoodPreset.BAMBOO;
   }
   public boolean hasMosaic(){
      return this.hasMosaic;
   }

   public Block getPartBlueLeaves() {
      return partBlueLeaves;
   }

   public Block getPartPurpleLeaves() {
      return partPurpleLeaves;
   }

   public Block getPartPinkLeaves() {
      return partPinkLeaves;
   }

   public Block getPartWhiteLeaves() {
      return partWhiteLeaves;
   }

   public enum WoodPreset {
      DEFAULT,
      MAPLE,
      JOSHUA,
      SANDY,
      NO_SAPLING,
      WISTERIA,
      WILLOW,
      LARCH,
      FANCY,
      NETHER,
      BAMBOO
   }

   public static void addToBuildingTab(ItemConvertible proceedingItem, ItemConvertible logPlacement, ItemConvertible signPlacement, ItemConvertible boatPlacement, WoodSet woodset){
      ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
         entries.addAfter(proceedingItem, woodset.getLog());
         if(woodset.getWoodPreset() == WoodPreset.JOSHUA) {
            entries.addAfter(woodset.getLog(), woodset.getBundle(), woodset.getStrippedLog(), woodset.getStrippedBundle(), woodset.getPlanks());
         } else if (!woodset.hasBark()) {
            entries.addAfter(woodset.getLog(), woodset.getStrippedLog(), woodset.getPlanks());
         }
         else {
            entries.addAfter(woodset.getLog(), woodset.getWood());
            entries.addAfter(woodset.getWood(), woodset.getStrippedLog(),woodset.getStrippedWood(), woodset.getPlanks());
         }
         entries.addAfter(woodset.getPlanks(), woodset.getStairs(), woodset.getSlab(),
                 woodset.getFence(), woodset.getFenceGate(),
                 woodset.getDoor(), woodset.getTrapDoor(),
                 woodset.getPressurePlate(), woodset.getButton());

         if (woodset.hasMosaic()){
            entries.addAfter(woodset.getPlanks(), woodset.getMosaic());
            entries.addAfter(woodset.getStairs(), woodset.getMosaicStairs());
            entries.addAfter(woodset.getSlab(), woodset.getMosaicSlab());
         }
      });
      ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(logPlacement, woodset.getLog().asItem()));
      ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> entries.addAfter(signPlacement, woodset.getSignItem(), woodset.getHangingSignItem()));
      ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> entries.addAfter(boatPlacement, woodset.getBoatItem(), woodset.getChestBoatItem()));
   }

}