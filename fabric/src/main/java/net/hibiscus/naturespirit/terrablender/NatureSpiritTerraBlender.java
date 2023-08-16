package net.hibiscus.naturespirit.terrablender;

import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

import static net.hibiscus.naturespirit.Constants.MOD_ID;

public class NatureSpiritTerraBlender implements TerraBlenderApi {
   @Override public void onTerraBlenderInitialized() {
      {
         Regions.register(new HibiscusRegion(new ResourceLocation(MOD_ID, "overworld"), 1));
         Regions.register(new HibiscusRegion2(new ResourceLocation(MOD_ID, "overworld_2"), 3));
         Regions.register(new HibiscusRegion3(new ResourceLocation(MOD_ID, "overworld_3"), 1));
//         Regions.register(new HibiscusRegion4(new Identifier(MOD_ID, "overworld_4"), 1));
         Regions.register(new HibiscusRegion5(new ResourceLocation(MOD_ID, "overworld_5"), 3));
         SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD,
                 MOD_ID,
                 NatureSpiritSurfaceRules.makeRules()
         );
      }
   }
}
