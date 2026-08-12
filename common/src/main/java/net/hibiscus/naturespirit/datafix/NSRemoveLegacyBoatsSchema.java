package net.hibiscus.naturespirit.datafix;

import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.templates.TypeTemplate;
import java.util.Map;
import java.util.function.Supplier;
import net.minecraft.util.datafix.schemas.NamespacedSchema;

public class NSRemoveLegacyBoatsSchema extends NamespacedSchema {

  public NSRemoveLegacyBoatsSchema(int versionKey, Schema parent) {
    super(versionKey, parent);
  }

  @Override
  public Map<String, Supplier<TypeTemplate>> registerEntities(Schema schema) {
    Map<String, Supplier<TypeTemplate>> map = super.registerEntities(schema);
    map.remove(NSBoatsSchema.LEGACY_BOAT);
    map.remove(NSBoatsSchema.LEGACY_CHEST_BOAT);
    return map;
  }
}
