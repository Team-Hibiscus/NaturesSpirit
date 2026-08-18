package net.hibiscus.naturespirit.registration;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;

import java.util.function.Supplier;

public class NSParticleTypes {

  public static final NSRegistrar<ParticleType<?>> PARTICLES = NSRegistrar.of(Registries.PARTICLE_TYPE);
  public static final NSHolder<SimpleParticleType> RED_MAPLE_LEAVES_PARTICLE = registerParticleType("red_maple_leaves", () -> new SimpleParticleType(false));
  public static final NSHolder<SimpleParticleType> ORANGE_MAPLE_LEAVES_PARTICLE = registerParticleType("orange_maple_leaves", () -> new SimpleParticleType(false));
  public static final NSHolder<SimpleParticleType> YELLOW_MAPLE_LEAVES_PARTICLE = registerParticleType("yellow_maple_leaves", () -> new SimpleParticleType(false));
  public static final NSHolder<SimpleParticleType> MILK_PARTICLE = registerParticleType("milk", () -> new SimpleParticleType(false));

  public static <T extends ParticleType<?>> NSHolder<T> registerParticleType(String name, Supplier<T> particleType) {
    return PARTICLES.register(name, particleType);
  }

  public static void bootstrap() {
  }
}
