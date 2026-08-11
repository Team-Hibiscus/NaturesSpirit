package net.hibiscus.naturespirit.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.Level;

public class CoconutHalfItem extends Item {

  private final Item LeftOverItem;

  public CoconutHalfItem(Properties settings, Item item) {
    super(settings);
    LeftOverItem = item;
  }

  @Override
  public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
    super.finishUsingItem(stack, world, user);
    if (user instanceof ServerPlayer serverPlayerEntity) {
      CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
      serverPlayerEntity.awardStat(Stats.ITEM_USED.get(this));
    }

    if (!world.isClientSide) {
      user.removeAllEffects();
    }

    if (stack.isEmpty()) {
      return new ItemStack(LeftOverItem);
    } else {
      if (user instanceof Player playerEntity && !playerEntity.getAbilities().instabuild) {
        ItemStack itemStack = new ItemStack(LeftOverItem);
        if (!playerEntity.getInventory().add(itemStack)) {
          playerEntity.drop(itemStack, false);
        }
      }

      return stack;
    }
  }

  @Override
  public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
    return ItemUtils.startUsingInstantly(world, user, hand);
  }
}
