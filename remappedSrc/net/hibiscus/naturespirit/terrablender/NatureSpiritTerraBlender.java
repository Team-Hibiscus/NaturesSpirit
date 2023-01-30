package net.hibiscus.naturespirit.terrablender;

import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;

import net.minecraft.util.Identifier;

public class NatureSpiritTerraBlender implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {
        {
            Regions.register(new HibiscusRegion(new Identifier(MOD_ID, "overworld"), 2));
            Regions.register(new HibiscusRegion2(new Identifier(MOD_ID, "overworld_2"), 1));


            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, NatureSpiritSurfaceRule.makeRules());
        }
    }
}
