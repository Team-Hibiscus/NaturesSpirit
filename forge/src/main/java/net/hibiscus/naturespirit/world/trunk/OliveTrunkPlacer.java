//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.hibiscus.naturespirit.world.trunk;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.hibiscus.naturespirit.util.HibiscusForgeWorldGen;
import net.hibiscus.naturespirit.util.HibiscusWorldGen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Plane;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer.FoliageAttachment;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class OliveTrunkPlacer extends TrunkPlacer {
   public static final Codec <OliveTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) -> {
      return trunkPlacerParts(instance).and(instance.group(IntProvider.POSITIVE_CODEC.fieldOf("extra_branch_steps")
              .forGetter((trunkPlacer) -> {
                 return trunkPlacer.extraBranchSteps;
              }), Codec.floatRange(0.0F, 1.0F).fieldOf("place_branch_per_log_probability").forGetter((trunkPlacer) -> {
         return trunkPlacer.placeBranchPerLogProbability;
      }), IntProvider.NON_NEGATIVE_CODEC.fieldOf("extra_branch_length").forGetter((trunkPlacer) -> {
         return trunkPlacer.extraBranchLength;
      }), RegistryCodecs.homogeneousList(Registries.BLOCK).fieldOf("can_grow_through").forGetter((trunkPlacer) -> {
         return trunkPlacer.canGrowThrough;
      }))).apply(instance, OliveTrunkPlacer::new);
   });
   private final IntProvider extraBranchSteps;
   private final float placeBranchPerLogProbability;
   private final IntProvider extraBranchLength;
   private final HolderSet <Block> canGrowThrough;

   public OliveTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight, IntProvider extraBranchSteps, float placeBranchPerLogProbability, IntProvider extraBranchLength, HolderSet <Block> canGrowThrough) {
      super(baseHeight, firstRandomHeight, secondRandomHeight);
      this.extraBranchSteps = extraBranchSteps;
      this.placeBranchPerLogProbability = placeBranchPerLogProbability;
      this.extraBranchLength = extraBranchLength;
      this.canGrowThrough = canGrowThrough;
   }

   protected TrunkPlacerType <?> type() {
      return HibiscusForgeWorldGen.OLIVE_TRUNK_PLACER.get();
   }

   public List <FoliageAttachment> placeTrunk(LevelSimulatedReader world, BiConsumer <BlockPos, BlockState> replacer, RandomSource random, int height, BlockPos startPos, TreeConfiguration config) {
      List <FoliageAttachment> list = Lists.newArrayList();
      MutableBlockPos mutable = new MutableBlockPos();
      MutableBlockPos mutable2 = new MutableBlockPos();
      boolean bl = random.nextBoolean();
      boolean bl2 = random.nextBoolean();
      boolean bl3 = random.nextBoolean();
      Direction trunkOffset = Plane.HORIZONTAL.getRandomDirection(random);
      Direction trunkOffset2 = Plane.HORIZONTAL.getRandomDirection(random);
      int randomInt = random.nextIntBetweenInclusive(0, 1);
      int randomInt2 = random.nextIntBetweenInclusive(-1, 1);

      for(int i = 0; i < height; ++i) {
         int j = startPos.getY() + i;
         Direction direction = Plane.HORIZONTAL.getRandomDirection(random);
         Direction direction2 = Plane.HORIZONTAL.getRandomDirection(random);
         Direction direction3 = Plane.HORIZONTAL.getRandomDirection(random);

         MutableBlockPos mutablePos1 = mutable.set(startPos.getX(), j, startPos.getZ());
         BlockPos pos2 = mutablePos1.relative(trunkOffset);
         BlockPos pos3 = pos2.relative(trunkOffset2);
         BlockPos blockPos = bl && i >= 2 ? (bl2 && trunkOffset != trunkOffset2 ? pos3 : pos2) : mutablePos1;

         if(bl && i == 2 && !bl3) {
            this.placeLog(world, replacer, random, mutablePos1, config);
         }
         if(this.placeLog(world,
                 replacer,
                 random,
                 blockPos,
                 config
         ) && i < height - 1 && random.nextFloat() < this.placeBranchPerLogProbability) {
            int k = this.extraBranchLength.sample(random);
            int l = Math.max(0, k - this.extraBranchLength.sample(random) - 1);
            int m = height + i - 7;
            if(i > 2) {
               this.generateExtraBranch(world, replacer, random, height, config, list, blockPos, j, direction, l, m, i);

               if(random.nextFloat() < this.placeBranchPerLogProbability - 0.65) {
                  this.generateExtraBranch(world,
                          replacer,
                          random,
                          height,
                          config,
                          list,
                          blockPos,
                          j,
                          direction2,
                          l,
                          Math.round(m / 2),
                          i
                  );
               }
               if(random.nextFloat() < this.placeBranchPerLogProbability - 0.85) {
                  this.generateExtraBranch(world,
                          replacer,
                          random,
                          height,
                          config,
                          list,
                          blockPos,
                          j,
                          direction3,
                          l,
                          Math.round(m / 2),
                          i
                  );
               }
            }
         }
         if(i == height - 1) {
            list.add(new FoliageAttachment(blockPos.above(1), 0, false));
         }
      }
      if(bl3 && bl) {
         for(int i = 0; i < height - randomInt; ++i) {
            int j = startPos.getY() + i;
            Direction direction = Plane.HORIZONTAL.getRandomDirection(random);
            Direction direction2 = Plane.HORIZONTAL.getRandomDirection(random);
            Direction direction3 = Plane.HORIZONTAL.getRandomDirection(random);

            MutableBlockPos mutablePos1 = mutable2.set(startPos.getX(), j, startPos.getZ());
            BlockPos pos2 = mutablePos1.relative(trunkOffset.getOpposite())
                    .relative(randomInt2 < 0 ? Direction.UP : Direction.DOWN, randomInt2);
            BlockPos pos3 = pos2.relative(trunkOffset2.getOpposite());
            BlockPos blockPos = i >= 2 ? bl2 && trunkOffset.getOpposite() != trunkOffset2.getOpposite() ? pos3 : pos2 : mutablePos1.relative(randomInt2 < 0 ? Direction.UP : Direction.DOWN,
                    randomInt2
            );

            if(i == 2) {
               this.placeLog(world,
                       replacer,
                       random,
                       mutablePos1.relative(randomInt2 < 0 ? Direction.UP : Direction.DOWN, randomInt2),
                       config
               );
            }
            if(this.placeLog(world,
                    replacer,
                    random,
                    blockPos,
                    config
            ) && i < height - 1 && random.nextFloat() < this.placeBranchPerLogProbability) {
               int k = this.extraBranchLength.sample(random);
               int l = Math.max(0, k - this.extraBranchLength.sample(random) - 1);
               int m = height + i - 7;
               if(i > 2) {
                  this.generateExtraBranch(world,
                          replacer,
                          random,
                          height,
                          config,
                          list,
                          blockPos,
                          j,
                          direction,
                          l,
                          m,
                          i
                  );

                  if(random.nextFloat() < this.placeBranchPerLogProbability - 0.65) {
                     this.generateExtraBranch(world,
                             replacer,
                             random,
                             height,
                             config,
                             list,
                             blockPos,
                             j,
                             direction2,
                             l,
                             Math.round(m / 2),
                             i
                     );
                  }
                  if(random.nextFloat() < this.placeBranchPerLogProbability - 0.85) {
                     this.generateExtraBranch(world,
                             replacer,
                             random,
                             height,
                             config,
                             list,
                             blockPos,
                             j,
                             direction3,
                             l,
                             Math.round(m / 2),
                             i
                     );
                  }
               }
            }
            if(i == ((height - 1) - randomInt2) - randomInt) {
               list.add(new FoliageAttachment(blockPos.above(1), 0, false));
            }
         }
      }

      return list;
   }

   private void generateExtraBranch(LevelSimulatedReader world, BiConsumer <BlockPos, BlockState> replacer, RandomSource random, int height, TreeConfiguration config, List <FoliageAttachment> nodes, BlockPos pos, int yOffset, Direction direction, int length, int steps, int b) {
      int i = yOffset + length;
      int j = pos.getX();
      int k = pos.getZ();
      MutableBlockPos mutable = new MutableBlockPos();
      boolean bl = random.nextFloat() < .5F;
      Direction direction2 = random.nextFloat() < .5F ? direction.getClockWise() : direction.getCounterClockWise();

      for(int l = length; l < height && steps > 0 && (steps < (bl ? 7 : 10)); --steps) {
         if(l >= 1) {
            int m = Math.round(yOffset + l / 5);
            j += direction.getStepX();
            if(bl) {
               k += Math.max(0, direction2.getStepZ() - random.nextInt(2));
            }
            else {
               k += direction.getStepZ();
            }
            i = m;
            if(this.placeLog(world, replacer, random, mutable.set(j, m, k), config, blockState -> {
               return blockState.trySetValue(RotatedPillarBlock.AXIS, direction.getAxis());
            })) {
               i = m + 1;
            }
         }
         ++l;
      }

      if(i - yOffset >= 1) {
         BlockPos blockPos = new BlockPos(j, i, k);
         nodes.add(new FoliageAttachment(blockPos, 0, false));
      }

   }

   protected boolean validTreePos(LevelSimulatedReader world, BlockPos pos) {
      return super.validTreePos(world, pos) || world.isStateAtPosition(pos, (state) -> {
         return state.is(this.canGrowThrough);
      });
   }
}
