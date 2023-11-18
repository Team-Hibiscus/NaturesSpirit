package net.hibiscus.naturespirit.datagen;


import net.hibiscus.naturespirit.NatureSpirit;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

import java.util.HashMap;

public class HibiscusBiomes {

   public static HashMap<String, RegistryKey<Biome>> BiomesHashMap = new HashMap<>();
   public static final RegistryKey <Biome> SUGI_FOREST = register("sugi_forest");
   public static final RegistryKey <Biome> WINDSWEPT_SUGI_FOREST = register("windswept_sugi_forest");
   public static final RegistryKey <Biome> LAVENDER_FIELDS = register("lavender_fields");
   public static final RegistryKey <Biome> ERODED_RIVER = register("eroded_river");
   public static final RegistryKey <Biome> MARSH = register("marsh");
   public static final RegistryKey <Biome> BAMBOO_WETLANDS = register("bamboo_wetlands");
   public static final RegistryKey <Biome> WISTERIA_FOREST = register("wisteria_forest");
   public static final RegistryKey <Biome> REDWOOD_FOREST = register("redwood_forest");
   public static final RegistryKey <Biome> ASPEN_FOREST = register("aspen_forest");
   public static final RegistryKey <Biome> MAPLE_WOODLANDS = register("maple_woodlands");
   public static final RegistryKey <Biome> GOLDEN_WILDS = register("golden_wilds");
   public static final RegistryKey <Biome> MARIGOLD_MEADOWS = register("marigold_meadows");
   public static final RegistryKey <Biome> FIR_FOREST = register("fir_forest");
   public static final RegistryKey <Biome> SNOWY_FIR_FOREST = register("snowy_fir_forest");
   public static final RegistryKey <Biome> CYPRESS_FIELDS = register("cypress_fields");
   public static final RegistryKey <Biome> CARNATION_FIELDS = register("carnation_fields");
   public static final RegistryKey <Biome> STRATIFIED_DESERT = register("stratified_desert");
   public static final RegistryKey <Biome> BLOOMING_DUNES = register("blooming_dunes");
   public static final RegistryKey <Biome> LIVELY_DUNES = register("lively_dunes");
   public static final RegistryKey <Biome> DRYLANDS = register("drylands");
   public static final RegistryKey <Biome> WOODED_DRYLANDS = register("wooded_drylands");
   public static final RegistryKey <Biome> XERIC_PLAINS = register("xeric_plains");
   public static final RegistryKey <Biome> WHITE_CLIFFS = register("white_cliffs");
   public static final RegistryKey <Biome> PRAIRIE = register("prairie");
   public static final RegistryKey <Biome> HEATHER_FIELDS = register("heather_fields");
   public static final RegistryKey <Biome> TUNDRA = register("tundra");
   public static final RegistryKey <Biome> ALPINE_CLEARINGS = register("alpine_clearings");
   public static final RegistryKey <Biome> ALPINE_HIGHLANDS = register("alpine_highlands");
   public static final RegistryKey <Biome> CONIFEROUS_COVERT = register("coniferous_covert");
   public static final RegistryKey <Biome> AMBER_COVERT = register("amber_covert");
   public static final RegistryKey <Biome> BOREAL_TAIGA = register("boreal_taiga");
   public static final RegistryKey <Biome> TROPICAL_SHORES = register("tropical_shores");


   public static boolean has_sugi_forest;
   public static boolean has_eroded_river;
   public static boolean has_marsh;
   public static boolean has_bamboo_wetlands;
   public static boolean has_wisteria_forest;
   public static boolean has_redwood_forest;
   public static boolean has_aspen_forest;
   public static boolean has_maple;
   public static boolean has_fir;
   public static boolean has_larch;
   public static boolean has_cypress_fields;
   public static boolean has_lively_dunes;
   public static boolean has_drylands;
   public static boolean has_xeric_plains;
   public static boolean has_white_cliffs;
   public static boolean has_tropical_shores;

   public static void set_has_sugi_forest(Boolean bl) {
      has_sugi_forest = bl;
   };
   public static void set_has_eroded_river(Boolean bl) {
      has_eroded_river = bl;
   };
   public static void set_has_marsh(Boolean bl) {
      has_marsh = bl;
   };
   public static void set_has_bamboo_wetlands(Boolean bl) {
      has_bamboo_wetlands = bl;
   };
   public static void set_has_wisteria_forest(Boolean bl) {
      has_wisteria_forest = bl;
   };
   public static void set_has_redwood_forest(Boolean bl) {
      has_redwood_forest = bl;
   };
   public static void set_has_aspen_forest(Boolean bl) {
      has_aspen_forest = bl;
   };
   public static void set_has_maple(Boolean bl) {
      has_maple = bl;
   };
   public static void set_has_fir(Boolean bl) {
      has_fir = bl;
   };
   public static void set_has_cypress_fields(Boolean bl) {
      has_cypress_fields = bl;
   };
   public static void set_has_lively_dunes(Boolean bl) {
      has_lively_dunes = bl;
   };
   public static void set_has_drylands(Boolean bl) {
      has_drylands = bl;
   };
   public static void set_has_white_cliffs(Boolean bl) {
      has_white_cliffs = bl;
   };
   public static void set_has_tropical_shores(Boolean bl) {
      has_tropical_shores = bl;
   };
   public static void set_has_xeric_plains(Boolean bl) {
      has_xeric_plains = bl;
   };
   public static void set_has_larch(Boolean bl) {
      has_larch = bl;
   };

   private static RegistryKey <Biome> register(String name) {
      System.out.println("Registered Resource Key for biome: " + name);
      RegistryKey<Biome> registryKey = RegistryKey.of(RegistryKeys.BIOME, new Identifier(NatureSpirit.MOD_ID, name));
      BiomesHashMap.put(name, registryKey);
      return registryKey;
   }
   public static void registerBiomes() {
   }
}