package net.hibiscus.naturespirit.registration;

import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.advancements.CoconutHitCriterion;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.advancement.criterion.Criterion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class HibiscusCriteria {
   public static void registerCriteria() {}
   public static final CoconutHitCriterion COCONUT_HIT_CRITERION = register( "coconut_hit",new CoconutHitCriterion());


   public static <T extends Criterion <?>> T register(String id, T criterion) {
      return (T) Registry.register(Registries.CRITERION, new Identifier(NatureSpirit.MOD_ID, id), criterion);
   }
}
