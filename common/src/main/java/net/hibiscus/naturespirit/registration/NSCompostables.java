package net.hibiscus.naturespirit.registration;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.world.level.ItemLike;

public final class NSCompostables {

    public static final List<Entry> COMPOSTABLES = new ArrayList<>();

    static {
        add(NSBlocks.RED_MOSS_BLOCK, 0.65F);
        add(NSBlocks.RED_MOSS_CARPET, 0.3F);
        add(NSBlocks.LOTUS_FLOWER_ITEM, 0.65F);
        add(NSBlocks.LOTUS_STEM, 0.2F);
        add(NSBlocks.AZOLLA_ITEM, 0.3F);
        add(NSBlocks.HELVOLA_FLOWER_ITEM, 0.3F);
        add(NSBlocks.HELVOLA_PAD_ITEM, 0.3F);
        add(NSBlocks.DESERT_TURNIP, 0.3F);
        add(NSBlocks.DESERT_TURNIP_BLOCK, 0.65F);
        add(NSBlocks.DESERT_TURNIP_ROOT_BLOCK, 0.5F);
        add(NSBlocks.WHOLE_PIZZA, 1.0F);
        add(NSBlocks.THREE_QUARTERS_PIZZA, 0.85F);
        add(NSBlocks.HALF_PIZZA, 0.5F);
        add(NSBlocks.QUARTER_PIZZA, 0.35F);
        add(NSBlocks.AUREATE_SUCCULENT_ITEM, 0.65F);
        add(NSBlocks.DROWSY_SUCCULENT_ITEM, 0.65F);
        add(NSBlocks.FOAMY_SUCCULENT_ITEM, 0.65F);
        add(NSBlocks.SAGE_SUCCULENT_ITEM, 0.65F);
        add(NSBlocks.IMPERIAL_SUCCULENT_ITEM, 0.65F);
        add(NSBlocks.ORNATE_SUCCULENT_ITEM, 0.65F);
        add(NSBlocks.REGAL_SUCCULENT_ITEM, 0.65F);
        add(NSBlocks.COCONUT_THATCH, 0.65F);
        add(NSBlocks.COCONUT_THATCH_CARPET, 0.1F);
        add(NSBlocks.COCONUT_THATCH_STAIRS, 0.5F);
        add(NSBlocks.COCONUT_THATCH_SLAB, 0.3F);
        add(NSBlocks.XERIC_THATCH, 0.65F);
        add(NSBlocks.XERIC_THATCH_CARPET, 0.1F);
        add(NSBlocks.XERIC_THATCH_STAIRS, 0.5F);
        add(NSBlocks.XERIC_THATCH_SLAB, 0.3F);
        add(NSBlocks.EVERGREEN_THATCH, 0.65F);
        add(NSBlocks.EVERGREEN_THATCH_CARPET, 0.1F);
        add(NSBlocks.EVERGREEN_THATCH_STAIRS, 0.5F);
        add(NSBlocks.EVERGREEN_THATCH_SLAB, 0.3F);
        add(NSBlocks.REDWOOD.getSapling(), 0.3F);
        add(NSBlocks.SUGI.getSapling(), 0.3F);
        add(NSBlocks.WISTERIA.getPurpleSapling(), 0.3F);
        add(NSBlocks.WISTERIA.getWhiteSapling(), 0.3F);
        add(NSBlocks.WISTERIA.getBlueSapling(), 0.3F);
        add(NSBlocks.WISTERIA.getPinkSapling(), 0.3F);
        add(NSBlocks.FIR.getSapling(), 0.3F);
        add(NSBlocks.WILLOW.getSapling(), 0.3F);
        add(NSBlocks.ASPEN.getSapling(), 0.3F);
        add(NSBlocks.MAPLE.getRedSapling(), 0.3F);
        add(NSBlocks.MAPLE.getOrangeSapling(), 0.3F);
        add(NSBlocks.MAPLE.getYellowSapling(), 0.3F);
        add(NSBlocks.CYPRESS.getSapling(), 0.3F);
        add(NSBlocks.OLIVE.getSapling(), 0.3F);
        add(NSBlocks.JOSHUA.getSapling(), 0.3F);
        add(NSBlocks.GHAF.getSapling(), 0.3F);
        add(NSBlocks.PALO_VERDE.getSapling(), 0.3F);
        add(NSBlocks.CEDAR.getSapling(), 0.3F);
        add(NSBlocks.LARCH.getSapling(), 0.3F);
        add(NSBlocks.MAHOGANY.getSapling(), 0.3F);
        add(NSBlocks.SAXAUL.getSapling(), 0.3F);
        add(NSBlocks.REDWOOD.getLeaves(), 0.3F);
        add(NSBlocks.REDWOOD.getFrostyLeaves(), 0.3F);
        add(NSBlocks.SUGI.getLeaves(), 0.3F);
        add(NSBlocks.WISTERIA.getPurpleLeaves(), 0.3F);
        add(NSBlocks.WISTERIA.getWhiteLeaves(), 0.3F);
        add(NSBlocks.WISTERIA.getBlueLeaves(), 0.3F);
        add(NSBlocks.WISTERIA.getPinkLeaves(), 0.3F);
        add(NSBlocks.WISTERIA.getPartPurpleLeaves(), 0.3F);
        add(NSBlocks.WISTERIA.getPartWhiteLeaves(), 0.3F);
        add(NSBlocks.WISTERIA.getPartBlueLeaves(), 0.3F);
        add(NSBlocks.WISTERIA.getPartPinkLeaves(), 0.3F);
        add(NSBlocks.FIR.getLeaves(), 0.3F);
        add(NSBlocks.FIR.getFrostyLeaves(), 0.3F);
        add(NSBlocks.WILLOW.getLeaves(), 0.3F);
        add(NSBlocks.ASPEN.getLeaves(), 0.3F);
        add(NSBlocks.ASPEN.getYellowLeaves(), 0.3F);
        add(NSBlocks.MAPLE.getRedLeaves(), 0.3F);
        add(NSBlocks.MAPLE.getOrangeLeaves(), 0.3F);
        add(NSBlocks.MAPLE.getYellowLeaves(), 0.3F);
        add(NSBlocks.CYPRESS.getLeaves(), 0.3F);
        add(NSBlocks.OLIVE.getLeaves(), 0.3F);
        add(NSBlocks.JOSHUA.getLeaves(), 0.3F);
        add(NSBlocks.GHAF.getLeaves(), 0.3F);
        add(NSBlocks.PALO_VERDE.getLeaves(), 0.3F);
        add(NSBlocks.CEDAR.getLeaves(), 0.3F);
        add(NSBlocks.COCONUT.getLeaves(), 0.3F);
        add(NSBlocks.LARCH.getLeaves(), 0.3F);
        add(NSBlocks.MAHOGANY.getLeaves(), 0.3F);
        add(NSBlocks.SAXAUL.getLeaves(), 0.3F);
        add(NSBlocks.WISTERIA.getPurpleVines(), 0.3F);
        add(NSBlocks.WISTERIA.getWhiteVines(), 0.3F);
        add(NSBlocks.WISTERIA.getBlueVines(), 0.3F);
        add(NSBlocks.WISTERIA.getPinkVines(), 0.3F);
        add(NSBlocks.WILLOW.getVines(), 0.3F);
        add(NSBlocks.LAVENDER.getFlowerBlock(), 0.4F);
        add(NSBlocks.BLEEDING_HEART.getFlowerBlock(), 0.4F);
        add(NSBlocks.BLUE_BULBS.getFlowerBlock(), 0.4F);
        add(NSBlocks.CARNATION.getFlowerBlock(), 0.4F);
        add(NSBlocks.GARDENIA.getFlowerBlock(), 0.4F);
        add(NSBlocks.SNAPDRAGON.getFlowerBlock(), 0.4F);
        add(NSBlocks.FOXGLOVE.getFlowerBlock(), 0.4F);
        add(NSBlocks.BEGONIA.getFlowerBlock(), 0.4F);
        add(NSBlocks.MARIGOLD.getFlowerBlock(), 0.4F);
        add(NSBlocks.BLUEBELL.getFlowerBlock(), 0.4F);
        add(NSBlocks.TIGER_LILY.getFlowerBlock(), 0.4F);
        add(NSBlocks.PURPLE_WILDFLOWER.getFlowerBlock(), 0.4F);
        add(NSBlocks.YELLOW_WILDFLOWER.getFlowerBlock(), 0.4F);
        add(NSBlocks.RED_HEATHER.getFlowerBlock(), 0.4F);
        add(NSBlocks.WHITE_HEATHER.getFlowerBlock(), 0.4F);
        add(NSBlocks.PURPLE_HEATHER.getFlowerBlock(), 0.4F);
        add(NSBlocks.ANEMONE.getFlowerBlock(), 0.3F);
        add(NSBlocks.DWARF_BLOSSOMS.getFlowerBlock(), 0.3F);
        add(NSBlocks.PROTEA.getFlowerBlock(), 0.3F);
        add(NSBlocks.HIBISCUS.getFlowerBlock(), 0.3F);
        add(NSBlocks.BLUE_IRIS.getFlowerBlock(), 0.3F);
        add(NSBlocks.BLACK_IRIS.getFlowerBlock(), 0.3F);
        add(NSBlocks.RUBY_BLOSSOMS.getFlowerBlock(), 0.4F);
        add(NSBlocks.SILVERBUSH.getFlowerBlock(), 0.4F);
        add(NSBlocks.CATTAIL, 0.4F);
        add(NSBlocks.TALL_FRIGID_GRASS, 0.3F);
        add(NSBlocks.FRIGID_GRASS, 0.3F);
        add(NSBlocks.TALL_SCORCHED_GRASS, 0.3F);
        add(NSBlocks.SCORCHED_GRASS, 0.3F);
        add(NSBlocks.TALL_BEACH_GRASS, 0.3F);
        add(NSBlocks.BEACH_GRASS, 0.3F);
        add(NSBlocks.TALL_SEDGE_GRASS, 0.3F);
        add(NSBlocks.SEDGE_GRASS, 0.3F);
        add(NSBlocks.LARGE_FLAXEN_FERN, 0.3F);
        add(NSBlocks.FLAXEN_FERN, 0.3F);
        add(NSBlocks.TALL_OAT_GRASS, 0.3F);
        add(NSBlocks.OAT_GRASS, 0.3F);
        add(NSBlocks.LARGE_LUSH_FERN, 0.3F);
        add(NSBlocks.LUSH_FERN, 0.3F);
        add(NSBlocks.TALL_MELIC_GRASS, 0.3F);
        add(NSBlocks.MELIC_GRASS, 0.3F);
        add(NSBlocks.GREEN_BEARBERRIES, 0.3F);
        add(NSBlocks.RED_BEARBERRIES, 0.3F);
        add(NSBlocks.PURPLE_BEARBERRIES, 0.3F);
        add(NSBlocks.GREEN_BITTER_SPROUTS, 0.3F);
        add(NSBlocks.RED_BITTER_SPROUTS, 0.3F);
        add(NSBlocks.PURPLE_BITTER_SPROUTS, 0.3F);
        add(NSBlocks.OLIVES, 0.3F);
        add(NSBlocks.OLIVE_BRANCH, 0.5F);
        add(NSBlocks.COCONUT_BLOCK, 0.2F);
        add(NSBlocks.COCONUT_SPROUT, 0.2F);
        add(NSBlocks.COCONUT_SHELL, 0.1F);
        add(NSBlocks.COCONUT_HALF, 0.1F);
        add(NSBlocks.SHIITAKE_MUSHROOM, 0.1F);
        add(NSBlocks.GRAY_POLYPORE, 0.1F);
        add(NSBlocks.ALLUAUDIA, 0.2F);
        add(NSBlocks.ALLUAUDIA_BUNDLE, 0.2F);
        add(NSBlocks.STRIPPED_ALLUAUDIA, 0.2F);
        add(NSBlocks.STRIPPED_ALLUAUDIA_BUNDLE, 0.2F);
    }

    private NSCompostables() {
    }

    public static void add(Supplier<? extends ItemLike> item, float chance) {
        COMPOSTABLES.add(new Entry(item, chance));
    }

    public record Entry(Supplier<? extends ItemLike> item, float chance) {
    }
}
