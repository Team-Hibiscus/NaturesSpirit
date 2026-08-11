package net.hibiscus.naturespirit.items;

import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.registration.NSDataComponents;
import net.hibiscus.naturespirit.blocks.PizzaBlock;
import net.hibiscus.naturespirit.blocks.block_entities.PizzaToppingVariant;
import net.hibiscus.naturespirit.registration.NSBlocks;
import net.hibiscus.naturespirit.registration.NSStatTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import java.util.List;

public class PizzaItem extends ItemNameBlockItem {

  public PizzaItem(Block block, Properties settings) {
    super(block, settings);
  }

  public void addBitesToPizza(ItemStack pizza) {
    BlockItemStateProperties blockStateComponent = pizza.getOrDefault(DataComponents.BLOCK_STATE, BlockItemStateProperties.EMPTY);
    CustomData blockEntityComponent = pizza.get(DataComponents.BLOCK_ENTITY_DATA);
    int pizzaSlice = this.asItem() == NSBlocks.WHOLE_PIZZA.get() ? 0 : this.asItem() == NSBlocks.THREE_QUARTERS_PIZZA.get() ? 1 : this.asItem() == NSBlocks.HALF_PIZZA.get() ? 2 : 3;
    pizza.set(DataComponents.BLOCK_STATE, blockStateComponent.with(PizzaBlock.BITES, pizzaSlice));
    if (blockEntityComponent == null) {
      CompoundTag nbtCompound = new CompoundTag();
      nbtCompound.putString("id", "natures_spirit:pizza_block_entity");
      pizza.set(DataComponents.BLOCK_ENTITY_DATA, CustomData.of(nbtCompound));
    }
  }

  @Override
  public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
    addBitesToPizza(stack);
  }

  @Override
  public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag type) {
    super.appendHoverText(stack, context, tooltip, type);
    List<PizzaToppingVariant> list = stack.get(NSDataComponents.TOPPINGS);
    if (list != null) {
      for (PizzaToppingVariant pizzaToppingVariant : list) {
        tooltip.add(Component.translatable("block.natures_spirit.pizza." + pizzaToppingVariant.translationKey().replace(":", ".")).withStyle(ChatFormatting.GRAY));
      }
    }
  }

  @Override
  public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
    ItemStack itemStack = super.finishUsingItem(stack, world, user);
    Item pizzaSlice = this.asItem() == NSBlocks.WHOLE_PIZZA.get() ? NSBlocks.THREE_QUARTERS_PIZZA.get()
            : this.asItem() == NSBlocks.THREE_QUARTERS_PIZZA.get() ? NSBlocks.HALF_PIZZA.get() : this.asItem() == NSBlocks.HALF_PIZZA.get() ? NSBlocks.QUARTER_PIZZA.get() : Items.AIR;

    Player holder = (Player) user;
    holder.awardStat(NSStatTypes.EAT_PIZZA_SLICE.get());
    List<PizzaToppingVariant> list = stack.get(NSDataComponents.TOPPINGS);
    if (list != null) {
      int foodAmount = 2;
      float saturationModifier = 0.2F;
      for (PizzaToppingVariant pizzaToppingVariant : list) {
        foodAmount += pizzaToppingVariant.hunger();
        saturationModifier += pizzaToppingVariant.saturation();
      }
      holder.getFoodData().eat(foodAmount, saturationModifier);
    }
    if (((Player) user).getAbilities().instabuild) {
      return itemStack;
    } else {
      ItemStack itemStack1 = new ItemStack(pizzaSlice, 1);
      itemStack1.transmuteCopy(stack.getItem(), 1);
      return itemStack1;
    }
  }
}
