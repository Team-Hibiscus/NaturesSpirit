package net.hibiscus.naturespirit.util;

import net.hibiscus.naturespirit.Constants;
import net.hibiscus.naturespirit.registration.RegistrationProvider;
import net.hibiscus.naturespirit.registration.RegistryObject;
import net.hibiscus.naturespirit.world.feature.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.DeltaFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class HibiscusWorldGen {

   public static final RegistrationProvider <Feature<?>> FEATURES = RegistrationProvider.get(Registries.FEATURE,
           Constants.MOD_ID
   );



   public static final Feature <DeltaFeatureConfiguration> HIBISCUS_DELTA_FEATURE = (Feature <DeltaFeatureConfiguration>) registerFeature(
           "water_delta_feature",
           new HibiscusDeltaFeature(DeltaFeatureConfiguration.CODEC)
   );
   public static final Feature <TurnipRootFeatureConfig> HIBISCUS_TURNIP_ROOT_FEATURE = (Feature <TurnipRootFeatureConfig>) registerFeature(
           "turnip_root_feature",
           new TurnipRootFeature(TurnipRootFeatureConfig.CODEC)
   );
   public static final Feature <NoneFeatureConfiguration> JOSHUA_TREE_FEATURE = (Feature <NoneFeatureConfiguration>) registerFeature(
           "joshua_tree_feature",
           new JoshuaTreeFeature(NoneFeatureConfiguration.CODEC)
   );

   public static Feature<?> registerFeature(String name, Feature<?> feature) {
      assert FEATURES != null;
      RegistryObject <Feature<?>> feature2 = FEATURES.register(name, () -> feature);
      return feature2.get();
   }
   public static void registerWorldGen() {
   };
}
