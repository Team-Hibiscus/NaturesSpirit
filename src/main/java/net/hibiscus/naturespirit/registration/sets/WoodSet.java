package net.hibiscus.naturespirit.registration.sets;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.blocks.*;
import net.hibiscus.naturespirit.entity.NSBoatEntity;
import net.hibiscus.naturespirit.items.NSBoatItem;
import net.hibiscus.naturespirit.registration.NSEntityTypes;
import net.hibiscus.naturespirit.registration.NSItemGroups;
import net.hibiscus.naturespirit.world.tree.*;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.village.TradeOffers;

import java.util.ArrayList;
import java.util.List;

import static net.hibiscus.naturespirit.registration.NSRegistryHelper.*;

public class WoodSet {
   private final ItemConvertible leavesBefore;
   private final ItemConvertible saplingBefore;
   private final ItemConvertible logBefore;
   private final ItemConvertible signBefore;
   private final ItemConvertible boatBefore;
   private final ItemConvertible buttonBefore;
   private final List<Block> registeredBlocksList = new ArrayList<>();
   private final List<Item> registeredItemsList = new ArrayList<>();
   private final Identifier name;
   private final MapColor sideColor;
   private final MapColor topColor;
   private final WoodPreset woodPreset;
   private BlockSetType blockSetType;
   private WoodType woodType;
   private Block log;
   private Block strippedLog;
   private Block bundle;
   private Block strippedBundle;
   private Block wood;
   private Block strippedWood;
   private Block leaves;
   private Block frostyLeaves;
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
   private Block willowVines;
   private Block willowVinesPlant;
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
   private final NSBoatEntity.HibiscusBoat boatType;
   private EntityType<BoatEntity> boatEntityType;
   private EntityType<BoatEntity> chestBoatEntityType;
   private final SaplingGenerator saplingGenerator;
   private final boolean hasMosaic;



   private void registerWood(){
      blockSetType = createBlockSetType();
      woodType = new WoodTypeBuilder().register(getNameID(), blockSetType);

      log = woodPreset == WoodPreset.JOSHUA ? createJoshuaLog() : createLog();
      strippedLog = woodPreset == WoodPreset.JOSHUA ? createStrippedJoshuaLog() : createStrippedLog();

      if (woodPreset == WoodPreset.JOSHUA) {
         bundle = createBundle();
         strippedBundle = createStrippedBundle();
         StrippableBlockRegistry.register(bundle, strippedBundle);
         FlammableBlockRegistry.getDefaultInstance().add(bundle, 5, 5);
         FlammableBlockRegistry.getDefaultInstance().add(strippedBundle, 5, 5);
      } else {
         StrippableBlockRegistry.register(log, strippedLog);
      }

      if (woodPreset != WoodPreset.BAMBOO && woodPreset != WoodPreset.JOSHUA) {
         wood = createWood();
         strippedWood = createStrippedWood();
         StrippableBlockRegistry.register(wood, strippedWood);
      }

      if (this.hasDefaultLeaves()){
         leaves = createLeaves();
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(leavesBefore, leaves));

         if (this.hasDefaultSapling()) {
            sapling = this.isSandy() ? createSandySapling(saplingGenerator) : createSapling(saplingGenerator);
            pottedSapling = createPottedSapling(sapling);
            ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(saplingBefore, sapling.asItem()));
            SaplingHashMap.put(getName(), new Block[]{sapling, pottedSapling});
            TradeOfferHelper.registerWanderingTraderOffers(1, factories -> factories.add(new TradeOffers.SellItemFactory(sapling, 5, 1, 8, 1)));
         }
      }

      if (woodPreset == WoodPreset.FROSTABLE){
         frostyLeaves = createLeaves("frosty_");
         leaves = createFrostableLeaves();
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(leavesBefore, leaves, frostyLeaves));
         sapling = createSapling(saplingGenerator);
         pottedSapling = createPottedSapling(sapling);
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(saplingBefore, sapling.asItem()));
         SaplingHashMap.put(getName(), new Block[]{sapling, pottedSapling});
         TradeOfferHelper.registerWanderingTraderOffers(1, factories -> factories.add(new TradeOffers.SellItemFactory(sapling, 5, 1, 8, 1)));

      }

      if (woodPreset == WoodPreset.WILLOW){
         leaves = createVinesLeavesBlock(willowVinesPlant, willowVines);
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(leavesBefore, leaves.asItem()));

         willowVines = createVines(willowVinesPlant);
         willowVinesPlant = createVinesPlant(willowVines);

         sapling = createSapling(saplingGenerator);
         pottedSapling = createPottedSapling(sapling);
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(saplingBefore, sapling.asItem()));
         SaplingHashMap.put(getName(), new Block[]{sapling, pottedSapling});
         TradeOfferHelper.registerWanderingTraderOffers(1, factories -> factories.add(new TradeOffers.SellItemFactory(sapling, 5, 1, 8, 1)));
      }
      if (woodPreset == WoodPreset.WISTERIA) {
         whiteLeaves = createVinesLeavesBlock("white_", whiteWisteriaVinesPlant, whiteWisteriaVines);
         blueLeaves = createVinesLeavesBlock("blue_", blueWisteriaVinesPlant, blueWisteriaVines);
         pinkLeaves = createVinesLeavesBlock("pink_", pinkWisteriaVinesPlant, pinkWisteriaVines);
         purpleLeaves = createVinesLeavesBlock("purple_", purpleWisteriaVinesPlant, purpleWisteriaVines);
         partWhiteLeaves = createVinesLeavesBlock("part_white_", whiteWisteriaVinesPlant, whiteWisteriaVines);
         partBlueLeaves = createVinesLeavesBlock("part_blue_", blueWisteriaVinesPlant, blueWisteriaVines);
         partPinkLeaves = createVinesLeavesBlock("part_pink_", pinkWisteriaVinesPlant, pinkWisteriaVines);
         partPurpleLeaves = createVinesLeavesBlock("part_purple_", purpleWisteriaVinesPlant, purpleWisteriaVines);
         whiteWisteriaVines = createVines("white_", whiteWisteriaVines);
         blueWisteriaVines = createVines("blue_", blueWisteriaVines);
         pinkWisteriaVines = createVines("pink_", pinkWisteriaVines);
         purpleWisteriaVines = createVines("purple_", purpleWisteriaVines);
         whiteWisteriaVinesPlant = createVinesPlant("white_", whiteWisteriaVines);
         blueWisteriaVinesPlant = createVinesPlant("blue_", blueWisteriaVines);
         pinkWisteriaVinesPlant = createVinesPlant("pink_", pinkWisteriaVines);
         purpleWisteriaVinesPlant = createVinesPlant("purple_", purpleWisteriaVines);
         whiteSapling = createSapling("white_", new WhiteWisteriaSaplingGenerator());
         blueSapling = createSapling("blue_", new BlueWisteriaSaplingGenerator());
         pinkSapling = createSapling("pink_", new PinkWisteriaSaplingGenerator());
         purpleSapling = createSapling("purple_", new PurpleWisteriaSaplingGenerator());
         pottedWhiteSapling = createPottedSapling("white_",whiteSapling);
         pottedBlueSapling = createPottedSapling("blue_", blueSapling);
         pottedPinkSapling = createPottedSapling("pink_", pinkSapling);
         pottedPurpleSapling = createPottedSapling("purple_",purpleSapling);
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(leavesBefore, whiteLeaves.asItem(), partWhiteLeaves, blueLeaves, partBlueLeaves, pinkLeaves, partPinkLeaves, purpleLeaves, partPurpleLeaves));
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(Blocks.VINE, whiteWisteriaVines.asItem()));
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(whiteWisteriaVines, blueWisteriaVines.asItem()));
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(blueWisteriaVines, pinkWisteriaVines.asItem()));
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(pinkWisteriaVines, purpleWisteriaVines.asItem()));
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(saplingBefore, whiteSapling.asItem()));
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(whiteSapling, blueSapling.asItem()));
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(blueSapling, pinkSapling.asItem()));
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(pinkSapling, purpleSapling.asItem()));
         SaplingHashMap.put("white_" + getName(), new Block[]{whiteSapling, pottedWhiteSapling});
         SaplingHashMap.put("blue_" + getName(), new Block[]{blueSapling, pottedBlueSapling});
         SaplingHashMap.put("pink_" + getName(), new Block[]{pinkSapling, pottedPinkSapling});
         SaplingHashMap.put("purple_" + getName(), new Block[]{purpleSapling, pottedPurpleSapling});
         SaplingHashMap.put("part_white_" + getName(), new Block[]{whiteSapling, pottedWhiteSapling});
         SaplingHashMap.put("part_blue_" + getName(), new Block[]{blueSapling, pottedBlueSapling});
         SaplingHashMap.put("part_pink_" + getName(), new Block[]{pinkSapling, pottedPinkSapling});
         SaplingHashMap.put("part_purple_" + getName(), new Block[]{purpleSapling, pottedPurpleSapling});
         TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
            factories.add(new TradeOffers.SellItemFactory(whiteSapling, 5, 1, 8, 1));
            factories.add(new TradeOffers.SellItemFactory(blueSapling, 5, 1, 8, 1));
            factories.add(new TradeOffers.SellItemFactory(purpleSapling, 5, 1, 8, 1));
            factories.add(new TradeOffers.SellItemFactory(pinkSapling, 5, 1, 8, 1));
         });
      }
      if (woodPreset == WoodPreset.MAPLE) {
         redLeaves = createMapleLeaves("red_", NatureSpirit.RED_MAPLE_LEAVES_PARTICLE);
         orangeLeaves = createMapleLeaves("orange_", NatureSpirit.ORANGE_MAPLE_LEAVES_PARTICLE);
         yellowLeaves = createMapleLeaves("yellow_", NatureSpirit.YELLOW_MAPLE_LEAVES_PARTICLE);
         redSapling = createSapling("red_", new RedMapleSaplingGenerator());
         orangeSapling = createSapling("orange_", new OrangeMapleSaplingGenerator());
         yellowSapling = createSapling("yellow_", new YellowMapleSaplingGenerator());
         pottedRedSapling = createPottedSapling("red_", redSapling);
         pottedOrangeSapling = createPottedSapling("orange_", orangeSapling);
         pottedYellowSapling = createPottedSapling("yellow_", yellowSapling);
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(leavesBefore, redLeaves.asItem()));
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(redLeaves, orangeLeaves.asItem()));
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(orangeLeaves, yellowLeaves.asItem()));
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(saplingBefore, redSapling.asItem()));
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(redSapling, orangeSapling.asItem()));
         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(orangeSapling, yellowSapling.asItem()));
         SaplingHashMap.put("red_" + getName(), new Block[]{redSapling, pottedRedSapling});
         SaplingHashMap.put("orange_" + getName(), new Block[]{orangeSapling, pottedOrangeSapling});
         SaplingHashMap.put("yellow_" + getName(), new Block[]{yellowSapling, pottedYellowSapling});
         TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
            factories.add(new TradeOffers.SellItemFactory(redSapling, 5, 1, 8, 1));
            factories.add(new TradeOffers.SellItemFactory(orangeSapling, 5, 1, 8, 1));
            factories.add(new TradeOffers.SellItemFactory(yellowSapling, 5, 1, 8, 1));
         });
      }
      if (this.hasMosaic()){
         mosaic = createMosaic();
         mosaicStairs = createMosaicStairs();
         mosaicSlab = createMosaicSlab();
         FlammableBlockRegistry.getDefaultInstance().add(mosaic, 5, 20);
         FlammableBlockRegistry.getDefaultInstance().add(mosaicSlab, 5, 20);
         FlammableBlockRegistry.getDefaultInstance().add(mosaicStairs, 5, 20);
         FuelRegistry.INSTANCE.add(mosaic, 300);
         FuelRegistry.INSTANCE.add(mosaicStairs, 300);
         FuelRegistry.INSTANCE.add(mosaicSlab, 150);
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

      boatEntityType = NSEntityTypes.registerEntityType(getName() + "_boat", NSEntityTypes.createBoatType(false, boatType));
      chestBoatEntityType = NSEntityTypes.registerEntityType(getName() + "_chest_boat", NSEntityTypes.createBoatType(true, boatType));

      boatItem = createItem(getName() + "_boat",
              new NSBoatItem(false, boatType, new Item.Settings().maxCount(1))
      );
      chestBoatItem = createItem(getName() + "_chest_boat",
              new NSBoatItem(true, boatType, new Item.Settings().maxCount(1))
      );
      RenderLayerHashMap.put(getName() + "_door", door);
      RenderLayerHashMap.put(getName() + "_trapdoor", trapDoor);

      FlammableBlockRegistry.getDefaultInstance().add(strippedLog, 5, 5);
      FlammableBlockRegistry.getDefaultInstance().add(log, 5, 5);
      FlammableBlockRegistry.getDefaultInstance().add(stairs, 5, 20);
      FlammableBlockRegistry.getDefaultInstance().add(slab, 5, 20);
      FlammableBlockRegistry.getDefaultInstance().add(planks, 5, 20);
      FlammableBlockRegistry.getDefaultInstance().add(fence, 5, 20);
      FlammableBlockRegistry.getDefaultInstance().add(fenceGate, 5, 20);

      FuelRegistry.INSTANCE.add(fence, 300);
      FuelRegistry.INSTANCE.add(fenceGate, 300);

      blockLogsTag = TagKey.of(RegistryKeys.BLOCK, new Identifier(getModID(), getName() + "_logs"));
      itemLogsTag = TagKey.of(RegistryKeys.ITEM, new Identifier(getModID(), getName() + "_logs"));
      addToBuildingTab(buttonBefore, logBefore, signBefore, boatBefore, this);

      for(Block item : registeredBlocksList) ItemGroupEvents.modifyEntriesEvent(NSItemGroups.NS_ITEM_GROUP).register(entries -> entries.add(item));
      for(Item item : registeredItemsList) ItemGroupEvents.modifyEntriesEvent(NSItemGroups.NS_ITEM_GROUP).register(entries -> entries.add(item));
   }



   public WoodSet(Identifier name, MapColor sideColor, MapColor topColor, ItemConvertible leavesBefore, ItemConvertible logBefore, ItemConvertible signBefore, ItemConvertible boatBefore, ItemConvertible buttonBefore, ItemConvertible saplingBefore, NSBoatEntity.HibiscusBoat boatType, SaplingGenerator saplingGenerator, WoodPreset woodPreset, boolean hasMosaic){
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
      this.saplingGenerator = saplingGenerator;
      this.hasMosaic = hasMosaic;
      registerWood();
      WoodHashMap.put(getName(), this);
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
   public Block getFrostyLeaves() {return frostyLeaves;}
   public Block getSapling() {return sapling;}
   public Block getPottedSapling() {return pottedSapling;}
   public Block getWillowVines() {return willowVines;}
   public Block getWillowVinesPlant() {
      return willowVinesPlant;
   }
   public Block getRedLeaves() {
      return redLeaves;
   }
   public Block getOrangeLeaves() {
      return orangeLeaves;
   }
   public Block getYellowLeaves() { return yellowLeaves; }
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

   private String getWoodName(){
      String name;
      if (woodPreset == WoodPreset.NETHER){
         name = getName() + "_hyphae";
      }
      else{
         name = getName() + "_wood";
      }
      return name;
   }
   private String getLogName(){
      String name;
      if (woodPreset == WoodPreset.BAMBOO){
         name = getName() + "_block";
      }
      else if (woodPreset == WoodPreset.NETHER){
         name = getName() + "_stem";
      }
      else{
         name = getName() + "_log";
      }
      return name;
   }
   private Block getBase(){
      Block base;
      if (woodPreset == WoodPreset.BAMBOO){
         base = Blocks.BAMBOO_PLANKS;
      }
      else if (woodPreset == WoodPreset.FANCY){
         base = Blocks.CHERRY_PLANKS;
      }
      else if (woodPreset == WoodPreset.NETHER){
         base = Blocks.CRIMSON_PLANKS;
      }
      else{
         base = Blocks.OAK_PLANKS;
      }
      return base;
   }
   private Block getSignBase(){
      Block base;
      if (woodPreset == WoodPreset.BAMBOO){
         base = Blocks.BAMBOO_SIGN;
      }
      else if (woodPreset == WoodPreset.FANCY){
         base = Blocks.CHERRY_SIGN;
      }
      else if (woodPreset == WoodPreset.NETHER){
         base = Blocks.CRIMSON_SIGN;
      }
      else{
         base = Blocks.OAK_SIGN;
      }
      return base;
   }
   private Block getHangingSignBase(){
      Block base;
      if (woodPreset == WoodPreset.BAMBOO){
         base = Blocks.BAMBOO_HANGING_SIGN;
      }
      else if (woodPreset == WoodPreset.FANCY){
         base = Blocks.CHERRY_HANGING_SIGN;
      }
      else if (woodPreset == WoodPreset.NETHER){
         base = Blocks.CRIMSON_HANGING_SIGN;
      }
      else{
         base = Blocks.OAK_HANGING_SIGN;
      }
      return base;
   }


   public NSBoatEntity.HibiscusBoat getboatType() {
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

   public List<Block> getRegisteredBlocksList() {
      return registeredBlocksList;
   }

   public List<Item> getRegisteredItemsList() {
      return registeredItemsList;
   }

   private Block createBlockWithItem(String blockID, Block block){
      registerBlockItem(blockID, block);
      Block listBlock = registerBlock(blockID, block);
      registeredBlocksList.add(listBlock);
      return listBlock;
   }
   public Item createItem(String blockID, Item item){
      Item listItem = registerItem(blockID, item);
      registeredItemsList.add(listItem);
      return listItem;
   }
   private PillarBlock createLogBlock(MapColor topMapColor, MapColor sideMapColor) {
      return new PillarBlock(FabricBlockSettings.create().mapColor(state -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor).strength(2.0F).sounds(this.getBlockSetType().soundType()));
   }
   private Block createLog() {
      return createBlockWithItem(getLogName(), createLogBlock(sideColor, topColor));
   }
   private Block createStrippedLog() {
      return createBlockWithItem("stripped_" + getLogName(), createLogBlock(sideColor, topColor));
   }
   private Block createBundle() {
      return createBlockWithItem( getName() + "_bundle", createLogBlock(sideColor, topColor));
   }
   private Block createStrippedBundle() {
      return createBlockWithItem("stripped_" + getName() + "_bundle", createLogBlock(sideColor, topColor));
   }
   private Block createJoshuaLog() {
      return createBlockWithItem(getLogName(), new BranchingTrunkBlock(FabricBlockSettings.create().burnable().mapColor(MapColor.GRAY).instrument(Instrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD)));
   }
   private Block createStrippedJoshuaLog() {
      return createBlockWithItem("stripped_" + getLogName(), new BranchingTrunkBlock(FabricBlockSettings.create().burnable().mapColor(MapColor.GRAY).instrument(Instrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD)));
   }
   private Block createWood() {
      return createBlockWithItem(getWoodName(), createLogBlock(sideColor, sideColor));
   }
   private Block createStrippedWood() {
      return createBlockWithItem("stripped_" + getWoodName(), createLogBlock(topColor, topColor));
   }
   private Block createLeaves() {
      Block block = createBlockWithItem(getName() + "_leaves", new LeavesBlock(FabricBlockSettings.create().mapColor(MapColor.DARK_GREEN).strength(0.2F).ticksRandomly().sounds(BlockSoundGroup.GRASS).nonOpaque().allowsSpawning(Blocks::canSpawnOnLeaves).suffocates(Blocks::never).blockVision(Blocks::never).burnable().pistonBehavior(PistonBehavior.DESTROY).solidBlock(Blocks::never)));
      RenderLayerHashMap.put(getName() + "_leaves", block);
      CompostingChanceRegistry.INSTANCE.add(block, 0.3F);
      FlammableBlockRegistry.getDefaultInstance().add(block, 5, 20);
      LeavesHashMap.put(getName(), block);
      return block;
   }
   private Block createLeaves(String prefix) {
      Block block = createBlockWithItem(prefix + getName() + "_leaves", new LeavesBlock(FabricBlockSettings.create().mapColor(MapColor.DARK_GREEN).strength(0.2F).ticksRandomly().sounds(BlockSoundGroup.GRASS).nonOpaque().allowsSpawning(Blocks::canSpawnOnLeaves).suffocates(Blocks::never).blockVision(Blocks::never).burnable().pistonBehavior(PistonBehavior.DESTROY).solidBlock(Blocks::never)));
      RenderLayerHashMap.put(prefix + getName() + "_leaves", block);
      CompostingChanceRegistry.INSTANCE.add(block, 0.3F);
      FlammableBlockRegistry.getDefaultInstance().add(block, 5, 20);
      LeavesHashMap.put(prefix + getName(), block);
      return block;
   }
   private Block createFrostableLeaves() {
      Block block = createBlockWithItem(getName() + "_leaves",
              new ProjectileLeavesBlock(FabricBlockSettings.create().mapColor(MapColor.DARK_GREEN).strength(0.2F).ticksRandomly().sounds(BlockSoundGroup.GRASS).nonOpaque().allowsSpawning(Blocks::canSpawnOnLeaves).suffocates(Blocks::never).blockVision(Blocks::never).burnable().pistonBehavior(PistonBehavior.DESTROY).solidBlock(Blocks::never), frostyLeaves));
      RenderLayerHashMap.put(getName() + "_leaves", block);
      CompostingChanceRegistry.INSTANCE.add(block, 0.3F);
      FlammableBlockRegistry.getDefaultInstance().add(block, 5, 20);
      LeavesHashMap.put(getName(), block);
      return block;
   }
   private Block createFrostableLeaves(String prefix) {
      Block block = createBlockWithItem(prefix + getName() + "_leaves",
              new ProjectileLeavesBlock(FabricBlockSettings.create().mapColor(MapColor.DARK_GREEN).strength(0.2F).ticksRandomly().sounds(BlockSoundGroup.GRASS).nonOpaque().allowsSpawning(Blocks::canSpawnOnLeaves).suffocates(Blocks::never).blockVision(Blocks::never).burnable().pistonBehavior(PistonBehavior.DESTROY).solidBlock(Blocks::never), frostyLeaves));
      RenderLayerHashMap.put(prefix + getName() + "_leaves", block);
      CompostingChanceRegistry.INSTANCE.add(block, 0.3F);
      FlammableBlockRegistry.getDefaultInstance().add(block, 5, 20);
      LeavesHashMap.put(prefix + getName(), block);
      return block;
   }
   private Block createMapleLeaves(String prefix, ParticleEffect particle) {
      Block block = createBlockWithItem(prefix + getName() + "_leaves", new ParticleLeavesBlock(FabricBlockSettings.create().mapColor(MapColor.DARK_GREEN).strength(0.2F).ticksRandomly().sounds(BlockSoundGroup.GRASS).nonOpaque().allowsSpawning(Blocks::canSpawnOnLeaves).suffocates(Blocks::never).blockVision(Blocks::never).burnable().pistonBehavior(PistonBehavior.DESTROY).solidBlock(Blocks::never), particle, 150));
      RenderLayerHashMap.put(prefix + getName() + "_leaves", block);
      CompostingChanceRegistry.INSTANCE.add(block, 0.3F);
      FlammableBlockRegistry.getDefaultInstance().add(block, 5, 20);
      LeavesHashMap.put(prefix + getName(), block);
      return block;
   }
   private Block createVinesLeavesBlock(Block vinesPlantBlock, Block vinesTipBlock) {
      Block block = createBlockWithItem(getName() + "_leaves",
           new VinesLeavesBlock(FabricBlockSettings.create().strength(0.2F).ticksRandomly().sounds(BlockSoundGroup.GRASS).nonOpaque()
               .allowsSpawning(Blocks::canSpawnOnLeaves).suffocates(Blocks::never).blockVision(Blocks::never).burnable().pistonBehavior(PistonBehavior.DESTROY).solidBlock(Blocks::never), vinesPlantBlock, vinesTipBlock));
      RenderLayerHashMap.put(getName() + "_leaves", block);
      CompostingChanceRegistry.INSTANCE.add(block, 0.3F);
      FlammableBlockRegistry.getDefaultInstance().add(block, 5, 20);
      LeavesHashMap.put(getName(), block);
      return block;
   }
   private Block createVinesLeavesBlock(String prefix, Block vinesPlantBlock, Block vinesTipBlock) {
      Block block = createBlockWithItem(prefix + getName() + "_leaves",
              new VinesLeavesBlock(FabricBlockSettings.create().strength(0.2F).ticksRandomly().sounds(BlockSoundGroup.GRASS).nonOpaque()
               .allowsSpawning(Blocks::canSpawnOnLeaves).suffocates(Blocks::never).blockVision(Blocks::never).burnable()
               .pistonBehavior(PistonBehavior.DESTROY).solidBlock(Blocks::never), vinesPlantBlock, vinesTipBlock));
      RenderLayerHashMap.put(prefix + getName() + "_leaves", block);
      CompostingChanceRegistry.INSTANCE.add(block, 0.3F);
      FlammableBlockRegistry.getDefaultInstance().add(block, 5, 20);
      LeavesHashMap.put(prefix + getName(), block);
      return block;
   }
   private Block createVines(Block vinesPlantBlock) {
      Block vinesBlock = createBlockWithItem(getName() + "_vines",
              new DownwardVineBlock(FabricBlockSettings
                      .create()
                      .pistonBehavior(PistonBehavior.DESTROY)
                      .ticksRandomly()
                      .noCollision()
                      .nonOpaque()
                      .breakInstantly()
                      .sounds(BlockSoundGroup.WEEPING_VINES), vinesPlantBlock));
      RenderLayerHashMap.put(getName() + "_vines", vinesBlock);
      CompostingChanceRegistry.INSTANCE.add(vinesBlock, 0.3F);
      return vinesBlock;
   }
   private Block createVines(String prefix, Block vinesPlantBlock) {
      Block vinesBlock = createBlockWithItem(prefix + getName() + "_vines",
              new DownwardVineBlock(FabricBlockSettings
                      .create()
                      .pistonBehavior(PistonBehavior.DESTROY)
                      .ticksRandomly()
                      .noCollision()
                      .nonOpaque()
                      .breakInstantly()
                      .sounds(BlockSoundGroup.WEEPING_VINES), vinesPlantBlock));
      RenderLayerHashMap.put(prefix + getName() + "_vines", vinesBlock);
      CompostingChanceRegistry.INSTANCE.add(vinesBlock, 0.3F);
      return vinesBlock;
   }
   private Block createVinesPlant(Block vines) {
      Block vinesPlant = registerBlock(getName() + "_vines_plant", new DownwardsVinePlantBlock(FabricBlockSettings
              .create()
              .pistonBehavior(PistonBehavior.DESTROY)
              .noCollision()
              .nonOpaque()
              .breakInstantly()
              .sounds(BlockSoundGroup.WEEPING_VINES)
              .dropsLike(vines), vines));
      RenderLayerHashMap.put(getName() + "_vines_plant", vinesPlant);
      return vinesPlant;
   }
   private Block createVinesPlant(String prefix, Block vines) {
      Block vinesPlant = registerBlock(prefix + getName() + "_vines_plant", new DownwardsVinePlantBlock(FabricBlockSettings
              .create()
              .pistonBehavior(PistonBehavior.DESTROY)
              .noCollision()
              .nonOpaque()
              .breakInstantly()
              .sounds(BlockSoundGroup.WEEPING_VINES)
              .dropsLike(vines), vines));
      RenderLayerHashMap.put(prefix + getName() + "_vines_plant", vinesPlant);
      return vinesPlant;
   }
   private Block createPlanks(){
      return createBlockWithItem(getName() + "_planks", new Block(FabricBlockSettings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor())));
   }
   private Block createStairs(){
      return createBlockWithItem(getName() + "_stairs", new StairsBlock(getBase().getDefaultState(), FabricBlockSettings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor())));
   }
   private Block createSlab(){
      return createBlockWithItem(getName() + "_slab", new SlabBlock(FabricBlockSettings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor())));
   }
   private Block createMosaic(){
      return createBlockWithItem(getName() + "_mosaic", new Block(FabricBlockSettings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor())));
   }
   private Block createMosaicStairs(){
      return createBlockWithItem(getName() + "_mosaic_stairs", new StairsBlock(getBase().getDefaultState(), FabricBlockSettings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor())));
   }
   private Block createMosaicSlab(){
      return createBlockWithItem(getName() + "_mosaic_slab", new SlabBlock(FabricBlockSettings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor())));
   }
   private Block createFence(){
      return createBlockWithItem(getName() + "_fence", new FenceBlock(FabricBlockSettings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor())));
   }
   private Block createFenceGate(){
      return createBlockWithItem(getName() + "_fence_gate", new FenceGateBlock(FabricBlockSettings.create().mapColor(getBase().getDefaultMapColor()).solid().instrument(Instrument.BASS).strength(2.0F, 3.0F).burnable(), WoodType.OAK));
   }
   private Block createPressurePlate(){
      return createBlockWithItem(getName() + "_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.create().mapColor(getBase().getDefaultMapColor()).solid().instrument(Instrument.BASS).noCollision().strength(0.5F).burnable().pistonBehavior(PistonBehavior.DESTROY), blockSetType));
   }
   private Block createButton(){
      return createBlockWithItem(getName() + "_button", new ButtonBlock(FabricBlockSettings.create().noCollision().strength(0.5F).pistonBehavior(PistonBehavior.DESTROY), blockSetType, 30, true));
   }
   private Block createDoor(){
      return createBlockWithItem(getName() + "_door", new DoorBlock(FabricBlockSettings.copy(getBase()).sounds(getBlockSetType().soundType()).nonOpaque().mapColor(getTopColor()), blockSetType));
   }
   private Block createTrapDoor(){
      return createBlockWithItem(getName() + "_trapdoor", new TrapdoorBlock(FabricBlockSettings.copy(getBase()).sounds(getBlockSetType().soundType()).nonOpaque().mapColor(getTopColor()), blockSetType));
   }
   private Block createSign(){
      return registerBlock(getName() + "_sign", new SignBlock(FabricBlockSettings.copy(getSignBase()).mapColor(topColor), woodType));
   }
   private Block createWallSign(){
      return registerBlock(getName() + "_wall_sign", new WallSignBlock(FabricBlockSettings.copy(getSignBase()).mapColor(topColor).dropsLike(sign), woodType));
   }
   private Block createHangingSign(){
      return registerBlock(getName() + "_hanging_sign", new HangingSignBlock(FabricBlockSettings.copy(getHangingSignBase()).mapColor(topColor), woodType));
   }
   private Block createWallHangingSign(){
      return registerBlock(getName() + "_wall_hanging_sign", new WallHangingSignBlock(FabricBlockSettings.copy(getHangingSignBase()).mapColor(topColor).dropsLike(hangingSign), woodType));
   }

   public Block createSapling(SaplingGenerator saplingGenerator) {
      Block block = createBlockWithItem(getName() + "_sapling", new SaplingBlock(saplingGenerator, FabricBlockSettings.copy(Blocks.SPRUCE_SAPLING)));
      CompostingChanceRegistry.INSTANCE.add(block, 0.3F);
      RenderLayerHashMap.put(getName() + "_sapling", block);
      return block;
   }
   public Block createSandySapling(SaplingGenerator saplingGenerator) {
      Block block = createBlockWithItem(getName() + "_sapling", new SandySaplingBlock(saplingGenerator, FabricBlockSettings.copy(Blocks.SPRUCE_SAPLING)));
      CompostingChanceRegistry.INSTANCE.add(block, 0.3F);
      RenderLayerHashMap.put(getName() + "_sapling", block);
      return block;
   }
   public Block createPottedSapling(Block sapling) {
      Block pot = registerBlock("potted_" + getName() + "_sapling", new FlowerPotBlock(sapling, FabricBlockSettings.create().breakInstantly().nonOpaque().pistonBehavior(PistonBehavior.DESTROY)));
      RenderLayerHashMap.put("potted_" + getName() + "_sapling", pot);
      return pot;
   }
   public Block createSapling(String prefix, SaplingGenerator saplingGenerator) {
      Block block = createBlockWithItem(prefix + getName() + "_sapling", new SaplingBlock(saplingGenerator, FabricBlockSettings.copy(Blocks.SPRUCE_SAPLING)));
      CompostingChanceRegistry.INSTANCE.add(block, 0.3F);
      RenderLayerHashMap.put(prefix + getName() + "_sapling", block);
      return block;
   }
   public Block createSandySapling(String prefix, SaplingGenerator saplingGenerator) {
      Block block = createBlockWithItem(prefix + getName() + "_sapling", new SandySaplingBlock(saplingGenerator, FabricBlockSettings.copy(Blocks.SPRUCE_SAPLING)));
      CompostingChanceRegistry.INSTANCE.add(block, 0.3F);
      RenderLayerHashMap.put(prefix + getName() + "_sapling", block);
      return block;
   }
   public Block createPottedSapling(String prefix, Block sapling) {
      Block pot = registerBlock("potted_" + prefix + getName() + "_sapling", new FlowerPotBlock(sapling, FabricBlockSettings.create().breakInstantly().nonOpaque().pistonBehavior(PistonBehavior.DESTROY)));
      RenderLayerHashMap.put("potted_" + prefix + getName() + "_sapling", pot);
      return pot;
   }
   private Item createSignItem(){
      return createItem(getName() + "_sign", new SignItem(new FabricItemSettings().maxCount(16), sign, wallSign));
   }
   private Item createHangingSignItem(){
      return createItem(getName() + "_hanging_sign", new HangingSignItem(hangingSign, hangingWallSign, new FabricItemSettings().maxCount(16)));
   }

   private BlockSetType createBlockSetType(){
      if (this.woodPreset == WoodPreset.BAMBOO){
         return BlockSetTypeBuilder.copyOf(BlockSetType.BAMBOO).register(getNameID());
      }
      else if (woodPreset == WoodPreset.FANCY){
         return BlockSetTypeBuilder.copyOf(BlockSetType.CHERRY).register(getNameID());
      }
      else if (this.woodPreset == WoodPreset.NETHER){
         return BlockSetTypeBuilder.copyOf(BlockSetType.CRIMSON).register(getNameID());
      }
      else{
         return BlockSetTypeBuilder.copyOf(BlockSetType.OAK).register(getNameID());
      }
   }

   public boolean isSandy(){
      return woodPreset == WoodPreset.JOSHUA || woodPreset == WoodPreset.SANDY;
   }
   public boolean hasDefaultLeaves(){
      return woodPreset == WoodPreset.DEFAULT || woodPreset == WoodPreset.WISTERIA || woodPreset == WoodPreset.FANCY || woodPreset == WoodPreset.JOSHUA || woodPreset == WoodPreset.NO_SAPLING || woodPreset == WoodPreset.SANDY;
   }
   public boolean hasDefaultSapling() {
      return woodPreset != WoodPreset.NO_SAPLING && woodPreset != WoodPreset.WISTERIA;
   }
   public boolean hasBark(){
      return woodPreset != WoodPreset.JOSHUA && woodPreset != WoodPreset.BAMBOO;
   }
   public boolean hasMosaic(){
      return this.hasMosaic;
   }

   public enum WoodPreset {
      DEFAULT, MAPLE, FROSTABLE, JOSHUA, SANDY, NO_SAPLING, WISTERIA, WILLOW, FANCY, NETHER, BAMBOO
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