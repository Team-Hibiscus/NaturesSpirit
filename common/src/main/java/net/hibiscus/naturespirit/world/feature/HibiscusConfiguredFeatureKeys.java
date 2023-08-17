package net.hibiscus.naturespirit.world.feature;

import net.hibiscus.naturespirit.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class HibiscusConfiguredFeatureKeys {
   public static final ResourceKey <ConfiguredFeature <?, ?>> WISTERIA_DELTA = registerKey("water_delta");
   public static final ResourceKey <ConfiguredFeature <?, ?>> SWAMP_DELTA = registerKey("swamp_delta");
   public static final ResourceKey <ConfiguredFeature <?, ?>> RIVER_DELTA = registerKey("river_delta");

   public static final ResourceKey <ConfiguredFeature <?, ?>> LARGE_REDWOOD_TREE = registerKey("large_redwood_tree");
   public static final ResourceKey <ConfiguredFeature <?, ?>> REDWOOD_TREE = registerKey("redwood_tree");
   public static final ResourceKey <ConfiguredFeature <?, ?>> LARGE_REDWOOD_TREE_SPAWN = registerKey(
           "large_redwood_tree_spawn");
   public static final ResourceKey <ConfiguredFeature <?, ?>> REDWOOD_TREE_SPAWN = registerKey("redwood_tree_spawn");
   public static final ResourceKey <ConfiguredFeature <?, ?>> WILLOW_TREE = registerKey("willow_tree");
   public static final ResourceKey <ConfiguredFeature <?, ?>> WILLOW_TREE_SPAWN = registerKey("willow_tree_spawn");
   public static final ResourceKey <ConfiguredFeature <?, ?>> WHITE_WISTERIA_TREE = registerKey("white_wisteria_tree");
   public static final ResourceKey <ConfiguredFeature <?, ?>> BLUE_WISTERIA_TREE = registerKey("blue_wisteria_tree");
   public static final ResourceKey <ConfiguredFeature <?, ?>> PURPLE_WISTERIA_TREE = registerKey("purple_wisteria_tree");
   public static final ResourceKey <ConfiguredFeature <?, ?>> PINK_WISTERIA_TREE = registerKey("pink_wisteria_tree");
   public static final ResourceKey <ConfiguredFeature <?, ?>> ASPEN_TREE = registerKey("aspen_tree");
   public static final ResourceKey <ConfiguredFeature <?, ?>> ASPEN_TREE_BEES = registerKey("aspen_tree_bees");
   public static final ResourceKey <ConfiguredFeature <?, ?>> ASPEN_TREE_SPAWN = registerKey("aspen_tree_spawn");
   public static final ResourceKey <ConfiguredFeature <?, ?>> FIR_TREE = registerKey("fir_tree");
   public static final ResourceKey <ConfiguredFeature <?, ?>> FIR_TREE_SPAWN = registerKey("fir_tree_spawn");
   public static final ResourceKey <ConfiguredFeature <?, ?>> WISTERIA_SPAWN = registerKey("wisteria_spawn");
   public static final ResourceKey <ConfiguredFeature <?, ?>> SUGI_TREE = registerKey("sugi_tree");
   public static final ResourceKey <ConfiguredFeature <?, ?>> SUGI_SPAWN = registerKey("sugi_spawn");
   public static final ResourceKey <ConfiguredFeature <?, ?>> CYPRESS_TREE = registerKey("cypress_tree");
   public static final ResourceKey <ConfiguredFeature <?, ?>> CYPRESS_TREE_SPAWN = registerKey("cypress_tree_spawn");
   public static final ResourceKey <ConfiguredFeature <?, ?>> OLIVE_TREE = registerKey("olive_tree");
   public static final ResourceKey <ConfiguredFeature <?, ?>> OLIVE_TREE_SPAWN = registerKey("olive_tree_spawn");
   public static final ResourceKey <ConfiguredFeature <?, ?>> JOSHUA_TREE = registerKey("joshua_tree");
   public static final ResourceKey <ConfiguredFeature <?, ?>> JOSHUA_TREE_SPAWN = registerKey("joshua_tree_spawn");

   public static final ResourceKey <ConfiguredFeature <?, ?>> OAK_BUSH = registerKey("oak_bush");
   public static final ResourceKey <ConfiguredFeature <?, ?>> SPRUCE_BUSH = registerKey("spruce_bush");
   public static final ResourceKey <ConfiguredFeature <?, ?>> OAK_BUSH_SPAWN = registerKey("oak_bush_spawn");
   public static final ResourceKey <ConfiguredFeature <?, ?>> SPRUCE_BUSH_SPAWN = registerKey("spruce_bush_spawn");
   public static final ResourceKey <ConfiguredFeature <?, ?>> FANCY_OAK_TREE_SPAWN = registerKey("custom_fancy_oak_tree_spawn");

   public static final ResourceKey <ConfiguredFeature <?, ?>> FLOWER_WISTERIA_FOREST = registerKey("flower_wisteria_forest");
   public static final ResourceKey <ConfiguredFeature <?, ?>> FLOWER_SUGI_FOREST = registerKey("flower_sugi_forest");
   public static final ResourceKey <ConfiguredFeature <?, ?>> FLOWER_REDWOOD_FOREST = registerKey("flower_redwood_forest");
   public static final ResourceKey <ConfiguredFeature <?, ?>> FLOWER_LAVENDER_FIELD = registerKey("flower_lavender_field");
   public static final ResourceKey <ConfiguredFeature <?, ?>> FLOWER_FOXGLOVE_FIELD = registerKey("flower_foxglove_field");
   public static final ResourceKey <ConfiguredFeature <?, ?>> FLOWER_ERODED_RIVER = registerKey("flower_eroded_river");
   public static final ResourceKey <ConfiguredFeature <?, ?>> FLOWER_GOLDEN_WILDS = registerKey("flower_golden_wilds");
   public static final ResourceKey <ConfiguredFeature <?, ?>> FLOWER_FIR_FOREST = registerKey("flower_fir_forest");
   public static final ResourceKey <ConfiguredFeature <?, ?>> FLOWER_CYPRESS_FIELDS = registerKey("flower_cypress_fields");
   public static final ResourceKey <ConfiguredFeature <?, ?>> PATCH_SCORCHED_GRASS = registerKey("patch_scorched_grass");
   public static final ResourceKey <ConfiguredFeature <?, ?>> PATCH_TALL_SCORCHED_GRASS = registerKey("patch_tall_scorched_grass");
   public static final ResourceKey <ConfiguredFeature <?, ?>> FLOWER_BLOOMING_DUNES = registerKey("flower_blooming_dunes");
   public static final ResourceKey <ConfiguredFeature <?, ?>> FLOWER_STRATIFIED_DESERT = registerKey("flower_stratified_desert");
   public static final ResourceKey <ConfiguredFeature <?, ?>> CATTAILS = registerKey("cattails");
   public static final ResourceKey <ConfiguredFeature <?, ?>> DESERT_TURNIP_STEM = registerKey("desert_turnip_stem");
   public static final ResourceKey <ConfiguredFeature <?, ?>> ROOTED_DESERT_TURNIP = registerKey("rooted_desert_turnip");

   public static ResourceKey <ConfiguredFeature <?, ?>> registerKey(String name) {
      return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Constants.MOD_ID, name));
   }
}
