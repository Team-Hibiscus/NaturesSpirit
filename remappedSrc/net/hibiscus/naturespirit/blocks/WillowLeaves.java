package net.hibiscus.naturespirit.blocks;

import net.hibiscus.naturespirit.registration.block_registration.HibiscusWoods;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class WillowLeaves extends LeavesBlock implements BonemealableBlock {
   public WillowLeaves(Properties properties) {
      super(properties);
   }

   @Override
   public boolean isValidBonemealTarget(@NotNull LevelReader levelReader, @NotNull BlockPos blockPos, @NotNull BlockState blockState, boolean bl) {
      Block vineBlock = HibiscusWoods.WILLOW_VINES;
      Block vineBlock2 = HibiscusWoods.WILLOW_VINES_PLANT;
      Optional <BlockPos> optional = BlockUtil.getTopConnectedBlock(levelReader,
              blockPos,
              vineBlock2,
              Direction.DOWN,
              vineBlock
      );
      return (optional.isPresent() && levelReader.getBlockState(optional.get().relative(Direction.DOWN))
              .isAir()) || levelReader.getBlockState(blockPos.relative(Direction.DOWN)).isAir();
   }


   @Override public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
      return true;
   }

   @Override public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
      Block vineBlock = HibiscusWoods.WILLOW_VINES;
      Block vineBlock2 = HibiscusWoods.WILLOW_VINES_PLANT;

      Optional <BlockPos> optional = BlockUtil.getTopConnectedBlock(serverLevel,
              blockPos,
              vineBlock2,
              Direction.DOWN,
              vineBlock
      );
      if(optional.isPresent()) {
         BlockState blockState2 = serverLevel.getBlockState(optional.get());
         ((WisteriaVine) blockState2.getBlock()).performBonemeal(serverLevel, randomSource, optional.get(), blockState2);
      }
      if(optional.isEmpty()) {
         serverLevel.setBlock(blockPos.below(), vineBlock.defaultBlockState(), 2);
      }
   }
}
