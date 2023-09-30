package net.hibiscus.naturespirit.terrablender;

import net.hibiscus.naturespirit.config.HibiscusConfig;
import net.hibiscus.naturespirit.world.NatureSpiritSurfaceRules;
import net.minecraft.util.Identifier;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

import java.io.IOException;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;

public class NatureSpiritTerraBlender implements TerraBlenderApi {
   @Override public void onTerraBlenderInitialized() {
      {
         try {
            HibiscusConfig.main();
         }
         catch(IOException e) {
            throw new RuntimeException(e);
         }
            Regions.register(new HibiscusRegion1(new Identifier(MOD_ID, "terra_ferax"), HibiscusConfig.terra_ferax_weight));
            Regions.register(new HibiscusRegion2(new Identifier(MOD_ID, "terra_solaris"), HibiscusConfig.terra_solaris_weight));
            Regions.register(new HibiscusRegion3(new Identifier(MOD_ID, "terra_flava"), HibiscusConfig.terra_flava_weight));
            Regions.register(new HibiscusRegion5(new Identifier(MOD_ID, "terra_laeta"), HibiscusConfig.terra_laeta_weight));
         SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, NatureSpiritSurfaceRules.makeRules());
      }
   }
}
