package net.hibiscus.naturespirit.registration;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import net.hibiscus.naturespirit.NaturesSpirit;
import net.hibiscus.naturespirit.blocks.*;
import net.hibiscus.naturespirit.blocks.block_entities.PizzaBlockEntity;
import net.hibiscus.naturespirit.datagen.NSConfiguredFeatures;
import net.hibiscus.naturespirit.items.*;
import net.hibiscus.naturespirit.registration.sets.FlowerSet;
import net.hibiscus.naturespirit.registration.sets.StoneSet;
import net.hibiscus.naturespirit.registration.sets.WoodSet;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.ColorRGBA;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.List;
import java.util.Set;
import java.util.Optional;
import java.util.function.Supplier;

import static net.hibiscus.naturespirit.NaturesSpirit.MOD_ID;
import static net.hibiscus.naturespirit.registration.NSRegistryHelper.*;

@SuppressWarnings("unused")
public class NSBlocks {

  public static final NSRegistrar<BlockEntityType<?>> BLOCK_ENTITIES = NSRegistrar.of(Registries.BLOCK_ENTITY_TYPE);

  public static final NSBlockHolder<RedMossBlock> RED_MOSS_BLOCK = registerBlock("red_moss_block", props -> new RedMossBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).strength(0.1F).sound(SoundType.MOSS).pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<CarpetBlock> RED_MOSS_CARPET = registerBlock("red_moss_carpet", props -> new CarpetBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).strength(0.1F).sound(SoundType.MOSS_CARPET).pushReaction(PushReaction.DESTROY));

  public static final NSBlockHolder<Block> SANDY_SOIL = registerBlock("sandy_soil", props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).instrument(NoteBlockInstrument.BASEDRUM).strength(0.5F).sound(SoundType.GRAVEL));

  public static final NSBlockHolder<ColoredFallingBlock> PINK_SAND = registerBlock("pink_sand", props -> new ColoredFallingBlock(new ColorRGBA(14331784), props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.RAW_IRON).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.SAND));

  public static final NSBlockHolder<Block> PINK_SANDSTONE = registerBlock("pink_sandstone",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.8F)
  );

  public static final NSBlockHolder<StairBlock> PINK_SANDSTONE_STAIRS = registerBlock("pink_sandstone_stairs",
          props -> new StairBlock(PINK_SANDSTONE.get().defaultBlockState(), props), () -> BlockBehaviour.Properties.ofFullCopy(PINK_SANDSTONE.get())
  );

  public static final NSBlockHolder<SlabBlock> PINK_SANDSTONE_SLAB = registerBlock("pink_sandstone_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2.0F, 6.0F)
  );

  public static final NSBlockHolder<WallBlock> PINK_SANDSTONE_WALL = registerBlock("pink_sandstone_wall", props -> new WallBlock(props), () -> BlockBehaviour.Properties.ofFullCopy(PINK_SANDSTONE.get()).forceSolidOn()
  );

  public static final NSBlockHolder<Block> CHISELED_PINK_SANDSTONE = registerBlock("chiseled_pink_sandstone",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.8F)
  );

  public static final NSBlockHolder<Block> SMOOTH_PINK_SANDSTONE = registerBlock("smooth_pink_sandstone",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2.0F, 6.0F)
  );

  public static final NSBlockHolder<StairBlock> SMOOTH_PINK_SANDSTONE_STAIRS = registerBlock("smooth_pink_sandstone_stairs",
          props -> new StairBlock(SMOOTH_PINK_SANDSTONE.get().defaultBlockState(), props), () -> BlockBehaviour.Properties.ofFullCopy(SMOOTH_PINK_SANDSTONE.get())
  );

  public static final NSBlockHolder<SlabBlock> SMOOTH_PINK_SANDSTONE_SLAB = registerBlock("smooth_pink_sandstone_slab", props -> new SlabBlock(props), () -> BlockBehaviour.Properties.ofFullCopy(SMOOTH_PINK_SANDSTONE.get()));

  public static final NSBlockHolder<Block> CUT_PINK_SANDSTONE = registerBlock("cut_pink_sandstone",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.8F)
  );

  public static final NSBlockHolder<SlabBlock> CUT_PINK_SANDSTONE_SLAB = registerBlock("cut_pink_sandstone_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2.0F, 6.0F)
  );

  public static final NSBlockHolder<SemiTallGrassBlock> TALL_FRIGID_GRASS = registerTallPlantBlock("tall_frigid_grass", props -> new SemiTallGrassBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.GLOW_LICHEN).noCollision().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<NSFernBlock> FRIGID_GRASS = registerTransparentBlock("frigid_grass", props -> new NSFernBlock(props, TALL_FRIGID_GRASS), () -> BlockBehaviour.Properties.of().mapColor(MapColor.GLOW_LICHEN).noCollision().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ).pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<TallLargeDesertFernBlock> TALL_SCORCHED_GRASS = registerTallPlantBlock("tall_scorched_grass", props -> new TallLargeDesertFernBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.GLOW_LICHEN).noCollision().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<LargeDesertFernBlock> SCORCHED_GRASS = registerTransparentBlock("scorched_grass", props -> new LargeDesertFernBlock(props, TALL_SCORCHED_GRASS), () -> BlockBehaviour.Properties.of().mapColor(MapColor.GLOW_LICHEN).noCollision().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ).pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<TallLargeDesertFernBlock> TALL_BEACH_GRASS = registerTallPlantBlock("tall_beach_grass", props -> new TallLargeDesertFernBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).noCollision().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<LargeDesertFernBlock> BEACH_GRASS = registerTransparentBlock("beach_grass", props -> new LargeDesertFernBlock(props, TALL_BEACH_GRASS), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).noCollision().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ).pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<TallSedgeGrassBlock> TALL_SEDGE_GRASS = registerTallPlantBlock("tall_sedge_grass", props -> new TallSedgeGrassBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollision().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<SedgeGrassBlock> SEDGE_GRASS = registerTransparentBlock("sedge_grass", props -> new SedgeGrassBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollision().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ).pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<DoublePlantBlock> LARGE_FLAXEN_FERN = registerTallPlantBlock("large_flaxen_fern", props -> new DoublePlantBlock(props), () -> BlockBehaviour.Properties.of().noCollision().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<NSFernBlock> FLAXEN_FERN = registerTransparentBlock("flaxen_fern", props -> new NSFernBlock(props, LARGE_FLAXEN_FERN), () -> BlockBehaviour.Properties.of().noCollision().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ).pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<SemiTallGrassBlock> TALL_OAT_GRASS = registerTallPlantBlock("tall_oat_grass", props -> new SemiTallGrassBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.SAND).noCollision().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<NSFernBlock> OAT_GRASS = registerTransparentBlock("oat_grass", props -> new NSFernBlock(props, TALL_OAT_GRASS), () -> BlockBehaviour.Properties.of().mapColor(MapColor.SAND).noCollision().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ).pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<DoublePlantBlock> LARGE_LUSH_FERN = registerTallPlantBlock("large_lush_fern", props -> new DoublePlantBlock(props), () -> BlockBehaviour.Properties.of().noCollision().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<NSFernBlock> LUSH_FERN = registerTransparentBlock("lush_fern", props -> new NSFernBlock(props, LARGE_LUSH_FERN), () -> BlockBehaviour.Properties.of().noCollision().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ).pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<DoublePlantBlock> TALL_MELIC_GRASS = registerTallPlantBlock("tall_melic_grass", props -> new DoublePlantBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).noCollision().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<NSFernBlock> MELIC_GRASS = registerTransparentBlock("melic_grass", props -> new NSFernBlock(props, TALL_MELIC_GRASS), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).noCollision().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ).pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<BearberryBlock> GREEN_BEARBERRIES = registerTransparentBlock("green_bearberries", props -> new BearberryBlock(props), () -> BlockBehaviour.Properties.of().noCollision().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ).pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<BearberryBlock> RED_BEARBERRIES = registerTransparentBlock("red_bearberries", props -> new BearberryBlock(props), () -> BlockBehaviour.Properties.of().noCollision().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ).pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<BearberryBlock> PURPLE_BEARBERRIES = registerTransparentBlock("purple_bearberries", props -> new BearberryBlock(props), () -> BlockBehaviour.Properties.of().noCollision().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ).pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<LargeSproutsBlock> GREEN_BITTER_SPROUTS = registerTransparentBlock("green_bitter_sprouts", props -> new LargeSproutsBlock(props), () -> BlockBehaviour.Properties.of().noCollision().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<LargeSproutsBlock> RED_BITTER_SPROUTS = registerTransparentBlock("red_bitter_sprouts", props -> new LargeSproutsBlock(props), () -> BlockBehaviour.Properties.of().noCollision().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<LargeSproutsBlock> PURPLE_BITTER_SPROUTS = registerTransparentBlock("purple_bitter_sprouts", props -> new LargeSproutsBlock(props), () -> BlockBehaviour.Properties.of().noCollision().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<CattailBlock> CATTAIL = registerTransparentBlock("cattail", props -> new CattailBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollision().instabreak().sound(SoundType.GRASS).ignitedByLava().pushReaction(PushReaction.DESTROY));

  public static final NSBlockHolder<AzollaBlock> AZOLLA = NSRegistryHelper.registerTransparentBlockWithoutItem("azolla", props -> new AzollaBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).pushReaction(PushReaction.DESTROY).randomTicks().noOcclusion().instabreak().noCollision().sound(SoundType.LILY_PAD));
  public static final NSItemHolder<AzollaItem> AZOLLA_ITEM = registerItem("azolla", props -> new AzollaItem(AZOLLA.get(), props), () -> new Item.Properties().useBlockDescriptionPrefix());

  public static final NSBlockHolder<SucculentBlock> ORNATE_SUCCULENT = registerTransparentBlockWithoutItem("ornate_succulent",
          props -> new SucculentBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).sound(SoundType.GRASS).noCollision().instabreak());
  public static final NSBlockHolder<SucculentBlock> DROWSY_SUCCULENT = registerTransparentBlockWithoutItem("drowsy_succulent",
          props -> new SucculentBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).sound(SoundType.GRASS).noCollision().instabreak());
  public static final NSBlockHolder<SucculentBlock> AUREATE_SUCCULENT = registerTransparentBlockWithoutItem("aureate_succulent",
          props -> new SucculentBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).sound(SoundType.GRASS).noCollision().instabreak());
  public static final NSBlockHolder<SucculentBlock> SAGE_SUCCULENT = registerTransparentBlockWithoutItem("sage_succulent",
          props -> new SucculentBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).sound(SoundType.GRASS).noCollision().instabreak());
  public static final NSBlockHolder<SucculentBlock> FOAMY_SUCCULENT = registerTransparentBlockWithoutItem("foamy_succulent",
          props -> new SucculentBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).sound(SoundType.GRASS).noCollision().instabreak());
  public static final NSBlockHolder<SucculentBlock> IMPERIAL_SUCCULENT = registerTransparentBlockWithoutItem("imperial_succulent",
          props -> new SucculentBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).sound(SoundType.GRASS).noCollision().instabreak());
  public static final NSBlockHolder<SucculentBlock> REGAL_SUCCULENT = registerTransparentBlockWithoutItem("regal_succulent",
          props -> new SucculentBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).sound(SoundType.GRASS).noCollision().instabreak());
  public static final NSBlockHolder<SucculentWallBlock> ORNATE_WALL_SUCCULENT = registerTransparentBlockWithoutItem("ornate_wall_succulent",
          props -> new SucculentWallBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).sound(SoundType.GRASS).noCollision().instabreak().overrideLootTable(ORNATE_SUCCULENT.get().getLootTable()));
  public static final NSBlockHolder<SucculentWallBlock> DROWSY_WALL_SUCCULENT = registerTransparentBlockWithoutItem("drowsy_wall_succulent",
          props -> new SucculentWallBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).sound(SoundType.GRASS).noCollision().instabreak().overrideLootTable(DROWSY_SUCCULENT.get().getLootTable()));
  public static final NSBlockHolder<SucculentWallBlock> AUREATE_WALL_SUCCULENT = registerTransparentBlockWithoutItem("aureate_wall_succulent",
          props -> new SucculentWallBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).sound(SoundType.GRASS).noCollision().instabreak().overrideLootTable(AUREATE_SUCCULENT.get().getLootTable()));
  public static final NSBlockHolder<SucculentWallBlock> SAGE_WALL_SUCCULENT = registerTransparentBlockWithoutItem("sage_wall_succulent",
          props -> new SucculentWallBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).sound(SoundType.GRASS).noCollision().instabreak().overrideLootTable(SAGE_SUCCULENT.get().getLootTable()));
  public static final NSBlockHolder<SucculentWallBlock> FOAMY_WALL_SUCCULENT = registerTransparentBlockWithoutItem("foamy_wall_succulent",
          props -> new SucculentWallBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).sound(SoundType.GRASS).noCollision().instabreak().overrideLootTable(FOAMY_SUCCULENT.get().getLootTable()));
  public static final NSBlockHolder<SucculentWallBlock> IMPERIAL_WALL_SUCCULENT = registerTransparentBlockWithoutItem("imperial_wall_succulent",
          props -> new SucculentWallBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).sound(SoundType.GRASS).noCollision().instabreak().overrideLootTable(IMPERIAL_SUCCULENT.get().getLootTable()));
  public static final NSBlockHolder<SucculentWallBlock> REGAL_WALL_SUCCULENT = registerTransparentBlockWithoutItem("regal_wall_succulent",
          props -> new SucculentWallBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).sound(SoundType.GRASS).noCollision().instabreak().overrideLootTable(REGAL_SUCCULENT.get().getLootTable()));
  public static final NSItemHolder<StandingAndWallBlockItem> ORNATE_SUCCULENT_ITEM = registerItem("ornate_succulent", props -> new StandingAndWallBlockItem(ORNATE_SUCCULENT.get(), ORNATE_WALL_SUCCULENT.get(), Direction.DOWN, props), () -> new Item.Properties().useBlockDescriptionPrefix());
  public static final NSItemHolder<StandingAndWallBlockItem> DROWSY_SUCCULENT_ITEM = registerItem("drowsy_succulent", props -> new StandingAndWallBlockItem(DROWSY_SUCCULENT.get(), DROWSY_WALL_SUCCULENT.get(), Direction.DOWN, props), () -> new Item.Properties().useBlockDescriptionPrefix());
  public static final NSItemHolder<StandingAndWallBlockItem> AUREATE_SUCCULENT_ITEM = registerItem("aureate_succulent", props -> new StandingAndWallBlockItem(AUREATE_SUCCULENT.get(), AUREATE_WALL_SUCCULENT.get(), Direction.DOWN, props), () -> new Item.Properties().useBlockDescriptionPrefix());
  public static final NSItemHolder<StandingAndWallBlockItem> SAGE_SUCCULENT_ITEM = registerItem("sage_succulent", props -> new StandingAndWallBlockItem(SAGE_SUCCULENT.get(), SAGE_WALL_SUCCULENT.get(), Direction.DOWN, props), () -> new Item.Properties().useBlockDescriptionPrefix());
  public static final NSItemHolder<StandingAndWallBlockItem> FOAMY_SUCCULENT_ITEM = registerItem("foamy_succulent", props -> new StandingAndWallBlockItem(FOAMY_SUCCULENT.get(), FOAMY_WALL_SUCCULENT.get(), Direction.DOWN, props), () -> new Item.Properties().useBlockDescriptionPrefix());
  public static final NSItemHolder<StandingAndWallBlockItem> IMPERIAL_SUCCULENT_ITEM = registerItem("imperial_succulent", props -> new StandingAndWallBlockItem(IMPERIAL_SUCCULENT.get(), IMPERIAL_WALL_SUCCULENT.get(), Direction.DOWN, props), () -> new Item.Properties().useBlockDescriptionPrefix());
  public static final NSItemHolder<StandingAndWallBlockItem> REGAL_SUCCULENT_ITEM = registerItem("regal_succulent", props -> new StandingAndWallBlockItem(REGAL_SUCCULENT.get(), REGAL_WALL_SUCCULENT.get(), Direction.DOWN, props), () -> new Item.Properties().useBlockDescriptionPrefix());

  public static final NSBlockHolder<ShiitakeMushroomPlantBlock> SHIITAKE_MUSHROOM = registerTransparentBlock("shiitake_mushroom", props -> new ShiitakeMushroomPlantBlock(
          props, NSConfiguredFeatures.HUGE_SHIITAKE_MUSHROOM), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).noCollision().randomTicks().instabreak().sound(SoundType.GRASS).lightLevel((state) -> 1)
                  .postProcess(NSRegistryHelper::postProcessSelf).pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<HugeMushroomBlock> SHIITAKE_MUSHROOM_BLOCK = registerBlock("shiitake_mushroom_block",
          props -> new HugeMushroomBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).instrument(NoteBlockInstrument.BASS).strength(0.2F).sound(SoundType.WOOD).ignitedByLava()
  );

  public static final NSBlockHolder<PolyporeBlock> GRAY_POLYPORE = registerTransparentBlock("gray_polypore", props -> new PolyporeBlock(
          props, NSConfiguredFeatures.GRAY_POLYPORE), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).noCollision().randomTicks().instabreak().sound(SoundType.GRASS).lightLevel((state) -> 1)
                  .postProcess(NSRegistryHelper::postProcessSelf).pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<HugeMushroomBlock> GRAY_POLYPORE_BLOCK = registerBlock("gray_polypore_block", props -> new HugeMushroomBlock(
          props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASS).strength(0.2F).sound(SoundType.WOOD).ignitedByLava()
  );


  public static final NSBlockHolder<FlowerPotBlock> POTTED_SCORCHED_GRASS = registerPottedBlock("potted_scorched_grass", SCORCHED_GRASS);
  public static final NSBlockHolder<FlowerPotBlock> POTTED_BEACH_GRASS = registerPottedBlock("potted_beach_grass", BEACH_GRASS);
  public static final NSBlockHolder<FlowerPotBlock> POTTED_SEDGE_GRASS = registerPottedBlock("potted_sedge_grass", SEDGE_GRASS);
  public static final NSBlockHolder<FlowerPotBlock> POTTED_FLAXEN_FERN = registerPottedBlock("potted_flaxen_fern", FLAXEN_FERN);
  public static final NSBlockHolder<FlowerPotBlock> POTTED_OAT_GRASS = registerPottedBlock("potted_oat_grass", OAT_GRASS);
  public static final NSBlockHolder<FlowerPotBlock> POTTED_MELIC_GRASS = registerPottedBlock("potted_melic_grass", MELIC_GRASS);
  public static final NSBlockHolder<FlowerPotBlock> POTTED_LUSH_FERN = registerPottedBlock("potted_lush_fern", LUSH_FERN);
  public static final NSBlockHolder<FlowerPotBlock> POTTED_FRIGID_GRASS = registerPottedBlock("potted_frigid_grass", FRIGID_GRASS);
  public static final NSBlockHolder<FlowerPotBlock> POTTED_GREEN_BEARBERRIES = registerPottedBlock("potted_green_bearberries", GREEN_BEARBERRIES);
  public static final NSBlockHolder<FlowerPotBlock> POTTED_RED_BEARBERRIES = registerPottedBlock("potted_red_bearberries", RED_BEARBERRIES);
  public static final NSBlockHolder<FlowerPotBlock> POTTED_PURPLE_BEARBERRIES = registerPottedBlock("potted_purple_bearberries", PURPLE_BEARBERRIES);
  public static final NSBlockHolder<FlowerPotBlock> POTTED_ORNATE_SUCCULENT = registerPottedBlock("potted_ornate_succulent", ORNATE_SUCCULENT);
  public static final NSBlockHolder<FlowerPotBlock> POTTED_DROWSY_SUCCULENT = registerPottedBlock("potted_drowsy_succulent", DROWSY_SUCCULENT);
  public static final NSBlockHolder<FlowerPotBlock> POTTED_AUREATE_SUCCULENT = registerPottedBlock("potted_aureate_succulent", AUREATE_SUCCULENT);
  public static final NSBlockHolder<FlowerPotBlock> POTTED_SAGE_SUCCULENT = registerPottedBlock("potted_sage_succulent", SAGE_SUCCULENT);
  public static final NSBlockHolder<FlowerPotBlock> POTTED_FOAMY_SUCCULENT = registerPottedBlock("potted_foamy_succulent", FOAMY_SUCCULENT);
  public static final NSBlockHolder<FlowerPotBlock> POTTED_IMPERIAL_SUCCULENT = registerPottedBlock("potted_imperial_succulent", IMPERIAL_SUCCULENT);
  public static final NSBlockHolder<FlowerPotBlock> POTTED_REGAL_SUCCULENT = registerPottedBlock("potted_regal_succulent", REGAL_SUCCULENT);
  public static final NSBlockHolder<FlowerPotBlock> POTTED_SHIITAKE_MUSHROOM = registerPottedBlock("potted_shiitake_mushroom", SHIITAKE_MUSHROOM);


  public static final FlowerSet LAVENDER = new FlowerSet("lavender", Items.PURPLE_DYE, FlowerSet.FlowerPreset.BIG_TALL);
  public static final FlowerSet BLEEDING_HEART = new FlowerSet("bleeding_heart", Items.PINK_DYE, FlowerSet.FlowerPreset.BIG_TALL);
  public static final FlowerSet BLUE_BULBS = new FlowerSet("blue_bulbs", Items.BLUE_DYE, FlowerSet.FlowerPreset.BIG_TALL);
  public static final FlowerSet CARNATION = new FlowerSet("carnation", Items.RED_DYE, FlowerSet.FlowerPreset.BIG_TALL);
  public static final FlowerSet GARDENIA = new FlowerSet("gardenia", Items.WHITE_DYE, FlowerSet.FlowerPreset.TALL);
  public static final FlowerSet SNAPDRAGON = new FlowerSet("snapdragon", Items.PINK_DYE, FlowerSet.FlowerPreset.TALL);
  public static final FlowerSet FOXGLOVE = new FlowerSet("foxglove", Items.PURPLE_DYE, FlowerSet.FlowerPreset.TALL);
  public static final FlowerSet BEGONIA = new FlowerSet("begonia", Items.ORANGE_DYE, FlowerSet.FlowerPreset.TALL);
  public static final FlowerSet MARIGOLD = new FlowerSet("marigold", Items.ORANGE_DYE, MobEffects.FIRE_RESISTANCE, FlowerSet.FlowerPreset.BIG_SMALL);
  public static final FlowerSet BLUEBELL = new FlowerSet("bluebell", Items.BLUE_DYE, MobEffects.HASTE, FlowerSet.FlowerPreset.BIG_SMALL);
  public static final FlowerSet TIGER_LILY = new FlowerSet("tiger_lily", Items.ORANGE_DYE, MobEffects.FIRE_RESISTANCE, FlowerSet.FlowerPreset.BIG_SMALL);
  public static final FlowerSet PURPLE_WILDFLOWER = new FlowerSet("purple_wildflower", Items.PURPLE_DYE, MobEffects.SLOW_FALLING, FlowerSet.FlowerPreset.BIG_SMALL);
  public static final FlowerSet YELLOW_WILDFLOWER = new FlowerSet("yellow_wildflower", Items.YELLOW_DYE, MobEffects.SLOW_FALLING, FlowerSet.FlowerPreset.BIG_SMALL);
  public static final FlowerSet RED_HEATHER = new FlowerSet("red_heather", Items.RED_DYE, MobEffects.FIRE_RESISTANCE, FlowerSet.FlowerPreset.BIG_SMALL);
  public static final FlowerSet WHITE_HEATHER = new FlowerSet("white_heather", Items.WHITE_DYE, MobEffects.FIRE_RESISTANCE, FlowerSet.FlowerPreset.BIG_SMALL);
  public static final FlowerSet PURPLE_HEATHER = new FlowerSet("purple_heather", Items.PURPLE_DYE, MobEffects.FIRE_RESISTANCE, FlowerSet.FlowerPreset.BIG_SMALL);
  public static final FlowerSet ANEMONE = new FlowerSet("anemone", Items.MAGENTA_DYE, MobEffects.RESISTANCE, FlowerSet.FlowerPreset.MID_SMALL);
  public static final FlowerSet DWARF_BLOSSOMS = new FlowerSet("dwarf_blossoms", Items.PINK_DYE, MobEffects.RESISTANCE, FlowerSet.FlowerPreset.MID_SMALL);
  public static final FlowerSet PROTEA = new FlowerSet("protea", Items.PINK_DYE, MobEffects.WATER_BREATHING, FlowerSet.FlowerPreset.MID_SMALL);
  public static final FlowerSet HIBISCUS = new FlowerSet("hibiscus", Items.RED_DYE, MobEffects.LUCK, FlowerSet.FlowerPreset.SMALL);
  public static final FlowerSet BLUE_IRIS = new FlowerSet("blue_iris", Items.LIGHT_BLUE_DYE, MobEffects.STRENGTH, FlowerSet.FlowerPreset.SMALL);
  public static final FlowerSet BLACK_IRIS = new FlowerSet("black_iris", Items.BLACK_DYE, MobEffects.STRENGTH, FlowerSet.FlowerPreset.SMALL);
  public static final FlowerSet RUBY_BLOSSOMS = new FlowerSet("ruby_blossoms", Items.RED_DYE, MobEffects.JUMP_BOOST, FlowerSet.FlowerPreset.BIG_SMALL);
  public static final FlowerSet SILVERBUSH = new FlowerSet("silverbush", FlowerSet.FlowerPreset.BIG_TALL);


  public static final NSBlockHolder<WaterFlowerbedBlock> HELVOLA = registerTransparentBlockWithoutItem("helvola", props -> new WaterFlowerbedBlock(props), () -> BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).randomTicks().noOcclusion().instabreak().friction(0.8F).sound(SoundType.LILY_PAD));
  public static final NSItemHolder<PlaceOnWaterBlockItem> HELVOLA_PAD_ITEM = registerItem("helvola_pad", props -> new PlaceOnWaterBlockItem(HELVOLA.get(), props), () -> new Item.Properties().overrideDescription("block." + MOD_ID + ".helvola"));
  public static final NSItemHolder<Item> HELVOLA_FLOWER_ITEM = registerItem("helvola_flower", props -> new Item(props), () -> new Item.Properties());

  public static final NSBlockHolder<LotusFlowerBlock> LOTUS_FLOWER = registerTransparentBlockWithoutItem("lotus_flower", props -> new LotusFlowerBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).pushReaction(PushReaction.DESTROY).randomTicks().noOcclusion().instabreak().sound(SoundType.LILY_PAD));
  public static final NSItemHolder<PlaceOnWaterBlockItem> LOTUS_FLOWER_ITEM = registerItem("lotus_flower", props -> new PlaceOnWaterBlockItem(LOTUS_FLOWER.get(), props), () -> new Item.Properties().useBlockDescriptionPrefix());
  public static final NSBlockHolder<LotusStemBlock> LOTUS_STEM = registerTransparentBlock("lotus_stem", props -> new LotusStemBlock(props, LOTUS_FLOWER.get()), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).pushReaction(PushReaction.DESTROY).noCollision().noOcclusion().instabreak().sound(SoundType.LILY_PAD));

  public static final NSBlockHolder<GrowingBranchingTrunkBlock> ALLUAUDIA = registerTransparentBlock("alluaudia", props -> new GrowingBranchingTrunkBlock(props), () -> BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.COLOR_GREEN).noOcclusion().sound(SoundType.VINE).destroyTime(.5f).strength(.5f).forceSolidOff());
  public static final NSBlockHolder<GrowingBranchingTrunkBlock> STRIPPED_ALLUAUDIA = registerTransparentBlock("stripped_alluaudia", props -> new GrowingBranchingTrunkBlock(props), () -> BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.WOOD).noOcclusion().sound(SoundType.VINE).destroyTime(.5f).strength(.5f).forceSolidOff());
  public static final NSBlockHolder<RotatedPillarBlock> STRIPPED_ALLUAUDIA_BUNDLE = registerTransparentBlock("stripped_alluaudia_bundle", props -> new RotatedPillarBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noOcclusion().sound(SoundType.VINE).destroyTime(.6f).strength(.6f));
  public static final NSBlockHolder<Block> ALLUAUDIA_BUNDLE = registerTransparentBlock("alluaudia_bundle", props -> new RotatedPillarBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).noOcclusion().sound(SoundType.VINE).destroyTime(.6f).strength(.6f));

  static {
    NSFlammables.add(ALLUAUDIA, 5, 5);
    NSFlammables.add(STRIPPED_ALLUAUDIA, 5, 5);
    NSFlammables.add(STRIPPED_ALLUAUDIA_BUNDLE, 5, 5);
    NSFlammables.add(ALLUAUDIA_BUNDLE, 5, 5);
  }

  public static final FoodProperties OLIVE_COMPONENT = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.4F).build();
  public static final NSItemHolder<Item> OLIVES = registerItem("olives", props -> new Item(props), () -> new Item.Properties().food(OLIVE_COMPONENT));

  public static final NSBlockHolder<RotatedPillarBlock> DESERT_TURNIP_ROOT_BLOCK = registerBlock("desert_turnip_root_block",
          props -> new RotatedPillarBlock(props), () -> BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(MapColor.PODZOL).strength(1.0F).sound(SoundType.ROOTS)
  );
  public static final NSBlockHolder<DesertTurnipBlock> DESERT_TURNIP_BLOCK = registerBlock("desert_turnip_block", props -> new DesertTurnipBlock(
      props), () -> BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(MapColor.ICE).strength(1.0F).sound(SoundType.ROOTS)
  );
  public static final NSBlockHolder<DesertTurnipStemBlock> DESERT_TURNIP_STEM = registerTransparentBlockWithoutItem("desert_turnip_stem",
          props -> new DesertTurnipStemBlock((DesertTurnipBlock) DESERT_TURNIP_BLOCK.get(), DESERT_TURNIP_ROOT_BLOCK.get(),
          props), () -> BlockBehaviour.Properties.of().noCollision().instabreak().randomTicks().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY));
  public static final FoodProperties DESERT_TURNIP_FOOD_COMPONENT = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.3F).build();
  public static final NSItemHolder<DesertTurnipItem> DESERT_TURNIP = registerItem("desert_turnip", props -> new DesertTurnipItem(DESERT_TURNIP_STEM.get(), props), () -> (new Item.Properties()).food(DESERT_TURNIP_FOOD_COMPONENT, Consumables.defaultFood().consumeSeconds(0.8F).build()));

  public static final NSBlockHolder<CheeseBlock> CHEESE_BLOCK = registerBlockWithoutItem("cheese_block", props -> new CheeseBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.SAND).strength(2.0F, 1.0F).sound(SoundType.AZALEA_LEAVES));
  public static final NSItemHolder<SolidBucketItem> CHEESE_BUCKET = registerItem("cheese_bucket", props -> new SolidBucketItem(CHEESE_BLOCK.get(), SoundEvents.BUCKET_EMPTY, props), () -> (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET));
  public static final NSItemHolder<CheeseArrowItem> CHEESE_ARROW =  registerItem("cheese_arrow", props -> new CheeseArrowItem(props), () -> new Item.Properties());
  public static final NSBlockHolder<MilkCauldronBlock> MILK_CAULDRON = registerBlockWithoutItem("milk_cauldron", props -> new MilkCauldronBlock(props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).overrideLootTable(Blocks.CAULDRON.getLootTable()));
  public static final NSBlockHolder<CheeseCauldronBlock> CHEESE_CAULDRON = registerBlockWithoutItem("cheese_cauldron", props -> new CheeseCauldronBlock(props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).overrideLootTable(Blocks.CAULDRON.getLootTable()));

  public static final FoodProperties STANDARD_PIZZA_COMPONENT = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.2F).build();
  public static final NSBlockHolder<PizzaBlock> PIZZA_BLOCK = registerBlockWithoutItem("pizza_block", props -> new PizzaBlock(props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
  public static final NSHolder<BlockEntityType<PizzaBlockEntity>> PIZZA_BLOCK_ENTITY_TYPE = BLOCK_ENTITIES.register("pizza_block_entity", () -> new BlockEntityType<>(PizzaBlockEntity::new, Set.of(PIZZA_BLOCK.get())));
  public static final NSItemHolder<PizzaItem> WHOLE_PIZZA = registerItem("whole_pizza", props -> new PizzaItem(PIZZA_BLOCK.get(), props), () -> new Item.Properties().stacksTo(1).food(STANDARD_PIZZA_COMPONENT));
  public static final NSItemHolder<PizzaItem> THREE_QUARTERS_PIZZA = registerItem("three_quarters_pizza", props -> new PizzaItem(PIZZA_BLOCK.get(), props), () -> new Item.Properties().stacksTo(1).food(STANDARD_PIZZA_COMPONENT));
  public static final NSItemHolder<PizzaItem> HALF_PIZZA = registerItem("half_pizza", props -> new PizzaItem(PIZZA_BLOCK.get(), props), () -> new Item.Properties().stacksTo(1).food(STANDARD_PIZZA_COMPONENT));
  public static final NSItemHolder<PizzaItem> QUARTER_PIZZA = registerItem("quarter_pizza", props -> new PizzaItem(PIZZA_BLOCK.get(), props), () -> new Item.Properties().stacksTo(1).food(STANDARD_PIZZA_COMPONENT));

  public static final NSItemHolder<Item> CHALK_POWDER = registerItem("chalk_powder", props -> new Item(props), () -> (new Item.Properties()));
  public static final NSItemHolder<Item> CALCITE_SHARD = registerItem("calcite_shard", props -> new Item(props), () -> new Item.Properties());
  public static final NSBlockHolder<AmethystClusterBlock> CALCITE_CLUSTER = registerBlock("calcite_cluster", props -> new AmethystClusterBlock(7, 3,
      props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).forceSolidOn().noOcclusion().randomTicks().sound(SoundType.CALCITE).strength(1.5F)
          .pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<AmethystClusterBlock> LARGE_CALCITE_BUD = registerBlock("large_calcite_bud",
          props -> new AmethystClusterBlock(4, 3, props), () -> BlockBehaviour.Properties.ofFullCopy(CALCITE_CLUSTER.get()).sound(SoundType.CALCITE).forceSolidOn().pushReaction(PushReaction.DESTROY)
  );
  public static final NSBlockHolder<AmethystClusterBlock> SMALL_CALCITE_BUD = registerBlock("small_calcite_bud",
          props -> new AmethystClusterBlock(3, 4, props), () -> BlockBehaviour.Properties.ofFullCopy(CALCITE_CLUSTER.get()).sound(SoundType.CALCITE).forceSolidOn().pushReaction(PushReaction.DESTROY)
  );

  public static final StoneSet TRAVERTINE = new StoneSet("travertine", MapColor.COLOR_LIGHT_GRAY, 1.5F, true, true, true, true);
  public static final StoneSet CHERT = new StoneSet("chert", MapColor.WOOD, .9F, false, true, false, true, true);
  public static final NSBlockHolder<DropExperienceBlock> CHERT_GOLD_ORE = registerBlock("chert_gold_ore", props -> new DropExperienceBlock(ConstantInt.of(0), props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_ORE).mapColor(MapColor.WOOD).strength(.9f));
  public static final NSBlockHolder<DropExperienceBlock> CHERT_IRON_ORE = registerBlock("chert_iron_ore", props -> new DropExperienceBlock(ConstantInt.of(0), props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE).mapColor(MapColor.WOOD).strength(.9f));
  public static final NSBlockHolder<DropExperienceBlock> CHERT_COAL_ORE = registerBlock("chert_coal_ore", props -> new DropExperienceBlock(UniformInt.of(0, 2), props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.COAL_ORE).mapColor(MapColor.WOOD).strength(.9f));
  public static final NSBlockHolder<DropExperienceBlock> CHERT_LAPIS_ORE = registerBlock("chert_lapis_ore", props -> new DropExperienceBlock(UniformInt.of(2, 5), props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.LAPIS_ORE).mapColor(MapColor.WOOD).strength(.9f));
  public static final NSBlockHolder<DropExperienceBlock> CHERT_DIAMOND_ORE = registerBlock("chert_diamond_ore", props -> new DropExperienceBlock(UniformInt.of(3, 7), props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE).mapColor(MapColor.WOOD).strength(.9f));
  public static final NSBlockHolder<RedStoneOreBlock> CHERT_REDSTONE_ORE = registerBlock("chert_redstone_ore", props -> new RedStoneOreBlock(props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_ORE).mapColor(MapColor.WOOD).strength(.6f));
  public static final NSBlockHolder<DropExperienceBlock> CHERT_EMERALD_ORE = registerBlock("chert_emerald_ore", props -> new DropExperienceBlock(UniformInt.of(3, 7), props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_ORE).mapColor(MapColor.WOOD).strength(.6f));
  public static final NSBlockHolder<DropExperienceBlock> CHERT_COPPER_ORE = registerBlock("chert_copper_ore", props -> new DropExperienceBlock(ConstantInt.of(0), props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE).mapColor(MapColor.WOOD).strength(.6f));

  public static final WoodSet REDWOOD = new WoodSet(
          "redwood",
          MapColor.TERRACOTTA_BROWN,
          MapColor.COLOR_RED,
          WoodSet.WoodPreset.FROSTABLE,
          false,
          new TreeGrower(NaturesSpirit.MOD_ID + "_redwood", Optional.of(NSConfiguredFeatures.LARGE_REDWOOD_TREE), Optional.of(NSConfiguredFeatures.REDWOOD_TREE), Optional.empty())
  );

  public static final WoodSet SUGI = new WoodSet(
          "sugi",
          MapColor.DEEPSLATE,
          MapColor.DIRT,
          WoodSet.WoodPreset.FANCY,
          true,
          new TreeGrower(NaturesSpirit.MOD_ID + "_sugi", Optional.empty(), Optional.of(NSConfiguredFeatures.SUGI_TREE), Optional.empty())
  );

  public static final Supplier<BlockSetType> PAPER_BLOCK_SET = Suppliers.memoize(() -> BlockSetType.register(new BlockSetType(MOD_ID + ":paper", true, true, true, BlockSetType.PressurePlateSensitivity.EVERYTHING, SoundType.CHERRY_WOOD, SoundEvents.CHERRY_WOOD_DOOR_CLOSE, SoundEvents.CHERRY_WOOD_DOOR_OPEN, SoundEvents.CHERRY_WOOD_TRAPDOOR_CLOSE, SoundEvents.CHERRY_WOOD_TRAPDOOR_OPEN, SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_ON, SoundEvents.CHERRY_WOOD_BUTTON_CLICK_OFF, SoundEvents.CHERRY_WOOD_BUTTON_CLICK_ON)));
  public static final Supplier<WoodType> PAPER_WOOD_TYPE = Suppliers.memoize(() -> WoodType.register(new WoodType(MOD_ID + ":paper", PAPER_BLOCK_SET.get())));
  public static final NSBlockHolder<Block> PAPER_BLOCK = registerBlock("paper_block", props -> new Block(props), () -> BlockBehaviour.Properties.ofFullCopy(SUGI.getPlanks().get()));
  public static final NSBlockHolder<IronBarsBlock> PAPER_PANEL = registerBlock("paper_panel", props -> new IronBarsBlock(props), () -> BlockBehaviour.Properties.ofFullCopy(SUGI.getPlanks().get()).noOcclusion());
  public static final NSBlockHolder<DoorBlock> PAPER_DOOR = registerTransparentBlock("paper_door", props -> new DoorBlock(PAPER_BLOCK_SET.get(), props), () -> BlockBehaviour.Properties.ofFullCopy(SUGI.getDoor().get()));
  public static final NSBlockHolder<TrapDoorBlock> PAPER_TRAPDOOR = registerTransparentBlock("paper_trapdoor", props -> new TrapDoorBlock(PAPER_BLOCK_SET.get(), props), () -> BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(3.0f).sound(SoundType.WOOD).isValidSpawn(NSRegistryHelper::never).noOcclusion());
  public static final NSBlockHolder<Block> FRAMED_PAPER_BLOCK = registerBlock("framed_paper_block", props -> new Block(props), () -> BlockBehaviour.Properties.ofFullCopy(SUGI.getPlanks().get()));
  public static final NSBlockHolder<IronBarsBlock> FRAMED_PAPER_PANEL = registerBlock("framed_paper_panel", props -> new IronBarsBlock(props), () -> BlockBehaviour.Properties.ofFullCopy(SUGI.getPlanks().get()).noOcclusion());
  public static final NSBlockHolder<DoorBlock> FRAMED_PAPER_DOOR = registerTransparentBlock("framed_paper_door", props -> new DoorBlock(PAPER_BLOCK_SET.get(), props), () -> BlockBehaviour.Properties.ofFullCopy(SUGI.getDoor().get()));
  public static final NSBlockHolder<TrapDoorBlock> FRAMED_PAPER_TRAPDOOR = registerTransparentBlock("framed_paper_trapdoor", props -> new TrapDoorBlock(PAPER_BLOCK_SET.get(), props), () -> BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(3.0f).sound(SoundType.WOOD).isValidSpawn(NSRegistryHelper::never).noOcclusion());
  public static final NSBlockHolder<GlazedTerracottaBlock> BLOOMING_PAPER_BLOCK = registerBlock("blooming_paper_block", props -> new GlazedTerracottaBlock(props), () -> BlockBehaviour.Properties.ofFullCopy(SUGI.getPlanks().get()));
  public static final NSBlockHolder<IronBarsBlock> BLOOMING_PAPER_PANEL = registerBlock("blooming_paper_panel", props -> new IronBarsBlock(props), () -> BlockBehaviour.Properties.ofFullCopy(SUGI.getPlanks().get()).noOcclusion());
  public static final NSBlockHolder<DoorBlock> BLOOMING_PAPER_DOOR = registerTransparentBlock("blooming_paper_door", props -> new DoorBlock(PAPER_BLOCK_SET.get(), props), () -> BlockBehaviour.Properties.ofFullCopy(SUGI.getDoor().get()));
  public static final NSBlockHolder<TrapDoorBlock> BLOOMING_PAPER_TRAPDOOR = registerTransparentBlock("blooming_paper_trapdoor", props -> new TrapDoorBlock(PAPER_BLOCK_SET.get(), props), () -> BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(3.0f).sound(SoundType.WOOD).isValidSpawn(NSRegistryHelper::never).noOcclusion());
  public static final NSBlockHolder<StandingSignBlock> PAPER_SIGN = registerBlockWithoutItem("paper_sign", props -> new StandingSignBlock(PAPER_WOOD_TYPE.get(), props), () -> BlockBehaviour.Properties.ofFullCopy(SUGI.getSign().get()));
  public static final NSBlockHolder<WallSignBlock> PAPER_WALL_SIGN = registerBlockWithoutItem("paper_wall_sign", props -> new WallSignBlock(PAPER_WOOD_TYPE.get(), props), () -> BlockBehaviour.Properties.ofFullCopy(SUGI.getSign().get()).overrideLootTable(PAPER_SIGN.get().getLootTable()));
  public static final NSBlockHolder<CeilingHangingSignBlock> PAPER_HANGING_SIGN = registerBlockWithoutItem("paper_hanging_sign", props -> new CeilingHangingSignBlock(PAPER_WOOD_TYPE.get(), props), () -> BlockBehaviour.Properties.ofFullCopy(SUGI.getHangingSign().get()));
  public static final NSBlockHolder<WallHangingSignBlock> PAPER_WALL_HANGING_SIGN = registerBlockWithoutItem("paper_wall_hanging_sign", props -> new WallHangingSignBlock(PAPER_WOOD_TYPE.get(), props), () -> BlockBehaviour.Properties.ofFullCopy(SUGI.getHangingSign().get()).overrideLootTable(PAPER_HANGING_SIGN.get().getLootTable()));
  public static final NSItemHolder<SignItem> PAPER_SIGN_ITEM = registerItem("paper_sign", props -> new SignItem(PAPER_SIGN.get(), PAPER_WALL_SIGN.get(), props), () -> new Item.Properties().stacksTo(16).useBlockDescriptionPrefix());
  public static final NSItemHolder<HangingSignItem> PAPER_HANGING_SIGN_ITEM = registerItem("paper_hanging_sign", props -> new HangingSignItem(PAPER_HANGING_SIGN.get(), PAPER_WALL_HANGING_SIGN.get(), props), () -> new Item.Properties().stacksTo(16).useBlockDescriptionPrefix());

  public static final WoodSet WISTERIA = new WoodSet(
      "wisteria",
      MapColor.COLOR_GRAY,
      MapColor.TERRACOTTA_WHITE,
      WoodSet.WoodPreset.WISTERIA,
      true,
          new TreeGrower(NaturesSpirit.MOD_ID + "_wisteria", Optional.empty(), Optional.of(NSConfiguredFeatures.WHITE_WISTERIA_TREE), Optional.empty())
  );
  public static final WoodSet FIR = new WoodSet(
      "fir",
      MapColor.COLOR_GRAY,
      MapColor.DIRT,
      WoodSet.WoodPreset.FROSTABLE,
      false,
          new TreeGrower(NaturesSpirit.MOD_ID + "_fir", Optional.empty(), Optional.of(NSConfiguredFeatures.FIR_TREE), Optional.empty())
  );
  public static final WoodSet WILLOW = new WoodSet(
      "willow",
      MapColor.TERRACOTTA_BLACK,
      MapColor.TERRACOTTA_BROWN,
      WoodSet.WoodPreset.WILLOW,
      false,
          new TreeGrower(NaturesSpirit.MOD_ID + "_willow", Optional.empty(), Optional.of(NSConfiguredFeatures.WILLOW_TREE), Optional.empty())
  );
  public static final WoodSet ASPEN = new WoodSet(
      "aspen",
      MapColor.WOOL,
      MapColor.SAND,
      WoodSet.WoodPreset.ASPEN,
      false,
          new TreeGrower(NaturesSpirit.MOD_ID + "_aspen", .5f, Optional.empty(), Optional.empty(), Optional.of(NSConfiguredFeatures.ASPEN_TREE),
                  Optional.of(NSConfiguredFeatures.YELLOW_ASPEN_TREE), Optional.empty(), Optional.empty())
  );
  public static final WoodSet MAPLE = new WoodSet(
      "maple",
      MapColor.PODZOL,
      MapColor.COLOR_ORANGE,
      WoodSet.WoodPreset.MAPLE,
      false,
          new TreeGrower(NaturesSpirit.MOD_ID + "_maple", Optional.empty(), Optional.of(NSConfiguredFeatures.RED_MAPLE_TREE), Optional.empty())
  );
  public static final WoodSet CYPRESS = new WoodSet(
      "cypress",
      MapColor.PODZOL,
      MapColor.WOOD,
      WoodSet.WoodPreset.DEFAULT,
      false,
          new TreeGrower(NaturesSpirit.MOD_ID + "_cypress", Optional.empty(), Optional.of(NSConfiguredFeatures.CYPRESS_TREE), Optional.empty())
  );
  public static final WoodSet OLIVE = new WoodSet(
      "olive",
      MapColor.SAND,
      MapColor.GRASS,
      WoodSet.WoodPreset.DEFAULT,
      false,
          new TreeGrower(NaturesSpirit.MOD_ID + "_olive", Optional.of(NSConfiguredFeatures.OLIVE_TREE), Optional.empty(), Optional.empty())
  );
  public static final NSBlockHolder<OliveBranchBlock> OLIVE_BRANCH = registerTransparentBlock("olive_branch", props -> new OliveBranchBlock(props), () -> BlockBehaviour.Properties.of().instabreak().noCollision().randomTicks().sound(SoundType.GRASS).noOcclusion().pushReaction(PushReaction.DESTROY));
  public static final WoodSet JOSHUA = new WoodSet(
      "joshua",
      MapColor.GRASS,
      MapColor.DEEPSLATE,
      WoodSet.WoodPreset.JOSHUA,
      true,
          new TreeGrower(NaturesSpirit.MOD_ID + "_joshua", Optional.empty(), Optional.of(NSConfiguredFeatures.JOSHUA_TREE), Optional.empty())
  );
  public static final WoodSet GHAF = new WoodSet(
      "ghaf",
      MapColor.COLOR_LIGHT_GRAY,
      MapColor.COLOR_BROWN,
      WoodSet.WoodPreset.SANDY,
      false,
          new TreeGrower(NaturesSpirit.MOD_ID + "_ghaf", Optional.empty(), Optional.of(NSConfiguredFeatures.GHAF_TREE), Optional.empty())
  );
  public static final NSBlockHolder<Block> XERIC_THATCH = registerBlock("xeric_thatch", props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).strength(0.4F).sound(SoundType.GRASS));
  public static final NSBlockHolder<StairBlock> XERIC_THATCH_STAIRS = registerBlock("xeric_thatch_stairs", props -> new StairBlock(XERIC_THATCH.get().defaultBlockState(), props), () -> BlockBehaviour.Properties.ofFullCopy(XERIC_THATCH.get()));
  public static final NSBlockHolder<SlabBlock> XERIC_THATCH_SLAB = registerBlock("xeric_thatch_slab", props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).sound(SoundType.GRASS).strength(0.4f));
  public static final NSBlockHolder<CarpetBlock> XERIC_THATCH_CARPET = registerBlock("xeric_thatch_carpet", props -> new CarpetBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).strength(0F).pushReaction(PushReaction.DESTROY).sound(SoundType.GRASS));
  public static final WoodSet PALO_VERDE = new WoodSet(
      "palo_verde",
      MapColor.COLOR_YELLOW,
      MapColor.GLOW_LICHEN,
      WoodSet.WoodPreset.SANDY,
      false,
          new TreeGrower(NaturesSpirit.MOD_ID + "_palo_verde", Optional.empty(), Optional.of(NSConfiguredFeatures.PALO_VERDE_TREE), Optional.empty())
  );
  public static final WoodSet COCONUT = new WoodSet(
      "coconut",
      MapColor.CRIMSON_STEM,
      MapColor.COLOR_BROWN,
      WoodSet.WoodPreset.NO_SAPLING,
      true,
          new TreeGrower(NaturesSpirit.MOD_ID + "_coconut", Optional.empty(), Optional.of(NSConfiguredFeatures.COCONUT_TREE), Optional.empty())
  );
  public static final NSBlockHolder<Block> COCONUT_THATCH = registerBlock("coconut_thatch", props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).strength(0.4F).sound(SoundType.GRASS));
  public static final NSBlockHolder<StairBlock> COCONUT_THATCH_STAIRS = registerBlock("coconut_thatch_stairs", props -> new StairBlock(COCONUT_THATCH.get().defaultBlockState(), props), () -> BlockBehaviour.Properties.ofFullCopy(COCONUT_THATCH.get()));
  public static final NSBlockHolder<SlabBlock> COCONUT_THATCH_SLAB = registerBlock("coconut_thatch_slab", props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.GRASS).strength(0.4f));
  public static final NSBlockHolder<CarpetBlock> COCONUT_THATCH_CARPET = registerBlock("coconut_thatch_carpet", props -> new CarpetBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).strength(0F).pushReaction(PushReaction.DESTROY).sound(SoundType.GRASS));
  public static final NSBlockHolder<CoconutBlock> COCONUT_BLOCK = registerTransparentBlock("coconut", props -> new CoconutBlock(props), () -> BlockBehaviour.Properties.of().strength(1.0F).sound(SoundType.GRASS).noOcclusion().pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<SproutingCoconutBlock> COCONUT_SPROUT = registerTransparentBlock("coconut_sprout", props -> new SproutingCoconutBlock(new TreeGrower(NaturesSpirit.MOD_ID + "_coconut", Optional.empty(), Optional.of(NSConfiguredFeatures.COCONUT_TREE), Optional.empty()), props), () -> BlockBehaviour.Properties.of().strength(1.0F).sound(SoundType.GRASS).noOcclusion().pushReaction(PushReaction.DESTROY));
  public static final FoodProperties COCONUT_COMPONENT = (new FoodProperties.Builder()).nutrition(6).saturationModifier(0.6F).build();
  public static final NSItemHolder<Item> COCONUT_SHELL = registerItem("coconut_shell", props -> new Item(props), () -> new Item.Properties());
  public static final NSItemHolder<CoconutHalfItem> COCONUT_HALF = registerItem("coconut_half", props -> new CoconutHalfItem(props, COCONUT_SHELL.get()), () -> new Item.Properties().food(COCONUT_COMPONENT));
  public static final WoodSet CEDAR = new WoodSet(
      "cedar",
      MapColor.TERRACOTTA_MAGENTA,
      MapColor.COLOR_GRAY,
      WoodSet.WoodPreset.DEFAULT,
      false,
          new TreeGrower(NaturesSpirit.MOD_ID + "_cedar", Optional.empty(), Optional.of(NSConfiguredFeatures.CEDAR_TREE), Optional.empty())
  );
  public static final WoodSet LARCH = new WoodSet(
      "larch",
      MapColor.COLOR_BLUE,
      MapColor.COLOR_LIGHT_GRAY,
      WoodSet.WoodPreset.DEFAULT,
      false,
          new TreeGrower(NaturesSpirit.MOD_ID + "_larch", Optional.empty(), Optional.of(NSConfiguredFeatures.LARCH_TREE), Optional.empty())
  );
  public static final NSBlockHolder<Block> EVERGREEN_THATCH = registerBlock("evergreen_thatch", props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).strength(0.4F).sound(SoundType.GRASS));
  public static final NSBlockHolder<StairBlock> EVERGREEN_THATCH_STAIRS = registerBlock("evergreen_thatch_stairs", props -> new StairBlock(EVERGREEN_THATCH.get().defaultBlockState(), props), () -> BlockBehaviour.Properties.ofFullCopy(EVERGREEN_THATCH.get()));
  public static final NSBlockHolder<SlabBlock> EVERGREEN_THATCH_SLAB = registerBlock("evergreen_thatch_slab", props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.GRASS).strength(0.4f));
  public static final NSBlockHolder<CarpetBlock> EVERGREEN_THATCH_CARPET = registerBlock("evergreen_thatch_carpet", props -> new CarpetBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).strength(0F).pushReaction(PushReaction.DESTROY).sound(SoundType.GRASS));
  public static final WoodSet MAHOGANY = new WoodSet(
      "mahogany",
      MapColor.COLOR_BROWN,
      MapColor.COLOR_LIGHT_GRAY,
      WoodSet.WoodPreset.DEFAULT,
      true,
          new TreeGrower(NaturesSpirit.MOD_ID + "_mahogany", Optional.of(NSConfiguredFeatures.MAHOGANY_TREE), Optional.empty(), Optional.empty())
  );
  public static final WoodSet SAXAUL = new WoodSet(
      "saxaul",
      MapColor.COLOR_LIGHT_GRAY,
      MapColor.COLOR_LIGHT_GRAY,
      WoodSet.WoodPreset.SANDY,
      false,
          new TreeGrower(NaturesSpirit.MOD_ID + "_saxaul", Optional.empty(), Optional.of(NSConfiguredFeatures.SAXAUL_TREE), Optional.empty())
  );

  public static final NSBlockHolder<PaperLanternBlock> PAPER_LANTERN = registerTransparentBlock("paper_lantern", props -> new PaperLanternBlock(
          props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<PaperLanternBlock> WHITE_PAPER_LANTERN = registerTransparentBlock("white_paper_lantern", props -> new PaperLanternBlock(
          props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<PaperLanternBlock> LIGHT_GRAY_PAPER_LANTERN = registerTransparentBlock("light_gray_paper_lantern", props -> new PaperLanternBlock(
          props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<PaperLanternBlock> GRAY_PAPER_LANTERN = registerTransparentBlock("gray_paper_lantern", props -> new PaperLanternBlock(
          props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<PaperLanternBlock> BLACK_PAPER_LANTERN = registerTransparentBlock("black_paper_lantern", props -> new PaperLanternBlock(
          props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<PaperLanternBlock> BROWN_PAPER_LANTERN = registerTransparentBlock("brown_paper_lantern", props -> new PaperLanternBlock(
          props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<PaperLanternBlock> RED_PAPER_LANTERN = registerTransparentBlock("red_paper_lantern", props -> new PaperLanternBlock(
          props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<PaperLanternBlock> ORANGE_PAPER_LANTERN = registerTransparentBlock("orange_paper_lantern", props -> new PaperLanternBlock(
          props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<PaperLanternBlock> YELLOW_PAPER_LANTERN = registerTransparentBlock("yellow_paper_lantern", props -> new PaperLanternBlock(
          props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<PaperLanternBlock> LIME_PAPER_LANTERN = registerTransparentBlock("lime_paper_lantern", props -> new PaperLanternBlock(
          props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<PaperLanternBlock> GREEN_PAPER_LANTERN = registerTransparentBlock("green_paper_lantern", props -> new PaperLanternBlock(
          props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<PaperLanternBlock> BLUE_PAPER_LANTERN = registerTransparentBlock("blue_paper_lantern", props -> new PaperLanternBlock(
          props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<PaperLanternBlock> LIGHT_BLUE_PAPER_LANTERN = registerTransparentBlock("light_blue_paper_lantern", props -> new PaperLanternBlock(
          props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<PaperLanternBlock> CYAN_PAPER_LANTERN = registerTransparentBlock("cyan_paper_lantern", props -> new PaperLanternBlock(
          props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<PaperLanternBlock> PURPLE_PAPER_LANTERN = registerTransparentBlock("purple_paper_lantern", props -> new PaperLanternBlock(
          props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<PaperLanternBlock> MAGENTA_PAPER_LANTERN = registerTransparentBlock("magenta_paper_lantern", props -> new PaperLanternBlock(
          props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY));
  public static final NSBlockHolder<PaperLanternBlock> PINK_PAPER_LANTERN = registerTransparentBlock("pink_paper_lantern", props -> new PaperLanternBlock(
          props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY));

  public static final NSBlockHolder<Block> KAOLIN = registerBlock(
      "kaolin",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)
  );
  public static final NSBlockHolder<Block> WHITE_KAOLIN = registerBlock(
      "white_kaolin",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)
  );
  public static final NSBlockHolder<Block> LIGHT_GRAY_KAOLIN = registerBlock(
      "light_gray_kaolin",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)
  );
  public static final NSBlockHolder<Block> GRAY_KAOLIN = registerBlock(
      "gray_kaolin",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)
  );
  public static final NSBlockHolder<Block> BLACK_KAOLIN = registerBlock(
      "black_kaolin",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)
  );
  public static final NSBlockHolder<Block> BROWN_KAOLIN = registerBlock(
      "brown_kaolin",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)
  );
  public static final NSBlockHolder<Block> RED_KAOLIN = registerBlock(
      "red_kaolin",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)
  );
  public static final NSBlockHolder<Block> ORANGE_KAOLIN = registerBlock(
      "orange_kaolin",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)
  );
  public static final NSBlockHolder<Block> YELLOW_KAOLIN = registerBlock(
      "yellow_kaolin",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)
  );
  public static final NSBlockHolder<Block> LIME_KAOLIN = registerBlock(
      "lime_kaolin",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)
  );
  public static final NSBlockHolder<Block> GREEN_KAOLIN = registerBlock(
      "green_kaolin",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)
  );
  public static final NSBlockHolder<Block> CYAN_KAOLIN = registerBlock(
      "cyan_kaolin",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)
  );
  public static final NSBlockHolder<Block> LIGHT_BLUE_KAOLIN = registerBlock(
      "light_blue_kaolin",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)
  );
  public static final NSBlockHolder<Block> BLUE_KAOLIN = registerBlock(
      "blue_kaolin",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)
  );
  public static final NSBlockHolder<Block> PURPLE_KAOLIN = registerBlock(
      "purple_kaolin",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)
  );
  public static final NSBlockHolder<Block> MAGENTA_KAOLIN = registerBlock(
      "magenta_kaolin",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)
  );
  public static final NSBlockHolder<Block> PINK_KAOLIN = registerBlock(
      "pink_kaolin",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)
  );

  public static final NSBlockHolder<StairBlock> KAOLIN_STAIRS = registerBlock(
          "kaolin_stairs",
          props -> new StairBlock(KAOLIN.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)
  );
  public static final NSBlockHolder<StairBlock> WHITE_KAOLIN_STAIRS = registerBlock(
          "white_kaolin_stairs",
          props -> new StairBlock(WHITE_KAOLIN.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)
  );
  public static final NSBlockHolder<StairBlock> LIGHT_GRAY_KAOLIN_STAIRS = registerBlock(
          "light_gray_kaolin_stairs",
          props -> new StairBlock(LIGHT_GRAY_KAOLIN.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)
  );
  public static final NSBlockHolder<StairBlock> GRAY_KAOLIN_STAIRS = registerBlock(
          "gray_kaolin_stairs",
          props -> new StairBlock(GRAY_KAOLIN.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)
  );
  public static final NSBlockHolder<StairBlock> BLACK_KAOLIN_STAIRS = registerBlock(
          "black_kaolin_stairs",
          props -> new StairBlock(BLACK_KAOLIN.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)
  );
  public static final NSBlockHolder<StairBlock> BROWN_KAOLIN_STAIRS = registerBlock(
          "brown_kaolin_stairs",
          props -> new StairBlock(BROWN_KAOLIN.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)
  );
  public static final NSBlockHolder<StairBlock> RED_KAOLIN_STAIRS = registerBlock(
          "red_kaolin_stairs",
          props -> new StairBlock(RED_KAOLIN.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)
  );
  public static final NSBlockHolder<StairBlock> ORANGE_KAOLIN_STAIRS = registerBlock(
          "orange_kaolin_stairs",
          props -> new StairBlock(ORANGE_KAOLIN.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)
  );
  public static final NSBlockHolder<StairBlock> YELLOW_KAOLIN_STAIRS = registerBlock(
          "yellow_kaolin_stairs",
          props -> new StairBlock(YELLOW_KAOLIN.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)
  );
  public static final NSBlockHolder<StairBlock> LIME_KAOLIN_STAIRS = registerBlock(
          "lime_kaolin_stairs",
          props -> new StairBlock(LIME_KAOLIN.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)
  );
  public static final NSBlockHolder<StairBlock> GREEN_KAOLIN_STAIRS = registerBlock(
          "green_kaolin_stairs",
          props -> new StairBlock(GREEN_KAOLIN.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)
  );
  public static final NSBlockHolder<StairBlock> CYAN_KAOLIN_STAIRS = registerBlock(
          "cyan_kaolin_stairs",
          props -> new StairBlock(CYAN_KAOLIN.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)
  );
  public static final NSBlockHolder<StairBlock> LIGHT_BLUE_KAOLIN_STAIRS = registerBlock(
          "light_blue_kaolin_stairs",
          props -> new StairBlock(LIGHT_BLUE_KAOLIN.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)
  );
  public static final NSBlockHolder<StairBlock> BLUE_KAOLIN_STAIRS = registerBlock(
          "blue_kaolin_stairs",
          props -> new StairBlock(BLUE_KAOLIN.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)
  );
  public static final NSBlockHolder<StairBlock> PURPLE_KAOLIN_STAIRS = registerBlock(
          "purple_kaolin_stairs",
          props -> new StairBlock(PURPLE_KAOLIN.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)
  );
  public static final NSBlockHolder<StairBlock> MAGENTA_KAOLIN_STAIRS = registerBlock(
          "magenta_kaolin_stairs",
          props -> new StairBlock(MAGENTA_KAOLIN.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)
  );
  public static final NSBlockHolder<StairBlock> PINK_KAOLIN_STAIRS = registerBlock(
          "pink_kaolin_stairs",
          props -> new StairBlock(PINK_KAOLIN.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)
  );

  public static final NSBlockHolder<SlabBlock> KAOLIN_SLAB = registerBlock(
      "kaolin_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<SlabBlock> WHITE_KAOLIN_SLAB = registerBlock(
      "white_kaolin_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<SlabBlock> LIGHT_GRAY_KAOLIN_SLAB = registerBlock(
      "light_gray_kaolin_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<SlabBlock> GRAY_KAOLIN_SLAB = registerBlock(
      "gray_kaolin_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<SlabBlock> BLACK_KAOLIN_SLAB = registerBlock(
      "black_kaolin_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<SlabBlock> BROWN_KAOLIN_SLAB = registerBlock(
      "brown_kaolin_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<SlabBlock> RED_KAOLIN_SLAB = registerBlock(
      "red_kaolin_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<SlabBlock> ORANGE_KAOLIN_SLAB = registerBlock(
      "orange_kaolin_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<SlabBlock> YELLOW_KAOLIN_SLAB = registerBlock(
      "yellow_kaolin_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<SlabBlock> LIME_KAOLIN_SLAB = registerBlock(
      "lime_kaolin_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<SlabBlock> GREEN_KAOLIN_SLAB = registerBlock(
      "green_kaolin_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<SlabBlock> CYAN_KAOLIN_SLAB = registerBlock(
      "cyan_kaolin_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<SlabBlock> LIGHT_BLUE_KAOLIN_SLAB = registerBlock(
      "light_blue_kaolin_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<SlabBlock> BLUE_KAOLIN_SLAB = registerBlock(
      "blue_kaolin_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<SlabBlock> PURPLE_KAOLIN_SLAB = registerBlock(
      "purple_kaolin_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<SlabBlock> MAGENTA_KAOLIN_SLAB = registerBlock(
      "magenta_kaolin_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<SlabBlock> PINK_KAOLIN_SLAB = registerBlock(
      "pink_kaolin_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<Block> KAOLIN_BRICKS = registerBlock(
      "kaolin_bricks",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<Block> WHITE_KAOLIN_BRICKS = registerBlock(
      "white_kaolin_bricks",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<Block> LIGHT_GRAY_KAOLIN_BRICKS = registerBlock(
      "light_gray_kaolin_bricks",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<Block> GRAY_KAOLIN_BRICKS = registerBlock(
      "gray_kaolin_bricks",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<Block> BLACK_KAOLIN_BRICKS = registerBlock(
      "black_kaolin_bricks",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<Block> BROWN_KAOLIN_BRICKS = registerBlock(
      "brown_kaolin_bricks",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<Block> RED_KAOLIN_BRICKS = registerBlock(
      "red_kaolin_bricks",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<Block> ORANGE_KAOLIN_BRICKS = registerBlock(
      "orange_kaolin_bricks",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<Block> YELLOW_KAOLIN_BRICKS = registerBlock(
      "yellow_kaolin_bricks",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<Block> LIME_KAOLIN_BRICKS = registerBlock(
      "lime_kaolin_bricks",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<Block> GREEN_KAOLIN_BRICKS = registerBlock(
      "green_kaolin_bricks",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<Block> CYAN_KAOLIN_BRICKS = registerBlock(
      "cyan_kaolin_bricks",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<Block> LIGHT_BLUE_KAOLIN_BRICKS = registerBlock(
      "light_blue_kaolin_bricks",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<Block> BLUE_KAOLIN_BRICKS = registerBlock(
      "blue_kaolin_bricks",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<Block> PURPLE_KAOLIN_BRICKS = registerBlock(
      "purple_kaolin_bricks",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<Block> MAGENTA_KAOLIN_BRICKS = registerBlock(
      "magenta_kaolin_bricks",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<Block> PINK_KAOLIN_BRICKS = registerBlock(
      "pink_kaolin_bricks",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<StairBlock> KAOLIN_BRICK_STAIRS = registerBlock(
          "kaolin_brick_stairs",
          props -> new StairBlock(KAOLIN_BRICKS.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<StairBlock> WHITE_KAOLIN_BRICK_STAIRS = registerBlock(
          "white_kaolin_brick_stairs",
          props -> new StairBlock(WHITE_KAOLIN_BRICKS.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<StairBlock> LIGHT_GRAY_KAOLIN_BRICK_STAIRS = registerBlock(
          "light_gray_kaolin_brick_stairs",
          props -> new StairBlock(LIGHT_GRAY_KAOLIN_BRICKS.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<StairBlock> GRAY_KAOLIN_BRICK_STAIRS = registerBlock(
          "gray_kaolin_brick_stairs",
          props -> new StairBlock(GRAY_KAOLIN_BRICKS.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<StairBlock> BLACK_KAOLIN_BRICK_STAIRS = registerBlock(
          "black_kaolin_brick_stairs",
          props -> new StairBlock(BLACK_KAOLIN_BRICKS.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<StairBlock> BROWN_KAOLIN_BRICK_STAIRS = registerBlock(
          "brown_kaolin_brick_stairs",
          props -> new StairBlock(BROWN_KAOLIN_BRICKS.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<StairBlock> RED_KAOLIN_BRICK_STAIRS = registerBlock(
          "red_kaolin_brick_stairs",
          props -> new StairBlock(RED_KAOLIN_BRICKS.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<StairBlock> ORANGE_KAOLIN_BRICK_STAIRS = registerBlock(
          "orange_kaolin_brick_stairs",
          props -> new StairBlock(ORANGE_KAOLIN_BRICKS.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<StairBlock> YELLOW_KAOLIN_BRICK_STAIRS = registerBlock(
          "yellow_kaolin_brick_stairs",
          props -> new StairBlock(YELLOW_KAOLIN_BRICKS.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<StairBlock> LIME_KAOLIN_BRICK_STAIRS = registerBlock(
          "lime_kaolin_brick_stairs",
          props -> new StairBlock(LIME_KAOLIN_BRICKS.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<StairBlock> GREEN_KAOLIN_BRICK_STAIRS = registerBlock(
          "green_kaolin_brick_stairs",
          props -> new StairBlock(GREEN_KAOLIN_BRICKS.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<StairBlock> CYAN_KAOLIN_BRICK_STAIRS = registerBlock(
          "cyan_kaolin_brick_stairs",
          props -> new StairBlock(CYAN_KAOLIN_BRICKS.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<StairBlock> LIGHT_BLUE_KAOLIN_BRICK_STAIRS = registerBlock(
          "light_blue_kaolin_brick_stairs",
          props -> new StairBlock(LIGHT_BLUE_KAOLIN_BRICKS.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<StairBlock> BLUE_KAOLIN_BRICK_STAIRS = registerBlock(
          "blue_kaolin_brick_stairs",
          props -> new StairBlock(BLUE_KAOLIN_BRICKS.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<StairBlock> PURPLE_KAOLIN_BRICK_STAIRS = registerBlock(
          "purple_kaolin_brick_stairs",
          props -> new StairBlock(PURPLE_KAOLIN_BRICKS.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<StairBlock> MAGENTA_KAOLIN_BRICK_STAIRS = registerBlock(
          "magenta_kaolin_brick_stairs",
          props -> new StairBlock(MAGENTA_KAOLIN_BRICKS.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<StairBlock> PINK_KAOLIN_BRICK_STAIRS = registerBlock(
          "pink_kaolin_brick_stairs",
          props -> new StairBlock(PINK_KAOLIN_BRICKS.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<SlabBlock> KAOLIN_BRICK_SLAB = registerBlock(
      "kaolin_brick_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<SlabBlock> WHITE_KAOLIN_BRICK_SLAB = registerBlock(
      "white_kaolin_brick_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<SlabBlock> LIGHT_GRAY_KAOLIN_BRICK_SLAB = registerBlock(
      "light_gray_kaolin_brick_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<SlabBlock> GRAY_KAOLIN_BRICK_SLAB = registerBlock(
      "gray_kaolin_brick_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<SlabBlock> BLACK_KAOLIN_BRICK_SLAB = registerBlock(
      "black_kaolin_brick_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<SlabBlock> BROWN_KAOLIN_BRICK_SLAB = registerBlock(
      "brown_kaolin_brick_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<SlabBlock> RED_KAOLIN_BRICK_SLAB = registerBlock(
      "red_kaolin_brick_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<SlabBlock> ORANGE_KAOLIN_BRICK_SLAB = registerBlock(
      "orange_kaolin_brick_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<SlabBlock> YELLOW_KAOLIN_BRICK_SLAB = registerBlock(
      "yellow_kaolin_brick_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<SlabBlock> LIME_KAOLIN_BRICK_SLAB = registerBlock(
      "lime_kaolin_brick_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<SlabBlock> GREEN_KAOLIN_BRICK_SLAB = registerBlock(
      "green_kaolin_brick_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<SlabBlock> CYAN_KAOLIN_BRICK_SLAB = registerBlock(
      "cyan_kaolin_brick_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<SlabBlock> LIGHT_BLUE_KAOLIN_BRICK_SLAB = registerBlock(
      "light_blue_kaolin_brick_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<SlabBlock> BLUE_KAOLIN_BRICK_SLAB = registerBlock(
      "blue_kaolin_brick_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<SlabBlock> PURPLE_KAOLIN_BRICK_SLAB = registerBlock(
      "purple_kaolin_brick_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<SlabBlock> MAGENTA_KAOLIN_BRICK_SLAB = registerBlock(
      "magenta_kaolin_brick_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<SlabBlock> PINK_KAOLIN_BRICK_SLAB = registerBlock(
      "pink_kaolin_brick_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)

  );
  public static final NSBlockHolder<Block> WHITE_CHALK = registerBlock(
      "white_chalk",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<Block> LIGHT_GRAY_CHALK = registerBlock(
      "light_gray_chalk",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<Block> GRAY_CHALK = registerBlock(
      "gray_chalk",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<Block> BLACK_CHALK = registerBlock(
      "black_chalk",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<Block> BROWN_CHALK = registerBlock(
      "brown_chalk",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<Block> RED_CHALK = registerBlock(
      "red_chalk",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<Block> ORANGE_CHALK = registerBlock(
      "orange_chalk",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<Block> YELLOW_CHALK = registerBlock(
      "yellow_chalk",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<Block> LIME_CHALK = registerBlock(
      "lime_chalk",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<Block> GREEN_CHALK = registerBlock(
      "green_chalk",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<Block> CYAN_CHALK = registerBlock(
      "cyan_chalk",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<Block> LIGHT_BLUE_CHALK = registerBlock(
      "light_blue_chalk",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<Block> BLUE_CHALK = registerBlock(
      "blue_chalk",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<Block> PURPLE_CHALK = registerBlock(
      "purple_chalk",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<Block> MAGENTA_CHALK = registerBlock(
      "magenta_chalk",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<Block> PINK_CHALK = registerBlock(
      "pink_chalk",
          props -> new Block(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<StairBlock> WHITE_CHALK_STAIRS = registerBlock(
          "white_chalk_stairs",
          props -> new StairBlock(WHITE_CHALK.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<StairBlock> LIGHT_GRAY_CHALK_STAIRS = registerBlock(
          "light_gray_chalk_stairs",
          props -> new StairBlock(LIGHT_GRAY_CHALK.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<StairBlock> GRAY_CHALK_STAIRS = registerBlock(
          "gray_chalk_stairs",
          props -> new StairBlock(GRAY_CHALK.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<StairBlock> BLACK_CHALK_STAIRS = registerBlock(
          "black_chalk_stairs",
          props -> new StairBlock(BLACK_CHALK.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<StairBlock> BROWN_CHALK_STAIRS = registerBlock(
          "brown_chalk_stairs",
          props -> new StairBlock(BROWN_CHALK.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<StairBlock> RED_CHALK_STAIRS = registerBlock(
          "red_chalk_stairs",
          props -> new StairBlock(RED_CHALK.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<StairBlock> ORANGE_CHALK_STAIRS = registerBlock(
          "orange_chalk_stairs",
          props -> new StairBlock(ORANGE_CHALK.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<StairBlock> YELLOW_CHALK_STAIRS = registerBlock(
          "yellow_chalk_stairs",
          props -> new StairBlock(YELLOW_CHALK.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<StairBlock> LIME_CHALK_STAIRS = registerBlock(
          "lime_chalk_stairs",
          props -> new StairBlock(LIME_CHALK.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<StairBlock> GREEN_CHALK_STAIRS = registerBlock(
          "green_chalk_stairs",
          props -> new StairBlock(GREEN_CHALK.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<StairBlock> CYAN_CHALK_STAIRS = registerBlock(
          "cyan_chalk_stairs",
          props -> new StairBlock(CYAN_CHALK.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<StairBlock> LIGHT_BLUE_CHALK_STAIRS = registerBlock(
          "light_blue_chalk_stairs",
          props -> new StairBlock(LIGHT_BLUE_CHALK.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<StairBlock> BLUE_CHALK_STAIRS = registerBlock(
          "blue_chalk_stairs",
          props -> new StairBlock(BLUE_CHALK.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<StairBlock> PURPLE_CHALK_STAIRS = registerBlock(
          "purple_chalk_stairs",
          props -> new StairBlock(PURPLE_CHALK.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<StairBlock> MAGENTA_CHALK_STAIRS = registerBlock(
          "magenta_chalk_stairs",
          props -> new StairBlock(MAGENTA_CHALK.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<StairBlock> PINK_CHALK_STAIRS = registerBlock(
          "pink_chalk_stairs",
          props -> new StairBlock(PINK_CHALK.get().defaultBlockState(),
                  props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<SlabBlock> WHITE_CHALK_SLAB = registerBlock(
      "white_chalk_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<SlabBlock> LIGHT_GRAY_CHALK_SLAB = registerBlock(
      "light_gray_chalk_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<SlabBlock> GRAY_CHALK_SLAB = registerBlock(
      "gray_chalk_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<SlabBlock> BLACK_CHALK_SLAB = registerBlock(
      "black_chalk_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<SlabBlock> BROWN_CHALK_SLAB = registerBlock(
      "brown_chalk_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<SlabBlock> RED_CHALK_SLAB = registerBlock(
      "red_chalk_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<SlabBlock> ORANGE_CHALK_SLAB = registerBlock(
      "orange_chalk_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<SlabBlock> YELLOW_CHALK_SLAB = registerBlock(
      "yellow_chalk_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<SlabBlock> LIME_CHALK_SLAB = registerBlock(
      "lime_chalk_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<SlabBlock> GREEN_CHALK_SLAB = registerBlock(
      "green_chalk_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<SlabBlock> CYAN_CHALK_SLAB = registerBlock(
      "cyan_chalk_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<SlabBlock> LIGHT_BLUE_CHALK_SLAB = registerBlock(
      "light_blue_chalk_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<SlabBlock> BLUE_CHALK_SLAB = registerBlock(
      "blue_chalk_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<SlabBlock> PURPLE_CHALK_SLAB = registerBlock(
      "purple_chalk_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<SlabBlock> MAGENTA_CHALK_SLAB = registerBlock(
      "magenta_chalk_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );
  public static final NSBlockHolder<SlabBlock> PINK_CHALK_SLAB = registerBlock(
      "pink_chalk_slab",
          props -> new SlabBlock(props), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)

  );

  private static final List<WoodSet> WOOD_SETS = ImmutableList.of(
          NSBlocks.REDWOOD,
          NSBlocks.SUGI,
          NSBlocks.WISTERIA,
          NSBlocks.FIR,
          NSBlocks.WILLOW,
          NSBlocks.ASPEN,
          NSBlocks.MAPLE,
          NSBlocks.CYPRESS,
          NSBlocks.OLIVE,
          NSBlocks.JOSHUA,
          NSBlocks.GHAF,
          NSBlocks.PALO_VERDE,
          NSBlocks.COCONUT,
          NSBlocks.CEDAR,
          NSBlocks.LARCH,
          NSBlocks.MAHOGANY,
          NSBlocks.SAXAUL
  );

  public static List<WoodSet> getWoodSets() {
    return WOOD_SETS;
  }

  static {
    NSFlammables.add(PAPER_BLOCK, 5, 20);
    NSFlammables.add(PAPER_PANEL, 5, 20);
    NSFlammables.add(FRAMED_PAPER_BLOCK, 5, 20);
    NSFlammables.add(FRAMED_PAPER_PANEL, 5, 20);
    NSFlammables.add(BLOOMING_PAPER_BLOCK, 5, 20);
    NSFlammables.add(BLOOMING_PAPER_PANEL, 5, 20);
    NSFlammables.add(OLIVE_BRANCH, 5, 20);
    NSFlammables.add(XERIC_THATCH, 5, 20);
    NSFlammables.add(XERIC_THATCH_SLAB, 5, 20);
    NSFlammables.add(XERIC_THATCH_CARPET, 5, 20);
    NSFlammables.add(COCONUT_THATCH, 5, 20);
    NSFlammables.add(COCONUT_THATCH_SLAB, 5, 20);
    NSFlammables.add(COCONUT_THATCH_CARPET, 5, 20);
    NSFlammables.add(EVERGREEN_THATCH, 5, 20);
    NSFlammables.add(EVERGREEN_THATCH_SLAB, 5, 20);
    NSFlammables.add(EVERGREEN_THATCH_CARPET, 5, 20);
    NSFlammables.add(PAPER_DOOR, 5, 20);
    NSFlammables.add(PAPER_TRAPDOOR, 5, 20);
    NSFlammables.add(FRAMED_PAPER_DOOR, 5, 20);
    NSFlammables.add(FRAMED_PAPER_TRAPDOOR, 5, 20);
    NSFlammables.add(BLOOMING_PAPER_DOOR, 5, 20);
    NSFlammables.add(BLOOMING_PAPER_TRAPDOOR, 5, 20);
    NSFlammables.add(XERIC_THATCH_STAIRS, 5, 20);
    NSFlammables.add(COCONUT_THATCH_STAIRS, 5, 20);
    NSFlammables.add(EVERGREEN_THATCH_STAIRS, 5, 20);
  }

  public static void bootstrap() {
  }

  private static NSBlockHolder<FlowerPotBlock> registerPottedBlock(String name, Supplier<? extends Block> plant) {
    return registerTransparentBlockWithoutItem(name, props -> new FlowerPotBlock(plant.get(), props), () -> BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY));
  }
}
