package net.hibiscus.naturespirit.blocks;

import net.hibiscus.naturespirit.datagen.NSConfiguredFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;

public class RedMossBlock extends Block implements BonemealableBlock {

  public RedMossBlock(Properties settings) {
    super(settings);
  }

  @Override
  public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
    return world.getBlockState(pos.above()).isAir();
  }

  @Override
  public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
    return true;
  }

  @Override
  public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
    world.registryAccess().registry(Registries.CONFIGURED_FEATURE).flatMap((registry) -> registry.getHolder(NSConfiguredFeatures.RED_MOSS_PATCH_BONEMEAL))
        .ifPresent((reference) -> {
          reference.value().place(world, world.getChunkSource().getGenerator(), random, pos.above());
        });
  }
}
