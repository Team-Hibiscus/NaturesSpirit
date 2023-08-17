package net.hibiscus.naturespirit.util;

import net.hibiscus.naturespirit.mixin.FoliagePlacerMixin;
import net.hibiscus.naturespirit.mixin.TreeDecoratorMixin;
import net.hibiscus.naturespirit.world.foliage_placer.*;
import net.hibiscus.naturespirit.world.tree_decorator.WisteriaVinesTreeDecorator;
import net.hibiscus.naturespirit.world.trunk.OliveTrunkPlacer;
import net.hibiscus.naturespirit.world.trunk.SugiTrunkPlacer;
import net.hibiscus.naturespirit.world.trunk.WisteriaTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import static net.hibiscus.naturespirit.mixin.TrunkPlacerTypeMixin.callRegister;

public class HibiscusFabricWorldGen {


   public static final TrunkPlacerType <WisteriaTrunkPlacer> WISTERIA_TRUNK_PLACER = callRegister("wisteria_trunk_placer",
           WisteriaTrunkPlacer.CODEC
   );
   public static final TrunkPlacerType <SugiTrunkPlacer> SUGI_TRUNK_PLACER = callRegister("sugi_trunk_placer",
           SugiTrunkPlacer.CODEC
   );
   public static final TrunkPlacerType <OliveTrunkPlacer> OLIVE_TRUNK_PLACER = callRegister("olive_trunk_placer",
           OliveTrunkPlacer.CODEC
   );
   public static final TreeDecoratorType <WisteriaVinesTreeDecorator> WISTERIA_VINES_TREE_DECORATOR = TreeDecoratorMixin.callRegister("wisteria_vines_tree_decorator",
           WisteriaVinesTreeDecorator.CODEC
   );


   public static final FoliagePlacerType <WisteriaFoliagePlacer> WISTERIA_FOLIAGE_PLACER_TYPE = FoliagePlacerMixin.callRegister("wisteria_foliage_placer",
           WisteriaFoliagePlacer.CODEC
   );
   public static final FoliagePlacerType <SugiFoliagePlacer> SUGI_FOLIAGE_PLACER_TYPE = FoliagePlacerMixin.callRegister("sugi_foliage_placer",
           SugiFoliagePlacer.CODEC
   );
   public static final FoliagePlacerType <AspenFoliagePlacer> ASPEN_FOLIAGE_PLACER_TYPE = FoliagePlacerMixin.callRegister("aspen_foliage_placer",
           AspenFoliagePlacer.CODEC
   );
   public static final FoliagePlacerType <FirFoliagePlacer> FIR_FOLIAGE_PLACER_TYPE = FoliagePlacerMixin.callRegister("fir_foliage_placer",
           FirFoliagePlacer.CODEC
   );
   public static final FoliagePlacerType <CypressFoliagePlacer> CYPRESS_FOLIAGE_PLACER_TYPE = FoliagePlacerMixin.callRegister("cypress_foliage_placer",
           CypressFoliagePlacer.CODEC
   );
   public static void registerWorldGen() {
   };
}
