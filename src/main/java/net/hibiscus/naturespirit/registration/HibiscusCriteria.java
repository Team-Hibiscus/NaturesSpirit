package net.hibiscus.naturespirit.registration;

import net.hibiscus.naturespirit.advancements.CoconutHitCriterion;
import net.hibiscus.naturespirit.mixin.CriteriaAccessor;

public class HibiscusCriteria {
   public static void registerCriteria() {}
   public static final CoconutHitCriterion COCONUT_HIT_CRITERION = CriteriaAccessor.callRegister(new CoconutHitCriterion());
}
