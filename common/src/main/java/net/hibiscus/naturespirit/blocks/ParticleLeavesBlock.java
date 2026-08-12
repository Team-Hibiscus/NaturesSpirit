package net.hibiscus.naturespirit.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;

import java.util.function.Supplier;

public class ParticleLeavesBlock extends LeavesBlock {

  Supplier<? extends ParticleOptions> particle;
  int chance;

  public ParticleLeavesBlock(Properties settings, Supplier<? extends ParticleOptions> particle, int chance) {
    super(1F / chance, settings);
    this.particle = particle;
    this.chance = chance;
  }

  @Override
  public MapCodec<? extends LeavesBlock> codec() {
    return null;
  }

  @Override
  protected void spawnFallingLeavesParticle(Level world, BlockPos pos, RandomSource random) {
    ParticleUtils.spawnParticleBelow(world, pos, random, particle.get());
  }
}
