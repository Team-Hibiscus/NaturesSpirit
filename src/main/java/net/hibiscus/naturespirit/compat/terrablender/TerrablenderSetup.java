package net.hibiscus.naturespirit.compat.terrablender;

import net.hibiscus.naturespirit.config.NSConfig;
import net.hibiscus.naturespirit.world.NSSurfaceRules;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;

public class TerrablenderSetup {
    public static void registerTerrablenderBiomes() {
        Regions.register(new TerraFeraxRegion(ResourceLocation.fromNamespaceAndPath(MOD_ID, "terra_ferax"), NSConfig.terraFeraxWeight));
        Regions.register(new TerraSolarisRegion(ResourceLocation.fromNamespaceAndPath(MOD_ID, "terra_solaris"), NSConfig.terraSolarisWeight));
        Regions.register(new TerraFlavaRegion(ResourceLocation.fromNamespaceAndPath(MOD_ID, "terra_flava"), NSConfig.terraFlavaWeight));
        Regions.register(new TerraMaterRegion(ResourceLocation.fromNamespaceAndPath(MOD_ID, "terra_mater"), NSConfig.terraMaterWeight));
        Regions.register(new TerraLaetaRegion(ResourceLocation.fromNamespaceAndPath(MOD_ID, "terra_laeta"), NSConfig.terraLaetaWeight));
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, NSSurfaceRules.makeRules());
    }
}
