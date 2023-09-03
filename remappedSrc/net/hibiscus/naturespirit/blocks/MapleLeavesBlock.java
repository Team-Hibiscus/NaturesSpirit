package net.hibiscus.naturespirit.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;

public class MapleLeavesBlock extends LeavesBlock {
   ParticleOptions particle;
   public MapleLeavesBlock(Properties settings, ParticleOptions particle) {
      super(settings);
      this.particle = particle;
   }

   public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
      super.animateTick(state, world, pos, random);
      if (random.nextInt(150) == 0) {
         BlockPos blockPos = pos.below();
         BlockState blockState = world.getBlockState(blockPos);
         if (!isFaceFull(blockState.getCollisionShape(world, blockPos), Direction.UP)) {
            ParticleUtils.spawnParticleBelow(world, pos, random, particle);
         }
      }
   }
}