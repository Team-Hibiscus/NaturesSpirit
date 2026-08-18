package net.hibiscus.naturespirit;

import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import net.hibiscus.naturespirit.blocks.block_entities.PizzaToppingVariant;
import net.hibiscus.naturespirit.config.NSConfig;
import net.hibiscus.naturespirit.platform.Services;
import net.hibiscus.naturespirit.registration.NSBiomes;
import net.hibiscus.naturespirit.registration.NSBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.entity.npc.villager.VillagerType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

public final class NSCommonContent {

    private NSCommonContent() {
    }

    public static void bootstrap() {
        builtinPacks();
        datapackRegistries();
        blockEntityBlocks();
        flowerPots();
        villagerBiomeTypes();
    }

    private static void builtinPacks() {
        BooleanSupplier artsAndCrafts = () -> Services.PLATFORM.isModLoaded("arts_and_crafts");
        pack("arts_and_crafts_res", PackType.CLIENT_RESOURCES, "arts_and_crafts", PackSource.BUILT_IN, true, artsAndCrafts);
        pack("arts_and_crafts_dat", PackType.SERVER_DATA, "arts_and_crafts", PackSource.BUILT_IN, true, artsAndCrafts);
        pack("plank_consistency", PackType.CLIENT_RESOURCES, "plank_consistency", PackSource.FEATURE, false, () -> true);
        pack("emissive_ores_compatibility", PackType.CLIENT_RESOURCES, "emissive_ores_compatibility", PackSource.FEATURE, false, () -> true);
        pack("modified_badlands", PackType.SERVER_DATA, "modified_badlands", PackSource.BUILT_IN, true, () -> NSConfig.badlandsToggle);
        pack("modified_birch_forest", PackType.SERVER_DATA, "modified_birch_forest", PackSource.BUILT_IN, true, () -> NSConfig.birchForestToggle);
        pack("modified_dark_forest", PackType.SERVER_DATA, "modified_dark_forest", PackSource.BUILT_IN, true, () -> NSConfig.darkForestToggle);
        pack("modified_desert", PackType.SERVER_DATA, "modified_desert", PackSource.BUILT_IN, true, () -> NSConfig.desertToggle);
        pack("modified_flower_forest", PackType.SERVER_DATA, "modified_flower_forest", PackSource.BUILT_IN, true, () -> NSConfig.flowerForestToggle);
        pack("modified_jungle", PackType.SERVER_DATA, "modified_jungle", PackSource.BUILT_IN, true, () -> NSConfig.jungleToggle);
        pack("modified_mountain_biomes", PackType.SERVER_DATA, "modified_mountain_biomes", PackSource.BUILT_IN, true, () -> NSConfig.mountainBiomesToggle);
        pack("modified_savannas", PackType.SERVER_DATA, "modified_savannas", PackSource.BUILT_IN, true, () -> NSConfig.savannaToggle);
        pack("modified_swamp", PackType.SERVER_DATA, "modified_swamp", PackSource.BUILT_IN, true, () -> NSConfig.swampToggle);
        pack("modified_vanilla_trees", PackType.SERVER_DATA, "modified_vanilla_trees", PackSource.BUILT_IN, false, () -> NSConfig.vanillaTreesToggle);
        pack("modified_windswept_hills", PackType.SERVER_DATA, "modified_windswept_hills", PackSource.BUILT_IN, true, () -> NSConfig.windsweptHillsToggle);
    }

    private static void pack(String name, PackType type, String translationKey, PackSource source, boolean alwaysActive, BooleanSupplier condition) {
        NSCommonHooks.BUILTIN_PACKS.add(new NSCommonHooks.BuiltinPack(name, type, Component.translatable("pack.natures_spirit." + translationKey), source, alwaysActive, Pack.Position.TOP, condition));
    }

    private static void datapackRegistries() {
        NSCommonHooks.DATAPACK_REGISTRIES.add(new NSCommonHooks.DatapackRegistry<>(NaturesSpirit.PIZZA_TOPPING_VARIANT, PizzaToppingVariant.CODEC, PizzaToppingVariant.CODEC, 256));
    }

    private static void blockEntityBlocks() {
        NSCommonHooks.BLOCK_ENTITY_BLOCKS.add(new NSCommonHooks.BlockEntityBlocks(() -> BlockEntityType.SIGN, List.of(
                NSBlocks.REDWOOD.getSign(),
                NSBlocks.REDWOOD.getWallSign(),
                NSBlocks.SUGI.getSign(),
                NSBlocks.SUGI.getWallSign(),
                NSBlocks.WISTERIA.getSign(),
                NSBlocks.WISTERIA.getWallSign(),
                NSBlocks.FIR.getSign(),
                NSBlocks.FIR.getWallSign(),
                NSBlocks.WILLOW.getSign(),
                NSBlocks.WILLOW.getWallSign(),
                NSBlocks.ASPEN.getSign(),
                NSBlocks.ASPEN.getWallSign(),
                NSBlocks.MAPLE.getSign(),
                NSBlocks.MAPLE.getWallSign(),
                NSBlocks.CYPRESS.getSign(),
                NSBlocks.CYPRESS.getWallSign(),
                NSBlocks.OLIVE.getSign(),
                NSBlocks.OLIVE.getWallSign(),
                NSBlocks.JOSHUA.getSign(),
                NSBlocks.JOSHUA.getWallSign(),
                NSBlocks.GHAF.getSign(),
                NSBlocks.GHAF.getWallSign(),
                NSBlocks.PALO_VERDE.getSign(),
                NSBlocks.PALO_VERDE.getWallSign(),
                NSBlocks.COCONUT.getSign(),
                NSBlocks.COCONUT.getWallSign(),
                NSBlocks.CEDAR.getSign(),
                NSBlocks.CEDAR.getWallSign(),
                NSBlocks.LARCH.getSign(),
                NSBlocks.LARCH.getWallSign(),
                NSBlocks.MAHOGANY.getSign(),
                NSBlocks.MAHOGANY.getWallSign(),
                NSBlocks.SAXAUL.getSign(),
                NSBlocks.SAXAUL.getWallSign(),
                NSBlocks.PAPER_SIGN,
                NSBlocks.PAPER_WALL_SIGN
        )));
        NSCommonHooks.BLOCK_ENTITY_BLOCKS.add(new NSCommonHooks.BlockEntityBlocks(() -> BlockEntityType.HANGING_SIGN, List.of(
                NSBlocks.REDWOOD.getHangingSign(),
                NSBlocks.REDWOOD.getHangingWallSign(),
                NSBlocks.SUGI.getHangingSign(),
                NSBlocks.SUGI.getHangingWallSign(),
                NSBlocks.WISTERIA.getHangingSign(),
                NSBlocks.WISTERIA.getHangingWallSign(),
                NSBlocks.FIR.getHangingSign(),
                NSBlocks.FIR.getHangingWallSign(),
                NSBlocks.WILLOW.getHangingSign(),
                NSBlocks.WILLOW.getHangingWallSign(),
                NSBlocks.ASPEN.getHangingSign(),
                NSBlocks.ASPEN.getHangingWallSign(),
                NSBlocks.MAPLE.getHangingSign(),
                NSBlocks.MAPLE.getHangingWallSign(),
                NSBlocks.CYPRESS.getHangingSign(),
                NSBlocks.CYPRESS.getHangingWallSign(),
                NSBlocks.OLIVE.getHangingSign(),
                NSBlocks.OLIVE.getHangingWallSign(),
                NSBlocks.JOSHUA.getHangingSign(),
                NSBlocks.JOSHUA.getHangingWallSign(),
                NSBlocks.GHAF.getHangingSign(),
                NSBlocks.GHAF.getHangingWallSign(),
                NSBlocks.PALO_VERDE.getHangingSign(),
                NSBlocks.PALO_VERDE.getHangingWallSign(),
                NSBlocks.COCONUT.getHangingSign(),
                NSBlocks.COCONUT.getHangingWallSign(),
                NSBlocks.CEDAR.getHangingSign(),
                NSBlocks.CEDAR.getHangingWallSign(),
                NSBlocks.LARCH.getHangingSign(),
                NSBlocks.LARCH.getHangingWallSign(),
                NSBlocks.MAHOGANY.getHangingSign(),
                NSBlocks.MAHOGANY.getHangingWallSign(),
                NSBlocks.SAXAUL.getHangingSign(),
                NSBlocks.SAXAUL.getHangingWallSign(),
                NSBlocks.PAPER_HANGING_SIGN,
                NSBlocks.PAPER_WALL_HANGING_SIGN
        )));
    }

    private static void flowerPots() {
        pot(NSBlocks.SCORCHED_GRASS, NSBlocks.POTTED_SCORCHED_GRASS);
        pot(NSBlocks.BEACH_GRASS, NSBlocks.POTTED_BEACH_GRASS);
        pot(NSBlocks.SEDGE_GRASS, NSBlocks.POTTED_SEDGE_GRASS);
        pot(NSBlocks.FLAXEN_FERN, NSBlocks.POTTED_FLAXEN_FERN);
        pot(NSBlocks.OAT_GRASS, NSBlocks.POTTED_OAT_GRASS);
        pot(NSBlocks.MELIC_GRASS, NSBlocks.POTTED_MELIC_GRASS);
        pot(NSBlocks.LUSH_FERN, NSBlocks.POTTED_LUSH_FERN);
        pot(NSBlocks.FRIGID_GRASS, NSBlocks.POTTED_FRIGID_GRASS);
        pot(NSBlocks.GREEN_BEARBERRIES, NSBlocks.POTTED_GREEN_BEARBERRIES);
        pot(NSBlocks.RED_BEARBERRIES, NSBlocks.POTTED_RED_BEARBERRIES);
        pot(NSBlocks.PURPLE_BEARBERRIES, NSBlocks.POTTED_PURPLE_BEARBERRIES);
        pot(NSBlocks.ORNATE_SUCCULENT, NSBlocks.POTTED_ORNATE_SUCCULENT);
        pot(NSBlocks.DROWSY_SUCCULENT, NSBlocks.POTTED_DROWSY_SUCCULENT);
        pot(NSBlocks.AUREATE_SUCCULENT, NSBlocks.POTTED_AUREATE_SUCCULENT);
        pot(NSBlocks.SAGE_SUCCULENT, NSBlocks.POTTED_SAGE_SUCCULENT);
        pot(NSBlocks.FOAMY_SUCCULENT, NSBlocks.POTTED_FOAMY_SUCCULENT);
        pot(NSBlocks.IMPERIAL_SUCCULENT, NSBlocks.POTTED_IMPERIAL_SUCCULENT);
        pot(NSBlocks.REGAL_SUCCULENT, NSBlocks.POTTED_REGAL_SUCCULENT);
        pot(NSBlocks.SHIITAKE_MUSHROOM, NSBlocks.POTTED_SHIITAKE_MUSHROOM);
        pot(NSBlocks.MARIGOLD.getFlowerBlock(), NSBlocks.MARIGOLD.getPottedFlowerBlock());
        pot(NSBlocks.BLUEBELL.getFlowerBlock(), NSBlocks.BLUEBELL.getPottedFlowerBlock());
        pot(NSBlocks.TIGER_LILY.getFlowerBlock(), NSBlocks.TIGER_LILY.getPottedFlowerBlock());
        pot(NSBlocks.PURPLE_WILDFLOWER.getFlowerBlock(), NSBlocks.PURPLE_WILDFLOWER.getPottedFlowerBlock());
        pot(NSBlocks.YELLOW_WILDFLOWER.getFlowerBlock(), NSBlocks.YELLOW_WILDFLOWER.getPottedFlowerBlock());
        pot(NSBlocks.RED_HEATHER.getFlowerBlock(), NSBlocks.RED_HEATHER.getPottedFlowerBlock());
        pot(NSBlocks.WHITE_HEATHER.getFlowerBlock(), NSBlocks.WHITE_HEATHER.getPottedFlowerBlock());
        pot(NSBlocks.PURPLE_HEATHER.getFlowerBlock(), NSBlocks.PURPLE_HEATHER.getPottedFlowerBlock());
        pot(NSBlocks.ANEMONE.getFlowerBlock(), NSBlocks.ANEMONE.getPottedFlowerBlock());
        pot(NSBlocks.DWARF_BLOSSOMS.getFlowerBlock(), NSBlocks.DWARF_BLOSSOMS.getPottedFlowerBlock());
        pot(NSBlocks.PROTEA.getFlowerBlock(), NSBlocks.PROTEA.getPottedFlowerBlock());
        pot(NSBlocks.HIBISCUS.getFlowerBlock(), NSBlocks.HIBISCUS.getPottedFlowerBlock());
        pot(NSBlocks.BLUE_IRIS.getFlowerBlock(), NSBlocks.BLUE_IRIS.getPottedFlowerBlock());
        pot(NSBlocks.BLACK_IRIS.getFlowerBlock(), NSBlocks.BLACK_IRIS.getPottedFlowerBlock());
        pot(NSBlocks.RUBY_BLOSSOMS.getFlowerBlock(), NSBlocks.RUBY_BLOSSOMS.getPottedFlowerBlock());
        pot(NSBlocks.REDWOOD.getSapling(), NSBlocks.REDWOOD.getPottedSapling());
        pot(NSBlocks.SUGI.getSapling(), NSBlocks.SUGI.getPottedSapling());
        pot(NSBlocks.WISTERIA.getPurpleSapling(), NSBlocks.WISTERIA.getPottedPurpleSapling());
        pot(NSBlocks.WISTERIA.getWhiteSapling(), NSBlocks.WISTERIA.getPottedWhiteSapling());
        pot(NSBlocks.WISTERIA.getBlueSapling(), NSBlocks.WISTERIA.getPottedBlueSapling());
        pot(NSBlocks.WISTERIA.getPinkSapling(), NSBlocks.WISTERIA.getPottedPinkSapling());
        pot(NSBlocks.FIR.getSapling(), NSBlocks.FIR.getPottedSapling());
        pot(NSBlocks.WILLOW.getSapling(), NSBlocks.WILLOW.getPottedSapling());
        pot(NSBlocks.ASPEN.getSapling(), NSBlocks.ASPEN.getPottedSapling());
        pot(NSBlocks.MAPLE.getRedSapling(), NSBlocks.MAPLE.getPottedRedSapling());
        pot(NSBlocks.MAPLE.getOrangeSapling(), NSBlocks.MAPLE.getPottedOrangeSapling());
        pot(NSBlocks.MAPLE.getYellowSapling(), NSBlocks.MAPLE.getPottedYellowSapling());
        pot(NSBlocks.CYPRESS.getSapling(), NSBlocks.CYPRESS.getPottedSapling());
        pot(NSBlocks.OLIVE.getSapling(), NSBlocks.OLIVE.getPottedSapling());
        pot(NSBlocks.JOSHUA.getSapling(), NSBlocks.JOSHUA.getPottedSapling());
        pot(NSBlocks.GHAF.getSapling(), NSBlocks.GHAF.getPottedSapling());
        pot(NSBlocks.PALO_VERDE.getSapling(), NSBlocks.PALO_VERDE.getPottedSapling());
        pot(NSBlocks.CEDAR.getSapling(), NSBlocks.CEDAR.getPottedSapling());
        pot(NSBlocks.LARCH.getSapling(), NSBlocks.LARCH.getPottedSapling());
        pot(NSBlocks.MAHOGANY.getSapling(), NSBlocks.MAHOGANY.getPottedSapling());
        pot(NSBlocks.SAXAUL.getSapling(), NSBlocks.SAXAUL.getPottedSapling());
    }

    private static void pot(Supplier<? extends Block> plant, Supplier<? extends Block> potted) {
        NSCommonHooks.FLOWER_POTS.add(new NSCommonHooks.PottedPlant(plant, potted));
    }

    private static void villagerBiomeTypes() {
        ResourceKey<VillagerType> wisteria = villagerType("wisteria");
        ResourceKey<VillagerType> cypress = villagerType("cypress");
        ResourceKey<VillagerType> adobe = villagerType("adobe");
        ResourceKey<VillagerType> coconut = villagerType("coconut");
        villager(NSBiomes.WISTERIA_FOREST, wisteria);
        villager(NSBiomes.CYPRESS_FIELDS, cypress);
        villager(NSBiomes.CARNATION_FIELDS, cypress);
        villager(NSBiomes.LAVENDER_FIELDS, cypress);
        villager(NSBiomes.STRATIFIED_DESERT, adobe);
        villager(NSBiomes.LIVELY_DUNES, adobe);
        villager(NSBiomes.BLOOMING_DUNES, adobe);
        villager(NSBiomes.DRYLANDS, VillagerType.DESERT);
        villager(NSBiomes.WOODED_DRYLANDS, VillagerType.DESERT);
        villager(NSBiomes.TROPICAL_SHORES, coconut);
    }

    private static ResourceKey<VillagerType> villagerType(String name) {
        return ResourceKey.create(Registries.VILLAGER_TYPE, NaturesSpirit.id(name));
    }

    private static void villager(ResourceKey<Biome> biome, ResourceKey<VillagerType> villagerType) {
        NSCommonHooks.VILLAGER_BIOME_TYPES.add(new NSCommonHooks.VillagerBiomeType(biome, villagerType));
    }
}
