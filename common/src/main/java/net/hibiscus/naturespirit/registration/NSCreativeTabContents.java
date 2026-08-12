package net.hibiscus.naturespirit.registration;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import net.hibiscus.naturespirit.NSCommonHooks;
import net.hibiscus.naturespirit.NSCommonHooks.TabOutput;
import net.hibiscus.naturespirit.NSCommonHooks.TabVisibility;
import net.hibiscus.naturespirit.platform.Services;
import net.hibiscus.naturespirit.registration.compat.NSArtsAndCraftsCompat;
import net.hibiscus.naturespirit.registration.sets.WoodSet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

public final class NSCreativeTabContents {

    private NSCreativeTabContents() {
    }

    public static void bootstrap() {
        add(CreativeModeTabs.BUILDING_BLOCKS, NSCreativeTabContents::buildingBlocks);
        add(CreativeModeTabs.NATURAL_BLOCKS, NSCreativeTabContents::naturalBlocks);
        add(CreativeModeTabs.FUNCTIONAL_BLOCKS, NSCreativeTabContents::functionalBlocks);
        add(CreativeModeTabs.TOOLS_AND_UTILITIES, NSCreativeTabContents::toolsAndUtilities);
        add(CreativeModeTabs.COLORED_BLOCKS, NSCreativeTabContents::coloredBlocks);
        add(CreativeModeTabs.INGREDIENTS, NSCreativeTabContents::ingredients);
        add(CreativeModeTabs.FOOD_AND_DRINKS, NSCreativeTabContents::foodAndDrinks);
    }

    private static void add(ResourceKey<CreativeModeTab> tab, Consumer<TabOutput> populate) {
        NSCommonHooks.CREATIVE_TAB_ENTRIES.add(new NSCommonHooks.CreativeTabEntries(tab, populate));
    }

    private static void buildingBlocks(TabOutput output) {
        ArrayList<ItemLike> wood = new ArrayList<>();
        wood.add(Items.BAMBOO_BUTTON);
        for(WoodSet woodset: NSBlocks.getWoodSets()) {
            wood.add(woodset.getLog().get());
            if (woodset.getWoodPreset() == WoodSet.WoodPreset.JOSHUA) {
                wood.addAll(List.of(woodset.getBundle().get(), woodset.getStrippedLog().get(), woodset.getStrippedBundle().get(), woodset.getPlanks().get()));
            } else if (!woodset.hasBark()) {
                wood.addAll(List.of(woodset.getStrippedLog().get(), woodset.getPlanks().get()));
            } else {
                wood.addAll(List.of(woodset.getWood().get(), woodset.getStrippedLog().get(), woodset.getStrippedWood().get(), woodset.getPlanks().get()));
            }
            if (woodset.hasMosaic()) {
                wood.addAll(List.of(woodset.getMosaic().get(), woodset.getStairs().get(), woodset.getMosaicStairs().get(), woodset.getSlab().get(), woodset.getMosaicSlab().get(),
                        woodset.getFence().get(), woodset.getFenceGate().get(),
                        woodset.getDoor().get(), woodset.getTrapDoor().get(),
                        woodset.getPressurePlate().get(), woodset.getButton().get()));
            } else {
                wood.addAll(List.of(woodset.getStairs().get(), woodset.getSlab().get(),
                        woodset.getFence().get(), woodset.getFenceGate().get(),
                        woodset.getDoor().get(), woodset.getTrapDoor().get(),
                        woodset.getPressurePlate().get(), woodset.getButton().get()));
            }
        }
        addAllAfterFirst(output, wood, TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(Items.POLISHED_ANDESITE_SLAB.getDefaultInstance(), NSBlocks.TRAVERTINE.getBase().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.TRAVERTINE.getBase().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getBaseStairs().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.TRAVERTINE.getBaseStairs().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getBaseSlab().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.TRAVERTINE.getBaseSlab().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getCobbled().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.TRAVERTINE.getCobbled().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getCobbledStairs().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.TRAVERTINE.getCobbledStairs().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getCobbledSlab().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.TRAVERTINE.getCobbledSlab().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getCobbledWall().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.TRAVERTINE.getCobbledWall().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getMossyCobbled().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.TRAVERTINE.getMossyCobbled().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getMossyCobbledStairs().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.TRAVERTINE.getMossyCobbledStairs().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getMossyCobbledSlab().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.TRAVERTINE.getMossyCobbledSlab().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getMossyCobbledWall().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.TRAVERTINE.getMossyCobbledWall().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getChiseled().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.TRAVERTINE.getChiseled().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getPolished().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.TRAVERTINE.getPolished().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getPolishedStairs().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.TRAVERTINE.getPolishedStairs().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getPolishedSlab().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.TRAVERTINE.getPolishedSlab().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getPolishedWall().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.TRAVERTINE.getPolishedWall().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getBricks().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.TRAVERTINE.getBricks().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getCrackedBricks().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.TRAVERTINE.getCrackedBricks().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getBricksStairs().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.TRAVERTINE.getBricksStairs().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getBricksSlab().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.TRAVERTINE.getBricksSlab().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getBricksWall().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.TRAVERTINE.getBricksWall().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getMossyBricks().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.TRAVERTINE.getMossyBricks().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getMossyBricksStairs().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.TRAVERTINE.getMossyBricksStairs().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getMossyBricksSlab().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.TRAVERTINE.getMossyBricksSlab().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getMossyBricksWall().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.TRAVERTINE.getMossyBricksWall().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getTiles().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.TRAVERTINE.getTiles().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getCrackedTiles().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.TRAVERTINE.getCrackedTiles().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getTilesStairs().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.TRAVERTINE.getTilesStairs().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getTilesSlab().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.TRAVERTINE.getTilesSlab().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getTilesWall().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.TRAVERTINE.getTilesWall().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getBase().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CHERT.getBase().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getBaseStairs().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CHERT.getBaseStairs().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getBaseSlab().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CHERT.getBaseSlab().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getChiseled().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CHERT.getChiseled().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getPolished().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CHERT.getPolished().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getPolishedStairs().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CHERT.getPolishedStairs().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getPolishedSlab().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CHERT.getPolishedSlab().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getPolishedWall().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CHERT.getPolishedWall().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getBricks().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CHERT.getBricks().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getCrackedBricks().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CHERT.getCrackedBricks().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getBricksStairs().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CHERT.getBricksStairs().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getBricksSlab().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CHERT.getBricksSlab().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getBricksWall().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CHERT.getBricksWall().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getTiles().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CHERT.getTiles().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getCrackedTiles().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CHERT.getCrackedTiles().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getTilesStairs().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CHERT.getTilesStairs().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getTilesSlab().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CHERT.getTilesSlab().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getTilesWall().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);

        output.insertAfter(Items.CUT_RED_SANDSTONE_SLAB.getDefaultInstance(), NSBlocks.PINK_SANDSTONE.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.PINK_SANDSTONE.get().asItem().getDefaultInstance(), NSBlocks.PINK_SANDSTONE_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.PINK_SANDSTONE_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.PINK_SANDSTONE_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.PINK_SANDSTONE_SLAB.get().asItem().getDefaultInstance(), NSBlocks.PINK_SANDSTONE_WALL.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.PINK_SANDSTONE_WALL.get().asItem().getDefaultInstance(), NSBlocks.CHISELED_PINK_SANDSTONE.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CHISELED_PINK_SANDSTONE.get().asItem().getDefaultInstance(), NSBlocks.SMOOTH_PINK_SANDSTONE.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.SMOOTH_PINK_SANDSTONE.get().asItem().getDefaultInstance(), NSBlocks.SMOOTH_PINK_SANDSTONE_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.SMOOTH_PINK_SANDSTONE_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.SMOOTH_PINK_SANDSTONE_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.SMOOTH_PINK_SANDSTONE_SLAB.get().asItem().getDefaultInstance(), NSBlocks.CUT_PINK_SANDSTONE.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CUT_PINK_SANDSTONE.get().asItem().getDefaultInstance(), NSBlocks.CUT_PINK_SANDSTONE_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
    }

    private static void naturalBlocks(TabOutput output) {
        output.insertAfter(Items.CHERRY_LOG.getDefaultInstance(), NSBlocks.REDWOOD.getLog().get().asItem().getDefaultInstance(), TabVisibility.PARENT_TAB_ONLY);
        output.insertAfter(NSBlocks.REDWOOD.getLog().get().asItem().getDefaultInstance(), NSBlocks.SUGI.getLog().get().asItem().getDefaultInstance(), TabVisibility.PARENT_TAB_ONLY);
        output.insertAfter(NSBlocks.SUGI.getLog().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getLog().get().asItem().getDefaultInstance(), TabVisibility.PARENT_TAB_ONLY);
        output.insertAfter(NSBlocks.WISTERIA.getLog().get().asItem().getDefaultInstance(), NSBlocks.FIR.getLog().get().asItem().getDefaultInstance(), TabVisibility.PARENT_TAB_ONLY);
        output.insertAfter(NSBlocks.FIR.getLog().get().asItem().getDefaultInstance(), NSBlocks.WILLOW.getLog().get().asItem().getDefaultInstance(), TabVisibility.PARENT_TAB_ONLY);
        output.insertAfter(NSBlocks.WILLOW.getLog().get().asItem().getDefaultInstance(), NSBlocks.ASPEN.getLog().get().asItem().getDefaultInstance(), TabVisibility.PARENT_TAB_ONLY);
        output.insertAfter(NSBlocks.ASPEN.getLog().get().asItem().getDefaultInstance(), NSBlocks.MAPLE.getLog().get().asItem().getDefaultInstance(), TabVisibility.PARENT_TAB_ONLY);
        output.insertAfter(NSBlocks.MAPLE.getLog().get().asItem().getDefaultInstance(), NSBlocks.CYPRESS.getLog().get().asItem().getDefaultInstance(), TabVisibility.PARENT_TAB_ONLY);
        output.insertAfter(NSBlocks.CYPRESS.getLog().get().asItem().getDefaultInstance(), NSBlocks.OLIVE.getLog().get().asItem().getDefaultInstance(), TabVisibility.PARENT_TAB_ONLY);
        output.insertAfter(NSBlocks.OLIVE.getLog().get().asItem().getDefaultInstance(), NSBlocks.JOSHUA.getLog().get().asItem().getDefaultInstance(), TabVisibility.PARENT_TAB_ONLY);
        output.insertAfter(NSBlocks.JOSHUA.getLog().get().asItem().getDefaultInstance(), NSBlocks.GHAF.getLog().get().asItem().getDefaultInstance(), TabVisibility.PARENT_TAB_ONLY);
        output.insertAfter(NSBlocks.GHAF.getLog().get().asItem().getDefaultInstance(), NSBlocks.PALO_VERDE.getLog().get().asItem().getDefaultInstance(), TabVisibility.PARENT_TAB_ONLY);
        output.insertAfter(NSBlocks.PALO_VERDE.getLog().get().asItem().getDefaultInstance(), NSBlocks.COCONUT.getLog().get().asItem().getDefaultInstance(), TabVisibility.PARENT_TAB_ONLY);
        output.insertAfter(NSBlocks.COCONUT.getLog().get().asItem().getDefaultInstance(), NSBlocks.CEDAR.getLog().get().asItem().getDefaultInstance(), TabVisibility.PARENT_TAB_ONLY);
        output.insertAfter(NSBlocks.CEDAR.getLog().get().asItem().getDefaultInstance(), NSBlocks.LARCH.getLog().get().asItem().getDefaultInstance(), TabVisibility.PARENT_TAB_ONLY);
        output.insertAfter(NSBlocks.LARCH.getLog().get().asItem().getDefaultInstance(), NSBlocks.MAHOGANY.getLog().get().asItem().getDefaultInstance(), TabVisibility.PARENT_TAB_ONLY);
        output.insertAfter(NSBlocks.MAHOGANY.getLog().get().asItem().getDefaultInstance(), NSBlocks.SAXAUL.getLog().get().asItem().getDefaultInstance(), TabVisibility.PARENT_TAB_ONLY);

        output.insertAfter(Items.GOLD_ORE.getDefaultInstance(), NSBlocks.CHERT_GOLD_ORE.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(Items.IRON_ORE.getDefaultInstance(), NSBlocks.CHERT_IRON_ORE.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(Items.COAL_ORE.getDefaultInstance(), NSBlocks.CHERT_COAL_ORE.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(Items.LAPIS_ORE.getDefaultInstance(), NSBlocks.CHERT_LAPIS_ORE.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(Items.DIAMOND_ORE.getDefaultInstance(), NSBlocks.CHERT_DIAMOND_ORE.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(Items.REDSTONE_ORE.getDefaultInstance(), NSBlocks.CHERT_REDSTONE_ORE.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(Items.EMERALD_ORE.getDefaultInstance(), NSBlocks.CHERT_EMERALD_ORE.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(Items.COPPER_ORE.getDefaultInstance(), NSBlocks.CHERT_COPPER_ORE.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);

        output.insertAfter(Items.ANDESITE.getDefaultInstance(), NSBlocks.TRAVERTINE.getBase().get().asItem().getDefaultInstance(), TabVisibility.PARENT_TAB_ONLY);
        output.insertAfter(NSBlocks.TRAVERTINE.getBase().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getBase().get().asItem().getDefaultInstance(), TabVisibility.PARENT_TAB_ONLY);

        output.insertAfter(Items.RED_SANDSTONE.getDefaultInstance(), NSBlocks.PINK_SAND.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.PINK_SAND.get().asItem().getDefaultInstance(), NSBlocks.PINK_SANDSTONE.get().asItem().getDefaultInstance(), TabVisibility.PARENT_TAB_ONLY);

        output.insertAfter(Items.BAMBOO.getDefaultInstance(), NSBlocks.OLIVE_BRANCH.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(Items.SWEET_BERRIES.getDefaultInstance(), NSBlocks.COCONUT_BLOCK.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);

        output.insertAfter(Items.RED_MUSHROOM.getDefaultInstance(), NSBlocks.SHIITAKE_MUSHROOM.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.SHIITAKE_MUSHROOM.get().asItem().getDefaultInstance(), NSBlocks.GRAY_POLYPORE.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);

        output.insertAfter(Items.RED_MUSHROOM_BLOCK.getDefaultInstance(), NSBlocks.SHIITAKE_MUSHROOM_BLOCK.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.SHIITAKE_MUSHROOM_BLOCK.get().asItem().getDefaultInstance(), NSBlocks.GRAY_POLYPORE_BLOCK.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);

        output.insertAfter(Items.SHROOMLIGHT.getDefaultInstance(), NSBlocks.DESERT_TURNIP_ROOT_BLOCK.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.DESERT_TURNIP_ROOT_BLOCK.get().asItem().getDefaultInstance(), NSBlocks.DESERT_TURNIP_BLOCK.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);

        output.insertAfter(Items.VINE.getDefaultInstance(), NSBlocks.WILLOW.getVines().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.WILLOW.getVines().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getWhiteVines().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.WISTERIA.getWhiteVines().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getBlueVines().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.WISTERIA.getBlueVines().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getPinkVines().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.WISTERIA.getPinkVines().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getPurpleVines().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);

        output.insertAfter(Items.CACTUS.getDefaultInstance(), NSBlocks.ALLUAUDIA.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.ALLUAUDIA.get().asItem().getDefaultInstance(), NSBlocks.ALLUAUDIA_BUNDLE.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.ALLUAUDIA_BUNDLE.get().asItem().getDefaultInstance(), NSBlocks.STRIPPED_ALLUAUDIA.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.STRIPPED_ALLUAUDIA.get().asItem().getDefaultInstance(), NSBlocks.STRIPPED_ALLUAUDIA_BUNDLE.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);

        output.insertAfter(Items.LILY_PAD.getDefaultInstance(), NSBlocks.HELVOLA_PAD_ITEM.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.HELVOLA_PAD_ITEM.get().asItem().getDefaultInstance(), NSBlocks.HELVOLA_FLOWER_ITEM.get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.HELVOLA_FLOWER_ITEM.get().asItem().getDefaultInstance(), NSBlocks.LOTUS_FLOWER_ITEM.get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LOTUS_FLOWER_ITEM.get().asItem().getDefaultInstance(), NSBlocks.LOTUS_STEM.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LOTUS_STEM.get().asItem().getDefaultInstance(), NSBlocks.AZOLLA_ITEM.get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);

        output.insertAfter(Items.FARMLAND.getDefaultInstance(), NSBlocks.SANDY_SOIL.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(Items.MOSS_BLOCK.getDefaultInstance(), NSBlocks.RED_MOSS_BLOCK.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(Items.MOSS_CARPET.getDefaultInstance(), NSBlocks.RED_MOSS_CARPET.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);

        output.insertAfter(Items.PITCHER_PLANT.getDefaultInstance(), NSBlocks.ORNATE_SUCCULENT_ITEM.get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.ORNATE_SUCCULENT_ITEM.get().getDefaultInstance(), NSBlocks.DROWSY_SUCCULENT_ITEM.get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.DROWSY_SUCCULENT_ITEM.get().getDefaultInstance(), NSBlocks.AUREATE_SUCCULENT_ITEM.get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.AUREATE_SUCCULENT_ITEM.get().getDefaultInstance(), NSBlocks.SAGE_SUCCULENT_ITEM.get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.SAGE_SUCCULENT_ITEM.get().getDefaultInstance(), NSBlocks.FOAMY_SUCCULENT_ITEM.get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.FOAMY_SUCCULENT_ITEM.get().getDefaultInstance(), NSBlocks.IMPERIAL_SUCCULENT_ITEM.get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.IMPERIAL_SUCCULENT_ITEM.get().getDefaultInstance(), NSBlocks.REGAL_SUCCULENT_ITEM.get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);

        output.insertAfter(Items.PEONY.getDefaultInstance(), NSBlocks.LAVENDER.getFlowerBlock().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LAVENDER.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.BLEEDING_HEART.getFlowerBlock().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.BLEEDING_HEART.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.BLUE_BULBS.getFlowerBlock().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.BLUE_BULBS.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.CARNATION.getFlowerBlock().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CARNATION.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.GARDENIA.getFlowerBlock().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.GARDENIA.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.SNAPDRAGON.getFlowerBlock().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.SNAPDRAGON.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.FOXGLOVE.getFlowerBlock().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.FOXGLOVE.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.BEGONIA.getFlowerBlock().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);

        output.insertAfter(Items.LILY_OF_THE_VALLEY.getDefaultInstance(), NSBlocks.MARIGOLD.getFlowerBlock().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.MARIGOLD.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.BLUEBELL.getFlowerBlock().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.BLUEBELL.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.TIGER_LILY.getFlowerBlock().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.TIGER_LILY.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.PURPLE_WILDFLOWER.getFlowerBlock().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.PURPLE_WILDFLOWER.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.YELLOW_WILDFLOWER.getFlowerBlock().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.YELLOW_WILDFLOWER.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.RED_HEATHER.getFlowerBlock().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.RED_HEATHER.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.WHITE_HEATHER.getFlowerBlock().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.WHITE_HEATHER.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.PURPLE_HEATHER.getFlowerBlock().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.PURPLE_HEATHER.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.ANEMONE.getFlowerBlock().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.ANEMONE.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.DWARF_BLOSSOMS.getFlowerBlock().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.DWARF_BLOSSOMS.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.PROTEA.getFlowerBlock().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.PROTEA.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.HIBISCUS.getFlowerBlock().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.HIBISCUS.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.BLUE_IRIS.getFlowerBlock().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.BLUE_IRIS.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.BLACK_IRIS.getFlowerBlock().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.BLACK_IRIS.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.RUBY_BLOSSOMS.getFlowerBlock().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.RUBY_BLOSSOMS.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.SILVERBUSH.getFlowerBlock().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);

        output.insertAfter(Items.LARGE_FERN.getDefaultInstance(), NSBlocks.CATTAIL.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CATTAIL.get().asItem().getDefaultInstance(), NSBlocks.TALL_FRIGID_GRASS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.TALL_FRIGID_GRASS.get().asItem().getDefaultInstance(), NSBlocks.TALL_SCORCHED_GRASS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.TALL_SCORCHED_GRASS.get().asItem().getDefaultInstance(), NSBlocks.TALL_BEACH_GRASS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.TALL_BEACH_GRASS.get().asItem().getDefaultInstance(), NSBlocks.TALL_SEDGE_GRASS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.TALL_SEDGE_GRASS.get().asItem().getDefaultInstance(), NSBlocks.LARGE_FLAXEN_FERN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LARGE_FLAXEN_FERN.get().asItem().getDefaultInstance(), NSBlocks.TALL_OAT_GRASS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.TALL_OAT_GRASS.get().asItem().getDefaultInstance(), NSBlocks.LARGE_LUSH_FERN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LARGE_LUSH_FERN.get().asItem().getDefaultInstance(), NSBlocks.TALL_MELIC_GRASS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.TALL_MELIC_GRASS.get().asItem().getDefaultInstance(), NSBlocks.GREEN_BEARBERRIES.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.GREEN_BEARBERRIES.get().asItem().getDefaultInstance(), NSBlocks.RED_BEARBERRIES.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.RED_BEARBERRIES.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_BEARBERRIES.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.PURPLE_BEARBERRIES.get().asItem().getDefaultInstance(), NSBlocks.GREEN_BITTER_SPROUTS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.GREEN_BITTER_SPROUTS.get().asItem().getDefaultInstance(), NSBlocks.RED_BITTER_SPROUTS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.RED_BITTER_SPROUTS.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_BITTER_SPROUTS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);

        output.insertAfter(Items.FERN.getDefaultInstance(), NSBlocks.BEACH_GRASS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.BEACH_GRASS.get().asItem().getDefaultInstance(), NSBlocks.SCORCHED_GRASS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.SCORCHED_GRASS.get().asItem().getDefaultInstance(), NSBlocks.SEDGE_GRASS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.SEDGE_GRASS.get().asItem().getDefaultInstance(), NSBlocks.FLAXEN_FERN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.FLAXEN_FERN.get().asItem().getDefaultInstance(), NSBlocks.OAT_GRASS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.OAT_GRASS.get().asItem().getDefaultInstance(), NSBlocks.LUSH_FERN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LUSH_FERN.get().asItem().getDefaultInstance(), NSBlocks.MELIC_GRASS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.MELIC_GRASS.get().asItem().getDefaultInstance(), NSBlocks.FRIGID_GRASS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);

        output.insertAfter(Items.CHERRY_LEAVES.getDefaultInstance(), NSBlocks.REDWOOD.getLeaves().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.REDWOOD.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.REDWOOD.getFrostyLeaves().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.REDWOOD.getFrostyLeaves().get().asItem().getDefaultInstance(), NSBlocks.SUGI.getLeaves().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.SUGI.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getPurpleLeaves().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.WISTERIA.getPurpleLeaves().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getWhiteLeaves().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.WISTERIA.getWhiteLeaves().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getBlueLeaves().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.WISTERIA.getBlueLeaves().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getPinkLeaves().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.WISTERIA.getPinkLeaves().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getPartPurpleLeaves().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.WISTERIA.getPartPurpleLeaves().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getPartWhiteLeaves().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.WISTERIA.getPartWhiteLeaves().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getPartBlueLeaves().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.WISTERIA.getPartBlueLeaves().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getPartPinkLeaves().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.WISTERIA.getPartPinkLeaves().get().asItem().getDefaultInstance(), NSBlocks.FIR.getLeaves().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.FIR.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.FIR.getFrostyLeaves().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.FIR.getFrostyLeaves().get().asItem().getDefaultInstance(), NSBlocks.WILLOW.getLeaves().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.WILLOW.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.ASPEN.getLeaves().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.ASPEN.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.ASPEN.getYellowLeaves().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.ASPEN.getYellowLeaves().get().asItem().getDefaultInstance(), NSBlocks.MAPLE.getRedLeaves().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.MAPLE.getRedLeaves().get().asItem().getDefaultInstance(), NSBlocks.MAPLE.getOrangeLeaves().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.MAPLE.getOrangeLeaves().get().asItem().getDefaultInstance(), NSBlocks.MAPLE.getYellowLeaves().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.MAPLE.getYellowLeaves().get().asItem().getDefaultInstance(), NSBlocks.CYPRESS.getLeaves().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CYPRESS.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.OLIVE.getLeaves().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.OLIVE.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.JOSHUA.getLeaves().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.JOSHUA.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.GHAF.getLeaves().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.GHAF.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.PALO_VERDE.getLeaves().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.PALO_VERDE.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.CEDAR.getLeaves().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CEDAR.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.COCONUT.getLeaves().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.COCONUT.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.LARCH.getLeaves().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LARCH.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.MAHOGANY.getLeaves().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.MAHOGANY.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.SAXAUL.getLeaves().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);

        output.insertAfter(Items.CHERRY_SAPLING.getDefaultInstance(), NSBlocks.REDWOOD.getSapling().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.REDWOOD.getSapling().get().asItem().getDefaultInstance(), NSBlocks.SUGI.getSapling().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.SUGI.getSapling().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getPurpleSapling().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.WISTERIA.getPurpleSapling().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getWhiteSapling().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.WISTERIA.getWhiteSapling().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getBlueSapling().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.WISTERIA.getBlueSapling().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getPinkSapling().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.WISTERIA.getPinkSapling().get().asItem().getDefaultInstance(), NSBlocks.FIR.getSapling().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.FIR.getSapling().get().asItem().getDefaultInstance(), NSBlocks.WILLOW.getSapling().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.WILLOW.getSapling().get().asItem().getDefaultInstance(), NSBlocks.ASPEN.getSapling().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.ASPEN.getSapling().get().asItem().getDefaultInstance(), NSBlocks.MAPLE.getRedSapling().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.MAPLE.getRedSapling().get().asItem().getDefaultInstance(), NSBlocks.MAPLE.getOrangeSapling().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.MAPLE.getOrangeSapling().get().asItem().getDefaultInstance(), NSBlocks.MAPLE.getYellowSapling().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.MAPLE.getYellowSapling().get().asItem().getDefaultInstance(), NSBlocks.CYPRESS.getSapling().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CYPRESS.getSapling().get().asItem().getDefaultInstance(), NSBlocks.OLIVE.getSapling().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.OLIVE.getSapling().get().asItem().getDefaultInstance(), NSBlocks.JOSHUA.getSapling().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.JOSHUA.getSapling().get().asItem().getDefaultInstance(), NSBlocks.GHAF.getSapling().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.GHAF.getSapling().get().asItem().getDefaultInstance(), NSBlocks.PALO_VERDE.getSapling().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.PALO_VERDE.getSapling().get().asItem().getDefaultInstance(), NSBlocks.CEDAR.getSapling().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CEDAR.getSapling().get().asItem().getDefaultInstance(), NSBlocks.LARCH.getSapling().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LARCH.getSapling().get().asItem().getDefaultInstance(), NSBlocks.MAHOGANY.getSapling().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.MAHOGANY.getSapling().get().asItem().getDefaultInstance(), NSBlocks.SAXAUL.getSapling().get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
    }

    private static void functionalBlocks(TabOutput output) {
        output.insertAfter(Items.BAMBOO_HANGING_SIGN.getDefaultInstance(), NSBlocks.REDWOOD.getSignItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.REDWOOD.getSignItem().get().getDefaultInstance(), NSBlocks.REDWOOD.getHangingSignItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.REDWOOD.getHangingSignItem().get().getDefaultInstance(), NSBlocks.SUGI.getSignItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.SUGI.getSignItem().get().getDefaultInstance(), NSBlocks.SUGI.getHangingSignItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.SUGI.getHangingSignItem().get().getDefaultInstance(), NSBlocks.WISTERIA.getSignItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.WISTERIA.getSignItem().get().getDefaultInstance(), NSBlocks.WISTERIA.getHangingSignItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.WISTERIA.getHangingSignItem().get().getDefaultInstance(), NSBlocks.FIR.getSignItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.FIR.getSignItem().get().getDefaultInstance(), NSBlocks.FIR.getHangingSignItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.FIR.getHangingSignItem().get().getDefaultInstance(), NSBlocks.WILLOW.getSignItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.WILLOW.getSignItem().get().getDefaultInstance(), NSBlocks.WILLOW.getHangingSignItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.WILLOW.getHangingSignItem().get().getDefaultInstance(), NSBlocks.ASPEN.getSignItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.ASPEN.getSignItem().get().getDefaultInstance(), NSBlocks.ASPEN.getHangingSignItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.ASPEN.getHangingSignItem().get().getDefaultInstance(), NSBlocks.MAPLE.getSignItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.MAPLE.getSignItem().get().getDefaultInstance(), NSBlocks.MAPLE.getHangingSignItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.MAPLE.getHangingSignItem().get().getDefaultInstance(), NSBlocks.CYPRESS.getSignItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CYPRESS.getSignItem().get().getDefaultInstance(), NSBlocks.CYPRESS.getHangingSignItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CYPRESS.getHangingSignItem().get().getDefaultInstance(), NSBlocks.OLIVE.getSignItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.OLIVE.getSignItem().get().getDefaultInstance(), NSBlocks.OLIVE.getHangingSignItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.OLIVE.getHangingSignItem().get().getDefaultInstance(), NSBlocks.JOSHUA.getSignItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.JOSHUA.getSignItem().get().getDefaultInstance(), NSBlocks.JOSHUA.getHangingSignItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.JOSHUA.getHangingSignItem().get().getDefaultInstance(), NSBlocks.GHAF.getSignItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.GHAF.getSignItem().get().getDefaultInstance(), NSBlocks.GHAF.getHangingSignItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.GHAF.getHangingSignItem().get().getDefaultInstance(), NSBlocks.PALO_VERDE.getSignItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.PALO_VERDE.getSignItem().get().getDefaultInstance(), NSBlocks.PALO_VERDE.getHangingSignItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.PALO_VERDE.getHangingSignItem().get().getDefaultInstance(), NSBlocks.COCONUT.getSignItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.COCONUT.getSignItem().get().getDefaultInstance(), NSBlocks.COCONUT.getHangingSignItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.COCONUT.getHangingSignItem().get().getDefaultInstance(), NSBlocks.CEDAR.getSignItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CEDAR.getSignItem().get().getDefaultInstance(), NSBlocks.CEDAR.getHangingSignItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CEDAR.getHangingSignItem().get().getDefaultInstance(), NSBlocks.LARCH.getSignItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LARCH.getSignItem().get().getDefaultInstance(), NSBlocks.LARCH.getHangingSignItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LARCH.getHangingSignItem().get().getDefaultInstance(), NSBlocks.MAHOGANY.getSignItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.MAHOGANY.getSignItem().get().getDefaultInstance(), NSBlocks.MAHOGANY.getHangingSignItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.MAHOGANY.getHangingSignItem().get().getDefaultInstance(), NSBlocks.SAXAUL.getSignItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.SAXAUL.getSignItem().get().getDefaultInstance(), NSBlocks.SAXAUL.getHangingSignItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);

        output.insertAfter(Items.SOUL_LANTERN.getDefaultInstance(), NSBlocks.WHITE_PAPER_LANTERN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_TAB_ONLY);
        output.insertAfter(NSBlocks.WHITE_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_GRAY_PAPER_LANTERN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_TAB_ONLY);
        output.insertAfter(NSBlocks.LIGHT_GRAY_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.GRAY_PAPER_LANTERN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_TAB_ONLY);
        output.insertAfter(NSBlocks.GRAY_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.BLACK_PAPER_LANTERN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_TAB_ONLY);
        output.insertAfter(NSBlocks.BLACK_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.BROWN_PAPER_LANTERN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_TAB_ONLY);
        output.insertAfter(NSBlocks.BROWN_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.RED_PAPER_LANTERN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_TAB_ONLY);
        output.insertAfter(NSBlocks.RED_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.ORANGE_PAPER_LANTERN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_TAB_ONLY);
        output.insertAfter(NSBlocks.ORANGE_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.YELLOW_PAPER_LANTERN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_TAB_ONLY);
        output.insertAfter(NSBlocks.YELLOW_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.LIME_PAPER_LANTERN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_TAB_ONLY);
        output.insertAfter(NSBlocks.LIME_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.GREEN_PAPER_LANTERN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_TAB_ONLY);
        output.insertAfter(NSBlocks.GREEN_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.CYAN_PAPER_LANTERN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_TAB_ONLY);
        output.insertAfter(NSBlocks.CYAN_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_BLUE_PAPER_LANTERN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_TAB_ONLY);
        output.insertAfter(NSBlocks.LIGHT_BLUE_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.BLUE_PAPER_LANTERN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_TAB_ONLY);
        output.insertAfter(NSBlocks.BLUE_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_PAPER_LANTERN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_TAB_ONLY);
        output.insertAfter(NSBlocks.PURPLE_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.MAGENTA_PAPER_LANTERN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_TAB_ONLY);
        output.insertAfter(NSBlocks.MAGENTA_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.PINK_PAPER_LANTERN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_TAB_ONLY);

    }

    private static void toolsAndUtilities(TabOutput output) {
        output.insertAfter(Items.BAMBOO_CHEST_RAFT.getDefaultInstance(), NSBlocks.REDWOOD.getBoatItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.REDWOOD.getBoatItem().get().getDefaultInstance(), NSBlocks.REDWOOD.getChestBoatItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.REDWOOD.getChestBoatItem().get().getDefaultInstance(), NSBlocks.SUGI.getBoatItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.SUGI.getBoatItem().get().getDefaultInstance(), NSBlocks.SUGI.getChestBoatItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.SUGI.getChestBoatItem().get().getDefaultInstance(), NSBlocks.WISTERIA.getBoatItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.WISTERIA.getBoatItem().get().getDefaultInstance(), NSBlocks.WISTERIA.getChestBoatItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.WISTERIA.getChestBoatItem().get().getDefaultInstance(), NSBlocks.FIR.getBoatItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.FIR.getBoatItem().get().getDefaultInstance(), NSBlocks.FIR.getChestBoatItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.FIR.getChestBoatItem().get().getDefaultInstance(), NSBlocks.WILLOW.getBoatItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.WILLOW.getBoatItem().get().getDefaultInstance(), NSBlocks.WILLOW.getChestBoatItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.WILLOW.getChestBoatItem().get().getDefaultInstance(), NSBlocks.ASPEN.getBoatItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.ASPEN.getBoatItem().get().getDefaultInstance(), NSBlocks.ASPEN.getChestBoatItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.ASPEN.getChestBoatItem().get().getDefaultInstance(), NSBlocks.MAPLE.getBoatItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.MAPLE.getBoatItem().get().getDefaultInstance(), NSBlocks.MAPLE.getChestBoatItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.MAPLE.getChestBoatItem().get().getDefaultInstance(), NSBlocks.CYPRESS.getBoatItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CYPRESS.getBoatItem().get().getDefaultInstance(), NSBlocks.CYPRESS.getChestBoatItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CYPRESS.getChestBoatItem().get().getDefaultInstance(), NSBlocks.OLIVE.getBoatItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.OLIVE.getBoatItem().get().getDefaultInstance(), NSBlocks.OLIVE.getChestBoatItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.OLIVE.getChestBoatItem().get().getDefaultInstance(), NSBlocks.JOSHUA.getBoatItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.JOSHUA.getBoatItem().get().getDefaultInstance(), NSBlocks.JOSHUA.getChestBoatItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.JOSHUA.getChestBoatItem().get().getDefaultInstance(), NSBlocks.GHAF.getBoatItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.GHAF.getBoatItem().get().getDefaultInstance(), NSBlocks.GHAF.getChestBoatItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.GHAF.getChestBoatItem().get().getDefaultInstance(), NSBlocks.PALO_VERDE.getBoatItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.PALO_VERDE.getBoatItem().get().getDefaultInstance(), NSBlocks.PALO_VERDE.getChestBoatItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.PALO_VERDE.getChestBoatItem().get().getDefaultInstance(), NSBlocks.COCONUT.getBoatItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.COCONUT.getBoatItem().get().getDefaultInstance(), NSBlocks.COCONUT.getChestBoatItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.COCONUT.getChestBoatItem().get().getDefaultInstance(), NSBlocks.CEDAR.getBoatItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CEDAR.getBoatItem().get().getDefaultInstance(), NSBlocks.CEDAR.getChestBoatItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CEDAR.getChestBoatItem().get().getDefaultInstance(), NSBlocks.LARCH.getBoatItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LARCH.getBoatItem().get().getDefaultInstance(), NSBlocks.LARCH.getChestBoatItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LARCH.getChestBoatItem().get().getDefaultInstance(), NSBlocks.MAHOGANY.getBoatItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.MAHOGANY.getBoatItem().get().getDefaultInstance(), NSBlocks.MAHOGANY.getChestBoatItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.MAHOGANY.getChestBoatItem().get().getDefaultInstance(), NSBlocks.SAXAUL.getBoatItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.SAXAUL.getBoatItem().get().getDefaultInstance(), NSBlocks.SAXAUL.getChestBoatItem().get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
    }

    private static void coloredBlocks(TabOutput output) {
        output.insertAfter(Items.PINK_TERRACOTTA.getDefaultInstance(), NSBlocks.KAOLIN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.WHITE_KAOLIN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.WHITE_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_GRAY_KAOLIN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LIGHT_GRAY_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.GRAY_KAOLIN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.GRAY_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.BLACK_KAOLIN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.BLACK_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.BROWN_KAOLIN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.BROWN_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.RED_KAOLIN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.RED_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.ORANGE_KAOLIN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.ORANGE_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.YELLOW_KAOLIN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.YELLOW_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.LIME_KAOLIN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LIME_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.GREEN_KAOLIN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.GREEN_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.CYAN_KAOLIN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CYAN_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_BLUE_KAOLIN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LIGHT_BLUE_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.BLUE_KAOLIN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.BLUE_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_KAOLIN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.PURPLE_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.MAGENTA_KAOLIN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.MAGENTA_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.PINK_KAOLIN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.PINK_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.KAOLIN_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.WHITE_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.WHITE_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_GRAY_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LIGHT_GRAY_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.GRAY_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.GRAY_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.BLACK_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.BLACK_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.BROWN_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.BROWN_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.RED_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.RED_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.ORANGE_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.ORANGE_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.YELLOW_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.YELLOW_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.LIME_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LIME_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.GREEN_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.GREEN_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.CYAN_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CYAN_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_BLUE_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LIGHT_BLUE_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.BLUE_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.BLUE_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.PURPLE_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.MAGENTA_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.MAGENTA_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.PINK_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.PINK_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.KAOLIN_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.WHITE_KAOLIN_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.WHITE_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_GRAY_KAOLIN_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LIGHT_GRAY_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.GRAY_KAOLIN_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.GRAY_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.BLACK_KAOLIN_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.BLACK_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.BROWN_KAOLIN_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.BROWN_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.RED_KAOLIN_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.RED_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.ORANGE_KAOLIN_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.ORANGE_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.YELLOW_KAOLIN_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.YELLOW_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.LIME_KAOLIN_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LIME_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.GREEN_KAOLIN_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.GREEN_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.CYAN_KAOLIN_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CYAN_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_BLUE_KAOLIN_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LIGHT_BLUE_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.BLUE_KAOLIN_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.BLUE_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_KAOLIN_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.PURPLE_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.MAGENTA_KAOLIN_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.MAGENTA_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.PINK_KAOLIN_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.PINK_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.KAOLIN_BRICKS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.WHITE_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.WHITE_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_GRAY_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LIGHT_GRAY_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.GRAY_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.GRAY_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.BLACK_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.BLACK_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.BROWN_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.BROWN_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.RED_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.RED_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.ORANGE_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.ORANGE_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.YELLOW_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.YELLOW_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.LIME_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LIME_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.GREEN_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.GREEN_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.CYAN_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CYAN_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_BLUE_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LIGHT_BLUE_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.BLUE_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.BLUE_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.PURPLE_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.MAGENTA_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.MAGENTA_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.PINK_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.PINK_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.WHITE_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.WHITE_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_GRAY_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LIGHT_GRAY_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.GRAY_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.GRAY_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.BLACK_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.BLACK_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.BROWN_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.BROWN_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.RED_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.RED_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.ORANGE_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.ORANGE_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.YELLOW_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.YELLOW_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.LIME_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LIME_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.GREEN_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.GREEN_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.CYAN_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CYAN_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_BLUE_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LIGHT_BLUE_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.BLUE_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.BLUE_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.PURPLE_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.MAGENTA_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.MAGENTA_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.PINK_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.PINK_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.WHITE_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.WHITE_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_GRAY_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LIGHT_GRAY_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.GRAY_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.GRAY_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.BLACK_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.BLACK_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.BROWN_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.BROWN_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.RED_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.RED_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.ORANGE_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.ORANGE_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.YELLOW_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.YELLOW_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.LIME_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LIME_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.GREEN_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.GREEN_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.CYAN_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CYAN_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_BLUE_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LIGHT_BLUE_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.BLUE_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.BLUE_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.PURPLE_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.MAGENTA_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.MAGENTA_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.PINK_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.PINK_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.WHITE_CHALK.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.WHITE_CHALK.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_GRAY_CHALK.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LIGHT_GRAY_CHALK.get().asItem().getDefaultInstance(), NSBlocks.GRAY_CHALK.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.GRAY_CHALK.get().asItem().getDefaultInstance(), NSBlocks.BLACK_CHALK.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.BLACK_CHALK.get().asItem().getDefaultInstance(), NSBlocks.BROWN_CHALK.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.BROWN_CHALK.get().asItem().getDefaultInstance(), NSBlocks.RED_CHALK.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.RED_CHALK.get().asItem().getDefaultInstance(), NSBlocks.ORANGE_CHALK.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.ORANGE_CHALK.get().asItem().getDefaultInstance(), NSBlocks.YELLOW_CHALK.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.YELLOW_CHALK.get().asItem().getDefaultInstance(), NSBlocks.LIME_CHALK.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LIME_CHALK.get().asItem().getDefaultInstance(), NSBlocks.GREEN_CHALK.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.GREEN_CHALK.get().asItem().getDefaultInstance(), NSBlocks.CYAN_CHALK.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CYAN_CHALK.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_BLUE_CHALK.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LIGHT_BLUE_CHALK.get().asItem().getDefaultInstance(), NSBlocks.BLUE_CHALK.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.BLUE_CHALK.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_CHALK.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.PURPLE_CHALK.get().asItem().getDefaultInstance(), NSBlocks.MAGENTA_CHALK.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.MAGENTA_CHALK.get().asItem().getDefaultInstance(), NSBlocks.PINK_CHALK.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.PINK_CHALK.get().asItem().getDefaultInstance(), NSBlocks.WHITE_CHALK_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.WHITE_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_GRAY_CHALK_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LIGHT_GRAY_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.GRAY_CHALK_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.GRAY_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.BLACK_CHALK_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.BLACK_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.BROWN_CHALK_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.BROWN_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.RED_CHALK_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.RED_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.ORANGE_CHALK_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.ORANGE_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.YELLOW_CHALK_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.YELLOW_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.LIME_CHALK_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LIME_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.GREEN_CHALK_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.GREEN_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.CYAN_CHALK_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CYAN_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_BLUE_CHALK_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LIGHT_BLUE_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.BLUE_CHALK_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.BLUE_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_CHALK_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.PURPLE_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.MAGENTA_CHALK_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.MAGENTA_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.PINK_CHALK_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.PINK_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.WHITE_CHALK_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.WHITE_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_GRAY_CHALK_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LIGHT_GRAY_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.GRAY_CHALK_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.GRAY_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.BLACK_CHALK_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.BLACK_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.BROWN_CHALK_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.BROWN_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.RED_CHALK_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.RED_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.ORANGE_CHALK_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.ORANGE_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.YELLOW_CHALK_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.YELLOW_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.LIME_CHALK_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LIME_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.GREEN_CHALK_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.GREEN_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.CYAN_CHALK_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CYAN_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_BLUE_CHALK_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LIGHT_BLUE_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.BLUE_CHALK_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.BLUE_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_CHALK_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.PURPLE_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.MAGENTA_CHALK_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.MAGENTA_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.PINK_CHALK_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);

        output.insertAfter(Items.PINK_CANDLE.getDefaultInstance(), NSBlocks.WHITE_PAPER_LANTERN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.WHITE_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_GRAY_PAPER_LANTERN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LIGHT_GRAY_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.GRAY_PAPER_LANTERN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.GRAY_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.BLACK_PAPER_LANTERN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.BLACK_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.BROWN_PAPER_LANTERN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.BROWN_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.RED_PAPER_LANTERN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.RED_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.ORANGE_PAPER_LANTERN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.ORANGE_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.YELLOW_PAPER_LANTERN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.YELLOW_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.LIME_PAPER_LANTERN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LIME_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.GREEN_PAPER_LANTERN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.GREEN_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.CYAN_PAPER_LANTERN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.CYAN_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_BLUE_PAPER_LANTERN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.LIGHT_BLUE_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.BLUE_PAPER_LANTERN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.BLUE_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_PAPER_LANTERN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.PURPLE_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.MAGENTA_PAPER_LANTERN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.MAGENTA_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.PINK_PAPER_LANTERN.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);


        if(Services.PLATFORM.isModLoaded("arts_and_crafts")) {
            output.insertBefore(NSBlocks.WHITE_CHALK.get().asItem().getDefaultInstance(), NSArtsAndCraftsCompat.BLEACHED_CHALK.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
            output.insertBefore(NSBlocks.WHITE_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSArtsAndCraftsCompat.BLEACHED_CHALK_STAIRS.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
            output.insertBefore(NSBlocks.WHITE_CHALK_SLAB.get().asItem().getDefaultInstance(), NSArtsAndCraftsCompat.BLEACHED_CHALK_SLAB.get().asItem().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    private static void ingredients(TabOutput output) {
        output.insertAfter(Items.AMETHYST_SHARD.getDefaultInstance(), NSBlocks.CALCITE_SHARD.get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(Items.HONEYCOMB.getDefaultInstance(), NSBlocks.CHALK_POWDER.get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(Items.BOWL.getDefaultInstance(), NSBlocks.COCONUT_SHELL.get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
    }

    private static void foodAndDrinks(TabOutput output) {
        output.insertAfter(Items.BEETROOT.getDefaultInstance(), NSBlocks.COCONUT_HALF.get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.COCONUT_HALF.get().getDefaultInstance(), NSBlocks.DESERT_TURNIP.get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(NSBlocks.DESERT_TURNIP.get().getDefaultInstance(), NSBlocks.OLIVES.get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(Items.BREAD.getDefaultInstance(), NSBlocks.WHOLE_PIZZA.get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
        output.insertAfter(Items.MILK_BUCKET.getDefaultInstance(), NSBlocks.CHEESE_BUCKET.get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
    }

    private static void addAllAfterFirst(TabOutput output, List<ItemLike> itemStacks, TabVisibility tabVisibility) {
        for (int i = 1; i < itemStacks.size(); i++) {
            output.insertAfter(itemStacks.get(i - 1).asItem().getDefaultInstance(), itemStacks.get(i).asItem().getDefaultInstance(), tabVisibility);
        }
    }
}
