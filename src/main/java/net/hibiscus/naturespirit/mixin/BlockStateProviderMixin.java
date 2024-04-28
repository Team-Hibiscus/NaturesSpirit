package net.hibiscus.naturespirit.mixin;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.hibiscus.naturespirit.world.feature.HibiscusSimpleBlockStateProvider;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.BlockStateProviderType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(BlockStateProviderType.class) public interface BlockStateProviderMixin {
   @Invoker static <P extends BlockStateProvider> BlockStateProviderType <P> callRegister(String id, MapCodec <HibiscusSimpleBlockStateProvider> codec) {
      throw new IllegalStateException();
   }
}
