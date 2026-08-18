package net.hibiscus.naturespirit.registration.sets;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import net.hibiscus.naturespirit.NSCommonHooks;
import net.hibiscus.naturespirit.blocks.*;
import net.hibiscus.naturespirit.datagen.NSConfiguredFeatures;
import net.hibiscus.naturespirit.registration.NSBlockHolder;
import net.hibiscus.naturespirit.registration.NSEntityTypes;
import net.hibiscus.naturespirit.registration.NSFlammables;
import net.hibiscus.naturespirit.registration.NSFuels;
import net.hibiscus.naturespirit.registration.NSItemHolder;
import net.hibiscus.naturespirit.registration.NSParticleTypes;
import net.hibiscus.naturespirit.registration.NSRegistryHelper;
import net.hibiscus.naturespirit.registration.NSStrippables;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import static net.hibiscus.naturespirit.NaturesSpirit.MOD_ID;
import static net.hibiscus.naturespirit.registration.NSRegistryHelper.*;

public class WoodSet {

  private final List<NSBlockHolder<? extends Block>> registeredBlocksList = new ArrayList<>();
  private final List<NSItemHolder<? extends Item>> registeredItemsList = new ArrayList<>();
  private final String name;
  private final MapColor sideColor;
  private final MapColor topColor;
  private final WoodPreset woodPreset;
  private Supplier<BlockSetType> blockSetType;
  private Supplier<WoodType> woodType;
  private NSBlockHolder<? extends Block> log;
  private NSBlockHolder<? extends Block> strippedLog;
  private NSBlockHolder<RotatedPillarBlock> bundle;
  private NSBlockHolder<RotatedPillarBlock> strippedBundle;
  private NSBlockHolder<RotatedPillarBlock> wood;
  private NSBlockHolder<RotatedPillarBlock> strippedWood;
  private NSBlockHolder<? extends LeavesBlock> leaves;
  private NSBlockHolder<? extends LeavesBlock> frostyLeaves;
  private NSBlockHolder<? extends SaplingBlock> sapling;
  private NSBlockHolder<FlowerPotBlock> pottedSapling;
  private NSBlockHolder<? extends LeavesBlock> redLeaves;
  private NSBlockHolder<? extends SaplingBlock> redSapling;
  private NSBlockHolder<FlowerPotBlock> pottedRedSapling;
  private NSBlockHolder<? extends LeavesBlock> orangeLeaves;
  private NSBlockHolder<? extends SaplingBlock> orangeSapling;
  private NSBlockHolder<FlowerPotBlock> pottedOrangeSapling;
  private NSBlockHolder<? extends LeavesBlock> yellowLeaves;
  private NSBlockHolder<? extends SaplingBlock> yellowSapling;
  private NSBlockHolder<FlowerPotBlock> pottedYellowSapling;
  private NSBlockHolder<? extends LeavesBlock> blueLeaves;
  private NSBlockHolder<? extends LeavesBlock> partBlueLeaves;
  private NSBlockHolder<? extends SaplingBlock> blueSapling;
  private NSBlockHolder<FlowerPotBlock> pottedBlueSapling;
  private NSBlockHolder<? extends LeavesBlock> purpleLeaves;
  private NSBlockHolder<? extends LeavesBlock> partPurpleLeaves;
  private NSBlockHolder<? extends SaplingBlock> purpleSapling;
  private NSBlockHolder<FlowerPotBlock> pottedPurpleSapling;
  private NSBlockHolder<? extends LeavesBlock> pinkLeaves;
  private NSBlockHolder<? extends LeavesBlock> partPinkLeaves;
  private NSBlockHolder<? extends SaplingBlock> pinkSapling;
  private NSBlockHolder<FlowerPotBlock> pottedPinkSapling;
  private NSBlockHolder<? extends LeavesBlock> whiteLeaves;
  private NSBlockHolder<? extends LeavesBlock> partWhiteLeaves;
  private NSBlockHolder<? extends SaplingBlock> whiteSapling;
  private NSBlockHolder<FlowerPotBlock> pottedWhiteSapling;
  private NSBlockHolder<DownwardVineBlock> vines;
  private NSBlockHolder<DownwardsVinePlantBlock> vinesPlant;
  private NSBlockHolder<DownwardVineBlock> blueVines;
  private NSBlockHolder<DownwardVineBlock> purpleVines;
  private NSBlockHolder<DownwardVineBlock> pinkVines;
  private NSBlockHolder<DownwardVineBlock> whiteVines;
  private NSBlockHolder<DownwardsVinePlantBlock> blueVinesPlant;
  private NSBlockHolder<DownwardsVinePlantBlock> purpleVinesPlant;
  private NSBlockHolder<DownwardsVinePlantBlock> pinkVinesPlant;
  private NSBlockHolder<DownwardsVinePlantBlock> whiteVinesPlant;
  private NSBlockHolder<Block> planks;
  private NSBlockHolder<StairBlock> stairs;
  private NSBlockHolder<SlabBlock> slab;
  private NSBlockHolder<Block> mosaic;
  private NSBlockHolder<StairBlock> mosaicStairs;
  private NSBlockHolder<SlabBlock> mosaicSlab;
  private NSBlockHolder<FenceBlock> fence;
  private NSBlockHolder<FenceGateBlock> fenceGate;
  private NSBlockHolder<PressurePlateBlock> pressurePlate;
  private NSBlockHolder<ButtonBlock> button;
  private NSBlockHolder<DoorBlock> door;
  private NSBlockHolder<TrapDoorBlock> trapDoor;
  private NSBlockHolder<StandingSignBlock> sign;
  private NSBlockHolder<WallSignBlock> wallSign;
  private NSBlockHolder<CeilingHangingSignBlock> hangingSign;
  private NSBlockHolder<WallHangingSignBlock> hangingWallSign;
  private NSItemHolder<SignItem> signItem;
  private NSItemHolder<HangingSignItem> hangingSignItem;
  private NSItemHolder<BoatItem> boatItem;
  private NSItemHolder<BoatItem> chestBoatItem;
  private final TreeGrower saplingGenerator;
  private final boolean hasMosaic;

  private void registerWood() {
    blockSetType = createBlockSetType();
    woodType = Suppliers.memoize(() -> WoodType.register(new WoodType(MOD_ID + ":" + getName(), blockSetType.get())));

    log = woodPreset == WoodPreset.JOSHUA ? createJoshuaLog() : createLog();
    strippedLog = woodPreset == WoodPreset.JOSHUA ? createStrippedJoshuaLog() : createStrippedLog();
    if (woodPreset == WoodPreset.JOSHUA) {
      bundle = createBundle();
      strippedBundle = createStrippedBundle();
    }


    if (woodPreset != WoodPreset.BAMBOO && woodPreset != WoodPreset.JOSHUA) {
      wood = createWood();
      strippedWood = createStrippedWood();
    }

    if (woodPreset == WoodPreset.JOSHUA) {
      NSStrippables.add(bundle, strippedBundle);
    } else {
      NSStrippables.add(log, strippedLog);
    }
    if (wood != null) {
      NSStrippables.add(wood, strippedWood);
    }

    if (this.hasDefaultLeaves()) {
      leaves = createLeaves();

      if (this.hasDefaultSapling()) {
        sapling = this.isSandy() ? createSandySapling(saplingGenerator) : createSapling(saplingGenerator);
        pottedSapling = createPottedSapling(sapling);
      }
    }

    if (woodPreset == WoodPreset.FROSTABLE) {
      frostyLeaves = createLeaves("frosty_");
      leaves = createFrostableLeaves();
      sapling = createSapling(saplingGenerator);
      pottedSapling = createPottedSapling(sapling);

    }

    if (woodPreset == WoodPreset.WILLOW) {
      vines = createVines(this::getVinesPlant);
      vinesPlant = createVinesPlant(vines);

      leaves = createVinesLeavesBlock(vinesPlant, vines);

      sapling = createSapling(saplingGenerator);
      pottedSapling = createPottedSapling(sapling);
    }
    if (woodPreset == WoodPreset.WISTERIA) {
      whiteVines = createVines("white_", this::getWhiteVinesPlant);
      blueVines = createVines("blue_", this::getBlueVinesPlant);
      pinkVines = createVines("pink_", this::getPinkVinesPlant);
      purpleVines = createVines("purple_", this::getPurpleVinesPlant);
      whiteVinesPlant = createVinesPlant("white_", whiteVines);
      blueVinesPlant = createVinesPlant("blue_", blueVines);
      pinkVinesPlant = createVinesPlant("pink_", pinkVines);
      purpleVinesPlant = createVinesPlant("purple_", purpleVines);
      whiteLeaves = createVinesLeavesBlock("white_", whiteVinesPlant, whiteVines);
      partWhiteLeaves = createVinesLeavesBlock("part_white_", whiteVinesPlant, whiteVines);
      blueLeaves = createVinesLeavesBlock("blue_", blueVinesPlant, blueVines);
      partBlueLeaves = createVinesLeavesBlock("part_blue_", blueVinesPlant, blueVines);
      pinkLeaves = createVinesLeavesBlock("pink_", pinkVinesPlant, pinkVines);
      partPinkLeaves = createVinesLeavesBlock("part_pink_", pinkVinesPlant, pinkVines);
      purpleLeaves = createVinesLeavesBlock("purple_", purpleVinesPlant, purpleVines);
      partPurpleLeaves = createVinesLeavesBlock("part_purple_", purpleVinesPlant, purpleVines);
      whiteSapling = createSapling("white_",
              new TreeGrower(MOD_ID + "_" + this.getName(), Optional.empty(), Optional.of(NSConfiguredFeatures.WHITE_WISTERIA_TREE), Optional.empty()));
      blueSapling = createSapling("blue_",
              new TreeGrower(MOD_ID + "_" + this.getName(), Optional.empty(), Optional.of(NSConfiguredFeatures.BLUE_WISTERIA_TREE), Optional.empty()));
      pinkSapling = createSapling("pink_",
              new TreeGrower(MOD_ID + "_" + this.getName(), Optional.empty(), Optional.of(NSConfiguredFeatures.PINK_WISTERIA_TREE), Optional.empty()));
      purpleSapling = createSapling("purple_",
              new TreeGrower(MOD_ID + "_" + this.getName(), Optional.empty(), Optional.of(NSConfiguredFeatures.PURPLE_WISTERIA_TREE), Optional.empty()));
      pottedWhiteSapling = createPottedSapling("white_", whiteSapling);
      pottedBlueSapling = createPottedSapling("blue_", blueSapling);
      pottedPinkSapling = createPottedSapling("pink_", pinkSapling);
      pottedPurpleSapling = createPottedSapling("purple_", purpleSapling);
    }
    if (woodPreset == WoodPreset.MAPLE) {
      redLeaves = createParticleLeaves("red_", NSParticleTypes.RED_MAPLE_LEAVES_PARTICLE, 100);
      orangeLeaves = createParticleLeaves("orange_", NSParticleTypes.ORANGE_MAPLE_LEAVES_PARTICLE, 100);
      yellowLeaves = createParticleLeaves("yellow_", NSParticleTypes.YELLOW_MAPLE_LEAVES_PARTICLE, 100);
      redSapling = createSapling("red_",
              new TreeGrower(MOD_ID + "_" + this.getName(), Optional.empty(), Optional.of(NSConfiguredFeatures.RED_MAPLE_TREE), Optional.empty()));
      orangeSapling = createSapling("orange_",
              new TreeGrower(MOD_ID + "_" + this.getName(), Optional.empty(), Optional.of(NSConfiguredFeatures.ORANGE_MAPLE_TREE), Optional.empty()));
      yellowSapling = createSapling("yellow_",
              new TreeGrower(MOD_ID + "_" + this.getName(), Optional.empty(), Optional.of(NSConfiguredFeatures.YELLOW_MAPLE_TREE), Optional.empty()));
      pottedRedSapling = createPottedSapling("red_", redSapling);
      pottedOrangeSapling = createPottedSapling("orange_", orangeSapling);
      pottedYellowSapling = createPottedSapling("yellow_", yellowSapling);
    }
    if (woodPreset == WoodPreset.ASPEN) {
      yellowLeaves = createLeaves("yellow_");
    }
    if (this.hasMosaic()) {
      mosaic = createMosaic();
      mosaicStairs = createMosaicStairs();
      mosaicSlab = createMosaicSlab();
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
    boatItem = createItem(getName() + "_boat", properties -> new BoatItem(NSEntityTypes.getBoat(getName()).get(), properties), () -> new Item.Properties().stacksTo(1));
    chestBoatItem = createItem(getName() + "_chest_boat", properties -> new BoatItem(NSEntityTypes.getChestBoat(getName()).get(), properties), () -> new Item.Properties().stacksTo(1));
  }

  public WoodSet(
          String name,
          MapColor sideColor,
          MapColor topColor,
          WoodPreset woodPreset,
          boolean hasMosaic,
          TreeGrower saplingGenerator
          ) {
    this.woodPreset = woodPreset;
    this.name = name;
    this.sideColor = sideColor;
    this.topColor = topColor;
      this.saplingGenerator = saplingGenerator;
      this.hasMosaic = hasMosaic;
    registerWood();
  }

  public String getName() {
    return name;
  }

  public Supplier<BlockSetType> getBlockSetType() {
    return this.blockSetType;
  }

  public WoodPreset getWoodPreset() {
    return woodPreset;
  }

  public MapColor getTopColor() {
    return topColor;
  }

  public Supplier<WoodType> getWoodType() {
    return woodType;
  }

  public NSBlockHolder<ButtonBlock> getButton() {
    return button;
  }

  public NSBlockHolder<FenceBlock> getFence() {
    return fence;
  }

  public NSBlockHolder<Block> getPlanks() {
    return planks;
  }

  public NSBlockHolder<SlabBlock> getSlab() {
    return slab;
  }

  public NSBlockHolder<FenceGateBlock> getFenceGate() {
    return fenceGate;
  }

  public NSBlockHolder<StairBlock> getStairs() {
    return stairs;
  }

  public NSBlockHolder<DoorBlock> getDoor() {
    return door;
  }

  public NSBlockHolder<CeilingHangingSignBlock> getHangingSign() {
    return hangingSign;
  }

  public NSBlockHolder<WallHangingSignBlock> getHangingWallSign() {
    return hangingWallSign;
  }

  public NSBlockHolder<PressurePlateBlock> getPressurePlate() {
    return pressurePlate;
  }

  public NSBlockHolder<StandingSignBlock> getSign() {
    return sign;
  }

  public NSBlockHolder<TrapDoorBlock> getTrapDoor() {
    return trapDoor;
  }

  public NSBlockHolder<WallSignBlock> getWallSign() {
    return wallSign;
  }

  public NSItemHolder<HangingSignItem> getHangingSignItem() {
    return hangingSignItem;
  }

  public NSItemHolder<SignItem> getSignItem() {
    return signItem;
  }

  public NSItemHolder<BoatItem> getBoatItem() {
    return boatItem;
  }

  public NSItemHolder<BoatItem> getChestBoatItem() {
    return chestBoatItem;
  }

  public NSBlockHolder<? extends Block> getLog() {
    return log;
  }

  public NSBlockHolder<? extends Block> getStrippedLog() {
    return strippedLog;
  }

  public NSBlockHolder<RotatedPillarBlock> getBundle() {
    return bundle;
  }

  public NSBlockHolder<RotatedPillarBlock> getStrippedBundle() {
    return strippedBundle;
  }

  public NSBlockHolder<RotatedPillarBlock> getWood() {
    return wood;
  }

  public NSBlockHolder<RotatedPillarBlock> getStrippedWood() {
    return strippedWood;
  }

  public NSBlockHolder<Block> getMosaic() {
    return mosaic;
  }

  public NSBlockHolder<StairBlock> getMosaicStairs() {
    return mosaicStairs;
  }

  public NSBlockHolder<SlabBlock> getMosaicSlab() {
    return mosaicSlab;
  }

  public NSBlockHolder<? extends LeavesBlock> getLeaves() {
    return leaves;
  }

  public NSBlockHolder<? extends LeavesBlock> getFrostyLeaves() {
    return frostyLeaves;
  }

  public NSBlockHolder<? extends SaplingBlock> getSapling() {
    return sapling;
  }

  public NSBlockHolder<FlowerPotBlock> getPottedSapling() {
    return pottedSapling;
  }

  public NSBlockHolder<DownwardVineBlock> getVines() {
    return vines;
  }

  public NSBlockHolder<DownwardsVinePlantBlock> getVinesPlant() {
    return vinesPlant;
  }

  public NSBlockHolder<? extends LeavesBlock> getRedLeaves() {
    return redLeaves;
  }

  public NSBlockHolder<? extends LeavesBlock> getOrangeLeaves() {
    return orangeLeaves;
  }

  public NSBlockHolder<? extends LeavesBlock> getYellowLeaves() {
    return yellowLeaves;
  }

  public NSBlockHolder<? extends LeavesBlock> getBlueLeaves() {
    return blueLeaves;
  }

  public NSBlockHolder<? extends LeavesBlock> getPurpleLeaves() {
    return purpleLeaves;
  }

  public NSBlockHolder<? extends LeavesBlock> getPinkLeaves() {
    return pinkLeaves;
  }

  public NSBlockHolder<? extends LeavesBlock> getWhiteLeaves() {
    return whiteLeaves;
  }

  public NSBlockHolder<FlowerPotBlock> getPottedRedSapling() {
    return pottedRedSapling;
  }

  public NSBlockHolder<FlowerPotBlock> getPottedOrangeSapling() {
    return pottedOrangeSapling;
  }

  public NSBlockHolder<FlowerPotBlock> getPottedYellowSapling() {
    return pottedYellowSapling;
  }

  public NSBlockHolder<FlowerPotBlock> getPottedBlueSapling() {
    return pottedBlueSapling;
  }

  public NSBlockHolder<FlowerPotBlock> getPottedPurpleSapling() {
    return pottedPurpleSapling;
  }

  public NSBlockHolder<FlowerPotBlock> getPottedPinkSapling() {
    return pottedPinkSapling;
  }

  public NSBlockHolder<FlowerPotBlock> getPottedWhiteSapling() {
    return pottedWhiteSapling;
  }

  public NSBlockHolder<? extends SaplingBlock> getRedSapling() {
    return redSapling;
  }

  public NSBlockHolder<? extends SaplingBlock> getOrangeSapling() {
    return orangeSapling;
  }

  public NSBlockHolder<? extends SaplingBlock> getYellowSapling() {
    return yellowSapling;
  }

  public NSBlockHolder<? extends SaplingBlock> getBlueSapling() {
    return blueSapling;
  }

  public NSBlockHolder<? extends SaplingBlock> getPurpleSapling() {
    return purpleSapling;
  }

  public NSBlockHolder<? extends SaplingBlock> getPinkSapling() {
    return pinkSapling;
  }

  public NSBlockHolder<? extends SaplingBlock> getWhiteSapling() {
    return whiteSapling;
  }

  public NSBlockHolder<DownwardVineBlock> getBlueVines() {
    return blueVines;
  }

  public NSBlockHolder<DownwardVineBlock> getPurpleVines() {
    return purpleVines;
  }

  public NSBlockHolder<DownwardVineBlock> getPinkVines() {
    return pinkVines;
  }

  public NSBlockHolder<DownwardVineBlock> getWhiteVines() {
    return whiteVines;
  }

  public NSBlockHolder<DownwardsVinePlantBlock> getBlueVinesPlant() {
    return blueVinesPlant;
  }

  public NSBlockHolder<DownwardsVinePlantBlock> getPurpleVinesPlant() {
    return purpleVinesPlant;
  }

  public NSBlockHolder<DownwardsVinePlantBlock> getPinkVinesPlant() {
    return pinkVinesPlant;
  }

  public NSBlockHolder<DownwardsVinePlantBlock> getWhiteVinesPlant() {
    return whiteVinesPlant;
  }

  public NSBlockHolder<? extends LeavesBlock> getPartBlueLeaves() {
    return partBlueLeaves;
  }

  public NSBlockHolder<? extends LeavesBlock> getPartPurpleLeaves() {
    return partPurpleLeaves;
  }

  public NSBlockHolder<? extends LeavesBlock> getPartPinkLeaves() {
    return partPinkLeaves;
  }

  public NSBlockHolder<? extends LeavesBlock> getPartWhiteLeaves() {
    return partWhiteLeaves;
  }

  private String getWoodName() {
    String name;
    if (woodPreset == WoodPreset.NETHER) {
      name = getName() + "_hyphae";
    } else {
      name = getName() + "_wood";
    }
    return name;
  }

  private String getLogName() {
    String name;
    if (woodPreset == WoodPreset.BAMBOO) {
      name = getName() + "_block";
    } else if (woodPreset == WoodPreset.NETHER) {
      name = getName() + "_stem";
    } else {
      name = getName() + "_log";
    }
    return name;
  }

  private Block getBase() {
    Block base;
    if (woodPreset == WoodPreset.BAMBOO) {
      base = Blocks.BAMBOO_PLANKS;
    } else if (woodPreset == WoodPreset.FANCY) {
      base = Blocks.CHERRY_PLANKS;
    } else if (woodPreset == WoodPreset.NETHER) {
      base = Blocks.CRIMSON_PLANKS;
    } else {
      base = Blocks.OAK_PLANKS;
    }
    return base;
  }

  private Block getSignBase() {
    Block base;
    if (woodPreset == WoodPreset.BAMBOO) {
      base = Blocks.BAMBOO_SIGN;
    } else if (woodPreset == WoodPreset.FANCY) {
      base = Blocks.CHERRY_SIGN;
    } else if (woodPreset == WoodPreset.NETHER) {
      base = Blocks.CRIMSON_SIGN;
    } else {
      base = Blocks.OAK_SIGN;
    }
    return base;
  }

  private Block getHangingSignBase() {
    Block base;
    if (woodPreset == WoodPreset.BAMBOO) {
      base = Blocks.BAMBOO_HANGING_SIGN;
    } else if (woodPreset == WoodPreset.FANCY) {
      base = Blocks.CHERRY_HANGING_SIGN;
    } else if (woodPreset == WoodPreset.NETHER) {
      base = Blocks.CRIMSON_HANGING_SIGN;
    } else {
      base = Blocks.OAK_HANGING_SIGN;
    }
    return base;
  }

  public List<NSBlockHolder<? extends Block>> getRegisteredBlocksList() {
    return ImmutableList.copyOf(registeredBlocksList);
  }

  public List<NSItemHolder<? extends Item>> getRegisteredItemsList() {
    return ImmutableList.copyOf(registeredItemsList);
  }

  private <T extends Block> NSBlockHolder<T> createBlockWithItem(String blockID, Function<Properties, T> block, Supplier<Properties> properties) {
    NSBlockHolder<T> listBlock = registerBlock(blockID, block, properties);
    registeredBlocksList.add(listBlock);
    return listBlock;
  }

  private <T extends Block> NSBlockHolder<T> createBlockWithoutItem(String blockID, Function<Properties, T> block, Supplier<Properties> properties) {
    NSBlockHolder<T> listBlock = registerBlockWithoutItem(blockID, block, properties);
    registeredBlocksList.add(listBlock);
    return listBlock;
  }

  public <T extends Item> NSItemHolder<T> createItem(String blockID, Function<Item.Properties, T> item, Supplier<Item.Properties> properties) {
    NSItemHolder<T> listItem = registerItem(blockID, item, properties);
    registeredItemsList.add(listItem);
    return listItem;
  }

  private static <T extends Block> NSBlockHolder<T> flammable(NSBlockHolder<T> block, int igniteOdds, int burnOdds) {
    NSFlammables.add(block, igniteOdds, burnOdds);
    return block;
  }

  private NSBlockHolder<RotatedPillarBlock> createLog() {
    return flammable(createBlockWithItem(getLogName(), RotatedPillarBlock::new, logProperties(topColor, sideColor)), 5, 5);
  }

  private NSBlockHolder<RotatedPillarBlock> createStrippedLog() {
    return flammable(createBlockWithItem("stripped_" + getLogName(), RotatedPillarBlock::new, logProperties(topColor, sideColor)), 5, 5);
  }

  private NSBlockHolder<RotatedPillarBlock> createBundle() {
    return flammable(createBlockWithItem(getName() + "_bundle", RotatedPillarBlock::new, logProperties(topColor, sideColor)), 5, 5);
  }

  private NSBlockHolder<RotatedPillarBlock> createStrippedBundle() {
    return flammable(createBlockWithItem("stripped_" + getName() + "_bundle", RotatedPillarBlock::new, logProperties(topColor, sideColor)), 5, 5);
  }

  private static Supplier<Properties> logProperties(MapColor topMapColor, MapColor sideMapColor) {
    return () -> Properties.of().mapColor(state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava();
  }

  private NSBlockHolder<BranchingTrunkBlock> createJoshuaLog() {
    return flammable(createBlockWithItem(getLogName(), BranchingTrunkBlock::new,
        () -> Properties.of().ignitedByLava().mapColor(MapColor.COLOR_GRAY).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD)), 5, 5);
  }

  private NSBlockHolder<BranchingTrunkBlock> createStrippedJoshuaLog() {
    return flammable(createBlockWithItem("stripped_" + getLogName(), BranchingTrunkBlock::new,
        () -> Properties.of().ignitedByLava().mapColor(MapColor.COLOR_GRAY).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD)), 5, 5);
  }

  private NSBlockHolder<RotatedPillarBlock> createWood() {
    return flammable(createBlockWithItem(getWoodName(), RotatedPillarBlock::new, logProperties(sideColor, sideColor)), 5, 5);
  }

  private NSBlockHolder<RotatedPillarBlock> createStrippedWood() {
    return flammable(createBlockWithItem("stripped_" + getWoodName(), RotatedPillarBlock::new, logProperties(topColor, topColor)), 5, 5);
  }

  private static Supplier<Properties> leavesProperties() {
    return () -> Properties.of().mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()
        .isValidSpawn(NSRegistryHelper::ocelotOrParrot).isSuffocating(NSRegistryHelper::never).isViewBlocking(NSRegistryHelper::never).ignitedByLava().pushReaction(PushReaction.DESTROY)
        .isRedstoneConductor(NSRegistryHelper::never);
  }

  private static Supplier<Properties> vinesLeavesProperties() {
    return () -> Properties.of().strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()
        .isValidSpawn(NSRegistryHelper::ocelotOrParrot).isSuffocating(NSRegistryHelper::never).isViewBlocking(NSRegistryHelper::never).ignitedByLava().pushReaction(PushReaction.DESTROY)
        .isRedstoneConductor(NSRegistryHelper::never);
  }

  private NSBlockHolder<TintedParticleLeavesBlock> createLeaves() {
    return createLeaves("");
  }

  private NSBlockHolder<TintedParticleLeavesBlock> createLeaves(String prefix) {
    return flammable(createBlockWithItem(prefix + getName() + "_leaves", properties -> new TintedParticleLeavesBlock(0.01F, properties), leavesProperties()), 30, 60);
  }

  private NSBlockHolder<ProjectileLeavesBlock> createFrostableLeaves() {
    return flammable(createBlockWithItem(getName() + "_leaves", properties -> new ProjectileLeavesBlock(properties, frostyLeaves), leavesProperties()), 30, 60);
  }

  private NSBlockHolder<ParticleLeavesBlock> createParticleLeaves(String prefix, Supplier<? extends ParticleOptions> particle, int chance) {
    return flammable(createBlockWithItem(prefix + getName() + "_leaves", properties -> new ParticleLeavesBlock(properties, particle, chance), leavesProperties()), 30, 60);
  }

  private NSBlockHolder<VinesLeavesBlock> createVinesLeavesBlock(NSBlockHolder<DownwardsVinePlantBlock> vinesPlantBlock, NSBlockHolder<DownwardVineBlock> vinesTipBlock) {
    return createVinesLeavesBlock("", vinesPlantBlock, vinesTipBlock);
  }

  private NSBlockHolder<VinesLeavesBlock> createVinesLeavesBlock(String prefix, NSBlockHolder<DownwardsVinePlantBlock> vinesPlantBlock, NSBlockHolder<DownwardVineBlock> vinesTipBlock) {
    return flammable(createBlockWithItem(prefix + getName() + "_leaves",
            properties -> new VinesLeavesBlock(properties, vinesPlantBlock, vinesTipBlock), vinesLeavesProperties()), 30, 60);
  }

  private NSBlockHolder<DownwardVineBlock> createVines(Supplier<NSBlockHolder<DownwardsVinePlantBlock>> vinesPlantBlock) {
    return createVines("", vinesPlantBlock);
  }

  private NSBlockHolder<DownwardVineBlock> createVines(String prefix, Supplier<NSBlockHolder<DownwardsVinePlantBlock>> vinesPlantBlock) {
    return createBlockWithItem(prefix + getName() + "_vines",
            properties -> new DownwardVineBlock(properties, vinesPlantBlock), () -> Properties
            .of()
            .pushReaction(PushReaction.DESTROY)
            .randomTicks()
            .noCollision()
            .noOcclusion()
            .instabreak()
            .sound(SoundType.WEEPING_VINES));
  }

  private NSBlockHolder<DownwardsVinePlantBlock> createVinesPlant(NSBlockHolder<DownwardVineBlock> vines) {
    return createVinesPlant("", vines);
  }

  private NSBlockHolder<DownwardsVinePlantBlock> createVinesPlant(String prefix, NSBlockHolder<DownwardVineBlock> vines) {
    return registerBlockWithoutItem(prefix + getName() + "_vines_plant",
        properties -> new DownwardsVinePlantBlock(properties, vines), () -> Properties
        .of()
        .pushReaction(PushReaction.DESTROY)
        .noCollision()
        .noOcclusion()
        .instabreak()
        .sound(SoundType.WEEPING_VINES)
        .overrideLootTable(vines.get().getLootTable()));
  }

  private Supplier<Properties> plankProperties() {
    return () -> Properties.ofFullCopy(getBase()).sound(getBlockSetType().get().soundType()).mapColor(getTopColor());
  }

  private NSBlockHolder<Block> createPlanks() {
    return flammable(createBlockWithItem(getName() + "_planks", Block::new, plankProperties()), 5, 20);
  }

  private NSBlockHolder<StairBlock> createStairs() {
    return flammable(createBlockWithItem(getName() + "_stairs",
            properties -> new StairBlock(getBase().defaultBlockState(), properties), plankProperties()), 5, 20);
  }

  private NSBlockHolder<SlabBlock> createSlab() {
    return flammable(createBlockWithItem(getName() + "_slab", SlabBlock::new, plankProperties()), 5, 20);
  }

  private NSBlockHolder<Block> createMosaic() {
    NSBlockHolder<Block> block = createBlockWithoutItem(getName() + "_mosaic", Block::new, plankProperties());
    NSFuels.add(NSRegistryHelper.registerItem(getName() + "_mosaic", properties -> new BlockItem(block.get(), properties), () -> new Item.Properties().useBlockDescriptionPrefix()), 300);
    return flammable(block, 5, 20);
  }

  private NSBlockHolder<StairBlock> createMosaicStairs() {
    NSBlockHolder<StairBlock> block = createBlockWithoutItem(getName() + "_mosaic_stairs",
            properties -> new StairBlock(getBase().defaultBlockState(), properties), plankProperties());
    NSFuels.add(NSRegistryHelper.registerItem(getName() + "_mosaic_stairs", properties -> new BlockItem(block.get(), properties), () -> new Item.Properties().useBlockDescriptionPrefix()), 300);
    return flammable(block, 5, 20);
  }

  private NSBlockHolder<SlabBlock> createMosaicSlab() {
    NSBlockHolder<SlabBlock> block = createBlockWithoutItem(getName() + "_mosaic_slab", SlabBlock::new, plankProperties());
    NSFuels.add(NSRegistryHelper.registerItem(getName() + "_mosaic_slab", properties -> new BlockItem(block.get(), properties), () -> new Item.Properties().useBlockDescriptionPrefix()), 150);
    return flammable(block, 5, 20);
  }

  private NSBlockHolder<FenceBlock> createFence() {
    return flammable(createBlockWithItem(getName() + "_fence", FenceBlock::new, plankProperties()), 5, 20);
  }

  private NSBlockHolder<FenceGateBlock> createFenceGate() {
    return flammable(createBlockWithItem(getName() + "_fence_gate", properties -> new FenceGateBlock(getWoodType().get(), properties),
            () -> Properties.of().mapColor(getBase().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).ignitedByLava()), 5, 20);
  }

  private NSBlockHolder<PressurePlateBlock> createPressurePlate() {
    return createBlockWithItem(getName() + "_pressure_plate", properties -> new PressurePlateBlock(getBlockSetType().get(), properties),
            () -> Properties.of().mapColor(this.getBase().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision().strength(0.5F).ignitedByLava()
                    .pushReaction(PushReaction.DESTROY));
  }

  private NSBlockHolder<ButtonBlock> createButton() {
    return createBlockWithItem(getName() + "_button",
            properties -> new ButtonBlock(getBlockSetType().get(), 30, properties), () -> Properties.of().noCollision().strength(0.5F).pushReaction(PushReaction.DESTROY));
  }

  private NSBlockHolder<DoorBlock> createDoor() {
    return createBlockWithItem(getName() + "_door", properties -> new DoorBlock(getBlockSetType().get(), properties),
            () -> Properties.ofFullCopy(getBase()).sound(getBlockSetType().get().soundType()).noOcclusion().mapColor(getTopColor()));
  }

  private NSBlockHolder<TrapDoorBlock> createTrapDoor() {
    return createBlockWithItem(getName() + "_trapdoor", properties -> new TrapDoorBlock(getBlockSetType().get(), properties),
            () -> Properties.ofFullCopy(getBase()).sound(getBlockSetType().get().soundType()).noOcclusion().mapColor(getTopColor()));
  }

  private NSBlockHolder<StandingSignBlock> createSign() {
    return registerBlockWithoutItem(getName() + "_sign", properties -> new StandingSignBlock(getWoodType().get(), properties),
            () -> Properties.ofFullCopy(getSignBase()).mapColor(this.getTopColor()));
  }

  private NSBlockHolder<WallSignBlock> createWallSign() {
    return registerBlockWithoutItem(getName() + "_wall_sign", properties -> new WallSignBlock(getWoodType().get(), properties),
            () -> Properties.ofFullCopy(getSignBase()).mapColor(this.getTopColor()).overrideLootTable(sign.get().getLootTable()));
  }

  private NSBlockHolder<CeilingHangingSignBlock> createHangingSign() {
    return registerBlockWithoutItem(getName() + "_hanging_sign", properties -> new CeilingHangingSignBlock(getWoodType().get(), properties),
            () -> Properties.ofFullCopy(getHangingSignBase()).mapColor(this.getTopColor()));
  }

  private NSBlockHolder<WallHangingSignBlock> createWallHangingSign() {
    return registerBlockWithoutItem(getName() + "_wall_hanging_sign",
            properties -> new WallHangingSignBlock(getWoodType().get(), properties),
            () -> Properties.ofFullCopy(getHangingSignBase()).mapColor(this.getTopColor()).overrideLootTable(hangingSign.get().getLootTable()));
  }

  public NSBlockHolder<SaplingBlock> createSapling(TreeGrower saplingGenerator) {
    return createSapling("", saplingGenerator);
  }

  public NSBlockHolder<SandySaplingBlock> createSandySapling(TreeGrower saplingGenerator) {
    return createSandySapling("", saplingGenerator);
  }

  public NSBlockHolder<FlowerPotBlock> createPottedSapling(NSBlockHolder<? extends SaplingBlock> sapling) {
    return createPottedSapling("", sapling);
  }

  public NSBlockHolder<SaplingBlock> createSapling(String prefix, TreeGrower saplingGenerator) {
    return createBlockWithItem(prefix + getName() + "_sapling", properties -> new SaplingBlock(saplingGenerator, properties),
            () -> Properties.ofFullCopy(Blocks.SPRUCE_SAPLING));
  }

  public NSBlockHolder<SandySaplingBlock> createSandySapling(String prefix, TreeGrower saplingGenerator) {
    return createBlockWithItem(prefix + getName() + "_sapling", properties -> new SandySaplingBlock(saplingGenerator, properties),
            () -> Properties.ofFullCopy(Blocks.SPRUCE_SAPLING));
  }

  public NSBlockHolder<FlowerPotBlock> createPottedSapling(String prefix, NSBlockHolder<? extends SaplingBlock> sapling) {
    NSBlockHolder<FlowerPotBlock> potted = registerTransparentBlockWithoutItem("potted_" + prefix + getName() + "_sapling",
            properties -> new FlowerPotBlock(sapling.get(), properties),
            () -> Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY));
    NSCommonHooks.FLOWER_POTS.add(new NSCommonHooks.PottedPlant(sapling, potted));
    return potted;
  }

  private NSItemHolder<SignItem> createSignItem() {
    return createItem(getName() + "_sign", properties -> new SignItem(sign.get(), wallSign.get(), properties),
            () -> new Item.Properties().stacksTo(16).useBlockDescriptionPrefix());
  }

  private NSItemHolder<HangingSignItem> createHangingSignItem() {
    return createItem(getName() + "_hanging_sign", properties -> new HangingSignItem(hangingSign.get(), hangingWallSign.get(), properties),
            () -> new Item.Properties().stacksTo(16).useBlockDescriptionPrefix());
  }

  private Supplier<BlockSetType> createBlockSetType() {
    if (this.woodPreset == WoodPreset.BAMBOO) {
      return Suppliers.memoize(() -> BlockSetType.register(new BlockSetType(MOD_ID + ":" + getName(), true,true, true, BlockSetType.PressurePlateSensitivity.EVERYTHING, SoundType.BAMBOO_WOOD, SoundEvents.BAMBOO_WOOD_DOOR_CLOSE, SoundEvents.BAMBOO_WOOD_DOOR_OPEN, SoundEvents.BAMBOO_WOOD_TRAPDOOR_CLOSE, SoundEvents.BAMBOO_WOOD_TRAPDOOR_OPEN, SoundEvents.BAMBOO_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.BAMBOO_WOOD_PRESSURE_PLATE_CLICK_ON, SoundEvents.BAMBOO_WOOD_BUTTON_CLICK_OFF, SoundEvents.BAMBOO_WOOD_BUTTON_CLICK_ON)));
    } else if (woodPreset == WoodPreset.FANCY) {
      return Suppliers.memoize(() -> BlockSetType.register(new BlockSetType(MOD_ID + ":" + getName(), true,true, true, BlockSetType.PressurePlateSensitivity.EVERYTHING, SoundType.CHERRY_WOOD, SoundEvents.CHERRY_WOOD_DOOR_CLOSE, SoundEvents.CHERRY_WOOD_DOOR_OPEN, SoundEvents.CHERRY_WOOD_TRAPDOOR_CLOSE, SoundEvents.CHERRY_WOOD_TRAPDOOR_OPEN, SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_ON, SoundEvents.CHERRY_WOOD_BUTTON_CLICK_OFF, SoundEvents.CHERRY_WOOD_BUTTON_CLICK_ON)));
    } else if (this.woodPreset == WoodPreset.NETHER) {
      return Suppliers.memoize(() -> BlockSetType.register(new BlockSetType(MOD_ID + ":" + getName(), true,true, true, BlockSetType.PressurePlateSensitivity.EVERYTHING, SoundType.NETHER_WOOD, SoundEvents.NETHER_WOOD_DOOR_CLOSE, SoundEvents.NETHER_WOOD_DOOR_OPEN, SoundEvents.NETHER_WOOD_TRAPDOOR_CLOSE, SoundEvents.NETHER_WOOD_TRAPDOOR_OPEN, SoundEvents.NETHER_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.NETHER_WOOD_PRESSURE_PLATE_CLICK_ON, SoundEvents.NETHER_WOOD_BUTTON_CLICK_OFF, SoundEvents.NETHER_WOOD_BUTTON_CLICK_ON)));
    } else {
      return Suppliers.memoize(() -> BlockSetType.register(new BlockSetType(MOD_ID + ":" + getName())));
    }
  }

  public boolean isSandy() {
    return woodPreset == WoodPreset.JOSHUA || woodPreset == WoodPreset.SANDY;
  }

  public boolean hasDefaultLeaves() {
    return woodPreset == WoodPreset.DEFAULT || woodPreset == WoodPreset.WISTERIA || woodPreset == WoodPreset.FANCY || woodPreset == WoodPreset.JOSHUA
        || woodPreset == WoodPreset.NO_SAPLING || woodPreset == WoodPreset.SANDY || woodPreset == WoodPreset.ASPEN;
  }

  public boolean hasDefaultSapling() {
    return woodPreset != WoodPreset.NO_SAPLING && woodPreset != WoodPreset.WISTERIA;
  }

  public boolean hasBark() {
    return woodPreset != WoodPreset.JOSHUA && woodPreset != WoodPreset.BAMBOO;
  }

  public boolean hasMosaic() {
    return this.hasMosaic;
  }

  public enum WoodPreset {
    DEFAULT, MAPLE, ASPEN, FROSTABLE, JOSHUA, SANDY, NO_SAPLING, WISTERIA, WILLOW, FANCY, NETHER, BAMBOO
  }
}
