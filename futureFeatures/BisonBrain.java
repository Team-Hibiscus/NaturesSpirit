//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.hibiscus.naturespirit.entity;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.hibiscus.naturespirit.registration.HibiscusEntityTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.brain.*;
import net.minecraft.entity.ai.brain.task.*;
import net.minecraft.entity.passive.CamelEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;

import java.util.function.Function;

public class BisonBrain {
   public static final int PREPARE_RAM_DURATION = 20;
   public static final int MAX_RAM_TARGET_DISTANCE = 7;
   private static final UniformIntProvider WALKING_SPEED = UniformIntProvider.create(5, 16);
   private static final float BREEDING_WALK_SPEED = 1.0F;
   private static final float FOLLOWING_TARGET_WALK_SPEED = 1.0F;
   private static final float TEMPTED_WALK_SPEED = 1.25F;
   private static final float FOLLOW_ADULT_WALK_SPEED = 1.25F;
   private static final float NORMAL_WALK_SPEED = 2.0F;
   private static final float PREPARING_RAM_WALK_SPEED = 1.25F;
   private static final UniformIntProvider RAM_COOLDOWN_RANGE = UniformIntProvider.create(2, 2);
   private static final TargetPredicate RAM_TARGET_PREDICATE = TargetPredicate.createAttackable().setPredicate((entity) -> {
      return !entity.getType().equals(HibiscusEntityTypes.BISON) && entity.getWorld().getWorldBorder().contains(entity.getBoundingBox());
   });
   private static final float RAM_SPEED = 3.0F;
   public static final int MIN_RAM_TARGET_DISTANCE = 4;
   public static final float ADULT_RAM_STRENGTH_MULTIPLIER = 2.5F;
   public static final float BABY_RAM_STRENGTH_MULTIPLIER = 1.0F;

   public BisonBrain() {
   }

   protected static Brain <?> create(Brain <BisonEntity> brain) {
      addCoreActivities(brain);
      addIdleActivities(brain);
      addRamActivities(brain);
      brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
      brain.setDefaultActivity(Activity.IDLE);
      brain.resetPossibleActivities(); return brain;
   }

   private static void addCoreActivities(Brain <BisonEntity> brain) {
      brain.setTaskList(Activity.CORE, 0, ImmutableList.of(
              new StayAboveWaterTask(0.8F),
              new FleeTask(2.0F),
              new LookAroundTask(25, 60),
              new WanderAroundTask(),
              new TemptationCooldownTask(MemoryModuleType.TEMPTATION_COOLDOWN_TICKS),
              new TemptationCooldownTask(MemoryModuleType.RAM_COOLDOWN_TICKS)
      ));
   }

   private static void addIdleActivities(Brain <BisonEntity> brain) {
      brain.setTaskList(Activity.IDLE, ImmutableList.of(Pair.of(0, LookAtMobWithIntervalTask.follow(EntityType.PLAYER, 6.0F, UniformIntProvider.create(30, 60))),
              Pair.of(0, new BreedTask(HibiscusEntityTypes.BISON, 1.0F)),
              Pair.of(1, new TemptTask((bisonEntity) -> {
                 return 1.25F;
              })),
              Pair.of(2, WalkTowardClosestAdultTask.create(WALKING_SPEED, 1.25F)),
              Pair.of(3, new RandomTask(ImmutableList.of(Pair.of(StrollTask.create(1.0F), 2), Pair.of(GoTowardsLookTargetTask.create(1.0F, 3), 2), Pair.of(new WaitTask(30, 50), 1))))
      ), ImmutableSet.of(Pair.of(MemoryModuleType.RAM_TARGET, MemoryModuleState.VALUE_ABSENT)));
   }


   private static void addRamActivities(Brain <BisonEntity> brain) {
      brain.setTaskList(Activity.RAM,
              ImmutableList.of(Pair.of(
                      0,
                      new BisonRamImpactTask((bison) -> RAM_COOLDOWN_RANGE, RAM_TARGET_PREDICATE, 3.0F, (bison) -> bison.isBaby() ? 1.0 : 2.5, (bison) -> SoundEvents.ENTITY_GOAT_RAM_IMPACT)
              ), Pair.of(1, new PrepareRamTask<>((bison) -> RAM_COOLDOWN_RANGE.getMin(), 1, 30, 3.0F, RAM_TARGET_PREDICATE, 5, (bison) -> SoundEvents.ENTITY_GOAT_PREPARE_RAM))),
              ImmutableSet.of(Pair.of(MemoryModuleType.TEMPTING_PLAYER, MemoryModuleState.VALUE_ABSENT),
                      Pair.of(MemoryModuleType.BREED_TARGET, MemoryModuleState.VALUE_ABSENT),
                      Pair.of(MemoryModuleType.RAM_COOLDOWN_TICKS, MemoryModuleState.VALUE_ABSENT)
              )
      );
   }

   public static void updateActivities(BisonEntity bison) {
//      bison.getBrain().forget(MemoryModuleType.NEAREST_VISIBLE_ADULT);
      bison.getBrain().resetPossibleActivities(ImmutableList.of(Activity.RAM, Activity.IDLE));
   }

   public static Ingredient getTemptItems() {
      return Ingredient.ofItems(Items.WHEAT);
   }

   public static class WalkTowardsBisonTask {
      public WalkTowardsBisonTask() {
      }

      public static SingleTickTask <PassiveEntity> create(UniformIntProvider executionRange, float speed) {
         return create(executionRange, (entity) -> {
            return speed;
         });
      }

      public static SingleTickTask <PassiveEntity> create(UniformIntProvider executionRange, Function <LivingEntity, Float> speed) {
         return TaskTriggerer.task((context) -> {
            return context.group(
                    context.queryMemoryValue(MemoryModuleType.NEAREST_VISIBLE_ADULT),
                    context.queryMemoryOptional(MemoryModuleType.LOOK_TARGET),
                    context.queryMemoryAbsent(MemoryModuleType.WALK_TARGET)
            ).apply(context, (nearestVisibleAdult, lookTarget, walkTarget) -> {
               return (world, entity, time) -> {
                  {
                     PassiveEntity passiveEntity = (PassiveEntity) context.getValue(nearestVisibleAdult); if(entity.isInRange(
                          passiveEntity,
                          (double) (executionRange.getMax() + 1)
                  ) && !entity.isInRange(passiveEntity, (double) executionRange.getMin())) {
                     WalkTarget walkTargetx = new WalkTarget(new EntityLookTarget(passiveEntity, false), (Float) speed.apply(entity), executionRange.getMin() - 1);
                     lookTarget.remember(new EntityLookTarget(passiveEntity, true)); walkTarget.remember(walkTargetx); return true;
                  }
                  else {
                     return false;
                  }
                  }
               };
            });
         });
      }
   }
}
