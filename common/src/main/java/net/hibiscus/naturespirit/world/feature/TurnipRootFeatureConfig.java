package net.hibiscus.naturespirit.world.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class TurnipRootFeatureConfig implements FeatureConfiguration {
   public static final Codec <TurnipRootFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> {
      return instance.group(PlacedFeature.CODEC.fieldOf("feature").forGetter((config) -> {
         return config.feature;
      }), Codec.intRange(1, 64).fieldOf("required_vertical_space_for_tree").forGetter((config) -> {
         return config.requiredVerticalSpaceForTree;
      }), Codec.intRange(1, 64).fieldOf("root_radius").forGetter((config) -> {
         return config.rootRadius;
      }), TagKey.hashedCodec(Registries.BLOCK).fieldOf("root_replaceable").forGetter((config) -> {
         return config.rootReplaceable;
      }), BlockStateProvider.CODEC.fieldOf("root_state_provider").forGetter((config) -> {
         return config.rootStateProvider;
      }), BlockStateProvider.CODEC.fieldOf("turnip_state_provider").forGetter((config) -> {
         return config.turnipStateProvider;
      }), Codec.intRange(1, 256).fieldOf("root_placement_attempts").forGetter((config) -> {
         return config.rootPlacementAttempts;
      }), Codec.intRange(1, 4096).fieldOf("root_column_max_height").forGetter((config) -> {
         return config.maxRootColumnHeight;
      }), Codec.intRange(1, 64).fieldOf("hanging_root_radius").forGetter((config) -> {
         return config.hangingRootRadius;
      }), Codec.intRange(0, 16).fieldOf("hanging_roots_vertical_span").forGetter((config) -> {
         return config.hangingRootVerticalSpan;
      }), BlockStateProvider.CODEC.fieldOf("hanging_root_state_provider").forGetter((config) -> {
         return config.hangingRootStateProvider;
      }), Codec.intRange(1, 256).fieldOf("hanging_root_placement_attempts").forGetter((config) -> {
         return config.hangingRootPlacementAttempts;
      }), Codec.intRange(1, 256).fieldOf("turnip_placement_attempts").forGetter((config) -> {
         return config.turnipPlacementAttempts;
      }), Codec.intRange(1, 64).fieldOf("allowed_vertical_water_for_tree").forGetter((config) -> {
         return config.allowedVerticalWaterForTree;
      }), BlockPredicate.CODEC.fieldOf("allowed_tree_position").forGetter((config) -> {
         return config.predicate;
      })).apply(instance, TurnipRootFeatureConfig::new);
   });
   public final Holder <PlacedFeature> feature;
   public final int requiredVerticalSpaceForTree;
   public final int rootRadius;
   public final TagKey <Block> rootReplaceable;
   public final BlockStateProvider rootStateProvider;
   public final BlockStateProvider turnipStateProvider;
   public final int rootPlacementAttempts;
   public final int maxRootColumnHeight;
   public final int hangingRootRadius;
   public final int hangingRootVerticalSpan;
   public final BlockStateProvider hangingRootStateProvider;
   public final int hangingRootPlacementAttempts;
   public final int turnipPlacementAttempts;
   public final int allowedVerticalWaterForTree;
   public final BlockPredicate predicate;

   public TurnipRootFeatureConfig(Holder <PlacedFeature> feature, int requiredVerticalSpaceForTree, int rootRadius, TagKey <Block> rootReplaceable, BlockStateProvider rootStateProvider, BlockStateProvider turnipStateProvider, int rootPlacementAttempts, int maxRootColumnHeight, int hangingRootRadius, int hangingRootVerticalSpan, BlockStateProvider hangingRootStateProvider, int hangingRootPlacementAttempts, int turnipPlacementAttempts, int allowedVerticalWaterForTree, BlockPredicate predicate) {
      this.feature = feature;
      this.requiredVerticalSpaceForTree = requiredVerticalSpaceForTree;
      this.rootRadius = rootRadius;
      this.rootReplaceable = rootReplaceable;
      this.rootStateProvider = rootStateProvider;
      this.turnipStateProvider = turnipStateProvider;
      this.rootPlacementAttempts = rootPlacementAttempts;
      this.maxRootColumnHeight = maxRootColumnHeight;
      this.hangingRootRadius = hangingRootRadius;
      this.hangingRootVerticalSpan = hangingRootVerticalSpan;
      this.hangingRootStateProvider = hangingRootStateProvider;
      this.hangingRootPlacementAttempts = hangingRootPlacementAttempts;
      this.turnipPlacementAttempts = turnipPlacementAttempts;
      this.allowedVerticalWaterForTree = allowedVerticalWaterForTree;
      this.predicate = predicate;
   }
}
