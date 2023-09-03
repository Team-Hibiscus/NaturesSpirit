//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.hibiscus.naturespirit.world.feature;

import com.mojang.serialization.Codec;
import java.util.function.Predicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class TurnipRootFeature extends Feature <TurnipRootFeatureConfig> {
   public TurnipRootFeature(Codec <TurnipRootFeatureConfig> codec) {
      super(codec);
   }

   private static boolean hasSpaceForTree(WorldGenLevel world, TurnipRootFeatureConfig config, BlockPos pos) {
      MutableBlockPos mutable = pos.mutable();

      for(int i = 1; i <= config.requiredVerticalSpaceForTree; ++i) {
         mutable.move(Direction.UP);
         BlockState blockState = world.getBlockState(mutable);
         if(!isAirOrWater(blockState, i, config.allowedVerticalWaterForTree)) {
            return false;
         }
      }

      return true;
   }

   private static boolean isAirOrWater(BlockState state, int height, int allowedVerticalWaterForTree) {
      if(state.isAir()) {
         return true;
      }
      else {
         int i = height + 1;
         return i <= allowedVerticalWaterForTree && state.getFluidState().is(FluidTags.WATER);
      }
   }

   private static boolean generateTreeAndRoots(WorldGenLevel world, ChunkGenerator generator, TurnipRootFeatureConfig config, RandomSource random, MutableBlockPos mutablePos, BlockPos pos) {
      for(int i = 0; i < config.maxRootColumnHeight; ++i) {
         mutablePos.move(Direction.UP);
         if(config.predicate.test(world, mutablePos) && hasSpaceForTree(world, config, mutablePos)) {
            BlockPos blockPos = mutablePos.below();
            if(world.getFluidState(blockPos).is(FluidTags.LAVA) || !world.getBlockState(blockPos).isSolid()) {
               return false;
            }

            if(config.feature.value().place(world, generator, random, mutablePos)) {
               generateRootsColumn(pos, pos.getY() + i, world, config, random);
               return true;
            }
         }
      }

      return false;
   }

   private static void generateRootsColumn(BlockPos pos, int maxY, WorldGenLevel world, TurnipRootFeatureConfig config, RandomSource random) {
      int i = pos.getX();
      int j = pos.getZ();
      MutableBlockPos mutable = pos.mutable();

      for(int k = pos.getY(); k < maxY; ++k) {
         generateRoots(world, config, random, i, j, mutable.set(i, k, j));
      }

   }

   private static void generateRoots(WorldGenLevel world, TurnipRootFeatureConfig config, RandomSource random, int x, int z, MutableBlockPos mutablePos) {
      int i = config.rootRadius;
      Predicate <BlockState> predicate = (state) -> {
         return state.is(config.rootReplaceable);
      };

      for(int j = 0; j < config.rootPlacementAttempts; ++j) {
         mutablePos.setWithOffset(mutablePos, random.nextInt(i) - random.nextInt(i), 0, random.nextInt(i) - random.nextInt(i));
         if(predicate.test(world.getBlockState(mutablePos))) {
            world.setBlock(mutablePos, config.rootStateProvider.getState(random, mutablePos), 2);
         }

         mutablePos.setX(x);
         mutablePos.setZ(z);
      }

   }

   private static void generateHangingRoots(WorldGenLevel world, TurnipRootFeatureConfig config, RandomSource random, BlockPos pos, MutableBlockPos mutablePos) {
      int i = config.hangingRootRadius;
      int j = config.hangingRootVerticalSpan;

      for(int k = 0; k < config.hangingRootPlacementAttempts; ++k) {
         mutablePos.setWithOffset(pos,
                 random.nextInt(i) - random.nextInt(i),
                 random.nextInt(j) - random.nextInt(j),
                 random.nextInt(i) - random.nextInt(i)
         );
         if(world.isEmptyBlock(mutablePos)) {
            BlockState blockState = config.hangingRootStateProvider.getState(random, mutablePos);
            if(blockState.canSurvive(world, mutablePos) && world.getBlockState(mutablePos.above()).isFaceSturdy(world,
                    mutablePos,
                    Direction.DOWN
            )) {
               world.setBlock(mutablePos, blockState, 2);
            }
         }
      }

   }

   private static void generateTurnips(WorldGenLevel world, TurnipRootFeatureConfig config, RandomSource random, BlockPos pos, MutableBlockPos mutablePos) {
      int i = config.hangingRootRadius;
      int j = config.hangingRootVerticalSpan;

      for(int k = 0; k < config.turnipPlacementAttempts; ++k) {
         mutablePos.setWithOffset(pos,
                 random.nextInt(i) - random.nextInt(i),
                 random.nextInt(j) - random.nextInt(j),
                 random.nextInt(i) - random.nextInt(i)
         );
         if(world.getBlockState(mutablePos.relative(Direction.UP, 1)) == config.rootStateProvider.getState(random,
                 mutablePos
         )) {
            BlockState blockState = config.turnipStateProvider.getState(random, mutablePos);
            if(blockState.canSurvive(world, mutablePos) && world.getBlockState(mutablePos.above()).isFaceSturdy(world,
                    mutablePos,
                    Direction.DOWN
            )) {
               world.setBlock(mutablePos, blockState, 2);
            }
         }
      }

   }

   public boolean place(FeaturePlaceContext <TurnipRootFeatureConfig> context) {
      WorldGenLevel structureWorldAccess = context.level();
      RandomSource random = context.random();
      BlockPos blockPos = context.origin();
      TurnipRootFeatureConfig turnipRootFeatureConfig = context.config();
      MutableBlockPos mutable = blockPos.mutable();
      if(generateTreeAndRoots(structureWorldAccess,
              context.chunkGenerator(),
              turnipRootFeatureConfig,
              random,
              mutable,
              blockPos
      )) {
         generateHangingRoots(structureWorldAccess, turnipRootFeatureConfig, random, blockPos, mutable);
         generateTurnips(structureWorldAccess, turnipRootFeatureConfig, random, blockPos, mutable);
      }

      return true;
   }
}
