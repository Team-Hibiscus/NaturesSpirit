package net.hibiscus.naturespirit.registration;


import net.hibiscus.naturespirit.NatureSpirit;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

import java.util.HashMap;

public class NSBiomes {

  public static HashMap<String, ResourceKey<Biome>> BiomesHashMap = new HashMap<>();
  public static final ResourceKey<Biome> SUGI_FOREST = register("sugi_forest");
  public static final ResourceKey<Biome> WINDSWEPT_SUGI_FOREST = register("windswept_sugi_forest");
  public static final ResourceKey<Biome> BLOOMING_SUGI_FOREST = register("blooming_sugi_forest");
  public static final ResourceKey<Biome> LAVENDER_FIELDS = register("lavender_fields");
  public static final ResourceKey<Biome> MARSH = register("marsh");
  public static final ResourceKey<Biome> BAMBOO_WETLANDS = register("bamboo_wetlands");
  public static final ResourceKey<Biome> WISTERIA_FOREST = register("wisteria_forest");
  public static final ResourceKey<Biome> REDWOOD_FOREST = register("redwood_forest");
  public static final ResourceKey<Biome> SNOWY_REDWOOD_FOREST = register("snowy_redwood_forest");
  public static final ResourceKey<Biome> ASPEN_FOREST = register("aspen_forest");
  public static final ResourceKey<Biome> MAPLE_WOODLANDS = register("maple_woodlands");
  public static final ResourceKey<Biome> GOLDEN_WILDS = register("golden_wilds");
  public static final ResourceKey<Biome> MARIGOLD_MEADOWS = register("marigold_meadows");
  public static final ResourceKey<Biome> FIR_FOREST = register("fir_forest");
  public static final ResourceKey<Biome> SNOWY_FIR_FOREST = register("snowy_fir_forest");
  public static final ResourceKey<Biome> CYPRESS_FIELDS = register("cypress_fields");
  public static final ResourceKey<Biome> CARNATION_FIELDS = register("carnation_fields");
  public static final ResourceKey<Biome> STRATIFIED_DESERT = register("stratified_desert");
  public static final ResourceKey<Biome> BLOOMING_DUNES = register("blooming_dunes");
  public static final ResourceKey<Biome> LIVELY_DUNES = register("lively_dunes");
  public static final ResourceKey<Biome> DRYLANDS = register("drylands");
  public static final ResourceKey<Biome> WOODED_DRYLANDS = register("wooded_drylands");
  public static final ResourceKey<Biome> XERIC_PLAINS = register("xeric_plains");
  public static final ResourceKey<Biome> CEDAR_THICKET = register("cedar_thicket");
  public static final ResourceKey<Biome> WHITE_CLIFFS = register("white_cliffs");
  public static final ResourceKey<Biome> PRAIRIE = register("prairie");
  public static final ResourceKey<Biome> OAK_SAVANNA = register("oak_savanna");
  public static final ResourceKey<Biome> HEATHER_FIELDS = register("heather_fields");
  public static final ResourceKey<Biome> TUNDRA = register("tundra");
  public static final ResourceKey<Biome> ALPINE_CLEARINGS = register("alpine_clearings");
  public static final ResourceKey<Biome> ALPINE_HIGHLANDS = register("alpine_highlands");
  public static final ResourceKey<Biome> CONIFEROUS_COVERT = register("coniferous_covert");
  public static final ResourceKey<Biome> BOREAL_TAIGA = register("boreal_taiga");
  public static final ResourceKey<Biome> TROPICAL_SHORES = register("tropical_shores");
  public static final ResourceKey<Biome> TROPICAL_WOODS = register("tropical_woods");
  public static final ResourceKey<Biome> SPARSE_TROPICAL_WOODS = register("sparse_tropical_woods");
  public static final ResourceKey<Biome> TROPICAL_BASIN = register("tropical_basin");
  public static final ResourceKey<Biome> ARID_SAVANNA = register("arid_savanna");
  public static final ResourceKey<Biome> SCORCHED_DUNES = register("scorched_dunes");
  public static final ResourceKey<Biome> FLOWERING_SHRUBLAND = register("flowering_shrubland");
  public static final ResourceKey<Biome> SHRUBLAND = register("shrubland");
  public static final ResourceKey<Biome> ARID_HIGHLANDS = register("arid_highlands");
  public static final ResourceKey<Biome> SHRUBBY_HIGHLANDS = register("shrubby_highlands");
  public static final ResourceKey<Biome> WOODY_HIGHLANDS = register("woody_highlands");
  public static final ResourceKey<Biome> RED_PEAKS = register("red_peaks");
  public static final ResourceKey<Biome> DUSTY_SLOPES = register("dusty_slopes");
  public static final ResourceKey<Biome> SNOWCAPPED_RED_PEAKS = register("snowcapped_red_peaks");
  public static final ResourceKey<Biome> SLEETED_SLOPES = register("sleeted_slopes");
  public static final ResourceKey<Biome> BLOOMING_HIGHLANDS = register("blooming_highlands");
  public static final ResourceKey<Biome> CHAPARRAL = register("chaparral");
  public static final ResourceKey<Biome> FLORAL_RIDGES = register("floral_ridges");

  private static ResourceKey<Biome> register(String name) {
    System.out.println("Registered Resource Key for biome: " + name);
    ResourceKey<Biome> registryKey = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, name));
    BiomesHashMap.put(name, registryKey);
    return registryKey;
  }
}
