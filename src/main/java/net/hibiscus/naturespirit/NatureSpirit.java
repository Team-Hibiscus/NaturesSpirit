package net.hibiscus.naturespirit;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.hibiscus.naturespirit.config.HibiscusConfig;
import net.hibiscus.naturespirit.entity.HibiscusBoatEntity;
import net.hibiscus.naturespirit.items.HibiscusBoatDispensorBehavior;
import net.hibiscus.naturespirit.mixin.StatsTypeAccessor;
import net.hibiscus.naturespirit.registration.*;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusBlocks;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusMiscBlocks;
import net.hibiscus.naturespirit.util.HibiscusCauldronBehavior;
import net.hibiscus.naturespirit.util.HibiscusEvents;
import net.hibiscus.naturespirit.util.HibiscusVillagers;
import net.hibiscus.naturespirit.world.HibiscusBiomes;
import net.hibiscus.naturespirit.world.HibiscusWorldGen;
import net.minecraft.block.DispenserBlock;
import net.minecraft.entity.passive.CatVariant;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.stat.StatFormatter;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Optional;

public class NatureSpirit implements ModInitializer {

   public static final String MOD_ID = "natures_spirit";
   public static final Logger LOGGER = LoggerFactory.getLogger("Nature's Spirit");
   public static final Identifier EAT_PIZZA_SLICE = StatsTypeAccessor.registerNew("eat_pizza_slice", StatFormatter.DEFAULT);
   public static final Identifier EAT_CHEESE = StatsTypeAccessor.registerNew("eat_cheese", StatFormatter.DEFAULT);
   public static final SimpleParticleType RED_MAPLE_LEAVES_PARTICLE = FabricParticleTypes.simple(false);
   public static final SimpleParticleType ORANGE_MAPLE_LEAVES_PARTICLE = FabricParticleTypes.simple(false);
   public static final SimpleParticleType YELLOW_MAPLE_LEAVES_PARTICLE = FabricParticleTypes.simple(false);
   public static final SimpleParticleType MILK_PARTICLE = FabricParticleTypes.simple(false);
   public static final SimpleParticleType CALCITE_BUBBLE_PARTICLE = FabricParticleTypes.simple(false);

   @Override public void onInitialize() {

      Optional <ModContainer> modContainer = FabricLoader.getInstance().getModContainer("natures_spirit");
      try {
         HibiscusConfig.main();
      }
      catch(IOException e) {
         throw new RuntimeException(e);
      }
      if(modContainer.isPresent()) {
         ResourceManagerHelper.registerBuiltinResourcePack(
                 Identifier.of(MOD_ID, "modified_vanilla_trees"), modContainer.get(),
                 Text.translatable("pack.natures_spirit.modified_vanilla_trees"),
                 HibiscusConfig.vanilla_trees_toggle ? ResourcePackActivationType.DEFAULT_ENABLED : ResourcePackActivationType.NORMAL
         );
         ResourceManagerHelper.registerBuiltinResourcePack(
                 Identifier.of(MOD_ID, "modified_flower_forest"), modContainer.get(),
                 Text.translatable("pack.natures_spirit.modified_flower_forest"),
                 HibiscusConfig.flower_forest_toggle ? ResourcePackActivationType.DEFAULT_ENABLED : ResourcePackActivationType.NORMAL
         );
         ResourceManagerHelper.registerBuiltinResourcePack(
                 Identifier.of(MOD_ID, "modified_birch_forest"), modContainer.get(),
                 Text.translatable("pack.natures_spirit.modified_birch_forest"),
                 HibiscusConfig.birch_forest_toggle ? ResourcePackActivationType.DEFAULT_ENABLED : ResourcePackActivationType.NORMAL
         );
         ResourceManagerHelper.registerBuiltinResourcePack(
                 Identifier.of(MOD_ID, "modified_jungle"), modContainer.get(),
                 Text.translatable("pack.natures_spirit.modified_jungle"),
                 HibiscusConfig.jungle_toggle ? ResourcePackActivationType.DEFAULT_ENABLED : ResourcePackActivationType.NORMAL
         );
         ResourceManagerHelper.registerBuiltinResourcePack(
                 Identifier.of(MOD_ID, "modified_swamp"), modContainer.get(),
                 Text.translatable("pack.natures_spirit.modified_swamp"),
                 HibiscusConfig.swamp_toggle ? ResourcePackActivationType.DEFAULT_ENABLED : ResourcePackActivationType.NORMAL
         );
         ResourceManagerHelper.registerBuiltinResourcePack(
                 Identifier.of(MOD_ID, "modified_desert"), modContainer.get(),
                 Text.translatable("pack.natures_spirit.modified_desert"),
                 HibiscusConfig.desert_toggle ? ResourcePackActivationType.DEFAULT_ENABLED : ResourcePackActivationType.NORMAL
         );
         ResourceManagerHelper.registerBuiltinResourcePack(
                 Identifier.of(MOD_ID, "modified_badlands"), modContainer.get(),
                 Text.translatable("pack.natures_spirit.modified_badlands"),
                 HibiscusConfig.badlands_toggle ? ResourcePackActivationType.DEFAULT_ENABLED : ResourcePackActivationType.NORMAL
         );
         ResourceManagerHelper.registerBuiltinResourcePack(
                 Identifier.of(MOD_ID, "modified_mountain_biomes"), modContainer.get(),
                 Text.translatable("pack.natures_spirit.modified_mountain_biomes"),
                 HibiscusConfig.mountain_biomes_toggle ? ResourcePackActivationType.DEFAULT_ENABLED : ResourcePackActivationType.NORMAL
         );
         ResourceManagerHelper.registerBuiltinResourcePack(
                 Identifier.of(MOD_ID, "modified_savannas"), modContainer.get(),
                 Text.translatable("pack.natures_spirit.modified_savannas"),
                 HibiscusConfig.savanna_toggle ? ResourcePackActivationType.DEFAULT_ENABLED : ResourcePackActivationType.NORMAL
         );
         ResourceManagerHelper.registerBuiltinResourcePack(
                 Identifier.of(MOD_ID, "modified_dark_forest"), modContainer.get(),
                 Text.translatable("pack.natures_spirit.modified_dark_forest"),
                 HibiscusConfig.dark_forest_toggle ? ResourcePackActivationType.DEFAULT_ENABLED : ResourcePackActivationType.NORMAL
         );
         ResourceManagerHelper.registerBuiltinResourcePack(
                 new Identifier(MOD_ID, "modified_windswept_hills"), modContainer.get(),
                 Text.translatable("pack.natures_spirit.modified_windswept_hills"),
                 HibiscusConfig.windswept_hills_toggle ? ResourcePackActivationType.DEFAULT_ENABLED : ResourcePackActivationType.NORMAL
         );
         ResourceManagerHelper.registerBuiltinResourcePack(
                 new Identifier(MOD_ID, "better_leaves_compatibility"), modContainer.get(),
                 Text.translatable("pack.natures_spirit.bushy_leaves_compatibility"),
                 ResourcePackActivationType.NORMAL
         );
         ResourceManagerHelper.registerBuiltinResourcePack(
                 new Identifier(MOD_ID, "emissive_ores_compatibility"), modContainer.get(),
                 Text.translatable("pack.natures_spirit.emissive_ores_compatibility"),
                 ResourcePackActivationType.NORMAL
         );
      }
      Registry.register(Registries.PARTICLE_TYPE, Identifier.of(MOD_ID, "red_maple_leaves"), RED_MAPLE_LEAVES_PARTICLE);
      Registry.register(Registries.PARTICLE_TYPE, Identifier.of(MOD_ID, "orange_maple_leaves"), ORANGE_MAPLE_LEAVES_PARTICLE);
      Registry.register(Registries.PARTICLE_TYPE, Identifier.of(MOD_ID, "yellow_maple_leaves"), YELLOW_MAPLE_LEAVES_PARTICLE);
      Registry.register(Registries.PARTICLE_TYPE, Identifier.of(MOD_ID, "milk"), MILK_PARTICLE);
      Registry.register(Registries.PARTICLE_TYPE, Identifier.of(MOD_ID, "calcite_bubble"), CALCITE_BUBBLE_PARTICLE);
      HibiscusSounds.registerSounds();
      HibiscusDataComponents.registerDataComponents();
      HibiscusVillagers.registerVillagers();
      HibiscusBiomes.registerBiomes();
      HibiscusBlocks.registerWoods();
      HibiscusBlocks.registerColoredBlocks();
      HibiscusBlocks.registerMisc();
      HibiscusEvents.registerEvents();
      HibiscusWorldGen.registerWorldGen();
      HibiscusItemGroups.registerItemGroup();
      HibiscusEntityTypes.registerEntityTypes();
      HibiscusSounds.registerSounds();
      HibiscusCriteria.registerCriteria();
      HibiscusCauldronBehavior.registerBehavior();

      CompostingChanceRegistry.INSTANCE.add(HibiscusMiscBlocks.DESERT_TURNIP_BLOCK.asItem(), 0.5F);
      CompostingChanceRegistry.INSTANCE.add(HibiscusMiscBlocks.DESERT_TURNIP_ROOT_BLOCK.asItem(), 0.4F);

      for(HibiscusBoatEntity.HibiscusBoat boat : HibiscusBoatEntity.HibiscusBoat.values()) {
         DispenserBlock.registerBehavior(boat.boat(), new HibiscusBoatDispensorBehavior(boat, false));
         DispenserBlock.registerBehavior(boat.chestBoat(), new HibiscusBoatDispensorBehavior(boat, true));
      }

      Registry.register(Registries.CAT_VARIANT, "trans", new CatVariant(Identifier.of(MOD_ID, "textures/entity/cat/trans" + ".png")));
   }
}
