package net.hibiscus.naturespirit.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.NotNull;

public class NSConfig {

	public static boolean cheese_arrow;
	public static boolean calcite_generator;
	public static boolean deepslate_generator;
	public static boolean vanilla_trees_toggle;
	public static boolean birch_forest_toggle;
	public static boolean flower_forest_toggle;
	public static boolean jungle_toggle;
	public static boolean swamp_toggle;
	public static boolean desert_toggle;
	public static boolean badlands_toggle;
	public static boolean mountain_biomes_toggle;
	public static boolean savanna_toggle;
	public static boolean dark_forest_toggle;
	public static boolean windswept_hills_toggle;

	public NSConfig() {
	}

	public static void main() throws IOException {
		Path configPath = Path.of(FabricLoader.getInstance().getConfigDir().toString(), "natures_spirit_1.6.3-1.21.1.json");
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

			calcite_generator = misc_features.get("calcite_generator").getAsBoolean();
			deepslate_generator = misc_features.get("deepslate_generator").getAsBoolean();
			cheese_arrow = misc_features.get("cheese_arrow").getAsBoolean();

			vanilla_trees_toggle = datapack_toggles.get("vanilla_trees_toggle").getAsBoolean();
			birch_forest_toggle = datapack_toggles.get("birch_forest_toggle").getAsBoolean();
			flower_forest_toggle = datapack_toggles.get("flower_forest_toggle").getAsBoolean();
			jungle_toggle = datapack_toggles.get("jungle_toggle").getAsBoolean();
			swamp_toggle = datapack_toggles.get("swamp_toggle").getAsBoolean();
			desert_toggle = datapack_toggles.get("desert_toggle").getAsBoolean();
			badlands_toggle = datapack_toggles.get("badlands_toggle").getAsBoolean();
			mountain_biomes_toggle = datapack_toggles.get("mountain_biomes_toggle").getAsBoolean();
			savanna_toggle = datapack_toggles.get("savanna_toggle").getAsBoolean();
			dark_forest_toggle = datapack_toggles.get("dark_forest_toggle").getAsBoolean();
			windswept_hills_toggle = datapack_toggles.get("windswept_hills_toggle").getAsBoolean();

		} catch (final IOException e) {
			System.err.println("An error occurred, delete the natures_spirit.config file in .minecraft/config and relaunch");
		}
	}

	@NotNull
	private static JsonObject getJsonObject() {

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
		miscObject.addProperty("cheese_arrow", true);
		jsonObjects.add("misc_features", miscObject);

		JsonObject datapackTogglesObject = new JsonObject();
		datapackTogglesObject.addProperty("vanilla_trees_toggle", false);
		datapackTogglesObject.addProperty("birch_forest_toggle", true);
		datapackTogglesObject.addProperty("flower_forest_toggle", true);
		datapackTogglesObject.addProperty("jungle_toggle", true);
		datapackTogglesObject.addProperty("swamp_toggle", true);
		datapackTogglesObject.addProperty("desert_toggle", true);
		datapackTogglesObject.addProperty("badlands_toggle", true);
		datapackTogglesObject.addProperty("mountain_biomes_toggle", true);
		datapackTogglesObject.addProperty("savanna_toggle", true);
		datapackTogglesObject.addProperty("dark_forest_toggle", true);
		datapackTogglesObject.addProperty("windswept_hills_toggle", true);
		jsonObjects.add("datapack_toggles", datapackTogglesObject);

		return jsonObjects;
	}

	@NotNull
	private static JsonObject getBiomesObject() {
		JsonObject biomesObject = new JsonObject();
		biomesObject.addProperty("has_sugi_forest", true);
		biomesObject.addProperty("has_windswept_sugi_forest", true);
		biomesObject.addProperty("has_blooming_sugi_forest", true);
		biomesObject.addProperty("has_lavender_fields", true);
		biomesObject.addProperty("has_marsh", true);
		biomesObject.addProperty("has_bamboo_wetlands", true);
		biomesObject.addProperty("has_wisteria_forest", true);
		biomesObject.addProperty("has_redwood_forest", true);
		biomesObject.addProperty("has_snowy_redwood_forest", true);
		biomesObject.addProperty("has_aspen_forest", true);
		biomesObject.addProperty("has_maple_woodlands", true);
		biomesObject.addProperty("has_golden_wilds", true);
		biomesObject.addProperty("has_marigold_meadows", true);
		biomesObject.addProperty("has_fir_forest", true);
		biomesObject.addProperty("has_snowy_fir_forest", true);
		biomesObject.addProperty("has_cypress_fields", true);
		biomesObject.addProperty("has_carnation_fields", true);
		biomesObject.addProperty("has_stratified_desert", true);
		biomesObject.addProperty("has_blooming_dunes", true);
		biomesObject.addProperty("has_lively_dunes", true);
		biomesObject.addProperty("has_drylands", true);
		biomesObject.addProperty("has_wooded_drylands", true);
		biomesObject.addProperty("has_xeric_plains", true);
		biomesObject.addProperty("has_white_cliffs", true);
		biomesObject.addProperty("has_prairie", true);
		biomesObject.addProperty("has_oak_savanna", true);
		biomesObject.addProperty("has_heather_fields", true);
		biomesObject.addProperty("has_tundra", true);
		biomesObject.addProperty("has_alpine_clearings", true);
		biomesObject.addProperty("has_alpine_highlands", true);
		biomesObject.addProperty("has_coniferous_covert", true);
		biomesObject.addProperty("has_boreal_taiga", true);
		biomesObject.addProperty("has_tropical_shores", true);
		biomesObject.addProperty("has_tropical_woods", true);
		biomesObject.addProperty("has_sparse_tropical_woods", true);
		biomesObject.addProperty("has_tropical_basin", true);
		biomesObject.addProperty("has_arid_savanna", true);
		biomesObject.addProperty("has_scorched_dunes", true);
		biomesObject.addProperty("has_flowering_shrubland", true);
		biomesObject.addProperty("has_shrubland", true);
		biomesObject.addProperty("has_arid_highlands", true);
		biomesObject.addProperty("has_shrubby_highlands", true);
		biomesObject.addProperty("has_woody_highlands", true);
		biomesObject.addProperty("has_red_peaks", true);
		biomesObject.addProperty("has_dusty_slopes", true);
		biomesObject.addProperty("has_snowcapped_red_peaks", true);
		biomesObject.addProperty("has_sleeted_slopes", true);
		biomesObject.addProperty("has_blooming_highlands", true);
		biomesObject.addProperty("has_chaparral", true);
		biomesObject.addProperty("has_floral_ridges", true);
		return biomesObject;
	}
}
