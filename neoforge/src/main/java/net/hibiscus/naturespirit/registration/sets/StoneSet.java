package net.hibiscus.naturespirit.registration.sets;

import com.google.common.base.Supplier;
import net.hibiscus.naturespirit.registration.NSRegistryHelper;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;

public class StoneSet {


  private DeferredBlock<Block> cobbled;
  private DeferredBlock<StairBlock> cobbledStairs;
  private DeferredBlock<SlabBlock> cobbledSlab;
  private DeferredBlock<WallBlock> cobbledWall;
  private DeferredBlock<Block> mossyCobbled;
  private DeferredBlock<StairBlock> mossyCobbledStairs;
  private DeferredBlock<SlabBlock> mossyCobbledSlab;
  private DeferredBlock<WallBlock> mossyCobbledWall;
  private DeferredBlock<? extends Block> base;
  private DeferredBlock<StairBlock> baseStairs;
  private DeferredBlock<SlabBlock> baseSlab;
  private DeferredBlock<Block> polished;
  private DeferredBlock<StairBlock> polishedStairs;
  private DeferredBlock<SlabBlock> polishedSlab;
  private DeferredBlock<WallBlock> polishedWall;
  private DeferredBlock<Block> tiles;
  private DeferredBlock<StairBlock> tilesStairs;
  private DeferredBlock<SlabBlock> tilesSlab;
  private DeferredBlock<WallBlock> tilesWall;
  private DeferredBlock<Block> bricks;
  private DeferredBlock<StairBlock> bricksStairs;
  private DeferredBlock<SlabBlock> bricksSlab;
  private DeferredBlock<WallBlock> bricksWall;
  private DeferredBlock<Block> chiseled;
  private DeferredBlock<Block> crackedBricks;
  private DeferredBlock<Block> crackedTiles;
  private DeferredBlock<Block> mossyBricks;
  private DeferredBlock<StairBlock> mossyBricksStairs;
  private DeferredBlock<SlabBlock> mossyBricksSlab;
  private DeferredBlock<WallBlock> mossyBricksWall;

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

  private <T extends Block> DeferredBlock<T> createBlockWithItem(String blockID, Supplier<T> block) {
      return NSRegistryHelper.registerBlock(blockID, block);
  }

  public String getName() {
    return name;
  }

  private DeferredBlock<Block> createBasic(String name, Supplier<Block> template) {
    return createBlockWithItem(name, () -> new Block(BlockBehaviour.Properties.ofFullCopy(template.get()).destroyTime(hardness).mapColor(getMapColor())));
  }

  private DeferredBlock<RotatedPillarBlock> createRotatable(String name, Supplier<Block> template) {
    return createBlockWithItem(name, () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(template.get()).destroyTime(hardness).mapColor(getMapColor())));
  }

  private DeferredBlock<StairBlock> createStairs(String name, DeferredBlock<? extends Block> template) {
    return createBlockWithItem(name + "_stairs", () -> new StairBlock(template.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(template.get())));
  }

  private DeferredBlock<SlabBlock> createSlab(String name, DeferredBlock<? extends Block> template) {
    return createBlockWithItem(name + "_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(template.get())));
  }

  private DeferredBlock<WallBlock> createWall(String name, DeferredBlock<Block> template) {
    return createBlockWithItem(name + "_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(template.get()).forceSolidOn()));
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

  public DeferredBlock<Block> getCobbled() {
    return cobbled;
  }

  public DeferredBlock<StairBlock> getCobbledStairs() {return cobbledStairs;}

  public DeferredBlock<SlabBlock> getCobbledSlab() {
    return cobbledSlab;
  }

  public DeferredBlock<WallBlock> getCobbledWall() {
    return cobbledWall;
  }

  public DeferredBlock<? extends Block> getBase() {
    return base;
  }

  public DeferredBlock<StairBlock> getBaseStairs() {
    return baseStairs;
  }

  public DeferredBlock<SlabBlock> getBaseSlab() {
    return baseSlab;
  }

  public DeferredBlock<Block> getPolished() {
    return polished;
  }

  public DeferredBlock<StairBlock> getPolishedStairs() {
    return polishedStairs;
  }

  public DeferredBlock<SlabBlock> getPolishedSlab() {
    return polishedSlab;
  }

  public DeferredBlock<WallBlock> getPolishedWall() {
    return polishedWall;
  }

  public DeferredBlock<Block> getTiles() {
    return tiles;
  }

  public DeferredBlock<StairBlock> getTilesStairs() {
    return tilesStairs;
  }

  public DeferredBlock<SlabBlock> getTilesSlab() {
    return tilesSlab;
  }

  public DeferredBlock<WallBlock> getTilesWall() {
    return tilesWall;
  }

  public DeferredBlock<Block> getBricks() {
    return bricks;
  }

  public DeferredBlock<StairBlock> getBricksStairs() {
    return bricksStairs;
  }

  public DeferredBlock<SlabBlock> getBricksSlab() {
    return bricksSlab;
  }

  public DeferredBlock<WallBlock> getBricksWall() {
    return bricksWall;
  }

  public DeferredBlock<Block> getChiseled() {
    return chiseled;
  }

  public DeferredBlock<Block> getCrackedBricks() {
    return crackedBricks;
  }

  public DeferredBlock<Block> getCrackedTiles() {
    return crackedTiles;
  }

  public DeferredBlock<Block> getMossyBricks() {
    return mossyBricks;
  }

  public DeferredBlock<StairBlock> getMossyBricksStairs() {
    return mossyBricksStairs;
  }

  public DeferredBlock<SlabBlock> getMossyBricksSlab() {
    return mossyBricksSlab;
  }

  public DeferredBlock<WallBlock> getMossyBricksWall() {
    return mossyBricksWall;
  }

  public DeferredBlock<Block> getMossyCobbled() {
    return mossyCobbled;
  }

  public DeferredBlock<StairBlock> getMossyCobbledStairs() {
    return mossyCobbledStairs;
  }

  public DeferredBlock<SlabBlock> getMossyCobbledSlab() {
    return mossyCobbledSlab;
  }

  public DeferredBlock<WallBlock> getMossyCobbledWall() {
    return mossyCobbledWall;
  }

  public MapColor getMapColor() {
    return mapColor;
  }
}
