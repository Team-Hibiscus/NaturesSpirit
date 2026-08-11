package net.hibiscus.naturespirit.registration.sets;

import net.hibiscus.naturespirit.blocks.LargeFlowerBlock;
import net.hibiscus.naturespirit.blocks.MidFlowerBlock;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;

import static net.hibiscus.naturespirit.registration.NSRegistryHelper.*;

public class FlowerSet {

  private final String name;
  private final Item dyeColor;
  private final Holder<MobEffect> statusEffect;
  private final FlowerPreset preset;
  private DeferredBlock<?> flowerBlock;
  private DeferredBlock<FlowerPotBlock> pottedFlowerBlock;

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
      flowerBlock = registerTallPlantBlock(name, () -> new TallFlowerBlock(BlockBehaviour.Properties.of()
              .noCollission()
              .instabreak()
              .sound(SoundType.GRASS)
              .ignitedByLava()
              .offsetType(BlockBehaviour.OffsetType.XZ)
              .pushReaction(PushReaction.DESTROY))
      );

    } else if (preset == FlowerPreset.BIG_SMALL) {
      flowerBlock = registerTransparentBlock(name, () -> new LargeFlowerBlock(statusEffect, 7, BlockBehaviour.Properties.of()
              .mapColor(MapColor.PLANT)
              .noCollission()
              .instabreak()
              .sound(SoundType.GRASS)
              .offsetType(BlockBehaviour.OffsetType.XZ)
              .pushReaction(PushReaction.DESTROY)
      ));
    } else if (preset == FlowerPreset.SMALL) {
      flowerBlock = registerTransparentBlock(name, () -> new FlowerBlock(statusEffect, 7, BlockBehaviour.Properties.of()
              .mapColor(MapColor.PLANT)
              .noCollission()
              .instabreak()
              .sound(SoundType.GRASS)
              .offsetType(BlockBehaviour.OffsetType.XZ)
              .pushReaction(PushReaction.DESTROY)
      ));
    } else if (preset == FlowerPreset.MID_SMALL) {
      flowerBlock = registerTransparentBlock(name, () -> new MidFlowerBlock(statusEffect, 7, BlockBehaviour.Properties.of()
              .mapColor(MapColor.PLANT)
              .noCollission()
              .instabreak()
              .sound(SoundType.GRASS)
              .offsetType(BlockBehaviour.OffsetType.XZ)
              .pushReaction(PushReaction.DESTROY)
      ));
    }
    if (hasFlowerPot()) {
      pottedFlowerBlock = registerTransparentBlockWithoutItem("potted_" + name, () -> new FlowerPotBlock( (() -> (FlowerPotBlock) Blocks.FLOWER_POT), flowerBlock, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
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

  public DeferredBlock<?> getFlowerBlock() {
    return flowerBlock;
  }

  public DeferredBlock<?> getPottedFlowerBlock() {
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
