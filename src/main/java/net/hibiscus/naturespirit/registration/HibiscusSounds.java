package net.hibiscus.naturespirit.registration;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;

public class HibiscusSounds {
   public static final RegistryEntry.Reference<SoundEvent> MUSIC_OVERWORLD_AUTUMN = registerReference("music.overworld.autumn");

   private static RegistryEntry.Reference<SoundEvent> registerReference(String id) {
      return registerReference(new Identifier(MOD_ID, id));
   }

   private static RegistryEntry.Reference<SoundEvent> registerReference(Identifier id) {
      return registerReference(id, id);
   }

   private static RegistryEntry.Reference<SoundEvent> registerReference(Identifier id, Identifier soundId) {
      return Registry.registerReference(Registries.SOUND_EVENT, id, SoundEvent.of(soundId));
   }
   public static void registerSounds() {}
}
