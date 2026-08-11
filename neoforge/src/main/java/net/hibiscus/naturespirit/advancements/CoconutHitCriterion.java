package net.hibiscus.naturespirit.advancements;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.hibiscus.naturespirit.registration.NSCriteria;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.CriterionValidator;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.storage.loot.LootContext;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class CoconutHitCriterion extends SimpleCriterionTrigger<CoconutHitCriterion.Conditions> {

  public CoconutHitCriterion() {
  }

  @Override
  public Codec<CoconutHitCriterion.Conditions> codec() {
    return CoconutHitCriterion.Conditions.CODEC;
  }

  public void trigger(ServerPlayer player, Entity projectile) {
    LootContext lootContext = EntityPredicate.createContext(player, projectile);
    this.trigger(player, (conditions) -> conditions.test(lootContext));
  }

  public record Conditions(
          Optional<ContextAwarePredicate> player, Optional<ContextAwarePredicate> projectile
  ) implements SimpleCriterionTrigger.SimpleInstance {

    public static final Codec<CoconutHitCriterion.Conditions> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(
                    EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(Conditions::player),
                    EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("projectile").forGetter(Conditions::projectile)
            ).apply(instance, Conditions::new));

    public static Criterion<CoconutHitCriterion.Conditions> create(Optional<ContextAwarePredicate> projectile) {
      return NSCriteria.COCONUT_HIT_CRITERION.get().createCriterion(new CoconutHitCriterion.Conditions(Optional.empty(), projectile));
    }

    public boolean test(LootContext projectileContext) {
      return this.projectile.isEmpty() || this.projectile.get().matches(projectileContext);
    }

    @Override
    public void validate(@NotNull CriterionValidator validator) {
      validator.validateEntity(this.projectile, ".projectile");
    }

    @Override
    public Optional<ContextAwarePredicate> player() {
      return this.player;
    }

    public Optional<ContextAwarePredicate> projectile() {
      return this.projectile;
    }
  }
}
