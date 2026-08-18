package net.hibiscus.naturespirit.datafix;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.templates.TypeTemplate;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.util.datafix.schemas.NamespacedSchema;

public class NSBoatsSchema extends NamespacedSchema {

  public static final List<String> WOODS = List.of("redwood", "sugi", "wisteria", "fir", "willow", "aspen", "maple", "cypress", "olive", "joshua",
          "ghaf", "palo_verde", "coconut", "cedar", "larch", "mahogany", "saxaul");
  public static final String LEGACY_BOAT = "natures_spirit:ns_boat";
  public static final String LEGACY_CHEST_BOAT = "natures_spirit:ns_chest_boat";

  public NSBoatsSchema(int versionKey, Schema parent) {
    super(versionKey, parent);
  }

  @Override
  public Map<String, Supplier<TypeTemplate>> registerEntities(Schema schema) {
    Map<String, Supplier<TypeTemplate>> map = super.registerEntities(schema);
    registerSimple(map, LEGACY_BOAT);
    registerChest(map, LEGACY_CHEST_BOAT);
    for (String wood : WOODS) {
      registerSimple(map, "natures_spirit:" + wood + "_boat");
      registerChest(map, "natures_spirit:" + wood + "_chest_boat");
    }
    return map;
  }

  private void registerChest(Map<String, Supplier<TypeTemplate>> map, String id) {
    register(map, id, name -> DSL.optionalFields("Items", DSL.list(References.ITEM_STACK.in(this))));
  }
}
