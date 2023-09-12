//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.hibiscus.naturespirit.world.feature.trunk;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.hibiscus.naturespirit.util.HibiscusWorldGen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Plane;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer.FoliageAttachment;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class MapleTrunkPlacer extends TrunkPlacer {
   private static final Codec <UniformInt> BRANCH_START_OFFSET_FROM_TOP_CODEC;
   public static final Codec <MapleTrunkPlacer> CODEC;
   private final IntProvider branchCount;
   private final IntProvider branchHorizontalLength;
   private final UniformInt branchStartOffsetFromTop;
   private final UniformInt secondBranchStartOffsetFromTop;
   private final IntProvider branchEndOffsetFromTop;

   public MapleTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight, IntProvider branchCount, IntProvider branchHorizontalLength, UniformInt branchStartOffsetFromTop, IntProvider branchEndOffsetFromTop) {
      super(baseHeight, firstRandomHeight, secondRandomHeight);
      this.branchCount = branchCount;
      this.branchHorizontalLength = branchHorizontalLength;
      this.branchStartOffsetFromTop = branchStartOffsetFromTop;
      this.secondBranchStartOffsetFromTop = UniformInt.of(branchStartOffsetFromTop.getMinValue(), branchStartOffsetFromTop.getMaxValue() - 1);
      this.branchEndOffsetFromTop = branchEndOffsetFromTop;
   }

   protected TrunkPlacerType <?> type() {
      return HibiscusWorldGen.MAPLE_TRUNK_PLACER;
   }

   public List <FoliageAttachment> placeTrunk(LevelSimulatedReader world, BiConsumer <BlockPos, BlockState> replacer, RandomSource random, int height, BlockPos startPos, TreeConfiguration config) {
      setDirtAt(world, replacer, random, startPos.below(), config);
      int i = Math.max(0, height - 1 + this.branchStartOffsetFromTop.sample(random));
      int j = Math.max(0, height - 1 + this.secondBranchStartOffsetFromTop.sample(random));
      if(j >= i) {
         ++j;
      }

      int k = this.branchCount.sample(random);
      boolean bl = k >= 3;
      boolean bl2 = k >= 2;
      boolean bl3 = k >= 4;
      boolean bl4 = k == 5;
      int l;
      if(bl) {
         l = height;
      }
      else if(bl2) {
         l = Math.max(i, j) + 1;
      }
      else {
         l = i + 1;
      }

      for(int m = 0; m < l; ++m) {
         this.placeLog(world, replacer, random, startPos.above(m), config);
      }

      List <FoliageAttachment> list = new ArrayList();
      if(bl) {
         list.add(new FoliageAttachment(startPos.above(l), 0, false));
      }

      MutableBlockPos mutable = new MutableBlockPos();
      Direction direction = Plane.HORIZONTAL.getRandomDirection(random);
      Direction direction2 = direction.getClockWise(Direction.Axis.Y);
      Function <BlockState, BlockState> function = (state) -> {
         return (BlockState) state.trySetValue(RotatedPillarBlock.AXIS, direction.getAxis());
      };
      Function <BlockState, BlockState> function2 = (state) -> {
         return (BlockState) state.trySetValue(RotatedPillarBlock.AXIS, direction2.getAxis());
      };
      list.add(this.generateBranch(world, replacer, random, height, startPos, config, function, direction, i, i < l - 1, mutable));
      if(bl2) {
         list.add(this.generateBranch(world, replacer, random, height, startPos, config, function, direction.getOpposite(), j, j < l - 1, mutable));
      }
      if(bl3) {
         list.add(this.generateBranch(world, replacer, random, height, startPos, config, function2, direction2, j, j < l - 1, mutable));
      }
      if(bl4) {
         list.add(this.generateBranch(world, replacer, random, height, startPos, config, function2, direction2.getOpposite(), j, j < l - 1, mutable));
      }

      return list;
   }

   private FoliageAttachment generateBranch(LevelSimulatedReader world, BiConsumer <BlockPos, BlockState> replacer, RandomSource random, int height, BlockPos startPos, TreeConfiguration config, Function <BlockState, BlockState> withAxisFunction, Direction direction, int branchStartOffset, boolean branchBelowHeight, MutableBlockPos mutablePos) {
      mutablePos.set(startPos).move(Direction.UP, branchStartOffset);
      int i = height - 1 + this.branchEndOffsetFromTop.sample(random);
      boolean bl = branchBelowHeight || i < branchStartOffset;
      int j = this.branchHorizontalLength.sample(random) + (bl ? 1 : 0);
      BlockPos blockPos = startPos.relative(direction, j).above(i);
      int k = bl ? 2 : 1;

      for(int l = 0; l < k; ++l) {
         this.placeLog(world, replacer, random, mutablePos.move(direction), config, withAxisFunction);
      }

      Direction direction2 = blockPos.getY() > mutablePos.getY() ? Direction.UP : Direction.DOWN;

      while(true) {
         int m = mutablePos.distManhattan(blockPos);
         if(m == 0) {
            return new FoliageAttachment(blockPos.above(), 0, false);
         }

         float f = (float) Math.abs(blockPos.getY() - mutablePos.getY()) / (float) m;
         boolean bl2 = random.nextFloat() < f;
         mutablePos.move(bl2 ? direction2 : direction);
         this.placeLog(world, replacer, random, mutablePos, config, bl2 ? Function.identity() : withAxisFunction);
      }
   }

   static {
      BRANCH_START_OFFSET_FROM_TOP_CODEC = ExtraCodecs.validate(UniformInt.CODEC, (branchStartOffsetFromTop) -> {
         return branchStartOffsetFromTop.getMaxValue() - branchStartOffsetFromTop.getMinValue() < 1 ? DataResult.error(() -> {
            return "Need at least 2 blocks variation for the branch starts to fit both branches";
         }) : DataResult.success(branchStartOffsetFromTop);
      });
      CODEC = RecordCodecBuilder.create((instance) -> {
         return trunkPlacerParts(instance).and(instance.group(IntProvider.codec(1, 5).fieldOf("branch_count").forGetter((trunkPlacer) -> {
            return trunkPlacer.branchCount;
         }), IntProvider.codec(1, 16).fieldOf("branch_horizontal_length").forGetter((trunkPlacer) -> {
            return trunkPlacer.branchHorizontalLength;
         }), IntProvider.codec(-16, 0, BRANCH_START_OFFSET_FROM_TOP_CODEC).fieldOf("branch_start_offset_from_top").forGetter((trunkPlacer) -> {
            return trunkPlacer.branchStartOffsetFromTop;
         }), IntProvider.codec(-16, 16).fieldOf("branch_end_offset_from_top").forGetter((trunkPlacer) -> {
            return trunkPlacer.branchEndOffsetFromTop;
         }))).apply(instance, MapleTrunkPlacer::new);
      });
   }
}
