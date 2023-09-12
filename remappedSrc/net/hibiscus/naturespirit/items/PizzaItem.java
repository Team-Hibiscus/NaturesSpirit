package net.hibiscus.naturespirit.items;

import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.registration.HibiscusBlocksAndItems;
import net.minecraft.ChatFormatting;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PizzaItem extends ItemNameBlockItem {
   public PizzaItem(Block block, Properties settings) {
      super(block, settings);
   }

   private static String[] forEachTopping(ItemStack pizza) {
      CompoundTag nbtCompound = pizza.getOrCreateTagElement("BlockStateTag");
      assert nbtCompound != null;
      String[] toppings = new String[9];
      toppings[0] = nbtCompound.getString("mushroom_topping");
      toppings[1] = nbtCompound.getString("green_olives_topping");
      toppings[2] = nbtCompound.getString("black_olives_topping");
      toppings[3] = nbtCompound.getString("beetroot_topping");
      toppings[4] = nbtCompound.getString("carrot_topping");
      toppings[5] = nbtCompound.getString("cod_topping");
      toppings[6] = nbtCompound.getString("chicken_topping");
      toppings[7] = nbtCompound.getString("pork_topping");
      toppings[8] = nbtCompound.getString("rabbit_topping");
      return toppings;
   }

   public void addBitesToPizza(ItemStack pizza) {
      CompoundTag nbtCompound = pizza.getOrCreateTagElement("BlockStateTag");
      assert nbtCompound != null;
      int pizzaSlice = this.asItem() == HibiscusBlocksAndItems.WHOLE_PIZZA ? 0 : this.asItem() == HibiscusBlocksAndItems.THREE_QUARTERS_PIZZA ? 1 : this.asItem() == HibiscusBlocksAndItems.HALF_PIZZA ? 2 : 3;
      nbtCompound.putInt("pizza_bites", pizzaSlice);
   }

   public void getAllToppings(ItemStack pizza) {
      CompoundTag nbtCompound = pizza.getOrCreateTagElement("BlockStateTag");
      assert nbtCompound != null;
      String[] toppings = forEachTopping(pizza);
      int j = 0;
      for(String topping : toppings) {
         if(topping.equals("true")) {
            j++;
         }
      }
      nbtCompound.putInt("toppings", j);
   }

   @Override public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
      addBitesToPizza(stack);
      getAllToppings(stack);
   }

   public void appendHoverText(ItemStack stack, @Nullable Level world, List <Component> tooltip, TooltipFlag context) {
      super.appendHoverText(stack, world, tooltip, context);
      String[] toppingBooleans = forEachTopping(stack);
      String[] toppingStrings = new String[9];
      toppingStrings[0] = "mushroom_topping";
      toppingStrings[1] = "green_olives_topping";
      toppingStrings[2] = "black_olives_topping";
      toppingStrings[3] = "beetroot_topping";
      toppingStrings[4] = "carrot_topping";
      toppingStrings[5] = "cod_topping";
      toppingStrings[6] = "chicken_topping";
      toppingStrings[7] = "pork_topping";
      toppingStrings[8] = "rabbit_topping";
      for(int i = 0; i < toppingBooleans.length; ++i) {
         if(toppingBooleans[i].equals("true")) {
            tooltip.add(Component.translatable("block.natures_spirit.pizza." + toppingStrings[i]).withStyle(ChatFormatting.GRAY));
         }
      }
   }

   public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
      ItemStack itemStack = super.finishUsingItem(stack, world, user);
      Item pizzaSlice = this.asItem() == HibiscusBlocksAndItems.WHOLE_PIZZA ? HibiscusBlocksAndItems.THREE_QUARTERS_PIZZA : this.asItem() == HibiscusBlocksAndItems.THREE_QUARTERS_PIZZA ? HibiscusBlocksAndItems.HALF_PIZZA : this.asItem() == HibiscusBlocksAndItems.HALF_PIZZA ? HibiscusBlocksAndItems.QUARTER_PIZZA : Items.AIR;

      Player holder = (Player) user;
      holder.awardStat(NatureSpirit.EAT_PIZZA_SLICE);
      int foodAmount = 0;
      float saturationModifier = 0F;
      String[] toppings = forEachTopping(stack);
      if(toppings[4].equals("true")) {
         foodAmount = foodAmount + 1;
         saturationModifier = saturationModifier + 0.1F;
      }
      if(toppings[7].equals("true")) {
         foodAmount = foodAmount + 2;
         saturationModifier = saturationModifier + 0.2F;
      }
      if(toppings[2].equals("true")) {
         foodAmount = foodAmount + 1;
         saturationModifier = saturationModifier + 0.1F;
      }
      if(toppings[1].equals("true")) {
         foodAmount = foodAmount + 2;
         saturationModifier = saturationModifier + 0.1F;
      }
      if(toppings[3].equals("true")) {
         foodAmount = foodAmount + 2;
         saturationModifier = saturationModifier + 0.1F;
      }
      if(toppings[6].equals("true")) {
         foodAmount = foodAmount + 2;
         saturationModifier = saturationModifier + 0.2F;
      }
      if(toppings[8].equals("true")) {
         foodAmount = foodAmount + 2;
         saturationModifier = saturationModifier + 0.2F;
      }
      if(toppings[5].equals("true")) {
         foodAmount = foodAmount + 2;
         saturationModifier = saturationModifier + 0.2F;
      }
      if(toppings[0].equals("true")) {
         foodAmount = foodAmount + 2;
         saturationModifier = saturationModifier + 0.1F;
      }
      holder.getFoodData().eat(foodAmount, saturationModifier);

      if(((Player) user).getAbilities().instabuild) {
         return itemStack;
      }
      else {
         assert itemStack.getTag() != null;
         ItemStack itemStack1 = new ItemStack(pizzaSlice, 1);
         itemStack1.setTag(stack.getTag());
         return itemStack1;
      }
   }
}
