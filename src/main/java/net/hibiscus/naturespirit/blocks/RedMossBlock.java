package net.hibiscus.naturespirit.blocks;

import net.hibiscus.naturespirit.datagen.NaturesSpiritConfiguredFeatures;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class RedMossBlock extends Block implements Fertilizable {

   public RedMossBlock(Settings settings) {
      super(settings);
   }

   public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
      return world.getBlockState(pos.up()).isAir();
   }

   public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
      return true;
   }

   public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
      world.getRegistryManager().getOptional(RegistryKeys.CONFIGURED_FEATURE).flatMap((registry) -> registry.getEntry(NaturesSpiritConfiguredFeatures.RED_MOSS_PATCH_BONEMEAL)).ifPresent((reference) -> {
         reference.value().generate(world, world.getChunkManager().getChunkGenerator(), random, pos.up());
      });
   }
}
