package net.hibiscus.naturespirit.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.JsonAdapter;
import net.fabricmc.loader.api.FabricLoader;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.datagen.HibiscusBiomes;

import java.io.*;
import java.nio.file.Path;

public class HibiscusConfig {

   public static int terra_ferax_weight;
   public static int terra_solaris_weight;
   public static int terra_flava_weight;
   public static int terra_laeta_weight;
   public HibiscusConfig() {}

   public static void main() throws IOException {
      Path configPath = Path.of(FabricLoader.getInstance().getConfigDir().toString(), "natures_spirit.json");

         try {
            if (configPath.toFile().createNewFile()) {
               JsonObject jsonObjects = new JsonObject();

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
               jsonObjects.add("biomes", biomesObject);
               JsonObject regionsObject = new JsonObject();
                       regionsObject.addProperty("terra_ferax_frequency", 3);
                       regionsObject.addProperty("terra_solaris_frequency", 3);
                       regionsObject.addProperty("terra_flava_frequency", 1);
                       regionsObject.addProperty("terra_laeta_frequency", 2);
               jsonObjects.add("region_weights", regionsObject);

               PrintWriter pw = new PrintWriter(configPath.toString());
               Gson gson = new GsonBuilder().setPrettyPrinting().create();
               pw.print(gson.toJson(jsonObjects));
               pw.flush();
               pw.close();
            }
            JsonObject obj = (JsonObject) JsonParser.parseReader(new FileReader(configPath.toString()));
            JsonObject biomes = (JsonObject) obj.get("biomes");
            JsonObject region_weights = (JsonObject) obj.get("region_weights");

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

            terra_ferax_weight = region_weights.get("terra_ferax_frequency").getAsInt();
            terra_solaris_weight = region_weights.get("terra_solaris_frequency").getAsInt();
            terra_flava_weight = region_weights.get("terra_flava_frequency").getAsInt();
            terra_laeta_weight = region_weights.get("terra_laeta_frequency").getAsInt();


         } catch(final IOException e) {
            System.err.println("An error occurred");
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

      System.out.println("Thanks for viewing your messages");
   }
}
