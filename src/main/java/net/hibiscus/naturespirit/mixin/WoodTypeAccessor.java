package net.hibiscus.naturespirit.mixin;

import net.minecraft.block.WoodType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(WoodType.class) public interface WoodTypeAccessor {
   @Invoker("register") static WoodType registerNew(WoodType type) {
      throw new AssertionError();
   }
}
