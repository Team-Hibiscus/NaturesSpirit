package net.hibiscus.naturespirit.registration;

import net.hibiscus.naturespirit.NatureSpirit;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class NSParticleTypes {

  public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, NatureSpirit.MOD_ID);
  public static final Supplier<SimpleParticleType> RED_MAPLE_LEAVES_PARTICLE = registerParticleType("red_maple_leaves", () -> new SimpleParticleType(false));
  public static final Supplier<SimpleParticleType> ORANGE_MAPLE_LEAVES_PARTICLE = registerParticleType("orange_maple_leaves", () -> new SimpleParticleType(false));
  public static final Supplier<SimpleParticleType> YELLOW_MAPLE_LEAVES_PARTICLE = registerParticleType("yellow_maple_leaves", () -> new SimpleParticleType(false));
  public static final Supplier<SimpleParticleType> MILK_PARTICLE = registerParticleType("milk", () -> new SimpleParticleType(false));

  public static <T extends ParticleType<?>> Supplier<T> registerParticleType(String name, Supplier<T> particleType) {
    return NSParticleTypes.PARTICLES.register(name, particleType);
  }

}
