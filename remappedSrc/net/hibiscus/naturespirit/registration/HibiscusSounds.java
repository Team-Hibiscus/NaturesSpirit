package net.hibiscus.naturespirit.registration;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class HibiscusSounds {
   public static final Holder.Reference<SoundEvent> MUSIC_OVERWORLD_AUTUMN = registerReference("music.overworld.autumn");

   private static Holder.Reference<SoundEvent> registerReference(String id) {
      return registerReference(new ResourceLocation(MOD_ID, id));
   }

   private static Holder.Reference<SoundEvent> registerReference(ResourceLocation id) {
      return registerReference(id, id);
   }

   private static Holder.Reference<SoundEvent> registerReference(ResourceLocation id, ResourceLocation soundId) {
      return Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(soundId));
   }
   public static void registerSounds() {}
}
