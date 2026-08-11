package net.hibiscus.naturespirit.registration;


import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.advancements.CoconutHitCriterion;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class NSCriteria {

  public static final DeferredRegister<CriterionTrigger<?>> CRITERIA = DeferredRegister.create(BuiltInRegistries.TRIGGER_TYPES, NatureSpirit.MOD_ID);
  public static final Supplier<CoconutHitCriterion> COCONUT_HIT_CRITERION = CRITERIA.register("coconut_hit", CoconutHitCriterion::new);

  public static void registerCriteria() {}
}