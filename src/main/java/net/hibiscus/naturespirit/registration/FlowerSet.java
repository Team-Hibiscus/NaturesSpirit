package net.hibiscus.naturespirit.registration;

import net.hibiscus.naturespirit.blocks.LargeFlowerBlock;
import net.hibiscus.naturespirit.blocks.LargeTallFlowerBlock;
import net.hibiscus.naturespirit.blocks.MidFlowerBlock;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.BlockSoundGroup;

import java.util.ArrayList;
import java.util.List;

import static net.hibiscus.naturespirit.registration.HibiscusRegistryHelper.registerPlantBlock;
import static net.hibiscus.naturespirit.registration.HibiscusRegistryHelper.registerPottedPlant;

public class FlowerSet {

   private String name;
   private Item dyeColor;
   private RegistryEntry <StatusEffect> statusEffect;
   private Item itemBefore;
   private FlowerPreset preset;
   private Block flowerBlock;
   private Block pottedFlowerBlock;
   private List <Block> registeredBlocksList = new ArrayList <>();
   private List<Item> registeredItemsList = new ArrayList<>();
   public FlowerSet(String name, Item dyeColor, RegistryEntry <StatusEffect> statusEffect, Item itemBefore,FlowerPreset preset) {
      this.name = name;
      this.dyeColor = dyeColor;
      this.statusEffect = statusEffect;
      this.itemBefore = itemBefore;
      this.preset = preset;
      this.registerFlower();
   }
   public FlowerSet(String name, Item dyeColor, Item itemBefore,FlowerPreset preset) {
      this.name = name;
      this.dyeColor = dyeColor;
      this.statusEffect = null;
      this.itemBefore = itemBefore;
      this.preset = preset;
      this.registerFlower();
   }
   public FlowerSet(String name, RegistryEntry <StatusEffect> statusEffect, Item itemBefore,FlowerPreset preset) {
      this.name = name;
      this.dyeColor = null;
      this.statusEffect = statusEffect;
      this.itemBefore = itemBefore;
      this.preset = preset;
      this.registerFlower();
   }
   public FlowerSet(String name, Item itemBefore, FlowerPreset preset) {
      this.name = name;
      this.dyeColor = null;
      this.statusEffect = null;
      this.itemBefore = itemBefore;
      this.preset = preset;
      this.registerFlower();
   }

   private void registerFlower() {
      if(this.preset == FlowerPreset.BIG_TALL) {
      this.flowerBlock = registerPlantBlock(this.name,
              new LargeTallFlowerBlock(AbstractBlock.Settings
                      .create()
                      .noCollision()
                      .breakInstantly()
                      .sounds(BlockSoundGroup.GRASS)
                      .burnable()
                      .offset(AbstractBlock.OffsetType.XZ)
                      .pistonBehavior(PistonBehavior.DESTROY)),
              HibiscusItemGroups.NS_MISC_ITEM_GROUP,
              this.itemBefore,
              0.4f
      );
   } else
      if(this.preset == FlowerPreset.TALL) {
         this.flowerBlock = registerPlantBlock(this.name,
                 new TallFlowerBlock(AbstractBlock.Settings
                         .create()
                         .noCollision()
                         .breakInstantly()
                         .sounds(BlockSoundGroup.GRASS)
                         .burnable()
                         .offset(AbstractBlock.OffsetType.XZ)
                         .pistonBehavior(PistonBehavior.DESTROY)),
                 HibiscusItemGroups.NS_MISC_ITEM_GROUP,
                 this.itemBefore,
                 0.4f
         );
      } else
      if(this.preset == FlowerPreset.BIG_SMALL) {
         this.flowerBlock = registerPlantBlock(this.name, new LargeFlowerBlock(
                 this.statusEffect,
                 7,
                 AbstractBlock.Settings
                         .create()
                         .mapColor(MapColor.DARK_GREEN)
                         .noCollision()
                         .breakInstantly()
                         .sounds(BlockSoundGroup.GRASS)
                         .offset(AbstractBlock.OffsetType.XZ)
                         .pistonBehavior(PistonBehavior.DESTROY)
         ), HibiscusItemGroups.NS_MISC_ITEM_GROUP, this.itemBefore, 0.4f);
      } else
      if(this.preset == FlowerPreset.SMALL) {
         this.flowerBlock = registerPlantBlock(this.name, new FlowerBlock(
                 this.statusEffect,
                 7,
                 AbstractBlock.Settings
                         .create()
                         .mapColor(MapColor.DARK_GREEN)
                         .noCollision()
                         .breakInstantly()
                         .sounds(BlockSoundGroup.GRASS)
                         .offset(AbstractBlock.OffsetType.XZ)
                         .pistonBehavior(PistonBehavior.DESTROY)
         ), HibiscusItemGroups.NS_MISC_ITEM_GROUP, this.itemBefore, 0.3f);
      }else
      if(this.preset == FlowerPreset.ANEMONE) {
         this.flowerBlock = registerPlantBlock(this.name, new MidFlowerBlock(
                 this.statusEffect,
                 7,
                 AbstractBlock.Settings
                         .create()
                         .mapColor(MapColor.DARK_GREEN)
                         .noCollision()
                         .breakInstantly()
                         .sounds(BlockSoundGroup.GRASS)
                         .offset(AbstractBlock.OffsetType.XZ)
                         .pistonBehavior(PistonBehavior.DESTROY)
         ), HibiscusItemGroups.NS_MISC_ITEM_GROUP, this.itemBefore, 0.3f);
      }
      if (this.hasFlowerPot()) {
         this.pottedFlowerBlock = registerPottedPlant(this.name, this.flowerBlock);
         registeredBlocksList.add(this.pottedFlowerBlock);
      }
      registeredBlocksList.add(this.flowerBlock);
      registeredItemsList.add(this.flowerBlock.asItem());
      HibiscusRegistryHelper.FlowerHashMap.put(this.name, this);
   }

   public int getDyeNumber() {
      switch (this.preset) {
         default -> {return 1;}
         case TALL, BIG_SMALL -> {return 2;}
         case BIG_TALL -> {return 4;}
      }
   }

   public List <Block> getRegisteredBlocksList() {
      return registeredBlocksList;
   }

   public List <Item> getRegisteredItemsList() {
      return registeredItemsList;
   }

   public FlowerPreset getPreset () {
      return this.preset;
   }
   public boolean hasFlowerPot() {
      return this.getPreset() == FlowerPreset.SMALL || this.getPreset() == FlowerPreset.BIG_SMALL || this.getPreset() == FlowerPreset.ANEMONE;
   }

   public boolean isTall() {
      return this.getPreset() == FlowerPreset.TALL || this.getPreset() == FlowerPreset.BIG_TALL;
   }
   public Item getDyeColor() {
      return this.dyeColor;
   }
   public Block getFlowerBlock() {
      return this.flowerBlock;
   }
   public Block getPottedFlowerBlock() {
      return this.pottedFlowerBlock;
   }
   public String getName() {
      return this.name;
   }

   public enum FlowerPreset {
      SMALL,
      ANEMONE,
      TALL,
      BIG_SMALL,
      BIG_TALL
   }
}
