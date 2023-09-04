package net.hibiscus.naturespirit.datagen;


import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.world.NatureSpiritOverworldBiomes;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.HashMap;

public class HibiscusBiomes {

   public static HashMap<String, RegistryKey<Biome>> BiomesHashMap = new HashMap<>();
   public static final RegistryKey <Biome> SUGI_FOREST = register("sugi_forest");
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
   public static final RegistryKey <Biome> CYPRESS_FIELDS = register("cypress_fields");
   public static final RegistryKey <Biome> CARNATION_FIELDS = register("carnation_fields");
   public static final RegistryKey <Biome> STRATIFIED_DESERT = register("stratified_desert");
   public static final RegistryKey <Biome> BLOOMING_DUNES = register("blooming_dunes");
   public static final RegistryKey <Biome> LIVELY_DUNES = register("lively_dunes");
   public static final RegistryKey <Biome> WHITE_CLIFFS = register("white_cliffs");
   public static final RegistryKey <Biome> PRAIRIE = register("prairie");

   private static RegistryKey <Biome> register(String name) {
      System.out.println("Registered Resource Key for biome: " + name);
      RegistryKey<Biome> registryKey = RegistryKey.of(RegistryKeys.BIOME, new Identifier(NatureSpirit.MOD_ID, name));
      BiomesHashMap.put(name, registryKey);
      return registryKey;
   }


   public static void bootstrap(Registerable <Biome> bootstapContext) {
      RegistryEntryLookup <PlacedFeature> holderGetter = bootstapContext.getRegistryLookup(RegistryKeys.PLACED_FEATURE);
      RegistryEntryLookup <ConfiguredCarver <?>> holderGetter2 = bootstapContext.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER);


      register(bootstapContext, WISTERIA_FOREST, NatureSpiritOverworldBiomes.wisteriaForest(holderGetter, holderGetter2));
      register(bootstapContext, ERODED_RIVER, NatureSpiritOverworldBiomes.erodedRiver(holderGetter, holderGetter2));
      register(bootstapContext, REDWOOD_FOREST, NatureSpiritOverworldBiomes.redwoodForest(holderGetter, holderGetter2));
      register(bootstapContext, FIR_FOREST, NatureSpiritOverworldBiomes.firForest(holderGetter, holderGetter2));
   }

   private static void register(Registerable <Biome> context, RegistryKey <Biome> key, Biome biome) {
      System.out.println("Registered Bootstrap for Biome");
      context.register(key, biome);
   }


   public static void registerBiomes() {
   }
}