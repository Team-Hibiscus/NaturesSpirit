package net.hibiscus.naturespirit.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalEntityTypeTags;
import net.hibiscus.naturespirit.registration.*;
import net.minecraft.registry.*;

import java.util.concurrent.CompletableFuture;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;

public class NatureSpiritDataGen implements DataGeneratorEntrypoint {

   public static final String[] DYE_COLORS = {
           "white", "light_gray", "gray", "black", "brown", "red", "orange", "yellow", "lime",
           "green", "cyan", "light_blue", "blue", "purple", "magenta", "pink",
//           "maroon",
//           "peach",
//           "vermilion",
//           "amber",
//           "banana",
//           "mold",
//           "artichoke",
//           "sage",
//           "shamrock",
//           "sap",
//           "mint",
//           "cerulean",
//           "navy",
//           "periwinkle",
//           "indigo",
//           "grape",
//           "fuchsia",
//           "velvet",
//           "mauve",
//           "acorn",
   };

   @Override public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
      FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

      pack.addProvider(NatureSpiritWorldGenerator::new);
      pack.addProvider(NatureSpiritModelGenerator::new);
      pack.addProvider(NatureSpiritLangGenerator::new);
      pack.addProvider(NatureSpiritRecipeGenerator::new);
      pack.addProvider(NatureSpiritBlockLootTableProvider::new);
      NatureSpiritBlockTagGenerator blockTagProvider = pack.addProvider(NatureSpiritBlockTagGenerator::new);
      pack.addProvider((output, registries) -> new NatureSpiritItemTagGenerator(output, registries, blockTagProvider));
      pack.addProvider(NatureSpiritEntityTypeGenerator::new);
      System.out.println("Initialized Data Generator");
   }

   @Override public void buildRegistry(RegistryBuilder registryBuilder) {
      registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, HibiscusConfiguredFeatures::bootstrap);
      registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, HibiscusPlacedFeatures::bootstrap);
      System.out.println("Built Registry");
   }


   @Override public String getEffectiveModId() {
      return MOD_ID;
   }


   public static class NatureSpiritEntityTypeGenerator extends FabricTagProvider.EntityTypeTagProvider {

      public NatureSpiritEntityTypeGenerator(FabricDataOutput output, CompletableFuture <RegistryWrapper.WrapperLookup> completableFuture) {
         super(output, completableFuture);
      }

      @Override protected void configure(RegistryWrapper.WrapperLookup arg) {
         for (WoodSet woodSet : HibiscusRegistryHelper.WoodHashMap.values()) {
            getOrCreateTagBuilder(ConventionalEntityTypeTags.BOATS).add(woodSet.getBoatEntityType(), woodSet.getChestBoatEntityType());
         }
      }
   }
}

