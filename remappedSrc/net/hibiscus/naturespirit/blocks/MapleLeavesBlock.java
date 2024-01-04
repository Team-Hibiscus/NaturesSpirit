package net.hibiscus.naturespirit.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.client.util.ParticleUtil;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class MapleLeavesBlock extends LeavesBlock {
   ParticleEffect particle;

   public MapleLeavesBlock(Settings settings, ParticleEffect particle) {
      super(settings);
      this.particle = particle;
   }

   public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
      super.randomDisplayTick(state, world, pos, random);
      if(random.nextInt(150) == 0) {
         BlockPos blockPos = pos.down();
         BlockState blockState = world.getBlockState(blockPos);
         if(!isFaceFullSquare(blockState.getCollisionShape(world, blockPos), Direction.UP)) {
            ParticleUtil.spawnParticle(world, pos, random, particle);
         }
      }
   }
}