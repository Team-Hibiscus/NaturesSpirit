package net.hibiscus.naturespirit.terrablender;

import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;

public class NatureSpiritTerraBlender implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {
        {
            Regions.register(new HibiscusRegion(new ResourceLocation(MOD_ID, "overworld"), 2));
            Regions.register(new HibiscusRegion2(new ResourceLocation(MOD_ID, "overworld_2"), 1));


            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, NatureSpiritSurfaceRule.makeRules());
        }
    }
}
