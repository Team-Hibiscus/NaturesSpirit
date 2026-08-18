package net.hibiscus.naturespirit.registration.sets;

import net.hibiscus.naturespirit.NSCommonHooks;
import net.hibiscus.naturespirit.blocks.LargeFlowerBlock;
import net.hibiscus.naturespirit.blocks.MidFlowerBlock;
import net.hibiscus.naturespirit.registration.NSBlockHolder;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import static net.hibiscus.naturespirit.registration.NSRegistryHelper.*;

public class FlowerSet {

  private final String name;
  private final Item dyeColor;
  private final Holder<MobEffect> statusEffect;
  private final FlowerPreset preset;
  private NSBlockHolder<?> flowerBlock;
  private NSBlockHolder<FlowerPotBlock> pottedFlowerBlock;

  public FlowerSet(String name, Item dyeColor, Holder<MobEffect> statusEffect, FlowerPreset preset) {
    this.name = name;
    this.dyeColor = dyeColor;
    this.statusEffect = statusEffect;
    this.preset = preset;
    this.registerFlower();
  }

  public FlowerSet(String name, Item dyeColor, FlowerPreset preset) {
    this.name = name;
    this.dyeColor = dyeColor;
    this.statusEffect = null;
    this.preset = preset;
    this.registerFlower();
  }

  public FlowerSet(String name, Holder<MobEffect> statusEffect, FlowerPreset preset) {
    this.name = name;
    this.dyeColor = null;
    this.statusEffect = statusEffect;
    this.preset = preset;
    this.registerFlower();
  }

  public FlowerSet(String name, FlowerPreset preset) {
    this.name = name;
    this.dyeColor = null;
    this.statusEffect = null;
    this.preset = preset;
    this.registerFlower();
  }

  private void registerFlower() {
    if (isTall()) {
      flowerBlock = registerTallPlantBlock(name, TallFlowerBlock::new, () -> BlockBehaviour.Properties.of()
              .noCollision()
              .instabreak()
              .sound(SoundType.GRASS)
              .ignitedByLava()
              .offsetType(BlockBehaviour.OffsetType.XZ)
              .pushReaction(PushReaction.DESTROY)
      );

    } else if (preset == FlowerPreset.BIG_SMALL) {
      flowerBlock = registerTransparentBlock(name, properties -> new LargeFlowerBlock(statusEffect, 7, properties), () -> BlockBehaviour.Properties.of()
              .mapColor(MapColor.PLANT)
              .noCollision()
              .instabreak()
              .sound(SoundType.GRASS)
              .offsetType(BlockBehaviour.OffsetType.XZ)
              .pushReaction(PushReaction.DESTROY)
      );
    } else if (preset == FlowerPreset.SMALL) {
      flowerBlock = registerTransparentBlock(name, properties -> new FlowerBlock(statusEffect, 7, properties), () -> BlockBehaviour.Properties.of()
              .mapColor(MapColor.PLANT)
              .noCollision()
              .instabreak()
              .sound(SoundType.GRASS)
              .offsetType(BlockBehaviour.OffsetType.XZ)
              .pushReaction(PushReaction.DESTROY)
      );
    } else if (preset == FlowerPreset.MID_SMALL) {
      flowerBlock = registerTransparentBlock(name, properties -> new MidFlowerBlock(statusEffect, 7, properties), () -> BlockBehaviour.Properties.of()
              .mapColor(MapColor.PLANT)
              .noCollision()
              .instabreak()
              .sound(SoundType.GRASS)
              .offsetType(BlockBehaviour.OffsetType.XZ)
              .pushReaction(PushReaction.DESTROY)
      );
    }
    if (hasFlowerPot()) {
      pottedFlowerBlock = registerTransparentBlockWithoutItem("potted_" + name, properties -> new FlowerPotBlock(flowerBlock.get(), properties), () -> BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY));
      NSCommonHooks.FLOWER_POTS.add(new NSCommonHooks.PottedPlant(flowerBlock, pottedFlowerBlock));
    }
  }

  public int getDyeNumber() {
    switch (preset) {
      case TALL, BIG_SMALL -> {
        return 2;
      }
      case BIG_TALL -> {
        return 4;
      }
      default -> {
        return 1;
      }
    }
  }

  public FlowerPreset getPreset() {
    return preset;
  }

  public boolean hasFlowerPot() {
    return getPreset() == FlowerPreset.SMALL || getPreset() == FlowerPreset.BIG_SMALL || getPreset() == FlowerPreset.MID_SMALL;
  }

  public boolean isTall() {
    return getPreset() == FlowerPreset.TALL || getPreset() == FlowerPreset.BIG_TALL;
  }

  public Item getDyeColor() {
    return dyeColor;
  }

  public NSBlockHolder<?> getFlowerBlock() {
    return flowerBlock;
  }

  public NSBlockHolder<?> getPottedFlowerBlock() {
    return pottedFlowerBlock;
  }

  public String getName() {
    return name;
  }

  public enum FlowerPreset {
    SMALL, MID_SMALL,
    TALL,
    BIG_SMALL,
    BIG_TALL
  }
}
