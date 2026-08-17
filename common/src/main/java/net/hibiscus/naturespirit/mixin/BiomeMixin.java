package net.hibiscus.naturespirit.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.hibiscus.naturespirit.registration.NSTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = Biome.class, remap = false)
public class BiomeMixin {

  @WrapOperation(method = "shouldFreeze(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;Z)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/biome/Biome;warmEnoughToRain(Lnet/minecraft/core/BlockPos;I)Z"))
  private boolean forceIceInTaggedBiomes(Biome instance, BlockPos pos, int seaLevel, Operation<Boolean> original, LevelReader level) {
    if (level.getBiome(pos).is(NSTags.Biomes.FORCE_ICE)) {
      return false;
    }
    return original.call(instance, pos, seaLevel);
  }
}
