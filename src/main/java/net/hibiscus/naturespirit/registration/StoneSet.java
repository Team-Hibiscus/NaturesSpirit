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
import net.hibiscus.naturespirit.entity.HibiscusBoatEntity;
import net.hibiscus.naturespirit.items.HibiscusBoatItem;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusMiscBlocks;
import net.hibiscus.naturespirit.world.tree.*;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.HangingSignItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SignItem;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.ArrayList;
import java.util.List;

import static net.hibiscus.naturespirit.registration.HibiscusRegistryHelper.*;
import static net.hibiscus.naturespirit.registration.block_registration.HibiscusMiscBlocks.SMOOTH_PINK_SANDSTONE_SLAB;
import static net.hibiscus.naturespirit.registration.block_registration.HibiscusMiscBlocks.SMOOTH_PINK_SANDSTONE_STAIRS;
import static net.minecraft.data.family.BlockFamilies.register;

public class StoneSet {


   private Block cobbled;
   private BlockFamily cobbledFamily;
   private Block cobbledStairs;
   private Block cobbledSlab;
   private Block cobbledWall;
   private Block mossyCobbled;
   private BlockFamily mossyCobbledFamily;
   private Block mossyCobbledStairs;
   private Block mossyCobbledSlab;
   private Block mossyCobbledWall;
   private Block base;
   private BlockFamily baseFamily;
   private Block baseStairs;
   private Block baseSlab;
   private Block polished;
   private BlockFamily polishedFamily;
   private Block polishedStairs;
   private Block polishedSlab;
   private Block polishedWall;
   private Block tiles;
   private BlockFamily tileFamily;
   private Block tilesStairs;
   private Block tilesSlab;
   private Block tilesWall;
   private Block bricks;
   private BlockFamily brickFamily;
   private Block bricksStairs;
   private Block bricksSlab;
   private Block bricksWall;
   private Block chiseled;
   private Block crackedBricks;
   private Block mossyBricks;
   private BlockFamily mossyBrickFamily;
   private Block mossyBricksStairs;
   private Block mossyBricksSlab;
   private Block mossyBricksWall;
   private List<Block> registeredBlocksList = new ArrayList<>();

   private Identifier name;
   private MapColor mapColor;
   private Item itemBefore;
   private Item item2Before;
   private boolean hasTiles;
   private boolean hasCobbled;
   private boolean hasCracked;
   private boolean hasMossy;
   private float hardness;



   private void registerStone(){

      base = createBasic(this.getName(), Blocks.ANDESITE);
      baseStairs = createStairs(this.getName(), base);
      baseSlab = createSlab(this.getName(), base);
      chiseled = createBasic("chiseled_" + this.getName(), Blocks.CHISELED_STONE_BRICKS);

      if(this.hasCobbled()) {
         cobbled = createBasic("cobbled_" + this.getName(), Blocks.COBBLESTONE);
         cobbledStairs = createStairs("cobbled_" + this.getName(), cobbled);
         cobbledSlab = createSlab("cobbled_" + this.getName(), cobbled);
         cobbledWall = createWall("cobbled_" + this.getName(), cobbled);
         cobbledFamily = register(cobbled).slab(cobbledSlab).stairs(cobbledStairs).chiseled(chiseled).wall(cobbledWall).build();
      }
      if (this.hasMossy() && this.hasCobbled()) {
         mossyCobbled = createBasic("mossy_cobbled_" + this.getName(), Blocks.MOSSY_COBBLESTONE);
         mossyCobbledStairs = createStairs("mossy_cobbled_" + this.getName(), mossyCobbled);
         mossyCobbledSlab = createSlab("mossy_cobbled_" + this.getName(), mossyCobbled);
         mossyCobbledWall = createWall("mossy_cobbled_" + this.getName(), mossyCobbled);
         mossyCobbledFamily = register(mossyCobbled).slab(mossyCobbledSlab).stairs(mossyCobbledStairs).wall(mossyCobbledWall).build();
      }

      polished = createBasic("polished_" + this.getName(), Blocks.POLISHED_ANDESITE);
      polishedStairs = createStairs("polished_" + this.getName(), polished);
      polishedSlab = createSlab("polished_" + this.getName(), polished);
      polishedWall = createWall("polished_" + this.getName(), polished);
      polishedFamily = register(polished).slab(polishedSlab).stairs(polishedStairs).wall(polishedWall).build();

      bricks = createBasic(this.getName() + "_bricks", Blocks.STONE_BRICKS);
      bricksStairs = createStairs(this.getName() + "_brick", bricks);
      bricksSlab = createSlab(this.getName() + "_brick", bricks);
      bricksWall = createWall(this.getName() + "_brick", bricks);

      if(this.hasMossy()) {
         mossyBricks = createBasic("mossy_" + this.getName() + "_bricks", Blocks.MOSSY_STONE_BRICKS);
         mossyBricksStairs = createStairs("mossy_" + this.getName() + "_brick", bricks);
         mossyBricksSlab = createSlab("mossy_" + this.getName() + "_brick", bricks);
         mossyBricksWall = createWall("mossy_" + this.getName() + "_brick", bricks);
         mossyBrickFamily = register(mossyBricks).slab(mossyBricksSlab).stairs(mossyBricksStairs).wall(mossyBricksWall).build();
      }
      if(this.hasCracked()) {
         crackedBricks = createBasic("cracked_" + this.getName() + "_bricks", Blocks.CRACKED_STONE_BRICKS);
         brickFamily = register(bricks).slab(bricksSlab).stairs(bricksStairs).wall(bricksWall).cracked(crackedBricks).build();
      }
      else {
         brickFamily = register(bricks).slab(bricksSlab).stairs(bricksStairs).wall(bricksWall).build();
      }
      if(this.hasTiles()) {
         tiles = createBasic(this.getName() + "_tiles", Blocks.COBBLESTONE);
         tilesStairs = createStairs(this.getName() + "_tile", polished);
         tilesSlab = createSlab(this.getName() + "_tile", polished);
         tilesWall = createWall(this.getName() + "_tile", polished);
         tileFamily = register(tiles).slab(tilesSlab).stairs(tilesStairs).wall(tilesWall).build();
      }


      if (this.hasCobbled()) {
         baseFamily = register(base).slab(baseSlab).stairs(baseStairs).polished(polished).build();
      }
      else {
         baseFamily = register(base).slab(baseSlab).stairs(baseStairs).polished(polished).chiseled(chiseled).build();
      }
      addToBuildingTab(this.itemBefore, this.item2Before, this);
      StoneHashMap.put(this.getName(), this);

      for(Block item : this.getRegisteredBlocksList()) ItemGroupEvents.modifyEntriesEvent(HibiscusItemGroups.NS_MISC_ITEM_GROUP).register(entries -> entries.add(item));
   }

   public StoneSet(Identifier name, MapColor mapColor, Item itemBefore, Item item2Before, float hardness, boolean hasCobbled, boolean hasCracked, boolean hasMossy, boolean hasTiles){
      this.name = name;
      this.mapColor = mapColor;
      this.itemBefore = itemBefore;
      this.item2Before = item2Before;
      this.hardness = hardness;
      this.hasTiles = hasTiles;
      this.hasCobbled = hasCobbled;
      this.hasCracked = hasCracked;
      this.hasMossy = hasMossy;
      registerStone();
   }
   private Block createBlockWithItem(String blockID, Block block){
      registerBlockItem(blockID, block);
      Block listBlock = HibiscusRegistryHelper.registerBlock(blockID, block);
      registeredBlocksList.add(listBlock);
      return listBlock;
   }

   public String getName() {
      return name.getPath();
   }
   public List<Block> getRegisteredBlocksList() {
      return registeredBlocksList;
   }
   private Block createBasic(String name, Block template){
      return createBlockWithItem(name, new Block(AbstractBlock.Settings.copy(template).hardness(this.hardness).mapColor(getMapColor())));
   }
   private Block createStairs(String name, Block template){
      return createBlockWithItem(name + "_stairs", new StairsBlock(template.getDefaultState(), AbstractBlock.Settings.copy(template)));
   }
   private Block createSlab(String name, Block template){
      return createBlockWithItem(name + "_slab", new SlabBlock(AbstractBlock.Settings.copy(template)));
   }
   private Block createWall(String name, Block template){
      return createBlockWithItem(name + "_wall", new WallBlock(AbstractBlock.Settings.copy(template).solid()));
   }

   public boolean hasTiles(){
      return this.hasTiles;
   }
   public boolean hasCracked(){
      return this.hasCracked;
   }
   public boolean hasCobbled(){
      return this.hasCobbled;
   }
   public boolean hasMossy(){
      return this.hasMossy;
   }

   public static void addToBuildingTab(Item proceedingItem, Item naturalStonePlacement, StoneSet stoneSet){
      ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
         entries.addAfter(proceedingItem, stoneSet.getBase(), stoneSet.getBaseStairs(), stoneSet.getBaseSlab(),
                 stoneSet.getChiseled(), stoneSet.getPolished(), stoneSet.getPolishedStairs(), stoneSet.getPolishedSlab(), stoneSet.getPolishedWall(), stoneSet.getBricks(),
                 stoneSet.getBricksStairs(), stoneSet.getBricksSlab(), stoneSet.getBricksWall());
         if(stoneSet.hasCobbled()) {
            entries.addAfter(stoneSet.getBaseSlab(), stoneSet.getCobbled(), stoneSet.getCobbledStairs(), stoneSet.getCobbledSlab(), stoneSet.getCobbledWall());
            if (stoneSet.hasMossy()) {
               entries.addAfter(stoneSet.getCobbledWall(), stoneSet.getMossyCobbled(), stoneSet.getMossyCobbledStairs(), stoneSet.getMossyCobbledSlab(), stoneSet.getMossyCobbledWall());
            }
         }
         if (stoneSet.hasCracked()) {
            entries.addAfter(stoneSet.getBricks(), stoneSet.getCrackedBricks());
         }
         if (stoneSet.hasTiles()) {
            entries.addAfter(stoneSet.getBricksWall(), stoneSet.getTiles(), stoneSet.getTilesStairs(), stoneSet.getTilesSlab(), stoneSet.getTilesWall());
         }
         if (stoneSet.hasMossy()) {
            entries.addAfter(stoneSet.getBricksWall(), stoneSet.getMossyBricks(), stoneSet.getMossyBricksStairs(), stoneSet.getMossyBricksSlab(), stoneSet.getMossyBricksWall());
         }
      });
      ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(naturalStonePlacement, stoneSet.getBase()));
   }

   public Block getCobbled() {
      return cobbled;
   }

   public Block getCobbledStairs() {
      return cobbledStairs;
   }

   public Block getCobbledSlab() {
      return cobbledSlab;
   }

   public Block getCobbledWall() {
      return cobbledWall;
   }

   public Block getBase() {
      return base;
   }

   public Block getBaseStairs() {
      return baseStairs;
   }

   public Block getBaseSlab() {
      return baseSlab;
   }

   public Block getPolished() {
      return polished;
   }

   public Block getPolishedStairs() {
      return polishedStairs;
   }

   public Block getPolishedSlab() {
      return polishedSlab;
   }

   public Block getPolishedWall() {
      return polishedWall;
   }

   public Block getTiles() {
      return tiles;
   }

   public Block getTilesStairs() {
      return tilesStairs;
   }

   public Block getTilesSlab() {
      return tilesSlab;
   }

   public Block getTilesWall() {
      return tilesWall;
   }

   public Block getBricks() {
      return bricks;
   }

   public Block getBricksStairs() {
      return bricksStairs;
   }

   public Block getBricksSlab() {
      return bricksSlab;
   }

   public Block getBricksWall() {
      return bricksWall;
   }

   public Block getChiseled() {
      return chiseled;
   }

   public Block getCrackedBricks() {
      return crackedBricks;
   }

   public Block getMossyBricks() {
      return mossyBricks;
   }

   public Block getMossyBricksStairs() {
      return mossyBricksStairs;
   }

   public Block getMossyBricksSlab() {
      return mossyBricksSlab;
   }

   public Block getMossyBricksWall() {
      return mossyBricksWall;
   }
   public Block getMossyCobbled() {
      return mossyCobbled;
   }

   public Block getMossyCobbledStairs() {
      return mossyCobbledStairs;
   }

   public Block getMossyCobbledSlab() {
      return mossyCobbledSlab;
   }

   public Block getMossyCobbledWall() {
      return mossyCobbledWall;
   }
   public MapColor getMapColor() {
      return mapColor;
   }

   public BlockFamily getMossyBrickFamily() {
      return mossyBrickFamily;
   }

   public BlockFamily getBrickFamily() {
      return brickFamily;
   }

   public BlockFamily getTileFamily() {
      return tileFamily;
   }

   public BlockFamily getPolishedFamily() {
      return polishedFamily;
   }

   public BlockFamily getBaseFamily() {
      return baseFamily;
   }

   public BlockFamily getCobbledFamily() {
      return cobbledFamily;
   }
   public BlockFamily getMossyCobbledFamily() {
      return mossyCobbledFamily;
   }
}