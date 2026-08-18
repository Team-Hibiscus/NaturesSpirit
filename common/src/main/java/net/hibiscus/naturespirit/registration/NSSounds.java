package net.hibiscus.naturespirit.registration;

import net.hibiscus.naturespirit.NaturesSpirit;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;

@SuppressWarnings("unused")
public class NSSounds {

  public static final NSRegistrar<SoundEvent> SOUND_EVENTS = NSRegistrar.of(Registries.SOUND_EVENT);

  public static final NSHolder<SoundEvent> MUSIC_OVERWORLD_ASPEN = registerReference("music.overworld.aspen");
  public static final NSHolder<SoundEvent> MUSIC_OVERWORLD_MAPLE = registerReference("music.overworld.maple");
  public static final NSHolder<SoundEvent> MUSIC_OVERWORLD_WISTERIA = registerReference("music.overworld.wisteria");
  public static final NSHolder<SoundEvent> MUSIC_OVERWORLD_REDWOOD = registerReference("music.overworld.redwood");
  public static final NSHolder<SoundEvent> MUSIC_OVERWORLD_DESERT = registerReference("music.overworld.desert");
  public static final NSHolder<SoundEvent> MUSIC_OVERWORLD_ARID = registerReference("music.overworld.arid");
  public static final NSHolder<SoundEvent> MUSIC_OVERWORLD_TROPICAL = registerReference("music.overworld.tropical");
  public static final NSHolder<SoundEvent> MUSIC_OVERWORLD_ALPINE = registerReference("music.overworld.alpine");

  public static void bootstrap() {
  }

  private static NSHolder<SoundEvent> registerReference(String id) {
    return SOUND_EVENTS.register(id, () -> SoundEvent.createVariableRangeEvent(NaturesSpirit.id(id)));
  }
}
