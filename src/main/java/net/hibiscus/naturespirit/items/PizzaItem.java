package net.hibiscus.naturespirit.items;

import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.registration.HibiscusBlocksAndItems;
import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PizzaItem extends AliasedBlockItem {
   public PizzaItem(Block block, Settings settings) {
      super(block, settings);
   }

   private static Boolean[] forEachTopping(ItemStack pizza) {
      NbtCompound nbtCompound = pizza.getOrCreateSubNbt("BlockEntityTag");
      assert nbtCompound != null;
      Boolean[] toppings = new Boolean[9];
      toppings[0] = nbtCompound.getBoolean("mushroom_topping");
      toppings[1] = nbtCompound.getBoolean("green_olives_topping");
      toppings[2] = nbtCompound.getBoolean("black_olives_topping");
      toppings[3] = nbtCompound.getBoolean("beetroot_topping");
      toppings[4] = nbtCompound.getBoolean("carrot_topping");
      toppings[5] = nbtCompound.getBoolean("cod_topping");
      toppings[6] = nbtCompound.getBoolean("chicken_topping");
      toppings[7] = nbtCompound.getBoolean("pork_topping");
      toppings[8] = nbtCompound.getBoolean("rabbit_topping");
      return toppings;
   }

   public void addBitesToPizza(ItemStack pizza) {
      NbtCompound nbtCompound = pizza.getOrCreateSubNbt("BlockEntityTag");
      NbtCompound nbtCompound2 = pizza.getOrCreateSubNbt("BlockStateTag");
      assert nbtCompound != null;
      int pizzaSlice = this.asItem() == HibiscusBlocksAndItems.WHOLE_PIZZA ? 0 : this.asItem() == HibiscusBlocksAndItems.THREE_QUARTERS_PIZZA ? 1 : this.asItem() == HibiscusBlocksAndItems.HALF_PIZZA ? 2 : 3;
      nbtCompound.putInt("pizza_bites", pizzaSlice);
      nbtCompound2.putInt("pizza_bites", pizzaSlice);
   }

   public void getAllToppings(ItemStack pizza) {
      NbtCompound nbtCompound = pizza.getOrCreateSubNbt("BlockEntityTag");
      assert nbtCompound != null;
      Boolean[] toppings = forEachTopping(pizza);
      int j = 0;
      for(boolean topping : toppings) {
         if(topping) {
            j++;
         }
      }
      nbtCompound.putInt("toppings", j);
   }

   @Override public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
      addBitesToPizza(stack);
      getAllToppings(stack);
   }

   public void appendTooltip(ItemStack stack, @Nullable World world, List <Text> tooltip, TooltipContext context) {
      super.appendTooltip(stack, world, tooltip, context);
      Boolean[] toppingBooleans = forEachTopping(stack);
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
         if(toppingBooleans[i]) {
            tooltip.add(Text.translatable("block.natures_spirit.pizza." + toppingStrings[i]).formatted(Formatting.GRAY));
         }
      }
   }

   public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
      ItemStack itemStack = super.finishUsing(stack, world, user);
      Item pizzaSlice = this.asItem() == HibiscusBlocksAndItems.WHOLE_PIZZA ? HibiscusBlocksAndItems.THREE_QUARTERS_PIZZA : this.asItem() == HibiscusBlocksAndItems.THREE_QUARTERS_PIZZA ? HibiscusBlocksAndItems.HALF_PIZZA : this.asItem() == HibiscusBlocksAndItems.HALF_PIZZA ? HibiscusBlocksAndItems.QUARTER_PIZZA : Items.AIR;

      PlayerEntity holder = (PlayerEntity) user;
      holder.incrementStat(NatureSpirit.EAT_PIZZA_SLICE);
      int foodAmount = 0;
      float saturationModifier = 0F;
      Boolean[] toppings = forEachTopping(stack);
      if(toppings[4]) {
         foodAmount = foodAmount + 1;
         saturationModifier = saturationModifier + 0.1F;
      }
      if(toppings[7]) {
         foodAmount = foodAmount + 2;
         saturationModifier = saturationModifier + 0.2F;
      }
      if(toppings[2]) {
         foodAmount = foodAmount + 1;
         saturationModifier = saturationModifier + 0.1F;
      }
      if(toppings[1]) {
         foodAmount = foodAmount + 2;
         saturationModifier = saturationModifier + 0.1F;
      }
      if(toppings[3]) {
         foodAmount = foodAmount + 2;
         saturationModifier = saturationModifier + 0.1F;
      }
      if(toppings[6]) {
         foodAmount = foodAmount + 2;
         saturationModifier = saturationModifier + 0.2F;
      }
      if(toppings[8]) {
         foodAmount = foodAmount + 2;
         saturationModifier = saturationModifier + 0.2F;
      }
      if(toppings[5]) {
         foodAmount = foodAmount + 2;
         saturationModifier = saturationModifier + 0.2F;
      }
      if(toppings[0]) {
         foodAmount = foodAmount + 2;
         saturationModifier = saturationModifier + 0.1F;
      }
      holder.getHungerManager().add(foodAmount, saturationModifier);

      if(((PlayerEntity) user).getAbilities().creativeMode) {
         return itemStack;
      }
      else {
         assert itemStack.getNbt() != null;
         ItemStack itemStack1 = new ItemStack(pizzaSlice, 1);
         itemStack1.setNbt(stack.getNbt());
         return itemStack1;
      }
   }
}
