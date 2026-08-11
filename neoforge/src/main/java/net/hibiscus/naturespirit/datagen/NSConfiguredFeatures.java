package net.hibiscus.naturespirit.datagen;

import net.hibiscus.naturespirit.NatureSpirit;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class NSConfiguredFeatures {

  public static final ResourceKey<ConfiguredFeature<?, ?>> RED_MOSS_PATCH_BONEMEAL = registerKey("red_moss_patch_bonemeal");
  public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_REDWOOD_TREE = registerKey("large_redwood_tree");
  public static final ResourceKey<ConfiguredFeature<?, ?>> REDWOOD_TREE = registerKey("redwood_tree");
  public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_FROSTY_REDWOOD_TREE = registerKey("large_frosty_redwood_tree");
  public static final ResourceKey<ConfiguredFeature<?, ?>> FROSTY_REDWOOD_TREE = registerKey("frosty_redwood_tree");
  public static final ResourceKey<ConfiguredFeature<?, ?>> WILLOW_TREE = registerKey("willow_tree");
  public static final ResourceKey<ConfiguredFeature<?, ?>> WHITE_WISTERIA_TREE = registerKey("white_wisteria_tree");
  public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_WISTERIA_TREE = registerKey("blue_wisteria_tree");
  public static final ResourceKey<ConfiguredFeature<?, ?>> PURPLE_WISTERIA_TREE = registerKey("purple_wisteria_tree");
  public static final ResourceKey<ConfiguredFeature<?, ?>> PINK_WISTERIA_TREE = registerKey("pink_wisteria_tree");
  public static final ResourceKey<ConfiguredFeature<?, ?>> ASPEN_TREE = registerKey("aspen_tree");
  public static final ResourceKey<ConfiguredFeature<?, ?>> ASPEN_TREE_BEES = registerKey("aspen_tree_bees");
  public static final ResourceKey<ConfiguredFeature<?, ?>> YELLOW_ASPEN_TREE = registerKey("yellow_aspen_tree");
  public static final ResourceKey<ConfiguredFeature<?, ?>> YELLOW_ASPEN_TREE_BEES = registerKey("yellow_aspen_tree_bees");
  public static final ResourceKey<ConfiguredFeature<?, ?>> RED_MAPLE_TREE = registerKey("red_maple_tree");
  public static final ResourceKey<ConfiguredFeature<?, ?>> ORANGE_MAPLE_TREE = registerKey("orange_maple_tree");
  public static final ResourceKey<ConfiguredFeature<?, ?>> YELLOW_MAPLE_TREE = registerKey("yellow_maple_tree");
  public static final ResourceKey<ConfiguredFeature<?, ?>> FIR_TREE = registerKey("fir_tree");
  public static final ResourceKey<ConfiguredFeature<?, ?>> LARCH_TREE = registerKey("larch_tree");
  public static final ResourceKey<ConfiguredFeature<?, ?>> SUGI_TREE = registerKey("sugi_tree");
  public static final ResourceKey<ConfiguredFeature<?, ?>> CYPRESS_TREE = registerKey("cypress_tree");
  public static final ResourceKey<ConfiguredFeature<?, ?>> OLIVE_TREE = registerKey("olive_tree");
  public static final ResourceKey<ConfiguredFeature<?, ?>> GHAF_TREE = registerKey("ghaf_tree");
  public static final ResourceKey<ConfiguredFeature<?, ?>> PALO_VERDE_TREE = registerKey("palo_verde_tree");
  public static final ResourceKey<ConfiguredFeature<?, ?>> MAHOGANY_TREE = registerKey("mahogany_tree");
  public static final ResourceKey<ConfiguredFeature<?, ?>> SAXAUL_TREE = registerKey("saxaul_tree");
  public static final ResourceKey<ConfiguredFeature<?, ?>> JOSHUA_TREE = registerKey("joshua_tree");
  public static final ResourceKey<ConfiguredFeature<?, ?>> COCONUT_TREE = registerKey("coconut_tree");
  public static final ResourceKey<ConfiguredFeature<?, ?>> CEDAR_TREE = registerKey("cedar_tree");
  public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_SHIITAKE_MUSHROOM = registerKey("huge_shiitake_mushroom");
  public static final ResourceKey<ConfiguredFeature<?, ?>> GRAY_POLYPORE = registerKey("gray_polypore");

  public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
    return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, name));
  }
}


