package net.hibiscus.naturespirit.registration.sets;

import net.hibiscus.naturespirit.registration.NSBlockHolder;
import net.hibiscus.naturespirit.registration.NSRegistryHelper;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Function;
import java.util.function.Supplier;

public class StoneSet {


  private NSBlockHolder<Block> cobbled;
  private NSBlockHolder<StairBlock> cobbledStairs;
  private NSBlockHolder<SlabBlock> cobbledSlab;
  private NSBlockHolder<WallBlock> cobbledWall;
  private NSBlockHolder<Block> mossyCobbled;
  private NSBlockHolder<StairBlock> mossyCobbledStairs;
  private NSBlockHolder<SlabBlock> mossyCobbledSlab;
  private NSBlockHolder<WallBlock> mossyCobbledWall;
  private NSBlockHolder<? extends Block> base;
  private NSBlockHolder<StairBlock> baseStairs;
  private NSBlockHolder<SlabBlock> baseSlab;
  private NSBlockHolder<Block> polished;
  private NSBlockHolder<StairBlock> polishedStairs;
  private NSBlockHolder<SlabBlock> polishedSlab;
  private NSBlockHolder<WallBlock> polishedWall;
  private NSBlockHolder<Block> tiles;
  private NSBlockHolder<StairBlock> tilesStairs;
  private NSBlockHolder<SlabBlock> tilesSlab;
  private NSBlockHolder<WallBlock> tilesWall;
  private NSBlockHolder<Block> bricks;
  private NSBlockHolder<StairBlock> bricksStairs;
  private NSBlockHolder<SlabBlock> bricksSlab;
  private NSBlockHolder<WallBlock> bricksWall;
  private NSBlockHolder<Block> chiseled;
  private NSBlockHolder<Block> crackedBricks;
  private NSBlockHolder<Block> crackedTiles;
  private NSBlockHolder<Block> mossyBricks;
  private NSBlockHolder<StairBlock> mossyBricksStairs;
  private NSBlockHolder<SlabBlock> mossyBricksSlab;
  private NSBlockHolder<WallBlock> mossyBricksWall;

  private final String name;
  private final MapColor mapColor;
  private final boolean hasTiles;
  private final boolean hasCobbled;
  private final boolean hasCracked;
  private final boolean hasMossy;
  private boolean isRotatable;
  private final float hardness;

  private void registerStone() {

    base = isRotatable ? createRotatable(getName(), () -> Blocks.ANDESITE) : createBasic(getName(),() -> Blocks.ANDESITE);
    baseStairs = createStairs(getName(), base);
    baseSlab = createSlab(getName(), base);
    chiseled = createBasic("chiseled_" + getName(), () -> Blocks.CHISELED_STONE_BRICKS);

    if (hasCobbled()) {
      cobbled = createBasic("cobbled_" + getName(), () -> Blocks.COBBLESTONE);
      cobbledStairs = createStairs("cobbled_" + getName(), cobbled);
      cobbledSlab = createSlab("cobbled_" + getName(), cobbled);
      cobbledWall = createWall("cobbled_" + getName(), cobbled);
    }
    if (hasMossy() && hasCobbled()) {
      mossyCobbled = createBasic("mossy_cobbled_" + getName(), () -> Blocks.MOSSY_COBBLESTONE);
      mossyCobbledStairs = createStairs("mossy_cobbled_" + getName(), mossyCobbled);
      mossyCobbledSlab = createSlab("mossy_cobbled_" + getName(), mossyCobbled);
      mossyCobbledWall = createWall("mossy_cobbled_" + getName(), mossyCobbled);
    }

    polished = createBasic("polished_" + getName(), () -> Blocks.POLISHED_ANDESITE);
    polishedStairs = createStairs("polished_" + getName(), polished);
    polishedSlab = createSlab("polished_" + getName(), polished);
    polishedWall = createWall("polished_" + getName(), polished);

    bricks = createBasic(getName() + "_bricks", () -> Blocks.STONE_BRICKS);
    bricksStairs = createStairs(getName() + "_brick", bricks);
    bricksSlab = createSlab(getName() + "_brick", bricks);
    bricksWall = createWall(getName() + "_brick", bricks);

    if (hasMossy()) {
      mossyBricks = createBasic("mossy_" + getName() + "_bricks", () -> Blocks.MOSSY_STONE_BRICKS);
      mossyBricksStairs = createStairs("mossy_" + getName() + "_brick", bricks);
      mossyBricksSlab = createSlab("mossy_" + getName() + "_brick", bricks);
      mossyBricksWall = createWall("mossy_" + getName() + "_brick", bricks);
    }
    if (hasCracked()) {
      crackedBricks = createBasic("cracked_" + getName() + "_bricks", () -> Blocks.CRACKED_STONE_BRICKS);
    }
    if (hasTiles()) {
      tiles = createBasic(getName() + "_tiles", () -> Blocks.COBBLESTONE);
      tilesStairs = createStairs(getName() + "_tile", polished);
      tilesSlab = createSlab(getName() + "_tile", polished);
      tilesWall = createWall(getName() + "_tile", polished);
      if (hasCracked()) {
        crackedTiles = createBasic("cracked_" + getName() + "_tiles", () -> Blocks.CRACKED_STONE_BRICKS);
      }
    }
  }

  public StoneSet(String name, MapColor mapColor, float hardness, boolean hasCobbled, boolean hasCracked, boolean hasMossy,
      boolean hasTiles) {
    this.name = name;
    this.mapColor = mapColor;
    this.hardness = hardness;
    this.hasTiles = hasTiles;
    this.hasCobbled = hasCobbled;
    this.hasCracked = hasCracked;
    this.hasMossy = hasMossy;
    registerStone();
  }

  public StoneSet(String name, MapColor mapColor, float hardness, boolean hasCobbled, boolean hasCracked, boolean hasMossy, boolean hasTiles,
      boolean isRotatable) {
    this.name = name;
    this.mapColor = mapColor;
    this.hardness = hardness;
    this.hasTiles = hasTiles;
    this.hasCobbled = hasCobbled;
    this.hasCracked = hasCracked;
    this.hasMossy = hasMossy;
    this.isRotatable = isRotatable;
    registerStone();
  }

  private <T extends Block> NSBlockHolder<T> createBlockWithItem(String blockID, Function<BlockBehaviour.Properties, T> block, Supplier<BlockBehaviour.Properties> properties) {
      return NSRegistryHelper.registerBlock(blockID, block, properties);
  }

  public String getName() {
    return name;
  }

  private NSBlockHolder<Block> createBasic(String name, Supplier<Block> template) {
    return createBlockWithItem(name, Block::new, () -> BlockBehaviour.Properties.ofFullCopy(template.get()).destroyTime(hardness).mapColor(getMapColor()));
  }

  private NSBlockHolder<RotatedPillarBlock> createRotatable(String name, Supplier<Block> template) {
    return createBlockWithItem(name, RotatedPillarBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(template.get()).destroyTime(hardness).mapColor(getMapColor()));
  }

  private NSBlockHolder<StairBlock> createStairs(String name, NSBlockHolder<? extends Block> template) {
    return createBlockWithItem(name + "_stairs", properties -> new StairBlock(template.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(template.get()));
  }

  private NSBlockHolder<SlabBlock> createSlab(String name, NSBlockHolder<? extends Block> template) {
    return createBlockWithItem(name + "_slab", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(template.get()));
  }

  private NSBlockHolder<WallBlock> createWall(String name, NSBlockHolder<Block> template) {
    return createBlockWithItem(name + "_wall", WallBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(template.get()).forceSolidOn());
  }

  public boolean hasTiles() {
    return hasTiles;
  }

  public boolean hasCracked() {
    return hasCracked;
  }

  public boolean hasCobbled() {
    return hasCobbled;
  }

  public boolean hasMossy() {
    return hasMossy;
  }

  public boolean isRotatable() {
    return isRotatable;
  }

  public NSBlockHolder<Block> getCobbled() {
    return cobbled;
  }

  public NSBlockHolder<StairBlock> getCobbledStairs() {return cobbledStairs;}

  public NSBlockHolder<SlabBlock> getCobbledSlab() {
    return cobbledSlab;
  }

  public NSBlockHolder<WallBlock> getCobbledWall() {
    return cobbledWall;
  }

  public NSBlockHolder<? extends Block> getBase() {
    return base;
  }

  public NSBlockHolder<StairBlock> getBaseStairs() {
    return baseStairs;
  }

  public NSBlockHolder<SlabBlock> getBaseSlab() {
    return baseSlab;
  }

  public NSBlockHolder<Block> getPolished() {
    return polished;
  }

  public NSBlockHolder<StairBlock> getPolishedStairs() {
    return polishedStairs;
  }

  public NSBlockHolder<SlabBlock> getPolishedSlab() {
    return polishedSlab;
  }

  public NSBlockHolder<WallBlock> getPolishedWall() {
    return polishedWall;
  }

  public NSBlockHolder<Block> getTiles() {
    return tiles;
  }

  public NSBlockHolder<StairBlock> getTilesStairs() {
    return tilesStairs;
  }

  public NSBlockHolder<SlabBlock> getTilesSlab() {
    return tilesSlab;
  }

  public NSBlockHolder<WallBlock> getTilesWall() {
    return tilesWall;
  }

  public NSBlockHolder<Block> getBricks() {
    return bricks;
  }

  public NSBlockHolder<StairBlock> getBricksStairs() {
    return bricksStairs;
  }

  public NSBlockHolder<SlabBlock> getBricksSlab() {
    return bricksSlab;
  }

  public NSBlockHolder<WallBlock> getBricksWall() {
    return bricksWall;
  }

  public NSBlockHolder<Block> getChiseled() {
    return chiseled;
  }

  public NSBlockHolder<Block> getCrackedBricks() {
    return crackedBricks;
  }

  public NSBlockHolder<Block> getCrackedTiles() {
    return crackedTiles;
  }

  public NSBlockHolder<Block> getMossyBricks() {
    return mossyBricks;
  }

  public NSBlockHolder<StairBlock> getMossyBricksStairs() {
    return mossyBricksStairs;
  }

  public NSBlockHolder<SlabBlock> getMossyBricksSlab() {
    return mossyBricksSlab;
  }

  public NSBlockHolder<WallBlock> getMossyBricksWall() {
    return mossyBricksWall;
  }

  public NSBlockHolder<Block> getMossyCobbled() {
    return mossyCobbled;
  }

  public NSBlockHolder<StairBlock> getMossyCobbledStairs() {
    return mossyCobbledStairs;
  }

  public NSBlockHolder<SlabBlock> getMossyCobbledSlab() {
    return mossyCobbledSlab;
  }

  public NSBlockHolder<WallBlock> getMossyCobbledWall() {
    return mossyCobbledWall;
  }

  public MapColor getMapColor() {
    return mapColor;
  }
}
