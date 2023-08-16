package net.hibiscus.naturespirit.datagen;


import net.hibiscus.naturespirit.Constants;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class HibiscusBiomes {

   public static final ResourceKey <Biome> SUGI_FOREST = register("sugi_forest");
   public static final ResourceKey <Biome> LAVENDER_FIELDS = register("lavender_fields");
   public static final ResourceKey <Biome> FOXGLOVE_FIELDS = register("foxglove_fields");
   public static final ResourceKey <Biome> ERODED_RIVER = register("eroded_river");
   public static final ResourceKey <Biome> WISTERIA_FOREST = register("wisteria_forest");
   public static final ResourceKey <Biome> REDWOOD_FOREST = register("redwood_forest");
   public static final ResourceKey <Biome> GOLDEN_WILDS = register("golden_wilds");
   public static final ResourceKey <Biome> GOLDEN_FIELDS = register("golden_fields");
   public static final ResourceKey <Biome> FIR_FOREST = register("fir_forest");
   public static final ResourceKey <Biome> CYPRESS_FIELDS = register("cypress_fields");
   public static final ResourceKey <Biome> CARNATION_FIELDS = register("carnation_fields");
   public static final ResourceKey <Biome> STRATIFIED_DESERT = register("stratified_desert");
   public static final ResourceKey <Biome> BLOOMING_DUNES = register("blooming_dunes");
   public static final ResourceKey <Biome> LIVELY_DUNES = register("lively_dunes");

   private static ResourceKey <Biome> register(String name) {
      System.out.println("Registered Resource Key for biome: " + name);
      return ResourceKey.create(Registries.BIOME, new ResourceLocation(Constants.MOD_ID, name));
   }


   public static void bootstrap(BootstapContext <Biome> bootstapContext) {
      HolderGetter <PlacedFeature> holderGetter = bootstapContext.lookup(Registries.PLACED_FEATURE);
      HolderGetter <ConfiguredWorldCarver <?>> holderGetter2 = bootstapContext.lookup(Registries.CONFIGURED_CARVER);


      register(
              bootstapContext,
              WISTERIA_FOREST,
              NatureSpiritOverworldBiomes.wisteriaForest(holderGetter, holderGetter2)
      );
      register(
              bootstapContext,
              LAVENDER_FIELDS,
              NatureSpiritOverworldBiomes.lavenderFields(holderGetter, holderGetter2)
      );
      register(
              bootstapContext,
              FOXGLOVE_FIELDS,
              NatureSpiritOverworldBiomes.foxgloveFields(holderGetter, holderGetter2)
      );
      register(bootstapContext, ERODED_RIVER, NatureSpiritOverworldBiomes.erodedRiver(holderGetter, holderGetter2));
      register(bootstapContext, REDWOOD_FOREST, NatureSpiritOverworldBiomes.redwoodForest(holderGetter, holderGetter2));
      register(bootstapContext, FIR_FOREST, NatureSpiritOverworldBiomes.firForest(holderGetter, holderGetter2));
   }

   private static void register(BootstapContext <Biome> context, ResourceKey <Biome> key, Biome biome) {
      System.out.println("Registered Bootstrap for Biome");
      context.register(key, biome);
   }


   public static void registerBiomes() {
   }
}