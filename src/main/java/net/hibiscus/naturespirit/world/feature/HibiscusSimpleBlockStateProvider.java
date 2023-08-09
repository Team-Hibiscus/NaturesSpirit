package net.hibiscus.naturespirit.world.feature;

import com.mojang.serialization.Codec;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.util.HibiscusWorldGen;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.BlockStateProviderType;

public class HibiscusSimpleBlockStateProvider extends BlockStateProvider {
   public static final Codec <HibiscusSimpleBlockStateProvider> CODEC;

   static {
      CODEC = BlockState.CODEC.fieldOf("state").xmap(HibiscusSimpleBlockStateProvider::new,
              (hibiscusSimpleBlockStateProvider) -> {
                 return hibiscusSimpleBlockStateProvider.state;
              }
      ).codec();
   }

   private final BlockState state;

   public HibiscusSimpleBlockStateProvider(BlockState state) {
      this.state = state;
   }

   @Override protected BlockStateProviderType <?> getType() {
      return HibiscusWorldGen.HIBISCUS_SIMPLE_BLOCK_STATE_PROVIDER;
   }

   @Override public BlockState get(Random randomSource, BlockPos blockPos) {
      return this.state;
   }
}
