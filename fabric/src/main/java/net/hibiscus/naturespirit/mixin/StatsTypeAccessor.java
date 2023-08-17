package net.hibiscus.naturespirit.mixin;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Stats.class) public interface StatsTypeAccessor {
   @Invoker("makeCustomStat") static ResourceLocation registerNew(String id, StatFormatter formatter) {
      throw new AssertionError();
   }
}
