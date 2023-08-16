package net.hibiscus.naturespirit.world.feature.trunk;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.hibiscus.naturespirit.util.HibiscusWorldGen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class WisteriaTrunkPlacer extends TrunkPlacer {
   public static final Codec <WisteriaTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) -> {
      return trunkPlacerParts(instance).and(instance.group(IntProvider.POSITIVE_CODEC.fieldOf("extra_branch_steps")
                      .forGetter((WisteriaTrunkPlacer) -> {
                         return WisteriaTrunkPlacer.extraBranchSteps;
                      }),
              Codec.floatRange(0.0F, 1.0F)
                      .fieldOf("place_branch_per_log_probability")
                      .forGetter((WisteriaTrunkPlacer) -> {
                         return WisteriaTrunkPlacer.placeBranchPerLogProbability;
                      }),
              IntProvider.NON_NEGATIVE_CODEC.fieldOf("extra_branch_length").forGetter((WisteriaTrunkPlacer) -> {
                 return WisteriaTrunkPlacer.extraBranchLength;
              }),
              RegistryCodecs.homogeneousList(Registries.BLOCK)
                      .fieldOf("can_grow_through")
                      .forGetter((WisteriaTrunkPlacer) -> {
                         return WisteriaTrunkPlacer.canGrowThrough;
                      })
      )).apply(instance, WisteriaTrunkPlacer::new);
   });
   private final IntProvider extraBranchSteps;
   private final float placeBranchPerLogProbability;
   private final IntProvider extraBranchLength;
   private final HolderSet <Block> canGrowThrough;

   public WisteriaTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight, IntProvider extraBranchSteps, float placeBranchPerLogProbability, IntProvider extraBranchLength, HolderSet <Block> canGrowThrough) {
      super(baseHeight, firstRandomHeight, secondRandomHeight);
      this.extraBranchSteps = extraBranchSteps;
      this.placeBranchPerLogProbability = placeBranchPerLogProbability;
      this.extraBranchLength = extraBranchLength;
      this.canGrowThrough = canGrowThrough;
   }

   protected TrunkPlacerType <?> type() {
      return HibiscusWorldGen.WISTERIA_TRUNK_PLACER;
   }

   public List <FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader world, BiConsumer <BlockPos, BlockState> replacer, RandomSource random, int height, BlockPos startPos, TreeConfiguration config) {
      List <FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();
      BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

      for(int i = 0; i < height; ++i) {
         int j = startPos.getY() + i;
         if(this.placeLog(world,
                 replacer,
                 random,
                 mutable.set(startPos.getX(), j, startPos.getZ()),
                 config
         ) && i < height - 3 && i > 2 && random.nextFloat() < this.placeBranchPerLogProbability) {
            Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
            int k = this.extraBranchLength.sample(random);
            int l = Math.max(0, k - this.extraBranchLength.sample(random) - 1);
            int m = this.extraBranchSteps.sample(random);
            int g = startPos.getY() + i - 1;
            this.placeBranch(world, replacer, random, height, config, list, mutable, j, direction, l, m);
         }
         if(i + 1 == height) {
            list.add(new FoliagePlacer.FoliageAttachment(mutable.set(startPos.getX(), j, startPos.getZ()), -1, false));
         }
      }

      return list;
   }

   private void placeBranch(LevelSimulatedReader world, BiConsumer <BlockPos, BlockState> replacer, RandomSource random, int height, TreeConfiguration config, List <FoliagePlacer.FoliageAttachment> nodes, BlockPos.MutableBlockPos pos, int yOffset, Direction direction, int length, int steps) {
      int i = yOffset + length;
      int j = pos.getX();
      int k = pos.getZ();

      for(int l = length; l < height && steps > 0; --steps) {
         if(l >= 1) {
            int m = yOffset + l;
            j += direction.getStepX();
            k += direction.getStepZ();
            i = m;
            if(this.placeLog(world, replacer, random, pos.set(j, m, k), config)) {
               i = m + 1;
            }
         }

         ++l;
      }

      if(i - yOffset > 1) {
         BlockPos blockPos = new BlockPos(j, i, k);
         nodes.add(new FoliagePlacer.FoliageAttachment(blockPos, 0, false));
      }

   }

   protected boolean validTreePos(LevelSimulatedReader levelSimulatedReader, BlockPos blockPos) {
      return super.validTreePos(levelSimulatedReader, blockPos) || levelSimulatedReader.isStateAtPosition(blockPos,
              (blockState) -> {
                 return blockState.is(this.canGrowThrough);
              }
      );
   }
}
