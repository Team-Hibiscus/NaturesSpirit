package net.hibiscus.naturespirit.config;

import net.fabricmc.loader.api.FabricLoader;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.datagen.HibiscusBiomes;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;
import org.spongepowered.configurate.serialize.SerializationException;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class HibiscusConfig {

   public static int terra_ferax_weight;
   public static int terra_solaris_weight;
   public static int terra_flava_weight;
   public static int terra_laeta_weight;
   public HibiscusConfig() {}

   public static void main() throws SerializationException {
      Path configPath = Path.of(FabricLoader.getInstance().getConfigDir().toString(), "natures_spirit.conf");
      final HoconConfigurationLoader loader = HoconConfigurationLoader.builder().path(configPath).build();
      final CommentedConfigurationNode root;

         try {
            if (configPath.toFile().createNewFile()) {
               FileWriter myWriter = new FileWriter(configPath.toFile());
               myWriter.write(
                       "biomes{has_sugi_forest=true\n"
                               + "has_eroded_river=true\n"
                               + "has_marsh=true\n"
                               + "has_bamboo_wetlands=true\n"
                               + "has_wisteria_forest=true\n"
                               + "has_redwood_forest=true\n"
                               + "has_aspen_forest=true\n"
                               + "has_maple=true\n"
                               + "has_fir=true\n"
                               + "has_cypress_fields=true\n"
                               + "has_lively_dunes=true\n"
                               + "has_drylands=true\n"
                               + "has_white_cliffs=true}\n"
                               + "regions{terra_ferax_frequency=3\n"
                                    + "terra_solaris_frequency=3\n"
                                    + "terra_flava_frequency=1\n"
                                    + "terra_laeta_frequency=2}");
               myWriter.close();
            }
         } catch(final IOException e) {
            System.err.println("An error occurred");
         }

      try {
         root = loader.load();
      }
      catch(final ConfigurateException e) {
         System.err.println("An error occurred while loading this configuration: " + e.getMessage());
         if(e.getCause() != null) {
            e.getCause().printStackTrace();
         }
         System.exit(1);
         return;
      }
      final ConfigurationNode terra_ferax_frequency_node = root.node("regions", "terra_ferax_frequency");
      final ConfigurationNode terra_solaris_frequency_node = root.node("regions", "terra_solaris_frequency");
      final ConfigurationNode terra_flava_frequency_node = root.node("regions", "terra_flava_frequency");
      final ConfigurationNode terra_laeta_frequency_node = root.node("regions", "terra_laeta_frequency");

      terra_ferax_weight = terra_ferax_frequency_node.getInt(3);
      terra_solaris_weight = terra_solaris_frequency_node.getInt(3);
      terra_flava_weight = terra_flava_frequency_node.getInt(1);
      terra_laeta_weight = terra_laeta_frequency_node.getInt(2);

      NatureSpirit.LOGGER.info("terra_ferax_frequency = " + terra_ferax_weight);
      NatureSpirit.LOGGER.info("terra_solaris_frequency = " + terra_solaris_weight);
      NatureSpirit.LOGGER.info("terra_flava_frequency = " + terra_flava_weight);
      NatureSpirit.LOGGER.info("terra_laeta_frequency = " + terra_laeta_weight);

      final ConfigurationNode has_sugi_forest_node = root.node("biomes", "has_sugi_forest");
      final ConfigurationNode has_eroded_river_node = root.node("biomes", "has_eroded_river");
      final ConfigurationNode has_marsh_node = root.node("biomes", "has_marsh");
      final ConfigurationNode has_bamboo_wetlands_node = root.node("biomes", "has_bamboo_wetlands");
      final ConfigurationNode has_wisteria_forest_node = root.node("biomes", "has_wisteria_forest");
      final ConfigurationNode has_redwood_forest_node = root.node("biomes", "has_redwood_forest");
      final ConfigurationNode has_aspen_forest_node = root.node("biomes", "has_aspen_forest");
      final ConfigurationNode has_maple_node = root.node("biomes", "has_maple");
      final ConfigurationNode has_fir_node = root.node("biomes", "has_fir");
      final ConfigurationNode has_cypress_fields_node = root.node("biomes", "has_cypress_fields");
      final ConfigurationNode has_lively_dunes_node = root.node("biomes", "has_lively_dunes");
      final ConfigurationNode has_drylands_node = root.node("biomes", "has_drylands");
      final ConfigurationNode has_white_cliffs_node = root.node("biomes", "has_white_cliffs");

      HibiscusBiomes.set_has_sugi_forest(has_sugi_forest_node.getBoolean(true));
      HibiscusBiomes.set_has_eroded_river(has_eroded_river_node.getBoolean(true));
      HibiscusBiomes.set_has_marsh(has_marsh_node.getBoolean(true));
      HibiscusBiomes.set_has_bamboo_wetlands(has_bamboo_wetlands_node.getBoolean(true));
      HibiscusBiomes.set_has_wisteria_forest(has_wisteria_forest_node.getBoolean(true));
      HibiscusBiomes.set_has_redwood_forest(has_redwood_forest_node.getBoolean(true));
      HibiscusBiomes.set_has_aspen_forest(has_aspen_forest_node.getBoolean(true));
      HibiscusBiomes.set_has_maple(has_maple_node.getBoolean(true));
      HibiscusBiomes.set_has_fir(has_fir_node.getBoolean(true));
      HibiscusBiomes.set_has_cypress_fields(has_cypress_fields_node.getBoolean(true));
      HibiscusBiomes.set_has_lively_dunes(has_lively_dunes_node.getBoolean(true));
      HibiscusBiomes.set_has_drylands(has_drylands_node.getBoolean(true));
      HibiscusBiomes.set_has_white_cliffs(has_white_cliffs_node.getBoolean(true));

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
