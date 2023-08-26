package net.hibiscus.naturespirit.terrablender;

import net.minecraft.util.Identifier;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;

public class NatureSpiritTerraBlender implements TerraBlenderApi {
   @Override public void onTerraBlenderInitialized() {
      {
         Regions.register(new HibiscusRegion1(new Identifier(MOD_ID, "redwood_and_marsh"), 3));
         Regions.register(new HibiscusRegion3(new Identifier(MOD_ID, "autumn_and_sugi"), 1));
         Regions.register(new HibiscusRegion5(new Identifier(MOD_ID, "desert_and_wisteria"), 2));
         SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD,
                 MOD_ID,
                 NatureSpiritSurfaceRules.makeRules()
         );
      }
   }
}
