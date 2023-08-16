package net.hibiscus.naturespirit.terrablender;

import net.hibiscus.naturespirit.Constants;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

import static net.hibiscus.naturespirit.Constants.MOD_ID;

@Mod(Constants.MOD_ID)
public class NatureSpiritTerrablender
{

   public NatureSpiritTerrablender()
   {
      IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
      bus.addListener(this::commonSetup);
   }

   private void commonSetup(final FMLCommonSetupEvent event)
   {
      event.enqueueWork(() ->
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
      });
   }
}
