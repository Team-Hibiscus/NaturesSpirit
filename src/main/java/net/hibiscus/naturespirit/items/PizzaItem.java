package net.hibiscus.naturespirit.items;

import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusMiscBlocks;
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
import net.minecraft.nbt.NbtList;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PizzaItem extends AliasedBlockItem {
   public PizzaItem(Block block, Settings settings) {
      super(block, settings);
   }


   public void addBitesToPizza(ItemStack pizza) {
      NbtCompound nbtCompound = pizza.getOrCreateSubNbt("BlockEntityTag");
      NbtCompound nbtCompound2 = pizza.getOrCreateSubNbt("BlockStateTag");
      assert nbtCompound != null;
      int pizzaSlice = this.asItem() == HibiscusMiscBlocks.WHOLE_PIZZA ? 0 : this.asItem() == HibiscusMiscBlocks.THREE_QUARTERS_PIZZA ? 1 : this.asItem() == HibiscusMiscBlocks.HALF_PIZZA ? 2 : 3;
      nbtCompound.putInt("pizza_bites", pizzaSlice);
      nbtCompound2.putInt("pizza_bites", pizzaSlice);
   }

   public void getAllToppings(ItemStack pizza) {
      NbtCompound nbtCompound = pizza.getOrCreateSubNbt("BlockEntityTag");
      assert nbtCompound != null;
      NbtList nbtList = ((NbtList)nbtCompound.get("topping_types"));
      if(nbtList != null) {
         int j = nbtList.size();
         nbtCompound.putInt("toppings_number", j);
      }
   }

   @Override public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
      addBitesToPizza(stack);
      getAllToppings(stack);
   }

   public void appendTooltip(ItemStack stack, @Nullable World world, List <Text> tooltip, TooltipContext context) {
      super.appendTooltip(stack, world, tooltip, context);


      NbtCompound nbtCompound = stack.getOrCreateSubNbt("BlockEntityTag");
      assert nbtCompound != null;
      NbtList nbtList = ((NbtList)nbtCompound.get("topping_types"));
      if (nbtList != null) {
         int j = nbtList.size();

         for(int i = 0; i < j; ++i) {
            tooltip.add(Text.translatable("block.natures_spirit.pizza." + nbtList.getString(i).replace(":", ".")).formatted(Formatting.GRAY));
         }
      }
   }

   public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
      ItemStack itemStack = super.finishUsing(stack, world, user);
      Item pizzaSlice = this.asItem() == HibiscusMiscBlocks.WHOLE_PIZZA ? HibiscusMiscBlocks.THREE_QUARTERS_PIZZA : this.asItem() == HibiscusMiscBlocks.THREE_QUARTERS_PIZZA ? HibiscusMiscBlocks.HALF_PIZZA : this.asItem() == HibiscusMiscBlocks.HALF_PIZZA ? HibiscusMiscBlocks.QUARTER_PIZZA : Items.AIR;

      PlayerEntity holder = (PlayerEntity) user;
      holder.incrementStat(NatureSpirit.EAT_PIZZA_SLICE);
      int foodAmount = 2;
      float saturationModifier = 0.2F;
      NbtCompound nbtCompound = stack.getOrCreateSubNbt("BlockEntityTag");
      assert nbtCompound != null;
      NbtList nbtList = ((NbtList)nbtCompound.get("topping_types"));
      if (nbtList != null) {
         int j = nbtList.size();
         for(int i = 0; i < j; i++) {
            foodAmount++;
            saturationModifier = saturationModifier + 0.1F;
         }
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
