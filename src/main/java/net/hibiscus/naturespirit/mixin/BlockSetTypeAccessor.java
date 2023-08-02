package net.hibiscus.naturespirit.mixin;

import net.minecraft.block.BlockSetType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(BlockSetType.class) public interface BlockSetTypeAccessor {
   @Invoker("register") static BlockSetType registerNew(BlockSetType type) {
      throw new AssertionError();
   }
}
