package net.hibiscus.naturespirit;

import net.hibiscus.naturespirit.terrablender.HibiscusBiomes;
import net.hibiscus.naturespirit.util.HibiscusForgeWorldGen;
import net.hibiscus.naturespirit.util.HibiscusRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatType;
import net.minecraft.world.entity.animal.CatVariant;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static net.hibiscus.naturespirit.CommonNatureSpirit.EAT_PIZZA_SLICE;

@Mod(Constants.MOD_ID)
public class ForgeNatureSpirit {
    private static final DeferredRegister<CatVariant> CAT_VARIANTS = DeferredRegister.create(Registries.CAT_VARIANT, Constants.MOD_ID);
    public static final RegistryObject <CatVariant> TRANS = CAT_VARIANTS.register("trans", () -> new CatVariant(new ResourceLocation("textures/entity/cat/trans" + ".png" + Constants.MOD_ID)));

    private static final DeferredRegister <ResourceLocation> STATS = DeferredRegister.create(Registries.CUSTOM_STAT, Constants.MOD_ID);
    public static final RegistryObject <ResourceLocation> REGISTER_EAT_PIZZA_SLICE = STATS.register("eat_pizza_slice", () -> EAT_PIZZA_SLICE);


    public ForgeNatureSpirit() {

        CAT_VARIANTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        STATS.register(FMLJavaModLoadingContext.get().getModEventBus());
        // This method is invoked by the Forge mod loader when it is ready
        // to load your mod. You can access Forge and Common code in this
        // project.
    
        // Use Forge to bootstrap the Common mod.
        Constants.LOG.info("Hello Forge world!");
        CommonNatureSpirit.init();
        HibiscusBiomes.registerBiomes();
        HibiscusRegister.registerBlocks();
        HibiscusRegister.registerItems();
        HibiscusForgeWorldGen.registerWorldGen();
    }
}