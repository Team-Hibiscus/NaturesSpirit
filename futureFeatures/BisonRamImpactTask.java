//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.hibiscus.naturespirit.entity;

import com.google.common.collect.ImmutableMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;

import net.hibiscus.naturespirit.registration.HibiscusEntityTypes;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.brain.*;
import net.minecraft.entity.ai.brain.task.MultiTickTask;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.GoatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.math.*;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class BisonRamImpactTask extends MultiTickTask <BisonEntity> {
   public static final int RUN_TIME = 200;
   public static final float SPEED_STRENGTH_MULTIPLIER = 1.65F;
   private final Function<BisonEntity, UniformIntProvider> cooldownRangeFactory;
   private final TargetPredicate targetPredicate;
   private final float speed;
   private final ToDoubleFunction<BisonEntity> strengthMultiplierFactory;
   private Vec3d direction;
   private final Function<BisonEntity, SoundEvent> impactSoundFactory;

   public BisonRamImpactTask(Function<BisonEntity, UniformIntProvider> cooldownRangeFactory, TargetPredicate targetPredicate, float speed, ToDoubleFunction<BisonEntity> strengthMultiplierFactory, Function<BisonEntity, SoundEvent> impactSoundFactory) {
      super(ImmutableMap.of(MemoryModuleType.RAM_COOLDOWN_TICKS, MemoryModuleState.VALUE_ABSENT, MemoryModuleType.RAM_TARGET, MemoryModuleState.VALUE_PRESENT), 200);
      this.cooldownRangeFactory = cooldownRangeFactory;
      this.targetPredicate = targetPredicate;
      this.speed = speed;
      this.strengthMultiplierFactory = strengthMultiplierFactory;
      this.impactSoundFactory = impactSoundFactory;
      this.direction = Vec3d.ZERO;
   }

   protected boolean shouldRun(ServerWorld serverWorld, BisonEntity bisonEntity) {
      return bisonEntity.getBrain().hasMemoryModule(MemoryModuleType.RAM_TARGET);
   }

   protected boolean shouldKeepRunning(ServerWorld serverWorld, BisonEntity bisonEntity, long l) {
      return bisonEntity.getBrain().hasMemoryModule(MemoryModuleType.RAM_TARGET);
   }

   protected void run(ServerWorld serverWorld, BisonEntity bisonEntity, long l) {
      BlockPos blockPos = bisonEntity.getBlockPos();
      Brain<?> brain = bisonEntity.getBrain();
      Optional <List <BisonEntity>> list = brain.getOptionalRegisteredMemory(HibiscusMemoryModuleTypes.NEARBY_ADULT_BISONS);
      Vec3d vec3d = (Vec3d)brain.getOptionalRegisteredMemory(MemoryModuleType.RAM_TARGET).get();
      list.ifPresent(bisonEntities -> bisonEntities.forEach((entity) -> {
         entity.getBrain().remember(MemoryModuleType.RAM_TARGET, vec3d);
         entity.getBrain().doExclusively(Activity.RAM);
      }));
      list.ifPresent((bisonEntities) -> MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(Text.literal("got herd and tried send")));
      this.direction = (new Vec3d((double)blockPos.getX() - vec3d.getX(), 0.0, (double)blockPos.getZ() - vec3d.getZ())).normalize();
      brain.remember(MemoryModuleType.WALK_TARGET, new WalkTarget(vec3d, this.speed, 0));
   }
   protected void keepRunning(ServerWorld serverWorld, BisonEntity bisonEntity, long l) {
      List<LivingEntity> list = serverWorld.getTargets(LivingEntity.class, this.targetPredicate, bisonEntity, bisonEntity.getBoundingBox());
      Brain<?> brain = bisonEntity.getBrain();
      if (!list.isEmpty()) {
         LivingEntity livingEntity = (LivingEntity)list.get(0);
         livingEntity.damage(serverWorld.getDamageSources().mobAttackNoAggro(bisonEntity), (float)bisonEntity.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE));
         int i = bisonEntity.hasStatusEffect(StatusEffects.SPEED) ? bisonEntity.getStatusEffect(StatusEffects.SPEED).getAmplifier() + 1 : 0;
         int j = bisonEntity.hasStatusEffect(StatusEffects.SLOWNESS) ? bisonEntity.getStatusEffect(StatusEffects.SLOWNESS).getAmplifier() + 1 : 0;
         float f = 0.25F * (float)(i - j);
         float g = MathHelper.clamp(bisonEntity.getMovementSpeed() * 1.65F, 0.2F, 3.0F) + f;
         float h = livingEntity.blockedByShield(serverWorld.getDamageSources().mobAttack(bisonEntity)) ? 0.5F : 1.0F;
         livingEntity.takeKnockback((double)(h * g) * this.strengthMultiplierFactory.applyAsDouble(bisonEntity), this.direction.getX(), this.direction.getZ());
         this.finishRam(serverWorld, bisonEntity);
         serverWorld.playSoundFromEntity((PlayerEntity)null, bisonEntity, (SoundEvent)this.impactSoundFactory.apply(bisonEntity), SoundCategory.NEUTRAL, 1.0F, 1.0F);
      } else {
         Optional<WalkTarget> optional = brain.getOptionalRegisteredMemory(MemoryModuleType.WALK_TARGET);
         Optional<Vec3d> optional2 = brain.getOptionalRegisteredMemory(MemoryModuleType.RAM_TARGET);
         boolean bl2 = optional.isEmpty() || optional2.isEmpty() || ((WalkTarget)optional.get()).getLookTarget().getPos().isInRange((Position)optional2.get(), 0.75);
         if (bl2) {
            this.finishRam(serverWorld, bisonEntity);
         }
      }

   }


   protected void finishRam(ServerWorld world, BisonEntity bisonEntity) {
      world.sendEntityStatus(bisonEntity, (byte)59);
      bisonEntity.getBrain().remember(MemoryModuleType.RAM_COOLDOWN_TICKS, ((UniformIntProvider)this.cooldownRangeFactory.apply(bisonEntity)).get(world.random));
      bisonEntity.getBrain().forget(MemoryModuleType.RAM_TARGET);
      MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(Text.literal("finish ram"));
   }
}
