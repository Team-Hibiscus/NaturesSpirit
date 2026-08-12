package net.hibiscus.naturespirit.datafix;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.serialization.Dynamic;
import java.util.Optional;
import net.minecraft.util.datafix.ExtraDataFixUtils;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.util.datafix.schemas.NamespacedSchema;

public class NSBoatRenameFix extends DataFix {

  private static final String FABRIC_TYPE_PREFIX = "natures_spirit_";

  public NSBoatRenameFix(Schema outputSchema) {
    super(outputSchema, true);
  }

  @Override
  public TypeRewriteRule makeRule() {
    OpticFinder<String> idFinder = DSL.fieldFinder("id", NamespacedSchema.namespacedString());
    Type<?> oldType = getInputSchema().getType(References.ENTITY);
    Type<?> newType = getOutputSchema().getType(References.ENTITY);
    return fixTypeEverywhereTyped("NSBoatRenameFix", oldType, newType, input -> {
      String id = input.getOptional(idFinder).orElse("");
      boolean legacy = id.equals(NSBoatsSchema.LEGACY_BOAT) || id.equals(NSBoatsSchema.LEGACY_CHEST_BOAT);
      boolean vanillaId = id.equals("minecraft:boat") || id.equals("minecraft:chest_boat");
      if (!legacy && !vanillaId) {
        return ExtraDataFixUtils.cast(newType, input);
      }
      Dynamic<?> tag = input.getOrCreate(DSL.remainderFinder());
      Optional<String> type = tag.get("Type").asString().result();
      String wood;
      if (legacy) {
        wood = type.filter(NSBoatsSchema.WOODS::contains).orElse("redwood");
      } else {
        if (type.isEmpty() || !type.get().startsWith(FABRIC_TYPE_PREFIX)) {
          return ExtraDataFixUtils.cast(newType, input);
        }
        String stripped = type.get().substring(FABRIC_TYPE_PREFIX.length());
        wood = NSBoatsSchema.WOODS.contains(stripped) ? stripped : "redwood";
      }
      String newId = "natures_spirit:" + wood + (id.endsWith("chest_boat") ? "_chest_boat" : "_boat");
      return ExtraDataFixUtils.cast(newType, input)
          .update(DSL.remainderFinder(), remainder -> remainder.remove("Type"))
          .set(idFinder, newId);
    });
  }
}
