package net.hibiscus.naturespirit.mixin;

import com.mojang.datafixers.DataFixerBuilder;
import com.mojang.datafixers.schemas.Schema;
import net.hibiscus.naturespirit.datafix.NSBoatRenameFix;
import net.hibiscus.naturespirit.datafix.NSBoatsSchema;
import net.hibiscus.naturespirit.datafix.NSRemoveLegacyBoatsSchema;
import net.minecraft.util.datafix.DataFixers;
import net.minecraft.util.datafix.fixes.AddNewChoices;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.util.filefix.FileFixerUpper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = DataFixers.class, remap = false)
public abstract class DataFixersMixin {

  @Inject(method = "addFixers", at = @At(value = "CONSTANT", args = "intValue=3564", ordinal = 0))
  private static void addBoatChoices(DataFixerBuilder fixerUpper, FileFixerUpper.Builder fileFixerUpper, CallbackInfo ci) {
    Schema schema = fixerUpper.addSchema(3460, NSBoatsSchema::new);
    fixerUpper.addFixer(new AddNewChoices(schema, "Added Nature's Spirit boats", References.ENTITY));
  }

  @Inject(method = "addFixers", at = @At(value = "CONSTANT", args = "intValue=4067", ordinal = 0))
  private static void renameLegacyBoats(DataFixerBuilder fixerUpper, FileFixerUpper.Builder fileFixerUpper, CallbackInfo ci) {
    fixerUpper.addFixer(new NSBoatRenameFix(fixerUpper.addSchema(4066, 1, NSRemoveLegacyBoatsSchema::new)));
  }
}
