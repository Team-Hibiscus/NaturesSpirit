package net.hibiscus.naturespirit.registration;


import net.hibiscus.naturespirit.advancements.CoconutHitCriterion;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.Registries;

public class NSCriteria {

  public static final NSRegistrar<CriterionTrigger<?>> CRITERIA = NSRegistrar.of(Registries.TRIGGER_TYPE);
  public static final NSHolder<CoconutHitCriterion> COCONUT_HIT_CRITERION = CRITERIA.register("coconut_hit", CoconutHitCriterion::new);

  public static void bootstrap() {
  }
}
