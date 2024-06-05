package net.hibiscus.naturespirit.advancements;

import com.google.gson.JsonObject;
import net.hibiscus.naturespirit.NatureSpirit;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.advancement.criterion.AbstractCriterionConditions;
import net.minecraft.entity.Entity;
import net.minecraft.loot.context.LootContext;
import net.minecraft.predicate.entity.AdvancementEntityPredicateDeserializer;
import net.minecraft.predicate.entity.AdvancementEntityPredicateSerializer;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

public class CoconutHitCriterion extends AbstractCriterion <CoconutHitCriterion.Conditions> {
   static final Identifier ID = new Identifier(NatureSpirit.MOD_ID, "coconut_hit");

   public CoconutHitCriterion() {
   }

   public Identifier getId() {
      return ID;
   }

   public CoconutHitCriterion.Conditions conditionsFromJson(JsonObject jsonObject, LootContextPredicate lootContextPredicate, AdvancementEntityPredicateDeserializer advancementEntityPredicateDeserializer) {
      LootContextPredicate lootContextPredicate2 = EntityPredicate.contextPredicateFromJson(jsonObject, "projectile", advancementEntityPredicateDeserializer);
      return new CoconutHitCriterion.Conditions(lootContextPredicate, lootContextPredicate2);
   }

   public void trigger(ServerPlayerEntity player, Entity projectile) {
      LootContext lootContext = EntityPredicate.createAdvancementEntityLootContext(player, projectile);
      this.trigger(player, (conditions) -> {
         return conditions.test(lootContext);
      });
   }

   public static class Conditions extends AbstractCriterionConditions {
      private final LootContextPredicate projectile;

      public Conditions(LootContextPredicate player, LootContextPredicate projectile) {
         super(CoconutHitCriterion.ID, player);
         this.projectile = projectile;
      }

      public static CoconutHitCriterion.Conditions create(LootContextPredicate projectile) {
         return new CoconutHitCriterion.Conditions(LootContextPredicate.EMPTY, projectile);
      }

      public JsonObject toJson(AdvancementEntityPredicateSerializer predicateSerializer) {
         JsonObject jsonObject = super.toJson(predicateSerializer);
         jsonObject.add("projectile", this.projectile.toJson(predicateSerializer));
         return jsonObject;
      }

      public boolean test(LootContext projectileContext) {
            return this.projectile.test(projectileContext);
      }
   }
}
