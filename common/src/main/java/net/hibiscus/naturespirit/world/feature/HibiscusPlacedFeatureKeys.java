package net.hibiscus.naturespirit.world.feature;

import net.hibiscus.naturespirit.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class HibiscusPlacedFeatureKeys {
   public static final ResourceKey <PlacedFeature> LARGE_REDWOOD_CHECKED = registerKey("large_redwood_checked");
   public static final ResourceKey <PlacedFeature> REDWOOD_CHECKED = registerKey("redwood_checked");
   public static final ResourceKey <PlacedFeature> ASPEN_CHECKED = registerKey("aspen_checked");
   public static final ResourceKey <PlacedFeature> ASPEN_BEES_CHECKED = registerKey("aspen_bees_checked");
   public static final ResourceKey <PlacedFeature> CYPRESS_CHECKED = registerKey("cypress_checked");
   public static final ResourceKey <PlacedFeature> FIR_CHECKED = registerKey("fir_checked");
   public static final ResourceKey <PlacedFeature> WILLOW_CHECKED = registerKey("willow_checked");
   public static final ResourceKey <PlacedFeature> WILLOW_PLACED = registerKey("willow_placed");
   public static final ResourceKey <PlacedFeature> WHITE_WISTERIA_CHECKED = registerKey("white_wisteria_checked");
   public static final ResourceKey <PlacedFeature> BLUE_WISTERIA_CHECKED = registerKey("blue_wisteria_checked");
   public static final ResourceKey <PlacedFeature> PINK_WISTERIA_CHECKED = registerKey("pink_wisteria_checked");
   public static final ResourceKey <PlacedFeature> PURPLE_WISTERIA_CHECKED = registerKey("purple_wisteria_checked");
   public static final ResourceKey <PlacedFeature> SUGI_CHECKED = registerKey("sugi_checked");
   public static final ResourceKey <PlacedFeature> OLIVE_CHECKED = registerKey("olive_checked");
   public static final ResourceKey <PlacedFeature> JOSHUA_CHECKED = registerKey("joshua_checked");

   public static final ResourceKey <PlacedFeature> OAK_BUSH_CHECKED = registerKey("oak_bush_checked");
   public static final ResourceKey <PlacedFeature> SPRUCE_BUSH_CHECKED = registerKey("spruce_bush_checked");

   public static final ResourceKey <PlacedFeature> FLOWER_WISTERIA_PLACED = registerKey("flower_wisteria_placed");
   public static final ResourceKey <PlacedFeature> FLOWER_SUGI_PLACED = registerKey("flower_sugi_placed");
   public static final ResourceKey <PlacedFeature> FLOWER_REDWOOD_PLACED = registerKey("flower_redwood_placed");
   public static final ResourceKey <PlacedFeature> FLOWER_FIR_PLACED = registerKey("flower_fir_placed");
   public static final ResourceKey <PlacedFeature> FLOWER_LAVENDER_PLACED = registerKey("flower_lavender_placed");
   public static final ResourceKey <PlacedFeature> FLOWER_FOXGLOVE_PLACED = registerKey("flower_foxglove_placed");
   public static final ResourceKey <PlacedFeature> FLOWER_RIVER_PLACED = registerKey("flower_river_placed");
   public static final ResourceKey <PlacedFeature> FLOWER_GOLDEN_PLACED = registerKey("flower_golden_placed");
   public static final ResourceKey <PlacedFeature> FLOWER_GOLDEN2_PLACED = registerKey("flower_golden2_placed");
   public static final ResourceKey <PlacedFeature> FLOWER_CYPRESS_PLACED = registerKey("flower_cypress_placed");
   public static final ResourceKey <PlacedFeature> FLOWER_CARNATION_PLACED = registerKey("flower_carnation_placed");
   public static final ResourceKey <PlacedFeature> PATCH_SCORCHED_GRASS_PLACED = registerKey("patch_scorched_grass_placed");
   public static final ResourceKey <PlacedFeature> PATCH_TALL_SCORCHED_GRASS_PLACED = registerKey("patch_tall_scorched_grass_placed");
   public static final ResourceKey <PlacedFeature> FLOWER_BLOOMING_DUNES_PLACED = registerKey("flower_blooming_dunes_placed");
   public static final ResourceKey <PlacedFeature> FLOWER_STRATIFIED_DESERT_PLACED = registerKey("flower_stratified_desert_placed");

   public static final ResourceKey <PlacedFeature> WISTERIA_WATER = registerKey("wisteria_water_placed");
   public static final ResourceKey <PlacedFeature> LAVENDER_WATER = registerKey("lavender_water_placed");
   public static final ResourceKey <PlacedFeature> SWAMP_WATER = registerKey("swamp_water_placed");
   public static final ResourceKey <PlacedFeature> RIVER_WATER = registerKey("river_water_placed");

   public static final ResourceKey <PlacedFeature> LARGE_REDWOOD_PLACED = registerKey("large_redwood_placed");
   public static final ResourceKey <PlacedFeature> REDWOOD_PLACED = registerKey("redwood_placed");
   public static final ResourceKey <PlacedFeature> ASPEN_PLACED = registerKey("aspen_placed");
   public static final ResourceKey <PlacedFeature> FEW_ASPEN_PLACED = registerKey("few_aspen_placed");
   public static final ResourceKey <PlacedFeature> DENSE_CYPRESS_PLACED = registerKey("dense_cypress_placed");
   public static final ResourceKey <PlacedFeature> CYPRESS_PLACED = registerKey("cypress_placed");
   public static final ResourceKey <PlacedFeature> FIR_PLACED = registerKey("fir_placed");
   public static final ResourceKey <PlacedFeature> DENSE_FIR_PLACED = registerKey("dense_fir_placed");
   public static final ResourceKey <PlacedFeature> SPRUCE_BUSH_PLACED = registerKey("spruce_bush_placed");
   public static final ResourceKey <PlacedFeature> REDWOOD_ROCK_PLACED = registerKey("redwood_rock_placed");
   public static final ResourceKey <PlacedFeature> WISTERIA_PLACED = registerKey("wisteria_placed");
   public static final ResourceKey <PlacedFeature> SUGI_PLACED = registerKey("sugi_placed");
   public static final ResourceKey <PlacedFeature> OLIVE_PLACED = registerKey("olive_placed");
   public static final ResourceKey <PlacedFeature> JOSHUA_PLACED = registerKey("joshua_placed");

   public static final ResourceKey <PlacedFeature> OAK_BUSH_PLACED = registerKey("oak_bush_placed");
   public static final ResourceKey <PlacedFeature> CUSTOM_FANCY_OAK_TREE_PLACED = registerKey(
           "custom_fancy_oak_tree_placed");
   public static final ResourceKey <PlacedFeature> CUSTOM_FANCY_OAK_TREE2_PLACED = registerKey(
           "custom_fancy_oak_tree2_placed");
   public static final ResourceKey <PlacedFeature> CATTAILS = registerKey("cattails_placed");
   public static final ResourceKey <PlacedFeature> ROOTED_DESERT_TURNIP = registerKey("rooted_desert_turnip_placed");

   public static ResourceKey <PlacedFeature> registerKey(String name) {
      return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Constants.MOD_ID, name));
   }
}
