package net.hibiscus.naturespirit.world.feature;


import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusWoods;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.DeltaFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Iterator;

public class HibiscusDeltaFeature extends Feature <DeltaFeatureConfig> {
   private static final ImmutableList <Block> CANNOT_REPLACE;
   private static final ImmutableList <Block> CAN_REPLACE;
   private static final Direction[] DIRECTIONS;
   private static final double RIM_SPAWN_CHANCE = 0.9D;

   static {
      CANNOT_REPLACE = ImmutableList.of(Blocks.BEDROCK,
              Blocks.NETHER_BRICKS,
              Blocks.NETHER_BRICK_FENCE,
              Blocks.NETHER_BRICK_STAIRS,
              Blocks.NETHER_WART,
              Blocks.CHEST,
              Blocks.SPAWNER,
              HibiscusWoods.BLUE_WISTERIA_LEAVES,
              HibiscusWoods.WHITE_WISTERIA_LEAVES,
              HibiscusWoods.PINK_WISTERIA_LEAVES,
              HibiscusWoods.WILLOW_LEAVES
      );
      CAN_REPLACE = ImmutableList.of(Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.GRASS_BLOCK);
      DIRECTIONS = Direction.values();
   }

   public HibiscusDeltaFeature(Codec <DeltaFeatureConfig> codec) {
      super(codec);
   }

   private static boolean isClear(WorldAccess level, BlockPos pos, DeltaFeatureConfig config) {
      BlockState blockState = level.getBlockState(pos);
      if(blockState.isOf(config.getContents().getBlock())) {
         return false;
      }
      else if(CANNOT_REPLACE.contains(blockState.getBlock())) {
         return false;
      }
      else if(CAN_REPLACE.contains(blockState.getBlock()) && !blockState.isOf(config.getContents().getBlock())) {
         Direction[] var4 = DIRECTIONS;
         int var5 = var4.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            Direction direction = var4[var6];
            boolean bl = level.getBlockState(pos.offset(direction)).isAir();
            if(bl && direction != Direction.UP || !bl && direction == Direction.UP) {
               return false;
            }
         }

         return true;
      }
      else {
         return false;
      }
   }

   public boolean generate(FeatureContext <DeltaFeatureConfig> context) {
      boolean bl = false;
      Random randomSource = context.getRandom();
      StructureWorldAccess worldGenLevel = context.getWorld();
      DeltaFeatureConfig deltaFeatureConfiguration = context.getConfig();
      BlockPos blockPos = context.getOrigin();
      boolean bl2 = randomSource.nextDouble() < 0.9D;
      int i = bl2 ? deltaFeatureConfiguration.getRimSize().get(randomSource) : 0;
      int j = bl2 ? deltaFeatureConfiguration.getRimSize().get(randomSource) : 0;
      boolean bl3 = bl2 && i != 0 && j != 0;
      int k = deltaFeatureConfiguration.getSize().get(randomSource);
      int l = deltaFeatureConfiguration.getSize().get(randomSource);
      int m = Math.max(k, l);
      Iterator var14 = BlockPos.iterateOutwards(blockPos, k, 0, l).iterator();

      while(var14.hasNext()) {
         BlockPos blockPos2 = (BlockPos) var14.next();
         if(blockPos2.getManhattanDistance(blockPos) > m) {
            break;
         }

         if(isClear(worldGenLevel, blockPos2, deltaFeatureConfiguration)) {
            if(bl3) {
               bl = true;
               this.setBlockState(worldGenLevel, blockPos2, deltaFeatureConfiguration.getRim());
            }

            BlockPos blockPos3 = blockPos2.add(i, 0, j);
            if(isClear(worldGenLevel, blockPos3, deltaFeatureConfiguration)) {
               bl = true;
               this.setBlockState(worldGenLevel, blockPos3, deltaFeatureConfiguration.getContents());
            }
         }
      }

      return bl;
   }
}