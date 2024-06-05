package net.hibiscus.naturespirit.registration;

import net.fabricmc.fabric.mixin.object.builder.CriteriaAccessor;
import net.hibiscus.naturespirit.advancements.CoconutHitCriterion;

public class HibiscusCriteria {
   public static void registerCriteria() {}
   public static final CoconutHitCriterion COCONUT_HIT_CRITERION = CriteriaAccessor.callRegister(new CoconutHitCriterion());
}
