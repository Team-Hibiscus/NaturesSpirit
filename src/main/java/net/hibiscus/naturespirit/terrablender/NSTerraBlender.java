package net.hibiscus.naturespirit.terrablender;

import net.hibiscus.naturespirit.config.NSConfig;
import net.hibiscus.naturespirit.world.NSSurfaceRules;
import net.minecraft.util.Identifier;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

import java.io.IOException;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;

public class NSTerraBlender implements TerraBlenderApi {
   @Override public void onTerraBlenderInitialized() {
      {
         try {
            NSConfig.main();
         }
         catch(IOException e) {
            throw new RuntimeException(e);
         }
            Regions.register(new TerraFeraxRegion(new Identifier(MOD_ID, "terra_ferax"), NSConfig.terra_ferax_weight));
            Regions.register(new TerraSolarisRegion(new Identifier(MOD_ID, "terra_solaris"), NSConfig.terra_solaris_weight));
            Regions.register(new TerraFlavaRegion(new Identifier(MOD_ID, "terra_flava"), NSConfig.terra_flava_weight));
            Regions.register(new TerraMaterRegion(new Identifier(MOD_ID, "terra_mater"), NSConfig.terra_mater_weight));
            Regions.register(new TerraLaetaRegion(new Identifier(MOD_ID, "terra_laeta"), NSConfig.terra_laeta_weight));
         SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, NSSurfaceRules.makeRules());
      }
   }
}
