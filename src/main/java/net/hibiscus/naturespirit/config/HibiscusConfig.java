package net.hibiscus.naturespirit.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.loader.api.FabricLoader;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.world.HibiscusBiomes;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Path;

public class HibiscusConfig {

   public static int terra_ferax_weight;
   public static int terra_solaris_weight;
   public static int terra_flava_weight;
   public static int terra_laeta_weight;
   public static int terra_mater_weight;

   public static boolean calcite_generator;
   public static boolean deepslate_generator;
   public static boolean vinegar;
   public static boolean vinegar_duplication;
   public static boolean vanilla_trees_toggle;
   public static boolean jungle_toggle;
   public static boolean swamp_toggle;
   public static boolean desert_toggle;
   public static boolean badlands_toggle;
   public static boolean mountain_biomes_toggle;
   public static boolean savanna_toggle;
   public static boolean dark_forest_toggle;
   public HibiscusConfig() {}

   public static void main() throws IOException {
      Path configPath = Path.of(FabricLoader.getInstance().getConfigDir().toString(), "natures_spirit_1.4.3-1.20.5.json");
         try {
            if (configPath.toFile().createNewFile()) {
               JsonObject jsonObjects = getJsonObject();
               PrintWriter pw = new PrintWriter(configPath.toString());
               Gson gson = new GsonBuilder().setPrettyPrinting().create();
               pw.print(gson.toJson(jsonObjects));
               pw.flush();
               pw.close();
            }
            JsonObject obj = (JsonObject) JsonParser.parseReader(new FileReader(configPath.toString()));
            JsonObject biomes = (JsonObject) obj.get("biomes");
            JsonObject region_weights = (JsonObject) obj.get("region_weights");
            JsonObject misc_features = (JsonObject) obj.get("misc_features");
            JsonObject datapack_toggles = (JsonObject) obj.get("datapack_toggles");

            HibiscusBiomes.set_has_sugi_forest(biomes.get("has_sugi_forest").getAsBoolean());
            HibiscusBiomes.set_has_eroded_river(biomes.get("has_eroded_river").getAsBoolean());
            HibiscusBiomes.set_has_marsh(biomes.get("has_marsh").getAsBoolean());
            HibiscusBiomes.set_has_bamboo_wetlands(biomes.get("has_bamboo_wetlands").getAsBoolean());
            HibiscusBiomes.set_has_wisteria_forest(biomes.get("has_wisteria_forest").getAsBoolean());
            HibiscusBiomes.set_has_redwood_forest(biomes.get("has_redwood_forest").getAsBoolean());
            HibiscusBiomes.set_has_aspen_forest(biomes.get("has_aspen_forest").getAsBoolean());
            HibiscusBiomes.set_has_maple(biomes.get("has_maple").getAsBoolean());
            HibiscusBiomes.set_has_fir(biomes.get("has_fir").getAsBoolean());
            HibiscusBiomes.set_has_cypress_fields(biomes.get("has_cypress_fields").getAsBoolean());
            HibiscusBiomes.set_has_lively_dunes(biomes.get("has_lively_dunes").getAsBoolean());
            HibiscusBiomes.set_has_drylands(biomes.get("has_drylands").getAsBoolean());
            HibiscusBiomes.set_has_white_cliffs(biomes.get("has_white_cliffs").getAsBoolean());
            HibiscusBiomes.set_has_tropical_shores(biomes.get("has_tropical_shores").getAsBoolean());
            HibiscusBiomes.set_has_xeric_plains(biomes.get("has_xeric_plains").getAsBoolean());
            HibiscusBiomes.set_has_larch(biomes.get("has_larch").getAsBoolean());
            HibiscusBiomes.set_has_oak_savanna(biomes.get("has_oak_savanna").getAsBoolean());
            HibiscusBiomes.set_has_mahogany(biomes.get("has_mahogany").getAsBoolean());
            HibiscusBiomes.set_has_arid(biomes.get("has_arid").getAsBoolean());
            HibiscusBiomes.set_has_shrublands(biomes.get("has_shrublands").getAsBoolean());
            HibiscusBiomes.set_has_steppe(biomes.get("has_steppe").getAsBoolean());

            terra_ferax_weight = region_weights.get("terra_ferax_frequency").getAsInt();
            terra_solaris_weight = region_weights.get("terra_solaris_frequency").getAsInt();
            terra_flava_weight = region_weights.get("terra_flava_frequency").getAsInt();
            terra_laeta_weight = region_weights.get("terra_laeta_frequency").getAsInt();
            terra_mater_weight = region_weights.get("terra_mater_frequency").getAsInt();

            calcite_generator = misc_features.get("calcite_generator").getAsBoolean();
            deepslate_generator = misc_features.get("deepslate_generator").getAsBoolean();
            vinegar = misc_features.get("vinegar").getAsBoolean();
            vinegar_duplication = misc_features.get("vinegar_duplication").getAsBoolean();

            vanilla_trees_toggle  = datapack_toggles.get("vanilla_trees_toggle").getAsBoolean();
            jungle_toggle = datapack_toggles.get("jungle_toggle").getAsBoolean();
            swamp_toggle = datapack_toggles.get("swamp_toggle").getAsBoolean();
            desert_toggle = datapack_toggles.get("desert_toggle").getAsBoolean();
            badlands_toggle = datapack_toggles.get("badlands_toggle").getAsBoolean();
            mountain_biomes_toggle = datapack_toggles.get("mountain_biomes_toggle").getAsBoolean();
            savanna_toggle = datapack_toggles.get("savanna_toggle").getAsBoolean();
            dark_forest_toggle = datapack_toggles.get("dark_forest_toggle").getAsBoolean();

         } catch(final IOException e) {
            System.err.println("An error occurred, delete the natures_spirit.config file in .minecraft/config and relaunch");
         }
      NatureSpirit.LOGGER.info("terra_ferax_frequency = " + terra_ferax_weight);
      NatureSpirit.LOGGER.info("terra_solaris_frequency = " + terra_solaris_weight);
      NatureSpirit.LOGGER.info("terra_flava_frequency = " + terra_flava_weight);
      NatureSpirit.LOGGER.info("terra_laeta_frequency = " + terra_laeta_weight);
      NatureSpirit.LOGGER.info("has_sugi_forest = " + HibiscusBiomes.has_sugi_forest);
      NatureSpirit.LOGGER.info("has_eroded_river = " + HibiscusBiomes.has_eroded_river);
      NatureSpirit.LOGGER.info("has_marsh = " + HibiscusBiomes.has_marsh);
      NatureSpirit.LOGGER.info("has_bamboo_wetlands = " + HibiscusBiomes.has_bamboo_wetlands);
      NatureSpirit.LOGGER.info("has_wisteria_forest = " + HibiscusBiomes.has_wisteria_forest);
      NatureSpirit.LOGGER.info("has_redwood_forest = " + HibiscusBiomes.has_redwood_forest);
      NatureSpirit.LOGGER.info("has_aspen_forest = " + HibiscusBiomes.has_aspen_forest);
      NatureSpirit.LOGGER.info("has_maple = " + HibiscusBiomes.has_maple);
      NatureSpirit.LOGGER.info("has_fir = " + HibiscusBiomes.has_fir);
      NatureSpirit.LOGGER.info("has_cypress_fields = " + HibiscusBiomes.has_cypress_fields);
      NatureSpirit.LOGGER.info("has_lively_dunes = " + HibiscusBiomes.has_lively_dunes);
      NatureSpirit.LOGGER.info("has_drylands = " + HibiscusBiomes.has_drylands);
      NatureSpirit.LOGGER.info("has_white_cliffs = " + HibiscusBiomes.has_white_cliffs);
      NatureSpirit.LOGGER.info("has_tropical_shores = " + HibiscusBiomes.has_tropical_shores);
      NatureSpirit.LOGGER.info("has_xeric_plains = " + HibiscusBiomes.has_xeric_plains);
      NatureSpirit.LOGGER.info("has_larch = " + HibiscusBiomes.has_larch);
      NatureSpirit.LOGGER.info("has_oak_savanna = " + HibiscusBiomes.has_oak_savanna);
      NatureSpirit.LOGGER.info("has_mahogany = " + HibiscusBiomes.has_mahogany);
      NatureSpirit.LOGGER.info("has_arid = " + HibiscusBiomes.has_arid);
      NatureSpirit.LOGGER.info("has_shrublands = " + HibiscusBiomes.has_shrublands);
      NatureSpirit.LOGGER.info("has_steppe = " + HibiscusBiomes.has_steppe);

      System.out.println("Thanks for viewing your messages");
   }

   @NotNull private static JsonObject getJsonObject() {

      JsonObject jsonObjects = new JsonObject();

      JsonObject biomesObject = getBiomesObject();
      jsonObjects.add("biomes", biomesObject);

      JsonObject regionsObject = new JsonObject();
      regionsObject.addProperty("terra_ferax_frequency", 4);
      regionsObject.addProperty("terra_solaris_frequency", 4);
      regionsObject.addProperty("terra_flava_frequency", 4);
      regionsObject.addProperty("terra_laeta_frequency", 4);
      regionsObject.addProperty("terra_mater_frequency", 4);
      jsonObjects.add("region_weights", regionsObject);

      JsonObject miscObject = new JsonObject();
      miscObject.addProperty("deepslate_generator", true);
      miscObject.addProperty("calcite_generator", true);
      miscObject.addProperty("vinegar", true);
      miscObject.addProperty("vinegar_duplication", true);
      jsonObjects.add("misc_features", miscObject);

      JsonObject datapackTogglesObject = new JsonObject();
      datapackTogglesObject.addProperty("vanilla_trees_toggle", false);
      datapackTogglesObject.addProperty("jungle_toggle", true);
      datapackTogglesObject.addProperty("swamp_toggle", true);
      datapackTogglesObject.addProperty("desert_toggle", true);
      datapackTogglesObject.addProperty("badlands_toggle", true);
      datapackTogglesObject.addProperty("mountain_biomes_toggle", true);
      datapackTogglesObject.addProperty("savanna_toggle", true);
      datapackTogglesObject.addProperty("dark_forest_toggle", true);
      jsonObjects.add("datapack_toggles", datapackTogglesObject);

      return jsonObjects;
   }

   @NotNull private static JsonObject getBiomesObject() {
      JsonObject biomesObject = new JsonObject();
      biomesObject.addProperty("has_sugi_forest", true);
      biomesObject.addProperty("has_eroded_river", true);
      biomesObject.addProperty("has_marsh", true);
      biomesObject.addProperty("has_bamboo_wetlands", true);
      biomesObject.addProperty("has_wisteria_forest", true);
      biomesObject.addProperty("has_redwood_forest", true);
      biomesObject.addProperty("has_aspen_forest", true);
      biomesObject.addProperty("has_maple", true);
      biomesObject.addProperty("has_fir", true);
      biomesObject.addProperty("has_cypress_fields", true);
      biomesObject.addProperty("has_lively_dunes", true);
      biomesObject.addProperty("has_drylands", true);
      biomesObject.addProperty("has_white_cliffs", true);
      biomesObject.addProperty("has_tropical_shores", true);
      biomesObject.addProperty("has_xeric_plains", true);
      biomesObject.addProperty("has_larch", true);
      biomesObject.addProperty("has_oak_savanna", true);
      biomesObject.addProperty("has_mahogany", true);
      biomesObject.addProperty("has_arid", true);
      biomesObject.addProperty("has_shrublands", true);
      biomesObject.addProperty("has_steppe", true);
      return biomesObject;
   }
}
