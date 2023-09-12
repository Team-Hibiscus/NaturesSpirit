package net.hibiscus.naturespirit.entity;

import com.mojang.serialization.Codec;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.mob.AbstractPiglinEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Optional;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;

public class HibiscusMemoryModuleTypes {

   public static final MemoryModuleType <List <BisonEntity>> NEARBY_ADULT_BISONS;

   private static <U> MemoryModuleType<U> register(String id, Codec <U> codec) {
      return (MemoryModuleType) Registry.register(Registries.MEMORY_MODULE_TYPE, new Identifier(MOD_ID, id), new MemoryModuleType(Optional.of(codec)));
   }
   public static void registerMemoryModuleTypes() {}
   private static <U> MemoryModuleType<U> register(String id) {
      return (MemoryModuleType)Registry.register(Registries.MEMORY_MODULE_TYPE, new Identifier(MOD_ID, id), new MemoryModuleType(Optional.empty()));
   }
   static {
      NEARBY_ADULT_BISONS = register("nearby_adult_bisons");
   }
}
