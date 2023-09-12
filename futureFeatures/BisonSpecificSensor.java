//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.hibiscus.naturespirit.entity;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.LivingTargetCache;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class BisonSpecificSensor extends Sensor <BisonEntity> {
   public BisonSpecificSensor() {
   }

   public Set<MemoryModuleType<?>> getOutputMemoryModules() {
      return ImmutableSet.of(MemoryModuleType.VISIBLE_MOBS, HibiscusMemoryModuleTypes.NEARBY_ADULT_BISONS);
   }

   protected void sense(ServerWorld serverWorld, BisonEntity bisonEntity) {
      Brain<?> brain = bisonEntity.getBrain();
      List<BisonEntity> list = Lists.newArrayList();
      LivingTargetCache livingTargetCache = brain.getOptionalRegisteredMemory(MemoryModuleType.VISIBLE_MOBS).orElse(LivingTargetCache.empty());
      Iterator var8 = livingTargetCache.iterate((livingEntityx) -> !livingEntityx.isBaby() && (livingEntityx instanceof BisonEntity)).iterator();

      while(var8.hasNext()) {
         LivingEntity livingEntity = (LivingEntity)var8.next();
         if (livingEntity instanceof BisonEntity bisonEntity1) {
            list.add(bisonEntity1);
         }
      }

      brain.remember(HibiscusMemoryModuleTypes.NEARBY_ADULT_BISONS, list);
   }
}
