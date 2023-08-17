package net.hibiscus.naturespirit.util;


import net.hibiscus.naturespirit.world.foliage_placer.*;
import net.hibiscus.naturespirit.world.tree_decorator.WisteriaVinesTreeDecorator;
import net.hibiscus.naturespirit.world.trunk.OliveTrunkPlacer;
import net.hibiscus.naturespirit.world.trunk.SugiTrunkPlacer;
import net.hibiscus.naturespirit.world.trunk.WisteriaTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class HibiscusForgeWorldGen {


   private static final DeferredRegister <TrunkPlacerType<?>> TRUNK_PLACERS = DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, "minecraft");
   public static final RegistryObject <TrunkPlacerType <WisteriaTrunkPlacer>> WISTERIA_TRUNK_PLACER = TRUNK_PLACERS.register("wisteria_trunk_placer",
           () -> new TrunkPlacerType<>(WisteriaTrunkPlacer.CODEC)
   );
   public static final RegistryObject <TrunkPlacerType <SugiTrunkPlacer>> SUGI_TRUNK_PLACER = TRUNK_PLACERS.register("sugi_trunk_placer",
           () -> new TrunkPlacerType<>(SugiTrunkPlacer.CODEC)
   );
   public static final RegistryObject <TrunkPlacerType <OliveTrunkPlacer>> OLIVE_TRUNK_PLACER = TRUNK_PLACERS.register("olive_trunk_placer",
           () -> new TrunkPlacerType<>(OliveTrunkPlacer.CODEC)
   );

   private static final DeferredRegister <TreeDecoratorType<?>> TREE_DECORATORS = DeferredRegister.create(ForgeRegistries.TREE_DECORATOR_TYPES, "minecraft");
   public static final RegistryObject <TreeDecoratorType <WisteriaVinesTreeDecorator>> WISTERIA_VINES_TREE_DECORATOR = TREE_DECORATORS.register("wisteria_vines_tree_decorator",
           () -> new TreeDecoratorType <>(WisteriaVinesTreeDecorator.CODEC)
   );

   private static final DeferredRegister <FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(ForgeRegistries.FOLIAGE_PLACER_TYPES, "minecraft");

   public static final RegistryObject <FoliagePlacerType <WisteriaFoliagePlacer>> WISTERIA_FOLIAGE_PLACER_TYPE = FOLIAGE_PLACERS.register("wisteria_foliage_placer",
           () -> new FoliagePlacerType <>(WisteriaFoliagePlacer.CODEC)
   );
   public static final RegistryObject <FoliagePlacerType <SugiFoliagePlacer>> SUGI_FOLIAGE_PLACER_TYPE = FOLIAGE_PLACERS.register("sugi_foliage_placer",
           () -> new FoliagePlacerType <>(SugiFoliagePlacer.CODEC)
   );
   public static final RegistryObject <FoliagePlacerType <AspenFoliagePlacer>> ASPEN_FOLIAGE_PLACER_TYPE = FOLIAGE_PLACERS.register("aspen_foliage_placer",
           () -> new FoliagePlacerType <>(AspenFoliagePlacer.CODEC)
   );
   public static final RegistryObject <FoliagePlacerType <FirFoliagePlacer>> FIR_FOLIAGE_PLACER_TYPE = FOLIAGE_PLACERS.register("fir_foliage_placer",
           () -> new FoliagePlacerType <>(FirFoliagePlacer.CODEC)
   );
   public static final RegistryObject <FoliagePlacerType <CypressFoliagePlacer>> CYPRESS_FOLIAGE_PLACER_TYPE = FOLIAGE_PLACERS.register("cypress_foliage_placer",
           () -> new FoliagePlacerType <>(CypressFoliagePlacer.CODEC)
   );
   public static void registerWorldGen() {
      FOLIAGE_PLACERS.register(FMLJavaModLoadingContext.get().getModEventBus());
      TREE_DECORATORS.register(FMLJavaModLoadingContext.get().getModEventBus());
      TRUNK_PLACERS.register(FMLJavaModLoadingContext.get().getModEventBus());
   };
}
