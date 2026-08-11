package net.hibiscus.naturespirit.registration.sets;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.blocks.*;
import net.hibiscus.naturespirit.datagen.NSConfiguredFeatures;
import net.hibiscus.naturespirit.entity.NSBoatEntity;
import net.hibiscus.naturespirit.items.NSBoatItem;
import net.hibiscus.naturespirit.registration.NSParticleTypes;
import net.hibiscus.naturespirit.registration.NSRegistryHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;
import static net.hibiscus.naturespirit.registration.NSRegistryHelper.*;

public class WoodSet {

  private final List<DeferredBlock<? extends Block>> registeredBlocksList = new ArrayList<>();
  private final List<DeferredItem<? extends Item>> registeredItemsList = new ArrayList<>();
  private final String name;
  private final MapColor sideColor;
  private final MapColor topColor;
  private final WoodPreset woodPreset;
  private Supplier<BlockSetType> blockSetType;
  private Supplier<WoodType> woodType;
  private DeferredBlock<? extends Block> log;
  private DeferredBlock<? extends Block> strippedLog;
  private DeferredBlock<RotatedPillarBlock> bundle;
  private DeferredBlock<RotatedPillarBlock> strippedBundle;
  private DeferredBlock<RotatedPillarBlock> wood;
  private DeferredBlock<RotatedPillarBlock> strippedWood;
  private DeferredBlock<? extends LeavesBlock> leaves;
  private DeferredBlock<? extends LeavesBlock> frostyLeaves;
  private DeferredBlock<? extends SaplingBlock> sapling;
  private DeferredBlock<FlowerPotBlock> pottedSapling;
  private DeferredBlock<? extends LeavesBlock> redLeaves;
  private DeferredBlock<? extends SaplingBlock> redSapling;
  private DeferredBlock<FlowerPotBlock> pottedRedSapling;
  private DeferredBlock<? extends LeavesBlock> orangeLeaves;
  private DeferredBlock<? extends SaplingBlock> orangeSapling;
  private DeferredBlock<FlowerPotBlock> pottedOrangeSapling;
  private DeferredBlock<? extends LeavesBlock> yellowLeaves;
  private DeferredBlock<? extends SaplingBlock> yellowSapling;
  private DeferredBlock<FlowerPotBlock> pottedYellowSapling;
  private DeferredBlock<? extends LeavesBlock> blueLeaves;
  private DeferredBlock<? extends LeavesBlock> partBlueLeaves;
  private DeferredBlock<? extends SaplingBlock> blueSapling;
  private DeferredBlock<FlowerPotBlock> pottedBlueSapling;
  private DeferredBlock<? extends LeavesBlock> purpleLeaves;
  private DeferredBlock<? extends LeavesBlock> partPurpleLeaves;
  private DeferredBlock<? extends SaplingBlock> purpleSapling;
  private DeferredBlock<FlowerPotBlock> pottedPurpleSapling;
  private DeferredBlock<? extends LeavesBlock> pinkLeaves;
  private DeferredBlock<? extends LeavesBlock> partPinkLeaves;
  private DeferredBlock<? extends SaplingBlock> pinkSapling;
  private DeferredBlock<FlowerPotBlock> pottedPinkSapling;
  private DeferredBlock<? extends LeavesBlock> whiteLeaves;
  private DeferredBlock<? extends LeavesBlock> partWhiteLeaves;
  private DeferredBlock<? extends SaplingBlock> whiteSapling;
  private DeferredBlock<FlowerPotBlock> pottedWhiteSapling;
  private DeferredBlock<DownwardVineBlock> vines;
  private DeferredBlock<DownwardsVinePlantBlock> vinesPlant;
  private DeferredBlock<DownwardVineBlock> blueVines;
  private DeferredBlock<DownwardVineBlock> purpleVines;
  private DeferredBlock<DownwardVineBlock> pinkVines;
  private DeferredBlock<DownwardVineBlock> whiteVines;
  private DeferredBlock<DownwardsVinePlantBlock> blueVinesPlant;
  private DeferredBlock<DownwardsVinePlantBlock> purpleVinesPlant;
  private DeferredBlock<DownwardsVinePlantBlock> pinkVinesPlant;
  private DeferredBlock<DownwardsVinePlantBlock> whiteVinesPlant;
  private DeferredBlock<Block> planks;
  private DeferredBlock<StairBlock> stairs;
  private DeferredBlock<SlabBlock> slab;
  private DeferredBlock<Block> mosaic;
  private DeferredBlock<StairBlock> mosaicStairs;
  private DeferredBlock<SlabBlock> mosaicSlab;
  private DeferredBlock<FenceBlock> fence;
  private DeferredBlock<FenceGateBlock> fenceGate;
  private DeferredBlock<PressurePlateBlock> pressurePlate;
  private DeferredBlock<ButtonBlock> button;
  private DeferredBlock<DoorBlock> door;
  private DeferredBlock<TrapDoorBlock> trapDoor;
  private DeferredBlock<StandingSignBlock> sign;
  private DeferredBlock<WallSignBlock> wallSign;
  private DeferredBlock<CeilingHangingSignBlock> hangingSign;
  private DeferredBlock<WallHangingSignBlock> hangingWallSign;
  private DeferredItem<SignItem> signItem;
  private DeferredItem<HangingSignItem> hangingSignItem;
  private DeferredItem<NSBoatItem> boatItem;
  private DeferredItem<NSBoatItem> chestBoatItem;
  private final Supplier<NSBoatEntity.Type> boatType;
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
              new TreeGrower(NatureSpirit.MOD_ID + "_" + this.getName(), Optional.empty(), Optional.of(NSConfiguredFeatures.WHITE_WISTERIA_TREE), Optional.empty()));
      blueSapling = createSapling("blue_",
              new TreeGrower(NatureSpirit.MOD_ID + "_" + this.getName(), Optional.empty(), Optional.of(NSConfiguredFeatures.BLUE_WISTERIA_TREE), Optional.empty()));
      pinkSapling = createSapling("pink_",
              new TreeGrower(NatureSpirit.MOD_ID + "_" + this.getName(), Optional.empty(), Optional.of(NSConfiguredFeatures.PINK_WISTERIA_TREE), Optional.empty()));
      purpleSapling = createSapling("purple_",
              new TreeGrower(NatureSpirit.MOD_ID + "_" + this.getName(), Optional.empty(), Optional.of(NSConfiguredFeatures.PURPLE_WISTERIA_TREE), Optional.empty()));
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
              new TreeGrower(NatureSpirit.MOD_ID + "_" + this.getName(), Optional.empty(), Optional.of(NSConfiguredFeatures.RED_MAPLE_TREE), Optional.empty()));
      orangeSapling = createSapling("orange_",
              new TreeGrower(NatureSpirit.MOD_ID + "_" + this.getName(), Optional.empty(), Optional.of(NSConfiguredFeatures.ORANGE_MAPLE_TREE), Optional.empty()));
      yellowSapling = createSapling("yellow_",
              new TreeGrower(NatureSpirit.MOD_ID + "_" + this.getName(), Optional.empty(), Optional.of(NSConfiguredFeatures.YELLOW_MAPLE_TREE), Optional.empty()));
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
    boatItem = createItem(getName() + "_boat", () -> new NSBoatItem(false, boatType.get(), new Item.Properties().stacksTo(1)));
    chestBoatItem = createItem(getName() + "_chest_boat", () -> new NSBoatItem(true, boatType.get(), new Item.Properties().stacksTo(1)));
  }

  public WoodSet(
          String name,
          MapColor sideColor,
          MapColor topColor,
          Supplier<NSBoatEntity.Type> boatType,
          WoodPreset woodPreset,
          boolean hasMosaic,
          TreeGrower saplingGenerator
          ) {
    this.woodPreset = woodPreset;
    this.name = name;
    this.sideColor = sideColor;
    this.topColor = topColor;
    this.boatType = boatType;
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

  public DeferredBlock<ButtonBlock> getButton() {
    return button;
  }

  public DeferredBlock<FenceBlock> getFence() {
    return fence;
  }

  public DeferredBlock<Block> getPlanks() {
    return planks;
  }

  public DeferredBlock<SlabBlock> getSlab() {
    return slab;
  }

  public DeferredBlock<FenceGateBlock> getFenceGate() {
    return fenceGate;
  }

  public DeferredBlock<StairBlock> getStairs() {
    return stairs;
  }

  public DeferredBlock<DoorBlock> getDoor() {
    return door;
  }

  public DeferredBlock<CeilingHangingSignBlock> getHangingSign() {
    return hangingSign;
  }

  public DeferredBlock<WallHangingSignBlock> getHangingWallSign() {
    return hangingWallSign;
  }

  public DeferredBlock<PressurePlateBlock> getPressurePlate() {
    return pressurePlate;
  }

  public DeferredBlock<StandingSignBlock> getSign() {
    return sign;
  }

  public DeferredBlock<TrapDoorBlock> getTrapDoor() {
    return trapDoor;
  }

  public DeferredBlock<WallSignBlock> getWallSign() {
    return wallSign;
  }

  public DeferredItem<HangingSignItem> getHangingSignItem() {
    return hangingSignItem;
  }

  public DeferredItem<SignItem> getSignItem() {
    return signItem;
  }

  public DeferredItem<NSBoatItem> getBoatItem() {
    return boatItem;
  }

  public DeferredItem<NSBoatItem> getChestBoatItem() {
    return chestBoatItem;
  }

  public DeferredBlock<? extends Block> getLog() {
    return log;
  }

  public DeferredBlock<? extends Block> getStrippedLog() {
    return strippedLog;
  }

  public DeferredBlock<RotatedPillarBlock> getBundle() {
    return bundle;
  }

  public DeferredBlock<RotatedPillarBlock> getStrippedBundle() {
    return strippedBundle;
  }

  public DeferredBlock<RotatedPillarBlock> getWood() {
    return wood;
  }

  public DeferredBlock<RotatedPillarBlock> getStrippedWood() {
    return strippedWood;
  }

  public DeferredBlock<Block> getMosaic() {
    return mosaic;
  }

  public DeferredBlock<StairBlock> getMosaicStairs() {
    return mosaicStairs;
  }

  public DeferredBlock<SlabBlock> getMosaicSlab() {
    return mosaicSlab;
  }

  public DeferredBlock<? extends LeavesBlock> getLeaves() {
    return leaves;
  }

  public DeferredBlock<? extends LeavesBlock> getFrostyLeaves() {
    return frostyLeaves;
  }

  public DeferredBlock<? extends SaplingBlock> getSapling() {
    return sapling;
  }

  public DeferredBlock<FlowerPotBlock> getPottedSapling() {
    return pottedSapling;
  }

  public DeferredBlock<DownwardVineBlock> getVines() {
    return vines;
  }

  public DeferredBlock<DownwardsVinePlantBlock> getVinesPlant() {
    return vinesPlant;
  }

  public DeferredBlock<? extends LeavesBlock> getRedLeaves() {
    return redLeaves;
  }

  public DeferredBlock<? extends LeavesBlock> getOrangeLeaves() {
    return orangeLeaves;
  }

  public DeferredBlock<? extends LeavesBlock> getYellowLeaves() {
    return yellowLeaves;
  }

  public DeferredBlock<? extends LeavesBlock> getBlueLeaves() {
    return blueLeaves;
  }

  public DeferredBlock<? extends LeavesBlock> getPurpleLeaves() {
    return purpleLeaves;
  }

  public DeferredBlock<? extends LeavesBlock> getPinkLeaves() {
    return pinkLeaves;
  }

  public DeferredBlock<? extends LeavesBlock> getWhiteLeaves() {
    return whiteLeaves;
  }

  public DeferredBlock<FlowerPotBlock> getPottedRedSapling() {
    return pottedRedSapling;
  }

  public DeferredBlock<FlowerPotBlock> getPottedOrangeSapling() {
    return pottedOrangeSapling;
  }

  public DeferredBlock<FlowerPotBlock> getPottedYellowSapling() {
    return pottedYellowSapling;
  }

  public DeferredBlock<FlowerPotBlock> getPottedBlueSapling() {
    return pottedBlueSapling;
  }

  public DeferredBlock<FlowerPotBlock> getPottedPurpleSapling() {
    return pottedPurpleSapling;
  }

  public DeferredBlock<FlowerPotBlock> getPottedPinkSapling() {
    return pottedPinkSapling;
  }

  public DeferredBlock<FlowerPotBlock> getPottedWhiteSapling() {
    return pottedWhiteSapling;
  }

  public DeferredBlock<? extends SaplingBlock> getRedSapling() {
    return redSapling;
  }

  public DeferredBlock<? extends SaplingBlock> getOrangeSapling() {
    return orangeSapling;
  }

  public DeferredBlock<? extends SaplingBlock> getYellowSapling() {
    return yellowSapling;
  }

  public DeferredBlock<? extends SaplingBlock> getBlueSapling() {
    return blueSapling;
  }

  public DeferredBlock<? extends SaplingBlock> getPurpleSapling() {
    return purpleSapling;
  }

  public DeferredBlock<? extends SaplingBlock> getPinkSapling() {
    return pinkSapling;
  }

  public DeferredBlock<? extends SaplingBlock> getWhiteSapling() {
    return whiteSapling;
  }

  public DeferredBlock<DownwardVineBlock> getBlueVines() {
    return blueVines;
  }

  public DeferredBlock<DownwardVineBlock> getPurpleVines() {
    return purpleVines;
  }

  public DeferredBlock<DownwardVineBlock> getPinkVines() {
    return pinkVines;
  }

  public DeferredBlock<DownwardVineBlock> getWhiteVines() {
    return whiteVines;
  }

  public DeferredBlock<DownwardsVinePlantBlock> getBlueVinesPlant() {
    return blueVinesPlant;
  }

  public DeferredBlock<DownwardsVinePlantBlock> getPurpleVinesPlant() {
    return purpleVinesPlant;
  }

  public DeferredBlock<DownwardsVinePlantBlock> getPinkVinesPlant() {
    return pinkVinesPlant;
  }

  public DeferredBlock<DownwardsVinePlantBlock> getWhiteVinesPlant() {
    return whiteVinesPlant;
  }

  public DeferredBlock<? extends LeavesBlock> getPartBlueLeaves() {
    return partBlueLeaves;
  }

  public DeferredBlock<? extends LeavesBlock> getPartPurpleLeaves() {
    return partPurpleLeaves;
  }

  public DeferredBlock<? extends LeavesBlock> getPartPinkLeaves() {
    return partPinkLeaves;
  }

  public DeferredBlock<? extends LeavesBlock> getPartWhiteLeaves() {
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


  public Supplier<NSBoatEntity.Type> getBoatType() {
    return boatType;
  }

  public List<DeferredBlock<? extends Block>> getRegisteredBlocksList() {
    return ImmutableList.copyOf(registeredBlocksList);
  }

  public List<DeferredItem<? extends Item>> getRegisteredItemsList() {
    return ImmutableList.copyOf(registeredItemsList);
  }

  private <T extends Block> DeferredBlock<T> createBlockWithItem(String blockID, Supplier<T> block) {
    DeferredBlock<T> listBlock = registerBlock(blockID, block);
    registeredBlocksList.add(listBlock);
    return listBlock;
  }

  private <T extends Block> DeferredBlock<T> createBlockWithoutItem(String blockID, Supplier<T> block) {
    DeferredBlock<T> listBlock = registerBlockWithoutItem(blockID, block);
    registeredBlocksList.add(listBlock);
    return listBlock;
  }

  public <T extends Item> DeferredItem<T> createItem(String blockID, Supplier<T> item) {
    DeferredItem<T> listItem = registerItem(blockID, item);
    registeredItemsList.add(listItem);
    return listItem;
  }

  private DeferredBlock<RotatedPillarBlock> createLog() {
    return createBlockWithItem(getLogName(), () -> log(topColor, sideColor));
  }

  private DeferredBlock<RotatedPillarBlock> createStrippedLog() {
    return createBlockWithItem("stripped_" + getLogName(), () -> log(topColor, sideColor));
  }

  private DeferredBlock<RotatedPillarBlock> createBundle() {
    return createBlockWithItem(getName() + "_bundle", () -> log(topColor, sideColor));
  }

  private DeferredBlock<RotatedPillarBlock> createStrippedBundle() {
    return createBlockWithItem("stripped_" + getName() + "_bundle", () -> log(topColor, sideColor));
  }
  private static RotatedPillarBlock log(MapColor p_285370_, MapColor p_285126_) {
    return new RotatedPillarBlock(Properties.of().mapColor((p_152624_) -> p_152624_.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? p_285370_ : p_285126_).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()){
      @Override
      public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
      }

      @Override
      public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
      }

      @Override
      public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
      }
    };
  }
  
  private DeferredBlock<BranchingTrunkBlock> createJoshuaLog() {
    return createBlockWithItem(getLogName(), () -> new BranchingTrunkBlock(
        Properties.of().ignitedByLava().mapColor(MapColor.COLOR_GRAY).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD)));
  }

  private DeferredBlock<BranchingTrunkBlock> createStrippedJoshuaLog() {
    return createBlockWithItem("stripped_" + getLogName(), () -> new BranchingTrunkBlock(
        Properties.of().ignitedByLava().mapColor(MapColor.COLOR_GRAY).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD)));
  }

  private DeferredBlock<RotatedPillarBlock> createWood() {
    return createBlockWithItem(getWoodName(), () -> log(sideColor, sideColor));
  }

  private DeferredBlock<RotatedPillarBlock> createStrippedWood() {
    return createBlockWithItem("stripped_" + getWoodName(), () -> log(topColor, topColor));
  }

  private DeferredBlock<LeavesBlock> createLeaves() {
    DeferredBlock<LeavesBlock> block = createBlockWithItem(getName() + "_leaves", () -> new LeavesBlock(
        Properties.of().mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()
            .isValidSpawn(NSRegistryHelper::ocelotOrParrot).isSuffocating(NSRegistryHelper::never).isViewBlocking(NSRegistryHelper::never).ignitedByLava().pushReaction(PushReaction.DESTROY)
            .isRedstoneConductor(NSRegistryHelper::never)){
      @Override
      public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
      }

      @Override
      public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 60;
      }

      @Override
      public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 30;
      }
    });
    LeavesHashMap.put(getName(), block);
    return block;
  }

  private DeferredBlock<LeavesBlock> createLeaves(String prefix) {
    DeferredBlock<LeavesBlock> block = createBlockWithItem(prefix + getName() + "_leaves", () -> new LeavesBlock(
        Properties.of().mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()
            .isValidSpawn(NSRegistryHelper::ocelotOrParrot).isSuffocating(NSRegistryHelper::never).isViewBlocking(NSRegistryHelper::never).ignitedByLava().pushReaction(PushReaction.DESTROY)
            .isRedstoneConductor(NSRegistryHelper::never)){
      @Override
      public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
      }

      @Override
      public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 60;
      }

      @Override
      public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 30;
      }
    });
    LeavesHashMap.put(prefix + getName(), block);
    return block;
  }

  private DeferredBlock<ProjectileLeavesBlock> createFrostableLeaves() {
    DeferredBlock<ProjectileLeavesBlock> block = createBlockWithItem(getName() + "_leaves",
            () -> new ProjectileLeavesBlock(Properties.of().mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()
            .isValidSpawn(NSRegistryHelper::ocelotOrParrot).isSuffocating(NSRegistryHelper::never).isViewBlocking(NSRegistryHelper::never).ignitedByLava().pushReaction(PushReaction.DESTROY)
            .isRedstoneConductor(NSRegistryHelper::never), frostyLeaves){
              @Override
              public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return true;
              }

              @Override
              public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return 60;
              }

              @Override
              public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return 30;
              }
            });
    LeavesHashMap.put(getName(), block);
    return block;
  }

  private DeferredBlock<ProjectileLeavesBlock> createFrostableLeaves(String prefix) {
    DeferredBlock<ProjectileLeavesBlock> block = createBlockWithItem(prefix + getName() + "_leaves",
            () -> new ProjectileLeavesBlock(Properties.of().mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()
            .isValidSpawn(NSRegistryHelper::ocelotOrParrot).isSuffocating(NSRegistryHelper::never).isViewBlocking(NSRegistryHelper::never).ignitedByLava().pushReaction(PushReaction.DESTROY)
            .isRedstoneConductor(NSRegistryHelper::never), frostyLeaves){
              @Override
              public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return true;
              }

              @Override
              public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return 60;
              }

              @Override
              public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return 30;
              }
            });
    LeavesHashMap.put(prefix + getName(), block);
    return block;
  }

  private DeferredBlock<ParticleLeavesBlock> createParticleLeaves(Supplier<? extends ParticleOptions> particle, int chance) {
    DeferredBlock<ParticleLeavesBlock> block = createBlockWithItem(getName() + "_leaves", () -> new ParticleLeavesBlock(
        Properties.of().mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()
            .isValidSpawn(NSRegistryHelper::ocelotOrParrot).isSuffocating(NSRegistryHelper::never).isViewBlocking(NSRegistryHelper::never).ignitedByLava().pushReaction(PushReaction.DESTROY)
            .isRedstoneConductor(NSRegistryHelper::never), particle, chance){
      @Override
      public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
      }

      @Override
      public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 60;
      }

      @Override
      public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 30;
      }
    });
    LeavesHashMap.put(getName(), block);
    return block;
  }

  private DeferredBlock<ParticleLeavesBlock> createParticleLeaves(String prefix, Supplier<? extends ParticleOptions> particle, int chance) {
    DeferredBlock<ParticleLeavesBlock> block = createBlockWithItem(prefix + getName() + "_leaves", () -> new ParticleLeavesBlock(
        Properties.of().mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()
            .isValidSpawn(NSRegistryHelper::ocelotOrParrot).isSuffocating(NSRegistryHelper::never).isViewBlocking(NSRegistryHelper::never).ignitedByLava().pushReaction(PushReaction.DESTROY)
            .isRedstoneConductor(NSRegistryHelper::never), particle, chance){
      @Override
      public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
      }

      @Override
      public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 60;
      }

      @Override
      public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 30;
      }
    });
    LeavesHashMap.put(prefix + getName(), block);
    return block;
  }

  private DeferredBlock<VinesLeavesBlock> createVinesLeavesBlock(DeferredBlock<DownwardsVinePlantBlock> vinesPlantBlock, DeferredBlock<DownwardVineBlock> vinesTipBlock) {
    DeferredBlock<VinesLeavesBlock> block = createBlockWithItem(getName() + "_leaves",
            () -> new VinesLeavesBlock(Properties.of().strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()
            .isValidSpawn(NSRegistryHelper::ocelotOrParrot).isSuffocating(NSRegistryHelper::never).isViewBlocking(NSRegistryHelper::never).ignitedByLava().pushReaction(PushReaction.DESTROY)
            .isRedstoneConductor(NSRegistryHelper::never), vinesPlantBlock, vinesTipBlock){
              @Override
              public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return true;
              }

              @Override
              public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return 60;
              }

              @Override
              public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return 30;
              }
            });
    LeavesHashMap.put(getName(), block);
    return block;
  }

  private DeferredBlock<VinesLeavesBlock> createVinesLeavesBlock(String prefix, DeferredBlock<DownwardsVinePlantBlock> vinesPlantBlock, DeferredBlock<DownwardVineBlock> vinesTipBlock) {
    DeferredBlock<VinesLeavesBlock> block = createBlockWithItem(prefix + getName() + "_leaves",
            () -> new VinesLeavesBlock(Properties.of().strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()
            .isValidSpawn(NSRegistryHelper::ocelotOrParrot).isSuffocating(NSRegistryHelper::never).isViewBlocking(NSRegistryHelper::never).ignitedByLava()
            .pushReaction(PushReaction.DESTROY).isRedstoneConductor(NSRegistryHelper::never), vinesPlantBlock, vinesTipBlock){
              @Override
              public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return true;
              }

              @Override
              public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return 60;
              }

              @Override
              public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return 30;
              }
            });
    LeavesHashMap.put(prefix + getName(), block);
    return block;
  }

  private DeferredBlock<DownwardVineBlock> createVines(Supplier<DeferredBlock<DownwardsVinePlantBlock>> vinesPlantBlock) {
    DeferredBlock<DownwardVineBlock> vinesBlock = createBlockWithItem(getName() + "_vines",
            () -> new DownwardVineBlock(Properties
            .of()
            .pushReaction(PushReaction.DESTROY)
            .randomTicks()
            .noCollission()
            .noOcclusion()
            .instabreak()
            .sound(SoundType.WEEPING_VINES), vinesPlantBlock));
    RenderLayerHashMap.put(getName() + "_vines", vinesBlock);
    return vinesBlock;
  }

  private DeferredBlock<DownwardVineBlock> createVines(String prefix, Supplier<DeferredBlock<DownwardsVinePlantBlock>> vinesPlantBlock) {
    DeferredBlock<DownwardVineBlock> vinesBlock = createBlockWithItem(prefix + getName() + "_vines",
            () -> new DownwardVineBlock(Properties
            .of()
            .pushReaction(PushReaction.DESTROY)
            .randomTicks()
            .noCollission()
            .noOcclusion()
            .instabreak()
            .sound(SoundType.WEEPING_VINES), vinesPlantBlock));
    RenderLayerHashMap.put(prefix + getName() + "_vines", vinesBlock);
    return vinesBlock;
  }

  private DeferredBlock<DownwardsVinePlantBlock> createVinesPlant(DeferredBlock<DownwardVineBlock> vines) {
    DeferredBlock<DownwardsVinePlantBlock> vinesPlant = registerBlockWithoutItem(getName() + "_vines_plant", () -> new DownwardsVinePlantBlock(Properties
        .of()
        .pushReaction(PushReaction.DESTROY)
        .noCollission()
        .noOcclusion()
        .instabreak()
        .sound(SoundType.WEEPING_VINES)
        .dropsLike(vines.get()), vines));
    RenderLayerHashMap.put(getName() + "_vines_plant", vinesPlant);
    return vinesPlant;
  }

  private DeferredBlock<DownwardsVinePlantBlock> createVinesPlant(String prefix, DeferredBlock<DownwardVineBlock> vines) {
    DeferredBlock<DownwardsVinePlantBlock> vinesPlant = registerBlockWithoutItem(prefix + getName() + "_vines_plant", () -> new DownwardsVinePlantBlock(Properties
        .of()
        .pushReaction(PushReaction.DESTROY)
        .noCollission()
        .noOcclusion()
        .instabreak()
        .sound(SoundType.WEEPING_VINES)
        .dropsLike(vines.get()), vines));
    RenderLayerHashMap.put(prefix + getName() + "_vines_plant", vinesPlant);
    return vinesPlant;
  }

  private DeferredBlock<Block> createPlanks() {
    return createBlockWithItem(getName() + "_planks", () -> new Block(Properties.ofFullCopy(getBase()).sound(getBlockSetType().get().soundType()).mapColor(getTopColor())){
      @Override
      public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
      }

      @Override
      public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 20;
      }

      @Override
      public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
      }
    });
  }

  private DeferredBlock<StairBlock> createStairs() {
    return createBlockWithItem(getName() + "_stairs",
            () -> new StairBlock(getBase().defaultBlockState(), Properties.ofFullCopy(getBase()).sound(getBlockSetType().get().soundType()).mapColor(getTopColor())){
              @Override
              public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return true;
              }

              @Override
              public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return 20;
              }

              @Override
              public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return 5;
              }
            });
  }

  private DeferredBlock<SlabBlock> createSlab() {
    return createBlockWithItem(getName() + "_slab", () -> new SlabBlock(Properties.ofFullCopy(getBase()).sound(getBlockSetType().get().soundType()).mapColor(getTopColor())){
      @Override
      public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
      }

      @Override
      public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 20;
      }

      @Override
      public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
      }
    });
  }

  private DeferredBlock<Block> createMosaic() {
    DeferredBlock<Block> block = createBlockWithoutItem(getName() + "_mosaic", () -> new Block(Properties.ofFullCopy(getBase()).sound(getBlockSetType().get().soundType()).mapColor(getTopColor())){
      @Override
      public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
      }

      @Override
      public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 20;
      }

      @Override
      public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
      }
    });
    NSRegistryHelper.registerItem(getName() + "_mosaic", () -> new BlockItem(block.get(), new Item.Properties()){
      @Override
      public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return 300;
      }
    });
    return block;
  }

  private DeferredBlock<StairBlock> createMosaicStairs() {
    DeferredBlock<StairBlock> block = createBlockWithoutItem(getName() + "_mosaic_stairs",
            () -> new StairBlock(getBase().defaultBlockState(), Properties.ofFullCopy(getBase()).sound(getBlockSetType().get().soundType()).mapColor(getTopColor())){
              @Override
              public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return true;
              }

              @Override
              public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return 20;
              }

              @Override
              public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return 5;
              }
            });
    NSRegistryHelper.registerItem(getName() + "_mosaic_stairs", () -> new BlockItem(block.get(), new Item.Properties()){
      @Override
      public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return 300;
      }
    });
    return block;
  }

  private DeferredBlock<SlabBlock> createMosaicSlab() {
    DeferredBlock<SlabBlock> block = createBlockWithoutItem(getName() + "_mosaic_slab", () -> new SlabBlock(Properties.ofFullCopy(getBase()).sound(getBlockSetType().get().soundType()).mapColor(getTopColor())){
      @Override
      public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
      }

      @Override
      public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 20;
      }

      @Override
      public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
      }
    });
    NSRegistryHelper.registerItem(getName() + "_mosaic_slab", () -> new BlockItem(block.get(), new Item.Properties()){
      @Override
      public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return 150;
      }
    });
    return block;
  }

  private DeferredBlock<FenceBlock> createFence() {
    return createBlockWithItem(getName() + "_fence", () -> new FenceBlock(Properties.ofFullCopy(getBase()).sound(getBlockSetType().get().soundType()).mapColor(getTopColor())){
      @Override
      public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
      }

      @Override
      public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 20;
      }

      @Override
      public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
      }
    });
  }

  private DeferredBlock<FenceGateBlock> createFenceGate() {
    return createBlockWithItem(getName() + "_fence_gate", () -> new FenceGateBlock(getWoodType().get(),
            Properties.of().mapColor(getBase().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).ignitedByLava()){
      @Override
      public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
      }

      @Override
      public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 20;
      }

      @Override
      public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
      }
    });
  }

  private DeferredBlock<PressurePlateBlock> createPressurePlate() {
    return createBlockWithItem(getName() + "_pressure_plate", () -> new PressurePlateBlock(getBlockSetType().get(),
            Properties.of().mapColor(this.getBase().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(0.5F).ignitedByLava()
                    .pushReaction(PushReaction.DESTROY)));
  }

  private DeferredBlock<ButtonBlock> createButton() {
    return createBlockWithItem(getName() + "_button",
            () -> new ButtonBlock(getBlockSetType().get(), 30, Properties.of().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY)));
  }

  private DeferredBlock<DoorBlock> createDoor() {
    DeferredBlock<DoorBlock> block = createBlockWithItem(getName() + "_door", () -> new DoorBlock(getBlockSetType().get(), Properties.ofFullCopy(getBase()).sound(getBlockSetType().get().soundType()).noOcclusion().mapColor(getTopColor())));
    RenderLayerHashMap.put(getName() + "_door", block);
    return block;
  }

  private DeferredBlock<TrapDoorBlock> createTrapDoor() {
    DeferredBlock<TrapDoorBlock> block = createBlockWithItem(getName() + "_trapdoor", () -> new TrapDoorBlock(getBlockSetType().get(), Properties.ofFullCopy(getBase()).sound(getBlockSetType().get().soundType()).noOcclusion().mapColor(getTopColor())));
    RenderLayerHashMap.put(getName() + "_trapdoor", block);
    return block;
  }

  private DeferredBlock<StandingSignBlock> createSign() {
    return registerBlockWithoutItem(getName() + "_sign", () -> new StandingSignBlock(getWoodType().get(), Properties.ofFullCopy(getSignBase()).mapColor(this.getTopColor())));
  }

  private DeferredBlock<WallSignBlock> createWallSign() {
    return registerBlockWithoutItem(getName() + "_wall_sign", () -> new WallSignBlock(getWoodType().get(), Properties.ofFullCopy(getSignBase()).mapColor(this.getTopColor()).dropsLike(sign.get())));
  }

  private DeferredBlock<CeilingHangingSignBlock> createHangingSign() {
    return registerBlockWithoutItem(getName() + "_hanging_sign", () -> new CeilingHangingSignBlock(getWoodType().get(), Properties.ofFullCopy(getHangingSignBase()).mapColor(this.getTopColor())));
  }

  private DeferredBlock<WallHangingSignBlock> createWallHangingSign() {
    return registerBlockWithoutItem(getName() + "_wall_hanging_sign",
            () -> new WallHangingSignBlock(getWoodType().get(), Properties.ofFullCopy(getHangingSignBase()).mapColor(this.getTopColor()).dropsLike(hangingSign.get())));
  }

  public DeferredBlock<SaplingBlock> createSapling(TreeGrower saplingGenerator) {
    DeferredBlock<SaplingBlock> block = createBlockWithItem(getName() + "_sapling", () -> new SaplingBlock(saplingGenerator, Properties.ofFullCopy(Blocks.SPRUCE_SAPLING)));
    RenderLayerHashMap.put(getName() + "_sapling", block);
    return block;
  }

  public DeferredBlock<SandySaplingBlock> createSandySapling(TreeGrower saplingGenerator) {
    DeferredBlock<SandySaplingBlock> block = createBlockWithItem(getName() + "_sapling", () -> new SandySaplingBlock(saplingGenerator, Properties.ofFullCopy(Blocks.SPRUCE_SAPLING)));
    RenderLayerHashMap.put(getName() + "_sapling", block);
    return block;
  }

  public DeferredBlock<FlowerPotBlock> createPottedSapling(DeferredBlock<? extends SaplingBlock> sapling) {
    return registerTransparentBlockWithoutItem("potted_" + getName() + "_sapling",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT).getEmptyPot(), sapling, Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
  }

  public DeferredBlock<SaplingBlock> createSapling(String prefix, TreeGrower saplingGenerator) {
    DeferredBlock<SaplingBlock> block = createBlockWithItem(prefix + getName() + "_sapling", () -> new SaplingBlock(saplingGenerator, Properties.ofFullCopy(Blocks.SPRUCE_SAPLING)));
    RenderLayerHashMap.put(prefix + getName() + "_sapling", block);
    return block;
  }

  public DeferredBlock<SandySaplingBlock> createSandySapling(String prefix, TreeGrower saplingGenerator) {
    DeferredBlock<SandySaplingBlock> block = createBlockWithItem(prefix + getName() + "_sapling", () -> new SandySaplingBlock(saplingGenerator, Properties.ofFullCopy(Blocks.SPRUCE_SAPLING)));
    RenderLayerHashMap.put(prefix + getName() + "_sapling", block);
    return block;
  }

  public DeferredBlock<FlowerPotBlock> createPottedSapling(String prefix, DeferredBlock<? extends SaplingBlock> sapling) {
    return registerTransparentBlockWithoutItem("potted_" + prefix + getName() + "_sapling",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT).getEmptyPot(), sapling, Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
  }

  private DeferredItem<SignItem> createSignItem() {
    return createItem(getName() + "_sign", () -> new SignItem(new Item.Properties().stacksTo(16), sign.get(), wallSign.get()));
  }

  private DeferredItem<HangingSignItem> createHangingSignItem() {
    return createItem(getName() + "_hanging_sign", () -> new HangingSignItem(hangingSign.get(), hangingWallSign.get(), new Item.Properties().stacksTo(16)));
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
