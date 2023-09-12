package net.hibiscus.naturespirit.util;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeRegistry;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.blocks.*;
import net.hibiscus.naturespirit.entity.HibiscusBoatEntity;
import net.hibiscus.naturespirit.items.HibiscusBoatItem;
import net.hibiscus.naturespirit.mixin.BlockSetTypeAccessor;
import net.hibiscus.naturespirit.mixin.WoodTypeAccessor;
import net.hibiscus.naturespirit.registration.HibiscusEntityTypes;
import net.hibiscus.naturespirit.registration.HibiscusItemGroups;
import net.hibiscus.naturespirit.world.feature.tree.*;
import net.minecraft.block.*;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.registries.Registries;
import net.minecraft.item.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import java.util.ArrayList;
import java.util.List;

import static net.hibiscus.naturespirit.util.HibiscusRegistryHelper.*;

public class WoodSet {


   private Block leavesBefore;
   private Block saplingBefore;
   private Block logBefore;
   private Block signBefore;
   private Item boatBefore;
   private Block buttonBefore;
   private List<Block> registeredBlocksList = new ArrayList<>();
   private List<Item> registeredItemsList = new ArrayList<>();
   private ResourceLocation name;
   private MapColor sideColor;
   private MapColor topColor;
   private WoodPreset woodPreset;
   private BlockSetType blockSetType;
   private WoodType woodType;
   private Block log;
   private Block strippedLog;
   private Block wood;
   private Block strippedWood;
   private Block leaves;
   private Block sapling;
   private Block pottedSapling;
   private Block redMapleLeaves;
   private Block redMapleSapling;
   private Block pottedRedMapleSapling;
   private Block orangeMapleLeaves;
   private Block orangeMapleSapling;
   private Block pottedOrangeMapleSapling;
   private Block yellowMapleLeaves;
   private Block yellowMapleSapling;
   private Block pottedYellowMapleSapling;
   private Block blueWisteriaLeaves;
   private Block blueWisteriaSapling;
   private Block pottedBlueWisteriaSapling;
   private Block purpleWisteriaLeaves;
   private Block purpleWisteriaSapling;
   private Block pottedPurpleWisteriaSapling;
   private Block pinkWisteriaLeaves;
   private Block pinkWisteriaSapling;
   private Block pottedPinkWisteriaSapling;
   private Block whiteWisteriaLeaves;
   private Block whiteWisteriaSapling;
   private Block pottedWhiteWisteriaSapling;
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
   private EntityType<Boat> boatEntityType;
   private EntityType<Boat> chestBoatEntityType;
   private AbstractTreeGrower saplingGenerator;



   private void registerWood(){
      blockSetType = createBlockSetType();
      woodType = WoodTypeAccessor.registerNew(new WoodType(this.getNameID().toString(), getBlockSetType()));

      log = this.getWoodPreset() == WoodPreset.JOSHUA ? createJoshuaLog() : createLog();
      strippedLog = this.getWoodPreset() == WoodPreset.JOSHUA ? createStrippedJoshuaLog() : createStrippedLog();
      if (this.getWoodPreset() != WoodPreset.JOSHUA) {
         StrippableBlockRegistry.register(log, strippedLog);
      }

      if (this.getWoodPreset() != WoodPreset.BAMBOO && this.getWoodPreset() != WoodPreset.JOSHUA){
         wood = createWood();
         strippedWood = createStrippedWood();
         StrippableBlockRegistry.register(wood, strippedWood);
      }
      else if (this.getWoodPreset() == WoodPreset.BAMBOO ){
         mosaic = createMosaic();
         mosaicStairs = createMosaicStairs();
         mosaicSlab = createMosaicSlab();
      }
      if (this.getWoodPreset() == WoodPreset.DEFAULT || this.getWoodPreset() == WoodPreset.FANCY || this.getWoodPreset() == WoodPreset.JOSHUA){
         leaves = createLeaves();
         RenderLayerHashMap.put(this.getName() + "_leaves", this.getLeaves());
         CompostingChanceRegistry.INSTANCE.add(this.getLeaves(), 0.3F);
         FlammableBlockRegistry.getDefaultInstance().add(this.getLeaves(), 5, 20);
         CompostingChanceRegistry.INSTANCE.add(this.getLeaves(), 0.3F);
         ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(this.getLeavesBefore(), this.getLeaves()));
         LeavesHashMap.put(this.getName(), this.getLeaves());

         sapling = createSapling(saplingGenerator);
         pottedSapling = createPottedSapling(this.getSapling());
         RenderLayerHashMap.put(this.getName() + "_sapling", this.getSapling());
         RenderLayerHashMap.put("potted_" + this.getName() + "_sapling", this.getPottedSapling());
         CompostingChanceRegistry.INSTANCE.add(this.getSapling(), 0.3F);
         ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(this.getSaplingBefore(), this.getSapling().asItem()));
         SaplingHashMap.put(this.getName(), new Block[]{this.getSapling(), this.getPottedSapling()});
      }
      if (this.getWoodPreset() == WoodPreset.WILLOW){
         leaves = createWillowLeaves();
         willowVines = createWillowVines();
         willowVinesPlant = createWillowVinesPlant(this.getWillowVines());
         RenderLayerHashMap.put(this.getName() + "_leaves", this.getLeaves());
         RenderLayerHashMap.put(this.getName() + "_vines", this.getWillowVines());
         RenderLayerHashMap.put(this.getName() + "_vines_plant", this.getWillowVinesPlant());
         CompostingChanceRegistry.INSTANCE.add(this.getLeaves(), 0.3F);
         CompostingChanceRegistry.INSTANCE.add(this.getWillowVines(), 0.3F);
         FlammableBlockRegistry.getDefaultInstance().add(this.getLeaves(), 5, 20);
         ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(this.getLeavesBefore(), this.getLeaves().asItem()));
         LeavesHashMap.put(this.getName(), this.getLeaves());

         sapling = createSapling(saplingGenerator);
         pottedSapling = createPottedSapling(this.getSapling());
         RenderLayerHashMap.put(this.getName() + "_sapling", this.getSapling());
         RenderLayerHashMap.put("potted_" + this.getName() + "_sapling", this.getPottedSapling());
         CompostingChanceRegistry.INSTANCE.add(this.getSapling(), 0.3F);
         ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(this.getSaplingBefore(), this.getSapling().asItem()));
         SaplingHashMap.put(this.getName(), new Block[]{this.getSapling(), this.getPottedSapling()});
      }
      if (this.getWoodPreset() == WoodPreset.WISTERIA) {
         whiteWisteriaLeaves = createWisteriaLeaves("white_");
         blueWisteriaLeaves = createWisteriaLeaves("blue_");
         pinkWisteriaLeaves = createWisteriaLeaves("pink_");
         purpleWisteriaLeaves = createWisteriaLeaves("purple_");
         whiteWisteriaVines = createWisteriaVines("white_");
         blueWisteriaVines = createWisteriaVines("blue_");
         pinkWisteriaVines = createWisteriaVines("pink_");
         purpleWisteriaVines = createWisteriaVines("purple_");
         whiteWisteriaVinesPlant = createWisteriaVinesPlant("white_", this.getWhiteWisteriaVines());
         blueWisteriaVinesPlant = createWisteriaVinesPlant("blue_", this.getBlueWisteriaVines());
         pinkWisteriaVinesPlant = createWisteriaVinesPlant("pink_", this.getPinkWisteriaVines());
         purpleWisteriaVinesPlant = createWisteriaVinesPlant("purple_", this.getPurpleWisteriaVines());
         whiteWisteriaSapling = createSapling("white_", new WhiteWisteriaSaplingGenerator());
         blueWisteriaSapling = createSapling("blue_", new BlueWisteriaSaplingGenerator());
         pinkWisteriaSapling = createSapling("pink_", new PinkWisteriaSaplingGenerator());
         purpleWisteriaSapling = createSapling("purple_", new PurpleWisteriaSaplingGenerator());
         pottedWhiteWisteriaSapling = createPottedSapling("white_",this.getWhiteWisteriaSapling());
         pottedBlueWisteriaSapling = createPottedSapling("blue_", this.getBlueWisteriaSapling());
         pottedPinkWisteriaSapling = createPottedSapling("pink_", this.getPinkWisteriaSapling());
         pottedPurpleWisteriaSapling = createPottedSapling("purple_",this.getPurpleWisteriaSapling());
         RenderLayerHashMap.put("white_" + this.getName() + "_leaves", this.getWhiteWisteriaLeaves());
         RenderLayerHashMap.put("blue_" + this.getName() + "_leaves", this.getBlueWisteriaLeaves());
         RenderLayerHashMap.put("pink_" + this.getName() + "_leaves", this.getPinkWisteriaLeaves());
         RenderLayerHashMap.put("purple_" + this.getName() + "_leaves", this.getPurpleWisteriaLeaves());
         RenderLayerHashMap.put("white_" + this.getName() + "_vines", this.getWhiteWisteriaVines());
         RenderLayerHashMap.put("blue_" + this.getName() + "_vines", this.getBlueWisteriaVines());
         RenderLayerHashMap.put("pink_" + this.getName() + "_vines", this.getPinkWisteriaVines());
         RenderLayerHashMap.put("purple_" + this.getName() + "_vines", this.getPurpleWisteriaVines());
         RenderLayerHashMap.put("white_" + this.getName() + "_vines_plant", this.getWhiteWisteriaVinesPlant());
         RenderLayerHashMap.put("blue_" + this.getName() + "_vines_plant", this.getBlueWisteriaVinesPlant());
         RenderLayerHashMap.put("pink_" + this.getName() + "_vines_plant", this.getPinkWisteriaVinesPlant());
         RenderLayerHashMap.put("purple_" + this.getName() + "_vines_plant", this.getPurpleWisteriaVinesPlant());
         RenderLayerHashMap.put("white_" + this.getName() + "_sapling", this.getWhiteWisteriaSapling());
         RenderLayerHashMap.put("blue_" + this.getName() + "_sapling", this.getBlueWisteriaSapling());
         RenderLayerHashMap.put("pink_" + this.getName() + "_sapling", this.getPinkWisteriaSapling());
         RenderLayerHashMap.put("purple_" + this.getName() + "_sapling", this.getPurpleWisteriaSapling());
         RenderLayerHashMap.put("potted_white_" + this.getName() + "_sapling", this.getPottedWhiteWisteriaSapling());
         RenderLayerHashMap.put("potted_blue_" + this.getName() + "_sapling", this.getPottedBlueWisteriaSapling());
         RenderLayerHashMap.put("potted_pink_" + this.getName() + "_sapling", this.getPottedPinkWisteriaSapling());
         RenderLayerHashMap.put("potted_purple_" + this.getName() + "_sapling", this.getPottedPurpleWisteriaSapling());
         FlammableBlockRegistry.getDefaultInstance().add(this.getWhiteWisteriaLeaves(), 5, 20);
         FlammableBlockRegistry.getDefaultInstance().add(this.getBlueWisteriaLeaves(), 5, 20);
         FlammableBlockRegistry.getDefaultInstance().add(this.getPinkWisteriaLeaves(), 5, 20);
         FlammableBlockRegistry.getDefaultInstance().add(this.getPurpleWisteriaLeaves(), 5, 20);
         CompostingChanceRegistry.INSTANCE.add(this.getWhiteWisteriaLeaves(), 0.3F);
         CompostingChanceRegistry.INSTANCE.add(this.getBlueWisteriaLeaves(), 0.3F);
         CompostingChanceRegistry.INSTANCE.add(this.getPinkWisteriaLeaves(), 0.3F);
         CompostingChanceRegistry.INSTANCE.add(this.getPurpleWisteriaLeaves(), 0.3F);
         CompostingChanceRegistry.INSTANCE.add(this.getWhiteWisteriaVines(), 0.3F);
         CompostingChanceRegistry.INSTANCE.add(this.getBlueWisteriaVines(), 0.3F);
         CompostingChanceRegistry.INSTANCE.add(this.getPinkWisteriaVines(), 0.3F);
         CompostingChanceRegistry.INSTANCE.add(this.getPurpleWisteriaVines(), 0.3F);
         CompostingChanceRegistry.INSTANCE.add(this.getWhiteWisteriaSapling(), 0.3F);
         CompostingChanceRegistry.INSTANCE.add(this.getBlueWisteriaSapling(), 0.3F);
         CompostingChanceRegistry.INSTANCE.add(this.getPinkWisteriaSapling(), 0.3F);
         CompostingChanceRegistry.INSTANCE.add(this.getPurpleWisteriaSapling(), 0.3F);
         ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(this.getLeavesBefore(), this.getWhiteWisteriaLeaves().asItem()));
         ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(this.getWhiteWisteriaLeaves(), this.getBlueWisteriaLeaves().asItem()));
         ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(this.getBlueWisteriaLeaves(), this.getPinkWisteriaLeaves().asItem()));
         ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(this.getPinkWisteriaLeaves(), this.getPurpleWisteriaLeaves().asItem()));
         ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(Blocks.VINE, this.getWhiteWisteriaVines().asItem()));
         ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(this.getWhiteWisteriaVines(), this.getBlueWisteriaVines().asItem()));
         ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(this.getBlueWisteriaVines(), this.getPinkWisteriaVines().asItem()));
         ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(this.getPinkWisteriaVines(), this.getPurpleWisteriaVines().asItem()));
         ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(this.getSaplingBefore(), this.getWhiteWisteriaSapling().asItem()));
         ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(this.getWhiteWisteriaSapling(), this.getBlueWisteriaSapling().asItem()));
         ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(this.getBlueWisteriaSapling(), this.getPinkWisteriaSapling().asItem()));
         ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(this.getPinkWisteriaSapling(), this.getPurpleWisteriaSapling().asItem()));
         LeavesHashMap.put("white_" + this.getName(), this.getWhiteWisteriaLeaves());
         LeavesHashMap.put("blue_" + this.getName(), this.getBlueWisteriaLeaves());
         LeavesHashMap.put("pink_" + this.getName(), this.getPinkWisteriaLeaves());
         LeavesHashMap.put("purple_" + this.getName(), this.getPurpleWisteriaLeaves());
         SaplingHashMap.put("white_" + this.getName(), new Block[]{this.getWhiteWisteriaSapling(), this.getPottedWhiteWisteriaSapling()});
         SaplingHashMap.put("blue_" + this.getName(), new Block[]{this.getBlueWisteriaSapling(), this.getPottedBlueWisteriaSapling()});
         SaplingHashMap.put("pink_" + this.getName(), new Block[]{this.getPinkWisteriaSapling(), this.getPottedPinkWisteriaSapling()});
         SaplingHashMap.put("purple_" + this.getName(), new Block[]{this.getPurpleWisteriaSapling(), this.getPottedPurpleWisteriaSapling()});
      }
      if (this.getWoodPreset() == WoodPreset.MAPLE) {
         redMapleLeaves = createMapleLeaves("red_", NatureSpirit.RED_MAPLE_LEAVES_PARTICLE);
         orangeMapleLeaves = createMapleLeaves("orange_", NatureSpirit.ORANGE_MAPLE_LEAVES_PARTICLE);
         yellowMapleLeaves = createMapleLeaves("yellow_", NatureSpirit.YELLOW_MAPLE_LEAVES_PARTICLE);
         redMapleSapling = createSapling("red_", new RedMapleSaplingGenerator());
         orangeMapleSapling = createSapling("orange_", new OrangeMapleSaplingGenerator());
         yellowMapleSapling = createSapling("yellow_", new YellowMapleSaplingGenerator());
         pottedRedMapleSapling = createPottedSapling("red_", this.getRedMapleSapling());
         pottedOrangeMapleSapling = createPottedSapling("orange_", this.getOrangeMapleSapling());
         pottedYellowMapleSapling = createPottedSapling("yellow_", this.getYellowMapleSapling());
         RenderLayerHashMap.put("red_" + this.getName() + "_leaves", this.getRedMapleLeaves());
         RenderLayerHashMap.put("orange_" + this.getName() + "_leaves", this.getOrangeMapleLeaves());
         RenderLayerHashMap.put("yellow_" + this.getName() + "_leaves", this.getYellowMapleLeaves());
         RenderLayerHashMap.put("red_" + this.getName() + "_sapling", this.getRedMapleSapling());
         RenderLayerHashMap.put("orange_" + this.getName() + "_sapling", this.getOrangeMapleSapling());
         RenderLayerHashMap.put("yellow_" + this.getName() + "_sapling", this.getYellowMapleSapling());
         RenderLayerHashMap.put("potted_red_" + this.getName() + "_sapling", this.getPottedRedMapleSapling());
         RenderLayerHashMap.put("potted_orange_" + this.getName() + "_sapling", this.getPottedOrangeMapleSapling());
         RenderLayerHashMap.put("potted_yellow_" + this.getName() + "_sapling", this.getPottedYellowMapleSapling());
         FlammableBlockRegistry.getDefaultInstance().add(this.getRedMapleLeaves(), 5, 20);
         FlammableBlockRegistry.getDefaultInstance().add(this.getOrangeMapleLeaves(), 5, 20);
         FlammableBlockRegistry.getDefaultInstance().add(this.getYellowMapleLeaves(), 5, 20);
         CompostingChanceRegistry.INSTANCE.add(this.getRedMapleLeaves(), 0.3F);
         CompostingChanceRegistry.INSTANCE.add(this.getOrangeMapleLeaves(), 0.3F);
         CompostingChanceRegistry.INSTANCE.add(this.getYellowMapleLeaves(), 0.3F);
         CompostingChanceRegistry.INSTANCE.add(this.getRedMapleSapling(), 0.3F);
         CompostingChanceRegistry.INSTANCE.add(this.getOrangeMapleSapling(), 0.3F);
         CompostingChanceRegistry.INSTANCE.add(this.getYellowMapleSapling(), 0.3F);
         ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(this.getLeavesBefore(), this.getRedMapleLeaves().asItem()));
         ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(this.getRedMapleLeaves(), this.getOrangeMapleLeaves().asItem()));
         ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(this.getOrangeMapleLeaves(), this.getYellowMapleLeaves().asItem()));
         ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(this.getSaplingBefore(), this.getRedMapleSapling().asItem()));
         ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(this.getRedMapleSapling(), this.getOrangeMapleSapling().asItem()));
         ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(this.getOrangeMapleSapling(), this.getYellowMapleSapling().asItem()));
         LeavesHashMap.put("red_" + this.getName(), this.getRedMapleLeaves());
         LeavesHashMap.put("orange_" + this.getName(), this.getOrangeMapleLeaves());
         LeavesHashMap.put("yellow_" + this.getName(), this.getYellowMapleLeaves());
         SaplingHashMap.put("red_" + this.getName(), new Block[]{this.getRedMapleSapling(), this.getPottedRedMapleSapling()});
         SaplingHashMap.put("orange_" + this.getName(), new Block[]{this.getOrangeMapleSapling(), this.getPottedOrangeMapleSapling()});
         SaplingHashMap.put("yellow_" + this.getName(), new Block[]{this.getYellowMapleSapling(), this.getPottedYellowMapleSapling()});
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
              new HibiscusBoatItem(false, this.getboatType(), new Item.Properties())
      );
      chestBoatItem = createItem(this.getName() + "_chest_boat",
              new HibiscusBoatItem(true, this.getboatType(), new Item.Properties())
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

      blockLogsTag = TagKey.create(Registries.BLOCK, new ResourceLocation(this.getModID(), this.getName() + "_logs"));
      itemLogsTag = TagKey.create(Registries.ITEM, new ResourceLocation(this.getModID(), this.getName() + "_logs"));
      addToBuildingTab(getButtonBefore().asItem(), getLogBefore().asItem(), getSignBefore().asItem(), getBoatBefore(), this);

      for(Block item : this.getRegisteredBlocksList()) ItemGroupEvents.modifyEntriesEvent(HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP).register(entries -> entries.accept(item));
      for(Item item : this.getRegisteredItemsList()) ItemGroupEvents.modifyEntriesEvent(HibiscusItemGroups.NATURES_SPIRIT_ITEM_GROUP).register(entries -> entries.accept(item));
   }



   public WoodSet(ResourceLocation name, MapColor sideColor, MapColor topColor, Block leavesBefore, Block logBefore, Block signBefore, Item boatBefore, Block buttonBefore, Block saplingBefore,HibiscusBoatEntity.HibiscusBoat boatType, AbstractTreeGrower saplingGenerator, WoodPreset woodPreset){
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
      return new SignItem(new FabricItemSettings().stacksTo(16), sign, wallSign);
   }
   private Item createHangingSignItem(Block hangingSign, Block hangingWallSign) {
      return new HangingSignItem(hangingSign, hangingWallSign, new FabricItemSettings().stacksTo(16));
   }
   public Item createItem(String blockID, Item item){
      Item listItem = registerItem(blockID, item);
      registeredItemsList.add(listItem);
      return listItem;
   }

   private RotatedPillarBlock createLogBlock(MapColor topMapColor, MapColor sideMapColor) {
      return new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor).strength(2.0F).sound(this.woodType().soundType()));
   }

   private BlockSetType woodType() {
      return this.blockSetType;
   }

   public ResourceLocation getNameID() {
      return name;
   }
   public String getName() {
      return name.getPath();
   }
   public String getModID() {
      return name.getNamespace();
   }
   public BlockSetType getBlockSetType() {
      return blockSetType;
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
   public Block getRedMapleLeaves() {
      return redMapleLeaves;
   }
   public Block getOrangeMapleLeaves() {
      return orangeMapleLeaves;
   }
   public Block getYellowMapleLeaves() {
      return yellowMapleLeaves;
   }
   public Block getBlueWisteriaLeaves() {
      return blueWisteriaLeaves;
   }
   public Block getPurpleWisteriaLeaves() {
      return purpleWisteriaLeaves;
   }
   public Block getPinkWisteriaLeaves() {
      return pinkWisteriaLeaves;
   }
   public Block getWhiteWisteriaLeaves() {
      return whiteWisteriaLeaves;
   }
   public Block getPottedRedMapleSapling() {
      return pottedRedMapleSapling;
   }
   public Block getPottedOrangeMapleSapling() {
      return pottedOrangeMapleSapling;
   }
   public Block getPottedYellowMapleSapling() {
      return pottedYellowMapleSapling;
   }
   public Block getPottedBlueWisteriaSapling() {
      return pottedBlueWisteriaSapling;
   }
   public Block getPottedPurpleWisteriaSapling() {
      return pottedPurpleWisteriaSapling;
   }
   public Block getPottedPinkWisteriaSapling() {
      return pottedPinkWisteriaSapling;
   }
   public Block getPottedWhiteWisteriaSapling() {
      return pottedWhiteWisteriaSapling;
   }
   public Block getRedMapleSapling() {
      return redMapleSapling;
   }
   public Block getOrangeMapleSapling() {
      return orangeMapleSapling;
   }
   public Block getYellowMapleSapling() {
      return yellowMapleSapling;
   }
   public Block getBlueWisteriaSapling() {
      return blueWisteriaSapling;
   }
   public Block getPurpleWisteriaSapling() {
      return purpleWisteriaSapling;
   }
   public Block getPinkWisteriaSapling() {
      return pinkWisteriaSapling;
   }
   public Block getWhiteWisteriaSapling() {
      return whiteWisteriaSapling;
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
   public EntityType <Boat> getBoatEntityType() {
      return boatEntityType;
   }
   public EntityType <Boat> getChestBoatEntityType() {
      return chestBoatEntityType;
   }

   public TagKey <Item> getItemLogsTag() {
      return itemLogsTag;
   }
   public TagKey <Block> getBlockLogsTag() {
      return blockLogsTag;
   }

   public Block getButtonBefore() {return buttonBefore;}
   public Block getLeavesBefore() {return leavesBefore;}
   public Block getSaplingBefore() {return saplingBefore;}
   public Block getLogBefore() {return logBefore;}
   public Block getSignBefore() {return signBefore;}
   public Item getBoatBefore() {return boatBefore;}


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
   private Block createJoshuaLog() {
      return createBlockWithItem(getLogName(), new JoshuaTrunkBlock(FabricBlockSettings.of().ignitedByLava().mapColor(MapColor.COLOR_GRAY).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD)));
   }
   private Block createStrippedJoshuaLog() {
      return createBlockWithItem("stripped_" + getLogName(), new JoshuaTrunkBlock(FabricBlockSettings.of().ignitedByLava().mapColor(MapColor.COLOR_GRAY).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD)));
   }
   private Block createWood() {
      return createBlockWithItem(getWoodName(), createLogBlock(this.getSideColor(), this.getSideColor()));
   }
   private Block createStrippedWood() {
      return createBlockWithItem("stripped_" + getWoodName(), createLogBlock(this.getTopColor(), this.getTopColor()));
   }
   private Block createLeaves() {
      return createBlockWithItem(this.getName() + "_leaves", new LeavesBlock(BlockBehaviour.Properties.copy(getBaseLeaves())));
   }
   private Block createMapleLeaves(String prefix, ParticleOptions particle) {
      return createBlockWithItem(prefix + this.getName() + "_leaves", new MapleLeavesBlock(BlockBehaviour.Properties.copy(getBaseLeaves()), particle));
   }
   private Block createWisteriaLeaves(String prefix) {
      return createBlockWithItem(prefix + this.getName() + "_leaves", new WisteriaLeaves(BlockBehaviour.Properties.copy(getBaseLeaves())));
   }
   private Block createWisteriaVines(String prefix) {
      return createBlockWithItem(prefix + this.getName() + "_vines",
              new WisteriaVine(FabricBlockSettings
              .of()
              .pushReaction(PushReaction.DESTROY)
              .randomTicks()
              .noCollission()
              .noOcclusion()
              .instabreak()
              .sound(SoundType.WEEPING_VINES)));
   }
   private Block createWisteriaVinesPlant(String prefix, Block vines) {
      return registerBlock(prefix + this.getName() + "_vines_plant", new WisteriaVinePlant(FabricBlockSettings
              .of()
              .pushReaction(PushReaction.DESTROY)
              .noCollission()
              .noOcclusion()
              .instabreak()
              .sound(SoundType.WEEPING_VINES)
              .dropsLike(vines), vines));
   }
   private Block createWillowLeaves() {
      return createBlockWithItem(this.getName() + "_leaves", new WillowLeaves(BlockBehaviour.Properties.copy(getBaseLeaves())));
   }
   private Block createWillowVines() {
      return createBlockWithItem(this.getName() + "_vines",
              new WillowVine(FabricBlockSettings
                      .of()
                      .pushReaction(PushReaction.DESTROY)
                      .randomTicks()
                      .noCollission()
                      .noOcclusion()
                      .instabreak()
                      .sound(SoundType.WEEPING_VINES)));
   }
   private Block createWillowVinesPlant(Block vines) {
      return registerBlock(this.getName() + "_vines_plant", new WillowVinePlant(FabricBlockSettings
              .of()
              .pushReaction(PushReaction.DESTROY)
              .noCollission()
              .noOcclusion()
              .instabreak()
              .sound(SoundType.WEEPING_VINES)
              .dropsLike(vines)));
   }
   private Block createPlanks(){
      return createBlockWithItem(this.getName() + "_planks", new Block(BlockBehaviour.Properties.copy(getBase()).sound(getBlockSetType().soundType()).mapColor(getTopColor())));
   }
   private Block createStairs(){
      return createBlockWithItem(this.getName() + "_stairs", new StairBlock(getBase().defaultBlockState(), BlockBehaviour.Properties.copy(getBase()).sound(getBlockSetType().soundType()).mapColor(getTopColor())));
   }
   private Block createSlab(){
      return createBlockWithItem(this.getName() + "_slab", new SlabBlock(BlockBehaviour.Properties.copy(getBase()).sound(getBlockSetType().soundType()).mapColor(getTopColor())));
   }
   private Block createMosaic(){
      return createBlockWithItem(this.getName() + "_mosaic", new Block(BlockBehaviour.Properties.copy(getBase()).sound(getBlockSetType().soundType()).mapColor(getTopColor())));
   }
   private Block createMosaicStairs(){
      return createBlockWithItem(this.getName() + "_mosaic_stairs", new StairBlock(getBase().defaultBlockState(), BlockBehaviour.Properties.copy(getBase()).sound(getBlockSetType().soundType()).mapColor(getTopColor())));
   }
   private Block createMosaicSlab(){
      return createBlockWithItem(this.getName() + "_mosaic_slab", new SlabBlock(BlockBehaviour.Properties.copy(getBase()).sound(getBlockSetType().soundType()).mapColor(getTopColor())));
   }
   private Block createFence(){
      return createBlockWithItem(this.getName() + "_fence", new FenceBlock(BlockBehaviour.Properties.copy(getBase()).sound(getBlockSetType().soundType()).mapColor(getTopColor())));
   }
   private Block createFenceGate(){
      return createBlockWithItem(this.getName() + "_fence_gate", new FenceGateBlock(BlockBehaviour.Properties.copy(getBase()).sound(getBlockSetType().soundType()).mapColor(getTopColor()), this.getWoodType()));
   }
   private Block createPressurePlate(){
      return createBlockWithItem(this.getName() + "_pressure_plate", new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(getBase()).sound(getBlockSetType().soundType()).mapColor(getTopColor()), this.getBlockSetType()));
   }
   private Block createButton(){
      return createBlockWithItem(this.getName() + "_button", new ButtonBlock(BlockBehaviour.Properties.copy(getBase()).sound(getBlockSetType().soundType()).mapColor(getTopColor()), this.getBlockSetType(), 30, true));
   }
   private Block createDoor(){
      return createBlockWithItem(this.getName() + "_door", new DoorBlock(BlockBehaviour.Properties.copy(getBase()).sound(getBlockSetType().soundType()).noOcclusion().mapColor(getTopColor()), this.getBlockSetType()));
   }
   private Block createTrapDoor(){
      return createBlockWithItem(this.getName() + "_trapdoor", new TrapDoorBlock(BlockBehaviour.Properties.copy(getBase()).sound(getBlockSetType().soundType()).noOcclusion().mapColor(getTopColor()), this.getBlockSetType()));
   }
   private Block createSign(){
      return registerBlock(this.getName() + "_sign", new StandingSignBlock(BlockBehaviour.Properties.copy(getSignBase()).mapColor(this.getTopColor()), woodType));
   }
   private Block createWallSign(){
      return registerBlock(this.getName() + "_wall_sign", new WallSignBlock(BlockBehaviour.Properties.copy(getSignBase()).mapColor(this.getTopColor()).dropsLike(sign), woodType));
   }
   private Block createHangingSign(){
      return registerBlock(this.getName() + "_hanging_sign", new CeilingHangingSignBlock(BlockBehaviour.Properties.copy(getHangingSignBase()).mapColor(this.getTopColor()), woodType));
   }
   private Block createWallHangingSign(){
      return registerBlock(this.getName() + "_wall_hanging_sign", new WallHangingSignBlock(BlockBehaviour.Properties.copy(getHangingSignBase()).mapColor(this.getTopColor()).dropsLike(hangingSign), woodType));
   }

   public Block createSapling(AbstractTreeGrower saplingGenerator) {
      return createBlockWithItem(this.getName() + "_sapling", new SaplingBlock(saplingGenerator, FabricBlockSettings.copy(Blocks.SPRUCE_SAPLING)));
   }
   public Block createPottedSapling(Block sapling) {
      return registerBlock("potted_" + this.getName() + "_sapling", new FlowerPotBlock(sapling, FabricBlockSettings.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
   }
   public Block createSapling(String prefix, AbstractTreeGrower saplingGenerator) {
      return createBlockWithItem(prefix + this.getName() + "_sapling", new SaplingBlock(saplingGenerator, FabricBlockSettings.copy(Blocks.SPRUCE_SAPLING)));
   }
   public Block createPottedSapling(String prefix, Block sapling) {
      return registerBlock("potted_" + prefix + this.getName() + "_sapling", new FlowerPotBlock(sapling, FabricBlockSettings.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
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
         return BlockSetTypeAccessor.registerNew(new BlockSetType(this.getNameID().toString(), true, SoundType.BAMBOO_WOOD, SoundEvents.BAMBOO_WOOD_DOOR_CLOSE, SoundEvents.BAMBOO_WOOD_DOOR_OPEN, SoundEvents.BAMBOO_WOOD_TRAPDOOR_CLOSE, SoundEvents.BAMBOO_WOOD_TRAPDOOR_OPEN, SoundEvents.BAMBOO_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.BAMBOO_WOOD_PRESSURE_PLATE_CLICK_ON, SoundEvents.BAMBOO_WOOD_BUTTON_CLICK_OFF, SoundEvents.BAMBOO_WOOD_BUTTON_CLICK_ON));
      }
      else if (this.getWoodPreset() == WoodPreset.FANCY){
         return BlockSetTypeAccessor.registerNew(new BlockSetType(this.getNameID().toString(), true, SoundType.CHERRY_WOOD, SoundEvents.CHERRY_WOOD_DOOR_CLOSE, SoundEvents.CHERRY_WOOD_DOOR_OPEN, SoundEvents.CHERRY_WOOD_TRAPDOOR_CLOSE, SoundEvents.CHERRY_WOOD_TRAPDOOR_OPEN, SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_ON, SoundEvents.CHERRY_WOOD_BUTTON_CLICK_OFF, SoundEvents.CHERRY_WOOD_BUTTON_CLICK_ON));
      }
      else if (this.woodPreset == WoodPreset.NETHER){
         return BlockSetTypeAccessor.registerNew(new BlockSetType(this.getNameID().toString(), true, SoundType.NETHER_WOOD, SoundEvents.NETHER_WOOD_DOOR_CLOSE, SoundEvents.NETHER_WOOD_DOOR_OPEN, SoundEvents.NETHER_WOOD_TRAPDOOR_CLOSE, SoundEvents.NETHER_WOOD_TRAPDOOR_OPEN, SoundEvents.NETHER_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.NETHER_WOOD_PRESSURE_PLATE_CLICK_ON, SoundEvents.NETHER_WOOD_BUTTON_CLICK_OFF, SoundEvents.NETHER_WOOD_BUTTON_CLICK_ON));
      }
      else{
         return BlockSetTypeAccessor.registerNew(new BlockSetType(this.getNameID().toString()));
      }
   }

   public boolean isNormalWood(){
      return this.getWoodPreset() == WoodPreset.DEFAULT || this.getWoodPreset() == WoodPreset.FANCY;
   }
   public boolean hasBark(){
      return this.getWoodPreset() != WoodPreset.JOSHUA && this.getWoodPreset() != WoodPreset.BAMBOO;
   }
   public enum WoodPreset {
      DEFAULT,
      MAPLE,
      JOSHUA,
      WISTERIA,
      WILLOW,
      FANCY,
      NETHER,
      BAMBOO
   }

   public static void addToBuildingTab(Item proceedingItem, Item logPlacement, Item signPlacement, Item boatPlacement,WoodSet woodset){
      ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(entries -> {
         entries.addAfter(proceedingItem, woodset.getLog());
         if (woodset.getWoodPreset() != WoodPreset.BAMBOO && woodset.getWoodPreset() != WoodPreset.JOSHUA){
            entries.addAfter(woodset.getLog(), woodset.getWood());
            entries.addAfter(woodset.getWood(), woodset.getStrippedLog(),woodset.getStrippedWood(), woodset.getPlanks());
         }
         else if (woodset.getWoodPreset() == WoodPreset.BAMBOO) {
            entries.addAfter(woodset.getLog(), woodset.getStrippedLog(),woodset.getMosaic(), woodset.getMosaicStairs(), woodset.getMosaicSlab(), woodset.getPlanks());
         }
         else if (woodset.getWoodPreset() == WoodPreset.JOSHUA) {
            entries.addAfter(woodset.getLog(), woodset.getStrippedLog(), woodset.getPlanks());
         }
         entries.addAfter(woodset.getPlanks(), woodset.getStairs(), woodset.getSlab(),
                 woodset.getFence(), woodset.getFenceGate(),
                 woodset.getDoor(), woodset.getTrapDoor(),
                 woodset.getPressurePlate(), woodset.getButton());
      });
      ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(logPlacement, woodset.getLog().asItem()));
      ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(entries -> entries.addAfter(signPlacement, woodset.getSignItem(), woodset.getHangingSignItem()));
      ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> entries.addAfter(boatPlacement, woodset.getBoatItem(), woodset.getChestBoatItem()));
   }

}